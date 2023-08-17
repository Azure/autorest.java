// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.encode.bytes;

import com.encode.bytes.models.Base64BytesProperty;
import com.encode.bytes.models.Base64UrlArrayBytesProperty;
import com.encode.bytes.models.Base64UrlBytesProperty;
import com.encode.bytes.models.DefaultBytesProperty;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class EncodeBytesTests {

    private final QueryClient queryClient = new BytesClientBuilder().buildQueryClient();
    private final HeaderClient headerClient = new BytesClientBuilder().buildHeaderClient();
    private final PropertyClient propertyClient = new BytesClientBuilder().buildPropertyClient();

    private final static byte[] DATA = "test".getBytes(StandardCharsets.UTF_8);

    @Test
    public void testQuery() {
        queryClient.defaultMethod(DATA);

        queryClient.base64(DATA);

        queryClient.base64Url(DATA);

        queryClient.base64UrlArray(Arrays.asList(DATA, DATA));
    }

    @Test
    public void testHeader() {
        headerClient.defaultMethod(DATA);

        headerClient.base64(DATA);

        headerClient.base64Url(DATA);

        headerClient.base64UrlArray(Arrays.asList(DATA, DATA));
    }

    @Test
    public void testProperty() {
        propertyClient.defaultMethod(new DefaultBytesProperty(DATA));

        propertyClient.base64(new Base64BytesProperty(DATA));

        propertyClient.base64Url(new Base64UrlBytesProperty(DATA));

        // fails due to this corner case https://github.com/Azure/autorest.java/issues/2170#issuecomment-1598116813
        propertyClient.base64UrlArray(new Base64UrlArrayBytesProperty(Arrays.asList(DATA, DATA)));
    }
}
