// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.contentnegotiation;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.payload.contentnegotiation.ContentNegotiationClientBuilder;
import com.payload.contentnegotiation.DifferentBodyClient;
import com.payload.contentnegotiation.SameBodyClient;
import com.payload.contentnegotiation.models.PngImageAsJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SharedRouteTests {

    private final SameBodyClient client1 = new ContentNegotiationClientBuilder().buildSameBodyClient();
    private final DifferentBodyClient client2 = new ContentNegotiationClientBuilder().httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildDifferentBodyClient();

    @Test
    public void testContentNegotiation() {
        BinaryData jpeg = client1.getAvatarAsJpeg();
        Assertions.assertNotNull(jpeg);
        BinaryData png = client1.getAvatarAsPng();
        Assertions.assertNotNull(png);

        PngImageAsJson pngJson = client2.getAvatarAsJson();
//        Assertions.assertNotNull(pngJson.getContent());
        png = client2.getAvatarAsPng();
        Assertions.assertNotNull(png);
    }
}
