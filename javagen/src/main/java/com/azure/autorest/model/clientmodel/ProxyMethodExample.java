/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.HashMap;
import java.util.Map;

public class ProxyMethodExample {

    // https://github.com/Azure/azure-rest-api-specs/blob/master/documentation/x-ms-examples.md

    public static class ParameterValue {
        private final Object objectValue;

        private static final ObjectMapper PRETTY_PRINTER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        private static final ObjectMapper NORMAL_PRINTER = new ObjectMapper();

        public ParameterValue(Object objectValue) {
            this.objectValue = objectValue;
        }

        public Object getObjectValue() {
            return objectValue;
        }

        @Override
        public String toString() {
            try {
                return "ParameterValue{" +
                        "objectValue=" + PRETTY_PRINTER.writeValueAsString(objectValue) +
                        '}';
            } catch (JsonProcessingException e) {
                return "ParameterValue{" +
                        "objectValue=" + objectValue +
                        '}';
            }
        }

        public String getJsonString() {
            try {
                return NORMAL_PRINTER.writeValueAsString(objectValue);
            } catch (JsonProcessingException e) {
                return objectValue.toString();
            }
        }
    }

    private final Map<String, ParameterValue> parameters = new HashMap<>();
    private final String xmsOriginalFile;

    public Map<String, ParameterValue> getParameters() {
        return parameters;
    }

    public String getXmsOriginalFile() {
        return xmsOriginalFile;
    }

    // response is ignored for now

    private ProxyMethodExample(String xmsOriginalFile) {
        this.xmsOriginalFile = xmsOriginalFile;
    }

    @Override
    public String toString() {
        return "ProxyMethodExample{" +
                "parameters=" + parameters +
                '}';
    }

    public static final class Builder {
        private final Map<String, ParameterValue> parameters = new HashMap<>();
        private String xmsOriginalFile;

        public Builder() {
        }

        public Builder parameter(String parameterName, Object parameterValue) {
            if (parameterValue != null) {
                this.parameters.put(parameterName, new ParameterValue(parameterValue));
            }
            return this;
        }

        public Builder xmsOriginalFile(String xmsOriginalFile) {
            this.xmsOriginalFile = xmsOriginalFile;
            return this;
        }

        public ProxyMethodExample build() {
            ProxyMethodExample proxyMethodExample = new ProxyMethodExample(xmsOriginalFile);
            proxyMethodExample.parameters.putAll(this.parameters);
            return proxyMethodExample;
        }
    }
}
