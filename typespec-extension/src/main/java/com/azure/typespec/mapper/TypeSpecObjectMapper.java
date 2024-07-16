// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.typespec.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.mapper.ObjectMapper;

public class TypeSpecObjectMapper extends ObjectMapper {
    private static final TypeSpecObjectMapper INSTANCE = new TypeSpecObjectMapper();

    public static TypeSpecObjectMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isPlainObject(ObjectSchema compositeType) {
        return false;
    }
}
