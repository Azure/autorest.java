// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.core.traits;

import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class TraitsTests {

    private TraitsClient client = new TraitsClientBuilder()
            .buildClient();

    @Test
    public void testGet() {
        OffsetDateTime unmodifiedSince = OffsetDateTime.of(2022, 8, 26, 14, 38, 0, 0, ZoneOffset.UTC);
        OffsetDateTime modifiedSince = OffsetDateTime.of(2021, 8, 26, 14, 38, 0, 0, ZoneOffset.UTC);

        client.smokeTest(1, "123",
                "\"valid\"", "\"invalid\"",
                unmodifiedSince, modifiedSince);
    }
}
