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
        /// <param name="restAPI">The REST API that the client will send requests to.</param>
        /// <param name="imports">The imports for the client.</param>
        /// <param name="serviceClientName">The name of the ServiceClient that contains this MethodGroupClient.</param>
        public MethodGroupClient(string className, string interfaceName, IEnumerable<string> implementedInterfaces, RestAPI restAPI, IEnumerable<string> imports, string serviceClientName)
        {
            ClassName = className;
            InterfaceName = interfaceName;
            ImplementedInterfaces = implementedInterfaces;
            RestAPI = restAPI;
            Imports = imports;
            ServiceClientName = serviceClientName;
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

        /// <summary>
        /// Get the REST API that this client will send requests to.
        /// </summary>
        public RestAPI RestAPI { get; }

        /// <summary>
        /// Get the imports for this client.
        /// </summary>
        public IEnumerable<string> Imports { get; }

        /// <summary>
        /// Get the name of the ServiceClient that contains this MethodGroupClient.
        /// </summary>
        public string ServiceClientName { get; }
    }
}
