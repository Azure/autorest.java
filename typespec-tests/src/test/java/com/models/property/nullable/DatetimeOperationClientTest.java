// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.property.nullable;

import com.azure.core.util.BinaryData;
import com.models.property.nullable.models.DatetimeProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class DatetimeOperationClientTest {

    DatetimeOperationClient client = new NullableClientBuilder().buildDatetimeOperationClient();

    @Test
    void patchNonNullWithResponse() {
        OffsetDateTime offsetDateTime = OffsetDateTime.parse("2022-08-26T18:38:00Z");
        DatetimeProperty datetimeProperty = new DatetimeProperty("foo", offsetDateTime);
        client.patchNonNullWithResponse(BinaryData.fromObject(datetimeProperty), null);
    }

    @Disabled("patch null not supported yet, com.azure.core.exception.HttpResponseException: Status code 400, \"{\"message\":\"Body provided doesn't match expected body\",\"expected\":{\"requiredProperty\":\"foo\",\"nullableProperty\":null},\"actual\":{\"requiredProperty\":\"foo\"}}\"\n")
    @Test
    void patchNullWithResponse() {
        DatetimeProperty datetimeProperty = new DatetimeProperty("foo", null);
        client.patchNullWithResponse(BinaryData.fromObject(datetimeProperty), null);
    }

    @Test
    void getNonNull() {
        DatetimeProperty response = client.getNonNull();
        assertNotNull(response.getNullableProperty());
    }

    @Test
    void getNull() {
        DatetimeProperty response = client.getNull();
        assertNull(response.getNullableProperty());
    }
}