import sys
import os
import logging
import argparse
import requests
import dataclasses
import re
from os import path
from typing import List, Dict

SDK_CLIENT_VERSIONS_URI: str = \
    'https://raw.githubusercontent.com/Azure/azure-sdk-for-java/main/eng/versioning/version_client.txt'
SDK_EXTERNAL_VERSIONS_URI: str = \
    'https://raw.githubusercontent.com/Azure/azure-sdk-for-java/main/eng/versioning/external_dependencies.txt'
EXTERNAL_DEPENDENCIES_TXT_PATH: str = 'eng/versioning/external_dependencies.txt'

root_path: str = '.'


@dataclasses.dataclass(frozen=True)
class Package:
    group: str
    artifact: str


@dataclasses.dataclass(frozen=True)
class PackageVersion:
    package: Package
    version: str


def load_versions(content: str) -> Dict[Package, str]:
    packages = {}
    for line in content.splitlines():
        if not line.startswith('#'):
            segments = line.split(';')
            package = segments[0]
            version = None if len(segments) == 1 else segments[1]
            package_segments = package.split(':')
            if len(package_segments) == 2:
                packages[Package(package_segments[0], package_segments[1])] = version
    return packages


def fill_versions(package_versions: Dict[Package, str],
                  versions_list: List[Dict[Package, str]]) -> List[PackageVersion]:
    packages = []
    for item in package_versions.items():
        package = item[0]
        version = item[1]
        if not version:
            for versions in versions_list:
                if package in versions:
                    version = versions[package]
                    break
        if not version:
            raise ValueError(f'version not found for package {package.group}:{package.artifact}')
        packages.append(PackageVersion(package, version))
    return packages


def is_xml_node(line: str, node: str) -> bool:
    trimmed_line = line.strip()
    return trimmed_line.startswith(f'<{node}>') and trimmed_line.endswith(f'</{node}>')


def extract_xml_node_text(line: str) -> str:
    trimmed_line = line.strip()
    return re.match(r'<.*?>(.*)</.*?>', trimmed_line).group(1)


def replace_xml_node_text(line: str, text: str) -> str:
    return re.sub(r'(\s*<.*?>)(.*)(</.*?>\s*)', r'\g<1>' + text + r'\g<3>', line)


def update_pom(package_versions: List[PackageVersion]):
    versions = {package.package: package.version for package in package_versions}

    pom_files = ['customization-base/src/main/resources/pom.xml', 'pom.xml']
    for folder in os.listdir(root_path):
        if path.isdir(path.join(root_path, folder)) and path.isfile(path.join(root_path, folder, 'pom.xml')):
            pom_files.append(path.join(folder, 'pom.xml'))

    for pom_file in pom_files:
        with open(path.join(root_path, pom_file), encoding='utf-8') as f_in:
            lines = f_in.readlines()
            new_lines = []
            package = None
            for line in lines:
                if is_xml_node(line, 'groupId'):
                    package = Package(extract_xml_node_text(line), None)
                elif is_xml_node(line, 'artifactId'):
                    if package:
                        package = Package(package.group, extract_xml_node_text(line))
                elif is_xml_node(line, 'version'):
                    if package and package in versions:
                        line = replace_xml_node_text(line, versions[package])
                else:
                    package = None
                new_lines.append(line)
        if not lines == new_lines:
            with open(path.join(root_path, pom_file), 'w', encoding='utf-8') as f_out:
                f_out.write(''.join(new_lines))
                logging.info(f'update POM {pom_file}')


def main():
    global root_path

    parser = argparse.ArgumentParser(description='Sync versions with azure-sdk-for-java')
    args = parser.parse_args()

    script_path = path.abspath(path.dirname(sys.argv[0]))
    root_path = path.abspath(path.join(script_path, '../..'))

    with requests.get(SDK_CLIENT_VERSIONS_URI) as response:
        client_versions = load_versions(response.content.decode('utf-8'))
    with requests.get(SDK_EXTERNAL_VERSIONS_URI) as response:
        external_versions = load_versions(response.content.decode('utf-8'))
    with open(path.join(root_path, EXTERNAL_DEPENDENCIES_TXT_PATH), encoding='utf-8') as f_in:
        package_versions = load_versions(f_in.read())
    package_versions = fill_versions(package_versions, [client_versions, external_versions])
    update_pom(package_versions)


if __name__ == '__main__':
    logging.basicConfig(
        stream=sys.stdout,
        level=logging.INFO,
        format='%(asctime)s %(levelname)s %(message)s',
        datefmt='%Y-%m-%d %X',
    )
    main()
