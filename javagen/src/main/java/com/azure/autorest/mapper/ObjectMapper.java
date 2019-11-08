package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.codemodel.ObjectSchema;

import java.util.HashMap;
import java.util.Map;

public class ObjectMapper implements IMapper<ObjectSchema, IType> {
    private ObjectMapper() {
    }

    private static ObjectMapper instance = new ObjectMapper();

    public static ObjectMapper getInstance() {
        return instance;
    }

    Map<ObjectSchema, IType> parsed = new HashMap<>();

    @Override
    public IType map(ObjectSchema compositeType) {
        JavaSettings settings = JavaSettings.getInstance();

        if (compositeType == null)
        {
            return null;
        }
        if (parsed.containsKey(compositeType))
        {
            return parsed.get(compositeType);
        }
        IType result = null;
        if (settings.isAzureOrFluent())
        {
            // TODO: Not that simple
            if (compositeType.getLanguage().getDefault().getName().equals(ClassType.Resource.getName()))
            {
                result = ClassType.Resource;
            }
            else if (compositeType.getLanguage().getDefault().getName().equals(ClassType.ProxyResource.getName()))
            {
                result = ClassType.ProxyResource;
            }
            else if (compositeType.getLanguage().getDefault().getName().equals(ClassType.SubResource.getName()))
            {
                result = ClassType.SubResource;
            }
        }

        if (result == null)
        {
            String classPackage;
            if (settings.IsCustomType(compositeType.getLanguage().getDefault().getName()))
            {
                classPackage = settings.getPackage(settings.getCustomTypesSubpackage());
            }
            else if (!settings.isFluent())
            {
                classPackage = settings.getPackage(settings.getModelsSubpackage());
            }
//            else if (compositeType.IsInnerModel)
//            {
//                classPackage = settings.getPackage(settings.getImplementationSubpackage());
//            }
            else
            {
                classPackage = settings.getPackage();
            }
            result = new ClassType(classPackage, compositeType.getLanguage().getDefault().getName(), null, compositeType.getExtensions(), false/*compositeType.IsInnerModel*/);
        }

        parsed.put(compositeType, result);
        return result;
    }
}
