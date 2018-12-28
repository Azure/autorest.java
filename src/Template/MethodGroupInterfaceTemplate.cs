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
    public class MethodGroupInterfaceTemplate : IJavaTemplate<MethodGroupClient, JavaFile>
    {
        private JavaSettings settings;
        private TemplateFactory factory;

        public MethodGroupInterfaceTemplate(TemplateFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public void Write(MethodGroupClient methodGroupClient, JavaFile javaFile)
        {
            HashSet<string> imports = new HashSet<string>();
            methodGroupClient.AddImportsTo(imports, false, settings);
            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClient.InterfaceName}.");
            });
            javaFile.PublicInterface(methodGroupClient.InterfaceName, interfaceBlock =>
            {
                foreach (ClientMethod clientMethod in methodGroupClient.ClientMethods)
                {
                    factory.GetWriter<ClientMethod, JavaType>().Write(clientMethod, interfaceBlock);
                }
            });
        }
    }
}