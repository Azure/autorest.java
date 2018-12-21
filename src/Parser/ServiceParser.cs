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
    public class ServiceParser : IParser<CodeModelJv, Service>
    {
        private JavaSettings settings;
        private ParserFactory factory;

        public ServiceParser(ParserFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public Service Parse(CodeModelJv codeModel)
        {

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = factory.GetParser<CodeModelJv, ServiceClient>().Parse(codeModel);

            ServiceManager manager = factory.GetParser<CodeModelJv, ServiceManager>().Parse(codeModel);

            List<Model.EnumType> enumTypes = new List<Model.EnumType>();
            foreach (EnumTypeJv autoRestEnumType in codeModel.EnumTypes.Where(e => e != null))
            {
                IType type = factory.GetParser<IModelTypeJv, IType>().Parse(autoRestEnumType);
                if (type is Model.EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ServiceException> exceptions = codeModel.ErrorTypes
                .Cast<CompositeTypeJv>()
                .Select(t => factory.GetParser<CompositeTypeJv, ServiceException>().Parse(t))
                .Where(t => t != null);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel);

            IEnumerable<CompositeTypeJv> autoRestModelTypes = codeModel.ModelTypes
                .Union(codeModel.HeaderTypes)
                .Cast<CompositeTypeJv>()
                .Where((CompositeTypeJv autoRestModelType) => autoRestModelType.ShouldGenerateModel);

            IEnumerable<ServiceModel> models = autoRestModelTypes
                .Select((CompositeTypeJv autoRestCompositeType) => factory.GetParser<CompositeTypeJv, ServiceModel>().Parse(autoRestCompositeType))
                .ToArray();

            IEnumerable<ResponseModel> responseModels = codeModel.Methods
                .Where(m => m.ReturnType.Headers != null)
                .Select(m => ParseResponse(m))
                .ToList();

            return new Service(serviceClientName,
                serviceClientDescription,
                enumTypes,
                exceptions,
                xmlSequenceWrappers,
                responseModels,
                models,
                manager,
                serviceClient);
        }

        private ResponseModel ParseResponse(Method method)
        {
            string name = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
            string package = settings.Package + "." + settings.ModelsSubpackage;
            string description = $"Contains all response data for the {method.Name} operation.";
            IType headersType =factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)method.ReturnType.Headers)?.AsNullable();
            IType bodyType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)method.ReturnType.Body)?.AsNullable();
            return new ResponseModel(name, package, description, headersType, bodyType);
        }

        private IEnumerable<XmlSequenceWrapper> ParseXmlSequenceWrappers(CodeModel codeModel)
        {
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

                            xmlSequenceWrappers.Add(new XmlSequenceWrapper(parameterListType, xmlRootElementName, xmlListElementName, xmlSequenceWrapperImports));
                        }
                    }
                }
            }
            return xmlSequenceWrappers;
        }
    }
}