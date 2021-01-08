// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.Region;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.util.Context;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.mgmtlitetest.resources.ResourceManager;
import com.azure.mgmtlitetest.resources.models.ResourceGroup;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.fluent.StorageAccountsClient;
import com.azure.mgmtlitetest.storage.models.AccessTier;
import com.azure.mgmtlitetest.storage.models.BlobContainer;
import com.azure.mgmtlitetest.storage.models.BlobServiceProperties;
import com.azure.mgmtlitetest.storage.models.BlobServices;
import com.azure.mgmtlitetest.storage.models.Kind;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
import com.azure.mgmtlitetest.storage.models.Sku;
import com.azure.mgmtlitetest.storage.models.SkuName;
import com.azure.mgmtlitetest.storage.models.StorageAccount;
import com.azure.mgmtlitetest.storage.models.StorageAccountListKeysResult;
import com.azure.mgmtlitetest.storage.models.StorageAccountRegenerateKeyParameters;
import com.azure.mgmtlitetest.storage.models.StorageAccounts;

import java.time.Duration;

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

    public void testStorage() {
        String rgName = "rg1-weidxu-fluentlite";
        String saName = "sa1weidxulite";

        StorageManager storageManager = mock(StorageManager.class);

        StorageAccount storageAccount = storageManager.storageAccounts().define(saName)
                .withRegion(Region.US_WEST)
                .withExistingResourceGroup(rgName)
                .withSku(new Sku().withName(SkuName.STANDARD_LRS))
                .withKind(Kind.STORAGE_V2)
                .withEnableHttpsTrafficOnly(true)
                .create();

        storageAccount = storageManager.storageAccounts().getByResourceGroup(rgName, saName);
        storageAccount.update()
                .withAccessTier(AccessTier.COOL)
                .apply();

        storageAccount.refresh();

        StorageAccountListKeysResult keys = storageAccount.listKeys();
        storageAccount.regenerateKey(new StorageAccountRegenerateKeyParameters().withKeyName(keys.keys().iterator().next().keyName()));

        BlobContainer blobContainer = storageManager.blobContainers().define("container1")
                .withExistingStorageAccount(rgName, saName)
                .withPublicAccess(PublicAccess.BLOB)
                .create(new Context("key", "value"));

        blobContainer = storageManager.blobContainers().get(rgName, saName, "container1");
        blobContainer.update()
                .withPublicAccess(PublicAccess.NONE)
                .apply(new Context("key", "value"));

        blobContainer.refresh();

        blobContainer.lease();

        BlobServiceProperties blobService = storageManager.blobServices().define()
                .withExistingStorageAccount(rgName, saName)
                .create();

        storageManager.storageAccounts().deleteByResourceGroup(rgName, saName);
    }

    public void testResources() {
        String rgName = "rg1-weidxu-fluentlite";

        ResourceManager resourceManager = mock(ResourceManager.class);

        ResourceGroup resourceGroup = resourceManager.resourceGroups().define(rgName)
                .withRegion(Region.US_WEST)
                .create();

        resourceGroup.update()
                .withManagedBy("javasdk")
                .apply();

        resourceGroup.refresh();
    }

    public void testConfigurable() {
        ResourceManager resourceManager = ResourceManager.configure()
                .withHttpClient(HttpClient.createDefault())
                .withLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
                .withDefaultPollInterval(Duration.ofSeconds(10))
                .authenticate(new EnvironmentCredentialBuilder().build(), new AzureProfile(AzureEnvironment.AZURE));
    }
}
