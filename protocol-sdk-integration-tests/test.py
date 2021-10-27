import sys
import platform
import subprocess
import logging
from os import path


OS_WINDOWS = platform.system().lower() == 'windows'


def main():
    script_path = path.abspath(path.dirname(sys.argv[0]))

    # generate code
    cmd = f'autorest --input-file=https://github.com/Azure/azure-rest-api-specs/blob/main/specification/purview/data-plane/Azure.Analytics.Purview.Account/preview/2019-11-01-preview/account.json --use=../ --java --low-level-client --output-folder={script_path}/sdk/purview/azure-analytics-purview-account --namespace=com.azure.analytics.purview.account --credential-types=tokencredential --credential-scopes=https://purview.azure.net/.default --sdk-integration --generate-samples'.split(' ')
    cmd[0] += ('.cmd' if OS_WINDOWS else '')
    subprocess.check_call(cmd, cwd=script_path)

    # build
    cmd = [
        'mvn' + ('.cmd' if OS_WINDOWS else ''),
        'clean',
        'package',
        '-Dmaven.javadoc.skip',
        '--no-transfer-progress'
    ]
    subprocess.check_call(cmd, cwd=path.join(script_path, 'sdk/purview/azure-analytics-purview-account'))


if __name__ == "__main__":
    logging.basicConfig(level=logging.INFO,
                        format='%(asctime)s %(levelname)s %(message)s',
                        datefmt='%Y-%m-%d %X')
    main()
