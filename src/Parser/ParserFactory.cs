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
    public class ParserFactory
    {
        public JavaSettings Settings { get; private set; }

        public ParserFactory(JavaSettings settings)
        {
            Settings = settings;
            typeParser = new TypeParser(this);
            serviceParser = new ClientParser(this);
            serviceClientParser = new ServiceClientParser(this);
            managerParser = new ManagerParser(this);
            methodGroupParser = new MethodGroupParser(this);
            restAPIMethodParser = new RestAPIMethodParser(this);
            clientMethodParser = new ClientMethodParser(this);
            restAPIParameterParser = new RestAPIParameterParser(this);
            methodParameterParser = new MethodParameterParser(this);
            propertyParser = new PropertyParser(this);
            compositeModelParser = new ModelParser(this);
            compositeExceptionParser = new ExceptionParser(this);
        }

        private TypeParser typeParser;
        private ClientParser serviceParser;
        private ServiceClientParser serviceClientParser;
        private ManagerParser managerParser;
        private MethodGroupParser methodGroupParser;
        private RestAPIMethodParser restAPIMethodParser;
        private ClientMethodParser clientMethodParser;
        private RestAPIParameterParser restAPIParameterParser;
        private MethodParameterParser methodParameterParser;
        private PropertyParser propertyParser;
        private ModelParser compositeModelParser;
        private ExceptionParser compositeExceptionParser;

        public IParser<FromT, ToT> GetParser<FromT, ToT>()
        {
            Type fromT = typeof(FromT);
            Type toT = typeof(ToT);

            if (fromT == typeof(IModelTypeJv) && toT == typeof(IType))
            {
                return (IParser<FromT, ToT>) typeParser;
            }
            else if (fromT == typeof(CodeModelJv) && toT == typeof(Client))
            {
                return (IParser<FromT, ToT>) serviceParser;
            }
            else if (fromT == typeof(CodeModelJv) && toT == typeof(ServiceClient))
            {
                return (IParser<FromT, ToT>) serviceClientParser;
            }
            else if (fromT == typeof(CodeModelJv) && toT == typeof(Manager))
            {
                return (IParser<FromT, ToT>) managerParser;
            }
            else if (fromT == typeof(MethodGroupJv) && toT == typeof(MethodGroupClient))
            {
                return (IParser<FromT, ToT>) methodGroupParser;
            }
            else if (fromT == typeof(MethodJv) && toT == typeof(ProxyMethod))
            {
                return (IParser<FromT, ToT>) restAPIMethodParser;
            }
            else if (fromT == typeof(MethodJv) && toT == typeof(IEnumerable<ClientMethod>))
            {
                return (IParser<FromT, ToT>) clientMethodParser;
            }
            else if (fromT == typeof(ParameterJv) && toT == typeof(ProxyMethodParameter))
            {
                return (IParser<FromT, ToT>) restAPIParameterParser;
            }
            else if (fromT == typeof(ParameterJv) && toT == typeof(MethodParameter))
            {
                return (IParser<FromT, ToT>) methodParameterParser;
            }
            else if (fromT == typeof(PropertyJv) && toT == typeof(ClientModelProperty))
            {
                return (IParser<FromT, ToT>) propertyParser;
            }
            else if (fromT == typeof(CompositeTypeJv) && toT == typeof(ClientModel))
            {
                return (IParser<FromT, ToT>) compositeModelParser;
            }
            else if (fromT == typeof(CompositeTypeJv) && toT == typeof(ClientException))
            {
                return (IParser<FromT, ToT>) compositeExceptionParser;
            }
            else
            {
                throw new NotSupportedException($"Cannot find a parser to parse {fromT} to {toT}.");
            }
        }
    }
}