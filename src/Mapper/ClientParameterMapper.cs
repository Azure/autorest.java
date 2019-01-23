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
    public class ClientParameterMapper : IMapper<ParameterJv, ClientMethodParameter>
    {
        private ClientParameterMapper()
        {
        }

        private static ClientParameterMapper _instance = new ClientParameterMapper();
        public static ClientParameterMapper Instance => _instance;

        public ClientMethodParameter Map(ParameterJv parameter)
        {
            var settings = JavaSettings.Instance;
            
            IType wireType = Mappers.TypeMapper.Map((IModelTypeJv)parameter.ModelType);
            if (parameter.IsNullable())
            {
                wireType = wireType.AsNullable();
            }
            IType parameterType = wireType.ClientType;

            string parameterDescription = parameter.Documentation;
            if (string.IsNullOrEmpty(parameterDescription))
            {
                parameterDescription = $"the {wireType.ClientType} value";
            }

            IEnumerable<ClassType> parameterAnnotations = settings.NonNullAnnotations && parameter.IsRequired ?
                new[] { ClassType.NonNull } : Enumerable.Empty<ClassType>();

            return new ClientMethodParameter(
                description: parameterDescription,
                isFinal: false,
                wireType: wireType,
                name: parameter.Name,
                isRequired: parameter.IsRequired,
                isConstant: parameter.IsConstant,
                fromClient: parameter.IsClientProperty,
                defaultValue: parameter.DefaultValue,
                annotations: parameterAnnotations);
        }
    }
}