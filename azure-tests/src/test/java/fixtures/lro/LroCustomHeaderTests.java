/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.lro;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import fixtures.lro.fluent.AutoRestLongRunningOperationTestService;
import fixtures.lro.fluent.models.ProductInner;
import fixtures.lro.implementation.AutoRestLongRunningOperationTestServiceBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class LroCustomHeaderTests {

    private static AutoRestLongRunningOperationTestService client;

    @BeforeAll
    public static void setup() {
        HttpPipeline pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(),
                new RetryPolicy(),
                new CookiePolicy(),
                (context, next) -> {
                    String requestId = context.getHttpRequest().getHeaders().getValue("x-ms-client-request-id");
                    if (requestId == null) {
                        context.getHttpRequest().getHeaders().put("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
                    }
                    return next.process();
                }
                //, new HttpLoggingPolicy(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
        ).build();

        client = new AutoRestLongRunningOperationTestServiceBuilder()
                .endpoint("http://localhost:3000")
                .defaultPollInterval(Duration.ofMillis(1))
                .pipeline(pipeline)
                .buildClient();
    }

    @Test
    public void putAsyncSucceded() {
        ProductInner product = client.getLrosCustomHeaders().putAsyncRetrySucceeded();
        Assertions.assertEquals("Succeeded", product.provisioningState());
    }

    @Test
    public void postAsyncSucceded() {
        client.getLrosCustomHeaders().postAsyncRetrySucceeded();
    }

    @Test
    public void putSucceeded() {
        ProductInner product = client.getLrosCustomHeaders().put201CreatingSucceeded200();
        Assertions.assertEquals("Succeeded", product.provisioningState());
    }

    @Test
    public void postSucceeded() {
        client.getLrosCustomHeaders().post202Retry200();
    }
}
