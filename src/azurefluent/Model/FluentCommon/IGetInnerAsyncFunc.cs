// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents "getInnerAsync" function or it's generalized form that retrieves a
    /// standard inner resource using apiCall.
    /// </summary>
    public interface IGetInnerAsyncFunc
    {
        string GeneralizedMethodImpl { get; }
        string GeneralizedMethodName { get; }
        bool IsGetInnerSupported { get; }
        string MethodName { get; }
        string MethodImpl(bool applyOverride);
    }
}
