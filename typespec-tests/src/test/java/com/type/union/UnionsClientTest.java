// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.union;

import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class UnionsClientTest {

    private final UnionClient client = new UnionClientBuilder().buildClient();

    @Test
    void sendInt() {
        client.sendIntWithResponse(BinaryData.fromString("{ \"simpleUnion\": 1 }"), null);
    }

    @Test
    void sendIntArray() {
        client.sendIntArrayWithResponse(BinaryData.fromString("{ \"simpleUnion\": [1, 2] }\n"), null);
    }

    @Test
    void sendFirstNamedUnionValue() {
        client.sendFirstNamedUnionValueWithResponse(BinaryData.fromString("{ \"namedUnion\": { \"name\": \"model1\", \"prop1\": 1 } }"), null);
    }

    @Test
    void sendSecondNamedUnionValue() {
        client.sendSecondNamedUnionValueWithResponse(BinaryData.fromString("{ \"namedUnion\": { \"name\": \"model2\", \"prop2\": 2 } }\n"), null);
    }

    @Test
    void receiveString() {
        Response<BinaryData> response = client.receiveStringWithResponse(null);
        Assertions.assertNotNull(response.getValue());
    }

    @Test
    void receiveIntArray() {
        Response<BinaryData> response = client.receiveIntArrayWithResponse(null);
        Assertions.assertNotNull(response.getValue());
    }

    @Test
    void receiveFirstNamedUnionValue() {
        Response<BinaryData> response = client.receiveFirstNamedUnionValueWithResponse(null);
        Assertions.assertNotNull(response.getValue());
    }

    @Test
    void receiveSecondNamedUnionValue() {
        Response<BinaryData> response = client.receiveSecondNamedUnionValueWithResponse(null);
        Assertions.assertNotNull(response.getValue());
    }
}
