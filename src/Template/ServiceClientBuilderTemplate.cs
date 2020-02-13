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
    /// Writes a ServiceClient to a JavaFile.
    /// </summary>
    public class ServiceClientBuilderTemplate : IJavaTemplate<ServiceClient, JavaFile>
    {
        private static ServiceClientBuilderTemplate _instance = new ServiceClientBuilderTemplate();
        public static ServiceClientBuilderTemplate Instance => _instance;

        private ServiceClientBuilderTemplate()
        {
        }

        public void Write(ServiceClient serviceClient, JavaFile javaFile)
        {
            var settings = JavaSettings.Instance;
            string serviceClientBuilderName = $"{serviceClient.InterfaceName}Builder";

            var commonProperties = new List<ServiceClientProperty>();
            if (settings.IsAzureOrFluent)
            {
                commonProperties.Add(new ServiceClientProperty("The environment to connect to",
                    ClassType.AzureEnvironment, "environment", false, "${ClassType.AzureEnvironment.Name}.AZURE"));
                commonProperties.Add(new ServiceClientProperty("The HTTP pipeline to send requests through",
                    ClassType.HttpPipeline, "pipeline", false, $"{ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class)"));
            }
            else
            {
                commonProperties.Add(new ServiceClientProperty("The HTTP pipeline to send requests through",
                    ClassType.HttpPipeline, "pipeline", false, $"new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build()"));
            }

            string buildReturnType = serviceClient.ClassName;
            if (!settings.IsFluent && settings.GenerateClientInterfaces) {
                buildReturnType = serviceClient.InterfaceName;
            }

            ISet<string> imports = new HashSet<string>();
            serviceClient.AddImportsTo(imports, false, true, settings);
            imports.Remove("com.azure.core.AzureServiceClient");
            imports.Add("com.azure.core.annotation.ServiceClientBuilder");
            javaFile.Import(imports);

            javaFile.JavadocComment(comment =>
            {
                string serviceClientTypeName = settings.IsFluent ? serviceClient.ClassName : serviceClient.InterfaceName;
                comment.Description($"A builder for creating a new instance of the {serviceClientTypeName} type.");
            });
            javaFile.Annotation($"ServiceClientBuilder(serviceClients = {buildReturnType}.class)");
            javaFile.PublicFinalClass(serviceClientBuilderName, classBlock =>
            {
                // Add ServiceClient client property variables, getters, and setters
                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties.Where(p => !p.IsReadOnly).Concat(commonProperties))
                {
                    classBlock.BlockComment(comment =>
                    {
                        comment.Line(serviceClientProperty.Description);
                    });
                    classBlock.PrivateMemberVariable($"{serviceClientProperty.Type} {serviceClientProperty.Name}");

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Sets {serviceClientProperty.Description}");
                        comment.Param(serviceClientProperty.Name, $"the {serviceClientProperty.Name} value.");
                        comment.Return($"the {serviceClientBuilderName}");
                    });
                    classBlock.PublicMethod($"{serviceClientBuilderName} {serviceClientProperty.Name.ToCamelCase()}({serviceClientProperty.Type} {serviceClientProperty.Name})", function =>
                    {
                        function.Line($"this.{serviceClientProperty.Name} = {serviceClientProperty.Name};");
                        function.Return("this");
                    });
                }

                // build method
                classBlock.JavadocComment(comment => 
                {
                    comment.Description($"Builds an instance of {buildReturnType} with the provided parameters");
                    comment.Return($"an instance of {buildReturnType}");
                });
                classBlock.PublicMethod($"{buildReturnType} build()", function =>
                {
                    function.If($"pipeline == null", ifBlock =>
                    {
                        function.Line($"this.pipeline = new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build();");
                    });
                    if (settings.IsAzureOrFluent)
                    {
                        function.Line($"{serviceClient.ClassName} client = new {serviceClient.ClassName}(pipeline, environment);");
                    }
                    else
                    {
                        function.Line($"{serviceClient.ClassName} client = new {serviceClient.ClassName}(pipeline);");
                    }
                    foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties.Where(p => !p.IsReadOnly))
                    {
                        var ifElseBlock = function.If($"this.{serviceClientProperty.Name} != null", ifBlock =>
                        {
                            ifBlock.Line($"client.set{serviceClientProperty.Name.ToPascalCase()}(this.{serviceClientProperty.Name});");
                        });
                        if (serviceClientProperty.DefaultValueExpression != null)
                        {
                            ifElseBlock.Else(elseBlock => 
                            {
                                elseBlock.Line($"client.set{serviceClientProperty.Name.ToPascalCase()}({serviceClientProperty.DefaultValueExpression});");
                            });
                        }
                    }
                    function.Line("return client;");
                });
            });
        }
    }
}