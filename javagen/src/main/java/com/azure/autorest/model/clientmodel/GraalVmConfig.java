// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.util.TemplateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GraalVmConfig {

    private final List<String> proxies;
    private final List<String> reflects;
    private final boolean fluent;

    public GraalVmConfig(List<String> proxies, List<String> reflects, boolean fluent) {
        this.proxies = proxies;
        this.reflects = reflects;
        this.fluent = fluent;
    }

    private static class ReflectConfig {
        @JsonProperty("name")
        private final String name;
        @JsonProperty("allDeclaredConstructors")
        private final boolean allDeclaredConstructors = true;
        @JsonProperty("allDeclaredFields")
        private final boolean allDeclaredFields = true;
        @JsonProperty("allDeclaredMethods")
        private final boolean allDeclaredMethods = true;

        private ReflectConfig(String name) {
            this.name = name;
        }
    }

    private static class ResourceConfig {

        private static class Pattern {
            @JsonProperty("pattern")
            private final String pattern;

            private Pattern(String pattern) {
                this.pattern = pattern;
            }
        }

        private static class Resource {
            @JsonProperty("includes")
            private final List<Pattern> includes;

            public Resource(List<Pattern> includes) {
                this.includes = includes;
            }
        }

        @JsonProperty("resources")
        private final Resource resources;
        private final List<Object> bundles = Collections.emptyList();

        private ResourceConfig(String artifactId) {
            this.resources = new Resource(Collections.singletonList(
                    new Pattern("\\Q" + artifactId + ".properties" + "\\E")));
        }
    }

    public boolean generateResourceConfig() {
        return !this.fluent;
    }

    // TODO: Template
    public String toProxyConfigJson() {
        List<List<String>> result = proxies.stream().map(Collections::singletonList).collect(Collectors.toList());
        return TemplateUtil.prettyPrintToJson(result);
    }

    public String toReflectConfigJson() {
        List<ReflectConfig> result = reflects.stream().map(ReflectConfig::new).collect(Collectors.toList());
        return TemplateUtil.prettyPrintToJson(result);
    }

    public String toResourceConfigJson(String artifactId) {
        return TemplateUtil.prettyPrintToJson(new ResourceConfig(artifactId));
    }
}
