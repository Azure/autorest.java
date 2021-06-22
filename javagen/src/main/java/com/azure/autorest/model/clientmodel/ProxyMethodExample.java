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
    }

    private final Map<String, ParameterValue> parameters = new HashMap<>();

    public Map<String, ParameterValue> getParameters() {
        return parameters;
    }

    // response is ignored for now

    public ProxyMethodExample() {
    }

    @Override
    public String toString() {
        return "ProxyMethodExample{" +
                "parameters=" + parameters +
                '}';
    }

    public static final class Builder {
        private final Map<String, ParameterValue> parameters = new HashMap<>();

        public Builder() {
        }

        public Builder parameter(String parameterName, Object parameterValue) {
            this.parameters.put(parameterName, new ParameterValue(parameterValue));
            return this;
        }

        public ProxyMethodExample build() {
            ProxyMethodExample proxyMethodExample = new ProxyMethodExample();
            proxyMethodExample.parameters.putAll(this.parameters);
            return proxyMethodExample;
        }
    }
}
