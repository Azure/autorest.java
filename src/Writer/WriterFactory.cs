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
    public class WriterFactory
    {
        public JavaSettings Settings { get; private set; }

        public WriterFactory(JavaSettings settings)
        {
            Settings = settings;
            serviceClientInterfaceWriter = new ServiceClientInterfaceWriter(this);
            serviceClientWriter = new ServiceClientWriter(this);
            managerWriter = new ManagerWriter(this);
            methodGroupInterfaceWriter = new MethodGroupInterfaceWriter(this);
            methodGroupWriter = new MethodGroupWriter(this);
            proxyWriter = new ProxyWriter(this);
            clientMethodWriter = new ClientMethodWriter(this);
            modelWriter = new ModelWriter(this);
            exceptionWriter = new ExceptionWriter(this);
            enumWriter = new EnumWriter(this);
            pageWriter = new PageWriter(this);
            responseWriter = new ResponseWriter(this);
            xmlSequenceWrapperWriter = new XmlSequenceWrapperWriter(this);
            packageInfoWriter = new PackageInfoWriter(this);
        }

        private ServiceClientInterfaceWriter serviceClientInterfaceWriter;
        private ServiceClientWriter serviceClientWriter;
        private ManagerWriter managerWriter;
        private MethodGroupInterfaceWriter methodGroupInterfaceWriter;
        private MethodGroupWriter methodGroupWriter;
        private ProxyWriter proxyWriter;
        private ClientMethodWriter clientMethodWriter;
        private ModelWriter modelWriter;
        private ExceptionWriter exceptionWriter;
        private EnumWriter enumWriter;
        private PageWriter pageWriter;
        private ResponseWriter responseWriter;
        private XmlSequenceWrapperWriter xmlSequenceWrapperWriter;
        private PackageInfoWriter packageInfoWriter;

        public IWriter<ModelT, ContextT> GetWriter<ModelT, ContextT>(bool isInterface = false) where ContextT : JavaContext
        {
            Type modelT = typeof(ModelT);
            Type contextT = typeof(ContextT);

            if (modelT == typeof(ServiceClient))
            {
                return isInterface ? (IWriter<ModelT, ContextT>) serviceClientInterfaceWriter : (IWriter<ModelT, ContextT>) serviceClientWriter;
            }
            else if (modelT == typeof(Manager))
            {
                return (IWriter<ModelT, ContextT>) managerWriter;
            }
            else if (modelT == typeof(MethodGroupClient))
            {
                return isInterface ? (IWriter<ModelT, ContextT>) methodGroupInterfaceWriter : (IWriter<ModelT, ContextT>) methodGroupWriter;
            }
            else if (modelT == typeof(Proxy))
            {
                return (IWriter<ModelT, ContextT>) proxyWriter;
            }
            else if (modelT == typeof(ClientMethod))
            {
                return (IWriter<ModelT, ContextT>) clientMethodWriter;
            }
            else if (modelT == typeof(ClientModel))
            {
                return (IWriter<ModelT, ContextT>) modelWriter;
            }
            else if (modelT == typeof(ClientException))
            {
                return (IWriter<ModelT, ContextT>) exceptionWriter;
            }
            else if (modelT == typeof(EnumType))
            {
                return (IWriter<ModelT, ContextT>) enumWriter;
            }
            else if (modelT == typeof(PageDetails))
            {
                return (IWriter<ModelT, ContextT>) pageWriter;
            }
            else if (modelT == typeof(ClientResponse))
            {
                return (IWriter<ModelT, ContextT>) responseWriter;
            }
            else if (modelT == typeof(XmlSequenceWrapper))
            {
                return (IWriter<ModelT, ContextT>) xmlSequenceWrapperWriter;
            }
            else if (modelT == typeof(PackageInfo))
            {
                return (IWriter<ModelT, ContextT>) packageInfoWriter;
            }
            else
            {
                throw new NotSupportedException($"Cannot find a Writer to write {modelT} to {contextT}.");
            }
        }
    }
}