// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that describing "getInnerAsync" method in it's standard or generalized form that retrieves a standard inner resource using apiCall.
    /// </summary>
    public interface IGetInnerAsyncFunc
    {
        /// <summary>
        /// The imports needed when an impl class defines a getInnerAsync method in it.
        /// </summary>
        HashSet<string> ImportsForImpl { get; }
        /// <summary>
        /// The implementation of "getInnerAsync" variant in generalized form.
        /// </summary>
        string GeneralizedMethodImpl { get; }
        /// <summary>
        /// The name of "getInnerAsync" method variant when it is in generalzied form.
        /// </summary>
        string GeneralizedMethodName { get; }
        /// <summary>
        /// True if this type represents a "getInnerAync" method that can retrieve a resource.
        /// </summary>
        bool IsGetInnerSupported { get; }
        /// <summary>
        ///  The name of "getInnerAsync" method variant when it is in normal form.
        /// </summary>
        string MethodName { get; }
        /// <summary>
        /// String representing how the arguments to "getInnerAsync" should be specified.
        /// </summary>
        string MethodInvocationParameter { get; }
        /// <summary>
        ///  The implementation of "getInnerAsync" variant in normal form.
        /// </summary>
        /// <param name="applyOverride"></param>
        /// <returns></returns>
        string MethodImpl(bool applyOverride);
    }
}
