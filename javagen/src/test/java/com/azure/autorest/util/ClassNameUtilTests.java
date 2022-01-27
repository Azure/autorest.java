// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import org.junit.Assert;
import org.junit.Test;

public class ClassNameUtilTests {

    @Test
    public void testTruncateClassName() {
        String name = ClassNameUtil.truncateClassName(
                "com.azure.resourcemanager.deviceprovisioningservices",
                "src/samples/java",
                "com.azure.resourcemanager.deviceprovisioningservices.generated",
                "IotDpsResourceCheckProvisioningServiceNameAvailabilitySamples");
        Assert.assertEquals("IotDpsResourceCheckProvisioningServiceNameAvailabilit", name);

        name = ClassNameUtil.truncateClassName(
                "com.azure.resourcemanager.datafactory",
                "src/samples/java",
                "com.azure.resourcemanager.datafactory.generated",
                "DataFlowDebugSessionAddDataFlowSamples");
        Assert.assertEquals("DataFlowDebugSessionAddDataFlowSamples", name);
    }
}
