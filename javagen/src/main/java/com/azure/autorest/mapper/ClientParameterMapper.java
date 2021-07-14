package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.util.CodeNamer;

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
        String name = parameter.getOriginalParameter() != null && parameter.getLanguage().getJava().getName().equals(parameter.getOriginalParameter().getLanguage().getJava().getName())
                ? CodeNamer.toCamelCase(parameter.getOriginalParameter().getSchema().getLanguage().getJava().getName()) + CodeNamer.toPascalCase(parameter.getLanguage().getJava().getName())
                : parameter.getLanguage().getJava().getName();
        name = CodeNamer.getEscapedReservedClientMethodParameterName(name);

        JavaSettings settings = JavaSettings.getInstance();
        ClientMethodParameter.Builder builder = new ClientMethodParameter.Builder()
                .name(name)
                .isRequired(parameter.isRequired())
                .fromClient(parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT);
        if (parameter.getProtocol() != null && parameter.getProtocol().getHttp() != null) {
                builder.location(parameter.getProtocol().getHttp().getIn());
        }

        IType wireType = Mappers.getSchemaMapper().map(parameter.getSchema());
        if (parameter.isNullable() || !parameter.isRequired()) {
            wireType = wireType.asNullable();
        }
        builder.rawType(wireType);
        if (settings.isLowLevelClient() && !(wireType instanceof PrimitiveType)) {
            if (parameter.getProtocol().getHttp().getIn() == RequestParameterLocation.Body) {
                wireType = ClassType.BinaryData;
            } else {
                wireType = ClassType.String;
            }
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

        String description = null;
        // parameter description
        if (parameter.getLanguage() != null) {
            description = parameter.getLanguage().getDefault().getDescription();
        }
        // fallback to parameter schema description
        if (description == null || description.isEmpty()) {
            if (parameter.getSchema() != null && parameter.getSchema().getLanguage() != null) {
                description = parameter.getSchema().getLanguage().getDefault().getDescription();
            }
        }
        // fallback to dummy description
        if (description == null || description.isEmpty()) {
            description = String.format("The %s parameter", name);
        }
        builder.description(description);
        return builder.build();
    }
}
