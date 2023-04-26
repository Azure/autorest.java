// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.parameters.spread;

import com.parameters.spread.models.BodyParameter;
import com.parameters.spread.models.SpreadWithMultipleParametersOptions;
import org.junit.jupiter.api.Test;

public class SpreadTests {

    private final AliasClient aliasClient = new SpreadClientBuilder().buildAliasClient();
    private final ModelClient modelClient = new SpreadClientBuilder().buildModelClient();

    @Test
    public void testSpread() {

        aliasClient.spreadAsRequestBody("foo");

        aliasClient.spreadAsRequestParameter("1", "bar", "foo");

        // options bag
        aliasClient.spreadWithMultipleParameters(new SpreadWithMultipleParametersOptions("1", "bar",
                "foo1", "foo2", "foo3", "foo4", "foo5", "foo6"));
    }

    @Test
    public void testModel() {

        modelClient.spreadAsRequestBody(new BodyParameter("foo"));
    }
}
