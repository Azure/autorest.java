// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Basic;
import fixtures.bodycomplex.models.CMYKColors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class BasicTests {
    @Test
    public void testSerialization() {
        Basic model =
                BinaryData.fromString("{\"id\":268043672,\"name\":\"hquvgjxpyb\",\"color\":\"cyan\"}")
                        .toObject(Basic.class);
        Assertions.assertEquals(268043672, model.getId());
        Assertions.assertEquals("hquvgjxpyb", model.getName());
        Assertions.assertEquals(CMYKColors.CYAN, model.getColor());
    }
}
