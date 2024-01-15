// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.payload.multipart;

import com.azure.core.http.policy.HttpLogDetailLevel;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.BinaryData;
import com.payload.FileUtils;
import com.payload.multipart.models.Address;
import com.payload.multipart.models.BinaryArrayPartsRequest;
import com.payload.multipart.models.ComplexPartsRequest;
import com.payload.multipart.models.JsonArrayPartsRequest;
import com.payload.multipart.models.JsonPartRequest;
import com.payload.multipart.models.MultiBinaryPartsRequest;
import com.payload.multipart.models.MultiPartRequest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;

public class MultipartTests {

    private final MultiPartClient client = new MultiPartClientBuilder().httpLogOptions(new HttpLogOptions().setLogLevel(HttpLogDetailLevel.BODY_AND_HEADERS)).buildClient();
    private final MultiPartAsyncClient asyncClient = new MultiPartClientBuilder().buildAsyncClient();

    private static final Path FILE = FileUtils.getJpgFile();

    private static final Path PNG_FILE = FileUtils.getPngFile();

    @Test
    public void testBasic() {
        MultiPartRequest request = new MultiPartRequest(
                "123",
                BinaryData.fromFile(FILE));

        client.basic(request);

        request.setProfileImageFilename("image.jpg");
        asyncClient.basic(request).block();
    }

    @Test
    public void testJson() {
        client.jsonPart(new JsonPartRequest(
                new Address("X"),
                BinaryData.fromFile(FILE)));
    }

    @Test
    public void testJsonArray() {
        client.jsonArrayParts(new JsonArrayPartsRequest(
                BinaryData.fromFile(FILE),
                Arrays.asList(new Address("Y"), new Address("Z")))
                .setProfileImageFilename("image.jpg"));
    }

    @Test
    public void testMultipleFiles() {
        client.multiBinaryParts(new MultiBinaryPartsRequest(
                BinaryData.fromFile(FILE))
                .setPicture(BinaryData.fromFile(FileUtils.getPngFile())));

        // "picture" be optional
        asyncClient.multiBinaryParts(new MultiBinaryPartsRequest(BinaryData.fromFile(FILE))).block();
    }

    @Test
    public void testFileArray() {
        // provide no filename
        client.binaryArrayParts(new BinaryArrayPartsRequest(
                "123",
                Arrays.asList(BinaryData.fromFile(PNG_FILE), BinaryData.fromFile(PNG_FILE))));

        // provide only 1 filename, when there are 2 files
        // filename contains non-ASCII
        asyncClient.binaryArrayParts(new BinaryArrayPartsRequest(
                "123",
                Arrays.asList(BinaryData.fromFile(PNG_FILE), BinaryData.fromFile(PNG_FILE)))
                .setPicturesFilenames(Collections.singletonList("voilà.jpg"))).block();
    }

    @Test
    public void testComplex() {
        client.complex(new ComplexPartsRequest(
                "123",
                new Address("X"),
                BinaryData.fromFile(FILE),
                Arrays.asList(new Address("Y"), new Address("Z")),
                Arrays.asList(BinaryData.fromFile(PNG_FILE), BinaryData.fromFile(PNG_FILE))));

        // provide 3 filenames, when there are 2 files
        asyncClient.complex(new ComplexPartsRequest(
                "123",
                new Address("X"),
                BinaryData.fromFile(FILE),
                Arrays.asList(new Address("Y"), new Address("Z")),
                Arrays.asList(BinaryData.fromFile(PNG_FILE), BinaryData.fromFile(PNG_FILE)))
                .setPicturesFilenames(Arrays.asList("picture1", "picture2", "picture3"))).block();
    }
}
