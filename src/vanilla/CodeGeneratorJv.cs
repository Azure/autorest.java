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
                var operationsInterfaceTemplate = new MethodGroupInterfaceTemplate { Model = methodGroup };
                string operationsInterfaceFileName = $"{methodGroup.TypeName.ToPascalCase()}.java";
                string operationsInterfaceFilePath = Path.Combine(packageFolderPath, operationsInterfaceFileName);
                await Write(operationsInterfaceTemplate, operationsInterfaceFilePath);
            }

            //Models
            await WriteModelJavaFiles(codeModel, "models").ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(codeModel, "models").ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(codeModel, "models").ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoFile(cm, packageFolderPath).ConfigureAwait(false);
            await WritePackageInfoFile(cm, packageFolderPath, "implementation").ConfigureAwait(false);
            await WritePackageInfoFile(cm, packageFolderPath, "models").ConfigureAwait(false);
        }

        private Task WritePackageInfoFile(CodeModel codeModel, string packageFolderPath, string subPackage = null)
        {
            PackageInfoTemplateModel model = new PackageInfoTemplateModel(codeModel, subPackage);

            PackageInfoTemplate template = new PackageInfoTemplate { Model = model };

            string packageInfoFilePath = packageFolderPath;
            if (!string.IsNullOrEmpty(subPackage))
            {
                packageInfoFilePath = Path.Combine(packageInfoFilePath, subPackage);
            }
            packageInfoFilePath = Path.Combine(packageInfoFilePath, _packageInfoFileName);

            return Write(template, packageInfoFilePath);
        }

        protected async Task WriteModelJavaFiles(CodeModel codeModel, string packageSuffix = null)
        {
            await WriteJavaFiles(DanCodeGenerator.GetModelJavaFiles(codeModel, Settings, packageSuffix)).ConfigureAwait(false);
        }


        protected async Task WriteEnumJavaFiles(CodeModel codeModel, string packageSuffix = null)
        {
            await WriteJavaFiles(DanCodeGenerator.GetEnumJavaFiles(codeModel, Settings, packageSuffix)).ConfigureAwait(false);
        }

        protected async Task WriteExceptionJavaFiles(CodeModelJv codeModel, string packageSuffix = null)
        {
            await WriteJavaFiles(DanCodeGenerator.GetExceptionJavaFiles(codeModel, Settings, packageSuffix)).ConfigureAwait(false);
        }

        protected async Task WriteJavaFiles(IEnumerable<JavaFile> javaFiles)
        {
            foreach (JavaFile javaFile in javaFiles)
            {
                await Write(javaFile.Contents.ToString(), javaFile.FilePath).ConfigureAwait(false);
            }
        }

        protected static Func<string, string> ModelsPathFunction => (string value) => "models";

        protected static Func<string,string> AddPathPrefixAndSuffix(string prefix, string suffix)
        {
            return (string value) => NormalizePath(Path.Combine(prefix, value, suffix));
        }

        protected static Func<string, string> AddPathPrefix(string prefix)
        {
            return (string value) => NormalizePath(Path.Combine(prefix, value));
        }

        private static string NormalizePath(string path)
        {
            return path.Replace('\\', '/').Replace("//", "/");
        }
    }
}
