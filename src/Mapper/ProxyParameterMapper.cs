// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class ProxyParameterMapper : IMapper<ParameterJv, ProxyMethodParameter>
    {
        private ProxyParameterMapper()
        {
        }

        private static ProxyParameterMapper _instance = new ProxyParameterMapper();
        public static ProxyParameterMapper Instance => _instance;

        public ProxyMethodParameter Map(ParameterJv parameter)
        {
            var settings = JavaSettings.Instance;
            string parameterRequestName = parameter.SerializedName;

            RequestParameterLocation parameterRequestLocation = parameter.ExtendedParameterLocation;
            string parameterHeaderCollectionPrefix = parameter.Extensions.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

            IModelTypeJv ParameterJvWireType = (IModelTypeJv) parameter.ModelType;
            IType wireType = Mappers.TypeMapper.Map((IModelTypeJv)parameter.ModelType);
            if (parameter.IsNullable())
            {
                wireType = wireType.AsNullable();
            }
            IType clientType = wireType.ClientType;
            if (wireType is ListType && settings.ShouldGenerateXmlSerialization && parameterRequestLocation == RequestParameterLocation.Body)
            {
                string parameterTypePackage = settings.GetPackage(settings.ImplementationSubpackage);
                string parameterTypeName = ParameterJvWireType.XmlName.ToPascalCase() + "Wrapper";
                wireType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
            }
            else if (wireType == ArrayType.ByteArray)
            {
                if (parameterRequestLocation != RequestParameterLocation.Body && parameterRequestLocation != RequestParameterLocation.FormData)
                {
                    wireType = ClassType.String;
                }
            }
            else if (wireType is ListType && parameter.Location != AutoRest.Core.Model.ParameterLocation.Body && parameter.Location != AutoRest.Core.Model.ParameterLocation.FormData)
            {
                wireType = ClassType.String;
            }

            bool parameterIsNullable = parameter.IsNullable();
            if (parameterIsNullable)
            {
                clientType = clientType.AsNullable();
            }

            string parameterDescription = parameter.Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {clientType} value";
            }

            string parameterVariableName = parameter.ClientProperty?.Name?.ToString();
            if (!string.IsNullOrEmpty(parameterVariableName))
            {
                CodeNamer codeNamer = CodeNamer.Instance;
                parameterVariableName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(parameterVariableName));
            }
            if (parameterVariableName == null)
            {
                if (!parameter.IsClientProperty)
                {
                    parameterVariableName = parameter.Name;
                }
                else
                {
                    string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                    string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                    if (!string.IsNullOrEmpty(clientPropertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                    }
                    parameterVariableName = $"{caller}.{clientPropertyName}()";
                }
            }

            bool parameterSkipUrlEncodingExtension = parameter.Extensions?.Get<bool>(SwaggerExtensions.SkipUrlEncodingExtension) == true;

            bool parameterIsConstant = parameter.IsConstant;

            bool parameterIsRequired = parameter.IsRequired;

            bool parameterIsServiceClientProperty = parameter.IsClientProperty;

            string parameterReference;
            if (!parameter.IsClientProperty)
            {
                parameterReference = parameter.Name;
            }
            else
            {
                string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                if (!string.IsNullOrEmpty(clientPropertyName))
                {
                    CodeNamer codeNamer = CodeNamer.Instance;
                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                }
                parameterReference = $"{caller}.{clientPropertyName}()";
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
                parameter.IsNullable(),
                parameterIsServiceClientProperty,
                parameterHeaderCollectionPrefix,
                parameterReference: parameterReference,
                defaultValue: parameter.DefaultValue,
                collectionFormat: parameter.CollectionFormat);
        }
    }
}