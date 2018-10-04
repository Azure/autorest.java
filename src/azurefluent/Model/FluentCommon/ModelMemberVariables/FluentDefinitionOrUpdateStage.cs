// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes a "Definition" or "Update" nested stage interface in a Fluent Model Interface.
    /// The types that implements Fluent Model Interface also implements these Nested stage interfaces.
    /// The methods exposed by stage interface enable user to set values for member variables or properties of composite member variable of the model Impl.
    /// </summary>
    public class FluentDefinitionOrUpdateStage
    {
        /// <summary>
        /// The comment for the stage interface.
        /// </summary>
        public string Comment { get; private set; }

        /// <summary>
        /// Name of the stage interface.
        /// </summary>
        public string Name { get; private set; }

        /// <summary>
        /// The methods of the stage interface.
        /// </summary>
        public List<FluentDefinitionOrUpdateStageMethod> Methods { get; set; }

        /// <summary>
        /// Creates FluentDefinitionOrUpdateStage.
        /// </summary>
        /// <param name="resourceName">The name of whose create or update stage this type describes</param>
        /// <param name="name">the name of the method parameter that this stage sets</param>
        public FluentDefinitionOrUpdateStage(string resourceName, string name)
        {
            this.Name = name;
            this.Methods = new List<FluentDefinitionOrUpdateStageMethod>();
            this.Comment = $"The stage of the {resourceName} {{0}} allowing to specify {name.Substring("With".Length)}.";
        }
    }
}
