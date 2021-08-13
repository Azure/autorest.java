// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapper implements IMapper<ObjectSchema, IType> {
    private static ObjectMapper instance = new ObjectMapper();
    Map<ObjectSchema, ClassType> parsed = new HashMap<>();

    protected ObjectMapper() {
    }

    public static ObjectMapper getInstance() {
        return instance;
    }

    @Override
    public ClassType map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();

        if (compositeType == null) {
            return null;
        }
        if (parsed.containsKey(compositeType)) {
            return parsed.get(compositeType);
        }

        ClassType result = null;
        if (settings.isFluent()) {
            result = mapPredefinedModel(compositeType);
        }
        if (result == null) {
            if (isPlainObject(compositeType)) {
                result = ClassType.Object;
            } else {
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
                result = new ClassType.Builder()
                        .packageName(classPackage)
                        .name(className)
                        .extensions(compositeType.getExtensions())
                        .build();
            }
        }

        parsed.put(compositeType, result);
        return result;
    }

    /**
     * Check that the type can be regarded as a plain java.lang.Object.
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
