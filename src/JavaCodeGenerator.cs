// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.Templates;
using System.Collections.Generic;
using System.Threading.Tasks;

namespace AutoRest.Java
{
    public class JavaCodeGenerator : CodeGenerator
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
        public override async Task Generate(CodeModel codeModel)
        {
            DanCodeGenerator.TransformCodeModel(codeModel);

            await WriteServiceClientJavaFile(codeModel).ConfigureAwait(false);

            await WriteMethodGroupJavaFiles(codeModel).ConfigureAwait(false);

            await WriteModelJavaFiles(codeModel).ConfigureAwait(false);

            await WriteEnumJavaFiles(codeModel).ConfigureAwait(false);

            await WriteXmlWrapperJavaFiles(codeModel).ConfigureAwait(false);

            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            await WritePackageInfoJavaFiles(codeModel, new[] { "", "implementation" }).ConfigureAwait(false);

            if (DanCodeGenerator.IsAzureOrFluent())
            {
                await WritePageJavaFiles(codeModel).ConfigureAwait(false);
            }

            if (!DanCodeGenerator.IsFluent())
            {
                await WriteAzureServiceClientInterfaceJavaFile(codeModel).ConfigureAwait(false);

                await WritePackageInfoJavaFiles(codeModel, new[] { "models" }).ConfigureAwait(false);
            }
            else
            {
                if (true == Settings.Instance.Host?.GetValue<bool?>("regenerate-manager").Result)
                {
                    // Manager
                    await WriteAzureServiceManagerJavaFile(codeModel).ConfigureAwait(false);
                }

                if (true == Settings.Instance.Host?.GetValue<bool?>("regenerate-pom").Result)
                {
                    // POM
                    await Write(new PomTemplate { Model = codeModel }, "pom.xml").ConfigureAwait(false);
                }
            }
        }

        protected Task WriteMethodGroupJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetMethodGroupClientAndMethodGroupClientInterfaceJavaFiles(codeModel, Settings));

        protected Task WriteAzureServiceManagerJavaFile(CodeModel codeModel)
            => WriteJavaFile(DanCodeGenerator.GetAzureServiceManagerJavaFile(codeModel, Settings));

        protected Task WritePageJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetPageJavaFiles(codeModel, Settings));

        protected Task WriteXmlWrapperJavaFiles(CodeModel codeModel)
            => WriteJavaFiles(DanCodeGenerator.GetXmlWrapperJavaFiles(codeModel, Settings));

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
