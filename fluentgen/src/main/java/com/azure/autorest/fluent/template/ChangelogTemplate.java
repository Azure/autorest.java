/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.util.FluentUtils;

public class ChangelogTemplate {

    public String write(Project project) {
        return FluentUtils.loadTextFromResource("Changelog.txt",
                "service-name", project.getServiceName(),
                "service-description", project.getServiceDescription(),
                "artifact-version", project.getVersion()
        );
    }
}
