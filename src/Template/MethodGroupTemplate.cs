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
    /// <summary>
    /// Writes a MethodGroupClient to a JavaFile.
    /// </summary>
    public class MethodGroupTemplate : IJavaTemplate<MethodGroupClient, JavaFile>
    {
        private static MethodGroupTemplate _instance = new MethodGroupTemplate();
        public static MethodGroupTemplate Instance => _instance;

        private MethodGroupTemplate()
        {
        }

        public void Write(MethodGroupClient methodGroupClient, JavaFile javaFile)
        {
            var settings = JavaSettings.Instance;
            ISet<string> imports = new HashSet<string>();
            methodGroupClient.AddImportsTo(imports, true, settings);
            javaFile.Import(imports);

            string parentDeclaration = methodGroupClient.ImplementedInterfaces.Any() ? $" implements {string.Join(", ", methodGroupClient.ImplementedInterfaces)}" : "";

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClient.InterfaceName}.");
            });
            javaFile.PublicFinalClass($"{methodGroupClient.ClassName}{parentDeclaration}", classBlock =>
            {
                classBlock.JavadocComment($"The proxy service used to perform REST calls.");
                classBlock.PrivateMemberVariable(methodGroupClient.Proxy.Name, "service");

                classBlock.JavadocComment("The service client containing this operation class.");
                classBlock.PrivateMemberVariable(methodGroupClient.ServiceClientName, "client");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Initializes an instance of {methodGroupClient.ClassName}.");
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.PublicConstructor($"{methodGroupClient.ClassName}({methodGroupClient.ServiceClientName} client)", constructor =>
                {
                    if (methodGroupClient.Proxy != null)
                    {
                        ClassType proxyType = (settings.IsAzureOrFluent ? ClassType.AzureProxy : ClassType.RestProxy);
                        constructor.Line($"this.service = {proxyType.Name}.create({methodGroupClient.Proxy.Name}.class, client.getHttpPipeline());");
                    }
                    constructor.Line("this.client = client;");
                });
                
                Templates.ProxyTemplate.Write(methodGroupClient.Proxy, classBlock);

                foreach (ClientMethod clientMethod in methodGroupClient.ClientMethods)
                {
                    Templates.ClientMethodTemplate.Write(clientMethod, classBlock);
                }
            });
        }
    }
}