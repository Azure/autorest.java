package com.azure.autorest.android.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.serializer.CollectionFormat;

import java.util.Set;

public class AndroidProxyMethodParameter extends ProxyMethodParameter {

    /**
     * Create a new RestAPIParameter based on the provided properties.
     * @param description The description of this parameter.
     * @param wireType The type of this parameter.
     * @param clientType The type of this parameter users interact with.
     * @param name The name of this parameter when it is used as a variable.
     * @param requestParameterLocation The location within the REST API method's HttpRequest where this parameter will be added.
     * @param requestParameterName The name of the HttpRequest's parameter to substitute with this parameter's value.
     * @param alreadyEncoded Whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.
     * @param isConstant Whether or not this parameter is a constant value.
     * @param isRequired Whether or not this parameter is required.
     * @param isNullable Whether or not this parameter is nullable.
     * @param fromClient Whether or not this parameter's value comes from a ServiceClientProperty.
     * @param headerCollectionPrefix The x-ms-header-collection-prefix extension value.
     * @param parameterReference The reference to this parameter from a caller.
     * @param defaultValue The default value of the parameter.
     * @param collectionFormat The collection format if the parameter is a list type.
     */
    protected AndroidProxyMethodParameter(String description,
                                          IType wireType,
                                          IType clientType,
                                          String name,
                                          RequestParameterLocation requestParameterLocation,
                                          String requestParameterName,
                                          boolean alreadyEncoded,
                                          boolean isConstant,
                                          boolean isRequired,
                                          boolean isNullable,
                                          boolean fromClient,
                                          String headerCollectionPrefix,
                                          String parameterReference,
                                          String defaultValue,
                                          CollectionFormat collectionFormat) {
        super(description,
                wireType,
                clientType,
                name,
                requestParameterLocation,
                requestParameterName,
                alreadyEncoded,
                isConstant,
                isRequired,
                isNullable,
                fromClient,
                headerCollectionPrefix,
                parameterReference,
                defaultValue,
                collectionFormat);
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (getRequestParameterLocation() != RequestParameterLocation.None) {
            imports.add(String.format("com.azure.android.core.rest.annotation.%1$sParam", CodeNamer.toPascalCase(getRequestParameterLocation().toString())));
        }
        if (getRequestParameterLocation() != RequestParameterLocation.Body) {
            if (getClientType() == com.azure.autorest.model.clientmodel.ArrayType.ByteArray) {
                imports.add("com.azure.android.core.util.Base64Util");
            }
        }

        getWireType().addImportsTo(imports, includeImplementationImports);
    }

    public static class Builder extends ProxyMethodParameter.Builder{
        @Override
        public ProxyMethodParameter build() {
            return new AndroidProxyMethodParameter(description,
                    wireType,
                    clientType,
                    name,
                    requestParameterLocation,
                    requestParameterName,
                    alreadyEncoded,
                    isConstant,
                    isRequired,
                    isNullable,
                    fromClient,
                    headerCollectionPrefix,
                    parameterReference,
                    defaultValue,
                    collectionFormat);
        }
    }
}
