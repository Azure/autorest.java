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
        /// <param name="proxy">The REST API that the client will send requests to.</param>
        /// <param name="serviceClientName">The name of the ServiceClient that contains this MethodGroupClient.</param>
        /// <param name="variableType">The type of this MethodGroupClient when it is used as a variable.</param>
        /// <param name="variableName">The variable name for any instances of this MethodGroupClient.</param>
        /// <param name="clientMethods">The ClientMethods for this MethodGroupClient.</param>
        public MethodGroupClient(string package, string className, string interfaceName, IEnumerable<string> implementedInterfaces, Proxy proxy, string serviceClientName, string variableType, string variableName, IEnumerable<ClientMethod> clientMethods)
        {
            Package = package;
            ClassName = className;
            InterfaceName = interfaceName;
            ImplementedInterfaces = implementedInterfaces;
            Proxy = proxy;
            ServiceClientName = serviceClientName;
            VariableType = variableType;
            VariableName = variableName;
            ClientMethods = clientMethods;
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
        /// Get the interfaces that the client implements.
        /// </summary>
        public IEnumerable<string> ImplementedInterfaces { get; }

        /// <summary>
        /// Get the REST API that this client will send requests to.
        /// </summary>
        public Proxy Proxy { get; }

        /// <summary>
        /// Get the name of the ServiceClient that contains this MethodGroupClient.
        /// </summary>
        public string ServiceClientName { get; }

        /// <summary>
        /// Get the type of this MethodGroupClient when it is used as a variable.
        /// </summary>
        public string VariableType { get; }

        /// <summary>
        /// Get the variable name for any instances of this MethodGroupClient.
        /// </summary>
        public string VariableName { get; }

        /// <summary>
        /// The client method overloads for this MethodGroupClient.
        /// </summary>
        public IEnumerable<ClientMethod> ClientMethods { get; }

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

            if (includeImplementationImports)
            {
                ClassType proxyType = settings.IsAzureOrFluent ? ClassType.AzureProxy : ClassType.RestProxy;
                imports.Add(proxyType.FullName);
            }

            Proxy.AddImportsTo(imports, includeImplementationImports, settings);

            foreach (ClientMethod clientMethod in ClientMethods)
            {
                clientMethod.AddImportsTo(imports, includeImplementationImports, settings);
            }
        }
    }
}
