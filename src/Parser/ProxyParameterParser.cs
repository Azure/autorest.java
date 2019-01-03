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
    public class RestAPIParameterParser : IParser<ParameterJv, ProxyMethodParameter>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public RestAPIParameterParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public ProxyMethodParameter Parse(ParameterJv parameter)
        {
            string parameterRequestName = parameter.SerializedName;

            RequestParameterLocation parameterRequestLocation = parameter.ExtendedParameterLocation;
            string parameterHeaderCollectionPrefix = parameter.Extensions.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

            IModelTypeJv ParameterJvWireType = (IModelTypeJv) parameter.ModelType;
            IType parameterType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)parameter.ModelType);
            if (parameter.IsNullable())
            {
                parameterType = parameterType.AsNullable();
            }
            if (parameterType is ListType && settings.ShouldGenerateXmlSerialization && parameterRequestLocation == RequestParameterLocation.Body)
            {
                string parameterTypePackage = settings.GetPackage(settings.ImplementationSubpackage);
                string parameterTypeName = ParameterJvWireType.XmlName.ToPascalCase() + "Wrapper";
                parameterType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
            }
            else if (parameterType == ArrayType.ByteArray)
            {
                if (parameterRequestLocation != RequestParameterLocation.Body && parameterRequestLocation != RequestParameterLocation.FormData)
                {
                    parameterType = ClassType.String;
                }
            }
            else if (parameterType is ListType && parameter.Location != AutoRest.Core.Model.ParameterLocation.Body && parameter.Location != AutoRest.Core.Model.ParameterLocation.FormData)
            {
                parameterType = ClassType.String;
            }

            bool parameterIsNullable = parameter.IsNullable();
            if (parameterIsNullable)
            {
                parameterType = parameterType.AsNullable();
            }

            string parameterDescription = parameter.Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {parameterType} value";
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
                parameterType,
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