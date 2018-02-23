// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;
using System.Net;
using AutoRestMethod = AutoRest.Core.Model.Method;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A method within a REST API.
    /// </summary>
    public class RestAPIMethod
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
        /// <param name="isPagingOperation">Whether or not this method is a request to get the first page of a sequence of pages.</param>
        /// <param name="description">The description of this method.</param>
        /// <param name="simulateAsPagingOperation">Whether or not to simulate this method as a paging operation.</param>
        /// <param name="isLongRunningOperation">Whether or not this method is a long running operation.</param>
        /// <param name="returnValueClientType">The return value's type as it is returned from the client.</param>
        /// <param name="autoRestMethod">The AutoRestMethod that this RestAPIMethod was created from.</param>
        /// <param name="isResumable">Whether or not this method is resumable.</param>
        public RestAPIMethod(string requestContentType, IType returnType, bool isPagingNextOperation, string httpMethod, string urlPath, IEnumerable<HttpStatusCode> responseExpectedStatusCodes, ClassType unexpectedResponseExceptionType, string name, IEnumerable<RestAPIParameter> parameters, bool isPagingOperation, string description, bool simulateAsPagingOperation, bool isLongRunningOperation, IType returnValueWireType, AutoRestMethod autoRestMethod,
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
            IsPagingOperation = isPagingOperation;
            Description = description;
            SimulateAsPagingOperation = simulateAsPagingOperation;
            IsLongRunningOperation = isLongRunningOperation;
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
        public string HttpMethod { get; }

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
        public IEnumerable<RestAPIParameter> Parameters { get; }

        /// <summary>
        /// Get whether or not this method is a request to get the first page of a sequence of pages.
        /// </summary>
        public bool IsPagingOperation { get; }

        /// <summary>
        /// Get the description of this method.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Get whether or not to simulate this method as a paging operation.
        /// </summary>
        public bool SimulateAsPagingOperation { get; }

        /// <summary>
        /// Get whether or not this method is a long running operation.
        /// </summary>
        public bool IsLongRunningOperation { get; }

        /// <summary>
        /// The value of the ReturnValueWireType annotation for this method.
        /// </summary>
        public IType ReturnValueWireType { get; }

        /// <summary>
        /// The AutoRestMethod that this RestAPIMethod was created from.
        /// </summary>
        public AutoRestMethod AutoRestMethod { get; }

        /// <summary>
        /// Get whether or not this method resumes polling of an LRO.
        /// </summary>
        public bool IsResumable { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            imports.Add($"com.microsoft.rest.v2.annotations.{HttpMethod.ToUpperInvariant()}");
            
            imports.Add("com.microsoft.rest.v2.annotations.ExpectedResponses");

            if (ReturnValueWireType != null)
            {
                imports.Add("com.microsoft.rest.v2.annotations.ReturnValueWireType");
                ReturnValueWireType.AddImportsTo(imports, includeImplementationImports);
            }

            if (UnexpectedResponseExceptionType != null)
            {
                imports.Add("com.microsoft.rest.v2.annotations.UnexpectedResponseExceptionType");
                UnexpectedResponseExceptionType.AddImportsTo(imports, includeImplementationImports);
            }

            if (IsResumable)
            {
                imports.Add("com.microsoft.rest.v2.annotations.ResumeOperation");
                imports.Add("com.microsoft.rest.v2.OperationDescription");
            }

            ReturnType.AddImportsTo(imports, includeImplementationImports);

            foreach (RestAPIParameter parameter in Parameters)
            {
                parameter.AddImportsTo(imports, includeImplementationImports, settings);
            }
        }
    }
}
