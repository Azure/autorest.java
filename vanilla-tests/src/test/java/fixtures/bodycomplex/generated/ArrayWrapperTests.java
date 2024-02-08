// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.ArrayWrapper;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;

public final class ArrayWrapperTests {

    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        ArrayWrapper model = BinaryData.fromString("{\"array\":[\"bc\"]}").toObject(ArrayWrapper.class);
        Assertions.assertEquals("bc", model.getArray().get(0));
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        ArrayWrapper model = new ArrayWrapper().setArray(Arrays.asList("bc"));
        model = BinaryData.fromObject(model).toObject(ArrayWrapper.class);
        Assertions.assertEquals("bc", model.getArray().get(0));
    }
}
