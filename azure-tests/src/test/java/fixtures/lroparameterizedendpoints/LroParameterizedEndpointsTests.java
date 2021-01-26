/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.lroparameterizedendpoints;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.http.policy.HttpLoggingPolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import fixtures.lroparameterizedendpoints.fluent.LroWithParamaterizedEndpoints;
import fixtures.lroparameterizedendpoints.implementation.LroWithParamaterizedEndpointsBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class LroParameterizedEndpointsTests {

    private static LroWithParamaterizedEndpoints client;

    @BeforeAll
    public static void setup() {
        HttpPipeline pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(),
                new RetryPolicy(),
                new CookiePolicy()
                //, new HttpLoggingPolicy(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
        ).build();

        client = new LroWithParamaterizedEndpointsBuilder()
                .host("host:3000")
                .defaultPollInterval(Duration.ofMillis(1))
                .pipeline(pipeline)
                .buildClient();
    }

    @Test
    @Disabled("relative link in location is not supported (Location: /lroParameterizedEndpoints/results/1)")
    public void pollWithParameterizedEndpoints() {
        client.getResourceProviders().pollWithParameterizedEndpoints("local");
    }
}
