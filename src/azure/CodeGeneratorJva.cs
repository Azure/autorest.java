// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Model;
using System;
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
        public override async Task Generate(CodeModel cm)
        {
            // get Azure Java specific codeModel
            var codeModel = cm as CodeModelJva;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Azure Java CodeModel");
            }

            // Service client
            await WriteAzureServiceClientJavaFile(codeModel).ConfigureAwait(false);

            // Service client interface
            await WriteAzureServiceClientInterfaceJavaFile(codeModel).ConfigureAwait(false);

            // operations
            foreach (MethodGroupJva methodGroup in codeModel.AllOperations)
            {
                // Operation
                await WriteAzureMethodGroupJavaFile(codeModel, methodGroup).ConfigureAwait(false);

                // Operation interface
                await WriteMethodGroupInterfaceJavaFile(codeModel, methodGroup).ConfigureAwait(false);
            }

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
            await WritePackageInfoJavaFiles(cm, new[] { "", "implementation", "models" }).ConfigureAwait(false);
        }
    }
}
