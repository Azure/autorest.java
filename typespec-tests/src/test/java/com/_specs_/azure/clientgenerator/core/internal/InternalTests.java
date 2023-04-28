// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.clientgenerator.core.internal;

import com._specs_.azure.clientgenerator.core.internal.models.PublicModel;
import com._specs_.azure.clientgenerator.core.internal.models.SharedModel;
import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class InternalTests {

    private InternalClient internalClient = new InternalClientBuilder()
            .buildClient();

    private SharedClient sharedClient = new InternalClientBuilder()
            .buildSharedClient();

    @Test
    public void testInternalOnlyModel() {
        String name = "test";

        Response<BinaryData> response = internalClient.internalOnlyWithResponse(name, new RequestOptions());
        Assertions.assertEquals(200, response.getStatusCode());
        Map responseBody = response.getValue().toObject(Map.class);
        Assertions.assertEquals(name, responseBody.get("name"));
    }

    @Test
    public void testPublicOnlyModel() {
        String name = "test";

        PublicModel responseBody = internalClient.publicOnly(name);
        Assertions.assertEquals(name, responseBody.getName());
    }

    @Test
    public void testSharedModel() {
        String name = "test";

        SharedModel modelResponseBody = sharedClient.publicMethod(name);
        Assertions.assertEquals(name, modelResponseBody.getName());

        Response<BinaryData> response = sharedClient.internalWithResponse(name, new RequestOptions());
        Assertions.assertEquals(200, response.getStatusCode());
        Map responseBody = response.getValue().toObject(Map.class);
        Assertions.assertEquals(name, responseBody.get("name"));
    }
}
