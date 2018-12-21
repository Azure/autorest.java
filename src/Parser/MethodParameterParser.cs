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
    public class MethodParameterParser : IParser<ParameterJv, MethodParameter>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public MethodParameterParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public MethodParameter Parse(ParameterJv parameter)
        {
            IType wireType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)parameter.ModelType);
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

            return new MethodParameter(
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