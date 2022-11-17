// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.FloatWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class FloatWrapperTests {
    @Test
    public void testDeserialize() throws Exception {
        FloatWrapper model =
                BinaryData.fromString("{\"field1\":32.06033,\"field2\":54.94055}").toObject(FloatWrapper.class);
        Assertions.assertEquals(32.06033F, model.getField1());
        Assertions.assertEquals(54.94055F, model.getField2());
    }

    @Test
    public void testSerialize() throws Exception {
        FloatWrapper model = new FloatWrapper().setField1(32.06033F).setField2(54.94055F);
        model = BinaryData.fromObject(model).toObject(FloatWrapper.class);
        Assertions.assertEquals(32.06033F, model.getField1());
        Assertions.assertEquals(54.94055F, model.getField2());
    }
}
