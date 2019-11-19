// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A parameter for a ProxyMethod.
    /// </summary>
    public class ProxyMethodParameter
    {
        /// <summary>
        /// Create a new RestAPIParameter based on the provided properties.
        /// </summary>
        /// <param name="description">The description of this parameter.</param>
        /// <param name="wireType">The type of this parameter.</param>
        /// <param name="clientType">The type of this parameter users interact with.</param>
        /// <param name="name">The name of this parameter when it is used as a variable.</param>
        /// <param name="requestParameterLocation">The location within the REST API method's HttpRequest where this parameter will be added.</param>
        /// <param name="requestParameterName">The name of the HttpRequest's parameter to substitute with this parameter's value.</param>
        /// <param name="alreadyEncoded">Whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.</param>
        /// <param name="isConstant">Whether or not this parameter is a constant value.</param>
        /// <param name="isRequired">Whether or not this parameter is required.</param>
        /// <param name="isNullable">Whether or not this parameter is nullable.</param>
        /// <param name="fromClient">Whether or not this parameter's value comes from a ServiceClientProperty.</param>
        /// <param name="headerCollectionPrefix">The x-ms-header-collection-prefix extension value.</param>
        /// <param name="parameterReference">The reference to this parameter from a caller.</param>
        /// <param name="defaultValue">The default value of the parameter.</param>
        /// <param name="collectionFormat">The collection format if the parameter is a list type.</param>
        public ProxyMethodParameter(string description,
            IType wireType,
            IType clientType,
            string name,
            RequestParameterLocation requestParameterLocation,
            string requestParameterName,
            bool alreadyEncoded,
            bool isConstant,
            bool isRequired,
            bool isNullable,
            bool fromClient,
            string headerCollectionPrefix,
            string parameterReference,
            string defaultValue,
            CollectionFormat collectionFormat)
        {
            Description = description;
            WireType = wireType;
            ClientType = clientType;
            Name = name;
            RequestParameterLocation = requestParameterLocation;
            RequestParameterName = requestParameterName;
            AlreadyEncoded = alreadyEncoded;
            IsConstant = isConstant;
            IsRequired = isRequired;
            IsNullable = isNullable;
            FromClient = fromClient;
            HeaderCollectionPrefix = headerCollectionPrefix;
            ParameterReference = parameterReference;
            CollectionFormat = collectionFormat;
            DefaultValue = defaultValue;
        }

        /// <summary>
        /// Get the description of this parameter.
        /// </summary>
        public string DefaultValue { get; }


        /// <summary>
        /// Get the description of this parameter.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get the type of this parameter.
        /// </summary>
        public IType WireType { get; }

        /// <summary>
        /// Get the type of this parameter.
        /// </summary>
        public IType ClientType { get; }

        /// <summary>
        /// Get the name of this parameter when it is used as a variable.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the location within the REST API method's URL where this parameter will be added.
        /// </summary>
        public RequestParameterLocation RequestParameterLocation { get; }

        /// <summary>
        /// Get the name of this parameter when it is serialized.
        /// </summary>
        public string RequestParameterName { get; }

        /// <summary>
        /// Whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.
        /// </summary>
        public bool AlreadyEncoded { get; }

        /// <summary>
        /// Get whether or not this parameter is a constant value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Get whether or not this parameter is required.
        /// </summary>
        public bool IsRequired { get; }

        public bool IsNullable { get; }

        /// <summary>
        /// Whether or not this parameter's value comes from a ServiceClientProperty.
        /// </summary>
        public bool FromClient { get; }

        /// <summary>
        /// The x-ms-header-collection-prefix extension value.
        /// </summary>
        public string HeaderCollectionPrefix { get; }

        public string ParameterReference { get; }

        public string ParameterReferenceConverted => $"{ParameterReference.ToCamelCase()}Converted";

        public CollectionFormat CollectionFormat { get; }

        public string ConvertFromClientType(string source, string target, bool alwaysNull = false, bool alwaysNonNull = false)
        {
            var clientType = WireType.ClientType;
            if (clientType == WireType)
            {
                return $"{WireType} {target} = {source};";
            }
            if (alwaysNull)
            {
                return $"{WireType} {target} = null;";
            }
            if (IsRequired || alwaysNonNull)
            {
                return $"{WireType} {target} = {WireType.ConvertFromClientType(source)};";
            }
            else
            {
                return $"{WireType} {target} = {source} == null ? null : {WireType.ConvertFromClientType(source)};";
            }
        }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            if (RequestParameterLocation != RequestParameterLocation.None && RequestParameterLocation != RequestParameterLocation.FormData)
            {
                imports.Add($"com.azure.core.annotation.{RequestParameterLocation}Param");
            }
            if (RequestParameterLocation != RequestParameterLocation.Body)
            {
                if (ClientType == ArrayType.ByteArray)
                {
                    imports.Add("com.azure.core.util.Base64Util");
                }
                else if (ClientType is ListType)
                {
                    imports.Add("com.azure.core.util.serializer.CollectionFormat");
                    imports.Add("com.azure.core.util.serializer.JacksonAdapter");
                }
            }
            if (RequestParameterLocation == RequestParameterLocation.FormData) {
                imports.Add($"com.azure.core.annotation.FormParam");
            }

            WireType.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
