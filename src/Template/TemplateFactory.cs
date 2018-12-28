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
    public class TemplateFactory
    {
        public JavaSettings Settings { get; private set; }

        public TemplateFactory(JavaSettings settings)
        {
            Settings = settings;
            serviceClientInterfaceWriter = new ServiceClientInterfaceTemplate(this);
            serviceClientWriter = new ServiceClientTemplate(this);
            managerWriter = new ManagerTemplate(this);
            methodGroupInterfaceWriter = new MethodGroupInterfaceTemplate(this);
            methodGroupWriter = new MethodGroupTemplate(this);
            proxyWriter = new ProxyTemplate(this);
            clientMethodWriter = new ClientMethodTemplate(this);
            modelWriter = new ModelTemplate(this);
            exceptionWriter = new ExceptionTemplate(this);
            enumWriter = new EnumTemplate(this);
            pageWriter = new PageTemplate(this);
            responseWriter = new ResponseTemplate(this);
            xmlSequenceWrapperWriter = new XmlSequenceWrapperTemplate(this);
            packageInfoWriter = new PackageInfoTemplate(this);
        }

        private ServiceClientInterfaceTemplate serviceClientInterfaceWriter;
        private ServiceClientTemplate serviceClientWriter;
        private ManagerTemplate managerWriter;
        private MethodGroupInterfaceTemplate methodGroupInterfaceWriter;
        private MethodGroupTemplate methodGroupWriter;
        private ProxyTemplate proxyWriter;
        private ClientMethodTemplate clientMethodWriter;
        private ModelTemplate modelWriter;
        private ExceptionTemplate exceptionWriter;
        private EnumTemplate enumWriter;
        private PageTemplate pageWriter;
        private ResponseTemplate responseWriter;
        private XmlSequenceWrapperTemplate xmlSequenceWrapperWriter;
        private PackageInfoTemplate packageInfoWriter;

        public IJavaTemplate<ModelT, ContextT> GetWriter<ModelT, ContextT>(bool isInterface = false) where ContextT : JavaContext
        {
            Type modelT = typeof(ModelT);
            Type contextT = typeof(ContextT);

            if (modelT == typeof(ServiceClient))
            {
                return isInterface ? (IJavaTemplate<ModelT, ContextT>) serviceClientInterfaceWriter : (IJavaTemplate<ModelT, ContextT>) serviceClientWriter;
            }
            else if (modelT == typeof(Manager))
            {
                return (IJavaTemplate<ModelT, ContextT>) managerWriter;
            }
            else if (modelT == typeof(MethodGroupClient))
            {
                return isInterface ? (IJavaTemplate<ModelT, ContextT>) methodGroupInterfaceWriter : (IJavaTemplate<ModelT, ContextT>) methodGroupWriter;
            }
            else if (modelT == typeof(Proxy))
            {
                return (IJavaTemplate<ModelT, ContextT>) proxyWriter;
            }
            else if (modelT == typeof(ClientMethod))
            {
                return (IJavaTemplate<ModelT, ContextT>) clientMethodWriter;
            }
            else if (modelT == typeof(ClientModel))
            {
                return (IJavaTemplate<ModelT, ContextT>) modelWriter;
            }
            else if (modelT == typeof(ClientException))
            {
                return (IJavaTemplate<ModelT, ContextT>) exceptionWriter;
            }
            else if (modelT == typeof(EnumType))
            {
                return (IJavaTemplate<ModelT, ContextT>) enumWriter;
            }
            else if (modelT == typeof(PageDetails))
            {
                return (IJavaTemplate<ModelT, ContextT>) pageWriter;
            }
            else if (modelT == typeof(ClientResponse))
            {
                return (IJavaTemplate<ModelT, ContextT>) responseWriter;
            }
            else if (modelT == typeof(XmlSequenceWrapper))
            {
                return (IJavaTemplate<ModelT, ContextT>) xmlSequenceWrapperWriter;
            }
            else if (modelT == typeof(PackageInfo))
            {
                return (IJavaTemplate<ModelT, ContextT>) packageInfoWriter;
            }
            else
            {
                throw new NotSupportedException($"Cannot find a Writer to write {modelT} to {contextT}.");
            }
        }
    }
}