// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.authentication.apikey;

import com.azure.core.credential.AzureKeyCredential;
import org.junit.jupiter.api.Test;

public class ApiKeyTests {

    @Test
    public void testValid() {
        AuthenticationApiKeyAsyncClient client = new AuthenticationApiKeyClientBuilder()
                // AzureKeyCredentialPolicy from core requires HTTPS
                .addPolicy(new AzureKeyCredentialPolicy("x-ms-api-key", new AzureKeyCredential("valid-key")))
                .buildAsyncClient();

        client.valid().block();
    }
}
