// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
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

namespace AutoRest.Java
{
    public class JavaCodeGenerator : CodeGenerator
    {
        private const string targetVersion = "1.1.3";
        internal const string pomVersion = targetVersion + "-SNAPSHOT";

        private const string httpPipelineImport = "com.microsoft.rest.v2.http." + httpPipelineType;
        private const string httpPipelineDescription = "The HTTP pipeline to send requests through.";
        private const string httpPipelineType = "HttpPipeline";
        private const string httpPipelineVariableName = "httpPipeline";

        private const string restProxyImport = "com.microsoft.rest.v2." + restProxyType;
        private const string restProxyType = "RestProxy";

        private const string serializerImport = "com.microsoft.rest.v2.protocol.SerializerAdapter";

        private const string azureEnvironmentImport = "com.microsoft.azure.v2." + azureEnvironmentType;
        private const string azureEnvironmentType = "AzureEnvironment";
        private const string azureEnvironmentVariableName = "azureEnvironment";
        private const string azureEnvironmentDescription = "The environment that requests will target.";

        private const string azureProxyImport = "com.microsoft.azure.v2." + azureProxyType;
        private const string azureProxyType = "AzureProxy";

        private const string serviceClientCredentialsType = "ServiceClientCredentials";
        private const string serviceClientCredentialsImport = "com.microsoft.rest.v2.credentials." + serviceClientCredentialsType;
        private const string azureTokenCredentialsType = "AzureTokenCredentials";
        private const string azureTokenCredentialsImport = "com.microsoft.azure.v2.credentials." + azureTokenCredentialsType;
        private const string credentialsVariableName = "credentials";
        private const string credentialsDescription = "the management credentials for Azure";

        private const string serviceCallbackVariableName = "serviceCallback";

        private const string implPackage = "implementation";
        private const string modelsPackage = ".models";

        private const string innerSupportsImportPrefix = "com.microsoft.azure.management.resources.fluentcore.collection.InnerSupports";
        private const string innerSupportsGetImport = innerSupportsImportPrefix + "Get";
        private const string innerSupportsDeleteImport = innerSupportsImportPrefix + "Delete";
        private const string innerSupportsListingImport = innerSupportsImportPrefix + "Listing";

        private const string GetByResourceGroup = "GetByResourceGroup";
        private const string ListByResourceGroup = "ListByResourceGroup";
        private const string List = "List";
        private const string Delete = "Delete";

        private static readonly IDictionary<KeyValuePair<string, string>, string> pageClasses = new Dictionary<KeyValuePair<string, string>, string>();

        private static readonly ISet<Property> innerModelProperties = new HashSet<Property>();
        private static readonly ISet<CompositeType> innerModelCompositeType = new HashSet<CompositeType>();

        private static readonly ISet<SequenceType> pagedListTypes = new HashSet<SequenceType>();

        private static readonly IDictionary<IModelType, string> pageImplTypes = new Dictionary<IModelType, string>();

        private static readonly IDictionary<Response, Method> responseParents = new Dictionary<Response, Method>();

        // This is a Not set because the default value for WantNullable was true.
        private static readonly ISet<PrimaryType> primaryTypeNotWantNullable = new HashSet<PrimaryType>();

        private static readonly ISet<string> primaryTypes = new HashSet<string>()
        {
            "int", "Integer",
            "long", "Long",
            "object", "Object",
            "bool", "Boolean",
            "double", "Double",
            "float", "Float",
            "byte", "Byte",
            "byte[]", "Byte[]",
            "String",
            "LocalDate",
            "DateTime",
            "DateTimeRfc1123",
            "Duration",
            "Period",
            "BigDecimal",
            "AsyncInputStream"
        };

        private const string ClientRuntimePackage = "com.microsoft.rest.v2:client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        
        public JavaCodeNamer Namer { get; private set; }

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";

        public override string ImplementationFileExtension => ".java";
        
        private static bool GetBoolSetting(Settings autoRestSettings, string settingName)
            => autoRestSettings.Host?.GetValue<bool?>(settingName).Result == true;

        private static string GetStringSetting(Settings autoRestSettings, string settingName)
            => autoRestSettings.Host?.GetValue<string>(settingName).Result;

        /// <summary>
        /// Generate Java client code for given ServiceClient.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override Task Generate(CodeModel codeModel)
        {
            Settings autoRestSettings = Settings.Instance;
            JavaSettings javaSettings = new JavaSettings
            {
                AutoRestSettings = autoRestSettings,
                IsAzure = GetBoolSetting(autoRestSettings, "azure-arm"),
                IsFluent = GetBoolSetting(autoRestSettings, "fluent"),
                RegenerateManagers = GetBoolSetting(autoRestSettings, "regenerate-manager"),
                RegeneratePom = GetBoolSetting(autoRestSettings, "regenerate-pom"),
                FileHeaderText = autoRestSettings.Header,
                MaximumJavadocCommentWidth = autoRestSettings.MaximumCommentColumns,
                ServiceName = GetAutoRestSettingsServiceName(autoRestSettings),
                Package = codeModel.Namespace.ToLowerInvariant(),
            };

            TransformCodeModel(codeModel, javaSettings);

            Service service = GetService(codeModel, javaSettings);

            List<JavaFile> javaFiles = new List<JavaFile>();
            javaFiles.Add(GetServiceClientJavaFile(codeModel, javaSettings));

            foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
            {
                javaFiles.Add(GetMethodGroupClientJavaFile(codeModel, javaSettings, methodGroup));
            }

            foreach (CompositeType modelType in GetModelTypes(codeModel, javaSettings))
            {
                javaFiles.Add(GetModelJavaFile(codeModel, javaSettings, modelType));
            }

            foreach (ServiceEnum serviceEnum in service.Enums)
            {
                javaFiles.Add(GetEnumJavaFile(serviceEnum, javaSettings));
            }

            foreach (SequenceType xmlWrapperSequenceType in GetXmlWrapperTypes(codeModel, javaSettings))
            {
                javaFiles.Add(GetXmlWrapperJavaFile(codeModel, javaSettings, xmlWrapperSequenceType));
            }

            foreach (ServiceException exception in service.Exceptions)
            {
                javaFiles.Add(GetExceptionJavaFile(exception, javaSettings));
            }

            foreach (string subPackage in new[] { "", "implementation" })
            {
                javaFiles.Add(GetPackageInfoJavaFiles(codeModel, javaSettings, subPackage));
            }

            if (javaSettings.IsAzureOrFluent)
            {
                foreach (KeyValuePair<KeyValuePair<string, string>, string> pageClass in pageClasses)
                {
                    javaFiles.Add(GetPageJavaFile(codeModel, javaSettings, pageClass));
                }
            }

            if (!javaSettings.IsFluent)
            {
                javaFiles.Add(GetAzureServiceClientInterfaceJavaFile(codeModel, javaSettings));

                foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
                {
                    javaFiles.Add(GetMethodGroupClientInterfaceJavaFile(codeModel, javaSettings, methodGroup));
                }

                javaFiles.Add(GetPackageInfoJavaFiles(codeModel, javaSettings, "models"));
            }
            else
            {
                if (javaSettings.RegenerateManagers)
                {
                    javaFiles.Add(GetAzureServiceManagerJavaFile(codeModel, javaSettings));
                }

                if (javaSettings.RegeneratePom)
                {
                    PomTemplate pomTemplate = new PomTemplate { Model = codeModel };
                    StringBuilder pomContentsBuilder = new StringBuilder();
                    using (pomTemplate.TextWriter = new StringWriter(pomContentsBuilder))
                    {
                        pomTemplate.ExecuteAsync().GetAwaiter().GetResult();
                    }
                    javaFiles.Add(new JavaFile("pom.xml", pomContentsBuilder.ToString()));
                }
            }

            return Task.WhenAll(javaFiles.Select(javaFile => Write(javaFile.Contents.ToString(), javaFile.FilePath)));
        }
        
        private static CodeModel TransformCodeModel(CodeModel codeModel, JavaSettings settings)
        {
            // List retrieved from
            // http://docs.oracle.com/javase/tutorial/java/nutsandbolts/_keywords.html
            CodeNamer.Instance.ReservedWords.AddRange(new[]
            {
                "abstract", "assert",   "boolean",  "break",    "byte",
                "case",     "catch",    "char",     "class",    "const",
                "continue", "default",  "do",       "double",   "else",
                "enum",     "extends",  "false",    "final",    "finally",
                "float",    "for",      "goto",     "if",       "implements",
                "import",   "int",      "long",     "interface","instanceof",
                "native",   "new",      "null",     "package",  "private",
                "protected","public",   "return",   "short",    "static",
                "strictfp", "super",    "switch",   "synchronized","this",
                "throw",    "throws",   "transient","true",     "try",
                "void",     "volatile", "while",    "date",     "datetime",
                "period",   "stream",   "string",   "object", "header"
            });

            if (!settings.IsAzureOrFluent)
            {
                SwaggerExtensions.NormalizeClientModel(codeModel);
            }
            else
            {
                settings.AddCredentials = true;

                // This extension from general extensions must be run prior to Azure specific extensions.
                SwaggerExtensions.ProcessParameterizedHost(codeModel);
                AzureExtensions.ProcessClientRequestIdExtension(codeModel);
                AzureExtensions.UpdateHeadMethods(codeModel);
                SwaggerExtensions.ProcessGlobalParameters(codeModel);
                SwaggerExtensions.FlattenModels(codeModel);
                SwaggerExtensions.FlattenMethodParameters(codeModel);
                ParameterGroupExtensionHelper.AddParameterGroups(codeModel);
                AddLongRunningOperations(codeModel, settings);
                AzureExtensions.AddAzureProperties(codeModel);
                AzureExtensions.SetDefaultResponses(codeModel);

                // set Parent on responses (required for pageable)
                foreach (Method method in codeModel.Methods)
                {
                    foreach (Response response in method.Responses.Values)
                    {
                        ResponseSetParent(response, method);
                    }
                    ResponseSetParent(method.DefaultResponse, method);
                    ResponseSetParent(method.ReturnType, method);
                }
                AzureExtensions.AddPageableMethod(codeModel);

                NormalizePaginatedMethods(codeModel, pageClasses, settings);

                if (settings.IsFluent)
                {
                    // determine inner models
                    foreach (Parameter param in codeModel.Methods.SelectMany(m => m.Parameters))
                    {
                        AppendInnerToTopLevelType(ParameterGetModelType(param), codeModel, settings);
                    }
                    foreach (Response response in codeModel.Methods.SelectMany(m => m.Responses).Select(r => r.Value))
                    {
                        AppendInnerToTopLevelType(response.Body, codeModel, settings);
                        AppendInnerToTopLevelType(response.Headers, codeModel, settings);
                    }
                    foreach (CompositeType model in codeModel.ModelTypes)
                    {
                        IModelType baseModelType = model.BaseModelType;
                        if (baseModelType != null && (IModelTypeName(baseModelType, settings) == "Resource" || IModelTypeName(baseModelType, settings) == "SubResource"))
                        {
                            AppendInnerToTopLevelType(model, codeModel, settings);
                        }
                    }
                }

                // param order (PATH first)
                foreach (Method method in codeModel.Methods)
                {
                    List<Parameter> parameters = method.Parameters.ToList();
                    method.ClearParameters();
                    foreach (Parameter parameter in parameters.Where(x => x.Location == ParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                    foreach (Parameter parameter in parameters.Where(x => x.Location != ParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                }
            }

            return codeModel;
        }

        private static void AppendInnerToTopLevelType(IModelType type, CodeModel serviceClient, JavaSettings settings)
        {
            if (type != null)
            {
                if (type is CompositeType compositeType)
                {
                    if (!CompositeTypeIsResource(compositeType, settings))
                    {
                        innerModelCompositeType.Add(compositeType);
                    }
                }
                else if (type is SequenceType sequenceType)
                {
                    AppendInnerToTopLevelType(sequenceType.ElementType, serviceClient, settings);
                }
                else if (type is DictionaryType dictionaryType)
                {
                    AppendInnerToTopLevelType(dictionaryType.ValueType, serviceClient, settings);
                }
            }
        }

        private static void AddLongRunningOperations(CodeModel codeModel, JavaSettings settings)
        {
            if (codeModel == null)
            {
                throw new ArgumentNullException("codeModel");
            }

            foreach (MethodGroup methodGroup in codeModel.Operations)
            {
                Method[] methods = methodGroup.Methods.ToArray();
                methodGroup.ClearMethods();
                foreach (Method method in methods)
                {
                    methodGroup.Add(method);
                    if (method.Extensions.Get<bool>(AzureExtensions.LongRunningExtension) == true)
                    {
                        var response = method.Responses.Values.First();
                        if (!method.Responses.ContainsKey(HttpStatusCode.OK))
                        {
                            method.Responses[HttpStatusCode.OK] = response;
                        }
                        if (!method.Responses.ContainsKey(HttpStatusCode.Accepted))
                        {
                            method.Responses[HttpStatusCode.Accepted] = response;
                        }
                        if (method.HttpMethod != HttpMethod.Get && !method.Responses.ContainsKey(HttpStatusCode.NoContent))
                        {
                            method.Responses[HttpStatusCode.NoContent] = response;
                        }

                        Method m = DependencyInjection.Duplicate(method);
                        // Rename original method
                        SetMethodName(method, "Begin" + GetMethodName(m, settings).ToPascalCase());
                        m.Extensions.Remove(AzureExtensions.LongRunningExtension);
                        methodGroup.Add(m);
                    }
                }
            }
        }

        /// <summary>
        /// Changes paginated method signatures to return Page type.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <param name="pageClasses"></param>
        private static void NormalizePaginatedMethods(CodeModel serviceClient, IDictionary<KeyValuePair<string, string>, string> pageClasses, JavaSettings settings)
        {
            if (serviceClient == null)
            {
                throw new ArgumentNullException("serviceClient");
            }

            var convertedTypes = new Dictionary<IModelType, IModelType>();

            foreach (Method method in serviceClient.Methods.Where(m => m.Extensions.ContainsKey(AzureExtensions.PageableExtension) || MethodSimulateAsPagingOperation(m, settings)))
            {
                string nextLinkString;
                string pageClassName = GetPagingSetting(method.Extensions, pageClasses, MethodSimulateAsPagingOperation(method, settings), out nextLinkString);
                if (string.IsNullOrEmpty(pageClassName))
                {
                    continue;
                }
                if (string.IsNullOrEmpty(nextLinkString))
                {
                    method.Extensions[AzureExtensions.PageableExtension] = null;
                }
                bool anyTypeConverted = false;
                foreach (HttpStatusCode responseStatus in method.Responses.Where(r => r.Value.Body is CompositeType).Select(s => s.Key).ToArray())
                {
                    anyTypeConverted = true;
                    CompositeType compositeType = (CompositeType)method.Responses[responseStatus].Body;
                    SequenceType sequenceType = GetCompositeTypeProperties(compositeType, settings).Select(p => GetPropertyModelType(p)).FirstOrDefault(t => t is SequenceType) as SequenceType;

                    // if the type is a wrapper over page-able response
                    if (sequenceType != null)
                    {
                        SequenceType pagedResult = DependencyInjection.New<SequenceType>();
                        pagedResult.ElementType = sequenceType.ElementType;
                        SequenceTypeSetPageImplType(pagedResult, pageClassName);

                        convertedTypes[method.Responses[responseStatus].Body] = pagedResult;
                        Response resp = DependencyInjection.New<Response>(pagedResult, method.Responses[responseStatus].Headers);
                        ResponseSetParent(resp, method);
                        method.Responses[responseStatus] = resp;
                    }
                }

                if (!anyTypeConverted && MethodSimulateAsPagingOperation(method, settings))
                {
                    foreach (HttpStatusCode responseStatus in method.Responses.Where(r => r.Value.Body is SequenceType).Select(s => s.Key).ToArray())
                    {
                        SequenceType sequenceType = (SequenceType)method.Responses[responseStatus].Body;

                        SequenceType pagedResult = DependencyInjection.New<SequenceType>();
                        pagedResult.ElementType = sequenceType.ElementType;
                        SequenceTypeSetPageImplType(pagedResult, pageClassName);

                        convertedTypes[method.Responses[responseStatus].Body] = pagedResult;
                        Response resp = DependencyInjection.New<Response>(pagedResult, method.Responses[responseStatus].Headers);
                        ResponseSetParent(resp, method);
                        method.Responses[responseStatus] = resp;
                    }
                }

                if (convertedTypes.ContainsKey(method.ReturnType.Body))
                {
                    Response resp = DependencyInjection.New<Response>(convertedTypes[method.ReturnType.Body], method.ReturnType.Headers);
                    ResponseSetParent(resp, method);
                    method.ReturnType = resp;
                }
            }

            SwaggerExtensions.RemoveUnreferencedTypes(serviceClient,
                new HashSet<string>(convertedTypes.Keys.Where(x => x is CompositeType).Cast<CompositeType>().Select(t => IModelTypeName(t, settings))));
        }

        private static string GetPagingSetting(Dictionary<string, object> extensions, IDictionary<KeyValuePair<string, string>, string> pageClasses, bool simulatingPaging, out string nextLinkName)
        {
            // default value
            nextLinkName = null;
            var itemName = "value";
            string className = null;

            if (extensions.ContainsKey(AzureExtensions.PageableExtension))
            {
                var ext = extensions[AzureExtensions.PageableExtension] as JContainer;

                if (ext == null)
                {
                    return null;
                }

                nextLinkName = (string)ext["nextLinkName"];
                itemName = (string)ext["itemName"] ?? "value";
                className = (string)ext["className"];
            }
            else if (!simulatingPaging)
            {
                return null;
            }

            var keypair = new KeyValuePair<string, string>(nextLinkName, itemName);
            if (!pageClasses.ContainsKey(keypair))
            {
                if (string.IsNullOrWhiteSpace(className))
                {
                    if (pageClasses.Count > 0)
                    {
                        className = $"PageImpl{pageClasses.Count}";
                    }
                    else
                    {
                        className = "PageImpl";
                    }
                }
                pageClasses.Add(keypair, className);
            }

            return pageClasses[keypair];
        }

        private static Service GetService(CodeModel codeModel, JavaSettings settings)
        {
            // Get Service Enum Types.
            List<ServiceEnum> enums = new List<ServiceEnum>();
            string enumSubPackage = settings.IsFluent ? null : modelsPackage;
            Func<char, bool> isUpper = new Func<char, bool>(c => c >= 'A' && c <= 'Z');
            Func<char, bool> isLower = new Func<char, bool>(c => c >= 'a' && c <= 'z');
            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                string enumName = IModelTypeName(enumType, settings);
                bool expandable = enumType.ModelAsString;

                List<ServiceEnumValue> enumValues = new List<ServiceEnumValue>();
                foreach (EnumValue enumValue in enumType.Values)
                {
                    string name = enumValue.MemberName;
                    if (!string.IsNullOrWhiteSpace(name))
                    {
                        name = new Regex("[\\ -]+").Replace(name, "_");
                        for (int i = 1; i < name.Length - 1; i++)
                        {
                            if (isUpper(name[i]))
                            {
                                if (name[i - 1] != '_' && isLower(name[i - 1]))
                                {
                                    name = name.Insert(i, "_");
                                }
                            }
                        }
                        name = name.ToUpperInvariant();
                    }

                    string value = enumValue.SerializedName;

                    enumValues.Add(new ServiceEnumValue(name, value));
                }

                enums.Add(new ServiceEnum(enumName, enumSubPackage, expandable, enumValues));
            }

            // Get Service Exceptions
            List<ServiceException> exceptions = new List<ServiceException>();
            foreach (CompositeType exceptionType in codeModel.ErrorTypes)
            {
                string exceptionName = CompositeTypeExceptionTypeDefinitionName(exceptionType, settings);
                string errorName = IModelTypeName(exceptionType, settings);
                
                // Skip any exceptions that are named "CloudErrorException" or have a body named
                // "CloudError" because those types already exist in the runtime.
                if (exceptionName != "CloudErrorException" && errorName != "CloudError")
                {
                    string exceptionSubPackage;
                    if (settings.IsFluent)
                    {
                        exceptionSubPackage = innerModelCompositeType.Contains(exceptionType) ? ".implementation" : "";
                    }
                    else
                    {
                        exceptionSubPackage = modelsPackage;
                    }

                    exceptions.Add(new ServiceException(exceptionName, errorName, exceptionSubPackage));
                }
            }

            return new Service(enums, exceptions);
        }

        private static JavaFile GetAzureServiceManagerJavaFile(CodeModel codeModel, JavaSettings settings)
        {
            string serviceName = GetServiceName(settings, codeModel);
            if (string.IsNullOrEmpty(serviceName))
            {
                serviceName = "MissingServiceName";
            }
            string className = $"{serviceName}Manager";

            string[] versionParts = targetVersion.Split('.');
            int minorVersion = int.Parse(versionParts[1]);
            int patchVersion = int.Parse(versionParts[2]);
            int newMinorVersion = (patchVersion == 0 ? minorVersion : minorVersion + 1);
            string betaSinceVersion = "V" + versionParts[0] + "_" + newMinorVersion + "_0";

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, className);

            javaFile.Import(
                "com.microsoft.azure.management.apigeneration.Beta",
                "com.microsoft.azure.management.apigeneration.Beta.SinceVersion",
                "com.microsoft.azure.management.resources.fluentcore.arm.AzureConfigurable",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.AzureConfigurableImpl",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.Manager",
                "com.microsoft.azure.v2.AzureEnvironment",
                azureTokenCredentialsImport,
                "com.microsoft.azure.v2.serializer.AzureJacksonAdapter");

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"Entry point to Azure {serviceName} resource management.");
            });
            javaFile.Annotation($"Beta(SinceVersion.{betaSinceVersion})");
            javaFile.PublicFinalClass($"{className} extends Manager<{className}, {codeModel.Name + "Impl"}>", classBlock =>
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
                    comment.Description($"Creates an instance of {className} that exposes {serviceName} resource management API entry points.");
                    comment.Param(credentialsVariableName, credentialsDescription);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({azureTokenCredentialsType} {credentialsVariableName}, String subscriptionId)", function =>
                {
                    function.Line($"final {httpPipelineType} {httpPipelineVariableName} = AzureProxy.defaultPipeline({className}.class, {credentialsVariableName});");
                    function.Return($"new {className}({httpPipelineVariableName}, subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {className} that exposes {serviceName} resource management API entry points.");
                    comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({httpPipelineType} {httpPipelineVariableName}, String subscriptionId)", function =>
                {
                    function.Return($"new {className}({httpPipelineVariableName}, subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The interface allowing configurations to be set.");
                });
                classBlock.PublicInterface("Configurable extends AzureConfigurable<Configurable>", interfaceBlock =>
                {
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Creates an instance of {className} that exposes {serviceName} management API entry points.");
                        comment.Param(credentialsVariableName, credentialsDescription);
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return($"the interface exposing {serviceName} management API entry points that work across subscriptions");
                    });
                    interfaceBlock.PublicMethod($"{className} authenticate({azureTokenCredentialsType} {credentialsVariableName}, String subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The implementation for Configurable interface.");
                });
                classBlock.PrivateStaticFinalClass("ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass =>
                {
                    innerClass.PublicMethod($"{className} authenticate({azureTokenCredentialsType} {credentialsVariableName}, String subscriptionId)", function =>
                    {
                        function.Return($"{className}.authenticate(build{httpPipelineType}({credentialsVariableName}), subscriptionId)");
                    });
                });

                classBlock.PrivateMethod($"private {className}({httpPipelineType} {httpPipelineVariableName}, String subscriptionId)", constructor =>
                {
                    constructor.Line("super(");
                    constructor.Indent(() =>
                    {
                        constructor.Line($"{httpPipelineVariableName},");
                        constructor.Line("subscriptionId,");
                        constructor.Line($"new {codeModel.Name}Impl({httpPipelineVariableName}).withSubscriptionId(subscriptionId));");
                    });
                });
            });

            return javaFile;
        }

        public static JavaFile GetPageJavaFile(CodeModel codeModel, JavaSettings settings, KeyValuePair<KeyValuePair<string, string>, string> pageClass)
        {
            string nextLinkName = pageClass.Key.Key;
            string itemName = pageClass.Key.Value;

            string className = pageClass.Value.ToPascalCase();

            string subPackage = (settings.IsFluent ? implPackage : modelsPackage);
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(subPackage, settings, className);
            javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty",
                            "com.microsoft.azure.v2.Page",
                            "java.util.List");

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                comment.Param("<T>", "type of Azure resource");
            });
            javaFile.PublicClass($"{className}<T> implements Page<T>", classBlock =>
            {
                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The link to the next page.");
                });
                classBlock.Annotation($"JsonProperty(\"{nextLinkName}\")");
                classBlock.PrivateMemberVariable("String", "nextPageLink");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The list of items.");
                });
                classBlock.Annotation($"JsonProperty(\"{itemName}\")");
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
                classBlock.PublicMethod($"{className}<T> setNextPageLink(String nextPageLink)", function =>
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
                classBlock.PublicMethod($"{className}<T> setItems(List<T> items)", function =>
                {
                    function.Line("this.items = items;");
                    function.Return("this");
                });
            });

            return javaFile;
        }

        public static IEnumerable<SequenceType> GetXmlWrapperTypes(CodeModel codeModel, JavaSettings settings)
        {
            ISet<SequenceType> result = new HashSet<SequenceType>();
            if (codeModel.ShouldGenerateXmlSerialization)
            {
                // Every sequence type used as a parameter to a service method.
                foreach (MethodGroup methodGroup in codeModel.Operations)
                {
                    foreach (Method method in methodGroup.Methods)
                    {
                        foreach (Parameter parameter in method.Parameters)
                        {
                            IModelType parameterType = ParameterGetModelType(parameter);
                            if (parameterType is SequenceType sequenceType)
                            {
                                result.Add(sequenceType);
                            }
                        }
                    }
                }
            }
            return result;
        }

        public static JavaFile GetXmlWrapperJavaFile(CodeModel codeModel, JavaSettings settings, SequenceType sequenceType)
        {
            string sequenceTypeName = IModelTypeName(sequenceType, settings);
            string xmlName = sequenceType.XmlName;
            string xmlNameCamelCase = xmlName.ToCamelCase();
            string className = $"{xmlName.ToPascalCase()}Wrapper";

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, className);
            javaFile.Import(GetIModelTypeImports(sequenceType, settings).Concat(new string[]
            {
                "com.fasterxml.jackson.annotation.JsonCreator",
                "com.fasterxml.jackson.annotation.JsonProperty",
                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty",
                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement"
            }));
            javaFile.Annotation($"JacksonXmlRootElement(localName = \"{xmlName}\")");
            javaFile.PublicClass(className, classBlock =>
            {
                classBlock.Annotation($"JacksonXmlProperty(localName = \"{sequenceType.ElementXmlName}\")");
                classBlock.PrivateFinalMemberVariable(sequenceTypeName, xmlNameCamelCase);

                classBlock.Annotation("JsonCreator");
                classBlock.PublicConstructor($"{className}(@JsonProperty(\"{xmlNameCamelCase}\") {sequenceTypeName} {xmlNameCamelCase})", constructor =>
                {
                    constructor.Line($"this.{xmlNameCamelCase} = {xmlNameCamelCase};");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Get the {xmlName} value.");
                    comment.Return($"the {xmlName} value");
                });
                classBlock.PublicMethod($"{sequenceTypeName} {xmlNameCamelCase}()", function =>
                {
                    function.Return(xmlNameCamelCase);
                });
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientJavaFile(CodeModel codeModel, JavaSettings settings)
        {
            string serviceClientClassName = GetServiceClientClassName(codeModel);
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, serviceClientClassName);

            string serviceClientClassDeclaration = $"{serviceClientClassName} extends ";
            if (settings.IsAzureOrFluent)
            {
                serviceClientClassDeclaration += "Azure";
            }
            serviceClientClassDeclaration += "ServiceClient";
            if (!settings.IsFluent)
            {
                serviceClientClassDeclaration += $" implements {GetServiceClientInterfaceName(codeModel)}";
            }

            HashSet<string> imports = new HashSet<string>();
            if (!settings.IsFluent)
            {
                string serviceClientInterfacePath = GetPackage(settings) + "." + GetServiceClientInterfaceName(codeModel);
                imports.Add(serviceClientInterfacePath);
                imports.AddRange(GetMethodGroups(codeModel).Select((MethodGroup methodGroup) => GetMethodGroupClientInterfacePath(methodGroup, settings)));
            }
            if (HasServiceClientCredentials(codeModel))
            {
                imports.Add(serviceClientCredentialsImport);
            }
            IEnumerable<Method> restAPIMethods = GetRestAPIMethods(codeModel);
            if (restAPIMethods.Any())
            {
                imports.Add("com.microsoft.rest.v2.RestResponse");
                imports.AddRange(restAPIMethods.SelectMany(m => MethodImplImports(m, settings)));
            }
            imports.Add(httpPipelineImport);
            if (settings.IsAzureOrFluent)
            {
                imports.Add(azureProxyImport);
                imports.Add("com.microsoft.azure.v2.AzureServiceClient");
                imports.Add(azureEnvironmentImport);
            }
            else
            {
                imports.Add(restProxyImport);
                imports.Add("com.microsoft.rest.v2.ServiceClient");
            }
            javaFile.Import(imports);

            javaFile.JavadocComment(comment =>
            {
                string serviceClientTypeName = settings.IsFluent ? serviceClientClassName : GetServiceClientInterfaceName(codeModel);
                comment.Description($"Initializes a new instance of the {serviceClientTypeName} type.");
            });
            javaFile.PublicClass(serviceClientClassDeclaration, classBlock =>
            {
                // Add proxy service member variable
                if (restAPIMethods.Any())
                {
                    classBlock.JavadocComment($"The proxy service used to perform REST calls.");
                    classBlock.PrivateMemberVariable(GetRestAPIInterfaceName(codeModel), "service");
                }

                // Add ServiceClient client property variables, getters, and setters
                IEnumerable<Property> clientProperties = GetServiceClientProperties(codeModel);
                foreach (Property clientProperty in clientProperties)
                {
                    string propertyDocumentation = clientProperty.Documentation.ToString();
                    string propertyType = IModelTypeName(IModelTypeServiceResponseVariant(GetPropertyModelType(clientProperty)), settings);
                    string propertyName = PropertyName(clientProperty);
                    string propertyNameCamelCase = propertyName.ToCamelCase();

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(propertyDocumentation);
                    });
                    classBlock.PrivateMemberVariable($"{propertyType} {propertyNameCamelCase}");

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets {propertyDocumentation}");
                        comment.Return($"the {propertyNameCamelCase} value.");
                    });
                    classBlock.PublicMethod($"{propertyType} {propertyNameCamelCase}()", function =>
                    {
                        function.Return($"this.{propertyNameCamelCase}");
                    });

                    if (!clientProperty.IsReadOnly)
                    {
                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description($"Sets {propertyDocumentation}");
                            comment.Param(propertyNameCamelCase, $"the {propertyNameCamelCase} value.");
                            comment.Return("the service client itself");
                        });
                        classBlock.PublicMethod($"{serviceClientClassName} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase})", function =>
                        {
                            function.Line($"this.{propertyNameCamelCase} = {propertyNameCamelCase};");
                            function.Return("this");
                        });
                    }
                }

                // Method Group Client declarations and getters
                foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
                {
                    string methodGroupDeclarationType = settings.IsFluent ? GetMethodGroupClientClassName(methodGroup, settings) : GetMethodGroupClientInterfaceName(methodGroup);
                    string methodGroupName = GetMethodGroupName(methodGroup);

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"The {methodGroupDeclarationType} object to access its operations.");
                    });
                    classBlock.PrivateMemberVariable(methodGroupDeclarationType, methodGroupName);

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets the {methodGroupDeclarationType} object to access its operations.");
                        comment.Return($"the {methodGroupDeclarationType} object.");
                    });
                    classBlock.PublicMethod($"{methodGroupDeclarationType} {methodGroupName}()", function =>
                    {
                        function.Return($"this.{methodGroupName}");
                    });
                }

                // Service Client Constructors
                string serviceClientInterfaceName = GetServiceClientInterfaceName(codeModel);
                string constructorDescription = $"Initializes an instance of {serviceClientInterfaceName} client.";
                if (settings.IsAzureOrFluent)
                {
                    if (HasServiceClientCredentials(codeModel))
                    {
                        string createDefaultPipelineExpression = CreateDefaultPipelineExpression(settings, serviceClientClassName + ".class", credentialsVariableName);
                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description(constructorDescription);
                            comment.Param(credentialsVariableName, credentialsDescription);
                        });
                        classBlock.PublicConstructor($"{serviceClientClassName}({serviceClientCredentialsType} {credentialsVariableName})", constructor =>
                        {
                            constructor.Line($"this({createDefaultPipelineExpression});");
                        });

                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description(constructorDescription);
                            comment.Param(credentialsVariableName, credentialsDescription);
                            comment.Param(azureEnvironmentVariableName, azureEnvironmentDescription);
                        });
                        classBlock.PublicConstructor($"{serviceClientClassName}({serviceClientCredentialsType} {credentialsVariableName}, {azureEnvironmentType} {azureEnvironmentVariableName})", constructor =>
                        {
                            constructor.Line($"this({createDefaultPipelineExpression}, {azureEnvironmentVariableName});");
                        });
                    }
                    else
                    {
                        string createDefaultPipelineExpression = CreateDefaultPipelineExpression(settings, serviceClientClassName);
                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description(constructorDescription);
                        });
                        classBlock.PublicConstructor($"{serviceClientClassName}()", constructor =>
                        {
                            constructor.Line($"this({createDefaultPipelineExpression});");
                        });

                        classBlock.JavadocComment(comment =>
                        {
                            comment.Description(constructorDescription);
                            comment.Param(azureEnvironmentVariableName, azureEnvironmentDescription);
                        });
                        classBlock.PublicConstructor($"{serviceClientClassName}({azureEnvironmentType} {azureEnvironmentVariableName})", constructor =>
                        {
                            constructor.Line($"this({createDefaultPipelineExpression}, {azureEnvironmentVariableName});");
                        });
                    }

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(constructorDescription);
                        comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    });
                    classBlock.PublicConstructor($"{serviceClientClassName}({httpPipelineType} {httpPipelineVariableName})", constructor =>
                    {
                        constructor.Line($"this({httpPipelineVariableName}, null);");
                    });

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(constructorDescription);
                        comment.Param(httpPipelineVariableName, httpPipelineDescription);
                        comment.Param(azureEnvironmentVariableName, azureEnvironmentDescription);
                    });
                    classBlock.PublicConstructor($"{serviceClientClassName}({httpPipelineType} {httpPipelineVariableName}, {azureEnvironmentType} {azureEnvironmentVariableName})", constructor =>
                    {
                        AddServiceClientConstructorBody(constructor, codeModel, settings);
                    });
                }
                else
                {
                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(constructorDescription);
                    });
                    classBlock.PublicConstructor($"{serviceClientClassName}()", constructor =>
                    {
                        constructor.Line($"this({CreateDefaultPipelineExpression(settings)});");
                    });

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(constructorDescription);
                        comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    });
                    classBlock.PublicConstructor($"{serviceClientClassName}({httpPipelineType} {httpPipelineVariableName})", constructor =>
                    {
                        AddServiceClientConstructorBody(constructor, codeModel, settings);
                    });
                }

                AddRestAPIInterface(classBlock, codeModel, settings);

                AddClientMethodOverloads(classBlock, GetRestAPIMethods(codeModel), settings);
            });

            return javaFile;
        }

        public static JavaFile GetAzureServiceClientInterfaceJavaFile(CodeModel codeModel, JavaSettings settings)
        {
            string interfaceName = GetServiceClientInterfaceName(codeModel);

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, interfaceName);

            List<string> imports = GetServiceClientInterfaceImorts(codeModel, settings).ToList();
            if (settings.IsFluent)
            {
                imports.Add("com.microsoft.azure.v2.AzureClient");
            }
            javaFile.Import(imports);

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"The interface for {interfaceName} class.");
            });
            javaFile.PublicInterface(interfaceName, interfaceBlock =>
            {
                AddServiceClientInterfacePropertyGettersAndSetters(interfaceBlock, codeModel, settings);

                foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
                {
                    string methodGroupClientInterfaceName = GetMethodGroupClientInterfaceName(methodGroup);
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets the {methodGroupClientInterfaceName} object to access its operations.");
                        comment.Return($"the {methodGroupClientInterfaceName} object.");
                    });
                    interfaceBlock.PublicMethod($"{methodGroupClientInterfaceName} {GetMethodGroupName(methodGroup).ToCamelCase()}()");
                }

                AddClientMethodOverloads(interfaceBlock, GetRestAPIMethods(codeModel), settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientJavaFile(CodeModel codeModel, JavaSettings settings, MethodGroup methodGroup)
        {
            string className = GetMethodGroupClientClassName(methodGroup, settings);

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, className);
            javaFile.Import(MethodGroupImplImports(methodGroup, settings));

            string methodGroupClientInterfaceName = GetMethodGroupClientInterfaceName(methodGroup);

            string parentDeclaration;
            if (settings.IsFluent)
            {
                IEnumerable<string> supportedInterfaces = MethodGroupSupportedInterfaces(methodGroup, settings);
                if (supportedInterfaces.Any())
                {
                    parentDeclaration = $" implements {string.Join(", ", supportedInterfaces)}";
                }
                else
                {
                    parentDeclaration = "";
                }
            }
            else
            {
                parentDeclaration = " implements " + MethodGroupTypeString(methodGroup, settings);
            }

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClientInterfaceName}.");
            });
            javaFile.PublicClass($"{className}{parentDeclaration}", classBlock =>
            {
                string restAPIInterfaceName = GetRestAPIInterfaceName(methodGroup, settings);
                string serviceClientTypeName = MethodGroupServiceClientType(methodGroup);

                classBlock.JavadocComment($"The proxy service used to perform REST calls.");
                classBlock.PrivateMemberVariable(restAPIInterfaceName, "service");

                classBlock.JavadocComment("The service client containing this operation class.");
                classBlock.PrivateMemberVariable(serviceClientTypeName, "client");

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Initializes an instance of {className}.");
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.PublicConstructor($"{className}({serviceClientTypeName} client)", constructor =>
                {
                    AddProxyVariableInitializationStatement(constructor, GetRestAPIMethods(methodGroup), GetRestAPIInterfaceName(methodGroup, settings), "client", settings);
                    constructor.Line("this.client = client;");
                });

                AddRestAPIInterface(classBlock, codeModel, methodGroup, settings);

                AddClientMethodOverloads(classBlock, GetRestAPIMethods(methodGroup), settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientInterfaceJavaFile(CodeModel codeModel, JavaSettings settings, MethodGroup methodGroup)
        {
            string methodGroupClientInterfaceName = GetMethodGroupClientInterfaceName(methodGroup);

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, methodGroupClientInterfaceName);

            IEnumerable<string> imports = methodGroup.Methods.SelectMany(method => GetClientInterfaceMethodImports(method, settings));
            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClientInterfaceName}.");
            });
            javaFile.PublicInterface(methodGroupClientInterfaceName, interfaceBlock =>
            {
                AddClientMethodOverloads(interfaceBlock, GetRestAPIMethods(methodGroup), settings);
            });
            return javaFile;
        }

        public static JavaFile GetPackageInfoJavaFiles(CodeModel codeModel, JavaSettings settings, string subPackage)
        {
            string title = codeModel.Name;
            string description = codeModel.Documentation;

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

        public static IEnumerable<CompositeType> GetModelTypes(CodeModel codeModel, JavaSettings settings)
        {
            List<CompositeType> result = new List<CompositeType>();

            foreach (CompositeType modelType in codeModel.ModelTypes.Union(codeModel.HeaderTypes))
            {
                if (!settings.IsAzure || (!CompositeTypeIsExternalExtension(modelType) && !CompositeTypeIsResource(modelType, settings)))
                {
                    result.Add(modelType);
                }
            }

            return result;
        }

        public static JavaFile GetModelJavaFile(CodeModel codeModel, JavaSettings settings, CompositeType modelType)
        {
            bool shouldGenerateXmlSerialization = codeModel.ShouldGenerateXmlSerialization;

            string className = IModelTypeName(modelType, settings);
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(GetCompositeTypeModelsPackage(modelType, settings), settings, className);

            List<string> imports = new List<string>();
            IEnumerable<Property> compositeTypeProperties = GetCompositeTypeProperties(modelType, settings);
            foreach (Property property in compositeTypeProperties)
            {
                IModelType propertyModelType = GetPropertyModelType(property);
                IEnumerable<string> propertyModelTypeImports = GetIModelTypeImports(propertyModelType, settings);
                if (propertyModelType.IsPrimaryType(KnownPrimaryType.DateTimeRfc1123) || propertyModelType.IsPrimaryType(KnownPrimaryType.Base64Url))
                {
                    imports.AddRange(propertyModelTypeImports);
                    imports.AddRange(GetIModelTypeImports(GetIModelTypeResponseVariant(propertyModelType), settings));
                }
                else if (settings.IsFluent)
                {
                    imports.AddRange(propertyModelTypeImports.Where(c => !c.StartsWith(GetPackage(settings), StringComparison.Ordinal) || c.EndsWith("Inner", StringComparison.Ordinal) ^ innerModelProperties.Contains(property)));
                }
                else
                {
                    imports.AddRange(propertyModelTypeImports.Where(c => !c.StartsWith(GetPackage(settings, "models"), StringComparison.OrdinalIgnoreCase)));
                }
            }

            if (compositeTypeProperties.Any(p => !GetSerializeAnnotationArgs(p, shouldGenerateXmlSerialization).IsNullOrEmpty()))
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonProperty");
            }
            if (compositeTypeProperties.Any(p => p.XmlIsAttribute))
            {
                imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
            }
            if (shouldGenerateXmlSerialization)
            {
                imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                if (compositeTypeProperties.Any(p => p.XmlIsWrapped && p.ModelType is SequenceType))
                {
                    imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper");
                }
            }

            // For polymorphism
            if (modelType.BaseIsPolymorphic)
            {
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                imports.Add("com.fasterxml.jackson.annotation.JsonTypeName");
                if (CompositeTypeSubTypes(modelType).Any())
                {
                    imports.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                }
            }

            // For flattening
            if (CompositeTypeNeedsFlatten(modelType, settings))
            {
                imports.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
            }

            if (settings.IsAzure)
            {
                foreach (Property property in GetCompositeTypeProperties(modelType, settings))
                {
                    IModelType propertyModelType = GetPropertyModelType(property);
                    if (propertyModelType is CompositeType compositeType && CompositeTypeIsResource(compositeType, settings))
                    {
                        imports.Add($"com.microsoft.azure.v2.{IModelTypeName(propertyModelType, settings)}");
                    }
                }

                CompositeType baseModelType = modelType.BaseModelType;
                string baseModelTypeName = IModelTypeName(baseModelType, settings);
                if (baseModelTypeName == "Resource" || baseModelTypeName == "SubResource")
                {
                    imports.Add($"com.microsoft.azure.v2.{baseModelTypeName}");
                }

                if (settings.IsFluent)
                {
                    if (baseModelTypeName != null && baseModelTypeName.EndsWith("Inner", StringComparison.Ordinal) ^ innerModelCompositeType.Contains(modelType))
                    {
                        imports.AddRange(GetIModelTypeImports(baseModelType, settings));
                    }
                }
            }
            javaFile.Import(imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                if (string.IsNullOrEmpty(modelType.Summary) && string.IsNullOrEmpty(modelType.Documentation))
                {
                    comment.Description($"The {IModelTypeName(modelType, settings)} model.");
                }
                else
                {
                    comment.Description($"{modelType.Summary.EscapeXmlComment()}{modelType.Documentation.EscapeXmlComment()}");
                }
            });

            if (modelType.BaseIsPolymorphic)
            {
                javaFile.Annotation($"JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"{modelType.BasePolymorphicDiscriminator}\")");
                javaFile.Annotation($"JsonTypeName(\"{modelType.SerializedName}\")");

                List<CompositeType> types = CompositeTypeSubTypes(modelType).ToList();
                if (types.Any())
                {
                    StringBuilder subTypeAnnotationBuilder = new StringBuilder();
                    subTypeAnnotationBuilder.AppendLine("JsonSubTypes({");

                    Func<CompositeType, bool, string> getSubTypeAnnotation = (CompositeType subType, bool isLast) =>
                    {
                        string subTypeAnnotation = $"@JsonSubTypes.Type(name = \"{subType.SerializedName}\", value = {IModelTypeName(subType, settings)}.class)";
                        if (!isLast)
                        {
                            subTypeAnnotation += ",";
                        }
                        return subTypeAnnotation;
                    };

                    foreach (CompositeType subType in types.SkipLast(1))
                    {
                        subTypeAnnotationBuilder.AppendLine(getSubTypeAnnotation(subType, false));
                    }
                    subTypeAnnotationBuilder.AppendLine(getSubTypeAnnotation(types.Last(), true));

                    subTypeAnnotationBuilder.Append("})");

                    javaFile.Annotation(subTypeAnnotationBuilder.ToString());
                }
            }
            if (shouldGenerateXmlSerialization)
            {
                javaFile.Annotation($"JacksonXmlRootElement(localName = \"{modelType.XmlName}\")");
            }

            if (CompositeTypeNeedsFlatten(modelType, settings))
            {
                javaFile.Annotation("JsonFlatten");
            }

            string classNameWithBaseType = className;
            string baseTypeName = IModelTypeName(modelType.BaseModelType, settings);
            if (!string.IsNullOrEmpty(baseTypeName))
            {
                classNameWithBaseType += $" extends {baseTypeName}";
            }
            javaFile.PublicClass(classNameWithBaseType, (classBlock) =>
            {
                IEnumerable<Property> properties = GetCompositeTypeProperties(modelType, settings);
                if (properties != null && properties.Any())
                {
                    foreach (Property property in properties)
                    {
                        classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                        {
                            if (string.IsNullOrEmpty(property.Summary) && string.IsNullOrEmpty(property.Documentation))
                            {
                                comment.Description($"The {PropertyName(property)} property.");
                            }
                            else
                            {
                                string summary = property.Summary.EscapeXmlComment();
                                if (!string.IsNullOrEmpty(summary))
                                {
                                    comment.Description(summary);
                                }

                                string documentation = property.Documentation.EscapeXmlComment();
                                if (!string.IsNullOrEmpty(documentation))
                                {
                                    comment.Description(documentation);
                                }
                            }
                        });
                        string annotationArgs = GetSerializeAnnotationArgs(property, shouldGenerateXmlSerialization);
                        if (!string.IsNullOrEmpty(annotationArgs))
                        {
                            if (property.XmlIsAttribute)
                            {
                                string localName = shouldGenerateXmlSerialization ? property.XmlName : property.SerializedName.ToString();
                                classBlock.Annotation($"JacksonXmlProperty(localName = \"{localName}\", isAttribute = true)");
                            }
                            else if (shouldGenerateXmlSerialization && property.XmlIsWrapped && property.ModelType is SequenceType)
                            {
                                string localName = shouldGenerateXmlSerialization ? property.XmlName : property.SerializedName.ToString();
                                classBlock.Annotation($"JacksonXmlElementWrapper(localName = \"{localName}\")");
                            }
                            else
                            {
                                classBlock.Annotation($"JsonProperty({annotationArgs})");
                            }
                        }
                        classBlock.PrivateMemberVariable($"{GetPropertyWireTypeName(property, settings)} {PropertyName(property)}");
                    }

                    IEnumerable<Property> constantProperties = properties.Where(property => property.IsConstant);
                    if (constantProperties.Any())
                    {
                        classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                        {
                            comment.Description($"Creates an instance of {className} class.");
                        });
                        classBlock.PublicConstructor($"{className}()", (constructor) =>
                        {
                            foreach (Property constantProperty in constantProperties)
                            {
                                bool propertyIsPrimitiveType = !(GetPropertyModelType(constantProperty) is CompositeType);
                                if (!propertyIsPrimitiveType)
                                {
                                    constructor.Line($"{PropertyName(constantProperty)} = new {GetPropertyWireTypeName(constantProperty, settings)}();");
                                }
                                else
                                {
                                    string defaultValue;
                                    try
                                    {
                                        defaultValue = GetPropertyDefaultValue(constantProperty);
                                    }
                                    catch (NotSupportedException)
                                    {
                                        defaultValue = null;
                                    }
                                    constructor.Line($"{PropertyName(constantProperty)} = {defaultValue};");
                                }
                            }
                        });
                    }

                    foreach (Property property in properties)
                    {
                        string variableName = PropertyName(property);
                        string clientTypeName = IModelTypeName(GetIModelTypeResponseVariant(GetPropertyModelType(property)), settings);
                        string wireTypeName = GetPropertyWireTypeName(property, settings);
                        bool clientTypeDifferentFromWireType = clientTypeName != wireTypeName;

                        classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                        {
                            comment.Description($"Get the {variableName} value.");
                            comment.Return($"the {variableName} value");
                        });
                        classBlock.PublicMethod($"{clientTypeName} {variableName}()", (methodBlock) =>
                        {
                            if (clientTypeDifferentFromWireType)
                            {
                                methodBlock.If($"this.{variableName} == null", (ifBlock) =>
                                {
                                    ifBlock.Return("null");
                                });
                                methodBlock.Return(ConvertProperty(wireTypeName, clientTypeName, $"this.{variableName}"));
                            }
                            else
                            {
                                methodBlock.Return($"this.{variableName}");
                            }
                        });

                        if (!property.IsReadOnly)
                        {
                            classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                            {
                                comment.Description($"Set the {variableName} value.");
                                comment.Param(variableName, $"the {variableName} value to set");
                                comment.Return($"the {className} object itself.");
                            });
                            classBlock.PublicMethod($"{className} with{variableName.ToPascalCase()}({clientTypeName} {variableName})", (methodBlock) =>
                            {
                                if (clientTypeDifferentFromWireType)
                                {
                                    methodBlock.If($"{variableName} == null", (ifBlock) =>
                                    {
                                        ifBlock.Line($"this.{variableName} = null;");
                                    })
                                    .Else((elseBlock) =>
                                    {
                                        elseBlock.Line($"this.{variableName} = {ConvertProperty(clientTypeName, wireTypeName, variableName)};");
                                    });
                                }
                                else
                                {
                                    methodBlock.Line($"this.{variableName} = {variableName};");
                                }
                                methodBlock.Return("this");
                            });
                        }
                    }
                }
            });

            return javaFile;
        }

        public static JavaFile GetExceptionJavaFile(ServiceException exception, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(exception.Subpackage, settings, exception.Name);
            javaFile.Import("com.microsoft.rest.v2.RestException",
                            "com.microsoft.rest.v2.http.HttpResponse");
            javaFile.JavadocComment((comment) =>
            {
                comment.Description($"Exception thrown for an invalid response with {exception.ErrorName} information.");
            });
            javaFile.Block($"public class {exception.Name} extends RestException", (classBlock) =>
            {
                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                });
                classBlock.Block($"public {exception.Name}(final String message, HttpResponse response)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response);");
                });
                classBlock.Line();
                classBlock.JavadocComment((comment) =>
                {
                    comment.Description($"Initializes a new instance of the {exception.Name} class.");
                    comment.Param("message", "the exception message or the response content if a message is not available");
                    comment.Param("response", "the HTTP response");
                    comment.Param("body", "the deserialized response body");
                });
                classBlock.Block($"public {exception.Name}(final String message, final HttpResponse response, final {exception.ErrorName} body)", (constructorBlock) =>
                {
                    constructorBlock.Line("super(message, response, body);");
                });
                classBlock.Line();
                classBlock.Annotation("Override");
                classBlock.Block($"public {exception.ErrorName} body()", (methodBlock) =>
                {
                    methodBlock.Return($"({exception.ErrorName}) super.body()");
                });
            });

            return javaFile;
        }

        public static JavaFile GetEnumJavaFile(ServiceEnum serviceEnum, JavaSettings settings)
        {
            string enumTypeComment = $"Defines values for {serviceEnum.Name}.";

            string subPackage = settings.IsFluent ? null : modelsPackage;
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(subPackage, settings, serviceEnum.Name);
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
                javaFile.PublicEnum(serviceEnum.Name, (enumBlock) =>
                {
                    if (serviceEnum.Values.Any())
                    {
                        Action<ServiceEnumValue, bool> enumValue = (ServiceEnumValue value, bool isLast) =>
                        {
                            enumBlock.JavadocComment($"Enum value {value.Value}.");
                            enumBlock.Line($"{value.Name}(\"{value.Value}\")" + (isLast ? ";" : ","));
                            enumBlock.Line();
                        };

                        foreach (ServiceEnumValue value in serviceEnum.Values.SkipLast(1))
                        {
                            enumValue(value, false);
                        }
                        enumValue(serviceEnum.Values.Last(), true);
                    }

                    enumBlock.JavadocComment($"The actual serialized value for a {serviceEnum.Name} instance.");
                    enumBlock.Line("private String value;");
                    enumBlock.Line();
                    enumBlock.Block($"{serviceEnum.Name}(String value)", (constructor) =>
                    {
                        constructor.Line("this.value = value;");
                    });
                    enumBlock.Line();
                    enumBlock.JavadocComment((comment) =>
                    {
                        comment.Description($"Parses a serialized value to a {serviceEnum.Name} instance.");
                        comment.Param("value", "the serialized value to parse.");
                        comment.Return($"the parsed {serviceEnum.Name} object, or null if unable to parse.");
                    });
                    enumBlock.Annotation("JsonCreator");
                    enumBlock.Block($"public static {serviceEnum.Name} fromString(String value)", (function) =>
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
                    enumBlock.Line();
                    enumBlock.Annotation("JsonValue",
                                         "Override");
                    enumBlock.Block("public String toString()", (function) =>
                    {
                        function.Return("this.value");
                    });
                });
            }

            return javaFile;
        }

        private static string GetPackage(JavaSettings settings, params string[] packageSuffixes)
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

        private static string GetAutoRestSettingsServiceName(Settings autoRestSettings)
            => GetStringSetting(autoRestSettings, "serviceName");

        internal static string GetServiceName(Settings autoRestSettings, CodeModel codeModel)
            => GetServiceName(GetAutoRestSettingsServiceName(autoRestSettings), codeModel);

        private static string GetServiceName(JavaSettings settings, CodeModel codeModel)
            => GetServiceName(settings.ServiceName, codeModel);

        private static string GetServiceName(string serviceName, CodeModel codeModel)
        {
            if (string.IsNullOrEmpty(serviceName))
            {
                Method method = codeModel.Methods[0];
                Match match = Regex.Match(input: method.Url, pattern: @"/providers/microsoft\.(\w+)/", options: RegexOptions.IgnoreCase);
                serviceName = match.Groups[1].Value.ToPascalCase();
            }
            return serviceName;
        }

        private static JavaFile GetJavaFileWithHeaderAndPackage(string subPackage, JavaSettings settings, string fileNameWithoutExtension)
        {
            string package = GetPackage(settings, subPackage);
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

        private static void AddRegularMethodOverloads(JavaType typeBlock, Method method, JavaSettings settings, bool onlyRequiredParameters)
        {
            IEnumerable<Parameter> clientMethodParameters = (onlyRequiredParameters ? GetClientMethodRequiredParameters(method) : GetClientMethodParameters(method));

            string methodName = GetMethodName(method, settings);
            Response methodReturnType = method.ReturnType;
            string methodArguments = string.Join(", ", clientMethodParameters.Select(parameter => GetParameterName(parameter)));

            // -------------
            // Synchronous T
            // -------------
            AddSynchronousMethodComment(typeBlock, method, clientMethodParameters, settings);
            typeBlock.PublicMethod(GetSynchronousMethodSignature(method, clientMethodParameters, settings), function =>
            {
                if (methodReturnType.Body == null)
                {
                    function.Line($"{methodName}Async({methodArguments}).blockingAwait();");
                }
                else
                {
                    function.Return($"{methodName}Async({methodArguments}).blockingGet()");
                }
            });

            // ----------------------
            // Async ServiceFuture<T>
            // ----------------------
            AddServiceFutureMethodComment(typeBlock, method, clientMethodParameters, settings);
            typeBlock.PublicMethod(GetServiceFutureMethodSignature(method, clientMethodParameters, settings), function =>
            {
                function.Return($"ServiceFuture.fromBody({methodName}Async({methodArguments}), {serviceCallbackVariableName})");
            });

            // -----------------------------------------
            // Async Single<RestResponse<THeader,TBody>>
            // -----------------------------------------
            string singleRestResponseReturnType = $"Single<{MethodRestResponseAbstractTypeName(method, settings)}>";
            string methodParameterDeclaration = MethodParameterDeclaration(method, settings, clientMethodParameters);

            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, clientMethodParameters, settings);
                ThrowsIllegalArgumentException(comment);
                comment.Return($"the {{@link {singleRestResponseReturnType}}} object if successful.");
            });
            typeBlock.PublicMethod($"{singleRestResponseReturnType} {methodName}WithRestResponseAsync({methodParameterDeclaration})", function =>
            {
                AddRequiredNullableParameterChecks(function, method);

                AddOptionalOrConstantParameterVariables(function, method, settings, onlyRequiredParameters);

                AddValidationChecks(function, method, onlyRequiredParameters);

                MethodBuildInputMappings(method, settings, onlyRequiredParameters, function);

                MethodParameterConversion(method, settings, MethodRetrofitParameters(method, settings), function);

                function.Return($"service.{methodName}({MethodParameterApiInvocation(method, settings)})");
            });

            // --------------
            // Async Maybe<T>
            // --------------
            string asyncMethodReturnType = (methodReturnType.Body == null ? "Completable" : $"Maybe<{ResponseServiceResponseGenericParameterString(methodReturnType, settings)}>");
            string methodParametersDeclaration = MethodParameterDeclaration(method, settings, clientMethodParameters);

            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, clientMethodParameters, settings);
                ThrowsIllegalArgumentException(comment);
                comment.Return($"the {{@link {asyncMethodReturnType}}} object if successful.");
            });
            typeBlock.PublicMethod($"{asyncMethodReturnType} {methodName}Async({methodParametersDeclaration})", function =>
            {
                function.Line($"return {methodName}WithRestResponseAsync({methodArguments})");
                function.Indent(() =>
                {
                    if (methodReturnType.Body == null)
                    {
                        function.Line(".toCompletable();");
                    }
                    else
                    {
                        string responseGenericBodyClientTypeString = ResponseGenericBodyClientTypeString(methodReturnType, settings);
                        string methodRestResponseAbstractTypeName = MethodRestResponseAbstractTypeName(method, settings);
                        function.Line($".flatMapMaybe(new Function<{methodRestResponseAbstractTypeName}, Maybe<{responseGenericBodyClientTypeString}>>() {{");
                        function.Indent(() =>
                        {
                            function.Block($"public Maybe<{responseGenericBodyClientTypeString}> apply({methodRestResponseAbstractTypeName} restResponse)", subFunction =>
                            {
                                subFunction.If("restResponse.body() == null", ifBlock =>
                                {
                                    ifBlock.Return("Maybe.empty()");
                                })
                                .Else(elseBlock =>
                                {
                                    elseBlock.Return("Maybe.just(restResponse.body())");
                                });
                            });
                        });
                        function.Line("});");
                    }
                });
            });
        }

        private static IEnumerable<Property> GetServiceClientProperties(CodeModel codeModel)
            => codeModel.Properties.Where(p => !IsServiceClientCredentialProperty(p));

        private static bool HasServiceClientCredentials(CodeModel codeModel)
            => codeModel.Properties.Any(IsServiceClientCredentialProperty);

        private static bool IsServiceClientCredentialProperty(Property property)
            => GetPropertyModelType(property).IsPrimaryType(KnownPrimaryType.Credentials);

        private static IEnumerable<string> GetServiceClientInterfaceImorts(CodeModel codeModel, JavaSettings settings)
            => GetRestAPIMethods(codeModel).SelectMany(m => GetClientInterfaceMethodImports(m, settings));

        private static IEnumerable<MethodGroup> GetMethodGroups(CodeModel codeModel)
            => codeModel.Operations.Where(operation => !string.IsNullOrEmpty(GetMethodGroupName(operation)));

        private static string GetRestAPIInterfaceName(CodeModel codeModel)
        {
            string restAPIInterfaceName = GetServiceClientInterfaceName(codeModel);
            if (!string.IsNullOrEmpty(restAPIInterfaceName))
            {
                restAPIInterfaceName += "Service";
            }
            return restAPIInterfaceName;
        }

        /// <summary>
        /// Get the REST API methods that are defined for the provided CodeModel/ServiceClient.
        /// </summary>
        /// <param name="codeModel"></param>
        /// <returns></returns>
        private static IEnumerable<Method> GetRestAPIMethods(CodeModel codeModel)
            => codeModel.Methods.Where(m => m.Group.IsNullOrEmpty());

        /// <summary>
        /// Get the REST API methods that are defined for the provided MethodGroup/MethodGroupClient.
        /// </summary>
        /// <param name="methodGroup"></param>
        /// <returns></returns>
        private static IEnumerable<Method> GetRestAPIMethods(MethodGroup methodGroup)
            => methodGroup.Methods;

        private static IModelType GetPropertyModelType(Property property)
        {
            IModelType propertyModelType = property.ModelType;
            if (propertyModelType == null)
            {
                return null;
            }
            return property.IsXNullable ?? !property.IsRequired
                ? propertyModelType
                : GetIModelTypeNonNullableVariant(propertyModelType);
        }

        private static IEnumerable<string> GetIModelTypeImports(IModelType modelType, JavaSettings settings)
        {
            IEnumerable<string> result = Enumerable.Empty<string>();

            if (modelType != null)
            {
                if (modelType is EnumType)
                {
                    string modelTypeName = IModelTypeName(modelType, settings);
                    if (modelTypeName != "String")
                    {
                        if (!settings.IsFluent)
                        {
                            result = new[]
                            {
                                string.Join(".", modelType.CodeModel?.Namespace.ToLowerInvariant(), "models", modelTypeName)
                            };
                        }
                        else
                        {
                            result = new[]
                            {
                                string.Join(".", (modelType.CodeModel?.Namespace.ToLowerInvariant()) + (modelTypeName.EndsWith("Inner") ? ".implementation" : ""), modelTypeName)
                            };
                        }
                    }
                }
                else if (modelType is CompositeType compositeType)
                {
                    string compositeTypeName = IModelTypeName(compositeType, settings);
                    bool compositeTypeIsExternalExtension = CompositeTypeIsExternalExtension(compositeType);
                    bool compositeTypeIsAzureResourceExtension = CompositeTypeIsAzureResourceExtension(compositeType);
                    result = CompositeTypeImports(compositeTypeName, compositeType.CodeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings);
                }
                else if (modelType is SequenceType sequenceType)
                {
                    result = GetIModelTypeImports(sequenceType.ElementType, settings)
                        .Concat(new[] { "java.util.List" });
                }
                else if (modelType is DictionaryType dictionaryType)
                {
                    result = GetIModelTypeImports(dictionaryType.ValueType, settings)
                        .Concat(new[] { "java.util.Map" });
                }
                else if (modelType is PrimaryType primaryType)
                {
                    switch (primaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.Base64Url:
                            result = new[] { "com.microsoft.rest.v2.Base64Url" };
                            break;
                        case KnownPrimaryType.Date:
                            result = new[] { "org.joda.time.LocalDate" };
                            break;
                        case KnownPrimaryType.DateTime:
                            result = new[] { "org.joda.time.DateTime" };
                            break;
                        case KnownPrimaryType.DateTimeRfc1123:
                            result = new[] { "com.microsoft.rest.v2.DateTimeRfc1123" };
                            break;
                        case KnownPrimaryType.Decimal:
                            result = new[] { "java.math.BigDecimal" };
                            break;
                        case KnownPrimaryType.Stream:
                            result = new[] { "com.microsoft.rest.v2.http.AsyncInputStream" };
                            break;
                        case KnownPrimaryType.TimeSpan:
                            result = new[] { "org.joda.time.Period" };
                            break;
                        case KnownPrimaryType.UnixTime:
                            result = new[] { "org.joda.time.DateTime", "org.joda.time.DateTimeZone" };
                            break;
                        case KnownPrimaryType.Uuid:
                            result = new[] { "java.util.UUID" };
                            break;
                        case KnownPrimaryType.Credentials:
                            result = new[] { serviceClientCredentialsImport };
                            break;
                        default:
                            result = Enumerable.Empty<string>();
                            break;
                    }
                }
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeImports(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, JavaSettings settings)
        {
            IEnumerable<string> result;

            if (settings.IsFluent)
            {
                result = CompositeTypeImportsFluent(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings);
            }
            else if (settings.IsAzure)
            {
                result = CompositeTypeImportsAzure(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings);
            }
            else
            {
                List<string> imports = new List<string>();
                if (compositeTypeName.Contains('<'))
                {
                    imports.AddRange(CompositeTypeGenericTypeImports(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings));
                }
                else
                {
                    imports.Add(string.Join(".", GetCompositeTypePackage(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings), compositeTypeName));
                }
                result = imports;
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeImportsFluent(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, JavaSettings settings)
        {
            List<string> result = new List<string>();
            if (compositeTypeName.Contains('<'))
            {
                result.AddRange(CompositeTypeGenericTypeImports(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings));
            }
            else
            {
                result.Add(string.Join(".", GetCompositeTypePackage(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings), compositeTypeName));
            }
            return result;
        }

        private static IEnumerable<string> CompositeTypeImportsAzure(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, JavaSettings settings)
        {
            List<string> result = new List<string>();
            if (!string.IsNullOrEmpty(compositeTypeName))
            {
                if (compositeTypeName.Contains('<'))
                {
                    result.AddRange(CompositeTypeGenericTypeImports(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings));
                }
                else if (CompositeTypeIsResource(compositeTypeName, compositeTypeIsAzureResourceExtension) || compositeTypeIsExternalExtension)
                {
                    result.Add(string.Join(".", GetCompositeTypePackage(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings), compositeTypeName));
                }
                else
                {
                    result.Add(string.Join(".", GetCompositeTypePackage(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings), "models", compositeTypeName));
                }
            }
            return result;
        }

        private static IModelType GetIModelTypeResponseVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is SequenceType sequenceTypeJv)
            {
                IModelType elementTypeResponseVariant = GetIModelTypeResponseVariant(sequenceTypeJv.ElementType);
                if (elementTypeResponseVariant != sequenceTypeJv.ElementType && PrimaryTypeNullable(elementTypeResponseVariant as PrimaryType) != false)
                {
                    SequenceType sequenceType = DependencyInjection.New<SequenceType>();
                    sequenceType.ElementType = elementTypeResponseVariant;
                    result = sequenceType;
                }
            }
            else if (modelType is DictionaryType dictionaryType)
            {
                IModelType valueTypeResponseVariant = GetIModelTypeResponseVariant(dictionaryType.ValueType);
                if (valueTypeResponseVariant != dictionaryType.ValueType && PrimaryTypeNullable(valueTypeResponseVariant as PrimaryType) != false)
                {
                    DictionaryType dictionaryTypeResult = DependencyInjection.New<DictionaryType>();
                    dictionaryTypeResult.ValueType = valueTypeResponseVariant;
                    result = dictionaryTypeResult;
                }
                else
                {
                    result = dictionaryType;
                }
            }
            else if (modelType is PrimaryType primaryType)
            {
                result = PrimaryTypeResponseVariant(primaryType);
            }

            return result;
        }

        private static string IModelTypeParameterVariantName(IModelType modelType, JavaSettings settings)
            => IModelTypeName(IModelTypeParameterVariant(modelType), settings);

        private static IModelType IModelTypeParameterVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is SequenceType sequenceType)
            {
                IModelType elementTypeResponseVariant = IModelTypeParameterVariant(sequenceType.ElementType);
                if (elementTypeResponseVariant != sequenceType.ElementType && PrimaryTypeNullable(elementTypeResponseVariant as PrimaryType) != false)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = elementTypeResponseVariant;
                    result = resultSequenceType;
                }
            }
            else if (modelType is DictionaryType dictionaryType)
            {
                IModelType valueType = dictionaryType.ValueType;
                IModelType valueTypeParameterVariant = IModelTypeParameterVariant(valueType);
                if (valueTypeParameterVariant != valueType && PrimaryTypeNullable(valueTypeParameterVariant as PrimaryType) != false)
                {
                    DictionaryType convertedValueDictionaryType = DependencyInjection.New<DictionaryType>();
                    convertedValueDictionaryType.ValueType = valueTypeParameterVariant;
                    result = convertedValueDictionaryType;
                }
            }
            else if (modelType is PrimaryType primaryType)
            {
                if (primaryType.KnownPrimaryType == KnownPrimaryType.DateTimeRfc1123)
                {
                    result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.DateTime);
                }
                else if (primaryType.KnownPrimaryType == KnownPrimaryType.UnixTime)
                {
                    result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.DateTime);
                }
                else if (primaryType.KnownPrimaryType == KnownPrimaryType.Base64Url)
                {
                    result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.ByteArray);
                }
            }

            return result;
        }

        private static IModelType GetIModelTypeNonNullableVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is PrimaryType primaryType)
            {
                PrimaryType resultPrimaryType = DependencyInjection.New<PrimaryType>(primaryType.KnownPrimaryType);
                resultPrimaryType.Format = primaryType.Format;
                primaryTypeNotWantNullable.Add(resultPrimaryType);

                result = resultPrimaryType;
            }

            return result;
        }

        private static string IModelTypeName(IModelType modelType, JavaSettings settings)
        {
            string result = null;
            if (modelType != null)
            {
                result = modelType.Name.ToString();
                if (modelType is EnumType)
                {
                    result = (string.IsNullOrEmpty(result) || result == "enum" ? "String" : CodeNamer.Instance.GetTypeName(result));
                }
                else if (modelType is SequenceType sequenceType)
                {
                    result = $"List<{IModelTypeName(sequenceType.ElementType, settings)}>";
                    if (pagedListTypes.Contains(modelType))
                    {
                        result = "Paged" + result;
                    }
                }
                else if (modelType is DictionaryType dictionaryType)
                {
                    result = $"Map<String, {IModelTypeName(dictionaryType.ValueType, settings)}>";
                }
                else if (modelType is CompositeType && settings.IsFluent)
                {
                    result = string.IsNullOrEmpty(result) || !innerModelCompositeType.Contains(modelType) ? result : result + "Inner";
                }
                else if (modelType is PrimaryType primaryType)
                {
                    switch (primaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.None:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Void" : "void";
                            break;
                        case KnownPrimaryType.Base64Url:
                            result = "Base64Url";
                            break;
                        case KnownPrimaryType.Boolean:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Boolean" : "boolean";
                            break;
                        case KnownPrimaryType.ByteArray:
                            result = "byte[]";
                            break;
                        case KnownPrimaryType.Date:
                            result = "LocalDate";
                            break;
                        case KnownPrimaryType.DateTime:
                            result = "DateTime";
                            break;
                        case KnownPrimaryType.DateTimeRfc1123:
                            result = "DateTimeRfc1123";
                            break;
                        case KnownPrimaryType.Double:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Double" : "double";
                            break;
                        case KnownPrimaryType.Decimal:
                            result = "BigDecimal";
                            break;
                        case KnownPrimaryType.Int:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Integer" : "int";
                            break;
                        case KnownPrimaryType.Long:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Long" : "long";
                            break;
                        case KnownPrimaryType.Stream:
                            result = "AsyncInputStream";
                            break;
                        case KnownPrimaryType.String:
                            result = "String";
                            break;
                        case KnownPrimaryType.TimeSpan:
                            result = "Period";
                            break;
                        case KnownPrimaryType.UnixTime:
                            result = PrimaryTypeGetWantNullable(primaryType) ? "Long" : "long";
                            break;
                        case KnownPrimaryType.Uuid:
                            result = "UUID";
                            break;
                        case KnownPrimaryType.Object:
                            result = "Object";
                            break;
                        case KnownPrimaryType.Credentials:
                            result = serviceClientCredentialsType;
                            break;

                        default:
                            throw new NotImplementedException($"Primary type {primaryType.KnownPrimaryType} is not implemented in {primaryType.GetType().Name}");
                    }
                }
            }
            return result;
        }

        private static IEnumerable<Property> GetCompositeTypeProperties(CompositeType compositeType, JavaSettings settings)
        {
            IEnumerable<Property> result = compositeType.Properties;

            if (settings.IsFluent)
            {
                bool compositeTypeIsInnerModel = innerModelCompositeType.Contains(compositeType);
                foreach (Property property in result)
                {
                    if (compositeTypeIsInnerModel)
                    {
                        innerModelProperties.Add(property);
                    }
                    else
                    {
                        innerModelProperties.Remove(property);
                    }
                }
            }

            return result;
        }

        private static string GetCompositeTypePackage(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, JavaSettings settings)
        {
            string result;

            if (settings.IsFluent)
            {
                if (CompositeTypeIsResource(compositeTypeName, compositeTypeIsAzureResourceExtension))
                {
                    result = "com.microsoft.azure.v2";
                }
                else if (compositeTypeIsExternalExtension)
                {
                    result = "com.microsoft.rest.v2";
                }
                else if (compositeTypeName.EndsWith("Inner", StringComparison.Ordinal))
                {
                    result = (codeModel?.Namespace.ToLowerInvariant()) + ".implementation";
                }
                else
                {
                    result = (codeModel?.Namespace.ToLowerInvariant());
                }
            }
            else if (settings.IsAzure)
            {
                if (CompositeTypeIsResource(compositeTypeName, compositeTypeIsAzureResourceExtension))
                {
                    result = "com.microsoft.azure.v2";
                }
                else
                {
                    if (compositeTypeIsExternalExtension)
                    {
                        result = "com.microsoft.rest.v2";
                    }
                    else
                    {
                        result = codeModel?.Namespace.ToLowerInvariant();
                    }
                }
            }
            else
            {
                if (compositeTypeIsExternalExtension)
                {
                    result = "com.microsoft.rest.v2";
                }
                else
                {
                    result = string.Join(".", codeModel?.Namespace.ToLowerInvariant(), "models");
                }
            }

            return result;
        }

        private static string GetCompositeTypeModelsPackage(CompositeType compositeType, JavaSettings settings)
        {
            string result;

            if (settings.IsFluent)
            {
                result = innerModelCompositeType.Contains(compositeType) ? ".implementation" : "";
            }
            else
            {
                result = modelsPackage;
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeGenericTypeImports(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, JavaSettings settings)
        {
            List<string> result = new List<string>();

            string[] types = compositeTypeName.Split(new String[] { "<", ">", ",", ", " }, StringSplitOptions.RemoveEmptyEntries);
            foreach (string innerTypeName in types.Where(t => !string.IsNullOrWhiteSpace(t)))
            {
                string trimmedInnerTypeName = innerTypeName.Trim();
                if (!primaryTypes.Contains(trimmedInnerTypeName))
                {
                    result.AddRange(CompositeTypeImports(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings));
                }
            }

            return result;
        }

        private static bool CompositeTypeIsResource(CompositeType compositeType, JavaSettings settings)
            => CompositeTypeIsResource(IModelTypeName(compositeType, settings), CompositeTypeIsAzureResourceExtension(compositeType));

        private static bool CompositeTypeIsResource(string compositeTypeName, bool isAzureResourceExtension)
            => compositeTypeName == "Resource" || (compositeTypeName == "SubResource" && isAzureResourceExtension);

        private static bool CompositeTypeNeedsFlatten(CompositeType compositeType, JavaSettings settings)
            => GetCompositeTypeProperties(compositeType, settings).Any(p => p.WasFlattened());

        private static string CompositeTypeExceptionTypeDefinitionName(CompositeType compositeType, JavaSettings settings)
        {
            string result = IModelTypeName(compositeType, settings) + "Exception";

            if (compositeType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
            {
                JContainer ext = compositeType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                if (ext != null && ext["name"] != null)
                {
                    result = ext["name"].ToString();
                }
            }
            return result;
        }

        private static IEnumerable<CompositeType> CompositeTypeSubTypes(CompositeType compositeType)
        {
            if (compositeType.BaseIsPolymorphic)
            {
                foreach (CompositeType type in compositeType.CodeModel.ModelTypes)
                {
                    if (type.BaseModelType != null &&
                        type.BaseModelType.SerializedName == compositeType.SerializedName)
                    {
                        yield return type;
                    }
                }
            }
        }

        private static bool GetExtensionBool(IDictionary<string, object> extensions, string extensionName)
            => extensions?.Get<bool>(extensionName) == true;

        private static bool GetExtensionBool(ModelType modelType, string extensionName)
            => GetExtensionBool(modelType?.Extensions, extensionName);

        private static bool CompositeTypeIsExternalExtension(CompositeType compositeType)
            => GetExtensionBool(compositeType, SwaggerExtensions.ExternalExtension);

        private static bool CompositeTypeIsAzureResourceExtension(CompositeType compositeType)
            => GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);

        private static string SequenceTypeGetPageImplType(IModelType modelType)
            => DictionaryGet(pageImplTypes, modelType);

        private static void SequenceTypeSetPageImplType(IModelType modelType, string pageImplType)
            => pageImplTypes[modelType] = pageImplType;

        private static Method ResponseGetParent(Response response)
            => DictionaryGet(responseParents, response);

        private static void ResponseSetParent(Response response, Method parent)
            => responseParents[response] = parent;

        private static bool ResponseIsPagedResponse(Response response)
        {
            Method parent = ResponseGetParent(response);
            return MethodIsPagingNextOperation(parent) || MethodIsPagingOperation(parent);
        }

        private static IModelType ResponseBodyClientType(Response response, JavaSettings settings)
        {
            IModelType result = GetIModelTypeResponseVariant(ResponseBodyWireType(response));
            if (settings.IsAzureOrFluent && result is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
            {
                SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                resultSequenceType.ElementType = bodySequenceType.ElementType;
                SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(bodySequenceType));
                pagedListTypes.Add(resultSequenceType);
                result = resultSequenceType;
            }
            return result;
        }

        private static TValue DictionaryGet<TKey, TValue>(IDictionary<TKey, TValue> dictionary, TKey key) where TValue : class
            => dictionary.ContainsKey(key) ? dictionary[key] : null;

        private static string ResponseGenericBodyClientTypeString(Response response, JavaSettings settings)
        {
            string result;

            if (settings.IsAzureOrFluent)
            {
                if (ResponseBodyClientType(response, settings) is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
                {
                    result = $"PagedList<{IModelTypeName(bodySequenceType.ElementType, settings)}>";
                }
                else
                {
                    IModelType responseBodyWireType = ResponseBodyWireType(response);
                    IModelType responseVariant = GetIModelTypeResponseVariant(responseBodyWireType);
                    if (PrimaryTypeNullable(responseVariant as PrimaryType) != false)
                    {
                        result = IModelTypeName(responseVariant, settings);
                    }
                    else
                    {
                        result = IModelTypeName(responseBodyWireType, settings);
                    }
                }
            }
            else
            {
                IModelType bodyWireType = ResponseBodyWireType(response);
                IModelType responseVariant = GetIModelTypeResponseVariant(bodyWireType);
                if (PrimaryTypeNullable(responseVariant as PrimaryType) != false)
                {
                    result = IModelTypeName(responseVariant, settings);
                }
                else
                {
                    result = IModelTypeName(bodyWireType, settings);
                }
            }

            return result;
        }

        private static string ResponseServiceFutureGenericParameterString(Response response, JavaSettings settings)
        {
            string result;

            if (settings.IsAzureOrFluent)
            {
                if (ResponseBodyClientType(response, settings) is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
                {
                    result = $"List<{IModelTypeName(bodySequenceType.ElementType, settings)}>";
                }
                result = ResponseGenericBodyClientTypeString(response, settings);
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response, settings);
            }

            return result;
        }

        private static string ResponseServiceResponseGenericParameterString(Response response, JavaSettings settings)
        {
            string result;

            if (settings.IsAzureOrFluent && ResponseBodyClientType(response, settings) is SequenceType bodySequenceType && (ResponseIsPagedResponse(response) || MethodSimulateAsPagingOperation(ResponseGetParent(response), settings)))
            {
                result = $"Page<{IModelTypeName(bodySequenceType.ElementType, settings)}>";
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response, settings);
            }

            return result;
        }

        private static string ResponseClientCallbackTypeString(Response response, JavaSettings settings)
        {
            string result;

            if (settings.IsAzureOrFluent && response.Body is SequenceType && ResponseIsPagedResponse(response))
            {
                result = IModelTypeName(ResponseBodyClientType(response, settings), settings);
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response, settings);
            }

            return result;
        }

        private static IModelType ResponseBodyWireType(Response response)
        {
            IModelType result = response.Body;
            if (result == null)
            {
                result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
            }
            return result;
        }

        private static IModelType ResponseHeaderClientType(Response response)
            => GetIModelTypeResponseVariant(response.Headers);

        private static string ResponseSequenceElementTypeString(Response response, JavaSettings settings)
            => response.Body is SequenceType bodySequenceType ? IModelTypeName(bodySequenceType.ElementType, settings) : "Void";

        private static string ResponseReturnValueWireType(Response response)
        {
            string returnValueWireType = null;

            IModelType body = response.Body;
            if (body != null)
            {
                Stack<IModelType> typeStack = new Stack<IModelType>();
                typeStack.Push(body);
                while (typeStack.Any())
                {
                    IModelType currentType = typeStack.Pop();
                    if (currentType is SequenceType currentSequenceType)
                    {
                        typeStack.Push(currentSequenceType.ElementType);
                    }
                    else if (currentType is DictionaryType currentDictionaryType)
                    {
                        typeStack.Push(currentDictionaryType.ValueType);
                    }
                    else if (currentType is PrimaryType currentPrimaryType)
                    {
                        string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                        if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                            currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                            currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                        {
                            returnValueWireType = currentPrimaryTypeName;
                            break;
                        }
                    }
                }
            }

            return returnValueWireType;
        }

        private static IEnumerable<string> ResponseInterfaceImports(Response response, JavaSettings settings)
            => GetIModelTypeImports(ResponseBodyClientType(response, settings), settings).Concat(GetIModelTypeImports(ResponseHeaderClientType(response), settings));

        private static IEnumerable<string> ResponseImplImports(Response response, JavaSettings settings)
        {
            List<string> imports = new List<string>(ResponseInterfaceImports(response, settings));

            IModelType bodyWireType = ResponseBodyWireType(response);
            imports.AddRange(GetIModelTypeImports(bodyWireType, settings));

            IModelType responseHeaders = response.Headers;
            imports.AddRange(GetIModelTypeImports(responseHeaders, settings));

            string returnValueWireType = ResponseReturnValueWireType(response);
            if (returnValueWireType != null)
            {
                imports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                imports.Add("com.microsoft.rest.v2." + returnValueWireType);
            }

            IModelType bodyClientType = ResponseBodyClientType(response, settings);
            IModelType headerClientType = ResponseHeaderClientType(response);
            if (((bodyWireType == null ? bodyClientType != null : !bodyWireType.StructurallyEquals(bodyClientType)) && IModelTypeName(bodyClientType, settings) != "void") ||
                (responseHeaders == null ? headerClientType != null : !responseHeaders.StructurallyEquals(headerClientType)))
            {
                IModelType responseBody = response.Body;
                if (responseBody is SequenceType || responseHeaders is SequenceType)
                {
                    imports.Add("java.util.ArrayList");
                }
                else if (responseBody is DictionaryType || responseHeaders is DictionaryType)
                {
                    imports.Add("java.util.HashMap");
                }
            }
            return imports;
        }

        private static bool MethodIsLongRunningOperation(Method method)
            => GetExtensionBool(method?.Extensions, AzureExtensions.LongRunningExtension);

        private static ISet<string> GetClientInterfaceMethodImports(Method method, JavaSettings settings)
        {
            HashSet<string> imports = new HashSet<string>();

            // static imports
            imports.Add("io.reactivex.Observable");
            imports.Add("io.reactivex.Single");
            imports.Add("com.microsoft.rest.v2.ServiceFuture");
            imports.Add("com.microsoft.rest.v2.ServiceCallback");
            imports.Add("com.microsoft.rest.v2.RestResponse");

            Response methodReturnType = method.ReturnType;
            if (methodReturnType.Body == null)
            {
                imports.Add("io.reactivex.Completable");
            }
            else
            {
                imports.Add("io.reactivex.Maybe");
            }

            // parameter types
            foreach (Parameter parameter in method.Parameters)
            {
                imports.AddRange(GetIModelTypeImports(ParameterClientType(parameter), settings));
            }

            // return type
            imports.AddRange(ResponseInterfaceImports(methodReturnType, settings));

            // exceptions
            imports.AddRange(MethodExceptionImports(method, settings));

            if (settings.IsAzure)
            {
                if (MethodIsLongRunningOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                }

                bool methodIsPagingOperation = MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method);
                if (methodIsPagingOperation)
                {
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                }

                if (settings.IsFluent)
                {
                    bool methodSimulateAsPagingOperation = MethodSimulateAsPagingOperation(method, settings);
                    if (methodIsPagingOperation || methodSimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.v2.PagedList");

                        if (methodIsPagingOperation)
                        {
                            imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                        }

                        if (!methodSimulateAsPagingOperation)
                        {
                            imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        }

                        if (ResponseBodyClientType(methodReturnType, settings) is SequenceType pageType)
                        {
                            imports.AddRange(CompositeTypeImportsFluent(SequenceTypeGetPageImplType(pageType), null, false, false, settings));
                        }
                    }
                }
            }

            return imports;
        }

        private static IEnumerable<string> MethodImplImports(Method restAPIMethod, JavaSettings settings)
        {
            HashSet<string> imports = new HashSet<string>();

            // static imports
            imports.Add("io.reactivex.Observable");
            imports.Add("io.reactivex.Single");
            imports.Add("io.reactivex.functions.Function");
            imports.Add("com.microsoft.rest.v2.annotations.Headers");
            imports.Add("com.microsoft.rest.v2.annotations.ExpectedResponses");
            if (HasUnexpectedResponseExceptionType(restAPIMethod))
            {
                imports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
            }
            imports.Add("com.microsoft.rest.v2.annotations.Host");
            imports.Add("com.microsoft.rest.v2.http.HttpClient");
            imports.Add("com.microsoft.rest.v2.ServiceFuture");
            imports.Add("com.microsoft.rest.v2.ServiceCallback");

            Response methodReturnType = restAPIMethod.ReturnType;
            if (methodReturnType.Body == null)
            {
                imports.Add("io.reactivex.Completable");
            }
            else
            {
                imports.Add("io.reactivex.Maybe");
            }

            IEnumerable<Parameter> methodRetrofitParameters = MethodRetrofitParameters(restAPIMethod, settings);
            foreach (Parameter retrofitParameter in methodRetrofitParameters)
            {
                ParameterLocation location = retrofitParameter.Location;
                if (location == ParameterLocation.Body || !NeedsSpecialSerialization(ParameterGetModelType(retrofitParameter)))
                {
                    imports.AddRange(GetIModelTypeImports(ParameterWireType(retrofitParameter), settings));
                }

                if (location != ParameterLocation.None && location != ParameterLocation.FormData)
                {
                    imports.Add($"com.microsoft.rest.v2.annotations.{location.ToString()}Param");
                }

                if (location != ParameterLocation.Body)
                {
                    IModelType modelType = ParameterGetModelType(retrofitParameter);
                    if (modelType.IsPrimaryType(KnownPrimaryType.ByteArray))
                    {
                        imports.Add("org.apache.commons.codec.binary.Base64");
                    }
                    else if (modelType is SequenceType)
                    {
                        imports.Add("com.microsoft.rest.v2.CollectionFormat");
                    }
                }
            }

            // Http verb annotations
            imports.Add($"com.microsoft.rest.v2.annotations.{restAPIMethod.HttpMethod.ToString().ToUpperInvariant()}");

            // response type conversion
            if (restAPIMethod.Responses.Any())
            {
                imports.Add("com.google.common.reflect.TypeToken");
            }

            // validation
            if (HasClientMethodParametersOrClientPropertiesToValidate(restAPIMethod))
            {
                imports.Add("com.microsoft.rest.v2.Validator");
            }

            // parameters
            IEnumerable<Parameter> methodLocalParameters = GetRestAPIMethodParameters(restAPIMethod);
            IEnumerable<Parameter> methodLogicalParameters = restAPIMethod.LogicalParameters;
            foreach (Parameter parameter in methodLocalParameters.Concat(methodLogicalParameters))
            {
                imports.AddRange(GetIModelTypeImports(ParameterClientType(parameter), settings));
            }

            // return type
            IEnumerable<string> methodReturnTypeResponseImplImports = ResponseImplImports(methodReturnType, settings);
            imports.AddRange(methodReturnTypeResponseImplImports);

            // response type (can be different from return type)
            IEnumerable<Response> methodResponses = restAPIMethod.Responses.Values;
            foreach (Response methodResponse in methodResponses)
            {
                imports.AddRange(ResponseImplImports(methodResponse, settings));
            }

            // exceptions
            imports.AddRange(MethodExceptionImports(restAPIMethod, settings));

            // parameterized host
            bool isParameterizedHost;
            bool containsParameterizedHostExtension = restAPIMethod?.CodeModel?.Extensions?.ContainsKey(SwaggerExtensions.ParameterizedHostExtension) ?? false;
            if (settings.IsAzureOrFluent)
            {
                isParameterizedHost = containsParameterizedHostExtension && !MethodIsPagingNextOperation(restAPIMethod);
            }
            else
            {
                isParameterizedHost = containsParameterizedHostExtension;
            }

            if (isParameterizedHost)
            {
                imports.Add("com.microsoft.rest.v2.annotations.HostParam");
            }

            if (settings.IsAzureOrFluent)
            {
                bool methodIsLongRunningOperation = MethodIsLongRunningOperation(restAPIMethod);
                if (methodIsLongRunningOperation)
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                    imports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");

                    restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                        .SelectMany(t => GetIModelTypeImports(t, settings))
                        .Where(i => !restAPIMethod.Parameters.Any(p => GetIModelTypeImports(ParameterGetModelType(p), settings).Contains(i)))
                        .ForEach(i => imports.Remove(i));

                    // return type may have been removed as a side effect
                    imports.AddRange(methodReturnTypeResponseImplImports);
                }
                CodeModel methodCodeModel = restAPIMethod.CodeModel;
                string typeName = SequenceTypeGetPageImplType(ResponseBodyClientType(methodReturnType, settings));
                bool methodIsPagingOperation = MethodIsPagingOperation(restAPIMethod);
                bool methodIsPagingNextOperation = MethodIsPagingNextOperation(restAPIMethod);
                bool methodIsPagingNonPollingOperation = MethodIsPagingNonPollingOperation(restAPIMethod);
                if (methodIsPagingOperation || methodIsPagingNextOperation)
                {
                    imports.Remove("java.util.ArrayList");
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                    imports.AddRange(CompositeTypeImportsAzure(typeName, methodCodeModel, false, false, settings));
                }
                else if (methodIsPagingNonPollingOperation)
                {
                    imports.AddRange(CompositeTypeImportsAzure(typeName, methodCodeModel, false, false, settings));
                }

                if (settings.IsFluent)
                {
                    string methodOperationExceptionTypeString = MethodOperationExceptionTypeString(restAPIMethod, settings);
                    if (methodOperationExceptionTypeString != "CloudException" && methodOperationExceptionTypeString != "RestException")
                    {
                        imports.RemoveWhere(i => CompositeTypeImportsAzure(methodOperationExceptionTypeString, methodCodeModel, false, false, settings).Contains(i));
                        imports.AddRange(CompositeTypeImportsFluent(methodOperationExceptionTypeString, methodCodeModel, false, false, settings));
                    }
                    if (methodIsLongRunningOperation)
                    {
                        restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                            .SelectMany(t => GetIModelTypeImports(t, settings))
                            .Where(i => !restAPIMethod.Parameters.Any(p => GetIModelTypeImports(ParameterGetModelType(p), settings).Contains(i)))
                            .ForEach(i => imports.Remove(i));
                        // return type may have been removed as a side effect
                        imports.AddRange(ResponseImplImports(restAPIMethod.ReturnType, settings));
                    }

                    SequenceType pageType = ResponseBodyClientType(restAPIMethod.ReturnType, settings) as SequenceType;
                    bool methodSimulateAsPagingOperation = MethodSimulateAsPagingOperation(restAPIMethod, settings);
                    if (methodIsPagingOperation || methodIsPagingNextOperation || methodSimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.v2.PagedList");

                        if (methodIsPagingOperation || methodIsPagingNextOperation)
                        {
                            imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                        }

                        if (!methodSimulateAsPagingOperation)
                        {
                            imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        }

                        imports.Remove("java.util.ArrayList");
                        imports.Add("com.microsoft.azure.v2.Page");
                    }

                    if ((methodIsPagingOperation || methodIsPagingNextOperation || methodSimulateAsPagingOperation || methodIsPagingNonPollingOperation) && pageType != null)
                    {
                        imports.RemoveWhere(i => CompositeTypeImportsAzure(SequenceTypeGetPageImplType(ResponseBodyClientType(methodReturnType, settings)), methodCodeModel, false, false, settings).Contains(i));
                    }
                }
            }

            return imports;
        }

        private static bool IsTopLevelResourceUrl(string url)
        {
            string[] urlSplits = url.Split('/');
            return urlSplits.Count() == 8 && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[0], "subscriptions")
                                && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "resourceGroups")
                                && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[4], "providers");
        }

        private enum MethodType
        {
            Other,
            ListBySubscription,
            ListByResourceGroup,
            Get,
            Delete
        }

        private static MethodType GetMethodType(Method method, JavaSettings settings)
        {
            Regex leading = new Regex("^/+");
            Regex trailing = new Regex("/+$");
            string methodUrl = trailing.Replace(leading.Replace(method.Url, ""), "");
            HttpMethod methodHttpMethod = method.HttpMethod;
            if (methodHttpMethod == HttpMethod.Get)
            {
                string[] urlSplits = methodUrl.Split('/');
                if ((urlSplits.Count() == 5 || urlSplits.Count() == 7)
                    && StringComparer.OrdinalIgnoreCase.Equals(urlSplits[0], "subscriptions")
                    && MethodHasSequenceType(method.ReturnType.Body, settings))
                {
                    if (urlSplits.Count() == 5)
                    {
                        if (StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "providers"))
                        {
                            return MethodType.ListBySubscription;
                        }
                        else
                        {
                            return MethodType.ListByResourceGroup;
                        }
                    }
                    else if (StringComparer.OrdinalIgnoreCase.Equals(urlSplits[2], "resourceGroups"))
                    {
                        return MethodType.ListByResourceGroup;
                    }
                }
                if (IsTopLevelResourceUrl(methodUrl))
                {
                    return MethodType.Get;
                }
            }
            else if (methodHttpMethod == HttpMethod.Delete)
            {
                if (method.Name.ToLowerInvariant().StartsWith("begin")
                    || method.MethodGroup.Methods.Count(x => x.HttpMethod == HttpMethod.Delete) > 1)
                {
                    return MethodType.Other;
                }

                if (IsTopLevelResourceUrl(methodUrl))
                {
                    return MethodType.Delete;
                }
            }

            return MethodType.Other;
        }

        private static bool MethodSimulateAsPagingOperation(Method method, JavaSettings settings)
        {
            bool result = false;

            string wellKnownMethodName = MethodWellKnownMethodName(method, settings);
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                MethodType methodType = GetMethodType(method, settings);
                if (methodType == MethodType.ListByResourceGroup || methodType == MethodType.ListBySubscription)
                {
                    result = true;
                }
            }

            return result;
        }

        private static string GetMethodName(Method method, JavaSettings settings)
        {
            string result = method.Name;

            string wellKnownMethodName = MethodWellKnownMethodName(method, settings);
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                IParent methodParent = method.Parent;
                string uniqueName = CodeNamer.Instance.GetUnique(wellKnownMethodName, method, methodParent.IdentifiersInScope, methodParent.Children.Except(method.SingleItemAsEnumerable()));
                if (uniqueName != result)
                {
                    result = uniqueName;
                }
            }

            return result.ToCamelCase();
        }

        private static void SetMethodName(Method method, string methodName)
        {
            method.Name = methodName;
        }

        private static string MethodWellKnownMethodName(Method method, JavaSettings settings)
        {
            string result = null;

            MethodGroup methodGroup = method.MethodGroup;
            if (!string.IsNullOrEmpty(GetMethodGroupName(methodGroup)))
            {
                MethodType methodType = GetMethodType(method, settings);
                if (methodType != MethodType.Other)
                {
                    if (methodGroup.Methods.Count(methodGroupMethod => GetMethodType(methodGroupMethod, settings) == methodType) == 1)
                    {
                        switch (methodType)
                        {
                            case MethodType.ListBySubscription:
                                result = List;
                                break;

                            case MethodType.ListByResourceGroup:
                                result = ListByResourceGroup;
                                break;

                            case MethodType.Delete:
                                result = Delete;
                                break;

                            case MethodType.Get:
                                result = GetByResourceGroup;
                                break;

                            default:
                                throw new Exception("Flow should not hit this statement.");
                        }
                    }
                }
            }

            return result;
        }

        private static bool MethodIsPagingNextOperation(Method method)
            => GetExtensionBool(method?.Extensions, "nextLinkMethod");

        private static bool MethodIsPagingOperation(Method method)
            => method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                method.Extensions[AzureExtensions.PageableExtension] != null &&
                !MethodIsPagingNextOperation(method);

        private static bool MethodIsPagingNonPollingOperation(Method method)
            => method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                    method.Extensions[AzureExtensions.PageableExtension] == null &&
                    !MethodIsPagingNextOperation(method);

        private static string MethodOperationExceptionTypeString(Method method, JavaSettings settings)
        {
            string result;

            if (settings.IsAzureOrFluent)
            {
                if (method.DefaultResponse.Body == null || IModelTypeName(method.DefaultResponse.Body, settings) == "CloudError")
                {
                    result = "CloudException";
                }
                else if (method.DefaultResponse.Body is CompositeType)
                {
                    CompositeType type = method.DefaultResponse.Body as CompositeType;
                    result = CompositeTypeExceptionTypeDefinitionName(type, settings);
                }
                else
                {
                    result = "RestException";
                }
            }
            else
            {
                if (method.DefaultResponse.Body is CompositeType)
                {
                    CompositeType type = method.DefaultResponse.Body as CompositeType;
                    result = CompositeTypeExceptionTypeDefinitionName(type, settings);
                }
                else
                {
                    result = "RestException";
                }
            }

            return result;
        }

        private static IEnumerable<Parameter> MethodRetrofitParameters(Method method, JavaSettings settings)
        {
            List<Parameter> parameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

            if (settings.IsAzureOrFluent && MethodIsPagingNextOperation(method))
            {
                parameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                nextUrlParam.SerializedName = "nextUrl";
                nextUrlParam.Documentation = "The URL to get the next page of items.";
                nextUrlParam.Location = ParameterLocation.Path;
                nextUrlParam.IsRequired = true;
                ParameterSetModelType(nextUrlParam, DependencyInjection.New<PrimaryType>(KnownPrimaryType.String));
                SetParameterName(nextUrlParam, "nextUrl");
                nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                parameters.Insert(0, nextUrlParam);
            }

            return parameters;
        }

        private static string MethodParameterDeclaration(Method method, JavaSettings settings, IEnumerable<Parameter> parameters)
        {
            string parameterPrefix = (settings.IsAzureOrFluent && (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method)) ? "final " : "");
            return string.Join(", ", parameters.Select(parameter =>
            {
                string parameterType = IModelTypeParameterVariantName(ParameterClientType(parameter), settings);
                string parameterName = GetParameterName(parameter);
                return $"{parameterPrefix}{parameterType} {parameterName}";
            }));
        }

        private static IEnumerable<string> MethodExceptionImports(Method method, JavaSettings settings)
        {
            HashSet<string> exceptionImports = new HashSet<string>();
            exceptionImports.Add("java.io.IOException");

            string methodOperationExceptionTypeImport = null;
            string methodOperationExceptionTypeName = MethodOperationExceptionTypeString(method, settings);
            switch (methodOperationExceptionTypeName)
            {
                case "CloudException":
                    methodOperationExceptionTypeImport = "com.microsoft.azure.v2.CloudException";
                    break;
                case "RestException":
                    methodOperationExceptionTypeImport = "com.microsoft.rest.v2.RestException";
                    break;
                default:
                    methodOperationExceptionTypeImport = $"{method.CodeModel.Namespace.ToLowerInvariant()}.models.{methodOperationExceptionTypeName}";
                    break;
            }
            exceptionImports.Add(methodOperationExceptionTypeImport);

            return exceptionImports;
        }

        private static string MethodReturnTypeResponseName(Method method, JavaSettings settings)
            => IModelTypeName(IModelTypeServiceResponseVariant(ResponseBodyClientType(method.ReturnType, settings)), settings);

        private static void MethodPagingGroupedParameterTransformation(Method method, bool filterRequired, JavaSettings settings, JavaBlock block)
        {
            if (MethodIsPagingOperation(method))
            {
                string invocation;
                Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, settings, out invocation);
                if (!method.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    Parameter groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                    Parameter nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;

                    string nextGroupTypeName = GetParameterName(nextGroupType);
                    string groupedTypeName = GetParameterName(groupedType);
                    if (nextGroupTypeName != groupedTypeName && (!filterRequired || groupedType.IsRequired))
                    {
                        string nextGroupTypeCamelCaseName = nextGroupTypeName.ToCamelCase();
                        string groupedTypeCamelCaseName = groupedTypeName.ToCamelCase();

                        string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(nextGroupTypeName) + (settings.IsFluent ? "Inner" : "");

                        if (!groupedType.IsRequired)
                        {
                            block.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = null;");
                            block.Line($"if ({groupedTypeCamelCaseName} != null) {{");
                            block.IncreaseIndent();
                            block.Line($"{nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                        }
                        else
                        {
                            block.Line($"{nextGroupTypeCodeName} {nextGroupTypeCamelCaseName} = new {nextGroupTypeCodeName}();");
                        }

                        foreach (Parameter outParam in nextMethod.InputParameterTransformation.Select(t => t.OutputParameter))
                        {
                            string outParamName = GetParameterName(outParam);
                            block.Line($"{nextGroupTypeCamelCaseName}.with{outParamName.ToPascalCase()}({groupedTypeCamelCaseName}.{outParamName.ToCamelCase()}());");
                        }

                        if (!groupedType.IsRequired)
                        {
                            block.DecreaseIndent();
                            block.Line("}");
                        }
                    }
                }
            }
        }

        private static string MethodNextMethodParameterInvocation(Method method, JavaSettings settings, bool filterRequired)
        {
            string invocation;
            Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, settings, out invocation);
            if (filterRequired)
            {
                if (method.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    return string.Join(", ", GetRestAPIMethodParameters(nextMethod).Select(p => p.IsRequired ? GetParameterName(p) : "null"));
                }
                var groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                List<string> invocations = new List<string>();
                foreach (Parameter parameter in GetRestAPIMethodParameters(nextMethod))
                {
                    if (parameter.IsRequired)
                    {
                        invocations.Add(GetParameterName(parameter));
                    }
                    else if (GetParameterName(parameter) == GetParameterName(nextGroupType) && groupedType.IsRequired)
                    {
                        invocations.Add(GetParameterName(parameter));
                    }
                    else
                    {
                        invocations.Add("null");
                    }
                }
                return string.Join(", ", invocations);
            }
            else
            {
                return ArgumentList(GetRestAPIMethodParameters(nextMethod).Where(p => !p.IsConstant));
            }
        }

        private static string MethodPagingNextPageLinkParameterName(Method method, JavaSettings settings)
        {
            string invocation;
            Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, settings, out invocation);
            return GetParameterName(nextMethod.Parameters.First(p => GetParameterName(p).StartsWith("next", StringComparison.OrdinalIgnoreCase)));
        }

        private static Method MethodGetPagingNextMethodWithInvocation(Method method, JavaSettings settings, out string invocation, bool async = false, bool singlePage = true)
        {
            string methodSuffixString = "";
            if (singlePage)
            {
                methodSuffixString = "SinglePage";
            }
            if (MethodIsPagingNextOperation(method))
            {
                invocation = GetMethodName(method, settings) + methodSuffixString + (async ? "Async" : "");
                return method;
            }
            string name = method.Extensions.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
            string group = method.Extensions.GetValue<Fixable<string>>("nextMethodGroup")?.ToCamelCase();
            group = CodeNamer.Instance.GetMethodGroupName(group);

            // The PagingNextMethod can be located in a different method group than this one.
            // It may or may not be explicitly declared.
            Method methodModel =
                method.CodeModel.Methods.FirstOrDefault(m =>
                    (group == null ? m.Group == null : group.Equals(m.Group, StringComparison.OrdinalIgnoreCase))
                    && GetMethodName(m, settings).Equals(name, StringComparison.OrdinalIgnoreCase));
            group = group.ToPascalCase();
            name = name + methodSuffixString;
            if (async)
            {
                name = name + "Async";
            }
            if (group == null || method.Group == methodModel.Group)
            {
                invocation = name;
            }
            else
            {
                invocation = $"{MethodClientReference(method).Replace("this.", "")}.get{group}().{name}";
            }
            return methodModel;
        }

        private static string MethodGetPagingNextMethodInvocation(Method method, JavaSettings settings, bool singlePage = true)
        {
            string invocation;
            MethodGetPagingNextMethodWithInvocation(method, settings, out invocation, true, singlePage);
            return invocation;
        }

        private static IEnumerable<Parameter> MethodOrderedRetrofitParameters(Method method, JavaSettings settings)
        {
            IEnumerable<Parameter> retrofitParameters = MethodRetrofitParameters(method, settings);
            return retrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                .Union(retrofitParameters.Where(p => p.Location != ParameterLocation.Path));
        }

        private static string ArgumentList(IEnumerable<Parameter> parameters)
            => string.Join(", ", parameters.Select(p => GetParameterName(p)));

        private static string MethodParameterApiInvocation(Method method, JavaSettings settings)
        {
            bool shouldUseXmlSerialization = method.CodeModel.ShouldGenerateXmlSerialization;

            IEnumerable<string> arguments = MethodOrderedRetrofitParameters(method, settings)
                .Select(parameter => shouldUseXmlSerialization && (ParameterWireType(parameter) is SequenceType)
                    ? $"new {ParameterWireType(parameter).XmlName}Wrapper({ParameterWireName(parameter)})"
                    : ParameterWireName(parameter));

            return string.Join(", ", arguments);
        }

        private static string MethodRestResponseHeadersName(Method method, JavaSettings settings)
            => method.ReturnType.Headers == null
                ? "Void"
                : IModelTypeName(ResponseHeaderClientType(method.ReturnType), settings);

        private static string MethodRestResponseAbstractTypeName(Method method, JavaSettings settings)
        {
            Response methodReturnType = method.ReturnType;
            string deserializedResponseHeadersType = MethodRestResponseHeadersName(method, settings);
            string deserializedResponseBodyType = methodReturnType.Body == null ? "Void" : ResponseServiceResponseGenericParameterString(methodReturnType, settings);
            return $"RestResponse<{deserializedResponseHeadersType}, {deserializedResponseBodyType}>";
        }

        private static string MethodRestResponseConcreteTypeName(Method method, JavaSettings settings)
        {
            Response methodReturnType = method.ReturnType;

            string deserializedResponseHeadersType = MethodRestResponseHeadersName(method, settings);

            string deserializedResponseBodyType;
            if (methodReturnType.Body == null)
            {
                deserializedResponseBodyType = "Void";
            }
            else if (settings.IsAzureOrFluent && ResponseBodyClientType(methodReturnType, settings) is SequenceType bodySequenceType && (ResponseIsPagedResponse(methodReturnType) || MethodSimulateAsPagingOperation(ResponseGetParent(methodReturnType), settings)))
            {
                deserializedResponseBodyType = $"{SequenceTypeGetPageImplType(bodySequenceType)}<{IModelTypeName(bodySequenceType.ElementType, settings)}>";
            }
            else
            {
                deserializedResponseBodyType = ResponseGenericBodyClientTypeString(methodReturnType, settings);
            }

            return $"RestResponse<{deserializedResponseHeadersType}, {deserializedResponseBodyType}>";
        }

        private static string MethodClientReference(Method method)
            => method.Group.IsNullOrEmpty() ? "this" : "this.client";

        private static void MethodParameterConversion(Method method, JavaSettings settings, IEnumerable<Parameter> parameters, JavaBlock block)
        {
            string methodClientReference = MethodClientReference(method);
            foreach (Parameter parameter in parameters)
            {
                if (ParameterNeedsConversion(parameter))
                {
                    string source = GetParameterName(parameter);

                    bool addedConversion = false;
                    ParameterLocation parameterLocation = parameter.Location;
                    if (parameterLocation != ParameterLocation.Body &&
                        parameterLocation != ParameterLocation.FormData &&
                        NeedsSpecialSerialization(ParameterGetModelType(parameter)))
                    {
                        IModelType clientType = ParameterClientType(parameter);
                        IModelType wireType = ParameterWireType(parameter);
                        string wireTypeName = IModelTypeName(wireType, settings);
                        string wireName = ParameterWireName(parameter);

                        if (clientType is PrimaryType primaryClientType && primaryClientType.IsPrimaryType(KnownPrimaryType.ByteArray))
                        {
                            if (wireType.IsPrimaryType(KnownPrimaryType.String))
                            {
                                block.Line($"{wireTypeName} {wireName} = Base64.encodeBase64String({source});");
                            }
                            else
                            {
                                block.Line($"{wireTypeName} {wireName} = Base64Url.encode({source});");
                            }
                            addedConversion = true;
                        }
                        else if (clientType is SequenceType)
                        {
                            block.Line("{0} {1} = {2}.serializerAdapter().serializeList({3}, CollectionFormat.{4});",
                                wireTypeName,
                                wireName,
                                methodClientReference,
                                source,
                                parameter.CollectionFormat.ToString().ToUpperInvariant());
                            addedConversion = true;
                        }
                    }

                    if (!addedConversion)
                    {
                        ParameterConvertClientTypeToWireType(block, settings, parameter, ParameterWireType(parameter), source, ParameterWireName(parameter), methodClientReference);
                    }
                }
            }
        }

        private static void MethodBuildInputMappings(Method method, JavaSettings settings, bool filterRequired, JavaBlock block)
        {
            foreach (ParameterTransformation transformation in method.InputParameterTransformation)
            {
                Parameter transformationOutputParameter = transformation.OutputParameter;
                string outParamName = GetParameterName(transformationOutputParameter);
                while (method.Parameters.Any(p => GetParameterName(p) == outParamName))
                {
                    outParamName += "1";
                }

                SetParameterName(transformationOutputParameter, outParamName);

                IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired).Select(m => GetParameterName(m.InputParameter) + " != null"));
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !filterRequired;
                if (conditionalAssignment)
                {
                    block.Line("{0} {1} = null;",
                        IModelTypeParameterVariantName(ParameterClientType(transformationOutputParameter), settings),
                        outParamName);
                    block.Line($"if ({nullCheck}) {{");
                    block.IncreaseIndent();
                }

                IModelType transformationOutputParameterModelType = ParameterGetModelType(transformationOutputParameter);
                bool transformationOutputParameterModelIsCompositeType = transformationOutputParameterModelType is CompositeType;
                string transformationOutputParameterClientParameterVariantTypeName = IModelTypeParameterVariantName(ParameterClientType(transformationOutputParameter), settings);
                if (transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) && transformationOutputParameterModelIsCompositeType)
                {
                    block.Line("{0}{1} = new {2}();",
                        !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        IModelTypeName(transformationOutputParameterModelType, settings));
                }

                foreach (ParameterMapping mapping in transformationParameterMappings)
                {
                    string inputPath = GetParameterName(mapping.InputParameter);
                    if (mapping.InputParameterProperty != null)
                    {
                        inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()";
                    }
                    if (filterRequired && !mapping.InputParameter.IsRequired)
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

                    block.Line("{0}{1}{2};",
                        !conditionalAssignment && !transformationOutputParameterModelIsCompositeType ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                        outParamName,
                        getMapping);
                }

                if (conditionalAssignment)
                {
                    block.DecreaseIndent();
                    block.Line("}");
                }
            }
        }

        private static void AddRequiredNullableParameterChecks(JavaBlock block, Method method)
        {
            IEnumerable<Parameter> requiredNullableParameters = method.Parameters.Where((Parameter parameter) =>
            {
                bool result = !parameter.IsConstant && parameter.IsRequired;
                if (result)
                {
                    IModelType parameterModelType = ParameterGetModelType(parameter);
                    result = !parameterModelType.IsPrimaryType(KnownPrimaryType.Int) &&
                             !parameterModelType.IsPrimaryType(KnownPrimaryType.Double) &&
                             !parameterModelType.IsPrimaryType(KnownPrimaryType.Boolean) &&
                             !parameterModelType.IsPrimaryType(KnownPrimaryType.Long) &&
                             !parameterModelType.IsPrimaryType(KnownPrimaryType.UnixTime);
                }
                return result;
            });

            foreach (Parameter requiredNullableParameter in requiredNullableParameters)
            {
                block.If($"{GetParameterName(requiredNullableParameter)} == null", ifBlock =>
                {
                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {GetParameterName(requiredNullableParameter)} is required and cannot be null.\");");
                });
            }
        }

        private static void AddValidationChecks(JavaBlock block, Method method, bool onlyRequiredParameters)
        {
            IEnumerable<Parameter> parametersToValidate = GetClientMethodParametersAndClientPropertiesToValidate(method);
            if (onlyRequiredParameters)
            {
                parametersToValidate = parametersToValidate.Where(p => p.IsRequired);
            }
            foreach (Parameter param in parametersToValidate)
            {
                block.Line($"Validator.validate({GetParameterName(param)});");
            }
        }

        private static void AddOptionalOrConstantParameterVariables(JavaBlock block, Method restAPIMethod, JavaSettings settings, bool addOptionalParameterVariables)
        {
            foreach (Parameter parameter in GetRestAPIMethodParameters(restAPIMethod))
            {
                string parameterName = GetParameterName(parameter);
                IModelType parameterClientType = ParameterClientType(parameter);

                if (addOptionalParameterVariables && !parameter.IsRequired)
                {
                    block.Line($"final {IModelTypeName(parameterClientType, settings)} {parameterName} = {IModelTypeDefaultValue(parameterClientType, restAPIMethod, settings) ?? "null"};");
                }

                if (parameter.IsConstant)
                {
                    block.Line($"final {IModelTypeParameterVariantName(parameterClientType, settings)} {parameterName} = {GetParameterDefaultValue(parameter) ?? "null"};");
                }
            }
        }

        private static bool HasClientMethodParametersOrClientPropertiesToValidate(Method method)
            => GetClientMethodParametersAndClientPropertiesToValidate(method).Any();

        private static IEnumerable<Parameter> GetClientMethodParametersAndClientPropertiesToValidate(Method method)
            => method.Parameters.Where(parameter =>
            {
                bool result = !parameter.IsConstant;
                if (result)
                {
                    IModelType parameterModelType = ParameterGetModelType(parameter);
                    result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType);
                }
                return result;
            });

        /// <summary>
        /// Get the required Client method overload parameters of the provided REST API method.
        /// </summary>
        /// <param name="restAPIMethod"></param>
        /// <returns></returns>
        private static IEnumerable<Parameter> GetClientMethodRequiredParameters(Method restAPIMethod)
            => GetClientMethodParameters(restAPIMethod).Where(parameter => parameter.IsRequired);

        /// <summary>
        /// Get the Client method overload parameters of the provided REST API method.
        /// </summary>
        /// <param name="restAPIMethod">The REST API method</param>
        /// <returns></returns>
        private static IEnumerable<Parameter> GetClientMethodParameters(Method restAPIMethod)
            => GetRestAPIMethodParameters(restAPIMethod).Where(parameter => !parameter.IsConstant);

        private static bool HasOptionalClientMethodParameters(Method restAPIMethod)
            => GetClientMethodParameters(restAPIMethod).Any(parameter => !parameter.IsRequired);

        /// <summary>
        /// Get the parameters for the provided REST API method.
        /// </summary>
        /// <param name="restAPIMethod">The REST API method</param>
        /// <returns></returns>
        private static IEnumerable<Parameter> GetRestAPIMethodParameters(Method restAPIMethod)
            => restAPIMethod.Parameters
                //Omit parameter-group properties for now since Java doesn't support them yet
                .Where(p => p != null && !p.IsClientProperty && !string.IsNullOrWhiteSpace(GetParameterName(p)))
                .OrderBy(item => !item.IsRequired);

        private static bool MethodHasSequenceType(IModelType modelType, JavaSettings settings)
        {
            if (modelType is SequenceType)
            {
                return true;
            }

            if (modelType is CompositeType ct)
            {
                return GetCompositeTypeProperties(ct, settings).Any(p => MethodHasSequenceType(GetPropertyModelType(p), settings));
            }

            return false;
        }

        private static IEnumerable<string> MethodGroupImplImports(MethodGroup methodGroup, JavaSettings settings)
        {
            IEnumerable<string> result;

            if (settings.IsFluent)
            {
                List<string> interfacesToImport = new List<string>();

                Method getMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), GetByResourceGroup));
                if (getMethod != null && TakesTwoRequiredParameters(getMethod))
                {
                    interfacesToImport.Add(innerSupportsGetImport);
                }

                Method deleteMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), Delete));
                if (deleteMethod != null && TakesTwoRequiredParameters(deleteMethod))
                {
                    interfacesToImport.Add(innerSupportsDeleteImport);
                }

                Method listMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), List));
                Method listByResourceGroup = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), ListByResourceGroup));
                bool anyMethodSupportsInnerListing = listMethod != null && listByResourceGroup != null
                    && StringComparer.OrdinalIgnoreCase.Equals(
                        ResponseSequenceElementTypeString(listMethod.ReturnType, settings),
                        ResponseSequenceElementTypeString(listByResourceGroup.ReturnType, settings));
                if (anyMethodSupportsInnerListing)
                {
                    interfacesToImport.Add(innerSupportsListingImport);
                }

                List<string> imports = new List<string>();
                imports.AddRange(interfacesToImport);

                List<string> azureImplImports = new List<string>();
                azureImplImports.Add("com.microsoft.rest.v2.RestProxy");
                azureImplImports.Add("com.microsoft.rest.v2.RestResponse");
                if (MethodGroupTypeString(methodGroup, settings) == GetMethodGroupClientInterfaceName(methodGroup))
                {
                    azureImplImports.Add(GetMethodGroupClientInterfacePath(methodGroup, settings));
                }
                azureImplImports.AddRange(methodGroup.Methods.SelectMany(m => MethodImplImports(m, settings)));
                azureImplImports.Add("com.microsoft.azure.v2.AzureProxy");
                azureImplImports.Remove("com.microsoft.rest.v2.RestProxy");

                string ns = methodGroup.CodeModel.Namespace.ToLowerInvariant();
                foreach (string azureImplImport in azureImplImports)
                {
                    if (azureImplImport.StartsWith(ns + ".implementation", StringComparison.OrdinalIgnoreCase))
                    {
                        // Same package, do nothing
                    }
                    else if (azureImplImport == ns + "." + GetMethodGroupClientInterfaceName(methodGroup))
                    {
                        // do nothing
                    }
                    else
                    {
                        imports.Add(azureImplImport);
                    }
                }

                result = imports;
            }
            else if (settings.IsAzure)
            {
                List<string> imports = new List<string>();
                imports.Add("com.microsoft.rest.v2.RestProxy");
                imports.Add("com.microsoft.rest.v2.RestResponse");
                if (MethodGroupTypeString(methodGroup, settings) == GetMethodGroupClientInterfaceName(methodGroup))
                {
                    imports.Add(GetMethodGroupClientInterfacePath(methodGroup, settings));
                }
                imports.AddRange(methodGroup.Methods.SelectMany(m => MethodImplImports(m, settings)));
                imports.Add("com.microsoft.azure.v2.AzureProxy");
                imports.Remove("com.microsoft.rest.v2.RestProxy");
                result = imports;
            }
            else
            {
                List<string> imports = new List<string>();
                imports.Add("com.microsoft.rest.v2.RestProxy");
                imports.Add("com.microsoft.rest.v2.RestResponse");
                if (MethodGroupTypeString(methodGroup, settings) == GetMethodGroupClientInterfaceName(methodGroup))
                {
                    imports.Add(GetMethodGroupClientInterfacePath(methodGroup, settings));
                }
                imports.AddRange(methodGroup.Methods.SelectMany(m => MethodImplImports(m, settings)));
                result = imports;
            }

            return result;
        }

        private static string MethodGroupServiceClientType(MethodGroup methodGroup)
            => methodGroup.CodeModel.Name + "Impl";

        private static bool TakesTwoRequiredParameters(Method method)
        {
            // When parameters are optional we generate more methods.
            return GetClientMethodParameters(method).Count(x => x.IsRequired) == 2;
        }

        private static IEnumerable<string> MethodGroupSupportedInterfaces(MethodGroup methodGroup, JavaSettings settings)
        {
            List<string> result = new List<string>();

            const string InnerSupportsGet = "InnerSupportsGet";
            const string InnerSupportsDelete = "InnerSupportsDelete";
            const string InnerSupportsListing = "InnerSupportsListing";

            Method getMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), GetByResourceGroup));
            if (getMethod != null && TakesTwoRequiredParameters(getMethod))
            {
                result.Add($"{InnerSupportsGet}<{ResponseGenericBodyClientTypeString(getMethod.ReturnType, settings)}>");
            }

            Method deleteMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), Delete));
            if (deleteMethod != null && TakesTwoRequiredParameters(deleteMethod))
            {
                result.Add($"{InnerSupportsDelete}<{ResponseClientCallbackTypeString(deleteMethod.ReturnType, settings)}>");
            }

            Method listMethod = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), List));
            Method listByResourceGroup = methodGroup.Methods.FirstOrDefault(x => StringComparer.OrdinalIgnoreCase.Equals(GetMethodName(x, settings), ListByResourceGroup));
            bool anyMethodSupportsInnerListing = listMethod != null && listByResourceGroup != null
                && StringComparer.OrdinalIgnoreCase.Equals(
                    ResponseSequenceElementTypeString(listMethod.ReturnType, settings),
                    ResponseSequenceElementTypeString(listByResourceGroup.ReturnType, settings));
            if (anyMethodSupportsInnerListing)
            {
                result.Add($"{InnerSupportsListing}<{ResponseSequenceElementTypeString(listMethod.ReturnType, settings)}>");
            }

            return result;
        }

        private static string GetMethodGroupClientInterfacePath(MethodGroup methodGroup, JavaSettings settings)
            => settings.Package + "." + GetMethodGroupClientInterfaceName(methodGroup);

        private static string MethodGroupTypeString(MethodGroup methodGroup, JavaSettings settings)
        {
            string methodGroupClientInterfaceName = GetMethodGroupClientInterfaceName(methodGroup);
            if (methodGroup.Methods
                    .SelectMany(m => MethodImplImports(m, settings))
                    .Any(i => i.Split('.').LastOrDefault() == methodGroupClientInterfaceName))
            {
                return GetMethodGroupClientInterfacePath(methodGroup, settings);
            }
            return methodGroupClientInterfaceName;
        }

        private static string GetRestAPIInterfaceName(MethodGroup methodGroup, JavaSettings settings)
            => GetMethodGroupName(methodGroup).ToPascalCase() + "Service";

        private static string GetMethodGroupName(MethodGroup methodGroup)
        {
            string result = methodGroup?.Name?.ToString();

            if (!string.IsNullOrEmpty(result))
            {
                result = result.ToCamelCase();

                if (!result.EndsWith('s'))
                {
                    result += 's';
                }
            }

            return result;
        }

        private static IModelType PrimaryTypeResponseVariant(PrimaryType primaryType)
        {
            if (primaryType.KnownPrimaryType == KnownPrimaryType.DateTimeRfc1123)
            {
                return DependencyInjection.New<PrimaryType>(KnownPrimaryType.DateTime);
            }
            else if (primaryType.KnownPrimaryType == KnownPrimaryType.UnixTime)
            {
                return DependencyInjection.New<PrimaryType>(KnownPrimaryType.DateTime);
            }
            else if (primaryType.KnownPrimaryType == KnownPrimaryType.Base64Url)
            {
                return DependencyInjection.New<PrimaryType>(KnownPrimaryType.ByteArray);
            }
            else if (primaryType.KnownPrimaryType == KnownPrimaryType.None)
            {
                return GetIModelTypeNonNullableVariant(primaryType);
            }
            else
            {
                return primaryType;
            }
        }

        private static bool PrimaryTypeGetWantNullable(PrimaryType primaryType)
            => !primaryTypeNotWantNullable.Contains(primaryType);

        private static bool PrimaryTypeNullable(PrimaryType primaryType)
        {
            if (primaryType == null || PrimaryTypeGetWantNullable(primaryType))
            {
                return true;
            }
            switch (primaryType.KnownPrimaryType)
            {
                case KnownPrimaryType.None:
                case KnownPrimaryType.Boolean:
                case KnownPrimaryType.Double:
                case KnownPrimaryType.Int:
                case KnownPrimaryType.Long:
                case KnownPrimaryType.UnixTime:
                    return false;
            }
            return true;
        }

        private static string IModelTypeDefaultValue(IModelType modelType, Method parent, JavaSettings settings)
        {
            string result;
            if (modelType is PrimaryType primaryType)
            {
                string modelTypeName = IModelTypeName(primaryType, settings);
                if (modelTypeName == "byte[]")
                {
                    result = "new byte[0]";
                }
                else if (modelTypeName == "Byte[]")
                {
                    result = "new Byte[0]";
                }
                else if (PrimaryTypeNullable(primaryType))
                {
                    result = null;
                }
                else
                {
                    throw new NotSupportedException($"{modelTypeName} does not have default value!");
                }
            }
            else
            {
                result = modelType.DefaultValue;
            }
            return result;
        }

        private static IModelType IModelTypeServiceResponseVariant(IModelType modelType)
            => GetIModelTypeNonNullableVariant(GetIModelTypeResponseVariant(modelType)) ?? modelType;

        private static string GetParameterName(Parameter parameter)
        {
            string result;
            if (!parameter.IsClientProperty)
            {
                result = parameter.Name;
            }
            else
            {
                string caller = (parameter.Method != null && true == parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                result = $"{caller}.{PropertyName(parameter.ClientProperty)}()";
            }
            return result;
        }

        private static void SetParameterName(Parameter parameter, string name)
        {
            parameter.Name = name;
        }

        private static IModelType ParameterGetModelType(Parameter parameter)
        {
            IModelType result = parameter.ModelType;
            if (result != null && !(parameter.IsXNullable ?? !parameter.IsRequired))
            {
                result = GetIModelTypeNonNullableVariant(result);
            }
            return result;
        }

        private static void ParameterSetModelType(Parameter parameter, IModelType modelType)
        {
            parameter.ModelType = modelType;
        }

        private static IModelType ParameterClientType(Parameter parameter)
            => IModelTypeParameterVariant(ParameterGetModelType(parameter));

        private static IModelType ParameterWireType(Parameter parameter)
        {
            IModelType modelType = ParameterGetModelType(parameter);
            if (modelType.IsPrimaryType(KnownPrimaryType.Stream))
            {
                // Just pass whatever we give to clients to RestProxy
                return ParameterClientType(parameter);
            }
            else if (!modelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                parameter.Location != ParameterLocation.Body &&
                parameter.Location != ParameterLocation.FormData &&
                NeedsSpecialSerialization(ParameterClientType(parameter)))
            {
                return DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
            }
            else
            {
                return modelType;
            }
        }

        private static bool ParameterNeedsConversion(Parameter parameter)
            => !ParameterClientType(parameter).StructurallyEquals(ParameterWireType(parameter));

        private static string ParameterWireName(Parameter parameter)
            => ParameterNeedsConversion(parameter) ? $"{GetParameterName(parameter).ToCamelCase()}Converted" : GetParameterName(parameter);

        private static void ParameterConvertClientTypeToWireType(JavaBlock block, JavaSettings settings, Parameter parameter, IModelType wireType, string source, string target, string clientReference, int level = 0)
        {
            bool parameterIsRequired = parameter.IsRequired;
            if (wireType.IsPrimaryType(KnownPrimaryType.DateTimeRfc1123))
            {
                if (parameterIsRequired)
                {
                    block.Line($"DateTimeRfc1123 {target} = new DateTimeRfc1123({source});");
                }
                else
                {
                    block.Line($"DateTimeRfc1123 {target} = {IModelTypeDefaultValue(wireType, parameter.Method, settings) ?? "null"};");
                    block.If($"{source} != null", ifBlock =>
                    {
                        ifBlock.Line($"{target} = new DateTimeRfc1123({source});");
                    });
                }
            }
            else if (wireType.IsPrimaryType(KnownPrimaryType.UnixTime))
            {
                if (parameterIsRequired)
                {
                    block.Line($"Long {target} = {source}.toDateTime(DateTimeZone.UTC).getMillis() / 1000;");
                }
                else
                {
                    block.Line($"Long {target} = {IModelTypeDefaultValue(wireType, parameter.Method, settings) ?? "null"};");
                    block.If($"{source} != null", ifBlock =>
                    {
                        ifBlock.Line($"{target} = {source}.toDateTime(DateTimeZone.UTC).getMillis() / 1000;");
                    });
                }
            }
            else if (wireType.IsPrimaryType(KnownPrimaryType.Base64Url))
            {
                if (parameterIsRequired)
                {
                    block.Line($"Base64Url {target} = Base64Url.encode({source});");
                }
                else
                {
                    block.Line($"Base64Url {target} = {IModelTypeDefaultValue(wireType, parameter.Method, settings) ?? "null"};");
                    block.If($"{source} != null", ifBlock =>
                    {
                        ifBlock.Line($"{target} = Base64Url.encode({source});");
                    });
                }
            }
            else if (wireType is SequenceType wireSequenceType)
            {
                if (!parameterIsRequired)
                {
                    block.Line("{0} {1} = {2};",
                        IModelTypeName(ParameterWireType(parameter), settings),
                        target,
                        IModelTypeDefaultValue(wireType, parameter.Method, settings) ?? "null");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                string levelSuffix = (level == 0 ? "" : level.ToString());
                string itemName = $"item{levelSuffix}";
                string itemTarget = $"value{levelSuffix}";
                IModelType elementType = wireSequenceType.ElementType;
                block.Line("{0}{1} = new ArrayList<{2}>();",
                    parameterIsRequired ? IModelTypeName(wireType, settings) + " " : "",
                    target,
                    IModelTypeName(elementType, settings));
                block.Line("for ({0} {1} : {2}) {{",
                    IModelTypeParameterVariantName(elementType, settings),
                    itemName,
                    source);
                block.Indent(() =>
                {
                    ParameterConvertClientTypeToWireType(block, settings, parameter, elementType, itemName, itemTarget, clientReference, level + 1);
                    block.Line($"{target}.add({itemTarget});");
                });
                block.Line("}");

                if (!parameterIsRequired)
                {
                    block.DecreaseIndent();
                    block.Line("}");
                }
            }
            else if (wireType is DictionaryType dictionaryType)
            {
                if (!parameterIsRequired)
                {
                    block.Line($"{IModelTypeName(ParameterWireType(parameter), settings)} {target} = {IModelTypeDefaultValue(wireType, parameter.Method, settings) ?? "null"};");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                IModelType valueType = dictionaryType.ValueType;

                string levelString = (level == 0 ? "" : level.ToString(CultureInfo.InvariantCulture));
                string itemName = $"entry{levelString}";
                string itemTarget = $"value{levelString}";

                block.Line($"{(parameterIsRequired ? IModelTypeName(wireType, settings) + " " : "")}{target} = new HashMap<String, {IModelTypeName(valueType, settings)}>();");
                block.Line($"for (Map.Entry<String, {IModelTypeParameterVariantName(valueType, settings)}> {itemName} : {source}.entrySet()) {{");
                block.Indent(() =>
                {
                    ParameterConvertClientTypeToWireType(block, settings, parameter, valueType, itemName + ".getValue()", itemTarget, clientReference, level + 1);
                    block.Line($"{target}.put({itemName}.getKey(), {itemTarget});");
                });
                block.Line("}");

                if (!parameterIsRequired)
                {
                    block.DecreaseIndent();
                    block.Line("}");
                }
            }
        }

        private static bool NeedsSpecialSerialization(IModelType type)
        {
            PrimaryType known = type as PrimaryType;
            return known != null &&
                type.IsPrimaryType(KnownPrimaryType.ByteArray) ||
                type is SequenceType;
        }

        private static bool HasUnexpectedResponseExceptionType(Method method)
        {
            return method.DefaultResponse.Body != null;
        }

        private static void AddRestAPIInterface(JavaClass classBlock, CodeModel codeModel, JavaSettings settings)
        {
            AddRestAPIInterface(classBlock, codeModel, null, settings);
        }

        private static void AddRestAPIInterface(JavaClass classBlock, CodeModel codeModel, MethodGroup methodGroup, JavaSettings settings)
        {
            IEnumerable<Method> restAPIMethods = methodGroup == null ? GetRestAPIMethods(codeModel) : GetRestAPIMethods(methodGroup);
            if (restAPIMethods.Any())
            {
                string clientTypeName = methodGroup == null ? GetServiceClientInterfaceName(codeModel) : GetMethodGroupClientInterfaceName(methodGroup);
                string restAPIBaseUrl = codeModel.BaseUrl;
                string restAPIInterfaceName = methodGroup == null ? GetRestAPIInterfaceName(codeModel) : GetRestAPIInterfaceName(methodGroup, settings);

                classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
                {
                    comment.Description($"The interface defining all the services for {clientTypeName} to be used by the proxy service to perform REST calls.");
                });
                classBlock.Annotation($"Host(\"{restAPIBaseUrl}\")");
                classBlock.Interface(restAPIInterfaceName, interfaceBlock =>
                {
                    foreach (Method restAPIMethod in restAPIMethods)
                    {
                        string requestContentType = restAPIMethod.RequestContentType;
                        if (requestContentType == "multipart/form-data" || requestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.LineComment($"@Multipart not supported by {restProxyType}");
                        }

                        if (MethodIsPagingNextOperation(restAPIMethod))
                        {
                            interfaceBlock.Annotation("GET(\"{nextUrl}\")");
                        }
                        else
                        {
                            string httpMethod = restAPIMethod.HttpMethod.ToString().ToUpper();
                            string path = restAPIMethod.Url.TrimStart('/');
                            interfaceBlock.Annotation($"{httpMethod}(\"{path}\")");
                        }

                        IEnumerable<HttpStatusCode> expectedResponses = restAPIMethod.Responses.Keys;
                        if (expectedResponses.Any())
                        {
                            string expectedResponseStrings = string.Join(", ", expectedResponses
                                .OrderBy(statusCode => statusCode)
                                .Select(statusCode => statusCode.ToString("D")));
                            interfaceBlock.Annotation($"ExpectedResponses({{{expectedResponseStrings}}})");
                        }

                        string methodReturnValueWireType = ResponseReturnValueWireType(restAPIMethod.ReturnType);
                        if (methodReturnValueWireType != null)
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({methodReturnValueWireType}.class)");
                        }

                        if (HasUnexpectedResponseExceptionType(restAPIMethod))
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({MethodOperationExceptionTypeString(restAPIMethod, settings)}.class)");
                        }

                        string methodName = GetMethodName(restAPIMethod, settings);

                        bool shouldGenerateXmlSerialization = restAPIMethod.CodeModel.ShouldGenerateXmlSerialization;

                        List<string> parameterDeclarationList = new List<string>();
                        IEnumerable<Parameter> retrofitParameters = MethodOrderedRetrofitParameters(restAPIMethod, settings);
                        foreach (Parameter parameter in retrofitParameters)
                        {
                            StringBuilder parameterDeclarationBuilder = new StringBuilder();
                            if (restAPIMethod.Url.Contains("{" + GetParameterName(parameter) + "}"))
                            {
                                parameter.Location = ParameterLocation.Path;
                            }

                            string name = parameter.SerializedName;
                            if (parameter.Extensions.ContainsKey("hostParameter"))
                            {
                                parameterDeclarationBuilder.Append($"@HostParam(\"{name}\") ");
                            }
                            else if (parameter.Location == ParameterLocation.Path ||
                                parameter.Location == ParameterLocation.Query ||
                                parameter.Location == ParameterLocation.Header)
                            {
                                ParameterLocation location = parameter.Location;
                                parameterDeclarationBuilder.Append($"@{location}Param(\"{name}\") ");
                            }
                            else if (parameter.Location == ParameterLocation.Body)
                            {
                                parameterDeclarationBuilder.Append($"@BodyParam(\"{restAPIMethod.RequestContentType}\") ");
                            }
                            else if (parameter.Location == ParameterLocation.FormData)
                            {
                                parameterDeclarationBuilder.Append($"/* @Part(\"{name}\") not supported by RestProxy */");
                            }

                            string declarativeName = PropertyName(parameter.ClientProperty) ?? GetParameterName(parameter);

                            IModelType parameterModelType = ParameterGetModelType(parameter);
                            bool shouldUseXmlListWrapper = shouldGenerateXmlSerialization && parameter.Location == ParameterLocation.Body && parameterModelType is SequenceType;
                            if (shouldUseXmlListWrapper)
                            {
                                parameterDeclarationBuilder.Append(parameterModelType.XmlName + "Wrapper");
                            }
                            else
                            {
                                parameterDeclarationBuilder.Append(IModelTypeName(ParameterWireType(parameter), settings));
                            }

                            parameterDeclarationBuilder.Append(" " + declarativeName);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);

                        if (settings.IsAzureOrFluent)
                        {
                            foreach (Parameter parameter in retrofitParameters.Where(p => p.Location == ParameterLocation.Path || p.Location == ParameterLocation.Query))
                            {
                                if (GetExtensionBool(parameter?.Extensions, SwaggerExtensions.SkipUrlEncodingExtension))
                                {
                                    string location = parameter.Location.ToString();
                                    string serializedName = parameter.SerializedName;
                                    parameterDeclarations = parameterDeclarations.Replace(
                                        $"@{location}Param(\"{serializedName}\")",
                                        $"@{location}Param(value = \"{serializedName}\", encoded = true)");
                                }
                            }
                        }

                        if (MethodIsLongRunningOperation(restAPIMethod))
                        {
                            interfaceBlock.PublicMethod($"Observable<OperationStatus<{ResponseServiceResponseGenericParameterString(restAPIMethod.ReturnType, settings)}>> {methodName}({parameterDeclarations})");
                        }
                        else
                        {
                            interfaceBlock.PublicMethod($"Single<{MethodRestResponseConcreteTypeName(restAPIMethod, settings)}> {methodName}({parameterDeclarations})");
                        }
                    }
                });
            }
        }

        private static void AddServiceClientInterfacePropertyGettersAndSetters(JavaInterface interfaceBlock, CodeModel codeModel, JavaSettings settings)
        {
            string serviceClientInterfaceName = GetServiceClientInterfaceName(codeModel);

            foreach (Property property in GetServiceClientProperties(codeModel))
            {
                string propertyDescription = property.Documentation;
                string propertyName = PropertyName(property);
                string propertyNameCamelCase = propertyName.ToCamelCase();
                string propertyType = IModelTypeName(IModelTypeServiceResponseVariant(GetPropertyModelType(property)), settings);

                interfaceBlock.JavadocComment(comment =>
                {
                    comment.Description($"Gets {propertyDescription}");
                    comment.Return($"the {propertyName} value");
                });
                interfaceBlock.PublicMethod($"{propertyType} {propertyNameCamelCase}()");

                if (!property.IsReadOnly)
                {
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Sets {propertyDescription}");
                        comment.Param(propertyNameCamelCase, $"the {propertyName} value");
                        comment.Return("the service client itself");
                    });
                    interfaceBlock.PublicMethod($"{serviceClientInterfaceName} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase})");
                }
            }
        }

        private static string GetServiceClientInterfaceName(CodeModel codeModel)
            => codeModel.Name.ToPascalCase();

        private static string GetServiceClientClassName(CodeModel codeModel)
            => GetServiceClientInterfaceName(codeModel) + "Impl";

        /// <summary>
        /// Get the name of the MethodGroupClient class for the provided methodGroup. If the fluent
        /// flag is set, then an "Inner" suffix will be appended to the end of the
        /// MethodGroupClient's interface name. If the fluent flag is not set, then an "Impl"
        /// suffix will be appended.
        /// </summary>
        /// <param name="methodGroup"></param>
        /// <param name="settings"></param>
        /// <returns></returns>
        private static string GetMethodGroupClientClassName(MethodGroup methodGroup, JavaSettings settings)
            => GetMethodGroupClientInterfaceName(methodGroup) + (settings.IsFluent ? "Inner" : "Impl");

        private static string GetMethodGroupClientInterfaceName(MethodGroup methodGroup)
            => GetMethodGroupName(methodGroup).ToPascalCase();

        private static void AddClientMethodOverloads(JavaType typeBlock, IEnumerable<Method> restAPIMethods, JavaSettings settings)
        {
            if (restAPIMethods.Any())
            {
                bool isAzureOrFluent = settings.IsAzureOrFluent;
                foreach (Method restAPIMethod in restAPIMethods)
                {
                    IEnumerable<Parameter> clientMethodParameters = GetClientMethodParameters(restAPIMethod);
                    IEnumerable<Parameter> requiredClientMethodParameters = GetClientMethodRequiredParameters(restAPIMethod);
                    bool hasOptionalClientMethodParameters = HasOptionalClientMethodParameters(restAPIMethod);

                    if (isAzureOrFluent)
                    {
                        if (MethodIsPagingOperation(restAPIMethod) || MethodIsPagingNextOperation(restAPIMethod))
                        {
                            if (hasOptionalClientMethodParameters)
                            {
                                AddPagingMethodOverloads(typeBlock, restAPIMethod, requiredClientMethodParameters, settings, true, true);
                            }
                            AddPagingMethodOverloads(typeBlock, restAPIMethod, clientMethodParameters, settings, false, false);
                        }
                        else if (MethodSimulateAsPagingOperation(restAPIMethod, settings))
                        {
                            if (hasOptionalClientMethodParameters)
                            {
                                AddSimulatedPagingMethodOverloads(typeBlock, restAPIMethod, requiredClientMethodParameters, settings, true, true);
                            }
                            AddSimulatedPagingMethodOverloads(typeBlock, restAPIMethod, clientMethodParameters, settings, false, false);
                        }
                        else if (MethodIsLongRunningOperation(restAPIMethod))
                        {
                            if (hasOptionalClientMethodParameters)
                            {
                                AddLongRunningOperationMethodOverloads(typeBlock, restAPIMethod, requiredClientMethodParameters, settings, true, true);
                            }
                            AddLongRunningOperationMethodOverloads(typeBlock, restAPIMethod, clientMethodParameters, settings, false, false);
                        }
                        else
                        {
                            if (hasOptionalClientMethodParameters)
                            {
                                AddRegularMethodOverloads(typeBlock, restAPIMethod, settings, true);
                            }
                            AddRegularMethodOverloads(typeBlock, restAPIMethod, settings, false);
                        }
                    }
                    else
                    {
                        if (hasOptionalClientMethodParameters)
                        {
                            AddRegularMethodOverloads(typeBlock, restAPIMethod, settings, true);
                        }
                        AddRegularMethodOverloads(typeBlock, restAPIMethod, settings, false);
                    }
                }
            }
        }

        private static void AddLongRunningOperationMethodOverloads(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings, bool filterRequired, bool onlyRequiredParameters)
        {
            string methodName = GetMethodName(method, settings);

            // --------------------
            // Synchronous Overload
            // --------------------
            AddSynchronousMethodComment(typeBlock, method, parameters, settings);
            typeBlock.PublicMethod(GetSynchronousMethodSignature(method, parameters, settings), function =>
            {
                string argumentList = ArgumentList(parameters);
                if (IModelTypeName(GetIModelTypeResponseVariant(ResponseBodyClientType(method.ReturnType, settings)), settings) == "void")
                {
                    function.Line($"{methodName}Async({argumentList}).blockingLast();");
                }
                else
                {
                    function.Return($"{methodName}Async({argumentList}).blockingLast().result()");
                }
            });

            // ----------------------------
            // Async ServiceFuture Overload
            // ----------------------------
            AddServiceFutureMethodComment(typeBlock, method, parameters, settings);
            typeBlock.PublicMethod(GetServiceFutureMethodSignature(method, parameters, settings), function =>
            {
                function.Return($"ServiceFutureUtil.fromLRO({methodName}Async({ArgumentList(parameters)}), {serviceCallbackVariableName})");
            });

            // ------------------------------------------
            // Async Observable<OperationStatus> Overload
            // ------------------------------------------
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                ThrowsIllegalArgumentException(comment);
                comment.Return("the observable for the request");
            });
            typeBlock.PublicMethod($"Observable<OperationStatus<{ResponseGenericBodyClientTypeString(method.ReturnType, settings)}>> {methodName}Async({MethodParameterDeclaration(method, settings, parameters)})", function =>
            {
                AddRequiredNullableParameterChecks(function, method);
                AddValidationChecks(function, method, onlyRequiredParameters);
                AddOptionalOrConstantParameterVariables(function, method, settings, onlyRequiredParameters);

                MethodBuildInputMappings(method, settings, filterRequired, function);
                IEnumerable<Parameter> requiredRetrofitParameters = MethodRetrofitParameters(method, settings).Where(p => p.IsRequired);
                MethodParameterConversion(method, settings, requiredRetrofitParameters, function);
                function.Return($"service.{methodName}({MethodParameterApiInvocation(method, settings)})");
            });
        }

        private static void AddPagingMethodOverloads(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings, bool filterRequired, bool onlyRequiredParameters)
        {
            string methodName = GetMethodName(method, settings);
            string parameterDeclaration = MethodParameterDeclaration(method, settings, parameters);
            string pageType = ResponseServiceResponseGenericParameterString(method.ReturnType, settings);

            // --------------------
            // Synchronous PageList
            // --------------------
            AddSynchronousMethodComment(typeBlock, method, parameters, settings);
            string returnType = MethodReturnTypeResponseName(method, settings);
            typeBlock.PublicMethod($"{returnType} {methodName}({parameterDeclaration})", function =>
            {
                function.Line($"{pageType} response = {methodName}SinglePageAsync({ArgumentList(parameters)}).blockingGet();");
                function.ReturnAnonymousClass($"new {ResponseGenericBodyClientTypeString(method.ReturnType, settings)}(response)", anonymousClass =>
                {
                    anonymousClass.Annotation("Override");
                    anonymousClass.PublicMethod($"{pageType} nextPage(String {MethodPagingNextPageLinkParameterName(method, settings)})", subFunction =>
                    {
                        MethodPagingGroupedParameterTransformation(method, filterRequired, settings, subFunction);
                        subFunction.Return($"{MethodGetPagingNextMethodInvocation(method, settings)}({MethodNextMethodParameterInvocation(method, settings, filterRequired)}).blockingGet()");
                    });
                });
            });

            // ------------------------------
            // Async Observable<PagedList<T>>
            // ------------------------------
            AddObservablePagedListMethodComment(typeBlock, method, parameters, settings);
            typeBlock.PublicMethod($"Observable<{pageType}> {methodName}Async({parameterDeclaration})", function =>
            {
                function.Line($"return {methodName}SinglePageAsync({ArgumentList(parameters)})");
                function.Indent(() =>
                {
                    function.Line(".toObservable()");
                    function.Line($".concatMap(new Function<{pageType}, Observable<{pageType}>>() {{");
                    function.Indent(() =>
                    {
                        function.Annotation("Override");
                        function.Block($"public Observable<{pageType}> apply({pageType} page)", subFunction =>
                        {
                            string pagingNextPageLinkParameterName = MethodPagingNextPageLinkParameterName(method, settings);
                            subFunction.Line($"String {pagingNextPageLinkParameterName} = page.nextPageLink();");
                            subFunction.If($"{pagingNextPageLinkParameterName} == null", ifBlock =>
                            {
                                ifBlock.Return("Observable.just(page)");
                            });
                            MethodPagingGroupedParameterTransformation(method, filterRequired, settings, subFunction);
                            subFunction.Return($"Observable.just(page).concatWith({MethodGetPagingNextMethodInvocation(method, settings, false)}({MethodNextMethodParameterInvocation(method, settings, filterRequired)}))");
                        });
                    });
                    function.Line("});");
                });
            });

            // ------------------------------
            // Async Single<Page<T>> Overload
            // ------------------------------
            Response methodReturnType = method.ReturnType;
            string singlePageMethodReturnType = $"Single<{pageType}>";
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                ThrowsIllegalArgumentException(comment);
                comment.Return($"the {{@link {singlePageMethodReturnType}}} object if successful.");
            });
            typeBlock.PublicMethod($"{singlePageMethodReturnType} {methodName}SinglePageAsync({parameterDeclaration})", function =>
            {
                AddRequiredNullableParameterChecks(function, method);
                AddValidationChecks(function, method, onlyRequiredParameters);
                AddOptionalOrConstantParameterVariables(function, method, settings, onlyRequiredParameters);

                MethodBuildInputMappings(method, settings, filterRequired, function);
                MethodParameterConversion(method, settings, MethodRetrofitParameters(method, settings), function);
                if (MethodIsPagingNextOperation(method))
                {
                    string methodUrl = method.Url;
                    Regex regex = new Regex("{\\w+}");

                    string substitutedMethodUrl = regex.Replace(methodUrl, "%s").TrimStart('/');

                    IEnumerable<Parameter> retrofitParameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None);
                    StringBuilder builder = new StringBuilder($"String.format(\"{substitutedMethodUrl}\"");
                    foreach (Match match in regex.Matches(methodUrl))
                    {
                        string serializedNameWithBrackets = match.Value;
                        string serializedName = serializedNameWithBrackets.Substring(1, serializedNameWithBrackets.Length - 2);
                        builder.Append(", " + ParameterWireName(retrofitParameters.First(p => p.SerializedName == serializedName)));
                    }
                    builder.Append(")");

                    function.Line($"String nextUrl = {builder.ToString()};");
                }
                string restResponseReturnType = MethodRestResponseConcreteTypeName(method, settings);
                function.Line($"return service.{methodName}({MethodParameterApiInvocation(method, settings)}).map(new Function<{restResponseReturnType}, {pageType}>() {{");
                function.Indent(() =>
                {
                    function.Annotation("Override");
                    function.Block($"public {pageType} apply({restResponseReturnType} response)", subFunction =>
                    {
                        subFunction.Return("response.body()");
                    });
                });
                function.Line("});");
            });
        }

        private static void AddSimulatedPagingMethodOverloads(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings, bool filterRequired, bool onlyRequiredParameters)
        {
            string methodName = GetMethodName(method, settings);
            Response methodReturnType = method.ReturnType;
            string sequenceElementTypeString = ResponseSequenceElementTypeString(methodReturnType, settings);
            string parameterDeclaration = MethodParameterDeclaration(method, settings, parameters);

            // ------------------------
            // Synchronous PagedList<T>
            // ------------------------
            string pageImplType = SequenceTypeGetPageImplType(ResponseBodyClientType(methodReturnType, settings));
            string synchronousReturnType = $"PagedList<{sequenceElementTypeString}>";
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                if (method.ReturnType.Body != null)
                {
                    comment.Return($"the {synchronousReturnType} object if successful.");
                }
            });
            typeBlock.PublicMethod($"{synchronousReturnType} {methodName}({parameterDeclaration})", function =>
            {
                function.Line($"{pageImplType}<{sequenceElementTypeString}> page = new {pageImplType}<>();");
                function.Line($"page.setItems({methodName}Async({ArgumentList(parameters)}).single().items());");
                function.Line("page.setNextPageLink(null);");
                function.ReturnAnonymousClass($"new {synchronousReturnType}(page)", anonymousClass =>
                {
                    anonymousClass.Annotation("Override");
                    anonymousClass.PublicMethod($"Page<{sequenceElementTypeString}> nextPage(String nextPageLink)", subFunction =>
                    {
                        subFunction.Return("null");
                    });
                });
            });

            // -------------------------
            // Async Observable<Page<T>>
            // -------------------------
            AddObservablePagedListMethodComment(typeBlock, method, parameters, settings);
            string responseServiceResponseGenericParameterString = ResponseServiceResponseGenericParameterString(methodReturnType, settings);
            typeBlock.PublicMethod($"Observable<Page<{sequenceElementTypeString}>> {methodName}Async({parameterDeclaration})", function =>
            {
                AddRequiredNullableParameterChecks(function, method);
                AddValidationChecks(function, method, onlyRequiredParameters);
                AddOptionalOrConstantParameterVariables(function, method, settings, onlyRequiredParameters);

                MethodBuildInputMappings(method, settings, filterRequired, function);

                MethodParameterConversion(method, settings, MethodRetrofitParameters(method, settings), function);

                function.Line($"return service.{methodName}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method, settings)}, {responseServiceResponseGenericParameterString}>() {{");
                function.Indent(() =>
                {
                    function.Annotation("Override");
                    function.Block($"public {responseServiceResponseGenericParameterString} apply({MethodRestResponseConcreteTypeName(method, settings)} response)", subFunction =>
                    {
                        subFunction.Return("response.body()");
                    });
                });
                function.Line("}).toObservable();");
            });
        }

        private static void AddSynchronousMethodComment(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                ThrowsIllegalArgumentException(comment);
                comment.Throws(MethodOperationExceptionTypeString(method, settings), "thrown if the request is rejected by server");
                comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                if (method.ReturnType.Body != null)
                {
                    comment.Return($"the {MethodReturnTypeResponseName(method, settings)} object if successful.");
                }
            });
        }

        private static string GetSynchronousMethodSignature(Method method, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            string returnType = MethodReturnTypeResponseName(method, settings);
            string methodName = GetMethodName(method, settings);
            return $"{returnType} {methodName}({MethodParameterDeclaration(method, settings, parameters)})";
        }

        private static void AddObservablePagedListMethodComment(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                ThrowsIllegalArgumentException(comment);
                if (method.ReturnType.Body != null)
                {
                    comment.Return($"the observable to the {MethodReturnTypeResponseName(method, settings)} object");
                }
                else
                {
                    comment.Return($"the {{@link Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType, settings)}>}} object if successful.");
                }
            });
        }

        private static string GetServiceFutureMethodReturnType(Method method, JavaSettings settings)
            => $"ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType, settings)}>";

        private static void AddServiceFutureMethodComment(JavaType typeBlock, Method method, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            typeBlock.JavadocComment(comment =>
            {
                AddMethodSummaryAndDescription(comment, method);
                AddParameters(comment, parameters, settings);
                comment.Param(serviceCallbackVariableName, "the async ServiceCallback to handle successful and failed responses.");
                ThrowsIllegalArgumentException(comment);
                comment.Return($"the {{@link {GetServiceFutureMethodReturnType(method, settings)}}} object");
            });
        }

        private static string GetServiceFutureMethodSignature(Method method, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            string returnType = GetServiceFutureMethodReturnType(method, settings);
            string methodName = GetMethodName(method, settings);

            string parameterDeclaration = MethodParameterDeclaration(method, settings, parameters);
            if (!parameterDeclaration.IsNullOrEmpty())
            {
                parameterDeclaration += ", ";
            }
            string callbackParameterDeclaration = null;
            if (settings.IsAzureOrFluent)
            {
                if (MethodIsPagingOperation(method))
                {
                    callbackParameterDeclaration += $"final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType, settings)}> {serviceCallbackVariableName}";
                }
                else if (MethodIsPagingNextOperation(method))
                {
                    callbackParameterDeclaration += $"final ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType, settings)}> serviceFuture, final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType, settings)}> {serviceCallbackVariableName}";
                }
            }
            if (callbackParameterDeclaration == null)
            {
                callbackParameterDeclaration = $"final ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType, settings)}> {serviceCallbackVariableName}";
            }

            parameterDeclaration += callbackParameterDeclaration;

            return $"{returnType} {methodName}Async({parameterDeclaration})";
        }

        private static void AddMethodSummaryAndDescription(JavaJavadocComment comment, Method method)
        {
            string summary = method.Summary;
            if (!string.IsNullOrEmpty(summary))
            {
                comment.Description(summary.EscapeXmlComment());
            }

            string description = method.Description;
            if (!string.IsNullOrEmpty(description))
            {
                comment.Description(description.EscapeXmlComment());
            }
        }

        private static void AddParameters(JavaJavadocComment comment, IEnumerable<Parameter> parameters, JavaSettings settings)
        {
            foreach (Parameter param in parameters)
            {
                string parameterDocumentation = param.Documentation;
                if (string.IsNullOrEmpty(parameterDocumentation))
                {
                    parameterDocumentation = $"the {IModelTypeName(ParameterGetModelType(param), settings)} value";
                }
                comment.Param(GetParameterName(param), parameterDocumentation.EscapeXmlComment());
            }
        }

        private static void ThrowsIllegalArgumentException(JavaJavadocComment comment)
        {
            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
        }

        private static void AddServiceClientConstructorBody(JavaBlock constructor, CodeModel codeModel, JavaSettings settings)
        {
            List<string> superCallArgumentList = new List<string>() { httpPipelineVariableName };
            if (settings.IsAzureOrFluent)
            {
                superCallArgumentList.Add(azureEnvironmentVariableName);
            }
            constructor.Line($"super({string.Join(", ", superCallArgumentList)});");

            foreach (Property serviceClientProperty in GetServiceClientProperties(codeModel).Where(p => GetPropertyDefaultValue(p) != null))
            {
                constructor.Line($"this.{PropertyName(serviceClientProperty)} = {GetPropertyDefaultValue(serviceClientProperty)};");
            }

            foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
            {
                string methodGroupClientName = GetMethodGroupName(methodGroup);
                string methodGroupClientClassName = GetMethodGroupClientClassName(methodGroup, settings);
                constructor.Line($"this.{methodGroupClientName} = new {methodGroupClientClassName}(this);");
            }

            AddProxyVariableInitializationStatement(constructor, GetRestAPIMethods(codeModel), GetRestAPIInterfaceName(codeModel), "this", settings);
        }

        private static string CreateDefaultPipelineExpression(JavaSettings settings, params string[] arguments)
        {
            string proxyCreatorType = GetProxyCreatorType(settings);
            return $"{proxyCreatorType}.createDefaultPipeline({string.Join(", ", arguments)})";
        }

        private static void AddProxyVariableInitializationStatement(JavaBlock constructor, IEnumerable<Method> restAPIMethods, string restAPIInterfaceName, string serviceClientVariableName, JavaSettings settings)
        {
            if (restAPIMethods.Any())
            {
                constructor.Line($"this.service = {GetProxyCreatorType(settings)}.create({restAPIInterfaceName}.class, {serviceClientVariableName});");
            }
        }

        private static string GetProxyCreatorType(JavaSettings settings)
            => settings.IsAzureOrFluent ? azureProxyType : restProxyType;

        private static string GetPropertyWireTypeName(Property property, JavaSettings settings)
            => IModelTypeName(GetPropertyModelType(property), settings);

        private static string ConvertProperty(string sourceTypeName, string targetTypeName, string expression)
        {
            string result = null;

            if (sourceTypeName == targetTypeName)
            {
                result = expression;
            }
            else
            {
                switch (sourceTypeName.ToLower())
                {
                    case "datetime":
                        switch (targetTypeName.ToLower())
                        {
                            case "datetimerfc1123":
                                result = $"new DateTimeRfc1123({expression})";
                                break;
                        }
                        break;

                    case "datetimerfc1123":
                        switch (targetTypeName.ToLower())
                        {
                            case "datetime":
                                result = $"{expression}.dateTime()";
                                break;
                        }
                        break;
                }

                if (result == null)
                {
                    throw new NotSupportedException($"No conversion from {sourceTypeName} to {targetTypeName} is available.");
                }
            }

            return result;
        }

        private static string GetSerializeAnnotationArgs(Property property, bool shouldUseXmlSerialization)
        {
            List<string> settings = new List<string>();
            settings.Add($"value = \"{(shouldUseXmlSerialization ? property.XmlName : property.SerializedName)}\"");
            if (property.IsRequired)
            {
                settings.Add("required = true");
            }
            if (property.IsReadOnly)
            {
                settings.Add("access = JsonProperty.Access.WRITE_ONLY");
            }
            return string.Join(", ", settings);
        }

        private static string PropertyName(Property property)
        {
            string result = property?.Name?.ToString();
            if (!string.IsNullOrEmpty(result))
            {
                CodeNamer codeNamer = CodeNamer.Instance;
                result = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(result));
            }
            return result;
        }

        private static string GetParameterDefaultValue(Parameter parameter)
        {
            return GetDefaultValue(parameter.DefaultValue, ParameterGetModelType(parameter));
        }

        private static string GetPropertyDefaultValue(Property property)
        {
            return GetDefaultValue(property.DefaultValue, GetPropertyModelType(property));
        }

        private static string GetDefaultValue(string defaultValue, IModelType type)
        {
            string result = defaultValue;
            if (result != null && type != null && type is PrimaryType primaryType)
            {
                switch (primaryType.KnownPrimaryType)
                {
                    case KnownPrimaryType.Double:
                        result = double.Parse(result).ToString();
                        break;

                    case KnownPrimaryType.String:
                        result = CodeNamer.Instance.QuoteValue(result);
                        break;

                    case KnownPrimaryType.Boolean:
                        result = result.ToLowerInvariant();
                        break;

                    case KnownPrimaryType.Long:
                        result = result + 'L';
                        break;

                    case KnownPrimaryType.Date:
                        result = $"LocalDate.parse(\"{result}\")";
                        break;

                    case KnownPrimaryType.DateTime:
                    case KnownPrimaryType.DateTimeRfc1123:
                        result = $"DateTime.parse(\"{result}\")";
                        break;

                    case KnownPrimaryType.TimeSpan:
                        result = $"Period.parse(\"{result}\")";
                        break;

                    case KnownPrimaryType.ByteArray:
                        result = $"\"{result}\".getBytes()";
                        break;
                }
            }
            return result;
        }
    }
}
