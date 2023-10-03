// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template;

import com.azure.autorest.model.projectmodel.Project;
import com.azure.autorest.util.TemplateUtil;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestProxyAssetsTemplate {

    private static class Assets {
        @JsonProperty("AssetsRepo")
        private String assetsRepo = "Azure/azure-sdk-assets";

        @JsonProperty("AssetsRepoPrefixPath")
        private String assetsRepoPrefixPath = "java";

        @JsonProperty("TagPrefix")
        private String tagPrefix;

        @JsonProperty("Tag")
        private String tag = "";

        public void setTagPrefix(String tagPrefix) {
            this.tagPrefix = tagPrefix;
        }
    }

    public String write(Project project) {
        Assets asserts = new Assets();
        String group;
        if (project.getSdkRepositoryUri().isPresent()) {
            String[] segments = project.getSdkRepositoryUri().get().split("/");
            group = segments[segments.length - 2];
        } else {
            // fallback to last segment of artifactId, this could be incorrect
            String[] segments = project.getArtifactId().split("-");
            group = segments[segments.length - 1];
        }
        asserts.setTagPrefix(String.format("java/%1$s/%2$s", group, project.getArtifactId()));
        return TemplateUtil.prettyPrintToJson(asserts);
    }
}
