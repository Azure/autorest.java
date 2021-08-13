package com.azure.autorest.model.clientmodel;

import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * A return value from a ClientMethod.
 */
public class ReturnValue {
    /**
     * The description of the return value.
     */
    private String description;
    /**
     * The type of the return value.
     */
    private IType type;
    /**
     * The type of the response body.
     */
    private IType responseBodyType;

    /**
     * Create a new ReturnValue object from the provided properties.
     * @param description The description of the return value.
     * @param type The type of the return value.
     */
    public ReturnValue(String description, IType type) {
        this.description = description;
        this.type = type;
    }

    public final String getDescription() {
        return description;
    }

    public final IType getType() {
        return type;
    }

    public final IType getResponseBodyType() {
        return responseBodyType;
    }

    public final ReturnValue setResponseBodyType(IType responseBodyType) {
        this.responseBodyType = responseBodyType;
        return this;
    }

    /**
     * Add this return value's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public final void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        getType().addImportsTo(imports, includeImplementationImports);
    }
}
