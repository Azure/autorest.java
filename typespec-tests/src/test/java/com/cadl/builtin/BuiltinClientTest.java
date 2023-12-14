// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.builtin;

import com.azure.core.util.BinaryData;
import com.cadl.builtin.models.Builtin;
import com.type.dictionary.models.InnerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

class BuiltinClientTest {
    @Test
    public void testSerializationForFloatMapProperty() {
        Builtin model = new Builtin(true, "value1", new byte[]{}, 1, 1, BigDecimal.ONE, 1L, 1.1, 1.1, Duration.ZERO, LocalDate.now(), OffsetDateTime.now(), null, null, null, new HashMap<>(), null);
        model.getNullableFloatDict().put("key", null);
        String json = BinaryData.fromObject(model).toString();
    }

    @Test
    public void testSerializationForModelMapProperty() {
        InnerModel model = new InnerModel("a");
        Map<String, InnerModel> map = new HashMap<>();
        map.put("b", null);
        model.setChildren(map);
        String json = BinaryData.fromObject(model).toString();
    }
}