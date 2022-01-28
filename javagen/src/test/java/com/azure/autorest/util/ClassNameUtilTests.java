// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import org.junit.Assert;
import org.junit.Test;

public class ClassNameUtilTests {

    @Test
    public void testTruncateClassName() {
        // limit class name
        String name = ClassNameUtil.truncateClassName(
                "com.azure.resourcemanager.deviceprovisioningservices",
                "src/samples/java",
                "com.azure.resourcemanager.deviceprovisioningservices.generated",
                "IotDpsResourceCheckProvisioningServiceNameAvailabilitySamples");
        Assert.assertEquals("IotDpsResourceCheckProvisioningServiceNameAvailabilit", name);

        // do nothing as too little remaining length for class name
        name = ClassNameUtil.truncateClassName(
                "com.azure.resourcemanager.deviceprovisioningservicespadpadpadpadpadpad",
                "src/samples/java",
                "com.azure.resourcemanager.deviceprovisioningservicespadpadpadpadpadpad.generated",
                "IotDpsResourceCheckProvisioningServiceNameAvailabilitySamples");
        Assert.assertEquals("IotDpsResourceCheckProvisioningServiceNameAvailabilitySamples", name);

        // no change
        name = ClassNameUtil.truncateClassName(
                "com.azure.resourcemanager.datafactory",
                "src/samples/java",
                "com.azure.resourcemanager.datafactory.generated",
                "DataFlowDebugSessionAddDataFlowSamples");
        Assert.assertEquals("DataFlowDebugSessionAddDataFlowSamples", name);
    }
}
