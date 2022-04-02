// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.DotFish;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DotFishTests {
    @Test
    public void testSerialization() {
        DotFish model =
                BinaryData.fromString("{\"fish.type\":\"DotFish\",\"species\":\"ejdpvwryoq\"}").toObject(DotFish.class);
        Assertions.assertEquals("ejdpvwryoq", model.getSpecies());
    }
}
