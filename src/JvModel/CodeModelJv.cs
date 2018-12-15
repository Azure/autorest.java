using System;
using System.Collections.Generic;
using System.Linq;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java;
using AutoRest.Core.Utilities;
using System.Text.RegularExpressions;

namespace AutoRest.Java.Model
{
    public class CodeModelJv : CodeModel
    {
        private static readonly ClassType[] nonNullAnnotation = new[] { ClassType.NonNull };

        public CodeModelJv() 
        {
            PageClasses = new List<PageDetails>();
        }
        
        private JavaSettings _javaSettings;
        public JavaSettings JavaSettings
        {
            get
            {
                if (_javaSettings != null)
                {
                    return _javaSettings;
                }
                else
                {
                    Settings autoRestSettings = Settings.Instance;

                    _javaSettings = new JavaSettings(
                        setAddCredentials: (bool value) => autoRestSettings.AddCredentials = value,
                        isAzure: autoRestSettings.GetBoolSetting("azure-arm"),
                        isFluent: autoRestSettings.GetBoolSetting("fluent"),
                        regenerateManagers: autoRestSettings.GetBoolSetting("regenerate-manager"),
                        regeneratePom: autoRestSettings.GetBoolSetting("regenerate-pom"),
                        fileHeaderText: autoRestSettings.Header,
                        maximumJavadocCommentWidth: autoRestSettings.MaximumCommentColumns,
                        serviceName: autoRestSettings.GetStringSetting("serviceName"),
                        package: this.Namespace.ToLowerInvariant(),
                        shouldGenerateXmlSerialization: this.ShouldGenerateXmlSerialization,
                        nonNullAnnotations: autoRestSettings.GetBoolSetting("non-null-annotations", true),
                        clientTypePrefix: autoRestSettings.GetStringSetting("client-type-prefix"),
                        generateClientInterfaces: autoRestSettings.GetBoolSetting("generate-client-interfaces", true),
                        implementationSubpackage: autoRestSettings.GetStringSetting("implementation-subpackage", "implementation"),
                        modelsSubpackage: autoRestSettings.GetStringSetting("models-subpackage", "models"),
                        requiredParameterClientMethods: autoRestSettings.GetBoolSetting("required-parameter-client-methods", true));

                    return _javaSettings;
                }
            }
        }

        private Lazy<MethodParameter> _serviceClientCredentialsParameter;
        public Lazy<MethodParameter> ServiceClientCredentialsParameter
        {
            get
            {
                if (_serviceClientCredentialsParameter != null)
                {
                    return _serviceClientCredentialsParameter;
                }
                else
                {
                    _serviceClientCredentialsParameter = new Lazy<MethodParameter>(() =>
                        new MethodParameter(
                            description: "the management credentials for Azure",
                            isFinal: false,
                            wireType: ClassType.ServiceClientCredentials,
                            name: "credentials",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            parameterReference: "this.credentials",
                            annotations: this.JavaSettings.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _serviceClientCredentialsParameter;
                }
            }
        }

        private Lazy<MethodParameter> _azureTokenCredentialsParameter;
        public Lazy<MethodParameter> AzureTokenCredentialsParameter
        {
            get
            {
                if (_azureTokenCredentialsParameter != null)
                {
                    return _azureTokenCredentialsParameter;
                }
                else
                {
                    _azureTokenCredentialsParameter = new Lazy<MethodParameter>(() =>
                        new MethodParameter(
                            description: "the management credentials for Azure",
                            isFinal: false,
                            wireType: ClassType.AzureTokenCredentials,
                            name: "credentials",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            parameterReference: "this.credentials",
                            annotations: this.JavaSettings.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _azureTokenCredentialsParameter;
                }
            }
        }

        private Lazy<MethodParameter> _azureEnvironmentParameter;
        public Lazy<MethodParameter> AzureEnvironmentParameter
        {
            get
            {
                if (_azureEnvironmentParameter != null)
                {
                    return _azureEnvironmentParameter;
                }
                else
                {
                    _azureEnvironmentParameter = new Lazy<MethodParameter>(() =>
                        new MethodParameter(
                            description: "The environment that requests will target.",
                            isFinal: false,
                            wireType: ClassType.AzureEnvironment,
                            name: "azureEnvironment",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            parameterReference: "this.azureEnvironment",
                            annotations: this.JavaSettings.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _azureEnvironmentParameter;
                }
            }
        }

        private Lazy<MethodParameter> _httpPipelineParameter;
        public Lazy<MethodParameter> HttpPipelineParameter
        {
            get
            {
                if (_httpPipelineParameter != null)
                {
                    return _httpPipelineParameter;
                }
                else
                {
                    _httpPipelineParameter = new Lazy<MethodParameter>(() =>
                        new MethodParameter(
                            description: "The HTTP pipeline to send requests through.",
                            isFinal: false,
                            wireType: ClassType.HttpPipeline,
                            name: "httpPipeline",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            parameterReference: "this.httpPipeline",
                            annotations: this.JavaSettings.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _httpPipelineParameter;
                }
            }
        }

        public List<PageDetails> PageClasses { get; }

        public ServiceClient GenerateServiceClient()
        {
            string serviceClientInterfaceName = JavaSettings.ClientTypePrefix??"" + Name;

            string serviceClientClassName = serviceClientInterfaceName;
            if (JavaSettings.GenerateClientInterfaces)
            {
                serviceClientClassName += "Impl";
            }

            RestAPI serviceClientRestAPI = null;
            IEnumerable<ClientMethod> serviceClientMethods = Enumerable.Empty<ClientMethod>();
            IEnumerable<MethodJv> codeModelRestAPIMethods = Methods
                .Cast<MethodJv>()
                .Where(m => m.Group.IsNullOrEmpty());
            if (codeModelRestAPIMethods.Any())
            {
                string restAPIName = serviceClientInterfaceName + "Service";
                string restAPIBaseURL = BaseUrl;
                List<RestAPIMethod> restAPIMethods = new List<RestAPIMethod>();
                foreach (MethodJv codeModelRestAPIMethod in codeModelRestAPIMethods)
                {
                    RestAPIMethod restAPIMethod = codeModelRestAPIMethod.GenerateRestAPIMethod(JavaSettings);
                    restAPIMethods.Add(restAPIMethod);
                }
                serviceClientRestAPI = new RestAPI(restAPIName, restAPIBaseURL, restAPIMethods);
                serviceClientMethods = codeModelRestAPIMethods.SelectMany(m => m.GenerateClientMethods(JavaSettings));
            }

            List<MethodGroupClient> serviceClientMethodGroupClients = new List<MethodGroupClient>();
            IEnumerable<MethodGroup> codeModelMethodGroups = Operations.Where((MethodGroup methodGroup) => !string.IsNullOrEmpty(methodGroup?.Name?.ToString()));
            foreach (MethodGroupJv codeModelMethodGroup in codeModelMethodGroups)
            {
                serviceClientMethodGroupClients.Add(codeModelMethodGroup.GenerateMethodGroup(JavaSettings));
            }

            bool usesCredentials = false;

            List<ServiceClientProperty> serviceClientProperties = new List<ServiceClientProperty>();
            foreach (Property codeModelServiceClientProperty in Properties)
            {
                string serviceClientPropertyDescription = codeModelServiceClientProperty.Documentation.ToString();

                string serviceClientPropertyName = CodeNamer.Instance.RemoveInvalidCharacters(codeModelServiceClientProperty.Name.ToCamelCase());

                IType serviceClientPropertyClientType = ((PropertyJv)codeModelServiceClientProperty).GenerateType(JavaSettings);

                bool serviceClientPropertyIsReadOnly = codeModelServiceClientProperty.IsReadOnly;

                string serviceClientPropertyDefaultValueExpression = serviceClientPropertyClientType.DefaultValueExpression(codeModelServiceClientProperty.DefaultValue);

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
            if (JavaSettings.IsAzureOrFluent)
            {
                if (usesCredentials)
                {
                    serviceClientConstructors.Add(new Constructor(ServiceClientCredentialsParameter.Value));
                    serviceClientConstructors.Add(new Constructor(ServiceClientCredentialsParameter.Value, AzureEnvironmentParameter.Value));
                }
                else
                {
                    serviceClientConstructors.Add(new Constructor());
                    serviceClientConstructors.Add(new Constructor(AzureEnvironmentParameter.Value));
                }

                serviceClientConstructors.Add(new Constructor(HttpPipelineParameter.Value));
                serviceClientConstructors.Add(new Constructor(HttpPipelineParameter.Value, AzureEnvironmentParameter.Value));
            }
            else
            {
                serviceClientConstructors.Add(new Constructor());
                serviceClientConstructors.Add(new Constructor(HttpPipelineParameter.Value));
            }

            return new ServiceClient(serviceClientClassName, serviceClientInterfaceName, serviceClientRestAPI, serviceClientMethodGroupClients, serviceClientProperties, serviceClientConstructors, serviceClientMethods, AzureEnvironmentParameter, ServiceClientCredentialsParameter, HttpPipelineParameter);
        }

        public ServiceManager GenerateManager()
        {
            ServiceManager manager = null;
            if (JavaSettings.IsFluent && JavaSettings.RegenerateManagers)
            {
                string serviceName = GetServiceName();
                if (string.IsNullOrEmpty(serviceName))
                {
                    serviceName = "MissingServiceName";
                }
                manager = new ServiceManager(Name, serviceName, AzureTokenCredentialsParameter, HttpPipelineParameter);
            }
            return manager;
        }

        internal string GetServiceName()
        {
            var serviceName = Settings.Instance.GetStringSetting("serviceName");
            if (string.IsNullOrEmpty(serviceName))
            {
                Method method = this.Methods.FirstOrDefault();
                Match match = Regex.Match(input: method.Url, pattern: @"/providers/microsoft\.(\w+)/", options: RegexOptions.IgnoreCase);
                serviceName = match.Groups[1].Value.ToPascalCase();
            }
            return serviceName;
        }
    }
}