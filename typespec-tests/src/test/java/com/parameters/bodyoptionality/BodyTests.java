// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.parameters.bodyoptionality;

import com.azure.core.http.rest.RequestOptions;
import com.azure.core.util.BinaryData;
import com.parameters.bodyoptionality.models.BodyModel;
import org.junit.jupiter.api.Test;

public class BodyTests {

    private final BodyOptionalityClient client = new BodyOptionalityClientBuilder().buildClient();
    private final OptionalExplicitClient optionalClient = new BodyOptionalityClientBuilder().buildOptionalExplicitClient();

    @Test
    public void testBodyOptionality() {
        client.requiredExplicit(new BodyModel("foo"));

        client.requiredImplicit(new BodyModel("foo"));

        optionalClient.setWithResponse(new RequestOptions()
                .setHeader("content-type", "application/json")
                .setBody(BinaryData.fromObject(new BodyModel("foo"))));
//        optionalClient.set(new BodyModel("foo"));
        optionalClient.omit();
    }
}
