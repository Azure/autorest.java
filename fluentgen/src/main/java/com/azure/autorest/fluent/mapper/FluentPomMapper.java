// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.model.projectmodel.FluentProject;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.mapper.PomMapper;
import com.azure.autorest.model.clientmodel.Pom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

        Set<String> addedDependencyPrefixes = new HashSet<>();
        List<String> dependencyIdentifiers = new ArrayList<>();
        addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                JSON_PREFIX, project.getPackageVersions().getAzureJsonVersion(), false);
        addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                CORE_PREFIX, project.getPackageVersions().getAzureCoreVersion(), false);
        addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                CORE_MANAGEMENT_PREFIX, project.getPackageVersions().getAzureCoreManagementVersion(), false);
        if (JavaSettings.getInstance().isGenerateTests()) {
            addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                    CORE_TEST_PREFIX, project.getPackageVersions().getAzureCoreTestVersion(), true);
            addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                    IDENTITY_PREFIX, project.getPackageVersions().getAzureIdentityVersion(), true);
            addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                    JUNIT_JUPITER_ENGINE_PREFIX, project.getPackageVersions().getJunitVersion(), true);
            addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                    MOCKITO_CORE_PREFIX, project.getPackageVersions().getMockitoVersion(), true);
            addDependencyIdentifier(dependencyIdentifiers, addedDependencyPrefixes,
                    SLF4J_SIMPLE_PREFIX, project.getPackageVersions().getSlf4jSimpleVersion(), true);
        }
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> addedDependencyPrefixes.stream().noneMatch(dependencyIdentifier::startsWith))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (FluentStatic.getFluentJavaSettings().isSdkIntegration()) {
            pom.setParentIdentifier(CLIENT_SDK_PARENT_PREFIX + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }

    private static void addDependencyIdentifier(List<String> dependencyIdentifiers, Set<String> prefixes,
                                                String prefix, String version, boolean isTestScope) {
        prefixes.add(prefix);
        dependencyIdentifiers.add(prefix + version + (isTestScope ? TEST_SUFFIX : ""));
    }
}
