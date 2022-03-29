// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.mapper.PomMapper;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FluentPomMapper extends PomMapper {

    public Pom map(FluentProject project) {
        Pom pom = new Pom();
        pom.setGroupId(project.getGroupId());
        pom.setArtifactId(project.getArtifactId());
        pom.setVersion(project.getVersion());

        pom.setServiceName(project.getServiceName() + " Management");
        pom.setServiceDescription(project.getServiceDescriptionForPom());

        List<String> dependencyIdentifiers = new ArrayList<>();
        dependencyIdentifiers.add("com.azure:azure-core:" + project.getPackageVersions().getAzureCoreVersion());
        dependencyIdentifiers.add("com.azure:azure-core-management:" + project.getPackageVersions().getAzureCoreManagementVersion());
        if (JavaSettings.getInstance().isGenerateTests()) {
            dependencyIdentifiers.add("com.azure:azure-core-test:" + project.getPackageVersions().getAzureCoreTestVersion() + ":test");
            dependencyIdentifiers.add("com.azure:azure-identity:" + project.getPackageVersions().getAzureIdentityVersion() + ":test");
            dependencyIdentifiers.add("org.slf4j:slf4j-simple:" + project.getPackageVersions().getSlf4jSimple() + ":test");
        }
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> !dependencyIdentifier.startsWith("com.azure:azure-core:")
                        && !dependencyIdentifier.startsWith("com.azure:azure-core-management:"))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
            pom.setParentIdentifier("com.azure:azure-client-sdk-parent:" + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
