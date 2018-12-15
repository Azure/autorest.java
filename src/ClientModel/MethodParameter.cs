// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A parameter for a method.
    /// </summary>
    public class MethodParameter
    {
        /// <summary>
        /// Create a new Parameter with the provided properties.
        /// </summary>
        /// <param name="description">The description of this parameter.</param>
        /// <param name="isFinal">Whether or not this parameter is final.</param>
        /// <param name="wireType">The type of this parameter.</param>
        /// <param name="name">The name of this parameter.</param>
        /// <param name="isRequired">Whether or not this parameter is required.</param>
        /// <param name="isConstant">Whether or not this parameter has a constant value.</param>
        /// <param name="fromClient">Whether or not this parameter is from a client property.</param>
        /// <param name="annotations">The annotations that should be part of this Parameter's declaration.</param>
        public MethodParameter(string description, bool isFinal, IType wireType, string name, bool isRequired, bool isConstant, bool fromClient, string defaultValue, IEnumerable<ClassType> annotations)
        {
            Description = description;
            IsFinal = isFinal;
            WireType = wireType;
            Name = name;
            IsRequired = isRequired;
            IsConstant = isConstant;
            FromClient = fromClient;
            DefaultValue = defaultValue;
            Annotations = annotations;
        }

        /// <summary>
        /// The description of this parameter.
        /// </summary>
        public string Description { get; }

        /// <summary>
        /// Whether or not this parameter is final.
        /// </summary>
        public bool IsFinal { get; }

        /// <summary>
        /// The type of this parameter.
        /// </summary>
        public IType ClientType => WireType.ClientType;

        /// <summary>
        /// The type of this parameter.
        /// </summary>
        public IType WireType { get; }

        /// <summary>
        /// The name of this parameter.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// Whether or not this parameter is required.
        /// </summary>
        public bool IsRequired { get; }

        /// <summary>
        /// Whether or not this parameter has a constant value.
        /// </summary>
        public bool IsConstant { get; }

        /// <summary>
        /// Whether or not this parameter is from a client property.
        /// </summary>
        public bool FromClient { get; }

        public string DefaultValue { get; }

        /// <summary>
        /// The annotations that should be part of this Parameter's declaration.
        /// </summary>
        public IEnumerable<ClassType> Annotations { get; }

        /// <summary>
        /// The full declaration of this parameter as it appears in a method signature.
        /// </summary>
        public string Declaration =>
            string.Join("", Annotations.Select((ClassType annotation) => $"@{annotation.Name} ")) +
            (IsFinal ? "final " : "") +
            $"{ClientType} {Name}";

        /// <summary>
        /// Add this parameter's imports to the provided ISet of imports.
        /// </summary>
        /// <param name="imports">The set of imports to add to.</param>
        /// <param name="includeImplementationImports">Whether or not to include imports that are only necessary for method implementations.</param>
        public virtual void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
            foreach (ClassType annotation in Annotations)
            {
                annotation.AddImportsTo(imports, includeImplementationImports);
            }
            ClientType.AddImportsTo(imports, includeImplementationImports);
        }
    }
}
