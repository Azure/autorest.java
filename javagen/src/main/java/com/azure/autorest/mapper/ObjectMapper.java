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
            result = mapImplementedModel(compositeType);
        }
        if (result == null) {
            if (isPlainObject(compositeType)) {
                result = ClassType.Object;
            } else {
                String classPackage;
                String className = compositeType.getLanguage().getJava().getName();
                if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
                    classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
                } else if (!settings.isFluent()) {
                    classPackage = settings.getPackage(settings.getModelsSubpackage());
                } else if (isInnerModel(compositeType)) {
                    className += "Inner";
                    classPackage = settings.getPackage(settings.getImplementationSubpackage());
                } else {
                    classPackage = settings.getPackage();
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
     * Extension for Fluent implemented type.
     *
     * @param compositeType object type
     * @return Whether the type is already implemented.
     */
    protected boolean isImplementedModel(ClassType compositeType) {
        return false;
    }

    /**
     * Extension for Fluent implemented type.
     *
     * @param compositeType object type
     * @return The implemented type.
     */
    protected ClassType mapImplementedModel(ObjectSchema compositeType) {
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
