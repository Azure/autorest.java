/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package fixtures.azurespecials;

import com.azure.core.exception.HttpResponseException;
import com.azure.core.http.HttpHeaders;
import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.AddHeadersFromContextPolicy;
import com.azure.core.http.policy.CookiePolicy;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.Context;
import fixtures.azurespecials.models.HeaderCustomNamedRequestIdParamGroupingParameters;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdHeadResponse;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdParamGroupingResponse;
import fixtures.azurespecials.models.HeadersCustomNamedRequestIdResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.UUID;

public class AzureSpecialParametersTests {

    private static AutoRestAzureSpecialParametersTestClient client;

    private static AutoRestAzureSpecialParametersTestClient clientNoSubscription;

    private static final Context CONTEXT = new Context("azure-http-headers-key", new HttpHeaders(Collections.singletonMap("x-ms-client-request-id", "9C4D50EE-2D56-4CD3-8152-34347DC9F2B0")));

    @BeforeAll
    public static void setup() {
        HttpPipeline pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(),
                new RetryPolicy(),
                new CookiePolicy(),
                new AddHeadersFromContextPolicy()
                //, new HttpLoggingPolicy(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS))
        ).build();

        client = new AutoRestAzureSpecialParametersTestClientBuilder()
                .pipeline(pipeline)
                .subscriptionId("1234-5678-9012-3456")
                .buildClient();

        clientNoSubscription = new AutoRestAzureSpecialParametersTestClientBuilder()
                .pipeline(pipeline)
                .buildClient();
    }

    @Test
    public void get() {
        client.getXMsClientRequestIds().getWithResponse(CONTEXT);
    }

    @Test
    public void getOverwriteNull() {
        client.getXMsClientRequestIds().getWithResponse(new Context("azure-http-headers-key", new HttpHeaders(Collections.singletonMap("x-ms-client-request-id", null))));
    }

    @Test
    public void getOverwriteError() {
        Assertions.assertThrows(HttpResponseException.class, () -> client.getXMsClientRequestIds().getWithResponse(new Context("azure-http-headers-key", new HttpHeaders(Collections.singletonMap("x-ms-client-request-id", UUID.randomUUID().toString())))));
    }

    @Test
    public void paramGet() {
        client.getXMsClientRequestIds().paramGet("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0");
    }

    @Test
    public void postMethodGlobalValid() {
        client.getSubscriptionInCredentials().postMethodGlobalValidWithResponse(CONTEXT);
    }

    @Test
    public void postMethodGlobalNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> clientNoSubscription.getSubscriptionInCredentials().postMethodGlobalNull());
    }

    @Test
    public void postMethodGlobalNotProvidedValid() {
        client.getSubscriptionInCredentials().postMethodGlobalNotProvidedValidWithResponse(CONTEXT);
    }

    @Test
    public void postMethodLocalValid() {
        clientNoSubscription.getSubscriptionInMethods().postMethodLocalValidWithResponse("1234-5678-9012-3456", CONTEXT);
    }

    @Test
    public void postMethodLocalNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> clientNoSubscription.getSubscriptionInMethods().postMethodLocalNull(null));
    }

    @Test
    public void postPathGlobalValid() {
        client.getSubscriptionInCredentials().postPathGlobalValidWithResponse(CONTEXT);
    }

    @Test
    public void postPathLocalValid() {
        clientNoSubscription.getSubscriptionInMethods().postPathLocalValidWithResponse("1234-5678-9012-3456", CONTEXT);
    }

    @Test
    public void postSwaggerGlobalValid() {
        client.getSubscriptionInCredentials().postSwaggerGlobalValidWithResponse(CONTEXT);
    }

    @Test
    public void postSwaggerLocalValid() {
        clientNoSubscription.getSubscriptionInMethods().postSwaggerLocalValidWithResponse("1234-5678-9012-3456", CONTEXT);
    }

    @Test
    public void getMethodGlobalValid() {
        client.getApiVersionDefaults().getMethodGlobalValid();
    }

    @Test
    public void getMethodGlobalNotProvidedValid() {
        client.getApiVersionDefaults().getMethodGlobalNotProvidedValid();
    }

    @Test
    public void getMethodLocalValid() {
        client.getApiVersionLocals().getMethodLocalValid();
    }

    @Test
    public void getMethodLocalNull() {
        client.getApiVersionLocals().getMethodLocalNull();
    }

    @Test
    public void getPathGlobalValid() {
        client.getApiVersionDefaults().getPathGlobalValid();
    }

    @Test
    public void getPathLocalValid() {
        client.getApiVersionLocals().getPathLocalValid();
    }

    @Test
    public void getSwaggerGlobalValid() {
        client.getApiVersionDefaults().getSwaggerGlobalValid();
    }

    @Test
    public void getSwaggerLocalValid() {
        client.getApiVersionLocals().getSwaggerLocalValid();
    }

    @Test
    public void getMethodPathValid() {
        client.getSkipUrlEncodings().getMethodPathValid("path1/path2/path3");
    }

    @Test
    public void getPathPathValid() {
        client.getSkipUrlEncodings().getPathValid("path1/path2/path3");
    }

    @Test
    public void getSwaggerPathValid() {
        client.getSkipUrlEncodings().getSwaggerPathValid();
    }

    @Test
    public void getMethodQueryValid() {
        client.getSkipUrlEncodings().getMethodQueryValid("value1&q2=value2&q3=value3");
    }

    @Test
    public void getMethodQueryNull() {
        client.getSkipUrlEncodings().getMethodQueryNull();
    }

    @Test
    public void getPathQueryValid() {
        client.getSkipUrlEncodings().getPathQueryValid("value1&q2=value2&q3=value3");
    }

    @Test
    public void getSwaggerQueryValid() {
        client.getSkipUrlEncodings().getSwaggerQueryValid();
    }

    @Test
    public void getWithFilter() {
        client.getOdatas().getWithFilter("id gt 5 and name eq 'foo'", 10, "id");
    }

    @Test
    public void customNamedRequestId() {
        HeadersCustomNamedRequestIdResponse response = client.getHeaders().customNamedRequestIdWithResponse("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0", Context.NONE);
        Assertions.assertEquals("123", response.getDeserializedHeaders().getFooRequestId());
    }

    @Test
    public void customNamedRequestIdHead() {
        HeadersCustomNamedRequestIdHeadResponse response = client.getHeaders().customNamedRequestIdHeadWithResponse("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0", Context.NONE);
        Assertions.assertEquals("123", response.getDeserializedHeaders().getFooRequestId());
    }

    @Test
    public void customNamedRequestIdParamGrouping() {
        HeadersCustomNamedRequestIdParamGroupingResponse response = client.getHeaders().customNamedRequestIdParamGroupingWithResponse(
                new HeaderCustomNamedRequestIdParamGroupingParameters().setFooClientRequestId("9C4D50EE-2D56-4CD3-8152-34347DC9F2B0"), Context.NONE);
        Assertions.assertEquals("123", response.getDeserializedHeaders().getFooRequestId());
    }
}
