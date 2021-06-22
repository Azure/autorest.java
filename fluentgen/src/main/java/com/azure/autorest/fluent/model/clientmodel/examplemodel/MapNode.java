/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.IType;

import java.util.HashMap;
import java.util.Map;

public class MapNode extends ExampleNode {

    private final Map<ExampleNode, String> keys = new HashMap<>();

    public MapNode(IType clientType, Object objectValue) {
        super(clientType, objectValue);
    }

    public Map<ExampleNode, String> getKeys() {
        return keys;
    }
}
