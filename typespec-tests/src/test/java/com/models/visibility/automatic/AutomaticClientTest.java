// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.models.visibility.automatic;

import com.azure.core.util.BinaryData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// These cases are using protocol method, we don't support automatic visibility for convenience methods yet, the tests are added for cadl-ranch coverage.
class AutomaticClientTest {

    AutomaticClient client = new AutomaticClientBuilder().buildClient();

    @Test
    void getModel() {
        client.getModelWithResponse(BinaryData.fromString("{\"queryProp\": 123}"), null);
    }

    @Test
    void headModel() {
        client.headModelWithResponse(BinaryData.fromString("{\"queryProp\": 123}"), null);
    }

    @Test
    void putModel() {
        client.putModelWithResponse(BinaryData.fromString("{\"createProp\": [\"foo\",\"bar\"], \"updateProp\": [1, 2]}"), null);
    }

    @Test
    void patchModel() {
        client.patchModelWithResponse(BinaryData.fromString("{\"updateProp\": [1, 2]}"), null);
    }

    @Test
    void postModel() {
        client.postModelWithResponse(BinaryData.fromString("{\"createProp\": [\"foo\",\"bar\"]}"), null);
    }

    @Test
    void deleteModel() {
        client.deleteModelWithResponse(BinaryData.fromString("{\"deleteProp\": true}"), null);
    }
}