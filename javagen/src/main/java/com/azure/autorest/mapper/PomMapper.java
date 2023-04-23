// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.projectmodel.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PomMapper implements IMapper<Project, Pom> {

    protected static final String TEST_SUFFIX = ":test";

    private static final List<String> KNOWN_DEPENDENCY_PREFIXES;
    static {
        KNOWN_DEPENDENCY_PREFIXES = Arrays.stream(Project.Dependency.values())
                .map(dependency -> dependency.getGroupId() + ":" + dependency.getArtifactId() + ":")
                .collect(Collectors.toList());
    }

    @Override
    public Pom map(Project project) {
        Pom pom = new Pom();
        pom.setGroupId(project.getGroupId());
        pom.setArtifactId(project.getArtifactId());
        pom.setVersion(project.getVersion());

        pom.setServiceName(project.getServiceName() + " Management");
        pom.setServiceDescription(project.getServiceDescriptionForPom());

        List<String> dependencyIdentifiers = new ArrayList<>();
        if (JavaSettings.getInstance().isStreamStyleSerialization()) {
            dependencyIdentifiers.add(Project.Dependency.AZURE_JSON.getDependencyIdentifier());
            dependencyIdentifiers.add(Project.Dependency.AZURE_XML.getDependencyIdentifier());
        }
        dependencyIdentifiers.add(Project.Dependency.AZURE_CORE.getDependencyIdentifier());
        dependencyIdentifiers.add(Project.Dependency.AZURE_CORE_HTTP_NETTY.getDependencyIdentifier());
        dependencyIdentifiers.add(Project.Dependency.JUNIT_JUPITER_API.getDependencyIdentifier());
        dependencyIdentifiers.add(Project.Dependency.JUNIT_JUPITER_ENGINE.getDependencyIdentifier());
        dependencyIdentifiers.add(Project.Dependency.MOCKITO_CORE.getDependencyIdentifier());
        dependencyIdentifiers.add(Project.Dependency.AZURE_CORE_TEST.getDependencyIdentifier() + TEST_SUFFIX);
        dependencyIdentifiers.add(Project.Dependency.AZURE_IDENTITY.getDependencyIdentifier() + TEST_SUFFIX);
        dependencyIdentifiers.add(Project.Dependency.SLF4J_SIMPLE.getDependencyIdentifier() + TEST_SUFFIX);
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> KNOWN_DEPENDENCY_PREFIXES.stream().noneMatch(dependencyIdentifier::startsWith))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (project.isIntegratedWithSdk()) {
            pom.setParentIdentifier(Project.Dependency.AZURE_CLIENT_SDK_PARENT.getDependencyIdentifier());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        pom.setRequireCompilerPlugins(!project.isIntegratedWithSdk());

        return pom;
    }
}
