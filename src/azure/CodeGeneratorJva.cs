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
using AutoRest.Java.Azure.Model;
using AutoRest.Java.azure.Templates;
using AutoRest.Java.Model;
using AutoRest.Java.vanilla.Templates;
using System;

namespace AutoRest.Java.Azure
{
    public class CodeGeneratorJva : CodeGeneratorJv
    {
        private const string ClientRuntimePackage = "com.microsoft.azure:azure-client-runtime:1.0.0-beta6-SNAPSHOT from snapshot repo https://oss.sonatype.org/content/repositories/snapshots/";
        private const string _packageInfoFileName = "package-info.java";
        
        public override bool IsSingleFileGenerationSupported => true;

        public override string UsageInstructions => $"The {ClientRuntimePackage} maven dependency is required to execute the generated code.";

        /// <summary>
        /// Generates Azure Java code for service client.
        /// </summary>
        /// <param name="serviceClient"></param>
        /// <returns></returns>
        public override async Task Generate(CodeModel cm)
        {
            var packagePath = $"src/main/java/{cm.Namespace.ToLower().Replace('.', '/')}";

            // get Azure Java specific codeModel
            var codeModel = cm as CodeModelJva;
            if (codeModel == null)
            {
                throw new InvalidCastException("CodeModel is not a Azure Java CodeModel");
            }

            var prefixName = await AutoRest.Core.Settings.Instance.Host?.GetValue("prefix-model-type");
            if (prefixName != null)
            {
                codeModel.Name = prefixName + codeModel.Name;
                // operations
                foreach (MethodGroupJva methodGroup in codeModel.AllOperations)
                {
                    methodGroup.Name = prefixName + methodGroup.Name.ToPascalCase();
                }
            }

            if (true == AutoRest.Core.Settings.Instance.Host?.GetValue<bool?>("with-optional-parameters").Result)
            {
                foreach (MethodGroupJva methodGroup in codeModel.AllOperations)
                {
                    foreach (MethodJva method in methodGroup.Methods)
                    {
                        // Operation with optional arguments
                        var ps = method.Parameters.ToList();
                        var optionalParamCount = ps.Where(x => !x.IsRequired).Count();
                        if (optionalParamCount > 1)
                        {
                            // Parameter param = ps.Where(x => !x.IsRequired).First();
                            // param.Name = $"{method.Name}OptionalParameter";
                            // param.IsRequired = true;
                            // param.Documentation = "object representing the optional parameters to be set before calling the respective API";
                            // param.ModelTypeName = $"{method.Name.ToPascalCase()}OptionalParameter";
                            // var otherModel = methodGroup.InterfaceImports.First(x => x.Contains(".models."));
                            // string optionalModelImport = otherModel.Substring(0, otherModel.LastIndexOf(".") + 1) + $"{method.Name.ToPascalCase()}OptionalParameter";
                            // var interfaceImports = methodGroup.InterfaceImports.ToList();
                            // interfaceImports.Add(optionalModelImport);
                            // interfaceImports.OrderBy(x => x);
                            // methodGroup.InterfaceImports = methodGroup.InterfaceImports.Concat(new[] { optionalModelImport});
                            method.Extensions.Add("hasOptionalParameters", true);
                            var operationsTemplate = new ANewTemplateItem { Model = method };
                            await Write(operationsTemplate, $"{packagePath}/models/{method.Name.ToPascalCase()}OptionalParameter{ImplementationFileExtension}");
                        }
                    }
                }
            }

            // Service client
            var serviceClientTemplate = new AzureServiceClientTemplate { Model = codeModel };
            await Write(serviceClientTemplate, $"{packagePath}/implementation/{codeModel.Name.ToPascalCase()}Impl{ImplementationFileExtension}");

            // Service client interface
            var serviceClientInterfaceTemplate = new AzureServiceClientInterfaceTemplate { Model = codeModel };
            await Write(serviceClientInterfaceTemplate, $"{packagePath}/{cm.Name.ToPascalCase()}{ImplementationFileExtension}");

            // operations
            foreach (MethodGroupJva methodGroup in codeModel.AllOperations)
            {
                // Operation
                var operationsTemplate = new AzureMethodGroupTemplate { Model = methodGroup };
                await Write(operationsTemplate, $"{packagePath}/implementation/{methodGroup.TypeName.ToPascalCase()}Impl{ImplementationFileExtension}");
                
                // Operation interface
                var operationsInterfaceTemplate = new AzureMethodGroupInterfaceTemplate { Model = methodGroup };
                await Write(operationsInterfaceTemplate, $"{packagePath}/{methodGroup.TypeName.ToPascalCase()}{ImplementationFileExtension}");
            }

            //Models
            foreach (CompositeTypeJva modelType in cm.ModelTypes.Concat(codeModel.HeaderTypes))
            {
                if (modelType.Extensions.ContainsKey(AzureExtensions.ExternalExtension) &&
                    (bool)modelType.Extensions[AzureExtensions.ExternalExtension])
                {
                    continue;
                }
                if (modelType.IsResource)
                {
                    continue;
                }

                var modelTemplate = new ModelTemplate { Model = modelType };
                await Write(modelTemplate, $"{packagePath}/models/{modelType.Name.ToPascalCase()}{ImplementationFileExtension}");
            }

            //Enums
            foreach (EnumTypeJva enumType in cm.EnumTypes)
            {
                var enumTemplate = new EnumTemplate { Model = enumType };
                await Write(enumTemplate, $"{packagePath}/models/{enumTemplate.Model.Name.ToPascalCase()}{ImplementationFileExtension}");
            }

            // Page class
            foreach (var pageClass in codeModel.pageClasses)
            {
                var pageTemplate = new PageTemplate
                {
                    Model = new PageJva(pageClass.Value, pageClass.Key.Key, pageClass.Key.Value),
                };
                await Write(pageTemplate, $"{packagePath}/models/{pageTemplate.Model.TypeDefinitionName.ToPascalCase()}{ImplementationFileExtension}");
            }

            // Exceptions
            foreach (CompositeTypeJv exceptionType in codeModel.ErrorTypes)
            {
                if (exceptionType.Name == "CloudError")
                {
                    continue;
                }

                var exceptionTemplate = new ExceptionTemplate { Model = exceptionType };
                await Write(exceptionTemplate, $"{packagePath}/models/{exceptionTemplate.Model.ExceptionTypeDefinitionName}{ImplementationFileExtension}");
            }

            // package-info.java
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm)
            }, $"{packagePath}/{_packageInfoFileName}");
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm, "implementation")
            }, $"{packagePath}/implementation/{_packageInfoFileName}");
            await Write(new PackageInfoTemplate
            {
                Model = new PackageInfoTemplateModel(cm, "models")
            }, $"{packagePath}/models/{_packageInfoFileName}");

            if (true == AutoRest.Core.Settings.Instance.Host?.GetValue<bool?>("regenerate-manager").Result)
            {
                await Write(new AzurePomTemplate { Model = codeModel }, "pom.xml");
            }
        }
    }
}
