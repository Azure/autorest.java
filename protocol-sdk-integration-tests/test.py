#!/usr/bin/env python3

import sys
import platform
import subprocess
import shutil
import logging
import json
import argparse
from os import path


AUTOREST_CORE_VERSION = '3.8.1'
OS_WINDOWS = platform.system().lower() == 'windows'
MAVEN_CLI = 'mvn' + ('.cmd' if OS_WINDOWS else '')


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
    cmd = f'autorest --input-file={json_path} --version={AUTOREST_CORE_VERSION} --use=../ --java --low-level-client --output-folder={output_folder} --namespace={namespace} {security_str} --sdk-integration --generate-samples --generate-tests --generate-models'.split(' ')
    cmd[0] += ('.cmd' if OS_WINDOWS else '')
    logging.info(' '.join(cmd))
    subprocess.check_call(cmd, cwd=script_path)

    logging.info('pass autorest')

    # build
    cmd = [
        MAVEN_CLI,
        'clean',
        'package',
        '-DskipTests',
        '-Dmaven.javadoc.skip',
        '--no-transfer-progress'
    ]
    subprocess.check_call(cmd, cwd=output_folder)

    logging.info('pass maven package')

    # verify
    cmd = [
        MAVEN_CLI,
        'verify',
        '-Drevapi.skip',
        '-Dmaven.javadoc.skip',
        '--no-transfer-progress'
    ]
    subprocess.check_call(cmd, cwd=output_folder)

    logging.info('pass maven verify')

    # verify folder/files
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


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        '--all',
        dest='verify_all',
        required=False,
        default=False,
        action='store_true',
        help='Generate and verify all projects',
    )

    return parser.parse_args()


def main():
    args = parse_args()
    verify_all = args.verify_all

    logging.info('verify all projects' if verify_all else 'verify required projects')
    
    script_path = path.abspath(path.dirname(sys.argv[0]))

    # install code quality reports
    reports_path = path.join(script_path, 'eng/code-quality-reports')
    cmd = [
        MAVEN_CLI,
        'install',
        '-Dmaven.javadoc.skip',
        '--no-transfer-progress'
    ]
    subprocess.check_call(cmd, cwd=reports_path)

    # run for each package
    with open(path.join(script_path, 'data-specs.json'), 'r', encoding='utf-8') as f_in:
        data_specs = json.load(f_in)

    for key, spec in data_specs.items():
        json_path = spec['input'][0]
        group = spec['group']
        module = spec['module']
        security = spec['security'] if 'security' in spec else 'AADToken'
        security_scopes = spec['security-scopes'] if 'security-scopes' in spec else None
        security_header_name = spec['security-header-name'] if 'security-header-name' in spec else None

        required = spec['required'] if 'required' in spec else False
        skip = spec['skip'] if 'skip' in spec else False

        output_path = path.join(script_path, 'sdk', group, module)
        namespace = 'com.' + module.replace('-', '.')

        if (verify_all or required) and not skip:
            logging.info(f'generate and verify for {key}')
            run(script_path, output_path, json_path, namespace,
                security, security_scopes, security_header_name)


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
