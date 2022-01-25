// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

package com.azure.autorest.model.clientmodel;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.serializer.CollectionFormat;

import java.util.Set;

/**
 * A parameter for a ProxyMethod.
 */
public class ProxyMethodParameter {
    /**
     * Get the description of this parameter.
     */
    private String description;
    /**
     * Get the raw type of this parameter. Result of SchemaMapper.
     */
    private IType rawType;
    /**
     * Get the type of this parameter.
     */
    private IType wireType;
    /**
     * Get the type of this parameter.
     */
    private IType clientType;
    /**
     * Get the name of this parameter when it is used as a variable.
     */
    private String name;
    /**
     * Get the location within the REST API method's URL where this parameter will be added.
     */
    private RequestParameterLocation requestParameterLocation = RequestParameterLocation.values()[0];
    /**
     * Get the name of this parameter when it is serialized.
     */
    private String requestParameterName;
    /**
     * Whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.
     */
    private boolean alreadyEncoded;
    /**
     * Get whether or not this parameter is a constant value.
     */
    private boolean isConstant;
    /**
     * Get whether or not this parameter is required.
     */
    private boolean isRequired;
    /**
     * Whether or not this parameter is nullable.
     */
    private boolean isNullable;
    /**
     * Whether or not this parameter's value comes from a ServiceClientProperty.
     */
    private boolean fromClient;
    /**
     * The x-ms-header-collection-prefix extension value.
     */
    private String headerCollectionPrefix;
    /**
     * The reference to this parameter from a caller.
     */
    private String parameterReference;
    /**
     * Get the description of this parameter.
     */
    private String defaultValue;
    /**
     * The collection format if the parameter is a list type.
     */
    private CollectionFormat collectionFormat;
    /**
     * The explode if the parameter is a list type.
     */
    private boolean explode;

    /**
     * Create a new RestAPIParameter based on the provided properties.
     * @param description The description of this parameter.
     * @param rawType The raw type of this parameter. Result of SchemaMapper.
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
     * @param explode Whether arrays and objects should generate separate parameters for each array item or object property.
     */
    protected ProxyMethodParameter(String description, IType rawType, IType wireType, IType clientType, String name, com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation requestParameterLocation, String requestParameterName, boolean alreadyEncoded, boolean isConstant, boolean isRequired, boolean isNullable, boolean fromClient, String headerCollectionPrefix, String parameterReference, String defaultValue, CollectionFormat collectionFormat, boolean explode) {
        this.description = description;
        this.rawType = rawType;
        this.wireType = wireType;
        this.clientType = clientType;
        this.name = name;
        this.requestParameterLocation = requestParameterLocation;
        this.requestParameterName = requestParameterName;
        this.alreadyEncoded = alreadyEncoded;
        this.isConstant = isConstant;
        this.isRequired = isRequired;
        this.isNullable = isNullable;
        this.fromClient = fromClient;
        this.headerCollectionPrefix = headerCollectionPrefix;
        this.parameterReference = parameterReference;
        this.collectionFormat = collectionFormat;
        this.explode = explode;
        this.defaultValue = defaultValue;
    }

    public final String getDefaultValue() {
        return defaultValue;
    }

    public final String getDescription() {
        return description;
    }

    public final IType getRawType() {
        return rawType;
    }

    public final IType getWireType() {
        return wireType;
    }

    public final IType getClientType() {
        return clientType;
    }

    public final String getName() {
        return name;
    }

    public final RequestParameterLocation getRequestParameterLocation() {
        return requestParameterLocation;
    }

    public final String getRequestParameterName() {
        return requestParameterName;
    }

    public final boolean getAlreadyEncoded() {
        return alreadyEncoded;
    }

    public final boolean getIsConstant() {
        return isConstant;
    }

    public final boolean getIsRequired() {
        return isRequired;
    }

    public final boolean getIsNullable() {
        return isNullable;
    }

    public final boolean getFromClient() {
        return fromClient;
    }

    public final String getHeaderCollectionPrefix() {
        return headerCollectionPrefix;
    }

    public final String getParameterReference() {
        return parameterReference;
    }

    public final String getParameterReferenceConverted() {
        return String.format("%1$sConverted", CodeNamer.toCamelCase(CodeNamer.removeInvalidCharacters(getParameterReference())));
    }

    public final CollectionFormat getCollectionFormat() {
        return collectionFormat;
    }

    public final boolean getExplode() {
        return explode;
    }

    public final String convertFromClientType(String source, String target, boolean alwaysNull) {
        return convertFromClientType(source, target, alwaysNull, false);
    }

    public final String convertFromClientType(String source, String target) {
        return convertFromClientType(source, target, false, false);
    }

    //C# TO JAVA CONVERTER NOTE: Java does not support optional parameters. Overloaded method(s) are created above:
//ORIGINAL LINE: public string ConvertFromClientType(string source, string target, bool alwaysNull = false, bool alwaysNonNull = false)
    public final String convertFromClientType(String source, String target, boolean alwaysNull, boolean alwaysNonNull) {
        if (getClientType() == getWireType()) {
            return String.format("%1$s %2$s = %3$s;", getWireType(), target, source);
        }
        if (alwaysNull) {
            return String.format("%1$s %2$s = null;", getWireType(), target);
        }
        if (getIsRequired() || alwaysNonNull) {
            return String.format("%1$s %2$s = %3$s;", getWireType(), target, getWireType().convertFromClientType(source));
        } else {
            return String.format("%1$s %2$s = %3$s == null ? null : %4$s;", getWireType(), target, source, getWireType().convertFromClientType(source));
        }
    }

    /**
     * Add this property's imports to the provided ISet of imports.
     * @param imports The set of imports to add to.
     * @param includeImplementationImports Whether or not to include imports that are only necessary for method implementations.
     */
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports, JavaSettings settings) {
        if (getRequestParameterLocation() != RequestParameterLocation.NONE/* && getRequestParameterLocation() != RequestParameterLocation.FormData*/) {
            imports.add(String.format("com.azure.core.annotation.%1$sParam", CodeNamer.toPascalCase(getRequestParameterLocation().toString())));
        }
        if (getRequestParameterLocation() != RequestParameterLocation.BODY) {
            if (getClientType() == ArrayType.ByteArray) {
                imports.add("com.azure.core.util.Base64Util");
            } else if (getClientType() instanceof ListType && !getExplode()) {
                imports.add("com.azure.core.util.serializer.CollectionFormat");
                if (!settings.isLowLevelClient()) {
                    imports.add("com.azure.core.util.serializer.JacksonAdapter");
                }
            } else if (getClientType() instanceof ListType && getExplode()) {
                imports.add("java.util.stream.Collectors");
            }
        }
//        if (getRequestParameterLocation() == RequestParameterLocation.FormData) {
//            imports.add(String.format("com.azure.core.annotation.FormParam"));
//        }

        getWireType().addImportsTo(imports, includeImplementationImports);
    }

    public static class Builder {
        protected String description;
        protected IType rawType;
        protected IType wireType;
        protected IType clientType;
        protected String name;
        protected RequestParameterLocation requestParameterLocation = RequestParameterLocation.values()[0];
        protected String requestParameterName;
        protected boolean alreadyEncoded = false;
        protected boolean isConstant = false;
        protected boolean isRequired;
        protected boolean isNullable;
        protected boolean fromClient;
        protected String headerCollectionPrefix;
        protected String parameterReference;
        protected String defaultValue;
        protected CollectionFormat collectionFormat;
        protected boolean explode;

        /**
         * Sets the description of this parameter.
         * @param description the description of this parameter
         * @return the Builder itself
         */
        public Builder description(String description) {
            this.description = description;
            return this;
        }

        /**
         * Sets the raw type of this parameter. Result of SchemaMapper.
         * @param rawType the raw type of this parameter
         * @return the Builder itself
         */
        public Builder rawType(IType rawType) {
            this.rawType = rawType;
            return this;
        }

        /**
         * Sets the type of this parameter.
         * @param wireType the type of this parameter
         * @return the Builder itself
         */
        public Builder wireType(IType wireType) {
            this.wireType = wireType;
            return this;
        }

        /**
         * Sets the type of this parameter.
         * @param clientType the type of this parameter
         * @return the Builder itself
         */
        public Builder clientType(IType clientType) {
            this.clientType = clientType;
            return this;
        }

        /**
         * Sets the name of this parameter when it is used as a variable.
         * @param name the name of this parameter when it is used as a variable
         * @return the Builder itself
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }

        /**
         * Sets the location within the REST API method's URL where this parameter will be added.
         * @param requestParameterLocation the location within the REST API method's URL where this parameter will be added
         * @return the Builder itself
         */
        public Builder requestParameterLocation(RequestParameterLocation requestParameterLocation) {
            this.requestParameterLocation = requestParameterLocation;
            return this;
        }

        /**
         * Sets the name of this parameter when it is serialized.
         * @param requestParameterName the name of this parameter when it is serialized
         * @return the Builder itself
         */
        public Builder requestParameterName(String requestParameterName) {
            this.requestParameterName = requestParameterName;
            return this;
        }

        /**
         * Sets whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.
         * @param alreadyEncoded whether or not the value of this parameter will already be encoded
         * @return the Builder itself
         */
        public Builder alreadyEncoded(boolean alreadyEncoded) {
            this.alreadyEncoded = alreadyEncoded;
            return this;
        }

        /**
         * Sets whether or not this parameter is a constant value.
         * @param isConstant whether or not this parameter is a constant value
         * @return the Builder itself
         */
        public Builder isConstant(boolean isConstant) {
            this.isConstant = isConstant;
            return this;
        }

        /**
         * Sets whether or not this parameter is required.
         * @param isRequired whether or not this parameter is required
         * @return the Builder itself
         */
        public Builder isRequired(boolean isRequired) {
            this.isRequired = isRequired;
            return this;
        }

        /**
         * Sets whether or not this parameter is nullable.
         * @param isNullable whether or not this parameter is nullable
         * @return the Builder itself
         */
        public Builder isNullable(boolean isNullable) {
            this.isNullable = isNullable;
            return this;
        }

        /**
         * Sets whether or not this parameter's value comes from a ServiceClientProperty.
         * @param fromClient whether or not this parameter's value comes from a ServiceClientProperty
         * @return the Builder itself
         */
        public Builder fromClient(boolean fromClient) {
            this.fromClient = fromClient;
            return this;
        }

        /**
         * Sets the x-ms-header-collection-prefix extension value.
         * @param headerCollectionPrefix the x-ms-header-collection-prefix extension value
         * @return the Builder itself
         */
        public Builder headerCollectionPrefix(String headerCollectionPrefix) {
            this.headerCollectionPrefix = headerCollectionPrefix;
            return this;
        }

        /**
         * Sets the reference to this parameter from a caller.
         * @param parameterReference the reference to this parameter from a caller
         * @return the Builder itself
         */
        public Builder parameterReference(String parameterReference) {
            this.parameterReference = parameterReference;
            return this;
        }

        /**
         * Sets the description of this parameter.
         * @param defaultValue the description of this parameter
         * @return the Builder itself
         */
        public Builder defaultValue(String defaultValue) {
            this.defaultValue = defaultValue;
            return this;
        }

        /**
         * Sets the collection format if the parameter is a list type.
         * @param collectionFormat the collection format if the parameter is a list type
         * @return the Builder itself
         */
        public Builder collectionFormat(CollectionFormat collectionFormat) {
            this.collectionFormat = collectionFormat;
            return this;
        }
        
        /**
         * Sets the explode if the parameter is a list type.
         * @param explode the explode if the parameter is a list type
         * @return the Builder itself
         */
        public Builder explode(boolean explode) {
            this.explode = explode;
            return this;
        }

        public ProxyMethodParameter build() {
            return new ProxyMethodParameter(description,
                    rawType,
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
                    collectionFormat,
                    explode);
        }
    }
}
