// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.mgmt.model.projectmodel;

import com.microsoft.typespec.http.client.generator.mgmt.FluentGen;
import com.microsoft.typespec.http.client.generator.mgmt.FluentGenAccessor;
import com.microsoft.typespec.http.client.generator.mgmt.TestUtils;
import com.microsoft.typespec.http.client.generator.mgmt.model.clientmodel.FluentClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

public class ProjectTests {

    private static FluentGenAccessor fluentgenAccessor;

    @BeforeAll
    public static void ensurePlugin() {
        FluentGen fluentgen = new TestUtils.MockFluentGen();
        fluentgenAccessor = new FluentGenAccessor(fluentgen);
    }

    @Test
    public void testFluentProject() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        FluentClient fluentClient = content.getFluentClient();

        FluentProject project = new FluentProject(fluentClient);
        Assertions.assertEquals("com.azure.resourcemanager", project.getGroupId());
        Assertions.assertEquals("azure-resourcemanager-mock", project.getArtifactId());
        Assertions.assertNotNull(project.getChangelog());

        // ignore for IO
        project.integrateWithSdk();

        Optional<String> coreMgmtVer = FluentProject.checkArtifact("com.azure:azure-core-management;1.1.0;1.2.0-beta.1", "com.azure:azure-core-management");
        Assertions.assertTrue(coreMgmtVer.isPresent());
        Assertions.assertEquals("1.1.0", coreMgmtVer.get());
    }
}
