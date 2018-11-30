// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The location of a parameter within a HTTP request.
    /// </summary>
    public enum RequestParameterLocation
    {
        Body,

        FormData,

        Host,

        Path,

        Header,

        None,

        Query,
    }
}
