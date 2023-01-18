// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.union;

import com.azure.core.util.BinaryData;
import com.cadl.union.models.ArrayData;
import com.cadl.union.models.ArrayDataDataUnionModel;
import com.cadl.union.models.ByteArrayDataUnionModel;
import com.cadl.union.models.SendLongOptions;
import com.cadl.union.models.StringDataUnionModel;
import com.cadl.union.models.TimeDataUnionModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnionTest {

    @Test
    public void testUnion() {
        SendLongOptions options = new SendLongOptions("id", "input", 1)
                .setDataUnion(new StringDataUnionModel("dataString"));
        BinaryData request = createRequest(options);
        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"dataString\"}", request.toString());

        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(new ArrayDataDataUnionModel(new ArrayData(Collections.singletonList("item1"))));
        request = createRequest(options);
        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":{\"data\":[\"item1\"]}}", request.toString());

        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(new ByteArrayDataUnionModel("data".getBytes(StandardCharsets.UTF_8)));
        request = createRequest(options);
        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"ZGF0YQ==\"}", request.toString());

        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(new TimeDataUnionModel(OffsetDateTime.of(2020, 1, 1, 4, 30, 10, 0, ZoneOffset.UTC)));
        request = createRequest(options);
        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"2020-01-01T04:30:10Z\"}", request.toString());

        options = new SendLongOptions("id", "input", 1)
                .setDataFloat(3.5);
        request = createRequest(options);
        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"data_float\":3.5}", request.toString());
    }

    private static BinaryData createRequest(SendLongOptions options) {
        Map<String, Object> requestObj = new HashMap<>();
        requestObj.put("user", options.getUser());
        requestObj.put("input", options.getInput());
        requestObj.put("dataInt", options.getDataInt());
        requestObj.put("dataUnion", options.getDataUnion());
        requestObj.put("dataLong", options.getDataLong());
        requestObj.put("data_float", options.getDataFloat());
        return BinaryData.fromObject(requestObj);
    }
}
