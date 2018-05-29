// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents "define" function or it's generalized form that starts
    /// fluent defintion of a resource.
    /// </summary>
    public interface IDefineFunc
    {
        string GeneralizedMethodDecl { get; }
        string GeneralizedMethodImpl { get; }
        string GeneralizedMethodName { get; }
        bool IsDefineSupported { get; }
        string MethodDecl { get; }
        string MethodImpl { get; }
        string MethodName { get; }
    }

}
