// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentModelMemberVariablesForGet : FluentModelMemberVariables
    {
        public FluentModelMemberVariablesForGet() : base(null)
        {
            this.FluentMethodGroup = null;
        }

        protected FluentModelMemberVariablesForGet(FluentMethodGroup fluentMethodGroup, FluentMethod createMethod) : base(createMethod)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        public FluentMethodGroup FluentMethodGroup { get; private set; }
    }
}
