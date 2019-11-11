package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.ParameterLocation;
import com.azure.autorest.extension.base.model.codemodel.Schema;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ArrayType;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.PrimitiveType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.model.clientmodel.RequestParameterLocation;
import com.azure.autorest.util.CodeNamer;

public class ProxyParameterMapper implements IMapper<Parameter, ProxyMethodParameter> {
    private ProxyParameterMapper() {
    }

    private static ProxyParameterMapper instance = new ProxyParameterMapper();

    public static ProxyParameterMapper getInstance() {
        return instance;
    }

    @Override
    public ProxyMethodParameter map(Parameter parameter) {
        JavaSettings settings = JavaSettings.getInstance();
        String parameterRequestName = parameter.getLanguage().getDefault().getName();

        ParameterLocation parameterRequestLocation = parameter.getProtocol().getHttp().getIn();

        //TODO: HeaderCollectionPrefix
//        String parameterHeaderCollectionPrefix = parameter.Extensions.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

        Schema ParameterJvWireType = parameter.getSchema();
        IType wireType = Mappers.getSchemaMapper().map(ParameterJvWireType);
        if (parameter.isNullable())
        {
            wireType = wireType.asNullable();
        }
        IType clientType = wireType.getClientType();

        //TODO: XML
        /*if (wireType instanceof ListType && settings.shouldGenerateXmlSerialization() && parameterRequestLocation == ParameterLocation.BODY){
            String parameterTypePackage = settings.getPackage(settings.getImplementationSubpackage());
            String parameterTypeName = ParameterJvWireType.XmlName.ToPascalCase() + "Wrapper";
            wireType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
        } else */if (wireType == ArrayType.ByteArray) {
            if (parameterRequestLocation != ParameterLocation.BODY && parameterRequestLocation != ParameterLocation.FORM_DATA) {
                wireType = ClassType.String;
            }
        }
        else if (wireType instanceof ListType && parameter.getProtocol().getHttp().getIn() != ParameterLocation.BODY && parameter.getProtocol().getHttp().getIn() != ParameterLocation.FORM_DATA)
        {
            wireType = ClassType.String;
        }

        boolean parameterIsNullable = parameter.isNullable();
        if (parameterIsNullable)
        {
            clientType = clientType.asNullable();
        }

        String parameterDescription = parameter.getDescription();
        if (parameterDescription == null || parameterDescription.isEmpty())
        {
            parameterDescription = String.format("the %s value", clientType);
        }

        String parameterVariableName = parameter.ClientProperty?.Name?.ToString();
        if (parameterVariableName != null && !parameterVariableName.isEmpty())
        {
            parameterVariableName = CodeNamer.toCamelCase(CodeNamer.removeInvalidCharacters(parameterVariableName));
        }
        if (parameterVariableName == null)
        {
            if (!parameter.getImplementation().equals(Parameter.ImplementationLocation.CLIENT))
            {
                parameterVariableName = parameter.getLanguage().getJava().getName();
            }
            else
            {
                String caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                String clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                if (!string.IsNullOrEmpty(clientPropertyName))
                {
                    CodeNamer codeNamer = CodeNamer.Instance;
                    clientPropertyName = codeNamer.PascalCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                }
                String prefix = "get";
                if (clientType == PrimitiveType.Boolean || clientType == ClassType.Boolean) {
                    prefix = "is";
                    if (parameterVariableName.ToCamelCase().StartsWith(prefix)) {
                        prefix = "";
                        clientPropertyName = clientPropertyName.ToCamelCase();
                    }
                }
                parameterVariableName = String.format("%s.%s%s()", caller, prefix, clientPropertyName);
            }
        }

        boolean parameterSkipUrlEncodingExtension = false; // TODO: SkipUrlEncoding parameter.Extensions?.Get<bool>(SwaggerExtensions.SkipUrlEncodingExtension) == true;

        boolean parameterIsConstant = false; //parameter.IsConstant;

        boolean parameterIsRequired = parameter.isRequired();

        boolean parameterIsServiceClientProperty = parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT;

        String parameterReference;
        if (!parameterIsServiceClientProperty)
        {
            parameterReference = parameter.getLanguage().getJava().getName();
        }
        else
        {
            String caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
            String clientPropertyName = parameter.ClientProperty?.Name?.ToString();
            if (!string.IsNullOrEmpty(clientPropertyName))
            {
                CodeNamer codeNamer = CodeNamer.Instance;
                clientPropertyName = codeNamer.PascalCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
            }
            String prefix = "get";
            if (clientType == PrimitiveType.Boolean || clientType == ClassType.Boolean) {
                prefix = "is";
                if (parameterVariableName.ToCamelCase().StartsWith(prefix)) {
                    prefix = "";
                    clientPropertyName = clientPropertyName.ToCamelCase();
                }
            }
            parameterReference = $"{caller}.{prefix}{clientPropertyName}()";
        }

        return new ProxyMethodParameter(
                parameterDescription,
                wireType,
                clientType,
                parameterVariableName,
                parameterRequestLocation,
                parameterRequestName, parameterSkipUrlEncodingExtension,
                parameterIsConstant,
                parameterIsRequired,
                parameter.isNullable(),
                parameterIsServiceClientProperty,
                null,
                parameterReference,
                parameter.DefaultValue,
                parameter.CollectionFormat);
    }
}
