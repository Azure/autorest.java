/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.projectmodel;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.Client;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

public class Project {

    private static final Logger logger = new PluginLogger(Javagen.getPluginInstance(), Project.class);

    protected String serviceName;
    protected String serviceDescription;
    protected String namespace;
    protected String groupId = "com.azure";
    protected String artifactId;
    protected String version = "1.0.0-beta.1";
    protected final PackageVersions packageVersions = new PackageVersions();
    protected final List<String> pomDependencyIdentifiers = new ArrayList<>();
    protected String sdkRepositoryUri;

    private String apiVersion;

    public static class PackageVersions {
        private String azureClientSdkParentVersion = "1.7.0";
        private String azureCoreVersion = "1.23.1";
        private String azureCoreManagementVersion = "1.4.4";
        private String azureCoreHttpNettyVersion = "1.11.4";
        private String azureCoreTestVersion = "1.7.5";
        private String azureIdentityVersion = "1.4.2";
        private String junitVersion = "5.8.1";
        private String revapiMavenPlugin = "0.11.2";

        public String getAzureClientSdkParentVersion() {
            return azureClientSdkParentVersion;
        }

        public String getAzureCoreVersion() {
            return azureCoreVersion;
        }

        public String getAzureCoreManagementVersion() {
            return azureCoreManagementVersion;
        }

        public String getAzureCoreHttpNettyVersion() {
            return azureCoreHttpNettyVersion;
        }

        public String getAzureCoreTestVersion() {
            return azureCoreTestVersion;
        }

        public String getAzureIdentityVersion() {
            return azureIdentityVersion;
        }

        public String getJunitVersion() {
            return junitVersion;
        }

        public String getRevapiMavenPlugin() {
            return revapiMavenPlugin;
        }
    }

    protected Project() {
    }

    public Project(Client client, String apiVersion) {
        JavaSettings settings = JavaSettings.getInstance();
        String serviceName = settings.getServiceName();
        if (CoreUtils.isNullOrEmpty(serviceName)) {
            serviceName = client.getClientName();
        }

        this.serviceName = serviceName;
        this.namespace = JavaSettings.getInstance().getPackage();
        this.artifactId = getArtifactIdFromNamespace();

        this.serviceDescription = String.format("This package contains Microsoft Azure %1$s client library.", serviceName);

        this.apiVersion = apiVersion;
    }

    public void integrateWithSdk() {
        findPackageVersions();

        findPomDependencies();

        findSdkRepositoryUri();
    }

    protected void findSdkRepositoryUri() {
        JavaSettings settings = JavaSettings.getInstance();
        String outputFolder = settings.getAutorestSettings().getOutputFolder();
        if (outputFolder != null) {
            Path path = Paths.get(outputFolder).normalize();
            List<String> pathSegment = new ArrayList<>();
            while (path != null) {
                Path childPath = path;
                path = path.getParent();

                pathSegment.add(childPath.getFileName().toString());

                if ("sdk".equals(childPath.getFileName().toString())) {
                    // childPath = azure-sdk-for-java/sdk, path = azure-sdk-for-java
                    break;
                }
            }
            if (path != null) {
                Collections.reverse(pathSegment);
                sdkRepositoryUri = "https://github.com/Azure/azure-sdk-for-java/blob/main/" + String.join("/", pathSegment);
                logger.info("Repository URI '{}' deduced from 'output-folder' parameter", sdkRepositoryUri);
            }
        }
    }

    private Optional<String> findSdkFolder() {
        JavaSettings settings = JavaSettings.getInstance();
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

    protected void findPackageVersions() {
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
                checkArtifact(line, "org.revapi:revapi-maven-plugin").ifPresent(v -> packageVersions.revapiMavenPlugin = v);
                checkArtifact(line, "com.azure:azure-client-sdk-parent").ifPresent(v -> packageVersions.azureClientSdkParentVersion = v);
                checkArtifact(line, "com.azure:azure-core").ifPresent(v -> packageVersions.azureCoreVersion = v);
                checkArtifact(line, "com.azure:azure-core-management").ifPresent(v -> packageVersions.azureCoreManagementVersion = v);
                checkArtifact(line, "com.azure:azure-core-http-netty").ifPresent(v -> packageVersions.azureCoreHttpNettyVersion = v);
                checkArtifact(line, "com.azure:azure-core-test").ifPresent(v -> packageVersions.azureCoreTestVersion = v);
                checkArtifact(line, "com.azure:azure-identity").ifPresent(v -> packageVersions.azureIdentityVersion = v);
            });
        }
    }

    public static Optional<String> checkArtifact(String line, String artifact) {
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

    protected void findPomDependencies() {
        JavaSettings settings = JavaSettings.getInstance();
        String outputFolder = settings.getAutorestSettings().getOutputFolder();
        if (outputFolder != null && Paths.get(outputFolder).isAbsolute()) {
            Path pomPath = Paths.get(outputFolder, "pom.xml");

            if (Files.isReadable(pomPath)) {
                try {
                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(pomPath.toFile());
                    NodeList nodeList = doc.getDocumentElement().getChildNodes();
                    for (int i = 0; i < nodeList.getLength(); ++i) {
                        Node node = nodeList.item(i);
                        if (node.getNodeType() == Node.ELEMENT_NODE) {
                            Element elementNode = (Element) node;
                            if ("dependencies".equals(elementNode.getTagName())) {
                                NodeList dependencyNodeList = elementNode.getChildNodes();
                                for (int j = 0; j < dependencyNodeList.getLength(); ++j) {
                                    Node dependencyNode = dependencyNodeList.item(j);
                                    if (dependencyNode.getNodeType() == Node.ELEMENT_NODE) {
                                        Element dependencyElementNode = (Element) dependencyNode;
                                        if ("dependency".equals(dependencyElementNode.getTagName())) {
                                            String groupId = null;
                                            String artifactId = null;
                                            String version = null;
                                            String scope = null;
                                            NodeList itemNodeList = dependencyElementNode.getChildNodes();
                                            for (int k = 0; k < itemNodeList.getLength(); ++k) {
                                                Node itemNode = itemNodeList.item(k);
                                                if (itemNode.getNodeType() == Node.ELEMENT_NODE) {
                                                    Element elementItemNode = (Element) itemNode;
                                                    switch (elementItemNode.getTagName()) {
                                                        case "groupId":
                                                            groupId = ((Text) elementItemNode.getChildNodes().item(0)).getWholeText();
                                                            break;
                                                        case "artifactId":
                                                            artifactId = ((Text) elementItemNode.getChildNodes().item(0)).getWholeText();
                                                            break;
                                                        case "version":
                                                            version = ((Text) elementItemNode.getChildNodes().item(0)).getWholeText();
                                                            break;
                                                        case "scope":
                                                            scope = ((Text) elementItemNode.getChildNodes().item(0)).getWholeText();
                                                            break;
                                                    }
                                                }
                                            }

                                            if (groupId != null && artifactId != null && version != null) {
                                                String dependencyIdentifier = String.format("%s:%s:%s", groupId, artifactId, version);
                                                if (scope != null) {
                                                    dependencyIdentifier += ":" + scope;
                                                }
                                                this.pomDependencyIdentifiers.add(dependencyIdentifier);
                                                logger.info("Found dependency identifier '{}' from POM", dependencyIdentifier);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (IOException | ParserConfigurationException | SAXException e) {
                    logger.warn("Failed to parse 'pom.xml'", e);
                }
            } else {
                logger.info("'pom.xml' not found or not readable");
            }
        } else {
            logger.warn("'output-folder' parameter is not an absolute path, fall back to default dependencies");
        }
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return this.serviceDescription;
    }

    public String getServiceDescriptionForPom() {
        return this.serviceDescription;
    }

    public String getServiceDescriptionForMarkdown() {
        return this.serviceDescription;
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

    public String getApiVersion() {
        return apiVersion;
    }

    public List<String> getPomDependencyIdentifiers() {
        return pomDependencyIdentifiers;
    }

    public Optional<String> getSdkRepositoryUri() {
        return Optional.ofNullable(sdkRepositoryUri);
    }

    public boolean isGenerateSamples() {
        return JavaSettings.getInstance().isGenerateSamples();
    }

    private static String getArtifactIdFromNamespace() {
        JavaSettings settings = JavaSettings.getInstance();
        String artifactId = settings.getArtifactId();
        if (CoreUtils.isNullOrEmpty(artifactId)) {
            artifactId = settings.getPackage().toLowerCase(Locale.ROOT)
                    .replace("com.", "")
                    .replace(".", "-");
        }
        return artifactId;
    }
}
