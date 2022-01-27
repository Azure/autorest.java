// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

/**
 * a NOT relationship between schemas
 * 
 */
public class NotSchema extends Schema {

    /**
     * the schema that this may not be.
     * (Required)
     * 
     */
    private Schema not;

    public Schema getNot() {
        return not;
    }

    public void setNot(Schema not) {
        this.not = not;
    }
}
