// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.type.model.flatten;

import com.type.model.flatten.models.ChildFlattenModel;
import com.type.model.flatten.models.ChildModel;
import com.type.model.flatten.models.FlattenModel;
import com.type.model.flatten.models.NestedFlattenModel;
import org.junit.jupiter.api.Test;

public class FlattenClientTests {
    private final FlattenClient flattenClient = new FlattenClientBuilder().buildClient();

    @Test
    public void testPutFlattenModel() {
        flattenClient.putFlattenModel(new FlattenModel("foo", new ChildModel("bar", 10)));
        flattenClient.putNestedFlattenModel(new NestedFlattenModel("foo", new ChildFlattenModel("bar", new ChildModel("test",10))));
    }

}