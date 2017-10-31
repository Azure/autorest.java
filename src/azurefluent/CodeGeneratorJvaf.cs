// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
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
        private const string ClientRuntimePackage = "com.microsoft.azure:azure-client-runtime:1.0.0-beta6-SNAPSHOT";
        private const string _packageInfoFileName = "package-info.java";

        public override bool IsSingleFileGenerationSupported => true;

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";


        class ModelNameComparer : IEqualityComparer<ModelType>
        {
            private ModelNameComparer() { }
            internal static ModelNameComparer Instance { get; } = new ModelNameComparer();

            public bool Equals(ModelType x, ModelType y)
            {
                return x.Name.Equals(y.Name) || x.XmlName.Equals(y.XmlName);
            }

            public int GetHashCode(ModelType obj)
            {
                return obj.Name.GetHashCode() ^ obj.XmlName.GetHashCode();
            }
        }

        /// <summary>
        /// Generates C# code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            string packagePath = $"src/main/java/{cm.Namespace.ToLower().Replace('.', '/')}";

            Func<string, string> getPathFunction = AddPathPrefixAndSuffix("src/main/java", "implementation");
            Func<string, string> getPackageFunction = AddPackageSuffix("implementation");

            // get Azure Java specific codeModel
            var codeModel = cm as CodeModelJvaf;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Azure Java Fluent CodeModel");
            }

            // Service client
            var serviceClientTemplate = new AzureServiceClientTemplate { Model = codeModel };
            await Write(serviceClientTemplate, $"{packagePath}/implementation/{codeModel.Name.ToPascalCase()}Impl{ImplementationFileExtension}");

            // operations
            foreach (MethodGroupJvaf methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new AzureMethodGroupTemplate { Model = methodGroup };
                await Write(operationsTemplate, $"{packagePath}/implementation/{methodGroup.TypeName.ToPascalCase()}Inner{ImplementationFileExtension}");
            }

            //Models
            await WriteModelJavaFiles(codeModel, getPathFunction, getPackageFunction).ConfigureAwait(false);

            //XML wrappers
            if (codeModel.ShouldGenerateXmlSerializationCached)
            {
                // Every sequence type used as a parameter to a service method.
                var parameterSequenceTypes = cm.Operations
                    .SelectMany(o => o.Methods)
                    .SelectMany(m => m.Parameters)
                    .Select(p => p.ModelType)
                    .OfType<SequenceTypeJv>()
                    .Distinct(ModelNameComparer.Instance)
                    .ToArray();

                foreach (SequenceTypeJv st in parameterSequenceTypes)
                {
                    var wrapperTemplate = new XmlListWrapperTemplate { Model = st };
                    await Write(wrapperTemplate, $"{packagePath}/{codeModel.ImplPackage.Trim('.')}/{st.XmlName.ToPascalCase()}Wrapper{ImplementationFileExtension}");
                }
            }

            //Enums
            await WriteEnumJavaFiles(codeModel, getPathFunction, getPackageFunction).ConfigureAwait(false);

            // Page class
            foreach (var pageClass in codeModel.pageClasses)
            {
                var pageTemplate = new PageTemplate
                {
                    Model = new PageJvaf(pageClass.Value, pageClass.Key.Key, pageClass.Key.Value),
                };
                await Write(pageTemplate, $"{packagePath}/implementation/{pageTemplate.Model.TypeDefinitionName.ToPascalCase()}{ImplementationFileExtension}");
            }

            // Exceptions
            await WriteExceptionJavaFiles(codeModel, AddPathPrefix("src/main/java"), null).ConfigureAwait(false);

            // package-info.java
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm)
            }, $"{packagePath}/{_packageInfoFileName}");
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm, "implementation")
            }, $"{packagePath}/implementation/{_packageInfoFileName}");

            if (true == AutoRest.Core.Settings.Instance.Host?.GetValue<bool?>("regenerate-manager").Result)
            {
                // Manager
                await Write(
                    new AzureServiceManagerTemplate { Model = codeModel },
                    $"{packagePath}/implementation/{codeModel.ServiceName}Manager{ImplementationFileExtension}");

                // POM
                await Write(new AzurePomTemplate { Model = codeModel }, "pom.xml");
            }
        }
    }
}
