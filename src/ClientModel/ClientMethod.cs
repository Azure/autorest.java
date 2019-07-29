// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;
using AutoRestIModelType = AutoRest.Core.Model.IModelType;
using AutoRestKnownPrimaryType = AutoRest.Core.Model.KnownPrimaryType;
using AutoRestMethod = AutoRest.Core.Model.Method;
using AutoRestParameter = AutoRest.Core.Model.Parameter;
using AutoRestParameterLocation = AutoRest.Core.Model.ParameterLocation;
using AutoRestSequenceType = AutoRest.Core.Model.SequenceType;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A ClientMethod that exists on a ServiceClient or MethodGroupClient that eventually will call a ProxyMethod.
    /// </summary>
    public class ClientMethod
    {
        /// <summary>
        /// Create a new ClientMethod with the provided properties.
        /// </summary>
        /// <param name="description">The description of this ClientMethod.</param>
        /// <param name="returnValue">The return value of this ClientMethod.</param>
        /// <param name="name">The name of this ClientMethod.</param>
        /// <param name="parameters">The parameters of this ClientMethod.</param>
        /// <param name="onlyRequiredParameters">Whether or not this ClientMethod has omitted optional parameters.</param>
        /// <param name="type">The type of this ClientMethod.</param>
        /// <param name="proxyMethod">The ProxyMethod that this ClientMethod eventually calls.</param>
        /// <param name="expressionsToValidate">The expressions (parameters and service client properties) that need to be validated in this ClientMethod.</param>
        /// <param name="requiredNullableParameterExpressions">The parameter expressions which are required.</param>
        /// <param name="groupedParameter">The parameter that needs to transformed before pagination.</param>
        /// <param name="groupedParameterTypeName">The type name of groupedParameter.</param>
        /// <param name="methodPageDetails">The pagination information if this is a paged method.</param>
        /// <param name="methodTransformationDetails">The parameter transformations before calling ProxyMethod.</param>
        public ClientMethod(string description, ReturnValue returnValue, string name, IEnumerable<ClientMethodParameter> parameters, bool onlyRequiredParameters, ClientMethodType type, ProxyMethod proxyMethod, IEnumerable<string> expressionsToValidate, List<string> requiredNullableParameterExpressions, Parameter groupedParameter, string groupedParameterTypeName, MethodPageDetails methodPageDetails, List<MethodTransformationDetail> methodTransformationDetails)
        {
            Description = description;
            ReturnValue = returnValue;
            Name = name;
            Parameters = parameters;
            OnlyRequiredParameters = onlyRequiredParameters;
            Type = type;
            ProxyMethod = proxyMethod;
            ExpressionsToValidate = expressionsToValidate;
            RequiredNullableParameterExpressions = requiredNullableParameterExpressions;
            GroupedParameter = groupedParameter;
            GroupedParameterTypeName = groupedParameterTypeName;
            MethodPageDetails = methodPageDetails;
            MethodTransformationDetails = methodTransformationDetails;
        }

        /// <summary>
        /// The description of this ClientMethod.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// The return value of this ClientMethod.
        /// </summary>
        public ReturnValue ReturnValue { get; }

        /// <summary>
        /// The name of this ClientMethod.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The parameters of this ClientMethod.
        /// </summary>
        public IEnumerable<ClientMethodParameter> Parameters { get; }

        /// <summary>
        /// Whether or not this ClientMethod has omitted optional parameters.
        /// </summary>
        public bool OnlyRequiredParameters { get; }

        /// <summary>
        /// The type of this ClientMethod.
        /// </summary>
        public ClientMethodType Type { get; }

        /// <summary>
        /// The RestAPIMethod that this ClientMethod eventually calls.
        /// </summary>
        public ProxyMethod ProxyMethod { get; }

        /// <summary>
        /// The expressions (parameters and service client properties) that need to be validated in this ClientMethod.
        /// </summary>
        public IEnumerable<string> ExpressionsToValidate { get; }

        public string ClientReference => ProxyMethod.AutoRestMethod.Group.IsNullOrEmpty() ? "this" : "this.client";

        /// <summary>
        /// Get the comma-separated list of parameter declarations for this ClientMethod.
        /// </summary>
        public string ParametersDeclaration => string.Join(", ", Parameters.Select((ClientMethodParameter parameter) => parameter.Declaration));

        /// <summary>
        /// Get the comma-separated list of parameter names for this ClientMethod.
        /// </summary>
        public string ArgumentList => string.Join(", ", Parameters.Select((ClientMethodParameter parameter) => parameter.Name));

        /// <summary>
        /// The full declaration of this ClientMethod.
        /// </summary>
        public string Declaration => $"{ReturnValue.Type} {Name}({ParametersDeclaration})";

        public string PagingAsyncSinglePageMethodName => ProxyMethod.Name + "SinglePageAsync";

        public string SimpleAsyncMethodName => ProxyMethod.Name + "Async";

        public IEnumerable<ClientMethodParameter> MethodParameters => Parameters
                    //Omit parameter-group properties for now since Java doesn't support them yet
                    .Where(parameter => parameter != null && !parameter.FromClient && !string.IsNullOrWhiteSpace(parameter.Name))
                    .OrderBy(item => !item.IsRequired);
        public IEnumerable<ClientMethodParameter> MethodNonConstantParameters => MethodParameters
            .Where(parameter => !parameter.IsConstant)
            .OrderBy(parameter => !parameter.IsRequired);

        public IEnumerable<ClientMethodParameter> MethodRequiredParameters => MethodNonConstantParameters
            .Where(parameter => parameter.IsRequired);

        public List<string> RequiredNullableParameterExpressions { get; }

        public Parameter GroupedParameter { get; }

        public string GroupedParameterTypeName { get; }

        public MethodPageDetails MethodPageDetails { get; }

        public List<MethodTransformationDetail> MethodTransformationDetails { get; }

        public IEnumerable<string> GetProxyMethodArguments(JavaSettings settings)
        {
            IEnumerable<string> restAPIMethodArguments = ProxyMethod.Parameters
                .Select(parameter =>
                {
                    string parameterName = parameter.ParameterReference;
                    IType parameterWireType = parameter.WireType;;
                    if (parameter.IsNullable)
                    {
                        parameterWireType = parameterWireType.AsNullable();
                    }
                    IType parameterClientType = parameter.ClientType;

                    if (parameterClientType != ClassType.Base64Url &&
                        parameter.RequestParameterLocation != RequestParameterLocation.Body &&
                        parameter.RequestParameterLocation != RequestParameterLocation.FormData &&
                        (parameterClientType is ArrayType || parameterClientType is ListType))
                    {
                        parameterWireType = ClassType.String;
                    }

                    string parameterWireName = parameterClientType != parameterWireType ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                    string result;
                    /*if (settings.ShouldGenerateXmlSerialization && parameterWireType is ListType)
                    {
                        // used to be $"new {parameterWireType.XmlName.ToPascalCase()}Wrapper({parameterWireName})"
                        result = $"new {parameterWireType.ToString().ToPascalCase()}Wrapper({parameterWireName})";
                    }
                    else */if (MethodTransformationDetails.Any(d => d.OutParameterName == parameterName + "1"))
                    {
                        result = MethodTransformationDetails.First(d => d.OutParameterName == parameterName + "1").OutParameterName;
                    }
                    else
                    {
                        result = parameterWireName;
                    }
                    return result;
                });
            return restAPIMethodArguments;
        }

        /// <summary>
        /// Add this ClientMethod's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public virtual void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            ReturnValue.AddImportsTo(imports, includeImplementationImports);

            imports.Add("com.azure.core.implementation.annotation.ServiceMethod");
            imports.Add("com.azure.core.implementation.annotation.ReturnType");

            foreach (ClientMethodParameter parameter in Parameters)
            {
                parameter.AddImportsTo(imports, includeImplementationImports);
            }

            if (includeImplementationImports)
            {
                if (ExpressionsToValidate.Any() && settings.ClientSideValidations)
                {
                    imports.Add(ClassType.Validator.FullName);
                }

                ProxyMethod.AddImportsTo(imports, includeImplementationImports, settings);
                foreach (ProxyMethodParameter parameter in ProxyMethod.Parameters) {
                    parameter.ClientType.AddImportsTo(imports, true);
                }
            }
        }
    }
}
