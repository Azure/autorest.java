/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.List;

/**
 * Example node for a Map.
 */
public class MapNode extends ExampleNode {

    private final List<String> keys = new ArrayList<>();

    public MapNode(IType clientType, Object objectValue) {
        super(clientType, objectValue);
    }

    public List<String> getKeys() {
        return keys;
    }
}
