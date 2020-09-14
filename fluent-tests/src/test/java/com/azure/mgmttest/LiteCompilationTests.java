// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.rest.PagedIterable;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.fluent.StorageAccountsClient;
import com.azure.mgmtlitetest.storage.models.BlobContainers;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
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
        BlobContainers blobContainers = storageManager.blobContainers();
        blobContainers.defineContainer("container1")
                .withExistingStorageAccount("rg-weidxu", "sa1weidxu")
                .withPublicAccess(PublicAccess.BLOB)
                .create();
    }
}
