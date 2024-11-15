// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package tsptest.armresourceprovider;

import com.azure.core.credential.AccessToken;
import com.azure.core.credential.TokenCredential;
import com.azure.core.http.HttpClient;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.profile.AzureProfile;
import com.azure.core.test.http.MockHttpResponse;
import tsptest.armresourceprovider.models.Operation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ArmTests {

    private static final String VALID_CHALLENGE_HEADER = "Bearer realm=\"\", authorization_uri=\"https://login.microsoftonline.com/common/oauth2/authorize\", error=\"insufficient_claims\", claims=\"eyJhY2Nlc3NfdG9rZW4iOnsibmJmIjp7ImVzc2VudGlhbCI6dHJ1ZSwidmFsdWUiOiIxNzI2MDc3NTk1In0sInhtc19jYWVlcnJvciI6eyJ2YWx1ZSI6IjEwMDEyIn19fQ==\"";

    @Test
    public void testCaeBehaviorForBearerTokenAuthenticationPolicy() {
        AtomicInteger tokenGetCount = new AtomicInteger();
        ArmResourceProviderManager armResourceProviderManager = ArmResourceProviderManager
                .configure()
                .withHttpClient(mockCaeHttpClient(tokenGetCount))
                .authenticate(mockCaeTokenCredential(tokenGetCount), new AzureProfile(AzureEnvironment.AZURE));
        Operation operation = armResourceProviderManager.operations()
                .list().stream().iterator().next();
        Assertions.assertEquals("mockOperation", operation.name());
        Assertions.assertEquals(2, tokenGetCount.get());
    }

    private HttpClient mockCaeHttpClient(AtomicInteger tokenGetCount) {
        return request -> {
            if (tokenGetCount.get() <= 1) {
                return Mono.just(new MockHttpResponse(
                        request,
                        401,
                        new HttpHeaders().add(HttpHeaderName.WWW_AUTHENTICATE, VALID_CHALLENGE_HEADER)));
            }
            return Mono.just(new MockHttpResponse(request, 200, mockOperationListResponse()));
        };
    }

    private Object mockOperationListResponse() {
        return Map.of("value", Collections.singletonList(Map.of("name", "mockOperation", "isDataAction", "false")));
    }

    private TokenCredential mockCaeTokenCredential(AtomicInteger tokenGetCount) {
        return tokenRequestContext -> {
            tokenGetCount.incrementAndGet();
            return Mono.just(new AccessToken("fake_token", OffsetDateTime.MAX));
        };
    }
}
