// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.DictionaryWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DictionaryWrapperTests {
    @Test
    public void testSerialization() {
        DictionaryWrapper model =
                BinaryData.fromString(
                                "{\"defaultProgram\":{\"agdfmglzlh\":\"jxrifkwmrv\",\"ktsizntoci\":\"paouajpsqu\",\"cmpoyfdkfo\":\"gknygjofjd\"}}")
                        .toObject(DictionaryWrapper.class);
        Assertions.assertEquals("jxrifkwmrv", model.getDefaultProgram().get("agdfmglzlh"));
    }
}
