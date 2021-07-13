// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.management.Resource;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.management.exception.ManagementException;
import com.azure.resourcemanager.resources.fluentcore.collection.InnerSupportsGet;
import com.azure.resourcemanager.resources.fluentcore.collection.InnerSupportsListing;
import com.azure.mgmttest.appservice.models.DefaultErrorResponseErrorException;
import com.azure.mgmttest.appservice.fluent.WebSiteManagementClient;
import com.azure.mgmttest.authorization.models.GraphError;
import com.azure.mgmttest.authorization.models.GraphErrorException;
import com.azure.mgmttest.cosmos.models.SqlDatabaseGetPropertiesResource;
import com.azure.mgmttest.network.fluent.models.NetworkInterfaceInner;
import com.azure.mgmttest.network.fluent.NetworkInterfacesClient;
import com.azure.mgmttest.network.fluent.models.NetworkSecurityGroupInner;
import com.azure.mgmttest.resources.models.IdentityUserAssignedIdentities;
import com.azure.mgmttest.resources.fluent.models.DeploymentExtendedInner;
import com.azure.mgmttest.resources.fluent.DeploymentsClient;
import com.azure.mgmttest.resources.fluent.models.ResourceGroupInner;
import com.azure.mgmttest.storage.fluent.models.StorageAccountInner;
import com.azure.mgmttest.storage.fluent.StorageAccountsClient;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;

public class CompilationTests {

    // Verify method signature at compile time.

    public void testManagementClient() {
        // Operation group
        WebSiteManagementClient webSiteManagementClient = mock(WebSiteManagementClient.class);
        webSiteManagementClient.list();

        StorageAccountsClient storageAccountsClient = mock(StorageAccountsClient.class);
        storageAccountsClient.list();
    }

    public void testOperationName() {
        // ListAll -> list, List -> listByResourceGroup (spec -> code).
        NetworkInterfacesClient networkInterfaces = mock(NetworkInterfacesClient.class);
        networkInterfaces.list();
        networkInterfaces.listAsync();
        networkInterfaces.listByResourceGroup(anyString());
        networkInterfaces.listByResourceGroupAsync(anyString());
        networkInterfaces.getByResourceGroup(anyString(), anyString());
        networkInterfaces.getByResourceGroupAsync(anyString(), anyString());
    }

    public void testInnerSupport() {
        // Add InnerSupportsListing to class.
        InnerSupportsListing<StorageAccountInner> storageAccounts = mock(StorageAccountsClient.class);
        storageAccounts.list();

        // Add InnerSupportsGet to class.
        InnerSupportsGet<DeploymentExtendedInner> deployments = mock(DeploymentsClient.class);
        deployments.getByResourceGroup(anyString(), anyString());

        InnerSupportsGet<NetworkInterfaceInner> networkInterfaces = mock(NetworkInterfacesClient.class);
        networkInterfaces.getByResourceGroup(anyString(), anyString());
    }

    public void testResourceType() {
        // ResourceGroup is regarded as subclass of Resource.
        Resource resourceGroup = mock(ResourceGroupInner.class);
        resourceGroup.id();

        // NetworkSecurityGroup is subclass of Resource, but the id property from spec is not readonly,
        // hence it get pulled out from Resource.
        NetworkSecurityGroupInner networkSecurityGroup = mock(NetworkSecurityGroupInner.class);
        networkSecurityGroup.withId(anyString());
        networkSecurityGroup.id();
    }

    public void testAdditionalPropertyName() {
        IdentityUserAssignedIdentities identities = new IdentityUserAssignedIdentities();
    }

    public void testMultipleInheritance() {
        SqlDatabaseGetPropertiesResource sqlDatabaseGetPropertiesResource = new SqlDatabaseGetPropertiesResource();
        sqlDatabaseGetPropertiesResource.rid();
        sqlDatabaseGetPropertiesResource.colls();
    }

//    public void testIntEnum() {
//        ContainerServiceMasterProfile containerServiceMasterProfile = new ContainerServiceMasterProfile();
//        containerServiceMasterProfile.withCount(Count.THREE);
//        int countInt = containerServiceMasterProfile.count().toInt();
//    }

    public void testException() {
        ManagementException exception = new DefaultErrorResponseErrorException(anyString(), null);
        ManagementError error = exception.getValue();

        GraphErrorException graphException = new GraphErrorException(anyString(), null);
        GraphError graphError = graphException.getValue();
    }
}
