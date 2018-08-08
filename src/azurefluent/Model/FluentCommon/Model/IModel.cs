// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An interface that various types representing return type of fluent method
    /// implements.
    /// </summary>
    public interface IModel
    {
        string RawModelName { get; }
    }
}
