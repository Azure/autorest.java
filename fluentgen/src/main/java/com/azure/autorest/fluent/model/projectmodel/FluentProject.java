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
import com.azure.autorest.model.projectmodel.Project;
import org.slf4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FluentProject extends Project {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), FluentProject.class);

    protected final ServiceDescription serviceDescription = new ServiceDescription();

    private Changelog changelog;
    private final List<CodeSample> codeSamples = new ArrayList<>();

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

    public FluentProject(FluentClient fluentClient) {
        this(fluentClient.getManager().getServiceName(), fluentClient.getInnerClient().getClientDescription());
    }

    protected FluentProject(String serviceName, String clientDescription) {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();

        this.groupId = "com.azure.resourcemanager";

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

    @Override
    public void integrateWithSdk() {
//        FluentPomTemplate.setProject(this);

        findPackageVersions();

        findPomDependencies();

        updateChangelog();

        findCodeSamples();

        findSdkRepositoryUri();
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

    private void findCodeSamples() {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();
        String outputFolder = settings.getAutorestSettings().getOutputFolder();
        if (outputFolder != null && Paths.get(outputFolder).isAbsolute()) {
            Path srcTestJavaPath = Paths.get(outputFolder).resolve(Paths.get("src", "test", "java"));
            if (Files.isDirectory(srcTestJavaPath)) {
                try {
                    Files.walk(srcTestJavaPath).forEach(path -> {
                        if (!Files.isDirectory(path) && Files.isReadable(path)
                                && (path.getFileName().toString().endsWith("Tests.java")
                                || path.getFileName().toString().endsWith("Test.java"))) {
                            logger.info("Attempt to find code sample from test file '{}'", path);
                            codeSamples.add(CodeSample.fromTestFile(path));
                        }
                    });
                } catch (IOException e) {
                    logger.warn("Failed to walk path '" + srcTestJavaPath + "'", e);
                }
            }
        } else {
            logger.warn("'output-folder' parameter is not an absolute path, skip code samples");
        }
    }

    @Override
    public String getServiceDescription() {
        return this.serviceDescription.getServiceDescription();
    }

    @Override
    public String getServiceDescriptionForPom() {
        return this.serviceDescription.getServiceDescriptionForPom();
    }

    @Override
    public String getServiceDescriptionForMarkdown() {
        return this.serviceDescription.getServiceDescriptionForMarkdown();
    }

    public Changelog getChangelog() {
        return changelog;
    }

    public List<CodeSample> getCodeSamples() {
        return codeSamples;
    }

    @Override
    public boolean isGenerateSamples() {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();
        return settings.isGenerateSamples();
    }
}
