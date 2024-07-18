// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.fluent.mapper.FluentModelMapper;

public class TypeSpecFluentModelMapper extends FluentModelMapper {
    private static final TypeSpecFluentModelMapper INSTANCE = new TypeSpecFluentModelMapper();

    public static TypeSpecFluentModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isPlainObject(ObjectSchema compositeType) {
        return false;
    }
}
