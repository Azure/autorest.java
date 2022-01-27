// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;



/**
 * a schema that represents a UnixTime value
 * 
 */
public class UnixTimeSchema extends PrimitiveSchema {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(UnixTimeSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UnixTimeSchema) == false) {
            return false;
        }
        UnixTimeSchema rhs = ((UnixTimeSchema) other);
        return true;
    }

}
