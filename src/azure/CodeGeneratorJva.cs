// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using System.Threading.Tasks;

namespace AutoRest.Java.Azure
{
    public class CodeGeneratorJva : CodeGeneratorJv
    {
        /// <summary>
        /// Generates Azure Java code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel codeModel)
        {
            // Service client
            await WriteServiceClientJavaFile(codeModel).ConfigureAwait(false);

            // Service client interface
            await WriteAzureServiceClientInterfaceJavaFile(codeModel).ConfigureAwait(false);

            // operations
            await WriteOperationJavaFiles(codeModel).ConfigureAwait(false);

            //Models
            await WriteModelJavaFiles(codeModel).ConfigureAwait(false);

            //XML wrappers
            await WriteXmlWrapperJavaFiles(codeModel).ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(codeModel).ConfigureAwait(false);

            // Page class
            await WritePageJavaFiles(codeModel).ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(codeModel).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(codeModel, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }
    }
}
