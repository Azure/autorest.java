// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Enumeration of different categories of "Standard Fluent Model" of a "Segment Fluent Method Group".
    /// </summary>
    public enum StanardModelType
    {
        /// <summary>
        /// Indicate that the "Standard Fluent Model" belongs to groupable "Segment Fluent Method Group".
        /// A groupable "Standard Fluent Model" is based on https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/resources/models/implementation/GroupableResourceCoreImpl.java
        /// </summary>
        GroupableTopLevel,
        /// <summary>
        /// Indicate that the "Standard Fluent Model" belongs to non-groupable "Segment Fluent Method Group".
        /// A non-groupable "Standard Fluent Model" is based on one of the following:
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableImpl.java
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/WrapperImpl.java
        /// </summary>
        NonGroupableTopLevel,
        /// <summary>
        /// Indicate that the "Standard Fluent Model" belongs to nested "Segment Fluent Method Group".
        /// A non-groupable "Standard Fluent Model" is based on one of the following:
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/CreatableUpdatableImpl.java
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/IndexableRefreshableImpl.java
        ///  https://github.com/Azure/autorest-clientruntime-for-java/blob/master/azure-arm-client-runtime/src/main/java/com/microsoft/azure/arm/model/implementation/WrapperImpl.java
        /// </summary>
        Nested
    }
}
