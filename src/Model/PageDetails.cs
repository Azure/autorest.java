// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A page class that contains results that are received from a service request.
    /// </summary>
    public class PageDetails
    {
        public PageDetails(string nextLinkName, string itemName, string className)
        {
            NextLinkName = nextLinkName;
            ItemName = itemName;
            ClassName = className;
        }

        public string NextLinkName { get; }

        public string ItemName { get; }

        public string ClassName { get; }
    }
}
