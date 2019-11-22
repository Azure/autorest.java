package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientParameterMapper implements IMapper<Parameter, ClientMethodParameter> {
    private static ClientParameterMapper instance = new ClientParameterMapper();

    private ClientParameterMapper() {
    }

    public static ClientParameterMapper getInstance() {
        return instance;
    }

    @Override
    public ClientMethodParameter map(Parameter parameter) {
        JavaSettings settings = JavaSettings.getInstance();

        IType wireType = Mappers.getSchemaMapper().map(parameter.getSchema());
        if (parameter.isNullable()) {
            wireType = wireType.asNullable();
        }

        List<ClassType> parameterAnnotations = settings.shouldNonNullAnnotations() && parameter.isRequired() ?
                Arrays.asList(ClassType.NonNull) : new ArrayList<>();

        return new ClientMethodParameter(
                parameter.getDescription(),
                false,
                wireType,
                parameter.getLanguage().getJava().getName(),
                parameter.isRequired(),
                parameter.getSchema() instanceof ConstantSchema,
                parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT,
                null,
                parameterAnnotations);
    }
}
