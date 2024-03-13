// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.scalar;

import com._specs_.azure.core.scalar.models.AzureLocationModel;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ScalarTests {
    private ScalarClient client = new ScalarClientBuilder()
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
            .buildClient();

    @Test
    public void testGet() {
        Assertions.assertEquals("eastus", client.get());
    }

    @Test
    public void testHeader() {
        client.headerMethod("eastus");
    }

    @Test
    public void testPost() {
        AzureLocationModel azureLocationModel = client.post(new AzureLocationModel("eastus"));
        Assertions.assertEquals("eastus", azureLocationModel.getLocation());
    }

    @Test
    public void testPut() {
        client.put("eastus");
    }

    @Test
    public void testQuery() {
        client.query("eastus");
    }
}
