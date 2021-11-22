#!/usr/bin/env python3

import sys
import platform
import subprocess
import shutil
import logging
from os import path


AUTOREST_CORE_VERSION = '3.6.6'
OS_WINDOWS = platform.system().lower() == 'windows'


def main():
    script_path = path.abspath(path.dirname(sys.argv[0]))

    purview_output_path = path.join(script_path, 'sdk/purview/azure-analytics-purview-account')

    shutil.rmtree(purview_output_path, ignore_errors=True)

    logging.info(f'delete {purview_output_path}')

    # generate code
    cmd = f'autorest --input-file=https://github.com/Azure/azure-rest-api-specs/blob/main/specification/purview/data-plane/Azure.Analytics.Purview.Account/preview/2019-11-01-preview/account.json --version={AUTOREST_CORE_VERSION} --use=../ --java --low-level-client --output-folder={purview_output_path} --namespace=com.azure.analytics.purview.account --credential-types=tokencredential --credential-scopes=https://purview.azure.net/.default --sdk-integration --generate-samples'.split(' ')
    cmd[0] += ('.cmd' if OS_WINDOWS else '')
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
    subprocess.check_call(cmd, cwd=purview_output_path)

    logging.info('pass maven package')

    assert path.exists(path.join(purview_output_path, 'README.md'))
    logging.info('pass README.md')

    assert path.exists(path.join(purview_output_path, 'CHANGELOG.md'))
    logging.info('pass CHANGELOG.md')

    assert path.exists(path.join(purview_output_path, 'swagger/README_SPEC.md'))
    logging.info('pass swagger/README_SPEC.md')

    assert path.exists(path.join(purview_output_path, 'src/samples/java/com/azure/analytics/purview/account/generated'))
    logging.info('pass samples')

    assert path.exists(path.join(purview_output_path, 'src/test/java/com/azure/analytics/purview/account/ClientTests.java'))
    logging.info('pass tests')


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
