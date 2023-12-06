// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.payload.multipart;

import com.azure.core.util.BinaryData;
import com.payload.FileUtils;
import com.payload.multipart.models.MultiPartRequest;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

public class MultipartTests {

    private final MultiPartClient client = new MultiPartClientBuilder().buildClient();
    private final MultiPartAsyncClient asyncClient = new MultiPartClientBuilder().buildAsyncClient();

    @Test
    public void testBasic() {
        Path file = FileUtils.getJpgFile();
        MultiPartRequest request = new MultiPartRequest("123", BinaryData.fromFile(file));

        client.basic(request);

        request.setProfileImageFilename("image.jpg");
        asyncClient.basic(request).block();
    }
}
