// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A page class that contains results that are received from a service request.
    /// </summary>
    public class MethodPageDetails
    {
        public MethodPageDetails(bool isNextMethod, IType pageType, GenericType pageImplType, string nextLinkVariableName, string nextLinkParameterName, MethodJv nextMethod, ParameterJv nextGroupParameter, string nextGroupParameterTypeName, string nextMethodInvocation, Func<MethodPageDetails, string> nextMethodParameterInvocation)
        {
            IsNextMethod = isNextMethod;
            PageType = pageType;
            PageImplType = pageImplType;
            NextLinkVariableName = nextLinkVariableName;
            NextLinkParameterName = nextLinkParameterName;
            NextMethod = nextMethod;
            NextGroupParameter = nextGroupParameter;
            NextGroupParameterTypeName = nextGroupParameterTypeName;
            NextMethodInvocation = nextMethodInvocation;
            NextMethodParameterInvocation = nextMethodParameterInvocation(this);
        }

        /// <summary>
        /// Get whether or not this method is a request to get the next page of a sequence of pages.
        /// </summary>
        public bool IsNextMethod { get; }

        public IType PageType { get; }

        public GenericType PageImplType { get; }

        public string NextLinkVariableName { get; }

        public string NextLinkParameterName { get; }

        public MethodJv NextMethod { get; }

        public ParameterJv NextGroupParameter { get; }

        public string NextGroupParameterTypeName { get; }

        public string NextMethodInvocation { get; }

        public string NextMethodParameterInvocation { get; }
    }
}
