// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.builtin;

import com.azure.core.util.BinaryData;
import com.cadl.builtin.models.Encoded;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.OffsetDateTime;

public class EncodeTests {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private static final OffsetDateTime DATE = OffsetDateTime.parse("2019-10-12T07:20:50.52Z");
    private static final byte[] DATA = "data".getBytes(StandardCharsets.UTF_8);

    @Test
    public void testEncoded() throws Exception {
        Duration timeInSeconds = Duration.ofSeconds(5);
        Duration timeInSecondsFraction = Duration.ofMillis(1500);

        Encoded encoded = new Encoded(timeInSeconds, timeInSecondsFraction, DATE, DATE, DATA, DATA);

        Assertions.assertEquals(timeInSeconds, encoded.getTimeInSeconds());
        Assertions.assertEquals(timeInSecondsFraction, encoded.getTimeInSecondsFraction());
        Assertions.assertNull(encoded.getTimeInSecondsOptional());

        BinaryData json = BinaryData.fromObject(encoded);

        JsonNode jsonNode = OBJECT_MAPPER.readTree(json.toString());
        double timeInSecondsInJson = jsonNode.get("timeInSeconds").asDouble();
        double timeInSecondsFractionInJson = jsonNode.get("timeInSecondsFraction").asDouble();
        Assertions.assertEquals(5, timeInSecondsInJson);
        Assertions.assertEquals(1.5, timeInSecondsFractionInJson);
        Assertions.assertEquals("2019-10-12T07:20:50.520Z", jsonNode.get("dateTime").asText());
        Assertions.assertEquals("Sat, 12 Oct 2019 07:20:50 GMT", jsonNode.get("dateTimeRfc7231").asText());
        Assertions.assertEquals("ZGF0YQ==", jsonNode.get("base64").asText());
        Assertions.assertEquals("ZGF0YQ", jsonNode.get("base64url").asText());
    }

    @Test
    public void testEncodedDurationInvalidPrecision() {
        Duration timeInSeconds = Duration.ofMillis(5700);
        Duration timeInSecondsFraction = Duration.ofDays(1);

        Encoded encoded = new Encoded(timeInSeconds, timeInSecondsFraction, DATE, DATE, DATA, DATA);

        // since the wire type is long (in seconds), 5.7 seconds will be 5 seconds
        Assertions.assertEquals(5, encoded.getTimeInSeconds().getSeconds());
        Assertions.assertEquals(86400, encoded.getTimeInSecondsFraction().getSeconds());
    }
}
