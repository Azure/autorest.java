// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.mapper;

import com.azure.autorest.extension.base.model.codemodel.AnySchema;
import com.azure.autorest.extension.base.model.codemodel.ArraySchema;
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

public class CustomProxyParameterMapper implements IMapper<Parameter, ProxyMethodParameter> {

    private static final CustomProxyParameterMapper INSTANCE = new CustomProxyParameterMapper();

    private CustomProxyParameterMapper() {
    }

    public static CustomProxyParameterMapper getInstance() {
        return INSTANCE;
    }


    @Override
    public ProxyMethodParameter map(Parameter parameter) {
        JavaSettings settings = JavaSettings.getInstance();

        ProxyMethodParameter.Builder builder = new ProxyMethodParameter.Builder()
                .requestParameterName(parameter.getLanguage().getDefault().getSerializedName())
                .name(parameter.getLanguage().getJava().getName())
                .isRequired(parameter.isRequired())
                .isNullable(parameter.isNullable());

        String headerCollectionPrefix = null;
        if (parameter.getExtensions() != null && parameter.getExtensions().getXmsHeaderCollectionPrefix() != null) {
            headerCollectionPrefix = parameter.getExtensions().getXmsHeaderCollectionPrefix();
        }
        builder.headerCollectionPrefix(headerCollectionPrefix);


        Schema parameterJvWireType = parameter.getSchema();

        IType wireType = Mappers.getSchemaMapper().map(parameterJvWireType);

        if (parameterJvWireType instanceof ArraySchema) {
            ArraySchema arraySchema = (ArraySchema) parameterJvWireType;
            if (arraySchema.getElementType() instanceof AnySchema) {
                wireType = ClassType.JsonPatchDocument;
            }
        }

        if (parameter.isNullable() || !parameter.isRequired()) {
            wireType = wireType.asNullable();
        }
        IType clientType = wireType.getClientType();
        builder.clientType(clientType);

        RequestParameterLocation parameterRequestLocation = parameter.getProtocol().getHttp().getIn();
        builder.requestParameterLocation(parameterRequestLocation);

        boolean parameterIsServiceClientProperty = parameter.getImplementation() == Parameter.ImplementationLocation.CLIENT;
        builder.fromClient(parameterIsServiceClientProperty);

        if (wireType instanceof ListType && settings.shouldGenerateXmlSerialization() && parameterRequestLocation == RequestParameterLocation.BODY){
            String parameterTypePackage = settings.getPackage(settings.getImplementationSubpackage());
            String parameterTypeName = CodeNamer
                    .toPascalCase(parameterJvWireType.getSerialization().getXml().getName() +
                            "Wrapper");
            wireType = new ClassType.Builder()
                    .packageName(parameterTypePackage)
                    .name(parameterTypeName)
                    .build();
        } else if (wireType == ArrayType.ByteArray) {
            if (parameterRequestLocation != RequestParameterLocation.BODY /*&& parameterRequestLocation != RequestParameterLocation.FormData*/) {
                wireType = ClassType.String;
            }
        } else if (wireType instanceof ListType && parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.BODY /*&& parameter.getProtocol().getHttp().getIn() != RequestParameterLocation.FormData*/) {
            if (parameter.getProtocol().getHttp().getExplode()) {
                wireType = new ListType(ClassType.String);
            } else {
                wireType = ClassType.String;
            }
        } else if (settings.isLowLevelClient() && !(wireType instanceof PrimitiveType)) {
            wireType = ClassType.String;
        }
        if (parameter.getProtocol().getHttp().getExplode()) {
            builder.alreadyEncoded(true);
        }
        builder.wireType(wireType);

        String parameterDescription = parameter.getDescription();
        if (parameterDescription == null || parameterDescription.isEmpty()) {
            parameterDescription = String.format("the %s value", clientType);
        }
        builder.description(parameterDescription);

        if (parameter.getExtensions() != null) {
            builder.alreadyEncoded(parameter.getExtensions().isXmsSkipUrlEncoding());
        }

        if (parameter.getSchema() instanceof ConstantSchema){
            builder.isConstant(true);
            Object objValue = ((ConstantSchema) parameter.getSchema()).getValue().getValue();
            builder.defaultValue(objValue == null ? null : String.valueOf(objValue));
        }

        String parameterReference = parameter.getLanguage().getJava().getName();
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
        builder.explode(parameter.getProtocol().getHttp().getExplode());

        return builder.build();
    }
}
