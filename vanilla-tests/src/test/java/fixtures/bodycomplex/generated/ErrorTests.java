// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Error;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class ErrorTests {
    @Test
    public void testSerialization() {
        Error model = BinaryData.fromString("{\"status\":1767665612,\"message\":\"hmtzopbsph\"}").toObject(Error.class);
        Assertions.assertEquals(1767665612, model.getStatus());
        Assertions.assertEquals("hmtzopbsph", model.getMessage());
    }
}
