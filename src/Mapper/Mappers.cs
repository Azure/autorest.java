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
    public class Mappers
    {
        public static TypeMapper TypeMapper => TypeMapper.Instance;

        public static ClientMapper ClientMapper => ClientMapper.Instance;

        public static ServiceClientMapper ServiceClientMapper => ServiceClientMapper.Instance;

        public static ManagerMapper ManagerMapper => ManagerMapper.Instance;

        public static MethodGroupMapper MethodGroupMapper => MethodGroupMapper.Instance;

        public static ProxyMethodMapper ProxyMethodMapper => ProxyMethodMapper.Instance;

        public static ClientMethodMapper ClientMethodMapper => ClientMethodMapper.Instance;

        public static ProxyParameterMapper ProxyParameterMapper => ProxyParameterMapper.Instance;

        public static ClientParameterMapper ClientParameterMapper => ClientParameterMapper.Instance;

        public static ModelPropertyMapper ModelPropertyMapper => ModelPropertyMapper.Instance;

        public static ModelMapper ModelMapper => ModelMapper.Instance;

        public static ExceptionMapper ExceptionMapper => ExceptionMapper.Instance;
    }
}