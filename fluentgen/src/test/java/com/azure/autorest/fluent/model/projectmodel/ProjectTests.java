/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.projectmodel;

import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.FluentGenAccessor;
import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.fluent.model.clientmodel.FluentClient;
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
    public void testProject() {
        TestUtils.ContentLocks content = TestUtils.initContentLocks(fluentgenAccessor);
        FluentClient fluentClient = content.getFluentClient();

        Project project = new Project(fluentClient);
        Assertions.assertEquals("com.azure.resourcemanager", project.getGroupId());
        Assertions.assertEquals("azure-resourcemanager-mock", project.getArtifactId());
        Assertions.assertNotNull(project.getChangelog());

        // ignore for IO
        project.integrateWithSdk();

        Optional<String> coreMgmtVer = Project.checkArtifact("com.azure:azure-core-management;1.1.0;1.2.0-beta.1", "com.azure:azure-core-management");
        Assertions.assertTrue(coreMgmtVer.isPresent());
        Assertions.assertEquals("1.1.0", coreMgmtVer.get());
    }
}
