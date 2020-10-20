/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.util.FluentJavaSettings;

import java.util.Locale;

public class Project {

    private final String serviceName;
    private final String serviceDescription;

    private final String namespace;
    private final String groupId = "com.azure.resourcemanager";
    private final String artifactId;
    private String version = "1.0.0-beta.1";

    public Project(FluentClient fluentClient) {
        FluentJavaSettings settings = FluentStatic.getFluentJavaSettings();

        this.serviceName = fluentClient.getManager().getServiceName();
        this.namespace = JavaSettings.getInstance().getPackage();
        this.artifactId = String.format("azure-resourcemanager-%1$s-generated", serviceName.toLowerCase(Locale.ROOT));

        settings.getArtifactVersion().ifPresent(version -> this.version = version);

        String clientDescription = fluentClient.getInnerClient().getClientDescription().trim();
        if (!clientDescription.endsWith(".")) {
            clientDescription += ".";
        }

        String serviceDescription = String.format(
                "This package contains Microsoft Azure SDK for %1$s Management SDK. %2$s Package tag %3$s.",
                serviceName,
                clientDescription,
                settings.getAutorestSettings().getTag());
        // TODO aka to Lite guidance page
        this.serviceDescription = serviceDescription;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public String getNamespace() {
        return namespace;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getVersion() {
        return version;
    }
}
