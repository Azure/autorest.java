// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.Dog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DogTests {
    @Test
    public void testSerialization() {
        Dog model =
                BinaryData.fromString("{\"food\":\"ybrkxvdumj\",\"id\":1041852302,\"name\":\"fwvukxgaud\"}")
                        .toObject(Dog.class);
        Assertions.assertEquals(1041852302, model.getId());
        Assertions.assertEquals("fwvukxgaud", model.getName());
        Assertions.assertEquals("ybrkxvdumj", model.getFood());
    }
}
