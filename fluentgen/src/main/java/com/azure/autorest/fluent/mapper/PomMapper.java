/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.fluent.model.projectmodel.Project;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.Arrays;

public class PomMapper {

    public Pom map(Project project) {
        Pom pom = new Pom();
        pom.setGroupId(project.getGroupId());
        pom.setArtifactId(project.getArtifactId());
        pom.setVersion(project.getVersion());

        pom.setServiceName(project.getServiceName());
        pom.setServiceDescription(project.getServiceDescriptionForPom());

        pom.setDependencyIdentifiers(Arrays.asList(
                "com.azure:azure-core:" + project.getPackageVersions().getAzureCoreVersion(),
                "com.azure:azure-core-management:" + project.getPackageVersions().getAzureCoreManagementVersion(),
                "com.azure:azure-identity:" + project.getPackageVersions().getAzureIdentityVersion() + ":test",
                "com.azure:azure-core-test:" + project.getPackageVersions().getAzureCoreTestVersion() + ":test",
                "com.azure.resourcemanager:azure-resourcemanager-resources:" + project.getPackageVersions().getAzureResourceManagerResourcesVersion() + ":test"
        ));

        if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
            pom.setParentIdentifier("com.azure:azure-client-sdk-parent:" + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
