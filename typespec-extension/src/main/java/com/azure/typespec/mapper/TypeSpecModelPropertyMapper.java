// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ModelPropertyMapper;

public class TypeSpecModelPropertyMapper extends ModelPropertyMapper {
    private static final TypeSpecModelPropertyMapper INSTANCE = new TypeSpecModelPropertyMapper();

    public static TypeSpecModelPropertyMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isPlainObject(ObjectSchema compositeType) {
        return false;
    }
}
