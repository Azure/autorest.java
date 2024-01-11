// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.DotSalmon;
import org.junit.jupiter.api.Assertions;

public final class DotSalmonTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        DotSalmon model = BinaryData
            .fromString(
                "{\"fish.type\":\"kgiawxklryplwck\",\"location\":\"tyxolniwpwc\",\"iswild\":true,\"species\":\"syyp\"}")
            .toObject(DotSalmon.class);
        Assertions.assertEquals("syyp", model.getSpecies());
        Assertions.assertEquals("tyxolniwpwc", model.getLocation());
        Assertions.assertEquals(true, model.iswild());
    }
}
