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
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.serializer.CollectionFormat;

public class ProxyParameterMapper implements IMapper<Parameter, ProxyMethodParameter> {
    private static ProxyParameterMapper instance = new ProxyParameterMapper();

    private ProxyParameterMapper() {
    }

    public static ProxyParameterMapper getInstance() {
        return instance;
    }

    @Override
    public ProxyMethodParameter map(Parameter parameter) {
        JavaSettings settings = JavaSettings.getInstance();
        String parameterRequestName = parameter.getLanguage().getDefault().getName();

        RequestParameterLocation parameterRequestLocation = parameter.getProtocol().getHttp().getIn();

        //TODO: HeaderCollectionPrefix
//        String parameterHeaderCollectionPrefix = parameter.Extensions.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

        Schema ParameterJvWireType = parameter.getSchema();
        IType wireType = Mappers.getSchemaMapper().map(ParameterJvWireType);
        if (parameter.isNullable() || !parameter.isRequired()) {
            wireType = wireType.asNullable();
        }
        IType clientType = wireType.getClientType();

        if (wireType instanceof ListType && settings.shouldGenerateXmlSerialization() && parameterRequestLocation == RequestParameterLocation.Body){
            String parameterTypePackage = settings.getPackage(settings.getImplementationSubpackage());
            String parameterTypeName = CodeNamer
                .toPascalCase(ParameterJvWireType.getSerialization().getXml().getName() +
                    "Wrapper");
            wireType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
        } else if (wireType == ArrayType.ByteArray) {
            if (parameterRequestLocation != RequestParameterLocation.Body /*&& parameterRequestLocation != RequestParameterLocation.FormData*/) {
                wireType = ClassType.String;
            }
        } else if (wireType instanceof ListType && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.Body /*&& parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.FormData*/) {
            wireType = ClassType.String;
        }

        String parameterDescription = parameter.getDescription();
        if (parameterDescription == null || parameterDescription.isEmpty()) {
            parameterDescription = String.format("the %s value", clientType);
        }

        boolean parameterSkipUrlEncodingExtension = false;
        if (parameter.getExtensions() != null) {
            parameterSkipUrlEncodingExtension = parameter.getExtensions().isXmsSkipUrlEncoding();
        }

        boolean parameterIsConstant = false;
        String defaultValue = null;
        if (parameter.getSchema() instanceof ConstantSchema){
          parameterIsConstant = true;
          Object objValue = ((ConstantSchema) parameter.getSchema()).getValue().getValue();
          defaultValue = objValue == null ? null : String.valueOf(objValue);
        }

        boolean parameterIsRequired = parameter.isRequired();

        boolean parameterIsServiceClientProperty = parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT;

        String parameterReference = parameter.getLanguage().getJava().getName();
        if (parameter.getImplementation().equals(Parameter.ImplementationLocation.CLIENT)) {
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

        return new ProxyMethodParameter(
                parameterDescription,
                wireType,
                clientType,
                parameter.getLanguage().getJava().getName(),
                parameterRequestLocation,
                parameterRequestName,
                parameterSkipUrlEncodingExtension,
                parameterIsConstant,
                parameterIsRequired,
                parameter.isNullable(),
                parameterIsServiceClientProperty,
                null,
                parameterReference,
                defaultValue,
                collectionFormat);
    }
}
