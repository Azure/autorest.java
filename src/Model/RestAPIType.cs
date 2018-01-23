// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A type that is used within a REST API method.
    /// </summary>
    public interface RestAPIType
    {
        /// <summary>
        /// Get the name of this type when it is being used from the client APIs.
        /// </summary>
        string ClientTypeName { get; }
    }
}
