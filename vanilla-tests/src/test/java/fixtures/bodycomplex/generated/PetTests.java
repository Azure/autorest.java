// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Pet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class PetTests {
    @Test
    public void testSerialization() {
        Pet model = BinaryData.fromString("{\"id\":1850052044,\"name\":\"dkfogknygj\"}").toObject(Pet.class);
        Assertions.assertEquals(1850052044, model.getId());
        Assertions.assertEquals("dkfogknygj", model.getName());
    }
}
