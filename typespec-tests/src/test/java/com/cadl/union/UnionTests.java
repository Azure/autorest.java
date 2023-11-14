// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.cadl.union;

import com.azure.core.util.BinaryData;
import com.cadl.union.models.Result;
import org.junit.jupiter.api.Test;

public class UnionTests {

    private static final byte[] bytes = new byte[] { 'a' };

    @Test
    public void testSerialization() {
        Result result = new Result("name", BinaryData.fromObject(bytes));
        BinaryData json = BinaryData.fromObject(result);
        result = json.toObject(Result.class);
    }
}
