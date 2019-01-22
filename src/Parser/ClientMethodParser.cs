// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;
using System.Text.RegularExpressions;
using AutoRest.Extensions.Azure;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class ClientMethodParser : IParser<MethodJv, IEnumerable<ClientMethod>>
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        private static readonly IEnumerable<IType> unixTimeTypes = new IType[] { PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime };
        private static readonly IEnumerable<IType> returnValueWireTypeOptions = new IType[] { ClassType.Base64Url, ClassType.DateTimeRfc1123 }.Concat(unixTimeTypes);

        private JavaSettings settings;
        private ParserFactory factory;

        public ClientMethodParser(ParserFactory factory)
        {
            this.settings = factory.Settings;
            this.factory = factory;
        }

        public IEnumerable<ClientMethod> Parse(MethodJv method)
        {
            var _clientMethods = new List<ClientMethod>();
            ProxyMethod restAPIMethod = factory.GetParser<MethodJv, ProxyMethod>().Parse(method);
            IEnumerable<ParameterJv> autoRestClientMethodAndConstantParameters = method.Parameters
                .Cast<ParameterJv>()
                //Omit parameter-group properties for now since Java doesn't support them yet
                .Where((ParameterJv ParameterJv) => ParameterJv != null && !ParameterJv.IsClientProperty && !string.IsNullOrWhiteSpace(ParameterJv.Name))
                .OrderBy(item => !item.IsRequired);
            IEnumerable<ParameterJv> autoRestClientMethodParameters = autoRestClientMethodAndConstantParameters
                .Where((ParameterJv ParameterJv) => !ParameterJv.IsConstant)
                .OrderBy((ParameterJv ParameterJv) => !ParameterJv.IsRequired);
            IEnumerable<ParameterJv> autoRestRequiredClientMethodParameters = autoRestClientMethodParameters
                .Where(parameter => parameter.IsRequired);

            Response autoRestRestAPIMethodReturnType = method.ReturnType;
            IModelTypeJv autoRestRestAPIMethodReturnBodyType = (IModelTypeJv) autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.None);

            IType restAPIMethodReturnBodyClientType = factory.GetParser<IModelTypeJv, IType>().Parse(autoRestRestAPIMethodReturnBodyType.ClientType);

            GenericType pageImplType = null;
            IType deserializedResponseBodyType;
            IType pageType;

            if (settings.IsAzureOrFluent &&
                restAPIMethodReturnBodyClientType is ListType restAPIMethodReturnBodyClientListType &&
                (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation || restAPIMethod.SimulateAsPagingOperation))
            {
                IType restAPIMethodReturnBodyClientListElementType = restAPIMethodReturnBodyClientListType.ElementType;

                restAPIMethodReturnBodyClientType = GenericType.PagedList(restAPIMethodReturnBodyClientListElementType);

                string pageImplTypeName = ((SequenceTypeJv) autoRestRestAPIMethodReturnBodyType).PageImplType;

                string pageImplSubPackage = settings.IsFluent ? settings.ImplementationSubpackage : settings.ModelsSubpackage;
                string pageImplPackage = $"{settings.Package}.{pageImplSubPackage}";

                pageImplType = new GenericType(pageImplPackage, pageImplTypeName, restAPIMethodReturnBodyClientListElementType);
                deserializedResponseBodyType = pageImplType;

                pageType = GenericType.Page(restAPIMethodReturnBodyClientListElementType);
            }
            else
            {
                deserializedResponseBodyType = restAPIMethodReturnBodyClientType;

                pageType = restAPIMethodReturnBodyClientType.AsNullable();
            }

            MethodParameter serviceCallbackParameter = new MethodParameter(
                description: "the async ServiceCallback to handle successful and failed responses.",
                isFinal: false,
                wireType: GenericType.ServiceCallback(restAPIMethodReturnBodyClientType),
                name: "serviceCallback",
                isRequired: true,
                isConstant: false,
                fromClient: false,
                defaultValue: null,
                annotations: Enumerable.Empty<ClassType>());

            MethodParameter contextParameter = new MethodParameter(
                description: "The context to associate with this operation.",
                isFinal: false,
                wireType: ClassType.Context,
                name: "context",
                isRequired: true,
                isConstant: false,
                fromClient: false,
                defaultValue: null,
                annotations: Enumerable.Empty<ClassType>());

            GenericType serviceFutureReturnType = GenericType.ServiceFuture(restAPIMethodReturnBodyClientType);

            GenericType observablePageType = GenericType.Observable(pageType);

            List<IEnumerable<ParameterJv>> ParameterJvLists = new List<IEnumerable<ParameterJv>>()
            {
                autoRestClientMethodParameters
            };
            if (settings.RequiredParameterClientMethods && autoRestClientMethodParameters.Any(parameter => !parameter.IsRequired))
            {
                ParameterJvLists.Insert(0, autoRestRequiredClientMethodParameters);
            }

            bool addSimpleClientMethods = true;

            List<string> requiredNullableParameterExpressions = new List<string>();
            if (restAPIMethod.IsResumable)
            {
                var parameter = restAPIMethod.Parameters.First();
                requiredNullableParameterExpressions.Add(parameter.Name);
            }
            else
            {
                foreach (ParameterJv autoRestParameter in method.Parameters)
                {
                    if (!autoRestParameter.IsConstant && autoRestParameter.IsRequired)
                    {
                        IType parameterType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)autoRestParameter.ModelType);
                        if (autoRestParameter.IsNullable())
                        {
                            parameterType = parameterType.AsNullable();
                        }

                        if (!(parameterType is PrimitiveType))
                        {
                            string parameterExpression;
                            if (!autoRestParameter.IsClientProperty)
                            {
                                parameterExpression = autoRestParameter.Name;
                            }
                            else
                            {
                                string caller = (autoRestParameter.Method != null && autoRestParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                                string clientPropertyName = autoRestParameter.ClientProperty?.Name?.ToString();
                                if (!string.IsNullOrEmpty(clientPropertyName))
                                {
                                    CodeNamer codeNamer = CodeNamer.Instance;
                                    clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                                }
                                parameterExpression = $"{caller}.{clientPropertyName}()";
                            }

                            requiredNullableParameterExpressions.Add(parameterExpression);
                        }
                    }
                }
            }

            Func<bool, List<MethodTransformationDetail>> transformationFunc = onlyRequiredParameters => {
                var transformations = new List<MethodTransformationDetail>();
                foreach (ParameterTransformation transformation in method.InputParameterTransformation)
                {
                    Parameter transformationOutputParameter = transformation.OutputParameter;
                    if (!transformationOutputParameter.IsRequired && onlyRequiredParameters)
                    {
                        // already added in AddOptionalAndConstantVariables
                        continue;
                    }
                    IModelTypeJv transformationOutputParameterModelType = (IModelTypeJv) transformationOutputParameter.ModelType;
                    if (transformationOutputParameterModelType != null && !transformationOutputParameter.IsNullable() && transformationOutputParameterModelType is PrimaryTypeJv transformationOutputParameterModelPrimaryType)
                    {
                        PrimaryTypeJv transformationOutputParameterModelNonNullablePrimaryType = DependencyInjection.New<PrimaryTypeJv>(transformationOutputParameterModelPrimaryType.KnownPrimaryType);
                        transformationOutputParameterModelNonNullablePrimaryType.Format = transformationOutputParameterModelPrimaryType.Format;
                        transformationOutputParameterModelNonNullablePrimaryType.IsNullable = false;

                        transformationOutputParameterModelType = transformationOutputParameterModelNonNullablePrimaryType;
                    }
                    IModelTypeJv transformationOutputParameterClientType = transformationOutputParameterModelType.ClientType;

                    string outParamName;
                    if (!transformationOutputParameter.IsClientProperty)
                    {
                        outParamName = transformationOutputParameter.Name;
                    }
                    else
                    {
                        string caller = (transformationOutputParameter.Method != null && transformationOutputParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                        string clientPropertyName = transformationOutputParameter.ClientProperty?.Name?.ToString();
                        if (!string.IsNullOrEmpty(clientPropertyName))
                        {
                            CodeNamer codeNamer = CodeNamer.Instance;
                            clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                        }
                        outParamName = $"{caller}.{clientPropertyName}()";
                    }
                    while (method.Parameters.Any((Parameter parameter) =>
                    {
                        string parameterName;
                        if (!parameter.IsClientProperty)
                        {
                            parameterName = parameter.Name;
                        }
                        else
                        {
                            string caller = (parameter.Method != null && parameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = parameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            parameterName = $"{caller}.{clientPropertyName}()";
                        }
                        return parameterName == outParamName;
                    }))
                    {
                        outParamName += "1";
                    }
                    
                    transformationOutputParameter.Name = outParamName;
                    var itype = factory.GetParser<IModelTypeJv, IType>().Parse(transformationOutputParameterModelType).AsNullable();
                    transformations.Add(new MethodTransformationDetail(itype, transformationOutputParameter.IsRequired, outParamName, transformation.ParameterMappings));
                }
                return transformations;
            };

            if (settings.IsAzureOrFluent)
            {
                MethodJv nextMethod = null;
                string nextMethodInvocation = null;
                if (restAPIMethod.IsPagingNextOperation)
                {
                    nextMethod = method;

                    nextMethodInvocation = restAPIMethod.Name;
                    string nextMethodWellKnownMethodName = null;
                    if (!string.IsNullOrEmpty(method.MethodGroup?.Name?.ToString()))
                    {
                        if (restAPIMethod.MethodType != MethodType.Other)
                        {
                            int methodsWithSameType = method.MethodGroup.Methods.Count((Method methodGroupMethod) =>
                            {
                                MethodType methodGroupMethodType = MethodType.Other;
                                string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                switch (methodGroupMethod.HttpMethod)
                                {
                                    case HttpMethod.Get:
                                        if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                            && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                            && methodGroupMethod.ReturnType.Body.MethodHasSequenceType())
                                        {
                                            if (methodGroupMethodUrlSplits.Length == 5)
                                            {
                                                if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                {
                                                    methodGroupMethodType = MethodType.ListBySubscription;
                                                }
                                                else
                                                {
                                                    methodGroupMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                            {
                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                            }
                                        }
                                        else if (methodGroupMethodUrlSplits.IsTopLevelResourceUrl())
                                        {
                                            methodGroupMethodType = MethodType.Get;
                                        }
                                        break;

                                    case HttpMethod.Delete:
                                        if (methodGroupMethodUrlSplits.IsTopLevelResourceUrl())
                                        {
                                            methodGroupMethodType = MethodType.Delete;
                                        }
                                        break;
                                }
                                return methodGroupMethodType == restAPIMethod.MethodType;
                            });

                            if (methodsWithSameType == 1)
                            {
                                switch (restAPIMethod.MethodType)
                                {
                                    case MethodType.ListBySubscription:
                                        nextMethodWellKnownMethodName = "List";
                                        break;

                                    case MethodType.ListByResourceGroup:
                                        nextMethodWellKnownMethodName = "ListByResourceGroup";
                                        break;

                                    case MethodType.Delete:
                                        nextMethodWellKnownMethodName = "Delete";
                                        break;

                                    case MethodType.Get:
                                        nextMethodWellKnownMethodName = "GetByResourceGroup";
                                        break;

                                    default:
                                        throw new Exception("Flow should not hit this statement.");
                                }
                            }
                        }
                    }
                    if (!string.IsNullOrWhiteSpace(nextMethodWellKnownMethodName))
                    {
                        nextMethodInvocation = CodeNamer.Instance.GetUnique(nextMethodWellKnownMethodName, method, method.Parent.IdentifiersInScope, method.Parent.Children.Except(method.SingleItemAsEnumerable()));
                    }
                    nextMethodInvocation = nextMethodInvocation.ToCamelCase();
                }
                else if (restAPIMethod.IsPagingOperation)
                {
                    string nextMethodName = method.Extensions?.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
                    string nextMethodGroup = method.Extensions?.GetValue<Fixable<string>>("nextMethodGroup")?.Value;

                    nextMethod = method.CodeModel.Methods.Cast<MethodJv>()
                        .FirstOrDefault((MethodJv codeModelMethod) =>
                        {
                            bool result = nextMethodGroup.EqualsIgnoreCase(codeModelMethod.Group);
                            if (result)
                            {
                                string codeModelMethodName = codeModelMethod.Name;
                                string codeModelMethodWellKnownMethodName = null;
                                MethodGroup codeModelMethodMethodGroup = codeModelMethod.MethodGroup;
                                if (!string.IsNullOrEmpty(codeModelMethodMethodGroup?.Name?.ToString()))
                                {
                                    MethodType codeModelMethodType = MethodType.Other;
                                    string codeModelMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(codeModelMethod.Url, ""), "");
                                    string[] codeModelMethodUrlSplits = codeModelMethodUrl.Split('/');
                                    switch (codeModelMethod.HttpMethod)
                                    {
                                        case HttpMethod.Get:
                                            if ((codeModelMethodUrlSplits.Length == 5 || codeModelMethodUrlSplits.Length == 7)
                                                            && codeModelMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                            && codeModelMethod.ReturnType.Body.MethodHasSequenceType())
                                            {
                                                if (codeModelMethodUrlSplits.Length == 5)
                                                {
                                                    if (codeModelMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                    {
                                                        codeModelMethodType = MethodType.ListBySubscription;
                                                    }
                                                    else
                                                    {
                                                        codeModelMethodType = MethodType.ListByResourceGroup;
                                                    }
                                                }
                                                else if (codeModelMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                {
                                                    codeModelMethodType = MethodType.ListByResourceGroup;
                                                }
                                            }
                                            else if (codeModelMethodUrlSplits.IsTopLevelResourceUrl())
                                            {
                                                codeModelMethodType = MethodType.Get;
                                            }
                                            break;

                                        case HttpMethod.Delete:
                                            if (codeModelMethodUrlSplits.IsTopLevelResourceUrl())
                                            {
                                                codeModelMethodType = MethodType.Delete;
                                            }
                                            break;
                                    }

                                    if (codeModelMethodType != MethodType.Other)
                                    {
                                        int methodsWithSameType = codeModelMethodMethodGroup.Methods.Count((Method methodGroupMethod) =>
                                        {
                                            MethodType methodGroupMethodType = MethodType.Other;
                                            string methodGroupMethodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(methodGroupMethod.Url, ""), "");
                                            string[] methodGroupMethodUrlSplits = methodGroupMethodUrl.Split('/');
                                            switch (methodGroupMethod.HttpMethod)
                                            {
                                                case HttpMethod.Get:
                                                    if ((methodGroupMethodUrlSplits.Length == 5 || methodGroupMethodUrlSplits.Length == 7)
                                                                    && methodGroupMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                                                    && methodGroupMethod.ReturnType.Body.MethodHasSequenceType())
                                                    {
                                                        if (methodGroupMethodUrlSplits.Length == 5)
                                                        {
                                                            if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                                            {
                                                                methodGroupMethodType = MethodType.ListBySubscription;
                                                            }
                                                            else
                                                            {
                                                                methodGroupMethodType = MethodType.ListByResourceGroup;
                                                            }
                                                        }
                                                        else if (methodGroupMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                                        {
                                                            methodGroupMethodType = MethodType.ListByResourceGroup;
                                                        }
                                                    }
                                                    else if (methodGroupMethodUrlSplits.IsTopLevelResourceUrl())
                                                    {
                                                        methodGroupMethodType = MethodType.Get;
                                                    }
                                                    break;

                                                case HttpMethod.Delete:
                                                    if (methodGroupMethodUrlSplits.IsTopLevelResourceUrl())
                                                    {
                                                        methodGroupMethodType = MethodType.Delete;
                                                    }
                                                    break;
                                            }
                                            return methodGroupMethodType == restAPIMethod.MethodType;
                                        });

                                        if (methodsWithSameType == 1)
                                        {
                                            switch (codeModelMethodType)
                                            {
                                                case MethodType.ListBySubscription:
                                                    codeModelMethodWellKnownMethodName = "List";
                                                    break;

                                                case MethodType.ListByResourceGroup:
                                                    codeModelMethodWellKnownMethodName = "ListByResourceGroup";
                                                    break;

                                                case MethodType.Delete:
                                                    codeModelMethodWellKnownMethodName = "Delete";
                                                    break;

                                                case MethodType.Get:
                                                    codeModelMethodWellKnownMethodName = "GetByResourceGroup";
                                                    break;

                                                default:
                                                    throw new Exception("Flow should not hit this statement.");
                                            }
                                        }
                                    }
                                }
                                if (!string.IsNullOrWhiteSpace(codeModelMethodWellKnownMethodName))
                                {
                                    IParent methodParent = codeModelMethod.Parent;
                                    codeModelMethodName = CodeNamer.Instance.GetUnique(codeModelMethodWellKnownMethodName, codeModelMethod, methodParent.IdentifiersInScope, methodParent.Children.Except(codeModelMethod.SingleItemAsEnumerable()));
                                }

                                result = nextMethodName.EqualsIgnoreCase(codeModelMethodName);
                            }
                            return result;
                        });

                    if (nextMethodGroup == null || method.Group == nextMethod.Group)
                    {
                        nextMethodInvocation = nextMethodName;
                    }
                    else
                    {
                        nextMethodInvocation = $"{(method.Group.IsNullOrEmpty() ? "this" : "client")}.get{nextMethodGroup.ToPascalCase()}().{nextMethodName}";
                    }
                }

                string nextPageLinkParameterName = null;
                string nextPageLinkVariableName = null;
                string nextGroupTypeName = null;
                string groupedTypeName = null;
                Func<bool, MethodPageDetails, string> nextMethodParameterInvocation = (b, pd) => null;
                Parameter groupedType = null;
                if (nextMethod != null)
                {
                    nextPageLinkParameterName = nextMethod.Parameters
                        .Select((Parameter parameter) => parameter.Name.Value)
                        .First((string parameterName) => parameterName.StartsWith("next", StringComparison.OrdinalIgnoreCase));

                    nextPageLinkVariableName = nextPageLinkParameterName;
                    

                    IEnumerable<ParameterJv> nextMethodRestAPIParameters = nextMethod.Parameters.Cast<ParameterJv>()
                        .Where((ParameterJv parameter) => parameter != null && !parameter.IsClientProperty && !string.IsNullOrWhiteSpace(parameter.Name))
                        .OrderBy(item => !item.IsRequired);

                    Parameter nextGroupType = null;
                    nextMethodParameterInvocation = (onlyRequiredParameters, pageDetails) => {
                        if (!onlyRequiredParameters)
                        {
                            return string.Join(", ", nextMethodRestAPIParameters
                                .Where(p => !p.IsConstant)
                                .Select((Parameter parameter) => parameter.Name == pageDetails.NextLinkParameterName ? pageDetails.NextLinkVariableName : parameter.Name.Value));
                        }
                        else if (method.InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                        {
                            return string.Join(", ", nextMethodRestAPIParameters
                                .Select((Parameter parameter) => parameter.IsRequired ? (parameter.Name == pageDetails.NextLinkParameterName ? pageDetails.NextLinkVariableName : parameter.Name.ToString()) : "null"));
                        }
                        else
                        {
                            groupedType = method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                            nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                            List<string> invocations = new List<string>();
                            foreach (Parameter parameter in nextMethodRestAPIParameters)
                            {
                                string parameterName = parameter.Name;

                                if (parameter.IsRequired)
                                {
                                    invocations.Add(parameterName == pageDetails.NextLinkParameterName ? pageDetails.NextLinkVariableName : parameterName);
                                }
                                else if (parameterName == nextGroupType.Name && groupedType.IsRequired)
                                {
                                    invocations.Add(parameterName == pageDetails.NextLinkParameterName ? pageDetails.NextLinkVariableName : parameterName);
                                }
                                else
                                {
                                    invocations.Add("null");
                                }
                            }
                            return string.Join(", ", invocations);
                        }
                    };

                    if (restAPIMethod.IsPagingOperation && !method.InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                    {
                        groupedType = groupedType ?? method.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                        nextGroupType = nextGroupType ?? nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;

                        if (!nextGroupType.IsClientProperty)
                        {
                            nextGroupTypeName = nextGroupType.Name;
                        }
                        else
                        {
                            string caller = (nextGroupType.Method != null && nextGroupType.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = nextGroupType.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            nextGroupTypeName = $"{caller}.{clientPropertyName}()";
                        }

                        if (!groupedType.IsClientProperty)
                        {
                            groupedTypeName = groupedType.Name;
                        }
                        else
                        {
                            string caller = (groupedType.Method != null && groupedType.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = groupedType.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            groupedTypeName = $"{caller}.{clientPropertyName}()";
                        }
                    }
                }

                if (restAPIMethod.IsResumable)
                {
                    var opDefParam = restAPIMethod.Parameters.First();
                    var parameters = new List<MethodParameter>();
                    var expressionsToValidate = new List<string>();
                    parameters.Add(
                        new MethodParameter(
                            opDefParam.Description,
                            false,
                            opDefParam.WireType,
                            opDefParam.Name, true, false, false, null,
                            new List<ClassType>()));
                    _clientMethods.Add(new ClientMethod(
                        description: restAPIMethod.Description + " (resume watch)",
                        returnValue: new ReturnValue(
                            description: "the observable for the request",
                            type: GenericType.Observable(GenericType.OperationStatus(restAPIMethodReturnBodyClientType))),
                        name: restAPIMethod.Name,
                        parameters: parameters,
                        onlyRequiredParameters: true,
                        type: ClientMethodType.Resumable,
                        restAPIMethod: restAPIMethod,
                        expressionsToValidate: expressionsToValidate,
                        requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                        groupedParameter: groupedType,
                        groupedParameterTypeName: groupedTypeName,
                        methodPageDetails: null,
                        methodTransformationDetails: null));

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation)
                {
                    
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(method, onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParameterJvs.Select(p => factory.GetParser<ParameterJv, MethodParameter>().Parse(p));
                        if (settings.AddContextParameter)
                        {
                            parameters = parameters.Prepend(contextParameter);
                        }

                        int count = 0;
                        while (parameters.Any((MethodParameter clientMethodParameter) => clientMethodParameter.Name == nextPageLinkVariableName))
                        {
                            ++count;
                            nextPageLinkVariableName = nextPageLinkParameterName + count;
                        }

                        MethodPageDetails pageDetailsSync = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkParameterName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: groupedType as ParameterJv,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: details => nextMethodParameterInvocation(onlyRequiredParameters, details));

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: groupedType as ParameterJv,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: details => nextMethodParameterInvocation(onlyRequiredParameters, details));
                        
                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                type: restAPIMethodReturnBodyClientType),
                            name: restAPIMethod.Name,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.PagingSync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetailsSync,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? $"the {observablePageType} object if successful." : $"the observable to the {restAPIMethodReturnBodyClientType} object",
                                type: observablePageType),
                            name: restAPIMethod.SimpleAsyncMethodName,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.PagingAsync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                        GenericType singlePageMethodReturnType = GenericType.Single(pageType);
                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: $"the {singlePageMethodReturnType} object if successful.",
                                type: singlePageMethodReturnType),
                            name: restAPIMethod.PagingAsyncSinglePageMethodName,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.PagingAsyncSinglePage,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));
                    }

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.SimulateAsPagingOperation)
                {
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(method, onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParameterJvs.Select(p => factory.GetParser<ParameterJv, MethodParameter>().Parse(p));
                        if (settings.AddContextParameter)
                        {
                            parameters = parameters.Prepend(contextParameter);
                        }

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: groupedType as ParameterJv,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: details => nextMethodParameterInvocation(onlyRequiredParameters, details));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                type: GenericType.PagedList(restAPIMethodReturnBodyClientType)),
                            name: restAPIMethod.Name,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimulatedPagingSync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? $"the {observablePageType} object if successful." : $"the observable to the {restAPIMethodReturnBodyClientType} object",
                                type: GenericType.Observable(GenericType.Page(restAPIMethodReturnBodyClientType))),
                            name: restAPIMethod.SimpleAsyncMethodName,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.SimulatedPagingAsync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));
                    }

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.IsLongRunningOperation)
                {
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(method, onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParameterJvs.Select(p => factory.GetParser<ParameterJv, MethodParameter>().Parse(p));
                        if (settings.AddContextParameter)
                        {
                            parameters = parameters.Prepend(contextParameter);
                        }

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: groupedType != null ? groupedType as ParameterJv : null,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: details => nextMethodParameterInvocation(onlyRequiredParameters, details));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                                type: restAPIMethodReturnBodyClientType),
                            name: restAPIMethod.Name,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.LongRunningSync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: $"the {serviceFutureReturnType} object",
                                type: serviceFutureReturnType),
                            name: restAPIMethod.SimpleAsyncMethodName,
                            parameters: parameters.ConcatSingleItem(serviceCallbackParameter),
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.LongRunningAsyncServiceCallback,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                        _clientMethods.Add(new ClientMethod(
                            description: restAPIMethod.Description,
                            returnValue: new ReturnValue(
                                description: "the observable for the request",
                                type: GenericType.Observable(GenericType.OperationStatus(restAPIMethodReturnBodyClientType))),
                            name: restAPIMethod.SimpleAsyncMethodName,
                            parameters: parameters,
                            onlyRequiredParameters: onlyRequiredParameters,
                            type: ClientMethodType.LongRunningAsync,
                            restAPIMethod: restAPIMethod,
                            expressionsToValidate: expressionsToValidate,
                            requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                            groupedParameter: groupedType,
                            groupedParameterTypeName: groupedTypeName,
                            methodPageDetails: pageDetails,
                            methodTransformationDetails: transformationFunc(onlyRequiredParameters)));
                    }

                    addSimpleClientMethods = false;
                }
            }

            if (addSimpleClientMethods)
            {
                bool isFluentDelete = settings.IsFluent && restAPIMethod.Name.EqualsIgnoreCase("Delete") && autoRestRequiredClientMethodParameters.Count() == 2;

                foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                {
                    bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                    IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(method, onlyRequiredParameters, settings);

                    IEnumerable<MethodParameter> parameters = ParameterJvs.Select(p => factory.GetParser<ParameterJv, MethodParameter>().Parse(p));
                    if (settings.AddContextParameter)
                    {
                        parameters = parameters.Prepend(contextParameter);
                    }

                    _clientMethods.Add(new ClientMethod(
                        description: restAPIMethod.Description,
                        returnValue: new ReturnValue(
                            description: restAPIMethodReturnBodyClientType == PrimitiveType.Void ? null : $"the {restAPIMethodReturnBodyClientType} object if successful.",
                            type: restAPIMethodReturnBodyClientType),
                        name: restAPIMethod.Name,
                        parameters: parameters,
                        onlyRequiredParameters: onlyRequiredParameters,
                        type: ClientMethodType.SimpleSync,
                        restAPIMethod: restAPIMethod,
                        expressionsToValidate: expressionsToValidate,
                        requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                        groupedParameter: null,
                        groupedParameterTypeName: null,
                        methodPageDetails: null,
                        methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                    _clientMethods.Add(new ClientMethod(
                        description: restAPIMethod.Description,
                        returnValue: new ReturnValue(
                            description: $"a ServiceFuture which will be completed with the result of the network request.",
                            type: serviceFutureReturnType),
                        name: restAPIMethod.SimpleAsyncMethodName,
                        parameters: parameters.ConcatSingleItem(serviceCallbackParameter),
                        onlyRequiredParameters: onlyRequiredParameters,
                        type: ClientMethodType.SimpleAsyncServiceCallback,
                        restAPIMethod: restAPIMethod,
                        expressionsToValidate: expressionsToValidate,
                        requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                        groupedParameter: null,
                        groupedParameterTypeName: null,
                        methodPageDetails: null,
                        methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                    _clientMethods.Add(new ClientMethod(
                        description: restAPIMethod.Description,
                        returnValue: new ReturnValue(
                            description: $"a Single which performs the network request upon subscription.",
                            type: restAPIMethod.ReturnType.ClientType),
                        name: restAPIMethod.SimpleAsyncRestResponseMethodName,
                        parameters: parameters,
                        onlyRequiredParameters: onlyRequiredParameters,
                        type: ClientMethodType.SimpleAsyncRestResponse,
                        restAPIMethod: restAPIMethod,
                        expressionsToValidate: expressionsToValidate,
                        requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                        groupedParameter: null,
                        groupedParameterTypeName: null,
                        methodPageDetails: null,
                        methodTransformationDetails: transformationFunc(onlyRequiredParameters)));

                    IType asyncMethodReturnType;
                    if (restAPIMethodReturnBodyClientType != PrimitiveType.Void)
                    {
                        asyncMethodReturnType = GenericType.Maybe(restAPIMethodReturnBodyClientType);
                    }
                    else if (isFluentDelete)
                    {
                        asyncMethodReturnType = GenericType.Maybe(ClassType.Void);
                    }
                    else
                    {
                        asyncMethodReturnType = ClassType.Completable;
                    }
                    _clientMethods.Add(new ClientMethod(
                        description: restAPIMethod.Description,
                        returnValue: new ReturnValue(
                            description: $"a Single which performs the network request upon subscription.",
                            type: asyncMethodReturnType),
                        name: restAPIMethod.SimpleAsyncMethodName,
                        parameters: parameters,
                        onlyRequiredParameters: onlyRequiredParameters,
                        type: ClientMethodType.SimpleAsync,
                        restAPIMethod: restAPIMethod,
                        expressionsToValidate: expressionsToValidate,
                        requiredNullableParameterExpressions: requiredNullableParameterExpressions,
                        groupedParameter: null,
                        groupedParameterTypeName: null,
                        methodPageDetails: null,
                        methodTransformationDetails: transformationFunc(onlyRequiredParameters)));
                }
            }
            return _clientMethods;
        }

        private IEnumerable<string> GenerateValidateExpressions(MethodJv method, bool onlyRequiredParameters, JavaSettings settings)
        {
            List<string> expressionsToValidate = new List<string>();
            foreach (ParameterJv autoRestParameter in method.Parameters)
            {
                if (!autoRestParameter.IsConstant)
                {
                    IType parameterType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)autoRestParameter.ModelType);
                    if (autoRestParameter.IsNullable())
                    {
                        parameterType = parameterType.AsNullable();
                    }

                    if (!(parameterType is PrimitiveType) &&
                        !(parameterType is Model.EnumType) &&
                        parameterType != ClassType.Object &&
                        parameterType != ClassType.Integer &&
                        parameterType != ClassType.Long &&
                        parameterType != ClassType.Double &&
                        parameterType != ClassType.BigDecimal &&
                        parameterType != ClassType.String &&
                        parameterType != ClassType.DateTime &&
                        parameterType != ClassType.LocalDate &&
                        parameterType != ClassType.DateTimeRfc1123 &&
                        parameterType != ClassType.Duration &&
                        parameterType != ClassType.Boolean &&
                        parameterType != ClassType.ServiceClientCredentials &&
                        parameterType != ClassType.AzureTokenCredentials &&
                        parameterType != ClassType.UUID &&
                        parameterType != ClassType.Base64Url &&
                        parameterType != ClassType.UnixTime &&
                        parameterType != ClassType.UnixTimeDateTime &&
                        parameterType != ClassType.UnixTimeLong &&
                        parameterType != ArrayType.ByteArray &&
                        parameterType != GenericType.FlowableByteBuffer &&
                        (!onlyRequiredParameters || autoRestParameter.IsRequired))
                    {
                        string parameterExpressionToValidate;
                        if (!autoRestParameter.IsClientProperty)
                        {
                            parameterExpressionToValidate = autoRestParameter.Name;
                        }
                        else
                        {
                            string caller = (autoRestParameter.Method != null && autoRestParameter.Method.Group.IsNullOrEmpty() ? "this" : "this.client");
                            string clientPropertyName = autoRestParameter.ClientProperty?.Name?.ToString();
                            if (!string.IsNullOrEmpty(clientPropertyName))
                            {
                                CodeNamer codeNamer = CodeNamer.Instance;
                                clientPropertyName = codeNamer.CamelCase(codeNamer.RemoveInvalidCharacters(clientPropertyName));
                            }
                            parameterExpressionToValidate = $"{caller}.{clientPropertyName}()";
                        }

                        expressionsToValidate.Add(parameterExpressionToValidate);
                    }
                }
            }
            return expressionsToValidate;
        }
    }
}