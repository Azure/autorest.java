// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.models.property.nullable.models.DurationProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class DurationOperationClientTest {

    DurationOperationClient client = new NullableClientBuilder().buildDurationOperationClient();

    @Test
    void patchNonNullWithResponse() {
        DurationProperty property = new DurationProperty("foo", Duration.parse("PT2974H14M12.011S"));
        client.patchNonNullWithResponse(BinaryData.fromObject(property), null);
    }

    @Disabled("patch null not supported yet, com.azure.core.exception.HttpResponseException: Status code 400, \"{\"message\":\"Body provided doesn't match expected body\",\"expected\":{\"requiredProperty\":\"foo\",\"nullableProperty\":null},\"actual\":{\"requiredProperty\":\"foo\"}}\"\n")
    @Test
    void patchNullWithResponse() {
        DurationProperty property = new DurationProperty("foo", null);
        client.patchNullWithResponse(BinaryData.fromObject(property), null);
    }

    @Test
    void getNonNull() {
        DurationProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        DurationProperty response = client.getNull();
        assertNull(response.getNullableProperty());

    }
}