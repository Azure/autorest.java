// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Defines enum values indicating various ways an Azure resource can be updated.
    /// </summary>
    public enum UpdateType
    {
        /// <summary>
        /// Resource cannot be updated.
        /// </summary>
        None,
        /// <summary>
        /// Resource exists under a resource group and can be updated.
        /// </summary>
        WithResourceGroupAsParent,
        /// <summary>
        /// Resource exists under a parent resource (excluding resource group and subscription) and can be updated.
        /// </summary>
        AsNestedChild,
        /// <summary>
        /// Resource exists under a subscription and can be updated.
        /// </summary>
        WithSubscriptionAsParent,
        /// <summary>
        ///  Resource exists under a parameterized parent and can be updated.
        /// </summary>
        WithParameterizedParent
    }
}
