// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details of an exception type that is used by a service.
    /// </summary>
    public class ClientException
    {
        private readonly string name;
        private readonly string errorName;
        private readonly string package;

        /// <summary>
        /// Create a new ServiceException with the provided properties.
        /// </summary>
        /// <param name="name">The name of the ServiceException type.</param>
        /// <param name="errorName">The name of the error type contained by the ServiceException.</param>
        /// <param name="subpackage">The subpackage that this Exception will appear in.</param>
        public ClientException(string package, string name, string errorName)
        {
            this.package = package;
            this.name = name;
            this.errorName = errorName;
        }

        /// <summary>
        /// The name of the ServiceException type.
        /// </summary>
        public string Name => name;

        /// <summary>
        /// The name of the error type contained by the ServiceException.
        /// </summary>
        public string ErrorName => errorName;

        /// <summary>
        /// The package that this Enum will appear in.
        /// </summary>
        public string Package => package;
    }
}
