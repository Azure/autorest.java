/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.util.FluentUtils;

import java.util.regex.Pattern;

public class ReadmeTemplate {

    public String write(Project project) {
        String contentTemplate = FluentUtils.loadTextFromResource("Readme.txt");
        return contentTemplate
                .replaceAll(Pattern.quote("{{service-name}}"), project.getServiceName())
                .replaceAll(Pattern.quote("{{service-description}}"), project.getServiceDescription())
                .replaceAll(Pattern.quote("{{group-id}}"), project.getGroupId())
                .replaceAll(Pattern.quote("{{artifact-id}}"), project.getArtifactId())
                .replaceAll(Pattern.quote("{{artifact-version}}"), project.getVersion());
    }
}
