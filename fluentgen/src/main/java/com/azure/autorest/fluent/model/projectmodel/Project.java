/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

public class Project {

    private static final Logger logger = LoggerFactory.getLogger(Project.class);

    private final String serviceName;
    private final String serviceDescription;

    private final String namespace;
    private final String groupId = "com.azure.resourcemanager";
    private final String artifactId;
    private String version = "1.0.0-beta.1";
    private final PackageVersions packageVersions = new PackageVersions();

    public static class PackageVersions {
        private String azureClientSdkParentVersion = "1.7.0";
        private String azureCoreManagementVersion = "1.0.0";
        private String jacocoMavenPlugin = "0.8.5";

        public String getAzureClientSdkParentVersion() {
            return azureClientSdkParentVersion;
        }

        public String getAzureCoreManagementVersion() {
            return azureCoreManagementVersion;
        }

        public String getJacocoMavenPlugin() {
            return jacocoMavenPlugin;
        }
    }

    public Project(FluentClient fluentClient) {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();

        this.serviceName = fluentClient.getManager().getServiceName();
        this.namespace = JavaSettings.getInstance().getPackage();
        this.artifactId = String.format("azure-resourcemanager-%1$s-generated", serviceName.toLowerCase(Locale.ROOT));

        settings.getArtifactVersion().ifPresent(version -> this.version = version);

        String clientDescription = fluentClient.getInnerClient().getClientDescription().trim();
        if (!clientDescription.isEmpty() && !clientDescription.endsWith(".")) {
            clientDescription += ".";
        }

        String serviceDescription = String.format(
                "This package contains Microsoft Azure SDK for %1$s Management SDK. %2$s Package tag %3$s.",
                serviceName,
                clientDescription,
                settings.getAutorestSettings().getTag());
        // TODO aka link to Lite guidance page
        this.serviceDescription = serviceDescription;
    }

    public void integrateWithSdk() {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();
        Optional<String> sdkFolderOpt = settings.getAutorestSettings().getAzureLibrariesForJavaFolder();
        if (!sdkFolderOpt.isPresent()) {
            logger.warn("azure-libraries-for-java-folder parameter not available");
            return;
        } else {
            if (!Paths.get(sdkFolderOpt.get()).isAbsolute()) {
                logger.warn("azure-libraries-for-java-folder parameter is not an absolute path");
                return;
            }
        }

        // find dependency version from versioning txt
        Path sdkPath = Paths.get(sdkFolderOpt.get());
        Path versionClientPath = sdkPath.resolve(Paths.get("eng", "versioning", "version_client.txt"));
        Path versionExternalPath = sdkPath.resolve(Paths.get("eng", "versioning", "external_dependencies.txt"));
        if (Files.isReadable(versionClientPath) && Files.isReadable(versionExternalPath)) {
            try {
                findPackageVersions(versionClientPath);
            } catch (IOException e) {
                logger.warn("Failed to parse version_client.txt", e);
            }
            try {
                findPackageVersions(versionExternalPath);
            } catch (IOException e) {
                logger.warn("Failed to parse external_dependencies.txt", e);
            }
        }
    }

    private void findPackageVersions(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line = reader.readLine();
            while (line != null) {
                checkArtifact(line, "org.jacoco:jacoco-maven-plugin").ifPresent(v -> packageVersions.jacocoMavenPlugin = v);
                checkArtifact(line, "com.azure:azure-core-management").ifPresent(v -> packageVersions.azureCoreManagementVersion = v);
                checkArtifact(line, "com.azure:azure-client-sdk-parent").ifPresent(v -> packageVersions.azureClientSdkParentVersion = v);

                line = reader.readLine();
            }
        }
    }

    private Optional<String> checkArtifact(String line, String artifact) {
        if (line.startsWith(artifact)) {
            String[] segments = line.split(Pattern.quote(";"));
            if (segments.length >= 2) {
                String version = segments[1];
                logger.info("Found version {} for artifact {}", version, artifact);
                return Optional.of(version);
            }
        }
        return Optional.empty();
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
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
}
