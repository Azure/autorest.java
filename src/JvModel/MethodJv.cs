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

namespace AutoRest.Java.Model
{
    public class MethodJv : Method
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        private static readonly IEnumerable<IType> unixTimeTypes = new IType[] { PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime };
        private static readonly IEnumerable<IType> returnValueWireTypeOptions = new IType[] { ClassType.Base64Url, ClassType.DateTimeRfc1123 }.Concat(unixTimeTypes);

        private RestAPIMethod _restAPIMethod;
        public RestAPIMethod GenerateRestAPIMethod(JavaSettings settings)
        {
            if (_restAPIMethod != null)
            {
                return _restAPIMethod;
            }
            string restAPIMethodRequestContentType = RequestContentType;

            bool restAPIMethodIsPagingNextOperation = Extensions?.Get<bool>("nextLinkMethod") == true;

            string restAPIMethodUrlPath = Url.TrimStart('/');

            IEnumerable<HttpStatusCode> restAPIMethodExpectedResponseStatusCodes = Responses.Keys.OrderBy(statusCode => statusCode);

            ClassType restAPIMethodExceptionType = null;
            if (DefaultResponse.Body != null)
            {
                IModelTypeJv autoRestExceptionType = (IModelTypeJv) DefaultResponse.Body;
                IType errorType = autoRestExceptionType?.GenerateType(settings);

                if (settings.IsAzureOrFluent && (errorType == null || errorType.ToString() == "CloudError"))
                {
                    restAPIMethodExceptionType = ClassType.CloudException;
                }
                else if (errorType is ClassType errorClassType)
                {
                    string exceptionPackage = settings.Package;
                    if (settings.IsFluent)
                    {
                        if (((CompositeTypeJv) autoRestExceptionType).IsInnerModel)
                        {
                            exceptionPackage = CodeGeneratorJv.GetPackage(settings, settings.ImplementationSubpackage);
                        }
                    }
                    else
                    {
                        exceptionPackage = CodeGeneratorJv.GetPackage(settings, settings.ModelsSubpackage);
                    }

                    string exceptionName = errorClassType.GetExtensionValue(SwaggerExtensions.NameOverrideExtension);
                    if (string.IsNullOrEmpty(exceptionName))
                    {
                        exceptionName = errorClassType.Name;
                        if (settings.IsFluent && !string.IsNullOrEmpty(exceptionName) && errorClassType.IsInnerModelType)
                        {
                            exceptionName += "Inner";
                        }
                        exceptionName += "Exception";
                    }
                    restAPIMethodExceptionType = new ClassType(exceptionPackage, exceptionName, null, null, false);
                }
                else
                {
                    restAPIMethodExceptionType = ClassType.RestException;
                }
            }

            string wellKnownMethodName = null;
            MethodGroup methodGroup = MethodGroup;
            if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
            {
                MethodType methodType = MethodType.Other;
                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(Url, ""), "");
                string[] methodUrlSplits = methodUrl.Split('/');
                switch (HttpMethod)
                {
                    case HttpMethod.Get:
                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                            && ReturnType.Body.MethodHasSequenceType())
                        {
                            if (methodUrlSplits.Length == 5)
                            {
                                if (methodUrlSplits[2].EqualsIgnoreCase("providers"))
                                {
                                    methodType = MethodType.ListBySubscription;
                                }
                                else
                                {
                                    methodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (methodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                            {
                                methodType = MethodType.ListByResourceGroup;
                            }
                        }
                        else if (methodUrlSplits.IsTopLevelResourceUrl())
                        {
                            methodType = MethodType.Get;
                        }
                        break;

                    case HttpMethod.Delete:
                        if (methodUrlSplits.IsTopLevelResourceUrl())
                        {
                            methodType = MethodType.Delete;
                        }
                        break;
                }

                if (methodType != MethodType.Other)
                {
                    int methodsWithSameType = methodGroup.Methods.Count((Method methodGroupMethod) =>
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
                        return methodGroupMethodType == methodType;
                    });

                    if (methodsWithSameType == 1)
                    {
                        switch (methodType)
                        {
                            case MethodType.ListBySubscription:
                                wellKnownMethodName = "List";
                                break;

                            case MethodType.ListByResourceGroup:
                                wellKnownMethodName = "ListByResourceGroup";
                                break;

                            case MethodType.Delete:
                                wellKnownMethodName = "Delete";
                                break;

                            case MethodType.Get:
                                wellKnownMethodName = "GetByResourceGroup";
                                break;

                            default:
                                throw new Exception("Flow should not hit this statement.");
                        }
                    }
                }
            }
            string restAPIMethodName;
            if (!string.IsNullOrWhiteSpace(wellKnownMethodName))
            {
                IParent methodParent = Parent;
                restAPIMethodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, this, methodParent.IdentifiersInScope, methodParent.Children.Except(new Method[] { this }));
            }
            else
            {
                restAPIMethodName = Name;
            }
            restAPIMethodName = restAPIMethodName.ToCamelCase();

            bool restAPIMethodSimulateMethodAsPagingOperation = (wellKnownMethodName == "List" || wellKnownMethodName == "ListByResourceGroup");

            bool restAPIMethodIsLongRunningOperation = Extensions?.Get<bool>(AzureExtensions.LongRunningExtension) == true;

            Response autoRestRestAPIMethodReturnType = ReturnType;
            IType responseBodyType = ((IModelTypeJv)autoRestRestAPIMethodReturnType.Body??new PrimaryTypeJv(KnownPrimaryType.None)).GenerateType(settings);
            ListType responseBodyWireListType = responseBodyType as ListType;

            IModelTypeJv autorestRestAPIMethodReturnClientType = ((IModelTypeJv) autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.None)).ConvertToClientType();
            SequenceTypeJv autorestRestAPIMethodReturnClientSequenceType = autorestRestAPIMethodReturnClientType as SequenceTypeJv;

            bool autorestRestAPIMethodReturnTypeIsPaged = Extensions?.Get<bool>("nextLinkMethod") == true ||
                (Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                 Extensions[AzureExtensions.PageableExtension] != null);

            if (settings.IsAzureOrFluent && responseBodyWireListType != null && autorestRestAPIMethodReturnTypeIsPaged)
            {
                SequenceTypeJv autoRestRestAPIMethodReturnClientPageListType = DependencyInjection.New<SequenceTypeJv>();
                autoRestRestAPIMethodReturnClientPageListType.ElementType = autorestRestAPIMethodReturnClientSequenceType.ElementType;

                string pageContainerSubPackage = (settings.IsFluent ? settings.ImplementationSubpackage : settings.ModelsSubpackage);
                string pageContainerPackage = $"{settings.Package}.{pageContainerSubPackage}";
                string pageContainerTypeName = autorestRestAPIMethodReturnClientSequenceType.PageImplType;

                autoRestRestAPIMethodReturnClientPageListType.PageImplType = pageContainerTypeName;

                responseBodyType = new GenericType(pageContainerPackage, pageContainerTypeName, responseBodyWireListType.ElementType);
            }

            // If there is a stream body and no Content-Length header parameter, add one automatically
            // Convert to list so we can use methods like FindIndex and Insert(int, T)
            List<Parameter> autoRestMethodParameters = new List<Parameter>(Parameters);
            int streamBodyParameterIndex = autoRestMethodParameters.FindIndex(p => p.Location == ParameterLocation.Body && p.ModelType is PrimaryTypeJv mt && mt.KnownPrimaryType == KnownPrimaryType.Stream);
            if (streamBodyParameterIndex != -1 &&
                !autoRestMethodParameters.Any(p =>
                    p.Location == ParameterLocation.Header && p.SerializedName.EqualsIgnoreCase("Content-Length")))
            {
                Parameter contentLengthParameter = DependencyInjection.New<Parameter>();
                contentLengthParameter.Method = this;
                contentLengthParameter.IsRequired = true;
                contentLengthParameter.Location = ParameterLocation.Header;
                contentLengthParameter.SerializedName = "Content-Length";
                contentLengthParameter.Name = "contentLength";
                contentLengthParameter.Documentation = "The content length";
                contentLengthParameter.ModelType = DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.Long);

                // Add the Content-Length parameter before the body parameter
                autoRestMethodParameters.Insert(streamBodyParameterIndex, contentLengthParameter);
                ClearParameters();
                AddRange(autoRestMethodParameters);
            }

            IType restAPIMethodReturnType;
            if (restAPIMethodIsLongRunningOperation)
            {
                IType operationStatusTypeArgument;
                if (settings.IsAzureOrFluent && responseBodyWireListType != null && (autorestRestAPIMethodReturnTypeIsPaged || restAPIMethodSimulateMethodAsPagingOperation))
                {
                    operationStatusTypeArgument = GenericType.Page(responseBodyWireListType.ElementType);
                }
                else
                {
                    operationStatusTypeArgument = responseBodyType;
                }
                restAPIMethodReturnType = GenericType.Observable(GenericType.OperationStatus(operationStatusTypeArgument));
            }
            else
            {
                IType singleValueType;
                if (autoRestRestAPIMethodReturnType.Headers != null)
                {
                    string className = MethodGroup.Name.ToPascalCase() + Name.ToPascalCase() + "Response";
                    singleValueType = new ClassType(settings.Package + "." + settings.ModelsSubpackage, className);
                }
                else if (responseBodyType.Equals(GenericType.FlowableByteBuffer))
                {
                    singleValueType = ClassType.StreamResponse;
                }
                else if (responseBodyType.Equals(PrimitiveType.Void))
                {
                    singleValueType = ClassType.VoidResponse;
                }
                else
                {
                    singleValueType = GenericType.BodyResponse(responseBodyType);
                }
                restAPIMethodReturnType = GenericType.Single(singleValueType);
            }

            List<RestAPIParameter> restAPIMethodParameters = new List<RestAPIParameter>();
            if (settings.AddContextParameter)
            {
                restAPIMethodParameters.Add(new RestAPIParameter(
                    description: "the user-defined context associated with this operation",
                    type: ClassType.Context,
                    name: "context",
                    requestParameterLocation: RequestParameterLocation.None,
                    requestParameterName: "context",
                    alreadyEncoded: true,
                    isConstant: false,
                    isRequired: true,
                    isNullable: false,
                    fromClient: false,
                    headerCollectionPrefix: null,
                    parameterReference: "context",
                    collectionFormat: CollectionFormat.None));
            }
            
            bool isResumable = Extensions.ContainsKey("java-resume");
            if (isResumable)
            {
                restAPIMethodParameters.Add(new RestAPIParameter(
                    description: "The OperationDescription object.",
                    type: ClassType.OperationDescription,
                    name: "operationDescription",
                    requestParameterLocation: RequestParameterLocation.None,
                    requestParameterName: "operationDescription",
                    alreadyEncoded: true,
                    isConstant: false,
                    isRequired: true,
                    isNullable: false,
                    fromClient: false,
                    headerCollectionPrefix: null,
                    parameterReference: "operationDescription",
                    collectionFormat: CollectionFormat.None));
            }
            else
            {
                List<Parameter> autoRestRestAPIMethodParameters = LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

                List<Parameter> autoRestMethodLogicalParameters = LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

                if (settings.IsAzureOrFluent && restAPIMethodIsPagingNextOperation)
                {
                    restAPIMethodParameters.Add(new RestAPIParameter(
                        description: "The URL to get the next page of items.",
                        type: ClassType.String,
                        name: "nextUrl",
                        requestParameterLocation: RequestParameterLocation.Path,
                        requestParameterName: "nextUrl",
                        alreadyEncoded: true,
                        isConstant: false,
                        isRequired: true,
                        isNullable: false,
                        fromClient: false,
                        headerCollectionPrefix: null,
                        parameterReference: "nextUrl",
                        collectionFormat: CollectionFormat.None));

                    autoRestMethodLogicalParameters.RemoveAll(p => p.Location == ParameterLocation.Path);
                }

                IEnumerable<Parameter> autoRestRestAPIMethodOrderedParameters = autoRestMethodLogicalParameters
                    .Where(p => p.Location == ParameterLocation.Path)
                    .Union(autoRestMethodLogicalParameters.Where(p => p.Location != ParameterLocation.Path));

                foreach (ParameterJv ParameterJv in autoRestRestAPIMethodOrderedParameters)
                {
                    restAPIMethodParameters.Add(ParameterJv.GenerateRestAPIParameter(settings));
                }
            }
            restAPIMethodParameters = restAPIMethodParameters.Where(p => p.RequestParameterLocation == RequestParameterLocation.Path)
                                 .Union(restAPIMethodParameters.Where(p => p.RequestParameterLocation != RequestParameterLocation.Path)).ToList();

            string restAPIMethodDescription = "";
            if (!string.IsNullOrEmpty(Summary))
            {
                restAPIMethodDescription += Summary;
            }
            if (!string.IsNullOrEmpty(Description))
            {
                if (restAPIMethodDescription != "")
                {
                    restAPIMethodDescription += Environment.NewLine;
                }
                restAPIMethodDescription += Description;
            }

            bool restAPIMethodIsPagingOperation = Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                Extensions[AzureExtensions.PageableExtension] != null &&
                !restAPIMethodIsPagingNextOperation;

            IType restAPIMethodReturnValueWireType = returnValueWireTypeOptions.FirstOrDefault((IType type) => restAPIMethodReturnType.Contains(type));
            if (unixTimeTypes.Contains(restAPIMethodReturnValueWireType))
            {
                restAPIMethodReturnValueWireType = ClassType.UnixTime;
            }

            _restAPIMethod = new RestAPIMethod(
                restAPIMethodRequestContentType,
                restAPIMethodReturnType,
                restAPIMethodIsPagingNextOperation,
                HttpMethod,
                restAPIMethodUrlPath,
                restAPIMethodExpectedResponseStatusCodes,
                restAPIMethodExceptionType,
                restAPIMethodName,
                restAPIMethodParameters,
                restAPIMethodIsPagingOperation,
                restAPIMethodDescription,
                restAPIMethodSimulateMethodAsPagingOperation,
                restAPIMethodIsLongRunningOperation,
                restAPIMethodReturnValueWireType,
                this,
                isResumable);
            
            return _restAPIMethod;
        }

        private List<ClientMethod> _clientMethods;
        public List<ClientMethod> GenerateClientMethods(JavaSettings settings)
        {
            if (_clientMethods != null)
            {
                return _clientMethods;
            }
            _clientMethods = new List<ClientMethod>();
            RestAPIMethod restAPIMethod = GenerateRestAPIMethod(settings);
            IEnumerable<ParameterJv> autoRestClientMethodAndConstantParameters = this.Parameters
                .Cast<ParameterJv>()
                //Omit parameter-group properties for now since Java doesn't support them yet
                .Where((ParameterJv ParameterJv) => ParameterJv != null && !ParameterJv.IsClientProperty && !string.IsNullOrWhiteSpace(ParameterJv.Name))
                .OrderBy(item => !item.IsRequired);
            IEnumerable<ParameterJv> autoRestClientMethodParameters = autoRestClientMethodAndConstantParameters
                .Where((ParameterJv ParameterJv) => !ParameterJv.IsConstant)
                .OrderBy((ParameterJv ParameterJv) => !ParameterJv.IsRequired);
            IEnumerable<ParameterJv> autoRestRequiredClientMethodParameters = autoRestClientMethodParameters
                .Where(parameter => parameter.IsRequired);

            Response autoRestRestAPIMethodReturnType = this.ReturnType;
            IModelTypeJv autoRestRestAPIMethodReturnBodyType = (IModelTypeJv) autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.None);

            IType restAPIMethodReturnBodyClientType = autoRestRestAPIMethodReturnBodyType.ConvertToClientType().GenerateType(settings);

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
                foreach (ParameterJv autoRestParameter in Parameters)
                {
                    if (!autoRestParameter.IsConstant && autoRestParameter.IsRequired)
                    {
                        IType parameterType = autoRestParameter.GenerateType(settings);

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

            if (settings.IsAzureOrFluent)
            {
                MethodJv nextMethod = null;
                string nextMethodInvocation = null;
                if (restAPIMethod.IsPagingNextOperation)
                {
                    nextMethod = this;

                    nextMethodInvocation = restAPIMethod.Name;
                    string nextMethodWellKnownMethodName = null;
                    if (!string.IsNullOrEmpty(MethodGroup?.Name?.ToString()))
                    {
                        if (restAPIMethod.MethodType != MethodType.Other)
                        {
                            int methodsWithSameType = MethodGroup.Methods.Count((Method methodGroupMethod) =>
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
                        nextMethodInvocation = CodeNamer.Instance.GetUnique(nextMethodWellKnownMethodName, this, Parent.IdentifiersInScope, Parent.Children.Except(this.SingleItemAsEnumerable()));
                    }
                    nextMethodInvocation = nextMethodInvocation.ToCamelCase();
                }
                else if (restAPIMethod.IsPagingOperation)
                {
                    string nextMethodName = Extensions?.GetValue<Fixable<string>>("nextMethodName")?.ToCamelCase();
                    string nextMethodGroup = Extensions?.GetValue<Fixable<string>>("nextMethodGroup")?.Value;

                    nextMethod = CodeModel.Methods.Cast<MethodJv>()
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

                    if (nextMethodGroup == null || Group == nextMethod.Group)
                    {
                        nextMethodInvocation = nextMethodName;
                    }
                    else
                    {
                        nextMethodInvocation = $"{(Group.IsNullOrEmpty() ? "this" : "client")}.get{nextMethodGroup.ToPascalCase()}().{nextMethodName}";
                    }
                }

                string nextPageLinkParameterName = null;
                string nextPageLinkVariableName = null;
                string nextGroupTypeName = null;
                string groupedTypeName = null;
                Func<bool, string> nextMethodParameterInvocation = null;
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
                    nextMethodParameterInvocation = onlyRequiredParameters => {
                        if (!onlyRequiredParameters)
                        {
                            return string.Join(", ", nextMethodRestAPIParameters
                                .Where(p => !p.IsConstant)
                                .Select((Parameter parameter) => parameter.Name == nextPageLinkParameterName ? nextPageLinkVariableName : parameter.Name.Value));
                        }
                        else if (InputParameterTransformation.IsNullOrEmpty() || nextMethod.InputParameterTransformation.IsNullOrEmpty())
                        {
                            return string.Join(", ", nextMethodRestAPIParameters
                                .Select((Parameter parameter) => parameter.IsRequired ? (parameter.Name == nextPageLinkParameterName ? nextPageLinkVariableName : parameter.Name.ToString()) : "null"));
                        }
                        else
                        {
                            groupedType = InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                            nextGroupType = nextMethod.InputParameterTransformation.First().ParameterMappings[0].InputParameter;
                            List<string> invocations = new List<string>();
                            foreach (Parameter parameter in nextMethodRestAPIParameters)
                            {
                                string parameterName = parameter.Name;

                                if (parameter.IsRequired)
                                {
                                    invocations.Add(parameterName == nextPageLinkParameterName ? nextPageLinkVariableName : parameterName);
                                }
                                else if (parameterName == nextGroupType.Name && groupedType.IsRequired)
                                {
                                    invocations.Add(parameterName == nextPageLinkParameterName ? nextPageLinkVariableName : parameterName);
                                }
                                else
                                {
                                    invocations.Add("null");
                                }
                            }
                            return string.Join(", ", invocations);
                        }
                    };

                    if (restAPIMethod.IsPagingOperation && !InputParameterTransformation.IsNullOrEmpty() && !nextMethod.InputParameterTransformation.IsNullOrEmpty())
                    {
                        groupedType = groupedType ?? InputParameterTransformation.First().ParameterMappings[0].InputParameter;
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
                            opDefParam.Type,
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
                        methodPageDetails: null));

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.IsPagingOperation || restAPIMethod.IsPagingNextOperation)
                {
                    
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParseClientMethodParameters(ParameterJvs, false, settings);

                        int count = 0;
                        var nextPageLinkVariableNameSync = nextPageLinkVariableName;
                        while (parameters.Any((MethodParameter clientMethodParameter) => clientMethodParameter.Name == nextPageLinkVariableName))
                        {
                            ++count;
                            nextPageLinkVariableNameSync = nextPageLinkParameterName + count;
                        }

                        MethodPageDetails pageDetailsSync = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableNameSync,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: (ParameterJv) groupedType,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: nextMethodParameterInvocation(onlyRequiredParameters));

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: (ParameterJv) groupedType,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: nextMethodParameterInvocation(onlyRequiredParameters));
                        
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
                            methodPageDetails: pageDetailsSync));

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
                            methodPageDetails: pageDetails));

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
                            methodPageDetails: pageDetails));
                    }

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.SimulateAsPagingOperation)
                {
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParseClientMethodParameters(ParameterJvs, false, settings);

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: (ParameterJv) groupedType,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: nextMethodParameterInvocation(onlyRequiredParameters));

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
                            methodPageDetails: pageDetails));

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
                            methodPageDetails: pageDetails));
                    }

                    addSimpleClientMethods = false;
                }
                else if (restAPIMethod.IsLongRunningOperation)
                {
                    foreach (IEnumerable<ParameterJv> ParameterJvs in ParameterJvLists)
                    {
                        bool onlyRequiredParameters = (ParameterJvs == autoRestRequiredClientMethodParameters);

                        IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(onlyRequiredParameters, settings);

                        IEnumerable<MethodParameter> parameters = ParseClientMethodParameters(ParameterJvs, false, settings);

                        MethodPageDetails pageDetails = new MethodPageDetails(
                            pageType: pageType,
                            pageImplType: pageImplType,
                            nextLinkVariableName: nextPageLinkVariableName,
                            nextLinkParameterName: nextPageLinkParameterName,
                            nextMethod: nextMethod,
                            nextGroupParameter: (ParameterJv) groupedType,
                            nextGroupParameterTypeName: nextGroupTypeName,
                            nextMethodInvocation: nextMethodInvocation,
                            nextMethodParameterInvocation: nextMethodParameterInvocation(onlyRequiredParameters));

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
                            methodPageDetails: pageDetails));

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
                            methodPageDetails: pageDetails));

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
                            methodPageDetails: pageDetails));
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

                    IEnumerable<string> expressionsToValidate = GenerateValidateExpressions(onlyRequiredParameters, settings);

                    IEnumerable<MethodParameter> parameters = ParseClientMethodParameters(ParameterJvs, false, settings);

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
                        methodPageDetails: null));

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
                        methodPageDetails: null));

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
                        methodPageDetails: null));

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
                        methodPageDetails: null));
                }
            }
            return _clientMethods;
        }

        public IEnumerable<string> GenerateValidateExpressions(bool onlyRequiredParameters, JavaSettings settings)
        {
            List<string> expressionsToValidate = new List<string>();
            foreach (ParameterJv autoRestParameter in Parameters)
            {
                if (!autoRestParameter.IsConstant)
                {
                    IType parameterType = autoRestParameter.GenerateType(settings);

                    if (!(parameterType is PrimitiveType) &&
                        !(parameterType is EnumType) &&
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

        private static IEnumerable<MethodParameter> ParseClientMethodParameters(IEnumerable<Parameter> autoRestParameters, bool parametersAreFinal, JavaSettings settings)
        {
            List<MethodParameter> parameters = new List<MethodParameter>();
            if (settings.AddContextParameter)
            {
                parameters.Add(new MethodParameter(
                    description: "The context to associate with this operation.",
                    isFinal: parametersAreFinal,
                    wireType: ClassType.Context,
                    name: "context",
                    isRequired: true,
                    isConstant: false,
                    fromClient: false,
                    defaultValue: null,
                    annotations: Enumerable.Empty<ClassType>()));
            }
            foreach (ParameterJv autoRestParameter in autoRestParameters)
            {
                parameters.Add(autoRestParameter.GenerateMethodParameter(parametersAreFinal, settings));
            }
            return parameters;
        }
    }
}