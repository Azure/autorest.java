/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.projectmodel.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PomMapper implements IMapper<Project, Pom> {

    private static final String CORE_KEY = "com.azure:azure-core:";
    private static final String CORE_HTTP_NETTY_KEY = "com.azure:azure-core-http-netty:";
    private static final String CORE_TEST_KEY = "com.azure:azure-core-test:";
    private static final String IDENTITY_KEY = "com.azure:azure-identity:";
    private static final String JUNIT_JUPITER_ENGINE_KEY = "org.junit.jupiter:junit-jupiter-engine:";

    private static final List<String> KNOWN_DEPENDENCY_KEYS = Arrays.asList(
            CORE_KEY,
            CORE_HTTP_NETTY_KEY,
            CORE_TEST_KEY,
            IDENTITY_KEY,
            JUNIT_JUPITER_ENGINE_KEY
    );

    @Override
    public Pom map(Project project) {
        Pom pom = new Pom();
        pom.setGroupId(project.getGroupId());
        pom.setArtifactId(project.getArtifactId());
        pom.setVersion(project.getVersion());

        pom.setServiceName(project.getServiceName() + " Management");
        pom.setServiceDescription(project.getServiceDescriptionForPom());

        List<String> dependencyIdentifiers = new ArrayList<>();
        dependencyIdentifiers.add(CORE_KEY + project.getPackageVersions().getAzureCoreVersion());
        dependencyIdentifiers.add(CORE_HTTP_NETTY_KEY + project.getPackageVersions().getAzureCoreHttpNettyVersion());
        dependencyIdentifiers.add(JUNIT_JUPITER_ENGINE_KEY + project.getPackageVersions().getJunitVersion() + ":test");
        dependencyIdentifiers.add(CORE_TEST_KEY + project.getPackageVersions().getAzureCoreTestVersion() + ":test");
        dependencyIdentifiers.add(IDENTITY_KEY + project.getPackageVersions().getAzureIdentityVersion() + ":test");
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> KNOWN_DEPENDENCY_KEYS.stream().noneMatch(dependencyIdentifier::startsWith))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (JavaSettings.getInstance().isSdkIntegration()) {
            pom.setParentIdentifier("com.azure:azure-client-sdk-parent:" + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
