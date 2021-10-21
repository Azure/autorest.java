/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.extension.base.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutorestSettings {

    private String tag;
    private String baseFolder;
    private String outputFolder;
    private String azureLibrariesForJavaFolder;
    private final List<String> inputFiles = new ArrayList<>();

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setBaseFolder(String baseFolder) {
        this.baseFolder = baseFolder;
    }

    public void setOutputFolder(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public void setAzureLibrariesForJavaFolder(String azureLibrariesForJavaFolder) {
        this.azureLibrariesForJavaFolder = azureLibrariesForJavaFolder;
    }

    public String getTag() {
        return tag;
    }

    public String getBaseFolder() {
        return baseFolder;
    }

    public String getOutputFolder() {
        return outputFolder;
    }

    public Optional<String> getAzureLibrariesForJavaFolder() {
        return Optional.ofNullable(azureLibrariesForJavaFolder);
    }

    public List<String> getInputFiles() {
        return inputFiles;
    }
}
