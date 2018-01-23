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

        private const string innerSupportsImportPrefix = "com.microsoft.azure.v2.management.resources.fluentcore.collection.InnerSupports";
        private const string innerSupportsGetImport = innerSupportsImportPrefix + "Get";
        private const string innerSupportsDeleteImport = innerSupportsImportPrefix + "Delete";
        private const string innerSupportsListingImport = innerSupportsImportPrefix + "Listing";

        private const string GetByResourceGroup = "GetByResourceGroup";
        private const string ListByResourceGroup = "ListByResourceGroup";
        private const string List = "List";
        private const string Delete = "Delete";

        private static readonly List<PageClass> pageClasses = new List<PageClass>();

        private static readonly Regex enumValueNameRegex = new Regex(@"[\\\/\.\+\ \-]+");

        private static readonly ISet<Property> innerModelProperties = new HashSet<Property>();
        private static readonly ISet<CompositeType> innerModelCompositeType = new HashSet<CompositeType>();

        private static readonly ISet<SequenceType> pagedListTypes = new HashSet<SequenceType>();

        private static readonly IDictionary<IModelType, string> pageImplTypes = new Dictionary<IModelType, string>();

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
            "Flowable<byte[]>"
        };

        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");                                

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
            JavaSettings javaSettings = new JavaSettings(
                setAddCredentials: (bool value) => autoRestSettings.AddCredentials = value,
                isAzure: GetBoolSetting(autoRestSettings, "azure-arm"),
                isFluent: GetBoolSetting(autoRestSettings, "fluent"),
                regenerateManagers: GetBoolSetting(autoRestSettings, "regenerate-manager"),
                regeneratePom: GetBoolSetting(autoRestSettings, "regenerate-pom"),
                fileHeaderText: autoRestSettings.Header,
                maximumJavadocCommentWidth: autoRestSettings.MaximumCommentColumns,
                serviceName: GetAutoRestSettingsServiceName(autoRestSettings),
                package: codeModel.Namespace.ToLowerInvariant(),
                shouldGenerateXmlSerialization: codeModel.ShouldGenerateXmlSerialization);

            TransformCodeModel(codeModel, javaSettings);

            Service service = ParseService(codeModel, javaSettings);

            List<JavaFile> javaFiles = new List<JavaFile>();

            javaFiles.Add(GetServiceClientJavaFile(codeModel, javaSettings));

            foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
            {
                javaFiles.Add(GetMethodGroupClientJavaFile(methodGroup, javaSettings));
            }

            foreach (ServiceModel model in service.Models)
            {
                javaFiles.Add(GetModelJavaFile(model, javaSettings));
            }

            foreach (ServiceEnum serviceEnum in service.Enums)
            {
                javaFiles.Add(GetEnumJavaFile(serviceEnum, javaSettings));
            }

            foreach (XmlSequenceWrapper xmlSequenceWrapper in service.XmlSequenceWrappers)
            {
                javaFiles.Add(GetXmlSequenceWrapperJavaFile(xmlSequenceWrapper, javaSettings));
            }

            foreach (ServiceException exception in service.Exceptions)
            {
                javaFiles.Add(GetExceptionJavaFile(exception, javaSettings));
            }

            foreach (string subPackage in service.SubPackages)
            {
                javaFiles.Add(GetPackageInfoJavaFiles(service, subPackage, javaSettings));
            }

            if (javaSettings.IsAzureOrFluent)
            {
                foreach (PageClass pageClass in pageClasses)
                {
                    javaFiles.Add(GetPageJavaFile(pageClass, javaSettings));
                }
            }

            if (service.Manager != null)
            {
                javaFiles.Add(GetServiceManagerJavaFile(service.Manager, javaSettings));
            }

            if (!javaSettings.IsFluent)
            {
                javaFiles.Add(GetServiceClientInterfaceJavaFile(codeModel, javaSettings));

                foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
                {
                    javaFiles.Add(GetMethodGroupClientInterfaceJavaFile(methodGroup, javaSettings));
                }
            }
            else
            {
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

                AzureExtensions.AddPageableMethod(codeModel);

                NormalizePaginatedMethods(codeModel, settings);

                if (settings.IsFluent)
                {
                    // determine inner models
                    foreach (Parameter parameter in codeModel.Methods.SelectMany(m => m.Parameters))
                    {
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                        }
                        AppendInnerToTopLevelType(parameterModelType, codeModel, settings);
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
                    string compositeTypeName = GetCompositeTypeName(compositeType, settings);
                    bool compositeTypeIsAzureResourceExtension = GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);
                    if (compositeTypeName != "Resource" && (compositeTypeName != "SubResource" || !compositeTypeIsAzureResourceExtension))
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
                        Response response = method.Responses.Values.First();
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
                        method.Name = "begin" + m.Name.ToPascalCase();
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
        private static void NormalizePaginatedMethods(CodeModel serviceClient, JavaSettings settings)
        {
            if (serviceClient == null)
            {
                throw new ArgumentNullException("serviceClient");
            }

            var convertedTypes = new Dictionary<IModelType, IModelType>();

            foreach (Method restAPIMethod in serviceClient.Methods)
            {
                bool methodHasPageableExtensions = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension);
                JContainer methodPageableExtensions = methodHasPageableExtensions ? restAPIMethod.Extensions[AzureExtensions.PageableExtension] as JContainer : null;

                bool simulateMethodAsPagingOperation = false;
                MethodGroup methodGroup = restAPIMethod.MethodGroup;
                if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                {
                    MethodType restAPIMethodType = MethodType.Other;
                    string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                    string[] urlSplits = methodUrl.Split('/');
                    switch (restAPIMethod.HttpMethod)
                    {
                        case HttpMethod.Get:
                            if ((urlSplits.Length == 5 || urlSplits.Length == 7)
                                && urlSplits[0].EqualsIgnoreCase("subscriptions")
                                && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                            {
                                if (urlSplits.Length == 5)
                                {
                                    if (urlSplits[2].EqualsIgnoreCase("providers"))
                                    {
                                        restAPIMethodType = MethodType.ListBySubscription;
                                    }
                                    else
                                    {
                                        restAPIMethodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (urlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                {
                                    restAPIMethodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (IsTopLevelResourceUrl(urlSplits))
                            {
                                restAPIMethodType = MethodType.Get;
                            }
                            break;

                        case HttpMethod.Delete:
                            if (IsTopLevelResourceUrl(urlSplits))
                            {
                                restAPIMethodType = MethodType.Delete;
                            }
                            break;
                    }

                    simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                        1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                            {
                                MethodType methodGroupMethodType = MethodType.Other;
                                string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                string[] methodGroupUrlSplits = methodGroupMethodUrl.Split('/');
                                switch (methodGroupMethod.HttpMethod)
                                {
                                    case HttpMethod.Get:
                                        if ((methodGroupUrlSplits.Length == 5 || methodGroupUrlSplits.Length == 7)
                                            && methodGroupUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                            && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                        {
                                            if (methodGroupUrlSplits.Length == 5)
                                            {
                                                if (methodGroupUrlSplits[2].EqualsIgnoreCase("providers"))
                                                {
                                                    methodGroupMethodType = MethodType.ListBySubscription;
                                                }
                                                else
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (methodGroupUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                            {
                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (IsTopLevelResourceUrl(methodGroupUrlSplits))
                                        {
                                            methodGroupMethodType = MethodType.Get;
                                        }
                                        break;

                                    case HttpMethod.Delete:
                                        if (IsTopLevelResourceUrl(methodGroupUrlSplits))
                                        {
                                            methodGroupMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                return methodGroupMethodType == restAPIMethodType;
                            });
                }

                if (methodPageableExtensions != null || simulateMethodAsPagingOperation)
                {
                    PageClass page = GetPagingSetting(methodHasPageableExtensions, methodPageableExtensions, simulateMethodAsPagingOperation);

                    if (page != null && !string.IsNullOrEmpty(page.ClassName))
                    {
                        if (string.IsNullOrEmpty(page.NextLinkName))
                        {
                            restAPIMethod.Extensions[AzureExtensions.PageableExtension] = null;
                        }
                        bool anyTypeConverted = false;
                        foreach (HttpStatusCode responseStatus in restAPIMethod.Responses.Where(r => r.Value.Body is CompositeType).Select(s => s.Key).ToArray())
                        {
                            anyTypeConverted = true;
                            CompositeType compositeType = (CompositeType)restAPIMethod.Responses[responseStatus].Body;
                            SequenceType sequenceType = GetCompositeTypeProperties(compositeType, settings).Select(p => GetPropertyModelType(p)).FirstOrDefault(t => t is SequenceType) as SequenceType;

                            // if the type is a wrapper over page-able response
                            if (sequenceType != null)
                            {
                                SequenceType pagedResult = DependencyInjection.New<SequenceType>();
                                pagedResult.ElementType = sequenceType.ElementType;
                                SequenceTypeSetPageImplType(pagedResult, page.ClassName);

                                convertedTypes[restAPIMethod.Responses[responseStatus].Body] = pagedResult;
                                Response resp = DependencyInjection.New<Response>(pagedResult, restAPIMethod.Responses[responseStatus].Headers);
                                restAPIMethod.Responses[responseStatus] = resp;
                            }
                        }

                        if (!anyTypeConverted && simulateMethodAsPagingOperation)
                        {
                            foreach (HttpStatusCode responseStatus in restAPIMethod.Responses.Where(r => r.Value.Body is SequenceType).Select(s => s.Key).ToArray())
                            {
                                SequenceType sequenceType = (SequenceType)restAPIMethod.Responses[responseStatus].Body;

                                SequenceType pagedResult = DependencyInjection.New<SequenceType>();
                                pagedResult.ElementType = sequenceType.ElementType;
                                SequenceTypeSetPageImplType(pagedResult, page.ClassName);

                                convertedTypes[restAPIMethod.Responses[responseStatus].Body] = pagedResult;
                                Response resp = DependencyInjection.New<Response>(pagedResult, restAPIMethod.Responses[responseStatus].Headers);
                                restAPIMethod.Responses[responseStatus] = resp;
                            }
                        }

                        if (convertedTypes.ContainsKey(restAPIMethod.ReturnType.Body))
                        {
                            Response resp = DependencyInjection.New<Response>(convertedTypes[restAPIMethod.ReturnType.Body], restAPIMethod.ReturnType.Headers);
                            restAPIMethod.ReturnType = resp;
                        }
                    }
                }
            }

            SwaggerExtensions.RemoveUnreferencedTypes(serviceClient,
                new HashSet<string>(convertedTypes.Keys.Where(x => x is CompositeType).Cast<CompositeType>().Select((CompositeType t) => GetCompositeTypeName(t, settings))));
        }

        private static PageClass GetPagingSetting(bool hasPageableExtension, JContainer methodPageableExtensions, bool simulatedMethodAsPaging)
        {
            string nextLinkName = null;
            string itemName = "value";
            string className = null;

            bool shouldCreatePage = true;

            if (hasPageableExtension)
            {
                if (methodPageableExtensions == null)
                {
                    shouldCreatePage = false;
                }
                else
                {
                    nextLinkName = (string)methodPageableExtensions["nextLinkName"];
                    itemName = (string)methodPageableExtensions["itemName"] ?? "value";
                    className = (string)methodPageableExtensions["className"];
                }
            }
            else if (!simulatedMethodAsPaging)
            {
                shouldCreatePage = false;
            }

            PageClass result = null;
            if (shouldCreatePage)
            {
                result = pageClasses.FirstOrDefault(page => page.NextLinkName == nextLinkName && page.ItemName == itemName);
                if (result == null)
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

                    result = new PageClass(nextLinkName, itemName, className);
                    pageClasses.Add(result);
                }
            }

            return result;
        }

        private static Service ParseService(CodeModel codeModel, JavaSettings settings)
        {
            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            IEnumerable<string> subpackages = ParseSubpackages(settings);

            IEnumerable<ServiceEnum> enums = ParseEnums(codeModel, settings);

            IEnumerable<ServiceException> exceptions = ParseExceptions(codeModel, settings);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel, settings);

            IEnumerable<ServiceModel> models = ParseModels(codeModel, settings);

            ServiceManager manager = ParseManager(serviceClientName, codeModel, settings);

            return new Service(serviceClientName, serviceClientDescription, subpackages, enums, exceptions, xmlSequenceWrappers, models, manager);
        }

        private static MethodGroupClient ParseMethodGroupClient(MethodGroup methodGroup, JavaSettings settings)
        {
            string interfaceName = methodGroup.Name.ToString().ToPascalCase();
            if (!interfaceName.EndsWith('s'))
            {
                interfaceName += 's';
            }

            string className = interfaceName + (settings.IsFluent ? "Inner" : "Impl");

            string methodGroupClientInterfacePath = settings.Package + "." + interfaceName;

            RestAPI restAPI = ParseRestAPI(methodGroup, settings);
            HashSet<string> imports = new HashSet<string>()
            {
                "com.microsoft.rest.v2.RestResponse"
            };

            foreach (Method restAPIMethod in methodGroup.Methods)
            {
                HashSet<string> methodImports = new HashSet<string>()
                {
                    "io.reactivex.Observable",
                    "io.reactivex.Single",
                    "io.reactivex.functions.Function",
                    "com.microsoft.rest.v2.ServiceFuture",
                    "com.microsoft.rest.v2.ServiceCallback",
                    "com.microsoft.rest.v2.annotations.ExpectedResponses",
                    "com.microsoft.rest.v2.annotations.Headers",
                    "com.microsoft.rest.v2.annotations.Host",
                    "com.microsoft.rest.v2.http.HttpClient",
                };

                if (restAPIMethod.DefaultResponse.Body != null)
                {
                    methodImports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                }

                Response restAPIMethodReturnType = restAPIMethod.ReturnType;
                if (restAPIMethodReturnType.Body == null)
                {
                    methodImports.Add("io.reactivex.Completable");
                }
                else
                {
                    methodImports.Add("io.reactivex.Maybe");
                }

                List<Parameter> methodRetrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();
                bool methodIsPagingNextOperation = GetExtensionBool(restAPIMethod?.Extensions, "nextLinkMethod");
                if (settings.IsAzureOrFluent && methodIsPagingNextOperation)
                {
                    methodRetrofitParameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                    Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                    nextUrlParam.SerializedName = "nextUrl";
                    nextUrlParam.Documentation = "The URL to get the next page of items.";
                    nextUrlParam.Location = ParameterLocation.Path;
                    nextUrlParam.IsRequired = true;
                    nextUrlParam.ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                    nextUrlParam.Name = "nextUrl";
                    nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                    methodRetrofitParameters.Insert(0, nextUrlParam);
                }

                foreach (Parameter parameter in methodRetrofitParameters)
                {
                    ParameterLocation location = parameter.Location;
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }
                    if (location == ParameterLocation.Body || !((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                    {
                        IModelType parameterWireType;
                        IModelType parameterClientType = ConvertToClientType(parameterModelType);
                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                        {
                            parameterWireType = parameterClientType;
                        }
                        else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                            location != ParameterLocation.Body &&
                            location != ParameterLocation.FormData &&
                            ((parameterClientType is PrimaryType parameterClientPrimaryType && parameterClientPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                        {
                            parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                        }
                        else
                        {
                            parameterWireType = parameterModelType;
                        }

                        methodImports.AddRange(GetIModelTypeImports(parameterWireType, settings));
                    }

                    if (location != ParameterLocation.FormData)
                    {
                        methodImports.Add($"com.microsoft.rest.v2.annotations.{location.ToString()}Param");
                    }

                    if (location != ParameterLocation.Body)
                    {
                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.ByteArray))
                        {
                            methodImports.Add("org.apache.commons.codec.binary.Base64");
                        }
                        else if (parameterModelType is SequenceType)
                        {
                            methodImports.Add("com.microsoft.rest.v2.CollectionFormat");
                        }
                    }
                }

                // Http verb annotations
                methodImports.Add($"com.microsoft.rest.v2.annotations.{restAPIMethod.HttpMethod.ToString().ToUpperInvariant()}");

                // response type conversion
                if (restAPIMethod.Responses.Any())
                {
                    methodImports.Add("com.google.common.reflect.TypeToken");
                }

                // validation
                bool hasClientMethodParametersOrClientPropertiesToValidate = restAPIMethod.Parameters.Where(parameter =>
                {
                    bool result = !parameter.IsConstant;
                    if (result)
                    {
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                        }
                        result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType);
                    }
                    return result;
                }).Any();
                if (hasClientMethodParametersOrClientPropertiesToValidate)
                {
                    methodImports.Add("com.microsoft.rest.v2.Validator");
                }

                // parameters
                IEnumerable<Parameter> methodLocalParameters = restAPIMethod.Parameters
                    //Omit parameter-group properties for now since Java doesn't support them yet
                    .Where((Parameter parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                    .OrderBy(item => !item.IsRequired);
                IEnumerable<Parameter> methodLogicalParameters = restAPIMethod.LogicalParameters;
                foreach (Parameter parameter in methodLocalParameters.Concat(methodLogicalParameters))
                {
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }
                    IModelType parameterClientType = ConvertToClientType(parameterModelType);
                    methodImports.AddRange(GetIModelTypeImports(parameterClientType, settings));
                }

                // return type
                IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                bool restAPIMethodReturnBodyIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                    (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                     restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnBodyIsPaged)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                    SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                    pagedListTypes.Add(resultSequenceType);
                    restAPIMethodReturnBodyClientType = resultSequenceType;
                }
                methodImports.AddRange(GetIModelTypeImports(restAPIMethodReturnBodyClientType, settings));
                methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(restAPIMethodReturnType.Headers), settings));

                IModelType returnTypeBodyWireType = restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                methodImports.AddRange(GetIModelTypeImports(returnTypeBodyWireType, settings));

                IModelType returnTypeHeadersWireType = restAPIMethodReturnType.Headers;
                methodImports.AddRange(GetIModelTypeImports(returnTypeHeadersWireType, settings));

                IModelType methodReturnTypeCurrentType = restAPIMethodReturnType.Body;
                while (methodReturnTypeCurrentType != null)
                {
                    if (methodReturnTypeCurrentType is SequenceType currentSequenceType)
                    {
                        methodReturnTypeCurrentType = currentSequenceType.ElementType;
                    }
                    else if (methodReturnTypeCurrentType is DictionaryType currentDictionaryType)
                    {
                        methodReturnTypeCurrentType = currentDictionaryType.ValueType;
                    }
                    else
                    {
                        if (methodReturnTypeCurrentType is PrimaryType currentPrimaryType)
                        {
                            string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                            if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                            {
                                methodImports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                                methodImports.Add("com.microsoft.rest.v2." + currentPrimaryTypeName);
                            }
                        }

                        methodReturnTypeCurrentType = null;
                    }
                }

                IModelType returnTypeHeaderClientType = ConvertToClientType(restAPIMethodReturnType.Headers);
                if (((returnTypeBodyWireType == null ? restAPIMethodReturnBodyClientType != null : !returnTypeBodyWireType.StructurallyEquals(restAPIMethodReturnBodyClientType)) && IModelTypeName(restAPIMethodReturnBodyClientType, settings) != "void") ||
                    (returnTypeHeadersWireType == null ? returnTypeHeaderClientType != null : !returnTypeHeadersWireType.StructurallyEquals(returnTypeHeaderClientType)))
                {
                    IModelType responseBody = restAPIMethodReturnType.Body;
                    if (responseBody is SequenceType || returnTypeHeadersWireType is SequenceType)
                    {
                        methodImports.Add("java.util.ArrayList");
                    }
                    else if (responseBody is DictionaryType || returnTypeHeadersWireType is DictionaryType)
                    {
                        methodImports.Add("java.util.HashMap");
                    }
                }

                // response type (can be different from return type)
                IEnumerable<Response> methodResponses = restAPIMethod.Responses.Values;
                foreach (Response methodResponse in methodResponses)
                {
                    methodImports.AddRange(GetIModelTypeImports(restAPIMethodReturnBodyClientType, settings));
                    methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(methodResponse.Headers), settings));

                    IModelType responseBodyWireType = methodResponse.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                    methodImports.AddRange(GetIModelTypeImports(responseBodyWireType, settings));

                    IModelType responseHeadersWireType = methodResponse.Headers;
                    methodImports.AddRange(GetIModelTypeImports(responseHeadersWireType, settings));

                    IModelType responseCurrentType = methodResponse.Body;
                    while (responseCurrentType != null)
                    {
                        if (responseCurrentType is SequenceType currentSequenceType)
                        {
                            responseCurrentType = currentSequenceType.ElementType;
                        }
                        else if (responseCurrentType is DictionaryType currentDictionaryType)
                        {
                            responseCurrentType = currentDictionaryType.ValueType;
                        }
                        else
                        {
                            if (responseCurrentType is PrimaryType currentPrimaryType)
                            {
                                string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                                if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                    currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                    currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                                {
                                    methodImports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                                    methodImports.Add("com.microsoft.rest.v2." + currentPrimaryTypeName);
                                }
                            }

                            responseCurrentType = null;
                        }
                    }

                    IModelType methodResponseBodyClientType = ConvertToClientType(methodResponse.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                    SequenceType methodResponseBodyClientSequenceType = methodResponseBodyClientType as SequenceType;

                    if (settings.IsAzureOrFluent && methodResponseBodyClientSequenceType != null && restAPIMethodReturnBodyIsPaged)
                    {
                        SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                        resultSequenceType.ElementType = methodResponseBodyClientSequenceType.ElementType;
                        SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(methodResponseBodyClientSequenceType));
                        pagedListTypes.Add(resultSequenceType);
                        methodResponseBodyClientType = resultSequenceType;
                    }

                    IModelType responseHeaderClientType = ConvertToClientType(methodResponse.Headers);
                    if (((responseBodyWireType == null ? methodResponseBodyClientType != null : !responseBodyWireType.StructurallyEquals(methodResponseBodyClientType)) && IModelTypeName(methodResponseBodyClientType, settings) != "void") ||
                        (responseHeadersWireType == null ? responseHeaderClientType != null : !responseHeadersWireType.StructurallyEquals(responseHeaderClientType)))
                    {
                        IModelType responseBody = methodResponse.Body;
                        if (responseBody is SequenceType || responseHeadersWireType is SequenceType)
                        {
                            methodImports.Add("java.util.ArrayList");
                        }
                        else if (responseBody is DictionaryType || responseHeadersWireType is DictionaryType)
                        {
                            methodImports.Add("java.util.HashMap");
                        }
                    }
                }

                // exceptions
                methodImports.Add("java.io.IOException");

                string methodOperationExceptionTypeName;
                IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                if (settings.IsAzureOrFluent && (restAPIMethodExceptionType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                {
                    methodOperationExceptionTypeName = "CloudException";
                }
                else if (restAPIMethodExceptionType is CompositeType compositeReturnType)
                {
                    methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                    if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                    {
                        JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                        if (ext != null && ext["name"] != null)
                        {
                            methodOperationExceptionTypeName = ext["name"].ToString();
                        }
                    }
                }
                else
                {
                    methodOperationExceptionTypeName = "RestException";
                }

                switch (methodOperationExceptionTypeName)
                {
                    case "CloudException":
                        methodImports.Add("com.microsoft.azure.v2.CloudException");
                        break;
                    case "RestException":
                        methodImports.Add("com.microsoft.rest.v2.RestException");
                        break;
                    default:
                        methodImports.Add($"{settings.Package}.models.{methodOperationExceptionTypeName}");
                        break;
                }

                // parameterized host
                bool isParameterizedHost;
                bool containsParameterizedHostExtension = restAPIMethod?.CodeModel?.Extensions?.ContainsKey(SwaggerExtensions.ParameterizedHostExtension) ?? false;
                if (settings.IsAzureOrFluent)
                {
                    isParameterizedHost = containsParameterizedHostExtension && !methodIsPagingNextOperation;
                }
                else
                {
                    isParameterizedHost = containsParameterizedHostExtension;
                }

                if (isParameterizedHost)
                {
                    methodImports.Add("com.microsoft.rest.v2.annotations.HostParam");
                }

                if (settings.IsAzureOrFluent)
                {
                    bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
                    if (methodIsLongRunningOperation)
                    {
                        methodImports.Add("com.microsoft.azure.v2.OperationStatus");
                        methodImports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");

                        restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                            .SelectMany(t => GetIModelTypeImports(t, settings))
                            .Where(i => !restAPIMethod.Parameters.Any(parameter =>
                            {
                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                return GetIModelTypeImports(parameterModelType, settings).Contains(i);
                            }))
                            .ForEach(i => methodImports.Remove(i));
                    }
                    
                    string typeName = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientType);

                    bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                        restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null &&
                        !methodIsPagingNextOperation;

                    bool methodIsPagingNonPollingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                        restAPIMethod.Extensions[AzureExtensions.PageableExtension] == null &&
                        !methodIsPagingNextOperation;

                    if (methodIsPagingOperation || methodIsPagingNextOperation)
                    {
                        methodImports.Remove("java.util.ArrayList");
                        methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        methodImports.Add("com.microsoft.azure.v2.Page");
                        methodImports.Add("com.microsoft.azure.v2.PagedList");
                        methodImports.AddRange(CompositeTypeImports(typeName, false, false, true, false, settings.Package));
                    }
                    else if (methodIsPagingNonPollingOperation)
                    {
                        methodImports.AddRange(CompositeTypeImports(typeName, false, false, true, false, settings.Package));
                    }

                    if (settings.IsFluent)
                    {
                        if (methodOperationExceptionTypeName != "CloudException" && methodOperationExceptionTypeName != "RestException")
                        {
                            IEnumerable<string> methodOperationExceptionTypeNameAzureImports = CompositeTypeImports(methodOperationExceptionTypeName, false, false, true, false, settings.Package);
                            methodImports.RemoveWhere(i => methodOperationExceptionTypeNameAzureImports.Contains(i));
                            methodImports.AddRange(CompositeTypeImports(methodOperationExceptionTypeName, false, false, false, true, settings.Package));
                        }
                        if (methodIsLongRunningOperation)
                        {
                            restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                                .SelectMany(t => GetIModelTypeImports(t, settings))
                                .Where(i => !restAPIMethod.Parameters.Any(parameter =>
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    return GetIModelTypeImports(parameterModelType, settings).Contains(i);
                                }))
                                .ForEach(i => methodImports.Remove(i));
                        }

                        SequenceType pageType = restAPIMethodReturnBodyClientType as SequenceType;

                        bool simulateMethodAsPagingOperation = false;
                        if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                        {
                            MethodType restAPIMethodType = MethodType.Other;
                            string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                            string[] methodUrlSplits = methodUrl.Split('/');
                            switch (restAPIMethod.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                        && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                                    {
                                        if (methodUrlSplits.Length == 5)
                                        {
                                            if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                restAPIMethodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                restAPIMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            restAPIMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Delete;
                                    }
                                    break;
                            }
                            simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                                1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                                {
                                    MethodType methodGroupMethodType = MethodType.Other;
                                    string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                    string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                    switch (methodGroupMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                            {
                                                if (methodGroupMethodUrlSplits.Length == 5)
                                                {
                                                    if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }
                                    return methodGroupMethodType == restAPIMethodType;
                                });
                        }

                        if (methodIsPagingOperation || methodIsPagingNextOperation || simulateMethodAsPagingOperation)
                        {
                            methodImports.Add("com.microsoft.azure.v2.PagedList");

                            if (!simulateMethodAsPagingOperation)
                            {
                                methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                            }

                            methodImports.Remove("java.util.ArrayList");
                            methodImports.Add("com.microsoft.azure.v2.Page");
                        }

                        if ((methodIsPagingOperation || methodIsPagingNextOperation || simulateMethodAsPagingOperation || methodIsPagingNonPollingOperation) && pageType != null)
                        {
                            string pageImplType = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType);
                            IEnumerable<string> pageImplTypeAzureImports = CompositeTypeImports(pageImplType, false, false, true, false, settings.Package);
                            methodImports.RemoveWhere(i => pageImplTypeAzureImports.Contains(i));
                        }
                    }
                }
                imports.AddRange(methodImports);
            }

            string methodGroupTypeString = interfaceName;
            if (imports.Any((string import) => import.Split('.').LastOrDefault() == interfaceName))
            {
                methodGroupTypeString = methodGroupClientInterfacePath;
            }
            else
            {
                imports.Add(methodGroupClientInterfacePath);
            }

            List<string> implementedInterfaces = new List<string>();
            
            if (!settings.IsFluent)
            {
                implementedInterfaces.Add(methodGroupTypeString);
            }

            if (!settings.IsAzureOrFluent)
            {
                imports.Add("com.microsoft.rest.v2.RestProxy");
            }
            else
            {
                imports.Add("com.microsoft.azure.v2.AzureProxy");

                if (settings.IsFluent)
                {
                    Method getMethod = null;
                    Method deleteMethod = null;
                    Method listMethod = null;
                    Method listByResourceGroupMethod = null;

                    foreach (Method method in methodGroup.Methods)
                    {
                        IEnumerable<Parameter> clientMethodParameters = method.Parameters
                            //Omit parameter-group properties for now since Java doesn't support them yet
                            .Where((Parameter parameter) => parameter != null && !parameter.IsClientProperty && !parameter.IsConstant && !string.IsNullOrWhiteSpace(parameter.Name))
                            .OrderBy(item => !item.IsRequired);

                        bool takesTwoRequiredParameters = clientMethodParameters.Count(x => x.IsRequired) == 2;

                        string methodName = method.Name;

                        if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                        {
                            MethodType methodType = MethodType.Other;
                            string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(method.Url, ""), "");
                            string[] urlSplits = methodUrl.Split('/');
                            switch (method.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((urlSplits.Length == 5 || urlSplits.Length == 7)
                                        && urlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(method.ReturnType.Body, settings))
                                    {
                                        if (urlSplits.Length == 5)
                                        {
                                            if (urlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                methodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                methodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (urlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            methodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(urlSplits))
                                    {
                                        methodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(urlSplits))
                                    {
                                        methodType = MethodType.Delete;
                                    }
                                    break;
                            }

                            if (methodType != MethodType.Other)
                            {
                                int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
                                {
                                    MethodType methodGroupMethodType = MethodType.Other;
                                    string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                    string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                    switch (methodGroupMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                            {
                                                if (methodGroupMethodUrlSplits.Length == 5)
                                                {
                                                    if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }
                                    return methodGroupMethodType == methodType;
                                });

                                if (methodsWithSameType == 1)
                                {
                                    switch (methodType)
                                    {
                                        case MethodType.ListBySubscription:
                                            listMethod = method;
                                            break;

                                        case MethodType.ListByResourceGroup:
                                            listByResourceGroupMethod = method;
                                            break;

                                        case MethodType.Delete:
                                            deleteMethod = method;
                                            break;

                                        case MethodType.Get:
                                            getMethod = method;
                                            break;
                                    }
                                }
                            }
                        }
                    }

                    if (getMethod != null)
                    {
                        imports.Add(innerSupportsGetImport);
                        Response getMethodReturnType = getMethod.ReturnType;

                        IModelType getMethodReturnBodyClientType = ConvertToClientType(getMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                        SequenceType getMethodReturnBodyClientSequenceType = getMethodReturnBodyClientType as SequenceType;

                        bool getMethodResponseIsPaged = GetExtensionBool(getMethod.Extensions, "nextLinkMethod") ||
                            (getMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                             getMethod.Extensions[AzureExtensions.PageableExtension] != null);

                        if (getMethodReturnBodyClientSequenceType != null && getMethodResponseIsPaged)
                        {
                            SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                            resultSequenceType.ElementType = getMethodReturnBodyClientSequenceType.ElementType;
                            SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(getMethodReturnBodyClientSequenceType));
                            pagedListTypes.Add(resultSequenceType);
                            getMethodReturnBodyClientType = resultSequenceType;
                        }

                        string responseGenericBodyClientTypeString;
                        if (getMethodReturnBodyClientSequenceType != null && getMethodResponseIsPaged)
                        {
                            responseGenericBodyClientTypeString = $"PagedList<{IModelTypeName(getMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                        }
                        else
                        {
                            IModelType responseBodyWireType = getMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                            IModelType responseVariant = ConvertToClientType(responseBodyWireType);

                            bool responseVariantPrimaryTypeIsNullable = true;
                            if (responseVariant is PrimaryType responseVariantPrimaryType && !PrimaryTypeGetWantNullable(responseVariantPrimaryType))
                            {
                                switch (responseVariantPrimaryType.KnownPrimaryType)
                                {
                                    case KnownPrimaryType.None:
                                    case KnownPrimaryType.Boolean:
                                    case KnownPrimaryType.Double:
                                    case KnownPrimaryType.Int:
                                    case KnownPrimaryType.Long:
                                    case KnownPrimaryType.UnixTime:
                                        responseVariantPrimaryTypeIsNullable = false;
                                        break;
                                }
                            }

                            IModelType responseGenericBodyClientType = responseVariantPrimaryTypeIsNullable ? responseVariant : responseBodyWireType;
                            responseGenericBodyClientTypeString = IModelTypeName(responseGenericBodyClientType, settings);
                        }
                        implementedInterfaces.Add($"InnerSupportsGet<{responseGenericBodyClientTypeString}>");
                    }

                    if (deleteMethod != null)
                    {
                        imports.Add(innerSupportsDeleteImport);

                        Response deleteMethodReturnType = deleteMethod.ReturnType;

                        IModelType deleteMethodReturnBodyType = ConvertToClientType(deleteMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                        SequenceType deleteMethodReturnSequenceType = deleteMethodReturnBodyType as SequenceType;

                        bool deleteMethodResponseIsPaged = GetExtensionBool(deleteMethod.Extensions, "nextLinkMethod") ||
                            (deleteMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                             deleteMethod.Extensions[AzureExtensions.PageableExtension] != null);

                        if (deleteMethodReturnSequenceType != null && deleteMethodResponseIsPaged)
                        {
                            SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                            resultSequenceType.ElementType = deleteMethodReturnSequenceType.ElementType;
                            SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(deleteMethodReturnSequenceType));
                            pagedListTypes.Add(resultSequenceType);
                            deleteMethodReturnBodyType = resultSequenceType;
                        }

                        string responseClientCallbackTypeString;
                        if (deleteMethodReturnSequenceType != null && deleteMethodResponseIsPaged)
                        {
                            responseClientCallbackTypeString = IModelTypeName(deleteMethodReturnBodyType, settings);
                        }
                        else if (settings.IsAzureOrFluent && deleteMethodReturnSequenceType != null && deleteMethodResponseIsPaged)
                        {
                            responseClientCallbackTypeString = $"PagedList<{IModelTypeName(deleteMethodReturnSequenceType.ElementType, settings)}>";
                        }
                        else
                        {
                            IModelType responseBodyWireType = deleteMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                            IModelType responseVariant = ConvertToClientType(responseBodyWireType);

                            bool responseVariantPrimaryTypeIsNullable = true;
                            if (responseVariant is PrimaryType responseVariantPrimaryType && !PrimaryTypeGetWantNullable(responseVariantPrimaryType))
                            {
                                switch (responseVariantPrimaryType.KnownPrimaryType)
                                {
                                    case KnownPrimaryType.None:
                                    case KnownPrimaryType.Boolean:
                                    case KnownPrimaryType.Double:
                                    case KnownPrimaryType.Int:
                                    case KnownPrimaryType.Long:
                                    case KnownPrimaryType.UnixTime:
                                        responseVariantPrimaryTypeIsNullable = false;
                                        break;
                                }
                            }

                            IModelType responseGenericBodyClientType = responseVariantPrimaryTypeIsNullable ? responseVariant : responseBodyWireType;
                            responseClientCallbackTypeString = IModelTypeName(responseGenericBodyClientType, settings);
                        }
                        implementedInterfaces.Add($"InnerSupportsDelete<{responseClientCallbackTypeString}>");
                    }

                    if (listMethod != null && listByResourceGroupMethod != null)
                    {
                        string listMethodResponseSequenceElementTypeString = listMethod.ReturnType.Body is SequenceType listMethodSequenceType ? IModelTypeName(listMethodSequenceType.ElementType, settings) : "Void";
                        string listByResourceGroupMethodResponseSequenceElementTypeString = listByResourceGroupMethod.ReturnType.Body is SequenceType listByResourceGroupMethodSequenceType ? IModelTypeName(listByResourceGroupMethodSequenceType.ElementType, settings) : "Void";
                        if (listMethodResponseSequenceElementTypeString.EqualsIgnoreCase(listByResourceGroupMethodResponseSequenceElementTypeString))
                        {
                            imports.Add(innerSupportsListingImport);
                            implementedInterfaces.Add($"InnerSupportsListing<{listMethodResponseSequenceElementTypeString}>");
                        }
                    }

                    string ns = methodGroup.CodeModel.Namespace.ToLowerInvariant();
                    imports.RemoveWhere((string import) =>
                        import.StartsWith(ns + ".implementation", StringComparison.OrdinalIgnoreCase) ||
                        import == ns + "." + interfaceName);
                }
            }

            string serviceClientName = methodGroup.CodeModel.Name + "Impl";

            return new MethodGroupClient(className, interfaceName, implementedInterfaces, restAPI, imports, serviceClientName);
        }

        private static RestAPI ParseRestAPI(MethodGroup methodGroup, JavaSettings settings)
        {
            string name = methodGroup.Name.ToString().ToPascalCase();
            if (!name.EndsWith('s'))
            {
                name += 's';
            }
            name += "Service";

            string baseURL = methodGroup.CodeModel.BaseUrl;

            List<RestAPIMethod> restAPIMethods = new List<RestAPIMethod>();
            foreach (Method method in methodGroup.Methods)
            {
                restAPIMethods.Add(ParseRestAPIMethod(method, settings));
            }

            return new RestAPI(name, baseURL, restAPIMethods);
        }

        private static RestAPIMethod ParseRestAPIMethod(Method restAPIMethod, JavaSettings settings)
        {
            string methodRequestContentType = restAPIMethod.RequestContentType;

            bool methodIsPagingNextOperation = GetExtensionBool(restAPIMethod?.Extensions, "nextLinkMethod");

            string methodHttpMethod = restAPIMethod.HttpMethod.ToString().ToUpper();

            string methodUrlPath = restAPIMethod.Url.TrimStart('/');

            IEnumerable<HttpStatusCode> methodExpectedResponseStatusCodes = restAPIMethod.Responses.Keys.OrderBy(statusCode => statusCode);

            Response restAPIMethodReturnType = restAPIMethod.ReturnType;

            string methodReturnValueWireType = null;
            IModelType returnTypeCurrentType = restAPIMethodReturnType.Body;
            while (returnTypeCurrentType != null)
            {
                if (returnTypeCurrentType is SequenceType currentSequenceType)
                {
                    returnTypeCurrentType = currentSequenceType.ElementType;
                }
                else if (returnTypeCurrentType is DictionaryType currentDictionaryType)
                {
                    returnTypeCurrentType = currentDictionaryType.ValueType;
                }
                else
                {
                    if (returnTypeCurrentType is PrimaryType currentPrimaryType)
                    {
                        string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                        if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                            currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                            currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                        {
                            methodReturnValueWireType = currentPrimaryTypeName;
                        }
                    }

                    returnTypeCurrentType = null;
                }
            }

            string methodOperationExceptionTypeName = null;
            if (restAPIMethod.DefaultResponse.Body != null)
            {
                IModelType restAPIMethodDefaultResponseReturnType = restAPIMethod.DefaultResponse.Body;
                if (settings.IsAzureOrFluent && (restAPIMethodDefaultResponseReturnType == null || IModelTypeName(restAPIMethodDefaultResponseReturnType, settings) == "CloudError"))
                {
                    methodOperationExceptionTypeName = "CloudException";
                }
                else if (restAPIMethodDefaultResponseReturnType is CompositeType compositeReturnType)
                {
                    methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                    if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                    {
                        JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                        if (ext != null && ext["name"] != null)
                        {
                            methodOperationExceptionTypeName = ext["name"].ToString();
                        }
                    }
                }
                else
                {
                    methodOperationExceptionTypeName = "RestException";
                }
            }

            string methodName = restAPIMethod.Name;
            string wellKnownMethodName = null;
            bool simulateMethodAsPagingOperation = false;
            MethodGroup methodGroup = restAPIMethod.MethodGroup;
            if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
            {
                MethodType restAPIMethodType = MethodType.Other;
                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                string[] methodUrlSplits = methodUrl.Split('/');
                switch (restAPIMethod.HttpMethod)
                {
                    case HttpMethod.Get:
                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                            && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                        {
                            if (methodUrlSplits.Length == 5)
                            {
                                if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                {
                                    restAPIMethodType = MethodType.ListBySubscription;
                                }
                                else
                                {
                                    restAPIMethodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                            {
                                restAPIMethodType = MethodType.ListByResourceGroup;
                            }
                        }
                        else if (IsTopLevelResourceUrl(methodUrlSplits))
                        {
                            restAPIMethodType = MethodType.Get;
                        }
                        break;

                    case HttpMethod.Delete:
                        if (IsTopLevelResourceUrl(methodUrlSplits))
                        {
                            restAPIMethodType = MethodType.Delete;
                        }
                        break;
                }

                if (restAPIMethodType != MethodType.Other)
                {
                    int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
                    {
                        MethodType methodGroupMethodType = MethodType.Other;
                        Regex leading = new Regex("^/+");
                        Regex trailing = new Regex("/+$");
                        string methodGroupMethodUrl = trailing.Replace(leading.Replace(methodGroupMethod.Url, ""), "");
                        string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                        switch (methodGroupMethod.HttpMethod)
                        {
                            case HttpMethod.Get:
                                if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                    && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                    && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                {
                                    if (methodGroupMethodUrlSplits.Length == 5)
                                    {
                                        if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                        {
                                            methodGroupMethodType = MethodType.ListBySubscription;
                                        }
                                        else
                                        {
                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                    {
                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                {
                                    methodGroupMethodType = MethodType.Get;
                                }
                                break;

                            case HttpMethod.Delete:
                                if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                {
                                    methodGroupMethodType = MethodType.Delete;
                                }
                                break;
                        }
                        return methodGroupMethodType == restAPIMethodType;
                    });

                    if (methodsWithSameType == 1)
                    {
                        switch (restAPIMethodType)
                        {
                            case MethodType.ListBySubscription:
                                wellKnownMethodName = List;
                                simulateMethodAsPagingOperation = true;
                                break;

                            case MethodType.ListByResourceGroup:
                                wellKnownMethodName = ListByResourceGroup;
                                simulateMethodAsPagingOperation = true;
                                break;

                            case MethodType.Delete:
                                wellKnownMethodName = Delete;
                                break;

                            case MethodType.Get:
                                wellKnownMethodName = GetByResourceGroup;
                                break;

                            default:
                                throw new Exception("Flow should not hit this statement.");
                        }
                    }
                }
            }
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                IParent methodParent = restAPIMethod.Parent;
                methodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, restAPIMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(restAPIMethod.SingleItemAsEnumerable()));
            }
            methodName = methodName.ToCamelCase();

            IModelType restAPIMethodReturnClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
            SequenceType restAPIMethodReturnClientSequenceType = restAPIMethodReturnClientType as SequenceType;

            bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                 restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

            if (settings.IsAzureOrFluent && restAPIMethodReturnClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
            {
                SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                resultSequenceType.ElementType = restAPIMethodReturnClientSequenceType.ElementType;
                SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnClientSequenceType));
                pagedListTypes.Add(resultSequenceType);
                restAPIMethodReturnClientType = resultSequenceType;
            }

            string responseGenericBodyClientTypeString;
            if (settings.IsAzureOrFluent && restAPIMethodReturnClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
            {
                responseGenericBodyClientTypeString = $"PagedList<{IModelTypeName(restAPIMethodReturnClientSequenceType.ElementType, settings)}>";
            }
            else
            {
                IModelType responseBodyWireType = restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                IModelType responseVariant = ConvertToClientType(responseBodyWireType);

                bool responseVariantPrimaryTypeIsNullable = true;
                if (responseVariant is PrimaryType responseVariantPrimaryType && !PrimaryTypeGetWantNullable(responseVariantPrimaryType))
                {
                    switch (responseVariantPrimaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.None:
                        case KnownPrimaryType.Boolean:
                        case KnownPrimaryType.Double:
                        case KnownPrimaryType.Int:
                        case KnownPrimaryType.Long:
                        case KnownPrimaryType.UnixTime:
                            responseVariantPrimaryTypeIsNullable = false;
                            break;
                    }
                }

                IModelType responseGenericBodyClientType = responseVariantPrimaryTypeIsNullable ? responseVariant : responseBodyWireType;
                responseGenericBodyClientTypeString = IModelTypeName(responseGenericBodyClientType, settings);
            }

            string methodAsyncReturnType;
            bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
            if (methodIsLongRunningOperation)
            {
                string responseGenericParameterString;
                if (settings.IsAzureOrFluent && restAPIMethodReturnClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                {
                    responseGenericParameterString = $"Page<{IModelTypeName(restAPIMethodReturnClientSequenceType.ElementType, settings)}>";
                }
                else
                {
                    responseGenericParameterString = responseGenericBodyClientTypeString;
                }
                methodAsyncReturnType = $"Observable<OperationStatus<{responseGenericParameterString}>>";
            }
            else
            {
                IModelType restAPIMethodResponseHeaders = restAPIMethodReturnType.Headers;
                string deserializedResponseHeadersType = restAPIMethodResponseHeaders == null ? "Void" : IModelTypeName(ConvertToClientType(restAPIMethodResponseHeaders), settings);
                string deserializedResponseBodyType;
                if (restAPIMethodReturnType.Body == null)
                {
                    deserializedResponseBodyType = "Void";
                }
                else if (settings.IsAzureOrFluent && restAPIMethodReturnClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                {
                    deserializedResponseBodyType = $"{SequenceTypeGetPageImplType(restAPIMethodReturnClientSequenceType)}<{IModelTypeName(restAPIMethodReturnClientSequenceType.ElementType, settings)}>";
                }
                else
                {
                    deserializedResponseBodyType = responseGenericBodyClientTypeString;
                }
                string restResponseConcreteTypeName = $"RestResponse<{deserializedResponseHeadersType}, {deserializedResponseBodyType}>";
                methodAsyncReturnType = $"Single<{restResponseConcreteTypeName}>";
            }

            List<RestAPIParameter> methodParameters = new List<RestAPIParameter>();
            List<Parameter> methodRetrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();
            if (settings.IsAzureOrFluent && methodIsPagingNextOperation)
            {
                methodRetrofitParameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                nextUrlParam.SerializedName = "nextUrl";
                nextUrlParam.Documentation = "The URL to get the next page of items.";
                nextUrlParam.Location = ParameterLocation.Path;
                nextUrlParam.IsRequired = true;
                nextUrlParam.ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                nextUrlParam.Name = "nextUrl";
                nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                methodRetrofitParameters.Insert(0, nextUrlParam);
            }

            IEnumerable<Parameter> orderedRetrofitParameters = methodRetrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                .Union(methodRetrofitParameters.Where(p => p.Location != ParameterLocation.Path));
            foreach (Parameter parameter in orderedRetrofitParameters)
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

                string parameterLocation = parameter.Location.ToString();
                if (restAPIMethod.Url.Contains("{" + parameterName + "}"))
                {
                    parameterLocation = ParameterLocation.Path.ToString();
                }
                else if (parameter.Extensions.ContainsKey("hostParameter"))
                {
                    parameterLocation = "Host";
                }

                string parameterSerializedName = parameter.SerializedName;

                string parameterVariableName = parameter.ClientProperty?.Name?.ToString();
                if (!string.IsNullOrEmpty(parameterVariableName))
                {
                    CodeNamer codeNamer = CodeNamer.Instance;
                    parameterVariableName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(parameterVariableName));
                }
                if (parameterVariableName == null)
                {
                    parameterVariableName = parameterName;
                }

                bool parameterSkipUrlEncodingExtension = GetExtensionBool(parameter.Extensions, SwaggerExtensions.SkipUrlEncodingExtension);

                bool parameterIsConstant = parameter.IsConstant;

                bool parameterIsRequired = parameter.IsRequired;

                bool parameterIsNullable = IsNullable(parameter);

                string parameterDescription = parameter.Documentation;
                if (string.IsNullOrEmpty(parameterDescription))
                {
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !parameterIsNullable)
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }
                    parameterDescription = $"the {IModelTypeName(parameterModelType, settings)} value";
                }

                if (!parameterIsNullable && parameter.ModelType is PrimaryType parameterPrimaryType)
                {
                    primaryTypeNotWantNullable.Add(parameterPrimaryType);
                }

                RestAPIType parameterType = ParseParameterType(parameter, settings);

                methodParameters.Add(new RestAPIParameter(parameterName, parameterLocation, parameterSerializedName, parameterVariableName, parameterSkipUrlEncodingExtension, parameterIsConstant, parameterIsRequired, parameterDescription, parameterType));
            }

            bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null &&
                !methodIsPagingNextOperation;

            string methodDescription = "";
            if (!string.IsNullOrEmpty(restAPIMethod.Summary))
            {
                methodDescription += restAPIMethod.Summary;
            }
            if (!string.IsNullOrEmpty(restAPIMethod.Description))
            {
                if (methodDescription != "")
                {
                    methodDescription += Environment.NewLine;
                }
                methodDescription += restAPIMethod.Description;
            }

            IModelType serviceResponseVariant = GetIModelTypeNonNullableVariant(ConvertToClientType(restAPIMethodReturnClientType)) ?? restAPIMethodReturnClientType;
            string methodReturnValueClientType = IModelTypeName(serviceResponseVariant, settings);
            if (methodReturnValueClientType == null)
            {
                methodReturnValueClientType = "void";
            }

            return new RestAPIMethod(methodRequestContentType, methodIsPagingNextOperation, methodHttpMethod, methodUrlPath, methodExpectedResponseStatusCodes, methodReturnValueWireType, methodOperationExceptionTypeName, methodName, methodAsyncReturnType, methodParameters, methodIsPagingOperation, methodDescription, simulateMethodAsPagingOperation, methodIsLongRunningOperation, methodReturnValueClientType);
        }

        private static bool IsNullable(IVariable variable)
            => variable.IsXNullable.HasValue ? variable.IsXNullable.Value : !variable.IsRequired;

        private static RestAPIType ParseParameterType(Parameter parameter, JavaSettings settings)
        {
            string clientTypeName = null;

            IModelType modelType = parameter.ModelType;
            if (modelType is SequenceType && settings.ShouldGenerateXmlSerialization && parameter.Location == ParameterLocation.Body)
            {
                clientTypeName = modelType.XmlName + "Wrapper";
            }
            else
            {
                IModelType clientType = ConvertToClientType(modelType);

                IModelType wireType = modelType;
                if (modelType is PrimaryType modelPrimaryType)
                {
                    switch (modelPrimaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.Stream:
                            wireType = clientType;
                            break;

                        case KnownPrimaryType.ByteArray:
                            if (parameter.Location != ParameterLocation.Body && parameter.Location != ParameterLocation.FormData)
                            {
                                clientTypeName = "String";
                            }
                            break;
                    }
                }
                else if (modelType is SequenceType && parameter.Location != ParameterLocation.Body && parameter.Location != ParameterLocation.FormData)
                {
                    clientTypeName = "String";
                }

                if (clientTypeName == null)
                {
                    clientTypeName = IModelTypeName(wireType, settings);
                }
            }

            return new RestAPIValueType(clientTypeName);
        }

        private static IEnumerable<string> ParseSubpackages(JavaSettings settings)
        {
            List<string> subpackages = new List<string>() { "", "implementation" };
            if (!settings.IsFluent)
            {
                subpackages.Add("models");
            }
            return subpackages;
        }

        private static IEnumerable<ServiceEnum> ParseEnums(CodeModel codeModel, JavaSettings settings)
        {
            List<ServiceEnum> enums = new List<ServiceEnum>();
            string enumSubPackage = settings.IsFluent ? null : modelsPackage;
            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                string enumName = GetEnumTypeName(enumType);
                bool expandable = enumType.ModelAsString;

                List<ServiceEnumValue> enumValues = new List<ServiceEnumValue>();
                foreach (EnumValue enumValue in enumType.Values)
                {
                    enumValues.Add(ParseEnumValue(enumValue.MemberName, enumValue.SerializedName));
                }

                enums.Add(new ServiceEnum(enumName, enumSubPackage, expandable, enumValues));
            }
            return enums;
        }

        internal static ServiceEnumValue ParseEnumValue(string name, string value)
        {
            if (!string.IsNullOrWhiteSpace(name))
            {
                name = enumValueNameRegex.Replace(name, "_");
                for (int i = 1; i < name.Length - 1; i++)
                {
                    if (char.IsUpper(name[i]))
                    {
                        if (name[i - 1] != '_' && char.IsLower(name[i - 1]))
                        {
                            name = name.Insert(i, "_");
                        }
                    }
                }
                name = name.ToUpperInvariant();
            }

            return new ServiceEnumValue(name, value);
        }

        private static IEnumerable<ServiceException> ParseExceptions(CodeModel codeModel, JavaSettings settings)
        {
            List<ServiceException> exceptions = new List<ServiceException>();
            foreach (CompositeType exceptionType in codeModel.ErrorTypes)
            {
                string methodOperationExceptionTypeName = GetCompositeTypeName(exceptionType, settings) + "Exception";
                if (exceptionType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                {
                    JContainer ext = exceptionType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                    if (ext != null && ext["name"] != null)
                    {
                        methodOperationExceptionTypeName = ext["name"].ToString();
                    }
                }

                string errorName = GetCompositeTypeName(exceptionType, settings);

                // Skip any exceptions that are named "CloudErrorException" or have a body named
                // "CloudError" because those types already exist in the runtime.
                if (methodOperationExceptionTypeName != "CloudErrorException" && errorName != "CloudError")
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

                    exceptions.Add(new ServiceException(methodOperationExceptionTypeName, errorName, exceptionSubPackage));
                }
            }
            return exceptions;
        }

        private static IEnumerable<XmlSequenceWrapper> ParseXmlSequenceWrappers(CodeModel codeModel, JavaSettings settings)
        {
            List<XmlSequenceWrapper> xmlSequenceWrappers = new List<XmlSequenceWrapper>();
            if (codeModel.ShouldGenerateXmlSerialization)
            {
                // Every sequence type used as a parameter to a service method.
                IEnumerable<Method> allMethods = codeModel.Methods.Concat(codeModel.Operations.SelectMany(methodGroup => methodGroup.Methods));
                IEnumerable<Parameter> allParameters = allMethods.SelectMany(method => method.Parameters);

                foreach (Parameter parameter in allParameters)
                {
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }

                    if (parameterModelType is SequenceType sequenceType)
                    {
                        string xmlElementName = sequenceType.XmlName.ToPascalCase();
                        if (xmlSequenceWrappers.Any(existingWrapper => existingWrapper.XmlElementName != xmlElementName))
                        {
                            string sequenceTypeName = GetSequenceTypeName(sequenceType, settings);

                            HashSet<string> xmlSequenceWrapperImports = new HashSet<string>()
                            {
                                "com.fasterxml.jackson.annotation.JsonCreator",
                                "com.fasterxml.jackson.annotation.JsonProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement",
                                "java.util.List",
                            };
                            xmlSequenceWrapperImports.AddRange(GetIModelTypeImports(sequenceType.ElementType, settings));

                            xmlSequenceWrappers.Add(new XmlSequenceWrapper(sequenceTypeName, xmlElementName, xmlSequenceWrapperImports));
                        }
                    }
                }
            }
            return xmlSequenceWrappers;
        }

        private static IEnumerable<ServiceModel> ParseModels(CodeModel codeModel, JavaSettings settings)
        {
            List<ServiceModel> models = new List<ServiceModel>();
            ServiceModels serviceModels = new ServiceModels();
            foreach (CompositeType modelType in GetModelTypes(codeModel, settings))
            {
                models.Add(ParseModel(modelType, settings, serviceModels));
            }
            return models;
        }

        private static ServiceModel ParseModel(CompositeType compositeType, JavaSettings settings, ServiceModels models)
        {
            string modelName = GetCompositeTypeName(compositeType, settings);

            ServiceModel result = models.GetModel(modelName);
            if (result == null)
            {
                string modelPackage = !settings.IsFluent ? modelsPackage : (innerModelCompositeType.Contains(compositeType) ? ".implementation" : "");

                bool isPolymorphic = compositeType.BaseIsPolymorphic;

                ServiceModel parentModel = null;
                if (compositeType.BaseModelType != null)
                {
                    parentModel = ParseModel(compositeType.BaseModelType, settings, models);
                }

                HashSet<string> modelImports = new HashSet<string>();
                IEnumerable<Property> compositeTypeProperties = GetCompositeTypeProperties(compositeType, settings);
                foreach (Property property in compositeTypeProperties)
                {
                    IModelType propertyModelType = GetPropertyModelType(property);
                    IEnumerable<string> propertyModelTypeImports = GetIModelTypeImports(propertyModelType, settings);
                    if (propertyModelType.IsPrimaryType(KnownPrimaryType.DateTimeRfc1123) || propertyModelType.IsPrimaryType(KnownPrimaryType.Base64Url))
                    {
                        modelImports.AddRange(propertyModelTypeImports);
                        modelImports.AddRange(GetIModelTypeImports(ConvertToClientType(propertyModelType), settings));
                    }
                    else if (settings.IsFluent)
                    {
                        modelImports.AddRange(propertyModelTypeImports.Where(c => !c.StartsWith(GetPackage(settings), StringComparison.Ordinal) || c.EndsWith("Inner", StringComparison.Ordinal) ^ innerModelProperties.Contains(property)));
                    }
                    else
                    {
                        modelImports.AddRange(propertyModelTypeImports.Where(c => !c.StartsWith(GetPackage(settings, "models"), StringComparison.OrdinalIgnoreCase)));
                    }
                }

                if (compositeTypeProperties.Any())
                {
                    modelImports.Add("com.fasterxml.jackson.annotation.JsonProperty");
                }
                if (compositeTypeProperties.Any(p => p.XmlIsAttribute))
                {
                    modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
                }
                if (settings.ShouldGenerateXmlSerialization)
                {
                    modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement");

                    if (compositeTypeProperties.Any(p => p.XmlIsWrapped && p.ModelType is SequenceType))
                    {
                        modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper");
                    }
                }

                // For polymorphism
                if (isPolymorphic)
                {
                    modelImports.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                    modelImports.Add("com.fasterxml.jackson.annotation.JsonTypeName");

                    if (compositeType.BaseIsPolymorphic && compositeType.CodeModel.ModelTypes.Any((CompositeType modelType) => modelType.BaseModelType?.SerializedName == compositeType.SerializedName))
                    {
                        modelImports.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                    }
                }

                // For flattening
                if (GetCompositeTypeProperties(compositeType, settings).Any(p => p.WasFlattened()))
                {
                    modelImports.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
                }

                if (settings.IsAzure)
                {
                    foreach (Property property in GetCompositeTypeProperties(compositeType, settings))
                    {
                        IModelType propertyModelType = GetPropertyModelType(property);
                        if (propertyModelType is CompositeType propertyCompositeType)
                        {
                            string propertyCompositeTypeName = GetCompositeTypeName(propertyCompositeType, settings);
                            bool propertyCompositeTypeIsAzureResourceExtension = GetExtensionBool(propertyCompositeType, AzureExtensions.AzureResourceExtension);
                            if (propertyCompositeTypeName == "Resource" || (propertyCompositeTypeName == "SubResource" && propertyCompositeTypeIsAzureResourceExtension))
                            {
                                modelImports.Add($"com.microsoft.azure.v2.{GetCompositeTypeName(propertyCompositeType, settings)}");
                            }
                        }
                    }

                    CompositeType baseModelType = compositeType.BaseModelType;
                    string baseModelTypeName = GetCompositeTypeName(baseModelType, settings);
                    if (baseModelTypeName == "Resource" || baseModelTypeName == "SubResource")
                    {
                        modelImports.Add($"com.microsoft.azure.v2.{baseModelTypeName}");
                    }

                    if (settings.IsFluent)
                    {
                        if (baseModelTypeName != null && baseModelTypeName.EndsWith("Inner", StringComparison.Ordinal) ^ innerModelCompositeType.Contains(compositeType))
                        {
                            modelImports.AddRange(GetIModelTypeImports(baseModelType, settings));
                        }
                    }
                }

                string modelDescription;
                if (string.IsNullOrEmpty(compositeType.Summary) && string.IsNullOrEmpty(compositeType.Documentation))
                {
                    modelDescription = $"The {modelName} model.";
                }
                else
                {
                    modelDescription = $"{compositeType.Summary}{compositeType.Documentation}";
                }

                string polymorphicDiscriminator = compositeType.BasePolymorphicDiscriminator;

                string modelSerializedName = compositeType.SerializedName;

                IEnumerable<ServiceModel> derivedTypes = models.GetDerivedTypes(modelName);

                string modelXmlName = compositeType.XmlName;

                bool needsFlatten = false;
                List<ServiceProperty> properties = new List<ServiceProperty>();
                foreach (Property property in GetCompositeTypeProperties(compositeType, settings))
                {
                    properties.Add(ParseProperty(property, settings));
                    if (!needsFlatten && property.WasFlattened())
                    {
                        needsFlatten = true;
                    }
                }

                result = new ServiceModel(modelName, modelPackage, modelImports, modelDescription, isPolymorphic, polymorphicDiscriminator, modelSerializedName, needsFlatten, parentModel, derivedTypes, modelXmlName, properties);

                models.AddModel(result);
            }

            return result;
        }

        private static ServiceProperty ParseProperty(Property property, JavaSettings settings)
        {
            string name = property?.Name?.ToString();
            if (!string.IsNullOrEmpty(name))
            {
                CodeNamer codeNamer = CodeNamer.Instance;
                name = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(name));
            }

            string description = "";
            if (string.IsNullOrEmpty(property.Summary) && string.IsNullOrEmpty(property.Documentation))
            {
                description = $"The {name} property.";
            }
            else
            {
                description = property.Summary;

                string documentation = property.Documentation;
                if (!string.IsNullOrEmpty(documentation))
                {
                    if (!string.IsNullOrEmpty(description))
                    {
                        description += Environment.NewLine;
                    }
                    description += documentation;
                }
            }

            List<string> annotationArgumentList = new List<string>()
            {
                $"value = \"{(settings.ShouldGenerateXmlSerialization ? property.XmlName : property.SerializedName)}\""
            };
            if (property.IsRequired)
            {
                annotationArgumentList.Add("required = true");
            }
            if (property.IsReadOnly)
            {
                annotationArgumentList.Add("access = JsonProperty.Access.WRITE_ONLY");
            }
            string annotationArguments = string.Join(", ", annotationArgumentList);

            bool isXmlAttribute = property.XmlIsAttribute;

            string xmlName;
            try
            {
                xmlName = property.XmlName;
            }
            catch (ArgumentNullException)
            {
                xmlName = null;
            }

            string serializedName = property.SerializedName;

            bool isXmlWrapper = property.XmlIsWrapped;

            IModelType propertyModelType = GetPropertyModelType(property);
            string wireTypeName = IModelTypeName(propertyModelType, settings);

            bool isConstant = property.IsConstant;

            bool modelTypeIsSequence = propertyModelType is SequenceType;
            bool modelTypeIsComposite = propertyModelType is CompositeType;

            string clientTypeName = IModelTypeName(ConvertToClientType(propertyModelType), settings);

            string defaultValue;
            try
            {
                defaultValue = property.DefaultValue;
                if (defaultValue != null && propertyModelType != null && propertyModelType is PrimaryType propertyModelPrimaryType)
                {
                    switch (propertyModelPrimaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.Double:
                            defaultValue = double.Parse(defaultValue).ToString();
                            break;

                        case KnownPrimaryType.String:
                            defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                            break;

                        case KnownPrimaryType.Boolean:
                            defaultValue = defaultValue.ToLowerInvariant();
                            break;

                        case KnownPrimaryType.Long:
                            defaultValue = defaultValue + 'L';
                            break;

                        case KnownPrimaryType.Date:
                            defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                            break;

                        case KnownPrimaryType.DateTime:
                        case KnownPrimaryType.DateTimeRfc1123:
                            defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                            break;

                        case KnownPrimaryType.TimeSpan:
                            defaultValue = $"Period.parse(\"{defaultValue}\")";
                            break;

                        case KnownPrimaryType.ByteArray:
                            defaultValue = $"\"{defaultValue}\".getBytes()";
                            break;
                    }
                }
            }
            catch (NotSupportedException)
            {
                defaultValue = null;
            }

            bool isReadOnly = property.IsReadOnly;

            return new ServiceProperty(name, description, annotationArguments, isXmlAttribute, xmlName, serializedName, isXmlWrapper, wireTypeName, isConstant, modelTypeIsSequence, modelTypeIsComposite, clientTypeName, defaultValue, isReadOnly);
        }

        private static ServiceManager ParseManager(string serviceClientName, CodeModel codeModel, JavaSettings settings)
        {
            ServiceManager manager = null;
            if (settings.IsFluent && settings.RegenerateManagers)
            {
                string serviceName = GetServiceName(settings.ServiceName, codeModel);
                if (string.IsNullOrEmpty(serviceName))
                {
                    serviceName = "MissingServiceName";
                }
                manager = new ServiceManager(serviceClientName, serviceName);
            }
            return manager;
        }

        private static JavaFile GetServiceManagerJavaFile(ServiceManager manager, JavaSettings settings)
        {
            string className = $"{manager.ServiceName}Manager";

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
                    comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} resource management API entry points.");
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
                        comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} management API entry points.");
                        comment.Param(credentialsVariableName, credentialsDescription);
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return($"the interface exposing {manager.ServiceName} management API entry points that work across subscriptions");
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
                        constructor.Line($"new {manager.ServiceClientName}Impl({httpPipelineVariableName}).withSubscriptionId(subscriptionId));");
                    });
                });
            });

            return javaFile;
        }

        public static JavaFile GetPageJavaFile(PageClass pageClass, JavaSettings settings)
        {
            string subPackage = (settings.IsFluent ? implPackage : modelsPackage);
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(subPackage, settings, pageClass.ClassName);
            javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty",
                            "com.microsoft.azure.v2.Page",
                            "java.util.List");

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                comment.Param("<T>", "type of Azure resource");
            });
            javaFile.PublicClass($"{pageClass.ClassName}<T> implements Page<T>", classBlock =>
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
            string xmlElementName = xmlSequenceWrapper.XmlElementName;
            string xmlElementNameCamelCase = xmlElementName.ToCamelCase();

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, xmlSequenceWrapper.WrapperClassName);
            javaFile.Import(xmlSequenceWrapper.Imports);
            javaFile.Annotation($"JacksonXmlRootElement(localName = \"{xmlElementName}\")");
            javaFile.PublicClass(xmlSequenceWrapper.WrapperClassName, classBlock =>
            {
                classBlock.Annotation($"JacksonXmlProperty(localName = \"{xmlElementName}\")");
                classBlock.PrivateFinalMemberVariable(xmlSequenceWrapper.SequenceType, xmlElementNameCamelCase);

                classBlock.Annotation("JsonCreator");
                classBlock.PublicConstructor($"{xmlSequenceWrapper.WrapperClassName}(@JsonProperty(\"{xmlElementNameCamelCase}\") {xmlSequenceWrapper.SequenceType} {xmlElementNameCamelCase})", constructor =>
                {
                    constructor.Line($"this.{xmlElementNameCamelCase} = {xmlElementNameCamelCase};");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Get the {xmlElementName} value.");
                    comment.Return($"the {xmlElementName} value");
                });
                classBlock.PublicMethod($"{xmlSequenceWrapper.SequenceType} {xmlElementNameCamelCase}()", function =>
                {
                    function.Return(xmlElementNameCamelCase);
                });
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientJavaFile(CodeModel codeModel, JavaSettings settings)
        {
            string serviceClientInterfaceName = codeModel.Name.ToPascalCase();
            string serviceClientClassName = serviceClientInterfaceName + "Impl";
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, serviceClientClassName);

            string serviceClientClassDeclaration = $"{serviceClientClassName} extends ";
            if (settings.IsAzureOrFluent)
            {
                serviceClientClassDeclaration += "Azure";
            }
            serviceClientClassDeclaration += "ServiceClient";
            if (!settings.IsFluent)
            {
                serviceClientClassDeclaration += $" implements {serviceClientInterfaceName}";
            }

            IEnumerable<MethodGroup> methodGroups = GetMethodGroups(codeModel);

            HashSet<string> imports = new HashSet<string>();
            if (!settings.IsFluent)
            {
                string serviceClientInterfacePath = GetPackage(settings) + "." + serviceClientInterfaceName;
                imports.Add(serviceClientInterfacePath);

                imports.AddRange(methodGroups.Select((MethodGroup methodGroup) =>
                {
                    string interfaceName = methodGroup.Name.ToString().ToPascalCase();
                    if (!interfaceName.EndsWith('s'))
                    {
                        interfaceName += 's';
                    }
                    return settings.Package + "." + interfaceName;
                }));
            }
            if (HasServiceClientCredentials(codeModel))
            {
                imports.Add(serviceClientCredentialsImport);
            }
            IEnumerable<Method> restAPIMethods = GetRestAPIMethods(codeModel);
            if (restAPIMethods.Any())
            {
                imports.Add("com.microsoft.rest.v2.RestResponse");

                foreach (Method restAPIMethod in restAPIMethods)
                {
                    HashSet<string> methodImports = new HashSet<string>()
                    {
                        "io.reactivex.Observable",
                        "io.reactivex.Single",
                        "io.reactivex.functions.Function",
                        "com.microsoft.rest.v2.ServiceFuture",
                        "com.microsoft.rest.v2.ServiceCallback",
                        "com.microsoft.rest.v2.annotations.ExpectedResponses",
                        "com.microsoft.rest.v2.annotations.Headers",
                        "com.microsoft.rest.v2.annotations.Host",
                        "com.microsoft.rest.v2.http.HttpClient",
                    };

                    if (restAPIMethod.DefaultResponse.Body != null)
                    {
                        methodImports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                    }

                    Response restAPIMethodReturnType = restAPIMethod.ReturnType;
                    if (restAPIMethodReturnType.Body == null)
                    {
                        methodImports.Add("io.reactivex.Completable");
                    }
                    else
                    {
                        methodImports.Add("io.reactivex.Maybe");
                    }

                    List<Parameter> methodRetrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();
                    bool methodIsPagingNextOperation = GetExtensionBool(restAPIMethod?.Extensions, "nextLinkMethod");
                    if (settings.IsAzureOrFluent && methodIsPagingNextOperation)
                    {
                        methodRetrofitParameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                        Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                        nextUrlParam.SerializedName = "nextUrl";
                        nextUrlParam.Documentation = "The URL to get the next page of items.";
                        nextUrlParam.Location = ParameterLocation.Path;
                        nextUrlParam.IsRequired = true;
                        nextUrlParam.ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                        nextUrlParam.Name = "nextUrl";
                        nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                        methodRetrofitParameters.Insert(0, nextUrlParam);
                    }

                    foreach (Parameter parameter in methodRetrofitParameters)
                    {
                        ParameterLocation location = parameter.Location;
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                        }
                        if (location == ParameterLocation.Body || !((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                        {
                            IModelType parameterWireType;
                            IModelType parameterClientType = ConvertToClientType(parameterModelType);
                            if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                            {
                                parameterWireType = parameterClientType;
                            }
                            else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                location != ParameterLocation.Body &&
                                location != ParameterLocation.FormData &&
                                ((parameterClientType is PrimaryType parameterClientPrimaryType && parameterClientPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                            {
                                parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                            }
                            else
                            {
                                parameterWireType = parameterModelType;
                            }

                            methodImports.AddRange(GetIModelTypeImports(parameterWireType, settings));
                        }

                        if (location != ParameterLocation.FormData)
                        {
                            methodImports.Add($"com.microsoft.rest.v2.annotations.{location.ToString()}Param");
                        }

                        if (location != ParameterLocation.Body)
                        {
                            if (parameterModelType.IsPrimaryType(KnownPrimaryType.ByteArray))
                            {
                                methodImports.Add("org.apache.commons.codec.binary.Base64");
                            }
                            else if (parameterModelType is SequenceType)
                            {
                                methodImports.Add("com.microsoft.rest.v2.CollectionFormat");
                            }
                        }
                    }

                    // Http verb annotations
                    methodImports.Add($"com.microsoft.rest.v2.annotations.{restAPIMethod.HttpMethod.ToString().ToUpperInvariant()}");

                    // response type conversion
                    if (restAPIMethod.Responses.Any())
                    {
                        methodImports.Add("com.google.common.reflect.TypeToken");
                    }

                    // validation
                    bool hasClientMethodParametersOrClientPropertiesToValidate = restAPIMethod.Parameters.Where(parameter =>
                    {
                        bool result = !parameter.IsConstant;
                        if (result)
                        {
                            IModelType parameterModelType = parameter.ModelType;
                            if (parameterModelType != null && !IsNullable(parameter))
                            {
                                parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                            }
                            result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType);
                        }
                        return result;
                    }).Any();
                    if (hasClientMethodParametersOrClientPropertiesToValidate)
                    {
                        methodImports.Add("com.microsoft.rest.v2.Validator");
                    }

                    // parameters
                    IEnumerable<Parameter> methodLocalParameters = restAPIMethod.Parameters
                        //Omit parameter-group properties for now since Java doesn't support them yet
                        .Where((Parameter parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                        .OrderBy(item => !item.IsRequired);
                    IEnumerable<Parameter> methodLogicalParameters = restAPIMethod.LogicalParameters;
                    foreach (Parameter parameter in methodLocalParameters.Concat(methodLogicalParameters))
                    {
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                        }
                        IModelType parameterClientType = ConvertToClientType(parameterModelType);
                        methodImports.AddRange(GetIModelTypeImports(parameterClientType, settings));
                    }

                    // return type
                    IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                    SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                    bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                        (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                         restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                    if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                    {
                        SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                        resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                        SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                        pagedListTypes.Add(resultSequenceType);
                        restAPIMethodReturnBodyClientType = resultSequenceType;
                    }
                    methodImports.AddRange(GetIModelTypeImports(restAPIMethodReturnBodyClientType, settings));
                    methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(restAPIMethodReturnType.Headers), settings));

                    IModelType returnTypeBodyWireType = restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                    methodImports.AddRange(GetIModelTypeImports(returnTypeBodyWireType, settings));

                    IModelType returnTypeHeadersWireType = restAPIMethodReturnType.Headers;
                    methodImports.AddRange(GetIModelTypeImports(returnTypeHeadersWireType, settings));

                    IModelType returnTypeCurrentType = restAPIMethodReturnType.Body;
                    while (returnTypeCurrentType != null)
                    {
                        if (returnTypeCurrentType is SequenceType currentSequenceType)
                        {
                            returnTypeCurrentType = currentSequenceType.ElementType;
                        }
                        else if (returnTypeCurrentType is DictionaryType currentDictionaryType)
                        {
                            returnTypeCurrentType = currentDictionaryType.ValueType;
                        }
                        else
                        {
                            if (returnTypeCurrentType is PrimaryType currentPrimaryType)
                            {
                                string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                                if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                    currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                    currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                                {
                                    methodImports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                                    methodImports.Add("com.microsoft.rest.v2." + currentPrimaryTypeName);
                                }
                            }

                            returnTypeCurrentType = null;
                        }
                    }

                    IModelType returnTypeHeaderClientType = ConvertToClientType(restAPIMethodReturnType.Headers);
                    if (((returnTypeBodyWireType == null ? restAPIMethodReturnBodyClientType != null : !returnTypeBodyWireType.StructurallyEquals(restAPIMethodReturnBodyClientType)) && IModelTypeName(restAPIMethodReturnBodyClientType, settings) != "void") ||
                        (returnTypeHeadersWireType == null ? returnTypeHeaderClientType != null : !returnTypeHeadersWireType.StructurallyEquals(returnTypeHeaderClientType)))
                    {
                        IModelType responseBody = restAPIMethodReturnType.Body;
                        if (responseBody is SequenceType || returnTypeHeadersWireType is SequenceType)
                        {
                            methodImports.Add("java.util.ArrayList");
                        }
                        else if (responseBody is DictionaryType || returnTypeHeadersWireType is DictionaryType)
                        {
                            methodImports.Add("java.util.HashMap");
                        }
                    }

                    // response type (can be different from return type)
                    IEnumerable<Response> methodResponses = restAPIMethod.Responses.Values;
                    foreach (Response methodResponse in methodResponses)
                    {
                        IModelType methodResponseBodyClientType = ConvertToClientType(methodResponse.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                        SequenceType methodResponseBodyClientSequenceType = methodResponseBodyClientType as SequenceType;

                        if (settings.IsAzureOrFluent && methodResponseBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                        {
                            SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                            resultSequenceType.ElementType = methodResponseBodyClientSequenceType.ElementType;
                            SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(methodResponseBodyClientSequenceType));
                            pagedListTypes.Add(resultSequenceType);
                            methodResponseBodyClientType = resultSequenceType;
                        }

                        methodImports.AddRange(GetIModelTypeImports(methodResponseBodyClientType, settings));
                        methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(methodResponse.Headers), settings));

                        IModelType responseBodyWireType = methodResponse.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                        methodImports.AddRange(GetIModelTypeImports(responseBodyWireType, settings));

                        IModelType responseHeadersWireType = methodResponse.Headers;
                        methodImports.AddRange(GetIModelTypeImports(responseHeadersWireType, settings));

                        IModelType responseCurrentType = methodResponse.Body;
                        while (responseCurrentType != null)
                        {
                            if (responseCurrentType is SequenceType currentSequenceType)
                            {
                                responseCurrentType = currentSequenceType.ElementType;
                            }
                            else if (responseCurrentType is DictionaryType currentDictionaryType)
                            {
                                responseCurrentType = currentDictionaryType.ValueType;
                            }
                            else
                            {
                                if (responseCurrentType is PrimaryType currentPrimaryType)
                                {
                                    string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                                    if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                        currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                        currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                                    {
                                        methodImports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                                        methodImports.Add("com.microsoft.rest.v2." + currentPrimaryTypeName);
                                    }
                                }

                                responseCurrentType = null;
                            }
                        }

                        IModelType responseHeaderClientType = ConvertToClientType(methodResponse.Headers);
                        if (((responseBodyWireType == null ? methodResponseBodyClientType != null : !responseBodyWireType.StructurallyEquals(methodResponseBodyClientType)) && IModelTypeName(methodResponseBodyClientType, settings) != "void") ||
                            (responseHeadersWireType == null ? responseHeaderClientType != null : !responseHeadersWireType.StructurallyEquals(responseHeaderClientType)))
                        {
                            IModelType responseBody = methodResponse.Body;
                            if (responseBody is SequenceType || responseHeadersWireType is SequenceType)
                            {
                                methodImports.Add("java.util.ArrayList");
                            }
                            else if (responseBody is DictionaryType || responseHeadersWireType is DictionaryType)
                            {
                                methodImports.Add("java.util.HashMap");
                            }
                        }
                    }

                    // exceptions
                    methodImports.Add("java.io.IOException");

                    string methodOperationExceptionTypeName;
                    IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                    if (settings.IsAzureOrFluent && (restAPIMethodExceptionType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                    {
                        methodOperationExceptionTypeName = "CloudException";
                    }
                    else if (restAPIMethodExceptionType is CompositeType compositeReturnType)
                    {
                        methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                        if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                        {
                            JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                            if (ext != null && ext["name"] != null)
                            {
                                methodOperationExceptionTypeName = ext["name"].ToString();
                            }
                        }
                    }
                    else
                    {
                        methodOperationExceptionTypeName = "RestException";
                    }

                    switch (methodOperationExceptionTypeName)
                    {
                        case "CloudException":
                            methodImports.Add("com.microsoft.azure.v2.CloudException");
                            break;
                        case "RestException":
                            methodImports.Add("com.microsoft.rest.v2.RestException");
                            break;
                        default:
                            methodImports.Add($"{settings.Package}.models.{methodOperationExceptionTypeName}");
                            break;
                    }

                    // parameterized host
                    bool isParameterizedHost;
                    bool containsParameterizedHostExtension = restAPIMethod?.CodeModel?.Extensions?.ContainsKey(SwaggerExtensions.ParameterizedHostExtension) ?? false;
                    if (settings.IsAzureOrFluent)
                    {
                        isParameterizedHost = containsParameterizedHostExtension && !methodIsPagingNextOperation;
                    }
                    else
                    {
                        isParameterizedHost = containsParameterizedHostExtension;
                    }

                    if (isParameterizedHost)
                    {
                        methodImports.Add("com.microsoft.rest.v2.annotations.HostParam");
                    }

                    if (settings.IsAzureOrFluent)
                    {
                        bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
                        if (methodIsLongRunningOperation)
                        {
                            methodImports.Add("com.microsoft.azure.v2.OperationStatus");
                            methodImports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");

                            restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                                .SelectMany(t => GetIModelTypeImports(t, settings))
                                .Where(i => !restAPIMethod.Parameters.Any(parameter =>
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    return GetIModelTypeImports(parameterModelType, settings).Contains(i);
                                }))
                                .ForEach(i => methodImports.Remove(i));
                        }
                        
                        string typeName = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientType);

                        bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                            restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null &&
                            !methodIsPagingNextOperation;

                        bool methodIsPagingNonPollingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                            restAPIMethod.Extensions[AzureExtensions.PageableExtension] == null &&
                            !methodIsPagingNextOperation;

                        if (methodIsPagingOperation || methodIsPagingNextOperation)
                        {
                            methodImports.Remove("java.util.ArrayList");
                            methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                            methodImports.Add("com.microsoft.azure.v2.Page");
                            methodImports.Add("com.microsoft.azure.v2.PagedList");
                            methodImports.AddRange(CompositeTypeImports(typeName, false, false, true, false, settings.Package));
                        }
                        else if (methodIsPagingNonPollingOperation)
                        {
                            methodImports.AddRange(CompositeTypeImports(typeName, false, false, true, false, settings.Package));
                        }

                        if (settings.IsFluent)
                        {
                            if (methodOperationExceptionTypeName != "CloudException" && methodOperationExceptionTypeName != "RestException")
                            {
                                IEnumerable<string> methodOperationExceptionTypeNameAzureImports = CompositeTypeImports(methodOperationExceptionTypeName, false, false, true, false, settings.Package);
                                methodImports.RemoveWhere(i => methodOperationExceptionTypeNameAzureImports.Contains(i));
                                methodImports.AddRange(CompositeTypeImports(methodOperationExceptionTypeName, false, false, false, true, settings.Package));
                            }
                            if (methodIsLongRunningOperation)
                            {
                                restAPIMethod.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { restAPIMethod.DefaultResponse.Body })
                                    .SelectMany(t => GetIModelTypeImports(t, settings))
                                    .Where(i => !restAPIMethod.Parameters.Any(parameter =>
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        return GetIModelTypeImports(parameterModelType, settings).Contains(i);
                                    }))
                                    .ForEach(i => methodImports.Remove(i));
                            }

                            bool simulateMethodAsPagingOperation = false;
                            MethodGroup methodGroup = restAPIMethod.MethodGroup;
                            if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                            {
                                MethodType restAPIMethodType = MethodType.Other;
                                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                                string[] methodUrlSplits = methodUrl.Split('/');
                                switch (restAPIMethod.HttpMethod)
                                {
                                    case HttpMethod.Get:
                                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                            && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                                        {
                                            if (methodUrlSplits.Length == 5)
                                            {
                                                if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                {
                                                    restAPIMethodType = MethodType.ListBySubscription;
                                                }
                                                else
                                                {
                                                    restAPIMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                            {
                                                restAPIMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (IsTopLevelResourceUrl(methodUrlSplits))
                                        {
                                            restAPIMethodType = MethodType.Get;
                                        }
                                        break;

                                    case HttpMethod.Delete:
                                        if (IsTopLevelResourceUrl(methodUrlSplits))
                                        {
                                            restAPIMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                                    1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                                    {
                                        MethodType methodGroupMethodType = MethodType.Other;
                                        string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                        string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                        switch (methodGroupMethod.HttpMethod)
                                        {
                                            case HttpMethod.Get:
                                                if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                    && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                    && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                                {
                                                    if (methodGroupMethodUrlSplits.Length == 5)
                                                    {
                                                        if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                        {
                                                            methodGroupMethodType = MethodType.ListBySubscription;
                                                        }
                                                        else
                                                        {
                                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                                        }
                                                    }
                                                    else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                {
                                                    methodGroupMethodType = MethodType.Get;
                                                }
                                                break;

                                            case HttpMethod.Delete:
                                                if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                {
                                                    methodGroupMethodType = MethodType.Delete;
                                                }
                                                break;
                                        }
                                        return methodGroupMethodType == restAPIMethodType;
                                    });
                            }

                            if (methodIsPagingOperation || methodIsPagingNextOperation || simulateMethodAsPagingOperation)
                            {
                                methodImports.Add("com.microsoft.azure.v2.PagedList");

                                if (!simulateMethodAsPagingOperation)
                                {
                                    methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                                }

                                methodImports.Remove("java.util.ArrayList");
                                methodImports.Add("com.microsoft.azure.v2.Page");
                            }

                            if ((methodIsPagingOperation || methodIsPagingNextOperation || simulateMethodAsPagingOperation || methodIsPagingNonPollingOperation) && restAPIMethodReturnBodyClientSequenceType != null)
                            {
                                string pageImplType = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientType);
                                IEnumerable<string> pageImplTypeAzureImports = CompositeTypeImports(pageImplType, false, false, true, false, settings.Package);
                                methodImports.RemoveWhere(i => pageImplTypeAzureImports.Contains(i));
                            }
                        }
                    }
                    imports.AddRange(methodImports);
                }
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
                string serviceClientTypeName = settings.IsFluent ? serviceClientClassName : serviceClientInterfaceName;
                comment.Description($"Initializes a new instance of the {serviceClientTypeName} type.");
            });
            javaFile.PublicClass(serviceClientClassDeclaration, classBlock =>
            {
                string restAPIInterfaceName = codeModel.Name.ToPascalCase();
                if (!string.IsNullOrEmpty(restAPIInterfaceName))
                {
                    restAPIInterfaceName += "Service";
                }

                // Add proxy service member variable
                if (restAPIMethods.Any())
                {
                    classBlock.JavadocComment($"The proxy service used to perform REST calls.");
                    classBlock.PrivateMemberVariable(restAPIInterfaceName, "service");
                }

                // Add ServiceClient client property variables, getters, and setters
                IEnumerable<Property> serviceClientProperties = GetServiceClientProperties(codeModel);
                foreach (Property serviceClientProperty in serviceClientProperties)
                {
                    string propertyDocumentation = serviceClientProperty.Documentation.ToString();
                    IModelType propertyModelType = GetPropertyModelType(serviceClientProperty);
                    IModelType serviceResponseVariant = GetIModelTypeNonNullableVariant(ConvertToClientType(propertyModelType)) ?? propertyModelType;
                    string propertyType = IModelTypeName(serviceResponseVariant, settings);
                    string propertyName = serviceClientProperty?.Name?.ToString();
                    if (!string.IsNullOrEmpty(propertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        propertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(propertyName));
                    }
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

                    if (!serviceClientProperty.IsReadOnly)
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
                foreach (MethodGroup methodGroup in methodGroups)
                {
                    string methodGroupName = methodGroup.Name.ToString().ToCamelCase();
                    if (!methodGroupName.EndsWith('s'))
                    {
                        methodGroupName += 's';
                    }

                    string methodGroupDeclarationType = (settings.IsFluent ? methodGroupName + "Inner" : methodGroupName).ToPascalCase();

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

                string proxyType = settings.IsAzureOrFluent ? azureProxyType : restProxyType;

                // Service Client Constructors
                string constructorDescription = $"Initializes an instance of {serviceClientInterfaceName} client.";
                if (settings.IsAzureOrFluent)
                {
                    if (HasServiceClientCredentials(codeModel))
                    {
                        string createDefaultPipelineExpression = $"{proxyType}.createDefaultPipeline({serviceClientClassName}.class, {credentialsVariableName})";

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
                        string createDefaultPipelineExpression = $"{proxyType}.createDefaultPipeline({serviceClientClassName}.class)";
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
                        List<string> superCallArgumentList = new List<string>() { httpPipelineVariableName };
                        if (settings.IsAzureOrFluent)
                        {
                            superCallArgumentList.Add(azureEnvironmentVariableName);
                        }
                        constructor.Line($"super({string.Join(", ", superCallArgumentList)});");

                        foreach (Property serviceClientProperty in serviceClientProperties)
                        {
                            string defaultValue = serviceClientProperty.DefaultValue;
                            IModelType propertyModelType = GetPropertyModelType(serviceClientProperty);
                            if (defaultValue != null)
                            {
                                if (propertyModelType is PrimaryType propertyModelPrimaryType)
                                {
                                    switch (propertyModelPrimaryType.KnownPrimaryType)
                                    {
                                        case KnownPrimaryType.Double:
                                            defaultValue = double.Parse(defaultValue).ToString();
                                            break;

                                        case KnownPrimaryType.String:
                                            defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                            break;

                                        case KnownPrimaryType.Boolean:
                                            defaultValue = defaultValue.ToLowerInvariant();
                                            break;

                                        case KnownPrimaryType.Long:
                                            defaultValue = defaultValue + 'L';
                                            break;

                                        case KnownPrimaryType.Date:
                                            defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.DateTime:
                                        case KnownPrimaryType.DateTimeRfc1123:
                                            defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.TimeSpan:
                                            defaultValue = $"Period.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.ByteArray:
                                            defaultValue = $"\"{defaultValue}\".getBytes()";
                                            break;
                                    }
                                }

                                string serviceClientPropertyName = serviceClientProperty?.Name?.ToString();
                                if (!string.IsNullOrEmpty(serviceClientPropertyName))
                                {
                                    CodeNamer codeNamer = CodeNamer.Instance;
                                    serviceClientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(serviceClientPropertyName));
                                }
                                constructor.Line($"this.{serviceClientPropertyName} = {defaultValue};");
                            }
                        }

                        foreach (MethodGroup methodGroup in methodGroups)
                        {
                            string methodGroupClientName = methodGroup.Name.ToString();
                            if (!methodGroupClientName.EndsWith('s'))
                            {
                                methodGroupClientName += 's';
                            }

                            string methodGroupClientClassName = methodGroupClientName.ToPascalCase() + (settings.IsFluent ? "Inner" : "Impl");

                            constructor.Line($"this.{methodGroupClientName.ToCamelCase()} = new {methodGroupClientClassName}(this);");
                        }

                        if (restAPIMethods.Any())
                        {
                            constructor.Line($"this.service = {proxyType}.create({restAPIInterfaceName}.class, this);");
                        }
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
                        constructor.Line($"this({proxyType}.createDefaultPipeline());");
                    });

                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description(constructorDescription);
                        comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    });
                    classBlock.PublicConstructor($"{serviceClientClassName}({httpPipelineType} {httpPipelineVariableName})", constructor =>
                    {
                        constructor.Line($"super({httpPipelineVariableName});");

                        foreach (Property serviceClientProperty in GetServiceClientProperties(codeModel))
                        {
                            string defaultValue = serviceClientProperty.DefaultValue;
                            IModelType propertyModelType = GetPropertyModelType(serviceClientProperty);
                            if (defaultValue != null)
                            {
                                if (propertyModelType is PrimaryType propertyModelPrimaryType)
                                {
                                    switch (propertyModelPrimaryType.KnownPrimaryType)
                                    {
                                        case KnownPrimaryType.Double:
                                            defaultValue = double.Parse(defaultValue).ToString();
                                            break;

                                        case KnownPrimaryType.String:
                                            defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                            break;

                                        case KnownPrimaryType.Boolean:
                                            defaultValue = defaultValue.ToLowerInvariant();
                                            break;

                                        case KnownPrimaryType.Long:
                                            defaultValue = defaultValue + 'L';
                                            break;

                                        case KnownPrimaryType.Date:
                                            defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.DateTime:
                                        case KnownPrimaryType.DateTimeRfc1123:
                                            defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.TimeSpan:
                                            defaultValue = $"Period.parse(\"{defaultValue}\")";
                                            break;

                                        case KnownPrimaryType.ByteArray:
                                            defaultValue = $"\"{defaultValue}\".getBytes()";
                                            break;
                                    }
                                }

                                string serviceClientPropertyName = serviceClientProperty?.Name?.ToString();
                                if (!string.IsNullOrEmpty(serviceClientPropertyName))
                                {
                                    CodeNamer codeNamer = CodeNamer.Instance;
                                    serviceClientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(serviceClientPropertyName));
                                }
                                constructor.Line($"this.{serviceClientPropertyName} = {defaultValue};");
                            }
                        }

                        foreach (MethodGroup methodGroup in methodGroups)
                        {
                            string methodGroupClientName = methodGroup.Name.ToString();
                            if (!methodGroupClientName.EndsWith('s'))
                            {
                                methodGroupClientName += 's';
                            }

                            string methodGroupClientClassName = methodGroupClientName.ToPascalCase() + "Impl";
                            constructor.Line($"this.{methodGroupClientName.ToCamelCase()} = new {methodGroupClientClassName}(this);");
                        }

                        if (restAPIMethods.Any())
                        {
                            constructor.Line($"this.service = {proxyType}.create({restAPIInterfaceName}.class, this);");
                        }
                    });
                }

                AddRestAPIInterface(classBlock, codeModel, null, settings);

                AddClientMethodOverloads(classBlock, restAPIMethods, settings);
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientInterfaceJavaFile(CodeModel codeModel, JavaSettings settings)
        {
            string interfaceName = codeModel.Name.ToPascalCase();

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, interfaceName);

            HashSet<string> imports = new HashSet<string>();
            foreach (Method restAPIMethod in GetRestAPIMethods(codeModel))
            {
                HashSet<string> methodImports = new HashSet<string>()
                {
                    "io.reactivex.Observable",
                    "io.reactivex.Single",
                    "com.microsoft.rest.v2.RestResponse",
                    "com.microsoft.rest.v2.ServiceCallback",
                    "com.microsoft.rest.v2.ServiceFuture",
                };

                Response restAPIMethodReturnType = restAPIMethod.ReturnType;
                if (restAPIMethodReturnType.Body == null)
                {
                    methodImports.Add("io.reactivex.Completable");
                }
                else
                {
                    methodImports.Add("io.reactivex.Maybe");
                }

                // parameter types
                foreach (Parameter parameter in restAPIMethod.Parameters)
                {
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }
                    IModelType parameterClientType = ConvertToClientType(parameterModelType);
                    methodImports.AddRange(GetIModelTypeImports(parameterClientType, settings));
                }

                // return type
                IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                    (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                     restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                    SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                    pagedListTypes.Add(resultSequenceType);
                    restAPIMethodReturnBodyClientType = resultSequenceType;
                }

                methodImports.AddRange(GetIModelTypeImports(restAPIMethodReturnBodyClientType, settings));
                methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(restAPIMethodReturnType.Headers), settings));

                // exceptions
                methodImports.Add("java.io.IOException");

                string methodOperationExceptionTypeName;
                IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                if (settings.IsAzureOrFluent && (restAPIMethodExceptionType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                {
                    methodOperationExceptionTypeName = "CloudException";
                }
                else if (restAPIMethodExceptionType is CompositeType compositeReturnType)
                {
                    methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                    if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                    {
                        JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                        if (ext != null && ext["name"] != null)
                        {
                            methodOperationExceptionTypeName = ext["name"].ToString();
                        }
                    }
                }
                else
                {
                    methodOperationExceptionTypeName = "RestException";
                }

                switch (methodOperationExceptionTypeName)
                {
                    case "CloudException":
                        methodImports.Add("com.microsoft.azure.v2.CloudException");
                        break;
                    case "RestException":
                        methodImports.Add("com.microsoft.rest.v2.RestException");
                        break;
                    default:
                        methodImports.Add($"{settings.Package}.models.{methodOperationExceptionTypeName}");
                        break;
                }

                if (settings.IsAzure)
                {
                    bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
                    if (methodIsLongRunningOperation)
                    {
                        methodImports.Add("com.microsoft.azure.v2.OperationStatus");
                    }

                    bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                        restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null;

                    if (methodIsPagingOperation)
                    {
                        methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        methodImports.Add("com.microsoft.azure.v2.Page");
                        methodImports.Add("com.microsoft.azure.v2.PagedList");
                    }

                    if (settings.IsFluent)
                    {
                        bool simulateMethodAsPagingOperation = false;
                        MethodGroup methodGroup = restAPIMethod.MethodGroup;
                        if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                        {
                            MethodType restAPIMethodType = MethodType.Other;
                            string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                            string[] methodUrlSplits = methodUrl.Split('/');
                            switch (restAPIMethod.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                        && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                                    {
                                        if (methodUrlSplits.Length == 5)
                                        {
                                            if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                restAPIMethodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                restAPIMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            restAPIMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Delete;
                                    }
                                    break;
                            }

                            simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                                1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                                {
                                    MethodType methodGroupMethodType = MethodType.Other;
                                    string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                    string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                    switch (methodGroupMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                            && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                            && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                            {
                                                if (methodGroupMethodUrlSplits.Length == 5)
                                                {
                                                    if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }
                                    return methodGroupMethodType == restAPIMethodType;
                                });
                        }
                        if (methodIsPagingOperation || simulateMethodAsPagingOperation)
                        {
                            methodImports.Add("com.microsoft.azure.v2.PagedList");

                            if (!simulateMethodAsPagingOperation)
                            {
                                methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                            }

                            if (restAPIMethodReturnBodyClientSequenceType != null)
                            {
                                string pageImplType = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType);
                                methodImports.AddRange(CompositeTypeImports(pageImplType, false, false, false, true, settings.Package));
                            }
                        }
                    }
                }
                imports.AddRange(methodImports);
            }
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
                foreach (Property property in GetServiceClientProperties(codeModel))
                {
                    string propertyDescription = property.Documentation;
                    string propertyName = property?.Name?.ToString();
                    if (!string.IsNullOrEmpty(propertyName))
                    {
                        CodeNamer codeNamer = CodeNamer.Instance;
                        propertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(propertyName));
                    }
                    string propertyNameCamelCase = propertyName.ToCamelCase();
                    IModelType propertyModelType = GetPropertyModelType(property);
                    IModelType serviceResponseVariant = GetIModelTypeNonNullableVariant(ConvertToClientType(propertyModelType)) ?? propertyModelType;
                    string propertyType = IModelTypeName(serviceResponseVariant, settings);

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
                        interfaceBlock.PublicMethod($"{interfaceName} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase})");
                    }
                }

                foreach (MethodGroup methodGroup in GetMethodGroups(codeModel))
                {
                    string methodGroupName = methodGroup.Name.ToString().ToCamelCase();
                    if (!methodGroupName.EndsWith('s'))
                    {
                        methodGroupName += 's';
                    }

                    string methodGroupClientInterfaceName = methodGroupName.ToPascalCase();
                    interfaceBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Gets the {methodGroupClientInterfaceName} object to access its operations.");
                        comment.Return($"the {methodGroupClientInterfaceName} object.");
                    });
                    interfaceBlock.PublicMethod($"{methodGroupClientInterfaceName} {methodGroupName}()");
                }

                AddClientMethodOverloads(interfaceBlock, GetRestAPIMethods(codeModel), settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientJavaFile(MethodGroup methodGroup, JavaSettings settings)
        {
            MethodGroupClient methodGroupClient = ParseMethodGroupClient(methodGroup, settings);

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, methodGroupClient.ClassName);
            javaFile.Import(methodGroupClient.Imports);

            string parentDeclaration = methodGroupClient.ImplementedInterfaces.Any() ? $" implements {string.Join(", ", methodGroupClient.ImplementedInterfaces)}" : "";

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
            {
                comment.Description($"An instance of this class provides access to all the operations defined in {methodGroupClient.InterfaceName}.");
            });
            javaFile.PublicClass($"{methodGroupClient.ClassName}{parentDeclaration}", classBlock =>
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
                        constructor.Line($"this.service = {(settings.IsAzureOrFluent ? azureProxyType : restProxyType)}.create({methodGroupClient.RestAPI.Name}.class, client);");
                    }
                    constructor.Line("this.client = client;");
                });

                AddRestAPIInterface(classBlock, methodGroupClient.RestAPI, methodGroupClient.InterfaceName, settings);

                AddClientMethodOverloads(classBlock, GetRestAPIMethods(methodGroup), settings);
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupClientInterfaceJavaFile(MethodGroup methodGroup, JavaSettings settings)
        {
            string methodGroupClientInterfaceName = methodGroup.Name.ToString().ToPascalCase();
            if (!methodGroupClientInterfaceName.EndsWith('s'))
            {
                methodGroupClientInterfaceName += 's';
            }

            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, methodGroupClientInterfaceName);

            HashSet<string> imports = new HashSet<string>();
            foreach (Method restAPIMethod in methodGroup.Methods)
            {
                HashSet<string> methodImports = new HashSet<string>()
                {
                    "io.reactivex.Observable",
                    "io.reactivex.Single",
                    "com.microsoft.rest.v2.RestResponse",
                    "com.microsoft.rest.v2.ServiceCallback",
                    "com.microsoft.rest.v2.ServiceFuture",
                };

                Response restAPIMethodReturnType = restAPIMethod.ReturnType;
                if (restAPIMethodReturnType.Body == null)
                {
                    methodImports.Add("io.reactivex.Completable");
                }
                else
                {
                    methodImports.Add("io.reactivex.Maybe");
                }

                // parameter types
                foreach (Parameter parameter in restAPIMethod.Parameters)
                {
                    IModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                    }
                    IModelType parameterClientType = ConvertToClientType(parameterModelType);
                    methodImports.AddRange(GetIModelTypeImports(parameterClientType, settings));
                }

                // return type
                IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                    (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                     restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                    SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                    pagedListTypes.Add(resultSequenceType);
                    restAPIMethodReturnBodyClientType = resultSequenceType;
                }
                methodImports.AddRange(GetIModelTypeImports(restAPIMethodReturnBodyClientType, settings));
                methodImports.AddRange(GetIModelTypeImports(ConvertToClientType(restAPIMethodReturnType.Headers), settings));

                // exceptions
                methodImports.Add("java.io.IOException");

                string methodOperationExceptionTypeName;
                IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                if (settings.IsAzureOrFluent && (restAPIMethodExceptionType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                {
                    methodOperationExceptionTypeName = "CloudException";
                }
                else if (restAPIMethodExceptionType is CompositeType compositeReturnType)
                {
                    methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                    if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                    {
                        JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                        if (ext != null && ext["name"] != null)
                        {
                            methodOperationExceptionTypeName = ext["name"].ToString();
                        }
                    }
                }
                else
                {
                    methodOperationExceptionTypeName = "RestException";
                }

                switch (methodOperationExceptionTypeName)
                {
                    case "CloudException":
                        methodImports.Add("com.microsoft.azure.v2.CloudException");
                        break;
                    case "RestException":
                        methodImports.Add("com.microsoft.rest.v2.RestException");
                        break;
                    default:
                        methodImports.Add($"{settings.Package}.models.{methodOperationExceptionTypeName}");
                        break;
                }

                if (settings.IsAzure)
                {
                    bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
                    if (methodIsLongRunningOperation)
                    {
                        methodImports.Add("com.microsoft.azure.v2.OperationStatus");
                    }

                    bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                        restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null;

                    if (methodIsPagingOperation)
                    {
                        methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                        methodImports.Add("com.microsoft.azure.v2.Page");
                        methodImports.Add("com.microsoft.azure.v2.PagedList");
                    }

                    if (settings.IsFluent)
                    {
                        bool simulateMethodAsPagingOperation = false;
                        if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                        {
                            MethodType restAPIMethodType = MethodType.Other;
                            string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                            string[] methodUrlSplits = methodUrl.Split('/');
                            switch (restAPIMethod.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                        && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                                    {
                                        if (methodUrlSplits.Length == 5)
                                        {
                                            if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                restAPIMethodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                restAPIMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            restAPIMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Delete;
                                    }
                                    break;
                            }

                            simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                                1 == methodGroup.Methods.Count((Method methodGroupMethod) =>
                                {
                                    MethodType methodGroupMethodType = MethodType.Other;
                                    string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                    string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                    switch (methodGroupMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                            && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                            && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                            {
                                                if (methodGroupMethodUrlSplits.Length == 5)
                                                {
                                                    if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }
                                    return methodGroupMethodType == restAPIMethodType;
                                });
                        }
                        if (methodIsPagingOperation || simulateMethodAsPagingOperation)
                        {
                            methodImports.Add("com.microsoft.azure.v2.PagedList");

                            if (!simulateMethodAsPagingOperation)
                            {
                                methodImports.Remove("com.microsoft.rest.v2.ServiceCallback");
                            }

                            if (restAPIMethodReturnBodyClientSequenceType != null)
                            {
                                string pageImplType = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType);
                                methodImports.AddRange(CompositeTypeImports(pageImplType, false, false, false, true, settings.Package));
                            }
                        }
                    }
                }
                imports.AddRange(methodImports);
            }
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

        public static IEnumerable<CompositeType> GetModelTypes(CodeModel codeModel, JavaSettings settings)
        {
            List<CompositeType> result = new List<CompositeType>();

            foreach (CompositeType modelType in codeModel.ModelTypes.Union(codeModel.HeaderTypes))
            {
                if (ShouldParseModelType(modelType, settings))
                {
                    result.Add(modelType);
                }
            }

            return result;
        }

        private static bool ShouldParseModelType(CompositeType modelType, JavaSettings settings)
        {
            bool shouldParseModelType = false;
            if (modelType != null)
            {
                if (!settings.IsAzure)
                {
                    shouldParseModelType = true;
                }
                else if (!GetExtensionBool(modelType, SwaggerExtensions.ExternalExtension))
                {
                    string modelTypeName = GetCompositeTypeName(modelType, settings);
                    bool modelTypeIsAzureResourceExtension = GetExtensionBool(modelType, AzureExtensions.AzureResourceExtension);
                    shouldParseModelType = modelTypeName != "Resource" && (modelTypeName != "SubResource" || !modelTypeIsAzureResourceExtension);
                }
            }
            return shouldParseModelType;
        }

        public static JavaFile GetModelJavaFile(ServiceModel model, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(model.Package, settings, model.Name);

            javaFile.Import(model.Imports);

            javaFile.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
            {
                comment.Description(model.Description);
            });

            if (model.IsPolymorphic)
            {
                bool hasDerivedModels = model.DerivedModels.Any();

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

            string classNameWithBaseType = model.Name;
            if (model.ParentModel != null)
            {
                classNameWithBaseType += $" extends {model.ParentModel.Name}";
            }
            javaFile.PublicClass(classNameWithBaseType, (classBlock) =>
            {
                foreach (ServiceProperty property in model.Properties)
                {
                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description(property.Description);
                    });

                    if (!string.IsNullOrEmpty(property.AnnotationArguments))
                    {
                        if (property.IsXmlAttribute)
                        {
                            string localName = settings.ShouldGenerateXmlSerialization ? property.XmlName : property.SerializedName;
                            classBlock.Annotation($"JacksonXmlProperty(localName = \"{localName}\", isAttribute = true)");
                        }
                        else if (settings.ShouldGenerateXmlSerialization && property.IsXmlWrapper && property.ModelTypeIsSequence)
                        {
                            string localName = settings.ShouldGenerateXmlSerialization ? property.XmlName : property.SerializedName.ToString();
                            classBlock.Annotation($"JacksonXmlElementWrapper(localName = \"{localName}\")");
                        }
                        else
                        {
                            classBlock.Annotation($"JsonProperty({property.AnnotationArguments})");
                        }
                    }
                    classBlock.PrivateMemberVariable($"{property.WireTypeName} {property.Name}");
                }

                IEnumerable<ServiceProperty> constantProperties = model.Properties.Where(property => property.IsConstant);
                if (constantProperties.Any())
                {
                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Creates an instance of {model.Name} class.");
                    });
                    classBlock.PublicConstructor($"{model.Name}()", (constructor) =>
                    {
                        foreach (ServiceProperty constantProperty in constantProperties)
                        {
                            bool propertyIsPrimitiveType = !(constantProperty.ModelTypeIsComposite);
                            if (!propertyIsPrimitiveType)
                            {
                                constructor.Line($"{constantProperty.Name} = new {constantProperty.WireTypeName}();");
                            }
                            else
                            {
                                constructor.Line($"{constantProperty.Name} = {constantProperty.DefaultValue};");
                            }
                        }
                    });
                }

                foreach (ServiceProperty property in model.Properties)
                {
                    classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, (comment) =>
                    {
                        comment.Description($"Get the {property.Name} value.");
                        comment.Return($"the {property.Name} value");
                    });
                    classBlock.PublicMethod($"{property.ClientTypeName} {property.Name}()", (methodBlock) =>
                    {
                        string sourceTypeName = property.WireTypeName;
                        string targetTypeName = property.ClientTypeName;
                        string expression = $"this.{property.Name}";
                        if (sourceTypeName == targetTypeName)
                        {
                            methodBlock.Return($"this.{property.Name}");
                        }
                        else
                        {
                            methodBlock.If($"{expression} == null", (ifBlock) =>
                            {
                                ifBlock.Return("null");
                            });

                            string propertyConversion = null;
                            switch (sourceTypeName.ToLower())
                            {
                                case "datetime":
                                    switch (targetTypeName.ToLower())
                                    {
                                        case "datetimerfc1123":
                                            propertyConversion = $"new DateTimeRfc1123({expression})";
                                            break;
                                    }
                                    break;

                                case "datetimerfc1123":
                                    switch (targetTypeName.ToLower())
                                    {
                                        case "datetime":
                                            propertyConversion = $"{expression}.dateTime()";
                                            break;
                                    }
                                    break;
                            }

                            if (propertyConversion == null)
                            {
                                throw new NotSupportedException($"No conversion from {sourceTypeName} to {targetTypeName} is available.");
                            }

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
                        classBlock.PublicMethod($"{model.Name} with{property.Name.ToPascalCase()}({property.ClientTypeName} {property.Name})", (methodBlock) =>
                        {
                            if (property.ClientTypeName != property.WireTypeName)
                            {
                                methodBlock.If($"{property.Name} == null", (ifBlock) =>
                                {
                                    ifBlock.Line($"this.{property.Name} = null;");
                                })
                                .Else((elseBlock) =>
                                {
                                    string sourceTypeName = property.ClientTypeName;
                                    string targetTypeName = property.WireTypeName;
                                    string expression = property.Name;
                                    string propertyConversion = null;
                                    if (sourceTypeName == targetTypeName)
                                    {
                                        propertyConversion = expression;
                                    }
                                    else
                                    {
                                        switch (sourceTypeName.ToLower())
                                        {
                                            case "datetime":
                                                switch (targetTypeName.ToLower())
                                                {
                                                    case "datetimerfc1123":
                                                        propertyConversion = $"new DateTimeRfc1123({expression})";
                                                        break;
                                                }
                                                break;

                                            case "datetimerfc1123":
                                                switch (targetTypeName.ToLower())
                                                {
                                                    case "datetime":
                                                        propertyConversion = $"{expression}.dateTime()";
                                                        break;
                                                }
                                                break;
                                        }

                                        if (propertyConversion == null)
                                        {
                                            throw new NotSupportedException($"No conversion from {sourceTypeName} to {targetTypeName} is available.");
                                        }
                                    }
                                    elseBlock.Line($"this.{property.Name} = {propertyConversion};");
                                });
                            }
                            else
                            {
                                methodBlock.Line($"this.{property.Name} = {property.Name};");
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

        private static IEnumerable<Property> GetServiceClientProperties(CodeModel codeModel)
            => codeModel.Properties.Where(p => !IsServiceClientCredentialProperty(p));

        private static bool HasServiceClientCredentials(CodeModel codeModel)
            => codeModel.Properties.Any(IsServiceClientCredentialProperty);

        private static bool IsServiceClientCredentialProperty(Property property)
            => GetPropertyModelType(property).IsPrimaryType(KnownPrimaryType.Credentials);

        private static IEnumerable<MethodGroup> GetMethodGroups(CodeModel codeModel)
            => codeModel.Operations.Where((MethodGroup operation) => !string.IsNullOrEmpty(operation?.Name?.ToString()));

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
            return IsNullable(property)
                ? propertyModelType
                : GetIModelTypeNonNullableVariant(propertyModelType);
        }

        private static IEnumerable<string> GetIModelTypeImports(IModelType modelType, JavaSettings settings)
        {
            HashSet<string> result = new HashSet<string>();

            if (modelType != null)
            {
                if (modelType is EnumType)
                {
                    string modelTypeName = IModelTypeName(modelType, settings);
                    if (modelTypeName != "String")
                    {
                        if (!settings.IsFluent)
                        {
                            result.Add(string.Join(".", settings.Package, "models", modelTypeName));
                        }
                        else
                        {
                            result.Add(string.Join(".", settings.Package + (modelTypeName.EndsWith("Inner") ? ".implementation" : ""), modelTypeName));
                        }
                    }
                }
                else if (modelType is CompositeType compositeType)
                {
                    string compositeTypeName = GetCompositeTypeName(compositeType, settings);
                    bool compositeTypeIsExternalExtension = GetExtensionBool(compositeType, SwaggerExtensions.ExternalExtension);
                    bool compositeTypeIsAzureResourceExtension = GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);
                    result.AddRange(CompositeTypeImports(compositeTypeName, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings.IsAzure, settings.IsFluent, settings.Package));
                }
                else if (modelType is SequenceType sequenceType)
                {
                    result.Add("java.util.List");
                    result.AddRange(GetIModelTypeImports(sequenceType.ElementType, settings));
                }
                else if (modelType is DictionaryType dictionaryType)
                {
                    result.Add("java.util.Map");
                    result.AddRange(GetIModelTypeImports(dictionaryType.ValueType, settings));
                }
                else if (modelType is PrimaryType primaryType)
                {
                    switch (primaryType.KnownPrimaryType)
                    {
                        case KnownPrimaryType.Base64Url:
                            result.Add("com.microsoft.rest.v2.Base64Url");
                            break;
                        case KnownPrimaryType.Date:
                            result.Add("org.joda.time.LocalDate");
                            break;
                        case KnownPrimaryType.DateTime:
                            result.Add("org.joda.time.DateTime");
                            break;
                        case KnownPrimaryType.DateTimeRfc1123:
                            result.Add("com.microsoft.rest.v2.DateTimeRfc1123");
                            break;
                        case KnownPrimaryType.Decimal:
                            result.Add("java.math.BigDecimal");
                            break;
                        case KnownPrimaryType.Stream:
                            result.Add("io.reactivex.Flowable");
                            break;
                        case KnownPrimaryType.TimeSpan:
                            result.Add("org.joda.time.Period");
                            break;
                        case KnownPrimaryType.UnixTime:
                            result.Add("org.joda.time.DateTime");
                            result.Add("org.joda.time.DateTimeZone");
                            break;
                        case KnownPrimaryType.Uuid:
                            result.Add("java.util.UUID");
                            break;
                        case KnownPrimaryType.Credentials:
                            result.Add(serviceClientCredentialsImport);
                            break;
                    }
                }
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeImports(string compositeTypeName, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, bool isAzure, bool isFluent, string settingsPackage)
        {
            HashSet<string> imports = new HashSet<string>();
            if (!string.IsNullOrEmpty(compositeTypeName))
            {
                if (compositeTypeName.Contains('<'))
                {
                    string[] types = compositeTypeName.Split(new String[] { "<", ">", ",", ", " }, StringSplitOptions.RemoveEmptyEntries);
                    foreach (string innerTypeName in types.Where(t => !string.IsNullOrWhiteSpace(t)))
                    {
                        string trimmedInnerTypeName = innerTypeName.Trim();
                        if (!primaryTypes.Contains(trimmedInnerTypeName))
                        {
                            imports.AddRange(CompositeTypeImports(compositeTypeName, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, isAzure, isFluent, settingsPackage));
                        }
                    }
                }
                else
                {
                    string compositeTypePackage;
                    if (isAzure || isFluent)
                    {
                        if (compositeTypeName == "Resource" || (compositeTypeName == "SubResource" && compositeTypeIsAzureResourceExtension))
                        {
                            compositeTypePackage = "com.microsoft.azure.v2";
                        }
                        else if (compositeTypeIsExternalExtension)
                        {
                            compositeTypePackage = "com.microsoft.rest.v2";
                        }
                        else if (isFluent)
                        {
                            if (compositeTypeName.EndsWith("Inner", StringComparison.Ordinal))
                            {
                                compositeTypePackage = settingsPackage + ".implementation";
                            }
                            else
                            {
                                compositeTypePackage = settingsPackage;
                            }
                        }
                        else
                        {
                            compositeTypePackage = settingsPackage + ".models";
                        }
                    }
                    else
                    {
                        if (compositeTypeIsExternalExtension)
                        {
                            compositeTypePackage = "com.microsoft.rest.v2";
                        }
                        else
                        {
                            compositeTypePackage = settingsPackage + ".models";
                        }
                    }
                    imports.Add(string.Join(".", compositeTypePackage, compositeTypeName));
                }
            }
            return imports;
        }

        private static IModelType ConvertToClientType(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is SequenceType sequenceTypeJv)
            {
                IModelType elementClientType = ConvertToClientType(sequenceTypeJv.ElementType);

                if (elementClientType != sequenceTypeJv.ElementType)
                {
                    bool elementClientPrimaryTypeIsNullable = true;
                    if (elementClientType is PrimaryType elementClientPrimaryType && !PrimaryTypeGetWantNullable(elementClientPrimaryType))
                    {
                        switch (elementClientPrimaryType.KnownPrimaryType)
                        {
                            case KnownPrimaryType.None:
                            case KnownPrimaryType.Boolean:
                            case KnownPrimaryType.Double:
                            case KnownPrimaryType.Int:
                            case KnownPrimaryType.Long:
                            case KnownPrimaryType.UnixTime:
                                elementClientPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    if (elementClientPrimaryTypeIsNullable)
                    {
                        SequenceType sequenceType = DependencyInjection.New<SequenceType>();
                        sequenceType.ElementType = elementClientType;
                        result = sequenceType;
                    }
                }
            }
            else if (modelType is DictionaryType dictionaryType)
            {
                IModelType dictionaryValueClientType = ConvertToClientType(dictionaryType.ValueType);

                if (dictionaryValueClientType != dictionaryType.ValueType)
                {
                    bool dictionaryValueClientPrimaryTypeIsNullable = true;
                    if (dictionaryValueClientType is PrimaryType dictionaryValueClientPrimaryType && !PrimaryTypeGetWantNullable(dictionaryValueClientPrimaryType))
                    {
                        switch (dictionaryValueClientPrimaryType.KnownPrimaryType)
                        {
                            case KnownPrimaryType.None:
                            case KnownPrimaryType.Boolean:
                            case KnownPrimaryType.Double:
                            case KnownPrimaryType.Int:
                            case KnownPrimaryType.Long:
                            case KnownPrimaryType.UnixTime:
                                dictionaryValueClientPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    if (dictionaryValueClientPrimaryTypeIsNullable)
                    {
                        DictionaryType dictionaryTypeResult = DependencyInjection.New<DictionaryType>();
                        dictionaryTypeResult.ValueType = dictionaryValueClientType;
                        result = dictionaryTypeResult;
                    }
                }
            }
            else if (modelType is PrimaryType primaryType)
            {
                if (primaryType.KnownPrimaryType == KnownPrimaryType.DateTimeRfc1123 ||
                    primaryType.KnownPrimaryType == KnownPrimaryType.UnixTime)
                {
                    result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.DateTime);
                }
                else if (primaryType.KnownPrimaryType == KnownPrimaryType.Base64Url)
                {
                    result = DependencyInjection.New<PrimaryType>(KnownPrimaryType.ByteArray);
                }
                else if (primaryType.KnownPrimaryType == KnownPrimaryType.None)
                {
                    result = GetIModelTypeNonNullableVariant(primaryType);
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
                if (modelType is EnumType enumType)
                {
                    result = GetEnumTypeName(enumType);
                }
                else if (modelType is SequenceType sequenceType)
                {
                    result = GetSequenceTypeName(sequenceType, settings);
                }
                else if (modelType is DictionaryType dictionaryType)
                {
                    result = $"Map<String, {IModelTypeName(dictionaryType.ValueType, settings)}>";
                }
                else if (modelType is CompositeType compositeType)
                {
                    result = GetCompositeTypeName(compositeType, settings);
                }
                else if (modelType is PrimaryType primaryType)
                {
                    result = PrimaryTypeName(primaryType);
                }
            }
            return result;
        }

        private static string PrimaryTypeName(PrimaryType primaryType)
        {
            string result;
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
                    result = "Flowable<byte[]>";
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
            return result;
        }

        private static string GetEnumTypeName(EnumType enumType)
        {
            string result = null;
            if (enumType != null)
            {
                result = enumType.Name.ToString();
                result = (string.IsNullOrEmpty(result) || result == "enum" ? "String" : CodeNamer.Instance.GetTypeName(result));
            }
            return result;
        }

        private static string GetCompositeTypeName(CompositeType compositeType, JavaSettings settings)
        {
            string result = null;
            if (compositeType != null)
            {
                result = compositeType.Name.ToString();

                if (settings.IsFluent)
                {
                    result = string.IsNullOrEmpty(result) || !innerModelCompositeType.Contains(compositeType) ? result : result + "Inner";
                }
            }
            return result;
        }

        private static string GetSequenceTypeName(SequenceType sequenceType, JavaSettings settings)
        {
            string result = null;
            if (sequenceType != null)
            {
                result = $"List<{IModelTypeName(sequenceType.ElementType, settings)}>";
                if (pagedListTypes.Contains(sequenceType))
                {
                    result = "Paged" + result;
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

        private static bool GetExtensionBool(IDictionary<string, object> extensions, string extensionName)
            => extensions?.Get<bool>(extensionName) == true;

        private static bool GetExtensionBool(ModelType modelType, string extensionName)
            => GetExtensionBool(modelType?.Extensions, extensionName);

        private static string SequenceTypeGetPageImplType(IModelType modelType)
            => pageImplTypes.ContainsKey(modelType) ? pageImplTypes[modelType] : null;

        private static void SequenceTypeSetPageImplType(IModelType modelType, string pageImplType)
            => pageImplTypes[modelType] = pageImplType;

        private static bool IsTopLevelResourceUrl(string[] urlSplits)
        {
            return urlSplits.Length == 8 &&
                urlSplits[0].EqualsIgnoreCase("subscriptions") &&
                urlSplits[2].EqualsIgnoreCase("resourceGroups") &&
                urlSplits[4].EqualsIgnoreCase("providers");
        }

        private enum MethodType
        {
            Other,
            ListBySubscription,
            ListByResourceGroup,
            Get,
            Delete
        }

        private static bool MethodHasSequenceType(IModelType modelType, JavaSettings settings)
            => modelType is SequenceType ||
            (modelType is CompositeType ct && GetCompositeTypeProperties(ct, settings).Any(p => MethodHasSequenceType(GetPropertyModelType(p), settings)));

        private static bool PrimaryTypeGetWantNullable(PrimaryType primaryType)
            => !primaryTypeNotWantNullable.Contains(primaryType);

        private static void ParameterConvertClientTypeToWireType(JavaBlock block, JavaSettings settings, Parameter parameter, IModelType parameterWireType, string source, string target, string clientReference, int level = 0)
        {
            bool parameterIsRequired = parameter.IsRequired;
            if (parameterWireType is PrimaryType parameterWirePrimaryType)
            {
                if (parameterWirePrimaryType.KnownPrimaryType == KnownPrimaryType.DateTimeRfc1123)
                {
                    if (parameterIsRequired)
                    {
                        block.Line($"DateTimeRfc1123 {target} = new DateTimeRfc1123({source});");
                    }
                    else
                    {
                        block.Line($"DateTimeRfc1123 {target} = null;");
                        block.If($"{source} != null", ifBlock =>
                        {
                            ifBlock.Line($"{target} = new DateTimeRfc1123({source});");
                        });
                    }
                }
                else if (parameterWirePrimaryType.KnownPrimaryType == KnownPrimaryType.UnixTime)
                {
                    if (parameterIsRequired)
                    {
                        block.Line($"Long {target} = {source}.toDateTime(DateTimeZone.UTC).getMillis() / 1000;");
                    }
                    else
                    {
                        block.Line($"Long {target} = null;");
                        block.If($"{source} != null", ifBlock =>
                        {
                            ifBlock.Line($"{target} = {source}.toDateTime(DateTimeZone.UTC).getMillis() / 1000;");
                        });
                    }
                }
                else if (parameterWirePrimaryType.KnownPrimaryType == KnownPrimaryType.Base64Url)
                {
                    if (parameterIsRequired)
                    {
                        block.Line($"Base64Url {target} = Base64Url.encode({source});");
                    }
                    else
                    {
                        block.Line($"Base64Url {target} = null;");
                        block.If($"{source} != null", ifBlock =>
                        {
                            ifBlock.Line($"{target} = Base64Url.encode({source});");
                        });
                    }
                }
            }
            else if (parameterWireType is SequenceType wireSequenceType)
            {
                if (!parameterIsRequired)
                {
                    block.Line("{0} {1} = {2};",
                        IModelTypeName(parameterWireType, settings),
                        target,
                        parameterWireType.DefaultValue ?? "null");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                string levelSuffix = (level == 0 ? "" : level.ToString());
                string itemName = $"item{levelSuffix}";
                string itemTarget = $"value{levelSuffix}";
                IModelType elementType = wireSequenceType.ElementType;
                block.Line("{0}{1} = new ArrayList<{2}>();",
                    parameterIsRequired ? IModelTypeName(parameterWireType, settings) + " " : "",
                    target,
                    IModelTypeName(elementType, settings));
                block.Line("for ({0} {1} : {2}) {{",
                    IModelTypeName(ConvertToClientType(elementType), settings),
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
            else if (parameterWireType is DictionaryType dictionaryType)
            {
                if (!parameterIsRequired)
                {
                    block.Line($"{IModelTypeName(parameterWireType, settings)} {target} = {parameterWireType.DefaultValue ?? "null"};");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                IModelType valueType = dictionaryType.ValueType;

                string levelString = (level == 0 ? "" : level.ToString(CultureInfo.InvariantCulture));
                string itemName = $"entry{levelString}";
                string itemTarget = $"value{levelString}";

                block.Line($"{(parameterIsRequired ? IModelTypeName(parameterWireType, settings) + " " : "")}{target} = new HashMap<String, {IModelTypeName(valueType, settings)}>();");
                block.Line($"for (Map.Entry<String, {IModelTypeName(ConvertToClientType(valueType), settings)}> {itemName} : {source}.entrySet()) {{");
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

        private static void AddRestAPIInterface(JavaClass classBlock, CodeModel codeModel, MethodGroup methodGroup, JavaSettings settings)
        {
            IEnumerable<Method> restAPIMethods = methodGroup == null ? GetRestAPIMethods(codeModel) : GetRestAPIMethods(methodGroup);
            if (restAPIMethods.Any())
            {
                string clientTypeName;
                if (methodGroup == null)
                {
                    clientTypeName = codeModel.Name.ToPascalCase();
                }
                else
                {
                    clientTypeName = methodGroup.Name.ToString().ToPascalCase();
                    if (!clientTypeName.EndsWith('s'))
                    {
                        clientTypeName += 's';
                    }
                }

                string restAPIInterfaceName = clientTypeName + "Service";

                classBlock.JavadocComment(settings.MaximumJavadocCommentWidth, comment =>
                {
                    comment.Description($"The interface defining all the services for {clientTypeName} to be used by the proxy service to perform REST calls.");
                });
                classBlock.Annotation($"Host(\"{codeModel.BaseUrl}\")");
                classBlock.Interface(restAPIInterfaceName, interfaceBlock =>
                {
                    foreach (Method restAPIMethod in restAPIMethods)
                    {
                        string requestContentType = restAPIMethod.RequestContentType;
                        if (requestContentType == "multipart/form-data" || requestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.LineComment($"@Multipart not supported by {restProxyType}");
                        }

                        bool methodIsPagingNextOperation = GetExtensionBool(restAPIMethod?.Extensions, "nextLinkMethod");
                        if (methodIsPagingNextOperation)
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

                        Response restAPIMethodReturnType = restAPIMethod.ReturnType;

                        IModelType returnTypeCurrentType = restAPIMethodReturnType.Body;
                        while (returnTypeCurrentType != null)
                        {
                            if (returnTypeCurrentType is SequenceType currentSequenceType)
                            {
                                returnTypeCurrentType = currentSequenceType.ElementType;
                            }
                            else if (returnTypeCurrentType is DictionaryType currentDictionaryType)
                            {
                                returnTypeCurrentType = currentDictionaryType.ValueType;
                            }
                            else
                            {
                                if (returnTypeCurrentType is PrimaryType currentPrimaryType)
                                {
                                    string currentPrimaryTypeName = currentPrimaryType?.Name?.FixedValue;
                                    if (currentPrimaryTypeName.EqualsIgnoreCase("Base64Url") ||
                                        currentPrimaryTypeName.EqualsIgnoreCase("DateTimeRfc1123") ||
                                        currentPrimaryTypeName.EqualsIgnoreCase("UnixTime"))
                                    {
                                        interfaceBlock.Annotation($"ReturnValueWireType({currentPrimaryTypeName}.class)");
                                    }
                                }

                                returnTypeCurrentType = null;
                            }
                        }

                        IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                        SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                        bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                            (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                             restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                        if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                        {
                            SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                            resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                            SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                            pagedListTypes.Add(resultSequenceType);
                            restAPIMethodReturnBodyClientType = resultSequenceType;
                        }

                        if (restAPIMethod.DefaultResponse.Body != null)
                        {
                            string methodOperationExceptionTypeName;
                            IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                            if (settings.IsAzureOrFluent && (restAPIMethodExceptionType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                            {
                                methodOperationExceptionTypeName = "CloudException";
                            }
                            else if (restAPIMethodExceptionType is CompositeType compositeReturnType)
                            {
                                methodOperationExceptionTypeName = GetCompositeTypeName(compositeReturnType, settings) + "Exception";
                                if (compositeReturnType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                                {
                                    JContainer ext = compositeReturnType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                                    if (ext != null && ext["name"] != null)
                                    {
                                        methodOperationExceptionTypeName = ext["name"].ToString();
                                    }
                                }
                            }
                            else
                            {
                                methodOperationExceptionTypeName = "RestException";
                            }
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({methodOperationExceptionTypeName}.class)");
                        }

                        bool simulateMethodAsPagingOperation = false;
                        string methodName = restAPIMethod.Name;
                        string wellKnownMethodName = null;
                        if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                        {
                            MethodType restAPIMethodType = MethodType.Other;
                            string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                            string[] methodUrlSplits = methodUrl.Split('/');
                            switch (restAPIMethod.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                        && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                                    {
                                        if (methodUrlSplits.Length == 5)
                                        {
                                            if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                restAPIMethodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                restAPIMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            restAPIMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(methodUrlSplits))
                                    {
                                        restAPIMethodType = MethodType.Delete;
                                    }
                                    break;
                            }

                            if (restAPIMethodType != MethodType.Other)
                            {
                                int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
                                {
                                    MethodType methodGroupMethodType = MethodType.Other;
                                    string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                    string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                    switch (methodGroupMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                            {
                                                if (methodGroupMethodUrlSplits.Length == 5)
                                                {
                                                    if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        methodGroupMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        methodGroupMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                            {
                                                methodGroupMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }
                                    return methodGroupMethodType == restAPIMethodType;
                                });

                                if (methodsWithSameType == 1)
                                {
                                    switch (restAPIMethodType)
                                    {
                                        case MethodType.ListBySubscription:
                                            wellKnownMethodName = List;
                                            simulateMethodAsPagingOperation = true;
                                            break;

                                        case MethodType.ListByResourceGroup:
                                            wellKnownMethodName = ListByResourceGroup;
                                            simulateMethodAsPagingOperation = true;
                                            break;

                                        case MethodType.Delete:
                                            wellKnownMethodName = Delete;
                                            break;

                                        case MethodType.Get:
                                            wellKnownMethodName = GetByResourceGroup;
                                            break;

                                        default:
                                            throw new Exception("Flow should not hit this statement.");
                                    }
                                }
                            }
                        }
                        if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
                        {
                            IParent methodParent = restAPIMethod.Parent;
                            methodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, restAPIMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(restAPIMethod.SingleItemAsEnumerable()));
                        }
                        methodName = methodName.ToCamelCase();

                        bool shouldGenerateXmlSerialization = settings.ShouldGenerateXmlSerialization;

                        List<string> parameterDeclarationList = new List<string>();

                        List<Parameter> methodRetrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();
                        if (settings.IsAzureOrFluent && methodIsPagingNextOperation)
                        {
                            methodRetrofitParameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                            Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                            nextUrlParam.SerializedName = "nextUrl";
                            nextUrlParam.Documentation = "The URL to get the next page of items.";
                            nextUrlParam.Location = ParameterLocation.Path;
                            nextUrlParam.IsRequired = true;
                            nextUrlParam.ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                            nextUrlParam.Name = "nextUrl";
                            nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                            methodRetrofitParameters.Insert(0, nextUrlParam);
                        }

                        IEnumerable<Parameter> orderedRetrofitParameters = methodRetrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                            .Union(methodRetrofitParameters.Where(p => p.Location != ParameterLocation.Path));
                        foreach (Parameter parameter in orderedRetrofitParameters)
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

                            StringBuilder parameterDeclarationBuilder = new StringBuilder();
                            if (restAPIMethod.Url.Contains("{" + parameterName + "}"))
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

                            string declarativeName = parameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(declarativeName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                declarativeName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(declarativeName));
                            }
                            if (declarativeName == null)
                            {
                                declarativeName = parameterName;
                            }

                            IModelType parameterModelType = parameter.ModelType;
                            if (parameterModelType != null && !IsNullable(parameter))
                            {
                                parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                            }
                            bool shouldUseXmlListWrapper = shouldGenerateXmlSerialization && parameter.Location == ParameterLocation.Body && parameterModelType is SequenceType;
                            if (shouldUseXmlListWrapper)
                            {
                                parameterDeclarationBuilder.Append(parameterModelType.XmlName + "Wrapper");
                            }
                            else
                            {
                                IModelType parameterWireType;
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);
                                if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                    parameter.Location != ParameterLocation.Body &&
                                    parameter.Location != ParameterLocation.FormData &&
                                    ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                }
                                else
                                {
                                    parameterWireType = parameterModelType;
                                }
                                parameterDeclarationBuilder.Append(IModelTypeName(parameterWireType, settings));
                            }

                            parameterDeclarationBuilder.Append(" " + declarativeName);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);

                        if (settings.IsAzureOrFluent)
                        {
                            foreach (Parameter parameter in orderedRetrofitParameters.Where(p => p.Location == ParameterLocation.Path || p.Location == ParameterLocation.Query))
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

                        string responseGenericBodyClientTypeString;
                        if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                        {
                            responseGenericBodyClientTypeString = $"PagedList<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                        }
                        else
                        {
                            IModelType responseBodyWireType = restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                            IModelType responseVariant = ConvertToClientType(responseBodyWireType);

                            bool responseVariantPrimaryTypeIsNullable = true;
                            if (responseVariant is PrimaryType responseVariantPrimaryType && !PrimaryTypeGetWantNullable(responseVariantPrimaryType))
                            {
                                switch (responseVariantPrimaryType.KnownPrimaryType)
                                {
                                    case KnownPrimaryType.None:
                                    case KnownPrimaryType.Boolean:
                                    case KnownPrimaryType.Double:
                                    case KnownPrimaryType.Int:
                                    case KnownPrimaryType.Long:
                                    case KnownPrimaryType.UnixTime:
                                        responseVariantPrimaryTypeIsNullable = false;
                                        break;
                                }
                            }

                            IModelType responseGenericBodyClientType = responseVariantPrimaryTypeIsNullable ? responseVariant : responseBodyWireType;
                            responseGenericBodyClientTypeString = IModelTypeName(responseGenericBodyClientType, settings);
                        }

                        bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);
                        if (methodIsLongRunningOperation)
                        {
                            string responseGenericParameterString;
                            if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                            {
                                responseGenericParameterString = $"Page<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                            }
                            else
                            {
                                responseGenericParameterString = responseGenericBodyClientTypeString;
                            }

                            interfaceBlock.PublicMethod($"Observable<OperationStatus<{responseGenericParameterString}>> {methodName}({parameterDeclarations})");
                        }
                        else
                        {
                            IModelType restAPIMethodResponseHeaders = restAPIMethodReturnType.Headers;
                            string deserializedResponseHeadersType = restAPIMethodResponseHeaders == null ? "Void" : IModelTypeName(ConvertToClientType(restAPIMethodResponseHeaders), settings);
                            string deserializedResponseBodyType;
                            if (restAPIMethodReturnType.Body == null)
                            {
                                deserializedResponseBodyType = "Void";
                            }
                            else if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                            {
                                deserializedResponseBodyType = $"{SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType)}<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                            }
                            else
                            {
                                deserializedResponseBodyType = responseGenericBodyClientTypeString;
                            }
                            string restResponseConcreteTypeName = $"RestResponse<{deserializedResponseHeadersType}, {deserializedResponseBodyType}>";
                            interfaceBlock.PublicMethod($"Single<{restResponseConcreteTypeName}> {methodName}({parameterDeclarations})");
                        }
                    }
                });
            }
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
                classBlock.Interface(restAPI.Name, interfaceBlock =>
                {
                    foreach (RestAPIMethod restAPIMethod in restAPI.Methods)
                    {
                        if (restAPIMethod.RequestContentType == "multipart/form-data" || restAPIMethod.RequestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.LineComment($"@Multipart not supported by {restProxyType}");
                        }

                        if (restAPIMethod.IsPagingNextOperation)
                        {
                            interfaceBlock.Annotation("GET(\"{nextUrl}\")");
                        }
                        else
                        {
                            interfaceBlock.Annotation($"{restAPIMethod.HttpMethod}(\"{restAPIMethod.UrlPath}\")");
                        }

                        if (restAPIMethod.ResponseExpectedStatusCodes.Any())
                        {
                            interfaceBlock.Annotation($"ExpectedResponses({{{string.Join(", ", restAPIMethod.ResponseExpectedStatusCodes.Select(statusCode => statusCode.ToString("D")))}}})");
                        }

                        if (!string.IsNullOrEmpty(restAPIMethod.ReturnValueWireType))
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({restAPIMethod.ReturnValueWireType}.class)");
                        }

                        if (!string.IsNullOrEmpty(restAPIMethod.UnexpectedResponseExceptionType))
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({restAPIMethod.UnexpectedResponseExceptionType}.class)");
                        }

                        List<string> parameterDeclarationList = new List<string>();
                        foreach (RestAPIParameter parameter in restAPIMethod.Parameters)
                        {
                            StringBuilder parameterDeclarationBuilder = new StringBuilder();

                            switch (parameter.Location.ToLower())
                            {
                                case "host":
                                    parameterDeclarationBuilder.Append($"@HostParam(\"{parameter.SerializedName}\") ");
                                    break;

                                case "path":
                                case "query":
                                case "header":
                                    parameterDeclarationBuilder.Append($"@{parameter.Location}Param(\"{parameter.SerializedName}\") ");
                                    break;

                                case "body":
                                    parameterDeclarationBuilder.Append($"@BodyParam(\"{restAPIMethod.RequestContentType}\") ");
                                    break;

                                case "formdata":
                                    parameterDeclarationBuilder.Append($"/* @Part(\"{parameter.SerializedName}\") not supported by RestProxy */");
                                    break;
                            }

                            parameterDeclarationBuilder.Append(parameter.Type.ClientTypeName + " " + parameter.VariableName);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);

                        if (settings.IsAzureOrFluent)
                        {
                            foreach (RestAPIParameter parameter in restAPIMethod.Parameters.Where(p => (p.Location.EqualsIgnoreCase("Path") || p.Location.EqualsIgnoreCase("Query")) && p.SkipUrlEncodingExtension))
                            {
                                parameterDeclarations = parameterDeclarations.Replace(
                                    $"@{parameter.Location}Param(\"{parameter.SerializedName}\")",
                                    $"@{parameter.Location}Param(value = \"{parameter.SerializedName}\", encoded = true)");
                            }
                        }

                        interfaceBlock.PublicMethod($"{restAPIMethod.AsyncReturnType} {restAPIMethod.Name}({parameterDeclarations})");
                    }
                });
            }
        }

        private static void AddClientMethodOverloads(JavaType typeBlock, IEnumerable<Method> restAPIMethods, JavaSettings settings)
        {
            foreach (Method restAPIMethod in restAPIMethods)
            {
                IEnumerable<Parameter> clientMethodAndConstantParameters = restAPIMethod.Parameters
                    //Omit parameter-group properties for now since Java doesn't support them yet
                    .Where((Parameter parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                    .OrderBy(item => !item.IsRequired);
                IEnumerable<Parameter> clientMethodParameters = clientMethodAndConstantParameters
                    .Where((Parameter parameter) => !parameter.IsConstant)
                    .OrderBy(item => !item.IsRequired);
                IEnumerable<Parameter> requiredClientMethodParameters = clientMethodParameters.Where(parameter => parameter.IsRequired);
                bool hasOptionalClientMethodParameters = clientMethodParameters.Any(parameter => !parameter.IsRequired);

                bool simulateMethodAsPagingOperation = false;
                string methodName = restAPIMethod.Name;
                string wellKnownMethodName = null;
                MethodGroup methodGroup = restAPIMethod.MethodGroup;
                MethodType restAPIMethodType = MethodType.Other;
                if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                {
                    string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                    string[] methodUrlSplits = methodUrl.Split('/');
                    switch (restAPIMethod.HttpMethod)
                    {
                        case HttpMethod.Get:
                            if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                                && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                && MethodHasSequenceType(restAPIMethod.ReturnType.Body, settings))
                            {
                                if (methodUrlSplits.Length == 5)
                                {
                                    if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                    {
                                        restAPIMethodType = MethodType.ListBySubscription;
                                    }
                                    else
                                    {
                                        restAPIMethodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                {
                                    restAPIMethodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (IsTopLevelResourceUrl(methodUrlSplits))
                            {
                                restAPIMethodType = MethodType.Get;
                            }
                            break;

                        case HttpMethod.Delete:
                            if (IsTopLevelResourceUrl(methodUrlSplits))
                            {
                                restAPIMethodType = MethodType.Delete;
                            }
                            break;
                    }

                    if (restAPIMethodType != MethodType.Other)
                    {
                        int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
                        {
                            MethodType methodGroupMethodType = MethodType.Other;
                            string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                            string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                            switch (methodGroupMethod.HttpMethod)
                            {
                                case HttpMethod.Get:
                                    if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                        && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                        && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                    {
                                        if (methodGroupMethodUrlSplits.Length == 5)
                                        {
                                            if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                            {
                                                methodGroupMethodType = MethodType.ListBySubscription;
                                            }
                                            else
                                            {
                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                        {
                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                        }
                                    }
                                    else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                    {
                                        methodGroupMethodType = MethodType.Get;
                                    }
                                    break;

                                case HttpMethod.Delete:
                                    if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                    {
                                        methodGroupMethodType = MethodType.Delete;
                                    }
                                    break;
                            }
                            return methodGroupMethodType == restAPIMethodType;
                        });

                        if (methodsWithSameType == 1)
                        {
                            switch (restAPIMethodType)
                            {
                                case MethodType.ListBySubscription:
                                    wellKnownMethodName = List;
                                    simulateMethodAsPagingOperation = true;
                                    break;

                                case MethodType.ListByResourceGroup:
                                    wellKnownMethodName = ListByResourceGroup;
                                    simulateMethodAsPagingOperation = true;
                                    break;

                                case MethodType.Delete:
                                    wellKnownMethodName = Delete;
                                    break;

                                case MethodType.Get:
                                    wellKnownMethodName = GetByResourceGroup;
                                    break;

                                default:
                                    throw new Exception("Flow should not hit this statement.");
                            }
                        }
                    }
                }
                if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
                {
                    IParent methodParent = restAPIMethod.Parent;
                    methodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, restAPIMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(restAPIMethod.SingleItemAsEnumerable()));
                }
                methodName = methodName.ToCamelCase();

                Response restAPIMethodReturnType = restAPIMethod.ReturnType;

                IModelType restAPIMethodReturnBodyClientType = ConvertToClientType(restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None));
                SequenceType restAPIMethodReturnBodyClientSequenceType = restAPIMethodReturnBodyClientType as SequenceType;

                bool restAPIMethodReturnTypeIsPaged = GetExtensionBool(restAPIMethod.Extensions, "nextLinkMethod") ||
                    (restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                     restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null);

                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = restAPIMethodReturnBodyClientSequenceType.ElementType;
                    SequenceTypeSetPageImplType(resultSequenceType, SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType));
                    pagedListTypes.Add(resultSequenceType);
                    restAPIMethodReturnBodyClientType = resultSequenceType;
                }

                string responseGenericBodyClientTypeString;
                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && restAPIMethodReturnTypeIsPaged)
                {
                    responseGenericBodyClientTypeString = $"PagedList<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                }
                else
                {
                    IModelType responseBodyWireType = restAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryType>(KnownPrimaryType.None);
                    IModelType responseVariant = ConvertToClientType(responseBodyWireType);

                    bool responseVariantPrimaryTypeIsNullable = true;
                    if (responseVariant is PrimaryType responseVariantPrimaryType && !PrimaryTypeGetWantNullable(responseVariantPrimaryType))
                    {
                        switch (responseVariantPrimaryType.KnownPrimaryType)
                        {
                            case KnownPrimaryType.None:
                            case KnownPrimaryType.Boolean:
                            case KnownPrimaryType.Double:
                            case KnownPrimaryType.Int:
                            case KnownPrimaryType.Long:
                            case KnownPrimaryType.UnixTime:
                                responseVariantPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    IModelType responseGenericBodyClientType = responseVariantPrimaryTypeIsNullable ? responseVariant : responseBodyWireType;
                    responseGenericBodyClientTypeString = IModelTypeName(responseGenericBodyClientType, settings);
                }

                SequenceType restAPIMethodReturnBodySequenceType = restAPIMethodReturnType.Body as SequenceType;
                string responseSequenceElementTypeString = restAPIMethodReturnBodySequenceType == null ? "Void" : IModelTypeName(restAPIMethodReturnBodySequenceType.ElementType, settings);

                IModelType restAPIMethodResponseHeaders = restAPIMethodReturnType.Headers;
                string deserializedResponseHeadersType = restAPIMethodResponseHeaders == null ? "Void" : IModelTypeName(ConvertToClientType(restAPIMethodResponseHeaders), settings);
                string deserializedResponseBodyType;

                if (restAPIMethodReturnType.Body == null)
                {
                    deserializedResponseBodyType = "Void";
                }
                else if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                {
                    deserializedResponseBodyType = $"{SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientSequenceType)}<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                }
                else
                {
                    deserializedResponseBodyType = responseGenericBodyClientTypeString;
                }
                string restResponseType = $"RestResponse<{deserializedResponseHeadersType}, {deserializedResponseBodyType}>";

                IEnumerable<Parameter> requiredNullableParameters = restAPIMethod.Parameters.Where((Parameter parameter) =>
                {
                    bool result = !parameter.IsConstant && parameter.IsRequired;
                    if (result)
                    {
                        IModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                        }
                        result = !parameterModelType.IsPrimaryType(KnownPrimaryType.Int) &&
                                 !parameterModelType.IsPrimaryType(KnownPrimaryType.Double) &&
                                 !parameterModelType.IsPrimaryType(KnownPrimaryType.Boolean) &&
                                 !parameterModelType.IsPrimaryType(KnownPrimaryType.Long) &&
                                 !parameterModelType.IsPrimaryType(KnownPrimaryType.UnixTime);
                    }
                    return result;
                });

                bool methodIsPagingNextOperation = GetExtensionBool(restAPIMethod?.Extensions, "nextLinkMethod");

                List<Parameter> methodRetrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();
                if (settings.IsAzureOrFluent && methodIsPagingNextOperation)
                {
                    methodRetrofitParameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                    Parameter nextUrlParam = DependencyInjection.New<Parameter>();
                    nextUrlParam.SerializedName = "nextUrl";
                    nextUrlParam.Documentation = "The URL to get the next page of items.";
                    nextUrlParam.Location = ParameterLocation.Path;
                    nextUrlParam.IsRequired = true;
                    nextUrlParam.ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                    nextUrlParam.Name = "nextUrl";
                    nextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                    methodRetrofitParameters.Insert(0, nextUrlParam);
                }

                IEnumerable<Parameter> methodOrderedRetrofitParameters = methodRetrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                                    .Union(methodRetrofitParameters.Where(p => p.Location != ParameterLocation.Path));

                string methodClientReference = restAPIMethod.Group.IsNullOrEmpty() ? "this" : "this.client";

                List<IEnumerable<Parameter>> parameterLists = new List<IEnumerable<Parameter>>()
                {
                    clientMethodParameters
                };
                if (clientMethodParameters.Any(parameter => !parameter.IsRequired))
                {
                    parameterLists.Insert(0, requiredClientMethodParameters);
                }

                string serviceFutureReturnType = $"ServiceFuture<{responseGenericBodyClientTypeString}>";
                IModelType serviceResponseVariant = GetIModelTypeNonNullableVariant(ConvertToClientType(restAPIMethodReturnBodyClientType)) ?? restAPIMethodReturnBodyClientType;
                string synchronousMethodReturnType = IModelTypeName(serviceResponseVariant, settings);

                string pageType;
                if (settings.IsAzureOrFluent && restAPIMethodReturnBodyClientSequenceType != null && (restAPIMethodReturnTypeIsPaged || simulateMethodAsPagingOperation))
                {
                    pageType = $"Page<{IModelTypeName(restAPIMethodReturnBodyClientSequenceType.ElementType, settings)}>";
                }
                else
                {
                    pageType = responseGenericBodyClientTypeString;
                }

                string methodOperationExceptionTypeName;
                IModelType restAPIMethodExceptionType = restAPIMethod.DefaultResponse.Body;
                if (settings.IsAzureOrFluent && (restAPIMethodReturnType == null || IModelTypeName(restAPIMethodExceptionType, settings) == "CloudError"))
                {
                    methodOperationExceptionTypeName = "CloudException";
                }
                else if (restAPIMethodExceptionType is CompositeType compositeExceptionType)
                {
                    methodOperationExceptionTypeName = GetCompositeTypeName(compositeExceptionType, settings) + "Exception";
                    if (compositeExceptionType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                    {
                        JContainer ext = compositeExceptionType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                        if (ext != null && ext["name"] != null)
                        {
                            methodOperationExceptionTypeName = ext["name"].ToString();
                        }
                    }
                }
                else
                {
                    methodOperationExceptionTypeName = "RestException";
                }

                bool addedClientMethods = false;

                if (settings.IsAzureOrFluent)
                {
                    bool methodIsLongRunningOperation = GetExtensionBool(restAPIMethod?.Extensions, AzureExtensions.LongRunningExtension);

                    bool methodIsPagingOperation = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                        restAPIMethod.Extensions[AzureExtensions.PageableExtension] != null &&
                        !methodIsPagingNextOperation;

                    if (methodIsPagingOperation || methodIsPagingNextOperation)
                    {
                        foreach (IEnumerable<Parameter> parameters in parameterLists)
                        {
                            bool onlyRequiredParameters = (parameters == requiredClientMethodParameters);

                            string parameterDeclaration = string.Join(", ", parameters.Select(parameter =>
                            {
                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);
                                string parameterType = IModelTypeName(parameterClientType, settings);
                                return $"final {parameterType} {parameter.Name}";
                            }));

                            Method nextMethod;
                            string nextMethodInvocation;
                            if (methodIsPagingNextOperation)
                            {
                                nextMethod = restAPIMethod;

                                nextMethodInvocation = restAPIMethod.Name;
                                string nextMethodWellKnownMethodName = null;
                                if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                                {
                                    if (restAPIMethodType != MethodType.Other)
                                    {
                                        int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
                                        {
                                            MethodType methodGroupMethodType = MethodType.Other;
                                            string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                            string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                            switch (methodGroupMethod.HttpMethod)
                                            {
                                                case HttpMethod.Get:
                                                    if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                        && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                        && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                                    {
                                                        if (methodGroupMethodUrlSplits.Length == 5)
                                                        {
                                                            if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                            {
                                                                methodGroupMethodType = MethodType.ListBySubscription;
                                                            }
                                                            else
                                                            {
                                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                                            }
                                                        }
                                                        else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                        {
                                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                                        }
                                                    }
                                                    else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                    {
                                                        methodGroupMethodType = MethodType.Get;
                                                    }
                                                    break;

                                                case HttpMethod.Delete:
                                                    if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                    {
                                                        methodGroupMethodType = MethodType.Delete;
                                                    }
                                                    break;
                                            }
                                            return methodGroupMethodType == restAPIMethodType;
                                        });

                                        if (methodsWithSameType == 1)
                                        {
                                            switch (restAPIMethodType)
                                            {
                                                case MethodType.ListBySubscription:
                                                    nextMethodWellKnownMethodName = List;
                                                    break;

                                                case MethodType.ListByResourceGroup:
                                                    nextMethodWellKnownMethodName = ListByResourceGroup;
                                                    break;

                                                case MethodType.Delete:
                                                    nextMethodWellKnownMethodName = Delete;
                                                    break;

                                                case MethodType.Get:
                                                    nextMethodWellKnownMethodName = GetByResourceGroup;
                                                    break;

                                                default:
                                                    throw new Exception("Flow should not hit this statement.");
                                            }
                                        }
                                    }
                                }
                                if (!string.IsNullOrWhiteSpace(nextMethodWellKnownMethodName))
                                {
                                    IParent methodParent = restAPIMethod.Parent;
                                    nextMethodInvocation = CodeNamer.Instance.GetUnique(nextMethodWellKnownMethodName, restAPIMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(restAPIMethod.SingleItemAsEnumerable()));
                                }
                                nextMethodInvocation = nextMethodInvocation.ToCamelCase();
                            }
                            else
                            {
                                string nextMethodName = restAPIMethod.Extensions.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
                                string nextMethodGroup = restAPIMethod.Extensions.GetValue<Fixable<string>>("nextMethodGroup");

                                nextMethod = restAPIMethod.CodeModel.Methods
                                    .FirstOrDefault((Method codeModelMethod) =>
                                        {
                                        bool result = nextMethodGroup.EqualsIgnoreCase(codeModelMethod.Group);
                                        if (result)
                                        {
                                            string codeModelMethodName = codeModelMethod.Name;
                                            string codeModelMethodWellKnownMethodName = null;
                                            MethodGroup codeModelMethodMethodGroup = codeModelMethod.MethodGroup;
                                            if (!string.IsNullOrEmpty(codeModelMethodMethodGroup?.Name?.ToString()))
                                            {
                                                    MethodType codeModelMethodType = MethodType.Other;
                                                    string codeModelMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(codeModelMethod.Url, ""), "");
                                                    string[] codeModelMethodUrlSplits = codeModelMethodUrl.Split('/');
                                                    switch (codeModelMethod.HttpMethod)
                                                    {
                                                        case HttpMethod.Get:
                                                            if ((codeModelMethodUrlSplits.Length == 5 || codeModelMethodUrlSplits.Length == 7)
                                                                && codeModelMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                                && MethodHasSequenceType(codeModelMethod.ReturnType.Body, settings))
                                                            {
                                                                if (codeModelMethodUrlSplits.Length == 5)
                                                                {
                                                                    if (codeModelMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                                    {
                                                                        codeModelMethodType = MethodType.ListBySubscription;
                                                                    }
                                                                    else
                                                                    {
                                                                        codeModelMethodType = MethodType.ListByResourceGroup;
                                                                    }
                                                                }
                                                                else if (codeModelMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                                {
                                                                    codeModelMethodType = MethodType.ListByResourceGroup;
                                                                }
                                                            }
                                                            else if (IsTopLevelResourceUrl(codeModelMethodUrlSplits))
                                                            {
                                                                codeModelMethodType = MethodType.Get;
                                                            }
                                                            break;

                                                        case HttpMethod.Delete:
                                                            if (IsTopLevelResourceUrl(codeModelMethodUrlSplits))
                                                            {
                                                                codeModelMethodType = MethodType.Delete;
                                                            }
                                                            break;
                                                    }

                                                    if (codeModelMethodType != MethodType.Other)
                                                    {
                                                        int methodsWithSameType = codeModelMethodMethodGroup.Methods.Count((Method methodGroupMethod) =>
                                                        {
                                                            MethodType methodGroupMethodType = MethodType.Other;
                                                            string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                                            string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                                            switch (methodGroupMethod.HttpMethod)
                                                            {
                                                                case HttpMethod.Get:
                                                                    if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                                        && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                                        && MethodHasSequenceType(methodGroupMethod.ReturnType.Body, settings))
                                                                    {
                                                                        if (methodGroupMethodUrlSplits.Length == 5)
                                                                        {
                                                                            if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                                            {
                                                                                methodGroupMethodType = MethodType.ListBySubscription;
                                                                            }
                                                                            else
                                                                            {
                                                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                                                            }
                                                                        }
                                                                        else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                                        {
                                                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                                                        }
                                                                    }
                                                                    else if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                                    {
                                                                        methodGroupMethodType = MethodType.Get;
                                                                    }
                                                                    break;

                                                                case HttpMethod.Delete:
                                                                    if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                                    {
                                                                        methodGroupMethodType = MethodType.Delete;
                                                                    }
                                                                    break;
                                                            }
                                                            return methodGroupMethodType == restAPIMethodType;
                                                        });

                                                        if (methodsWithSameType == 1)
                                                        {
                                                            switch (codeModelMethodType)
                                                            {
                                                                case MethodType.ListBySubscription:
                                                                    codeModelMethodWellKnownMethodName = List;
                                                                    break;

                                                                case MethodType.ListByResourceGroup:
                                                                    codeModelMethodWellKnownMethodName = ListByResourceGroup;
                                                                    break;

                                                                case MethodType.Delete:
                                                                    codeModelMethodWellKnownMethodName = Delete;
                                                                    break;

                                                                case MethodType.Get:
                                                                    codeModelMethodWellKnownMethodName = GetByResourceGroup;
                                                                    break;

                                                                default:
                                                                    throw new Exception("Flow should not hit this statement.");
                                                            }
                                                        }
                                                    }
                                                }
                                                if (!string.IsNullOrWhiteSpace(codeModelMethodWellKnownMethodName))
                                                {
                                                    IParent methodParent = codeModelMethod.Parent;
                                                    codeModelMethodName = CodeNamer.Instance.GetUnique(codeModelMethodWellKnownMethodName, codeModelMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(codeModelMethod.SingleItemAsEnumerable()));
                                                }

                                                result = nextMethodName.EqualsIgnoreCase(codeModelMethodName);
                                            }
                                            return result;
                                        });

                                if (nextMethodGroup == null || restAPIMethod.Group == nextMethod.Group)
                                {
                                    nextMethodInvocation = nextMethodName;
                                }
                                else
                                {
                                    nextMethodInvocation = $"{(restAPIMethod.Group.IsNullOrEmpty() ? "this" : "client")}.get{nextMethodGroup.ToPascalCase()}().{nextMethodName}";
                                }
                            }

                            string nextPageLinkParameterName = nextMethod.Parameters
                                .Select((Parameter parameter) =>
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
                                    return parameterName;
                                })
                                .First((string parameterName) => parameterName.StartsWith("next", StringComparison.OrdinalIgnoreCase));

                            IEnumerable<Parameter> nextMethodRestAPIParameters = nextMethod.Parameters
                                .Where((Parameter parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                                .OrderBy(item => !item.IsRequired);

                            string nextMethodParameterInvocation;
                            Parameter groupedType = null;
                            Parameter nextGroupType = null;
                            if (!onlyRequiredParameters)
                            {
                                nextMethodParameterInvocation = string.Join(", ", nextMethodRestAPIParameters.Where(p => !p.IsConstant).Select((Parameter parameter) => parameter.Name));
                            }
                            else if (restAPIMethod.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                            {
                                nextMethodParameterInvocation = string.Join(", ", nextMethodRestAPIParameters.Select((Parameter parameter) => parameter.IsRequired ? parameter.Name.ToString() : "null"));
                            }
                            else
                            {
                                groupedType = restAPIMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                                nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                                List<string> invocations = new List<string>();
                                foreach (Parameter parameter in nextMethodRestAPIParameters)
                                {
                                    string parameterName = parameter.Name;

                                    if (parameter.IsRequired)
                                    {
                                        invocations.Add(parameterName);
                                    }
                                    else if (parameterName == nextGroupType.Name && groupedType.IsRequired)
                                    {
                                        invocations.Add(parameterName);
                                    }
                                    else
                                    {
                                        invocations.Add("null");
                                    }
                                }
                                nextMethodParameterInvocation = string.Join(", ", invocations);
                            }

                            string argumentList = string.Join(", ", parameters.Select((Parameter parameter) => parameter.Name));

                            string nextGroupTypeName = null;
                            string groupedTypeName = null;
                            if (methodIsPagingOperation && !restAPIMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                            {
                                groupedType = groupedType ?? restAPIMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                                nextGroupType = nextGroupType ?? nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;

                                if (!nextGroupType.IsClientProperty)
                                {
                                    nextGroupTypeName = nextGroupType.Name;
                                }
                                else
                                {
                                    string caller = (nextGroupType.Method != null && nextGroupType.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                    string clientPropertyName = nextGroupType.ClientProperty?.Name?.ToString();
                                    if (!string.IsNullOrEmpty(clientPropertyName))
                                    {
                                        CodeNamer codeNamer = CodeNamer.Instance;
                                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                    }
                                    nextGroupTypeName = $"{caller}.{clientPropertyName}()";
                                }

                                if (!groupedType.IsClientProperty)
                                {
                                    groupedTypeName = groupedType.Name;
                                }
                                else
                                {
                                    string caller = (groupedType.Method != null && groupedType.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                    string clientPropertyName = groupedType.ClientProperty?.Name?.ToString();
                                    if (!string.IsNullOrEmpty(clientPropertyName))
                                    {
                                        CodeNamer codeNamer = CodeNamer.Instance;
                                        clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                    }
                                    groupedTypeName = $"{caller}.{clientPropertyName}()";
                                }
                            }

                            // --------------------
                            // Synchronous PageList
                            // --------------------
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                comment.Throws(methodOperationExceptionTypeName, "thrown if the request is rejected by server");
                                comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                                if (restAPIMethod.ReturnType.Body != null)
                                {
                                    comment.Return($"the {synchronousMethodReturnType} object if successful.");
                                }
                            });
                            typeBlock.PublicMethod($"{synchronousMethodReturnType} {methodName}({parameterDeclaration})", function =>
                            {
                                function.Line($"{pageType} response = {methodName}SinglePageAsync({argumentList}).blockingGet();");
                                function.ReturnAnonymousClass($"new {responseGenericBodyClientTypeString}(response)", anonymousClass =>
                                {
                                    anonymousClass.Annotation("Override");
                                    anonymousClass.PublicMethod($"{pageType} nextPage(String {nextPageLinkParameterName})", subFunction =>
                                    {
                                        if (methodIsPagingOperation && !restAPIMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                                        {
                                            if (nextGroupTypeName != groupedTypeName && (!onlyRequiredParameters || groupedType.IsRequired))
                                            {
                                                string nextGroupTypeCamelCaseName = nextGroupTypeName.ToCamelCase();
                                                string groupedTypeCamelCaseName = groupedTypeName.ToCamelCase();

                                                string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(nextGroupTypeName) + (settings.IsFluent ? "Inner" : "");

                                                if (!groupedType.IsRequired)
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

                                                foreach (Parameter outputParameter in nextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
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

                                                if (!groupedType.IsRequired)
                                                {
                                                    subFunction.DecreaseIndent();
                                                    subFunction.Line("}");
                                                }
                                            }
                                        }

                                        subFunction.Return($"{nextMethodInvocation}SinglePageAsync({nextMethodParameterInvocation}).blockingGet()");
                                    });
                                });
                            });

                            // ------------------------------
                            // Async Observable<PagedList<T>>
                            // ------------------------------
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                if (restAPIMethodReturnType.Body != null)
                                {
                                    comment.Return($"the observable to the {synchronousMethodReturnType} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Observable<{pageType}>}} object if successful.");
                                }
                            });
                            typeBlock.PublicMethod($"Observable<{pageType}> {methodName}Async({parameterDeclaration})", function =>
                            {
                                function.Line($"return {methodName}SinglePageAsync({argumentList})");
                                function.Indent(() =>
                                {
                                    function.Line(".toObservable()");
                                    function.Line($".concatMap(new Function<{pageType}, Observable<{pageType}>>() {{");
                                    function.Indent(() =>
                                    {
                                        function.Annotation("Override");
                                        function.Block($"public Observable<{pageType}> apply({pageType} page)", subFunction =>
                                        {
                                            subFunction.Line($"String {nextPageLinkParameterName} = page.nextPageLink();");
                                            subFunction.If($"{nextPageLinkParameterName} == null", ifBlock =>
                                            {
                                                ifBlock.Return("Observable.just(page)");
                                            });

                                            if (methodIsPagingOperation && !restAPIMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                                            {
                                                if (nextGroupTypeName != groupedTypeName && (!onlyRequiredParameters || groupedType.IsRequired))
                                                {
                                                    string nextGroupTypeCamelCaseName = nextGroupTypeName.ToCamelCase();
                                                    string groupedTypeCamelCaseName = groupedTypeName.ToCamelCase();

                                                    string nextGroupTypeCodeName = CodeNamer.Instance.GetTypeName(nextGroupTypeName) + (settings.IsFluent ? "Inner" : "");

                                                    if (!groupedType.IsRequired)
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

                                                    foreach (Parameter outputParameter in nextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
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

                                                    if (!groupedType.IsRequired)
                                                    {
                                                        subFunction.DecreaseIndent();
                                                        subFunction.Line("}");
                                                    }
                                                }
                                            }

                                            subFunction.Return($"Observable.just(page).concatWith({nextMethodInvocation}Async({nextMethodParameterInvocation}))");
                                        });
                                    });
                                    function.Line("});");
                                });
                            });

                            // ------------------------------
                            // Async Single<Page<T>> Overload
                            // ------------------------------
                            string singlePageMethodReturnType = $"Single<{pageType}>";
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                comment.Return($"the {{@link {singlePageMethodReturnType}}} object if successful.");
                            });
                            typeBlock.PublicMethod($"{singlePageMethodReturnType} {methodName}SinglePageAsync({parameterDeclaration})", function =>
                            {
                                foreach (Parameter parameter in requiredNullableParameters)
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

                                    function.If($"{parameterName} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {parameterName} is required and cannot be null.\");");
                                    });
                                }

                                IEnumerable<Parameter> parametersToValidate = restAPIMethod.Parameters.Where(parameter =>
                                {
                                    bool result = !parameter.IsConstant;
                                    if (result)
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType) && (!onlyRequiredParameters || parameter.IsRequired);
                                    }

                                    return result;
                                });

                                foreach (Parameter parameter in parametersToValidate)
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

                                    function.Line($"Validator.validate({parameterName});");
                                }

                                foreach (Parameter parameter in clientMethodAndConstantParameters)
                                {
                                    string parameterName = parameter.Name;

                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    if (onlyRequiredParameters && !parameter.IsRequired)
                                    {
                                        string parameterDefaultValue;
                                        if (parameterClientType is PrimaryType parameterClientPrimaryType)
                                        {
                                            string modelTypeName = IModelTypeName(parameterClientPrimaryType, settings);
                                            if (modelTypeName == "byte[]")
                                            {
                                                parameterDefaultValue = "new byte[0]";
                                            }
                                            else if (modelTypeName == "Byte[]")
                                            {
                                                parameterDefaultValue = "new Byte[0]";
                                            }
                                            else
                                            {
                                                parameterDefaultValue = null;
                                            }
                                        }
                                        else
                                        {
                                            parameterDefaultValue = parameterClientType.DefaultValue;
                                        }
                                        function.Line($"final {IModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                    }
                                    else if (parameter.IsConstant)
                                    {
                                        string defaultValue = parameter.DefaultValue;
                                        if (defaultValue != null && parameterModelType is PrimaryType parameterModelPrimaryType)
                                        {
                                            switch (parameterModelPrimaryType.KnownPrimaryType)
                                            {
                                                case KnownPrimaryType.Double:
                                                    defaultValue = double.Parse(defaultValue).ToString();
                                                    break;

                                                case KnownPrimaryType.String:
                                                    defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                    break;

                                                case KnownPrimaryType.Boolean:
                                                    defaultValue = defaultValue.ToLowerInvariant();
                                                    break;

                                                case KnownPrimaryType.Long:
                                                    defaultValue = defaultValue + 'L';
                                                    break;

                                                case KnownPrimaryType.Date:
                                                    defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.DateTime:
                                                case KnownPrimaryType.DateTimeRfc1123:
                                                    defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.TimeSpan:
                                                    defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.ByteArray:
                                                    defaultValue = $"\"{defaultValue}\".getBytes()";
                                                    break;
                                            }
                                        }
                                        function.Line($"final {IModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                    }
                                }

                                foreach (ParameterTransformation transformation in restAPIMethod.InputParameterTransformation)
                                {
                                    Parameter transformationOutputParameter = transformation.OutputParameter;
                                    IModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                    if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter))
                                    {
                                        transformationOutputParameterModelType = GetIModelTypeNonNullableVariant(transformationOutputParameterModelType);
                                    }
                                    IModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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
                                    while (restAPIMethod.Parameters.Any((Parameter parameter) =>
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

                                    string transformationOutputParameterClientParameterVariantTypeName = IModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

                                    IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                                    string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                                        .Select((ParameterMapping m) =>
                                        {
                                            Parameter parameter = m.InputParameter;

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
                                    bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !onlyRequiredParameters;
                                    if (conditionalAssignment)
                                    {
                                        function.Line("{0} {1} = null;",
                                            transformationOutputParameterClientParameterVariantTypeName,
                                            outParamName);
                                        function.Line($"if ({nullCheck}) {{");
                                        function.IncreaseIndent();
                                    }

                                    bool transformationOutputParameterModelIsCompositeType = transformationOutputParameterModelType is CompositeType;
                                    if (transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) && transformationOutputParameterModelIsCompositeType)
                                    {
                                        function.Line("{0}{1} = new {2}();",
                                            !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            GetCompositeTypeName(transformationOutputParameterModelType as CompositeType, settings));
                                    }

                                    foreach (ParameterMapping mapping in transformationParameterMappings)
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
                                        if (onlyRequiredParameters && !mapping.InputParameter.IsRequired)
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
                                            !conditionalAssignment && !transformationOutputParameterModelIsCompositeType ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            getMapping);
                                    }

                                    if (conditionalAssignment)
                                    {
                                        function.DecreaseIndent();
                                        function.Line("}");
                                    }
                                }

                                foreach (Parameter parameter in methodRetrofitParameters)
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    IModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                        parameter.Location != ParameterLocation.Body &&
                                        parameter.Location != ParameterLocation.FormData &&
                                        ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    if (!parameterClientType.StructurallyEquals(parameterWireType))
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
                                        string parameterWireName = $"{parameterName.ToCamelCase()}Converted";

                                        bool addedConversion = false;
                                        ParameterLocation parameterLocation = parameter.Location;
                                        if (parameterLocation != ParameterLocation.Body &&
                                            parameterLocation != ParameterLocation.FormData &&
                                            ((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                                        {
                                            string parameterWireTypeName = IModelTypeName(parameterWireType, settings);

                                            if (parameterClientType is PrimaryType primaryClientType && primaryClientType.KnownPrimaryType == KnownPrimaryType.ByteArray)
                                            {
                                                if (parameterWireType.IsPrimaryType(KnownPrimaryType.String))
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                                }
                                                else
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                                }
                                                addedConversion = true;
                                            }
                                            else if (parameterClientType is SequenceType)
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
                                            ParameterConvertClientTypeToWireType(function, settings, parameter, parameterWireType, parameterName, parameterWireName, methodClientReference);
                                        }
                                    }
                                }

                                if (methodIsPagingNextOperation)
                                {
                                    string methodUrl = restAPIMethod.Url;
                                    Regex regex = new Regex("{\\w+}");

                                    string substitutedMethodUrl = regex.Replace(methodUrl, "%s").TrimStart('/');

                                    IEnumerable<Parameter> retrofitParameters = restAPIMethod.LogicalParameters.Where(p => p.Location != ParameterLocation.None);
                                    StringBuilder builder = new StringBuilder($"String.format(\"{substitutedMethodUrl}\"");
                                    foreach (Match match in regex.Matches(methodUrl))
                                    {
                                        string serializedNameWithBrackets = match.Value;
                                        string serializedName = serializedNameWithBrackets.Substring(1, serializedNameWithBrackets.Length - 2);
                                        Parameter parameter = retrofitParameters.First(p => p.SerializedName == serializedName);

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

                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }

                                        IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                        IModelType parameterWireType;
                                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                        {
                                            parameterWireType = parameterClientType;
                                        }
                                        else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                            parameter.Location != ParameterLocation.Body &&
                                            parameter.Location != ParameterLocation.FormData &&
                                            ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                        {
                                            parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
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

                                IEnumerable<Parameter> orderedRetrofitParameters = methodRetrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                                    .Union(methodRetrofitParameters.Where(p => p.Location != ParameterLocation.Path));
                                string restAPIMethodArgumentList = string.Join(", ", orderedRetrofitParameters
                                    .Select((Parameter parameter) =>
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
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                        IModelType parameterWireType;
                                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                        {
                                            parameterWireType = parameterClientType;
                                        }
                                        else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                            parameter.Location != ParameterLocation.Body &&
                                            parameter.Location != ParameterLocation.FormData &&
                                            ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                        {
                                            parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                        }
                                        else
                                        {
                                            parameterWireType = parameterModelType;
                                        }

                                        string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                        string result;
                                        if (settings.ShouldGenerateXmlSerialization && parameterWireType is SequenceType)
                                        {
                                            result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                        }
                                        else
                                        {
                                            result = parameterWireName;
                                        }
                                        return result;
                                    }));

                                function.Line($"return service.{methodName}({restAPIMethodArgumentList}).map(new Function<{restResponseType}, {pageType}>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public {pageType} apply({restResponseType} response)", subFunction =>
                                    {
                                        subFunction.Return("response.body()");
                                    });
                                });
                                function.Line("});");
                            });
                        }

                        addedClientMethods = true;
                    }
                    else if (simulateMethodAsPagingOperation)
                    {
                        string sequenceElementTypeString = restAPIMethodReturnType.Body is SequenceType bodySequenceType ? IModelTypeName(bodySequenceType.ElementType, settings) : "Void";

                        foreach (IEnumerable<Parameter> parameters in parameterLists)
                        {
                            bool onlyRequiredParameters = (parameters == requiredClientMethodParameters);
                            bool filterRequired = onlyRequiredParameters;

                            string parameterDeclaration = string.Join(", ", parameters.Select(parameter =>
                            {
                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);
                                string parameterType = IModelTypeName(parameterClientType, settings);
                                return $"{parameterType} {parameter.Name}";
                            }));

                            // ------------------------
                            // Synchronous PagedList<T>
                            // ------------------------
                            string pageImplType = SequenceTypeGetPageImplType(restAPIMethodReturnBodyClientType);
                            string synchronousReturnType = $"PagedList<{sequenceElementTypeString}>";
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                if (restAPIMethod.ReturnType.Body != null)
                                {
                                    comment.Return($"the {synchronousReturnType} object if successful.");
                                }
                            });
                            typeBlock.PublicMethod($"{synchronousReturnType} {methodName}({parameterDeclaration})", function =>
                            {
                                function.Line($"{pageImplType}<{sequenceElementTypeString}> page = new {pageImplType}<>();");

                                string argumentList = string.Join(", ", parameters.Select((Parameter parameter) => parameter.Name));
                                function.Line($"page.setItems({methodName}Async({argumentList}).single().items());");
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
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                if (restAPIMethod.ReturnType.Body != null)
                                {
                                    comment.Return($"the observable to the {synchronousMethodReturnType} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Observable<{pageType}>}} object if successful.");
                                }
                            });
                            typeBlock.PublicMethod($"Observable<Page<{sequenceElementTypeString}>> {methodName}Async({parameterDeclaration})", function =>
                            {
                                foreach (Parameter parameter in requiredNullableParameters)
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

                                    function.If($"{parameterName} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {parameterName} is required and cannot be null.\");");
                                    });
                                }

                                IEnumerable<Parameter> parametersToValidate = restAPIMethod.Parameters.Where(parameter =>
                                {
                                    bool result = !parameter.IsConstant;
                                    if (result)
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType) && (!onlyRequiredParameters || parameter.IsRequired);
                                    }

                                    return result;
                                });
                                foreach (Parameter parameter in parametersToValidate)
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

                                    function.Line($"Validator.validate({parameterName});");
                                }

                                foreach (Parameter parameter in clientMethodAndConstantParameters)
                                {
                                    string parameterName = parameter.Name;

                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    if (onlyRequiredParameters && !parameter.IsRequired)
                                    {
                                        string parameterDefaultValue;
                                        if (parameterClientType is PrimaryType parameterClientPrimaryType)
                                        {
                                            string modelTypeName = IModelTypeName(parameterClientPrimaryType, settings);
                                            if (modelTypeName == "byte[]")
                                            {
                                                parameterDefaultValue = "new byte[0]";
                                            }
                                            else if (modelTypeName == "Byte[]")
                                            {
                                                parameterDefaultValue = "new Byte[0]";
                                            }
                                            else
                                            {
                                                parameterDefaultValue = null;
                                            }
                                        }
                                        else
                                        {
                                            parameterDefaultValue = parameterClientType.DefaultValue;
                                        }
                                        function.Line($"final {IModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                    }
                                    else if (parameter.IsConstant)
                                    {
                                        string defaultValue = parameter.DefaultValue;
                                        if (defaultValue != null && parameterModelType is PrimaryType parameterModelPrimaryType)
                                        {
                                            switch (parameterModelPrimaryType.KnownPrimaryType)
                                            {
                                                case KnownPrimaryType.Double:
                                                    defaultValue = double.Parse(defaultValue).ToString();
                                                    break;

                                                case KnownPrimaryType.String:
                                                    defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                    break;

                                                case KnownPrimaryType.Boolean:
                                                    defaultValue = defaultValue.ToLowerInvariant();
                                                    break;

                                                case KnownPrimaryType.Long:
                                                    defaultValue = defaultValue + 'L';
                                                    break;

                                                case KnownPrimaryType.Date:
                                                    defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.DateTime:
                                                case KnownPrimaryType.DateTimeRfc1123:
                                                    defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.TimeSpan:
                                                    defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.ByteArray:
                                                    defaultValue = $"\"{defaultValue}\".getBytes()";
                                                    break;
                                            }
                                        }
                                        function.Line($"final {IModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                    }
                                }

                                foreach (ParameterTransformation transformation in restAPIMethod.InputParameterTransformation)
                                {
                                    Parameter transformationOutputParameter = transformation.OutputParameter;
                                    IModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                    if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter))
                                    {
                                        transformationOutputParameterModelType = GetIModelTypeNonNullableVariant(transformationOutputParameterModelType);
                                    }
                                    IModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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
                                    while (restAPIMethod.Parameters.Any((Parameter parameter) =>
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

                                    string transformationOutputParameterClientParameterVariantTypeName = IModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

                                    IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                                    string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                                        .Select((ParameterMapping m) =>
                                        {
                                            Parameter parameter = m.InputParameter;

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
                                    bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !filterRequired;
                                    if (conditionalAssignment)
                                    {
                                        function.Line("{0} {1} = null;",
                                            transformationOutputParameterClientParameterVariantTypeName,
                                            outParamName);
                                        function.Line($"if ({nullCheck}) {{");
                                        function.IncreaseIndent();
                                    }

                                    bool transformationOutputParameterModelIsCompositeType = transformationOutputParameterModelType is CompositeType;
                                    if (transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) && transformationOutputParameterModelIsCompositeType)
                                    {
                                        function.Line("{0}{1} = new {2}();",
                                            !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            GetCompositeTypeName(transformationOutputParameterModelType as CompositeType, settings));
                                    }

                                    foreach (ParameterMapping mapping in transformationParameterMappings)
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

                                        function.Line("{0}{1}{2};",
                                            !conditionalAssignment && !transformationOutputParameterModelIsCompositeType ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            getMapping);
                                    }

                                    if (conditionalAssignment)
                                    {
                                        function.DecreaseIndent();
                                        function.Line("}");
                                    }
                                }

                                foreach (Parameter parameter in methodRetrofitParameters)
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    IModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                        parameter.Location != ParameterLocation.Body &&
                                        parameter.Location != ParameterLocation.FormData &&
                                        ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    if (!parameterClientType.StructurallyEquals(parameterWireType))
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
                                        string parameterWireName = $"{parameterName.ToCamelCase()}Converted";

                                        bool addedConversion = false;
                                        ParameterLocation parameterLocation = parameter.Location;
                                        if (parameterLocation != ParameterLocation.Body &&
                                            parameterLocation != ParameterLocation.FormData &&
                                            ((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                                        {
                                            string parameterWireTypeName = IModelTypeName(parameterWireType, settings);

                                            if (parameterClientType is PrimaryType primaryClientType && primaryClientType.KnownPrimaryType == KnownPrimaryType.ByteArray)
                                            {
                                                if (parameterWireType.IsPrimaryType(KnownPrimaryType.String))
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                                }
                                                else
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                                }
                                                addedConversion = true;
                                            }
                                            else if (parameterClientType is SequenceType)
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
                                            ParameterConvertClientTypeToWireType(function, settings, parameter, parameterWireType, parameterName, parameterWireName, methodClientReference);
                                        }
                                    }
                                }

                                string restAPIMethodArgumentList = string.Join(", ", methodOrderedRetrofitParameters
                                    .Select((Parameter parameter) =>
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
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                        IModelType parameterWireType;
                                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                        {
                                            parameterWireType = parameterClientType;
                                        }
                                        else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                            parameter.Location != ParameterLocation.Body &&
                                            parameter.Location != ParameterLocation.FormData &&
                                            ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                        {
                                            parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                        }
                                        else
                                        {
                                            parameterWireType = parameterModelType;
                                        }

                                        string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                        string result;
                                        if (settings.ShouldGenerateXmlSerialization && parameterWireType is SequenceType)
                                        {
                                            result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                        }
                                        else
                                        {
                                            result = parameterWireName;
                                        }
                                        return result;
                                    }));

                                function.Line($"return service.{methodName}({restAPIMethodArgumentList}).map(new Function<{restResponseType}, {pageType}>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public {pageType} apply({restResponseType} response)", subFunction =>
                                    {
                                        subFunction.Return("response.body()");
                                    });
                                });
                                function.Line("}).toObservable();");
                            });
                        }

                        addedClientMethods = true;
                    }
                    else if (methodIsLongRunningOperation)
                    {
                        foreach (IEnumerable<Parameter> parameters in parameterLists)
                        {
                            bool onlyRequiredParameters = (parameters == requiredClientMethodParameters);
                            
                            string argumentList = string.Join(", ", parameters.Select((Parameter parameter) => parameter.Name));

                            string parameterDeclaration = string.Join(", ", parameters.Select(parameter =>
                            {
                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);
                                string parameterType = IModelTypeName(parameterClientType, settings);
                                return $"{parameterType} {parameter.Name}";
                            }));

                            // --------------------
                            // Synchronous Overload
                            // --------------------
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                comment.Throws(methodOperationExceptionTypeName, "thrown if the request is rejected by server");
                                comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                                if (restAPIMethod.ReturnType.Body != null)
                                {
                                    comment.Return($"the {synchronousMethodReturnType} object if successful.");
                                }
                            });
                            typeBlock.PublicMethod($"{synchronousMethodReturnType} {methodName}({parameterDeclaration})", function =>
                            {
                                if (IModelTypeName(ConvertToClientType(restAPIMethodReturnBodyClientType), settings) == "void")
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
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Param(serviceCallbackVariableName, "the async ServiceCallback to handle successful and failed responses.");
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                comment.Return($"the {{@link {serviceFutureReturnType}}} object");
                            });

                            string serviceCallbackParameterDeclaration = parameterDeclaration;
                            if (!serviceCallbackParameterDeclaration.IsNullOrEmpty())
                            {
                                serviceCallbackParameterDeclaration += ", ";
                            }
                            serviceCallbackParameterDeclaration += $"final ServiceCallback<{responseGenericBodyClientTypeString}> {serviceCallbackVariableName}";

                            typeBlock.PublicMethod($"{serviceFutureReturnType} {methodName}Async({serviceCallbackParameterDeclaration})", function =>
                            {
                                function.Return($"ServiceFutureUtil.fromLRO({methodName}Async({argumentList}), {serviceCallbackVariableName})");
                            });

                            // ------------------------------------------
                            // Async Observable<OperationStatus> Overload
                            // ------------------------------------------
                            typeBlock.JavadocComment(comment =>
                            {
                                comment.Description(restAPIMethod.Summary);
                                comment.Description(restAPIMethod.Description);
                                foreach (Parameter parameter in parameters)
                                {
                                    string parameterName = parameter.Name;

                                    string parameterDocumentation = parameter.Documentation;
                                    if (string.IsNullOrEmpty(parameterDocumentation))
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                    }

                                    comment.Param(parameterName, parameterDocumentation);
                                }
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                                comment.Return("the observable for the request");
                            });
                            typeBlock.PublicMethod($"Observable<OperationStatus<{responseGenericBodyClientTypeString}>> {methodName}Async({parameterDeclaration})", function =>
                            {
                                foreach (Parameter parameter in requiredNullableParameters)
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

                                    function.If($"{parameterName} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {parameterName} is required and cannot be null.\");");
                                    });
                                }

                                IEnumerable<Parameter> parametersToValidate = restAPIMethod.Parameters.Where(parameter =>
                                {
                                    bool result = !parameter.IsConstant;
                                    if (result)
                                    {
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType) && (!onlyRequiredParameters || parameter.IsRequired);
                                    }

                                    return result;
                                });
                                foreach (Parameter parameter in parametersToValidate)
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

                                    function.Line($"Validator.validate({parameterName});");
                                }

                                foreach (Parameter parameter in clientMethodAndConstantParameters)
                                {
                                    string parameterName = parameter.Name;

                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    if (onlyRequiredParameters && !parameter.IsRequired)
                                    {
                                        string parameterDefaultValue;
                                        if (parameterClientType is PrimaryType parameterClientPrimaryType)
                                        {
                                            string modelTypeName = IModelTypeName(parameterClientPrimaryType, settings);
                                            if (modelTypeName == "byte[]")
                                            {
                                                parameterDefaultValue = "new byte[0]";
                                            }
                                            else if (modelTypeName == "Byte[]")
                                            {
                                                parameterDefaultValue = "new Byte[0]";
                                            }
                                            else
                                            {
                                                parameterDefaultValue = null;
                                            }
                                        }
                                        else
                                        {
                                            parameterDefaultValue = parameterClientType.DefaultValue;
                                        }
                                        function.Line($"final {IModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                    }
                                    else if (parameter.IsConstant)
                                    {
                                        string defaultValue = parameter.DefaultValue;
                                        if (defaultValue != null && parameterModelType is PrimaryType parameterModelPrimaryType)
                                        {
                                            switch (parameterModelPrimaryType.KnownPrimaryType)
                                            {
                                                case KnownPrimaryType.Double:
                                                    defaultValue = double.Parse(defaultValue).ToString();
                                                    break;

                                                case KnownPrimaryType.String:
                                                    defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                    break;

                                                case KnownPrimaryType.Boolean:
                                                    defaultValue = defaultValue.ToLowerInvariant();
                                                    break;

                                                case KnownPrimaryType.Long:
                                                    defaultValue = defaultValue + 'L';
                                                    break;

                                                case KnownPrimaryType.Date:
                                                    defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.DateTime:
                                                case KnownPrimaryType.DateTimeRfc1123:
                                                    defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.TimeSpan:
                                                    defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                    break;

                                                case KnownPrimaryType.ByteArray:
                                                    defaultValue = $"\"{defaultValue}\".getBytes()";
                                                    break;
                                            }
                                        }
                                        function.Line($"final {IModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                    }
                                }

                                foreach (ParameterTransformation transformation in restAPIMethod.InputParameterTransformation)
                                {
                                    Parameter transformationOutputParameter = transformation.OutputParameter;
                                    IModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                    if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter))
                                    {
                                        transformationOutputParameterModelType = GetIModelTypeNonNullableVariant(transformationOutputParameterModelType);
                                    }
                                    IModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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
                                    while (restAPIMethod.Parameters.Any((Parameter parameter) =>
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

                                    string transformationOutputParameterClientParameterVariantTypeName = IModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

                                    IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                                    string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                                        .Select((ParameterMapping m) =>
                                        {
                                            Parameter parameter = m.InputParameter;

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
                                    bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !onlyRequiredParameters;
                                    if (conditionalAssignment)
                                    {
                                        function.Line("{0} {1} = null;",
                                            transformationOutputParameterClientParameterVariantTypeName,
                                            outParamName);
                                        function.Line($"if ({nullCheck}) {{");
                                        function.IncreaseIndent();
                                    }

                                    bool transformationOutputParameterModelIsCompositeType = transformationOutputParameterModelType is CompositeType;
                                    if (transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) && transformationOutputParameterModelIsCompositeType)
                                    {
                                        function.Line("{0}{1} = new {2}();",
                                            !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            GetCompositeTypeName(transformationOutputParameterModelType as CompositeType, settings));
                                    }

                                    foreach (ParameterMapping mapping in transformationParameterMappings)
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
                                        if (onlyRequiredParameters && !mapping.InputParameter.IsRequired)
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
                                            !conditionalAssignment && !transformationOutputParameterModelIsCompositeType ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                            outParamName,
                                            getMapping);
                                    }

                                    if (conditionalAssignment)
                                    {
                                        function.DecreaseIndent();
                                        function.Line("}");
                                    }
                                }

                                foreach (Parameter parameter in methodRetrofitParameters)
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    IModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                        parameter.Location != ParameterLocation.Body &&
                                        parameter.Location != ParameterLocation.FormData &&
                                        ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    if (!parameterClientType.StructurallyEquals(parameterWireType))
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
                                        string parameterWireName = $"{parameterName.ToCamelCase()}Converted";

                                        bool addedConversion = false;
                                        ParameterLocation parameterLocation = parameter.Location;
                                        if (parameterLocation != ParameterLocation.Body &&
                                            parameterLocation != ParameterLocation.FormData &&
                                            ((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                                        {
                                            string parameterWireTypeName = IModelTypeName(parameterWireType, settings);

                                            if (parameterClientType is PrimaryType primaryClientType && primaryClientType.KnownPrimaryType == KnownPrimaryType.ByteArray)
                                            {
                                                if (parameterWireType.IsPrimaryType(KnownPrimaryType.String))
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                                }
                                                else
                                                {
                                                    function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                                }
                                                addedConversion = true;
                                            }
                                            else if (parameterClientType is SequenceType)
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
                                            ParameterConvertClientTypeToWireType(function, settings, parameter, parameterWireType, parameterName, parameterWireName, methodClientReference);
                                        }
                                    }
                                }

                                string restAPIMethodArgumentList = string.Join(", ", methodOrderedRetrofitParameters
                                    .Select((Parameter parameter) =>
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
                                        IModelType parameterModelType = parameter.ModelType;
                                        if (parameterModelType != null && !IsNullable(parameter))
                                        {
                                            parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                        }
                                        IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                        IModelType parameterWireType;
                                        if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                        {
                                            parameterWireType = parameterClientType;
                                        }
                                        else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                            parameter.Location != ParameterLocation.Body &&
                                            parameter.Location != ParameterLocation.FormData &&
                                            ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                        {
                                            parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                        }
                                        else
                                        {
                                            parameterWireType = parameterModelType;
                                        }

                                        string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                        string result;
                                        if (settings.ShouldGenerateXmlSerialization && parameterWireType is SequenceType)
                                        {
                                            result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                        }
                                        else
                                        {
                                            result = parameterWireName;
                                        }
                                        return result;
                                    }));

                                function.Return($"service.{methodName}({restAPIMethodArgumentList})");
                            });
                        }

                        addedClientMethods = true;
                    }
                }

                if (!addedClientMethods)
                {
                    bool isFluentDelete = settings.IsFluent && methodName.EqualsIgnoreCase(Delete) && requiredClientMethodParameters.Count() == 2;

                    foreach (IEnumerable<Parameter> parameters in parameterLists)
                    {
                        bool onlyRequiredParameters = (parameters == requiredClientMethodParameters);

                        string methodArguments = string.Join(", ", parameters.Select((Parameter parameter) => parameter.Name));

                        string parameterDeclaration = string.Join(", ", parameters.Select(parameter =>
                        {
                            IModelType parameterModelType = parameter.ModelType;
                            if (parameterModelType != null && !IsNullable(parameter))
                            {
                                parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                            }
                            IModelType parameterClientType = ConvertToClientType(parameterModelType);
                            string parameterType = IModelTypeName(parameterClientType, settings);
                            return $"{parameterType} {parameter.Name}";
                        }));

                        // -------------
                        // Synchronous T
                        // -------------
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(restAPIMethod.Summary);
                            comment.Description(restAPIMethod.Description);
                            foreach (Parameter parameter in parameters)
                            {
                                string parameterName = parameter.Name;

                                string parameterDocumentation = parameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDocumentation))
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                }

                                comment.Param(parameterName, parameterDocumentation);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Throws(methodOperationExceptionTypeName, "thrown if the request is rejected by server");
                            comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
                            if (restAPIMethod.ReturnType.Body != null)
                            {
                                comment.Return($"the {synchronousMethodReturnType} object if successful.");
                            }
                        });
                        typeBlock.PublicMethod($"{synchronousMethodReturnType} {methodName}({parameterDeclaration})", function =>
                        {
                            if (restAPIMethodReturnType.Body == null)
                            {
                                if (isFluentDelete)
                                {
                                    function.Line($"{methodName}Async({methodArguments}).blockingGet();");
                                }
                                else
                                {
                                    function.Line($"{methodName}Async({methodArguments}).blockingAwait();");
                                }
                            }
                            else
                            {
                                function.Return($"{methodName}Async({methodArguments}).blockingGet()");
                            }
                        });

                        // ----------------------
                        // Async ServiceFuture<T>
                        // ----------------------
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(restAPIMethod.Summary);
                            comment.Description(restAPIMethod.Description);
                            foreach (Parameter parameter in parameters)
                            {
                                string parameterName = parameter.Name;

                                string parameterDocumentation = parameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDocumentation))
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                }

                                comment.Param(parameterName, parameterDocumentation);
                            }
                            comment.Param(serviceCallbackVariableName, "the async ServiceCallback to handle successful and failed responses.");
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return($"the {{@link {serviceFutureReturnType}}} object");
                        });

                        string serviceCallbackParameterDeclaration = parameterDeclaration;
                        if (!serviceCallbackParameterDeclaration.IsNullOrEmpty())
                        {
                            serviceCallbackParameterDeclaration += ", ";
                        }
                        serviceCallbackParameterDeclaration += $"final ServiceCallback<{responseGenericBodyClientTypeString}> {serviceCallbackVariableName}";

                        typeBlock.PublicMethod($"{serviceFutureReturnType} {methodName}Async({serviceCallbackParameterDeclaration})", function =>
                        {
                            function.Return($"ServiceFuture.fromBody({methodName}Async({methodArguments}), {serviceCallbackVariableName})");
                        });

                        // -----------------------------------------
                        // Async Single<RestResponse<THeader,TBody>>
                        // -----------------------------------------
                        string singleRestResponseReturnType = $"Single<{restResponseType}>";
                        
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(restAPIMethod.Summary);
                            comment.Description(restAPIMethod.Description);
                            foreach (Parameter parameter in parameters)
                            {
                                string parameterName = parameter.Name;

                                string parameterDocumentation = parameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDocumentation))
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                }

                                comment.Param(parameterName, parameterDocumentation);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return($"the {{@link {singleRestResponseReturnType}}} object if successful.");
                        });
                        typeBlock.PublicMethod($"{singleRestResponseReturnType} {methodName}WithRestResponseAsync({parameterDeclaration})", function =>
                        {
                            foreach (Parameter parameter in requiredNullableParameters)
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

                                function.If($"{parameterName} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {parameterName} is required and cannot be null.\");");
                                });
                            }

                            foreach (Parameter parameter in clientMethodAndConstantParameters)
                            {
                                string parameterName = parameter.Name;

                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                if (onlyRequiredParameters && !parameter.IsRequired)
                                {
                                    string parameterDefaultValue;
                                    if (parameterClientType is PrimaryType parameterClientPrimaryType)
                                    {
                                        string modelTypeName = IModelTypeName(parameterClientPrimaryType, settings);
                                        if (modelTypeName == "byte[]")
                                        {
                                            parameterDefaultValue = "new byte[0]";
                                        }
                                        else if (modelTypeName == "Byte[]")
                                        {
                                            parameterDefaultValue = "new Byte[0]";
                                        }
                                        else
                                        {
                                            parameterDefaultValue = null;
                                        }
                                    }
                                    else
                                    {
                                        parameterDefaultValue = parameterClientType.DefaultValue;
                                    }
                                    function.Line($"final {IModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                }
                                else if (parameter.IsConstant)
                                {
                                    string defaultValue = parameter.DefaultValue;
                                    if (defaultValue != null && parameterModelType is PrimaryType parameterModelPrimaryType)
                                    {
                                        switch (parameterModelPrimaryType.KnownPrimaryType)
                                        {
                                            case KnownPrimaryType.Double:
                                                defaultValue = double.Parse(defaultValue).ToString();
                                                break;

                                            case KnownPrimaryType.String:
                                                defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                break;

                                            case KnownPrimaryType.Boolean:
                                                defaultValue = defaultValue.ToLowerInvariant();
                                                break;

                                            case KnownPrimaryType.Long:
                                                defaultValue = defaultValue + 'L';
                                                break;

                                            case KnownPrimaryType.Date:
                                                defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                break;

                                            case KnownPrimaryType.DateTime:
                                            case KnownPrimaryType.DateTimeRfc1123:
                                                defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                break;

                                            case KnownPrimaryType.TimeSpan:
                                                defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                break;

                                            case KnownPrimaryType.ByteArray:
                                                defaultValue = $"\"{defaultValue}\".getBytes()";
                                                break;
                                        }
                                    }
                                    function.Line($"final {IModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                }
                            }

                            IEnumerable<Parameter> parametersToValidate = restAPIMethod.Parameters.Where(parameter =>
                            {
                                bool result = !parameter.IsConstant;
                                if (result)
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    result = !(parameterModelType is PrimaryType) && !(parameterModelType is EnumType) && (!onlyRequiredParameters || parameter.IsRequired);
                                }

                                return result;
                            });
                            foreach (Parameter parameter in parametersToValidate)
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

                                function.Line($"Validator.validate({parameterName});");
                            }

                            foreach (ParameterTransformation transformation in restAPIMethod.InputParameterTransformation)
                            {
                                Parameter transformationOutputParameter = transformation.OutputParameter;
                                IModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter))
                                {
                                    transformationOutputParameterModelType = GetIModelTypeNonNullableVariant(transformationOutputParameterModelType);
                                }
                                IModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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
                                while (restAPIMethod.Parameters.Any((Parameter parameter) =>
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

                                string transformationOutputParameterClientParameterVariantTypeName = IModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

                                IEnumerable<ParameterMapping> transformationParameterMappings = transformation.ParameterMappings;
                                string nullCheck = string.Join(" || ", transformationParameterMappings.Where(m => !m.InputParameter.IsRequired)
                                    .Select((ParameterMapping m) =>
                                    {
                                        Parameter parameter = m.InputParameter;

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
                                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformationOutputParameter.IsRequired && !onlyRequiredParameters;
                                if (conditionalAssignment)
                                {
                                    function.Line("{0} {1} = null;",
                                        transformationOutputParameterClientParameterVariantTypeName,
                                        outParamName);
                                    function.Line($"if ({nullCheck}) {{");
                                    function.IncreaseIndent();
                                }

                                bool transformationOutputParameterModelIsCompositeType = transformationOutputParameterModelType is CompositeType;
                                if (transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) && transformationOutputParameterModelIsCompositeType)
                                {
                                    function.Line("{0}{1} = new {2}();",
                                        !conditionalAssignment ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                        outParamName,
                                        GetCompositeTypeName(transformationOutputParameterModelType as CompositeType, settings));
                                }

                                foreach (ParameterMapping mapping in transformationParameterMappings)
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
                                    if (onlyRequiredParameters && !mapping.InputParameter.IsRequired)
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
                                        !conditionalAssignment && !transformationOutputParameterModelIsCompositeType ? transformationOutputParameterClientParameterVariantTypeName + " " : "",
                                        outParamName,
                                        getMapping);
                                }

                                if (conditionalAssignment)
                                {
                                    function.DecreaseIndent();
                                    function.Line("}");
                                }
                            }

                            foreach (Parameter parameter in methodRetrofitParameters)
                            {
                                IModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                }
                                IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                IModelType parameterWireType;
                                if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                    parameter.Location != ParameterLocation.Body &&
                                    parameter.Location != ParameterLocation.FormData &&
                                    ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                }
                                else
                                {
                                    parameterWireType = parameterModelType;
                                }

                                if (!parameterClientType.StructurallyEquals(parameterWireType))
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
                                    string parameterWireName = $"{parameterName.ToCamelCase()}Converted";

                                    bool addedConversion = false;
                                    ParameterLocation parameterLocation = parameter.Location;
                                    if (parameterLocation != ParameterLocation.Body &&
                                        parameterLocation != ParameterLocation.FormData &&
                                        ((parameterModelType is PrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterModelType is SequenceType))
                                    {
                                        string parameterWireTypeName = IModelTypeName(parameterWireType, settings);

                                        if (parameterClientType is PrimaryType primaryClientType && primaryClientType.KnownPrimaryType == KnownPrimaryType.ByteArray)
                                        {
                                            if (parameterWireType.IsPrimaryType(KnownPrimaryType.String))
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                            }
                                            else
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                            }
                                            addedConversion = true;
                                        }
                                        else if (parameterClientType is SequenceType)
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
                                        ParameterConvertClientTypeToWireType(function, settings, parameter, parameterWireType, parameterName, parameterWireName, methodClientReference);
                                    }
                                }
                            }

                            string restAPIMethodArgumentList = string.Join(", ", methodOrderedRetrofitParameters
                                .Select((Parameter parameter) =>
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
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    IModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    IModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(KnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(KnownPrimaryType.Base64Url) &&
                                        parameter.Location != ParameterLocation.Body &&
                                        parameter.Location != ParameterLocation.FormData &&
                                        ((parameterClientType is PrimaryType primaryType && primaryType.KnownPrimaryType == KnownPrimaryType.ByteArray) || parameterClientType is SequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                    string result;
                                    if (settings.ShouldGenerateXmlSerialization && parameterWireType is SequenceType)
                                    {
                                        result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                    }
                                    else
                                    {
                                        result = parameterWireName;
                                    }
                                    return result;
                                }));

                            function.Return($"service.{methodName}({restAPIMethodArgumentList})");
                        });

                        // --------------
                        // Async Maybe<T>
                        // --------------
                        string asyncMethodReturnType;
                        if (restAPIMethodReturnType.Body != null)
                        {
                            asyncMethodReturnType = $"Maybe<{pageType}>";
                        }
                        else if (isFluentDelete)
                        {
                            asyncMethodReturnType = "Maybe<Void>";
                        }
                        else
                        {
                            asyncMethodReturnType = "Completable";
                        }

                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(restAPIMethod.Summary);
                            comment.Description(restAPIMethod.Description);
                            foreach (Parameter parameter in parameters)
                            {
                                string parameterName = parameter.Name;

                                string parameterDocumentation = parameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDocumentation))
                                {
                                    IModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        parameterModelType = GetIModelTypeNonNullableVariant(parameterModelType);
                                    }
                                    parameterDocumentation = $"the {IModelTypeName(parameterModelType, settings)} value";
                                }

                                comment.Param(parameterName, parameterDocumentation);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return($"the {{@link {asyncMethodReturnType}}} object if successful.");
                        });
                        typeBlock.PublicMethod($"{asyncMethodReturnType} {methodName}Async({parameterDeclaration})", function =>
                        {
                            function.Line($"return {methodName}WithRestResponseAsync({methodArguments})");
                            function.Indent(() =>
                            {
                                if (restAPIMethodReturnType.Body == null)
                                {
                                    if (isFluentDelete)
                                    {
                                        function.Line(".flatMapMaybe(new Function<RestResponse<?, ?>, Maybe<Void>>() {");
                                        function.Indent(() =>
                                        {
                                            function.Block("public Maybe<Void> apply(RestResponse<?, ?> restResponse)", subFunction =>
                                            {
                                                subFunction.Return("Maybe.empty()");
                                            });
                                        });
                                        function.Line("});");
                                    }
                                    else
                                    {
                                        function.Line(".toCompletable();");
                                    }
                                }
                                else
                                {
                                    function.Line($".flatMapMaybe(new Function<{restResponseType}, Maybe<{responseGenericBodyClientTypeString}>>() {{");
                                    function.Indent(() =>
                                    {
                                        function.Block($"public Maybe<{responseGenericBodyClientTypeString}> apply({restResponseType} restResponse)", subFunction =>
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
                }
            }
        }
    }
}
