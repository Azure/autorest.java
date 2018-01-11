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
        /// <param name="name">The name of the service.</param>
        /// <param name="description">The description of the service.</param>
        /// <param name="subpackages">The subpackage folder paths that will get package-info.java files.</param>
        /// <param name="enums">The enum types that are used by the service.</param>
        /// <param name="exceptions">The exception types that are used by the service.</param>
        /// <param name="xmlSequenceWrappers"></param>
        public Service(string name, string description, IEnumerable<string> subpackages, IEnumerable<ServiceEnum> enums, IEnumerable<ServiceException> exceptions, IEnumerable<XmlSequenceWrapper> xmlSequenceWrappers, IEnumerable<ServiceModel> models)
        {
            Name = name;
            Description = description;
            SubPackages = subpackages;
            Enums = enums;
            Exceptions = exceptions;
            XmlSequenceWrappers = xmlSequenceWrappers;
            Models = models;
        }

        /// <summary>
        /// The name of this service.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The description of this service.
        /// </summary>
        public string Description { get; }

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
        /// Get the XML sequence wrappers that are used by this service.
        /// </summary>
        public IEnumerable<XmlSequenceWrapper> XmlSequenceWrappers { get; }

        /// <summary>
        /// Get the model types that are used by this service.
        /// </summary>
        public IEnumerable<ServiceModel> Models { get; }
    }
}
