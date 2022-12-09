// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.extensibleenums;

import com.extensibleenums.models.DaysOfWeekExtensibleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class ExtensibleEnumsClientTest {

    ExtensibleEnumsClient client = new ExtensibleEnumsClientBuilder().buildClient();

    @Test
    void getKnownValue() {
        DaysOfWeekExtensibleEnum daysOfWeekExtensibleEnum = client.getKnownValue();
        Assertions.assertEquals(DaysOfWeekExtensibleEnum.MONDAY, daysOfWeekExtensibleEnum);
    }

    @Test
    @Disabled("issue https://github.com/Azure/cadl-ranch/issues/158")
    void getUnknownValue() {
        DaysOfWeekExtensibleEnum daysOfWeekExtensibleEnum = client.getUnknownValue();
        Assertions.assertEquals("Weekend", daysOfWeekExtensibleEnum.toString());
    }

    @Test
    void putKnownValue() {
        DaysOfWeekExtensibleEnum daysOfWeekExtensibleEnum = DaysOfWeekExtensibleEnum.MONDAY;
        client.putKnownValue(daysOfWeekExtensibleEnum);
    }

    @Test
    @Disabled("issue https://github.com/Azure/cadl-ranch/issues/158")
    void putUnknownValue() {
        DaysOfWeekExtensibleEnum daysOfWeekExtensibleEnum = DaysOfWeekExtensibleEnum.fromString("Weekend");
        client.putUnknownValue(daysOfWeekExtensibleEnum);
    }
}