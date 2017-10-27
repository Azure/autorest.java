// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Globalization;
using System.IO;
using System.Linq;
using System.Threading.Tasks;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Java.vanilla.Templates;
using AutoRest.Java.Model;
using AutoRest.Core.Model;
using System;
using AutoRest.Java.DanModel;
using System.Collections.Generic;

namespace AutoRest.Java
{
    public class CodeGeneratorJv : CodeGenerator
    {
        private const string ClientRuntimePackage = "com.microsoft.rest:client-runtime:1.0.0-beta6-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        private const string _packageInfoFileName = "package-info.java";

        public CodeNamerJv Namer { get; private set; }

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";

        public override string ImplementationFileExtension => ".java";

        protected Settings Settings
        {
            get { return Settings.Instance; }
        }

        /// <summary>
        /// Generate Java client code for given ServiceClient.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            // get Java specific codeModel
            var codeModel = cm as CodeModelJv;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Java CodeModel");
            }

            // Service client
            var serviceClientTemplate = new ServiceClientTemplate { Model = codeModel };
            await Write(serviceClientTemplate, $"{Path.Combine("implementation", cm.Name.ToPascalCase() + "Impl")}{ImplementationFileExtension}");

            // Service client interface
            var serviceClientInterfaceTemplate = new ServiceClientInterfaceTemplate { Model = codeModel };
            await Write(serviceClientInterfaceTemplate, $"{cm.Name.ToPascalCase()}{ImplementationFileExtension}");
            
            // operations
            foreach (MethodGroupJv methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new MethodGroupTemplate { Model = methodGroup };
                await Write(operationsTemplate, $"{Path.Combine("implementation", methodGroup.TypeName.ToPascalCase())}Impl{ImplementationFileExtension}");

                // Operation interface
                var operationsInterfaceTemplate = new MethodGroupInterfaceTemplate { Model = methodGroup };
                await Write(operationsInterfaceTemplate, $"{methodGroup.TypeName.ToPascalCase()}{ImplementationFileExtension}");
            }

            //Models
            await WriteModelJavaFiles(codeModel, "models", "models").ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(codeModel, "models", "models").ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(codeModel, "models", "models").ConfigureAwait(false);
            
            // package-info.java
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm)
            }, _packageInfoFileName);
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm, "implementation")
            }, Path.Combine("implementation", _packageInfoFileName));
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm, "models")
            }, Path.Combine("models", _packageInfoFileName));
        }

        protected async Task WriteModelJavaFiles(CodeModel codeModel, string relativePath, string packageSuffix)
        {
            await WriteJavaFiles(DanCodeGenerator.GetModelJavaFiles(codeModel, Settings, relativePath, packageSuffix)).ConfigureAwait(false);
        }

        protected async Task WriteEnumJavaFiles(CodeModel codeModel, string relativePath, string packageSuffix)
        {
            await WriteJavaFiles(DanCodeGenerator.GetEnumJavaFiles(codeModel, Settings, relativePath, packageSuffix)).ConfigureAwait(false);
        }

        protected async Task WriteExceptionJavaFiles(CodeModelJv codeModel, string relativePath, string packageSuffix)
        {
            await WriteJavaFiles(DanCodeGenerator.GetExceptionJavaFiles(codeModel, Settings, relativePath, packageSuffix)).ConfigureAwait(false);
        }

        protected async Task WriteJavaFiles(IEnumerable<JavaFile> javaFiles)
        {
            foreach (JavaFile javaFile in javaFiles)
            {
                await Write(javaFile.Contents.ToString(), javaFile.FilePath).ConfigureAwait(false);
            }
        }
    }
}
