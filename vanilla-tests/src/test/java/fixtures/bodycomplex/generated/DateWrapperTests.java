// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.models.DateWrapper;

public final class DateWrapperTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        DateWrapper model = BinaryData.fromString("{}").toObject(DateWrapper.class);
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        DateWrapper model = new DateWrapper();
        model = BinaryData.fromObject(model).toObject(DateWrapper.class);
    }
}
