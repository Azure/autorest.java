/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.lro;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import fixtures.lro.fluent.AutoRestLongRunningOperationTestService;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class LroNoRetryTests {

    private static AutoRestLongRunningOperationTestService client;

    @BeforeAll
    public static void setup() {
        HttpPipeline pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(),
                new RetryPolicy(),
                new CookiePolicy()
                //, new HttpLoggingPolicy(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
        ).build();

        client = new AutoRestLongRunningOperationTestServiceBuilder()
                .endpoint("http://localhost:3000")
                .defaultPollInterval(Duration.ofMillis(1))
                .pipeline(pipeline)
                .buildClient();
    }

    @Test
    public void putNonRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putNonRetry400());
    }

    @Test
    public void putNonRetry201Creating400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putNonRetry201Creating400());
    }

    @Test
    public void putNonRetry201Creating400InvalidJson() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putNonRetry201Creating400InvalidJson());
    }

    @Test
    public void putAsyncRelativeRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putAsyncRelativeRetry400());
    }

    @Test
    public void deleteNonRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().deleteNonRetry400());
    }

    @Test
    public void delete202NonRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().delete202NonRetry400());
    }

    @Test
    public void deleteAsyncRelativeRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().deleteAsyncRelativeRetry400());
    }

    @Test
    public void postNonRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().postNonRetry400());
    }

    @Test
    public void post202NonRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().post202NonRetry400());
    }

    @Test
    public void postAsyncRelativeRetry400() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().postAsyncRelativeRetry400());
    }

    @Test
    @Disabled("expect error when no payload")
    public void putError201NoProvisioningStatePayload() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putError201NoProvisioningStatePayload());
    }

    @Test
    public void putAsyncRelativeRetryNoStatus() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putAsyncRelativeRetryNoStatus());
    }

    @Test
    public void putAsyncRelativeRetryNoStatusPayload() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putAsyncRelativeRetryNoStatusPayload());
    }

    @Test
    public void delete204Succeeded() {
        client.getLrosaDs().delete204Succeeded();
    }

    @Test
    public void deleteAsyncRelativeRetryNoStatus() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().deleteAsyncRelativeRetryNoStatus());
    }

    @Test
    public void post202NoLocation() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().post202NoLocation());
    }

    @Test
    public void postAsyncRelativeRetryNoPayload() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().postAsyncRelativeRetryNoPayload());
    }

    @Test
    public void put200InvalidJson() {
        // handling of invalid json depends on azure-core-management
        //Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().put200InvalidJson());

        client.getLrosaDs().put200InvalidJson();
    }

    @Test
    public void putAsyncRelativeRetryInvalidHeader() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putAsyncRelativeRetryInvalidHeader());
    }

    @Test
    public void putAsyncRelativeRetryInvalidJsonPolling() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().putAsyncRelativeRetryInvalidJsonPolling());
    }

    @Test
    public void delete202RetryInvalidHeader() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().delete202RetryInvalidHeader());
    }

    @Test
    public void deleteAsyncRelativeRetryInvalidHeader() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().deleteAsyncRelativeRetryInvalidHeader());
    }

    @Test
    public void post202RetryInvalidHeader() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().post202RetryInvalidHeader());
    }

    @Test
    public void postAsyncRelativeRetryInvalidHeader() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().postAsyncRelativeRetryInvalidHeader());
    }

    @Test
    public void postAsyncRelativeRetryInvalidJsonPolling() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getLrosaDs().postAsyncRelativeRetryInvalidJsonPolling());
    }
}
