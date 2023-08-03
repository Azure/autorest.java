// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.client.structure;

import com.client.structure.service.GroupClient;
import com.client.structure.service.RenamedOperationClient;
import com.client.structure.service.RenamedOperationClientBuilder;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class RenameOperationTests {

    private final RenamedOperationClient client = new RenamedOperationClientBuilder().client("renamed-operation").buildClient();
    private final GroupClient groupClient = new RenamedOperationClientBuilder().client("renamed-operation").buildGroupClient();

    @Disabled("mockapi bug")
    @Test
    public void testClient() {
        client.renamedOne();
        client.renamedThree();
        client.renamedFive();

        groupClient.renamedTwo();
        groupClient.renamedFour();
        groupClient.renamedSix();
    }
}
