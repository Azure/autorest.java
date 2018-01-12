// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of a group of methods within a ServiceClient.
    /// </summary>
    public class MethodGroupClient
    {
        /// <summary>
        /// Create a new MethodGroupClient with the provided properties.
        /// </summary>
        /// <param name="className">The name of the client's class.</param>
        /// <param name="interfaceName">The name of the client's interface.</param>
        /// <param name="implementedInterfaces">The interfaces that the client implements.</param>
        public MethodGroupClient(string className, string interfaceName, IEnumerable<string> implementedInterfaces, RestAPI restAPI)
        {
            ClassName = className;
            InterfaceName = interfaceName;
            ImplementedInterfaces = implementedInterfaces;
        }

        /// <summary>
        /// Get the name of this client's class.
        /// </summary>
        public string ClassName { get; }

        /// <summary>
        /// Get the name of this client's interface.
        /// </summary>
        public string InterfaceName { get; }

        /// <summary>
        /// Get the interfaces that the client implements.
        /// </summary>
        public IEnumerable<string> ImplementedInterfaces { get; }
    }
}
