// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.model.generated;

import com.cadl.model.models.NestedModel;
import com.cadl.model.models.NestedModel1;
import com.cadl.model.models.NestedModel2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public final class ModelOpPutNestedTests extends ModelClientTestBase {

    @Test
    @Disabled
    public void testModelOpPutNestedTests() {
        // method invocation
        NestedModel response = modelClient.putNested(null);
        // response assertion
        Assertions.assertNotNull(response);
        // verify property "nested1"
        NestedModel1 responseNested1 = response.getNested1();
        Assertions.assertNotNull(responseNested1);
        NestedModel2 responseNested1Nested2 = responseNested1.getNested2();
        Assertions.assertNotNull(responseNested1Nested2);
        Assertions.assertEquals("123", responseNested1Nested2.getData());
    }
}
