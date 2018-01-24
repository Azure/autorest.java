// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Net;

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
        /// <param name="asyncReturnType">The asynchronous return type of this RestAPIMethod.</param>
        public RestAPIMethod(string requestContentType, bool isPagingNextOperation, string httpMethod, string urlPath, IEnumerable<HttpStatusCode> responseExpectedStatusCodes, string unexpectedResponseExceptionType, string name, IEnumerable<RestAPIParameter> parameters, bool isPagingOperation, string description, bool simulateAsPagingOperation, bool isLongRunningOperation, RestAPIGenericType asyncReturnType)
        {
            RequestContentType = requestContentType;
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
            AsyncReturnType = asyncReturnType;
        }

        /// <summary>
        /// Get the Content-Type of the request.
        /// </summary>
        public string RequestContentType { get; }

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
        public string UnexpectedResponseExceptionType { get; }

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
        /// The asynchronous return type of this RestAPIMethod.
        /// </summary>
        public RestAPIGenericType AsyncReturnType { get; }
    }
}
