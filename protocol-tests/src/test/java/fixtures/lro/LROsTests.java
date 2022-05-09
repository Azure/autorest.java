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
        client = new LROsClientBuilder().buildClient();
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

    @Test
    public void beginPatch200SucceededIgnoreHeaders() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPatch200SucceededIgnoreHeaders(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Succeeded\""));
    }

    @Test
    public void beginPatch201RetryOnlyAsyncHeader() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPatch201RetryWithAsyncHeader(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Accepted\""));
    }

    @Test
    public void beginPatch202RetryAsyncAndLocationHeader() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPatch202RetryWithAsyncAndLocationHeader(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Succeeded\""));
    }

    @Test
    public void beginPut201Succeeded() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPut201Succeeded(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Succeeded\""));
    }

    @Test
    public void beginPost202List() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPost202List(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"name\": \"foo\""));
    }

    @Test
    public void beginPut200SucceededNoState() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPut200SucceededNoState(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"name\": \"foo\""));
    }

    @Test
    public void beginPut202Retry200() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPut202Retry200(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(pollResponse.getValue().toString().contains("\"name\": \"foo\""));
    }

    @Test
    public void beginPutAsyncRetrySucceeded() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller = client.beginPutAsyncRetrySucceeded(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Succeeded\""));
    }

    @Test
    public void beginNonRetryErrorPutAsyncRetry400() {
        RequestOptions requestOptions = new RequestOptions()
                .setBody(BinaryData.fromString("{\"location\": \"West US\"}"));
        SyncPoller<BinaryData, BinaryData> poller =  client.beginPutAsyncRetryFailed(requestOptions);
        PollResponse<BinaryData> pollResponse = poller.waitForCompletion();
        Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, pollResponse.getStatus());
        Assertions.assertTrue(poller.getFinalResult().toString().contains("\"provisioningState\": \"Failed\""));
    }

}
