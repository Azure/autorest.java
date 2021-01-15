/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.lro;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import fixtures.lro.fluent.AutoRestLongRunningOperationTestService;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class LroRetryTests {

    private static AutoRestLongRunningOperationTestService client;

    @BeforeAll
    public static void setup() {
        HttpPipeline pipeline;
        pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(),
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
    public void put201CreatingSucceeded200() {
        ProductInner product = client.getLroRetrys().put201CreatingSucceeded200();
        Assertions.assertEquals("100", product.id());
    }

    @Test
    public void putAsyncRelativeRetrySucceeded() {
        ProductInner product = client.getLroRetrys().putAsyncRelativeRetrySucceeded();
        Assertions.assertEquals("100", product.id());
    }

    @Test
    public void deleteProvisioning202Accepted200Succeeded() {
        ProductInner product = client.getLroRetrys().deleteProvisioning202Accepted200Succeeded();
        Assertions.assertEquals("100", product.id());
    }

    @Test
    public void delete202Retry200() {
        client.getLroRetrys().delete202Retry200();
    }

    @Test
    public void deleteAsyncRelativeRetrySucceeded() {
        client.getLroRetrys().deleteAsyncRelativeRetrySucceeded();
    }

    @Test
    public void post202Retry200() {
        client.getLroRetrys().post202Retry200();
    }

    @Test
    public void postAsyncRelativeRetrySucceeded() {
        client.getLroRetrys().postAsyncRelativeRetrySucceeded();
    }
}
