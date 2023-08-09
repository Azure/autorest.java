// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.contentnegotiation;

import com.payload.contentnegotiation.ContentNegotiationClientBuilder;
import com.payload.contentnegotiation.DifferentBodyClient;
import com.payload.contentnegotiation.SameBodyClient;
import com.payload.contentnegotiation.models.PngImageAsJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SharedRouteTests {

    private final SameBodyClient client1 = new ContentNegotiationClientBuilder().buildSameBodyClient();
    private final DifferentBodyClient client2 = new ContentNegotiationClientBuilder().buildDifferentBodyClient();

    @Test
    public void testContentNegotiation() {
        client1.getAvatarAsJpeg();
        client1.getAvatarAsPng();

        PngImageAsJson pngJson = client2.getAvatarAsJson();
        Assertions.assertNotNull(pngJson.getContent());
        client2.getAvatarAsPng();
    }
}
