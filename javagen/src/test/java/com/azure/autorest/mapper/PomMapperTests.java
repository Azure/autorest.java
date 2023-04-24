// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.MockUnitJavagen;
import com.azure.autorest.model.clientmodel.Pom;
import com.azure.autorest.model.projectmodel.Project;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class PomMapperTests {

    private static class MockProject extends Project {
        MockProject() {
            serviceName = "Mock";
            serviceDescription = "Mock Service";
            namespace = "com.azure.mock";
            artifactId = "azure-mock";

            pomDependencyIdentifiers.add("com.azure:azure-core-test:1.15.0");
        }
    }

    @Test
    public void testMergeDependencies() {
        MockUnitJavagen javagen = new MockUnitJavagen();
        Project mockProject = new MockProject();
        Pom pom = new PomMapper().map(mockProject);
        Assert.assertEquals("com.azure", pom.getGroupId());
        Assert.assertEquals("azure-mock", pom.getArtifactId());
        Assert.assertEquals("Mock", pom.getServiceName());
        List<String> dependencies = pom.getDependencyIdentifiers();
        Assert.assertTrue(dependencies.stream().anyMatch(d -> d.startsWith("com.azure:azure-core:")));
        Assert.assertTrue(dependencies.stream().anyMatch(d -> d.startsWith("com.azure:azure-core-test:")));
        Assert.assertTrue(dependencies.stream().noneMatch(d -> d.startsWith("com.azure:azure-core-test:15.0")));    // it should have higher version
    }
}
