#!/usr/bin/env python3

#  Copyright (c) Microsoft Corporation. All rights reserved.
#  Licensed under the MIT License.

import os
import sys
import logging
import argparse
import subprocess
import glob
import json
import shutil
from typing import List

sdk_root: str

skip_artifacts: List[str] = [
    'azure-ai-anomalydetector',         # deprecated
    'azure-ai-vision-imageanalysis',    # temporary disabled for modification on Javadoc
    'azure-communication-messages'      # temporary disabled for error on partial-update
]


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--sdk-root',
        required=True,
        help='azure-sdk-for-java repository root.',
    )
    parser.add_argument(
        '--version',
        required=True,
        help='@azure-tools/typespec-java version.',
    )
    return parser.parse_args()


def update_emitter(version: str):
    emitter_package_json_path = os.path.join(sdk_root, 'eng/emitter-package.json')
    with open(emitter_package_json_path, mode='r', encoding='utf-8') as f:
        package_json = json.load(f)

    with open(emitter_package_json_path, mode='w', encoding='utf-8') as f:
        package_json['dependencies']['@azure-tools/typespec-java'] = version
        json.dump(package_json, f, indent=2)

    logging.info('Update emitter-package.json to use @azure-tools/typespec-java version %s', version)


def get_generated_folder_from_artifact(module_path: str, artifact: str, type: str) -> str:
    path = os.path.join(module_path, 'src', type, 'java', 'com')
    for seg in artifact.split('-'):
        path = os.path.join(path, seg)
    path = os.path.join(path, 'generated')
    return path


def update_sdks():
    for tsp_location_file in glob.glob(os.path.join(sdk_root, 'sdk/*/*/tsp-location.yaml')):
        module_path = os.path.dirname(tsp_location_file)
        artifact = os.path.basename(module_path)

        if artifact in skip_artifacts:
            continue

        generated_samples_path = os.path.join(
            module_path, get_generated_folder_from_artifact(module_path, artifact, 'samples'))
        generated_test_path = os.path.join(
            module_path, get_generated_folder_from_artifact(module_path, artifact, 'test'))
        generated_samples_exists = os.path.isdir(generated_samples_path)
        generated_test_exists = os.path.isdir(generated_test_path)

        logging.info('Generate for module %s', artifact)
        subprocess.check_call(['tsp-client', 'update'], cwd=module_path)

        if not generated_samples_exists:
            shutil.rmtree(generated_samples_path, ignore_errors=True)
        if not generated_test_exists:
            shutil.rmtree(generated_test_path, ignore_errors=True)

    cmd = ['git', 'add', '.']
    subprocess.check_call(cmd, cwd=sdk_root)


def main():
    global sdk_root

    args = vars(parse_args())
    sdk_root = args['sdk_root']

    update_emitter(args['version'])

    update_sdks()


if __name__ == '__main__':
    logging.basicConfig(
        stream=sys.stdout,
        level=logging.INFO,
        format='%(asctime)s %(levelname)s %(message)s',
        datefmt='%Y-%m-%d %X',
    )
    main()
