// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.arm.models.resources;

import com.azure.arm.models.resources.models.NestedProxyResource;
import com.azure.arm.models.resources.models.NestedProxyResourceProperties;
import com.azure.arm.models.resources.models.NestedProxyResourceUpdateProperties;
import com.azure.arm.models.resources.models.ProvisioningState;
import com.azure.arm.models.resources.models.TopLevelTrackedResource;
import com.azure.arm.models.resources.models.TopLevelTrackedResourceProperties;
import com.azure.arm.models.resources.models.TopLevelTrackedResourceUpdateProperties;
import com.azure.core.management.Region;
import com.azure.core.util.Context;
import org.utils.ArmUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ArmResourceTest {
    private static final String TOP_LEVEL_TRACKED_RESOURCE_ID = "/subscriptions/00000000-0000-0000-0000-000000000000/resourceGroups/test-rg/providers/Azure.Arm.Models.Resources/topLevelTrackedResources/top";
    private static final String TOP_LEVEL_TRACKED_RESOURCE_NAME = "top";
    private static final String TOP_LEVEL_TRACKED_RESOURCE_TYPE = "topLevel";
    private static final String NESTED_PROXY_RESOURCE_ID = "/subscriptions/00000000-0000-0000-0000-000000000000/resourceGroups/test-rg/providers/Azure.Arm.Models.Resources/topLevelTrackedResources/top/nestedProxyResources/nested";
    private static final String NESTED_PROXY_RESOURCE_NAME_TYPE = "nested";
    private static final String TOP_LEVEL_TRACKED_RESOURCE_LOCATION = Region.US_EAST.name();
    private static final String RESOURCE_DESCRIPTION_VALID = "valid";
    private static final String RESOURCE_DESCRIPTION_VALID2 = "valid2";
    private static final ProvisioningState RESOURCE_PROVISIONING_STATE = ProvisioningState.SUCCEEDED;
    private static final String RESOURCE_GROUP_NAME = "test-rg";
    private final ResourcesManager manager = ResourcesManager.authenticate(
            ArmUtils.createTestHttpPipeline(),
            ArmUtils.getAzureProfile());

    @Test public void testTopLevelTrackedResource() {
        // TopLevelTrackedResources.createOrReplace
        TopLevelTrackedResource  topLevelTrackedResource = manager.topLevelTrackedResources()
                .define(TOP_LEVEL_TRACKED_RESOURCE_NAME)
                .withRegion(Region.US_EAST)
                .withExistingResourceGroup(RESOURCE_GROUP_NAME)
                .withProperties(new TopLevelTrackedResourceProperties().withDescription(RESOURCE_DESCRIPTION_VALID))
                .create();
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_ID, topLevelTrackedResource.id());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_NAME, topLevelTrackedResource.name());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_TYPE, topLevelTrackedResource.type());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_LOCATION, topLevelTrackedResource.location());
        Assertions.assertNotNull(topLevelTrackedResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, topLevelTrackedResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, topLevelTrackedResource.properties().provisioningState());

        // TopLevelTrackedResources.listByResourceGroup
        List<TopLevelTrackedResource> topLevelTrackedResourceList = manager.topLevelTrackedResources()
                .listByResourceGroup(RESOURCE_GROUP_NAME).stream().collect(Collectors.toList());
        Assertions.assertEquals(1, topLevelTrackedResourceList.size());
        topLevelTrackedResourceList.forEach(resource -> {
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_ID, resource.id());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_NAME, resource.name());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_TYPE, resource.type());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_LOCATION, resource.location());
            Assertions.assertNotNull(resource.properties());
            Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, resource.properties().description());
            Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, resource.properties().provisioningState());
        });

        // TopLevelTrackedResources.listBySubscription
        topLevelTrackedResourceList = manager.topLevelTrackedResources()
                .list()
                .stream()
                .filter(resource -> TOP_LEVEL_TRACKED_RESOURCE_ID.equals(resource.id()))
                .collect(Collectors.toList());
        Assertions.assertEquals(1, topLevelTrackedResourceList.size());
        topLevelTrackedResourceList.forEach(resource -> {
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_ID, resource.id());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_NAME, resource.name());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_TYPE, resource.type());
            Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_LOCATION, resource.location());
            Assertions.assertNotNull(resource.properties());
            Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, resource.properties().description());
            Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, resource.properties().provisioningState());
        });

        // TopLevelTrackedResources.get
        topLevelTrackedResource = manager.topLevelTrackedResources()
                .getByResourceGroup(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME);
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_ID, topLevelTrackedResource.id());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_NAME, topLevelTrackedResource.name());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_TYPE, topLevelTrackedResource.type());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_LOCATION, topLevelTrackedResource.location());
        Assertions.assertNotNull(topLevelTrackedResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, topLevelTrackedResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, topLevelTrackedResource.properties().provisioningState());

        // TopLevelTrackedResources.update
        topLevelTrackedResource.update()
                .withProperties(
                        new TopLevelTrackedResourceUpdateProperties().withDescription(RESOURCE_DESCRIPTION_VALID2))
                .apply();
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_ID, topLevelTrackedResource.id());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_NAME, topLevelTrackedResource.name());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_TYPE, topLevelTrackedResource.type());
        Assertions.assertEquals(TOP_LEVEL_TRACKED_RESOURCE_LOCATION, topLevelTrackedResource.location());
        Assertions.assertNotNull(topLevelTrackedResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID2, topLevelTrackedResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, topLevelTrackedResource.properties().provisioningState());

        // TopLevelTrackedResources.delete
        manager.topLevelTrackedResources().delete(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME, Context.NONE);
    }

    @Test
    public void testNestedProxyResource() {
        // NestedProxyResources.createOrReplace
        NestedProxyResource nestedProxyResource = manager.nestedProxyResources()
                .define(NESTED_PROXY_RESOURCE_NAME_TYPE)
                .withExistingTopLevelTrackedResource(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME)
                .withProperties(new NestedProxyResourceProperties().withDescription(RESOURCE_DESCRIPTION_VALID))
                .create();
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_ID, nestedProxyResource.id());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.name());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.type());
        Assertions.assertNotNull(nestedProxyResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, nestedProxyResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, nestedProxyResource.properties().provisioningState());

        // NestedProxyResources.listByTopLevelTrackedResource
        List<NestedProxyResource> nestedProxyResourceList = manager.nestedProxyResources()
                .listByTopLevelTrackedResource(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME)
                .stream().collect(Collectors.toList());
        Assertions.assertEquals(1, nestedProxyResourceList.size());
        nestedProxyResourceList.forEach(resource -> {
            Assertions.assertEquals(NESTED_PROXY_RESOURCE_ID, resource.id());
            Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, resource.name());
            Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, resource.type());
            Assertions.assertNotNull(resource.properties());
            Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, resource.properties().description());
            Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, resource.properties().provisioningState());
        });

        // NestedProxyResources.get
        nestedProxyResource= manager.nestedProxyResources()
                .get(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME, NESTED_PROXY_RESOURCE_NAME_TYPE);
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_ID, nestedProxyResource.id());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.name());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.type());
        Assertions.assertNotNull(nestedProxyResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID, nestedProxyResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, nestedProxyResource.properties().provisioningState());

        // NestedProxyResources.update
        nestedProxyResource.update()
                .withProperties(
                        new NestedProxyResourceUpdateProperties().withDescription(RESOURCE_DESCRIPTION_VALID2))
                .apply();
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_ID, nestedProxyResource.id());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.name());
        Assertions.assertEquals(NESTED_PROXY_RESOURCE_NAME_TYPE, nestedProxyResource.type());
        Assertions.assertNotNull(nestedProxyResource.properties());
        Assertions.assertEquals(RESOURCE_DESCRIPTION_VALID2, nestedProxyResource.properties().description());
        Assertions.assertEquals(RESOURCE_PROVISIONING_STATE, nestedProxyResource.properties().provisioningState());

        // NestedProxyResources.delete
        manager.nestedProxyResources().delete(RESOURCE_GROUP_NAME, TOP_LEVEL_TRACKED_RESOURCE_NAME, NESTED_PROXY_RESOURCE_NAME_TYPE);
    }
}
