// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.contentnegotiation;

import com.Utils;
import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.payload.contentnegotiation.ContentNegotiationClientBuilder;
import com.payload.contentnegotiation.DifferentBodyClient;
import com.payload.contentnegotiation.SameBodyClient;
import com.payload.contentnegotiation.models.PngImageAsJson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SharedRouteTests {

    private final SameBodyClient client1 = new ContentNegotiationClientBuilder().buildSameBodyClient();
    private final DifferentBodyClient client2 = new ContentNegotiationClientBuilder().httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildDifferentBodyClient();

    @Test
    public void testContentNegotiation() {
        byte[] jpgBytes = Utils.getJpgBytes();
        byte[] pngBytes = Utils.getPngBytes();

        BinaryData jpeg = client1.getAvatarAsJpeg();
        Assertions.assertNotNull(jpeg);
        Assertions.assertArrayEquals(jpgBytes, jpeg.toBytes());

        BinaryData png = client1.getAvatarAsPng();
        Assertions.assertNotNull(png);
        Assertions.assertArrayEquals(pngBytes, png.toBytes());

        PngImageAsJson pngJson = client2.getAvatarAsJson();
        Assertions.assertNotNull(pngJson.getContent());
        Assertions.assertArrayEquals(pngBytes, pngJson.getContent());

        png = client2.getAvatarAsPng();
        Assertions.assertNotNull(png);
        Assertions.assertArrayEquals(pngBytes, png.toBytes());
    }
}
