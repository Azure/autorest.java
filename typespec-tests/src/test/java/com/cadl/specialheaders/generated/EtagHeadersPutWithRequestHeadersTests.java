// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.specialheaders.generated;

import com.azure.core.http.RequestConditions;
import com.cadl.specialheaders.models.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public final class EtagHeadersPutWithRequestHeadersTests extends SpecialHeadersClientTestBase {

    @Test
    @Disabled
    public void testEtagHeadersPutWithRequestHeadersTests() {
        // method invocation
        Resource response = etagHeadersClient.putWithRequestHeaders("name",
            new Resource("myType").setDescription("This is sample for Etag headers"),
            new RequestConditions().setIfMatch("64e005"));
        // response assertion
        Assertions.assertNotNull(response);
    }
}
