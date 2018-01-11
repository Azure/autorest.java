// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A container for the types associated for accessing a specific service.
    /// </summary>
    public class Service
    {
        /// <summary>
        /// Create a new Service with the provided values.
        /// </summary>
        /// <param name="clientName">The name of the service client.</param>
        /// <param name="clientDescription">The description of the service client.</param>
        /// <param name="subpackages">The subpackage folder paths that will get package-info.java files.</param>
        /// <param name="enums">The enum types that are used by the service.</param>
        /// <param name="exceptions">The exception types that are used by the service.</param>
        /// <param name="xmlSequenceWrappers"></param>
        public Service(string clientName, string clientDescription, IEnumerable<string> subpackages, IEnumerable<ServiceEnum> enums, IEnumerable<ServiceException> exceptions, IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers, ServiceManager manager)
        {
            ClientName = clientName;
            ClientDescription = clientDescription;
            SubPackages = subpackages;
            Enums = enums;
            Exceptions = exceptions;
            XmlSequenceWrappers = xmlSequenceWrappers;
            Manager = manager;
        }

        /// <summary>
        /// The name of this service client.
        /// </summary>
        public string ClientName { get; }

        /// <summary>
        /// The description of this service.
        /// </summary>
        public string ClientDescription { get; }

        /// <summary>
        /// The subpackage folder paths that will get package-info.java files.
        /// </summary>
        public IEnumerable<string> SubPackages { get; }

        /// <summary>
        /// Get the enum types that are used by this service.
        /// </summary>
        public IEnumerable<ServiceEnum> Enums { get; }

        /// <summary>
        /// Get the exception types that are used by this service.
        /// </summary>
        public IEnumerable<ServiceException> Exceptions { get; }

        /// <summary>
        /// Get the XML sequence wrapper types that are used by this service.
        /// </summary>
        public IEnumerable<XmlSequenceWrapper> XmlSequenceWrappers { get; }

        /// <summary>
        /// Get the Manager for this service.
        /// </summary>
        public ServiceManager Manager { get; }
    }
}
