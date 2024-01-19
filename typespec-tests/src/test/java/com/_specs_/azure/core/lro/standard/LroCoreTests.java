// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.lro.standard;

import com._specs_.azure.core.lro.standard.models.ExportedUser;
import com._specs_.azure.core.lro.standard.models.User;
import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollOperationDetails;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LroCoreTests {

    private StandardClient client = new StandardClientBuilder()
            .buildClient();

    @Test
    public void testPut() {
        SyncPoller<PollOperationDetails, User> poller = client.beginCreateOrReplace(
                "madge", new User("contributor"));

        PollResponse<PollOperationDetails> response = poller.waitForCompletion();
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
        SyncPoller<PollOperationDetails, Void> poller = client.beginDelete("madge");

        PollResponse<PollOperationDetails> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());
    }

    @Test
    public void testPost() {
        SyncPoller<PollOperationDetails, ExportedUser> poller = client.beginExport(
                "madge", "json");

        PollResponse<PollOperationDetails> response = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.getStatus());

        ExportedUser finalResult = poller.getFinalResult();
        Assertions.assertEquals("madge", finalResult.getName());
    }
}
