// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.parameters.bodyoptionality;

import com.parameters.bodyoptionality.models.BodyModel;
import org.junit.jupiter.api.Test;

public class BodyTests {

    private final BodyOptionalityClient client = new BodyOptionalityClientBuilder().buildClient();
    private final OptionalExplicitClient optionalClient = new BodyOptionalityClientBuilder().buildOptionalExplicitClient();

    @Test
    public void testBodyOptionality() {
        client.requiredExplicit(new BodyModel("foo"));

        client.requiredImplicit(new BodyModel("foo"));

        optionalClient.omit();
    }
}
