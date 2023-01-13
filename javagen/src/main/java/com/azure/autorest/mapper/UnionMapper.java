// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.OrSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UnionMapper implements IMapper<OrSchema, IType> {
    private static final UnionMapper INSTANCE = new UnionMapper();
    Map<OrSchema, ClassType> parsed = new ConcurrentHashMap<>();

    protected UnionMapper() {
    }

    public static UnionMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ClassType map(OrSchema compositeType) {
        if (compositeType == null) {
            return null;
        }

        return parsed.computeIfAbsent(compositeType, this::createClassType);
    }

    private ClassType createClassType(OrSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();

        String classPackage;
        String className = compositeType.getLanguage().getJava().getName();
        if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
            classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
        } else {
            classPackage = settings.getPackage(settings.getModelsSubpackage());
        }

        return new ClassType.Builder()
                .packageName(classPackage)
                .name(className)
                .extensions(compositeType.getExtensions())
                .build();
    }
}
