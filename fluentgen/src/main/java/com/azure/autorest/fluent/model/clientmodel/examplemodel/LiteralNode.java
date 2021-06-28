/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.IType;

public class LiteralNode extends ExampleNode {

    private String literalsValue;

    public LiteralNode(IType clientType, Object objectValue) {
        super(clientType, objectValue);
    }

    public String getLiteralsValue() {
        return literalsValue;
    }

    public LiteralNode setLiteralsValue(String literalsValue) {
        this.literalsValue = literalsValue;
        return this;
    }
}
