// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java
{
    /// <summary>
    /// Settings that are used by the Java AutoRest Generator.
    /// </summary>
    public class JavaSettings
    {
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
        /// <param name="packagePrivate">Whether or not the created types will be package private.</param>
        /// <param name="stringDates">Whether or not DateTime types should be represented as Strings (generally for better/different precision).</param>
        public JavaSettings(Action<bool> setAddCredentials, bool isAzure, bool isFluent, bool regenerateManagers, bool regeneratePom, string fileHeaderText, int maximumJavadocCommentWidth, string serviceName, string package, bool shouldGenerateXmlSerialization, bool nonNullAnnotations, bool packagePrivate, bool stringDates)
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
            PackagePrivate = packagePrivate;
            StringDates = stringDates;
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

        public bool ShouldGenerateXmlSerialization { get; }

        /// <summary>
        /// Whether or not to add the @NotNull annotation to required parameters in client methods.
        /// </summary>
        public bool NonNullAnnotations { get; }

        /// <summary>
        /// Whether or not the created types will be package private.
        /// </summary>
        public bool PackagePrivate { get; }

        /// <summary>
        /// Whether or not DateTime types should be represented as Strings (generally for better/different precision).
        /// </summary>
        public bool StringDates { get; }
    }
}
