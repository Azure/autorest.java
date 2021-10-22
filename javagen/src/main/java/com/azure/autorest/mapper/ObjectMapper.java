// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper implements IMapper<ObjectSchema, IType> {
    private static final ObjectMapper INSTANCE = new ObjectMapper();
    Map<ObjectSchema, ClassType> parsed = new ConcurrentHashMap<>();

    protected ObjectMapper() {
    }

    public static ObjectMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ClassType map(ObjectSchema compositeType) {
        if (compositeType == null) {
            return null;
        }

        return parsed.computeIfAbsent(compositeType, this::createClassType);
    }

    private ClassType createClassType(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();
        if (settings.isFluent()) {
            return mapPredefinedModel(compositeType);
        }

        if (isPlainObject(compositeType)) {
            return ClassType.Object;
        }

        String classPackage;
        String className = compositeType.getLanguage().getJava().getName();
        if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
            classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
        } else if (settings.isFluent() && isInnerModel(compositeType)) {
            className += "Inner";
            classPackage = settings.getPackage(settings.getFluentModelsSubpackage());
        } else if (settings.isFluent() && compositeType.isFlattenedSchema()) {
            // put class of flattened type to implementation package
            classPackage = settings.getPackage(settings.getFluentModelsSubpackage());
        } else {
            classPackage = settings.getPackage(settings.getModelsSubpackage());
        }

        return new ClassType.Builder()
            .packageName(classPackage)
            .name(className)
            .extensions(compositeType.getExtensions())
            .build();
    }

    /**
     * Check that the type can be regarded as a plain java.lang.Object.
     *
     * @param compositeType The type to check.
     */
    public static boolean isPlainObject(ObjectSchema compositeType) {
        return compositeType.getProperties().isEmpty() && compositeType.getDiscriminator() == null
            && compositeType.getParents() == null && compositeType.getChildren() == null
            && (compositeType.getExtensions() == null || compositeType.getExtensions().getXmsEnum() == null);
    }

    /**
     * Extension for Fluent predefined type.
     *
     * @param compositeType object type
     * @return The predefined type.
     */
    protected ClassType mapPredefinedModel(ObjectSchema compositeType) {
        return null;
    }

    /**
     * Extension for Fluent inner model.
     *
     * @param compositeType object type
     * @return whether the type should be treated as inner model
     */
    protected boolean isInnerModel(ObjectSchema compositeType) {
        return false;
    }
}
