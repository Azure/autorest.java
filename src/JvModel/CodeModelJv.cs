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

        private Lazy<ClientMethodParameter> _serviceClientCredentialsParameter;
        public Lazy<ClientMethodParameter> ServiceClientCredentialsParameter
        {
            get
            {
                if (_serviceClientCredentialsParameter != null)
                {
                    return _serviceClientCredentialsParameter;
                }
                else
                {
                    _serviceClientCredentialsParameter = new Lazy<ClientMethodParameter>(() =>
                        new ClientMethodParameter(
                            description: "the management credentials for Azure",
                            isFinal: false,
                            wireType: ClassType.ServiceClientCredentials,
                            name: "credentials",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            annotations: JavaSettings.Instance.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _serviceClientCredentialsParameter;
                }
            }
        }

        private Lazy<ClientMethodParameter> _azureTokenCredentialsParameter;
        public Lazy<ClientMethodParameter> AzureTokenCredentialsParameter
        {
            get
            {
                if (_azureTokenCredentialsParameter != null)
                {
                    return _azureTokenCredentialsParameter;
                }
                else
                {
                    _azureTokenCredentialsParameter = new Lazy<ClientMethodParameter>(() =>
                        new ClientMethodParameter(
                            description: "the management credentials for Azure",
                            isFinal: false,
                            wireType: ClassType.AzureTokenCredentials,
                            name: "credentials",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            annotations: JavaSettings.Instance.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _azureTokenCredentialsParameter;
                }
            }
        }

        private Lazy<ClientMethodParameter> _azureEnvironmentParameter;
        public Lazy<ClientMethodParameter> AzureEnvironmentParameter
        {
            get
            {
                if (_azureEnvironmentParameter != null)
                {
                    return _azureEnvironmentParameter;
                }
                else
                {
                    _azureEnvironmentParameter = new Lazy<ClientMethodParameter>(() =>
                        new ClientMethodParameter(
                            description: "The environment that requests will target.",
                            isFinal: false,
                            wireType: ClassType.AzureEnvironment,
                            name: "azureEnvironment",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            annotations: JavaSettings.Instance.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _azureEnvironmentParameter;
                }
            }
        }

        private Lazy<ClientMethodParameter> _httpPipelineParameter;
        public Lazy<ClientMethodParameter> HttpPipelineParameter
        {
            get
            {
                if (_httpPipelineParameter != null)
                {
                    return _httpPipelineParameter;
                }
                else
                {
                    _httpPipelineParameter = new Lazy<ClientMethodParameter>(() =>
                        new ClientMethodParameter(
                            description: "The HTTP pipeline to send requests through.",
                            isFinal: false,
                            wireType: ClassType.HttpPipeline,
                            name: "httpPipeline",
                            isRequired: true,
                            isConstant: false,
                            fromClient: true,
                            defaultValue: null,
                            annotations: JavaSettings.Instance.NonNullAnnotations  ? nonNullAnnotation : Enumerable.Empty<ClassType>()));
                    return _httpPipelineParameter;
                }
            }
        }

        public List<PageDetails> PageClasses { get; }

        internal string GetServiceName()
        {
            var serviceName = Settings.Instance.GetStringSetting("serviceName");
            if (string.IsNullOrEmpty(serviceName))
            {
                Method method = this.Methods.FirstOrDefault(m => m.Url.ToLower().Contains("/providers/microsoft."));
                if (method == null)
                {
                    serviceName = this.Namespace.Split('.').Last();
                }
                else
                {
                    Match match = Regex.Match(input: method.Url, pattern: @"/providers/microsoft\.(\w+)/", options: RegexOptions.IgnoreCase);
                    serviceName = match.Groups[1].Value.ToPascalCase();
                }
            }
            return serviceName;
        }
    }
}