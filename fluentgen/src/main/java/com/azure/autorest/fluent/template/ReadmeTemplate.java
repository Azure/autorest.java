// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.projectmodel.CodeSample;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.util.TemplateUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ReadmeTemplate extends com.azure.autorest.template.ReadmeTemplate {

    public String write(FluentProject project) {
        StringBuilder sampleCodesBuilder = new StringBuilder();
        for (CodeSample codeSample : project.getCodeSamples()) {
            if (codeSample.getCode() != null) {
                sampleCodesBuilder.append("```java\n")
                        .append(codeSample.getCode())
                        .append("```\n");
            }
        }

        if (project.isGenerateSamples() && project.getSdkRepositoryUri().isPresent()) {
            sampleCodesBuilder.append("[Code snippets and samples]")
                    .append("(").append(project.getSdkRepositoryUri().get()).append("/SAMPLE.md").append(")")
                    .append("\n");
        }

        String impression = "";
        if (project.getSdkRepositoryPath().isPresent()) {
            try {
                impression = "![Impressions](https://azure-sdk-impressions.azurewebsites.net/api/impressions/"
                        + URLEncoder.encode("azure-sdk-for-java/" + project.getSdkRepositoryPath().get() + "/README.png", StandardCharsets.UTF_8.name())
                        + ")";
            } catch (UnsupportedEncodingException e) {
                // NOOP
            }
        }

        return FluentUtils.loadTextFromResource("Readme.txt",
                TemplateUtil.SERVICE_NAME, project.getServiceName(),
                TemplateUtil.SERVICE_DESCRIPTION, project.getServiceDescriptionForMarkdown(),
                TemplateUtil.GROUP_ID, project.getGroupId(),
                TemplateUtil.ARTIFACT_ID, project.getArtifactId(),
                TemplateUtil.ARTIFACT_VERSION, project.getVersion(),
                TemplateUtil.MANAGER_CLASS, FluentStatic.getFluentManager().getType().getName(),
                TemplateUtil.SAMPLE_CODES, sampleCodesBuilder.toString(),
                TemplateUtil.IMPRESSION_PIXEL, impression
        );
    }
}
