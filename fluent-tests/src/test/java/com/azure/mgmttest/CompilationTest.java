/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.mgmttest;

import com.azure.core.management.Resource;
import com.azure.management.resources.fluentcore.collection.InnerSupportsGet;
import com.azure.management.resources.fluentcore.collection.InnerSupportsListing;
import com.azure.mgmttest.network.models.NetworkInterfaceInner;
import com.azure.mgmttest.network.models.NetworkInterfacesInner;
import com.azure.mgmttest.network.models.NetworkSecurityGroupInner;
import com.azure.mgmttest.resources.models.DeploymentExtendedInner;
import com.azure.mgmttest.resources.models.DeploymentsInner;
import com.azure.mgmttest.resources.models.ResourceGroupInner;
import com.azure.mgmttest.storage.models.StorageAccountInner;
import com.azure.mgmttest.storage.models.StorageAccountsInner;

import static org.mockito.Mockito.*;

public class CompilationTest {

    // Verify method signature at compile time.

    public void testOperationName() {
        // ListAll -> list, List -> listByResourceGroup (spec -> code).
        NetworkInterfacesInner networkInterfaces = mock(NetworkInterfacesInner.class);
        networkInterfaces.list();
        networkInterfaces.listAsync();
        networkInterfaces.listByResourceGroup(anyString());
        networkInterfaces.listByResourceGroupAsync(anyString());
        networkInterfaces.getByResourceGroup(anyString(), anyString());
        networkInterfaces.getByResourceGroupAsync(anyString(), anyString());
    }

    public void testInnerSupport() {
        // Add InnerSupportsListing to class.
        InnerSupportsListing<StorageAccountInner> storageAccounts = mock(StorageAccountsInner.class);
        storageAccounts.list();

        // Add InnerSupportsGet to class.
        InnerSupportsGet<DeploymentExtendedInner> deployments = mock(DeploymentsInner.class);
        deployments.getByResourceGroup(anyString(), anyString());

        InnerSupportsGet<NetworkInterfaceInner> networkInterfaces = mock(NetworkInterfacesInner.class);
        networkInterfaces.getByResourceGroup(anyString(), anyString());
    }

    public void testResourceType() {
        // ResourceGroup is regarded as subclass of Resource.
        Resource resourceGroup = mock(ResourceGroupInner.class);
        resourceGroup.getId();

        // NetworkSecurityGroup is subclass of Resource, but the id property from spec is not readonly,
        // hence it get pulled out from Resource.
        NetworkSecurityGroupInner networkSecurityGroup = mock(NetworkSecurityGroupInner.class);
        networkSecurityGroup.withId(anyString());
    }
}
