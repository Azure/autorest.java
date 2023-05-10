// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.builtin;

import com.azure.core.util.BinaryData;
import com.cadl.builtin.models.Encoded;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class EncodeTests {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    public void testEncodedDuration() throws Exception {
        Duration timeInSeconds = Duration.ofSeconds(5);
        Duration timeInSecondsFraction = Duration.ofMillis(1500);

        Encoded encoded = new Encoded(timeInSeconds, timeInSecondsFraction);

        Assertions.assertEquals(timeInSeconds, encoded.getTimeInSeconds());
        Assertions.assertEquals(timeInSecondsFraction, encoded.getTimeInSecondsFraction());
        Assertions.assertNull(encoded.getTimeInSecondsOptional());

        BinaryData json = BinaryData.fromObject(encoded);

        JsonNode jsonNode = OBJECT_MAPPER.readTree(json.toString());
        double timeInSecondsInJson = jsonNode.get("timeInSeconds").asDouble();
        double timeInSecondsFractionInJson = jsonNode.get("timeInSecondsFraction").asDouble();
        Assertions.assertEquals(5, timeInSecondsInJson);
        Assertions.assertEquals(1.5, timeInSecondsFractionInJson);
    }
}
