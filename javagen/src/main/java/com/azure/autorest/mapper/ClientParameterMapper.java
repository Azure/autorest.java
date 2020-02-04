package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;

import java.util.ArrayList;
import java.util.Arrays;

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
        ClientMethodParameter.Builder builder = new ClientMethodParameter.Builder()
                .name(parameter.getLanguage().getJava().getName())
                .isRequired(parameter.isRequired())
                .fromClient(parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT);

        IType wireType = Mappers.getSchemaMapper().map(parameter.getSchema());
        if (parameter.isNullable() || !parameter.isRequired()) {
            wireType = wireType.asNullable();
        }
        builder.wireType(wireType);

        builder.annotations(settings.shouldNonNullAnnotations() && parameter.isRequired() ?
                Arrays.asList(ClassType.NonNull) : new ArrayList<>());

        boolean isConstant = false;
        String defaultValue = null;
        if (parameter.getSchema() instanceof ConstantSchema) {
            isConstant = true;
            Object objValue = ((ConstantSchema) parameter.getSchema()).getValue().getValue();
            defaultValue = objValue == null ? null : String.valueOf(objValue);
        }
        builder.isConstant(isConstant).defaultValue(defaultValue);

        if (parameter.getSchema() != null && parameter.getSchema().getLanguage() != null) {
            builder.description(parameter.getSchema().getLanguage().getDefault().getDescription());
        }
        return builder.build();
    }
}
