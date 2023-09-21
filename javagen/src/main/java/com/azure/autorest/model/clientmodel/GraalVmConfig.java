// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraalVmConfig {

    private List<String> proxies = new ArrayList<>();
    private List<String> reflects = new ArrayList<>();
    private List<String> resources = new ArrayList<>();

    private GraalVmConfig() {

    }

    public List<String> getProxies() {
        return proxies;
    }

    public List<String> getReflects() {
        return reflects;
    }

    public List<String> getResources() {
        return resources;
    }

    // TODO: Builder
    public static GraalVmConfig fromClient(Collection<ClientModel> models, Collection<ServiceClient> serviceClients) {
        GraalVmConfig result = new GraalVmConfig();

        // Reflect
        result.reflects = models.stream()
                .map(m -> m.getPackage() + "." + m.getName())
                .collect(Collectors.toList());

        // Proxy
        result.proxies = serviceClients.stream()
                .flatMap(sc -> {
                    if (sc.getMethodGroupClients() != null) {
                        return sc.getMethodGroupClients().stream();
                    } else {
                        return Stream.empty();
                    }
                })
                .filter(m -> m.getProxy() != null)
                .map(m -> m.getPackage() + "." + m.getClassName() + "$" + m.getProxy().getName())
                .collect(Collectors.toList());
        result.proxies.addAll(serviceClients.stream()
                .filter(sc -> sc.getProxy() != null)
                .map(sc -> sc.getPackage() + "." + sc.getClassName() + "$" + sc.getProxy().getName())
                .collect(Collectors.toList()));

        return result;
    }

    private static class Reflect {
        @JsonProperty("name")
        private final String name;
        @JsonProperty("allDeclaredConstructors")
        private final boolean allDeclaredConstructors = true;
        @JsonProperty("allDeclaredFields")
        private final boolean allDeclaredFields = true;
        @JsonProperty("allDeclaredMethods")
        private final boolean allDeclaredMethods = true;

        private Reflect(String name) {
            this.name = name;
        }
    }

    static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    // TODO: Template
    public String toProxyConfigJson() {
        List<List<String>> result = proxies.stream().map(Collections::singletonList).collect(Collectors.toList());
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String toReflectConfigJson() {
        List<Reflect> result = reflects.stream().map(Reflect::new).collect(Collectors.toList());
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(result);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
