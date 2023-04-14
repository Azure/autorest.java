// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.models.property.nullable.models.CollectionsModelProperty;
import com.models.property.nullable.models.InnerModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class CollectionsModelClientTest {

    CollectionsModelClient client = new NullableClientBuilder().buildCollectionsModelClient();

    @Test
    void patchNonNullWithResponse() {
        CollectionsModelProperty property = new CollectionsModelProperty("foo", Arrays.asList(new InnerModel("hello"), new InnerModel("world")));
        client.patchNonNullWithResponse(BinaryData.fromObject(property), null);
    }

    @Disabled("patch null not supported yet, com.azure.core.exception.HttpResponseException: Status code 400, \"{\"message\":\"Body provided doesn't match expected body\",\"expected\":{\"requiredProperty\":\"foo\",\"nullableProperty\":null},\"actual\":{\"requiredProperty\":\"foo\"}}\"\n")
    @Test
    void patchNullWithResponse() {
        CollectionsModelProperty property = new CollectionsModelProperty("foo", null);
        client.patchNonNullWithResponse(BinaryData.fromObject(property), null);
    }

    @Test
    void getNonNull() {
        CollectionsModelProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        CollectionsModelProperty response = client.getNull();
        assertNull(response.getNullableProperty());
    }
}