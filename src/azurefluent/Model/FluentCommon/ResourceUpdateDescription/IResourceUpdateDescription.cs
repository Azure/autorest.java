// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes update of a 'Standard Model' (which describes an Azure resource) of a fluent method group.
    /// </summary>
    public interface IResourceUpdateDescription
    {
        HashSet<string> ImportsForModelInterface { get; }
        bool SupportsUpdating { get; }
        StandardFluentMethod UpdateMethod { get; }
        UpdateType UpdateType { get; }
    }
}