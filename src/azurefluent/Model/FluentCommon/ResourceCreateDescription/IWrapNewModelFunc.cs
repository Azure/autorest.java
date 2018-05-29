// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents 'wrapModel(string name)' function or it's generalized form that wraps 
    /// new standard inner model in it's impl model, such wrapping is done only when a new Azure 
    /// resource representing inner model needs to be created.
    /// </summary>
    public interface IWrapNewModelFunc
    {
        string GeneralizedMethodImpl { get; }
        string GeneralizedMethodName { get; }
        bool IsWrapNewModelSupported { get; }
        string MethodName { get; }
        string MethodImpl(bool applyOverride);
    }
}
