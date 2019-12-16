package com.azure.autorest.model.clientmodel;

import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A map type used by a client.
 */
public class MapType extends GenericType {
    /**
     * Create a new MapType from the provided properties.
     * @param valueType The type of values that are stored in this dictionary.
     */
    public MapType(IType valueType) {
        super("java.util", "Map", ClassType.String, valueType);
    }

    /**
     * The type of values that are stored in this map.
     */
    public final IType getValueType() {
        return getTypeArguments()[1];
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        super.addImportsTo(imports, includeImplementationImports);
    }

    public String validate(String expression) {
        String elementValidation = getValueType().validate("e");
        if (elementValidation != null) {
            return String.format("%s.values().forEach(e -> %s)", expression, elementValidation);
        } else {
            return null;
        }
    }
}