// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core;

import com._specs_.azure.core.models.User;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoreTests {

    private CoreAsyncClient client = new CoreClientBuilder()
            .buildAsyncClient();

    @Test
    public void testCreateOrUpdate() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Madge");
        Response<BinaryData> response = client.createOrUpdateWithResponse(1, BinaryData.fromObject(body), null).block();

        Assertions.assertEquals(200, response.getStatusCode());
        Map<String, Object> responseBody = response.getValue().toObject(Map.class);
        Assertions.assertEquals(1, responseBody.get("id"));
        Assertions.assertEquals("Madge", responseBody.get("name"));
    }

    @Test
    public void testCreateOrReplace() {
        User user = client.createOrReplace(1, new User("Madge")).block();

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Madge", user.getName());
    }

    @Test
    public void testGet() {
        User user = client.get(1).block();

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Madge", user.getName());
    }

    @Test
    public void testList() {
        List<User> users = client.list().collectList().block();

        Assertions.assertEquals(2, users.size());
        Assertions.assertEquals(1, users.get(0).getId());
        Assertions.assertEquals("Madge", users.get(0).getName());
        Assertions.assertEquals(2, users.get(1).getId());
        Assertions.assertEquals("John", users.get(1).getName());
    }


    @Test
    public void testDelete() {
        client.delete(1).block();
    }

    @Test
    public void testAction() {
        User user = client.export(1, "json").block();

        Assertions.assertEquals(1, user.getId());
        Assertions.assertEquals("Madge", user.getName());
    }
}
