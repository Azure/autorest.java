// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.azure.Templates;
using System.Threading.Tasks;

namespace AutoRest.Java.Azure.Fluent
{
    public class CodeGeneratorJvaf : CodeGeneratorJva
    {
        /// <summary>
        /// Generates C# code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            // Service client
            await WriteAzureServiceClientJavaFile(cm).ConfigureAwait(false);

            // operations
            await WriteOperationJavaFiles(cm).ConfigureAwait(false);

            //Models
            await WriteModelJavaFiles(cm).ConfigureAwait(false);

            //XML wrappers
            await WriteXmlWrapperJavaFiles(cm).ConfigureAwait(false);

            //Enums
            await WriteEnumJavaFiles(cm).ConfigureAwait(false);

            // Page class
            await WritePageJavaFiles(cm).ConfigureAwait(false);

            // Exceptions
            await WriteExceptionJavaFiles(cm).ConfigureAwait(false);

            // package-info.java
            await WritePackageInfoJavaFiles(cm, new[] { "", "implementation" }).ConfigureAwait(false);

            if (true == Settings.Instance.Host?.GetValue<bool?>("regenerate-manager").Result)
            {
                // Manager
                await WriteAzureServiceManagerJavaFile(cm).ConfigureAwait(false);
            }

            if (true == Settings.Instance.Host?.GetValue<bool?>("regenerate-pom").Result)
            { 
                // POM
                await Write(new AzurePomTemplate { Model = cm }, "pom.xml");
            }
        }
    }
}
