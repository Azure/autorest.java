/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.fluent.model.FluentType;
import com.azure.autorest.mapper.ModelMapper;
import com.azure.autorest.model.clientmodel.ClassType;

public class FluentModelMapper extends ModelMapper {

    private static final FluentModelMapper INSTANCE = new FluentModelMapper();

    public static FluentModelMapper getInstance() {
        return INSTANCE;
    }

    @Override
    protected boolean isPredefinedModel(ClassType modelType) {
        return !FluentType.nonResourceType(modelType) || !FluentType.nonManagementError(modelType);
    }
}
