// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;


import java.util.Objects;

/**
 * a schema that represents a key-value collection
 * 
 */
public class DictionarySchema extends ComplexSchema {

    /**
     * 
     * (Required)
     * 
     */
    private Schema elementType;

    private Boolean nullableItems;

    /**
     * 
     * (Required)
     * 
     */
    public Schema getElementType() {
        return elementType;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setElementType(Schema elementType) {
        this.elementType = elementType;
    }

    public Boolean getNullableItems() {
        return nullableItems;
    }

    public void setNullableItems(Boolean nullableItems) {
        this.nullableItems = nullableItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DictionarySchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("elementType");
        sb.append('=');
        sb.append(((this.elementType == null)?"<null>":this.elementType));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DictionarySchema that = (DictionarySchema) o;
        return Objects.equals(elementType, that.elementType) && Objects.equals(nullableItems, that.nullableItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elementType, nullableItems);
    }
}
