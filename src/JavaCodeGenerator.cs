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
    public class JavaCodeGenerator : CodeGenerator
    {
        private const string targetVersion = "1.1.3";
        internal const string pomVersion = targetVersion + "-SNAPSHOT";

        private static readonly Parameter serviceClientCredentialsParameter = new Parameter("the management credentials for Azure", false, ClassType.ServiceClientCredentials, "credentials", true);
        private static readonly Parameter azureTokenCredentialsParameter = new Parameter("the management credentials for Azure", false, ClassType.AzureTokenCredentials, "credentials", true);
        private static readonly Parameter azureEnvironmentParameter = new Parameter("The environment that requests will target.", false, ClassType.AzureEnvironment, "azureEnvironment", true);
        private static readonly Parameter httpPipelineParameter = new Parameter("The HTTP pipeline to send requests through.", false, ClassType.HttpPipeline, "httpPipeline", true);

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

        private static readonly List<PageDetails> pageClasses = new List<PageDetails>();

        private static readonly Regex enumValueNameRegex = new Regex(@"[\\\/\.\+\ \-]+");

        private static readonly HashSet<AutoRestProperty> innerModelProperties = new HashSet<AutoRestProperty>();
        private static readonly ISet<AutoRestCompositeType> innerModelCompositeType = new HashSet<AutoRestCompositeType>();

        private static readonly ISet<AutoRestSequenceType> autoRestPagedListTypes = new HashSet<AutoRestSequenceType>();
        private static readonly ISet<ListType> pagedListTypes = new HashSet<ListType>();

        private static readonly IDictionary<AutoRestIModelType, string> pageImplTypes = new Dictionary<AutoRestIModelType, string>();

        // This is a Not set because the default value for WantNullable was true.
        private static readonly ISet<AutoRestPrimaryType> primaryTypeNotWantNullable = new HashSet<AutoRestPrimaryType>();

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
            "Flowable<ByteBuffer>"
        };

        private static readonly IDictionary<AutoRestIModelType, IType> parsedAutoRestIModelTypes = new Dictionary<AutoRestIModelType, IType>();

        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        private static readonly IEnumerable<IType> unixTimeTypes = new IType[] { PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime };
        private static readonly IEnumerable<IType> returnValueWireTypeOptions = new IType[] { ClassType.Base64Url, ClassType.DateTimeRfc1123 }.Concat(unixTimeTypes);

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
        public override Task Generate(AutoRestCodeModel codeModel)
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

            Service service = ParseService(codeModel, javaSettings);

            List<JavaFile> javaFiles = new List<JavaFile>();

            javaFiles.Add(GetServiceClientJavaFile(service.ServiceClient, javaSettings));

            foreach (MethodGroupClient methodGroupClient in service.ServiceClient.MethodGroupClients)
            {
                javaFiles.Add(GetMethodGroupClientJavaFile(methodGroupClient, javaSettings));
            }

            foreach (ServiceModel model in service.Models)
            {
                javaFiles.Add(GetModelJavaFile(model, javaSettings));
            }

            foreach (EnumType serviceEnum in service.Enums)
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
                foreach (PageDetails pageClass in pageClasses)
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
                javaFiles.Add(GetServiceClientInterfaceJavaFile(service.ServiceClient, javaSettings));

                foreach (MethodGroupClient methodGroupClient in service.ServiceClient.MethodGroupClients)
                {
                    javaFiles.Add(GetMethodGroupClientInterfaceJavaFile(methodGroupClient, javaSettings));
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

        private static void AppendInnerToTopLevelType(AutoRestIModelType type, AutoRestCodeModel serviceClient, JavaSettings settings)
        {
            if (type != null)
            {
                if (type is AutoRestCompositeType compositeType)
                {
                    string compositeTypeName = compositeType.Name.ToString();
                    if (!string.IsNullOrEmpty(compositeTypeName) && innerModelCompositeType.Contains(compositeType))
                    {
                        compositeTypeName += "Inner";
                    }

                    bool compositeTypeIsAzureResourceExtension = GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);
                    if (compositeTypeName != "Resource" && (compositeTypeName != "SubResource" || !compositeTypeIsAzureResourceExtension))
                    {
                        innerModelCompositeType.Add(compositeType);
                        innerModelProperties.AddRange(compositeType.Properties);
                    }
                }
                else if (type is AutoRestSequenceType sequenceType)
                {
                    AppendInnerToTopLevelType(sequenceType.ElementType, serviceClient, settings);
                }
                else if (type is AutoRestDictionaryType dictionaryType)
                {
                    AppendInnerToTopLevelType(dictionaryType.ValueType, serviceClient, settings);
                }
            }
        }

        private static Service ParseService(AutoRestCodeModel codeModel, JavaSettings settings)
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

                foreach (AutoRestMethodGroup methodGroup in codeModel.Operations)
                {
                    AutoRestMethod[] methods = methodGroup.Methods.ToArray();
                    methodGroup.ClearMethods();
                    foreach (AutoRestMethod method in methods)
                    {
                        methodGroup.Add(method);
                        if (GetExtensionBool(method.Extensions, AzureExtensions.LongRunningExtension))
                        {
                            AutoRestResponse response = method.Responses.Values.First();
                            if (!method.Responses.ContainsKey(HttpStatusCode.OK))
                            {
                                method.Responses[HttpStatusCode.OK] = response;
                            }
                            if (!method.Responses.ContainsKey(HttpStatusCode.Accepted))
                            {
                                method.Responses[HttpStatusCode.Accepted] = response;
                            }
                            if (method.HttpMethod != AutoRestHttpMethod.Get && !method.Responses.ContainsKey(HttpStatusCode.NoContent))
                            {
                                method.Responses[HttpStatusCode.NoContent] = response;
                            }

                            AutoRestMethod m = DependencyInjection.Duplicate(method);
                            method.Name = "begin" + m.Name.ToPascalCase();
                            m.Extensions.Remove(AzureExtensions.LongRunningExtension);
                            methodGroup.Add(m);
                        }
                    }
                }

                AzureExtensions.AddAzureProperties(codeModel);
                AzureExtensions.SetDefaultResponses(codeModel);

                AzureExtensions.AddPageableMethod(codeModel);

                IDictionary<AutoRestIModelType, AutoRestIModelType> convertedTypes = new Dictionary<AutoRestIModelType, AutoRestIModelType>();

                foreach (AutoRestMethod restAPIMethod in codeModel.Methods)
                {
                    bool simulateMethodAsPagingOperation = false;
                    AutoRestMethodGroup methodGroup = restAPIMethod.MethodGroup;
                    if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
                    {
                        MethodType restAPIMethodType = MethodType.Other;
                        string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.Url, ""), "");
                        string[] urlSplits = methodUrl.Split('/');
                        switch (restAPIMethod.HttpMethod)
                        {
                            case AutoRestHttpMethod.Get:
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

                            case AutoRestHttpMethod.Delete:
                                if (IsTopLevelResourceUrl(urlSplits))
                                {
                                    restAPIMethodType = MethodType.Delete;
                                }
                                break;
                        }

                        simulateMethodAsPagingOperation = (restAPIMethodType == MethodType.ListByResourceGroup || restAPIMethodType == MethodType.ListBySubscription) &&
                            1 == methodGroup.Methods.Count((AutoRestMethod methodGroupMethod) =>
                            {
                                MethodType methodGroupMethodType = MethodType.Other;
                                string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                string[] methodGroupUrlSplits = methodGroupMethodUrl.Split('/');
                                switch (methodGroupMethod.HttpMethod)
                                {
                                    case AutoRestHttpMethod.Get:
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

                                    case AutoRestHttpMethod.Delete:
                                        if (IsTopLevelResourceUrl(methodGroupUrlSplits))
                                        {
                                            methodGroupMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                return methodGroupMethodType == restAPIMethodType;
                            });
                    }

                    bool methodHasPageableExtensions = restAPIMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension);
                    JContainer methodPageableExtensions = !methodHasPageableExtensions ? null : restAPIMethod.Extensions[AzureExtensions.PageableExtension] as JContainer;
                    if (methodPageableExtensions != null || simulateMethodAsPagingOperation)
                    {
                        string nextLinkName = null;
                        string itemName = "value";
                        string className = null;

                        bool shouldCreatePageDetails = false;

                        if (methodHasPageableExtensions)
                        {
                            if (methodPageableExtensions != null)
                            {
                                shouldCreatePageDetails = true;

                                nextLinkName = (string)methodPageableExtensions["nextLinkName"];
                                itemName = (string)methodPageableExtensions["itemName"] ?? "value";
                                className = (string)methodPageableExtensions["className"];
                            }
                        }
                        else if (simulateMethodAsPagingOperation)
                        {
                            shouldCreatePageDetails = true;
                        }

                        PageDetails pageDetails = null;
                        if (shouldCreatePageDetails)
                        {
                            pageDetails = pageClasses.FirstOrDefault(page => page.NextLinkName == nextLinkName && page.ItemName == itemName);
                            if (pageDetails == null)
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

                                pageDetails = new PageDetails(nextLinkName, itemName, className);
                                pageClasses.Add(pageDetails);
                            }

                            if (!string.IsNullOrEmpty(pageDetails.ClassName))
                            {
                                if (string.IsNullOrEmpty(pageDetails.NextLinkName))
                                {
                                    restAPIMethod.Extensions[AzureExtensions.PageableExtension] = null;
                                }

                                bool anyTypeConverted = false;
                                foreach (HttpStatusCode responseStatus in restAPIMethod.Responses.Where(r => r.Value.Body is AutoRestCompositeType).Select(s => s.Key).ToArray())
                                {
                                    anyTypeConverted = true;
                                    AutoRestCompositeType compositeType = (AutoRestCompositeType)restAPIMethod.Responses[responseStatus].Body;
                                    AutoRestSequenceType sequenceType = compositeType.Properties
                                        .Select((AutoRestProperty property) =>
                                        {
                                            AutoRestIModelType propertyModelType = property.ModelType;
                                            if (propertyModelType != null && !IsNullable(property) && propertyModelType is AutoRestPrimaryType propertyModelPrimaryType)
                                            {
                                                AutoRestPrimaryType propertyModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(propertyModelPrimaryType.KnownPrimaryType);
                                                propertyModelNonNullablePrimaryType.Format = propertyModelPrimaryType.Format;
                                                primaryTypeNotWantNullable.Add(propertyModelNonNullablePrimaryType);

                                                propertyModelType = propertyModelNonNullablePrimaryType;
                                            }
                                            return propertyModelType;
                                        })
                                        .FirstOrDefault(t => t is AutoRestSequenceType) as AutoRestSequenceType;

                                    // if the type is a wrapper over page-able response
                                    if (sequenceType != null)
                                    {
                                        AutoRestSequenceType pagedResult = DependencyInjection.New<AutoRestSequenceType>();
                                        pagedResult.ElementType = sequenceType.ElementType;
                                        SequenceTypeSetPageImplType(pagedResult, pageDetails.ClassName);

                                        convertedTypes[restAPIMethod.Responses[responseStatus].Body] = pagedResult;
                                        AutoRestResponse resp = DependencyInjection.New<AutoRestResponse>(pagedResult, restAPIMethod.Responses[responseStatus].Headers);
                                        restAPIMethod.Responses[responseStatus] = resp;
                                    }
                                }

                                if (!anyTypeConverted && simulateMethodAsPagingOperation)
                                {
                                    foreach (HttpStatusCode responseStatus in restAPIMethod.Responses.Where(r => r.Value.Body is AutoRestSequenceType).Select(s => s.Key).ToArray())
                                    {
                                        AutoRestSequenceType sequenceType = (AutoRestSequenceType)restAPIMethod.Responses[responseStatus].Body;

                                        AutoRestSequenceType pagedResult = DependencyInjection.New<AutoRestSequenceType>();
                                        pagedResult.ElementType = sequenceType.ElementType;
                                        SequenceTypeSetPageImplType(pagedResult, pageDetails.ClassName);

                                        convertedTypes[restAPIMethod.Responses[responseStatus].Body] = pagedResult;
                                        AutoRestResponse resp = DependencyInjection.New<AutoRestResponse>(pagedResult, restAPIMethod.Responses[responseStatus].Headers);
                                        restAPIMethod.Responses[responseStatus] = resp;
                                    }
                                }

                                if (convertedTypes.ContainsKey(restAPIMethod.ReturnType.Body))
                                {
                                    AutoRestResponse resp = DependencyInjection.New<AutoRestResponse>(convertedTypes[restAPIMethod.ReturnType.Body], restAPIMethod.ReturnType.Headers);
                                    restAPIMethod.ReturnType = resp;
                                }
                            }
                        }
                    }
                }

                SwaggerExtensions.RemoveUnreferencedTypes(codeModel,
                    new HashSet<string>(convertedTypes.Keys
                        .Where(x => x is AutoRestCompositeType)
                        .Cast<AutoRestCompositeType>()
                        .Select((AutoRestCompositeType compositeType) =>
                        {
                            string compositeTypeName = compositeType.Name.ToString();
                            if (settings.IsFluent && !string.IsNullOrEmpty(compositeTypeName) && innerModelCompositeType.Contains(compositeType))
                            {
                                compositeTypeName += "Inner";
                            }
                            return compositeTypeName;
                        })));

                if (settings.IsFluent)
                {
                    // determine inner models
                    foreach (AutoRestParameter parameter in codeModel.Methods.SelectMany(m => m.Parameters))
                    {
                        AutoRestIModelType parameterModelType = parameter.ModelType;
                        if (parameterModelType != null && !IsNullable(parameter))
                        {
                            if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                            {
                                AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                parameterModelType = nonNullableParameterModelPrimaryType;
                            }
                        }
                        AppendInnerToTopLevelType(parameterModelType, codeModel, settings);
                    }
                    foreach (AutoRestResponse response in codeModel.Methods.SelectMany(m => m.Responses).Select(r => r.Value))
                    {
                        AppendInnerToTopLevelType(response.Body, codeModel, settings);
                        AppendInnerToTopLevelType(response.Headers, codeModel, settings);
                    }
                    foreach (AutoRestCompositeType model in codeModel.ModelTypes)
                    {
                        AutoRestIModelType baseModelType = model.BaseModelType;
                        if (baseModelType != null && (AutoRestIModelTypeName(baseModelType, settings) == "Resource" || AutoRestIModelTypeName(baseModelType, settings) == "SubResource"))
                        {
                            AppendInnerToTopLevelType(model, codeModel, settings);
                        }
                    }
                }

                // param order (PATH first)
                foreach (AutoRestMethod method in codeModel.Methods)
                {
                    List<AutoRestParameter> parameters = method.Parameters.ToList();
                    method.ClearParameters();
                    foreach (AutoRestParameter parameter in parameters.Where(x => x.Location == AutoRestParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                    foreach (AutoRestParameter parameter in parameters.Where(x => x.Location != AutoRestParameterLocation.Path))
                    {
                        method.Add(parameter);
                    }
                }
            }

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = ParseServiceClient(codeModel, settings);

            IEnumerable<string> subpackages = ParseSubpackages(settings);

            List<EnumType> enumTypes = new List<EnumType>();
            foreach (AutoRestEnumType autoRestEnumType in codeModel.EnumTypes)
            {
                IType type = ParseEnumType(autoRestEnumType, settings);
                if (type is EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ServiceException> exceptions = ParseExceptions(codeModel, settings);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel, settings);

            IEnumerable<ServiceModel> models = ParseModels(codeModel, settings);

            ServiceManager manager = ParseManager(serviceClientName, codeModel, settings);

            return new Service(serviceClientName, serviceClientDescription, subpackages, enumTypes, exceptions, xmlSequenceWrappers, models, manager, serviceClient);
        }

        private static ServiceClient ParseServiceClient(AutoRestCodeModel codeModel, JavaSettings settings)
        {
            string serviceClientInterfaceName = codeModel.Name.ToPascalCase();

            string serviceClientClassName = serviceClientInterfaceName + "Impl";

            RestAPI serviceClientRestAPI = null;
            IEnumerable<ClientMethod> serviceClientMethods = Enumerable.Empty<ClientMethod>();
            IEnumerable<AutoRestMethod> codeModelRestAPIMethods = codeModel.Methods.Where(m => m.Group.IsNullOrEmpty());
            if (codeModelRestAPIMethods.Any())
            {
                string restAPIName = serviceClientInterfaceName + "Service";
                string restAPIBaseURL = codeModel.BaseUrl;
                List<RestAPIMethod> restAPIMethods = new List<RestAPIMethod>();
                foreach (AutoRestMethod codeModelRestAPIMethod in codeModelRestAPIMethods)
                {
                    RestAPIMethod restAPIMethod = ParseRestAPIMethod(codeModelRestAPIMethod, settings);
                    restAPIMethods.Add(restAPIMethod);
                }
                serviceClientRestAPI = new RestAPI(restAPIName, restAPIBaseURL, restAPIMethods);
                serviceClientMethods = ParseClientMethods(serviceClientRestAPI, settings);
            }

            List<MethodGroupClient> serviceClientMethodGroupClients = new List<MethodGroupClient>();
            IEnumerable<AutoRestMethodGroup> codeModelMethodGroups = codeModel.Operations.Where((AutoRestMethodGroup methodGroup) => !string.IsNullOrEmpty(methodGroup?.Name?.ToString()));
            foreach (AutoRestMethodGroup codeModelMethodGroup in codeModelMethodGroups)
            {
                serviceClientMethodGroupClients.Add(ParseMethodGroupClient(codeModelMethodGroup, settings));
            }

            bool usesCredentials = false;

            List<ServiceClientProperty> serviceClientProperties = new List<ServiceClientProperty>();
            foreach (AutoRestProperty codeModelServiceClientProperty in codeModel.Properties)
            {
                string serviceClientPropertyDescription = codeModelServiceClientProperty.Documentation.ToString();

                string serviceClientPropertyName = CodeNamer.Instance.RemoveInvalidCharacters(codeModelServiceClientProperty.Name.ToCamelCase());

                IType serviceClientPropertyClientType = ConvertToClientType(ParseType(codeModelServiceClientProperty.ModelType, settings));

                bool serviceClientPropertyIsReadOnly = codeModelServiceClientProperty.IsReadOnly;

                string serviceClientPropertyDefaultValueExpression = codeModelServiceClientProperty.DefaultValue;
                if (serviceClientPropertyDefaultValueExpression != null)
                {
                    if (serviceClientPropertyClientType == PrimitiveType.Double || serviceClientPropertyClientType == ClassType.Double)
                    {
                        serviceClientPropertyDefaultValueExpression = double.Parse(serviceClientPropertyDefaultValueExpression).ToString();
                    }
                    else if (serviceClientPropertyClientType == ClassType.String)
                    {
                        serviceClientPropertyDefaultValueExpression = CodeNamer.Instance.QuoteValue(serviceClientPropertyDefaultValueExpression);
                    }
                    else if (serviceClientPropertyClientType == PrimitiveType.Boolean || serviceClientPropertyClientType == ClassType.Boolean)
                    {
                        serviceClientPropertyDefaultValueExpression = serviceClientPropertyDefaultValueExpression.ToLowerInvariant();
                    }
                    else if (serviceClientPropertyClientType == PrimitiveType.Long || serviceClientPropertyClientType == ClassType.Long)
                    {
                        serviceClientPropertyDefaultValueExpression = serviceClientPropertyDefaultValueExpression + 'L';
                    }
                    else if (serviceClientPropertyClientType == ClassType.LocalDate)
                    {
                        serviceClientPropertyDefaultValueExpression = $"LocalDate.parse(\"{serviceClientPropertyDefaultValueExpression}\")";
                    }
                    else if (serviceClientPropertyClientType == ClassType.DateTime || serviceClientPropertyClientType == ClassType.DateTimeRfc1123)
                    {
                        serviceClientPropertyDefaultValueExpression = $"DateTime.parse(\"{serviceClientPropertyDefaultValueExpression}\")";
                    }
                    else if (serviceClientPropertyClientType == ClassType.Period)
                    {
                        serviceClientPropertyDefaultValueExpression = $"Period.parse(\"{serviceClientPropertyDefaultValueExpression}\")";
                    }
                    else if (serviceClientPropertyClientType == ArrayType.ByteArray)
                    {
                        serviceClientPropertyDefaultValueExpression = $"\"{serviceClientPropertyDefaultValueExpression}\".getBytes()";
                    }
                }

                if (serviceClientPropertyClientType == ClassType.ServiceClientCredentials)
                {
                    usesCredentials = true;
                }
                else
                {
                    serviceClientProperties.Add(new ServiceClientProperty(serviceClientPropertyDescription, serviceClientPropertyClientType, serviceClientPropertyName, serviceClientPropertyIsReadOnly, serviceClientPropertyDefaultValueExpression));
                }
            }

            List<Constructor> serviceClientConstructors = new List<Constructor>();
            string constructorDescription = $"Initializes an instance of {serviceClientInterfaceName} client.";
            if (settings.IsAzureOrFluent)
            {
                if (usesCredentials)
                {
                    serviceClientConstructors.Add(new Constructor(serviceClientCredentialsParameter));
                    serviceClientConstructors.Add(new Constructor(serviceClientCredentialsParameter, azureEnvironmentParameter));
                }
                else
                {
                    serviceClientConstructors.Add(new Constructor());
                    serviceClientConstructors.Add(new Constructor(azureEnvironmentParameter));
                }

                serviceClientConstructors.Add(new Constructor(httpPipelineParameter));
                serviceClientConstructors.Add(new Constructor(httpPipelineParameter, azureEnvironmentParameter));
            }
            else
            {
                serviceClientConstructors.Add(new Constructor());
                serviceClientConstructors.Add(new Constructor(httpPipelineParameter));
            }

            return new ServiceClient(serviceClientClassName, serviceClientInterfaceName, serviceClientRestAPI, serviceClientMethodGroupClients, serviceClientProperties, serviceClientConstructors, serviceClientMethods);
        }

        private static MethodGroupClient ParseMethodGroupClient(AutoRestMethodGroup methodGroup, JavaSettings settings)
        {
            string interfaceName = methodGroup.Name.ToString().ToPascalCase();
            if (!interfaceName.EndsWith('s'))
            {
                interfaceName += 's';
            }

            string className = interfaceName + (settings.IsFluent ? "Inner" : "Impl");

            string methodGroupClientInterfacePath = settings.Package + "." + interfaceName;

            string restAPIName = methodGroup.Name.ToString().ToPascalCase();
            if (!restAPIName.EndsWith('s'))
            {
                restAPIName += 's';
            }
            restAPIName += "Service";
            string restAPIBaseURL = methodGroup.CodeModel.BaseUrl;
            List<RestAPIMethod> restAPIMethods = new List<RestAPIMethod>();
            foreach (AutoRestMethod method in methodGroup.Methods)
            {
                restAPIMethods.Add(ParseRestAPIMethod(method, settings));
            }
            RestAPI restAPI = new RestAPI(restAPIName, restAPIBaseURL, restAPIMethods);

            List<string> implementedInterfaces = new List<string>();
            if (!settings.IsFluent)
            {
                implementedInterfaces.Add(interfaceName);
            }

            string serviceClientName = methodGroup.CodeModel.Name + "Impl";

            string variableType = interfaceName + (settings.IsFluent ? "Inner" : "");
            string variableName = interfaceName.ToCamelCase();

            IEnumerable<ClientMethod> clientMethods = ParseClientMethods(restAPI, settings);

            return new MethodGroupClient(className, interfaceName, implementedInterfaces, restAPI, serviceClientName, variableType, variableName, clientMethods);
        }

        private static RestAPIMethod ParseRestAPIMethod(AutoRestMethod autoRestMethod, JavaSettings settings)
        {
            string restAPIMethodRequestContentType = autoRestMethod.RequestContentType;

            bool restAPIMethodIsPagingNextOperation = GetExtensionBool(autoRestMethod?.Extensions, "nextLinkMethod");

            string restAPIMethodHttpMethod = autoRestMethod.HttpMethod.ToString().ToUpper();

            string restAPIMethodUrlPath = autoRestMethod.Url.TrimStart('/');

            IEnumerable<HttpStatusCode> restAPIMethodExpectedResponseStatusCodes = autoRestMethod.Responses.Keys.OrderBy(statusCode => statusCode);

            ClassType restAPIMethodExceptionType = null;
            if (autoRestMethod.DefaultResponse.Body != null)
            {
                AutoRestIModelType autoRestExceptionType = autoRestMethod.DefaultResponse.Body;
                IType errorType = ParseType(autoRestExceptionType, settings);

                if (settings.IsAzureOrFluent && (errorType == null || errorType.ToString() == "CloudError"))
                {
                    restAPIMethodExceptionType = ClassType.CloudException;
                }
                else if (errorType is ClassType errorClassType)
                {
                    string exceptionPackage = settings.Package;
                    if (settings.IsFluent)
                    {
                        if (innerModelCompositeType.Contains(autoRestExceptionType))
                        {
                            exceptionPackage += ".implementation";
                        }
                    }
                    else
                    {
                        exceptionPackage += ".models";
                    }

                    string exceptionName = errorClassType.GetExtensionValue(SwaggerExtensions.NameOverrideExtension);
                    if (string.IsNullOrEmpty(exceptionName))
                    {
                        exceptionName = errorClassType.Name;
                        if (settings.IsFluent && !string.IsNullOrEmpty(exceptionName) && errorClassType.IsInnerModelType)
                        {
                            exceptionName += "Inner";
                        }
                        exceptionName += "Exception";
                    }
                    restAPIMethodExceptionType = new ClassType(exceptionPackage, exceptionName, null, null, false);
                }
                else
                {
                    restAPIMethodExceptionType = ClassType.RestException;
                }
            }

            string wellKnownMethodName = null;
            AutoRestMethodGroup methodGroup = autoRestMethod.MethodGroup;
            if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
            {
                MethodType methodType = MethodType.Other;
                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(autoRestMethod.Url, ""), "");
                string[] methodUrlSplits = methodUrl.Split('/');
                switch (autoRestMethod.HttpMethod)
                {
                    case AutoRestHttpMethod.Get:
                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                            && MethodHasSequenceType(autoRestMethod.ReturnType.Body, settings))
                        {
                            if (methodUrlSplits.Length == 5)
                            {
                                if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                {
                                    methodType = MethodType.ListBySubscription;
                                }
                                else
                                {
                                    methodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                            {
                                methodType = MethodType.ListByResourceGroup;
                            }
                        }
                        else if (IsTopLevelResourceUrl(methodUrlSplits))
                        {
                            methodType = MethodType.Get;
                        }
                        break;

                    case AutoRestHttpMethod.Delete:
                        if (IsTopLevelResourceUrl(methodUrlSplits))
                        {
                            methodType = MethodType.Delete;
                        }
                        break;
                }

                if (methodType != MethodType.Other)
                {
                    int methodsWithSameType = methodGroup.Methods.Count((AutoRestMethod methodGroupMethod) =>
                    {
                        MethodType methodGroupMethodType = MethodType.Other;
                        string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                        string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                        switch (methodGroupMethod.HttpMethod)
                        {
                            case AutoRestHttpMethod.Get:
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

                            case AutoRestHttpMethod.Delete:
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
                                wellKnownMethodName = List;
                                break;

                            case MethodType.ListByResourceGroup:
                                wellKnownMethodName = ListByResourceGroup;
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
            string restAPIMethodName;
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                AutoRestIParent methodParent = autoRestMethod.Parent;
                restAPIMethodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, autoRestMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(autoRestMethod.SingleItemAsEnumerable()));
            }
            else
            {
                restAPIMethodName = autoRestMethod.Name;
            }
            restAPIMethodName = restAPIMethodName.ToCamelCase();

            bool restAPIMethodSimulateMethodAsPagingOperation = (wellKnownMethodName == List || wellKnownMethodName == ListByResourceGroup);

            bool restAPIMethodIsLongRunningOperation = GetExtensionBool(autoRestMethod?.Extensions, AzureExtensions.LongRunningExtension);

            AutoRestResponse autoRestRestAPIMethodReturnType = autoRestMethod.ReturnType;
            IType responseBodyType = ParseType(autoRestRestAPIMethodReturnType.Body, settings);
            ListType responseBodyWireListType = responseBodyType as ListType;

            AutoRestIModelType autorestRestAPIMethodReturnClientType = ConvertToClientType(autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.None));
            AutoRestSequenceType autorestRestAPIMethodReturnClientSequenceType = autorestRestAPIMethodReturnClientType as AutoRestSequenceType;

            bool autorestRestAPIMethodReturnTypeIsPaged = GetExtensionBool(autoRestMethod.Extensions, "nextLinkMethod") ||
                (autoRestMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                 autoRestMethod.Extensions[AzureExtensions.PageableExtension] != null);

            if (settings.IsAzureOrFluent && responseBodyWireListType != null && autorestRestAPIMethodReturnTypeIsPaged)
            {
                AutoRestSequenceType autoRestRestAPIMethodReturnClientPageListType = DependencyInjection.New<AutoRestSequenceType>();
                autoRestRestAPIMethodReturnClientPageListType.ElementType = autorestRestAPIMethodReturnClientSequenceType.ElementType;

                string pageContainerSubPackage = (settings.IsFluent ? "implementation" : "models");
                string pageContainerPackage = $"{settings.Package}.{pageContainerSubPackage}";
                string pageContainerTypeName = SequenceTypeGetPageImplType(autorestRestAPIMethodReturnClientSequenceType);

                SequenceTypeSetPageImplType(autoRestRestAPIMethodReturnClientPageListType, pageContainerTypeName);
                autoRestPagedListTypes.Add(autoRestRestAPIMethodReturnClientPageListType);

                responseBodyType = new GenericType(pageContainerPackage, pageContainerTypeName, responseBodyWireListType.ElementType);
                pagedListTypes.Add(responseBodyWireListType);
            }

            IType restAPIMethodReturnType;
            if (restAPIMethodIsLongRunningOperation)
            {
                IType operationStatusTypeArgument;
                if (settings.IsAzureOrFluent && responseBodyWireListType != null && (autorestRestAPIMethodReturnTypeIsPaged || restAPIMethodSimulateMethodAsPagingOperation))
                {
                    operationStatusTypeArgument = GenericType.Page(responseBodyWireListType.ElementType);
                }
                else
                {
                    operationStatusTypeArgument = responseBodyType;
                }
                restAPIMethodReturnType = GenericType.Observable(GenericType.OperationStatus(operationStatusTypeArgument));
            }
            else
            {
                IType restResponseHeadersType = ConvertToClientType(ParseType(autoRestRestAPIMethodReturnType.Headers, settings));

                IType restResponseBodyType;
                if (settings.IsAzureOrFluent && responseBodyWireListType != null && (autorestRestAPIMethodReturnTypeIsPaged || restAPIMethodSimulateMethodAsPagingOperation))
                {
                    restResponseBodyType = responseBodyWireListType;
                }
                else
                {
                    restResponseBodyType = responseBodyType;
                }

                restAPIMethodReturnType = GenericType.Single(GenericType.RestResponse(restResponseHeadersType, responseBodyType));
            }

            List<AutoRestParameter> autoRestRestAPIMethodParameters = autoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None).ToList();

            List<AutoRestParameter> autoRestMethodLogicalParameters = autoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None).ToList();

            List<RestAPIParameter> restAPIMethodParameters = new List<RestAPIParameter>();
            if (settings.IsAzureOrFluent && restAPIMethodIsPagingNextOperation)
            {
                restAPIMethodParameters.Add(new RestAPIParameter(
                    description: "The URL to get the next page of items.",
                    type: ClassType.String,
                    name: "nextUrl",
                    requestParameterLocation: RequestParameterLocation.Path,
                    requestParameterName: "nextUrl",
                    alreadyEncoded: true,
                    isConstant: false,
                    isRequired: true,
                    isServiceClientProperty: false));

                autoRestMethodLogicalParameters.RemoveAll(p => p.Location == AutoRestParameterLocation.Path);
            }

            IEnumerable<AutoRestParameter> autoRestRestAPIMethodOrderedParameters = autoRestMethodLogicalParameters
                .Where(p => p.Location == AutoRestParameterLocation.Path)
                .Union(autoRestMethodLogicalParameters.Where(p => p.Location != AutoRestParameterLocation.Path));

            foreach (AutoRestParameter autoRestParameter in autoRestRestAPIMethodOrderedParameters)
            {
                string parameterRequestName = autoRestParameter.SerializedName;

                RequestParameterLocation parameterRequestLocation = ParseParameterRequestLocation(autoRestParameter.Location);
                if (autoRestMethod.Url.Contains("{" + parameterRequestName + "}"))
                {
                    parameterRequestLocation = RequestParameterLocation.Path;
                }
                else if (autoRestParameter.Extensions.ContainsKey("hostParameter"))
                {
                    parameterRequestLocation = RequestParameterLocation.Host;
                }

                AutoRestIModelType autoRestParameterWireType = autoRestParameter.ModelType;
                IType parameterType = ParseType(autoRestParameterWireType, settings);
                if (parameterType is ListType && settings.ShouldGenerateXmlSerialization && parameterRequestLocation == RequestParameterLocation.Body)
                {
                    string parameterTypePackage = settings.Package + ".implementation";
                    string parameterTypeName = autoRestParameterWireType.XmlName + "Wrapper";
                    parameterType = new ClassType(parameterTypePackage, parameterTypeName, null, null, false);
                }
                else if (parameterType == ArrayType.ByteArray)
                {
                    if (parameterRequestLocation != RequestParameterLocation.Body && parameterRequestLocation != RequestParameterLocation.FormData)
                    {
                        parameterType = ClassType.String;
                    }
                }
                else if (parameterType is ListType && autoRestParameter.Location != AutoRestParameterLocation.Body && autoRestParameter.Location != AutoRestParameterLocation.FormData)
                {
                    parameterType = ClassType.String;
                }

                bool parameterIsNullable = IsNullable(autoRestParameter);
                if (parameterIsNullable)
                {
                    parameterType = parameterType.AsNullable();
                }

                string parameterDescription = autoRestParameter.Documentation;
                if (string.IsNullOrEmpty(parameterDescription))
                {
                    if (!parameterIsNullable && autoRestParameter.ModelType is AutoRestPrimaryType parameterPrimaryType)
                    {
                        primaryTypeNotWantNullable.Add(parameterPrimaryType);
                    }

                    if (autoRestParameterWireType != null && !parameterIsNullable)
                    {
                        if (autoRestParameterWireType is AutoRestPrimaryType parameterModelPrimaryType)
                        {
                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                            autoRestParameterWireType = nonNullableParameterModelPrimaryType;
                        }
                    }
                    parameterDescription = $"the {AutoRestIModelTypeName(autoRestParameterWireType, settings)} value";
                }

                string parameterVariableName = autoRestParameter.ClientProperty?.Name?.ToString();
                if (!string.IsNullOrEmpty(parameterVariableName))
                {
                    CodeNamer codeNamer = CodeNamer.Instance;
                    parameterVariableName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(parameterVariableName));
                }
                if (parameterVariableName == null)
                {
                    if (!autoRestParameter.IsClientProperty)
                    {
                        parameterVariableName = autoRestParameter.Name;
                    }
                    else
                    {
                        string caller = (autoRestParameter.Method != null && autoRestParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = autoRestParameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        parameterVariableName = $"{caller}.{clientPropertyName}()";
                    }
                }

                bool parameterSkipUrlEncodingExtension = GetExtensionBool(autoRestParameter.Extensions, SwaggerExtensions.SkipUrlEncodingExtension);

                bool parameterIsConstant = autoRestParameter.IsConstant;

                bool parameterIsRequired = autoRestParameter.IsRequired;

                bool parameterIsServiceClientProperty = autoRestParameter.IsClientProperty;

                restAPIMethodParameters.Add(new RestAPIParameter(parameterDescription, parameterType, parameterVariableName, parameterRequestLocation, parameterRequestName, parameterSkipUrlEncodingExtension, parameterIsConstant, parameterIsRequired, parameterIsServiceClientProperty));
            }

            string restAPIMethodDescription = "";
            if (!string.IsNullOrEmpty(autoRestMethod.Summary))
            {
                restAPIMethodDescription += autoRestMethod.Summary;
            }
            if (!string.IsNullOrEmpty(autoRestMethod.Description))
            {
                if (restAPIMethodDescription != "")
                {
                    restAPIMethodDescription += Environment.NewLine;
                }
                restAPIMethodDescription += autoRestMethod.Description;
            }

            bool restAPIMethodIsPagingOperation = autoRestMethod.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                autoRestMethod.Extensions[AzureExtensions.PageableExtension] != null &&
                !restAPIMethodIsPagingNextOperation;

            IType restAPIMethodReturnValueWireType = returnValueWireTypeOptions.FirstOrDefault((IType type) => restAPIMethodReturnType.Contains(type));
            if (unixTimeTypes.Contains(restAPIMethodReturnValueWireType))
            {
                restAPIMethodReturnValueWireType = ClassType.UnixTime;
            }

            return new RestAPIMethod(
                restAPIMethodRequestContentType,
                restAPIMethodReturnType,
                restAPIMethodIsPagingNextOperation,
                restAPIMethodHttpMethod,
                restAPIMethodUrlPath,
                restAPIMethodExpectedResponseStatusCodes,
                restAPIMethodExceptionType,
                restAPIMethodName,
                restAPIMethodParameters,
                restAPIMethodIsPagingOperation,
                restAPIMethodDescription,
                restAPIMethodSimulateMethodAsPagingOperation,
                restAPIMethodIsLongRunningOperation,
                restAPIMethodReturnValueWireType,
                autoRestMethod);
        }

        private static RequestParameterLocation ParseParameterRequestLocation(AutoRestParameterLocation autoRestParameterLocation)
        {
            RequestParameterLocation parameterRequestLocation;
            switch (autoRestParameterLocation)
            {
                case AutoRestParameterLocation.Body:
                    parameterRequestLocation = RequestParameterLocation.Body;
                    break;

                case AutoRestParameterLocation.FormData:
                    parameterRequestLocation = RequestParameterLocation.FormData;
                    break;

                case AutoRestParameterLocation.Header:
                    parameterRequestLocation = RequestParameterLocation.Header;
                    break;

                case AutoRestParameterLocation.None:
                    parameterRequestLocation = RequestParameterLocation.None;
                    break;

                case AutoRestParameterLocation.Path:
                    parameterRequestLocation = RequestParameterLocation.Path;
                    break;

                case AutoRestParameterLocation.Query:
                    parameterRequestLocation = RequestParameterLocation.Query;
                    break;

                default:
                    throw new ArgumentException("Unrecognized AutoRest ParameterLocation value: " + autoRestParameterLocation);
            }
            return parameterRequestLocation;
        }

        private static IType ParseType(AutoRestIModelType autoRestIModelType, JavaSettings settings)
        {
            IType result = null;
            if (autoRestIModelType == null)
            {
                result = PrimitiveType.Void;
            }
            else if (parsedAutoRestIModelTypes.ContainsKey(autoRestIModelType))
            {
                result = parsedAutoRestIModelTypes[autoRestIModelType];
            }
            else
            {
                if (autoRestIModelType is AutoRestSequenceType autoRestSequenceType)
                {
                    result = new ListType(ParseType(autoRestSequenceType.ElementType, settings));
                }
                else if (autoRestIModelType is AutoRestDictionaryType autoRestDictionaryType)
                {
                    result = new MapType(ParseType(autoRestDictionaryType.ValueType, settings));
                }
                else if (autoRestIModelType is AutoRestEnumType autoRestEnumType)
                {
                    result = ParseEnumType(autoRestEnumType, settings);
                }
                else if (autoRestIModelType is AutoRestCompositeType autoRestCompositeType)
                {
                    string classTypeName = AutoRestCompositeTypeName(autoRestCompositeType, settings);
                    if (settings.IsAzureOrFluent)
                    {
                        if (classTypeName == ClassType.Resource.Name)
                        {
                            result = ClassType.Resource;
                        }
                        else if (classTypeName == ClassType.SubResource.Name)
                        {
                            result = ClassType.SubResource;
                        }
                    }
                    
                    if (result == null)
                    {
                        bool isInnerModelType = innerModelCompositeType.Contains(autoRestCompositeType);

                        string classSubPackage = "";
                        if (!settings.IsFluent)
                        {
                            classSubPackage = ".models";
                        }
                        else if (isInnerModelType)
                        {
                            classSubPackage = ".implementation";
                        }
                        string classPackage = settings.Package + classSubPackage;

                        IDictionary<string, string> extensions = null;
                        if (autoRestCompositeType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                        {
                            JContainer ext = autoRestCompositeType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                            if (ext != null && ext["name"] != null)
                            {
                                extensions = new Dictionary<string, string>();
                                extensions[SwaggerExtensions.NameOverrideExtension] = ext["name"].ToString();
                            }
                        }
                        result = new ClassType(classPackage, classTypeName, null, extensions, isInnerModelType);
                    }
                }
                else if (autoRestIModelType is AutoRestPrimaryType autoRestPrimaryType)
                {
                    switch (autoRestPrimaryType.KnownPrimaryType)
                    {
                        case AutoRestKnownPrimaryType.None:
                            result = PrimitiveType.Void;
                            break;
                        case AutoRestKnownPrimaryType.Base64Url:
                            result = ClassType.Base64Url;
                            break;
                        case AutoRestKnownPrimaryType.Boolean:
                            result = PrimitiveType.Boolean;
                            break;
                        case AutoRestKnownPrimaryType.ByteArray:
                            result = ArrayType.ByteArray;
                            break;
                        case AutoRestKnownPrimaryType.Date:
                            result = ClassType.LocalDate;
                            break;
                        case AutoRestKnownPrimaryType.DateTime:
                            result = ClassType.DateTime;
                            break;
                        case AutoRestKnownPrimaryType.DateTimeRfc1123:
                            result = ClassType.DateTimeRfc1123;
                            break;
                        case AutoRestKnownPrimaryType.Double:
                            result = PrimitiveType.Double;
                            break;
                        case AutoRestKnownPrimaryType.Decimal:
                            result = ClassType.BigDecimal;
                            break;
                        case AutoRestKnownPrimaryType.Int:
                            result = PrimitiveType.Int;
                            break;
                        case AutoRestKnownPrimaryType.Long:
                            result = PrimitiveType.Long;
                            break;
                        case AutoRestKnownPrimaryType.Stream:
                            result = GenericType.FlowableByteBuffer;
                            break;
                        case AutoRestKnownPrimaryType.String:
                            if (autoRestPrimaryType.Format.EqualsIgnoreCase(ClassType.URL.Name))
                            {
                                result = ClassType.URL;
                            }
                            else
                            {
                                result = ClassType.String;
                            }
                            break;
                        case AutoRestKnownPrimaryType.TimeSpan:
                            result = ClassType.Period;
                            break;
                        case AutoRestKnownPrimaryType.UnixTime:
                            result = PrimitiveType.UnixTimeLong;
                            break;
                        case AutoRestKnownPrimaryType.Uuid:
                            result = ClassType.UUID;
                            break;
                        case AutoRestKnownPrimaryType.Object:
                            result = ClassType.Object;
                            break;
                        case AutoRestKnownPrimaryType.Credentials:
                            result = ClassType.ServiceClientCredentials;
                            break;
                        default:
                            throw new NotImplementedException($"Unrecognized AutoRest KnownPrimaryType: {autoRestPrimaryType.KnownPrimaryType}");
                    }
                }
                else
                {
                    throw new ArgumentException($"Unrecognized AutoRest IModelType. Class: {autoRestIModelType.GetType().Name}, Name: {AutoRestIModelTypeName(autoRestIModelType, settings)}");
                }

                parsedAutoRestIModelTypes[autoRestIModelType] = result;
            }
            return result;
        }

        private static IType ParseEnumType(AutoRestEnumType autoRestEnumType, JavaSettings settings)
        {
            string enumTypeName = autoRestEnumType?.Name?.ToString();

            IType enumType;
            if (string.IsNullOrEmpty(enumTypeName) || enumTypeName == "enum")
            {
                enumType = ClassType.String;
            }
            else
            {
                string enumPackage = settings.Package + (settings.IsFluent ? "" : modelsPackage);

                enumTypeName = CodeNamer.Instance.GetTypeName(enumTypeName);

                bool expandable = autoRestEnumType.ModelAsString;

                List<ServiceEnumValue> enumValues = new List<ServiceEnumValue>();
                foreach (AutoRestEnumValue enumValue in autoRestEnumType.Values)
                {
                    enumValues.Add(ParseEnumValue(enumValue.MemberName, enumValue.SerializedName));
                }

                enumType = new EnumType(enumPackage, enumTypeName, expandable, enumValues);
            }

            return enumType;
        }

        private static bool IsNullable(AutoRestIVariable variable)
            => variable.IsXNullable.HasValue ? variable.IsXNullable.Value : !variable.IsRequired;

        private static IEnumerable<string> ParseSubpackages(JavaSettings settings)
        {
            List<string> subpackages = new List<string>() { "", "implementation" };
            if (!settings.IsFluent)
            {
                subpackages.Add("models");
            }
            return subpackages;
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

        private static IEnumerable<ServiceException> ParseExceptions(AutoRestCodeModel codeModel, JavaSettings settings)
        {
            List<ServiceException> exceptions = new List<ServiceException>();
            foreach (AutoRestCompositeType exceptionType in codeModel.ErrorTypes)
            {
                string errorName = exceptionType.Name.ToString();
                if (settings.IsFluent && !string.IsNullOrEmpty(errorName) && innerModelCompositeType.Contains(exceptionType))
                {
                    errorName += "Inner";
                }

                string methodOperationExceptionTypeName = errorName + "Exception";

                if (exceptionType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                {
                    JContainer ext = exceptionType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                    if (ext != null && ext["name"] != null)
                    {
                        methodOperationExceptionTypeName = ext["name"].ToString();
                    }
                }

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
                    AutoRestIModelType parameterModelType = parameter.ModelType;
                    if (parameterModelType != null && !IsNullable(parameter))
                    {
                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                        {
                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                            parameterModelType = nonNullableParameterModelPrimaryType;
                        }
                    }

                    if (parameterModelType is AutoRestSequenceType sequenceType)
                    {
                        string xmlElementName = sequenceType.XmlName.ToPascalCase();
                        if (xmlSequenceWrappers.Any(existingWrapper => existingWrapper.XmlElementName != xmlElementName))
                        {
                            string sequenceTypeName = $"List<{AutoRestIModelTypeName(sequenceType.ElementType, settings)}>";

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

        private static IEnumerable<ServiceModel> ParseModels(AutoRestCodeModel codeModel, JavaSettings settings)
        {
            List<ServiceModel> models = new List<ServiceModel>();
            ServiceModels serviceModels = new ServiceModels();
            foreach (AutoRestCompositeType modelType in GetModelTypes(codeModel, settings))
            {
                models.Add(ParseModel(modelType, settings, serviceModels));
            }
            return models;
        }

        private static ServiceModel ParseModel(AutoRestCompositeType compositeType, JavaSettings settings, ServiceModels models)
        {
            string modelName = compositeType.Name.ToString();
            if (settings.IsFluent && !string.IsNullOrEmpty(modelName) && innerModelCompositeType.Contains(compositeType))
            {
                modelName += "Inner";
            }

            ServiceModel result = models.GetModel(modelName);
            if (result == null)
            {
                string modelSubPackage = !settings.IsFluent ? modelsPackage : (innerModelCompositeType.Contains(compositeType) ? ".implementation" : "");

                bool isPolymorphic = compositeType.BaseIsPolymorphic;

                ServiceModel parentModel = null;
                if (compositeType.BaseModelType != null)
                {
                    parentModel = ParseModel(compositeType.BaseModelType, settings, models);
                }

                HashSet<string> modelImports = new HashSet<string>();
                IEnumerable<AutoRestProperty> compositeTypeProperties = compositeType.Properties;
                foreach (AutoRestProperty property in compositeTypeProperties)
                {
                    AutoRestIModelType propertyModelType = property.ModelType;
                    AutoRestPrimaryType propertyModelPrimaryType = propertyModelType as AutoRestPrimaryType;
                    if (propertyModelType != null && !IsNullable(property) && propertyModelPrimaryType != null)
                    {
                        AutoRestPrimaryType propertyModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(propertyModelPrimaryType.KnownPrimaryType);
                        propertyModelNonNullablePrimaryType.Format = propertyModelPrimaryType.Format;
                        primaryTypeNotWantNullable.Add(propertyModelNonNullablePrimaryType);

                        propertyModelType = propertyModelNonNullablePrimaryType;
                    }

                    IEnumerable<string> propertyModelTypeImports = GetIModelTypeImports(propertyModelType, settings);
                    if (propertyModelPrimaryType != null && (propertyModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.DateTimeRfc1123 || propertyModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.Base64Url))
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

                    if (compositeTypeProperties.Any(p => p.XmlIsWrapped && p.ModelType is AutoRestSequenceType))
                    {
                        modelImports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper");
                    }
                }

                // For polymorphism
                if (isPolymorphic)
                {
                    modelImports.Add("com.fasterxml.jackson.annotation.JsonTypeInfo");
                    modelImports.Add("com.fasterxml.jackson.annotation.JsonTypeName");

                    if (compositeType.BaseIsPolymorphic && compositeType.CodeModel.ModelTypes.Any((AutoRestCompositeType modelType) => modelType.BaseModelType?.SerializedName == compositeType.SerializedName))
                    {
                        modelImports.Add("com.fasterxml.jackson.annotation.JsonSubTypes");
                    }
                }

                // For flattening
                if (compositeTypeProperties.Any(p => p.WasFlattened()))
                {
                    modelImports.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
                }

                if (settings.IsAzure)
                {
                    foreach (AutoRestProperty property in compositeTypeProperties)
                    {
                        if (property.ModelType is AutoRestCompositeType propertyCompositeType)
                        {
                            string propertyCompositeTypeName = propertyCompositeType.Name.ToString();
                            if (settings.IsFluent && !string.IsNullOrEmpty(propertyCompositeTypeName) && innerModelCompositeType.Contains(propertyCompositeType))
                            {
                                propertyCompositeTypeName += "Inner";
                            }

                            bool propertyCompositeTypeIsAzureResourceExtension = GetExtensionBool(propertyCompositeType, AzureExtensions.AzureResourceExtension);
                            if (propertyCompositeTypeName == "Resource" || (propertyCompositeTypeName == "SubResource" && propertyCompositeTypeIsAzureResourceExtension))
                            {
                                modelImports.Add($"com.microsoft.azure.v2.{propertyCompositeTypeName}");
                            }
                        }
                    }

                    AutoRestCompositeType baseModelType = compositeType.BaseModelType;
                    if (baseModelType != null)
                    {
                        string baseModelTypeName = baseModelType.Name.ToString();
                        if (settings.IsFluent && !string.IsNullOrEmpty(baseModelTypeName) && innerModelCompositeType.Contains(baseModelType))
                        {
                            baseModelTypeName += "Inner";
                        }

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
                List<ServiceModelProperty> properties = new List<ServiceModelProperty>();
                foreach (AutoRestProperty property in compositeTypeProperties)
                {
                    properties.Add(ParseModelProperty(property, settings));
                    if (!needsFlatten && property.WasFlattened())
                    {
                        needsFlatten = true;
                    }
                }

                result = new ServiceModel(modelName, modelSubPackage, modelImports, modelDescription, isPolymorphic, polymorphicDiscriminator, modelSerializedName, needsFlatten, parentModel, derivedTypes, modelXmlName, properties);

                models.AddModel(result);
            }

            return result;
        }

        private static ServiceModelProperty ParseModelProperty(AutoRestProperty property, JavaSettings settings)
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

            AutoRestIModelType propertyModelType = property.ModelType;
            AutoRestPrimaryType propertyModelPrimaryType = propertyModelType as AutoRestPrimaryType;
            if (propertyModelType != null && !IsNullable(property) && propertyModelPrimaryType != null)
            {
                AutoRestPrimaryType propertyModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(propertyModelPrimaryType.KnownPrimaryType);
                propertyModelNonNullablePrimaryType.Format = propertyModelPrimaryType.Format;
                primaryTypeNotWantNullable.Add(propertyModelNonNullablePrimaryType);

                propertyModelType = propertyModelNonNullablePrimaryType;
            }

            string xmlListElementName = propertyModelType is AutoRestSequenceType st ? st.ElementXmlName : null;

            string wireTypeName = AutoRestIModelTypeName(propertyModelType, settings);

            bool isConstant = property.IsConstant;

            bool modelTypeIsSequence = propertyModelType is AutoRestSequenceType;
            bool modelTypeIsComposite = propertyModelType is AutoRestCompositeType;

            string clientTypeName = AutoRestIModelTypeName(ConvertToClientType(propertyModelType), settings);

            string defaultValue;
            try
            {
                defaultValue = property.DefaultValue;
                if (defaultValue != null && propertyModelType != null && propertyModelPrimaryType != null)
                {
                    switch (propertyModelPrimaryType.KnownPrimaryType)
                    {
                        case AutoRestKnownPrimaryType.Double:
                            defaultValue = double.Parse(defaultValue).ToString();
                            break;

                        case AutoRestKnownPrimaryType.String:
                            defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                            break;

                        case AutoRestKnownPrimaryType.Boolean:
                            defaultValue = defaultValue.ToLowerInvariant();
                            break;

                        case AutoRestKnownPrimaryType.Long:
                            defaultValue = defaultValue + 'L';
                            break;

                        case AutoRestKnownPrimaryType.Date:
                            defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                            break;

                        case AutoRestKnownPrimaryType.DateTime:
                        case AutoRestKnownPrimaryType.DateTimeRfc1123:
                            defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                            break;

                        case AutoRestKnownPrimaryType.TimeSpan:
                            defaultValue = $"Period.parse(\"{defaultValue}\")";
                            break;

                        case AutoRestKnownPrimaryType.ByteArray:
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

            return new ServiceModelProperty(name, description, annotationArguments, isXmlAttribute, xmlName, serializedName, isXmlWrapper, xmlListElementName, wireTypeName, isConstant, modelTypeIsSequence, modelTypeIsComposite, clientTypeName, defaultValue, isReadOnly);
        }

        private static ServiceManager ParseManager(string serviceClientName, AutoRestCodeModel codeModel, JavaSettings settings)
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
                    comment.Param(azureTokenCredentialsParameter.Name, azureTokenCredentialsParameter.Description);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({azureTokenCredentialsParameter.Declaration}, String subscriptionId)", function =>
                {
                    function.Line($"final {httpPipelineParameter.Type} {httpPipelineParameter.Name} = AzureProxy.defaultPipeline({className}.class, {azureTokenCredentialsParameter.Name});");
                    function.Return($"new {className}({httpPipelineParameter.Name}, subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {className} that exposes {manager.ServiceName} resource management API entry points.");
                    comment.Param(httpPipelineParameter.Name, httpPipelineParameter.Description);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.PublicStaticMethod($"{className} authenticate({httpPipelineParameter.Type} {httpPipelineParameter.Name}, String subscriptionId)", function =>
                {
                    function.Return($"new {className}({httpPipelineParameter.Name}, subscriptionId)");
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
                        comment.Param(azureTokenCredentialsParameter.Name, azureTokenCredentialsParameter.Description);
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return($"the interface exposing {manager.ServiceName} management API entry points that work across subscriptions");
                    });
                    interfaceBlock.PublicMethod($"{className} authenticate({azureTokenCredentialsParameter.Declaration}, String subscriptionId)");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description("The implementation for Configurable interface.");
                });
                classBlock.PrivateStaticFinalClass("ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass =>
                {
                    innerClass.PublicMethod($"{className} authenticate({azureTokenCredentialsParameter.Declaration}, String subscriptionId)", function =>
                    {
                        function.Return($"{className}.authenticate(build{httpPipelineParameter.Type}({azureTokenCredentialsParameter.Name}), subscriptionId)");
                    });
                });

                classBlock.PrivateMethod($"private {className}({httpPipelineParameter.Declaration}, String subscriptionId)", constructor =>
                {
                    constructor.Line("super(");
                    constructor.Indent(() =>
                    {
                        constructor.Line($"{httpPipelineParameter.Name},");
                        constructor.Line("subscriptionId,");
                        constructor.Line($"new {manager.ServiceClientName}Impl({httpPipelineParameter.Name}).withSubscriptionId(subscriptionId));");
                    });
                });
            });

            return javaFile;
        }

        public static JavaFile GetPageJavaFile(PageDetails pageClass, JavaSettings settings)
        {
            string subPackage = settings.IsFluent ? implPackage : modelsPackage;
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

        public static JavaFile GetServiceClientJavaFile(ServiceClient serviceClient, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, serviceClient.ClassName);

            string serviceClientClassDeclaration = $"{serviceClient.ClassName} extends ";
            if (settings.IsAzureOrFluent)
            {
                serviceClientClassDeclaration += "Azure";
            }
            serviceClientClassDeclaration += "ServiceClient";
            if (!settings.IsFluent)
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
            javaFile.PublicClass(serviceClientClassDeclaration, classBlock =>
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
                bool serviceClientUsesCredentials = serviceClient.Constructors.Any(constructor => constructor.Parameters.Contains(serviceClientCredentialsParameter));
                foreach (Constructor constructor in serviceClient.Constructors)
                {
                    classBlock.JavadocComment(comment =>
                    {
                        comment.Description($"Initializes an instance of {serviceClient.InterfaceName} client.");
                        foreach (Parameter parameter in constructor.Parameters)
                        {
                            comment.Param(parameter.Name, parameter.Description);
                        }
                    });

                    classBlock.PublicConstructor($"{serviceClient.ClassName}({string.Join(", ", constructor.Parameters.Select(parameter => parameter.Declaration))})", constructorBlock =>
                    {
                        if (settings.IsAzureOrFluent)
                        {
                            if (constructor.Parameters.SequenceEqual(new[] { serviceClientCredentialsParameter }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class, {serviceClientCredentialsParameter.Name}));");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { serviceClientCredentialsParameter, azureEnvironmentParameter }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class, {serviceClientCredentialsParameter.Name}), {azureEnvironmentParameter.Name});");
                            }
                            else if (!constructor.Parameters.Any())
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class));");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { azureEnvironmentParameter }))
                            {
                                constructorBlock.Line($"this({ClassType.AzureProxy.Name}.createDefaultPipeline({serviceClient.ClassName}.class), {azureEnvironmentParameter.Name});");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { httpPipelineParameter }))
                            {
                                constructorBlock.Line($"this({httpPipelineParameter.Name}, null);");
                            }
                            else if (constructor.Parameters.SequenceEqual(new[] { httpPipelineParameter, azureEnvironmentParameter }))
                            {
                                constructorBlock.Line($"super({httpPipelineParameter.Name}, {azureEnvironmentParameter.Name});");

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
                            else if (constructor.Parameters.SequenceEqual(new[] { httpPipelineParameter }))
                            {
                                constructorBlock.Line($"super({httpPipelineParameter.Name});");

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
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, serviceClient.InterfaceName);

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
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(implPackage, settings, methodGroupClient.ClassName);

            ISet<string> imports = new HashSet<string>();
            methodGroupClient.AddImportsTo(imports, true, settings);
            javaFile.Import(imports);

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
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(null, settings, methodGroupClient.InterfaceName);

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

        public static IEnumerable<AutoRestCompositeType> GetModelTypes(AutoRestCodeModel codeModel, JavaSettings settings)
        {
            List<AutoRestCompositeType> result = new List<AutoRestCompositeType>();

            foreach (AutoRestCompositeType modelType in codeModel.ModelTypes.Union(codeModel.HeaderTypes))
            {
                if (ShouldParseModelType(modelType, settings))
                {
                    result.Add(modelType);
                }
            }

            return result;
        }

        private static bool ShouldParseModelType(AutoRestCompositeType modelType, JavaSettings settings)
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
                    string modelTypeName = modelType.Name.ToString();
                    if (settings.IsFluent && !string.IsNullOrEmpty(modelTypeName) && innerModelCompositeType.Contains(modelType))
                    {
                        modelTypeName += "Inner";
                    }

                    bool modelTypeIsAzureResourceExtension = GetExtensionBool(modelType, AzureExtensions.AzureResourceExtension);
                    shouldParseModelType = modelTypeName != "Resource" && (modelTypeName != "SubResource" || !modelTypeIsAzureResourceExtension);
                }
            }
            return shouldParseModelType;
        }

        public static JavaFile GetModelJavaFile(ServiceModel model, JavaSettings settings)
        {
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(model.SubPackage, settings, model.Name);

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
                foreach (ServiceModelProperty property in model.Properties)
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

                            classBlock.Annotation($"JsonProperty(\"{property.XmlListElementName}\")");
                        }
                        else
                        {
                            classBlock.Annotation($"JsonProperty({property.AnnotationArguments})");
                        }
                    }
                    classBlock.PrivateMemberVariable($"{property.WireTypeName} {property.Name}");
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

                foreach (ServiceModelProperty property in model.Properties)
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

        public static JavaFile GetEnumJavaFile(EnumType serviceEnum, JavaSettings settings)
        {
            string enumTypeComment = $"Defines values for {serviceEnum.Name}.";

            string subpackage = settings.IsFluent ? null : modelsPackage;
            JavaFile javaFile = GetJavaFileWithHeaderAndPackage(subpackage, settings, serviceEnum.Name);
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

        internal static string GetServiceName(Settings autoRestSettings, AutoRestCodeModel codeModel)
            => GetServiceName(GetAutoRestSettingsServiceName(autoRestSettings), codeModel);

        private static string GetServiceName(string serviceName, AutoRestCodeModel codeModel)
        {
            if (string.IsNullOrEmpty(serviceName))
            {
                AutoRestMethod method = codeModel.Methods[0];
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

        private static IEnumerable<string> GetIModelTypeImports(AutoRestIModelType modelType, JavaSettings settings)
        {
            HashSet<string> result = new HashSet<string>();

            if (modelType != null)
            {
                if (modelType is AutoRestEnumType)
                {
                    string modelTypeName = AutoRestIModelTypeName(modelType, settings);
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
                else if (modelType is AutoRestCompositeType compositeType)
                {
                    string compositeTypeName = compositeType.Name.ToString();
                    if (settings.IsFluent && !string.IsNullOrEmpty(compositeTypeName) && innerModelCompositeType.Contains(compositeType))
                    {
                        compositeTypeName += "Inner";
                    }

                    bool compositeTypeIsExternalExtension = GetExtensionBool(compositeType, SwaggerExtensions.ExternalExtension);
                    bool compositeTypeIsAzureResourceExtension = GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);
                    result.AddRange(CompositeTypeImports(compositeTypeName, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings.IsAzure, settings.IsFluent, settings.Package));
                }
                else if (modelType is AutoRestSequenceType sequenceType)
                {
                    result.Add("java.util.List");
                    result.AddRange(GetIModelTypeImports(sequenceType.ElementType, settings));
                }
                else if (modelType is AutoRestDictionaryType dictionaryType)
                {
                    result.Add("java.util.Map");
                    result.AddRange(GetIModelTypeImports(dictionaryType.ValueType, settings));
                }
                else if (modelType is AutoRestPrimaryType primaryType)
                {
                    switch (primaryType.KnownPrimaryType)
                    {
                        case AutoRestKnownPrimaryType.Base64Url:
                            result.Add("com.microsoft.rest.v2.Base64Url");
                            break;
                        case AutoRestKnownPrimaryType.Date:
                            result.Add("org.joda.time.LocalDate");
                            break;
                        case AutoRestKnownPrimaryType.DateTime:
                            result.Add("org.joda.time.DateTime");
                            break;
                        case AutoRestKnownPrimaryType.DateTimeRfc1123:
                            result.Add("com.microsoft.rest.v2.DateTimeRfc1123");
                            break;
                        case AutoRestKnownPrimaryType.Decimal:
                            result.Add("java.math.BigDecimal");
                            break;
                        case AutoRestKnownPrimaryType.Stream:
                            result.Add("io.reactivex.Flowable");
                            break;
                        case AutoRestKnownPrimaryType.TimeSpan:
                            result.Add("org.joda.time.Period");
                            break;
                        case AutoRestKnownPrimaryType.UnixTime:
                            result.Add("org.joda.time.DateTime");
                            result.Add("org.joda.time.DateTimeZone");
                            break;
                        case AutoRestKnownPrimaryType.Uuid:
                            result.Add("java.util.UUID");
                            break;
                        case AutoRestKnownPrimaryType.Credentials:
                            result.Add($"{ClassType.ServiceClientCredentials.Package}.{ClassType.ServiceClientCredentials.Name}");
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

        private static IType ConvertToClientType(IType wireType)
        {
            IType clientType = wireType;
            if (wireType is GenericType wireGenericType)
            {
                IType[] wireTypeArguments = wireGenericType.TypeArguments;
                IType[] clientTypeArguments = wireTypeArguments.Select(ConvertToClientType).ToArray();

                for (int i = 0; i < clientTypeArguments.Length; ++i)
                {
                    if (clientTypeArguments[i] != wireTypeArguments[i])
                    {
                        if (wireGenericType is ListType)
                        {
                            clientType = new ListType(clientTypeArguments[0]);
                        }
                        else if (wireGenericType is MapType)
                        {
                            clientType = new MapType(clientTypeArguments[1]);
                        }
                        else
                        {
                            clientType = new GenericType(wireGenericType.Package, wireGenericType.Name, clientTypeArguments);
                        }
                        break;
                    }
                }
            }
            else if (wireType == ClassType.DateTimeRfc1123)
            {
                clientType = ClassType.DateTime;
            }
            else if (wireType == PrimitiveType.UnixTimeLong)
            {
                clientType = ClassType.UnixTimeDateTime;
            }
            else if (wireType == ClassType.Base64Url)
            {
                clientType = ArrayType.ByteArray;
            }
            return clientType;
        }

        private static AutoRestIModelType ConvertToClientType(AutoRestIModelType modelType)
        {
            AutoRestIModelType result = modelType;

            if (modelType is AutoRestSequenceType sequenceTypeJv)
            {
                AutoRestIModelType elementClientType = ConvertToClientType(sequenceTypeJv.ElementType);

                if (elementClientType != sequenceTypeJv.ElementType)
                {
                    bool elementClientPrimaryTypeIsNullable = true;
                    if (elementClientType is AutoRestPrimaryType elementClientPrimaryType && !PrimaryTypeGetWantNullable(elementClientPrimaryType))
                    {
                        switch (elementClientPrimaryType.KnownPrimaryType)
                        {
                            case AutoRestKnownPrimaryType.None:
                            case AutoRestKnownPrimaryType.Boolean:
                            case AutoRestKnownPrimaryType.Double:
                            case AutoRestKnownPrimaryType.Int:
                            case AutoRestKnownPrimaryType.Long:
                            case AutoRestKnownPrimaryType.UnixTime:
                                elementClientPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    if (elementClientPrimaryTypeIsNullable)
                    {
                        AutoRestSequenceType sequenceType = DependencyInjection.New<AutoRestSequenceType>();
                        sequenceType.ElementType = elementClientType;
                        result = sequenceType;
                    }
                }
            }
            else if (modelType is AutoRestDictionaryType dictionaryType)
            {
                AutoRestIModelType dictionaryValueClientType = ConvertToClientType(dictionaryType.ValueType);

                if (dictionaryValueClientType != dictionaryType.ValueType)
                {
                    bool dictionaryValueClientPrimaryTypeIsNullable = true;
                    if (dictionaryValueClientType is AutoRestPrimaryType dictionaryValueClientPrimaryType && !PrimaryTypeGetWantNullable(dictionaryValueClientPrimaryType))
                    {
                        switch (dictionaryValueClientPrimaryType.KnownPrimaryType)
                        {
                            case AutoRestKnownPrimaryType.None:
                            case AutoRestKnownPrimaryType.Boolean:
                            case AutoRestKnownPrimaryType.Double:
                            case AutoRestKnownPrimaryType.Int:
                            case AutoRestKnownPrimaryType.Long:
                            case AutoRestKnownPrimaryType.UnixTime:
                                dictionaryValueClientPrimaryTypeIsNullable = false;
                                break;
                        }
                    }

                    if (dictionaryValueClientPrimaryTypeIsNullable)
                    {
                        AutoRestDictionaryType dictionaryTypeResult = DependencyInjection.New<AutoRestDictionaryType>();
                        dictionaryTypeResult.ValueType = dictionaryValueClientType;
                        result = dictionaryTypeResult;
                    }
                }
            }
            else if (modelType is AutoRestPrimaryType primaryType)
            {
                if (primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.DateTimeRfc1123 ||
                    primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.UnixTime)
                {
                    result = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.DateTime);
                }
                else if (primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.Base64Url)
                {
                    result = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.ByteArray);
                }
                else if (primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.None)
                {
                    AutoRestPrimaryType nonNullableResult = DependencyInjection.New<AutoRestPrimaryType>(primaryType.KnownPrimaryType);
                    nonNullableResult.Format = primaryType.Format;
                    primaryTypeNotWantNullable.Add(nonNullableResult);

                    result = nonNullableResult;
                }
            }

            return result;
        }

        private static string AutoRestIModelTypeName(AutoRestIModelType modelType, JavaSettings settings)
        {
            string result = null;
            if (modelType != null)
            {
                result = modelType.Name.ToString();
                if (modelType is AutoRestEnumType enumType)
                {
                    result = enumType.Name.ToString();
                    result = (string.IsNullOrEmpty(result) || result == "enum" ? "String" : CodeNamer.Instance.GetTypeName(result));
                }
                else if (modelType is AutoRestSequenceType sequenceType)
                {
                    result = $"List<{AutoRestIModelTypeName(sequenceType.ElementType, settings)}>";
                    if (autoRestPagedListTypes.Contains(sequenceType))
                    {
                        result = "Paged" + result;
                    }
                }
                else if (modelType is AutoRestDictionaryType dictionaryType)
                {
                    result = $"Map<String, {AutoRestIModelTypeName(dictionaryType.ValueType, settings)}>";
                }
                else if (modelType is AutoRestCompositeType compositeType)
                {
                    result = AutoRestCompositeTypeName(compositeType, settings);
                }
                else if (modelType is AutoRestPrimaryType primaryType)
                {
                    result = AutoRestPrimaryTypeName(primaryType, settings);
                }
            }
            return result;
        }

        private static string AutoRestCompositeTypeName(AutoRestCompositeType autoRestCompositeType, JavaSettings settings)
        {
            string autoRestCompositeTypeName = autoRestCompositeType.Name.ToString();
            if (settings.IsFluent && !string.IsNullOrEmpty(autoRestCompositeTypeName) && innerModelCompositeType.Contains(autoRestCompositeType))
            {
                autoRestCompositeTypeName += "Inner";
            }
            return autoRestCompositeTypeName;
        }

        private static string AutoRestPrimaryTypeName(AutoRestPrimaryType autoRestPrimaryType, JavaSettings settings)
        {
            string autoRestPrimaryTypeName;
            switch (autoRestPrimaryType.KnownPrimaryType)
            {
                case AutoRestKnownPrimaryType.None:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Void" : "void";
                    break;
                case AutoRestKnownPrimaryType.Base64Url:
                    autoRestPrimaryTypeName = "Base64Url";
                    break;
                case AutoRestKnownPrimaryType.Boolean:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Boolean" : "boolean";
                    break;
                case AutoRestKnownPrimaryType.ByteArray:
                    autoRestPrimaryTypeName = "byte[]";
                    break;
                case AutoRestKnownPrimaryType.Date:
                    autoRestPrimaryTypeName = "LocalDate";
                    break;
                case AutoRestKnownPrimaryType.DateTime:
                    autoRestPrimaryTypeName = "DateTime";
                    break;
                case AutoRestKnownPrimaryType.DateTimeRfc1123:
                    autoRestPrimaryTypeName = "DateTimeRfc1123";
                    break;
                case AutoRestKnownPrimaryType.Double:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Double" : "double";
                    break;
                case AutoRestKnownPrimaryType.Decimal:
                    autoRestPrimaryTypeName = "BigDecimal";
                    break;
                case AutoRestKnownPrimaryType.Int:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Integer" : "int";
                    break;
                case AutoRestKnownPrimaryType.Long:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Long" : "long";
                    break;
                case AutoRestKnownPrimaryType.Stream:
                    autoRestPrimaryTypeName = "Flowable<byte[]>";
                    break;
                case AutoRestKnownPrimaryType.String:
                    autoRestPrimaryTypeName = "String";
                    break;
                case AutoRestKnownPrimaryType.TimeSpan:
                    autoRestPrimaryTypeName = "Period";
                    break;
                case AutoRestKnownPrimaryType.UnixTime:
                    autoRestPrimaryTypeName = PrimaryTypeGetWantNullable(autoRestPrimaryType) ? "Long" : "long";
                    break;
                case AutoRestKnownPrimaryType.Uuid:
                    autoRestPrimaryTypeName = "UUID";
                    break;
                case AutoRestKnownPrimaryType.Object:
                    autoRestPrimaryTypeName = "Object";
                    break;
                case AutoRestKnownPrimaryType.Credentials:
                    autoRestPrimaryTypeName = ClassType.ServiceClientCredentials.Name;
                    break;

                default:
                    throw new NotImplementedException($"Primary type {autoRestPrimaryType.KnownPrimaryType} is not implemented in {autoRestPrimaryType.GetType().Name}");
            }
            return autoRestPrimaryTypeName;
        }

        private static bool GetExtensionBool(IDictionary<string, object> extensions, string extensionName)
            => extensions?.Get<bool>(extensionName) == true;

        private static bool GetExtensionBool(AutoRestModelType modelType, string extensionName)
            => GetExtensionBool(modelType?.Extensions, extensionName);

        private static string SequenceTypeGetPageImplType(AutoRestIModelType modelType)
            => pageImplTypes.ContainsKey(modelType) ? pageImplTypes[modelType] : null;

        private static void SequenceTypeSetPageImplType(AutoRestIModelType modelType, string pageImplType)
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

        private static bool MethodHasSequenceType(AutoRestIModelType modelType, JavaSettings settings)
        {
            return modelType is AutoRestSequenceType ||
                (modelType is AutoRestCompositeType modelCompositeType &&
                 modelCompositeType.Properties.Any((AutoRestProperty property) => MethodHasSequenceType(property.ModelType, settings)));
        }

        private static bool PrimaryTypeGetWantNullable(AutoRestPrimaryType primaryType)
            => !primaryTypeNotWantNullable.Contains(primaryType);

        private static void ParameterConvertClientTypeToWireType(JavaBlock block, JavaSettings settings, AutoRestParameter parameter, AutoRestIModelType parameterWireType, string source, string target, string clientReference, int level = 0)
        {
            bool parameterIsRequired = parameter.IsRequired;
            if (parameterWireType is AutoRestPrimaryType parameterWirePrimaryType)
            {
                if (parameterWirePrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.DateTimeRfc1123)
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
                else if (parameterWirePrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.UnixTime)
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
                else if (parameterWirePrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.Base64Url)
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
            else if (parameterWireType is AutoRestSequenceType wireSequenceType)
            {
                if (!parameterIsRequired)
                {
                    block.Line("{0} {1} = {2};",
                        AutoRestIModelTypeName(parameterWireType, settings),
                        target,
                        parameterWireType.DefaultValue ?? "null");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                string levelSuffix = (level == 0 ? "" : level.ToString());
                string itemName = $"item{levelSuffix}";
                string itemTarget = $"value{levelSuffix}";
                AutoRestIModelType elementType = wireSequenceType.ElementType;
                block.Line("{0}{1} = new ArrayList<{2}>();",
                    parameterIsRequired ? AutoRestIModelTypeName(parameterWireType, settings) + " " : "",
                    target,
                    AutoRestIModelTypeName(elementType, settings));
                block.Line("for ({0} {1} : {2}) {{",
                    AutoRestIModelTypeName(ConvertToClientType(elementType), settings),
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
            else if (parameterWireType is AutoRestDictionaryType dictionaryType)
            {
                if (!parameterIsRequired)
                {
                    block.Line($"{AutoRestIModelTypeName(parameterWireType, settings)} {target} = {parameterWireType.DefaultValue ?? "null"};");
                    block.Line($"if ({source} != null) {{");
                    block.IncreaseIndent();
                }

                AutoRestIModelType valueType = dictionaryType.ValueType;

                string levelString = (level == 0 ? "" : level.ToString(CultureInfo.InvariantCulture));
                string itemName = $"entry{levelString}";
                string itemTarget = $"value{levelString}";

                block.Line($"{(parameterIsRequired ? AutoRestIModelTypeName(parameterWireType, settings) + " " : "")}{target} = new HashMap<String, {AutoRestIModelTypeName(valueType, settings)}>();");
                block.Line($"for (Map.Entry<String, {AutoRestIModelTypeName(ConvertToClientType(valueType), settings)}> {itemName} : {source}.entrySet()) {{");
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
                            interfaceBlock.LineComment($"@Multipart not supported by {ClassType.RestProxy.Name}");
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

                        if (restAPIMethod.ReturnValueWireType != null)
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({restAPIMethod.ReturnValueWireType}.class)");
                        }

                        if (restAPIMethod.UnexpectedResponseExceptionType != null)
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({restAPIMethod.UnexpectedResponseExceptionType}.class)");
                        }

                        List<string> parameterDeclarationList = new List<string>();
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
                                    if (settings.IsAzureOrFluent && parameter.AlreadyEncoded && (parameter.RequestParameterLocation == RequestParameterLocation.Path || parameter.RequestParameterLocation == RequestParameterLocation.Query))
                                    {
                                        parameterDeclarationBuilder.Append($"value = \"{parameter.RequestParameterName}\", encoded = true");
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
                                    throw new ArgumentException("Unrecognized RequestParameterLocation value: " + parameter.RequestParameterLocation);
                            }

                            parameterDeclarationBuilder.Append(parameter.Type + " " + parameter.Name);
                            parameterDeclarationList.Add(parameterDeclarationBuilder.ToString());
                        }

                        string parameterDeclarations = string.Join(", ", parameterDeclarationList);

                        IType restAPIMethodReturnValueClientType = ConvertToClientType(restAPIMethod.ReturnType);
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
                AutoRestMethod autoRestMethod = clientMethod.AutoRestMethod;

                IEnumerable<AutoRestParameter> autoRestClientMethodAndConstantParameters = autoRestMethod.Parameters
                    //Omit parameter-group properties for now since Java doesn't support them yet
                    .Where((AutoRestParameter autoRestParameter) => autoRestParameter != null && !autoRestParameter.IsClientProperty && !string.IsNullOrWhiteSpace(autoRestParameter.Name))
                    .OrderBy(item => !item.IsRequired);
                IEnumerable<AutoRestParameter> autoRestClientMethodParameters = autoRestClientMethodAndConstantParameters
                    .Where((AutoRestParameter autoRestParameter) => !autoRestParameter.IsConstant)
                    .OrderBy((AutoRestParameter autoRestParameter) => !autoRestParameter.IsRequired);
                IEnumerable<AutoRestParameter> autoRestRequiredClientMethodParameters = autoRestClientMethodParameters
                    .Where(parameter => parameter.IsRequired);

                AutoRestMethodGroup autoRestMethodGroup = autoRestMethod.MethodGroup;
                MethodType autoRestRestAPIMethodType = MethodType.Other;
                if (!string.IsNullOrEmpty(autoRestMethodGroup?.Name?.ToString()))
                {
                    string autoRestMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(restAPIMethod.UrlPath, ""), "");
                    string[] autoRestMethodUrlSplits = autoRestMethodUrl.Split('/');
                    switch (autoRestMethod.HttpMethod)
                    {
                        case AutoRestHttpMethod.Get:
                            if ((autoRestMethodUrlSplits.Length == 5 || autoRestMethodUrlSplits.Length == 7)
                                && autoRestMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                && MethodHasSequenceType(autoRestMethod.ReturnType.Body, settings))
                            {
                                if (autoRestMethodUrlSplits.Length == 5)
                                {
                                    if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                    {
                                        autoRestRestAPIMethodType = MethodType.ListBySubscription;
                                    }
                                    else
                                    {
                                        autoRestRestAPIMethodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                {
                                    autoRestRestAPIMethodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (IsTopLevelResourceUrl(autoRestMethodUrlSplits))
                            {
                                autoRestRestAPIMethodType = MethodType.Get;
                            }
                            break;

                        case AutoRestHttpMethod.Delete:
                            if (IsTopLevelResourceUrl(autoRestMethodUrlSplits))
                            {
                                autoRestRestAPIMethodType = MethodType.Delete;
                            }
                            break;
                    }
                }

                AutoRestResponse autoRestRestAPIMethodReturnType = autoRestMethod.ReturnType;
                AutoRestIModelType autoRestRestAPIMethodReturnBodyType = autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.None);

                IType restAPIMethodReturnBodyClientType = ConvertToClientType(ParseType(autoRestRestAPIMethodReturnBodyType, settings));

                GenericType pageImplType = null;
                IType deserializedResponseBodyType;
                IType pageType;

                if (settings.IsAzureOrFluent &&
                    restAPIMethodReturnBodyClientType is ListType restAPIMethodReturnBodyClientListType &&
                    (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation || restAPIMethod.SimulateAsPagingOperation))
                {
                    IType restAPIMethodReturnBodyClientListElementType = restAPIMethodReturnBodyClientListType.ElementType;

                    restAPIMethodReturnBodyClientType = GenericType.PagedList(restAPIMethodReturnBodyClientListElementType);

                    string pageImplTypeName = SequenceTypeGetPageImplType(autoRestRestAPIMethodReturnBodyType);

                    string pageImplSubPackage = settings.IsFluent ? implPackage : modelsPackage;
                    string pageImplPackage = $"{settings.Package}.{pageImplSubPackage}";

                    pageImplType = new GenericType(pageImplPackage, pageImplTypeName, restAPIMethodReturnBodyClientListElementType);
                    deserializedResponseBodyType = pageImplType;

                    pageType = GenericType.Page(restAPIMethodReturnBodyClientListElementType);
                }
                else
                {
                    deserializedResponseBodyType = restAPIMethodReturnBodyClientType;

                    pageType = restAPIMethodReturnBodyClientType.AsNullable();
                }

                GenericType restResponseType = GenericType.RestResponse(
                    headersType: ConvertToClientType(ParseType(autoRestRestAPIMethodReturnType.Headers, settings)),
                    bodyType: deserializedResponseBodyType);

                Parameter serviceCallbackParameter = new Parameter(
                    description: "the async ServiceCallback to handle successful and failed responses.",
                    isFinal: false,
                    type: GenericType.ServiceCallback(restAPIMethodReturnBodyClientType),
                    name: "serviceCallback",
                    isRequired: true);

                GenericType serviceFutureReturnType = GenericType.ServiceFuture(restAPIMethodReturnBodyClientType);

                GenericType observablePageType = GenericType.Observable(pageType);

                List<string> requiredNullableParameterExpressions = new List<string>();
                foreach (AutoRestParameter autoRestParameter in autoRestMethod.Parameters)
                {
                    if (!autoRestParameter.IsConstant && autoRestParameter.IsRequired)
                    {
                        IType parameterType = ParseType(autoRestParameter.ModelType, settings);
                        if (IsNullable(autoRestParameter))
                        {
                            parameterType = parameterType.AsNullable();
                        }

                        if (!(parameterType is PrimitiveType))
                        {
                            string parameterExpression;
                            if (!autoRestParameter.IsClientProperty)
                            {
                                parameterExpression = autoRestParameter.Name;
                            }
                            else
                            {
                                string caller = (autoRestParameter.Method != null && autoRestParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                string clientPropertyName = autoRestParameter.ClientProperty?.Name?.ToString();
                                if (!string.IsNullOrEmpty(clientPropertyName))
                                {
                                    CodeNamer codeNamer = CodeNamer.Instance;
                                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                }
                                parameterExpression = $"{caller}.{clientPropertyName}()";
                            }

                            requiredNullableParameterExpressions.Add(parameterExpression);
                        }
                    }
                }

                List<AutoRestParameter> autoRestMethodRetrofitParameters = autoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None).ToList();
                if (settings.IsAzureOrFluent && restAPIMethod.IsPagingNextOperation)
                {
                    autoRestMethodRetrofitParameters.RemoveAll(p => p.Location == AutoRestParameterLocation.Path);

                    AutoRestParameter autoRestNextUrlParam = DependencyInjection.New<AutoRestParameter>();
                    autoRestNextUrlParam.SerializedName = "nextUrl";
                    autoRestNextUrlParam.Documentation = "The URL to get the next page of items.";
                    autoRestNextUrlParam.Location = AutoRestParameterLocation.Path;
                    autoRestNextUrlParam.IsRequired = true;
                    autoRestNextUrlParam.ModelType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
                    autoRestNextUrlParam.Name = "nextUrl";
                    autoRestNextUrlParam.Extensions.Add(SwaggerExtensions.SkipUrlEncodingExtension, true);
                    autoRestMethodRetrofitParameters.Insert(0, autoRestNextUrlParam);
                }

                IEnumerable<AutoRestParameter> autoRestMethodOrderedRetrofitParameters = autoRestMethodRetrofitParameters.Where(p => p.Location == AutoRestParameterLocation.Path)
                                    .Union(autoRestMethodRetrofitParameters.Where(p => p.Location != AutoRestParameterLocation.Path));

                string methodClientReference = autoRestMethod.Group.IsNullOrEmpty() ? "this" : "this.client";

                AutoRestMethod nextMethod = null;
                string nextMethodInvocation = null;
                if (restAPIMethod.IsPagingNextOperation)
                {
                    nextMethod = autoRestMethod;

                    nextMethodInvocation = restAPIMethod.Name;
                    string nextMethodWellKnownMethodName = null;
                    if (!string.IsNullOrEmpty(autoRestMethodGroup?.Name?.ToString()))
                    {
                        if (autoRestRestAPIMethodType != MethodType.Other)
                        {
                            int methodsWithSameType = autoRestMethodGroup.Methods.Count((AutoRestMethod methodGroupMethod) =>
                            {
                                MethodType methodGroupMethodType = MethodType.Other;
                                string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                switch (methodGroupMethod.HttpMethod)
                                {
                                    case AutoRestHttpMethod.Get:
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

                                    case AutoRestHttpMethod.Delete:
                                        if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                        {
                                            methodGroupMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                return methodGroupMethodType == autoRestRestAPIMethodType;
                            });

                            if (methodsWithSameType == 1)
                            {
                                switch (autoRestRestAPIMethodType)
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
                        AutoRestIParent methodParent = autoRestMethod.Parent;
                        nextMethodInvocation = CodeNamer.Instance.GetUnique(nextMethodWellKnownMethodName, autoRestMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(autoRestMethod.SingleItemAsEnumerable()));
                    }
                    nextMethodInvocation = nextMethodInvocation.ToCamelCase();
                }
                else if (restAPIMethod.IsPagingOperation)
                {
                    string nextMethodName = autoRestMethod.Extensions.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
                    string nextMethodGroup = autoRestMethod.Extensions.GetValue<Fixable<string>>("nextMethodGroup");

                    nextMethod = autoRestMethod.CodeModel.Methods
                        .FirstOrDefault((AutoRestMethod codeModelMethod) =>
                        {
                            bool result = nextMethodGroup.EqualsIgnoreCase(codeModelMethod.Group);
                            if (result)
                            {
                                string codeModelMethodName = codeModelMethod.Name;
                                string codeModelMethodWellKnownMethodName = null;
                                AutoRestMethodGroup codeModelMethodMethodGroup = codeModelMethod.MethodGroup;
                                if (!string.IsNullOrEmpty(codeModelMethodMethodGroup?.Name?.ToString()))
                                {
                                    MethodType codeModelMethodType = MethodType.Other;
                                    string codeModelMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(codeModelMethod.Url, ""), "");
                                    string[] codeModelMethodUrlSplits = codeModelMethodUrl.Split('/');
                                    switch (codeModelMethod.HttpMethod)
                                    {
                                        case AutoRestHttpMethod.Get:
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

                                        case AutoRestHttpMethod.Delete:
                                            if (IsTopLevelResourceUrl(codeModelMethodUrlSplits))
                                            {
                                                codeModelMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }

                                    if (codeModelMethodType != MethodType.Other)
                                    {
                                        int methodsWithSameType = codeModelMethodMethodGroup.Methods.Count((AutoRestMethod methodGroupMethod) =>
                                        {
                                            MethodType methodGroupMethodType = MethodType.Other;
                                            string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                            string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                            switch (methodGroupMethod.HttpMethod)
                                            {
                                                case AutoRestHttpMethod.Get:
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

                                                case AutoRestHttpMethod.Delete:
                                                    if (IsTopLevelResourceUrl(methodGroupMethodUrlSplits))
                                                    {
                                                        methodGroupMethodType = MethodType.Delete;
                                                    }
                                                    break;
                                            }
                                            return methodGroupMethodType == autoRestRestAPIMethodType;
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
                                    AutoRestIParent methodParent = codeModelMethod.Parent;
                                    codeModelMethodName = CodeNamer.Instance.GetUnique(codeModelMethodWellKnownMethodName, codeModelMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(codeModelMethod.SingleItemAsEnumerable()));
                                }

                                result = nextMethodName.EqualsIgnoreCase(codeModelMethodName);
                            }
                            return result;
                        });

                    if (nextMethodGroup == null || autoRestMethod.Group == nextMethod.Group)
                    {
                        nextMethodInvocation = nextMethodName;
                    }
                    else
                    {
                        nextMethodInvocation = $"{(autoRestMethod.Group.IsNullOrEmpty() ? "this" : "client")}.get{nextMethodGroup.ToPascalCase()}().{nextMethodName}";
                    }
                }

                string nextPageLinkParameterName = null;
                string nextGroupTypeName = null;
                string groupedTypeName = null;
                string nextMethodParameterInvocation = null;
                AutoRestParameter groupedType = null;
                if (nextMethod != null)
                {
                    nextPageLinkParameterName = nextMethod.Parameters
                        .Select((AutoRestParameter parameter) =>
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

                    IEnumerable<AutoRestParameter> nextMethodRestAPIParameters = nextMethod.Parameters
                        .Where((AutoRestParameter parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                        .OrderBy(item => !item.IsRequired);

                    AutoRestParameter nextGroupType = null;
                    if (!clientMethod.OnlyRequiredParameters)
                    {
                        nextMethodParameterInvocation = string.Join(", ", nextMethodRestAPIParameters.Where(p => !p.IsConstant).Select((AutoRestParameter parameter) => parameter.Name));
                    }
                    else if (autoRestMethod.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                    {
                        nextMethodParameterInvocation = string.Join(", ", nextMethodRestAPIParameters.Select((AutoRestParameter parameter) => parameter.IsRequired ? parameter.Name.ToString() : "null"));
                    }
                    else
                    {
                        groupedType = autoRestMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                        nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                        List<string> invocations = new List<string>();
                        foreach (AutoRestParameter parameter in nextMethodRestAPIParameters)
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

                    if (restAPIMethod.IsPagingOperation && !autoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                    {
                        groupedType = groupedType ?? autoRestMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
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
                }

                bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase(Delete) && autoRestRequiredClientMethodParameters.Count() == 2;

                switch (clientMethod.Type)
                {
                    case ClientMethodType.PagingSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                            function.Line($"{pageType} response = {GetPagingAsyncSinglePageMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingGet();");
                            function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(response)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.PublicMethod($"{pageType} nextPage(String {nextPageLinkParameterName})", subFunction =>
                                {
                                    if (restAPIMethod.IsPagingOperation && !autoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                                    {
                                        if (nextGroupTypeName != groupedTypeName && (!clientMethod.OnlyRequiredParameters || groupedType.IsRequired))
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

                                            foreach (AutoRestParameter outputParameter in nextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
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

                                    subFunction.Return($"{GetPagingAsyncSinglePageMethodName(nextMethodInvocation)}({nextMethodParameterInvocation}).blockingGet()");
                                });
                            });
                        });
                        break;

                    case ClientMethodType.PagingAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                            function.Line($"return {GetPagingAsyncSinglePageMethodName(clientMethod)}({clientMethod.ArgumentList})");
                            function.Indent(() =>
                            {
                                function.Line(".toObservable()");
                                function.Line($".concatMap(new {GenericType.Function(pageType, clientMethod.ReturnValue.Type)}() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public {clientMethod.ReturnValue.Type} apply({pageType} page)", subFunction =>
                                    {
                                        subFunction.Line($"String {nextPageLinkParameterName} = page.nextPageLink();");
                                        subFunction.If($"{nextPageLinkParameterName} == null", ifBlock =>
                                        {
                                            ifBlock.Return("Observable.just(page)");
                                        });

                                        if (clientMethod.RestAPIMethod.IsPagingOperation && !clientMethod.AutoRestMethod.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                                        {
                                            if (nextGroupTypeName != groupedTypeName && (!clientMethod.OnlyRequiredParameters || groupedType.IsRequired))
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

                                                foreach (AutoRestParameter outputParameter in nextMethod.InputParameterTransformation.Select(transformation => transformation.OutputParameter))
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
                        break;

                    case ClientMethodType.PagingAsyncSinglePage:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                            foreach (string requiredNullableParameterExpression in requiredNullableParameterExpressions)
                            {
                                function.If($"{requiredNullableParameterExpression} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {requiredNullableParameterExpression} is required and cannot be null.\");");
                                });
                            }

                            foreach (string expressionToValidate in clientMethod.ExpressionsToValidate)
                            {
                                function.Line($"Validator.validate({expressionToValidate});");
                            }

                            foreach (AutoRestParameter parameter in autoRestClientMethodAndConstantParameters)
                            {
                                string parameterName = parameter.Name;

                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                if (clientMethod.OnlyRequiredParameters && !parameter.IsRequired)
                                {
                                    string parameterDefaultValue;
                                    if (parameterClientType is AutoRestPrimaryType parameterClientPrimaryType)
                                    {
                                        string modelTypeName = AutoRestIModelTypeName(parameterClientPrimaryType, settings);
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
                                    function.Line($"final {AutoRestIModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                }
                                else if (parameter.IsConstant)
                                {
                                    string defaultValue = parameter.DefaultValue;
                                    if (defaultValue != null && parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        switch (parameterModelPrimaryType.KnownPrimaryType)
                                        {
                                            case AutoRestKnownPrimaryType.Double:
                                                defaultValue = double.Parse(defaultValue).ToString();
                                                break;

                                            case AutoRestKnownPrimaryType.String:
                                                defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                break;

                                            case AutoRestKnownPrimaryType.Boolean:
                                                defaultValue = defaultValue.ToLowerInvariant();
                                                break;

                                            case AutoRestKnownPrimaryType.Long:
                                                defaultValue = defaultValue + 'L';
                                                break;

                                            case AutoRestKnownPrimaryType.Date:
                                                defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.DateTime:
                                            case AutoRestKnownPrimaryType.DateTimeRfc1123:
                                                defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.TimeSpan:
                                                defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.ByteArray:
                                                defaultValue = $"\"{defaultValue}\".getBytes()";
                                                break;
                                        }
                                    }
                                    function.Line($"final {AutoRestIModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                }
                            }

                            foreach (AutoRestParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
                            {
                                AutoRestParameter transformationOutputParameter = transformation.OutputParameter;
                                AutoRestIModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter) && transformationOutputParameterModelType is AutoRestPrimaryType transformationOutputParameterModelPrimaryType)
                                {
                                    AutoRestPrimaryType transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                                    primaryTypeNotWantNullable.Add(transformationOutputParameterModelNonNullablePrimaryType);

                                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                                }
                                AutoRestIModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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

                                string transformationOutputParameterClientParameterVariantTypeName = AutoRestIModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

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

                                AutoRestCompositeType transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as AutoRestCompositeType;
                                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                                {
                                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && innerModelCompositeType.Contains(transformationOutputParameterModelCompositeType))
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

                            foreach (AutoRestParameter parameter in autoRestMethodRetrofitParameters)
                            {
                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                AutoRestIModelType parameterWireType;
                                if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                    parameter.Location != AutoRestParameterLocation.Body &&
                                    parameter.Location != AutoRestParameterLocation.FormData &&
                                    ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
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
                                    AutoRestParameterLocation parameterLocation = parameter.Location;
                                    if (parameterLocation != AutoRestParameterLocation.Body &&
                                        parameterLocation != AutoRestParameterLocation.FormData &&
                                        ((parameterModelType is AutoRestPrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterModelType is AutoRestSequenceType))
                                    {
                                        string parameterWireTypeName = AutoRestIModelTypeName(parameterWireType, settings);

                                        if (parameterClientType is AutoRestPrimaryType primaryClientType && primaryClientType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray)
                                        {
                                            if (parameterWireType.IsPrimaryType(AutoRestKnownPrimaryType.String))
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                            }
                                            else
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                            }
                                            addedConversion = true;
                                        }
                                        else if (parameterClientType is AutoRestSequenceType)
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

                            if (restAPIMethod.IsPagingNextOperation)
                            {
                                string methodUrl = autoRestMethod.Url;
                                Regex regex = new Regex("{\\w+}");

                                string substitutedMethodUrl = regex.Replace(methodUrl, "%s").TrimStart('/');

                                IEnumerable<AutoRestParameter> retrofitParameters = autoRestMethod.LogicalParameters.Where(p => p.Location != AutoRestParameterLocation.None);
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

                                    AutoRestIModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                        {
                                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }

                                    AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    AutoRestIModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
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

                            IEnumerable<AutoRestParameter> orderedRetrofitParameters = autoRestMethodRetrofitParameters.Where(p => p.Location == AutoRestParameterLocation.Path)
                                .Union(autoRestMethodRetrofitParameters.Where(p => p.Location != AutoRestParameterLocation.Path));
                            string restAPIMethodArgumentList = string.Join(", ", orderedRetrofitParameters
                                .Select((AutoRestParameter parameter) =>
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
                                    AutoRestIModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                        {
                                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }
                                    AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    AutoRestIModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                    string result;
                                    if (settings.ShouldGenerateXmlSerialization && parameterWireType is AutoRestSequenceType)
                                    {
                                        result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                    }
                                    else
                                    {
                                        result = parameterWireName;
                                    }
                                    return result;
                                }));

                            function.Line($"return service.{restAPIMethod.Name}({restAPIMethodArgumentList}).map(new {GenericType.Function(restResponseType, pageType)}() {{");
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
                        break;

                    case ClientMethodType.SimulatedPagingSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Line($"{pageImplType} page = new {pageImplType}<>();");
                            function.Line($"page.setItems({GetSimulatedPagingAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).single().items());");
                            function.Line("page.setNextPageLink(null);");
                            function.ReturnAnonymousClass($"new {clientMethod.ReturnValue.Type}(page)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.PublicMethod($"{pageType} nextPage(String nextPageLink)", subFunction =>
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
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (requiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            foreach (string requiredNullableParameterExpression in requiredNullableParameterExpressions)
                            {
                                function.If($"{requiredNullableParameterExpression} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {requiredNullableParameterExpression} is required and cannot be null.\");");
                                });
                            }
                            foreach (string expressionToValidate in clientMethod.ExpressionsToValidate)
                            {
                                function.Line($"Validator.validate({expressionToValidate});");
                            }
                            foreach (AutoRestParameter parameter in autoRestClientMethodAndConstantParameters)
                            {
                                string parameterName = parameter.Name;

                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                if (clientMethod.OnlyRequiredParameters && !parameter.IsRequired)
                                {
                                    string parameterDefaultValue;
                                    if (parameterClientType is AutoRestPrimaryType parameterClientPrimaryType)
                                    {
                                        string modelTypeName = AutoRestIModelTypeName(parameterClientPrimaryType, settings);
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
                                    function.Line($"final {AutoRestIModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                }
                                else if (parameter.IsConstant)
                                {
                                    string defaultValue = parameter.DefaultValue;
                                    if (defaultValue != null && parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        switch (parameterModelPrimaryType.KnownPrimaryType)
                                        {
                                            case AutoRestKnownPrimaryType.Double:
                                                defaultValue = double.Parse(defaultValue).ToString();
                                                break;

                                            case AutoRestKnownPrimaryType.String:
                                                defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                break;

                                            case AutoRestKnownPrimaryType.Boolean:
                                                defaultValue = defaultValue.ToLowerInvariant();
                                                break;

                                            case AutoRestKnownPrimaryType.Long:
                                                defaultValue = defaultValue + 'L';
                                                break;

                                            case AutoRestKnownPrimaryType.Date:
                                                defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.DateTime:
                                            case AutoRestKnownPrimaryType.DateTimeRfc1123:
                                                defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.TimeSpan:
                                                defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.ByteArray:
                                                defaultValue = $"\"{defaultValue}\".getBytes()";
                                                break;
                                        }
                                    }
                                    function.Line($"final {AutoRestIModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                }
                            }

                            foreach (AutoRestParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
                            {
                                AutoRestParameter transformationOutputParameter = transformation.OutputParameter;
                                AutoRestIModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter) && transformationOutputParameterModelType is AutoRestPrimaryType transformationOutputParameterModelPrimaryType)
                                {
                                    AutoRestPrimaryType transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                                    primaryTypeNotWantNullable.Add(transformationOutputParameterModelNonNullablePrimaryType);

                                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                                }
                                AutoRestIModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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

                                string transformationOutputParameterClientParameterVariantTypeName = AutoRestIModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

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

                                AutoRestCompositeType transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as AutoRestCompositeType;
                                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                                {
                                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && innerModelCompositeType.Contains(transformationOutputParameterModelCompositeType))
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

                            foreach (AutoRestParameter parameter in autoRestMethodRetrofitParameters)
                            {
                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                AutoRestIModelType parameterWireType;
                                if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                    parameter.Location != AutoRestParameterLocation.Body &&
                                    parameter.Location != AutoRestParameterLocation.FormData &&
                                    ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
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
                                    AutoRestParameterLocation parameterLocation = parameter.Location;
                                    if (parameterLocation != AutoRestParameterLocation.Body &&
                                        parameterLocation != AutoRestParameterLocation.FormData &&
                                        ((parameterModelType is AutoRestPrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterModelType is AutoRestSequenceType))
                                    {
                                        string parameterWireTypeName = AutoRestIModelTypeName(parameterWireType, settings);

                                        if (parameterClientType is AutoRestPrimaryType primaryClientType && primaryClientType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray)
                                        {
                                            if (parameterWireType.IsPrimaryType(AutoRestKnownPrimaryType.String))
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                            }
                                            else
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                            }
                                            addedConversion = true;
                                        }
                                        else if (parameterClientType is AutoRestSequenceType)
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

                            string restAPIMethodArgumentList = string.Join(", ", autoRestMethodOrderedRetrofitParameters
                                .Select((AutoRestParameter parameter) =>
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
                                    AutoRestIModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                        {
                                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }
                                    AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    AutoRestIModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                    string result;
                                    if (settings.ShouldGenerateXmlSerialization && parameterWireType is AutoRestSequenceType)
                                    {
                                        result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                    }
                                    else
                                    {
                                        result = parameterWireName;
                                    }
                                    return result;
                                }));

                            function.Line($"return service.{clientMethod.RestAPIMethod.Name}({restAPIMethodArgumentList}).map(new {GenericType.Function(restResponseType, pageType)}() {{");
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
                        break;

                    case ClientMethodType.LongRunningSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (requiredNullableParameterExpressions.Any())
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
                                function.Line($"{GetLongRunningAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingLast();");
                            }
                            else
                            {
                                function.Return($"{GetLongRunningAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingLast().result()");
                            }
                        });
                        break;

                    case ClientMethodType.LongRunningAsyncServiceCallback:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Return($"ServiceFutureUtil.fromLRO({GetLongRunningAsyncMethodName(clientMethod)}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                        });
                        break;

                    case ClientMethodType.LongRunningAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            if (requiredNullableParameterExpressions.Any() || clientMethod.ExpressionsToValidate.Any())
                            {
                                comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            }
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            foreach (string requiredNullableParameterExpression in requiredNullableParameterExpressions)
                            {
                                function.If($"{requiredNullableParameterExpression} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {requiredNullableParameterExpression} is required and cannot be null.\");");
                                });
                            }

                            foreach (string expressionToValidate in clientMethod.ExpressionsToValidate)
                            {
                                function.Line($"Validator.validate({expressionToValidate});");
                            }

                            foreach (AutoRestParameter parameter in autoRestClientMethodAndConstantParameters)
                            {
                                string parameterName = parameter.Name;

                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                if (clientMethod.OnlyRequiredParameters && !parameter.IsRequired)
                                {
                                    string parameterDefaultValue;
                                    if (parameterClientType is AutoRestPrimaryType parameterClientPrimaryType)
                                    {
                                        string modelTypeName = AutoRestIModelTypeName(parameterClientPrimaryType, settings);
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
                                    function.Line($"final {AutoRestIModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                }
                                else if (parameter.IsConstant)
                                {
                                    string defaultValue = parameter.DefaultValue;
                                    if (defaultValue != null && parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        switch (parameterModelPrimaryType.KnownPrimaryType)
                                        {
                                            case AutoRestKnownPrimaryType.Double:
                                                defaultValue = double.Parse(defaultValue).ToString();
                                                break;

                                            case AutoRestKnownPrimaryType.String:
                                                defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                break;

                                            case AutoRestKnownPrimaryType.Boolean:
                                                defaultValue = defaultValue.ToLowerInvariant();
                                                break;

                                            case AutoRestKnownPrimaryType.Long:
                                                defaultValue = defaultValue + 'L';
                                                break;

                                            case AutoRestKnownPrimaryType.Date:
                                                defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.DateTime:
                                            case AutoRestKnownPrimaryType.DateTimeRfc1123:
                                                defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.TimeSpan:
                                                defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.ByteArray:
                                                defaultValue = $"\"{defaultValue}\".getBytes()";
                                                break;
                                        }
                                    }
                                    function.Line($"final {AutoRestIModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                }
                            }

                            foreach (AutoRestParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
                            {
                                AutoRestParameter transformationOutputParameter = transformation.OutputParameter;
                                AutoRestIModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter) && transformationOutputParameterModelType is AutoRestPrimaryType transformationOutputParameterModelPrimaryType)
                                {
                                    AutoRestPrimaryType transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                                    primaryTypeNotWantNullable.Add(transformationOutputParameterModelNonNullablePrimaryType);

                                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                                }
                                AutoRestIModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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

                                string transformationOutputParameterClientParameterVariantTypeName = AutoRestIModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

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

                                AutoRestCompositeType transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as AutoRestCompositeType;
                                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                                {
                                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && innerModelCompositeType.Contains(transformationOutputParameterModelCompositeType))
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

                            foreach (AutoRestParameter parameter in autoRestMethodRetrofitParameters)
                            {
                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                AutoRestIModelType parameterWireType;
                                if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                    parameter.Location != AutoRestParameterLocation.Body &&
                                    parameter.Location != AutoRestParameterLocation.FormData &&
                                    ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
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
                                    AutoRestParameterLocation parameterLocation = parameter.Location;
                                    if (parameterLocation != AutoRestParameterLocation.Body &&
                                        parameterLocation != AutoRestParameterLocation.FormData &&
                                        ((parameterModelType is AutoRestPrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterModelType is AutoRestSequenceType))
                                    {
                                        string parameterWireTypeName = AutoRestIModelTypeName(parameterWireType, settings);

                                        if (parameterClientType is AutoRestPrimaryType primaryClientType && primaryClientType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray)
                                        {
                                            if (parameterWireType.IsPrimaryType(AutoRestKnownPrimaryType.String))
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                            }
                                            else
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                            }
                                            addedConversion = true;
                                        }
                                        else if (parameterClientType is AutoRestSequenceType)
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

                            string restAPIMethodArgumentList = string.Join(", ", autoRestMethodOrderedRetrofitParameters
                                .Select((AutoRestParameter parameter) =>
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
                                    AutoRestIModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                        {
                                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }
                                    AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    AutoRestIModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                    string result;
                                    if (settings.ShouldGenerateXmlSerialization && parameterWireType is AutoRestSequenceType)
                                    {
                                        result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                    }
                                    else
                                    {
                                        result = parameterWireName;
                                    }
                                    return result;
                                }));

                            function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        });
                        break;

                    case ClientMethodType.SimpleSync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                                function.Return($"{GetSimpleAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingGet()");
                            }
                            else if (isFluentDelete)
                            {
                                function.Line($"{GetSimpleAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingGet();");
                            }
                            else
                            {
                                function.Line($"{GetSimpleAsyncMethodName(clientMethod)}({clientMethod.ArgumentList}).blockingAwait();");
                            }
                        });
                        break;

                    case ClientMethodType.SimpleAsyncServiceCallback:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
                            comment.Return(clientMethod.ReturnValue.Description);
                        });
                        typeBlock.PublicMethod(clientMethod.Declaration, function =>
                        {
                            function.Return($"ServiceFuture.fromBody({GetSimpleAsyncMethodName(clientMethod)}({string.Join(", ", clientMethod.Parameters.SkipLast(1).Select(parameter => parameter.Name))}), {serviceCallbackParameter.Name})");
                        });
                        break;

                    case ClientMethodType.SimpleAsyncRestResponse:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                            foreach (string requiredNullableParameterExpression in requiredNullableParameterExpressions)
                            {
                                function.If($"{requiredNullableParameterExpression} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {requiredNullableParameterExpression} is required and cannot be null.\");");
                                });
                            }

                            foreach (AutoRestParameter parameter in autoRestClientMethodAndConstantParameters)
                            {
                                string parameterName = parameter.Name;

                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                if (clientMethod.OnlyRequiredParameters && !parameter.IsRequired)
                                {
                                    string parameterDefaultValue;
                                    if (parameterClientType is AutoRestPrimaryType parameterClientPrimaryType)
                                    {
                                        string modelTypeName = AutoRestIModelTypeName(parameterClientPrimaryType, settings);
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
                                    function.Line($"final {AutoRestIModelTypeName(parameterClientType, settings)} {parameterName} = {parameterDefaultValue ?? "null"};");
                                }
                                else if (parameter.IsConstant)
                                {
                                    string defaultValue = parameter.DefaultValue;
                                    if (defaultValue != null && parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        switch (parameterModelPrimaryType.KnownPrimaryType)
                                        {
                                            case AutoRestKnownPrimaryType.Double:
                                                defaultValue = double.Parse(defaultValue).ToString();
                                                break;

                                            case AutoRestKnownPrimaryType.String:
                                                defaultValue = CodeNamer.Instance.QuoteValue(defaultValue);
                                                break;

                                            case AutoRestKnownPrimaryType.Boolean:
                                                defaultValue = defaultValue.ToLowerInvariant();
                                                break;

                                            case AutoRestKnownPrimaryType.Long:
                                                defaultValue = defaultValue + 'L';
                                                break;

                                            case AutoRestKnownPrimaryType.Date:
                                                defaultValue = $"LocalDate.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.DateTime:
                                            case AutoRestKnownPrimaryType.DateTimeRfc1123:
                                                defaultValue = $"DateTime.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.TimeSpan:
                                                defaultValue = $"Period.parse(\"{defaultValue}\")";
                                                break;

                                            case AutoRestKnownPrimaryType.ByteArray:
                                                defaultValue = $"\"{defaultValue}\".getBytes()";
                                                break;
                                        }
                                    }
                                    function.Line($"final {AutoRestIModelTypeName(ConvertToClientType(parameterClientType), settings)} {parameterName} = {defaultValue ?? "null"};");
                                }
                            }

                            foreach (string expressionToValidate in clientMethod.ExpressionsToValidate)
                            {
                                function.Line($"Validator.validate({expressionToValidate});");
                            }

                            foreach (AutoRestParameterTransformation transformation in autoRestMethod.InputParameterTransformation)
                            {
                                AutoRestParameter transformationOutputParameter = transformation.OutputParameter;
                                AutoRestIModelType transformationOutputParameterModelType = transformationOutputParameter.ModelType;
                                if (transformationOutputParameterModelType != null && !IsNullable(transformationOutputParameter) && transformationOutputParameterModelType is AutoRestPrimaryType transformationOutputParameterModelPrimaryType)
                                {
                                    AutoRestPrimaryType transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<AutoRestPrimaryType>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                                    transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                                    primaryTypeNotWantNullable.Add(transformationOutputParameterModelNonNullablePrimaryType);

                                    transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                                }
                                AutoRestIModelType transformationOutputParameterClientType = ConvertToClientType(transformationOutputParameterModelType);

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

                                string transformationOutputParameterClientParameterVariantTypeName = AutoRestIModelTypeName(ConvertToClientType(transformationOutputParameterClientType), settings);

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

                                AutoRestCompositeType transformationOutputParameterModelCompositeType = transformationOutputParameterModelType as AutoRestCompositeType;
                                if (transformationOutputParameterModelCompositeType != null && transformationParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)))
                                {
                                    string transformationOutputParameterModelCompositeTypeName = transformationOutputParameterModelCompositeType.Name.ToString();
                                    if (settings.IsFluent && !string.IsNullOrEmpty(transformationOutputParameterModelCompositeTypeName) && innerModelCompositeType.Contains(transformationOutputParameterModelCompositeType))
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

                            foreach (AutoRestParameter parameter in autoRestMethodRetrofitParameters)
                            {
                                AutoRestIModelType parameterModelType = parameter.ModelType;
                                if (parameterModelType != null && !IsNullable(parameter))
                                {
                                    if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                    {
                                        AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                        nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                        primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                        parameterModelType = nonNullableParameterModelPrimaryType;
                                    }
                                }
                                AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                AutoRestIModelType parameterWireType;
                                if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                {
                                    parameterWireType = parameterClientType;
                                }
                                else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                    parameter.Location != AutoRestParameterLocation.Body &&
                                    parameter.Location != AutoRestParameterLocation.FormData &&
                                    ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                {
                                    parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
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
                                    AutoRestParameterLocation parameterLocation = parameter.Location;
                                    if (parameterLocation != AutoRestParameterLocation.Body &&
                                        parameterLocation != AutoRestParameterLocation.FormData &&
                                        ((parameterModelType is AutoRestPrimaryType parameterModelPrimaryType && parameterModelPrimaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterModelType is AutoRestSequenceType))
                                    {
                                        string parameterWireTypeName = AutoRestIModelTypeName(parameterWireType, settings);

                                        if (parameterClientType is AutoRestPrimaryType primaryClientType && primaryClientType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray)
                                        {
                                            if (parameterWireType.IsPrimaryType(AutoRestKnownPrimaryType.String))
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64.encodeBase64String({parameterName});");
                                            }
                                            else
                                            {
                                                function.Line($"{parameterWireTypeName} {parameterWireName} = Base64Url.encode({parameterName});");
                                            }
                                            addedConversion = true;
                                        }
                                        else if (parameterClientType is AutoRestSequenceType)
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

                            string restAPIMethodArgumentList = string.Join(", ", autoRestMethodOrderedRetrofitParameters
                                .Select((AutoRestParameter parameter) =>
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
                                    AutoRestIModelType parameterModelType = parameter.ModelType;
                                    if (parameterModelType != null && !IsNullable(parameter))
                                    {
                                        if (parameterModelType is AutoRestPrimaryType parameterModelPrimaryType)
                                        {
                                            AutoRestPrimaryType nonNullableParameterModelPrimaryType = DependencyInjection.New<AutoRestPrimaryType>(parameterModelPrimaryType.KnownPrimaryType);
                                            nonNullableParameterModelPrimaryType.Format = parameterModelPrimaryType.Format;
                                            primaryTypeNotWantNullable.Add(nonNullableParameterModelPrimaryType);

                                            parameterModelType = nonNullableParameterModelPrimaryType;
                                        }
                                    }
                                    AutoRestIModelType parameterClientType = ConvertToClientType(parameterModelType);

                                    AutoRestIModelType parameterWireType;
                                    if (parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Stream))
                                    {
                                        parameterWireType = parameterClientType;
                                    }
                                    else if (!parameterModelType.IsPrimaryType(AutoRestKnownPrimaryType.Base64Url) &&
                                        parameter.Location != AutoRestParameterLocation.Body &&
                                        parameter.Location != AutoRestParameterLocation.FormData &&
                                        ((parameterClientType is AutoRestPrimaryType primaryType && primaryType.KnownPrimaryType == AutoRestKnownPrimaryType.ByteArray) || parameterClientType is AutoRestSequenceType))
                                    {
                                        parameterWireType = DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.String);
                                    }
                                    else
                                    {
                                        parameterWireType = parameterModelType;
                                    }

                                    string parameterWireName = !parameterClientType.StructurallyEquals(parameterWireType) ? $"{parameterName.ToCamelCase()}Converted" : parameterName;

                                    string result;
                                    if (settings.ShouldGenerateXmlSerialization && parameterWireType is AutoRestSequenceType)
                                    {
                                        result = $"new {parameterWireType.XmlName}Wrapper({parameterWireName})";
                                    }
                                    else
                                    {
                                        result = parameterWireName;
                                    }
                                    return result;
                                }));

                            function.Return($"service.{restAPIMethod.Name}({restAPIMethodArgumentList})");
                        });
                        break;

                    case ClientMethodType.SimpleAsync:
                        typeBlock.JavadocComment(comment =>
                        {
                            comment.Description(clientMethod.Description);
                            foreach (Parameter parameter in clientMethod.Parameters)
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
                            function.Line($"return {GetSimpleAsyncRestResponseMethodName(clientMethod)}({clientMethod.ArgumentList})");
                            function.Indent(() =>
                            {
                                if (restAPIMethodReturnBodyClientType != PrimitiveType.Void)
                                {
                                    function.Line($".flatMapMaybe(new {GenericType.Function(restResponseType, clientMethod.ReturnValue.Type)}() {{");
                                    function.Indent(() =>
                                    {
                                        function.Block($"public {clientMethod.ReturnValue.Type} apply({restResponseType} restResponse)", subFunction =>
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
                                else if (isFluentDelete)
                                {
                                    function.Line($".flatMapMaybe(new {GenericType.Function(restResponseType, clientMethod.ReturnValue.Type)}() {{");
                                    function.Indent(() =>
                                    {
                                        function.Block($"public {clientMethod.ReturnValue.Type} apply({restResponseType} restResponse)", subFunction =>
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
                            });
                        });
                        break;

                    default:
                        throw new ArgumentException($"There is no method implementation for {nameof(ClientMethodType)}.{clientMethod.Type}.");
                }
            }
        }

        private static IEnumerable<string> GetExpressionsToValidate(RestAPIMethod restAPIMethod, bool onlyRequiredParameters, JavaSettings settings)
        {
            AutoRestMethod autoRestMethod = restAPIMethod.AutoRestMethod;

            List<string> expressionsToValidate = new List<string>();
            foreach (AutoRestParameter autoRestParameter in autoRestMethod.Parameters)
            {
                if (!autoRestParameter.IsConstant)
                {
                    IType parameterType = ParseType(autoRestParameter.ModelType, settings);
                    if (IsNullable(autoRestParameter))
                    {
                        parameterType = parameterType.AsNullable();
                    }

                    if (!(parameterType is PrimitiveType) &&
                        !(parameterType is EnumType) &&
                        parameterType != ClassType.Object &&
                        parameterType != ClassType.Integer &&
                        parameterType != ClassType.Long &&
                        parameterType != ClassType.Double &&
                        parameterType != ClassType.BigDecimal &&
                        parameterType != ClassType.String &&
                        parameterType != ClassType.DateTime &&
                        parameterType != ClassType.LocalDate &&
                        parameterType != ClassType.DateTimeRfc1123 &&
                        parameterType != ClassType.Period &&
                        parameterType != ClassType.Boolean &&
                        parameterType != ClassType.ServiceClientCredentials &&
                        parameterType != ClassType.AzureTokenCredentials &&
                        parameterType != ClassType.UUID &&
                        parameterType != ClassType.Base64Url &&
                        parameterType != ClassType.UnixTime &&
                        parameterType != ClassType.UnixTimeDateTime &&
                        parameterType != ClassType.UnixTimeLong &&
                        parameterType != ArrayType.ByteArray &&
                        parameterType != GenericType.FlowableByteBuffer &&
                        (!onlyRequiredParameters || autoRestParameter.IsRequired))
                    {
                        string parameterExpressionToValidate;
                        if (!autoRestParameter.IsClientProperty)
                        {
                            parameterExpressionToValidate = autoRestParameter.Name;
                        }
                        else
                        {
                            string caller = (autoRestParameter.Method != null && autoRestParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = autoRestParameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            parameterExpressionToValidate = $"{caller}.{clientPropertyName}()";
                        }

                        expressionsToValidate.Add(parameterExpressionToValidate);
                    }
                }
            }
            return expressionsToValidate;
        }

        private static IEnumerable<ClientMethod> ParseClientMethods(RestAPI restAPI, JavaSettings settings)
        {
            List<ClientMethod> clientMethods = new List<ClientMethod>();

            foreach (RestAPIMethod restAPIMethod in restAPI.Methods)
            {
                IEnumerable<AutoRestParameter> autoRestClientMethodAndConstantParameters = restAPIMethod.AutoRestMethod.Parameters
                    //Omit parameter-group properties for now since Java doesn't support them yet
                    .Where((AutoRestParameter autoRestParameter) => autoRestParameter != null && !autoRestParameter.IsClientProperty && !string.IsNullOrWhiteSpace(autoRestParameter.Name))
                    .OrderBy(item => !item.IsRequired);
                IEnumerable<AutoRestParameter> autoRestClientMethodParameters = autoRestClientMethodAndConstantParameters
                    .Where((AutoRestParameter autoRestParameter) => !autoRestParameter.IsConstant)
                    .OrderBy((AutoRestParameter autoRestParameter) => !autoRestParameter.IsRequired);
                IEnumerable<AutoRestParameter> autoRestRequiredClientMethodParameters = autoRestClientMethodParameters
                    .Where(parameter => parameter.IsRequired);

                AutoRestResponse autoRestRestAPIMethodReturnType = restAPIMethod.AutoRestMethod.ReturnType;
                AutoRestIModelType autoRestRestAPIMethodReturnBodyType = autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<AutoRestPrimaryType>(AutoRestKnownPrimaryType.None);

                IType restAPIMethodReturnBodyClientType = ConvertToClientType(ParseType(autoRestRestAPIMethodReturnBodyType, settings));

                GenericType pageImplType = null;
                IType deserializedResponseBodyType;
                IType pageType;

                if (settings.IsAzureOrFluent &&
                    restAPIMethodReturnBodyClientType is ListType restAPIMethodReturnBodyClientListType &&
                    (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation || restAPIMethod.SimulateAsPagingOperation))
                {
                    IType restAPIMethodReturnBodyClientListElementType = restAPIMethodReturnBodyClientListType.ElementType;

                    restAPIMethodReturnBodyClientType = GenericType.PagedList(restAPIMethodReturnBodyClientListElementType);

                    string pageImplTypeName = SequenceTypeGetPageImplType(autoRestRestAPIMethodReturnBodyType);

                    string pageImplSubPackage = settings.IsFluent ? implPackage : modelsPackage;
                    string pageImplPackage = $"{settings.Package}.{pageImplSubPackage}";

                    pageImplType = new GenericType(pageImplPackage, pageImplTypeName, restAPIMethodReturnBodyClientListElementType);
                    deserializedResponseBodyType = pageImplType;

                    pageType = GenericType.Page(restAPIMethodReturnBodyClientListElementType);
                }
                else
                {
                    deserializedResponseBodyType = restAPIMethodReturnBodyClientType;

                    pageType = restAPIMethodReturnBodyClientType.AsNullable();
                }

                Parameter serviceCallbackParameter = new Parameter(
                    description: "the async ServiceCallback to handle successful and failed responses.",
                    isFinal: false,
                    type: GenericType.ServiceCallback(restAPIMethodReturnBodyClientType),
                    name: "serviceCallback",
                    isRequired: true);

                GenericType serviceFutureReturnType = GenericType.ServiceFuture(restAPIMethodReturnBodyClientType);

                GenericType observablePageType = GenericType.Observable(pageType);

                List<IEnumerable<AutoRestParameter>> autoRestParameterLists = new List<IEnumerable<AutoRestParameter>>()
                {
                    autoRestClientMethodParameters
                };
                if (autoRestClientMethodParameters.Any(parameter => !parameter.IsRequired))
                {
                    autoRestParameterLists.Insert(0, autoRestRequiredClientMethodParameters);
                }

                bool addSimpleClientMethods = true;

                if (settings.IsAzureOrFluent)
                {
                    if (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation)
                    {
                        foreach (IEnumerable<AutoRestParameter> autoRestParameters in autoRestParameterLists)
                        {
                            bool onlyRequiredParameters = (autoRestParameters == autoRestRequiredClientMethodParameters);

                            IEnumerable<string> expressionsToValidate = GetExpressionsToValidate(restAPIMethod, onlyRequiredParameters, settings);

                            List<Parameter> parameters = new List<Parameter>();
                            foreach (AutoRestParameter autoRestParameter in autoRestParameters)
                            {
                                IType parameterType = ParseType(autoRestParameter.ModelType, settings);
                                if (IsNullable(autoRestParameter))
                                {
                                    parameterType = parameterType.AsNullable();
                                }

                                string parameterDescription = autoRestParameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDescription))
                                {
                                    parameterDescription = $"the {parameterType} value";
                                }

                                parameters.Add(new Parameter(
                                    description: parameterDescription,
                                    isFinal: true,
                                    type: parameterType,
                                    name: autoRestParameter.Name,
                                    isRequired: autoRestParameter.IsRequired));
                            }

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                    type: restAPIMethodReturnBodyClientType),
                                name: restAPIMethod.Name,
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.PagingSync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? $"the {{@link {observablePageType}}} object if successful." : $"the observable to the {restAPIMethodReturnBodyClientType} object",
                                    type: observablePageType),
                                name: GetPagingAsyncMethodName(restAPIMethod),
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.PagingAsync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));

                            GenericType singlePageMethodReturnType = GenericType.Single(pageType);
                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: $"the {{@link {singlePageMethodReturnType}}} object if successful.",
                                    type: singlePageMethodReturnType),
                                name: GetPagingAsyncSinglePageMethodName(restAPIMethod),
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.PagingAsyncSinglePage,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));
                        }

                        addSimpleClientMethods = false;
                    }
                    else if (restAPIMethod.SimulateAsPagingOperation)
                    {
                        foreach (IEnumerable<AutoRestParameter> autoRestParameters in autoRestParameterLists)
                        {
                            bool onlyRequiredParameters = (autoRestParameters == autoRestRequiredClientMethodParameters);

                            IEnumerable<string> expressionsToValidate = GetExpressionsToValidate(restAPIMethod, onlyRequiredParameters, settings);

                            List<Parameter> parameters = new List<Parameter>();
                            foreach (AutoRestParameter autoRestParameter in autoRestParameters)
                            {
                                IType parameterType = ParseType(autoRestParameter.ModelType, settings);
                                if (IsNullable(autoRestParameter))
                                {
                                    parameterType = parameterType.AsNullable();
                                }

                                parameters.Add(new Parameter(
                                    description: $"the {parameterType} value",
                                    isFinal: false,
                                    type: parameterType,
                                    name: autoRestParameter.Name,
                                    isRequired: autoRestParameter.IsRequired));
                            }

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                    type: GenericType.PagedList(restAPIMethodReturnBodyClientType)),
                                name: restAPIMethod.Name,
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.SimulatedPagingSync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? $"the {{@link {observablePageType}}} object if successful." : $"the observable to the {restAPIMethodReturnBodyClientType} object",
                                    type: GenericType.Observable(GenericType.Page(restAPIMethodReturnBodyClientType))),
                                name: GetSimulatedPagingAsyncMethodName(restAPIMethod),
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.SimulatedPagingAsync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));
                        }

                        addSimpleClientMethods = false;
                    }
                    else if (restAPIMethod.IsLongRunningOperation)
                    {
                        foreach (IEnumerable<AutoRestParameter> autoRestParameters in autoRestParameterLists)
                        {
                            bool onlyRequiredParameters = (autoRestParameters == autoRestRequiredClientMethodParameters);

                            IEnumerable<string> expressionsToValidate = GetExpressionsToValidate(restAPIMethod, onlyRequiredParameters, settings);

                            List<Parameter> parameters = new List<Parameter>();
                            foreach (AutoRestParameter autoRestParameter in autoRestParameters)
                            {
                                IType parameterType = ParseType(autoRestParameter.ModelType, settings);
                                if (IsNullable(autoRestParameter))
                                {
                                    parameterType = parameterType.AsNullable();
                                }

                                string parameterDescription = autoRestParameter.Documentation;
                                if (string.IsNullOrEmpty(parameterDescription))
                                {
                                    parameterDescription = $"the {parameterType} value";
                                }

                                parameters.Add(new Parameter(
                                    description: parameterDescription,
                                    isFinal: false,
                                    type: parameterType,
                                    name: autoRestParameter.Name,
                                    isRequired: autoRestParameter.IsRequired));
                            }

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                    type: restAPIMethodReturnBodyClientType),
                                name: restAPIMethod.Name,
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.LongRunningSync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: $"the {{@link {serviceFutureReturnType}}} object",
                                    type: serviceFutureReturnType),
                                name: GetLongRunningAsyncMethodName(restAPIMethod),
                                parameters: parameters.ConcatSingleItem(serviceCallbackParameter),
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.LongRunningAsyncServiceCallback,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));

                            clientMethods.Add(new ClientMethod(
                                description: restAPIMethod.Description,
                                returnValue: new ReturnValue(
                                    description: "the observable for the request",
                                    type: GenericType.Observable(GenericType.OperationStatus(restAPIMethodReturnBodyClientType))),
                                name: GetLongRunningAsyncMethodName(restAPIMethod),
                                parameters: parameters,
                                onlyRequiredParameters: onlyRequiredParameters,
                                type: ClientMethodType.LongRunningAsync,
                                restAPIMethod: restAPIMethod,
                                expressionsToValidate: expressionsToValidate));
                        }

                        addSimpleClientMethods = false;
                    }
                }

                if (addSimpleClientMethods)
                {
                    bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase(Delete) && autoRestRequiredClientMethodParameters.Count() == 2;

                    foreach (IEnumerable<AutoRestParameter> autoRestParameters in autoRestParameterLists)
                    {
                        bool onlyRequiredParameters = (autoRestParameters == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GetExpressionsToValidate(restAPIMethod, onlyRequiredParameters, settings);

                        List<Parameter> parameters = new List<Parameter>();
                        foreach (AutoRestParameter autoRestParameter in autoRestParameters)
                        {
                            IType parameterType = ConvertToClientType(ParseType(autoRestParameter.ModelType, settings));
                            if (IsNullable(autoRestParameter))
                            {
                                parameterType = parameterType.AsNullable();
                            }

                            string parameterDescription = autoRestParameter.Documentation;
                            if (string.IsNullOrEmpty(parameterDescription))
                            {
                                parameterDescription = $"the {parameterType} value";
                            }

                            parameters.Add(new Parameter(
                                description: parameterDescription,
                                isFinal: false,
                                type: parameterType,
                                name: autoRestParameter.Name,
                                isRequired: autoRestParameter.IsRequired));
                        }

                        clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                type: restAPIMethodReturnBodyClientType),
                            name: restAPIMethod.Name,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimpleSync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate));

                        clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: $"the {{@link {serviceFutureReturnType}}} object",
                                type: serviceFutureReturnType),
                            name: GetSimpleAsyncMethodName(restAPIMethod),
                            parameters: parameters.ConcatSingleItem(serviceCallbackParameter),
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimpleAsyncServiceCallback,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate));

                        GenericType singleRestResponseReturnType = GenericType.Single(GenericType.RestResponse(
                            headersType: ConvertToClientType(ParseType(autoRestRestAPIMethodReturnType.Headers, settings)),
                            bodyType: deserializedResponseBodyType));
                        clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: $"the {{@link {singleRestResponseReturnType}}} object if successful.",
                                type: singleRestResponseReturnType),
                            name: GetSimpleAsyncRestResponseMethodName(restAPIMethod),
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimpleAsyncRestResponse,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate));

                        IType asyncMethodReturnType;
                        if (restAPIMethodReturnBodyClientType != PrimitiveType.Void)
                        {
                            asyncMethodReturnType = GenericType.Maybe(restAPIMethodReturnBodyClientType);
                        }
                        else if (isFluentDelete)
                        {
                            asyncMethodReturnType = GenericType.Maybe(ClassType.Void);
                        }
                        else
                        {
                            asyncMethodReturnType = ClassType.Completable;
                        }
                        clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: $"the {{@link {asyncMethodReturnType}}} object if successful.",
                                type: asyncMethodReturnType),
                            name: GetSimpleAsyncMethodName(restAPIMethod),
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimpleAsync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate));
                    }
                }
            }

            return clientMethods;
        }

        private static string GetPagingAsyncMethodName(RestAPIMethod restAPIMethod)
        {
            return restAPIMethod.Name + "Async";
        }

        private static string GetPagingAsyncSinglePageMethodName(ClientMethod clientMethod)
        {
            return GetPagingAsyncSinglePageMethodName(clientMethod.RestAPIMethod);
        }

        private static string GetPagingAsyncSinglePageMethodName(RestAPIMethod restAPIMethod)
        {
            return GetPagingAsyncSinglePageMethodName(restAPIMethod.Name);
        }

        private static string GetPagingAsyncSinglePageMethodName(string restAPIMethodName)
        {
            return restAPIMethodName + "SinglePageAsync";
        }

        private static string GetSimulatedPagingAsyncMethodName(ClientMethod clientMethod)
        {
            return GetSimulatedPagingAsyncMethodName(clientMethod.RestAPIMethod);
        }

        private static string GetSimulatedPagingAsyncMethodName(RestAPIMethod restAPIMethod)
        {
            return restAPIMethod.Name + "Async";
        }

        private static string GetLongRunningAsyncMethodName(ClientMethod clientMethod)
        {
            return GetLongRunningAsyncMethodName(clientMethod.RestAPIMethod);
        }

        private static string GetLongRunningAsyncMethodName(RestAPIMethod restAPIMethod)
        {
            return restAPIMethod.Name + "Async";
        }

        private static string GetSimpleAsyncMethodName(ClientMethod clientMethod)
        {
            return GetSimpleAsyncMethodName(clientMethod.RestAPIMethod);
        }

        private static string GetSimpleAsyncMethodName(RestAPIMethod restAPIMethod)
        {
            return restAPIMethod.Name + "Async";
        }

        private static string GetSimpleAsyncRestResponseMethodName(ClientMethod clientMethod)
        {
            return GetSimpleAsyncRestResponseMethodName(clientMethod.RestAPIMethod);
        }

        private static string GetSimpleAsyncRestResponseMethodName(RestAPIMethod restAPIMethod)
        {
            return restAPIMethod.Name + "WithRestResponseAsync";
        }
    }
}
