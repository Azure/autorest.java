/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.template.FluentPomTemplate;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.regex.Pattern;

public class Project {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), Project.class);

    private final String serviceName;
    private final ServiceDescription serviceDescription = new ServiceDescription();

    private final String namespace;
    private final String groupId = "com.azure.resourcemanager";
    private final String artifactId;
    private String version = "1.0.0-beta.1";
    private final PackageVersions packageVersions = new PackageVersions();
    private Changelog changelog;

    public static class PackageVersions {
        private String azureClientSdkParentVersion = "1.7.0";
        private String azureCoreVersion = "1.15.0";
        private String azureCoreManagementVersion = "1.2.1";
        private String jacocoMavenPlugin = "0.8.5";

        public String getAzureClientSdkParentVersion() {
            return azureClientSdkParentVersion;
        }

        public String getAzureCoreVersion() {
            return azureCoreVersion;
        }

        public String getAzureCoreManagementVersion() {
            return azureCoreManagementVersion;
        }

        public String getJacocoMavenPlugin() {
            return jacocoMavenPlugin;
        }
    }

    private static class ServiceDescription {
        private String simpleDescription;
        private String clientDescription;
        private String tagDescription;

        private String getServiceDescription() {
            return String.format("%1$s %2$s %3$s",
                    simpleDescription,
                    clientDescription,
                    tagDescription);
        }

        public String getServiceDescriptionForPom() {
            return String.format("%1$s %2$s %3$s %4$s",
                    simpleDescription,
                    "For documentation on how to use this package, please see https://aka.ms/azsdk/java/mgmt.",
                    clientDescription,
                    tagDescription);
        }

        public String getServiceDescriptionForMarkdown() {
            return this.getServiceDescription() + " For documentation on how to use this package, please see [Azure Management Libraries for Java](https://aka.ms/azsdk/java/mgmt).";
        }
    }

    public Project(FluentClient fluentClient) {
        this(fluentClient.getManager().getServiceName(), fluentClient.getInnerClient().getClientDescription());
    }

    protected Project(String serviceName, String clientDescription) {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();

        this.serviceName = serviceName;
        this.namespace = JavaSettings.getInstance().getPackage();
        this.artifactId = FluentUtils.getArtifactId();

        settings.getArtifactVersion().ifPresent(version -> this.version = version);

        if (clientDescription == null) {
            clientDescription = "";
        }
        if (!clientDescription.isEmpty() && !clientDescription.endsWith(".")) {
            clientDescription += ".";
        }

        final String simpleDescriptionTemplate = "This package contains Microsoft Azure SDK for %1$s Management SDK.";
        final String tagDescriptionTemplate = "Package tag %1$s.";

        this.serviceDescription.simpleDescription = String.format(simpleDescriptionTemplate, serviceName);
        this.serviceDescription.clientDescription = clientDescription;
        this.serviceDescription.tagDescription = String.format(tagDescriptionTemplate, settings.getAutorestSettings().getTag());

        this.changelog = new Changelog(this);
    }

    public void integrateWithSdk() {
        FluentPomTemplate.setProject(this);

        findPackageVersions();

        updateChangelog();
    }

    private Optional<String> findSdkFolder() {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();
        Optional<String> sdkFolderOpt = settings.getAutorestSettings().getAzureLibrariesForJavaFolder();
        if (!sdkFolderOpt.isPresent()) {
            logger.info("'azure-libraries-for-java-folder' parameter not available");
        } else {
            if (!Paths.get(sdkFolderOpt.get()).isAbsolute()) {
                logger.info("'azure-libraries-for-java-folder' parameter is not an absolute path");
                sdkFolderOpt = Optional.empty();
            }
        }

        // try to deduct it from "output-folder"
        if (!sdkFolderOpt.isPresent()) {
            String outputFolder = settings.getAutorestSettings().getOutputFolder();
            if (outputFolder != null && Paths.get(outputFolder).isAbsolute()) {
                Path path = Paths.get(outputFolder).normalize();
                while (path != null) {
                    Path childPath = path;
                    path = path.getParent();

                    if ("sdk".equals(childPath.getFileName().toString())) {
                        // childPath = azure-sdk-for-java/sdk, path = azure-sdk-for-java
                        break;
                    }
                }
                if (path != null) {
                    logger.info("'azure-sdk-for-java' SDK folder '{}' deduced from 'output-folder' parameter", path.toString());
                    sdkFolderOpt = Optional.of(path.toString());
                }
            }
        }

        if (!sdkFolderOpt.isPresent()) {
            logger.warn("'azure-sdk-for-java' SDK folder not found, fallback to default versions for dependencies");
        }

        return sdkFolderOpt;
    }

    private void findPackageVersions() {
        Optional<String> sdkFolderOpt = findSdkFolder();
        if (!sdkFolderOpt.isPresent()) {
            return;
        }

        // find dependency version from versioning txt
        Path sdkPath = Paths.get(sdkFolderOpt.get());
        Path versionClientPath = sdkPath.resolve(Paths.get("eng", "versioning", "version_client.txt"));
        Path versionExternalPath = sdkPath.resolve(Paths.get("eng", "versioning", "external_dependencies.txt"));
        if (Files.isReadable(versionClientPath) && Files.isReadable(versionExternalPath)) {
            try {
                findPackageVersions(versionClientPath);
            } catch (IOException e) {
                logger.warn("Failed to parse 'version_client.txt'", e);
            }
            try {
                findPackageVersions(versionExternalPath);
            } catch (IOException e) {
                logger.warn("Failed to parse 'external_dependencies.txt'", e);
            }
        } else {
            logger.warn("'version_client.txt' or 'external_dependencies.txt' not found or not readable");
        }
    }

    private void findPackageVersions(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            reader.lines().forEach(line -> {
                checkArtifact(line, "org.jacoco:jacoco-maven-plugin").ifPresent(v -> packageVersions.jacocoMavenPlugin = v);
                checkArtifact(line, "com.azure:azure-core").ifPresent(v -> packageVersions.azureCoreVersion = v);
                checkArtifact(line, "com.azure:azure-core-management").ifPresent(v -> packageVersions.azureCoreManagementVersion = v);
                checkArtifact(line, "com.azure:azure-client-sdk-parent").ifPresent(v -> packageVersions.azureClientSdkParentVersion = v);
            });
        }
    }

    static Optional<String> checkArtifact(String line, String artifact) {
        if (line.startsWith(artifact + ";")) {
            String[] segments = line.split(Pattern.quote(";"));
            if (segments.length >= 2) {
                String version = segments[1];
                logger.info("Found version '{}' for artifact '{}'", version, artifact);
                return Optional.of(version);
            }
        }
        return Optional.empty();
    }

    private void updateChangelog() {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();
        String outputFolder = settings.getAutorestSettings().getOutputFolder();
        if (outputFolder != null && Paths.get(outputFolder).isAbsolute()) {
            Path changelogPath = Paths.get(outputFolder, "CHANGELOG.md");

            if (Files.isReadable(changelogPath)) {
                try (BufferedReader reader = Files.newBufferedReader(changelogPath, StandardCharsets.UTF_8)) {
                    this.changelog = new Changelog(reader);
                    logger.info("Update 'CHANGELOG.md' for version '{}'", version);
                    this.changelog.updateForVersion(this);
                } catch (IOException e) {
                    logger.warn("Failed to parse 'CHANGELOG.md'", e);
                }
            } else {
                logger.info("'CHANGELOG.md' not found or not readable");
            }
        } else {
            logger.warn("'output-folder' parameter is not an absolute path, fallback to default CHANGELOG.md");
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return this.serviceDescription.getServiceDescription();
    }

    public String getServiceDescriptionForPom() {
        return this.serviceDescription.getServiceDescriptionForPom();
    }

    public String getServiceDescriptionForMarkdown() {
        return this.serviceDescription.getServiceDescriptionForMarkdown();
    }

    public String getNamespace() {
        return namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }

    public PackageVersions getPackageVersions() {
        return packageVersions;
    }

    public Changelog getChangelog() {
        return changelog;
    }
}
