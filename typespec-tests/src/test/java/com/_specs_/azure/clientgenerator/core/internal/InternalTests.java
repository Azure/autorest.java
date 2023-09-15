// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.clientgenerator.core.internal;

import com._specs_.azure.clientgenerator.core.internal.implementation.models.InternalModel;
import com._specs_.azure.clientgenerator.core.internal.models.PublicModel;
import com._specs_.azure.clientgenerator.core.internal.models.SharedModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InternalTests {

    private final InternalClient internalClient = new InternalClientBuilder()
            .buildClient();

    private final SharedClient sharedClient = new InternalClientBuilder()
            .buildSharedClient();

    @Test
    public void testInternalOnlyModel() {
        String name = "test";

        InternalModel modelResponseBody = internalClient.internalOnly(name);
        Assertions.assertEquals(name, modelResponseBody.getName());
    }

    @Test
    public void testPublicOnlyModel() {
        String name = "test";

        PublicModel modelResponseBody = internalClient.publicOnly(name);
        Assertions.assertEquals(name, modelResponseBody.getName());
    }

    @Test
    public void testSharedModel() {
        String name = "test";

        SharedModel modelResponseBody = sharedClient.publicMethod(name);
        Assertions.assertEquals(name, modelResponseBody.getName());

        SharedModel sharedModelResponseBody = sharedClient.internal(name);
        Assertions.assertEquals(name, sharedModelResponseBody.getName());
    }
}
