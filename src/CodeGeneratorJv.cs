// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Model;
using AutoRest.Java.Templates;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;
using AutoRestCodeModel = AutoRest.Core.Model.CodeModel;
using AutoRestCompositeType = AutoRest.Core.Model.CompositeType;
using AutoRestDictionaryType = AutoRest.Core.Model.DictionaryType;
using AutoRestEnumType = AutoRest.Core.Model.EnumType;
using AutoRestEnumValue = AutoRest.Core.Model.EnumValue;
using AutoRestHttpMethod = AutoRest.Core.Model.HttpMethod;
using AutoRestIModelType = AutoRest.Core.Model.IModelType;
using AutoRestIParent = AutoRest.Core.Model.IParent;
using AutoRestIVariable = AutoRest.Core.Model.IVariable;
using AutoRestKnownPrimaryType = AutoRest.Core.Model.KnownPrimaryType;
using AutoRestMethod = AutoRest.Core.Model.Method;
using AutoRestMethodGroup = AutoRest.Core.Model.MethodGroup;
using AutoRestModelType = AutoRest.Core.Model.ModelType;
using AutoRestParameter = AutoRest.Core.Model.Parameter;
using AutoRestParameterLocation = AutoRest.Core.Model.ParameterLocation;
using AutoRestParameterMapping = AutoRest.Core.Model.ParameterMapping;
using AutoRestParameterTransformation = AutoRest.Core.Model.ParameterTransformation;
using AutoRestPrimaryType = AutoRest.Core.Model.PrimaryType;
using AutoRestProperty = AutoRest.Core.Model.Property;
using AutoRestResponse = AutoRest.Core.Model.Response;
using AutoRestSequenceType = AutoRest.Core.Model.SequenceType;

namespace AutoRest.Java
{
    public class CodeGeneratorJv : CodeGenerator
    {
        private const string targetVersion = "1.1.3";
        internal const string pomVersion = targetVersion + "-SNAPSHOT";
        private const string ClientRuntimePackage = "com.microsoft.rest.v2:client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";
        public override string ImplementationFileExtension => ".java";

        /// <summary>
        /// Generate Java client code for given ServiceClient.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override Task Generate(AutoRestCodeModel codeModel)
        {
            var cm = (CodeModelJv) codeModel;

            Service service = ParseService(cm, cm.JavaSettings);

            List<JavaFile> javaFiles = new List<JavaFile>();

            javaFiles.Add(GetServiceClientJavaFile(service.ServiceClient, cm.JavaSettings));

            foreach (MethodGroupClient methodGroupClient in service.ServiceClient.MethodGroupClients)
            {
                javaFiles.Add(GetMethodGroupClientJavaFile(methodGroupClient, cm.JavaSettings));
            }

            foreach (ResponseModel rm in service.ResponseModels)
            {
                javaFiles.Add(GetResponseJavaFile(rm, cm.JavaSettings));
            }

            foreach (ServiceModel model in service.Models)
            {
                javaFiles.Add(GetModelJavaFile(model, cm.JavaSettings));
            }

            foreach (EnumType serviceEnum in service.Enums)
            {
                javaFiles.Add(GetEnumJavaFile(serviceEnum, cm.JavaSettings));
            }

            foreach (XmlSequenceWrapper xmlSequenceWrapper in service.XmlSequenceWrappers)
            {
                javaFiles.Add(GetXmlSequenceWrapperJavaFile(xmlSequenceWrapper, cm.JavaSettings));
            }

            foreach (ServiceException exception in service.Exceptions)
            {
                javaFiles.Add(GetExceptionJavaFile(exception, cm.JavaSettings));
            }


            if (cm.JavaSettings.IsAzureOrFluent)
            {
                foreach (PageDetails pageClass in cm.PageClasses)
                {
                    javaFiles.Add(GetPageJavaFile(pageClass, cm.JavaSettings));
                }
            }

            if (service.Manager != null)
            {
                javaFiles.Add(GetServiceManagerJavaFile(service.Manager, cm.JavaSettings));
            }

            if (!cm.JavaSettings.IsFluent)
            {
                if (cm.JavaSettings.GenerateClientInterfaces)
                {
                    javaFiles.Add(GetServiceClientInterfaceJavaFile(service.ServiceClient, cm.JavaSettings));

                    foreach (MethodGroupClient methodGroupClient in service.ServiceClient.MethodGroupClients)
                    {
                        javaFiles.Add(GetMethodGroupClientInterfaceJavaFile(methodGroupClient, cm.JavaSettings));
                    }
                }
            }
            else
            {
                if (cm.JavaSettings.RegeneratePom)
                {
                    PomTemplate pomTemplate = new PomTemplate { Model = (CodeModelJv) codeModel };
                    StringBuilder pomContentsBuilder = new StringBuilder();
                    using (pomTemplate.TextWriter = new StringWriter(pomContentsBuilder))
                    {
                        pomTemplate.ExecuteAsync().GetAwaiter().GetResult();
                    }
                    javaFiles.Add(new JavaFile("pom.xml", pomContentsBuilder.ToString()));
                }
            }

            string folderPrefix = "src/main/java/" + cm.JavaSettings.Package.Replace('.', '/').Trim('/');
            ISet<string> foldersWithGeneratedFiles = new HashSet<string>(javaFiles.Select((JavaFile javaFile) => Path.GetDirectoryName(javaFile.FilePath)));
            foreach (string folderWithGeneratedFiles in foldersWithGeneratedFiles)
            {
                string subpackage = folderWithGeneratedFiles
                    .Substring(folderPrefix.Length)
                    .Replace('/', '.')
                    .Replace('\\', '.')
                    .Trim('.');
                javaFiles.Add(GetPackageInfoJavaFiles(service, subpackage, cm.JavaSettings));
            }

            return Task.WhenAll(javaFiles.Select(javaFile => Write(javaFile.Contents.ToString(), javaFile.FilePath)));
        }

        private static Service ParseService(CodeModelJv codeModel, JavaSettings settings)
        {

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = codeModel.GenerateServiceClient();

            List<EnumType> enumTypes = new List<EnumType>();
            foreach (EnumTypeJv autoRestEnumType in codeModel.EnumTypes)
            {
                IType type = autoRestEnumType?.GenerateType(settings);
                if (type is EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ServiceException> exceptions = codeModel.ErrorTypes
                .Cast<CompositeTypeJv>()
                .Select(t => t.GenerateException(settings))
                .Where(t => t != null);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel, settings);

            #region Parse Models
            IEnumerable<CompositeTypeJv> autoRestModelTypes = codeModel.ModelTypes
                .Union(codeModel.HeaderTypes)
                .Cast<CompositeTypeJv>()
                .Where((CompositeTypeJv autoRestModelType) => autoRestModelType.ShouldGenerateModel);

            IEnumerable<ServiceModel> models = autoRestModelTypes
                .Select((CompositeTypeJv autoRestCompositeType) => autoRestCompositeType.GenerateModel(settings))
                .ToArray();

            IEnumerable<ResponseModel> responseModels = codeModel.Methods
                .Where(m => m.ReturnType.Headers != null)
                .Select(m => ParseResponse(m, settings))
                .ToList();

            #endregion

            ServiceManager manager = codeModel.GenerateManager();

            return new Service(serviceClientName, serviceClientDescription, enumTypes, exceptions, xmlSequenceWrappers, responseModels, models, manager, serviceClient);
        }

        private static ResponseModel ParseResponse(AutoRestMethod method, JavaSettings settings)
        {
            string name = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
            string package = settings.Package + "." + settings.ModelsSubpackage;
            string description = $"Contains all response data for the {method.Name} operation.";
            IType headersType = ((IModelTypeJv)method.ReturnType.Headers)?.GenerateType(settings).AsNullable();
            IType bodyType = ((IModelTypeJv)method.ReturnType.Body)?.GenerateType(settings).AsNullable();
            return new ResponseModel(name, package, description, headersType, bodyType);
        }

        private static IEnumerable<XmlSequenceWrapper> ParseXmlSequenceWrappers(AutoRestCodeModel codeModel, JavaSettings settings)
        {
            List<XmlSequenceWrapper> xmlSequenceWrappers = new List<XmlSequenceWrapper>();
            if (codeModel.ShouldGenerateXmlSerialization)
            {
                // Every sequence type used as a parameter to a service method.
                IEnumerable<AutoRestMethod> allMethods = codeModel.Methods.Concat(codeModel.Operations.SelectMany(methodGroup => methodGroup.Methods));
                IEnumerable<AutoRestParameter> allParameters = allMethods.SelectMany(method => method.Parameters);

                foreach (AutoRestParameter parameter in allParameters)
                {
                    IType parameterType = ((IModelTypeJv)parameter.ModelType)?.GenerateType(settings);

                    if (parameterType is ListType parameterListType && parameter.ModelType is SequenceTypeJv sequenceType)
                    {
                        string xmlRootElementName = sequenceType.XmlName;
                        string xmlListElementName = sequenceType.ElementType.XmlProperties?.Name ?? sequenceType.ElementXmlName;
                        if (!xmlSequenceWrappers.Any(existingWrapper => existingWrapper.XmlRootElementName == xmlRootElementName && existingWrapper.XmlListElementName == xmlListElementName))
                        {
                            HashSet<string> xmlSequenceWrapperImports = new HashSet<string>()
                            {
                                "com.fasterxml.jackson.annotation.JsonCreator",
                                "com.fasterxml.jackson.annotation.JsonProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement"
                            };
                            parameterListType.AddImportsTo(xmlSequenceWrapperImports, true);

                            xmlSequenceWrappers.Add(new XmlSequenceWrapper(parameterListType, xmlRootElementName, xmlListElementName, xmlSequenceWrapperImports));
                        }
                    }
                }
            }
            return xmlSequenceWrappers;
        }

        private static JavaFile GetServiceManagerJavaFile(ServiceManager manager, JavaSettings settings)
        {
            string className = $"{manager.ServiceName}Manager";

            string[] versionParts = targetVersion.Split('.');
            int minorVersion = int.Parse(versionParts[1]);
            int patchVersion = int.Parse(versionParts[2]);
            int newMinorVersion = (patchVersion == 0 ? minorVersion : minorVersion + 1);
            string betaSinceVersion = "V" + versionParts[0] + "_" + newMinorVersion + "_0";

            string subpackage = settings.ImplementationSubpackage;
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(subpackage, settings, className);

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

            return javaFile;
        }

        public static JavaFile GetPageJavaFile(PageDetails pageClass, JavaSettings settings)
        {
            string subPackage = settings.IsFluent ? settings.ImplementationSubpackage : settings.ModelsSubpackage;
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(subPackage, settings, pageClass.ClassName);
            javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty",
                            "com.microsoft.azure.v2.Page",
                            "java.util.List");

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                comment.Param("<T>", "type of Azure resource");
            });
            javaFile.PublicFinalClass($"{pageClass.ClassName}<T> implements Page<T>", classBlock =>
            {
                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The link to the next page.");
                });
                classBlock.Annotation($"JsonProperty(\"{pageClass.NextLinkName}\")");
                classBlock.PrivateMemberVariable("String", "nextPageLink");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The list of items.");
                });
                classBlock.Annotation($"JsonProperty(\"{pageClass.ItemName}\")");
                classBlock.PrivateMemberVariable("List<T>", "items");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Gets the link to the next page.");
                    comment.Return("the link to the next page.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("String nextPageLink()", function =>
                {
                    function.Return("this.nextPageLink");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Gets the list of items.");
                    comment.Return("the list of items in {@link List}.");
                });
                classBlock.Annotation("Override");
                classBlock.PublicMethod("List<T> items()", function =>
                {
                    function.Return("items");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Sets the link to the next page.");
                    comment.Param("nextPageLink", "the link to the next page.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod($"{pageClass.ClassName}<T> setNextPageLink(String nextPageLink)", function =>
                {
                    function.Line("this.nextPageLink = nextPageLink;");
                    function.Return("this");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("Sets the list of items.");
                    comment.Param("items", "the list of items in {@link List}.");
                    comment.Return("this Page object itself.");
                });
                classBlock.PublicMethod($"{pageClass.ClassName}<T> setItems(List<T> items)", function =>
                {
                    function.Line("this.items = items;");
                    function.Return("this");
                });
            });

            return javaFile;
        }

        public static JavaFile GetXmlSequenceWrapperJavaFile(XmlSequenceWrapper xmlSequenceWrapper, JavaSettings settings)
        {
            string xmlRootElementName = xmlSequenceWrapper.XmlRootElementName;
            string xmlListElementName = xmlSequenceWrapper.XmlListElementName;

            string xmlElementNameCamelCase = xmlRootElementName.ToCamelCase();

            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(settings.ImplementationSubpackage, settings, xmlSequenceWrapper.WrapperClassName);

            ListType sequenceType = xmlSequenceWrapper.SequenceType;

            javaFile.Import(xmlSequenceWrapper.Imports);

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"A wrapper around {sequenceType} which provides top-level metadata for serialization.");
            });
            javaFile.Annotation($"JacksonXmlRootElement(localName = \"{xmlRootElementName}\")");
            javaFile.PublicFinalClass(xmlSequenceWrapper.WrapperClassName, classBlock =>
            {
                classBlock.Annotation($"JacksonXmlProperty(localName = \"{xmlListElementName}\")");
                classBlock.PrivateFinalMemberVariable(sequenceType.ToString(), xmlElementNameCamelCase);

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {xmlSequenceWrapper.WrapperClassName}.");
                    comment.Param(xmlElementNameCamelCase, "the list");
                });
                classBlock.Annotation("JsonCreator");
                classBlock.PublicConstructor($"{xmlSequenceWrapper.WrapperClassName}(@JsonProperty(\"{xmlListElementName}\") {sequenceType} {xmlElementNameCamelCase})", constructor =>
                {
                    constructor.Line($"this.{xmlElementNameCamelCase} = {xmlElementNameCamelCase};");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Get the {sequenceType} contained in this wrapper.");
                    comment.Return($"the {sequenceType}");
                });
                classBlock.PublicMethod($"{sequenceType} items()", function =>
                {
                    function.Return(xmlElementNameCamelCase);
                });
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientJavaFile(ServiceClient serviceClient, JavaSettings settings)
        {
            string subPackage = settings.GenerateClientInterfaces ? settings.ImplementationSubpackage : null;
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(subPackage, settings, serviceClient.ClassName);

            string serviceClientClassDeclaration = $"{serviceClient.ClassName} extends ";
            if (settings.IsAzureOrFluent)
            {
                serviceClientClassDeclaration += "Azure";
            }
            serviceClientClassDeclaration += "ServiceClient";
            if (!settings.IsFluent && settings.GenerateClientInterfaces)
            {
                serviceClientClassDeclaration += $" implements {serviceClient.InterfaceName}";
            }

            ISet<string> imports = new HashSet<string>();
            serviceClient.AddImportsTo(imports, true, settings);
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
                    classBlock.PublicMethod($"{serviceClientProperty.Type} {serviceClientProperty.Name}()", function =>
                    {
                        function.Return($"this.{serviceClientProperty.Name}");
                    });

                    if (!serviceClientProperty.IsReadOnly)
                    {
                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description($"Sets {serviceClientProperty.Description}");
                            comment.Param(serviceClientProperty.Name, $"the {serviceClientProperty.Name} value.");
                            comment.Return("the service client itself");
                        });
                        classBlock.PublicMethod($"{serviceClient.ClassName} with{serviceClientProperty.Name.ToPascalCase()}({serviceClientProperty.Type} {serviceClientProperty.Name})", function =>
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
                        foreach (MethodParameter parameter in constructor.Parameters)
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
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class, {serviceClient.AzureEnvironmentParameter.Value.Name}), {serviceClient.AzureEnvironmentParameter.Value.Name});");
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

                                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties)
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
                                    constructorBlock.Line($"this.service = {ClassType.AzureProxy.Name}.create({serviceClient.RestAPI.Name}.class, this);");
                                }
                            }
                        }
                        else
                        {
                            if (!constructor.Parameters.Any())
                            {
                                constructorBlock.Line($"this({ClassType.RestProxy.Name}.createDefaultPipeline());");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClient.HttpPipelineParameter.Value }))
                            {
                                constructorBlock.Line($"super({serviceClient.HttpPipelineParameter.Value.Name});");

                                foreach (ServiceClientProperty serviceClientProperty in serviceClient.Properties)
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
                                    constructorBlock.Line($"this.service = {ClassType.RestProxy.Name}.create({serviceClient.RestAPI.Name}.class, this);");
                                }
                            }
                        }
                    });
                }

                AddRestAPIInterface(classBlock, serviceClient.RestAPI, serviceClient.InterfaceName, settings);

                AddClientMethods(classBlock, serviceClient.ClientMethods, settings);
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientInterfaceJavaFile(ServiceClient serviceClient, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(null, settings, serviceClient.InterfaceName);

            HashSet<string> imports = new HashSet<string>();
            serviceClient.AddImportsTo(imports, false, settings);
            javaFile.Import(imports);

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"The interface for {serviceClient.InterfaceName} class.");
            });
            javaFile.PublicInterface(serviceClient.InterfaceName, interfaceBlock =>
            {
                foreach (ServiceClientProperty property in serviceClient.Properties)
                {
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets {property.Description}");
                        comment.Return($"the {property.Name} value");
                    });
                    interfaceBlock.PublicMethod($"{property.Type} {property.Name}()");

                    if (!property.IsReadOnly)
                    {
                        interfaceBlock.JavadocComment(comment =>
                        {
                            comment.Description($"Sets {property.Description}");
                            comment.Param(property.Name, $"the {property.Name} value");
                            comment.Return("the service client itself");
                        });
                        interfaceBlock.PublicMethod($"{serviceClient.InterfaceName} with{property.Name.ToPascalCase()}({property.Type} {property.Name})");
                    }
                }

                foreach (MethodGroupClient methodGroupClient in serviceClient.MethodGroupClients)
                {
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets the {methodGroupClient.InterfaceName} object to access its operations.");
                        comment.Return($"the {methodGroupClient.InterfaceName} object.");
                    });
                    interfaceBlock.PublicMethod($"{methodGroupClient.InterfaceName} {methodGroupClient.VariableName}()");
                }

                AddClientMethods(interfaceBlock, serviceClient.ClientMethods, settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientJavaFile(MethodGroupClient methodGroupClient, JavaSettings settings)
        {
            string subPackage = settings.GenerateClientInterfaces ? settings.ImplementationSubpackage : null;
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(subPackage, settings, methodGroupClient.ClassName);

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
                classBlock.PrivateMemberVariable(methodGroupClient.RestAPI.Name, "service");

                classBlock.JavadocComment("The service client containing this operation class.");
                classBlock.PrivateMemberVariable(methodGroupClient.ServiceClientName, "client");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Initializes an instance of {methodGroupClient.ClassName}.");
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.PublicConstructor($"{methodGroupClient.ClassName}({methodGroupClient.ServiceClientName} client)", constructor =>
                {
                    if (methodGroupClient.RestAPI != null)
                    {
                        ClassType proxyType = (settings.IsAzureOrFluent ? ClassType.AzureProxy : ClassType.RestProxy);
                        constructor.Line($"this.service = {proxyType.Name}.create({methodGroupClient.RestAPI.Name}.class, client);");
                    }
                    constructor.Line("this.client = client;");
                });

                AddRestAPIInterface(classBlock, methodGroupClient.RestAPI, methodGroupClient.InterfaceName, settings);

                AddClientMethods(classBlock, methodGroupClient.ClientMethods, settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientInterfaceJavaFile(MethodGroupClient methodGroupClient, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(null, settings, methodGroupClient.InterfaceName);

            HashSet<string> imports = new HashSet<string>();
            methodGroupClient.AddImportsTo(imports, false, settings);
            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClient.InterfaceName}.");
            });
            javaFile.PublicInterface(methodGroupClient.InterfaceName, interfaceBlock =>
            {
                AddClientMethods(interfaceBlock, methodGroupClient.ClientMethods, settings);
            });
            return javaFile;
        }

        public static JavaFile GetPackageInfoJavaFiles(Service service, string subPackage, JavaSettings settings)
        {
            string title = service.ClientName;
            string description = service.ClientDescription;

            string package = GetPackage(settings, subPackage);
            JavaFile javaFile = GetJavaFile(package, "package-info");

            if (!string.IsNullOrEmpty(settings.FileHeaderText))
            {
                javaFile.LineComment(settings.MaximumJavadocCommentWidth, (comment) =>
                {
                    comment.Line(settings.FileHeaderText);
                });
                javaFile.Line();
            }

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                if (string.IsNullOrEmpty(subPackage))
                {
                    comment.Description($"This package contains the classes for {title}.");
                }
                else
                {
                    comment.Description($"This package contains the {subPackage} classes for {title}.");
                }

                if (!string.IsNullOrEmpty(description))
                {
                    comment.Description(description);
                }
            });

            javaFile.Package(package);

            return javaFile;
        }

        public static JavaFile GetResponseJavaFile(ResponseModel response, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(response.Package, settings, response.Name);
            ISet<string> imports = new HashSet<string> { "java.util.Map", "com.microsoft.rest.v2.http.HttpRequest" };
            IType restResponseType = GenericType.RestResponse(response.HeadersType, response.BodyType);
            restResponseType.AddImportsTo(imports, includeImplementationImports: true);

            bool isStreamResponse = response.BodyType.Equals(GenericType.FlowableByteBuffer);
            if (isStreamResponse)
            {
                imports.Add("java.io.Closeable");
                imports.Add("io.reactivex.internal.functions.Functions");
            }

            javaFile.Import(imports);

            string classSignature = isStreamResponse
                ? $"{response.Name} extends {restResponseType} implements Closeable"
                : $"{response.Name} extends {restResponseType}";

            javaFile.JavadocComment(javadoc =>
            {
                javadoc.Description(response.Description);
            });

            javaFile.PublicFinalClass(classSignature, classBlock =>
            {
                classBlock.JavadocComment(javadoc =>
                {
                    javadoc.Description($"Creates an instance of {response.Name}.");
                    javadoc.Param("statusCode", "the status code of the HTTP response");
                    javadoc.Param("headers", "the deserialized headers of the HTTP response");
                    javadoc.Param("rawHeaders", "the raw headers of the HTTP response");
                    javadoc.Param("body", isStreamResponse ? "the body content stream" : "the deserialized body of the HTTP response");
                });
                classBlock.PublicConstructor(
                    $"{response.Name}(HttpRequest request, int statusCode, {response.HeadersType} headers, Map<String, String> rawHeaders, {response.BodyType} body)",
                    ctorBlock => ctorBlock.Line("super(request, statusCode, headers, rawHeaders, body);"));

                if (!response.HeadersType.Equals(ClassType.Void))
                {
                    classBlock.JavadocComment(javadoc => javadoc.Return("the deserialized response headers"));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod($"{response.HeadersType} headers()", methodBlock => methodBlock.Return("super.headers()"));
                }

                if (!response.BodyType.Equals(ClassType.Void))
                {
                    if (response.BodyType.Equals(GenericType.FlowableByteBuffer))
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the response content stream"));
                    }
                    else
                    {
                        classBlock.JavadocComment(javadoc => javadoc.Return("the deserialized response body"));
                    }


                    classBlock.Annotation("Override");
                    classBlock.PublicMethod($"{response.BodyType} body()", methodBlock => methodBlock.Return("super.body()"));
                }

                if (isStreamResponse)
                {
                    classBlock.JavadocComment(javadoc => javadoc.Description("Disposes of the connection associated with this stream response."));
                    classBlock.Annotation("Override");
                    classBlock.PublicMethod("void close()",
                        methodBlock => methodBlock.Line("body().subscribe(Functions.emptyConsumer(), Functions.<Throwable>emptyConsumer()).dispose();"));
                }
            });
            return javaFile;
        }

        public static JavaFile GetModelJavaFile(ServiceModel model, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(model.Package, settings, model.Name);

            ISet<string> imports = new HashSet<string>();
            model.AddImportsTo(imports, settings);

            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description(model.Description);
            });

            bool hasDerivedModels = model.DerivedModels.Any();
            if (model.IsPolymorphic)
            {
                javaFile.Annotation($"JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"{model.PolymorphicDiscriminator}\"{(hasDerivedModels ? $", defaultImpl = {model.Name}.class" : "")})");
                javaFile.Annotation($"JsonTypeName(\"{model.SerializedName}\")");

                if (hasDerivedModels)
                {
                    javaFile.Line("@JsonSubTypes({");
                    javaFile.Indent(() =>
                    {
                        Func<ServiceModel, string> getDerivedTypeAnnotation = (ServiceModel derivedType)
                            => $"@JsonSubTypes.Type(name = \"{derivedType.SerializedName}\", value = {derivedType.Name}.class)";

                        foreach (ServiceModel derivedModel in model.DerivedModels.SkipLast(1))
                        {
                            javaFile.Line(getDerivedTypeAnnotation(derivedModel) + ',');
                        }
                        javaFile.Line(getDerivedTypeAnnotation(model.DerivedModels.Last()));
                    });
                    javaFile.Line("})");
                }
            }

            if (settings.ShouldGenerateXmlSerialization)
            {
                javaFile.Annotation($"JacksonXmlRootElement(localName = \"{model.XmlName}\")");
            }

            if (model.NeedsFlatten)
            {
                javaFile.Annotation("JsonFlatten");
            }

            List<JavaModifier> classModifiers = new List<JavaModifier>();
            if (!hasDerivedModels && !model.NeedsFlatten)
            {
                classModifiers.Add(JavaModifier.Final);
            }

            string classNameWithBaseType = model.Name;
            if (model.ParentModel != null)
            {
                classNameWithBaseType += $" extends {model.ParentModel.Name}";
            }
            javaFile.PublicClass(classModifiers, classNameWithBaseType, (classBlock) =>
            {
                string propertyXmlWrapperClassName(ServiceModelProperty property) => property.XmlName + "Wrapper";

                foreach (ServiceModelProperty property in model.Properties)
                {
                    string xmlWrapperClassName = propertyXmlWrapperClassName(property);
                    if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper)
                    {
                        classBlock.PrivateStaticFinalClass(xmlWrapperClassName, innerClass =>
                        {
                            IType propertyClientType = property.WireType.ClientType;

                            innerClass.Annotation($"JacksonXmlProperty(localName = \"{property.XmlListElementName}\")");
                            innerClass.PrivateFinalMemberVariable(propertyClientType.ToString(), "items");

                            innerClass.Annotation("JsonCreator");
                            innerClass.PrivateConstructor(
                                $"{xmlWrapperClassName}(@JacksonXmlProperty(localName = \"{property.XmlListElementName}\") {propertyClientType} items)",
                                constructor => constructor.Line("this.items = items;"));
                        });
                    }

                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description(property.Description);
                    });

                    if (!string.IsNullOrEmpty(property.HeaderCollectionPrefix))
                    {
                        classBlock.Annotation("HeaderCollection(\"" + property.HeaderCollectionPrefix + "\")");
                    }
                    else if (settings.ShouldGenerateXmlSerialization && property.IsXmlAttribute)
                    {
                        string localName = settings.ShouldGenerateXmlSerialization ? property.XmlName : property.SerializedName;
                        classBlock.Annotation($"JacksonXmlProperty(localName = \"{localName}\", isAttribute = true)");
                    }
                    else if (settings.ShouldGenerateXmlSerialization && property.WireType is ListType && !property.IsXmlWrapper)
                    {
                        classBlock.Annotation($"JsonProperty(\"{property.XmlListElementName}\")");
                    }
                    else if (!string.IsNullOrEmpty(property.AnnotationArguments))
                    {
                        classBlock.Annotation($"JsonProperty({property.AnnotationArguments})");
                    }

                    if (settings.ShouldGenerateXmlSerialization)
                    {
                        if (property.IsXmlWrapper)
                        {
                            classBlock.PrivateMemberVariable($"{xmlWrapperClassName} {property.Name}");
                        }
                        else if (property.WireType is ListType listType)
                        {
                            classBlock.PrivateMemberVariable($"{property.WireType} {property.Name} = new ArrayList<>()");
                        }
                        else
                        {
                            classBlock.PrivateMemberVariable($"{property.WireType} {property.Name}");
                        }
                    }
                    else
                    {
                        classBlock.PrivateMemberVariable($"{property.WireType} {property.Name}");
                    }
                }

                IEnumerable<ServiceModelProperty> constantProperties = model.Properties.Where(property => property.IsConstant);
                if (constantProperties.Any())
                {
                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Creates an instance of {model.Name} class.");
                    });
                    classBlock.PublicConstructor($"{model.Name}()", (constructor) =>
                    {
                        foreach (ServiceModelProperty constantProperty in constantProperties)
                        {
                            constructor.Line($"{constantProperty.Name} = {constantProperty.DefaultValue};");
                        }
                    });
                }

                foreach (ServiceModelProperty property in model.Properties)
                {
                    IType propertyType = property.WireType;
                    IType propertyClientType = propertyType.ClientType;

                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Get the {property.Name} value.");
                        comment.Return($"the {property.Name} value");
                    });
                    classBlock.PublicMethod($"{propertyClientType} {property.Name}()", (methodBlock) =>
                    {
                        string sourceTypeName = propertyType.ToString();
                        string targetTypeName = propertyClientType.ToString();
                        string expression = $"this.{property.Name}";
                        if (sourceTypeName == targetTypeName)
                        {
                            if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper && property.WireType is ListType listType)
                            {
                                methodBlock.If($"this.{property.Name} == null", ifBlock =>
                                {
                                    ifBlock.Line($"this.{property.Name} = new {propertyXmlWrapperClassName(property)}(new ArrayList<{listType.ElementType}>());");
                                });
                                methodBlock.Return($"this.{property.Name}.items");
                            }
                            else
                            {
                                methodBlock.Return($"this.{property.Name}");
                            }
                        }
                        else
                        {
                            methodBlock.If($"{expression} == null", (ifBlock) =>
                            {
                                ifBlock.Return("null");
                            });

                            string propertyConversion = propertyType.ConvertToClientType(expression);

                            methodBlock.Return(propertyConversion);
                        }
                    });

                    if (!property.IsReadOnly)
                    {
                        classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                        {
                            comment.Description($"Set the {property.Name} value.");
                            comment.Param(property.Name, $"the {property.Name} value to set");
                            comment.Return($"the {model.Name} object itself.");
                        });
                        classBlock.PublicMethod($"{model.Name} with{property.Name.ToPascalCase()}({propertyClientType} {property.Name})", (methodBlock) =>
                        {
                            if (propertyClientType != propertyType)
                            {
                                methodBlock.If($"{property.Name} == null", (ifBlock) =>
                                {
                                    ifBlock.Line($"this.{property.Name} = null;");
                                })
                                .Else((elseBlock) =>
                                {
                                    string sourceTypeName = propertyClientType.ToString();
                                    string targetTypeName = propertyType.ToString();
                                    string expression = property.Name;
                                    string propertyConversion = propertyType.ConvertFromClientType(expression);
                                    elseBlock.Line($"this.{property.Name} = {propertyConversion};");
                                });
                            }
                            else
                            {
                                if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper)
                                {
                                    methodBlock.Line($"this.{property.Name} = new {propertyXmlWrapperClassName(property)}({property.Name});");
                                }
                                else
                                {
                                    methodBlock.Line($"this.{property.Name} = {property.Name};");
                                }
                            }
                            methodBlock.Return("this");
                        });
                    }
                }
            });

            return javaFile;
        }

        public static JavaFile GetExceptionJavaFile(ServiceException exception, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(exception.Subpackage, settings, exception.Name);

            javaFile.Import("com.microsoft.rest.v2.RestException",
                            "com.microsoft.rest.v2.http.HttpResponse");
            javaFile.JavadocComment((comment) =>
            {
                comment.Description($"Exception thrown for an invalid response with {exception.ErrorName} information.");
            });
            javaFile.PublicFinalClass($"{exception.Name} extends RestException", (classBlock) =>
            {
                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                });
                classBlock.PublicConstructor($"{exception.Name}(String message, HttpResponse response)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response);");
                });

                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                    comment.Param("body", "the deserialized response body");
                });
                classBlock.PublicConstructor($"{exception.Name}(String message, HttpResponse response, {exception.ErrorName} body)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response, body);");
                });

                classBlock.Annotation("Override");
                classBlock.PublicMethod($"{exception.ErrorName} body()", (methodBlock) =>
                {
                    methodBlock.Return($"({exception.ErrorName}) super.body()");
                });
            });

            return javaFile;
        }

        public static JavaFile GetEnumJavaFile(EnumType serviceEnum, JavaSettings settings)
        {
            string enumTypeComment = $"Defines values for {serviceEnum.Name}.";

            string subpackage = settings.IsFluent ? null : settings.ModelsSubpackage;
            JavaFile javaFile = GetJavaFileWithHeaderAndSubPackage(subpackage, settings, serviceEnum.Name);
            if (serviceEnum.Expandable)
            {
                javaFile.Import("java.util.Collection",
                                "com.fasterxml.jackson.annotation.JsonCreator",
                                "com.microsoft.rest.v2.ExpandableStringEnum");
                javaFile.JavadocComment(comment =>
                {
                    comment.Description(enumTypeComment);
                });
                javaFile.PublicFinalClass($"{serviceEnum.Name} extends ExpandableStringEnum<{serviceEnum.Name}>", (classBlock) =>
                {
                    foreach (ServiceEnumValue enumValue in serviceEnum.Values)
                    {
                        classBlock.JavadocComment($"Static value {enumValue.Value} for {serviceEnum.Name}.");
                        classBlock.PublicStaticFinalVariable($"{serviceEnum.Name} {enumValue.Name} = fromString(\"{enumValue.Value}\")");
                    }

                    classBlock.JavadocComment((comment) =>
                    {
                        comment.Description($"Creates or finds a {serviceEnum.Name} from its string representation.");
                        comment.Param("name", "a name to look for");
                        comment.Return($"the corresponding {serviceEnum.Name}");
                    });
                    classBlock.Annotation("JsonCreator");
                    classBlock.PublicStaticMethod($"{serviceEnum.Name} fromString(String name)", (function) =>
                    {
                        function.Return($"fromString(name, {serviceEnum.Name}.class)");
                    });

                    classBlock.JavadocComment((comment) =>
                    {
                        comment.Return($"known {serviceEnum.Name} values");
                    });
                    classBlock.PublicStaticMethod($"Collection<{serviceEnum.Name}> values()", (function) =>
                    {
                        function.Return($"values({serviceEnum.Name}.class)");
                    });
                });
            }
            else
            {
                javaFile.Import("com.fasterxml.jackson.annotation.JsonCreator",
                                "com.fasterxml.jackson.annotation.JsonValue");
                javaFile.JavadocComment(comment =>
                {
                    comment.Description(enumTypeComment);
                });
                javaFile.PublicEnum(serviceEnum.Name, enumBlock =>
                {
                    foreach (ServiceEnumValue value in serviceEnum.Values)
                    {
                        enumBlock.Value(value.Name, value.Value);
                    }

                    enumBlock.JavadocComment($"The actual serialized value for a {serviceEnum.Name} instance.");
                    enumBlock.PrivateFinalMemberVariable("String", "value");

                    enumBlock.PrivateConstructor($"{serviceEnum.Name}(String value)", (constructor) =>
                    {
                        constructor.Line("this.value = value;");
                    });

                    enumBlock.JavadocComment((comment) =>
                    {
                        comment.Description($"Parses a serialized value to a {serviceEnum.Name} instance.");
                        comment.Param("value", "the serialized value to parse.");
                        comment.Return($"the parsed {serviceEnum.Name} object, or null if unable to parse.");
                    });
                    enumBlock.Annotation("JsonCreator");
                    enumBlock.PublicStaticMethod($"{serviceEnum.Name} fromString(String value)", (function) =>
                    {
                        function.Line($"{serviceEnum.Name}[] items = {serviceEnum.Name}.values();");
                        function.Block($"for ({serviceEnum.Name} item : items)", (foreachBlock) =>
                        {
                            foreachBlock.If("item.toString().equalsIgnoreCase(value)", (ifBlock) =>
                            {
                                ifBlock.Return("item");
                            });
                        });
                        function.Return("null");
                    });

                    enumBlock.Annotation("JsonValue",
                                         "Override");
                    enumBlock.PublicMethod("String toString()", (function) =>
                    {
                        function.Return("this.value");
                    });
                });
            }

            return javaFile;
        }

        // TODO: Move it somewhere
        internal static string GetPackage(JavaSettings settings, params string[] packageSuffixes)
        {
            string package = settings.Package;
            if (packageSuffixes != null)
            {
                foreach (string packageSuffix in packageSuffixes)
                {
                    if (!string.IsNullOrEmpty(packageSuffix))
                    {
                        package += "." + packageSuffix.Trim('.');
                    }
                }
            }
            return package;
        }

        private static JavaFile GetJavaFile(string package, string fileNameWithoutExtension)
        {
            string folderPath = Path.Combine("src", "main", "java", package.Replace('.', Path.DirectorySeparatorChar));
            string filePath = Path.Combine(folderPath, $"{fileNameWithoutExtension}.java")
                .Replace('\\', '/')
                .Replace("//", "/");
            return new JavaFile(filePath);
        }

        private static JavaFile GetJavaFileWithHeaderAndSubPackage(string subPackage, JavaSettings settings, string fileNameWithoutExtension)
        {
            string package = GetPackage(settings, subPackage);
            return GetJavaFileWithHeaderAndPackage(package, settings, fileNameWithoutExtension);
        }

        private static JavaFile GetJavaFileWithHeaderAndPackage(string package, JavaSettings settings, string fileNameWithoutExtension)
        {
            JavaFile javaFile = GetJavaFile(package, fileNameWithoutExtension);

            string headerComment = settings.FileHeaderText;
            if (!string.IsNullOrEmpty(headerComment))
            {
                javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                {
                    comment.Description(headerComment);
                });
                javaFile.Line();
            }

            javaFile.Package(package);
            javaFile.Line();

            return javaFile;
        }

        private static void AddRestAPIInterface(JavaClass classBlock, RestAPI restAPI, string clientTypeName, JavaSettings settings)
        {
            if (restAPI != null)
            {
                classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
                {
                    comment.Description($"The interface defining all the services for {clientTypeName} to be used by the proxy service to perform REST calls.");
                });
                classBlock.Annotation($"Host(\"{restAPI.BaseURL}\")");
                classBlock.Interface(JavaVisibility.Private, restAPI.Name, interfaceBlock =>
                {
                    foreach (RestAPIMethod restAPIMethod in restAPI.Methods)
                    {
                        if (restAPIMethod.RequestContentType == "multipart/form-data" || restAPIMethod.RequestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.LineComment($"@Multipart not supported by {ClassType.RestProxy.Name}");
                        }

                        if (restAPIMethod.IsPagingNextOperation)
                        {
                            interfaceBlock.Annotation("GET(\"{nextUrl}\")");
                        }
                        else
                        {
                            interfaceBlock.Annotation($"{restAPIMethod.HttpMethod.ToString().ToUpperInvariant()}(\"{restAPIMethod.UrlPath}\")");
                        }

                        if (restAPIMethod.ResponseExpectedStatusCodes.Any())
                        {
                            interfaceBlock.Annotation($"ExpectedResponses({{{string.Join(", ", restAPIMethod.ResponseExpectedStatusCodes.Select(statusCode => statusCode.ToString("D")))}}})");
                        }

                        if (restAPIMethod.ReturnValueWireType != null)
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({restAPIMethod.ReturnValueWireType}.class)");
                        }

                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({restAPIMethod.UnexpectedResponseExceptionType}.class)");
                        }

                        List<string> parameterDeclarationList = new List<string>();
                        if (restAPIMethod.IsResumable)
                        {
                            interfaceBlock.Annotation($"ResumeOperation");
                        }

                        foreach (RestAPIParameter parameter in restAPIMethod.Parameters)
                        {
                            StringBuilder parameterDeclarationBuilder = new StringBuilder();

                            switch (parameter.RequestParameterLocation)
                            {
                                case RequestParameterLocation.Host:
                                case RequestParameterLocation.Path:
                                case RequestParameterLocation.Query:
                                case RequestParameterLocation.Header:
                                    parameterDeclarationBuilder.Append($"@{parameter.RequestParameterLocation}Param(");
                                    if ((parameter.RequestParameterLocation == RequestParameterLocation.Path || parameter.RequestParameterLocation == RequestParameterLocation.Query) && settings.IsAzureOrFluent && parameter.AlreadyEncoded)
                                    {
                                        parameterDeclarationBuilder.Append($"value = \"{parameter.RequestParameterName}\", encoded = true");
                                    }
                                    else if (parameter.RequestParameterLocation == RequestParameterLocation.Header && !string.IsNullOrEmpty(parameter.HeaderCollectionPrefix))
                                    {
                                        parameterDeclarationBuilder.Append($"\"{parameter.HeaderCollectionPrefix}\"");
                                    }
                                    else
                                    {
                                        parameterDeclarationBuilder.Append($"\"{parameter.RequestParameterName}\"");
                                    }
                                    parameterDeclarationBuilder.Append(") ");

                                    break;

                                case RequestParameterLocation.Body:
                                    parameterDeclarationBuilder.Append($"@BodyParam(\"{restAPIMethod.RequestContentType}\") ");
                                    break;

                                case RequestParameterLocation.FormData:
                                    parameterDeclarationBuilder.Append($"/* @Part(\"{parameter.RequestParameterName}\") not supported by RestProxy */");
                                    break;

                                default:
                                    if (!restAPIMethod.IsResumable && parameter.Type != ClassType.Context)
                                    {
                                        throw new ArgumentException("Unrecognized RequestParameterLocation value: " + parameter.RequestParameterLocation);
                                    }

                                    break;
                            }

                            parameterDeclarationBuilder.Append(parameter.Type + " " + parameter.Name);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);
                        IType restAPIMethodReturnValueClientType = restAPIMethod.ReturnType.ClientType;
                        interfaceBlock.PublicMethod($"{restAPIMethodReturnValueClientType} {restAPIMethod.Name}({parameterDeclarations})");
                    }
                });
            }
        }

        private static void AddClientMethods(JavaType typeBlock, IEnumerable<ClientMethod> clientMethods, JavaSettings settings)
        {
            foreach (ClientMethod clientMethod in clientMethods)
            {
                RestAPIMethod restAPIMethod = clientMethod.RestAPIMethod;

                var restAPIMethodReturnBodyClientType = restAPIMethod.ReturnType.ClientType;

                MethodParameter serviceCallbackParameter = new MethodParameter(
                    description: "the async ServiceCallback to handle successful and failed responses.",
                    isFinal: false,
                    wireType: GenericType.ServiceCallback(restAPIMethodReturnBodyClientType),
                    name: "serviceCallback",
                    isRequired: true,
                    isConstant: false,
                    fromClient: false,
                    defaultValue: null,
                    // GetClientMethodParameterAnnotations() is provided false for isRequired so
                    // that this parameter won't get marked as NonNull.
                    annotations: Enumerable.Empty<ClassType>());

                MethodPageDetails pageDetails = clientMethod.MethodPageDetails;

                GenericType serviceFutureReturnType = GenericType.ServiceFuture(restAPIMethodReturnBodyClientType);
                
                bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase("Delete") && clientMethod.MethodRequiredParameters.Count() == 2;

                switch (clientMethod.Type)
                {
                    case ClientMethodType.PagingSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            if (restAPIMethod.UnexpectedResponseExceptionType != null)
                            {
                                comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                            }
                            comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Line($"{pageDetails.PageType} response = {clientMethod.PagingAsyncSinglePageMethodName}({clientMethod.ArgumentList}).blockingGet();");
                            function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(response)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.PublicMethod($"{pageDetails.PageType} nextPage(String {pageDetails.NextLinkParameterName})", subFunction =>
                                {
                                    if (restAPIMethod.IsPagingOperation && !restAPIMethod.AutoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
                                    {
                                        if (pageDetails.NextGroupParameterTypeName != clientMethod.GroupedParameterTypeName && (!clientMethod.OnlyRequiredParameters || clientMethod.GroupedParameter.IsRequired))
                                        {
                                            string nextGroupTypeCamelCaseName = pageDetails.NextGroupParameterTypeName.ToCamelCase();
                                            string groupedTypeCamelCaseName = clientMethod.GroupedParameterTypeName.ToCamelCase();

                                            string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(pageDetails.NextGroupParameterTypeName) + (settings.IsFluent ? "Inner" : "");

                                            if (!clientMethod.GroupedParameter.IsRequired)
                                            {
                                                subFunction.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = null;");
                                                subFunction.Line($"if ({groupedTypeCamelCaseName} != null) {{");
                                                subFunction.IncreaseIndent();
                                                subFunction.Line($"{nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                            }
                                            else
                                            {
                                                subFunction.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                            }

                                            foreach (AutoRestParameter outputParameter in pageDetails.NextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
                                            {
                                                string outputParameterName;
                                                if (!outputParameter.IsClientProperty)
                                                {
                                                    outputParameterName = outputParameter.Name;
                                                }
                                                else
                                                {
                                                    string caller = (outputParameter.Method != null && outputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                                    string clientPropertyName = outputParameter.ClientProperty?.Name?.ToString();
                                                    if (!string.IsNullOrEmpty(clientPropertyName))
                                                    {
                                                        CodeNamer codeNamer = CodeNamer.Instance;
                                                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                                    }
                                                    outputParameterName = $"{caller}.{clientPropertyName}()";
                                                }
                                                subFunction.Line($"{nextGroupTypeCamelCaseName}.with{outputParameterName.ToPascalCase()}({groupedTypeCamelCaseName}.{outputParameterName.ToCamelCase()}());");
                                            }

                                            if (!clientMethod.GroupedParameter.IsRequired)
                                            {
                                                subFunction.DecreaseIndent();
                                                subFunction.Line("}");
                                            }
                                        }
                                    }

                                    subFunction.Return($"{pageDetails.NextMethodInvocation + "SinglePageAsync"}({pageDetails.NextMethodParameterInvocation}).blockingGet()");
                                });
                            });
                        });
                        break;

                    case ClientMethodType.PagingAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Line($"return {clientMethod.PagingAsyncSinglePageMethodName}({clientMethod.ArgumentList})");
                            function.Indent(() =>
                            {
                                function.Line(".toObservable()");
                                function.Text($".concatMap(");
                                function.Lambda(pageDetails.PageType.ToString(), "page", lambda =>
                                {
                                    lambda.Line($"String {pageDetails.NextLinkVariableName} = page.nextPageLink();");
                                    lambda.If($"{pageDetails.NextLinkVariableName} == null", ifBlock =>
                                    {
                                        ifBlock.Return("Observable.just(page)");
                                    });

                                    if (clientMethod.RestAPIMethod.IsPagingOperation && !clientMethod.AutoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !pageDetails.NextMethod.InputParameterTransformation.IsNullOrEmpty())
                                    {
                                        if (pageDetails.NextGroupParameterTypeName != clientMethod.GroupedParameterTypeName && (!clientMethod.OnlyRequiredParameters || clientMethod.GroupedParameter.IsRequired))
                                        {
                                            string nextGroupTypeCamelCaseName = pageDetails.NextGroupParameterTypeName.ToCamelCase();
                                            string groupedTypeCamelCaseName = clientMethod.GroupedParameterTypeName.ToCamelCase();

                                            string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(pageDetails.NextGroupParameterTypeName) + (settings.IsFluent ? "Inner" : "");

                                            if (!clientMethod.GroupedParameter.IsRequired)
                                            {
                                                lambda.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = null;");
                                                lambda.Line($"if ({groupedTypeCamelCaseName} != null) {{");
                                                lambda.IncreaseIndent();
                                                lambda.Line($"{nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                            }
                                            else
                                            {
                                                lambda.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                                            }

                                            foreach (AutoRestParameter outputParameter in pageDetails.NextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
                                            {
                                                string outputParameterName;
                                                if (!outputParameter.IsClientProperty)
                                                {
                                                    outputParameterName = outputParameter.Name;
                                                }
                                                else
                                                {
                                                    string caller = (outputParameter.Method != null && outputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                                    string clientPropertyName = outputParameter.ClientProperty?.Name?.ToString();
                                                    if (!string.IsNullOrEmpty(clientPropertyName))
                                                    {
                                                        CodeNamer codeNamer = CodeNamer.Instance;
                                                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                                    }
                                                    outputParameterName = $"{caller}.{clientPropertyName}()";
                                                }
                                                lambda.Line($"{nextGroupTypeCamelCaseName}.with{outputParameterName.ToPascalCase()}({groupedTypeCamelCaseName}.{outputParameterName.ToCamelCase()}());");
                                            }

                                            if (!clientMethod.GroupedParameter.IsRequired)
                                            {
                                                lambda.DecreaseIndent();
                                                lambda.Line("}");
                                            }
                                        }
                                    }

                                    lambda.Return($"Observable.just(page).concatWith({pageDetails.NextMethodInvocation}Async({pageDetails.NextMethodParameterInvocation}))");
                                });
                                function.Line(");");
                            });
                        });
                        break;

                    case ClientMethodType.PagingAsyncSinglePage:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                            AddValidations(function, clientMethod.ExpressionsToValidate);
                            AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                            ApplyParameterTransformations(function, clientMethod, settings);
                            ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                            if (restAPIMethod.IsPagingNextOperation)
                            {
                                string methodUrl = restAPIMethod.UrlPath;
                                Regex regex = new Regex("{\\w+}");

                                string substitutedMethodUrl = regex.Replace(methodUrl, "%s");

                                IEnumerable<AutoRestParameter> retrofitParameters = restAPIMethod.AutoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None);
                                StringBuilder builder = new StringBuilder($"String.format(\"{substitutedMethodUrl}\"");
                                foreach (Match match in regex.Matches(methodUrl))
                                {
                                    string serializedNameWithBrackets = match.Value;
                                    string serializedName = serializedNameWithBrackets.Substring(1, serializedNameWithBrackets.Length - 2);
                                    AutoRestParameter parameter = retrofitParameters.First(p => p.SerializedName == serializedName);

                                    string parameterName;
                                    if (!parameter.IsClientProperty)
                                    {
                                        parameterName = parameter.Name;
                                    }
                                    else
                                    {
                                        string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                        string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                                        if (!string.IsNullOrEmpty(clientPropertyName))
                                        {
                                            CodeNamer codeNamer = CodeNamer.Instance;
                                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                        }
                                        parameterName = $"{caller}.{clientPropertyName}()";
                                    }

                                    IModelTypeJv parameterModelType = (IModelTypeJv) parameter.ModelType;
                                    if (parameterModelType != null && !parameter.IsNullable())
                                    {
                                        if (parameterModelType is PrimaryTypeJv parameterModelPrimaryType)
                                        {
                                            PrimaryTypeJv nonNullableParameterModelPrimaryType = DependencyInjection.New<PrimaryTypeJv>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            nonNullableParameterModelPrimaryType.IsNullable = false;

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }

                                    IModelTypeJv parameterClientType = parameterModelType.ConvertToClientType();

                                    IModelTypeJv parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is PrimaryTypeJv primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<PrimaryTypeJv>(AutoRestKnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;
                                    builder.Append(", " + parameterWireName);
                                }
                                builder.Append(")");

                                function.Line($"String nextUrl = {builder.ToString()};");
                            }

                            IType returnValueTypeArgumentType = ((GenericType)restAPIMethod.ReturnType).TypeArguments.Single();

                            string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);

                            function.Line($"return service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                            function.Indent(() =>
                            {
                                function.Text(".map(");
                                function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.body()");
                                function.Line(");");
                            });
                        });
                        break;

                    case ClientMethodType.SimulatedPagingSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Line($"{pageDetails.PageImplType} page = new {pageDetails.PageImplType}<>();");
                            function.Line($"page.setItems({clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).single().items());");
                            function.Line("page.setNextPageLink(null);");
                            function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(page)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.PublicMethod($"{pageDetails.PageType} nextPage(String nextPageLink)", subFunction =>
                                {
                                    subFunction.Return("null");
                                });
                            });
                        });
                        break;

                    case ClientMethodType.SimulatedPagingAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                            AddValidations(function, clientMethod.ExpressionsToValidate);
                            AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                            ApplyParameterTransformations(function, clientMethod, settings);
                            ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);

                            IType returnValueTypeArgumentType = ((GenericType)restAPIMethod.ReturnType).TypeArguments.Single();
                            string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                            function.Line($"return service.{clientMethod.RestAPIMethod.Name}({restAPIMethodArgumentList})");
                            function.Indent(() =>
                            {
                                function.Text(".map(");
                                function.Lambda(returnValueTypeArgumentType.ToString(), "res", "res.body()");
                                function.Line(")");
                                function.Line(".toObservable();");
                            });
                        });
                        break;

                    case ClientMethodType.LongRunningSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (clientMethod.RequiredNullableParameterExpressions.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            if (restAPIMethod.UnexpectedResponseExceptionType != null)
                            {
                                comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                            }
                            comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            if (clientMethod.ReturnValue.Type == PrimitiveType.Void)
                            {
                                function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingLast();");
                            }
                            else
                            {
                                function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingLast().result()");
                            }
                        });
                        break;

                    case ClientMethodType.LongRunningAsyncServiceCallback:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Return($"ServiceFutureUtil.fromLRO({clientMethod.SimpleAsyncMethodName}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                        });
                        break;

                    case ClientMethodType.Resumable:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            var parameter = restAPIMethod.Parameters.First();
                            AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                            function.Return($"service.{restAPIMethod.Name}({parameter.Name})");
                        });
                        break;

                    case ClientMethodType.LongRunningAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (clientMethod.RequiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                            AddValidations(function, clientMethod.ExpressionsToValidate);
                            AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                            ApplyParameterTransformations(function, clientMethod, settings);
                            ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                            string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                            function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        });
                        break;

                    case ClientMethodType.SimpleSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            if (restAPIMethod.UnexpectedResponseExceptionType != null)
                            {
                                comment.Throws(restAPIMethod.UnexpectedResponseExceptionType.ToString(), "thrown if the request is rejected by server");
                            }
                            comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            if (clientMethod.ReturnValue.Type != PrimitiveType.Void)
                            {
                                function.Return($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingGet()");
                            }
                            else if (isFluentDelete)
                            {
                                function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingGet();");
                            }
                            else
                            {
                                function.Line($"{clientMethod.SimpleAsyncMethodName}({clientMethod.ArgumentList}).blockingAwait();");
                            }
                        });
                        break;

                    case ClientMethodType.SimpleAsyncServiceCallback:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Return($"ServiceFuture.fromBody({clientMethod.Name}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                        });
                        break;

                    case ClientMethodType.SimpleAsyncRestResponse:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            AddNullChecks(function, clientMethod.RequiredNullableParameterExpressions);
                            AddValidations(function, clientMethod.ExpressionsToValidate);
                            AddOptionalAndConstantVariables(function, clientMethod, clientMethod.MethodParameters, settings);
                            ApplyParameterTransformations(function, clientMethod, settings);
                            ConvertClientTypesToWireTypes(function, restAPIMethod.Parameters, clientMethod.ClientReference, settings);
                            string restAPIMethodArgumentList = restAPIMethod.GetArgumentList(settings);
                            function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        });
                        break;

                    case ClientMethodType.SimpleAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (MethodParameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (!string.IsNullOrEmpty(clientMethod.ParametersDeclaration))
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Line($"return {clientMethod.RestAPIMethod.SimpleAsyncRestResponseMethodName}({clientMethod.ArgumentList})");
                            function.Indent(() =>
                            {
                                GenericType restAPIMethodClientReturnType = (GenericType)restAPIMethod.ReturnType.ClientType;
                                IType returnValueTypeArgumentClientType = restAPIMethodClientReturnType.TypeArguments.Single();
                                if (restAPIMethodReturnBodyClientType != PrimitiveType.Void)
                                {
                                    function.Text($".flatMapMaybe(");
                                    function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "res.body() == null ? Maybe.empty() : Maybe.just(res.body())");
                                    function.Line(");");
                                }
                                else if (isFluentDelete)
                                {
                                    function.Text($".flatMapMaybe(");
                                    function.Lambda(returnValueTypeArgumentClientType.ToString(), "res", "Maybe.empty()");
                                    function.Line(");");
                                }
                                else
                                {
                                    function.Line(".toCompletable();");
                                }
                            });
                        });
                        break;

                    default:
                        throw new ArgumentException($"There is no method implementation for {nameof(ClientMethodType)}.{clientMethod.Type}.");
                }
            }
        }

        private static void AddNullChecks(JavaBlock function, IEnumerable<string> expressionsToCheck)
        {
            foreach (string expressionToCheck in expressionsToCheck)
            {
                function.If($"{expressionToCheck} == null", ifBlock =>
                {
                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {expressionToCheck} is required and cannot be null.\");");
                });
            }
        }

        private static void AddValidations(JavaBlock function, IEnumerable<string> expressionsToValidate)
        {
            foreach (string expressionToValidate in expressionsToValidate)
            {
                function.Line($"Validator.validate({expressionToValidate});");
            }
        }

        private static void AddOptionalAndConstantVariables(JavaBlock function, ClientMethod clientMethod, IEnumerable<MethodParameter> autoRestClientMethodAndConstantParameters, JavaSettings settings)
        {
            foreach (MethodParameter parameter in autoRestClientMethodAndConstantParameters)
            {
                if ((clientMethod.OnlyRequiredParameters && !parameter.IsRequired) || parameter.IsConstant)
                {
                    IType parameterClientType = parameter.ClientType.ClientType;
                    string defaultValue = parameterClientType.DefaultValueExpression(parameter.DefaultValue);
                    function.Line($"final {parameterClientType} {parameter.Name} = {defaultValue ?? "null"};");
                }
            }
        }

        private static void ApplyParameterTransformations(JavaBlock function, ClientMethod clientMethod, JavaSettings settings)
        {
            AutoRestMethod autoRestMethod = clientMethod.AutoRestMethod;

            foreach (AutoRestParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
            {
                AutoRestParameter transformationOutputParameter = transformation.OutputParameter;
                IModelTypeJv transformationOutputParameterModelType = (IModelTypeJv) transformationOutputParameter.ModelType;
                if (transformationOutputParameterModelType != null && !transformationOutputParameter.IsNullable() && transformationOutputParameterModelType is PrimaryTypeJv transformationOutputParameterModelPrimaryType)
                {
                    PrimaryTypeJv transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<PrimaryTypeJv>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                    transformationOutputParameterModelNonNullablePrimaryType.IsNullable = false;

                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                }
                IModelTypeJv transformationOutputParameterClientType = transformationOutputParameterModelType.ConvertToClientType();

                string outParamName;
                if (!transformationOutputParameter.IsClientProperty)
                {
                    outParamName = transformationOutputParameter.Name;
                }
                else
                {
                    string caller = (transformationOutputParameter.Method != null && transformationOutputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                    string clientPropertyName = transformationOutputParameter.ClientProperty?.Name?.ToString();
                    if (!string.IsNullOrEmpty(clientPropertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                    }
                    outParamName = $"{caller}.{clientPropertyName}()";
                }
                while (autoRestMethod.Parameters.Any((AutoRestParameter parameter) =>
                {
                    string parameterName;
                    if (!parameter.IsClientProperty)
                    {
                        parameterName = parameter.Name;
                    }
                    else
                    {
                        string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        parameterName = $"{caller}.{clientPropertyName}()";
                    }
                    return parameterName == outParamName;
                }))
                {
                    outParamName += "1";
                }

                transformationOutputParameter.Name = outParamName;

                string transformationOutputParameterClientParameterVariantTypeName = transformationOutputParameterClientType.ConvertToClientType().ModelTypeName;

                IEnumerable<AutoRestParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                    .Select((AutoRestParameterMapping m) =>
                    {
                        AutoRestParameter parameter = m.InputParameter;

                        string parameterName;
                        if (!parameter.IsClientProperty)
                        {
                            parameterName = parameter.Name;
                        }
                        else
                        {
                            string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            parameterName = $"{caller}.{clientPropertyName}()";
                        }

                        return parameterName + " != null";
                    }));
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !clientMethod.OnlyRequiredParameters;
                if (conditionalAssignment)
                {
                    function.Line("{0} {1} = null;",
                        transformationOutputParameterClientParameterVariantTypeName,
                        outParamName);
                    function.Line($"if ({nullCheck}) {{");
                    function.IncreaseIndent();
                }

                CompositeTypeJv transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as CompositeTypeJv;
                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                {
                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && transformationOutputParameterModelCompositeType.IsInnerModel)
                    {
                        transformationOutputParameterModelCompositeTypeName += "Inner";
                    }

                    function.Line("{0}{1} = new {2}();",
                        !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        transformationOutputParameterModelCompositeTypeName);
                }

                foreach (AutoRestParameterMapping mapping in transformationParameterMappings)
                {
                    string inputPath;
                    if (!mapping.InputParameter.IsClientProperty)
                    {
                        inputPath = mapping.InputParameter.Name;
                    }
                    else
                    {
                        string caller = (mapping.InputParameter.Method != null && mapping.InputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = mapping.InputParameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        inputPath = $"{caller}.{clientPropertyName}()";
                    }

                    if (mapping.InputParameterProperty != null)
                    {
                        inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()";
                    }
                    if (clientMethod.OnlyRequiredParameters && !mapping.InputParameter.IsRequired)
                    {
                        inputPath = "null";
                    }

                    string getMapping;
                    if (mapping.OutputParameterProperty != null)
                    {
                        getMapping = $".with{CodeNamer.Instance.PascalCase(mapping.OutputParameterProperty)}({inputPath})";
                    }
                    else
                    {
                        getMapping = $" = {inputPath}";
                    }

                    function.Line("{0}{1}{2};",
                        !conditionalAssignment && transformationOutputParameterModelCompositeType == null ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        getMapping);
                }

                if (conditionalAssignment)
                {
                    function.DecreaseIndent();
                    function.Line("}");
                }
            }
        }

        private static void ConvertClientTypesToWireTypes(JavaBlock function, IEnumerable<RestAPIParameter> autoRestMethodRetrofitParameters, string methodClientReference, JavaSettings settings)
        {
            foreach (RestAPIParameter parameter in autoRestMethodRetrofitParameters)
            {
                IType parameterWireType = parameter.Type;;
                if (parameter.IsNullable)
                {
                    parameterWireType = parameterWireType.AsNullable();
                }
                IType parameterClientType = parameterWireType.ClientType;

                if (parameterClientType != ClassType.Base64Url &&
                    parameter.RequestParameterLocation != RequestParameterLocation.Body &&
                    parameter.RequestParameterLocation != RequestParameterLocation.FormData &&
                    (parameterClientType == ArrayType.ByteArray) || parameterClientType is ListType)
                {
                    parameterWireType = ClassType.String;
                }

                if (parameterWireType != parameterClientType)
                {
                    string parameterName = parameter.ParameterReference;
                    string parameterWireName = parameter.ParameterReferenceConverted;

                    bool addedConversion = false;
                    RequestParameterLocation parameterLocation = parameter.RequestParameterLocation;
                    if (parameterLocation != RequestParameterLocation.Body &&
                        parameterLocation != RequestParameterLocation.FormData &&
                        (parameter.Type is ArrayType || parameter.Type is ListType))
                    {
                        string parameterWireTypeName = ((IModelTypeJv)parameterWireType).ModelTypeName;

                        if (parameterClientType == ArrayType.ByteArray)
                        {
                            if (parameterWireType == ClassType.String)
                            {
                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Util.encodeToString({parameterName});");
                            }
                            else
                            {
                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                            }
                            addedConversion = true;
                        }
                        else if (parameterClientType is ListType)
                        {
                            function.Line("{0} {1} = {2}.serializerAdapter().serializeList({3}, CollectionFormat.{4});",
                                parameterWireTypeName,
                                parameterWireName,
                                methodClientReference,
                                parameterName,
                                parameter.CollectionFormat.ToString().ToUpperInvariant());
                            addedConversion = true;
                        }
                    }

                    if (!addedConversion)
                    {
                        function.Line(parameter.ConvertFromClientType(parameterName, parameterWireName));
                    }
                }
            }
        }
    }
}
