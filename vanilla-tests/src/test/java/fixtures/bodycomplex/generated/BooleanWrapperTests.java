// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.BooleanWrapper;
import org.junit.jupiter.api.Assertions;

public final class BooleanWrapperTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        BooleanWrapper model
            = BinaryData.fromString("{\"field_true\":false,\"field_false\":false}").toObject(BooleanWrapper.class);
        Assertions.assertEquals(false, model.isFieldTrue());
        Assertions.assertEquals(false, model.isFieldFalse());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        BooleanWrapper model = new BooleanWrapper().setFieldTrue(false).setFieldFalse(false);
        model = BinaryData.fromObject(model).toObject(BooleanWrapper.class);
        Assertions.assertEquals(false, model.isFieldTrue());
        Assertions.assertEquals(false, model.isFieldFalse());
    }
}
