// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.model;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmitterOptions {
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    @JsonProperty(value="namespace")
    private String namespace;

    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    @JsonProperty(value="output-dir")
    private String outputDir;

    @JsonProperty(value = "flavor")
    private String flavor = "Azure";

    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    @JsonProperty(value="service-name")
    private String serviceName;

    @JsonProperty(value="service-versions")
    private List<String> serviceVersions;

    @JsonProperty(value = "generate-tests")
    private Boolean generateTests = true;

    @JsonProperty(value = "generate-samples")
    private Boolean generateSamples = true;

    @JsonProperty(value = "enable-sync-stack")
    private Boolean enableSyncStack = true;

    @JsonProperty(value = "stream-style-serialization")
    private Boolean streamStyleSerialization = true;

    @JsonProperty(value="partial-update")
    private Boolean partialUpdate;

    @JsonProperty(value="custom-types")
    private String customTypes;

    @JsonProperty(value="custom-types-subpackage")
    private String customTypeSubpackage;

    @JsonProperty(value="customization-class")
    private String customizationClass;

    @JsonProperty(value="include-api-view-properties")
    private Boolean includeApiViewProperties;
    @JsonProperty(value = "polling")
    private Map<String, JavaSettings.PollingDetails> polling = new HashMap<>();

    @JsonProperty(value = "arm")
    private Boolean arm = false;

    @JsonProperty(value="models-subpackage")
    private String modelsSubpackage;

    @JsonProperty(value="dev-options")
    private DevOptions devOptions;

    public String getNamespace() {
        return namespace;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public String getServiceName() {
        return serviceName;
    }

    public Boolean getPartialUpdate() {
        return partialUpdate;
    }

    public Boolean getGenerateTests() {
        return generateTests;
    }

    public Boolean getGenerateSamples() {
        return generateSamples;
    }

    public Boolean getEnableSyncStack() {
        return enableSyncStack;
    }

    public Boolean getStreamStyleSerialization() {
        return streamStyleSerialization;
    }

    public EmitterOptions setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public EmitterOptions setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public List<String> getServiceVersions() {
        return serviceVersions;
    }

    public DevOptions getDevOptions() {
        return devOptions;
    }

    public String getCustomTypes() {
        return customTypes;
    }

    public String getCustomTypeSubpackage() {
        return customTypeSubpackage;
    }

    public String getCustomizationClass() {
        return customizationClass;
    }

    public Boolean includeApiViewProperties() {
        return includeApiViewProperties;
    }

    public Map<String, JavaSettings.PollingDetails> getPolling() {
        return polling;
    }

    public void setPolling(Map<String, JavaSettings.PollingDetails> polling) {
        this.polling = polling;
    }

    public Boolean getArm() {
        return arm;
    }

    public String getModelsSubpackage() {
        return modelsSubpackage;
    }

    public String getFlavor() {
        return flavor;
    }

    public static class EmptyStringToNullDeserializer extends JsonDeserializer<String> {

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
            String value = jsonParser.readValueAs(String.class);
            if (value != null && value.isEmpty()) {
                return null;
            }
            return value;
        }
    }
}
