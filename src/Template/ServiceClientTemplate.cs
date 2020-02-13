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
    public class ServiceClientTemplate : IJavaTemplate<ServiceClient, JavaFile>
    {
        private static ServiceClientTemplate _instance = new ServiceClientTemplate();
        public static ServiceClientTemplate Instance => _instance;

        private ServiceClientTemplate()
        {
        }

        public void Write(ServiceClient serviceClient, JavaFile javaFile)
        {
            var settings = JavaSettings.Instance;
            string serviceClientClassDeclaration = $"{serviceClient.ClassName}";
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                serviceClientClassDeclaration += $" implements {serviceClient.InterfaceName}";
            }

            ISet<string> imports = new HashSet<string>();
            serviceClient.AddImportsTo(imports, true, false, settings);
            javaFile.Import(imports);

            javaFile.JavadocComment(comment =>
            {
                string serviceClientTypeName = settings.IsFluent ? serviceClient.ClassName : serviceClient.InterfaceName;
                comment.Description($"Initializes a new instance of the {serviceClientTypeName} type.");
            });
            javaFile.PublicFinalClass(serviceClientClassDeclaration, classBlock =>
            {
                // Add proxy service member variable
                if (serviceClient.RestAPI != null)
                {
                    classBlock.JavadocComment($"The proxy service used to perform REST calls.");
                    classBlock.PrivateMemberVariable(serviceClient.RestAPI.Name, "service");
                }

                // Add ServiceClient client property variables, getters, and setters
                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties)
                {
                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(serviceClientProperty.Description);
                    });
                    classBlock.PrivateMemberVariable($"{serviceClientProperty.Type} {serviceClientProperty.Name}");

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets {serviceClientProperty.Description}");
                        comment.Return($"the {serviceClientProperty.Name} value.");
                    });
                    classBlock.PublicMethod($"{serviceClientProperty.Type} {serviceClientProperty.GetterName}()", function =>
                    {
                        function.Return($"this.{serviceClientProperty.Name}");
                    });

                    if (!serviceClientProperty.IsReadOnly)
                    {
                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description($"Sets {serviceClientProperty.Description}");
                            comment.Param(serviceClientProperty.Name, $"the {serviceClientProperty.Name} value.");
                        });
                        classBlock.PackagePrivateMethod($"{serviceClient.ClassName} {serviceClientProperty.SetterName}({serviceClientProperty.Type} {serviceClientProperty.Name})", function =>
                        {
                            function.Line($"this.{serviceClientProperty.Name} = {serviceClientProperty.Name};");
                            function.Return("this");
                        });
                    }
                }

                // AutoRestMethod Group Client declarations and getters
                foreach (MethodGroupClient methodGroupClient in serviceClient.MethodGroupClients)
                {
                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"The {methodGroupClient.VariableType} object to access its operations.");
                    });
                    classBlock.PrivateMemberVariable(methodGroupClient.VariableType, methodGroupClient.VariableName);

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets the {methodGroupClient.VariableType} object to access its operations.");
                        comment.Return($"the {methodGroupClient.VariableType} object.");
                    });
                    classBlock.PublicMethod($"{methodGroupClient.VariableType} {methodGroupClient.VariableName}()", function =>
                    {
                        function.Return($"this.{methodGroupClient.VariableName}");
                    });
                }

                // Service Client Constructors
                bool serviceClientUsesCredentials = serviceClient.Constructors.Any(constructor => constructor.Parameters.Contains(serviceClient.ServiceClientCredentialsParameter.Value));
                foreach (Constructor constructor in serviceClient.Constructors)
                {
                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Initializes an instance of {serviceClient.InterfaceName} client.");
                        foreach (ClientMethodParameter parameter in constructor.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                    });

                    classBlock.PublicConstructor($"{serviceClient.ClassName}({string.Join(", ", constructor.Parameters.Select(parameter => parameter.Declaration))})", constructorBlock =>
                    {
                        if (settings.IsAzureOrFluent)
                        {
                            if (constructor.Parameters.SequenceEqual(new[] { serviceClient.ServiceClientCredentialsParameter.Value }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class, {serviceClient.ServiceClientCredentialsParameter.Value.Name}));");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.ServiceClientCredentialsParameter.Value, serviceClient.AzureEnvironmentParameter.Value }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class, {serviceClient.ServiceClientCredentialsParameter.Value.Name}), {serviceClient.AzureEnvironmentParameter.Value.Name});");
                            }
                            else if (!constructor.Parameters.Any())
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class));");
                            } 
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.AzureEnvironmentParameter.Value }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class), {serviceClient.AzureEnvironmentParameter.Value.Name});");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.HttpPipelineParameter.Value }))
                            {
                                constructorBlock.Line($"this({serviceClient.HttpPipelineParameter.Value.Name}, null);");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.HttpPipelineParameter.Value, serviceClient.AzureEnvironmentParameter.Value }))
                            {
                                constructorBlock.Line($"super({serviceClient.HttpPipelineParameter.Value.Name}, {serviceClient.AzureEnvironmentParameter.Value.Name});");

                                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties.Where(p => p.IsReadOnly))
                                {
                                    if (serviceClientProperty.DefaultValueExpression != null)
                                    {
                                        constructorBlock.Line($"this.{serviceClientProperty.Name} = {serviceClientProperty.DefaultValueExpression};");
                                    }
                                }

                                foreach (MethodGroupClient methodGroupClient in serviceClient.MethodGroupClients)
                                {
                                    constructorBlock.Line($"this.{methodGroupClient.VariableName} = new {methodGroupClient.ClassName}(this);");
                                }

                                if (serviceClient.RestAPI != null)
                                {
                                    constructorBlock.Line($"this.service = {ClassType.AzureProxy.Name}.create({serviceClient.RestAPI.Name}.class, httpPipeline);");
                                }
                            }
                        }
                        else
                        {
                            if (!constructor.Parameters.Any())
                            {
                                constructorBlock.Line($"this(new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy(), new CookiePolicy()).build());");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.HttpPipelineParameter.Value }))
                            {
                                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties.Where(p => p.IsReadOnly))
                                {
                                    constructorBlock.Line($"this.httpPipeline = httpPipeline;");
                                    if (serviceClientProperty.DefaultValueExpression != null)
                                    {
                                        constructorBlock.Line($"this.{serviceClientProperty.Name} = {serviceClientProperty.DefaultValueExpression};");
                                    }
                                }

                                foreach (MethodGroupClient methodGroupClient in serviceClient.MethodGroupClients)
                                {
                                    constructorBlock.Line($"this.{methodGroupClient.VariableName} = new {methodGroupClient.ClassName}(this);");
                                }

                                if (serviceClient.RestAPI != null)
                                {
                                    constructorBlock.Line($"this.service = {ClassType.RestProxy.Name}.create({serviceClient.RestAPI.Name}.class, this.httpPipeline);");
                                }
                            }
                        }
                    });
                }

                Templates.ProxyTemplate.Write(serviceClient.RestAPI, classBlock);

                foreach (ClientMethod clientMethod in serviceClient.ClientMethods)
                {
                    Templates.ClientMethodTemplate.Write(clientMethod, classBlock);
                }
            });
        }
    }
}