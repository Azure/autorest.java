// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Azure.Fluent.Model;
using AutoRest.Java.azure.Templates;
using AutoRest.Java.Model;
using AutoRest.Java.vanilla.Templates;
using System;
using System.Text.RegularExpressions;
using AutoRest.Java.DanModel;

namespace AutoRest.Java.Azure.Fluent
{
    public class CodeGeneratorJvaf : CodeGeneratorJva
    {
        private const string ClientRuntimePackage = "com.microsoft.azure.v2:azure-client-runtime:2.0.0-SNAPSHOT";
        private const string _packageInfoFileName = "package-info.java";

        public override bool IsSingleFileGenerationSupported => true;

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";
        
        /// <summary>
        /// Generates C# code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            // get Azure Java specific codeModel
            var codeModel = cm as CodeModelJvaf;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Azure Java Fluent CodeModel");
            }

            string package = codeModel.Namespace.ToLowerInvariant();
            string baseFolderPath = Path.Combine("src", "main", "java");
            string packageFolderPath = Path.Combine(baseFolderPath, package.Replace('.', Path.DirectorySeparatorChar));
            string implementationFolderPath = Path.Combine(packageFolderPath, "implementation");

            // Service client
            await WriteAzureServiceClientJavaFile(codeModel).ConfigureAwait(false);

            // operations
            foreach (MethodGroupJvaf methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new AzureMethodGroupTemplate { Model = methodGroup };
                string operationsFileName = $"{methodGroup.TypeName.ToPascalCase()}Inner.java";
                string operationsFilePath = Path.Combine(implementationFolderPath, operationsFileName);
                await Write(operationsTemplate, operationsFilePath);
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
                    Model = new PageJvaf(pageClass.Value, pageClass.Key.Key, pageClass.Key.Value),
                };
                string pageFileName = $"{pageTemplate.Model.TypeDefinitionName.ToPascalCase()}.java";
                string pageFilePath = Path.Combine(implementationFolderPath, pageFileName);
                await Write(pageTemplate, pageFilePath);
            }

            // Exceptions
            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(cm, packageFolderPath, new[] { "", "implementation" }).ConfigureAwait(false);

            if (true == Settings.Instance.Host?.GetValue<bool?>("regenerate-manager").Result)
            {
                // Manager
                var managerTemplate = new AzureServiceManagerTemplate { Model = codeModel };
                string managerFileName = $"{codeModel.ServiceName}Manager.java";
                string managerFilePath = Path.Combine(implementationFolderPath, managerFileName);
                await Write(managerTemplate, managerFilePath).ConfigureAwait(false);

                // POM
                await Write(new AzurePomTemplate { Model = codeModel }, "pom.xml");
            }
        }
    }
}
