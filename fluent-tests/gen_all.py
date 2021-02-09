#!/usr/bin/env python3

import os
import sys
import platform
import json
import subprocess
import logging
import argparse
import dataclasses
import re
import datetime
from typing import List


AUTOREST_CORE_VERSION = '3.0.6350'
OS_WINDOWS = platform.system().lower() == 'windows'


def parse_args() -> argparse.Namespace:
    parser = argparse.ArgumentParser()
    parser.add_argument(
        '-u',
        '--use',
        default=os.path.join('.', 'autorest.java'),
        help='autorest.java source or tgz',
    )
    parser.add_argument(
        '-s',
        '--specs',
        help='specs folder',
    )
    parser.add_argument(
        '-t',
        '--test-output',
        default=os.path.join('.', 'fluent_generated'),
        help='Output for manual test',
    )
    parser.add_argument(
        '-r',
        '--report-output',
        default='.',
        help='Output for report',
    )
    return parser.parse_args()


def codegen(autorest_java: str, specs_dir: str, sdk: str, output_sdk_dir: str) -> subprocess.CompletedProcess:
    logging.info(f'generate code for RP: {sdk}')

    readme_dir = os.path.join(specs_dir, sdk, 'resource-manager', 'readme.md')
    namespace = f'azure.resourcemanager.{sdk}'.lower()
    namespace = re.sub('[^a-z.]', '', namespace)

    command = [
        'autorest' + ('.cmd' if OS_WINDOWS else ''),
        # '--verbose',
        '--version=' + AUTOREST_CORE_VERSION,
        '--java',
        '--use=' + autorest_java,
        '--pipeline.modelerfour.additional-checks=false',
        '--pipeline.modelerfour.lenient-model-deduplication=true',
        '--azure-arm',
        '--fluent=lite',
        '--java.fluent=lite',
        '--java.license-header=MICROSOFT_MIT_SMALL',
        '--java.output-folder=' + output_sdk_dir,
        '--java.namespace=' + namespace,
        readme_dir]

    logging.info(' '.join(command))
    result = subprocess.run(command, capture_output=True, text=True, encoding='utf-8')
    if result.returncode:
        logging.error(f'generate code failed for RP: {sdk}')
    return result


def maven_build(output_sdk_dir: str, sdk: str) -> subprocess.CompletedProcess:
    logging.info(f'maven build for RP: {sdk}')

    command = [
        'mvn' + ('.cmd' if OS_WINDOWS else ''),
        'package',
        '--no-transfer-progress'
    ]

    logging.info(' '.join(command))
    result = subprocess.run(command, capture_output=True, text=True, encoding='utf-8', cwd=output_sdk_dir)
    if result.returncode:
        logging.error(f'maven build failed for RP: {sdk}')
    return result


@dataclasses.dataclass
class CodegenResult:
    sdk: str
    success: bool
    failure_cause: str = ''
    stdout: str = ''
    stderr: str = ''


def report_markdown(report_dir: str, results: List[CodegenResult]) -> None:
    sdks_success = [result.sdk for result in results if result.success]
    sdks_failure_codegen_java = [result.sdk for result in results
                                 if not result.success and result.failure_cause.startswith('codegen_java')]
    sdks_failure_build = [result.sdk for result in results
                          if not result.success and result.failure_cause.startswith('build')]
    logging.info(f'success for PRs: {sdks_success}')
    if sdks_failure_codegen_java:
        logging.error(f'failure at java codegen for PRs: {sdks_failure_codegen_java}')
    if sdks_failure_build:
        logging.error(f'failure at build for PRs: {sdks_failure_build}')

    lines = []
    lines.append('# Java Codegen Report')
    lines.append('Generated at ' + datetime.datetime.now().isoformat())
    lines.append('## Success')
    lines.append('<details>')
    lines.append(f'<summary>RP count: {len(sdks_success)}</summary>')
    lines.append('')
    for sdk in sdks_success:
        lines.append('- ' + sdk)
    lines.append('</details>')
    lines.append('')

    lines.append('## Failure at Codegen')
    for sdk in sdks_failure_codegen_java:
        lines.append('- ' + sdk)
    lines.append('')

    lines.append('## Failure at Build')
    for sdk in sdks_failure_build:
        lines.append('- ' + sdk)
    lines.append('')

    lines.append('## Logs')
    for result in results:
        if not result.success:
            lines.append('<details>')
            lines.append(f'<summary>{result.sdk}</summary>')
            lines.append('')
            lines.append('**stdout**')
            lines.append('```')
            lines.extend([line for line in result.stdout.split('\n') if not line.startswith('Progress (')])
            lines.append('```')
            lines.append('**stderr**')
            lines.append('```')
            lines.extend(result.stderr.split('\n'))
            lines.append('```')
            lines.append('</details>')
            lines.append('')

    with open(os.path.join(report_dir, 'report.md'), 'w') as f:
        f.write('\n'.join(lines))


def main():
    args = vars(parse_args())

    sdks = []
    specs_dir = os.path.join(args['specs'], 'specification')
    for sdk in os.listdir(specs_dir):
        sdk_dir = os.path.join(specs_dir, sdk)
        if os.path.exists(os.path.join(sdk_dir, 'resource-manager', 'readme.md')):
            sdks.append(sdk)

    sdks.sort()

    autorest_java = args['use']
    output_dir = args['test_output']
    report_dir = args['report_output']

    logging.info(f'prepare to generate for RPs: {sdks}')

    results = []
    for sdk in sdks:
        output_sdk_dir = os.path.join(output_dir, f'azure-resourcemanager-{sdk}')
        codegen_result = codegen(autorest_java, specs_dir, sdk, output_sdk_dir)
        if not codegen_result.returncode:
            build_result = maven_build(output_sdk_dir, sdk)
            if not build_result.returncode:
                results.append(CodegenResult(sdk=sdk, success=True))
            else:
                logging.warning(build_result.stdout)
                logging.error(build_result.stderr)
                results.append(CodegenResult(sdk=sdk, success=False, failure_cause='build',
                                             stdout=build_result.stdout, stderr=build_result.stderr))
        else:
            logging.warning(codegen_result.stdout)
            error_log = codegen_result.stderr
            logging.error(error_log)
            if 'JavaCheck' not in error_log \
                    and ('Error: Plugin fluentnamer reported failure' in error_log
                         or 'Error: Plugin fluentgen reported failure' in error_log):
                cause = 'codegen_java'
            else:
                cause = 'codegen'
            results.append(CodegenResult(sdk=sdk, success=False, failure_cause=cause,
                                         stdout=codegen_result.stdout, stderr=error_log))

    report_markdown(report_dir, results)


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
