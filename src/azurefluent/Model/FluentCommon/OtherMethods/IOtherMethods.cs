// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing collection of OtherMethod in a fluent method group (fluent collection).
    /// 
    /// An "OtherMethod" represents a "Non-Standard Method" that needs to exposed in a fluent method group (fluent collection) interface
    /// [e.g. VirtualMachines::capture(captureParams), Disks::grantAccess(accessData)].
    /// "Standard Method"s are those exposed via Standard Fluent Interfaces defined under fluentcore.
    ///     https://github.com/Azure/azure-libraries-for-java/tree/master/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection
    ///     https://github.com/Azure/azure-libraries-for-java/tree/master/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection
    /// </summary>
    public interface IOtherMethods
    {
        /// <summary>
        /// The imports to be imported in a fluent method group (fluent collection) impl which contains implementation of OtherMethods.
        /// </summary>
        HashSet<string> ImportsForImpl { get; }
        /// <summary>
        /// The imports to be imported in a fluent method group (fluent collection) interface which contains declaration of OtherMethods.
        /// </summary>
        HashSet<string> ImportsForInterface { get; }
        /// <summary>
        /// The declaration of OtherMethods in a fluent method group (fluent collection) interface.
        /// </summary>
        IEnumerable<string> MethodDecls { get; }
        /// <summary>
        /// The implementation of OtherMethods in a fluent method group (fluent collection) impl.
        /// </summary>
        IEnumerable<string> MethodImpls { get; }
        /// <summary>
        /// The fluent models returned by the OtherMethods.
        /// </summary>
        IEnumerable<IModel> OtherFluentModels { get; }
    }
}