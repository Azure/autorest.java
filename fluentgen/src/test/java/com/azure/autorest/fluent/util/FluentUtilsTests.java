/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.util.CodeNamer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FluentUtilsTests {

    @BeforeAll
    public static void ensurePlugin() {
        new TestUtils.MockFluentGen();
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
        Assertions.assertEquals("azure-resourcemanager-appservice-generated", FluentUtils.getArtifactIdFromPackageName("com.azure.resourcemanager.appservice.generated"));

        Assertions.assertEquals("azure-resourcemanager-appservice", FluentUtils.getArtifactIdFromPackageName("com.azure.resourcemanager.appservice"));
    }

    @Test
    public void testSplitFlattenedSerializedName() {
        Assertions.assertEquals(Collections.singletonList("odata.properties"), FluentUtils.splitFlattenedSerializedName("odata.properties".replace(".", "\\\\.")));

        Assertions.assertEquals(Arrays.asList("properties", "virtualNetworkSubnetId"), FluentUtils.splitFlattenedSerializedName("properties.virtualNetworkSubnetId"));

        Assertions.assertEquals(Arrays.asList("odata.properties", "virtualNetworkSubnetId"), FluentUtils.splitFlattenedSerializedName("odata.properties".replace(".", "\\\\.") + ".virtualNetworkSubnetId"));
    }
}
