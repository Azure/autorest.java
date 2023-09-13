// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com._specs_.azure.clientgenerator.core.access;

import com._specs_.azure.clientgenerator.core.access.implementation.models.AbstractModel;
import com._specs_.azure.clientgenerator.core.access.implementation.models.InternalDecoratorModelInInternal;
import com._specs_.azure.clientgenerator.core.access.implementation.models.NoDecoratorModelInInternal;
import com._specs_.azure.clientgenerator.core.access.implementation.models.OuterModel;
import com._specs_.azure.clientgenerator.core.access.implementation.models.PublicDecoratorModelInInternal;
import com._specs_.azure.clientgenerator.core.access.implementation.models.RealModel;
import com._specs_.azure.clientgenerator.core.access.models.SharedModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccessTests {

    private final PublicOperationClient publicClient = new AccessClientBuilder().buildPublicOperationClient();
    private final InternalOperationClient internalClient = new AccessClientBuilder().buildInternalOperationClient();
    private final SharedModelInOperationClient sharedClient = new AccessClientBuilder().buildSharedModelInOperationClient();
    private final RelativeModelInOperationClient relativeClient = new AccessClientBuilder().buildRelativeModelInOperationClient();

    @Test
    public void test() {
        publicClient.publicDecoratorInPublic("");
        publicClient.noDecoratorInPublic("");

//        internalClient.internalDecoratorInInternalWithResponse("", null);
//        internalClient.noDecoratorInInternalWithResponse("", null);
//        internalClient.publicDecoratorInInternalWithResponse("", null);
        internalClient.internalDecoratorInInternal("");
        internalClient.noDecoratorInInternal("");
        internalClient.publicDecoratorInInternal("");

        sharedClient.publicMethod("");
        sharedClient.internalWithResponse("", null).getValue().toObject(SharedModel.class);

//        relativeClient.operationWithResponse("", null);
//        relativeClient.discriminatorWithResponse("real", null).getValue();
        relativeClient.operation("");
        AbstractModel abstractModel = relativeClient.discriminator("real");
        Assertions.assertTrue(abstractModel instanceof RealModel);
    }
}
