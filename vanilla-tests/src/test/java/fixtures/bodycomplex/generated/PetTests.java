// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;

import fixtures.bodycomplex.models.Pet;
import org.junit.jupiter.api.Assertions;

public final class PetTests {
    @org.junit.jupiter.api.Test
    public void testDeserialize() throws Exception {
        Pet model = BinaryData.fromString("{\"id\":920256064,\"name\":\"rtfw\"}").toObject(Pet.class);
        Assertions.assertEquals(920256064, model.getId());
        Assertions.assertEquals("rtfw", model.getName());
    }

    @org.junit.jupiter.api.Test
    public void testSerialize() throws Exception {
        Pet model = new Pet().setId(920256064).setName("rtfw");
        model = BinaryData.fromObject(model).toObject(Pet.class);
        Assertions.assertEquals(920256064, model.getId());
        Assertions.assertEquals("rtfw", model.getName());
    }
}
