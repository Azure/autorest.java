/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.subscriptionidapiversion;

import fixtures.subscriptionidapiversion.models.SampleResourceGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class MicrosoftAzureUrlTests {

    private static MicrosoftAzureTestUrl client;

    @BeforeAll
    public static void setup() {
        client = new MicrosoftAzureTestUrlBuilder()
                .subscriptionId(UUID.randomUUID().toString())
                .buildClient();
    }

    @Test
    public void getSampleResourceGroup() {
        SampleResourceGroup resourceGroup = client.getGroups().getSampleResourceGroup("testgroup101");
        Assertions.assertEquals("West US", resourceGroup.getLocation());
    }
}
