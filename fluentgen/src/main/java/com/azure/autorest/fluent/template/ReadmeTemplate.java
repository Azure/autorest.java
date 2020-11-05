/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.util.FluentUtils;

public class ReadmeTemplate {

    public String write(Project project) {
        return FluentUtils.loadTextFromResource("Readme.txt",
                TextTemplate.SERVICE_NAME, project.getServiceName(),
                TextTemplate.SERVICE_DESCRIPTION, project.getServiceDescriptionForMarkdown(),
                TextTemplate.GROUP_ID, project.getGroupId(),
                TextTemplate.ARTIFACT_ID, project.getArtifactId(),
                TextTemplate.ARTIFACT_VERSION, project.getVersion()
        );
    }
}
