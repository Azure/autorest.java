// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A member variable in a fluent model impl representing one of the ancestor in ARM path that identifies the azure model that fluent model wraps.
    /// </summary>
    public class FluentModelParentRefMemberVariable : FluentModelMemberVariable
    {
        public FluentModelParentRefMemberVariable(ParentSegment parentSegment)
            : base(variableName: parentSegment.Parameter.Name, fromParameter: parentSegment.Parameter)
        {
            this.ParentRefName = parentSegment.Name;
            this.IndexOfUriSegment = parentSegment.Position;
        }

        public string ParentRefName { get; private set; }

        public int IndexOfUriSegment
        {
            get; private set;
        }

        public string ExtractParentRefFrom(string source)
        {
            string toStringTemplate = Utils.ToStringTemplateForType(this.FromParameter.ClientType);
            // e.g - UUID.fromString(IdParsingUtils.getValueFromIdByName(inner.id(), "resourceGroups"));
            //
            return string.Format($"{toStringTemplate};", $"IdParsingUtils.getValueFromIdByName({source}, \"{ParentRefName}\")");
        }
    }
}
