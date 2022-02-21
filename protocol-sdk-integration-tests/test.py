#!/usr/bin/env python3

import sys
import platform
import subprocess
import shutil
import logging
import json
import argparse
from os import path


AUTOREST_CORE_VERSION = '3.6.6'
OS_WINDOWS = platform.system().lower() == 'windows'


def run(script_path: str, output_folder: str, json_path: str, namespace: str,
        security: str, security_scopes: str = None, security_header_name: str = None):
    logging.info(f'SDK for {json_path}')

    package_relative_path = namespace.replace('.', '/')

    shutil.rmtree(output_folder, ignore_errors=True)

    logging.info(f'delete {output_folder}')

    # generate code
    security_str = f'--security={security}'
    if security_scopes:
        security_str += f' --security-scopes={security_scopes}'
    if security_header_name:
        security_str += f' --security-header-name={security_header_name}'
    cmd = f'autorest --input-file={json_path} --version={AUTOREST_CORE_VERSION} --use=../ --java --low-level-client --output-folder={output_folder} --namespace={namespace} {security_str} --sdk-integration --generate-samples --generate-tests'.split(' ')
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

    # verify
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

    with open(path.join(script_path, 'data-specs.json'), 'r', encoding='utf-8') as f_in:
        data_specs = json.load(f_in)

    for key, spec in data_specs.items():
        json_path = spec['input'][0]
        group = spec['group']
        module = spec['module']
        security = spec['security'] if 'security' in spec else 'AADToken'
        security_scopes = spec['security-scopes'] if 'security-scopes' in spec else None
        security_header_name = spec['security-header-name'] if 'security-header-name' in spec else None

        required = spec['required']

        output_path = path.join(script_path, 'sdk', group, module)
        namespace = 'com.' + module.replace('-', '.')

        logging.info(f'case {key}')
        run(script_path, output_path, json_path, namespace,
            security, security_scopes, security_header_name)


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
