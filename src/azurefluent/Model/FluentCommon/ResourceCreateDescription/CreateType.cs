// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Defines enum values indicating various ways an Azure resource can be created.
    /// </summary>
    public enum CreateType
    {
        /// <summary>
        /// Resource cannot be created.
        /// </summary>
        None,
        /// <summary>
        /// Resource can be created directly under a resource group.
        /// </summary>
        WithResourceGroupAsParent,
        /// <summary>
        /// Resource can be created as child resource of a parent resource (excluding resource group and subscription)
        /// </summary>
        AsNestedChild,
        /// <summary>
        /// Resource can be created directly under the subscription.
        /// </summary>
        WithSubscriptionAsParent,
        /// <summary>
        /// Resource can be created under another resource which is parameterized.
        /// </summary>
        WithParameterizedParent
    }
}
