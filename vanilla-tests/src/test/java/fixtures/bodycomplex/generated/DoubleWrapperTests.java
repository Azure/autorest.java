// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.generated;

import com.azure.core.util.BinaryData;
import fixtures.bodycomplex.models.DoubleWrapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public final class DoubleWrapperTests {
    @Test
    public void testSerialization() {
        DoubleWrapper model =
                BinaryData.fromString(
                                "{\"field1\":16.923790768541068,\"field_56_zeros_after_the_dot_and_negative_zero_before_dot_and_this_is_a_long_field_name_on_purpose\":10.963676785184385}")
                        .toObject(DoubleWrapper.class);
        Assertions.assertEquals(16.923790768541068, model.getField1());
        Assertions.assertEquals(
                10.963676785184385,
                model.getField56ZerosAfterTheDotAndNegativeZeroBeforeDotAndThisIsALongFieldNameOnPurpose());
    }
}
