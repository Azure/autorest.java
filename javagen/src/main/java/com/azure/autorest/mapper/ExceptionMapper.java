package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ObjectSchema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClientException;
import java.util.HashMap;
import java.util.Map;

public class ExceptionMapper implements IMapper<ObjectSchema, ClientException> {
    private static ExceptionMapper instance = new ExceptionMapper();
    Map<ObjectSchema, ClientException> parsed = new HashMap<>();

    protected ExceptionMapper() {
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

        ClientException exception = buildException(compositeType, settings);
        parsed.put(compositeType, exception);
        return exception;
    }

    protected ClientException buildException(ObjectSchema compositeType, JavaSettings settings) {
        String errorName = compositeType.getLanguage().getJava().getName();
        String methodOperationExceptionTypeName = errorName + "Exception";

        if (compositeType.getExtensions() != null && compositeType.getExtensions().getXmsClientName() != null) {
            methodOperationExceptionTypeName = compositeType.getExtensions().getXmsClientName();
        }

        boolean isCustomType = settings.isCustomType(methodOperationExceptionTypeName);
        String exceptionSubPackage = isCustomType
                ? settings.getCustomTypesSubpackage()
                : settings.getModelsSubpackage();
        String packageName = settings.getPackage(exceptionSubPackage);

        return createClientExceptionBuilder()
                .packageName(packageName)
                .name(methodOperationExceptionTypeName)
                .errorName(errorName)
                .build();
    }

    protected ClientException.Builder createClientExceptionBuilder() {
        return new ClientException.Builder();
    }
}
