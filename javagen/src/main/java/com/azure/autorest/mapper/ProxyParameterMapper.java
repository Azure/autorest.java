package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.ConstantSchema;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter.Builder;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.serializer.CollectionFormat;

public class ProxyParameterMapper implements IMapper<Parameter, ProxyMethodParameter> {
    private static ProxyParameterMapper instance = new ProxyParameterMapper();

    protected ProxyParameterMapper() {
    }

    public static ProxyParameterMapper getInstance() {
        return instance;
    }

    @Override
    public ProxyMethodParameter map(Parameter parameter) {
        JavaSettings settings = JavaSettings.getInstance();

        String name = parameter.getLanguage().getJava().getName();

        Builder builder = createProxyMethodParameterBuilder()
                .requestParameterName(parameter.getLanguage().getDefault().getSerializedName())
                .name(name)
                .isRequired(parameter.isRequired())
                .isNullable(parameter.isNullable());

        String headerCollectionPrefix = null;
        if (parameter.getExtensions() != null && parameter.getExtensions().getXmsHeaderCollectionPrefix() != null) {
            headerCollectionPrefix = parameter.getExtensions().getXmsHeaderCollectionPrefix();
        }
        builder.headerCollectionPrefix(headerCollectionPrefix);

        RequestParameterLocation parameterRequestLocation = parameter.getProtocol().getHttp().getIn();
        builder.requestParameterLocation(parameterRequestLocation);

        boolean parameterIsServiceClientProperty = parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT;
        builder.fromClient(parameterIsServiceClientProperty);

        Schema ParameterJvWireType = parameter.getSchema();
        IType wireType = Mappers.getSchemaMapper().map(ParameterJvWireType);
        if (parameter.isNullable() || !parameter.isRequired()) {
            wireType = wireType.asNullable();
        }
        builder.rawType(wireType);

        IType clientType = wireType.getClientType();
        if (settings.isLowLevelClient() && !(clientType instanceof PrimitiveType)) {
            if (parameterRequestLocation == RequestParameterLocation.Body /*&& parameterRequestLocation != RequestParameterLocation.FormData*/) {
                clientType = ClassType.BinaryData;
            } else {
                clientType = ClassType.String;
            }
        }
        builder.clientType(clientType);

        if (wireType instanceof ListType && settings.shouldGenerateXmlSerialization() && parameterRequestLocation == RequestParameterLocation.Body){
            String parameterTypePackage = settings.getPackage(settings.getImplementationSubpackage());
            String parameterTypeName = CodeNamer
                .toPascalCase(ParameterJvWireType.getSerialization().getXml().getName() +
                    "Wrapper");
            wireType = new ClassType.Builder()
                .packageName(parameterTypePackage)
                .name(parameterTypeName)
                .build();
        } else if (wireType == ArrayType.ByteArray) {
            if (parameterRequestLocation != RequestParameterLocation.Body /*&& parameterRequestLocation != RequestParameterLocation.FormData*/) {
                wireType = ClassType.String;
            }
        } else if (wireType instanceof ListType && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.Body /*&& parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.FormData*/) {
            wireType = ClassType.String;
        } else if (settings.isLowLevelClient() && !(wireType instanceof PrimitiveType)) {
            if (parameterRequestLocation == RequestParameterLocation.Body /*&& parameterRequestLocation != RequestParameterLocation.FormData*/) {
                wireType = ClassType.BinaryData;
            } else {
                wireType = ClassType.String;
            }
        }
        builder.wireType(wireType);

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

        if (parameter.getExtensions() != null) {
            builder.alreadyEncoded(parameter.getExtensions().isXmsSkipUrlEncoding());
        }

        if (parameter.getSchema() instanceof ConstantSchema){
            builder.isConstant(true);
            Object objValue = ((ConstantSchema) parameter.getSchema()).getValue().getValue();
            builder.defaultValue(objValue == null ? null : String.valueOf(objValue));
        }

        // parameterReference is what ClientMethod calls the ProxyMethod
        String parameterReference = CodeNamer.getEscapedReservedClientMethodParameterName(name);
        if (Parameter.ImplementationLocation.CLIENT.equals(parameter.getImplementation())) {
            String operationGroupName = parameter.getOperation().getOperationGroup().getLanguage().getJava().getName();
            String caller = (operationGroupName == null || operationGroupName.isEmpty()) ? "this" : "this.client";
            String clientPropertyName = parameter.getLanguage().getJava().getName();
            if (clientPropertyName != null && !clientPropertyName.isEmpty()) {
                clientPropertyName = CodeNamer.toPascalCase(CodeNamer.removeInvalidCharacters(clientPropertyName));
            }
            String prefix = "get";
            if (clientType == PrimitiveType.Boolean || clientType == ClassType.Boolean) {
                prefix = "is";
                if (CodeNamer.toCamelCase(parameterReference).startsWith(prefix)) {
                    prefix = "";
                    clientPropertyName = CodeNamer.toCamelCase(clientPropertyName);
                }
            }
            parameterReference = String.format("%s.%s%s()", caller, prefix, clientPropertyName);
        }
        builder.parameterReference(parameterReference);

        CollectionFormat collectionFormat = null;
        if (parameter.getProtocol().getHttp().getStyle() != null) {
            switch (parameter.getProtocol().getHttp().getStyle()) {
                case SIMPLE:
                    collectionFormat = CollectionFormat.CSV;
                    break;
                case SPACE_DELIMITED:
                    collectionFormat = CollectionFormat.SSV;
                    break;
                case PIPE_DELIMITED:
                    collectionFormat = CollectionFormat.PIPES;
                    break;
                case TAB_DELIMITED:
                    collectionFormat = CollectionFormat.TSV;
                    break;
                default:
                    collectionFormat = CollectionFormat.CSV;
            }
        }
        if (collectionFormat == null && clientType instanceof ListType
                && ClassType.String == wireType) {
            collectionFormat = CollectionFormat.CSV;
        }
        builder.collectionFormat(collectionFormat);

        return builder.build();
    }

    protected ProxyMethodParameter.Builder createProxyMethodParameterBuilder() {
        return new ProxyMethodParameter.Builder();
    }
}
