// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class AutorestSettings {

    private String title;
    private String tag;
    private String baseFolder;
    private String outputFolder;
    private List<String> security = new ArrayList<>();
    private List<String> securityScopes = new ArrayList<>();
    private String securityHeaderName;
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

    public Optional<String> getTitle() {
        return Optional.ofNullable(this.title);
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getSecurity() {
        return this.security;
    }

    public void setSecurity(List<String> security) {
        this.security = Objects.requireNonNull(security);
    }

    public List<String> getSecurityScopes() {
        return this.securityScopes;
    }

    public void setSecurityScopes(List<String> securityScopes) {
        this.securityScopes = Objects.requireNonNull(securityScopes);
    }

    public Optional<String> getSecurityHeaderName() {
        return Optional.ofNullable(this.securityHeaderName);
    }

    public void setSecurityHeaderName(String securityHeaderName) {
        this.securityHeaderName = securityHeaderName;
    }
}
