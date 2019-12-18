package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapper implements IMapper<ObjectSchema, IType> {
    private static ObjectMapper instance = new ObjectMapper();
    Map<ObjectSchema, IType> parsed = new HashMap<>();

    private ObjectMapper() {
    }

    public static ObjectMapper getInstance() {
        return instance;
    }

    @Override
    public IType map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();

        if (compositeType == null) {
            return null;
        }
        if (parsed.containsKey(compositeType)) {
            return parsed.get(compositeType);
        }
        IType result = null;
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
            if (ModelMapper.isPlainObject(compositeType)) {
                result = ClassType.Object;
            } else {
                String classPackage;
                if (settings.isCustomType(compositeType.getLanguage().getJava().getName())) {
                    classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
                } else if (!settings.isFluent()) {
                    classPackage = settings.getPackage(settings.getModelsSubpackage());
                }
//                else if (compositeType.IsInnerModel)
//                {
//                    classPackage = settings.getPackage(settings.getImplementationSubpackage());
//                }
                else {
                    classPackage = settings.getPackage();
                }
                result = new ClassType(classPackage, compositeType.getLanguage().getJava().getName(), null, compositeType.getExtensions(), false/*compositeType.IsInnerModel*/);
            }
        }

        parsed.put(compositeType, result);
        return result;
    }
}
