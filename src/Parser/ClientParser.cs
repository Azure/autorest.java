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
    public class ClientParser : IParser<CodeModelJv, Client>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public ClientParser(ParserFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public Client Parse(CodeModelJv codeModel)
        {

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = factory.GetParser<CodeModelJv, ServiceClient>().Parse(codeModel);

            Manager manager = factory.GetParser<CodeModelJv, Manager>().Parse(codeModel);

            List<Model.EnumType> enumTypes = new List<Model.EnumType>();
            foreach (EnumTypeJv autoRestEnumType in codeModel.EnumTypes.Where(e => e != null))
            {
                IType type = factory.GetParser<IModelTypeJv, IType>().Parse(autoRestEnumType);
                if (type is Model.EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ClientException> exceptions = codeModel.ErrorTypes
                .Cast<CompositeTypeJv>()
                .Select(t => factory.GetParser<CompositeTypeJv, ClientException>().Parse(t))
                .Where(t => t != null);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel);

            IEnumerable<CompositeTypeJv> autoRestModelTypes = codeModel.ModelTypes
                .Union(codeModel.HeaderTypes)
                .Cast<CompositeTypeJv>()
                .Where((CompositeTypeJv autoRestModelType) => autoRestModelType.ShouldGenerateModel);

            var modelAll = string.Join(",", autoRestModelTypes.Select(armt => armt.Name));

            IEnumerable<ClientModel> models = autoRestModelTypes
                .Select((CompositeTypeJv autoRestCompositeType) => factory.GetParser<CompositeTypeJv, ClientModel>().Parse(autoRestCompositeType))
                .ToArray();

            IEnumerable<ClientResponse> responseModels = codeModel.Methods
                .Where(m => m.ReturnType.Headers != null)
                .Select(m => ParseResponse(m))
                .ToList();

            List<PackageInfo> packageInfos = new List<PackageInfo>();
            packageInfos.Add(new PackageInfo(
                settings.Package,
                $"This package contains the classes for {serviceClientName}.\n{serviceClientDescription}"));
            if (settings.GenerateClientInterfaces && !string.IsNullOrEmpty(settings.ImplementationSubpackage))
            {
                packageInfos.Add(new PackageInfo(
                    settings.GetPackage(settings.ImplementationSubpackage),
                    $"This package contains the implementations and inner classes for {serviceClientName}.\n{serviceClientDescription}"));
            }
            if (!settings.IsFluent && !string.IsNullOrEmpty(settings.ModelsSubpackage))
            {
                packageInfos.Add(new PackageInfo(
                    settings.GetPackage(settings.ModelsSubpackage),
                    $"This package contains the data models for {serviceClientName}.\n{serviceClientDescription}"));
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
            string package = settings.Package + "." + settings.ModelsSubpackage;
            string description = $"Contains all response data for the {method.Name} operation.";
            IType headersType = (factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)method.ReturnType.Headers) ?? PrimitiveType.Void).AsNullable();
            IType bodyType = (factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)method.ReturnType.Body) ?? PrimitiveType.Void).AsNullable();
            return new ClientResponse(name, package, description, headersType, bodyType);
        }

        private IEnumerable<XmlSequenceWrapper> ParseXmlSequenceWrappers(CodeModel codeModel)
        {
            string package = settings.GetPackage(settings.ImplementationSubpackage);
            List<XmlSequenceWrapper> xmlSequenceWrappers = new List<XmlSequenceWrapper>();
            if (codeModel.ShouldGenerateXmlSerialization)
            {
                // Every sequence type used as a parameter to a service method.
                IEnumerable<Method> allMethods = codeModel.Methods.Concat(codeModel.Operations.SelectMany(methodGroup => methodGroup.Methods));
                IEnumerable<Parameter> allParameters = allMethods.SelectMany(method => method.Parameters);

                foreach (Parameter parameter in allParameters)
                {
                    IType parameterType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)parameter.ModelType);

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