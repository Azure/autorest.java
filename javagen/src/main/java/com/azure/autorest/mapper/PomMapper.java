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

    private static final String CORE_PREFIX = "com.azure:azure-core:";
    private static final String CORE_HTTP_NETTY_PREFIX = "com.azure:azure-core-http-netty:";
    private static final String CORE_TEST_PREFIX = "com.azure:azure-core-test:";
    private static final String IDENTITY_PREFIX = "com.azure:azure-identity:";
    private static final String JUNIT_JUPITER_ENGINE_PREFIX = "org.junit.jupiter:junit-jupiter-engine:";

    private static final String TEST_SUFFIX = ":test";

    private static final List<String> KNOWN_DEPENDENCY_PREFIXES = Arrays.asList(
            CORE_PREFIX,
            CORE_HTTP_NETTY_PREFIX,
            CORE_TEST_PREFIX,
            IDENTITY_PREFIX,
            JUNIT_JUPITER_ENGINE_PREFIX
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
        dependencyIdentifiers.add(CORE_PREFIX + project.getPackageVersions().getAzureCoreVersion());
        dependencyIdentifiers.add(CORE_HTTP_NETTY_PREFIX + project.getPackageVersions().getAzureCoreHttpNettyVersion());
        dependencyIdentifiers.add(JUNIT_JUPITER_ENGINE_PREFIX + project.getPackageVersions().getJunitVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(CORE_TEST_PREFIX + project.getPackageVersions().getAzureCoreTestVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(IDENTITY_PREFIX + project.getPackageVersions().getAzureIdentityVersion() + TEST_SUFFIX);
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> KNOWN_DEPENDENCY_PREFIXES.stream().noneMatch(dependencyIdentifier::startsWith))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (JavaSettings.getInstance().isSdkIntegration()) {
            pom.setParentIdentifier("com.azure:azure-client-sdk-parent:" + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
