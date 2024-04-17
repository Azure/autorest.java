// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.implementation;

import com.azure.core.management.Region;
import com.azure.core.management.SystemData;
import com.azure.core.util.Context;
import com.cadl.armresourceprovider.fluent.models.TopLevelArmResourceInner;
import com.cadl.armresourceprovider.models.ProvisioningState;
import com.cadl.armresourceprovider.models.TopLevelArmResource;
import com.cadl.armresourceprovider.models.TopLevelArmResourceUpdate;
import com.cadl.armresourceprovider.models.TopLevelArmResourceUpdateProperties;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class TopLevelArmResourceImpl
    implements TopLevelArmResource, TopLevelArmResource.Definition, TopLevelArmResource.Update {
    private TopLevelArmResourceInner innerObject;

    private final com.cadl.armresourceprovider.ArmResourceProviderManager serviceManager;

    public String id() {
        return this.innerModel().id();
    }

    public String name() {
        return this.innerModel().name();
    }

    public String type() {
        return this.innerModel().type();
    }

    public String location() {
        return this.innerModel().location();
    }

    public Map<String, String> tags() {
        Map<String, String> inner = this.innerModel().tags();
        if (inner != null) {
            return Collections.unmodifiableMap(inner);
        } else {
            return Collections.emptyMap();
        }
    }

    public SystemData systemData() {
        return this.innerModel().systemData();
    }

    public List<String> configurationEndpoints() {
        List<String> inner = this.innerModel().configurationEndpoints();
        if (inner != null) {
            return Collections.unmodifiableList(inner);
        } else {
            return Collections.emptyList();
        }
    }

    public String userName() {
        return this.innerModel().userName();
    }

    public String userNames() {
        return this.innerModel().userNames();
    }

    public String accuserName() {
        return this.innerModel().accuserName();
    }

    public OffsetDateTime startTimeStamp() {
        return this.innerModel().startTimeStamp();
    }

    public ProvisioningState provisioningState() {
        return this.innerModel().provisioningState();
    }

    public Region region() {
        return Region.fromName(this.regionName());
    }

    public String regionName() {
        return this.location();
    }

    public String resourceGroupName() {
        return resourceGroupName;
    }

    public TopLevelArmResourceInner innerModel() {
        return this.innerObject;
    }

    private com.cadl.armresourceprovider.ArmResourceProviderManager manager() {
        return this.serviceManager;
    }

    private String resourceGroupName;

    private String topLevelArmResourceName;

    private TopLevelArmResourceUpdate updateProperties;

    public TopLevelArmResourceImpl withExistingResourceGroup(String resourceGroupName) {
        this.resourceGroupName = resourceGroupName;
        return this;
    }

    public TopLevelArmResource create() {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .createOrUpdate(resourceGroupName, topLevelArmResourceName, this.innerModel(), Context.NONE);
        return this;
    }

    public TopLevelArmResource create(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .createOrUpdate(resourceGroupName, topLevelArmResourceName, this.innerModel(), context);
        return this;
    }

    TopLevelArmResourceImpl(String name, com.cadl.armresourceprovider.ArmResourceProviderManager serviceManager) {
        this.innerObject = new TopLevelArmResourceInner();
        this.serviceManager = serviceManager;
        this.topLevelArmResourceName = name;
    }

    public TopLevelArmResourceImpl update() {
        this.updateProperties = new TopLevelArmResourceUpdate();
        return this;
    }

    public TopLevelArmResource apply() {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .updateWithResponse(resourceGroupName, topLevelArmResourceName, updateProperties, Context.NONE)
            .getValue();
        return this;
    }

    public TopLevelArmResource apply(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .updateWithResponse(resourceGroupName, topLevelArmResourceName, updateProperties, context)
            .getValue();
        return this;
    }

    TopLevelArmResourceImpl(TopLevelArmResourceInner innerObject,
        com.cadl.armresourceprovider.ArmResourceProviderManager serviceManager) {
        this.innerObject = innerObject;
        this.serviceManager = serviceManager;
        this.resourceGroupName = ResourceManagerUtils.getValueFromIdByName(innerObject.id(), "resourceGroups");
        this.topLevelArmResourceName
            = ResourceManagerUtils.getValueFromIdByName(innerObject.id(), "topLevelArmResources");
    }

    public TopLevelArmResource refresh() {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .getByResourceGroupWithResponse(resourceGroupName, topLevelArmResourceName, Context.NONE)
            .getValue();
        return this;
    }

    public TopLevelArmResource refresh(Context context) {
        this.innerObject = serviceManager.serviceClient()
            .getTopLevelArmResourceInterfaces()
            .getByResourceGroupWithResponse(resourceGroupName, topLevelArmResourceName, context)
            .getValue();
        return this;
    }

    public TopLevelArmResourceImpl withRegion(Region location) {
        this.innerModel().withLocation(location.toString());
        return this;
    }

    public TopLevelArmResourceImpl withRegion(String location) {
        this.innerModel().withLocation(location);
        return this;
    }

    public TopLevelArmResourceImpl withTags(Map<String, String> tags) {
        if (isInCreateMode()) {
            this.innerModel().withTags(tags);
            return this;
        } else {
            this.updateProperties.withTags(tags);
            return this;
        }
    }

    public TopLevelArmResourceImpl withUserName(String userName) {
        this.innerModel().withUserName(userName);
        return this;
    }

    public TopLevelArmResourceImpl withUserNames(String userNames) {
        this.innerModel().withUserNames(userNames);
        return this;
    }

    public TopLevelArmResourceImpl withAccuserName(String accuserName) {
        this.innerModel().withAccuserName(accuserName);
        return this;
    }

    public TopLevelArmResourceImpl withStartTimeStamp(OffsetDateTime startTimeStamp) {
        this.innerModel().withStartTimeStamp(startTimeStamp);
        return this;
    }

    public TopLevelArmResourceImpl withProperties(TopLevelArmResourceUpdateProperties properties) {
        this.updateProperties.withProperties(properties);
        return this;
    }

    private boolean isInCreateMode() {
        return this.innerModel().id() == null;
    }
}
