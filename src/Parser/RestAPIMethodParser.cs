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
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using AutoRest.Extensions.Azure;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    public class RestAPIMethodParser : IParser<MethodJv, RestAPIMethod>
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        private static readonly IEnumerable<IType> unixTimeTypes = new IType[] { PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime };
        private static readonly IEnumerable<IType> returnValueWireTypeOptions = new IType[] { ClassType.Base64Url, ClassType.DateTimeRfc1123 }.Concat(unixTimeTypes);

        private JavaSettings settings;
        private ParserFactory factory;

        private Dictionary<MethodJv, RestAPIMethod> parsed = new Dictionary<MethodJv, RestAPIMethod>();

        public RestAPIMethodParser(ParserFactory factory)
        {
            this.factory = factory;
            this.settings = factory.Settings;
        }

        public RestAPIMethod Parse(MethodJv method)
        {
            if (parsed.ContainsKey(method))
            {
                return parsed[method];
            }
            string restAPIMethodRequestContentType = method.RequestContentType;

            bool restAPIMethodIsPagingNextOperation = method.Extensions?.Get<bool>("nextLinkMethod") == true;

            string restAPIMethodUrlPath = method.Url.TrimStart('/');

            IEnumerable<HttpStatusCode> restAPIMethodExpectedResponseStatusCodes = method.Responses.Keys.OrderBy(statusCode => statusCode);

            ClassType restAPIMethodExceptionType = null;
            if (method.DefaultResponse.Body != null)
            {
                IModelTypeJv autoRestExceptionType = (IModelTypeJv) method.DefaultResponse.Body;
                IType errorType = factory.GetParser<IModelTypeJv, IType>().Parse(autoRestExceptionType);

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
            MethodGroup methodGroup = method.MethodGroup;
            if (!string.IsNullOrEmpty(methodGroup?.Name?.ToString()))
            {
                MethodType methodType = MethodType.Other;
                string methodUrl = methodTypeTrailing.Replace(methodTypeLeading.Replace(method.Url, ""), "");
                string[] methodUrlSplits = methodUrl.Split('/');
                switch (method.HttpMethod)
                {
                    case HttpMethod.Get:
                        if ((methodUrlSplits.Length == 5 || methodUrlSplits.Length == 7)
                            && methodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                            && method.ReturnType.Body.MethodHasSequenceType())
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
                IParent methodParent = method.Parent;
                restAPIMethodName = CodeNamer.Instance.GetUnique(wellKnownMethodName, method, methodParent.IdentifiersInScope, methodParent.Children.Except(new Method[] { method }));
            }
            else
            {
                restAPIMethodName = method.Name;
            }
            restAPIMethodName = restAPIMethodName.ToCamelCase();

            bool restAPIMethodSimulateMethodAsPagingOperation = (wellKnownMethodName == "List" || wellKnownMethodName == "ListByResourceGroup");

            bool restAPIMethodIsLongRunningOperation = method.Extensions?.Get<bool>(AzureExtensions.LongRunningExtension) == true;

            Response autoRestRestAPIMethodReturnType = method.ReturnType;
            IType responseBodyType = factory.GetParser<IModelTypeJv, IType>().Parse((IModelTypeJv)autoRestRestAPIMethodReturnType.Body??new PrimaryTypeJv(KnownPrimaryType.None));
            ListType responseBodyWireListType = responseBodyType as ListType;

            IModelTypeJv autorestRestAPIMethodReturnClientType = ((IModelTypeJv) autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.None)).ConvertToClientType();
            SequenceTypeJv autorestRestAPIMethodReturnClientSequenceType = autorestRestAPIMethodReturnClientType as SequenceTypeJv;

            bool autorestRestAPIMethodReturnTypeIsPaged = method.Extensions?.Get<bool>("nextLinkMethod") == true ||
                (method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                 method.Extensions[AzureExtensions.PageableExtension] != null);

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
            List<Parameter> autoRestMethodParameters = new List<Parameter>(method.Parameters);
            int streamBodyParameterIndex = autoRestMethodParameters.FindIndex(p => p.Location == ParameterLocation.Body && p.ModelType is PrimaryTypeJv mt && mt.KnownPrimaryType == KnownPrimaryType.Stream);
            if (streamBodyParameterIndex != -1 &&
                !autoRestMethodParameters.Any(p =>
                    p.Location == ParameterLocation.Header && p.SerializedName.EqualsIgnoreCase("Content-Length")))
            {
                Parameter contentLengthParameter = DependencyInjection.New<Parameter>();
                contentLengthParameter.Method = method;
                contentLengthParameter.IsRequired = true;
                contentLengthParameter.Location = ParameterLocation.Header;
                contentLengthParameter.SerializedName = "Content-Length";
                contentLengthParameter.Name = "contentLength";
                contentLengthParameter.Documentation = "The content length";
                contentLengthParameter.ModelType = DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.Long);

                // Add the Content-Length parameter before the body parameter
                autoRestMethodParameters.Insert(streamBodyParameterIndex, contentLengthParameter);
                method.ClearParameters();
                method.AddRange(autoRestMethodParameters);
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
                    string className = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
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
            
            bool isResumable = method.Extensions.ContainsKey("java-resume");
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
                List<Parameter> autoRestRestAPIMethodParameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

                List<Parameter> autoRestMethodLogicalParameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

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
                    restAPIMethodParameters.Add(factory.GetParser<ParameterJv, RestAPIParameter>().Parse(ParameterJv));
                }
            }
            restAPIMethodParameters = restAPIMethodParameters.Where(p => p.RequestParameterLocation == RequestParameterLocation.Path)
                                 .Union(restAPIMethodParameters.Where(p => p.RequestParameterLocation != RequestParameterLocation.Path)).ToList();

            string restAPIMethodDescription = "";
            if (!string.IsNullOrEmpty(method.Summary))
            {
                restAPIMethodDescription += method.Summary;
            }
            if (!string.IsNullOrEmpty(method.Description))
            {
                if (restAPIMethodDescription != "")
                {
                    restAPIMethodDescription += Environment.NewLine;
                }
                restAPIMethodDescription += method.Description;
            }

            bool restAPIMethodIsPagingOperation = method.Extensions.ContainsKey(AzureExtensions.PageableExtension) &&
                method.Extensions[AzureExtensions.PageableExtension] != null &&
                !restAPIMethodIsPagingNextOperation;

            IType restAPIMethodReturnValueWireType = returnValueWireTypeOptions.FirstOrDefault((IType type) => restAPIMethodReturnType.Contains(type));
            if (unixTimeTypes.Contains(restAPIMethodReturnValueWireType))
            {
                restAPIMethodReturnValueWireType = ClassType.UnixTime;
            }

            parsed[method] = new RestAPIMethod(
                restAPIMethodRequestContentType,
                restAPIMethodReturnType,
                restAPIMethodIsPagingNextOperation,
                method.HttpMethod,
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
                method,
                isResumable);
            
            return parsed[method];
        }
    }
}