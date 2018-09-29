// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// A member variable in a fluent model impl representing a positional param in ARM path that identifies the azure model that fluent model wraps.
    /// </summary>
    public class FluentModelPositionalPathMemberVariable : FluentModelMemberVariable
    {
        public FluentModelPositionalPathMemberVariable(PositionalSegment positionalSegment)
            : base(variableName: positionalSegment.Parameter.Name, fromParameter: positionalSegment.Parameter)
        {
            this.Position = positionalSegment.Position;
        }

        public int Position { get; private set; }

        public string ExtractPositionParameterFrom(string source)
        {
            string toStringTemplate = Utils.ToStringTemplateForType(this.FromParameter.ClientType);
            // e.g - UUID.fromString(IdParsingUtils.getValueFromIdByPosition(inner.id(), 2));
            //
            return string.Format($"{toStringTemplate};", $"IdParsingUtils.getValueFromIdByPosition({source}, {Position})");
        }
    }
}
