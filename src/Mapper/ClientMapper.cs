// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text.RegularExpressions;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Core.Utilities.Collections;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    /// <summary>
    /// Maps a CodeModelJv to a Client.
    /// </summary>
    public class ClientMapper : IMapper<CodeModelJv, Client>
    {
        private ClientMapper()
        {
        }

        private static ClientMapper _instance = new ClientMapper();
        public static ClientMapper Instance => _instance;

        public Client Map(CodeModelJv codeModel)
        {
            var settings = JavaSettings.Instance;

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = Mappers.ServiceClientMapper.Map(codeModel);

            Manager manager = Mappers.ManagerMapper.Map(codeModel);

            List<Model.EnumType> enumTypes = new List<Model.EnumType>();
            foreach (EnumTypeJv autoRestEnumType in codeModel.EnumTypes.Where(e => e != null))
            {
                IType type = Mappers.TypeMapper.Parse(autoRestEnumType);
                if (type is Model.EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ClientException> exceptions = codeModel.ErrorTypes
                .Cast<CompositeTypeJv>()
                .Select(t => Mappers.ExceptionMapper.Map(t))
                .Where(t => t != null);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel);

            IEnumerable<CompositeTypeJv> autoRestModelTypes = codeModel.ModelTypes
                .Union(codeModel.HeaderTypes)
                .Cast<CompositeTypeJv>()
                .Where((CompositeTypeJv autoRestModelType) => autoRestModelType.ShouldGenerateModel);

            var modelAll = string.Join(",", autoRestModelTypes.Select(armt => armt.Name));

            IEnumerable<ClientModel> models = autoRestModelTypes
                .Select((CompositeTypeJv autoRestCompositeType) => Mappers.ModelMapper.Map(autoRestCompositeType))
                .ToArray();

            IEnumerable<ClientResponse> responseModels = codeModel.Methods
                .Where(m => m.ReturnType.Headers != null)
                .Select(m => ParseResponse(m))
                .ToList();

            List<PackageInfo> packageInfos = new List<PackageInfo>();
            if (settings.GenerateClientInterfaces || !settings.GenerateClientAsImpl || string.IsNullOrEmpty(settings.ImplementationSubpackage))
            {
                packageInfos.Add(new PackageInfo(
                    settings.Package,
                    $"Package containing the classes for {serviceClientName}.\n{serviceClientDescription}"));
            }
            if (settings.GenerateClientAsImpl && !string.IsNullOrEmpty(settings.ImplementationSubpackage))
            {
                packageInfos.Add(new PackageInfo(
                    settings.GetPackage(settings.ImplementationSubpackage),
                    $"Package containing the implementations and inner classes for {serviceClientName}.\n{serviceClientDescription}"));
            }
            if (!settings.IsFluent && !string.IsNullOrEmpty(settings.ModelsSubpackage))
            {
                packageInfos.Add(new PackageInfo(
                    settings.GetPackage(settings.ModelsSubpackage),
                    $"Package containing the data models for {serviceClientName}.\n{serviceClientDescription}"));
            }

            return new Client(serviceClientName,
                serviceClientDescription,
                enumTypes,
                exceptions,
                xmlSequenceWrappers,
                responseModels,
                models,
                packageInfos,
                manager,
                serviceClient);
        }

        private ClientResponse ParseResponse(Method method)
        {
            string name = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
            string package = JavaSettings.Instance.Package + "." + JavaSettings.Instance.ModelsSubpackage;
            string description = $"Contains all response data for the {method.Name} operation.";
            IType headersType = (Mappers.TypeMapper.Map((IModelTypeJv)method.ReturnType.Headers) ?? PrimitiveType.Void).AsNullable();
            IType bodyType = (Mappers.TypeMapper.Map((IModelTypeJv)method.ReturnType.Body) ?? PrimitiveType.Void).AsNullable();
            return new ClientResponse(name, package, description, headersType, bodyType);
        }

        private IEnumerable<XmlSequenceWrapper> ParseXmlSequenceWrappers(CodeModel codeModel)
        {
            string package = JavaSettings.Instance.GetPackage(JavaSettings.Instance.ImplementationSubpackage);
            List<XmlSequenceWrapper> xmlSequenceWrappers = new List<XmlSequenceWrapper>();
            if (codeModel.ShouldGenerateXmlSerialization)
            {
                // Every sequence type used as a parameter to a service method.
                IEnumerable<Method> allMethods = codeModel.Methods.Concat(codeModel.Operations.SelectMany(methodGroup => methodGroup.Methods));
                IEnumerable<Parameter> allParameters = allMethods.SelectMany(method => method.Parameters);

                foreach (Parameter parameter in allParameters)
                {
                    IType parameterType = Mappers.TypeMapper.Map((IModelTypeJv)parameter.ModelType);

                    if (parameterType is ListType parameterListType && parameter.ModelType is SequenceTypeJv sequenceType)
                    {
                        string xmlRootElementName = sequenceType.XmlName;
                        string xmlListElementName = sequenceType.ElementType.XmlProperties?.Name ?? sequenceType.ElementXmlName;
                        if (!xmlSequenceWrappers.Any(existingWrapper => existingWrapper.XmlRootElementName == xmlRootElementName && existingWrapper.XmlListElementName == xmlListElementName))
                        {
                            HashSet<string> xmlSequenceWrapperImports = new HashSet<string>()
                            {
                                "com.fasterxml.jackson.annotation.JsonCreator",
                                "com.fasterxml.jackson.annotation.JsonProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty",
                                "com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement"
                            };
                            parameterListType.AddImportsTo(xmlSequenceWrapperImports, true);

                            xmlSequenceWrappers.Add(new XmlSequenceWrapper(package, parameterListType, xmlRootElementName, xmlListElementName, xmlSequenceWrapperImports));
                        }
                    }
                }
            }
            return xmlSequenceWrappers;
        }
    }
}