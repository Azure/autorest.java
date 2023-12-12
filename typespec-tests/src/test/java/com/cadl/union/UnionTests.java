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

    private static final byte[] BYTES = new byte[] { 'a' };

    @Test
    public void testSerialization() {
        // test non-null Union (required property)
        Result result = new Result("name", BinaryData.fromObject(BYTES));
        BinaryData json = BinaryData.fromObject(result);
        result = json.toObject(Result.class);
        Assertions.assertNotNull(result.getData());

        // test null Union (optional property)
        SubResult subResult = new SubResult("name", BinaryData.fromObject(BYTES));
        json = BinaryData.fromObject(subResult);
        subResult = json.toObject(SubResult.class);
        Assertions.assertNotNull(result.getData());
        Assertions.assertNull(subResult.getArrayData());

        // test non-null Union (optional property)
        subResult = new SubResult("name", BinaryData.fromObject(BYTES));
        subResult.setArrayData(BinaryData.fromObject(Collections.singletonList("data1")));
        json = BinaryData.fromObject(subResult);
        subResult = json.toObject(SubResult.class);
        Assertions.assertNotNull(subResult.getArrayData());
    }
}
