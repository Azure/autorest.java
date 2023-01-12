// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.union;

import com.azure.core.util.BinaryData;
import com.cadl.union.models.ArrayData;
import com.cadl.union.models.DataUnionModel;
import com.cadl.union.models.SendLongOptions;
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
        DataUnionModel dataUnion = new DataUnionModel();
        dataUnion.setDataUnionString("dataString");
        SendLongOptions options = new SendLongOptions("id", "input", 1)
                .setDataUnion(dataUnion);
        BinaryData request = createRequest(options);

        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"dataString\"}", request.toString());

        dataUnion = new DataUnionModel();
        dataUnion.setDataUnionArrayData(new ArrayData(Collections.singletonList("item1")));
        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(dataUnion);
        request = createRequest(options);

        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":{\"data\":[\"item1\"]}}", request.toString());

        dataUnion = new DataUnionModel();
        dataUnion.setDataUnionByteArray("data".getBytes(StandardCharsets.UTF_8));
        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(dataUnion);
        request = createRequest(options);

        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"ZGF0YQ==\"}", request.toString());

        dataUnion = new DataUnionModel();
        dataUnion.setDataUnionTime(OffsetDateTime.of(2020, 1, 1, 4, 30, 10, 0, ZoneOffset.UTC));
        options = new SendLongOptions("id", "input", 1)
                .setDataUnion(dataUnion);
        request = createRequest(options);

        Assertions.assertEquals("{\"dataInt\":1,\"input\":\"input\",\"dataUnion\":\"2020-01-01T04:30:10Z\"}", request.toString());
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
