/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.mgmttest;

import com.azure.core.management.Resource;
import com.azure.management.resources.fluentcore.collection.InnerSupportsGet;
import com.azure.management.resources.fluentcore.collection.InnerSupportsListing;
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
        NetworkInterfacesInner nic = mock(NetworkInterfacesInner.class);
        nic.list();
        nic.listAsync();
        nic.listByResourceGroup(anyString());
        nic.listByResourceGroupAsync(anyString());
    }

    public void testInnerSupport() {
        InnerSupportsListing<StorageAccountInner> sa = mock(StorageAccountsInner.class);
        sa.list();

        InnerSupportsGet<DeploymentExtendedInner> de = mock(DeploymentsInner.class);
        de.getByResourceGroup(anyString(), anyString());
    }

    public void testResourceType() {
        Resource rg = mock(ResourceGroupInner.class);
        rg.getId();

        NetworkSecurityGroupInner nsg = mock(NetworkSecurityGroupInner.class);
        nsg.withId(anyString());
    }
}
