// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of a ServiceClient.
    /// </summary>
    public class ServiceClient
    {
        /// <summary>
        /// Create a new ServiceClient with the provided properties.
        /// </summary>
        /// <param name="className">The name of the client's class.</param>
        /// <param name="interfaceName">The name of the client's interface.</param>
        /// <param name="imports">The imports for the client.</param>
        /// <param name="restAPI">The REST API that the client will send requests to.</param>
        /// <param name="methodGroupClients">The MethodGroupClients that belong to this ServiceClient.</param>
        /// <param name="properties">The properties of this ServiceClient</param>
        /// <param name="usesCredentials">Whether or not this ServiceClient uses credentials to authenticate to its service.</param>
        public ServiceClient(string className, string interfaceName, IEnumerable<string> imports, RestAPI restAPI, IEnumerable<MethodGroupClient> methodGroupClients, IEnumerable<ServiceClientProperty> properties, bool usesCredentials)
        {
            ClassName = className;
            InterfaceName = interfaceName;
            Imports = imports;
            RestAPI = restAPI;
            MethodGroupClients = methodGroupClients;
            Properties = properties;
            UsesCredentials = usesCredentials;
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
        /// Get the imports for this client.
        /// </summary>
        public IEnumerable<string> Imports { get; }

        /// <summary>
        /// Get the REST API that this client will send requests to.
        /// </summary>
        public RestAPI RestAPI { get; }

        /// <summary>
        /// The MethodGroupClients that belong to this ServiceClient.
        /// </summary>
        public IEnumerable<MethodGroupClient> MethodGroupClients { get; }

        /// <summary>
        /// The properties of this ServiceClient.
        /// </summary>
        public IEnumerable<ServiceClientProperty> Properties { get; }

        /// <summary>
        /// Get whether or not this ServiceClient uses credentials to authenticate to its service.
        /// </summary>
        public bool UsesCredentials { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            if (!settings.IsFluent)
            {
                imports.Add($"{settings.Package}.{InterfaceName}");
            }

            if (UsesCredentials)
            {
                ClassType.ServiceClientCredentials.AddImportsTo(imports, true);
            }

            foreach (ServiceClientProperty serviceClientProperty in Properties)
            {
                serviceClientProperty.AddImportsTo(imports, true);
            }

            foreach (MethodGroupClient methodGroupClient in MethodGroupClients)
            {
                methodGroupClient.AddImportsTo(imports, true, settings);
            }

            RestAPI?.AddImportsTo(imports, true, settings);
        }
    }
}
