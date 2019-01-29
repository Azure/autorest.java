// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
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
        /// <param name="package">The package that this service client belongs to.</param>
        /// <param name="className">The name of the client's class.</param>
        /// <param name="interfaceName">The name of the client's interface.</param>
        /// <param name="restAPI">The REST API that the client will send requests to.</param>
        /// <param name="methodGroupClients">The MethodGroupClients that belong to this ServiceClient.</param>
        /// <param name="properties">The properties of this ServiceClient</param>
        /// <param name="constructors">The constructors for this ServiceClient.</param>
        /// <param name="clientMethods">The client method overloads for this ServiceClient.</param>
        /// <param name="azureEnvironmentParameter">The AzureEnvironment parameter.</param>
        /// <param name="serviceClientCredentialsParameter">The credentials parameter.</param>
        /// <param name="httpPipelineParameter">The HttpPipeline parameter.</param>
        public ServiceClient(string package, string className, string interfaceName, Proxy restAPI, IEnumerable<MethodGroupClient> methodGroupClients, IEnumerable<ServiceClientProperty> properties, IEnumerable<Constructor> constructors, IEnumerable<ClientMethod> clientMethods, Lazy<ClientMethodParameter> azureEnvironmentParameter, Lazy<ClientMethodParameter> serviceClientCredentialsParameter, Lazy<ClientMethodParameter> httpPipelineParameter)
        {
            Package = package;
            ClassName = className;
            InterfaceName = interfaceName;
            RestAPI = restAPI;
            MethodGroupClients = methodGroupClients;
            Properties = properties;
            Constructors = constructors;
            ClientMethods = clientMethods;
            AzureEnvironmentParameter = azureEnvironmentParameter;
            ServiceClientCredentialsParameter = serviceClientCredentialsParameter;
            HttpPipelineParameter = httpPipelineParameter;
        }

        public string Package { get; }

        /// <summary>
        /// Get the name of this client's class.
        /// </summary>
        public string ClassName { get; }

        /// <summary>
        /// Get the name of this client's interface.
        /// </summary>
        public string InterfaceName { get; }

        /// <summary>
        /// Get the REST API that this client will send requests to.
        /// </summary>
        public Proxy RestAPI { get; }

        /// <summary>
        /// The MethodGroupClients that belong to this ServiceClient.
        /// </summary>
        public IEnumerable<MethodGroupClient> MethodGroupClients { get; }

        /// <summary>
        /// The properties of this ServiceClient.
        /// </summary>
        public IEnumerable<ServiceClientProperty> Properties { get; }

        /// <summary>
        /// The constructors for this ServiceClient.
        /// </summary>
        public IEnumerable<Constructor> Constructors { get; }

        /// <summary>
        /// The client method overloads for this ServiceClient.
        /// </summary>
        public IEnumerable<ClientMethod> ClientMethods { get; }

        public Lazy<ClientMethodParameter> AzureEnvironmentParameter { get; }

        public Lazy<ClientMethodParameter> ServiceClientCredentialsParameter { get; }

        public Lazy<ClientMethodParameter> HttpPipelineParameter { get; }

        /// <summary>
        /// Add this property's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports, JavaSettings settings)
        {
            foreach (ClientMethod clientMethod in ClientMethods)
            {
                clientMethod.AddImportsTo(imports, includeImplementationImports, settings);
            }

            if (includeImplementationImports)
            {
                if (settings.IsAzureOrFluent)
                {
                    imports.Add("com.microsoft.azure.v2.AzureServiceClient");
                    imports.Add("com.microsoft.azure.v2.AzureProxy");
                }
                else
                {
                    imports.Add("com.microsoft.rest.v2.ServiceClient");
                    imports.Add("com.microsoft.rest.v2.RestProxy");
                }

                if (!settings.IsFluent)
                {
                    imports.Add($"{settings.Package}.{InterfaceName}");
                    foreach (MethodGroupClient methodGroupClient in MethodGroupClients)
                    {
                        imports.Add($"{settings.Package}.{methodGroupClient.InterfaceName}");
                    }
                }

                foreach (ServiceClientProperty serviceClientProperty in Properties)
                {
                    serviceClientProperty.AddImportsTo(imports, includeImplementationImports);
                }

                foreach (Constructor constructor in Constructors)
                {
                    constructor.AddImportsTo(imports, includeImplementationImports);
                }
            }

            RestAPI?.AddImportsTo(imports, includeImplementationImports, settings);
        }
    }
}
