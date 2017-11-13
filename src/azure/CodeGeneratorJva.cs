// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.azure.Templates;
using AutoRest.Java.Model;
using AutoRest.Java.vanilla.Templates;
using System;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Azure
{
    public class CodeGeneratorJva : CodeGeneratorJv
    {
        private const string ClientRuntimePackage = "com.microsoft.azure.v2:azure-client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        private const string _packageInfoFileName = "package-info.java";
        
        public override bool IsSingleFileGenerationSupported => true;

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";

        /// <summary>
        /// Generates Azure Java code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            // get Azure Java specific codeModel
            var codeModel = cm as CodeModelJva;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Azure Java CodeModel");
            }

            string package = codeModel.Namespace.ToLowerInvariant();
            string baseFolderPath = Path.Combine("src", "main", "java");
            string packageFolderPath = Path.Combine(baseFolderPath, package.Replace('.', Path.DirectorySeparatorChar));
            string implementationFolderPath = Path.Combine(packageFolderPath, "implementation");
            string modelsFolderPath = Path.Combine(packageFolderPath, "models");

            // Service client
            await WriteAzureServiceClientJavaFile(codeModel).ConfigureAwait(false);

            // Service client interface
            await WriteAzureServiceClientInterfaceJavaFile(codeModel).ConfigureAwait(false);

            // operations
            foreach (MethodGroupJva methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new AzureMethodGroupTemplate { Model = methodGroup };
                string operationsFileName = $"{methodGroup.TypeName.ToPascalCase()}Impl.java";
                string operationsFilePath = Path.Combine(implementationFolderPath, operationsFileName);
                await Write(operationsTemplate, operationsFilePath);

                // Operation interface
                await WriteMethodGroupInterfaceJavaFile(codeModel, methodGroup).ConfigureAwait(false);
            }

            //Models
            await WriteModelJavaFiles(codeModel).ConfigureAwait(false);

            //XML wrappers
            await WriteXmlWrapperFiles(codeModel).ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(codeModel).ConfigureAwait(false);

            // Page class
            foreach (var pageClass in codeModel.pageClasses)
            {
                var pageTemplate = new PageTemplate
                {
                    Model = new PageJva(pageClass.Value, pageClass.Key.Key, pageClass.Key.Value),
                };
                string pageFileName = $"{pageTemplate.Model.TypeDefinitionName.ToPascalCase()}.java";
                string pageFilePath = Path.Combine(modelsFolderPath, pageFileName);
                await Write(pageTemplate, pageFilePath);
            }

            // Exceptions
            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(cm, packageFolderPath, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }
    }
}
