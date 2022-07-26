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

    protected static final String CLIENT_SDK_PARENT_PREFIX = "com.azure:azure-client-sdk-parent:";
    protected static final String JSON_PREFIX = "com.azure:azure-json:";
    protected static final String CORE_PREFIX = "com.azure:azure-core:";
    protected static final String CORE_HTTP_NETTY_PREFIX = "com.azure:azure-core-http-netty:";
    protected static final String CORE_TEST_PREFIX = "com.azure:azure-core-test:";
    protected static final String IDENTITY_PREFIX = "com.azure:azure-identity:";
    protected static final String JUNIT_JUPITER_ENGINE_PREFIX = "org.junit.jupiter:junit-jupiter-engine:";
    protected static final String MOCKITO_CORE_PREFIX = "org.mockito:mockito-core:";
    protected static final String SLF4J_SIMPLE_PREFIX = "org.slf4j:slf4j-simple:";

    protected static final String TEST_SUFFIX = ":test";

    private static final List<String> KNOWN_DEPENDENCY_PREFIXES = Arrays.asList(
            JSON_PREFIX,
            CORE_PREFIX,
            CORE_HTTP_NETTY_PREFIX,
            CORE_TEST_PREFIX,
            IDENTITY_PREFIX,
            JUNIT_JUPITER_ENGINE_PREFIX,
            SLF4J_SIMPLE_PREFIX
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
        if (JavaSettings.getInstance().isStreamStyleSerialization()) {
            dependencyIdentifiers.add(JSON_PREFIX + project.getPackageVersions().getAzureJsonVersion());
        }
        dependencyIdentifiers.add(CORE_PREFIX + project.getPackageVersions().getAzureCoreVersion());
        dependencyIdentifiers.add(CORE_HTTP_NETTY_PREFIX + project.getPackageVersions().getAzureCoreHttpNettyVersion());
        dependencyIdentifiers.add(JUNIT_JUPITER_ENGINE_PREFIX + project.getPackageVersions().getJunitVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(MOCKITO_CORE_PREFIX + project.getPackageVersions().getMockitoVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(CORE_TEST_PREFIX + project.getPackageVersions().getAzureCoreTestVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(IDENTITY_PREFIX + project.getPackageVersions().getAzureIdentityVersion() + TEST_SUFFIX);
        dependencyIdentifiers.add(SLF4J_SIMPLE_PREFIX + project.getPackageVersions().getSlf4jSimpleVersion() + TEST_SUFFIX);
        dependencyIdentifiers.addAll(project.getPomDependencyIdentifiers().stream()
                .filter(dependencyIdentifier -> KNOWN_DEPENDENCY_PREFIXES.stream().noneMatch(dependencyIdentifier::startsWith))
                .collect(Collectors.toList()));
        pom.setDependencyIdentifiers(dependencyIdentifiers);

        if (JavaSettings.getInstance().isSdkIntegration()) {
            pom.setParentIdentifier(CLIENT_SDK_PARENT_PREFIX + project.getPackageVersions().getAzureClientSdkParentVersion());
            pom.setParentRelativePath("../../parents/azure-client-sdk-parent");
        }

        return pom;
    }
}
