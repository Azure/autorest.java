// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details needed to create a Manager class for the service.
    /// </summary>
    public class ServiceManager
    {
        /// <summary>
        /// Create a new ServiceManager with the provided properties.
        /// </summary>
        /// <param name="serviceClientName">The name of the service client.</param>
        /// <param name="serviceName">The name of the service.</param>
        public ServiceManager(string serviceClientName, string serviceName)
        {
            ServiceClientName = serviceClientName;
            ServiceName = serviceName;
        }

        /// <summary>
        /// The name of the service client.
        /// </summary>
        public string ServiceClientName { get; }

        /// <summary>
        /// The name of the service.
        /// </summary>
        public string ServiceName { get; }
    }
}
