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
        private const string ClientRuntimePackage = "com.microsoft.rest.v2:client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
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
            await WriteServiceClientJavaFile(codeModel).ConfigureAwait(false);

            // Service client interface
            await WriteServiceClientInterfaceJavaFile(codeModel).ConfigureAwait(false);

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

            // XML wrappers
            await WriteXmlWrapperFiles(codeModel, codeModel.ImplPackage).ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(codeModel, packageFolderPath, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }

        protected async Task WriteXmlWrapperFiles(CodeModelJv codeModel, string packageFolderPath)
        {
            if (codeModel.ShouldGenerateXmlSerializationCached)
            {
                // Every sequence type used as a parameter to a service method.
                var parameterSequenceTypes = codeModel.Operations
                    .SelectMany(o => o.Methods)
                    .SelectMany(m => m.Parameters)
                    .Select(p => p.ModelType)
                    .OfType<SequenceTypeJv>()
                    .Distinct(ModelNameComparer.Instance)
                    .ToArray();

                foreach (SequenceTypeJv st in parameterSequenceTypes)
                {
                    var wrapperTemplate = new XmlListWrapperTemplate { Model = st };
                    await Write(wrapperTemplate, $"{packageFolderPath}/{st.XmlName.ToPascalCase()}Wrapper{ImplementationFileExtension}");
                }
            }
        }

        protected Task WriteServiceClientJavaFile(CodeModelJv codeModel)
            => WriteJavaFile(DanCodeGenerator.GetServiceClientJavaFile(codeModel, Settings));

        protected Task WriteServiceClientInterfaceJavaFile(CodeModelJv codeModel)
            => WriteJavaFile(DanCodeGenerator.GetServiceClientInterfaceJavaFile(codeModel, Settings));

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
