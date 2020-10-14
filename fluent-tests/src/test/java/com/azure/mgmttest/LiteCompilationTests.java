// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.Context;
import com.azure.mgmtlitetest.resources.ResourceManager;
import com.azure.mgmtlitetest.resources.models.ResourceGroup;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.fluent.StorageAccountsClient;
import com.azure.mgmtlitetest.storage.models.AccessTier;
import com.azure.mgmtlitetest.storage.models.BlobContainer;
import com.azure.mgmtlitetest.storage.models.Kind;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
import com.azure.mgmtlitetest.storage.models.Sku;
import com.azure.mgmtlitetest.storage.models.SkuName;
import com.azure.mgmtlitetest.storage.models.StorageAccount;
import com.azure.mgmtlitetest.storage.models.StorageAccounts;

import static org.mockito.Mockito.mock;

public class LiteCompilationTests {

    public void testInterface() {
        StorageManager storageManager = mock(StorageManager.class);
        StorageAccounts storageAccounts = storageManager.storageAccounts();
        PagedIterable<StorageAccount> accounts = storageAccounts.list();
        String name = accounts.iterator().next().name();
    }

    public void testServiceClient() {
        // Client
        StorageAccountsClient storageAccountsClient = mock(StorageAccountsClient.class);
        storageAccountsClient.list();
    }

    public void testFluentInterface() {
        StorageManager storageManager = mock(StorageManager.class);

        StorageAccount storageAccount = storageManager.storageAccounts().define("sa1weidxu")
                .withLocation("westus")
                .withExistingResourceGroup("rg-weidxu")
                .withSku(new Sku().withName(SkuName.STANDARD_LRS))
                .withKind(Kind.STORAGE_V2)
                .withEnableHttpsTrafficOnly(true)
                .create();

        storageAccount = storageManager.storageAccounts().getByResourceGroup("rg-weidxu", "sa1weidxu");
        storageAccount.update()
                .withAccessTier(AccessTier.COOL)
                .apply();

        storageAccount.refresh();

        BlobContainer blobContainer = storageManager.blobContainers().defineContainer("container1")
                .withExistingStorageAccount("rg-weidxu", "sa1weidxu")
                .withPublicAccess(PublicAccess.BLOB)
                .create(new Context("key", "value"));

        blobContainer = storageManager.blobContainers().get("rg-weidxu", "sa1weidxu", "container1");
        blobContainer.update()
                .withPublicAccess(PublicAccess.NONE)
                .apply(new Context("key", "value"));

        blobContainer.refresh();

        ResourceManager resourceManager = mock(ResourceManager.class);

        ResourceGroup resourceGroup = resourceManager.resourceGroups().define("rg-weidxu")
                .create();

        resourceGroup.refresh();
    }
}
