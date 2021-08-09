/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.plugin.NewPlugin;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import org.slf4j.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FluentJavaSettings {

    private final Logger logger;

    private final NewPlugin host;

    /**
     * Java class names for extra Inner classes.
     */
    private final Set<String> javaNamesForAddInner = new HashSet<>();

    /**
     * Java class names for excluded Inner classes.
     */
    private final Set<String> javaNamesForRemoveInner = new HashSet<>();

    private final Set<String> javaNamesForRemoveModel = new HashSet<>();

    private final Set<String> javaNamesForPreserveModel = new HashSet<>();

//    /**
//     * Whether to generate property method with track1 naming (e.g. foo, withFoo), instead of track2 naming (e.g. getFoo, setFoo).
//     */
//    private boolean track1Naming = true;
//
//    /**
//     * Whether to treat read-only resource property as SubResource type.
//     */
//    private boolean resourcePropertyAsSubResource = false;

    /**
     * Operation group name for ungrouped operations.
     */
    private String nameForUngroupedOperations;

    /**
     * Naming override.
     */
    private final Map<String, String> namingOverride = new HashMap<>();

    private final Map<String, String> renameModel = new HashMap<>();

    private String pomFilename = "pom.xml";

    private String artifactVersion;

    private boolean generateSamples = false;

    private boolean sdkIntegration = false;

    private AutorestSettings autorestSettings;

    public FluentJavaSettings(NewPlugin host) {
        Objects.requireNonNull(host);
        this.host = host;
        this.logger = new PluginLogger(host, FluentJavaSettings.class);
        loadSettings();
    }

    public Set<String> getJavaNamesForAddInner() {
        return javaNamesForAddInner;
    }

    public Set<String> getJavaNamesForRemoveInner() {
        return javaNamesForRemoveInner;
    }

    public boolean isTrack1Naming() {
        return true;
        //return track1Naming;
    }

    public boolean isResourcePropertyAsSubResource() {
        return false;
        //return resourcePropertyAsSubResource;
    }

    public Optional<String> getNameForUngroupedOperations() {
        return Optional.ofNullable(nameForUngroupedOperations);
    }

    public Map<String, String> getNamingOverride() {
        return namingOverride;
    }

    public Map<String, String> getJavaNamesForRenameModel() {
        return renameModel;
    }

    public Set<String> getJavaNamesForRemoveModel() {
        return javaNamesForRemoveModel;
    }

    public Set<String> getJavaNamesForPreserveModel() {
        return javaNamesForPreserveModel;
    }

    public String getPomFilename() {
        return pomFilename;
    }

    public Optional<String> getArtifactVersion() {
        return Optional.ofNullable(artifactVersion);
    }

    public boolean isGenerateSamples() {
        return generateSamples;
    }

    public boolean isSdkIntegration() {
        return sdkIntegration;
    }

    public AutorestSettings getAutorestSettings() {
        if (autorestSettings == null) {
            autorestSettings = new AutorestSettings();

            loadStringSetting("tag", autorestSettings::setTag);

            loadStringSetting("base-folder", autorestSettings::setBaseFolder);
            loadStringSetting("output-folder", autorestSettings::setOutputFolder);
            loadStringSetting("azure-libraries-for-java-folder", autorestSettings::setAzureLibrariesForJavaFolder);

            List<Object> inputFiles = host.getValue(List.class, "input-file");
            if (inputFiles != null) {
                autorestSettings.getInputFiles().addAll(inputFiles.stream().map(Object::toString).collect(Collectors.toList()));
                logger.info("List of input files : {}", autorestSettings.getInputFiles());
            }
        }

        return autorestSettings;
    }

    private void loadSettings() {
        loadStringSetting("add-inner", s -> {
            if (!CoreUtils.isNullOrEmpty(s)) {
                javaNamesForAddInner.addAll(
                        Arrays.stream(s.split(Pattern.quote(",")))
                                .map(String::trim)
                                .filter(s1 -> !s1.isEmpty())
                                .collect(Collectors.toSet()));
            }
        });

        loadStringSetting("remove-inner", s -> {
            if (!CoreUtils.isNullOrEmpty(s)) {
                javaNamesForRemoveInner.addAll(
                        Arrays.stream(s.split(Pattern.quote(",")))
                                .map(String::trim)
                                .filter(s1 -> !s1.isEmpty())
                                .collect(Collectors.toSet()));
            }
        });

        loadStringSetting("rename-model", s -> {
            if (!CoreUtils.isNullOrEmpty(s)) {
                String[] renamePairs = s.split(Pattern.quote(","));
                for (String pair : renamePairs) {
                    String[] fromAndTo = pair.split(Pattern.quote(":"));
                    if (fromAndTo.length == 2) {
                        String from = fromAndTo[0];
                        String to = fromAndTo[1];
                        if (!CoreUtils.isNullOrEmpty(from) && !CoreUtils.isNullOrEmpty(to)) {
                            renameModel.put(from, to);
                        }
                    }
                }
            }
        });

        loadStringSetting("remove-model", s -> {
            if (!CoreUtils.isNullOrEmpty(s)) {
                javaNamesForRemoveModel.addAll(
                        Arrays.stream(s.split(Pattern.quote(",")))
                                .map(String::trim)
                                .filter(s1 -> !s1.isEmpty())
                                .collect(Collectors.toSet()));
            }
        });

        loadStringSetting("preserve-model", s -> {
            if (!CoreUtils.isNullOrEmpty(s)) {
                javaNamesForPreserveModel.addAll(
                        Arrays.stream(s.split(Pattern.quote(",")))
                                .map(String::trim)
                                .filter(s1 -> !s1.isEmpty())
                                .collect(Collectors.toSet()));
            }
        });

//        loadBooleanSetting("track1-naming", b -> track1Naming = b);
//        loadBooleanSetting("resource-property-as-subresource", b -> resourcePropertyAsSubResource = b);

        loadStringSetting("name-for-ungrouped-operations", s -> nameForUngroupedOperations = s);

        loadStringSetting("pom-file", s -> pomFilename = s);
        loadStringSetting("package-version", s -> artifactVersion = s);

        loadBooleanSetting("generate-samples", b -> generateSamples = b);

        loadBooleanSetting("sdk-integration", b -> sdkIntegration = b);

        Map<String, String> namingOverride = host.getValue(new TypeReference<Map<String, String>>() {}.getType(), "pipeline.fluentnamer.naming.override");
        if (namingOverride != null) {
            this.namingOverride.putAll(namingOverride);
        }
    }

    private void loadBooleanSetting(String settingName, Consumer<Boolean> action) {
        Boolean settingValue = host.getBooleanValue(settingName);
        logger.info("Option, boolean, {} : {}", settingName, settingValue);
        if (settingValue != null) {
            action.accept(settingValue);
        }
    }

    private void loadStringSetting(String settingName, Consumer<String> action) {
        String settingValue = host.getStringValue(settingName);
        logger.info("Option, string, {} : {}", settingName, settingValue);
        if (settingValue != null) {
            action.accept(settingValue);
        }
    }
}
