using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure;
using AutoRest.Java.Azure.Fluent;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using Newtonsoft.Json.Linq;
using System;
using System.Collections.Generic;
using System.Collections.Immutable;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;

namespace AutoRest.Java.DanModel
{
    public static class DanCodeGenerator
    {
        public const string targetVersion = "1.1.3";
        public const string pomVersion = targetVersion + "-SNAPSHOT";

        private const string httpPipelineImport = "com.microsoft.rest.v2.http." + httpPipelineType;
        private const string httpPipelineDescription = "The HTTP pipeline to send requests through.";
        private const string httpPipelineType = "HttpPipeline";
        private const string httpPipelineVariableName = "httpPipeline";

        private const string restProxyImport = "com.microsoft.rest.v2." + restProxyType;
        private const string restProxyType = "RestProxy";

        private const string serializerImport = "com.microsoft.rest.v2.protocol.SerializerAdapter";

        private const string azureEnvironmentImport = "com.microsoft.azure.v2." + azureEnvironmentType;
        private const string azureEnvironmentType = "AzureEnvironment";
        private const string azureEnvironemntVariableName = "azureEnvironment";
        private const string azureEnvironmentDescription = "The environment that requests will target.";

        private const string azureProxyImport = "com.microsoft.azure.v2." + azureProxyType;
        private const string azureProxyType = "AzureProxy";

        private const string implPackage = "implementation";
        internal const string modelsPackage = ".models";

        public static readonly IDictionary<KeyValuePair<string, string>, string> pageClasses = new Dictionary<KeyValuePair<string, string>, string>();

        public static readonly ISet<Property> innerModelProperties = new HashSet<Property>();
        public static readonly ISet<CompositeType> innerModelCompositeType = new HashSet<CompositeType>();

        public static readonly ISet<SequenceType> pagedListTypes = new HashSet<SequenceType>();

        private static readonly IDictionary<IModelType, string> pageImplTypes = new Dictionary<IModelType, string>();

        private static readonly IDictionary<Response, Method> responseParents = new Dictionary<Response, Method>();

        public static string BetaSinceVersion()
        {
            string[] versionParts = targetVersion.Split('.');
            int minorVersion = int.Parse(versionParts[1]);
            int patchVersion = int.Parse(versionParts[2]);

            int newMinorVersion = patchVersion == 0
                ? minorVersion
                : minorVersion + 1;

            string result = "V" + versionParts[0] + "_" + newMinorVersion + "_0";
            return result;
        }

        public static IEnumerable<JavaFile> GetOperationJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> result = new List<JavaFile>();

            bool isFluent = IsFluent(settings);
            bool isAzure = IsAzure(settings);

            foreach (MethodGroupJv methodGroup in GetAllOperations(codeModel))
            {
                if (isFluent || isAzure)
                {
                    result.Add(GetAzureMethodGroupJavaFile(codeModel, settings, methodGroup as MethodGroupJva));
                }
                else
                {
                    result.Add(GetMethodGroupJavaFile(codeModel, settings, methodGroup));
                }

                if (!isFluent)
                {
                    result.Add(GetMethodGroupInterfaceJavaFile(codeModel, settings, methodGroup));
                }
            }

            return result;
        }

        public static JavaFile GetAzureServiceManagerJavaFile(CodeModel codeModel, Settings settings)
        {
            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            string serviceName = GetServiceName(settings, codeModel);
            if (string.IsNullOrEmpty(serviceName))
            {
                serviceName = "MissingServiceName";
            }
            string className = $"{serviceName}Manager";

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);

            javaFile.Import(
                "com.microsoft.azure.management.apigeneration.Beta",
                "com.microsoft.azure.management.apigeneration.Beta.SinceVersion",
                "com.microsoft.azure.management.resources.fluentcore.arm.AzureConfigurable",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.AzureConfigurableImpl",
                "com.microsoft.azure.management.resources.fluentcore.arm.implementation.Manager",
                "com.microsoft.azure.v2.AzureEnvironment",
                "com.microsoft.azure.v2.credentials.AzureTokenCredentials",
                "com.microsoft.azure.v2.serializer.AzureJacksonAdapter");

            javaFile.MultipleLineComment(comment =>
            {
                comment.Line($"Entry point to Azure {serviceName} resource management.");
            });
            javaFile.Annotation($"Beta(SinceVersion.{BetaSinceVersion()})");
            javaFile.Block($"public final class {className} extends Manager<{className}, {codeModel.Name + "Impl"}>", classBlock =>
            {
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Get a Configurable instance that can be used to create {className} with optional configuration.");
                    comment.Line();
                    comment.Return("the instance allowing configurations");
                });
                classBlock.Block("public static Configurable configure()", function =>
                {
                    function.Return($"new {className}.ConfigurableImpl()");
                });
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Creates an instance of {className} that exposes {serviceName} resource management API entry points.");
                    comment.Line();
                    comment.Param("credentials", "the credentials to use");
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.Block($"public static {className} authenticate(AzureTokenCredentials credentials, String subscriptionId)", function =>
                {
                    function.Line($"final {httpPipelineType} {httpPipelineVariableName} = AzureProxy.defaultPipeline({className}.class, credentials);");
                    function.Return($"new {className}({httpPipelineVariableName}, subscriptionId)");
                });
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Creates an instance of {className} that exposes {serviceName} resource management API entry points.");
                    comment.Line();
                    comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    comment.Param("subscriptionId", "the subscription UUID");
                    comment.Return($"the {className}");
                });
                classBlock.Block($"public static {className} authenticate({httpPipelineType} {httpPipelineVariableName}, String subscriptionId)", function =>
                {
                    function.Return($"new {className}({httpPipelineVariableName}, subscriptionId)");
                });
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line("The interface allowing configurations to be set.");
                });
                classBlock.Block("public interface Configurable extends AzureConfigurable<Configurable>", interfaceBlock =>
                {
                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"Creates an instance of {className} that exposes {serviceName} management API entry points.");
                        comment.Line();
                        comment.Param("credentials", "the credentials to use");
                        comment.Param("subscriptionId", "the subscription UUID");
                        comment.Return($"the interface exposing {serviceName} management API entry points that work across subscriptions");
                    });
                    interfaceBlock.Line($"{className} authenticate(AzureTokenCredentials credentials, String subscriptionId);");
                });
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line("The implementation for Configurable interface.");
                });
                classBlock.Block("private static final class ConfigurableImpl extends AzureConfigurableImpl<Configurable> implements Configurable", innerClass =>
                {
                    innerClass.Block($"public {className} authenticate(AzureTokenCredentials credentials, String subscriptionId)", function =>
                    {
                        function.Return($"{className}.authenticate(build{httpPipelineType}(credentials), subscriptionId)");
                    });
                });
                classBlock.Line();
                classBlock.Block($"private {className}({httpPipelineType} {httpPipelineVariableName}, String subscriptionId)", constructor =>
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

        public static IEnumerable<JavaFile> GetPageJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> result = new List<JavaFile>();

            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            foreach (KeyValuePair<KeyValuePair<string, string>, string> pageClass in pageClasses)
            {
                string nextLinkName = pageClass.Key.Key;
                string itemName = pageClass.Key.Value;

                string className = pageClass.Value.ToPascalCase();

                string subPackage = (IsFluent(settings) ? implPackage : modelsPackage);
                JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, subPackage, settings, className);
                javaFile.Import("com.fasterxml.jackson.annotation.JsonProperty",
                                "com.microsoft.azure.v2.Page",
                                "java.util.List");

                javaFile.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
                {
                    comment.Line("An instance of this class defines a page of Azure resources and a link to get the next page of resources, if any.");
                    comment.Line();
                    comment.Param("<T>", "type of Azure resource");
                });
                javaFile.Block($"public class {className}<T> implements Page<T>", classBlock =>
                {
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("The link to the next page.");
                    });
                    classBlock.Annotation($"JsonProperty(\"{nextLinkName}\")");
                    classBlock.Line("private String nextPageLink;");
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("The list of items.");
                    });
                    classBlock.Annotation($"JsonProperty(\"{itemName}\")");
                    classBlock.Line("private List<T> items;");
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("Gets the link to the next page.");
                        comment.Line();
                        comment.Return("the link to the next page.");
                    });
                    classBlock.Annotation("Override");
                    classBlock.Block("public String nextPageLink()", function =>
                    {
                        function.Return("this.nextPageLink");
                    });
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("Gets the list of items.");
                        comment.Line();
                        comment.Return("the list of items in {@link List}.");
                    });
                    classBlock.Annotation("Override");
                    classBlock.Block("public List<T> items()", function =>
                    {
                        function.Return("items");
                    });
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("Sets the link to the next page.");
                        comment.Line();
                        comment.Param("nextPageLink", "the link to the next page.");
                        comment.Return("this Page object itself.");
                    });
                    classBlock.Block($"public {className}<T> setNextPageLink(String nextPageLink)", function =>
                    {
                        function.Line("this.nextPageLink = nextPageLink;");
                        function.Return("this");
                    });
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line("Sets the list of items.");
                        comment.Line();
                        comment.Param("items", "the list of items in {@link List}.");
                        comment.Return("this Page object itself.");
                    });
                    classBlock.Block($"public {className}<T> setItems(List<T> items)", function =>
                    {
                        function.Line("this.items = items;");
                        function.Return("this");
                    });
                });

                result.Add(javaFile);
            }

            return result;
        }

        public static IEnumerable<JavaFile> GetXmlWrapperJavaFiles(CodeModel codeModel, Settings settings)
        {
            IEnumerable<JavaFile> result;
            if (!codeModel.ShouldGenerateXmlSerialization)
            {
                result = Enumerable.Empty<JavaFile>();
            }
            else
            {
                Dictionary<SequenceType, JavaFile> javaFileMap = new Dictionary<SequenceType, JavaFile>();

                // Every sequence type used as a parameter to a service method.
                foreach (MethodGroup methodGroup in codeModel.Operations)
                {
                    foreach (Method method in methodGroup.Methods)
                    {
                        foreach (Parameter parameter in method.Parameters)
                        {
                            IModelType parameterType = parameter.ModelType;
                            if (parameterType is SequenceType sequenceType && !javaFileMap.Keys.Contains(sequenceType, ModelNameComparer.Instance))
                            {
                                string sequenceTypeName = GetIModelTypeName(sequenceType);
                                string xmlName = sequenceType.XmlName;
                                string xmlNameCamelCase = xmlName.ToCamelCase();
                                string className = $"{xmlName.ToPascalCase()}Wrapper";

                                JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);
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
                                    classBlock.Line();
                                    classBlock.Annotation($"JacksonXmlProperty(localName = \"{sequenceType.ElementXmlName}\")");
                                    classBlock.Line($"private final {sequenceTypeName} {xmlNameCamelCase};");
                                    classBlock.Line();
                                    classBlock.Annotation("JsonCreator");
                                    classBlock.Block($"public {className}(@JsonProperty(\"{xmlNameCamelCase}\") {sequenceTypeName} {xmlNameCamelCase})", constructor =>
                                    {
                                        constructor.Line($"this.{xmlNameCamelCase} = {xmlNameCamelCase};");
                                    });
                                    classBlock.Line();
                                    classBlock.MultipleLineComment(comment =>
                                    {
                                        comment.Line($"Get the {xmlName} value.");
                                        comment.Line();
                                        comment.Return($"the {xmlName} value");
                                    });
                                    classBlock.Block($"public {sequenceTypeName} {xmlNameCamelCase}()", function =>
                                    {
                                        function.Return(xmlNameCamelCase);
                                    });
                                });

                                javaFileMap.Add(sequenceType, javaFile);
                            }
                        }
                    }
                }
                result = javaFileMap.Values;
            }
            return result;
        }

        public static JavaFile GetAzureServiceClientJavaFile(CodeModel codeModel, Settings settings)
        {
            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            string className = $"{codeModel.Name.ToPascalCase()}Impl";

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);

            bool fluent = IsFluent(settings);

            IEnumerable<string> imports = GetImplImports(codeModel, settings);
            if (fluent)
            {
                imports = imports.Where(import =>
                    !import.StartsWith($"{codeModel.Namespace}.{implPackage}", StringComparison.OrdinalIgnoreCase) &&
                    !codeModel.Operations.Any(operation => import.EndsWith(operation.TypeName, StringComparison.OrdinalIgnoreCase)) &&
                    !import.EndsWith(codeModel.Name, StringComparison.OrdinalIgnoreCase));
            }
            javaFile.Import(imports.Concat(new[]
            {
                azureProxyImport,
                azureEnvironmentImport,
                "com.microsoft.azure.v2.AzureServiceClient",
                httpPipelineImport,
                "com.microsoft.rest.v2.RestResponse"
            }));

            string implements = (fluent ? "" : $" implements {codeModel.Name}");
            javaFile.MultipleLineComment(comment =>
            {
                comment.Line($"Initializes a new instance of the {className} class.");
            });
            javaFile.Block($"public class {className} extends AzureServiceClient{implements}", classBlock =>
            {
                string serviceClientType = GetServiceClientServiceType(codeModel);
                IEnumerable<Method> rootMethods = GetRootMethods(codeModel);
                bool hasRootMethods = rootMethods.Any();

                if (hasRootMethods)
                {
                    classBlock.SingleLineComment($"The {restProxyType} service to perform REST calls.");
                    classBlock.Line($"private {serviceClientType} service;");
                }
                classBlock.Line();

                AddMemberVariablesWithGettersAndSettings(classBlock, GetPropertiesEx(codeModel), className);

                foreach (MethodGroupJv operation in GetAllOperations(codeModel))
                {
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"The {operation.MethodGroupDeclarationType} object to access its operations.");
                    });
                    classBlock.Line($"private {operation.MethodGroupDeclarationType} {operation.Name};");
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"Gets the {operation.MethodGroupDeclarationType} object to access its operations.");
                        comment.Return($"the {operation.MethodGroupDeclarationType} object.");
                    });
                    classBlock.Block($"public {operation.MethodGroupDeclarationType} {operation.Name}()", function =>
                    {
                        function.Return($"this.{operation.Name}");
                    });
                }
                classBlock.Line();

                string constructorDescription = $"Initializes an instance of {codeModel.Name} client.";
                if (settings.AddCredentials)
                {
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line(constructorDescription);
                        comment.Line();
                        comment.Param("credentials", "the management credentials for Azure");
                    });
                    classBlock.Block($"public {className}(ServiceClientCredentials credentials)", constructor =>
                    {
                        constructor.Line($"this({azureProxyType}.defaultPipeline({className}.class, credentials));");
                    });
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line(constructorDescription);
                        comment.Line();
                        comment.Param("credentials", "the management credentials for Azure");
                        comment.Param(azureEnvironemntVariableName, azureEnvironmentDescription);
                    });
                    classBlock.Block($"public {className}(ServiceClientCredentials credentials, {azureEnvironmentType} {azureEnvironemntVariableName})", constructor =>
                    {
                        constructor.Line($"this({azureProxyType}.defaultPipeline({className}.class, credentials), {azureEnvironemntVariableName});");
                    });
                }
                else
                {
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line(constructorDescription);
                    });
                    classBlock.Block($"public {className}()", constructor =>
                    {
                        constructor.Line($"this({azureProxyType}.defaultPipeline({className}.class, credentials));");
                    });
                    classBlock.Line();
                    classBlock.MultipleLineComment(comment =>
                    {
                        comment.Line(constructorDescription);
                        comment.Line();
                        comment.Param(azureEnvironemntVariableName, azureEnvironmentDescription);
                    });
                    classBlock.Block($"public {className}({azureEnvironmentType} {azureEnvironemntVariableName})", constructor =>
                    {
                        constructor.Line($"this({azureProxyType}.defaultPipeline({className}.class, credentials), {azureEnvironemntVariableName});");
                    });
                }


                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line(constructorDescription);
                    comment.Line();
                    comment.Param(httpPipelineVariableName, httpPipelineDescription);
                });
                classBlock.Block($"public {className}({httpPipelineType} {httpPipelineVariableName})", constructor =>
                {
                    constructor.Line($"this({httpPipelineVariableName}, null);");
                });
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line(constructorDescription);
                    comment.Line();
                    comment.Param(httpPipelineVariableName, httpPipelineDescription);
                    comment.Param(azureEnvironemntVariableName, azureEnvironmentDescription);
                });
                classBlock.Block($"public {className}({httpPipelineType} {httpPipelineVariableName}, {azureEnvironmentType} {azureEnvironemntVariableName})", constructor =>
                {
                    constructor.Line($"super({httpPipelineVariableName}, {azureEnvironemntVariableName});");
                    constructor.Line("initialize();");
                });
                classBlock.Line();
                classBlock.Block("protected void initialize()", function =>
                {
                    foreach (Property property in GetPropertiesEx(codeModel).Where(p => p.DefaultValue != null))
                    {
                        function.Line($"this.{property.Name} = {property.DefaultValue};");
                    }

                    foreach (MethodGroupJva operation in GetAllOperations(codeModel))
                    {
                        function.Line($"this.{operation.Name} = new {operation.MethodGroupImplType}(this);");
                    }

                    string defaultHeaders = "";
                    if (!string.IsNullOrWhiteSpace(defaultHeaders))
                    {
                        function.Line(defaultHeaders);
                    }

                    if (hasRootMethods)
                    {
                        function.Line("initializeService();");
                    }
                });

                if (hasRootMethods)
                {
                    classBlock.Line();
                    classBlock.Block("private void initializeService()", function =>
                    {
                        function.Line($"service = {azureProxyType}.create({serviceClientType}.class, this);");
                    });

                    classBlock.Line();

                    classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
                    {
                        comment.Line($"The interface defining all the services for {codeModel.Name} to be used by {restProxyType} to perform REST calls.");
                    });
                    classBlock.Annotation($"Host(\"{GetBaseUrl(codeModel)}\")");
                    classBlock.Block($"interface {GetServiceClientServiceType(codeModel)}", interfaceBlock =>
                    {
                        foreach (Method method in GetRootMethods(codeModel))
                        {
                            if (MethodIsPagingNextOperation(method))
                            {
                                interfaceBlock.Annotation("GET(\"{{nextUrl}}\")");
                            }
                            else
                            {
                                interfaceBlock.Annotation($"{method.HttpMethod.ToString().ToUpper()}(\"{method.Url.TrimStart('/')}\")");
                            }
                            interfaceBlock.Line(MethodExpectedResponsesAnnotation(method));
                            if (method.DefaultResponse.Body != null)
                            {
                                interfaceBlock.Annotation($"UnexpectedResponseExceptionType({MethodOperationExceptionTypeString(method, settings)}.class)");
                            }

                            string methodParameterApiDeclaration = MethodParameterApiDeclaration(method, settings);
                            if (MethodIsLongRunningOperation(method))
                            {
                                interfaceBlock.Line($"Observable<OperationStatus<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>> {MethodName(method)}({methodParameterApiDeclaration});");
                            }
                            else
                            {
                                interfaceBlock.Line($"Single<{MethodRestResponseConcreteTypeName(method)}> {MethodName(method)}({methodParameterApiDeclaration});");
                            }

                            interfaceBlock.Line();
                        }
                    });

                    classBlock.Line();
                }

                foreach (Method method in rootMethods)
                {
                    IEnumerable<ParameterJv> nonConstantLocalParameters = MethodLocalParameters(method).Where(p => !p.IsConstant);
                    IEnumerable<ParameterJv> nonConstantOptionalLocalParameters = nonConstantLocalParameters.Where(p => !p.IsRequired);
                    IEnumerable<ParameterJv> nonConstantRequiredLocalParameters = nonConstantLocalParameters.Where(p => p.IsRequired);

                    if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                    {
                        if (nonConstantOptionalLocalParameters.Any())
                        {
                            // -----------------------------------------------
                            // All pages. Synchronous with required parameters
                            // -----------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                AddParameters(comment, nonConstantRequiredLocalParameters);
                                ThrowsIllegalArgumentException(comment);
                                ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                ThrowsRuntimeException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                }
                            });
                            classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                function.Line($"{ResponseServiceResponseGenericParameterString(method.ReturnType)} response = {MethodName(method)}SinglePageAsync({MethodRequiredParameterInvocation(method)}).blockingGet();");
                                function.Block($"return new {ResponseGenericBodyClientTypeString(method.ReturnType)}(response)", anonymousClass =>
                                {
                                    anonymousClass.Annotation("Override");
                                    anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} nextPage(String {MethodPagingNextPageLinkParameterName(method)})", subFunction =>
                                    {
                                        subFunction.Line(MethodPagingGroupedParameterTransformation(method, true, settings));
                                        subFunction.Return($"{MethodGetPagingNextMethodInvocation(method)}({MethodNextMethodParameterInvocation(method, true)}).blockingGet()");
                                    });
                                });
                            });
                            classBlock.Line();

                            // -----------------------------------------------
                            // All pages. Observable with required parameters.
                            // -----------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                AddParameters(comment, nonConstantRequiredLocalParameters);
                                ThrowsIllegalArgumentException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the observable to the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                                }
                            });
                            classBlock.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                function.Line($"return {MethodName(method)}SinglePageAsync({MethodRequiredParameterInvocation(method)})");
                                function.Indent(() =>
                                {
                                    function.Line(".toObservable()");
                                    function.Line($".concatMap(new Function<{ResponseServiceResponseGenericParameterString(method.ReturnType)}, Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>>() {{");
                                    function.Indent(() =>
                                    {
                                        function.Annotation("Override");
                                        function.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> apply({ResponseServiceResponseGenericParameterString(method.ReturnType)} page)", subFunction =>
                                        {
                                            string pagingNextPageLinkParameterName = MethodPagingNextPageLinkParameterName(method);
                                            subFunction.Line($"String {pagingNextPageLinkParameterName} = page.nextPageLink();");
                                            subFunction.If($"{pagingNextPageLinkParameterName} == null", ifBlock =>
                                            {
                                                ifBlock.Return("Observable.just(page)");
                                            });
                                            subFunction.Line(MethodPagingGroupedParameterTransformation(method, true, settings));
                                            subFunction.Return($"Observable.just(page).concatWith({MethodGetPagingNextMethodInvocation(method, false)}({MethodNextMethodParameterInvocation(method, true)}))");
                                        });
                                    });
                                    function.Line("});");
                                });
                            });
                            classBlock.Line();

                            // -------------------------------------------------
                            // Single page. Observable with required parameters.
                            // -------------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                AddParameters(comment, nonConstantRequiredLocalParameters);
                                ThrowsIllegalArgumentException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the {MethodReturnTypeResponseName(method)} object if successful.");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                                }
                            });
                            classBlock.Block($"public Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}SinglePageAsync({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                {
                                    function.If($"{param.Name} == null)", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                    });
                                }

                                foreach (ParameterJv param in MethodParametersToValidate(method).Where(p => p.IsRequired))
                                {
                                    function.Line($"Validator.validate({param.Name});");
                                }

                                foreach (ParameterJv parameter in MethodLocalParameters(method))
                                {
                                    if (!parameter.IsRequired)
                                    {
                                        function.Line($"final {GetIModelTypeName(parameter.ClientType)} {parameter.Name} = {parameter.ClientType.GetDefaultValue(method) ?? "null"});");
                                    }
                                    if (parameter.IsConstant)
                                    {
                                        function.Line($"final {GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType))} {parameter.Name} = {parameter.DefaultValue ?? "null"});");
                                    }
                                }

                                function.Line(MethodBuildInputMappings(method, true));
                                function.Line(MethodParameterConversion(method, settings));
                                if (MethodIsPagingNextOperation(method))
                                {
                                    function.Line($"String nextUrl = {MethodNextUrlConstructor(method)};");
                                }
                                function.Block($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>()", anonymousClass =>
                                {
                                    anonymousClass.Annotation("Override");
                                    anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                    {
                                        subFunction.Return("response.body()");
                                    });
                                });
                            });
                            classBlock.Line();
                        }

                        // -------------------------------------------
                        // All pages. Synchronous with all parameters.
                        // -------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, nonConstantLocalParameters);
                            ThrowsIllegalArgumentException(comment);
                            ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                            ThrowsRuntimeException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                            }
                        });
                        classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            function.Line($"{ResponseServiceResponseGenericParameterString(method.ReturnType)} response = {MethodName(method)}SinglePageAsync({MethodParameterInvocation(method)}).blockingGet();");
                            function.Block($"return new {ResponseGenericBodyClientTypeString(method.ReturnType)}(response)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} nextPage(String {MethodPagingNextPageLinkParameterName(method)})", subFunction =>
                                {
                                    subFunction.Line(MethodPagingGroupedParameterTransformation(method, false, settings));
                                    subFunction.Return($"{MethodGetPagingNextMethodInvocation(method)}({MethodNextMethodParameterInvocation(method, false)}).blockingGet()");
                                });
                            });
                        });

                        classBlock.Line();

                        // ------------------------------------------
                        // All pages. Observable with all parameters.
                        // ------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, nonConstantLocalParameters);
                            ThrowsIllegalArgumentException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the observable to the {MethodReturnTypeResponseName(method)} object");
                            }
                            else
                            {
                                comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });
                        classBlock.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            function.Line($"return {MethodName(method)}SinglePageAsync({MethodParameterInvocation(method)})");
                            function.Indent(() =>
                            {
                                function.Line(".toObservable()");
                                function.Line($".concatMap(new Function<{ResponseServiceResponseGenericParameterString(method.ReturnType)}, Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> apply({ResponseServiceResponseGenericParameterString(method.ReturnType)} page)", subFunction =>
                                    {
                                        string pagingNextPageLinkParameterName = MethodPagingNextPageLinkParameterName(method);
                                        subFunction.Line($"String {pagingNextPageLinkParameterName} = page.nextPageLink();");
                                        subFunction.If($"{pagingNextPageLinkParameterName} == null", ifBlock =>
                                        {
                                            ifBlock.Return("Observable.just(page)");
                                        });
                                        subFunction.Line(MethodPagingGroupedParameterTransformation(method, false, settings));
                                        subFunction.Return($"Observable.just(page).concatWith({MethodGetPagingNextMethodInvocation(method, false)}({MethodNextMethodParameterInvocation(method, false)}))");
                                    });
                                });
                                function.Line("});");
                            });
                        });
                        classBlock.Line();

                        // --------------------------------------------
                        // Single page. Observable with all parameters.
                        // --------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, nonConstantLocalParameters);
                            ThrowsIllegalArgumentException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the {MethodReturnTypeResponseName(method)} object if successful.");
                            }
                            else
                            {
                                comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });
                        classBlock.Block($"public Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}SinglePageAsync({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                            {
                                function.If($"{param.Name} == null", ifBlock =>
                                {
                                    function.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                });
                            }

                            foreach (ParameterJv param in MethodParametersToValidate(method))
                            {
                                function.Line($"Validator.validate({param.Name});");
                            }

                            foreach (ParameterJv parameter in MethodLocalParameters(method))
                            {
                                if (parameter.IsConstant)
                                {
                                    function.Line($"final {GetIModelTypeName(parameter.ModelType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                }
                            }

                            function.Line(MethodBuildInputMappings(method));
                            function.Line(MethodParameterConversion(method, settings));
                            if (MethodIsPagingNextOperation(method))
                            {
                                function.Line($"String nextUrl = {MethodNextUrlConstructor(method)};");
                            }
                            function.Block($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>()", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                {
                                    subFunction.Return("response.body()");
                                });
                            });
                        });
                        classBlock.Line();
                    }
                    else
                    {
                        if (!MethodIsLongRunningOperation(method))
                        {
                            GenerateRootMethodFunctions(classBlock, method, settings);
                        }
                        else
                        {
                            if (nonConstantOptionalLocalParameters.Any())
                            {
                                // -----------------------------------------
                                // Synchronous with only required parameters
                                // -----------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantRequiredLocalParameters);
                                    ThrowsIllegalArgumentException(comment);
                                    ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                    ThrowsRuntimeException(comment);
                                    if (method.ReturnType.Body != null)
                                    {
                                        comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                    }
                                });
                                classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)})", function =>
                                {
                                    if (GetIModelTypeName(GetIModelTypeResponseVariant(ResponseBodyClientType(method.ReturnType))) == "void")
                                    {
                                        function.Line($"{MethodName(method)}Async({MethodRequiredParameterInvocation(method)}).blockingLast().result();");
                                    }
                                    else
                                    {
                                        function.Return($"{MethodName(method)}Async({MethodRequiredParameterInvocation(method)}).blockingLast().result()");
                                    }
                                });

                                // --------------------------------------
                                // Callback with only required parameters
                                // --------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantRequiredLocalParameters);
                                    ParamServiceCallback(comment);
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the {@link ServiceFuture} object");
                                });
                                classBlock.Block($"public ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclarationWithCallback(method, settings)})", function =>
                                {
                                    function.Return($"ServiceFutureUtil.fromLRO({MethodName(method)}Async({MethodRequiredParameterInvocation(method)}, serviceCallback)");
                                });

                                // ----------------------------------------
                                // Observable with only required parameters
                                // ----------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantRequiredLocalParameters);
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the observable for the request");
                                });
                                classBlock.Block($"public Observable<OperationStatus<{ResponseGenericBodyClientTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)})", function =>
                                {
                                    foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                    {
                                        function.If($"{param.Name} == null", ifBlock =>
                                        {
                                            ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                        });
                                    }

                                    foreach (ParameterJv param in MethodParametersToValidate(method).Where(p => p.IsRequired))
                                    {
                                        function.Line($"Validator.validate({param.Name});");
                                    }

                                    foreach (ParameterJv parameter in MethodLocalParameters(method))
                                    {
                                        if (!parameter.IsRequired)
                                        {
                                            function.Line($"final {GetIModelTypeName(parameter.WireType)} {parameter.WireName} = {parameter.WireType.GetDefaultValue(method) ?? "null"};");
                                        }
                                        if (parameter.IsConstant)
                                        {
                                            function.Line($"final {GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType))} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                        }
                                    }

                                    function.Line(MethodBuildInputMappings(method, true));
                                    function.Line(MethodRequiredParameterConversion(method, settings));
                                    function.Return($"service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)})");
                                });

                                // -------------------------------
                                // Synchronous with all parameters
                                // -------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantLocalParameters);
                                    ThrowsIllegalArgumentException(comment);
                                    ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                    ThrowsRuntimeException(comment);
                                    if (method.ReturnType.Body != null)
                                    {
                                        comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                    }
                                });
                                classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodParameterDeclaration(method, settings)})", function =>
                                {
                                    if (MethodReturnTypeResponseName(method) == "void")
                                    {
                                        function.Line($"{MethodName(method)}Async({MethodParameterInvocation(method)}).blockingLast();");
                                    }
                                    else
                                    {
                                        function.Return($"{MethodName(method)}Async({MethodParameterInvocation(method)}).blockingLast().result()");
                                    }
                                });
                                classBlock.Line();

                                // ----------------------------
                                // Callback with all parameters
                                // ----------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantLocalParameters);
                                    ParamServiceCallback(comment);
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the {{@link ServiceFuture}} object");
                                });
                                classBlock.Block($"public ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodParameterDeclarationWithCallback(method, settings)})", function =>
                                {
                                    function.Return($"ServiceFutureUtil.fromLRO({MethodName(method)}Async({MethodParameterInvocation(method)}, serviceCallback)");
                                });
                                classBlock.Line();

                                // ------------------------------
                                // Observable with all parameters
                                // ------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    AddParameters(comment, nonConstantLocalParameters);
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the observable for the request");
                                });
                                classBlock.Block($"public Observable<OperationStatus<{ResponseGenericBodyClientTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodParameterDeclaration(method, settings)})", function =>
                                {
                                    foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                    {
                                        function.If($"{param.Name} == null", ifBlock =>
                                        {
                                            ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                        });
                                    }

                                    foreach (ParameterJv param in MethodParametersToValidate(method))
                                    {
                                        function.Line($"Validator.validate({param.Name});");
                                    }

                                    foreach (ParameterJv parameter in MethodLocalParameters(method))
                                    {
                                        if (parameter.IsConstant)
                                        {
                                            function.Line($"final {GetIModelTypeName(parameter.ModelType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                        }
                                    }

                                    function.Line(MethodBuildInputMappings(method));
                                    function.Line(MethodParameterConversion(method, settings));
                                    function.Return($"service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)})");
                                });
                            }
                        }
                    }
                    classBlock.Line();
                }
            });

            return javaFile;
        }

        public static JavaFile GetAzureServiceClientInterfaceJavaFile(CodeModel codeModel, Settings settings)
        {
            string interfaceName = codeModel.Name;

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, null, settings, interfaceName);

            List<string> imports = GetInterfaceImports(codeModel, settings).ToList();
            if (IsFluent(settings))
            {
                imports.Add("com.microsoft.azure.v2.AzureClient");
            }
            javaFile.Import(imports);

            javaFile.MultipleLineComment(comment =>
            {
                comment.Line($"The interface for {interfaceName} class.");
            });
            javaFile.PublicInterface(interfaceName, interfaceBlock =>
            {
                bool isFirstMethod = true;
                foreach (Property property in GetPropertiesEx(codeModel))
                {
                    string propertyDescription = property.Documentation;
                    string propertyName = property.Name;
                    string propertyNameCamelCase = propertyName.ToCamelCase();
                    string propertyType = GetIModelTypeName(GetPropertyModelType(property).ServiceResponseVariant());

                    if (isFirstMethod)
                    {
                        isFirstMethod = false;
                    }
                    else
                    {
                        interfaceBlock.Line();
                    }
                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"Gets {propertyDescription}.");
                        comment.Line();
                        comment.Return($"the {propertyName} value.");
                    });
                    interfaceBlock.Line($"{propertyType} {propertyNameCamelCase}();");
                    if (!property.IsReadOnly)
                    {
                        interfaceBlock.Line();
                        interfaceBlock.MultipleLineComment(comment =>
                        {
                            comment.Line($"Sets {propertyDescription}.");
                            comment.Line();
                            comment.Param(propertyNameCamelCase, $"the {propertyName} value.");
                            comment.Return("the service client itself");
                        });
                        interfaceBlock.Line($"{interfaceName} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase});");
                    }
                }

                foreach (MethodGroupJv operation in GetAllOperations(codeModel))
                {
                    string operationType = operation.TypeName;

                    interfaceBlock.Line();
                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"Gets the {operationType} object to access its operations.");
                        comment.Return($"the {operationType} object.");
                    });
                    interfaceBlock.Line($"{operationType} {operation.Name.ToCamelCase()}();");
                }

                interfaceBlock.Line();
                AddInterfaceMethodSignatures(interfaceBlock, codeModel, settings);
            });

            return javaFile;
        }

        public static JavaFile GetAzureMethodGroupJavaFile(CodeModel codeModel, Settings settings, MethodGroupJva methodGroup)
        {
            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            string className = methodGroup.MethodGroupImplType;

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);
            javaFile.Import(methodGroup.ImplImports);

            javaFile.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
            {
                comment.Line($"An instance of this class provides access to all the operations defined in {methodGroup.TypeName}.");
            });
            javaFile.Block($"public class {className}{methodGroup.ParentDeclaration}", classBlock =>
            {
                string methodGroupServiceType = methodGroup.MethodGroupServiceType;
                string serviceClientType = methodGroup.ServiceClientType;

                classBlock.SingleLineComment($"The {restProxyType} service to perform REST calls.");
                classBlock.Line($"private {methodGroupServiceType} service;");
                classBlock.SingleLineComment($"The service client containing this operation class.");
                classBlock.Line($"private {serviceClientType} client;");
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Initializes an instance of {className}.");
                    comment.Line();
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.Block($"public {className}({serviceClientType} client)", constructor =>
                {
                    constructor.Line($"this.service = {azureProxyType}.create({methodGroupServiceType}.class, client);");
                    constructor.Line("this.client = client;");
                });
                classBlock.Line();

                IMethodGroupJva methodGroupJva = methodGroup;
                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
                {
                    comment.Line($"The interface defining all the services for {methodGroupJva.Name} to be used by {restProxyType} to perform REST calls.");
                });
                classBlock.Annotation($"Host(\"{GetBaseUrl(methodGroupJva.CodeModel)}\")");
                classBlock.Block($"interface {methodGroupJva.ServiceType}", interfaceBlock =>
                {
                    foreach (Method method in methodGroupJva.Methods)
                    {
                        string methodName = MethodName(method);

                        interfaceBlock.Annotation($"Headers({{ \"x-ms-logging-context: {methodGroupJva.LoggingContext} {methodName}\" }})");
                        if (MethodIsPagingNextOperation(method))
                        {
                            interfaceBlock.Annotation("GET(\"{nextUrl}\")");
                        }
                        else
                        {
                            interfaceBlock.Annotation($"{method.HttpMethod.ToString().ToUpper()}(\"{method.Url.TrimStart('/')}\")");
                        }

                        string expectedResponsesAnnotation = MethodExpectedResponsesAnnotation(method);
                        if (!string.IsNullOrWhiteSpace(expectedResponsesAnnotation))
                        {
                            interfaceBlock.Line(expectedResponsesAnnotation);
                        }

                        if (method.DefaultResponse.Body != null)
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({MethodOperationExceptionTypeString(method, settings)}.class)");
                        }

                        string methodParameterApiDeclaration = MethodParameterApiDeclaration(method, settings);
                        if (MethodIsLongRunningOperation(method))
                        {
                            interfaceBlock.Line($"Observable<OperationStatus<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>> {methodName}({methodParameterApiDeclaration});");
                        }
                        else
                        {
                            interfaceBlock.Line($"Single<{MethodRestResponseConcreteTypeName(method)}> {methodName}({methodParameterApiDeclaration});");
                        }

                        interfaceBlock.Line();
                    }
                });

                classBlock.Line();

                foreach (Method method in methodGroup.Methods)
                {
                    if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                    {
                        if (MethodLocalParameters(method).Any(p => !p.IsConstant && !p.IsRequired))
                        {
                            // -----------------------------------------------
                            // All pages. Synchronous with required parameters
                            // -----------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ThrowsIllegalArgumentException(comment);
                                ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                ThrowsRuntimeException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                }
                            });
                            classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                function.Line($"{ResponseServiceResponseGenericParameterString(method.ReturnType)} response = {MethodName(method)}SinglePageAsync({MethodRequiredParameterInvocation(method)}).blockingGet();");
                                function.ReturnBlock($"new {ResponseGenericBodyClientTypeString(method.ReturnType)}(response)", anonymousClass =>
                                {
                                    anonymousClass.Annotation("Override");
                                    anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} nextPage(String {MethodPagingNextPageLinkParameterName(method)})", subFunction =>
                                    {
                                        string transformation = MethodPagingGroupedParameterTransformation(method, true, settings);
                                        if (!string.IsNullOrWhiteSpace(transformation))
                                        {
                                            subFunction.Line(transformation);
                                        }
                                        subFunction.Return($"{MethodGetPagingNextMethodInvocation(method)}({MethodNextMethodParameterInvocation(method, true)}).blockingGet()");
                                    });
                                });
                            });
                            classBlock.Line();

                            // -----------------------------------------------
                            // All pages. Observable with required parameters.
                            // -----------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ThrowsIllegalArgumentException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the observable to the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                                }
                            });
                            classBlock.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                function.Line($"return {MethodName(method)}SinglePageAsync({MethodRequiredParameterInvocation(method)})");
                                function.Indent(() =>
                                {
                                    function.Line(".toObservable()");
                                    string serviceResponseGenericParameterString = ResponseServiceResponseGenericParameterString(method.ReturnType);
                                    function.Line($".concatMap(new Function<{serviceResponseGenericParameterString}, Observable<{serviceResponseGenericParameterString}>>() {{");
                                    function.Indent(() =>
                                    {
                                        function.Annotation("Override");
                                        function.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> apply({ResponseServiceResponseGenericParameterString(method.ReturnType)} page)", subFunction =>
                                        {
                                            string pagingNextPageLinkParameterName = MethodPagingNextPageLinkParameterName(method);
                                            subFunction.Line($"String {pagingNextPageLinkParameterName} = page.nextPageLink();");
                                            subFunction.If($"{pagingNextPageLinkParameterName} == null", ifBlock =>
                                            {
                                                ifBlock.Return("Observable.just(page)");
                                            });
                                            string transformation = MethodPagingGroupedParameterTransformation(method, true, settings);
                                            if (!string.IsNullOrWhiteSpace(transformation))
                                            {
                                                subFunction.Line(transformation);
                                            }
                                            subFunction.Return($"Observable.just(page).concatWith({MethodGetPagingNextMethodInvocation(method, false)}({MethodNextMethodParameterInvocation(method, true)}))");
                                        });
                                    });
                                    function.Line("});");
                                });
                            });
                            classBlock.Line();

                            // -------------------------------------------------
                            // Single page. Observable with required parameters.
                            // -------------------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ThrowsIllegalArgumentException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the {MethodReturnTypeResponseName(method)} object if successful.");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                                }
                            });
                            classBlock.Block($"public Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}SinglePageAsync({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                {
                                    string paramName = param.Name;
                                    function.If($"{paramName} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {paramName} is required and cannot be null.\");");
                                    });
                                }

                                foreach (ParameterJv param in MethodParametersToValidate(method).Where(p => p.IsRequired))
                                {
                                    function.Line($"Validator.validate({param.Name});");
                                }

                                foreach (ParameterJv parameter in MethodLocalParameters(method))
                                {
                                    if (!parameter.IsRequired)
                                    {
                                        function.Line($"final {GetIModelTypeName(parameter.ClientType)} {parameter.Name} = {parameter.ClientType.GetDefaultValue(method) ?? "null"};");
                                    }

                                    if (parameter.IsConstant)
                                    {
                                        function.Line($"final {GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType))} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                    }
                                }

                                string inputMappings = MethodBuildInputMappings(method, true);
                                if (!string.IsNullOrWhiteSpace(inputMappings))
                                {
                                    function.Line(inputMappings);
                                }

                                string parameterConversion = MethodParameterConversion(method, settings);
                                if (!string.IsNullOrWhiteSpace(parameterConversion))
                                {
                                    function.Line(parameterConversion);
                                }

                                if (MethodIsPagingNextOperation(method))
                                {
                                    function.Line($"String nextUrl = {MethodNextUrlConstructor(method)};");
                                }

                                function.Line($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                    {
                                        subFunction.Return("response.body()");
                                    });
                                });
                                function.Line("});");
                            });
                            classBlock.Line();
                        }

                        // -------------------------------------------
                        // All pages. Synchronous with all parameters.
                        // -------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                            {
                                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                            }
                            ThrowsIllegalArgumentException(comment);
                            ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                            ThrowsRuntimeException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                            }
                        });
                        classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            function.Line($"{ResponseServiceResponseGenericParameterString(method.ReturnType)} response = {MethodName(method)}SinglePageAsync({MethodParameterInvocation(method)}).blockingGet();");
                            function.ReturnBlock($"new {ResponseGenericBodyClientTypeString(method.ReturnType)}(response)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} nextPage(String {MethodPagingNextPageLinkParameterName(method)})", subFunction =>
                                {
                                    string transformation = MethodPagingGroupedParameterTransformation(method, false, settings);
                                    if (!string.IsNullOrWhiteSpace(transformation))
                                    {
                                        subFunction.Line(transformation);
                                    }
                                    subFunction.Return($"{MethodGetPagingNextMethodInvocation(method)}({MethodNextMethodParameterInvocation(method, false)}).blockingGet()");
                                });
                            });
                        });
                        classBlock.Line();

                        // ------------------------------------------
                        // All pages. Observable with all parameters.
                        // ------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                            {
                                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                            }
                            ThrowsIllegalArgumentException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the observable to the {MethodReturnTypeResponseName(method)} object");
                            }
                            else
                            {
                                comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });
                        classBlock.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            function.Line($"return {MethodName(method)}SinglePageAsync({MethodParameterInvocation(method)})");
                            function.Indent(() =>
                            {
                                function.Line(".toObservable()");
                                function.Line($".concatMap(new Function<{ResponseServiceResponseGenericParameterString(method.ReturnType)}, Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> apply({ResponseServiceResponseGenericParameterString(method.ReturnType)} page)", subFunction =>
                                    {
                                        string nextPageLinkVariableName = MethodPagingNextPageLinkParameterName(method);
                                        subFunction.Line($"String {nextPageLinkVariableName} = page.nextPageLink();");
                                        subFunction.If($"{nextPageLinkVariableName} == null", ifBlock =>
                                        {
                                            ifBlock.Return("Observable.just(page)");
                                        });

                                        string transformation = MethodPagingGroupedParameterTransformation(method, false, settings);
                                        if (!string.IsNullOrWhiteSpace(transformation))
                                        {
                                            subFunction.Line(transformation);
                                        }
                                        subFunction.Return($"Observable.just(page).concatWith({MethodGetPagingNextMethodInvocation(method, false)}({MethodNextMethodParameterInvocation(method, false)}))");
                                    });
                                });
                                function.Line("});");
                            });
                        });
                        classBlock.Line();

                        // --------------------------------------------
                        // Single page. Observable with all parameters.
                        // --------------------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                            {
                                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                            }
                            ThrowsIllegalArgumentException(comment);
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the {MethodReturnTypeResponseName(method)} object if successful.");
                            }
                            else
                            {
                                comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });
                        classBlock.Block($"public Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}SinglePageAsync({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                            {
                                function.If($"{param.Name} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                });
                            }

                            foreach (ParameterJv param in MethodParametersToValidate(method))
                            {
                                function.Line($"Validator.validate({param.Name});");
                            }

                            foreach (ParameterJv parameter in MethodLocalParameters(method))
                            {
                                if (parameter.IsConstant)
                                {
                                    function.Line($"final {GetIModelTypeName(parameter.ModelType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                }
                            }

                            string inputMappings = MethodBuildInputMappings(method).Trim();
                            if (!string.IsNullOrEmpty(inputMappings))
                            {
                                function.Line(inputMappings);
                            }

                            string parameterConversion = MethodParameterConversion(method, settings).Trim();
                            if (!string.IsNullOrEmpty(parameterConversion))
                            {
                                function.Line(parameterConversion);
                            }

                            if (MethodIsPagingNextOperation(method))
                            {
                                function.Line($"String nextUrl = {MethodNextUrlConstructor(method)};");
                            }

                            function.Line($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>() {{");
                            function.Indent(() =>
                            {
                                function.Annotation("Override");
                                function.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                {
                                    subFunction.Return("response.body()");
                                });
                            });
                            function.Line("});");
                        });
                        classBlock.Line();
                    }
                    else if (MethodSimulateAsPagingOperation(method))
                    {
                        if (MethodLocalParameters(method).Any(p => !p.IsConstant && !p.IsRequired))
                        {
                            // -----------------------------------------
                            // Synchronous with only required parameters
                            // -----------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the PagedList<{ResponseSequenceElementTypeString(method.ReturnType)}> object if successful.");
                                }
                            });
                            classBlock.Block($"public PagedList<{ResponseSequenceElementTypeString(method.ReturnType)}> {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                string pageImplType = SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType));
                                string sequenceElementTypeString = ResponseSequenceElementTypeString(method.ReturnType);
                                function.Line($"{pageImplType}<{sequenceElementTypeString}> page = new {pageImplType}<>();");
                                function.Line($"page.setItems({MethodName(method)}Async({MethodRequiredParameterInvocation(method)}).single().items());");
                                function.Line("page.setNextPageLink(null);");
                                function.ReturnBlock($"new PagedList<{sequenceElementTypeString}>(page)", anonymousClass =>
                                {
                                    anonymousClass.Annotation("Override");
                                    anonymousClass.Block($"public Page<{sequenceElementTypeString}> nextPage(String nextPageLink)", subFunction =>
                                    {
                                        subFunction.Return("null");
                                    });
                                });
                            });
                            classBlock.Line();

                            // ----------------------------------------
                            // Observable with only required parameters
                            // ----------------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the observable to the {ResponseGenericBodyClientTypeString(method.ReturnType).EscapeXmlComment()} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Observable<Page<{ResponseSequenceElementTypeString(method.ReturnType)}>>}} object if successful.");
                                }
                            });
                            classBlock.Block($"public Observable<Page<{ResponseSequenceElementTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)})", function =>
                            {
                                foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                {
                                    function.If($"{param.Name} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                    });
                                }
                                foreach (ParameterJv param in MethodParametersToValidate(method).Where(p => p.IsRequired))
                                {
                                    function.Line($"Validator.validate({param.Name});");
                                }
                                foreach (ParameterJv parameter in MethodLocalParameters(method))
                                {
                                    if (!parameter.IsRequired)
                                    {
                                        function.Line($"final {GetIModelTypeName(parameter.ClientType)} {parameter.Name} = {parameter.ClientType.GetDefaultValue(method) ?? "null"};");
                                    }
                                    if (parameter.IsConstant)
                                    {
                                        function.Line($"final {GetIModelTypeName(parameter.ClientType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                    }
                                }

                                string inputMappings = MethodBuildInputMappings(method, true);
                                if (!string.IsNullOrWhiteSpace(inputMappings))
                                {
                                    function.Line(inputMappings);
                                }

                                string parameterConversion = MethodParameterConversion(method, settings);
                                if (!string.IsNullOrWhiteSpace(parameterConversion))
                                {
                                    function.Line(parameterConversion);
                                }

                                function.Line($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>() {{");
                                function.Indent(() =>
                                {
                                    function.Annotation("Override");
                                    function.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                    {
                                        subFunction.Return("response.body()");
                                    });
                                });
                                function.Line("}).toObservable();");
                            });
                            classBlock.Line();
                        }

                        // -------------------------------
                        // Synchronous with all parameters
                        // -------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                            {
                                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                            }
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the PagedList<{ResponseSequenceElementTypeString(method.ReturnType)}> object if successful.");
                            }
                        });
                        classBlock.Block($"public PagedList<{ResponseSequenceElementTypeString(method.ReturnType)}> {MethodName(method)}({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            string pageImplType = SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType));
                            string sequenceElementTypeString = ResponseSequenceElementTypeString(method.ReturnType);
                            function.Line($"{pageImplType}<{sequenceElementTypeString}> page = new {pageImplType}<>();");
                            function.Line($"page.setItems({MethodName(method)}Async({MethodParameterInvocation(method)}).toBlocking().single().items());");
                            function.Line($"page.setNextPageLink(null);");
                            function.ReturnBlock($"new PagedList<{sequenceElementTypeString}>(page)", anonymousClass =>
                            {
                                anonymousClass.Annotation("Override");
                                anonymousClass.Block($"public Page<{sequenceElementTypeString}> nextPage(String nextPageLink)", subFunction =>
                                {
                                    subFunction.Return("null");
                                });
                            });
                        });
                        classBlock.Line();

                        // ------------------------------
                        // Observable with all parameters
                        // ------------------------------
                        classBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                            {
                                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                            }
                            if (method.ReturnType.Body != null)
                            {
                                comment.Return($"the observable to the {ResponseGenericBodyClientTypeString(method.ReturnType).EscapeXmlComment()} object");
                            }
                            else
                            {
                                comment.Return($"the {{@link Observable<Page<{ResponseSequenceElementTypeString(method.ReturnType)}>>}} object if successful.");
                            }
                        });
                        classBlock.Block($"public Observable<Page<{ResponseSequenceElementTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodParameterDeclaration(method, settings)})", function =>
                        {
                            foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                            {
                                function.If($"{param.Name} == null", ifBlock =>
                                {
                                    ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                });
                            }
                            foreach (ParameterJv param in MethodParametersToValidate(method))
                            {
                                function.Line($"Validator.validate({param.Name});");
                            }
                            foreach (ParameterJv parameter in MethodLocalParameters(method))
                            {
                                if (parameter.IsConstant)
                                {
                                    function.Line($"final {GetIModelTypeName(parameter.ModelType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                }
                            }

                            string inputMappings = MethodBuildInputMappings(method, true);
                            if (!string.IsNullOrWhiteSpace(inputMappings))
                            {
                                function.Line(inputMappings);
                            }

                            string parameterConversion = MethodParameterConversion(method, settings);
                            if (!string.IsNullOrWhiteSpace(parameterConversion))
                            {
                                function.Line(parameterConversion);
                            }

                            function.Line($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)}).map(new Function<{MethodRestResponseConcreteTypeName(method)}, {ResponseServiceResponseGenericParameterString(method.ReturnType)}>() {{");
                            function.Indent(() =>
                            {
                                function.Annotation("Override");
                                function.Block($"public {ResponseServiceResponseGenericParameterString(method.ReturnType)} apply({MethodRestResponseConcreteTypeName(method)} response)", subFunction =>
                                {
                                    subFunction.Return("response.body()");
                                });
                            });
                            function.Line("}).toObservable();");
                        });
                        classBlock.Line();
                    }
                    else
                    {
                        if (!MethodIsLongRunningOperation(method))
                        {
                            GenerateRootMethodFunctions(classBlock, method, settings);
                        }
                        else
                        {
                            if (MethodLocalParameters(method).Any(p => !p.IsConstant && !p.IsRequired))
                            {
                                // -----------------------------------------
                                // Synchronous with only required parameters
                                // -----------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                    {
                                        comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                    }
                                    ThrowsIllegalArgumentException(comment);
                                    ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                    ThrowsRuntimeException(comment);
                                    if (method.ReturnType.Body != null)
                                    {
                                        comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                    }
                                });
                                classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)})", function =>
                                {
                                    if (GetIModelTypeName(GetIModelTypeResponseVariant(ResponseBodyClientType(method.ReturnType))) == "void")
                                    {
                                        function.Line($"{MethodName(method)}Async({MethodRequiredParameterInvocation(method)}).blockingLast().result();");
                                    }
                                    else
                                    {
                                        function.Return($"{MethodName(method)}Async({MethodRequiredParameterInvocation(method)}).blockingLast().result()");
                                    }
                                });
                                classBlock.Line();

                                // --------------------------------------
                                // Callback with only required parameters
                                // --------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                    {
                                        comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                    }
                                    ParamServiceCallback(comment);
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the {@link ServiceFuture} object");
                                });
                                classBlock.Block($"public ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclarationWithCallback(method, settings)})", function =>
                                {
                                    function.Return($"ServiceFutureUtil.fromLRO({MethodName(method)}Async({MethodRequiredParameterInvocation(method)}), serviceCallback)");
                                });
                                classBlock.Line();

                                // ----------------------------------------
                                // Observable with only required parameters
                                // ----------------------------------------
                                classBlock.MultipleLineComment(comment =>
                                {
                                    AddMethodSummaryAndDescription(comment, method);
                                    foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                                    {
                                        comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                    }
                                    ThrowsIllegalArgumentException(comment);
                                    comment.Return("the observable for the request");
                                });
                                classBlock.Block($"public Observable<OperationStatus<{ResponseGenericBodyClientTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)})", function =>
                                {
                                    foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                    {
                                        function.If($"{param.Name} == null", ifBlock =>
                                        {
                                            ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                        });
                                    }
                                    foreach (ParameterJv param in MethodParametersToValidate(method).Where(p => p.IsRequired))
                                    {
                                        function.Line($"Validator.validate({param.Name});");
                                    }
                                    foreach (ParameterJv parameter in MethodLocalParameters(method))
                                    {
                                        if (!parameter.IsRequired)
                                        {
                                            function.Line($"final {GetIModelTypeName(parameter.WireType)} {parameter.WireName} = {parameter.WireType.GetDefaultValue(method) ?? "null"};");
                                        }
                                        if (parameter.IsConstant)
                                        {
                                            function.Line($"final {GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType))} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                        }
                                    }

                                    string inputMappings = MethodBuildInputMappings(method, true);
                                    if (!string.IsNullOrWhiteSpace(inputMappings))
                                    {
                                        function.Line(inputMappings);
                                    }

                                    string parameterConversion = MethodRequiredParameterConversion(method, settings);
                                    if (!string.IsNullOrWhiteSpace(parameterConversion))
                                    {
                                        function.Line(parameterConversion);
                                    }

                                    function.Return($"service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)})");
                                });
                                classBlock.Line();
                            }

                            // -------------------------------
                            // Synchronous with all parameters
                            // -------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ThrowsIllegalArgumentException(comment);
                                ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                                ThrowsRuntimeException(comment);
                                if (method.ReturnType.Body != null)
                                {
                                    comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                                }
                            });
                            classBlock.Block($"public {MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodParameterDeclaration(method, settings)})", function =>
                            {
                                if (MethodReturnTypeResponseName(method) == "void")
                                {
                                    function.Line($"{MethodName(method)}Async({MethodParameterInvocation(method)}).blockingLast();");
                                }
                                else
                                {
                                    function.Return($"{MethodName(method)}Async({MethodParameterInvocation(method)}).blockingLast().result()");
                                }
                            });
                            classBlock.Line();

                            // ----------------------------
                            // Callback with all parameters
                            // ----------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ParamServiceCallback(comment);
                                ThrowsIllegalArgumentException(comment);
                                comment.Return("the {@link ServiceFuture} object");
                            });
                            classBlock.Block($"public ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodParameterDeclarationWithCallback(method, settings)})", function =>
                            {
                                function.Return($"ServiceFutureUtil.fromLRO({MethodName(method)}Async({MethodParameterInvocation(method)}), serviceCallback)");
                            });
                            classBlock.Line();

                            // ------------------------------
                            // Observable with all parameters
                            // ------------------------------
                            classBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                foreach (ParameterJv param in MethodLocalParameters(method).Where(p => !p.IsConstant))
                                {
                                    comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment().Trim());
                                }
                                ThrowsIllegalArgumentException(comment);
                                comment.Return("the observable for the request");
                            });
                            classBlock.Block($"public Observable<OperationStatus<{ResponseGenericBodyClientTypeString(method.ReturnType)}>> {MethodName(method)}Async({MethodParameterDeclaration(method, settings)})", function =>
                            {
                                foreach (ParameterJv param in MethodRequiredNullableParameters(method))
                                {
                                    function.If($"{param.Name} == null", ifBlock =>
                                    {
                                        ifBlock.Line($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                                    });
                                }
                                foreach (ParameterJv param in MethodParametersToValidate(method))
                                {
                                    function.Line($"Validator.validate({param.Name});");
                                }
                                foreach (ParameterJv parameter in MethodLocalParameters(method))
                                {
                                    if (parameter.IsConstant)
                                    {
                                        function.Line($"final {GetIModelTypeName(parameter.ModelType)} {parameter.Name} = {parameter.DefaultValue ?? "null"};");
                                    }
                                }

                                string inputMapping = MethodBuildInputMappings(method);
                                if (!string.IsNullOrWhiteSpace(inputMapping))
                                {
                                    function.Line(inputMapping);
                                }

                                string parameterConversion = MethodParameterConversion(method, settings);
                                if (!string.IsNullOrWhiteSpace(parameterConversion))
                                {
                                    function.Line(parameterConversion);
                                }

                                function.Return($"service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)})");
                            });
                        }
                    }

                    classBlock.Line();
                }
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientJavaFile(CodeModel codeModel, Settings settings)
        {
            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            string interfaceName = codeModel.Name.ToPascalCase();
            string className = $"{interfaceName}Impl";
            IEnumerable<Method> rootMethods = GetRootMethods(codeModel);
            bool hasRootMethods = rootMethods.Any();

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);

            List<string> imports = new List<string>(GetImplImports(codeModel, settings));
            imports.AddRange(new[]
            {
                httpPipelineImport,
                restProxyImport,
                "com.microsoft.rest.v2.ServiceClient"
            });
            if (hasRootMethods)
            {
                imports.AddRange(new[]
                {
                    "com.microsoft.rest.v2.RestResponse",
                });
            }
            javaFile.Import(imports);

            javaFile.MultipleLineComment(comment =>
            {
                comment.Line($"Initializes a new instance of the {interfaceName} class.");
            });
            javaFile.PublicClass($"{className} extends ServiceClient implements {interfaceName}", classBlock =>
            {
                string serviceClientType = GetServiceClientServiceType(codeModel);
                string baseUrl = GetBaseUrl(codeModel);

                if (hasRootMethods)
                {
                    classBlock.PrivateMemberVariable("The proxy service to use to perform REST calls.", serviceClientType, "service");
                }

                AddMemberVariablesWithGettersAndSettings(classBlock, codeModel.Properties, className);

                foreach (MethodGroupJv operation in GetAllOperations(codeModel))
                {
                    string operationType = operation.TypeName;
                    string operationName = operation.Name;

                    classBlock.Line();
                    classBlock.PrivateMemberVariable($"The {operationType} object to access its operations.", operationType, operationName);
                    classBlock.Line();
                    classBlock.PublicGetter(operationType, operationName);
                }

                classBlock.Line();
                classBlock.PublicConstructor(
                    $"Initializes an instance of {interfaceName} client.",
                    className, constructor =>
                    {
                        constructor.Line($"this({restProxyType}.createDefaultPipeline());");
                    });

                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Initializes an instance of {interfaceName} client.");
                    comment.Line();
                    comment.Param(httpPipelineVariableName, httpPipelineDescription);
                });
                classBlock.Block($"public {className}({httpPipelineType} {httpPipelineVariableName})", constructor =>
                {
                    constructor.Line($"super({httpPipelineVariableName});");

                    constructor.Line();
                    foreach (Property property in codeModel.Properties.Where(p => p.DefaultValue != null))
                    {
                        constructor.Line($"this.{property.Name} = {property.DefaultValue};");
                    }
                    foreach (MethodGroupJv operation in GetAllOperations(codeModel))
                    {
                        constructor.Line($"this.{operation.Name} = new {operation.TypeName}Impl(this);");
                    }

                    if (hasRootMethods)
                    {
                        constructor.Line();
                        constructor.Line($"service = {restProxyType}.create({serviceClientType}.class, {httpPipelineVariableName});");
                    }
                });

                if (hasRootMethods)
                {
                    classBlock.Line();
                    classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
                    {
                        comment.Line($"The interface defining all the services for {interfaceName} to be used by Retrofit to perform actually REST calls.");
                    });
                    classBlock.Annotation($"Host(\"{baseUrl}\")");
                    classBlock.Block($"interface {serviceClientType}", interfaceBlock =>
                    {
                        bool isFirstLine = true;
                        foreach (Method method in codeModel.Methods)
                        {
                            if (isFirstLine)
                            {
                                isFirstLine = false;
                            }
                            else
                            {
                                interfaceBlock.Line();
                            }

                            if (method.RequestContentType == "multipart/form-data" || method.RequestContentType == "application/x-www-form-urlencoded")
                            {
                                interfaceBlock.SingleLineSlashSlashComment($"@Multipart not supported by {restProxyType}");
                            }
                            interfaceBlock.Annotation($"{method.HttpMethod.ToString().ToUpper()}(\"{method.Url.TrimStart('/')}\")");
                            if (method.ReturnType.Body.IsPrimaryType(KnownPrimaryType.Stream))
                            {
                                interfaceBlock.SingleLineSlashSlashComment($"@Streaming not supported by {restProxyType}");
                            }
                            interfaceBlock.Line(MethodExpectedResponsesAnnotation(method));
                            if (method.DefaultResponse.Body != null)
                            {
                                interfaceBlock.Annotation($"UnexpectedResponseExceptionType({MethodOperationExceptionTypeString(method, settings)}.class)");
                            }
                            interfaceBlock.Line($"Single<{MethodRestResponseConcreteTypeName(method)}> {MethodName(method)}({MethodParameterApiDeclaration(method, settings)});");
                        }
                    });

                    foreach (Method method in rootMethods)
                    {
                        classBlock.Line();
                        GenerateRootMethodFunctions(classBlock, method, settings);
                    }
                }
            });

            return javaFile;
        }

        public static JavaFile GetServiceClientInterfaceJavaFile(CodeModel codeModel, Settings settings)
        {
            string interfaceName = codeModel.Name;

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, null, settings, interfaceName);

            javaFile.Import(GetInterfaceImports(codeModel, settings));

            javaFile.MultipleLineComment(comment =>
            {
                comment.Line($"The interface for {interfaceName} class.");
            });
            javaFile.PublicInterface(interfaceName, interfaceBlock =>
            {
                foreach (Property property in codeModel.Properties)
                {
                    string propertyDescription = property.Documentation;
                    string propertyType = GetIModelTypeName(GetPropertyModelType(property).ServiceResponseVariant());
                    string propertyName = property.Name;
                    string propertyNameCamelCase = propertyName.ToCamelCase();

                    interfaceBlock.Line();
                    AddPropertyGetterComment(interfaceBlock, propertyDescription, propertyNameCamelCase);
                    interfaceBlock.Line($"{propertyType} {propertyNameCamelCase}();");

                    if (!property.IsReadOnly)
                    {
                        interfaceBlock.Line();
                        AddPropertySetterComment(interfaceBlock, propertyDescription, propertyNameCamelCase);
                        interfaceBlock.Line($"{interfaceName} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase});");
                    }
                }

                foreach (MethodGroupJv operation in GetAllOperations(codeModel))
                {
                    string operationType = operation.TypeName;
                    string operationName = operation.Name;

                    interfaceBlock.Line();

                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        comment.Line($"Gets the {operationType} object to access its operations.");
                        comment.Return($"the {operationType} object.");
                    });
                    interfaceBlock.Line($"{operationType} {operationName}();");
                }

                interfaceBlock.Line();

                AddInterfaceMethodSignatures(interfaceBlock, codeModel, settings);
            });

            return javaFile;
        }

        private static void AddInterfaceMethodSignatures(JavaBlock interfaceBlock, CodeModel codeModel, Settings settings)
        {
            IEnumerable<Method> rootMethods = GetRootMethods(codeModel);
            if (rootMethods.Any())
            {
                foreach (Method method in rootMethods)
                {
                    string methodSummary = method.Summary;
                    string methodSummaryXmlEscaped = methodSummary?.EscapeXmlComment().Period();
                    string methodDescription = method.Description;
                    string methodDescriptionXmlEscaped = methodDescription?.EscapeXmlComment().Period();

                    IEnumerable<ParameterJv> methodParameters = MethodLocalParameters(method).Where(p => !p.IsConstant);
                    if (methodParameters.Any(p => !p.IsRequired))
                    {
                        IEnumerable<ParameterJv> requiredMethodParameters = methodParameters.Where(p => p.IsRequired);

                        interfaceBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, requiredMethodParameters);
                            ThrowsIllegalArgumentException(comment);
                            ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                            ThrowsRuntimeException(comment);
                            if (MethodReturnTypeResponseName(method).Else("void") != "void")
                            {
                                comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                            }
                        });
                        interfaceBlock.Line($"{MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodRequiredParameterDeclaration(method, settings)});");

                        interfaceBlock.Line();

                        interfaceBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, requiredMethodParameters);
                            ParamServiceCallback(comment);
                            ThrowsIllegalArgumentException(comment);
                            comment.Return("the {@link ServiceFuture} object");
                        });
                        interfaceBlock.Line($"ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclarationWithCallback(method, settings)});");

                        interfaceBlock.Line();

                        interfaceBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, requiredMethodParameters);
                            ThrowsIllegalArgumentException(comment);
                            if (MethodReturnTypeResponseName(method).Else("void") != "void")
                            {
                                comment.Return($"the observable to the {MethodReturnTypeResponseName(method)} object");
                            }
                            else
                            {
                                comment.Return($"the {{@link Maybe<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });

                        if (MethodReturnTypeResponseName(method).Else("void") != "void")
                        {
                            interfaceBlock.Line($"Maybe<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)});");
                        }
                        else
                        {
                            interfaceBlock.Line($"Completable {MethodName(method)}Async({MethodRequiredParameterDeclaration(method, settings)});");
                        }

                        if (MethodShouldGenerateBeginRestResponseMethod(method, settings))
                        {
                            interfaceBlock.Line();

                            interfaceBlock.MultipleLineComment(comment =>
                            {
                                AddMethodSummaryAndDescription(comment, method);
                                AddParameters(comment, requiredMethodParameters);
                                ThrowsIllegalArgumentException(comment);
                                if (MethodReturnTypeResponseName(method).Else("void") != "void")
                                {
                                    comment.Return($"the observable to the {MethodReturnTypeResponseName(method)} object");
                                }
                                else
                                {
                                    comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                                }
                            });
                            interfaceBlock.Line($"Single<{MethodRestResponseAbstractTypeName(method)}> {MethodName(method)}WithRestResponseAsync({MethodRequiredParameterDeclaration(method, settings)});");
                        }
                    }

                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        AddMethodSummaryAndDescription(comment, method);
                        AddParameters(comment, methodParameters);
                        ThrowsIllegalArgumentException(comment);
                        ThrowsOperationException(comment, MethodOperationExceptionTypeString(method, settings));
                        ThrowsRuntimeException(comment);
                        if (MethodReturnTypeResponseName(method).Else("void") != "void")
                        {
                            comment.Return($"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.");
                        }
                    });
                    interfaceBlock.Line($"{MethodReturnTypeResponseName(method)} {MethodName(method)}({MethodParameterDeclaration(method, settings)});");

                    interfaceBlock.Line();

                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        AddMethodSummaryAndDescription(comment, method);
                        AddParameters(comment, methodParameters);
                        ParamServiceCallback(comment);
                        ThrowsIllegalArgumentException(comment);
                        comment.Return("the {@link ServiceFuture} object");
                    });
                    interfaceBlock.Line($"ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({MethodParameterDeclarationWithCallback(method, settings)});");

                    interfaceBlock.Line();

                    interfaceBlock.MultipleLineComment(comment =>
                    {
                        AddMethodSummaryAndDescription(comment, method);
                        AddParameters(comment, methodParameters);
                        ThrowsIllegalArgumentException(comment);
                        if (MethodReturnTypeResponseName(method).Else("void") != "void")
                        {
                            comment.Return($"the observable to the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object");
                        }
                        else
                        {
                            comment.Return($"the {{@link Maybe<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                        }
                    });

                    string methodParameterDeclaration = MethodParameterDeclaration(method, settings);
                    if (MethodReturnTypeResponseName(method).Else("void") != "void")
                    {
                        interfaceBlock.Line($"Maybe<{ResponseServiceResponseGenericParameterString(method.ReturnType)}> {MethodName(method)}Async({methodParameterDeclaration});");
                    }
                    else
                    {
                        interfaceBlock.Line($"Completable {MethodName(method)}Async({methodParameterDeclaration});");
                    }

                    interfaceBlock.Line();

                    if (MethodShouldGenerateBeginRestResponseMethod(method, settings))
                    {
                        interfaceBlock.MultipleLineComment(comment =>
                        {
                            AddMethodSummaryAndDescription(comment, method);
                            AddParameters(comment, methodParameters);
                            ThrowsIllegalArgumentException(comment);
                            if (MethodReturnTypeResponseName(method).Else("void") != "void")
                            {
                                comment.Return($"the observable to the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object");
                            }
                            else
                            {
                                comment.Return($"the {{@link Single<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>}} object if successful.");
                            }
                        });
                    }
                    interfaceBlock.Line($"Single<{MethodRestResponseAbstractTypeName(method)}> {MethodName(method)}WithRestResponseAsync({MethodParameterDeclaration(method, settings)});");

                    interfaceBlock.Line();

                    interfaceBlock.Line();
                }
            }
        }

        private static void AddMemberVariablesWithGettersAndSettings(JavaBlock classBlock, IEnumerable<Property> properties, string className)
        {
            foreach (Property property in properties)
            {
                string propertyDocumentation = property.Documentation.ToString().Period();
                string propertyType = GetIModelTypeName(GetPropertyModelType(property).ServiceResponseVariant());
                string propertyName = property.Name;
                string propertyNameCamelCase = propertyName.ToCamelCase();

                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line(propertyDocumentation);
                });
                classBlock.Line($"private {propertyType} {propertyNameCamelCase};");
                classBlock.Line();
                AddPropertyGetterComment(classBlock, propertyDocumentation, propertyNameCamelCase);
                classBlock.Block($"public {propertyType} {propertyNameCamelCase}()", function =>
                {
                    function.Return($"this.{propertyNameCamelCase}");
                });

                if (!property.IsReadOnly)
                {
                    classBlock.Line();
                    AddPropertySetterComment(classBlock, propertyDocumentation, propertyNameCamelCase);
                    classBlock.Block($"public {className} with{propertyName.ToPascalCase()}({propertyType} {propertyNameCamelCase})", function =>
                    {
                        function.Line($"this.{propertyNameCamelCase} = {propertyNameCamelCase};");
                        function.Return("this");
                    });
                }
            }
        }

        private static void AddPropertyGetterComment(JavaBlock typeBlock, string propertyDocumentation, string propertyNameCamelCase)
        {
            typeBlock.MultipleLineComment(comment =>
            {
                comment.Line($"Gets {propertyDocumentation}");
                comment.Line();
                comment.Return($"the {propertyNameCamelCase} value.");
            });
        }

        private static void AddPropertySetterComment(JavaBlock typeBlock, string propertyDocumentation, string propertyNameCamelCase)
        {
            typeBlock.MultipleLineComment(comment =>
            {
                comment.Line($"Sets {propertyDocumentation}");
                comment.Line();
                comment.Param(propertyNameCamelCase, $"the {propertyNameCamelCase} value.");
                comment.Return("the service client itself");
            });
        }

        private static void AddMethodSummaryAndDescription(JavaMultipleLineComment comment, Method method)
        {
            if (!string.IsNullOrEmpty(method.Summary))
            {
                comment.Line(method.Summary.EscapeXmlComment().Period());
            }
            if (!string.IsNullOrEmpty(method.Description))
            {
                comment.Line(method.Description.EscapeXmlComment().Period());
            }
            comment.Line();
        }

        private static void AddParameters(JavaMultipleLineComment comment, IEnumerable<ParameterJv> parameters)
        {
            foreach (ParameterJv param in parameters)
            {
                comment.Param(param.Name, param.Documentation.Else("the " + GetIModelTypeName(param.ModelType) + " value").EscapeXmlComment());
            }
        }

        private static void ParamServiceCallback(JavaMultipleLineComment comment)
        {
            comment.Param("serviceCallback", "the async ServiceCallback to handle successful and failed responses.");
        }

        private static void ThrowsIllegalArgumentException(JavaMultipleLineComment comment)
        {
            comment.Throws("IllegalArgumentException", "thrown if parameters fail the validation");
        }

        private static void ThrowsOperationException(JavaMultipleLineComment comment, string operationExceptionType)
        {
            comment.Throws(operationExceptionType, "thrown if the request is rejected by server");
        }

        private static void ThrowsRuntimeException(JavaMultipleLineComment comment)
        {
            comment.Throws("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
        }

        public static JavaFile GetMethodGroupJavaFile(CodeModel codeModel, Settings settings, MethodGroupJv methodGroup)
        {
            string methodGroupTypeName = methodGroup.TypeName;
            string className = $"{methodGroupTypeName.ToPascalCase()}Impl";

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, implPackage, settings, className);

            javaFile.Import(methodGroup.ImplImports);

            int maximumCommentWidth = GetMaximumCommentWidth(settings);
            javaFile.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
            {
                comment.Line($"An instance of this class provides access to all the operations defined in {methodGroupTypeName}.");
            });
            javaFile.PublicClass($"{className}{methodGroup.ParentDeclaration}", classBlock =>
            {
                string serviceType = methodGroup.MethodGroupServiceType;
                string serviceClientType = methodGroup.ServiceClientType;

                classBlock.PrivateMemberVariable($"The {restProxyType} service to perform REST calls.", serviceType, "service");
                classBlock.Line();
                classBlock.PrivateMemberVariable("The service client containing this operation class.", serviceClientType, "client");
                classBlock.Line();
                classBlock.MultipleLineComment(comment =>
                {
                    comment.Line($"Initializes an instance of {methodGroupTypeName}.");
                    comment.Line();
                    comment.Param("client", "the instance of the service client containing this operation class.");
                });
                classBlock.Block($"public {className}({serviceClientType} client)", constructor =>
                {
                    constructor.Line($"this.service = {restProxyType}.create({serviceType}.class, client.httpPipeline(), client.serializerAdapter());");
                    constructor.Line("this.client = client;");
                });
                classBlock.Line();

                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, comment =>
                {
                    comment.Line($"The interface defining all the services for {methodGroupTypeName} to be used by {restProxyType} to perform REST calls.");
                });

                classBlock.Annotation($"Host(\"{GetBaseUrl(methodGroup.CodeModel)}\")");
                classBlock.Block($"interface {methodGroup.MethodGroupServiceType}", interfaceBlock =>
                {
                    foreach (Method method in methodGroup.Methods)
                    {
                        string methodName = MethodName(method);
                        string methodRequestContentType = method.RequestContentType;
                        if (methodRequestContentType == "multipart/form-data" || methodRequestContentType == "application/x-www-form-urlencoded")
                        {
                            interfaceBlock.SingleLineSlashSlashComment($"@Multipart not supported by {restProxyType}");
                        }
                        else
                        {
                            interfaceBlock.Annotation($"Headers({{ \"x-ms-logging-context: {methodGroup.MethodGroupFullType} {methodName}\" }})");
                        }
                        interfaceBlock.Annotation($"{method.HttpMethod.ToString().ToUpper()}(\"{method.Url.TrimStart('/')}\")");
                        if (method.ReturnType.Body.IsPrimaryType(KnownPrimaryType.Stream))
                        {
                            interfaceBlock.SingleLineSlashSlashComment($"@Streaming not supported by {restProxyType}");
                        }
                        string expectedResponsesAnnotation = MethodExpectedResponsesAnnotation(method);
                        if (!string.IsNullOrWhiteSpace(expectedResponsesAnnotation))
                        {
                            interfaceBlock.Line(MethodExpectedResponsesAnnotation(method));
                        }
                        string methodReturnValueWireType = ResponseReturnValueWireType(method.ReturnType);
                        if (methodReturnValueWireType != null)
                        {
                            interfaceBlock.Annotation($"ReturnValueWireType({methodReturnValueWireType}.class)");
                        }
                        if (method.DefaultResponse.Body != null)
                        {
                            interfaceBlock.Annotation($"UnexpectedResponseExceptionType({MethodOperationExceptionTypeString(method, settings)}.class)");
                        }
                        interfaceBlock.Line($"Single<{MethodRestResponseConcreteTypeName(method)}> {methodName}({MethodParameterApiDeclaration(method, settings)});");
                        interfaceBlock.Line();
                    }
                });

                classBlock.Line();

                foreach (Method method in methodGroup.Methods)
                {
                    GenerateRootMethodFunctions(classBlock, method, settings);

                    classBlock.Line();
                }
            });

            return javaFile;
        }

        public static JavaFile GetMethodGroupInterfaceJavaFile(CodeModel codeModel, Settings settings, MethodGroupJv methodGroup)
        {
            string interfaceName = methodGroup.TypeName;

            JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, null, settings, interfaceName);

            IEnumerable<string> imports = methodGroup.Methods.SelectMany(method => MethodInterfaceImports(method, settings));
            javaFile.Import(imports);

            int maximumCommentWidth = GetMaximumCommentWidth(settings);
            javaFile.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
            {
                comment.Line($"An instance of this class provides access to all the operations defined in {interfaceName}.");
            });
            javaFile.PublicInterface(interfaceName, (typeBlock) =>
            {
                IEnumerable<JavaMethod> methods = methodGroup.Methods.SelectMany(methodGroupMethod => ParseMethod(methodGroupMethod, settings));
                foreach (JavaMethod method in methods)
                {
                    typeBlock.MultipleLineComment((comment) =>
                    {
                        comment.Line(method.Description);
                        comment.Line();
                        if (method.Parameters != null)
                        {
                            foreach (JavaMethodParameter parameter in method.Parameters)
                            {
                                comment.Param(parameter.Name, parameter.Description);
                            }
                        }
                        if (method.Throws != null)
                        {
                            foreach (JavaThrow methodThrow in method.Throws)
                            {
                                comment.Throws(methodThrow.ExceptionTypeName, methodThrow.Description);
                            }
                        }
                        if (!string.IsNullOrEmpty(method.Return.Description))
                        {
                            comment.Return(method.Return.Description);
                        }
                    });
                    typeBlock.Line($"{method.Return.Type} {method.Name}({string.Join(", ", method.Parameters.Select(parameter => parameter.Declaration))});");
                    typeBlock.Line();
                }
            });
            return javaFile;
        }

        private static IEnumerable<JavaMethod> ParseMethod(Method method, Settings settings)
        {
            string description = "";
            if (!string.IsNullOrEmpty(method.Summary))
            {
                description += method.Summary.EscapeXmlComment().Period();
            }
            if (!string.IsNullOrEmpty(method.Description))
            {
                if (!string.IsNullOrEmpty(description))
                {
                    description += "\n";
                }
                description += method.Description.EscapeXmlComment().Period();
            }

            JavaThrow operationExceptionThrow = new JavaThrow(MethodOperationExceptionTypeString(method, settings), "thrown if the request is rejected by server");
            JavaThrow runtimeExceptionThrow = new JavaThrow("RuntimeException", "all other wrapped checked exceptions if the request fails to be sent");
            JavaThrow illegalArgumentExceptionThrow = new JavaThrow("IllegalArgumentException", "thrown if parameters fail the validation");

            string syncReturnType = MethodReturnTypeResponseName(method);
            string xmlEscapedSyncReturnType = syncReturnType.EscapeXmlComment();
            string asyncInnerReturnType = ResponseGenericBodyClientTypeString(method.ReturnType);
            string serviceFutureReturnType = $"ServiceFuture<{asyncInnerReturnType}>";

            bool isFluentOrAzure = IsFluentOrAzure(settings);

            string asyncReturnType;
            if (!isFluentOrAzure)
            {
                // TODO: consolidate this conversion
                if (method.ReturnType.Body == null)
                {
                    asyncReturnType = "Completable";
                }
                else
                {
                    asyncReturnType = $"Maybe<{asyncInnerReturnType}>";
                }
            }
            else
            {
                if (MethodIsLongRunningOperation(method))
                {
                    asyncReturnType = $"Observable<OperationStatus<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>>";
                }
                else if (method.ReturnType.Body == null)
                {
                    asyncReturnType = "Completable";
                }
                else if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    asyncReturnType = $"Observable<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>";
                }
                else
                {
                    asyncReturnType = $"Maybe<{ResponseServiceResponseGenericParameterString(method.ReturnType)}>";
                }
            }

            string asyncRestResponseReturnType = $"Single<{MethodRestResponseAbstractTypeName(method)}>";

            JavaMethodReturn syncReturn = new JavaMethodReturn(
                syncReturnType,
                syncReturnType == "void"
                    ? null
                    : $"the {xmlEscapedSyncReturnType} object if successful.");

            JavaMethodReturn callbackReturn = new JavaMethodReturn(
                serviceFutureReturnType,
                $"the {{@link {serviceFutureReturnType.EscapeXmlComment()}}} object");

            JavaMethodReturn asyncReturn = new JavaMethodReturn(
                asyncReturnType,
                $"the {{@link {asyncReturnType.EscapeXmlComment()}}} object if successful.");

            JavaMethodReturn asyncRestResponseReturn = new JavaMethodReturn(
                asyncRestResponseReturnType,
                $"the {{@link {asyncRestResponseReturnType.EscapeXmlComment()}}} object if successful.");

            string methodName = MethodName(method);
            string asyncMethodName = $"{methodName}Async";
            string asyncRestResponseMethodName = $"{methodName}WithRestResponseAsync";

            IEnumerable<ParameterJv> nonConstantParameters = MethodLocalParameters(method).Where(p => !p.IsConstant);
            IEnumerable<ParameterJv> nonConstantRequiredParameters = nonConstantParameters.Where(p => p.IsRequired);
            IEnumerable<JavaMethodParameter> parameters = nonConstantParameters.Select(ParseParameter);
            IEnumerable<JavaMethodParameter> requiredParameters = nonConstantRequiredParameters.Select(ParseParameter);

            JavaMethodParameter callbackParameter = new JavaMethodParameter(
                "the async ServiceCallback to handle successful and failed responses.",
                $"ServiceCallback<{asyncInnerReturnType}>",
                "serviceCallback",
                final: true);

            bool shouldGenerateCallbackMethod =
                !isFluentOrAzure ||
                (!MethodIsPagingOperation(method) &&
                 !MethodIsPagingNextOperation(method));

            bool shouldGenerateRestResponseMethod =
                !isFluentOrAzure ||
                (!MethodIsLongRunningOperation(method) &&
                 !MethodIsPagingOperation(method) &&
                 !MethodIsPagingNextOperation(method));

            List<JavaMethod> javaMethods = new List<JavaMethod>();

            void addMethods(IEnumerable<JavaMethodParameter> methodParameters)
            {
                // Sync
                javaMethods.Add(new JavaMethod(
                    description,
                    new[] { illegalArgumentExceptionThrow, operationExceptionThrow, runtimeExceptionThrow },
                    syncReturn,
                    methodName,
                    methodParameters));

                if (shouldGenerateCallbackMethod)
                {
                    // Callback
                    javaMethods.Add(new JavaMethod(
                        description,
                        new[] { illegalArgumentExceptionThrow },
                        callbackReturn,
                        asyncMethodName,
                        methodParameters.Concat(new[] { callbackParameter })));
                }

                // Async
                javaMethods.Add(new JavaMethod(
                    description,
                    new[] { illegalArgumentExceptionThrow },
                    asyncReturn,
                    asyncMethodName,
                    methodParameters));

                if (shouldGenerateRestResponseMethod)
                {
                    // RestResponse Async
                    javaMethods.Add(new JavaMethod(
                        description,
                        new[] { illegalArgumentExceptionThrow },
                        asyncRestResponseReturn,
                        asyncRestResponseMethodName,
                        methodParameters));
                }
            };

            if (nonConstantParameters.Any(p => !p.IsRequired))
            {
                addMethods(requiredParameters);
            }

            addMethods(parameters);

            return javaMethods;
        }

        private static JavaMethodParameter ParseParameter(ParameterJv parameter)
        {
            string description = parameter.Documentation.Else($"the {GetIModelTypeName(parameter.ModelType)} value").EscapeXmlComment();
            string type = GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType));
            string name = parameter.Name;
            return new JavaMethodParameter(description, type, name);
        }

        public static IEnumerable<JavaFile> GetPackageInfoJavaFiles(CodeModel codeModel, Settings settings, IEnumerable<string> subPackages)
        {
            List<JavaFile> packageInfoJavaFiles = new List<JavaFile>();

            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            string title = codeModel.Name;
            string description = codeModel.Documentation;

            foreach (string subPackage in subPackages)
            {
                string package = GetPackage(codeModel, subPackage);
                string folderPath = GetFolderPath(package);

                string filePath = GetFilePath(folderPath, "package-info");
                JavaFile javaFile = new JavaFile(filePath);

                if (!string.IsNullOrEmpty(headerComment))
                {
                    javaFile.WordWrappedMultipleLineSlashSlashComment(maximumHeaderCommentWidth, (comment) =>
                    {
                        comment.Line(headerComment);
                    });
                    javaFile.Line();
                }

                javaFile.WordWrappedMultipleLineComment(maximumHeaderCommentWidth, (comment) =>
                {
                    if (string.IsNullOrEmpty(subPackage))
                    {
                        comment.Line($"This package contains the classes for {title}.");
                    }
                    else
                    {
                        comment.Line($"This package contains the {subPackage} classes for {title}.");
                    }

                    if (!string.IsNullOrEmpty(description))
                    {
                        comment.Line(description.Period());
                    }
                });

                javaFile.Package(package);

                packageInfoJavaFiles.Add(javaFile);
            }

            return packageInfoJavaFiles;
        }

        public static IEnumerable<JavaFile> GetModelJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> exceptionJavaFiles = new List<JavaFile>();

            int maximumCommentWidth = GetMaximumCommentWidth(settings);

            foreach (CompositeType modelType in codeModel.ModelTypes.Union(codeModel.HeaderTypes))
            {
                bool shouldGenerate = true;

                if (IsAzure(settings))
                {
                    bool isExternalExtension =
                        modelType.Extensions.ContainsKey(AzureExtensions.ExternalExtension) &&
                        (bool)modelType.Extensions[AzureExtensions.ExternalExtension];

                    shouldGenerate = !isExternalExtension && !CompositeTypeIsResource(modelType);
                }

                if (shouldGenerate)
                {
                    List<string> imports = new List<string>();
                    imports.AddRange(GetCompositeTypeProperties(modelType).SelectMany(pm => GetImports(pm, settings)));

                    if (GetCompositeTypeProperties(modelType).Any(p => !p.GetJsonProperty().IsNullOrEmpty()))
                    {
                        imports.Add("com.fasterxml.jackson.annotation.JsonProperty");
                    }

                    if (GetCompositeTypeProperties(modelType).Any(p => p.XmlIsAttribute))
                    {
                        imports.Add("com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty");
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
                    if (CompositeTypeNeedsFlatten(modelType))
                    {
                        imports.Add("com.microsoft.rest.v2.serializer.JsonFlatten");
                    }

                    if (IsAzure(settings))
                    {
                        foreach (Property property in GetCompositeTypeProperties(modelType))
                        {
                            IModelType propertyModelType = GetPropertyModelType(property);
                            if (propertyModelType is CompositeType compositeType && CompositeTypeIsResource(compositeType))
                            {
                                imports.Add($"com.microsoft.azure.v2.{GetIModelTypeName(propertyModelType)}");
                            }
                        }

                        if (modelType.BaseModelType != null && (GetIModelTypeName(modelType.BaseModelType) == "Resource" || GetIModelTypeName(modelType.BaseModelType) == "SubResource"))
                        {
                            imports.Add("com.microsoft.azure.v2." + GetIModelTypeName(modelType.BaseModelType));
                        }

                        if (IsFluent(settings))
                        {
                            if (modelType.BaseModelType != null && GetIModelTypeName(modelType.BaseModelType).EndsWith("Inner", StringComparison.Ordinal) ^ innerModelCompositeType.Contains(modelType))
                            {
                                imports.AddRange(GetIModelTypeImports(modelType.BaseModelType));
                            }
                        }
                    }

                    string classComment;
                    if (string.IsNullOrEmpty(modelType.Summary) && string.IsNullOrEmpty(modelType.Documentation))
                    {
                        classComment = $"The {GetIModelTypeName(modelType)} model.";
                    }
                    else
                    {
                        classComment = $"{modelType.Summary.EscapeXmlComment().Period()}{modelType.Documentation.EscapeXmlComment().Period()}";
                    }

                    List<string> classAnnotations = new List<string>();
                    if (modelType.BaseIsPolymorphic)
                    {
                        classAnnotations.Add($"JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = \"{modelType.BasePolymorphicDiscriminator}\")");
                        classAnnotations.Add($"JsonTypeName(\"{modelType.SerializedName}\")");

                        List<CompositeType> types = CompositeTypeSubTypes(modelType).ToList();
                        if (types.Any())
                        {
                            StringBuilder subTypeAnnotationBuilder = new StringBuilder();
                            subTypeAnnotationBuilder.AppendLine("JsonSubTypes({");

                            Func<CompositeType, bool, string> getSubTypeAnnotation = (CompositeType subType, bool isLast) =>
                            {
                                string subTypeAnnotation = $"@JsonSubTypes.Type(name = \"{subType.SerializedName}\", value = {GetIModelTypeName(subType)}.class)";
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

                            classAnnotations.Add(subTypeAnnotationBuilder.ToString());
                        }
                    }

                    if (CompositeTypeNeedsFlatten(modelType))
                    {
                        classAnnotations.Add("JsonFlatten");
                    }

                    string className = GetIModelTypeName(modelType);

                    string baseTypeName = GetIModelTypeName(modelType.BaseModelType);

                    IEnumerable<JavaMemberVariable> memberVariables = GetCompositeTypeProperties(modelType).Select((Property property) =>
                    {
                        string comment;
                        if (string.IsNullOrEmpty(property.Summary) && string.IsNullOrEmpty(property.Documentation))
                        {
                            comment = $"The {property.Name} property.";
                        }
                        else
                        {
                            string summary = property.Summary.EscapeXmlComment().Period();
                            string documentation = property.Documentation.EscapeXmlComment().Period();

                            comment = summary;
                            if (!string.IsNullOrEmpty(documentation))
                            {
                                if (!string.IsNullOrEmpty(comment))
                                {
                                    comment += "\n";
                                }
                                comment += documentation;
                            }
                        }

                        string annotation = null;
                        string jsonSetting = property.GetJsonProperty();
                        if (!string.IsNullOrEmpty(jsonSetting))
                        {
                            if (property.XmlIsAttribute)
                            {
                                annotation = $"JacksonXmlProperty(localName = \"{property.SerializedName}\", isAttribute = true)";
                            }
                            else
                            {
                                annotation = $"JsonProperty({jsonSetting})";
                            }
                        }

                        bool isConstant = property.IsConstant;
                        bool isReadOnly = property.IsReadOnly;

                        bool isPrimitive = !(GetPropertyModelType(property) is CompositeType);
                        JavaType wireType = new JavaType(GetIModelTypeName(GetPropertyModelType(property)), isPrimitive);
                        JavaType clientType = new JavaType(GetIModelTypeName(GetIModelTypeResponseVariant(GetPropertyModelType(property))), isPrimitive);

                        string name = property.Name;

                        string defaultValue;
                        try
                        {
                            defaultValue = property.DefaultValue;
                        }
                        catch (NotSupportedException)
                        {
                            defaultValue = null;
                        }

                        return new JavaMemberVariable(comment, annotation, isConstant, isReadOnly, wireType, clientType, name, defaultValue);
                    });

                    JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, GetCompositeTypeModelsPackage(modelType, settings), settings, className);
                    javaFile.Import(imports);
                    javaFile.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                    {
                        comment.Line(classComment);
                    });
                    javaFile.Annotation(classAnnotations);

                    string classNameWithBaseType = className;
                    if (!string.IsNullOrEmpty(baseTypeName))
                    {
                        classNameWithBaseType += $" extends {baseTypeName}";
                    }
                    javaFile.PublicClass(classNameWithBaseType, (classBlock) =>
                    {
                        if (memberVariables != null && memberVariables.Any())
                        {
                            foreach (JavaMemberVariable memberVariable in memberVariables)
                            {
                                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                {
                                    comment.Line(memberVariable.Comment);
                                });
                                classBlock.Annotation(memberVariable.Annotation);
                                classBlock.Line($"private {memberVariable.WireType.Name} {memberVariable.Name};");
                                classBlock.Line();
                            }

                            IEnumerable<JavaMemberVariable> constantMemberVariables = memberVariables.Where((memberVariable) => memberVariable.IsConstant);
                            if (constantMemberVariables.Any())
                            {
                                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                {
                                    comment.Line($"Creates an instance of {className} class.");
                                });
                                classBlock.Block($"public {className}()", (constructor) =>
                                {
                                    foreach (JavaMemberVariable memberVariable in constantMemberVariables)
                                    {
                                        JavaType type = memberVariable.WireType;
                                        if (!type.IsPrimitive)
                                        {
                                            constructor.Line($"{memberVariable.Name} = new {type.Name}();");
                                        }
                                        else
                                        {
                                            constructor.Line($"{memberVariable.Name} = {memberVariable.DefaultValue};");
                                        }
                                    }
                                });
                                classBlock.Line();
                            }

                            foreach (JavaMemberVariable memberVariable in memberVariables)
                            {
                                string variableName = memberVariable.Name;
                                JavaType clientType = memberVariable.ClientType;
                                JavaType wireType = memberVariable.WireType;
                                string clientTypeName = clientType.Name;
                                bool clientTypeDifferentFromWireType = !clientType.Equals(memberVariable.WireType);

                                classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                {
                                    comment.Line($"Get the {variableName} value.");
                                    comment.Line();
                                    comment.Return($"the {variableName} value");
                                });
                                classBlock.Block($"public {clientTypeName} {variableName}()", (methodBlock) =>
                                {
                                    if (clientTypeDifferentFromWireType)
                                    {
                                        methodBlock.If($"this.{variableName} == null", (ifBlock) =>
                                        {
                                            ifBlock.Return("null");
                                        });
                                        methodBlock.Return(wireType.ConvertTo(clientType, $"this.{variableName}"));
                                    }
                                    else
                                    {
                                        methodBlock.Return($"this.{variableName}");
                                    }
                                });
                                classBlock.Line();

                                if (!memberVariable.IsReadOnly)
                                {
                                    classBlock.WordWrappedMultipleLineComment(maximumCommentWidth, (comment) =>
                                    {
                                        comment.Line($"Set the {variableName} value.");
                                        comment.Line();
                                        comment.Param(variableName, $"the {variableName} value to set");
                                        comment.Return($"the {className} object itself.");
                                    });
                                    classBlock.Block($"public {className} with{variableName.ToPascalCase()}({clientTypeName} {variableName})", (methodBlock) =>
                                    {
                                        if (clientTypeDifferentFromWireType)
                                        {
                                            methodBlock.If($"{variableName} == null", (ifBlock) =>
                                            {
                                                ifBlock.Line($"this.{variableName} = null;");
                                            })
                                            .Else((elseBlock) =>
                                            {
                                                elseBlock.Line($"this.{variableName} = {clientType.ConvertTo(wireType, variableName)};");
                                            });
                                        }
                                        else
                                        {
                                            methodBlock.Line($"this.{variableName} = {variableName};");
                                        }
                                        methodBlock.Return("this");
                                    });
                                    classBlock.Line();
                                }
                            }
                        }
                    });

                    exceptionJavaFiles.Add(javaFile);
                }
            }

            return exceptionJavaFiles;
        }

        public static IEnumerable<JavaFile> GetExceptionJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> exceptionJavaFiles = new List<JavaFile>();

            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = settings.MaximumCommentColumns;

            foreach (CompositeType exceptionType in codeModel.ErrorTypes)
            {
                string exceptionBodyTypeName = GetIModelTypeName(exceptionType);
                string exceptionName = CompositeTypeExceptionTypeDefinitionName(exceptionType);

                // Skip any exceptions that are named "CloudErrorException" or have a body named
                // "CloudError" because those types already exist in the runtime.
                if (exceptionBodyTypeName != "CloudError" && exceptionName != "CloudErrorException")
                {
                    JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, GetCompositeTypeModelsPackage(exceptionType, settings), settings, exceptionName);
                    javaFile.Import("com.microsoft.rest.v2.RestException",
                                    "com.microsoft.rest.v2.http.HttpResponse");
                    javaFile.MultipleLineComment((comment) =>
                    {
                        comment.Line($"Exception thrown for an invalid response with {exceptionBodyTypeName} information.");
                    });
                    javaFile.Block($"public class {exceptionName} extends RestException", (classBlock) =>
                    {
                        classBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line($"Initializes a new instance of the {exceptionName} class.")
                                .Line()
                                .Param("message", "the exception message or the response content if a message is not available")
                                .Param("response", "the HTTP response");
                        });
                        classBlock.Block($"public {exceptionName}(final String message, HttpResponse response)", (constructorBlock) =>
                        {
                            constructorBlock.Line("super(message, response);");
                        });
                        classBlock.Line();
                        classBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line($"Initializes a new instance of the {exceptionName} class.");
                            comment.Line();
                            comment.Param("message", "the exception message or the response content if a message is not available");
                            comment.Param("response", "the HTTP response");
                            comment.Param("body", "the deserialized response body");
                        });
                        classBlock.Block($"public {exceptionName}(final String message, final HttpResponse response, final {exceptionBodyTypeName} body)", (constructorBlock) =>
                        {
                            constructorBlock.Line("super(message, response, body);");
                        });
                        classBlock.Line();
                        classBlock.Annotation("Override");
                        classBlock.Block($"public {exceptionBodyTypeName} body()", (methodBlock) =>
                        {
                            methodBlock.Return($"({exceptionBodyTypeName}) super.body()");
                        });
                    });

                    exceptionJavaFiles.Add(javaFile);
                }
            }

            return exceptionJavaFiles;
        }

        public static IEnumerable<JavaFile> GetEnumJavaFiles(CodeModel codeModel, Settings settings)
        {
            List<JavaFile> enumJavaFiles = new List<JavaFile>();

            foreach (EnumType enumType in codeModel.EnumTypes)
            {
                string enumName = GetIModelTypeName(enumType);
                string enumTypeComment = $"Defines values for {enumName}.";

                IEnumerable<JavaEnumValue> enumValues = enumType.Values
                    .Select((EnumValue value) => new JavaEnumValue(value.MemberName, value.SerializedName));

                JavaFile javaFile = GenerateJavaFileWithHeaderAndPackage(codeModel, modelsPackage, settings, enumName);
                if (enumType.ModelAsString)
                {
                    javaFile.Import("java.util.Collection",
                                    "com.fasterxml.jackson.annotation.JsonCreator",
                                    "com.microsoft.rest.v2.ExpandableStringEnum");
                    javaFile.MultipleLineComment(comment =>
                    {
                        comment.Line(enumTypeComment);
                    });
                    javaFile.PublicFinalClass($"{enumName} extends ExpandableStringEnum<{enumName}>", (classBlock) =>
                    {
                        foreach (JavaEnumValue value in enumValues)
                        {
                            classBlock.SingleLineComment($"Static value {value.Value} for {enumName}.")
                                    .Line($"public static final {enumName} {value.Name} = fromString(\"{value.Value}\");")
                                    .Line();
                        }

                        classBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line($"Creates or finds a {enumName} from its string representation.")
                                .Param("name", "a name to look for")
                                .Return($"the corresponding {enumName}");
                        });
                        classBlock.Annotation("JsonCreator");
                        classBlock.Block($"public static {enumName} fromString(String name)", (function) =>
                        {
                            function.Return($"fromString(name, {enumName}.class)");
                        });
                        classBlock.Line();
                        classBlock.MultipleLineComment((comment) =>
                        {
                            comment.Return($"known {enumName} values");
                        });
                        classBlock.Block($"public static Collection<{enumName}> values()", (function) =>
                        {
                            function.Return($"values({enumName}.class)");
                        });
                    });
                }
                else
                {
                    javaFile.Import("com.fasterxml.jackson.annotation.JsonCreator",
                                    "com.fasterxml.jackson.annotation.JsonValue");
                    javaFile.MultipleLineComment(comment =>
                    {
                        comment.Line(enumTypeComment);
                    });
                    javaFile.PublicEnum(enumName, (enumBlock) =>
                    {
                        if (enumValues.Any())
                        {
                            Action<JavaEnumValue, bool> enumValue = (JavaEnumValue value, bool isLast) =>
                             {
                                 enumBlock.SingleLineComment($"Enum value {value.Value}.")
                                     .Line($"{value.Name}(\"{value.Value}\")" + (isLast ? ";" : ","))
                                     .Line();
                             };

                            foreach (JavaEnumValue value in enumValues.SkipLast(1))
                            {
                                enumValue(value, false);
                            }
                            enumValue(enumValues.Last(), true);
                        }

                        enumBlock.SingleLineComment($"The actual serialized value for a {enumName} instance.");
                        enumBlock.Line("private String value;");
                        enumBlock.Line();
                        enumBlock.Block($"{enumName}(String value)", (constructor) =>
                        {
                            constructor.Line("this.value = value;");
                        });
                        enumBlock.Line();
                        enumBlock.MultipleLineComment((comment) =>
                        {
                            comment.Line($"Parses a serialized value to a {enumName} instance.");
                            comment.Line()
                                    .Param("value", "the serialized value to parse.")
                                    .Return($"the parsed {enumName} object, or null if unable to parse.");
                        });
                        enumBlock.Annotation("JsonCreator");
                        enumBlock.Block($"public static {enumName} fromString(String value)", (function) =>
                        {
                            function.Line($"{enumName}[] items = {enumName}.values();");
                            function.Block($"for ({enumName} item : items)", (foreachBlock) =>
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

                enumJavaFiles.Add(javaFile);
            }

            return enumJavaFiles;
        }

        private static string GetPackage(CodeModel codeModel, params string[] packageSuffixes)
        {
            string package = codeModel.Namespace.ToLowerInvariant();
            if (packageSuffixes != null && packageSuffixes.Length > 0)
            {
                foreach (string packageSuffix in packageSuffixes)
                {
                    if (!string.IsNullOrEmpty(packageSuffix))
                    {
                        package = $"{package}.{packageSuffix.Trim('.')}";
                    }
                }
            }
            return package;
        }

        private static string GetFolderPath(string package)
        {
            return Path.Combine("src", "main", "java", package.Replace('.', Path.DirectorySeparatorChar));
        }

        private static string GetFilePath(string folderPath, string fileNameWithoutExtension)
        {
            string result = Path.Combine(folderPath, $"{fileNameWithoutExtension}.java");

            result = result.Replace('\\', '/');
            result = result.Replace("//", "/");

            return result;
        }

        private static int GetMaximumCommentWidth(Settings settings)
        {
            return settings.MaximumCommentColumns;
        }

        public static string GetServiceName(Settings settings, CodeModel codeModel)
        {
            var serviceNameSetting = settings.Host?.GetValue<string>("service-name").Result;
            if (!string.IsNullOrEmpty(serviceNameSetting))
            {
                return serviceNameSetting;
            }

            var method = codeModel.Methods[0];
            var match = Regex.Match(input: method.Url, pattern: @"/providers/microsoft\.(\w+)/", options: RegexOptions.IgnoreCase);
            var serviceName = match.Groups[1].Value.ToPascalCase();
            return serviceName;
        }

        private static JavaFile GenerateJavaFileWithHeaderAndPackage(CodeModel codeModel, string subPackage, Settings settings, string fileNameWithoutExtension)
        {
            string package = GetPackage(codeModel, subPackage);
            string folderPath = GetFolderPath(package);

            string headerComment = settings.Header;

            int maximumHeaderCommentWidth = GetMaximumCommentWidth(settings);

            string filePath = GetFilePath(folderPath, fileNameWithoutExtension);
            JavaFile javaFile = new JavaFile(filePath);

            if (!string.IsNullOrEmpty(headerComment))
            {
                javaFile.WordWrappedMultipleLineComment(maximumHeaderCommentWidth, (comment) =>
                {
                    comment.Line(headerComment);
                });
                javaFile.Line();
            }

            if (!string.IsNullOrEmpty(package))
            {
                javaFile.Package(package);
                javaFile.Line();
            }

            return javaFile;
        }

        private static void GenerateRootMethodFunctions(JavaBlock classBlock, Method method, Settings settings)
        {
            IEnumerable<ParameterJv> allParameters = MethodLocalParameters(method).Where(p => !p.IsConstant);
            IEnumerable<ParameterJv> requiredParameters = allParameters.Where(p => p.IsRequired);
            bool hasOptionalParameters = allParameters.Count() != requiredParameters.Count();

            CompositeType callbackParamModelType = DependencyInjection.New<CompositeType>();
            callbackParamModelType.Name.FixedValue = $"ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType)}>";

            ParameterJv callbackParam = new ParameterJv()
            {
                ModelType = callbackParamModelType,
                Name = "serviceCallback",
                SerializedName = "serviceCallback",
                Documentation = "the async ServiceCallback to handle successful and failed responses."
            };

            const string callbackReturnDocumentation = "the {@link ServiceFuture} object";

            if (hasOptionalParameters)
            {
                // --------------------------------------
                // Synchronous, Body, required parameters
                // --------------------------------------
                classBlock.Text(MethodJavadoc(method, requiredParameters, MethodSyncExceptionDocumentation(method, settings), MethodSyncReturnDocumentation(method)));
                classBlock.Text(MethodSyncImpl(method, requiredParameters));
                classBlock.Line();

                // -----------------------------------
                // Callback, Body, required parameters
                // -----------------------------------
                classBlock.Text(MethodJavadoc(method, requiredParameters.ConcatSingleItem(callbackParam), MethodAsyncExceptionDocumentation(), callbackReturnDocumentation));
                classBlock.Text(MethodCallbackImpl(method, requiredParameters, callbackParam));
                classBlock.Line();

                // ---------------------------------------------
                // Observable, RestResponse, required parameters
                // ---------------------------------------------
                classBlock.Text(MethodJavadoc(method, requiredParameters, MethodAsyncExceptionDocumentation(), MethodObservableReturnDocumentation(method)));
                classBlock.Text(MethodObservableRestResponseImpl(method, requiredParameters, true, settings));
                classBlock.Line();

                // -------------------------------------
                // Observable, Body, required parameters
                // -------------------------------------
                classBlock.Text(MethodJavadoc(method, requiredParameters, MethodAsyncExceptionDocumentation(), MethodObservableReturnDocumentation(method)));
                classBlock.Text(MethodObservableImpl(method, requiredParameters));
                classBlock.Line();
            }

            // ---------------------------------
            // Synchronous, Body, all parameters
            // ---------------------------------
            classBlock.Text(MethodJavadoc(method, allParameters, MethodSyncExceptionDocumentation(method, settings), MethodSyncReturnDocumentation(method)));
            classBlock.Text(MethodSyncImpl(method, allParameters));
            classBlock.Line();

            // ------------------------------
            // Callback, Body, all parameters
            // ------------------------------
            classBlock.Text(MethodJavadoc(method, allParameters.ConcatSingleItem(callbackParam), MethodAsyncExceptionDocumentation(), callbackReturnDocumentation));
            classBlock.Text(MethodCallbackImpl(method, allParameters, callbackParam));
            classBlock.Line();

            // ----------------------------------------
            // Observable, RestResponse, all parameters
            // ----------------------------------------
            classBlock.Text(MethodJavadoc(method, allParameters, MethodAsyncExceptionDocumentation(), MethodObservableReturnDocumentation(method)));
            classBlock.Text(MethodObservableRestResponseImpl(method, allParameters, false, settings));
            classBlock.Line();

            // --------------------------------
            // Observable, Body, all parameters
            // --------------------------------
            classBlock.Text(MethodJavadoc(method, allParameters, MethodAsyncExceptionDocumentation(), MethodObservableReturnDocumentation(method)));
            classBlock.Text(MethodObservableImpl(method, allParameters));
            classBlock.Line();
        }

        private static bool IsFluent(Settings settings)
            => GetBoolSetting(settings, "Fluent");

        private static bool IsAzure(Settings settings)
            => GetBoolSetting(settings, "Azure");

        private static bool IsFluentOrAzure(Settings settings)
            => IsFluent(settings) || IsAzure(settings);

        private static bool GetBoolSetting(Settings settings, string settingName)
        {
            bool result = false;

            object value;
            if (settings.CustomSettings.TryGetValue(settingName, out value))
            {
                result = (bool)value;
            }

            return result;
        }

        private static IEnumerable<Property> GetPropertiesEx(CodeModel codeModel)
            => codeModel.Properties.Where(p => GetIModelTypeName(GetPropertyModelType(p)) != "ServiceClientCredentials");

        private static string GetBaseUrl(CodeModel codeModel)
        {
            return codeModel.BaseUrl;
        }

        private static IEnumerable<string> GetImplImports(CodeModel codeModel, Settings settings)
        {
            HashSet<string> classes = new HashSet<string>();
            classes.Add(codeModel.Namespace.ToLowerInvariant() + "." + codeModel.Name);
            foreach (var methodGroupFullType in GetAllOperations(codeModel).Select(op => op.MethodGroupFullType).Distinct())
            {
                classes.Add(methodGroupFullType);
            }
            if (codeModel.Properties.Any(p => GetPropertyModelType(p).IsPrimaryType(KnownPrimaryType.Credentials)))
            {
                classes.Add("com.microsoft.rest.v2.credentials.ServiceClientCredentials");
            }

            classes.AddRange(GetRootMethods(codeModel)
                .SelectMany(m => MethodImplImports(m, settings))
                .OrderBy(i => i));

            return classes.AsEnumerable();
        }

        private static IEnumerable<string> GetInterfaceImports(CodeModel codeModel, Settings settings)
        {
            HashSet<string> classes = new HashSet<string>();

            classes.AddRange(GetRootMethods(codeModel)
                .SelectMany(m => MethodInterfaceImports(m, settings))
                .OrderBy(i => i).Distinct());

            return classes.ToList();
        }

        private static IEnumerable<MethodGroupJv> GetAllOperations(CodeModel codeModel)
            => codeModel.Operations.Where(operation => !operation.Name.IsNullOrEmpty()).Cast<MethodGroupJv>();

        private static string GetServiceClientServiceType(CodeModel codeModel)
            => CodeNamerJv.GetServiceName(codeModel.Name.ToPascalCase());

        private static IEnumerable<Method> GetRootMethods(CodeModel codeModel)
            => codeModel.Methods.Where(m => m.Group.IsNullOrEmpty());

        private static IEnumerable<string> GetImports(Property property, Settings settings)
        {
            IEnumerable<string> result = null;

            if (IsFluent(settings))
            {
                IModelType modelType = GetPropertyModelType(property);
                List<string> imports = new List<string>(GetIModelTypeImports(modelType)
                            .Where(c => !c.StartsWith(property.Parent.CodeModel?.Namespace.ToLowerInvariant(), StringComparison.Ordinal) ||
                                c.EndsWith("Inner", StringComparison.Ordinal) ^ innerModelProperties.Contains(property)));

                if (modelType.IsPrimaryType(KnownPrimaryType.DateTimeRfc1123))
                {
                    imports.AddRange(GetIModelTypeImports(modelType));
                    imports.AddRange(GetIModelTypeImports(GetIModelTypeResponseVariant(modelType)));
                }

                result = imports;
            }
            else
            {
                IModelType modelType = GetPropertyModelType(property);
                List<string> imports = new List<string>(GetIModelTypeImports(modelType)
                        .Where(c => !c.StartsWith(
                            string.Join(
                                ".",
                                property.Parent?.CodeModel?.Namespace.ToLowerInvariant(),
                                "models"),
                            StringComparison.OrdinalIgnoreCase)));
                if (modelType.IsPrimaryType(KnownPrimaryType.DateTimeRfc1123)
                    || modelType.IsPrimaryType(KnownPrimaryType.Base64Url))
                {
                    imports.AddRange(GetIModelTypeImports(modelType));
                    imports.AddRange(GetIModelTypeImports(GetIModelTypeResponseVariant(modelType)));
                }

                result = imports;
            }

            return result;
        }

        internal static IModelType GetPropertyModelType(Property property)
        {
            if (property.ModelType == null)
            {
                return null;
            }
            return property.IsXNullable ?? !property.IsRequired
                ? property.ModelType
                : GetIModelTypeNonNullableVariant(property.ModelType);
        }

        internal static IEnumerable<string> GetIModelTypeImports(IModelType modelType)
            => GetIModelTypeImports(modelType, Settings.Instance);

        private static IEnumerable<string> GetIModelTypeImports(IModelType modelType, Settings settings)
        {
            IEnumerable<string> result = Enumerable.Empty<string>();

            if (modelType != null)
            {
                if (modelType is EnumType)
                {
                    if (GetIModelTypeName(modelType) != "String")
                    {
                        if (!IsFluent(settings))
                        {
                            result = new[]
                            {
                                string.Join(".", modelType.CodeModel?.Namespace.ToLowerInvariant(), "models", GetIModelTypeName(modelType))
                            };
                        }
                        else
                        {
                            result = new[]
                            {
                                string.Join(".", (modelType.CodeModel?.Namespace.ToLowerInvariant()) + (GetIModelTypeName(modelType).EndsWith("Inner") ? ".implementation" : ""), GetIModelTypeName(modelType))
                            };
                        }
                    }
                }
                else if (modelType is CompositeType compositeType)
                {
                    string compositeTypeName = GetIModelTypeName(compositeType);
                    bool compositeTypeIsExternalExtension = CompositeTypeIsExternalExtension(compositeType);
                    bool compositeTypeIsAzureResourceExtension = CompositeTypeIsAzureResourceExtension(compositeType);
                    result = CompositeTypeImports(compositeTypeName, compositeType.CodeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings);
                }
                else if (modelType is SequenceType sequenceType)
                {
                    result = GetIModelTypeImports(sequenceType.ElementType)
                        .Concat(new[] { "java.util.List" });
                }
                else if (modelType is IModelTypeJv modelTypeJv)
                {
                    result = modelTypeJv.Imports;
                }
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeImports(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, Settings settings)
        {
            IEnumerable<string> result;

            if (IsFluent(settings))
            {
                result = CompositeTypeImportsFluent(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings);
            }
            else if (IsAzure(settings))
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

        internal static IEnumerable<string> CompositeTypeImportsFluent(string compositeTypeName, CodeModel codeModel)
            => CompositeTypeImportsFluent(compositeTypeName, codeModel, false, false, Settings.Instance);

        internal static IEnumerable<string> CompositeTypeImportsFluent(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, Settings settings)
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

        internal static IEnumerable<string> CompositeTypeImportsAzure(string compositeTypeName, CodeModel codeModel)
            => CompositeTypeImportsAzure(compositeTypeName, codeModel, false, false, Settings.Instance);

        internal static IEnumerable<string> CompositeTypeImportsAzure(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, Settings settings)
        {
            List<string> result = new List<string>();
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
            return result;
        }

        internal static IModelType GetIModelTypeResponseVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is SequenceType sequenceTypeJv)
            {
                IModelType elementTypeResponseVariant = GetIModelTypeResponseVariant(sequenceTypeJv.ElementType);
                if (elementTypeResponseVariant != sequenceTypeJv.ElementType && (elementTypeResponseVariant as PrimaryTypeJv)?.Nullable != false)
                {
                    SequenceType sequenceType = DependencyInjection.New<SequenceType>();
                    sequenceType.ElementType = elementTypeResponseVariant;
                    result = sequenceType;
                }
            }
            else if (modelType is IModelTypeJv modelTypeJv)
            {
                result = modelTypeJv.ResponseVariant;
            }

            return result;
        }

        internal static IModelType GetIModelTypeParameterVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is SequenceType sequenceType)
            {
                IModelType elementTypeResponseVariant = GetIModelTypeParameterVariant(sequenceType.ElementType);
                if (elementTypeResponseVariant != sequenceType.ElementType && (elementTypeResponseVariant as PrimaryTypeJv)?.Nullable != false)
                {
                    SequenceType resultSequenceType = DependencyInjection.New<SequenceType>();
                    resultSequenceType.ElementType = elementTypeResponseVariant;
                    result = resultSequenceType;
                }
            }
            else if (modelType is IModelTypeJv modelTypeJv)
            {
                result = modelTypeJv.ParameterVariant;
            }

            return result;
        }

        internal static IModelType GetIModelTypeNonNullableVariant(IModelType modelType)
        {
            IModelType result = modelType;

            if (modelType is IModelTypeJv modelTypeJv)
            {
                result = modelTypeJv.NonNullableVariant;
            }

            return result;
        }

        internal static string GetIModelTypeName(IModelType modelType)
            => GetIModelTypeName(modelType, Settings.Instance);

        private static string GetIModelTypeName(IModelType modelType, Settings settings)
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
                    result = $"List<{GetIModelTypeName(sequenceType.ElementType)}>";
                    if (pagedListTypes.Contains(modelType))
                    {
                        result = "Paged" + result;
                    }
                }
                else if (modelType is DictionaryTypeJv dictionaryTypeJv)
                {
                    result = $"Map<String, {GetIModelTypeName(dictionaryTypeJv.ValueType)}>";
                }
                else if (modelType is CompositeType && IsFluent(settings))
                {
                    result = string.IsNullOrEmpty(result) || !innerModelCompositeType.Contains(modelType) ? result : result + "Inner";
                }
                else if (modelType is PrimaryTypeJv primaryTypeJv)
                {
                    result = primaryTypeJv.ImplementationName;
                }
            }
            return result;
        }

        internal static string GetIModelTypeFixedName(IModelType modelType)
        {
            return modelType?.Name?.FixedValue;
        }

        internal static IEnumerable<Property> GetCompositeTypeProperties(CompositeType compositeType)
            => GetCompositeTypeProperties(compositeType, Settings.Instance);

        private static IEnumerable<Property> GetCompositeTypeProperties(CompositeType compositeType, Settings settings)
        {
            IEnumerable<Property> result = compositeType.Properties;

            if (IsFluent(settings))
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

        private static string GetCompositeTypePackage(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, Settings settings)
        {
            string result;

            if (IsFluent(settings))
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
            else if (IsAzure(settings))
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

        private static string GetCompositeTypeModelsPackage(CompositeType compositeType, Settings settings)
        {
            string result;

            if (IsFluent(settings))
            {
                result = innerModelCompositeType.Contains(compositeType) ? ".implementation" : "";
            }
            else
            {
                result = modelsPackage;
            }

            return result;
        }

        private static IEnumerable<string> CompositeTypeGenericTypeImports(string compositeTypeName, CodeModel codeModel, bool compositeTypeIsExternalExtension, bool compositeTypeIsAzureResourceExtension, Settings settings)
        {
            List<string> result = new List<string>();

            string[] types = compositeTypeName.Split(new String[] { "<", ">", ",", ", " }, StringSplitOptions.RemoveEmptyEntries);
            foreach (string innerTypeName in types.Where(t => !string.IsNullOrWhiteSpace(t)))
            {
                string trimmedInnerTypeName = innerTypeName.Trim();
                if (!CodeNamerJv.PrimaryTypes.Contains(trimmedInnerTypeName))
                {
                    result.AddRange(CompositeTypeImports(compositeTypeName, codeModel, compositeTypeIsExternalExtension, compositeTypeIsAzureResourceExtension, settings));
                }
            }

            return result;
        }

        internal static bool CompositeTypeIsResource(CompositeType compositeType)
            => CompositeTypeIsResource(GetIModelTypeName(compositeType), CompositeTypeIsAzureResourceExtension(compositeType));

        private static bool CompositeTypeIsResource(string compositeTypeName, bool isAzureResourceExtension)
            => compositeTypeName == "Resource" || (compositeTypeName == "SubResource" && isAzureResourceExtension);

        internal static bool CompositeTypeNeedsFlatten(CompositeType compositeType)
            => GetCompositeTypeProperties(compositeType).Any(p => p.WasFlattened());

        internal static string CompositeTypeExceptionTypeDefinitionName(CompositeType compositeType)
        {
            string result = GetIModelTypeName(compositeType) + "Exception";

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

        internal static bool GetExtensionBool(IDictionary<string, object> extensions, string extensionName)
            => extensions?.Get<bool>(extensionName) == true;

        internal static bool GetExtensionBool(ModelType modelType, string extensionName)
            => GetExtensionBool(modelType?.Extensions, extensionName);

        private static bool CompositeTypeIsExternalExtension(CompositeType compositeType)
            => GetExtensionBool(compositeType, "x-ms-external");

        internal static bool CompositeTypeIsAzureResourceExtension(CompositeType compositeType)
            => GetExtensionBool(compositeType, AzureExtensions.AzureResourceExtension);

        internal static string SequenceTypeGetPageImplType(IModelType modelType)
            => DictionaryGet(pageImplTypes, modelType);

        internal static void SequenceTypeSetPageImplType(IModelType modelType, string pageImplType)
            => pageImplTypes[modelType] = pageImplType;

        internal static Method ResponseGetParent(Response response)
            => DictionaryGet(responseParents, response);

        internal static void ResponseSetParent(Response response, Method parent)
            => responseParents[response] = parent;

        internal static bool ResponseIsPagedResponse(Response response)
        {
            Method parent = ResponseGetParent(response);
            return MethodIsPagingNextOperation(parent) || MethodIsPagingOperation(parent);
        }

        internal static IModelType ResponseBodyClientType(Response response)
            => ResponseBodyClientType(response, Settings.Instance);

        private static IModelType ResponseBodyClientType(Response response, Settings settings)
        {
            IModelType result = GetIModelTypeResponseVariant(ResponseBodyWireType(response));
            if ((IsFluent(settings) || IsAzure(settings)) && result is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
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

        internal static string ResponseGenericBodyClientTypeString(Response response)
            => ResponseGenericBodyClientTypeString(response, Settings.Instance);

        private static string ResponseGenericBodyClientTypeString(Response response, Settings settings)
        {
            string result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                if (ResponseBodyClientType(response) is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
                {
                    result = $"PagedList<{GetIModelTypeName(bodySequenceType.ElementType)}>";
                }
                else
                {
                    IModelType responseBodyWireType = ResponseBodyWireType(response);
                    IModelType responseVariant = GetIModelTypeResponseVariant(responseBodyWireType);
                    if ((responseVariant as PrimaryTypeJv)?.Nullable != false)
                    {
                        result = GetIModelTypeName(responseVariant);
                    }
                    else
                    {
                        result = GetIModelTypeName(responseBodyWireType);
                    }
                }
            }
            else
            {
                IModelType bodyWireType = ResponseBodyWireType(response);
                IModelType responseVariant = GetIModelTypeResponseVariant(bodyWireType);
                if ((responseVariant as PrimaryTypeJv)?.Nullable != false)
                {
                    result = GetIModelTypeName(responseVariant);
                }
                else
                {
                    result = GetIModelTypeName(bodyWireType);
                }
            }

            return result;
        }

        internal static string ResponseServiceFutureGenericParameterString(Response response)
            => ResponseServiceFutureGenericParameterString(response, Settings.Instance);

        private static string ResponseServiceFutureGenericParameterString(Response response, Settings settings)
        {
            string result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                if (ResponseBodyClientType(response) is SequenceType bodySequenceType && ResponseIsPagedResponse(response))
                {
                    result = string.Format(CultureInfo.InvariantCulture, "List<{0}>", GetIModelTypeName(bodySequenceType.ElementType));
                }
                result = ResponseGenericBodyClientTypeString(response);
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response);
            }

            return result;
        }

        internal static string ResponseServiceResponseGenericParameterString(Response response)
            => ResponseServiceResponseGenericParameterString(response, Settings.Instance);

        internal static string ResponseServiceResponseGenericParameterString(Response response, Settings settings)
        {
            string result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                if (ResponseBodyClientType(response) is SequenceType bodySequenceType && (ResponseIsPagedResponse(response) || MethodSimulateAsPagingOperation(ResponseGetParent(response))))
                {
                    result = $"Page<{GetIModelTypeName(bodySequenceType.ElementType)}>";
                }
                else
                {
                    result = ResponseGenericBodyClientTypeString(response, settings);
                }
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response, settings);
            }

            return result;
        }

        internal static string ResponseServiceResponseConcreteParameterString(Response response)
            => ResponseServiceResponseConcreteParameterString(response, Settings.Instance);

        private static string ResponseServiceResponseConcreteParameterString(Response response, Settings settings)
        {
            string result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                if (ResponseBodyClientType(response) is SequenceType bodySequenceType && (ResponseIsPagedResponse(response) || MethodSimulateAsPagingOperation(ResponseGetParent(response))))
                {
                    result = $"{SequenceTypeGetPageImplType(bodySequenceType)}<{GetIModelTypeName(bodySequenceType.ElementType)}>";
                }
                else
                {
                    result = ResponseGenericBodyClientTypeString(response);
                }
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response);
            }

            return result;
        }

        internal static string ResponseClientCallbackTypeString(Response response)
            => ResponseClientCallbackTypeString(response, Settings.Instance);

        internal static string ResponseClientCallbackTypeString(Response response, Settings settings)
        {
            string result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                if (response.Body is SequenceType && ResponseIsPagedResponse(response))
                {
                    result = GetIModelTypeName(ResponseBodyClientType(response));
                }
                else
                {
                    result = ResponseGenericBodyClientTypeString(response);
                }
            }
            else
            {
                result = ResponseGenericBodyClientTypeString(response);
            }

            return result;
        }

        internal static IModelType ResponseBodyWireType(Response response)
        {
            IModelType result = response.Body;
            if (result == null)
            {
                result = new PrimaryTypeJv(KnownPrimaryType.None);
            }
            return result;
        }

        internal static IModelType ResponseHeaderClientType(Response response)
            => GetIModelTypeResponseVariant(response.Headers);

        internal static string ResponseSequenceElementTypeString(Response response)
            => response.Body is SequenceType bodySequenceType ? GetIModelTypeName(bodySequenceType.ElementType) : "Void";

        internal static string ResponseReturnValueWireType(Response response)
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
                        string currentPrimaryTypeName = GetIModelTypeFixedName(currentPrimaryType);
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

        internal static IEnumerable<string> ResponseInterfaceImports(Response response)
            => GetIModelTypeImports(ResponseBodyClientType(response)).Concat(GetIModelTypeImports(ResponseHeaderClientType(response)));

        internal static IEnumerable<string> ResponseImplImports(Response response)
        {
            List<string> imports = new List<string>(ResponseInterfaceImports(response));

            IModelType bodyWireType = ResponseBodyWireType(response);
            imports.AddRange(GetIModelTypeImports(bodyWireType));

            IModelType responseHeaders = response.Headers;
            imports.AddRange(GetIModelTypeImports(responseHeaders));

            string returnValueWireType = ResponseReturnValueWireType(response);
            if (returnValueWireType != null)
            {
                imports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                imports.Add("com.microsoft.rest.v2." + returnValueWireType);
            }

            IModelType bodyClientType = ResponseBodyClientType(response);
            IModelType headerClientType = ResponseHeaderClientType(response);
            if (((bodyWireType == null ? bodyClientType != null : !bodyWireType.StructurallyEquals(bodyClientType)) && GetIModelTypeName(bodyClientType) != "void") ||
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

        internal static bool MethodIsLongRunningOperation(Method method)
            => GetExtensionBool(method?.Extensions, AzureExtensions.LongRunningExtension);

        internal static void MethodTransformPagingGroupedParameter(Method method, IndentedStringBuilder builder, Method nextMethod, bool filterRequired, Settings settings)
        {
            if (IsFluent(settings))
            {
                if (method.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    return;
                }
                var groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                if (nextGroupType.Name == groupedType.Name)
                {
                    return;
                }
                var nextGroupTypeName = CodeNamer.Instance.GetTypeName(nextGroupType.Name) + "Inner";
                if (filterRequired && !groupedType.IsRequired)
                {
                    return;
                }
                if (!groupedType.IsRequired)
                {
                    builder.AppendLine("{0} {1} = null;", nextGroupTypeName, nextGroupType.Name.ToCamelCase());
                    builder.AppendLine("if ({0} != null) {{", groupedType.Name.ToCamelCase());
                    builder.Indent();
                    builder.AppendLine("{0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
                }
                else
                {
                    builder.AppendLine("{1} {0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
                }
                foreach (var outParam in nextMethod.InputParameterTransformation.Select(t => t.OutputParameter))
                {
                    builder.AppendLine("{0}.with{1}({2}.{3}());", nextGroupType.Name.ToCamelCase(), outParam.Name.ToPascalCase(), groupedType.Name.ToCamelCase(), outParam.Name.ToCamelCase());
                }
                if (!groupedType.IsRequired)
                {
                    builder.Outdent().AppendLine(@"}");
                }
            }
            else
            {
                if (method.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    return;
                }
                var groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                if (nextGroupType.Name == groupedType.Name)
                {
                    return;
                }
                var nextGroupTypeName = CodeNamerJva.Instance.GetTypeName(nextGroupType.Name);
                if (filterRequired && !groupedType.IsRequired)
                {
                    return;
                }
                if (!groupedType.IsRequired)
                {
                    builder.AppendLine("{0} {1} = null;", nextGroupTypeName, nextGroupType.Name.ToCamelCase());
                    builder.AppendLine("if ({0} != null) {{", groupedType.Name.ToCamelCase());
                    builder.Indent();
                    builder.AppendLine("{0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
                }
                else
                {
                    builder.AppendLine("{1} {0} = new {1}();", nextGroupType.Name.ToCamelCase(), nextGroupTypeName);
                }
                foreach (var outParam in nextMethod.InputParameterTransformation.Select(t => t.OutputParameter))
                {
                    builder.AppendLine("{0}.with{1}({2}.{3}());", nextGroupType.Name.ToCamelCase(), outParam.Name.ToPascalCase(), groupedType.Name.ToCamelCase(), outParam.Name.ToCamelCase());
                }
                if (!groupedType.IsRequired)
                {
                    builder.Outdent().AppendLine(@"}");
                }
            }
        }

        internal static IEnumerable<string> MethodInterfaceImports(Method method, Settings settings)
        {
            IEnumerable<string> result = Enumerable.Empty<string>();

            if (IsFluent(settings))
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");
                imports.Add("com.microsoft.rest.v2.RestResponse");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                // parameter types
                method.Parameters.OfType<ParameterJv>().ForEach(p => imports.AddRange(p.InterfaceImports));
                // return type
                imports.AddRange(ResponseInterfaceImports(method.ReturnType));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null)
                        {
                            imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                        }
                    });

                if (MethodIsLongRunningOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                }
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                }

                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method) || MethodSimulateAsPagingOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.PagedList");

                    if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                    {
                        imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    }

                    if (!MethodSimulateAsPagingOperation(method))
                    {
                        imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    }

                    if (ResponseBodyClientType(method.ReturnType) is SequenceType pageType)
                    {
                        imports.AddRange(CompositeTypeImportsFluent(SequenceTypeGetPageImplType(pageType), null));
                    }
                }

                result = imports;
            }
            else if (IsAzure(settings))
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");
                imports.Add("com.microsoft.rest.v2.RestResponse");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                // parameter types
                method.Parameters.OfType<ParameterJv>().ForEach(p => imports.AddRange(p.InterfaceImports));
                // return type
                imports.AddRange(ResponseInterfaceImports(method.ReturnType));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null)
                        {
                            imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                        }
                    });

                if (MethodIsLongRunningOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                }
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                }

                result = imports;
            }
            else
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");
                imports.Add("com.microsoft.rest.v2.RestResponse");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                // parameter types
                method.Parameters.OfType<ParameterJv>().ForEach(p => imports.AddRange(p.InterfaceImports));
                // return type
                imports.AddRange(ResponseInterfaceImports(method.ReturnType));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null)
                        {
                            imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                        }
                    });

                result = imports;
            }

            return result;
        }

        internal static IEnumerable<string> MethodImplImports(Method method)
            => MethodImplImports(method, Settings.Instance);

        private static IEnumerable<string> MethodImplImports(Method method, Settings settings)
        {
            IEnumerable<string> result = Enumerable.Empty<string>();

            if (IsFluent(settings))
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("io.reactivex.functions.Function");
                imports.Add("com.microsoft.rest.v2.annotations.Headers");
                imports.Add("com.microsoft.rest.v2.annotations.ExpectedResponses");
                imports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                imports.Add("com.microsoft.rest.v2.annotations.Host");
                imports.Add("com.microsoft.rest.v2.http.HttpClient");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.RetrofitImports));
                // Http verb annotations
                imports.Add("com.microsoft.rest.v2.annotations." + method.HttpMethod.ToString().ToUpperInvariant());
                // response type conversion
                if (method.Responses.Any())
                {
                    imports.Add("com.google.common.reflect.TypeToken");
                }
                // validation
                if (!MethodParametersToValidate(method).IsNullOrEmpty())
                {
                    imports.Add("com.microsoft.rest.v2.Validator");
                }
                // parameters
                MethodLocalParameters(method).Concat(method.LogicalParameters.OfType<ParameterJv>())
                    .ForEach(p => imports.AddRange(p.ClientImplImports));
                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.WireImplImports));
                // return type
                imports.AddRange(ResponseImplImports(method.ReturnType));
                // response type (can be different from return type)
                method.Responses.ForEach(r => imports.AddRange(ResponseImplImports(r.Value)));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null) imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                    });
                // parameterized host
                if (MethodIsParameterizedHost(method, settings))
                {
                    imports.Add("com.microsoft.rest.v2.annotations.HostParam");
                }

                if (MethodIsLongRunningOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                    imports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");
                    imports.Remove("com.microsoft.azure.v2.AzureResponseBuilder");
                    method.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { method.DefaultResponse.Body })
                        .SelectMany(t => GetIModelTypeImports(t))
                        .Where(i => !method.Parameters.Any(p => GetIModelTypeImports(p.ModelType).Contains(i)))
                        .ForEach(i => imports.Remove(i));
                    // return type may have been removed as a side effect
                    imports.AddRange(ResponseImplImports(method.ReturnType));
                }
                string typeName = SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType));
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    imports.Remove("java.util.ArrayList");
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                    imports.AddRange(CompositeTypeImportsAzure(typeName, method.CodeModel));
                }
                else if (MethodIsPagingNonPollingOperation(method))
                {
                    imports.AddRange(CompositeTypeImportsAzure(typeName, method.CodeModel));
                }

                if (MethodOperationExceptionTypeString(method, settings) != "CloudException" && MethodOperationExceptionTypeString(method, settings) != "RestException")
                {
                    imports.RemoveWhere(i => CompositeTypeImportsAzure(MethodOperationExceptionTypeString(method, settings), method.CodeModel).Contains(i));
                    imports.AddRange(CompositeTypeImportsFluent(MethodOperationExceptionTypeString(method, settings), method.CodeModel));
                }
                if (MethodIsLongRunningOperation(method))
                {
                    imports.Remove("com.microsoft.azure.v2.AzureResponseBuilder");
                    method.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { method.DefaultResponse.Body })
                        .SelectMany(t => GetIModelTypeImports(t))
                        .Where(i => !method.Parameters.Any(p => GetIModelTypeImports(p.ModelType).Contains(i)))
                        .ForEach(i => imports.Remove(i));
                    // return type may have been removed as a side effect
                    imports.AddRange(ResponseImplImports(method.ReturnType));
                }

                SequenceType pageType = ResponseBodyClientType(method.ReturnType) as SequenceType;
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method) || MethodSimulateAsPagingOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.PagedList");

                    if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                    {
                        imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    }

                    if (!MethodSimulateAsPagingOperation(method))
                    {
                        imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    }

                    imports.Remove("java.util.ArrayList");
                    imports.Add("com.microsoft.azure.v2.Page");
                    if (pageType != null)
                    {
                        imports.RemoveWhere(i => CompositeTypeImportsAzure(SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType)), method.CodeModel).Contains(i));
                    }
                }

                if (MethodIsPagingNonPollingOperation(method) && pageType != null)
                {
                    imports.RemoveWhere(i => CompositeTypeImportsAzure(SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType)), method.CodeModel).Contains(i));
                }

                result = imports;
            }
            else if (IsAzure(settings))
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("io.reactivex.functions.Function");
                imports.Add("com.microsoft.rest.v2.annotations.Headers");
                imports.Add("com.microsoft.rest.v2.annotations.ExpectedResponses");
                imports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                imports.Add("com.microsoft.rest.v2.annotations.Host");
                imports.Add("com.microsoft.rest.v2.http.HttpClient");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.RetrofitImports));
                // Http verb annotations
                imports.Add("com.microsoft.rest.v2.annotations." + method.HttpMethod.ToString().ToUpperInvariant());
                // response type conversion
                if (method.Responses.Any())
                {
                    imports.Add("com.google.common.reflect.TypeToken");
                }
                // validation
                if (!MethodParametersToValidate(method).IsNullOrEmpty())
                {
                    imports.Add("com.microsoft.rest.v2.Validator");
                }
                // parameters
                MethodLocalParameters(method).Concat(method.LogicalParameters.OfType<ParameterJv>())
                    .ForEach(p => imports.AddRange(p.ClientImplImports));
                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.WireImplImports));
                // return type
                imports.AddRange(ResponseImplImports(method.ReturnType));
                // response type (can be different from return type)
                method.Responses.ForEach(r => imports.AddRange(ResponseImplImports(r.Value)));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null) imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                    });
                // parameterized host
                if (MethodIsParameterizedHost(method, settings))
                {
                    imports.Add("com.microsoft.rest.v2.annotations.HostParam");
                }

                if (MethodIsLongRunningOperation(method))
                {
                    imports.Add("com.microsoft.azure.v2.OperationStatus");
                    imports.Add("com.microsoft.azure.v2.util.ServiceFutureUtil");
                    imports.Remove("com.microsoft.azure.v2.AzureResponseBuilder");
                    method.Responses.Select(r => r.Value.Body).Concat(new IModelType[] { method.DefaultResponse.Body })
                        .SelectMany(t => GetIModelTypeImports(t))
                        .Where(i => !method.Parameters.Any(p => GetIModelTypeImports(p.ModelType).Contains(i)))
                        .ForEach(i => imports.Remove(i));
                    // return type may have been removed as a side effect
                    imports.AddRange(ResponseImplImports(method.ReturnType));
                }
                string typeName = SequenceTypeGetPageImplType(ResponseBodyClientType(method.ReturnType));
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    imports.Remove("java.util.ArrayList");
                    imports.Remove("com.microsoft.rest.v2.ServiceCallback");
                    imports.Add("com.microsoft.azure.v2.ListOperationCallback");
                    imports.Add("com.microsoft.azure.v2.Page");
                    imports.Add("com.microsoft.azure.v2.PagedList");
                    imports.AddRange(CompositeTypeImportsAzure(typeName, method.CodeModel));
                }
                else if (MethodIsPagingNonPollingOperation(method))
                {
                    imports.AddRange(CompositeTypeImportsAzure(typeName, method.CodeModel));
                }

                result = imports;
            }
            else
            {
                HashSet<string> imports = new HashSet<string>();
                // static imports
                imports.Add("io.reactivex.Observable");
                imports.Add("io.reactivex.Single");
                imports.Add("io.reactivex.functions.Function");
                imports.Add("com.microsoft.rest.v2.annotations.Headers");
                imports.Add("com.microsoft.rest.v2.annotations.ExpectedResponses");
                imports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                imports.Add("com.microsoft.rest.v2.annotations.Host");
                imports.Add("com.microsoft.rest.v2.http.HttpClient");
                imports.Add("com.microsoft.rest.v2.ServiceFuture");
                imports.Add("com.microsoft.rest.v2.ServiceCallback");

                if (method.ReturnType.Body == null)
                {
                    imports.Add("io.reactivex.Completable");
                }
                else
                {
                    imports.Add("io.reactivex.Maybe");
                }

                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.RetrofitImports));
                // Http verb annotations
                imports.Add("com.microsoft.rest.v2.annotations." + method.HttpMethod.ToString().ToUpperInvariant());
                // response type conversion
                if (method.Responses.Any())
                {
                    imports.Add("com.google.common.reflect.TypeToken");
                }
                // validation
                if (!MethodParametersToValidate(method).IsNullOrEmpty())
                {
                    imports.Add("com.microsoft.rest.v2.Validator");
                }
                // parameters
                MethodLocalParameters(method).Concat(method.LogicalParameters.OfType<ParameterJv>())
                    .ForEach(p => imports.AddRange(p.ClientImplImports));
                MethodRetrofitParameters(method, settings).ForEach(p => imports.AddRange(p.WireImplImports));
                // return type
                imports.AddRange(ResponseImplImports(method.ReturnType));
                // response type (can be different from return type)
                method.Responses.ForEach(r => imports.AddRange(ResponseImplImports(r.Value)));
                // exceptions
                MethodExceptionString(method, settings).Split(new string[] { ", " }, StringSplitOptions.RemoveEmptyEntries)
                    .ForEach(ex =>
                    {
                        string exceptionImport = CodeNamerJv.GetJavaException(ex, method.CodeModel);
                        if (exceptionImport != null) imports.Add(CodeNamerJv.GetJavaException(ex, method.CodeModel));
                    });
                // parameterized host
                if (MethodIsParameterizedHost(method, settings))
                {
                    imports.Add("com.microsoft.rest.v2.annotations.HostParam");
                }

                result = imports;
            }

            return result;
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

        private static MethodType GetMethodType(Method method)
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
                    && MethodHasSequenceType(method.ReturnType.Body))
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

        internal static bool MethodSimulateAsPagingOperation(Method method)
        {
            bool result = false;

            string wellKnownMethodName = MethodWellKnownMethodName(method);
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                MethodType methodType = GetMethodType(method);
                if (methodType == MethodType.ListByResourceGroup || methodType == MethodType.ListBySubscription)
                {
                    result = true;
                }
            }

            return result;
        }

        internal static string MethodName(Method method)
        {
            string result = method.Name;

            string wellKnownMethodName = MethodWellKnownMethodName(method);
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                IParent methodParent = method.Parent;
                string uniqueName = CodeNamer.Instance.GetUnique(wellKnownMethodName, method, methodParent.IdentifiersInScope, methodParent.Children.Except(method.SingleItemAsEnumerable()));
                if (uniqueName != result)
                {
                    result = uniqueName;
                }
            }

            return result;
        }

        private static string MethodWellKnownMethodName(Method method)
        {
            string result = null;

            MethodGroup methodGroup = method.MethodGroup;
            if (!string.IsNullOrEmpty(methodGroup.Name))
            {
                MethodType methodType = GetMethodType(method);
                if (methodType != MethodType.Other)
                {
                    if (methodGroup.Methods.Count(methodGroupMethod => GetMethodType(methodGroupMethod) == methodType) == 1)
                    {
                        switch (methodType)
                        {
                            case MethodType.ListBySubscription:
                                result = WellKnowMethodNames.List;
                                break;

                            case MethodType.ListByResourceGroup:
                                result = WellKnowMethodNames.ListByResourceGroup;
                                break;

                            case MethodType.Delete:
                                result = WellKnowMethodNames.Delete;
                                break;

                            case MethodType.Get:
                                result = WellKnowMethodNames.GetByResourceGroup;
                                break;

                            default:
                                throw new Exception("Flow should not hit this statement.");
                        }
                    }
                }
            }

            return result;
        }

        internal static bool MethodIsPagingNextOperation(Method method)
            => GetExtensionBool(method?.Extensions, "nextLinkMethod");

        internal static bool MethodIsPagingOperation(Method method)
            => method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                method.Extensions[AzureExtensions.PageableExtension] != null &&
                !MethodIsPagingNextOperation(method);

        internal static bool MethodIsPagingNonPollingOperation(Method method)
            => method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                    method.Extensions[AzureExtensions.PageableExtension] == null &&
                    !MethodIsPagingNextOperation(method);

        internal static bool MethodShouldGenerateBeginRestResponseMethod(Method method, Settings settings)
        {
            bool result;

            if (IsFluentOrAzure(settings))
            {
                result = !MethodIsLongRunningOperation(method) && !MethodIsPagingOperation(method) && !MethodIsPagingNextOperation(method);
            }
            else
            {
                result = true;
            }

            return result;
        }

        internal static string MethodOperationExceptionTypeString(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                if (method.DefaultResponse.Body == null || GetIModelTypeName(method.DefaultResponse.Body) == "CloudError")
                {
                    result = "CloudException";
                }
                else if (method.DefaultResponse.Body is CompositeType)
                {
                    CompositeType type = method.DefaultResponse.Body as CompositeType;
                    result = CompositeTypeExceptionTypeDefinitionName(type);
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
                    result = CompositeTypeExceptionTypeDefinitionName(type);
                }
                else
                {
                    result = "RestException";
                }
            }

            return result;
        }

        internal static IEnumerable<ParameterJv> MethodRetrofitParameters(Method method, Settings settings)
        {
            IEnumerable<ParameterJv> result;

            if (IsFluentOrAzure(settings))
            {
                List<ParameterJv> parameters = MethodJvRetrofitParameters(method).ToList();

                if (MethodIsPagingNextOperation(method))
                {
                    parameters.RemoveAll(p => p.Location == ParameterLocation.Path);

                    var nextUrlParam = new ParameterJv
                    {
                        Name = "nextUrl",
                        SerializedName = "nextUrl",
                        ModelType = DependencyInjection.New<PrimaryType>(KnownPrimaryType.String),
                        Documentation = "The URL to get the next page of items.",
                        Location = ParameterLocation.Path,
                        IsRequired = true
                    };
                    nextUrlParam.Extensions.Add(AzureExtensions.SkipUrlEncodingExtension, true);
                    parameters.Insert(0, nextUrlParam);
                }

                result = parameters;
            }
            else
            {
                result = MethodJvRetrofitParameters(method);
            }

            return result;
        }

        private static IEnumerable<ParameterJv> MethodJvRetrofitParameters(Method method)
            => method.LogicalParameters
                .OfType<ParameterJv>()
                .Where(p => p.Location != ParameterLocation.None);

        private static string MethodParameterApiDeclaration(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                bool shouldGenerateXmlSerialization = method.CodeModel.ShouldGenerateXmlSerialization;

                List<string> declarations = new List<string>();
                foreach (ParameterJv parameter in MethodOrderedRetrofitParameters(method, settings))
                {
                    StringBuilder declarationBuilder = new StringBuilder();
                    if (method.Url.Contains("{" + parameter.Name + "}"))
                    {
                        parameter.Location = ParameterLocation.Path;
                    }

                    var name = parameter.SerializedName;
                    if (parameter.Extensions.ContainsKey("hostParameter"))
                    {
                        declarationBuilder.Append($"@HostParam(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Path ||
                        parameter.Location == ParameterLocation.Query ||
                        parameter.Location == ParameterLocation.Header)
                    {
                        var location = parameter.Location;
                        declarationBuilder.Append($"@{location}Param(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Body)
                    {
                        declarationBuilder.Append($"@BodyParam(\"{method.RequestContentType}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.FormData)
                    {
                        declarationBuilder.Append($"/* @Part(\"{name}\") not supported by RestProxy */");
                    }

                    var declarativeName = parameter.ClientProperty != null ? parameter.ClientProperty.Name : parameter.Name;

                    bool shouldUseXmlListWrapper = shouldGenerateXmlSerialization && (parameter.ModelType is SequenceType);
                    if (shouldUseXmlListWrapper)
                    {
                        declarationBuilder.Append(parameter.ModelType.XmlName + "Wrapper");
                    }
                    else
                    {
                        declarationBuilder.Append(GetIModelTypeName(parameter.WireType));
                    }

                    declarationBuilder.Append(" " + declarativeName);
                    declarations.Add(declarationBuilder.ToString());
                }

                string declaration = string.Join(", ", declarations);

                foreach (ParameterJv parameter in MethodRetrofitParameters(method, settings).Where(p =>
                    p.Location == ParameterLocation.Path || p.Location == ParameterLocation.Query))
                {
                    if (GetExtensionBool(parameter?.Extensions, AzureExtensions.SkipUrlEncodingExtension))
                    {
                        declaration = declaration.Replace(
                            string.Format(CultureInfo.InvariantCulture, "@{0}Param(\"{1}\")", parameter.Location.ToString(), parameter.SerializedName),
                            string.Format(CultureInfo.InvariantCulture, "@{0}Param(value = \"{1}\", encoded = true)", parameter.Location.ToString(), parameter.SerializedName));
                    }
                }
                result = declaration;
            }
            else
            {
                bool shouldGenerateXmlSerialization = method.CodeModel.ShouldGenerateXmlSerialization;

                List<string> declarations = new List<string>();
                foreach (ParameterJv parameter in MethodOrderedRetrofitParameters(method, settings))
                {
                    StringBuilder declarationBuilder = new StringBuilder();
                    if (method.Url.Contains("{" + parameter.Name + "}"))
                    {
                        parameter.Location = ParameterLocation.Path;
                    }

                    var name = parameter.SerializedName;
                    if (parameter.Extensions.ContainsKey("hostParameter"))
                    {
                        declarationBuilder.Append($"@HostParam(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Path ||
                        parameter.Location == ParameterLocation.Query ||
                        parameter.Location == ParameterLocation.Header)
                    {
                        var location = parameter.Location;
                        declarationBuilder.Append($"@{location}Param(\"{name}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.Body)
                    {
                        declarationBuilder.Append($"@BodyParam(\"{method.RequestContentType}\") ");
                    }
                    else if (parameter.Location == ParameterLocation.FormData)
                    {
                        declarationBuilder.Append($"/* @Part(\"{name}\") not supported by RestProxy */");
                    }

                    var declarativeName = parameter.ClientProperty != null ? parameter.ClientProperty.Name : parameter.Name;

                    bool shouldUseXmlListWrapper = shouldGenerateXmlSerialization && (parameter.ModelType is SequenceType);
                    if (shouldUseXmlListWrapper)
                    {
                        declarationBuilder.Append(parameter.ModelType.XmlName + "Wrapper");
                    }
                    else
                    {
                        declarationBuilder.Append(GetIModelTypeName(parameter.WireType));
                    }

                    declarationBuilder.Append(" " + declarativeName);
                    declarations.Add(declarationBuilder.ToString());
                }

                result = string.Join(", ", declarations);
            }

            return result;
        }

        internal static string MethodNextUrlConstructor(Method method)
        {
            StringBuilder builder = new StringBuilder("String.format(");
            Regex regex = new Regex("{\\w+}");

            string methodUrl = method.Url;
            MatchCollection matches = regex.Matches(methodUrl);
            builder.Append("\"").Append(regex.Replace(methodUrl, "%s").TrimStart('/')).Append("\"");

            IEnumerable<ParameterJv> retrofitParameters = MethodJvRetrofitParameters(method);
            foreach (Match match in matches)
            {
                string serializedName = match.Value.Trim('{', '}');
                builder.Append(", " + retrofitParameters.First(p => p.SerializedName == serializedName).WireName);
            }
            return builder.Append(")").ToString();
        }

        internal static string MethodParameterDeclaration(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    List<string> declarations = new List<string>();
                    foreach (ParameterJv parameter in MethodLocalParameters(method).Where(p => !p.IsConstant))
                    {
                        declarations.Add($"final {GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType))} {parameter.Name}");
                    }

                    result = string.Join(", ", declarations);
                }
                else
                {
                    List<string> declarations = new List<string>();
                    foreach (var parameter in MethodLocalParameters(method).Where(p => !p.IsConstant))
                    {
                        declarations.Add(GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                    }

                    result = string.Join(", ", declarations);
                }
            }
            else
            {
                List<string> declarations = new List<string>();
                foreach (var parameter in MethodLocalParameters(method).Where(p => !p.IsConstant))
                {
                    declarations.Add(GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                }

                result = string.Join(", ", declarations);
            }

            return result;
        }

        private static string MethodRequiredParameterDeclaration(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    List<string> declarations = new List<string>();
                    foreach (ParameterJv parameter in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                    {
                        declarations.Add("final " + GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                    }
                    result = string.Join(", ", declarations);
                }
                else
                {
                    List<string> declarations = new List<string>();
                    foreach (var parameter in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                    {
                        declarations.Add(GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                    }
                    result = string.Join(", ", declarations);
                }
            }
            else
            {
                List<string> declarations = new List<string>();
                foreach (var parameter in MethodLocalParameters(method).Where(p => !p.IsConstant && p.IsRequired))
                {
                    declarations.Add(GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name);
                }
                result = string.Join(", ", declarations);
            }

            return result;
        }

        private static string MethodParameterDeclarationWithCallback(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                string parameters = MethodParameterDeclaration(method, settings);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }

                if (MethodIsPagingOperation(method))
                {
                    parameters += $"final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType)}> serviceCallback";
                }
                else if (MethodIsPagingNextOperation(method))
                {
                    parameters += $"final ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> serviceFuture, final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType)}> serviceCallback";
                }
                else
                {
                    parameters += $"final ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType)}> serviceCallback";
                }

                result = parameters;
            }
            else
            {
                string parameters = MethodParameterDeclaration(method, settings);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += $"final ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType)}> serviceCallback";
                result = parameters;
            }

            return result;
        }

        private static string MethodRequiredParameterDeclarationWithCallback(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                string parameters = MethodRequiredParameterDeclaration(method, settings);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }

                if (MethodIsPagingOperation(method))
                {
                    parameters += $"final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType)}> serviceCallback";
                }
                else if (MethodIsPagingNextOperation(method))
                {
                    parameters += $"final ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> serviceFuture, final ListOperationCallback<{ResponseSequenceElementTypeString(method.ReturnType)}> serviceCallback";
                }
                else
                {
                    parameters += $"final ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType)}> serviceCallback";
                }
                result = parameters;
            }
            else
            {
                string parameters = MethodRequiredParameterDeclaration(method, settings);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += $"final ServiceCallback<{ResponseGenericBodyClientTypeString(method.ReturnType)}> serviceCallback";
                result = parameters;
            }

            return result;
        }

        private static string MethodParameterInvocationWithCallback(Method method, Settings settings)
        {
            string result;

            if (IsFluentOrAzure(settings))
            {
                string parameters = MethodParameterInvocation(method);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += "serviceCallback";
                if (MethodIsPagingOperation(method) || MethodIsPagingNextOperation(method))
                {
                    parameters = parameters.Replace("serviceCallback", "serviceFuture, serviceCallback");
                }
                result = parameters;
            }
            else
            {
                string parameters = MethodParameterInvocation(method);
                if (!parameters.IsNullOrEmpty())
                {
                    parameters += ", ";
                }
                parameters += "serviceCallback";
                result = parameters;
            }

            return result;
        }

        private static bool MethodIsParameterizedHost(Method method, Settings settings)
        {
            bool result;

            bool containsParameterizedHostExtension = method?.CodeModel?.Extensions?.ContainsKey(SwaggerExtensions.ParameterizedHostExtension) ?? false;
            if (IsFluent(settings) || IsAzure(settings))
            {
                result = containsParameterizedHostExtension && !MethodIsPagingNextOperation(method);
            }
            else
            {
                result = containsParameterizedHostExtension;
            }

            return result;
        }

        internal static IEnumerable<string> MethodExceptions(Method method, Settings settings)
        {
            IEnumerable<string> result;

            if (IsFluent(settings) || IsAzure(settings))
            {
                List<string> exceptions = new List<string>();
                exceptions.Add(MethodOperationExceptionTypeString(method, settings));
                exceptions.Add("IOException");
                if (MethodRequiredNullableParameters(method).Any())
                {
                    exceptions.Add("IllegalArgumentException");
                }
                if (MethodIsLongRunningOperation(method))
                {
                    exceptions.Add("InterruptedException");
                }
                result = exceptions;
            }
            else
            {
                List<string> exceptions = new List<string>();
                exceptions.Add(MethodOperationExceptionTypeString(method, settings));
                exceptions.Add("IOException");
                if (MethodRequiredNullableParameters(method).Any())
                {
                    exceptions.Add("IllegalArgumentException");
                }
                result = exceptions;
            }

            return result;
        }

        private static string MethodExceptionString(Method method, Settings settings)
            => string.Join(", ", MethodExceptions(method, settings));

        internal static string MethodReturnTypeResponseName(Method method)
            => GetIModelTypeName(ResponseBodyClientType(method.ReturnType)?.ServiceResponseVariant());

        private static string MethodPagingGroupedParameterTransformation(Method method, bool filterRequired, Settings settings)
        {
            var builder = new IndentedStringBuilder();
            if (MethodIsPagingOperation(method))
            {
                string invocation;
                Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, out invocation);
                MethodTransformPagingGroupedParameter(method, builder, nextMethod, filterRequired, settings);
            }
            return builder.ToString();
        }

        private static string MethodNextMethodParameterInvocation(Method method, bool filterRequired)
        {
            string invocation;
            Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, out invocation);
            if (filterRequired)
            {
                if (method.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                {
                    return string.Join(", ", MethodLocalParameters(nextMethod).Select(p => p.IsRequired ? (string)p.Name : "null"));
                }
                var groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                var nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                List<string> invocations = new List<string>();
                foreach (var parameter in MethodLocalParameters(nextMethod))
                {
                    if (parameter.IsRequired)
                    {
                        invocations.Add(parameter.Name);
                    }
                    else if (parameter.Name == nextGroupType.Name && groupedType.IsRequired)
                    {
                        invocations.Add(parameter.Name);
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
                return MethodParameterInvocation(nextMethod);
            }
        }

        private static string MethodPagingNextPageLinkParameterName(Method method)
        {
            string invocation;
            Method nextMethod = MethodGetPagingNextMethodWithInvocation(method, out invocation);
            return nextMethod.Parameters.First(p => p.Name.ToString().StartsWith("next", StringComparison.OrdinalIgnoreCase)).Name;
        }

        internal static Method MethodGetPagingNextMethodWithInvocation(Method method, out string invocation, bool async = false, bool singlePage = true)
        {
            string methodSuffixString = "";
            if (singlePage)
            {
                methodSuffixString = "SinglePage";
            }
            if (MethodIsPagingNextOperation(method))
            {
                invocation = MethodName(method) + methodSuffixString + (async ? "Async" : "");
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
                    && m.Name.ToString().Equals(name, StringComparison.OrdinalIgnoreCase));
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

        private static string MethodGetPagingNextMethodInvocation(Method method, bool singlePage = true)
        {
            string invocation;
            MethodGetPagingNextMethodWithInvocation(method, out invocation, true, singlePage);
            return invocation;
        }

        private static IEnumerable<ParameterJv> MethodOrderedRetrofitParameters(Method method, Settings settings)
        {
            IEnumerable<ParameterJv> retrofitParameters = MethodRetrofitParameters(method, settings);
            return retrofitParameters.Where(p => p.Location == ParameterLocation.Path)
                .Union(retrofitParameters.Where(p => p.Location != ParameterLocation.Path));
        }

        private static string MethodParameterInvocation(Method method)
            => string.Join(", ", MethodLocalParameters(method).Where(p => !p.IsConstant).Select(p => p.Name));

        private static string MethodRequiredParameterInvocation(Method methodJv)
        {
            List<string> invocations = new List<string>();
            foreach (ParameterJv parameter in MethodLocalParameters(methodJv))
            {
                if (parameter.IsRequired && !parameter.IsConstant)
                {
                    invocations.Add(parameter.Name);
                }
            }
            return string.Join(", ", invocations);
        }

        private static string MethodParameterApiInvocation(Method method, Settings settings)
        {
            bool shouldUseXmlSerialization = method.CodeModel.ShouldGenerateXmlSerialization;

            IEnumerable<string> arguments = MethodOrderedRetrofitParameters(method, settings)
                .Select(parameter => shouldUseXmlSerialization && (parameter.WireType is SequenceType)
                    ? $"new {parameter.WireType.XmlName}Wrapper({parameter.WireName})"
                    : parameter.WireName);

            return string.Join(", ", arguments);
        }

        private static string MethodJavadoc(Method method, IEnumerable<ParameterJv> parameters, IEnumerable<string> exceptionsDocumentation, string optionalReturnDocumentation)
        {
            IndentedStringBuilder builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine("/**");
            if (!string.IsNullOrEmpty(method.Summary))
            {
                builder.AppendLine(" * " + method.Summary.EscapeXmlComment().Period());
            }

            if (!string.IsNullOrEmpty(method.Description))
            {
                builder.AppendLine(" * " + method.Description.EscapeXmlComment().Period());
            }

            builder.AppendLine(" * ");

            foreach (ParameterJv param in parameters)
            {
                string paramDoc = param.Documentation.Else($"the {GetIModelTypeName(param.ModelType)} value").EscapeXmlComment().Trim();
                builder.AppendLine($" * @param {param.Name} {paramDoc}");
            }

            foreach (string exception in exceptionsDocumentation)
            {
                builder.AppendLine(exception);
            }

            if (!string.IsNullOrEmpty(optionalReturnDocumentation))
            {
                builder.AppendLine($" * @return {optionalReturnDocumentation}");
            }

            builder.AppendLine(" */");
            return builder.ToString();
        }

        internal static string MethodParameterDeclaration(IEnumerable<ParameterJv> parameters)
            => string.Join(", ", parameters.Select(parameter => GetIModelTypeName(GetIModelTypeParameterVariant(parameter.ClientType)) + " " + parameter.Name));

        internal static string MethodArguments(IEnumerable<ParameterJv> parameters)
            => string.Join(", ", parameters.Select(parameter => parameter.Name.Value));

        private static IEnumerable<string> MethodAsyncExceptionDocumentation()
            => new[] { " * @throws IllegalArgumentException thrown if parameters fail the validation" };

        internal static string MethodRestResponseHeadersName(Method method)
            => method.ReturnType.Headers == null
                ? "Void"
                : GetIModelTypeName(ResponseHeaderClientType(method.ReturnType));

        internal static string MethodRestResponseAbstractBodyName(Method method)
            => method.ReturnType.Body == null
                ? "Void"
                : ResponseServiceResponseGenericParameterString(method.ReturnType);

        internal static string MethodRestResponseConcreteBodyName(Method method)
            => method.ReturnType.Body == null
                ? "Void"
                : ResponseServiceResponseConcreteParameterString(method.ReturnType);

        internal static string MethodRestResponseAbstractTypeName(Method method)
            => $"RestResponse<{MethodRestResponseHeadersName(method)}, {MethodRestResponseAbstractBodyName(method)}>";

        private static string MethodRestResponseConcreteTypeName(Method method)
            => $"RestResponse<{MethodRestResponseHeadersName(method)}, {MethodRestResponseConcreteBodyName(method)}>";

        private static string MethodObservableReturnDocumentation(Method method)
            => string.IsNullOrEmpty(MethodReturnTypeResponseName(method)) ? "" : $"a {{@link Single}} emitting the {MethodRestResponseAbstractTypeName(method)} object";

        private static string MethodObservableRestResponseImpl(Method method, IEnumerable<ParameterJv> parameters, bool takeOnlyRequiredParameters, Settings settings)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public Single<{MethodRestResponseAbstractTypeName(method)}> {MethodName(method)}WithRestResponseAsync({MethodParameterDeclaration(parameters)}) {{");
            builder.Indent();

            // Check presence of required parameters
            foreach (Parameter param in MethodRequiredNullableParameters(method))
            {
                builder.AppendLine($"if ({param.Name} == null) {{");
                builder.Indent();
                builder.AppendLine($"throw new IllegalArgumentException(\"Parameter {param.Name} is required and cannot be null.\");");
                builder.Outdent();
                builder.AppendLine("}");
            }

            foreach (ParameterJv param in MethodLocalParameters(method))
            {
                IModelType parameterClientType = param.ClientType;
                string parameterClientTypeName = GetIModelTypeName(parameterClientType);
                if (takeOnlyRequiredParameters && !param.IsRequired)
                {
                    builder.AppendLine($"final {parameterClientTypeName} {param.Name} = {parameterClientType.GetDefaultValue(method) ?? "null"};");
                }
                else if (param.IsConstant)
                {
                    builder.AppendLine($"final {parameterClientTypeName} {param.Name} = {param.DefaultValue ?? "null"};");
                }
            }

            foreach (Parameter param in MethodParametersToValidate(method))
            {
                builder.AppendLine($"Validator.validate({param.Name});");
            }

            string mappings = MethodBuildInputMappings(method, takeOnlyRequiredParameters);
            if (!string.IsNullOrWhiteSpace(mappings))
            {
                builder.AppendLine(mappings.Trim());
            }

            string parameterConversion = MethodParameterConversion(method, settings);
            if (!string.IsNullOrWhiteSpace(parameterConversion))
            {
                builder.AppendLine(parameterConversion.Trim());
            }

            builder.AppendLine($"return service.{MethodName(method)}({MethodParameterApiInvocation(method, settings)});");
            builder.Outdent();
            builder.AppendLine("}");

            return builder.ToString();
        }

        private static string MethodObservableImpl(Method method, IEnumerable<ParameterJv> parameters)
        {
            string returnType;
            if (method.ReturnType.Body == null)
            {
                returnType = "Completable";
            }
            else
            {
                returnType = $"Maybe<{ResponseGenericBodyClientTypeString(method.ReturnType)}>";
            }

            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            builder.AppendLine($"public {returnType} {MethodName(method)}Async({MethodParameterDeclaration(parameters)}) {{");
            builder.Indent();
            builder.AppendLine($"return {MethodName(method)}WithRestResponseAsync({MethodArguments(parameters)})");
            builder.Indent();

            if (method.ReturnType.Body == null)
            {
                builder.AppendLine($".toCompletable();");
            }
            else
            {
                builder.AppendLine($".flatMapMaybe(new Function<{MethodRestResponseAbstractTypeName(method)}, Maybe<{ResponseGenericBodyClientTypeString(method.ReturnType)}>>() {{");
                builder.Indent();
                builder.AppendLine($"public Maybe<{ResponseGenericBodyClientTypeString(method.ReturnType)}> apply({MethodRestResponseAbstractTypeName(method)} restResponse) {{");
                builder.Indent();
                builder.AppendLine("if (restResponse.body() == null) {");
                builder.Indent();
                builder.AppendLine("return Maybe.empty();");
                builder.Outdent();
                builder.AppendLine("} else {");
                builder.Indent();
                builder.AppendLine("return Maybe.just(restResponse.body());");
                builder.Outdent();
                builder.AppendLine("}");
                builder.Outdent();
                builder.AppendLine("}");
                builder.Outdent();
                builder.AppendLine("});");
            }

            builder.Outdent();
            builder.AppendLine("}");
            return builder.ToString();
        }

        private static string MethodCallbackImpl(Method method, IEnumerable<ParameterJv> parameters, ParameterJv callbackParam)
        {
            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            string methodName = MethodName(method);
            builder.AppendLine($"public ServiceFuture<{ResponseServiceFutureGenericParameterString(method.ReturnType)}> {methodName}Async({MethodParameterDeclaration(parameters.ConcatSingleItem(callbackParam))}) {{");
            builder.Indent();
            builder.AppendLine($"return ServiceFuture.fromBody({methodName}Async({MethodArguments(parameters)}), {callbackParam.Name});");
            builder.Outdent();
            builder.AppendLine("}");

            return builder.ToString();
        }

        private static string[] MethodSyncExceptionDocumentation(Method method, Settings settings)
            => new[]
            {
                " * @throws IllegalArgumentException thrown if parameters fail the validation",
                $" * @throws {MethodOperationExceptionTypeString(method, settings)} thrown if the request is rejected by server",
                " * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent"
            };

        private static string MethodSyncReturnDocumentation(Method method)
            => string.IsNullOrEmpty(MethodReturnTypeResponseName(method)) && MethodReturnTypeResponseName(method) != "void"
                ? ""
                : $"the {MethodReturnTypeResponseName(method).EscapeXmlComment()} object if successful.";

        private static string MethodSyncImpl(Method method, IEnumerable<ParameterJv> parameters)
        {
            string paramString = MethodParameterDeclaration(parameters);
            string argsString = MethodArguments(parameters);

            var builder = new IndentedStringBuilder(IndentedStringBuilder.FourSpaces);
            string methodName = MethodName(method);
            builder.AppendLine($"public {MethodReturnTypeResponseName(method)} {methodName}({paramString}) {{");
            builder.Indent();

            if (GetIModelTypeName(GetIModelTypeResponseVariant(ResponseBodyClientType(method.ReturnType))) == "void")
            {
                builder.AppendLine($"{methodName}Async({argsString}).blockingAwait();");
            }
            else
            {
                builder.AppendLine($"return {methodName}Async({argsString}).blockingGet();");
            }

            builder.Outdent();
            builder.AppendLine("}");

            return builder.ToString();
        }

        internal static string MethodClientReference(Method method)
            => method.Group.IsNullOrEmpty() ? "this" : "this.client";

        private static string MethodParameterConversion(Method method, Settings settings)
        {
            IndentedStringBuilder builder = new IndentedStringBuilder();
            string methodClientReference = MethodClientReference(method);
            foreach (ParameterJv p in MethodRetrofitParameters(method, settings))
            {
                if (p.NeedsConversion)
                {
                    builder.Append(p.ConvertToWireType(p.Name, methodClientReference));
                }
            }
            return builder.ToString();
        }

        private static string MethodRequiredParameterConversion(Method method, Settings settings)
        {
            IndentedStringBuilder builder = new IndentedStringBuilder();
            string methodClientReference = MethodClientReference(method);
            foreach (var p in MethodRetrofitParameters(method, settings).Where(p => p.IsRequired))
            {
                if (p.NeedsConversion)
                {
                    builder.Append(p.ConvertToWireType(p.Name, methodClientReference));
                }
            }
            return builder.ToString();
        }

        internal static string MethodBuildInputMappings(Method method, bool filterRequired = false)
        {
            IndentedStringBuilder builder = new IndentedStringBuilder();
            foreach (ParameterTransformation transformation in method.InputParameterTransformation)
            {
                var outParamName = transformation.OutputParameter.Name;
                while (method.Parameters.Any(p => p.Name == outParamName))
                {
                    outParamName += "1";
                }
                transformation.OutputParameter.Name = outParamName;
                string nullCheck = MethodBuildNullCheckExpression(transformation);
                bool conditionalAssignment = !string.IsNullOrEmpty(nullCheck) && !transformation.OutputParameter.IsRequired && !filterRequired;
                if (conditionalAssignment)
                {
                    builder.AppendLine("{0} {1} = null;",
                            GetIModelTypeName(GetIModelTypeParameterVariant(((ParameterJv)transformation.OutputParameter).ClientType)),
                            outParamName);
                    builder.AppendLine("if ({0}) {{", nullCheck).Indent();
                }

                if (transformation.ParameterMappings.Any(m => !string.IsNullOrEmpty(m.OutputParameterProperty)) &&
                    transformation.OutputParameter.ModelType is CompositeType)
                {
                    builder.AppendLine("{0}{1} = new {2}();",
                        !conditionalAssignment ? GetIModelTypeName(GetIModelTypeParameterVariant(((ParameterJv)transformation.OutputParameter).ClientType)) + " " : "",
                        outParamName,
                        GetIModelTypeName(transformation.OutputParameter.ModelType));
                }

                foreach (var mapping in transformation.ParameterMappings)
                {
                    builder.AppendLine("{0}{1}{2};",
                        !conditionalAssignment && !(transformation.OutputParameter.ModelType is CompositeType) ?
                            GetIModelTypeName(GetIModelTypeParameterVariant(((ParameterJv)transformation.OutputParameter).ClientType)) + " " : "",
                        outParamName,
                        MethodGetMapping(mapping, filterRequired));
                }

                if (conditionalAssignment)
                {
                    builder.Outdent()
                       .AppendLine("}");
                }
            }

            return builder.ToString();
        }

        private static string MethodGetMapping(ParameterMapping mapping, bool filterRequired)
        {
            string inputPath = mapping.InputParameter.Name;
            if (mapping.InputParameterProperty != null)
            {
                inputPath += "." + CodeNamer.Instance.CamelCase(mapping.InputParameterProperty) + "()";
            }
            if (filterRequired && !mapping.InputParameter.IsRequired)
            {
                inputPath = "null";
            }

            string outputPath = "";
            if (mapping.OutputParameterProperty != null)
            {
                outputPath += ".with" + CodeNamer.Instance.PascalCase(mapping.OutputParameterProperty);
                return string.Format(CultureInfo.InvariantCulture, "{0}({1})", outputPath, inputPath);
            }
            else
            {
                return string.Format(CultureInfo.InvariantCulture, "{0} = {1}", outputPath, inputPath);
            }
        }

        private static string MethodBuildNullCheckExpression(ParameterTransformation transformation)
        {
            if (transformation == null)
            {
                throw new ArgumentNullException("transformation");
            }

            return string.Join(" || ",
                transformation.ParameterMappings
                    .Where(m => !m.InputParameter.IsRequired)
                    .Select(m => m.InputParameter.Name + " != null"));
        }

        private static IEnumerable<ParameterJv> MethodRequiredNullableParameters(Method method)
        {
            foreach (ParameterJv param in method.Parameters)
            {
                if (!param.ModelType.IsPrimaryType(KnownPrimaryType.Int) &&
                    !param.ModelType.IsPrimaryType(KnownPrimaryType.Double) &&
                    !param.ModelType.IsPrimaryType(KnownPrimaryType.Boolean) &&
                    !param.ModelType.IsPrimaryType(KnownPrimaryType.Long) &&
                    !param.ModelType.IsPrimaryType(KnownPrimaryType.UnixTime) &&
                    !param.IsConstant && param.IsRequired)
                {
                    yield return param;
                }
            }
        }

        private static IEnumerable<ParameterJv> MethodParametersToValidate(Method method)
        {
            foreach (ParameterJv param in method.Parameters)
            {
                if (param.ModelType is PrimaryType ||
                    param.ModelType is EnumType ||
                    param.IsConstant)
                {
                    continue;
                }
                yield return param;
            }
        }

        private static IEnumerable<ParameterJv> MethodLocalParameters(Method method)
        {
            //Omit parameter-group properties for now since Java doesn't support them yet
            var par = method.Parameters
                .OfType<ParameterJv>()
                .Where(p => p != null && !p.IsClientProperty && !string.IsNullOrWhiteSpace(p.Name))
                .OrderBy(item => !item.IsRequired)
                .ToList();
            return par;
        }

        private static string MethodExpectedResponsesAnnotation(Method method)
        {
            string result;

            if (method.Responses.Count == 0)
            {
                result = "";
            }
            else
            {
                string annotationArgs = string.Join(", ", method.Responses.Keys.OrderBy(k => k).Select(k => k.ToString("D")));
                result = $"@ExpectedResponses({{{annotationArgs}}})";
            }

            return result;
        }

        private static bool MethodHasSequenceType(IModelType mt)
        {
            if (mt is SequenceType)
            {
                return true;
            }

            if (mt is CompositeType ct)
            {
                return GetCompositeTypeProperties(ct).Any(p => MethodHasSequenceType(GetPropertyModelType(p)));
            }

            return false;
        }
    }
}
