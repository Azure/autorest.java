// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.flatten.generated;

import com.cadl.flatten.models.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public final class FlattenOpSendTests extends FlattenClientTestBase {

    @Test
    @Disabled
    public void testFlattenOpSendTests() {
        // method invocation
        flattenClient.send("myRequiredId", "myRequiredInput", new User("myOptionalUser"));
    }
}
