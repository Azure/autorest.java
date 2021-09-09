/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.TestUtils;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ProxyMethodExampleTests {

    @BeforeAll
    public static void ensurePlugin() {
        new TestUtils.MockFluentGen();
    }

    @Test
    public void testRelativeOriginalFileName() {
        ProxyMethodExample example = new ProxyMethodExample.Builder()
                .originalFile("file:///c:/github/azure-rest-api-specs/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/examples/PutDeploymentAtScope.json")
                .build();
        Assertions.assertEquals("specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/examples/PutDeploymentAtScope.json", example.getRelativeOriginalFileName());

        example = new ProxyMethodExample.Builder()
                .originalFile("https://raw.githubusercontent.com/Azure/azure-rest-api-specs/main/specification/resources/resource-manager/Microsoft.Authorization/stable/2020-09-01/examples/getDataPolicyManifest.json")
                .build();
        Assertions.assertEquals("specification/resources/resource-manager/Microsoft.Authorization/stable/2020-09-01/examples/getDataPolicyManifest.json", example.getRelativeOriginalFileName());

        // not able to parse
        example = new ProxyMethodExample.Builder()
                .originalFile("/c:/github/azure-rest-api-specs/specification/resources/resource-manager/Microsoft.Resources/stable/2019-08-01/examples/PutDeploymentAtScope.json")
                .build();
        Assertions.assertEquals(example.getOriginalFile(), example.getRelativeOriginalFileName());
    }
}
