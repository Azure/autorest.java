package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper implements IMapper<ObjectSchema, ClientException> {
    private static ExceptionMapper instance = new ExceptionMapper();
    Map<ObjectSchema, ClientException> parsed = new HashMap<>();

    private ExceptionMapper() {
    }

    public static ExceptionMapper getInstance() {
        return instance;
    }

    @Override
    public ClientException map(ObjectSchema compositeType) {
        if (compositeType == null) {
            return null;
        }

        JavaSettings settings = JavaSettings.getInstance();

        if (parsed.containsKey(compositeType)) {
            return parsed.get(compositeType);
        }

        String errorName = compositeType.getLanguage().getJava().getName();
        String methodOperationExceptionTypeName = errorName + "Exception";

        if (compositeType.getExtensions() != null && compositeType.getExtensions().getXmsClientName() != null) {
            methodOperationExceptionTypeName = compositeType.getExtensions().getXmsClientName();
        }

        if (!settings.isFluent()) {
            String exceptionSubPackage;
            boolean isCustomType = settings.isCustomType(methodOperationExceptionTypeName);
            if (isCustomType) {
                exceptionSubPackage = settings.getCustomTypesSubpackage();
            } else if (settings.isFluent()) {
                exceptionSubPackage = "";
            } else {
                exceptionSubPackage = settings.getModelsSubpackage();
            }
            String packageName = settings.getPackage(exceptionSubPackage);

            ClientException exception = new ClientException(packageName, methodOperationExceptionTypeName, errorName, ClassType.HttpResponseException);
            parsed.put(compositeType, exception);
            return exception;
        }

        return null;
    }
}
