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
        // ListAll -> list, List -> listByResourceGroup (spec -> code).
        NetworkInterfacesInner nic = mock(NetworkInterfacesInner.class);
        nic.list();
        nic.listAsync();
        nic.listByResourceGroup(anyString());
        nic.listByResourceGroupAsync(anyString());
    }

    public void testInnerSupport() {
        // Add InnerSupportsListing to class.
        InnerSupportsListing<StorageAccountInner> sa = mock(StorageAccountsInner.class);
        sa.list();

        // Add InnerSupportsGet to class.
        InnerSupportsGet<DeploymentExtendedInner> de = mock(DeploymentsInner.class);
        de.getByResourceGroup(anyString(), anyString());
    }

    public void testResourceType() {
        // ResourceGroup is regarded as subclass of Resource.
        Resource rg = mock(ResourceGroupInner.class);
        rg.getId();

        // NetworkSecurityGroup is subclass of Resource, but the id property from spec is not readonly,
        // hence it get pulled out from Resource.
        NetworkSecurityGroupInner nsg = mock(NetworkSecurityGroupInner.class);
        nsg.withId(anyString());
    }
}
