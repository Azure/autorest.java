// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;



/**
 * a schema that represents a constant value
 * 
 */
public class ConstantSchema extends Schema {

    /**
     * 
     * (Required)
     * 
     */
    private Schema valueType;
    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    private ConstantValue value;

    /**
     * 
     * (Required)
     * 
     */
    public Schema getValueType() {
        return valueType;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setValueType(Schema valueType) {
        this.valueType = valueType;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    public ConstantValue getValue() {
        return value;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    public void setValue(ConstantValue value) {
        this.value = value;
    }
}
