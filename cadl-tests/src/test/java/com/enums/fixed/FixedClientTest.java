// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.enums.fixed;

import com.enums.fixed.models.DaysOfWeekEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FixedClientTest {

    FixedClient client = new FixedClientBuilder().buildClient();

    @Test
    void getKnownValue() {
        DaysOfWeekEnum daysOfWeekEnum = client.getKnownValue();
        Assertions.assertEquals(DaysOfWeekEnum.MONDAY, daysOfWeekEnum);
    }

    @Test
    void putKnownValue() {
        client.putKnownValue(DaysOfWeekEnum.MONDAY);
    }


    // Not a valid test for Java, as compiler will fail at "DaysOfWeekEnum.Weekend"
//    @Test
//    void putUnknownValue() {
//        client.putUnknownValue(DaysOfWeekEnum.Weekend);
//    }
}