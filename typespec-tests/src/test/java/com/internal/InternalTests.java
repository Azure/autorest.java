// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.internal;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class InternalTests {

    InternalClient internalClient = new InternalClientBuilder().buildClient();

    @Test
    public void getTest() {
        String name = "mockName";
        Response<BinaryData> response = internalClient.getInternalWithResponse(name, new RequestOptions());

        int statusCode = response.getStatusCode();
        BinaryData bodyBinary = response.getValue();
        Map body = bodyBinary.toObject(Map.class);

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(name, body.get("name"));
    }

    @Test
    public void postTest() {
        Map<String, Object> requestBody = new HashMap<>();
        String name = "mockName";

        requestBody.put("id", 1);
        requestBody.put("name", name);

        Response<BinaryData> response = internalClient.postInternalWithResponse(BinaryData.fromObject(requestBody), new RequestOptions());

        int statusCode = response.getStatusCode();
        Map responseBody = response.getValue().toObject(Map.class);

        Assertions.assertEquals(200, statusCode);
        Assertions.assertEquals(1, responseBody.get("id"));
        Assertions.assertEquals(name, responseBody.get("name"));
    }
}
