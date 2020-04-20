package com.azure.autorest.model.clientmodel;

import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A sequence type used by a client.
 */
public class ListType extends GenericType {
    /**
     * Create a new ListType from the provided properties.
     * @param elementType The type of elements that are stored in this sequence.
     */
    public ListType(IType elementType) {
        super("java.util", "List", elementType);
    }

    /**
     * The type of elements that are stored in this sequence.
     */
    public final IType getElementType() {
        return getTypeArguments()[0];
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        super.addImportsTo(imports, includeImplementationImports);
    }

    @Override
    public String validate(String expression) {
        return validate(expression, 0);
    }

    @Override
    public String validate(String expression, int depth) {
        String var = depth == 0 ? "e" : "e" + depth;
        String elementValidation = getElementType() instanceof GenericType
                ? ((GenericType) getElementType()).validate(var, ++depth)
                : getElementType().validate(var);
        if (elementValidation != null) {
            return String.format("%s.forEach(%s -> %s)", expression, var, elementValidation);
        } else {
            return null;
        }
    }
}
