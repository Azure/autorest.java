// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.traits;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class TraitsTests {

    private TraitsClient client = new TraitsClientBuilder()
            .buildClient();

    @Test
    public void testGet() {
        OffsetDateTime time = OffsetDateTime.of(2022, 8, 26, 18, 38, 0, 0, ZoneOffset.UTC);

        client.get(1, "123",
                "valid", "invalid",
                time, time, UUID.randomUUID().toString());
    }

    @Test
    public void testDelete() {
        client.delete(1, UUID.randomUUID().toString());
    }
}
