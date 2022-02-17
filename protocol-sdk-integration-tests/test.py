#!/usr/bin/env python3

import sys
import platform
import subprocess
import shutil
import logging
from os import path


AUTOREST_CORE_VERSION = '3.6.6'
OS_WINDOWS = platform.system().lower() == 'windows'


def run(script_path: str, output_folder: str, json_path: str, namespace: str, security_scopes: str = None):
    logging.info(f'SDK for {json_path}')

    package_relative_path = namespace.replace('.', '/')

    shutil.rmtree(output_folder, ignore_errors=True)

    logging.info(f'delete {output_folder}')

    # generate code
    security = 'AADToken'
    security_scopes_str = f'--security={security}'
    if security_scopes:
        security_scopes_str += f' --security-scopes={security_scopes}'
    cmd = f'autorest --input-file={json_path} --version={AUTOREST_CORE_VERSION} --use=../ --java --low-level-client --output-folder={output_folder} --namespace={namespace} {security_scopes_str} --sdk-integration --generate-samples --generate-tests'.split(' ')
    cmd[0] += ('.cmd' if OS_WINDOWS else '')
    logging.info(' '.join(cmd))
    subprocess.check_call(cmd, cwd=script_path)

    logging.info('pass autorest')

    # build
    cmd = [
        'mvn' + ('.cmd' if OS_WINDOWS else ''),
        'clean',
        'package',
        '-Dmaven.javadoc.skip',
        '--no-transfer-progress'
    ]
    subprocess.check_call(cmd, cwd=output_folder)

    logging.info('pass maven package')

    assert path.exists(path.join(output_folder, 'README.md'))
    logging.info('pass README.md')

    assert path.exists(path.join(output_folder, 'CHANGELOG.md'))
    logging.info('pass CHANGELOG.md')

    assert path.exists(path.join(output_folder, 'swagger/README_SPEC.md'))
    logging.info('pass swagger/README_SPEC.md')

    assert path.exists(path.join(output_folder, f'src/samples/java/{package_relative_path}/generated'))
    logging.info('pass samples')

    assert path.exists(path.join(output_folder, f'src/test/java/{package_relative_path}/generated'))
    logging.info('pass tests')


def main():
    script_path = path.abspath(path.dirname(sys.argv[0]))

    purview_json_path = 'https://github.com/Azure/azure-rest-api-specs/blob/main/specification/purview/data-plane/Azure.Analytics.Purview.Account/preview/2019-11-01-preview/account.json'
    purview_output_path = path.join(script_path, 'sdk/purview/azure-analytics-purview-account')

    run(script_path, purview_output_path, purview_json_path, 'com.azure.analytics.purview.account', 'https://purview.azure.net/.default')

    translator_json_path = 'https://github.com/Azure/azure-rest-api-specs/blob/main/specification/cognitiveservices/data-plane/TranslatorText/stable/v1.0/TranslatorBatch.json'
    translator_output_path = path.join(script_path, 'sdk/ai/azure-ai-translator')

    run(script_path, translator_output_path, translator_json_path, 'com.azure.ai.translator', 'https://noop.azure.net/.default')


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
