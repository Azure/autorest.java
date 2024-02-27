// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

/**
 * Represents an ODataQuery value.
 */
public class ODataQuerySchema extends Schema {

    @Override
    public String toString() {
        return ODataQuerySchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[]";
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        return other instanceof ODataQuerySchema;
    }
}
