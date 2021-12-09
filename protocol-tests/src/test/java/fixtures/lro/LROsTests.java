/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.lro;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollResponse;
import com.azure.core.util.polling.SyncPoller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class LROsTests {
    private static LROsClient client;

    @BeforeAll
    public static void setup() {
        client = new AutoRestLongRunningOperationTestServiceClientBuilder().buildLROsClient();
    }

    @Test
    public void beginPut200Succeeded() throws Exception {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPut200Succeeded(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Succeeded\""));
    }
}
