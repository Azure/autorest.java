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

        public MethodParameter GenerateParameter(bool final, JavaSettings settings)
        {
            IType parameterType = this.GenerateType(settings).ClientType;

            string parameterDescription = Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {parameterType} value";
            }

            IEnumerable<ClassType> parameterAnnotations = settings.NonNullAnnotations && IsRequired ?
                new[] { ClassType.NonNull } : Enumerable.Empty<ClassType>();

            return new MethodParameter(
                description: parameterDescription,
                isFinal: final,
                type: parameterType,
                name: Name,
                isRequired: IsRequired,
                isConstant: IsConstant,
                fromClient: IsClientProperty,
                defaultValue: DefaultValue,
                annotations: parameterAnnotations);
        }
    }
}