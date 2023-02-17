// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.unions.generated.unions;

import com.unions.UnionsClient;
import com.unions.UnionsClientBuilder;
import com.unions.models.Model1;
import com.unions.models.Model1NamedUnionModel;
import com.unions.models.ModelWithNamedUnionProperty;
import org.junit.jupiter.api.Test;

public class UnionTests {

    private UnionsClient client = new UnionsClientBuilder().buildClient();

    @Test
    public void testNamed() {
        client.sendFirstNamedUnionValue(new ModelWithNamedUnionProperty(
                new Model1NamedUnionModel(new Model1("model1", 1))));
    }
}
