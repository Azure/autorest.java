// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ModelMapper;

public class TypeSpecModelMapper extends ModelMapper {
    private static final TypeSpecModelMapper INSTANCE = new TypeSpecModelMapper();
    public static TypeSpecModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isPlainObject(ObjectSchema compositeType) {
        return false;
    }
}
