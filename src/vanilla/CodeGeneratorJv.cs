// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.DanModel;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace AutoRest.Java
{
    public class CodeGeneratorJv : CodeGenerator
    {
        private const string ClientRuntimePackage = "com.microsoft.rest.v2:client-runtime:2.0.0-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        
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
            // Service client
            await WriteServiceClientJavaFile(cm).ConfigureAwait(false);

            // Service client interface
            await WriteServiceClientInterfaceJavaFile(cm).ConfigureAwait(false);

            // operations
            await WriteOperationJavaFiles(cm).ConfigureAwait(false);

            //Models
            await WriteModelJavaFiles(cm).ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(cm).ConfigureAwait(false);

            // XML wrappers
            await WriteXmlWrapperJavaFiles(cm).ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(cm).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(cm, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }

        protected Task WriteOperationJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetOperationJavaFiles(codeModel, Settings));

        protected Task WriteAzureServiceManagerJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetAzureServiceManagerJavaFile(codeModel, Settings));

        protected Task WritePageJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetPageJavaFiles(codeModel, Settings));

        protected Task WriteXmlWrapperJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetXmlWrapperJavaFiles(codeModel, Settings));

        protected Task WriteAzureServiceClientJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetAzureServiceClientJavaFile(codeModel, Settings));

        protected Task WriteAzureServiceClientInterfaceJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetAzureServiceClientInterfaceJavaFile(codeModel, Settings));

        protected Task WriteServiceClientJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetServiceClientJavaFile(codeModel, Settings));

        protected Task WriteServiceClientInterfaceJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetServiceClientInterfaceJavaFile(codeModel, Settings));

        protected Task WritePackageInfoJavaFiles(CodeModel codeModel, string[] subPackages)
            => WriteJavaFiles(DanCodeGenerator.GetPackageInfoJavaFiles(codeModel, Settings, subPackages));

        protected Task WriteModelJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetModelJavaFiles(codeModel, Settings));

        protected Task WriteEnumJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetEnumJavaFiles(codeModel, Settings));

        protected Task WriteExceptionJavaFiles(CodeModel codeModel)
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
