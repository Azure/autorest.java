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

    protected static final String CORE_MANAGEMENT_PREFIX = "com.azure:azure-core-management:";

    public Pom map(FluentProject project) {
        Pom pom = new Pom();
        pom.setGroupId(project.getGroupId());
        pom.setArtifactId(project.getArtifactId());
        pom.setVersion(project.getVersion());

        pom.setServiceName(project.getServiceName() + " Management");
        pom.setServiceDescription(project.getServiceDescriptionForPom());

        List<String> dependencyIdentifiers = new ArrayList<>();
        dependencyIdentifiers.add(JSON_PREFIX + project.getPackageVersions().getAzureJsonVersion());
        dependencyIdentifiers.add(CORE_PREFIX + project.getPackageVersions().getAzureCoreVersion());
        dependencyIdentifiers.add(CORE_MANAGEMENT_PREFIX + project.getPackageVersions().getAzureCoreManagementVersion());
        if (JavaSettings.getInstance().isGenerateTests()) {
            dependencyIdentifiers.add(CORE_TEST_PREFIX + project.getPackageVersions().getAzureCoreTestVersion() + TEST_SUFFIX);
            dependencyIdentifiers.add(IDENTITY_PREFIX + project.getPackageVersions().getAzureIdentityVersion() + TEST_SUFFIX);
            dependencyIdentifiers.add(JUNIT_JUPITER_ENGINE_PREFIX + project.getPackageVersions().getJunitVersion() + TEST_SUFFIX);
            dependencyIdentifiers.add(SLF4J_SIMPLE_PREFIX + project.getPackageVersions().getSlf4jSimple() + TEST_SUFFIX);
        }
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> !dependencyIdentifier.startsWith(CORE_PREFIX)
                        && !dependencyIdentifier.startsWith(CORE_MANAGEMENT_PREFIX))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
            pom.setParentIdentifier(CLIENT_SDK_PARENT_PREFIX + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
