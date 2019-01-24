// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details needed to create a package-info class for the client.
    /// </summary>
    public class PackageInfo
    {
        public PackageInfo(string package, string description)
        {
            Package = package;
            Description = description;
        }

        public string Package;

        public string Description { get; }
    }
}
