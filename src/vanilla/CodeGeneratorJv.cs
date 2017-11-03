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

            string package = codeModel.Namespace.ToLowerInvariant();
            string baseFolderPath = Path.Combine("src", "main", "java");
            string packageFolderPath = Path.Combine(baseFolderPath, package.Replace('.', Path.DirectorySeparatorChar));
            string implementationFolderPath = Path.Combine(packageFolderPath, "implementation");

            // Service client
            var serviceClientTemplate = new ServiceClientTemplate { Model = codeModel };
            string serviceClientFileName = $"{cm.Name.ToPascalCase()}Impl.java";
            string serviceClientFilePath = Path.Combine(implementationFolderPath, serviceClientFileName);
            await Write(serviceClientTemplate, serviceClientFilePath);

            // Service client interface
            var serviceClientInterfaceTemplate = new ServiceClientInterfaceTemplate { Model = codeModel };
            string serviceClientInterfaceFileName = $"{cm.Name.ToPascalCase()}.java";
            string serviceClientInterfaceFilePath = Path.Combine(packageFolderPath, serviceClientInterfaceFileName);
            await Write(serviceClientInterfaceTemplate, serviceClientInterfaceFilePath);
            
            // operations
            foreach (MethodGroupJv methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new MethodGroupTemplate { Model = methodGroup };
                string operationsFileName = $"{methodGroup.TypeName.ToPascalCase()}Impl.java";
                string operationsFilePath = Path.Combine(implementationFolderPath, operationsFileName);
                await Write(operationsTemplate, operationsFilePath);

                // Operation interface
                await WriteMethodGroupInterfaceJavaFile(codeModel, methodGroup).ConfigureAwait(false);
            }

            //Models
            await WriteModelJavaFiles(codeModel).ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(codeModel).ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(codeModel, packageFolderPath, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }

        protected Task WriteMethodGroupInterfaceJavaFile(CodeModel codeModel, MethodGroupJv methodGroup)
            => WriteJavaFile(DanCodeGenerator.GetMethodGroupInterfaceJavaFile(codeModel, Settings, methodGroup));

        protected Task WritePackageInfoJavaFiles(CodeModel codeModel, string packageFolderPath, string[] subPackages)
            => WriteJavaFiles(DanCodeGenerator.GetPackageInfoJavaFiles(codeModel, Settings, subPackages));

        protected Task WriteModelJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetModelJavaFiles(codeModel, Settings));

        protected Task WriteEnumJavaFiles(CodeModelJv codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetEnumJavaFiles(codeModel, Settings));

        protected Task WriteExceptionJavaFiles(CodeModelJv codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetExceptionJavaFiles(codeModel, Settings));

        protected async Task WriteJavaFiles(IEnumerable<JavaFile> javaFiles)
        {
            foreach (JavaFile javaFile in javaFiles)
            {
                await WriteJavaFile(javaFile).ConfigureAwait(false);
            }
        }

        protected Task WriteJavaFile(JavaFile javaFile)
            => Write(javaFile.Contents.ToString(), javaFile.FilePath);
    }
}
