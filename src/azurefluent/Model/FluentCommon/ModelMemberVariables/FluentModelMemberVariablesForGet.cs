// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Specialized variant of FluentModelMemberVariables for 'Get' method
    /// i.e. Memeber variables in this collection corresponds to 'Get' method parameter.
    /// </summary>
    public class FluentModelMemberVariablesForGet : FluentModelMemberVariables
    {
        public FluentModelMemberVariablesForGet() : base(null)
        {
            this.FluentMethodGroup = null;
        }

        protected FluentModelMemberVariablesForGet(SegmentFluentMethodGroup fluentMethodGroup, StandardFluentMethod createMethod) : base(createMethod)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        /// <summary>
        /// The fluent method group containing the fluent method for Get, whose parameters are used to
        /// derive the get member variables.
        /// </summary>
        public SegmentFluentMethodGroup FluentMethodGroup { get; private set; }
    }
}
