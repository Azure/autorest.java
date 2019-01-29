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
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    /// <summary>
    /// A collection of templates for writing JV models to Java files and contexts.
    /// </summary>
    public class Templates
    {
        public static ServiceClientInterfaceTemplate ServiceClientInterfaceTemplate => ServiceClientInterfaceTemplate.Instance;
        
        public static ServiceClientTemplate ServiceClientTemplate => ServiceClientTemplate.Instance;
        
        public static ManagerTemplate ManagerTemplate => ManagerTemplate.Instance;
        
        public static MethodGroupInterfaceTemplate MethodGroupInterfaceTemplate => MethodGroupInterfaceTemplate.Instance;
        
        public static MethodGroupTemplate MethodGroupTemplate => MethodGroupTemplate.Instance;
        
        public static ProxyTemplate ProxyTemplate => ProxyTemplate.Instance;
        
        public static ClientMethodTemplate ClientMethodTemplate => ClientMethodTemplate.Instance;
        
        public static ModelTemplate ModelTemplate => ModelTemplate.Instance;
        
        public static ExceptionTemplate ExceptionTemplate => ExceptionTemplate.Instance;
        
        public static EnumTemplate EnumTemplate => EnumTemplate.Instance;
        
        public static PageTemplate PageTemplate => PageTemplate.Instance;
        
        public static ResponseTemplate ResponseTemplate => ResponseTemplate.Instance;
        
        public static XmlSequenceWrapperTemplate XmlSequenceWrapperTemplate => XmlSequenceWrapperTemplate.Instance;
        
        public static PackageInfoTemplate PackageInfoTemplate => PackageInfoTemplate.Instance;
    }
}