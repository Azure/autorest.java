/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;

import java.util.Locale;

public class Project {

    private final String serviceName;
    private final String serviceDescription;

    private final String namespace;
    private final String groupId = "com.azure.resourcemanager";
    private final String artifactId;
    private String version = "1.0.0-beta.1";

    public Project(FluentClient fluentClient) {
        this.serviceName = fluentClient.getManager().getServiceName();
        this.namespace = JavaSettings.getInstance().getPackage();
        this.artifactId = String.format("azure-resourcemanager-%1$s-generated", serviceName.toLowerCase(Locale.ROOT));

        FluentStatic.getFluentJavaSettings().getArtifactVersion().ifPresent(version -> this.version = version);

        this.serviceDescription = fluentClient.getInnerClient().getClientDescription();
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
