// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin.generated;

import com.azure.core.util.Configuration;
import com.cadl.builtin.BuiltinClient;
import com.cadl.builtin.BuiltinClientBuilder;
import com.cadl.builtin.models.Builtin;
import com.cadl.builtin.models.Encoded;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class BuiltinOpWrite {
    public static void main(String[] args) {
        BuiltinClient builtinClient
            = new BuiltinClientBuilder().endpoint(Configuration.getGlobalConfiguration().get("ENDPOINT")).buildClient();
        // BEGIN:com.cadl.builtin.generated.builtinopwriteconvenience.builtinopwrite
        builtinClient.writeConvenience(
            new Builtin(true, "string", "[1, 2, 3]".getBytes(), 10, 10L, new BigDecimal("1"), 10L, 1.0, 1.0,
                Duration.parse("P1h"), LocalDate.parse("1999-01-01"), OffsetDateTime.parse("1988-07-20T13:34:45"),
                Arrays.asList("a", "b", "c"), mapOf("myBytes", "[1, 2, 3]".getBytes()), "https://www.example.org",
                mapOf("max", 15.0D, "min", 14.0D, "average", 14.3D), new Encoded()));
        // END:com.cadl.builtin.generated.builtinopwriteconvenience.builtinopwrite
    }

    // Use "Map.of" if available
    @SuppressWarnings("unchecked")
    private static <T> Map<String, T> mapOf(Object... inputs) {
        Map<String, T> map = new HashMap<>();
        for (int i = 0; i < inputs.length; i += 2) {
            String key = (String) inputs[i];
            T value = (T) inputs[i + 1];
            map.put(key, value);
        }
        return map;
    }
}
