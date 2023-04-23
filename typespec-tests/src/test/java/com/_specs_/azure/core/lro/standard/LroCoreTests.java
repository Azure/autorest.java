// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.lro.standard;

import com._specs_.azure.core.lro.standard.models.OperationState;
import com._specs_.azure.core.lro.standard.models.ResourceOperationStatusUserError;
import com._specs_.azure.core.lro.standard.models.ResourceOperationStatusUserExportedUserError;
import com._specs_.azure.core.lro.standard.models.User;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LroCoreTests {

    private StandardClient client = new StandardClientBuilder()
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
            .buildClient();

    @Disabled("PR https://github.com/Azure/azure-sdk-for-java/pull/34174")
    @Test
    public void testPut() {
        SyncPoller<ResourceOperationStatusUserError, User> poller = client.beginCreateOrReplace(
                "madge", new User("contributor"));

        PollResponse<ResourceOperationStatusUserError> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());

        User user = poller.getFinalResult();
        Assertions.assertEquals("madge", user.getName());
    }

    @Test
    public void testPutProtocol() {
        SyncPoller<BinaryData, BinaryData> poller = client.beginCreateOrReplace(
                "madge", BinaryData.fromObject(new User("contributor")), null);

        PollResponse<BinaryData> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());

        BinaryData finalResult = poller.getFinalResult();
        User user = finalResult.toObject(User.class);
        Assertions.assertEquals("madge", user.getName());
    }

    @Test
    public void testDelete() {
        SyncPoller<ResourceOperationStatusUserError, Void> poller = client.beginDelete("madge");

        PollResponse<ResourceOperationStatusUserError> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertEquals(OperationState.SUCCEEDED, response.getValue().getStatus());
    }

    @Test
    public void testPost() {
        SyncPoller<ResourceOperationStatusUserExportedUserError, ResourceOperationStatusUserExportedUserError> poller = client.beginExport(
                "madge", "json");

        PollResponse<ResourceOperationStatusUserExportedUserError> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertEquals(OperationState.SUCCEEDED, response.getValue().getStatus());
        Assertions.assertNotNull(response.getValue().getResult());

        ResourceOperationStatusUserExportedUserError finalResult = poller.getFinalResult();
        Assertions.assertEquals(OperationState.SUCCEEDED, finalResult.getStatus());
        Assertions.assertNotNull(finalResult.getResult());
    }
}
