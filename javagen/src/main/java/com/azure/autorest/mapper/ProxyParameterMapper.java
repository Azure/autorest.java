package com.azure.autorest.mapper;

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
        if (parameter.isNullable()) {
            wireType = wireType.asNullable();
        }
        IType clientType = wireType.getClientType();

        //TODO: XML
        /*if (wireType instanceof ListType && settings.shouldGenerateXmlSerialization() && parameterRequestLocation == ParameterLocation.BODY){
            String parameterTypePackage = settings.getPackage(settings.getImplementationSubpackage());
            String parameterTypeName = ParameterJvWireType.XmlName.ToPascalCase() + "Wrapper";
            wireType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
        } else */
        if (wireType == ArrayType.ByteArray) {
            if (parameterRequestLocation != RequestParameterLocation.Body && parameterRequestLocation != RequestParameterLocation.FormData) {
                wireType = ClassType.String;
            }
        } else if (wireType instanceof ListType && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.Body && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.FormData) {
            wireType = ClassType.String;
        }

        boolean parameterIsNullable = parameter.isNullable();
        if (parameterIsNullable) {
            clientType = clientType.asNullable();
        }

        String parameterDescription = parameter.getDescription();
        if (parameterDescription == null || parameterDescription.isEmpty()) {
            parameterDescription = String.format("the %s value", clientType);
        }

        boolean parameterSkipUrlEncodingExtension = false; // TODO: SkipUrlEncoding parameter.Extensions?.Get<bool>(SwaggerExtensions.SkipUrlEncodingExtension) == true;

        boolean parameterIsConstant = false; //parameter.IsConstant;

        boolean parameterIsRequired = parameter.isRequired();

        boolean parameterIsServiceClientProperty = parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT;

        String parameterReference = parameter.getLanguage().getJava().getName();
        if (parameter.getImplementation().equals(Parameter.ImplementationLocation.CLIENT)) {
            String caller = (parameter.getOperation() != null && parameter.getOperation().getOperationGroup() == null) ? "this" : "this.client";
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
                null,
                null);// TODO: CollectionFormat parameter.CollectionFormat);
    }
}
