// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.lro.core;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

import com.azure.lro.core.models.ResourceOperationStatusUserError;
import com.azure.lro.core.models.ResourceOperationStatusUserExportedUserError;
import com.azure.lro.core.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LroCoreTests {

    private CoreClient client = new CoreClientBuilder()
            .httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
            .buildClient();

    @Test
    public void testPut() {
        SyncPoller<ResourceOperationStatusUserError, User> poller = client.beginCreateOrReplace(
                "madge", new User("contributor"));

        PollResponse<ResourceOperationStatusUserError> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
    }

    @Test
    public void testDelete() {
        SyncPoller<ResourceOperationStatusUserError, Void> poller = client.beginDelete("madge");

        PollResponse<ResourceOperationStatusUserError> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
    }

    @Test
    public void testPost() {
        SyncPoller<ResourceOperationStatusUserExportedUserError, ResourceOperationStatusUserExportedUserError> poller = client.beginExport(
                "madge", "json");

        PollResponse<ResourceOperationStatusUserExportedUserError> response = poller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
        Assertions.assertNotNull(response.getValue().getResult());
    }
}
