package com.enums.fixed;

import com.azure.core.exception.HttpResponseException;
import com.enums.extensible.models.DaysOfWeekExtensibleEnum;
import com.enums.fixed.models.DaysOfWeekEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

//    client.putUnknownValue will throw below error, which will cause the request can not be sent to the test server.
//    java.lang.NumberFormatException: Cannot parse null string
//    at java.base/java.lang.Long.parseLong(Long.java:674)
//    at java.base/java.lang.Long.parseLong(Long.java:836)
    @Test
    void putUnknownValue() {
        Assertions.assertThrows(NumberFormatException.class, () -> client.putUnknownValue(DaysOfWeekEnum.fromString("Weekend")));
    }
}