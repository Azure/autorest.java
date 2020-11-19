/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.util.CodeNamer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FluentUtilsTests {

    @BeforeAll
    public static void ensurePlugin() {
        new Utils.MockFluentGen();
    }

    @Test
    public void testGetServiceName() {
        final String packageName = "com.azure.resourcemanager.appservice.generated";

        Assertions.assertEquals("Web", FluentUtils.getServiceNameFromClientName("WebManagementClient", packageName));

        Assertions.assertEquals("ResourceGraph", FluentUtils.getServiceNameFromClientName("ResourceGraphClient", packageName));

        Assertions.assertEquals("Appservice", CodeNamer.toPascalCase(FluentUtils.getServiceNameFromClientName("Web", packageName)));
    }

    @Test
    public void testGetArtifactId() {
        final String packageName = "com.azure.resourcemanager.appservice.generated";

        Assertions.assertEquals("azure-resourcemanager-appservice-generated", FluentUtils.getArtifactIdFromPackageName(packageName));
    }
}
