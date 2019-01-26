// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using AutoRest.Core;

namespace AutoRest.Java
{
    /// <summary>
    /// Settings that are used by the Java AutoRest Generator.
    /// </summary>
    public class JavaSettings
    {
        private static JavaSettings _instance;

        public static JavaSettings Instance
        {
            get
            {
                if (_instance == null)
                {
                    Settings autoRestSettings = Settings.Instance;

                    _instance = new JavaSettings(
                        setAddCredentials: (bool value) => autoRestSettings.AddCredentials = value,
                        isAzure: autoRestSettings.GetBoolSetting("azure-arm"),
                        isFluent: autoRestSettings.GetBoolSetting("fluent"),
                        regenerateManagers: autoRestSettings.GetBoolSetting("regenerate-manager"),
                        regeneratePom: autoRestSettings.GetBoolSetting("regenerate-pom"),
                        fileHeaderText: autoRestSettings.Header,
                        maximumJavadocCommentWidth: autoRestSettings.MaximumCommentColumns,
                        serviceName: autoRestSettings.GetStringSetting("serviceName"),
                        package: CodeNamer.Instance.GetNamespaceName(autoRestSettings.Namespace).ToLowerInvariant(),
                        shouldGenerateXmlSerialization: autoRestSettings.Host?.GetValue<bool?>("enable-xml").Result == true,
                        nonNullAnnotations: autoRestSettings.GetBoolSetting("non-null-annotations", true),
                        clientTypePrefix: autoRestSettings.GetStringSetting("client-type-prefix"),
                        generateClientInterfaces: autoRestSettings.GetBoolSetting("generate-client-interfaces", true),
                        implementationSubpackage: autoRestSettings.GetStringSetting("implementation-subpackage", "implementation"),
                        modelsSubpackage: autoRestSettings.GetStringSetting("models-subpackage", "models"),
                        requiredParameterClientMethods: autoRestSettings.GetBoolSetting("required-parameter-client-methods", true),
                        addContextParameter: autoRestSettings.GetBoolSetting("add-context-parameter", false));
                }
                return _instance;
            }
        }

        private readonly Action<bool> setAddCredentials;

        /// <summary>
        /// Create a new JavaSettings object with the provided properties.
        /// </summary>
        /// <param name="setAddCredentials"></param>
        /// <param name="isAzure"></param>
        /// <param name="isFluent"></param>
        /// <param name="regenerateManagers"></param>
        /// <param name="regeneratePom"></param>
        /// <param name="fileHeaderText"></param>
        /// <param name="maximumJavadocCommentWidth"></param>
        /// <param name="serviceName"></param>
        /// <param name="package"></param>
        /// <param name="shouldGenerateXmlSerialization"></param>
        /// <param name="nonNullAnnotations">Whether or not to add the @NotNull annotation to required parameters in client methods.</param>
        /// <param name="stringDates">Whether or not DateTime types should be represented as Strings (generally for better/different precision).</param>
        /// <param name="clientTypePrefix">The prefix that will be added to each generated client type.</param>
        /// <param name="generateClientInterfaces">Whether or not interfaces will be generated for Service and Method Group clients.</param>
        /// <param name="implementationSubpackage">The sub-package that the Service and Method Group client implementation classes will be put into.</param>
        /// <param name="modelsSubpackage">The sub-package that Enums, Exceptions, and Model types will be put into.</param>
        /// <param name="requiredParameterClientMethods">Whether or not Service and Method Group client method overloads that omit optional parameters will be created.</param>
        private JavaSettings(
            Action<bool> setAddCredentials,
            bool isAzure,
            bool isFluent,
            bool regenerateManagers,
            bool regeneratePom,
            string fileHeaderText,
            int maximumJavadocCommentWidth,
            string serviceName,
            string package,
            bool shouldGenerateXmlSerialization,
            bool nonNullAnnotations,
            string clientTypePrefix,
            bool generateClientInterfaces,
            string implementationSubpackage,
            string modelsSubpackage,
            bool requiredParameterClientMethods,
            bool addContextParameter)
        {
            this.setAddCredentials = setAddCredentials;
            IsAzure = isAzure;
            IsFluent = isFluent;
            RegenerateManagers = regenerateManagers;
            RegeneratePom = regeneratePom;
            FileHeaderText = fileHeaderText;
            MaximumJavadocCommentWidth = maximumJavadocCommentWidth;
            ServiceName = serviceName;
            Package = package;
            ShouldGenerateXmlSerialization = shouldGenerateXmlSerialization;
            NonNullAnnotations = nonNullAnnotations;
            ClientTypePrefix = clientTypePrefix;
            GenerateClientInterfaces = generateClientInterfaces;
            ImplementationSubpackage = implementationSubpackage;
            ModelsSubpackage = modelsSubpackage;
            RequiredParameterClientMethods = requiredParameterClientMethods;
            AddContextParameter = addContextParameter;
        }

        public bool IsAzure { get; }

        public bool IsFluent { get; }

        public bool IsAzureOrFluent => IsAzure || IsFluent;

        public bool AddCredentials
        {
            set => setAddCredentials(value);
        }

        public bool RegenerateManagers { get; }

        public bool RegeneratePom { get; }

        public string FileHeaderText { get; }

        public int MaximumJavadocCommentWidth { get; }

        public string ServiceName { get; }

        public string Package { get; }

        public bool ShouldGenerateXmlSerialization { get; internal set; }

        /// <summary>
        /// Whether or not to add the @NotNull annotation to required parameters in client methods.
        /// </summary>
        public bool NonNullAnnotations { get; }

        /// <summary>
        /// The prefix that will be added to each generated client type.
        /// </summary>
        public string ClientTypePrefix { get; }

        /// <summary>
        /// Whether or not interfaces will be generated for Service and Method Group clients.
        /// </summary>
        public bool GenerateClientInterfaces { get; }

        /// <summary>
        /// The sub-package that the Service and Method Group client implementation classes will be put into.
        /// </summary>
        public string ImplementationSubpackage { get; }

        /// <summary>
        /// The sub-package that Enums, Exceptions, and Model types will be put into.
        /// </summary>
        public string ModelsSubpackage { get; }

        /// <summary>
        /// Whether or not Service and Method Group client method overloads that omit optional parameters will be created.
        /// </summary>
        public bool RequiredParameterClientMethods { get; }
 
        /// <summary>
        /// Indicates whether the leading com.microsoft.rest.v3.Context parameter should be included in generated methods.
        /// </summary>
        public bool AddContextParameter { get; }
    }
}
