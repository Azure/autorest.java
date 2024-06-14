// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.flatten.generated;

import com.cadl.flatten.models.SendLongOptions;
import com.cadl.flatten.models.TodoItemStatus;
import com.cadl.flatten.models.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public final class FlattenOpSendLongTests extends FlattenClientTestBase {
    @Test
    @Disabled
    public void testFlattenOpSendLongTests() {
        // method invocation
        flattenClient
            .sendLong(new SendLongOptions("myRequiredId", "myRequiredInput", 11, "title", TodoItemStatus.NOT_STARTED)
                .setFilter("name=myName")
                .setUser(new User("myOptionalUser"))
                .setDataIntOptional(12)
                .setDataLong(13L)
                .setDataFloat(14.0D)
                .setDescription("description"));
    }
}
