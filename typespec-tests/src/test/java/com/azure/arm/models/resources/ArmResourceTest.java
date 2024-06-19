// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.arm.models.resources;

import com.azure.arm.models.resources.models.TopLevelTrackedResource;
import com.utils.ArmUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ArmResourceTest {

    private final ResourcesManager manager = ResourcesManager.authenticate(
            ArmUtils.createTestHttpPipeline(),
            ArmUtils.getAzureProfile());

    @Test
    public void testTopLevel() {
        TopLevelTrackedResource resource = manager.topLevelTrackedResources().getByResourceGroup("test-rg", "top");
        Assertions.assertEquals("topLevel", resource.type());
    }
}
