// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.lro;

import com.azure.core.util.BinaryData;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.polling.SyncPoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class LroTests {

    private final LroAsyncClient client = new LroClientBuilder()
            .buildAsyncClient();

    @Disabled("response of poll is {status: Succeeded}, not a String")
    @Test
    public void testLro() {
        PollerFlux<String, String> lroFlux = client.beginCreate();
        SyncPoller<String, String> lroPoller = lroFlux.getSyncPoller();

        PollResponse<String> pollResponse = lroPoller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertEquals("Test for polling succeed", lroPoller.getFinalResult());
    }

    @Test
    public void testLroProtocol() {
        PollerFlux<BinaryData, BinaryData> lroFlux = client.beginCreate(null);
        SyncPoller<BinaryData, BinaryData> lroPoller = lroFlux.getSyncPoller();

        PollResponse<BinaryData> pollResponse = lroPoller.waitForCompletion();

        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertEquals("Test for polling succeed", lroPoller.getFinalResult().toObject(String.class));
    }
}
