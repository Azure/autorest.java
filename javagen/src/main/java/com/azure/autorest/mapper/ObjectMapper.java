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

    private ObjectMapper() {
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
        if (settings.isAzureOrFluent()) {
            // TODO: Not that simple
            if (compositeType.getLanguage().getJava().getName().equals(ClassType.Resource.getName())) {
                result = ClassType.Resource;
            } else if (compositeType.getLanguage().getJava().getName().equals(ClassType.ProxyResource.getName())) {
                result = ClassType.ProxyResource;
            } else if (compositeType.getLanguage().getJava().getName().equals(ClassType.SubResource.getName())) {
                result = ClassType.SubResource;
            }
        }

        if (result == null) {
            if (isPlainObject(compositeType)) {
                result = ClassType.Object;
            } else {
                final boolean isInnerModel = isInnerModel(compositeType);
                String classPackage;
                String className = compositeType.getLanguage().getJava().getName();
                if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
                    classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
                } else if (!settings.isFluent()) {
                    classPackage = settings.getPackage(settings.getModelsSubpackage());
                } else if (isInnerModel) {
                    className += "Inner";
                    classPackage = settings.getPackage(settings.getImplementationSubpackage());
                }
                else {
                    classPackage = settings.getPackage();
                }
                result = new ClassType(classPackage, className, null, compositeType.getExtensions(), isInnerModel/*compositeType.IsInnerModel*/);
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

    public static boolean nonResourceType(ObjectSchema compositeType) {
        // TODO no exact way to know this
        return !(ClassType.Resource.getName().equals(compositeType.getLanguage().getJava().getName())
                || ClassType.ProxyResource.getName().equals(compositeType.getLanguage().getJava().getName())
                || ClassType.SubResource.getName().equals(compositeType.getLanguage().getJava().getName()));
    }

    public static boolean nonResourceType(ClassType modelType) {
        // TODO no exact way to know this
        return !(ClassType.Resource.equals(modelType)
                || ClassType.ProxyResource.equals(modelType)
                || ClassType.SubResource.equals(modelType));
    }

    protected boolean isInnerModel(ObjectSchema compositeType) {
        return false;
    }
}
