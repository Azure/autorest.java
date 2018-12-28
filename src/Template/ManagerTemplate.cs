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
    public class ManagerTemplate : IJavaTemplate<Manager, JavaFile>
    {
        private JavaSettings settings;
        private TemplateFactory factory;

        public ManagerTemplate(TemplateFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public void Write(Manager manager, JavaFile javaFile)
        {
            string className = $"{manager.ServiceName}Manager";

            string[] versionParts = CodeGeneratorJv.targetVersion.Split('.');
            int minorVersion = int.Parse(versionParts[1]);
            int patchVersion = int.Parse(versionParts[2]);
            int newMinorVersion = (patchVersion == 0 ? minorVersion : minorVersion + 1);
            string betaSinceVersion = "V" + versionParts[0] + "_" + newMinorVersion + "_0";
            
            javaFile.Import(
                "com.microsoft.azure.management.apigeneration.Beta",
                "com.microsoft.azure.management.apigeneration.Beta.SinceVersion",
                "com.microsoft.azure.management.resources.fluentcore.arm.AzureConfigurable",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.AzureConfigurableImpl",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.Manager",
                "com.microsoft.azure.v2.AzureEnvironment",
                $"{ClassType.AzureTokenCredentials.Package}.{ClassType.AzureTokenCredentials.Name}",
                "com.microsoft.azure.v2.serializer.AzureJacksonAdapter");

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"Entry point to Azure {manager.ServiceName} resource management.");
            });
            javaFile.Annotation($"Beta(SinceVersion.{betaSinceVersion})");
            javaFile.PublicFinalClass($"{className} extends Manager<{className}, {manager.ServiceClientName + "Impl"}>", classBlock =>
            {
                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Get a Configurable instance that can be used to create {className} with optional configuration.");
                    comment.Return("the instance allowing configurations");
                });
                classBlock.PublicStaticMethod("Configurable configure()", function =>
                {
                    function.Return($"new {className}.ConfigurableImpl()");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} resource management API entry points.");
                    comment.Param(manager.AzureTokenCredentialsParameter.Value.Name, manager.AzureTokenCredentialsParameter.Value.Description);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({manager.AzureTokenCredentialsParameter.Value.Declaration}, String subscriptionId)", function =>
                {
                    function.Line($"final {manager.HttpPipelineParameter.Value.ClientType} {manager.HttpPipelineParameter.Value.Name} = AzureProxy.defaultPipeline({className}.class, {manager.AzureTokenCredentialsParameter.Value.Name});");
                    function.Return($"new {className}({manager.HttpPipelineParameter.Value.Name}, subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} resource management API entry points.");
                    comment.Param(manager.HttpPipelineParameter.Value.Name, manager.HttpPipelineParameter.Value.Description);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({manager.HttpPipelineParameter.Value.ClientType} {manager.HttpPipelineParameter.Value.Name}, String subscriptionId)", function =>
                {
                    function.Return($"new {className}({manager.HttpPipelineParameter.Value.Name}, subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The interface allowing configurations to be set.");
                });
                classBlock.PublicInterface("Configurable extends AzureConfigurable<Configurable>", interfaceBlock =>
                {
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} management API entry points.");
                        comment.Param(manager.AzureTokenCredentialsParameter.Value.Name, manager.AzureTokenCredentialsParameter.Value.Description);
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return($"the interface exposing {manager.ServiceName} management API entry points that work across subscriptions");
                    });
                    interfaceBlock.PublicMethod($"{className} authenticate({manager.AzureTokenCredentialsParameter.Value.Declaration}, String subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The implementation for Configurable interface.");
                });
                classBlock.PrivateStaticFinalClass("ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass =>
                {
                    innerClass.PublicMethod($"{className} authenticate({manager.AzureTokenCredentialsParameter.Value.Declaration}, String subscriptionId)", function =>
                    {
                        function.Return($"{className}.authenticate(build{manager.HttpPipelineParameter.Value.ClientType}({manager.AzureTokenCredentialsParameter.Value.Name}), subscriptionId)");
                    });
                });

                classBlock.PrivateMethod($"private {className}({manager.HttpPipelineParameter.Value.Declaration}, String subscriptionId)", constructor =>
                {
                    constructor.Line("super(");
                    constructor.Indent(() =>
                    {
                        constructor.Line($"{manager.HttpPipelineParameter.Value.Name},");
                        constructor.Line("subscriptionId,");
                        constructor.Line($"new {manager.ServiceClientName}Impl({manager.HttpPipelineParameter.Value.Name}).withSubscriptionId(subscriptionId));");
                    });
                });
            });
        }
    }
}