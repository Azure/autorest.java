// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.payload.multipart;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.payload.FileUtils;
import com.payload.multipart.models.Address;
import com.payload.multipart.models.JsonArrayPartsRequest;
import com.payload.multipart.models.JsonPartRequest;
import com.payload.multipart.models.MultiBinaryPartsRequest;
import com.payload.multipart.models.MultiPartRequest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;

public class MultipartTests {

    private final MultiPartClient client = new MultiPartClientBuilder().httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildClient();
    private final MultiPartAsyncClient asyncClient = new MultiPartClientBuilder().buildAsyncClient();

    private static final Path FILE = FileUtils.getJpgFile();

    @Test
    public void testBasic() {
        MultiPartRequest request = new MultiPartRequest("123", BinaryData.fromFile(FILE));

        client.basic(request);

        request.setProfileImageFilename("image.jpg");
        asyncClient.basic(request).block();
    }

    @Test
    public void testJson() {
        client.jsonPart(new JsonPartRequest(new Address("X"), BinaryData.fromFile(FILE)));
    }

    @Test
    public void testJsonArray() {
        client.jsonArrayParts(new JsonArrayPartsRequest(BinaryData.fromFile(FILE), Arrays.asList(new Address("Y"), new Address("Z"))).setProfileImageFilename("image.jpg"));
    }

    @Test
    public void testMultipleFiles() {
        client.multiBinaryParts(new MultiBinaryPartsRequest(BinaryData.fromFile(FILE)).setPicture(BinaryData.fromFile(FileUtils.getPngFile())));

        asyncClient.multiBinaryParts(new MultiBinaryPartsRequest(BinaryData.fromFile(FILE)).setPicture(BinaryData.fromFile(FileUtils.getPngFile())));
    }
}
