// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core;

import com._specs_.azure.core.models.User;
import com.azure.core.http.rest.PagedFlux;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class CoreTests {

    private CoreAsyncClient client = new CoreClientBuilder()
            .buildAsyncClient();

    @Test
    public void testCreateOrUpdate() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Madge");
        Mono<Response<BinaryData>> response = client.createOrUpdateWithResponse(1, BinaryData.fromObject(body), null);

        StepVerifier.create(response)
                .assertNext(r -> {
                    Assertions.assertEquals(200, r.getStatusCode());
                    Map<String, Object> responseBody = r.getValue().toObject(Map.class);
                    Assertions.assertEquals(1, responseBody.get("id"));
                    Assertions.assertEquals("Madge", responseBody.get("name"));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void testCreateOrReplace() {
        Mono<User> response = client.createOrReplace(1, new User("Madge"));

        StepVerifier.create(response)
                .assertNext(user -> {
                    Assertions.assertEquals(1, user.getId());
                    Assertions.assertEquals("Madge", user.getName());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void testGet() {
        Mono<User> response = client.get(1);

        StepVerifier.create(response)
                .assertNext(user -> {
                    Assertions.assertEquals(1, user.getId());
                    Assertions.assertEquals("Madge", user.getName());
                    Assertions.assertEquals("11bdc430-65e8-45ad-81d9-8ffa60d55b59", user.getEtag());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void testList() {
        PagedFlux<User> response = client.list(5, 10, 100,
                Collections.singletonList("id"),
                "id lt 10",
                Arrays.asList("id", "orders", "etag"),
                Collections.singletonList("orders"));

        StepVerifier.create(response)
                .assertNext(user -> {
                    Assertions.assertEquals(1, user.getId());
                    Assertions.assertEquals("Madge", user.getName());
                    Assertions.assertNotNull(user.getEtag());
                    Assertions.assertNotNull(user.getOrders());
                })
                .assertNext(user -> {
                    Assertions.assertEquals(2, user.getId());
                    Assertions.assertEquals("John", user.getName());
                })
                .expectComplete()
                .verify();

        client.listWithPage().blockLast();
    }


    @Test
    public void testDelete() {
        Mono<Void> response = client.delete(1);

        StepVerifier.create(response)
                .expectComplete()
                .verify();
    }

    @Test
    public void testAction() {
        Mono<User> response = client.export(1, "json");

        StepVerifier.create(response)
                .assertNext(user -> {
                    Assertions.assertEquals(1, user.getId());
                    Assertions.assertEquals("Madge", user.getName());
                })
                .expectComplete()
                .verify();
    }
}
