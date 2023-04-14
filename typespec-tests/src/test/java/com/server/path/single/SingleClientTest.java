// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.server.path.single;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SingleClientTest {

    SingleClient client = new SingleClientBuilder().endpoint("http://localhost:3000").buildClient();

    @Test
    void myOp() {
        client.myOp();
    }
}