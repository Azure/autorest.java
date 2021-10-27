/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProxyMethodExample {

    private final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), ProxyMethodExample.class);

    // https://azure.github.io/autorest/extensions/#x-ms-examples

    public static class ParameterValue {
        private final Object objectValue;

        private static final ObjectMapper PRETTY_PRINTER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        private static final ObjectMapper NORMAL_PRINTER = new ObjectMapper();

        public ParameterValue(Object objectValue) {
            this.objectValue = objectValue;
        }

        /**
         * @return the object value of the parameter
         */
        public Object getObjectValue() {
            return objectValue;
        }

        /**
         * Gets the un-escaped query value.
         *
         * This is done by heuristic, and not guaranteed to be correct.
         *
         * @return the un-escaped query value
         */
        public Object getUnescapedQueryValue() {
            Object unescapedValue = objectValue;
            if (objectValue instanceof String) {
                try {
                    unescapedValue = URLDecoder.decode((String) objectValue, StandardCharsets.UTF_8.name());
                } catch (UnsupportedEncodingException e) {
                    // NOOP
                }
            }
            return unescapedValue;
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
    private final String originalFile;
    private String relativeOriginalFileName;

    /**
     * @return the map of parameter name to parameter object values
     */
    public Map<String, ParameterValue> getParameters() {
        return parameters;
    }

    /**
     * @return value of "x-ms-original-file" extension
     */
    public String getOriginalFile() {
        return originalFile;
    }

    /**
     * Heuristically find relative path of the original file to the repository.
     *
     * For instance, "specification/resources/resource-manager/Microsoft.Authorization/stable/2020-09-01/examples/getDataPolicyManifest.json"
     *
     * @return the relative path of the original file
     */
    public String getRelativeOriginalFileName() {
        if (relativeOriginalFileName == null && !CoreUtils.isNullOrEmpty(this.getOriginalFile())) {
            String originalFileName = this.getOriginalFile();
            try {
                URL url = new URI(originalFileName).toURL();
                switch (url.getProtocol()) {
                    case "http":
                    case "https":
                    {
                        String[] segments = url.getPath().split(Pattern.quote("/"));
                        if (segments.length > 3) {
                            // first 3 should be owner, name, branch
                            originalFileName = Arrays.stream(segments)
                                    .filter(s -> !s.isEmpty())
                                    .skip(3)
                                    .collect(Collectors.joining("/"));
                        }
                        break;
                    }

                    case "file":
                    {
                        String[] segments = url.getPath().split(Pattern.quote("/"));
                        int resourceManagerOrDataPlaneSegmentIndex = -1;
                        for (int i = 0; i < segments.length; ++i) {
                            if ("resource-manager".equals(segments[i]) || "data-plane".equals(segments[i])) {
                                resourceManagerOrDataPlaneSegmentIndex = i;
                                break;
                            }
                        }
                        if (resourceManagerOrDataPlaneSegmentIndex > 2) {
                            originalFileName = Arrays.stream(segments)
                                    .skip(resourceManagerOrDataPlaneSegmentIndex - 2)
                                    .collect(Collectors.joining("/"));
                        }
                        break;
                    }

                    default:
                    {
                        LOGGER.error("Unknown protocol in x-ms-original-file: '{}'", originalFileName);
                        break;
                    }
                }
            } catch (MalformedURLException | URISyntaxException | IllegalArgumentException e) {
                LOGGER.error("Failed to parse x-ms-original-file: '{}'", originalFileName);
            }
            relativeOriginalFileName = originalFileName;
        }
        return relativeOriginalFileName;
    }

    // response is ignored for now

    private ProxyMethodExample(String originalFile) {
        this.originalFile = originalFile;
    }

    @Override
    public String toString() {
        return "ProxyMethodExample{" +
                "parameters=" + parameters +
                '}';
    }

    public static final class Builder {
        private final Map<String, ParameterValue> parameters = new HashMap<>();
        private String originalFile;

        public Builder() {
        }

        public Builder parameter(String parameterName, Object parameterValue) {
            if (parameterValue != null) {
                this.parameters.put(parameterName, new ParameterValue(parameterValue));
            }
            return this;
        }

        public Builder originalFile(String originalFile) {
            this.originalFile = originalFile;
            return this;
        }

        public ProxyMethodExample build() {
            ProxyMethodExample proxyMethodExample = new ProxyMethodExample(originalFile);
            proxyMethodExample.parameters.putAll(this.parameters);
            return proxyMethodExample;
        }
    }
}
