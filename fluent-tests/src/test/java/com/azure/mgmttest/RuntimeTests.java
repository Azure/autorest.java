// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.mgmttest;

import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.management.serializer.AzureJacksonAdapter;
import com.azure.core.util.Configuration;
import com.azure.core.util.serializer.SerializerEncoding;
import com.azure.identity.EnvironmentCredentialBuilder;
import com.azure.mgmtlitetest.storage.StorageManager;
import com.azure.mgmtlitetest.storage.models.BlobContainer;
import com.azure.mgmtlitetest.storage.models.BlobContainers;
import com.azure.mgmtlitetest.storage.models.PublicAccess;
import com.azure.mgmtlitetest.storage.models.StorageAccount;
import com.azure.mgmttest.appservice.models.DefaultErrorResponseError;
import com.azure.mgmttest.authorization.models.GraphErrorException;
import com.azure.mgmttest.storage.fluent.StorageManagementClientBuilder;
import com.azure.mgmttest.storage.fluent.StorageManagementClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.mock;

public class RuntimeTests {

    private static final String MOCK_SUBSCRIPTION_ID = "00000000-0000-0000-0000-000000000000";

    @Test
    public void testManagementClient() {
        StorageManagementClient storageManagementClient = new StorageManagementClientBuilder()
                .pipeline(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build())
                .endpoint(AzureEnvironment.AZURE.getResourceManagerEndpoint())
                .subscriptionId(MOCK_SUBSCRIPTION_ID)
                .buildClient();
        Assertions.assertNotNull(storageManagementClient.getHttpPipeline());
        Assertions.assertEquals(MOCK_SUBSCRIPTION_ID, storageManagementClient.getSubscriptionId());
        Assertions.assertNotNull(storageManagementClient.getStorageAccounts());
    }

    @Test
    public void testWebException() throws IOException {
        final String errorBody = "{\"error\":{\"code\":\"WepAppError\",\"message\":\"Web app error.\",\"innererror\":\"Deployment error.\",\"details\":[{\"code\":\"InnerError\"}]}}";

        AzureJacksonAdapter serializerAdapter = new AzureJacksonAdapter();
        DefaultErrorResponseError webError = serializerAdapter.deserialize(errorBody, DefaultErrorResponseError.class, SerializerEncoding.JSON);
        Assertions.assertEquals("WepAppError", webError.getCode());
        Assertions.assertNotNull(webError.getDetails());
        Assertions.assertEquals(1, webError.getDetails().size());
        Assertions.assertEquals("InnerError", webError.getDetails().get(0).getCode());

        GraphErrorException graphException = new GraphErrorException("mock graph error", null);
        Assertions.assertFalse((Object) graphException instanceof ManagementException);
        Assertions.assertFalse((Object) graphException.getValue() instanceof ManagementError);
    }

    @Test
    public void testStorageManager() {
        StorageManager storageManager = authenticateStorageManager();
        PagedIterable<StorageAccount> storageAccounts = storageManager.storageAccounts().list();
        //List<StorageAccount> storageAccountList = storageManager.storageAccounts().list().stream().collect(Collectors.toList());
    }

    @Test
    @Disabled("live test")
    public void testBlobContainer() {
        StorageManager storageManager = authenticateStorageManager();
        BlobContainers blobContainers = storageManager.blobContainers();
        BlobContainer blobContainer = blobContainers.defineContainer("container1")
                .withExistingStorageAccount("rg-weidxu", "sa1weidxu")
                .withPublicAccess(PublicAccess.BLOB)
                .create();

        blobContainers.get("rg-weidxu", "sa1weidxu", "container1")
                .update()
                .withPublicAccess(PublicAccess.NONE)
                .apply();
    }

    private StorageManager authenticateStorageManager() {
        String subscriptionId = Configuration.getGlobalConfiguration().get(Configuration.PROPERTY_AZURE_SUBSCRIPTION_ID);
        if (subscriptionId == null) {
            subscriptionId = "";
        }
        return StorageManager.authenticate(new EnvironmentCredentialBuilder().build(), AzureEnvironment.AZURE, subscriptionId);
    }
}
