// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A parameter for a REST API method.
    /// </summary>
    public class RestAPIParameter
    {
        /// <summary>
        /// Create a new RestAPIParameter based on the provided properties.
        /// </summary>
        /// <param name="description">The description of this parameter.</param>
        /// <param name="type">The type of this parameter.</param>
        /// <param name="name">The name of this parameter when it is used as a variable.</param>
        /// <param name="requestParameterLocation">The location within the REST API method's HttpRequest where this parameter will be added.</param>
        /// <param name="requestParameterName">The name of the HttpRequest's parameter to substitute with this parameter's value.</param>
        /// <param name="alreadyEncoded">Whether or not the value of this parameter will already be encoded (and can therefore be skipped when other parameters' values are being encoded.</param>
        /// <param name="isConstant">Whether or not this parameter is a constant value.</param>
        /// <param name="isRequired">Whether or not this parameter is required.</param>
        /// <param name="isServiceClientProperty">Whether or not this parameter's value comes from a ServiceClientProperty.</param>
        /// <param name="headerCollectionPrefix">The x-ms-header-collection-prefix extension value.</param>
        public RestAPIParameter(string description, IType type, string name, RequestParameterLocation requestParameterLocation, string requestParameterName, bool alreadyEncoded, bool isConstant, bool isRequired, bool isServiceClientProperty, string headerCollectionPrefix)
        {
            Description = description;
            Type = type;
            Name = name;
            RequestParameterLocation = requestParameterLocation;
            RequestParameterName = requestParameterName;
            AlreadyEncoded = alreadyEncoded;
            IsConstant = isConstant;
            IsRequired = isRequired;
            HeaderCollectionPrefix = headerCollectionPrefix;
        }

        /// <summary>
        /// Get the description of this parameter.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get the type of this parameter.
        /// </summary>
        public IType Type { get; }

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

        /// <summary>
        /// Whether or not this parameter's value comes from a ServiceClientProperty.
        /// </summary>
        public bool IsServiceClientProperty { get; }

        /// <summary>
        /// The x-ms-header-collection-prefix extension value.
        /// </summary>
        public string HeaderCollectionPrefix { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            if (RequestParameterLocation != RequestParameterLocation.None && RequestParameterLocation != RequestParameterLocation.FormData)
            {
                imports.Add($"com.microsoft.rest.v2.annotations.{RequestParameterLocation}Param");
            }
            if (RequestParameterLocation != RequestParameterLocation.Body)
            {
                if (Type == ArrayType.ByteArray)
                {
                    imports.Add("org.apache.commons.codec.binary.Base64");
                }
                else if (Type is ListType)
                {
                    imports.Add("com.microsoft.rest.v2.CollectionFormat");
                }
            }

            Type.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
