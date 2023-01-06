// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.IntWrapper;
import org.junit.jupiter.api.Assertions;

public final class IntWrapperTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        IntWrapper model =
                BinaryData.fromString("{\"field1\":318397575,\"field2\":1830732530}").toObject(IntWrapper.class);
        Assertions.assertEquals(318397575, model.getField1());
        Assertions.assertEquals(1830732530, model.getField2());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        IntWrapper model = new IntWrapper().setField1(318397575).setField2(1830732530);
        model = BinaryData.fromObject(model).toObject(IntWrapper.class);
        Assertions.assertEquals(318397575, model.getField1());
        Assertions.assertEquals(1830732530, model.getField2());
    }
}
