/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.BinarySchema;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.IType;

public class BinaryMapper implements IMapper<BinarySchema, IType> {

    private static final BinaryMapper INSTANCE = new BinaryMapper();

    private BinaryMapper() {
        // private constructor
    }

    public static BinaryMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(BinarySchema binarySchema) {
        return ArrayType.ByteArray;
    }
}
