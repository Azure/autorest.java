// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.authentication.oauth2;

import com.azure.core.credential.AccessToken;
import com.azure.core.exception.HttpResponseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.OffsetDateTime;

public class OAuth2Tests {

    private static AuthenticationOAuth2Client buildClient() {
        return new AuthenticationOAuth2ClientBuilder()
                // not able to use azure-identity in this test.
                .addPolicy(new BearerTokenAuthenticationPolicy(tokenRequestContext -> Mono.just(new AccessToken("https://security.microsoft.com/.default", OffsetDateTime.now().plusHours(1)))))
                .buildClient();
    }

    @Test
    public void testValid() {
        AuthenticationOAuth2Client client = buildClient();

        client.valid();
    }

    @Test
    public void testInvalid() {
        AuthenticationOAuth2Client client = buildClient();

        // assert HttpResponseException
        Assertions.assertThrows(HttpResponseException.class, client::invalid);
    }
}
