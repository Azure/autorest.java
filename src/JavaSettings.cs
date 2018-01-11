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

        public JavaSettings(Action<bool> setAddCredentials, bool isAzure, bool isFluent, bool regenerateManagers, bool regeneratePom, string fileHeaderText, int maximumJavadocCommentWidth, string serviceName, string package, bool shouldGenerateXmlSerialization)
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
        }

        public bool IsAzure { get; }

        public bool IsFluent { get; }

        public bool IsAzureOrFluent => IsAzure || IsFluent;

        public bool AddCredentials
        {
            set => setAddCredentials(value);
        }

        public bool RegenerateManagers { get; set; }

        public bool RegeneratePom { get; set; }

        public string FileHeaderText { get; set; }

        public int MaximumJavadocCommentWidth { get; set; }

        public string ServiceName { get; set; }

        public string Package { get; set; }

        public bool ShouldGenerateXmlSerialization { get; set; }
    }
}
