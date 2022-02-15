// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.core.http.HttpHeaders;
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ProxyMethodExample {

    private final Logger LOGGER = new PluginLogger(Javagen.getPluginInstance(), ProxyMethodExample.class);

    private static final ObjectMapper PRETTY_PRINTER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    // https://azure.github.io/autorest/extensions/#x-ms-examples
    // https://github.com/Azure/azure-rest-api-specs/blob/main/documentation/x-ms-examples.md

    public static class ParameterValue {
        private final Object objectValue;

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

    public static class Response {

        private final int statusCode;
        private final HttpHeaders httpHeaders;
        private final Object body;

        @SuppressWarnings("unchecked")
        public Response(int statusCode, Object response) {
            this.statusCode = statusCode;
            this.httpHeaders = new HttpHeaders();
            if (response instanceof Map) {
                Map<String, Object> responseMap = (Map<String, Object>) response;
                if (responseMap.containsKey("headers") && responseMap.get("headers") instanceof Map) {
                    Map<String, Object> headersMap = (Map<String, Object>) responseMap.get("headers");
                    headersMap.forEach((header, value) -> {
                        httpHeaders.add(header, value.toString());
                    });
                }
                this.body = responseMap.getOrDefault("body", null);
            } else {
                this.body = null;
            }
        }

        /** @return the status code */
        public int getStatusCode() {
            return statusCode;
        }

        /** @return the http headers */
        public HttpHeaders getHttpHeaders() {
            return httpHeaders;
        }

        /** @return the response body */
        public Object getBody() {
            return body;
        }

        @Override
        public String toString() {
            try {
                return "Response{" +
                        "statusCode=" + statusCode +
                        ", httpHeaders=" + httpHeaders +
                        ", body=" + PRETTY_PRINTER.writeValueAsString(body) +
                        '}';
            } catch (JsonProcessingException e) {
                return "Response{" +
                        "statusCode=" + statusCode +
                        ", httpHeaders=" + httpHeaders +
                        ", body=" + body +
                        '}';
            }
        }
    }

    private final Map<String, ParameterValue> parameters = new LinkedHashMap<>();
    private final Map<Integer, Response> responses = new LinkedHashMap<>();
    private final String originalFile;
    private String relativeOriginalFileName;
    private String codeSnippetIdentifier;

    /**
     * @return the map of parameter name to parameter object values
     */
    public Map<String, ParameterValue> getParameters() {
        return parameters;
    }

    /**
     * @return the map of status code to response
     */
    public Map<Integer, Response> getResponses() {
        return responses;
    }

    /**
     * @return the primary response
     */
    public Optional<Response> getPrimaryResponse() {
        if (responses.isEmpty()) {
            return Optional.empty();
        }

        Optional<Response> response = responses.values().stream()
                .filter(r -> r.statusCode / 100 == 2)
                .findFirst();
        if (!response.isPresent()) {
            response = responses.values().stream().findFirst();
        }
        return response;
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

    /**
     * identifier of the codesnippet label from codesnippet-maven-plugin
     * @see <a href="https://github.com/Azure/azure-sdk-tools/blob/main/packages/java-packages/codesnippet-maven-plugin/README.md">codesnippet-maven-plugin</a>
     * @return the identifier of the codesnippet label that wraps around the example code
     */
    public String getCodeSnippetIdentifier() {
        return codeSnippetIdentifier;
    }

    private ProxyMethodExample(String originalFile) {
        this.originalFile = originalFile;
    }

    @Override
    public String toString() {
        return "ProxyMethodExample{" +
                "parameters=" + parameters +
                ", responses=" + responses +
                '}';
    }

    public static final class Builder {
        private final Map<String, ParameterValue> parameters = new LinkedHashMap<>();
        private final Map<Integer, Response> responses = new LinkedHashMap<>();
        private String originalFile;
        private String codeSnippetIdentifier;

        public Builder() {
        }

        public Builder parameter(String parameterName, Object parameterValue) {
            if (parameterValue != null) {
                this.parameters.put(parameterName, new ParameterValue(parameterValue));
            }
            return this;
        }

        public Builder response(Integer statusCode, Object response) {
            this.responses.put(statusCode, new Response(statusCode, response));
            return this;
        }

        public Builder originalFile(String originalFile) {
            this.originalFile = originalFile;
            return this;
        }

        public Builder codeSnippetIdentifier(String identifier) {
            this.codeSnippetIdentifier = identifier;
            return this;
        }

        public ProxyMethodExample build() {
            ProxyMethodExample proxyMethodExample = new ProxyMethodExample(originalFile);
            proxyMethodExample.parameters.putAll(this.parameters);
            proxyMethodExample.responses.putAll(this.responses);
            proxyMethodExample.codeSnippetIdentifier = this.codeSnippetIdentifier;
            return proxyMethodExample;
        }
    }
}
