// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.union;

import com.azure.core.util.BinaryData;
import com.cadl.union.models.Result;
import com.cadl.union.models.SubResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class UnionTests {

    private static final byte[] bytes = new byte[] { 'a' };

    @Test
    public void testSerialization() {
        // test non-null Union
        Result result = new Result("name", BinaryData.fromObject(bytes));
        BinaryData json = BinaryData.fromObject(result);
        result = json.toObject(Result.class);
        Assertions.assertNotNull(result.getData());

        // test null Union
        SubResult subResult = new SubResult("name", BinaryData.fromObject(bytes));
        json = BinaryData.fromObject(subResult);
        subResult = json.toObject(SubResult.class);
        Assertions.assertNotNull(result.getData());
        Assertions.assertNull(subResult.getArrayData());

        subResult = new SubResult("name", BinaryData.fromObject(bytes));
        subResult.setArrayData(BinaryData.fromObject(Collections.singletonList("data1")));
        json = BinaryData.fromObject(subResult);
        subResult = json.toObject(SubResult.class);
        Assertions.assertNotNull(subResult.getArrayData());
    }
}
