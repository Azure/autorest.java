/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.template;

import com.azure.autorest.model.projectmodel.Project;
import com.azure.autorest.util.TemplateUtil;

public class ReadmeTemplate {

    public String write(Project project) {
        return TemplateUtil.loadTextFromResource("Readme_protocol.txt",
                TemplateUtil.SERVICE_NAME, project.getServiceName(),
                TemplateUtil.SERVICE_DESCRIPTION, project.getServiceDescriptionForMarkdown(),
                TemplateUtil.GROUP_ID, project.getGroupId(),
                TemplateUtil.ARTIFACT_ID, project.getArtifactId(),
                TemplateUtil.ARTIFACT_VERSION, project.getVersion(),
                TemplateUtil.PACKAGE_NAME, project.getNamespace()
        );
    }
}
