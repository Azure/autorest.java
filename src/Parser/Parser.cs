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
    public class Parser
    {
        private TypeParser typeParser;
        private CompositeModelParser compositeModelParser;
        private CompositeExceptionParser compositeExceptionParser;

        public Parser(JavaSettings settings)
        {
            this.typeParser = new TypeParser(settings);
            this.compositeModelParser = new CompositeModelParser(settings);
            this.compositeExceptionParser = new CompositeExceptionParser(settings);
        }

        public Service ParseService(CodeModelJv codeModel)
        {

            string serviceClientName = codeModel.Name;
            string serviceClientDescription = codeModel.Documentation;

            ServiceClient serviceClient = codeModel.GenerateServiceClient();

            ServiceManager manager = codeModel.GenerateManager();

            List<Model.EnumType> enumTypes = new List<Model.EnumType>();
            foreach (EnumTypeJv autoRestEnumType in codeModel.EnumTypes.Where(e => e != null))
            {
                IType type = typeParser.Parse(autoRestEnumType);
                if (type is Model.EnumType enumType)
                {
                    enumTypes.Add(enumType);
                }
            }

            IEnumerable<ServiceException> exceptions = codeModel.ErrorTypes
                .Cast<CompositeTypeJv>()
                .Select(t => compositeExceptionParser.Parse(t))
                .Where(t => t != null);

            IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers = ParseXmlSequenceWrappers(codeModel);

            IEnumerable<CompositeTypeJv> autoRestModelTypes = codeModel.ModelTypes
                .Union(codeModel.HeaderTypes)
                .Cast<CompositeTypeJv>()
                .Where((CompositeTypeJv autoRestModelType) => autoRestModelType.ShouldGenerateModel);

            IEnumerable<ServiceModel> models = autoRestModelTypes
                .Select((CompositeTypeJv autoRestCompositeType) => compositeModelParser.Parse(autoRestCompositeType))
                .ToArray();

            IEnumerable<ResponseModel> responseModels = codeModel.Methods
                .Where(m => m.ReturnType.Headers != null)
                .Select(m => ParseResponse(m))
                .ToList();

            return new Service(serviceClientName, serviceClientDescription, enumTypes, exceptions, xmlSequenceWrappers, responseModels, models, manager, serviceClient);
        }

        private ResponseModel ParseResponse(Method method)
        {
            string name = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
            string package = settings.Package + "." + settings.ModelsSubpackage;
            string description = $"Contains all response data for the {method.Name} operation.";
            IType headersType = ((IModelTypeJv)method.ReturnType.Headers)?.GenerateType(settings).AsNullable();
            IType bodyType = ((IModelTypeJv)method.ReturnType.Body)?.GenerateType(settings).AsNullable();
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
                    IType parameterType = ((IModelTypeJv)parameter.ModelType)?.GenerateType(settings);

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