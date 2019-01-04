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

        private Dictionary<Tuple<Type, Type>, Object> parsers = new Dictionary<Tuple<Type, Type>, object>();

        public ParserFactory(JavaSettings settings)
        {
            Settings = settings;
            InitParser<IModelTypeJv, IType, TypeParser>();
            InitParser<CodeModelJv, Client, ClientParser>();
            InitParser<CodeModelJv, ServiceClient, ServiceClientParser>();
            InitParser<CodeModelJv, Manager, ManagerParser>();
            InitParser<MethodGroupJv, MethodGroupClient, MethodGroupParser>();
            InitParser<MethodJv, ProxyMethod, RestAPIMethodParser>();
            InitParser<MethodJv, IEnumerable<ClientMethod>, ClientMethodParser>();
            InitParser<ParameterJv, ProxyMethodParameter, RestAPIParameterParser>();
            InitParser<ParameterJv, MethodParameter, MethodParameterParser>();
            InitParser<PropertyJv, ClientModelProperty, PropertyParser>();
            InitParser<CompositeTypeJv, ClientModel, ModelParser>();
            InitParser<CompositeTypeJv, ClientException, ExceptionParser>();
        }

        public IParser<FromT, ToT> GetParser<FromT, ToT>()
        {
            var fromT = typeof(FromT);
            var toT = typeof(ToT);
            var key = new Tuple<Type, Type>(fromT, toT);

            if (parsers.ContainsKey(key))
            {
                return (IParser<FromT, ToT>) parsers[key];
            }
            else
            {
                throw new NotSupportedException($"Cannot find a parser to parse {fromT} to {toT}.");
            }
        }

        private void InitParser<FromT, ToT, ParserT>()
        {
            var fromT = typeof(FromT);
            var toT = typeof(ToT);
            var parserT = typeof(ParserT);

            var constructor = parserT.GetConstructor(new Type[] { typeof(ParserFactory) });
            parsers.Add(new Tuple<Type, Type>(fromT, toT), constructor.Invoke(new Object[] { this }));
        }
    }
}