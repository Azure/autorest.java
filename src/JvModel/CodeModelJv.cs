using System;
using System.Collections.Generic;
using System.Linq;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java;

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
                            type: ClassType.ServiceClientCredentials,
                            name: "credentials",
                            isRequired: true,
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
                            type: ClassType.AzureTokenCredentials,
                            name: "credentials",
                            isRequired: true,
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
                            type: ClassType.AzureEnvironment,
                            name: "azureEnvironment",
                            isRequired: true,
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
                            type: ClassType.HttpPipeline,
                            name: "httpPipeline",
                            isRequired: true,
                            annotations: this.JavaSettings.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _httpPipelineParameter;
                }
            }
        }

        public List<PageDetails> PageClasses { get; }
    }
}