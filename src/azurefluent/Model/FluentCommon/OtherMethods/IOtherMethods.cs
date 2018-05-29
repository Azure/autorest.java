// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public interface IOtherMethods
    {
        HashSet<string> ImportsForImpl { get; }
        HashSet<string> ImportsForInterface { get; }
        IEnumerable<string> MethodDecls { get; }
        IEnumerable<string> MethodImpls { get; }
        IEnumerable<FluentModel> OtherFluentModels { get; }
    }
}