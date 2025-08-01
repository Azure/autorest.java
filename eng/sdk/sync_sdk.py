#!/usr/bin/env python3

#  Copyright (c) Microsoft Corporation. All rights reserved.
#  Licensed under the MIT License.

import os
import sys
import logging
import argparse
import subprocess
import glob
import shutil
import json
from typing import List

sdk_root: str

skip_artifacts: List[str] = [
    "azure-ai-anomalydetector",  # deprecated
    # expect failure on below
    # "azure-developer-devcenter",  # 2 breaks introduced into stable api-version
    # "azure-ai-vision-face",  # SDK in development
]


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        "--sdk-root",
        type=str,
        required=True,
        help="azure-sdk-for-java repository root.",
    )
    parser.add_argument(
        "--package-json-path",
        type=str,
        required=True,
        help="path to package.json of typespec-java.",
    )
    parser.add_argument(
        "--dev-package",
        type=str,
        required=False,
        default="false",
        help="use build from the branch, instead of published typespec-java.",
    )
    return parser.parse_args()


def update_emitter(package_json_path: str, use_dev_package: bool):
    if use_dev_package:
        # we cannot use "tsp-client generate-config-files" in dev mode, as this command also updates the lock file
        logging.info("Update emitter-package.json")
        subprocess.check_call(
            [
                "pwsh",
                "./eng/common/scripts/typespec/New-EmitterPackageJson.ps1",
                "-PackageJsonPath",
                package_json_path,
                "-OutputDirectory",
                "eng",
            ],
            cwd=sdk_root,
        )

        # replace version with path to dev package
        dev_package_path = None
        typespec_extension_path = os.path.dirname(package_json_path)
        for file in os.listdir(typespec_extension_path):
            if file.endswith(".tgz"):
                dev_package_path = os.path.abspath(os.path.join(typespec_extension_path, file))
                logging.info(f'Found dev package at "{dev_package_path}"')
                break
        if dev_package_path:
            emitter_package_path = os.path.join(sdk_root, "eng", "emitter-package.json")
            with open(emitter_package_path, "r") as json_file:
                package_json = json.load(json_file)
            package_json["dependencies"]["@azure-tools/typespec-java"] = dev_package_path
            with open(emitter_package_path, "w") as json_file:
                logging.info(f'Update emitter-package.json to use typespec-java from "{dev_package_path}"')
                json.dump(package_json, json_file, indent=2)
        else:
            logging.error("Failed to locate the dev package.")

        logging.info("Update emitter-package-lock.json")
        subprocess.check_call(["tsp-client", "generate-lock-file"], cwd=sdk_root)
    else:
        logging.info("Update emitter-package.json and emitter-package-lock.json")
        subprocess.check_call(
            ["tsp-client", "generate-config-files", "--package-json", package_json_path], cwd=sdk_root
        )


def get_generated_folder_from_artifact(module_path: str, artifact: str, type: str) -> str:
    path = os.path.join(module_path, "src", type, "java", "com")
    for seg in artifact.split("-"):
        path = os.path.join(path, seg)
    path = os.path.join(path, "generated")
    return path


def update_sdks():
    failed_modules = []
    for tsp_location_file in glob.glob(os.path.join(sdk_root, "sdk/*/*/tsp-location.yaml")):
        module_path = os.path.dirname(tsp_location_file)
        artifact = os.path.basename(module_path)

        arm_module = "-resourcemanager-" in artifact

        if artifact in skip_artifacts:
            continue

        if os.path.dirname(module_path).endswith("-v2"):
            # skip modules on azure-core-v2
            logging.info(f"Skip azure-core-v2 module on path {module_path}")
            continue

        generated_samples_path = os.path.join(
            module_path, get_generated_folder_from_artifact(module_path, artifact, "samples")
        )
        generated_test_path = os.path.join(
            module_path, get_generated_folder_from_artifact(module_path, artifact, "test")
        )
        generated_samples_exists = os.path.isdir(generated_samples_path)
        generated_test_exists = os.path.isdir(generated_test_path)

        if arm_module:
            logging.info("Delete generated source code of resourcemanager module %s", artifact)
            shutil.rmtree(os.path.join(module_path, "src", "main", "resources"), ignore_errors=True)
            delete_generated_source_code(os.path.join(module_path, "src", "main", "java"))

        logging.info(f"Generate for module {artifact}")
        try:
            subprocess.check_call(["tsp-client", "update"], cwd=module_path)
        except subprocess.CalledProcessError:
            # one retry
            # sometimes customization have intermittent failure
            logging.warning(f"Retry generate for module {artifact}")
            try:
                subprocess.check_call(["tsp-client", "update", "--debug"], cwd=module_path)
            except subprocess.CalledProcessError:
                logging.error(f"Failed to generate for module {artifact}")
                failed_modules.append(artifact)

        if not arm_module:
            # run mvn package, as this is what's done in "TypeSpec-Compare-CurrentToCodegeneration.ps1" script
            subprocess.check_call(["mvn", "--no-transfer-progress", "codesnippet:update-codesnippet"], cwd=module_path)

        if arm_module:
            # revert mock test code
            cmd = ["git", "checkout", "src/test"]
            subprocess.check_call(cmd, cwd=module_path)

        if not generated_samples_exists:
            shutil.rmtree(generated_samples_path, ignore_errors=True)
        if not generated_test_exists:
            shutil.rmtree(generated_test_path, ignore_errors=True)

    # revert change on pom.xml, readme.md, changelog.md, etc.
    cmd = ["git", "checkout", "**/pom.xml"]
    subprocess.check_call(cmd, cwd=sdk_root)
    cmd = ["git", "checkout", "**/*.md"]
    subprocess.check_call(cmd, cwd=sdk_root)

    cmd = ["git", "add", "."]
    subprocess.check_call(cmd, cwd=sdk_root)

    if failed_modules:
        logging.error(f"Failed modules {failed_modules}")


def delete_generated_source_code(path: str) -> None:
    autorest_generated_header = "Code generated by Microsoft (R) AutoRest Code Generator"
    typespec_generated_header = "Code generated by Microsoft (R) TypeSpec Code Generator"
    if os.path.exists(path):
        for file in os.listdir(path):
            cur_path = os.path.join(path, file)
            if os.path.isdir(cur_path):
                # Recurse into subdirectory
                delete_generated_source_code(cur_path)
            else:
                try:
                    # Read file content and check for header
                    with open(cur_path, 'r', encoding='utf-8') as f:
                        content = f.read()
                    if autorest_generated_header in content or typespec_generated_header in content:
                        os.remove(cur_path)  # Delete the file
                except Exception as e:
                    # Skip files that can't be read (binary files, permission issues)
                    print(f"Warning: Could not process file {cur_path}: {e}")
                    continue


def main():
    global sdk_root

    args = vars(parse_args())
    sdk_root = args["sdk_root"]

    update_emitter(args["package_json_path"], args["dev_package"].lower() == "true")

    update_sdks()


if __name__ == "__main__":
    logging.basicConfig(
        stream=sys.stdout,
        level=logging.INFO,
        format="%(asctime)s %(levelname)s %(message)s",
        datefmt="%Y-%m-%d %X",
    )
    main()
