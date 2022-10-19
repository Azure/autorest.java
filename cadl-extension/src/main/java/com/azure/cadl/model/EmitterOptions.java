// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.cadl.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.IOException;

public class EmitterOptions {
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private String namespace;
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private String outputDir;
    @JsonDeserialize(using = EmptyStringToNullDeserializer.class)
    private String serviceName;
    private Boolean partialUpdate;

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

    public EmitterOptions setNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    public EmitterOptions setOutputDir(String outputDir) {
        this.outputDir = outputDir;
        return this;
    }

    public EmitterOptions setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this;
    }

    public EmitterOptions setPartialUpdate(Boolean partialUpdate) {
        this.partialUpdate = partialUpdate;
        return this;
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
