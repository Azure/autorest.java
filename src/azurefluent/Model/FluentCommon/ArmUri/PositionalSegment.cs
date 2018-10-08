// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Represents a parameter in the raw ARM Uri which is not preceded by parent.
    /// For the raw Uri - "/{scope}/providers/Microsoft.EventGrid/eventSubscriptions/{eventSubscriptionName}/{filtered}"
    /// the PositionalSegments are:
    /// 
    ///     || Name           Parameter                              Position   ||
    ///     ======================================================================
    ///  1. |  scope        | ParameterJv instance for {scope}    |  0
    ///     ----------------------------------------------------------------------
    ///  2. |  filtered     | ParameterJv instance for {filtered} |  5
    /// </summary>
    public class PositionalSegment : ParameterSegment
    {
        public PositionalSegment(string name, int position) : base(name, position)
        { }
    }
}
