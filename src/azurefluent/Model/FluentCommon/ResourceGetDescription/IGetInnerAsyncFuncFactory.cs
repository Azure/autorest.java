// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Entry point to retrieve "getInnerAsync" description for retrieving inner resource.
    /// </summary>
    public interface IGetInnerAsyncFuncFactory
    {
        /// <summary>
        /// Returns true if retrieving inner from a scope is supported.
        /// </summary>
        bool IsGetInnerSupported { get; }
        /// <summary>
        /// Gets a description of "getInnerAsync" that can be used to retrieve an inner resource in resource group scope.
        /// </summary>
        IGetInnerAsyncFunc GetFromResourceGroupAsyncFunc { get; }
        /// <summary>
        /// Gets a description of "getInnerAsync" function that can be used to retrieve an inner resource in parent scope.
        /// </summary>
        IGetInnerAsyncFunc GetFromParentAsyncFunc { get; }
    }
}
