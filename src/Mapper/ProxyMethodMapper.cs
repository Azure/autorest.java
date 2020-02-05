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
    /// <summary>
    /// Maps a MethodJv to a ProxyMethod.
    /// </summary>
    public class ProxyMethodMapper : IMapper<MethodJv, ProxyMethod>
    {
        private static readonly Regex methodTypeLeading = new Regex("^/+");
        private static readonly Regex methodTypeTrailing = new Regex("/+$");

        private static readonly IEnumerable<IType> unixTimeTypes = new IType[] { PrimitiveType.UnixTimeLong, ClassType.UnixTimeLong, ClassType.UnixTimeDateTime };
        private static readonly IEnumerable<IType> returnValueWireTypeOptions = new IType[] { ClassType.Base64Url, ClassType.DateTimeRfc1123 }.Concat(unixTimeTypes);

        private Dictionary<MethodJv, ProxyMethod> parsed = new Dictionary<MethodJv, ProxyMethod>();

        private static ProxyMethodMapper _instance = new ProxyMethodMapper();
        public static ProxyMethodMapper Instance => _instance;

        private ProxyMethodMapper()
        {
        }

        public ProxyMethod Map(MethodJv method)
        {
            var settings = JavaSettings.Instance;
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
                IType errorType = Mappers.TypeMapper.Map(autoRestExceptionType);

                if (settings.IsAzureOrFluent && (errorType == null || errorType.ToString() == "CloudError"))
                {
                    restAPIMethodExceptionType = ClassType.CloudException;
                }
                else if (errorType is ClassType errorClassType)
                {
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
                    
                    string exceptionPackage = settings.Package;
                    if (settings.IsCustomType(exceptionName))
                    {
                        exceptionPackage = settings.GetPackage(settings.CustomTypesSubpackage);
                    }
                    else if (settings.IsFluent)
                    {
                        if (((CompositeTypeJv) autoRestExceptionType).IsInnerModel)
                        {
                            exceptionPackage = settings.GetPackage(settings.ImplementationSubpackage);
                        }
                    }
                    else
                    {
                        exceptionPackage = settings.GetPackage(settings.ModelsSubpackage);
                    }

                    restAPIMethodExceptionType = new ClassType(exceptionPackage, exceptionName, null, null, false);
                }
                else
                {
                    restAPIMethodExceptionType = ClassType.RestException;
                }
            }

            string restAPIMethodName;
            if (!string.IsNullOrWhiteSpace(method.WellKnownMethodName))
            {
                IParent methodParent = method.Parent;
                restAPIMethodName = CodeNamer.Instance.GetUnique(method.WellKnownMethodName, method, methodParent.IdentifiersInScope, methodParent.Children.Except(new Method[] { method }));
            }
            else
            {
                restAPIMethodName = method.Name;
            }
            restAPIMethodName = restAPIMethodName.ToCamelCase();

            bool restAPIMethodSimulateMethodAsPagingOperation = (method.WellKnownMethodName == "List" || method.WellKnownMethodName == "ListByResourceGroup");

            bool restAPIMethodIsLongRunningOperation = method.Extensions?.Get<bool>(AzureExtensions.LongRunningExtension) == true;

            Response autoRestRestAPIMethodReturnType = method.ReturnType;
            IType responseBodyType = Mappers.TypeMapper.Map((IModelTypeJv)autoRestRestAPIMethodReturnType.Body??new PrimaryTypeJv(KnownPrimaryType.None));
            ListType responseBodyWireListType = responseBodyType as ListType;

            IModelTypeJv autorestRestAPIMethodReturnClientType = ((IModelTypeJv) autoRestRestAPIMethodReturnType.Body ?? DependencyInjection.New<PrimaryTypeJv>(KnownPrimaryType.None)).ClientType;
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
                restAPIMethodReturnType = GenericType.Flux(GenericType.OperationStatus(operationStatusTypeArgument));
            }
            else
            {
                IType singleValueType;
                if (autoRestRestAPIMethodReturnType.Headers != null)
                {
                    string className = method.MethodGroup.Name.ToPascalCase() + method.Name.ToPascalCase() + "Response";
                    string subpackage = settings.ModelsSubpackage;
                    if (settings.IsCustomType(className))
                    {
                        subpackage = settings.CustomTypesSubpackage;
                    }
                    singleValueType = new ClassType(settings.GetPackage(subpackage), className);
                }
                else if (responseBodyType.Equals(GenericType.FluxByteBuffer))
                {
                    singleValueType = ClassType.StreamResponse;
                }
                else if (responseBodyType.Equals(PrimitiveType.Void))
                {
                    singleValueType = GenericType.Response(ClassType.Void);
                }
                else
                {
                    singleValueType = GenericType.BodyResponse(responseBodyType);
                }
                restAPIMethodReturnType = GenericType.Mono(singleValueType);
            }

            List<ProxyMethodParameter> restAPIMethodParameters = new List<ProxyMethodParameter>();
            
            bool isResumable = method.Extensions.ContainsKey("java-resume");
            if (isResumable)
            {
                restAPIMethodParameters.Add(new ProxyMethodParameter(
                    description: "The OperationDescription object.",
                    wireType: ClassType.OperationDescription,
                    clientType: ClassType.OperationDescription,
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
                    defaultValue: null,
                    collectionFormat: CollectionFormat.None));
            }
            else
            {
                List<Parameter> autoRestRestAPIMethodParameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

                List<Parameter> autoRestMethodLogicalParameters = method.LogicalParameters.Where(p => p.Location != ParameterLocation.None).ToList();

                if (settings.IsAzureOrFluent && restAPIMethodIsPagingNextOperation)
                {
                    restAPIMethodParameters.Add(new ProxyMethodParameter(
                        description: "The URL to get the next page of items.",
                        wireType: ClassType.String,
                        clientType: ClassType.String,
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
                        defaultValue: null,
                        collectionFormat: CollectionFormat.None));

                    autoRestMethodLogicalParameters.RemoveAll(p => p.Location == ParameterLocation.Path);
                }

                IEnumerable<Parameter> autoRestRestAPIMethodOrderedParameters = autoRestMethodLogicalParameters
                    .Where(p => p.Location == ParameterLocation.Path)
                    .Union(autoRestMethodLogicalParameters.Where(p => p.Location != ParameterLocation.Path));

                foreach (ParameterJv parameterJv in autoRestRestAPIMethodOrderedParameters)
                {
                    restAPIMethodParameters.Add(Mappers.ProxyParameterMapper.Map(parameterJv));
                }
            }
            restAPIMethodParameters = restAPIMethodParameters.Where(p => p.RequestParameterLocation == RequestParameterLocation.Path)
                                 .Union(restAPIMethodParameters.Where(p => p.RequestParameterLocation != RequestParameterLocation.Path)).ToList();
                                 
            if (settings.AddContextParameter)
            {
                restAPIMethodParameters.Add(new ProxyMethodParameter(
                    description: "the user-defined context associated with this operation",
                    wireType: ClassType.Context,
                    clientType: ClassType.Context,
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
                    defaultValue: null,
                    collectionFormat: CollectionFormat.None));
            }

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

            IType restAPIMethodReturnValueWireType = returnValueWireTypeOptions.FirstOrDefault((IType type) => restAPIMethodReturnType.Contains(type));
            // if (unixTimeTypes.Contains(restAPIMethodReturnValueWireType))
            // {
            //     restAPIMethodReturnValueWireType = ClassType.UnixTime;
            // }

            parsed[method] = new ProxyMethod(
                restAPIMethodRequestContentType,
                restAPIMethodReturnType,
                restAPIMethodIsPagingNextOperation,
                method.HttpMethod,
                restAPIMethodUrlPath,
                restAPIMethodExpectedResponseStatusCodes,
                restAPIMethodExceptionType,
                restAPIMethodName,
                restAPIMethodParameters,
                restAPIMethodDescription,
                restAPIMethodReturnValueWireType,
                method,
                isResumable);
            
            return parsed[method];
        }
    }
}