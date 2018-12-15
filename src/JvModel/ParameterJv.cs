// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Utilities;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core;
using System;
using AutoRest.Extensions;

namespace AutoRest.Java.Model
{
    public class ParameterJv : Parameter
    {
        public ParameterJv()
            : base()
        {}

        public IType GenerateType(JavaSettings settings)
        {
            IType result = ((IModelTypeJv)ModelType)?.GenerateType(settings);
            if (result != null && this.IsNullable())
            {
                result = result.AsNullable();
            }
            return result;
        }

        public RequestParameterLocation ExtendedParameterLocation
        {
            get
            {
                RequestParameterLocation parameterRequestLocation;
                switch (Location)
                {
                    case AutoRest.Core.Model.ParameterLocation.Body:
                        parameterRequestLocation = RequestParameterLocation.Body;
                        break;

                    case AutoRest.Core.Model.ParameterLocation.FormData:
                        parameterRequestLocation = RequestParameterLocation.FormData;
                        break;

                    case AutoRest.Core.Model.ParameterLocation.Header:
                        parameterRequestLocation = RequestParameterLocation.Header;
                        break;

                    case AutoRest.Core.Model.ParameterLocation.None:
                        parameterRequestLocation = RequestParameterLocation.None;
                        break;

                    case AutoRest.Core.Model.ParameterLocation.Path:
                        parameterRequestLocation = RequestParameterLocation.Path;
                        break;

                    case AutoRest.Core.Model.ParameterLocation.Query:
                        parameterRequestLocation = RequestParameterLocation.Query;
                        break;

                    default:
                        parameterRequestLocation = RequestParameterLocation.None;
                        break;
                }
                if (Method.Url.Contains("{" + SerializedName + "}"))
                {
                    parameterRequestLocation = RequestParameterLocation.Path;
                }
                else if (Extensions.ContainsKey("hostParameter"))
                {
                    parameterRequestLocation = RequestParameterLocation.Host;
                }
                return parameterRequestLocation;
            }
        }

        public RestAPIParameter GenerateRestAPIParameter(JavaSettings settings)
        {
            string parameterRequestName = SerializedName;

            RequestParameterLocation parameterRequestLocation = ExtendedParameterLocation;
            string parameterHeaderCollectionPrefix = Extensions.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

            IModelTypeJv ParameterJvWireType = (IModelTypeJv) ModelType;
            IType parameterType = GenerateType(settings);
            if (parameterType is ListType && settings.ShouldGenerateXmlSerialization && parameterRequestLocation == RequestParameterLocation.Body)
            {
                string parameterTypePackage = CodeGeneratorJv.GetPackage(settings, settings.ImplementationSubpackage);
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
            else if (parameterType is ListType && Location != AutoRest.Core.Model.ParameterLocation.Body && Location != AutoRest.Core.Model.ParameterLocation.FormData)
            {
                parameterType = ClassType.String;
            }

            bool parameterIsNullable = this.IsNullable();
            if (parameterIsNullable)
            {
                parameterType = parameterType.AsNullable();
            }

            string parameterDescription = Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {parameterType} value";
            }

            string parameterVariableName = ClientProperty?.Name?.ToString();
            if (!string.IsNullOrEmpty(parameterVariableName))
            {
                CodeNamer codeNamer = CodeNamer.Instance;
                parameterVariableName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(parameterVariableName));
            }
            if (parameterVariableName == null)
            {
                if (!IsClientProperty)
                {
                    parameterVariableName = Name;
                }
                else
                {
                    string caller = (Method != null && Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                    string clientPropertyName = ClientProperty?.Name?.ToString();
                    if (!string.IsNullOrEmpty(clientPropertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                    }
                    parameterVariableName = $"{caller}.{clientPropertyName}()";
                }
            }

            bool parameterSkipUrlEncodingExtension = Extensions?.Get<bool>(SwaggerExtensions.SkipUrlEncodingExtension) == true;

            bool parameterIsConstant = IsConstant;

            bool parameterIsRequired = IsRequired;

            bool parameterIsServiceClientProperty = IsClientProperty;

            string parameterReference;
            if (!IsClientProperty)
            {
                parameterReference = Name;
            }
            else
            {
                string caller = (Method != null && Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                string clientPropertyName = ClientProperty?.Name?.ToString();
                if (!string.IsNullOrEmpty(clientPropertyName))
                {
                    CodeNamer codeNamer = CodeNamer.Instance;
                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                }
                parameterReference = $"{caller}.{clientPropertyName}()";
            }

            return new RestAPIParameter(
                parameterDescription,
                parameterType,
                parameterVariableName,
                parameterRequestLocation,
                parameterRequestName, parameterSkipUrlEncodingExtension,
                parameterIsConstant,
                parameterIsRequired,
                parameterIsServiceClientProperty,
                parameterHeaderCollectionPrefix,
                parameterReference: parameterReference,
                collectionFormat: CollectionFormat);
        }

        public MethodParameter GenerateMethodParameter(bool final, JavaSettings settings)
        {
            IType wireType = this.GenerateType(settings);
            IType parameterType = wireType.ClientType;

            string parameterDescription = Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {wireType.ClientType} value";
            }

            IEnumerable<ClassType> parameterAnnotations = settings.NonNullAnnotations && IsRequired ?
                new[] { ClassType.NonNull } : Enumerable.Empty<ClassType>();

            return new MethodParameter(
                description: parameterDescription,
                isFinal: final,
                wireType: wireType,
                name: Name,
                isRequired: IsRequired,
                isConstant: IsConstant,
                fromClient: IsClientProperty,
                defaultValue: DefaultValue,
                annotations: parameterAnnotations);
        }
    }
}