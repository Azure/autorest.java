// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text.RegularExpressions;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRestMethod = AutoRest.Core.Model.Method;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A method within a Proxy.
    /// </summary>
    public class ProxyMethod
    {
        /// <summary>
        /// Create a new RestAPIMethod with the provided properties.
        /// </summary>
        /// <param name="requestContentType">The Content-Type of the request.</param>
        /// <param name="returnType">The type of value that is returned from this method.</param>
        /// <param name="isPagingNextOperation">Whether or not this method is a request to get the next page of a sequence of pages.</param>
        /// <param name="httpMethod">The HTTP method that will be used for this method.</param>
        /// <param name="urlPath">The path of this method's request URL.</param>
        /// <param name="responseExpectedStatusCodes">The status codes that are expected in the response.</param>
        /// <param name="returnValueWireType">The return value's type as it is received from the network (across the wire).</param>
        /// <param name="unexpectedResponseExceptionType">The exception type to throw if this method receives and unexpected response status code.</param>
        /// <param name="name">The name of this REST API method.</param>
        /// <param name="asyncReturnType">The return type of this method with its asynchronous container.</param>
        /// <param name="parameters">The parameters that are provided to this method.</param>
        /// <param name="description">The description of this method.</param>
        /// <param name="returnValueClientType">The return value's type as it is returned from the client.</param>
        /// <param name="autoRestMethod">The AutoRestMethod that this RestAPIMethod was created from.</param>
        /// <param name="isResumable">Whether or not this method is resumable.</param>
        public ProxyMethod(string requestContentType, IType returnType, bool isPagingNextOperation, HttpMethod httpMethod, string urlPath, IEnumerable<HttpStatusCode> responseExpectedStatusCodes, ClassType unexpectedResponseExceptionType, string name, IEnumerable<ProxyMethodParameter> parameters, string description, IType returnValueWireType, MethodJv autoRestMethod,
            bool isResumable)
        {
            RequestContentType = requestContentType;
            ReturnType = returnType;
            IsPagingNextOperation = isPagingNextOperation;
            HttpMethod = httpMethod;
            UrlPath = urlPath;
            ResponseExpectedStatusCodes = responseExpectedStatusCodes;
            UnexpectedResponseExceptionType = unexpectedResponseExceptionType;
            Name = name;
            Parameters = parameters;
            Description = description;
            ReturnValueWireType = returnValueWireType;
            AutoRestMethod = autoRestMethod;
            IsResumable = isResumable;
        }

        /// <summary>
        /// Get the Content-Type of the request.
        /// </summary>
        public string RequestContentType { get; }

        /// <summary>
        /// The value that is returned from this method.
        /// </summary>
        public IType ReturnType { get; }

        /// <summary>
        /// Get whether or not this method is a request to get the next page of a sequence of pages.
        /// </summary>
        public bool IsPagingNextOperation { get; }

        /// <summary>
        /// Get the HTTP method that will be used for this method.
        /// </summary>
        public HttpMethod HttpMethod { get; }

        /// <summary>
        /// Get the path of this method's request URL.
        /// </summary>
        public string UrlPath { get; }

        /// <summary>
        /// Get the status codes that are expected in the response.
        /// </summary>
        public IEnumerable<HttpStatusCode> ResponseExpectedStatusCodes { get; }

        /// <summary>
        /// Get the exception type to throw if this method receives and unexpected response status code.
        /// </summary>
        public ClassType UnexpectedResponseExceptionType { get; }

        /// <summary>
        /// Get the name of this Rest API method.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Get the parameters that are provided to this method.
        /// </summary>
        public IEnumerable<ProxyMethodParameter> Parameters { get; }

        /// <summary>
        /// Get the description of this method.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// The value of the ReturnValueWireType annotation for this method.
        /// </summary>
        public IType ReturnValueWireType { get; }

        /// <summary>
        /// The AutoRestMethod that this RestAPIMethod was created from.
        /// </summary>
        public MethodJv AutoRestMethod { get; }

        /// <summary>
        /// Get whether or not this method resumes polling of an LRO.
        /// </summary>
        public bool IsResumable { get; }

        public string PagingAsyncSinglePageMethodName => Name + "SinglePageAsync";

        public string SimpleAsyncMethodName => Name + "Async";

        public string SimpleAsyncRestResponseMethodName => Name + "WithRestResponseAsync";

        private MethodType? _methodType;
        public MethodType MethodType
        {
            get
            {
                if (_methodType != null)
                {
                    return _methodType.Value;
                }
                _methodType = MethodType.Other;
                if (!string.IsNullOrEmpty(AutoRestMethod.MethodGroup?.Name?.ToString()))
                {
                    string autoRestMethodUrl = new Regex("/+$").Replace(new Regex("^/+").Replace(UrlPath, ""), "");
                    string[] autoRestMethodUrlSplits = autoRestMethodUrl.Split('/');
                    switch (AutoRestMethod.HttpMethod)
                    {
                        case HttpMethod.Get:
                            if ((autoRestMethodUrlSplits.Length == 5 || autoRestMethodUrlSplits.Length == 7)
                                && autoRestMethodUrlSplits[0].EqualsIgnoreCase("subscriptions")
                                && AutoRestMethod.ReturnType.Body.MethodHasSequenceType())
                            {
                                if (autoRestMethodUrlSplits.Length == 5)
                                {
                                    if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("providers"))
                                    {
                                        _methodType = MethodType.ListBySubscription;
                                    }
                                    else
                                    {
                                        _methodType = MethodType.ListByResourceGroup;
                                    }
                                }
                                else if (autoRestMethodUrlSplits[2].EqualsIgnoreCase("resourceGroups"))
                                {
                                    _methodType = MethodType.ListByResourceGroup;
                                }
                            }
                            else if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
                            {
                                _methodType = MethodType.Get;
                            }
                            break;

                        case HttpMethod.Delete:
                            if (autoRestMethodUrlSplits.IsTopLevelResourceUrl())
                            {
                                _methodType = MethodType.Delete;
                            }
                            break;
                    }
                }
                return _methodType.Value;
            }
        }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            if (UnexpectedResponseExceptionType != null)
            {
                if (includeImplementationImports)
                {
                    imports.Add("com.azure.common.annotations.UnexpectedResponseExceptionType");
                }

                UnexpectedResponseExceptionType.AddImportsTo(imports, includeImplementationImports);
            }

            if (includeImplementationImports)
            {
                if (IsResumable)
                {
                    imports.Add("com.azure.common.annotations.ResumeOperation");
                }
                imports.Add($"com.azure.common.annotations.{HttpMethod.ToString().ToUpperInvariant()}");

                imports.Add("com.azure.common.annotations.ExpectedResponses");

                if (ReturnValueWireType != null)
                {
                    imports.Add("com.azure.common.annotations.ReturnValueWireType");
                    ReturnValueWireType.AddImportsTo(imports, includeImplementationImports);
                }

                ReturnType.AddImportsTo(imports, includeImplementationImports);

                foreach (ProxyMethodParameter parameter in Parameters)
                {
                    parameter.AddImportsTo(imports, includeImplementationImports, settings);
                }
            }
        }
    }
}
