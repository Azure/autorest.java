﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details needed to create a Manager class for the service.
    /// </summary>
    public class Manager
    {
        /// <summary>
        /// Create a new ServiceManager with the provided properties.
        /// </summary>
        /// <param name="serviceClientName">The name of the service client.</param>
        /// <param name="serviceName">The name of the service.</param>
        public Manager(string serviceClientName, string serviceName, Lazy<MethodParameter> azureTokenCredentialsParameter, Lazy<MethodParameter> httpPipelineParameter)
        {
            ServiceClientName = serviceClientName;
            ServiceName = serviceName;
            AzureTokenCredentialsParameter = azureTokenCredentialsParameter;
            HttpPipelineParameter = httpPipelineParameter;
        }

        /// <summary>
        /// The name of the service client.
        /// </summary>
        public string ServiceClientName { get; }

        /// <summary>
        /// The name of the service.
        /// </summary>
        public string ServiceName { get; }

        public Lazy<MethodParameter> AzureTokenCredentialsParameter { get; }

        public Lazy<MethodParameter> HttpPipelineParameter { get; }
    }
}
