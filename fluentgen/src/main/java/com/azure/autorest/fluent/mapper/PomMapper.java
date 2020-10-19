/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.fluent.model.clientmodel.FluentClient;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.Arrays;

public class PomMapper {

    public Pom map(FluentClient fluentClient) {
        String serviceName = fluentClient.getProject().getServiceName();

        Pom pom = new Pom();
        pom.setGroupId(fluentClient.getProject().getGroupId());
        pom.setArtifactId(fluentClient.getProject().getArtifactId());
        pom.setVersion(fluentClient.getProject().getVersion());

        pom.setServiceName(serviceName);
        pom.setServiceDescription(fluentClient.getInnerClient().getClientDescription());

        pom.setDependencyIdentifiers(Arrays.asList(
                "com.azure:azure-core-management:1.0.0"
        ));

        if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
            pom.setParentIdentifier("com.azure:azure-client-sdk-parent:1.7.0");
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
