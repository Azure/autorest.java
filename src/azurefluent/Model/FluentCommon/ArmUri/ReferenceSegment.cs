// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// The segments in the ARM Uri those are not ParentSegment, PositionalSegment or TerminalSegment.
    /// For the raw Uri - "/subscriptions/{subscriptionId}/providers/Microsoft.Compute/locations/{location}/publishers/{publisherName}/artifacttypes/vmextension/types/{type}/versions"
    /// the ReferenceSegment are:
    ///     ||    Name                         RefName      Position
    ///     ========================================================
    ///  1. | providers          | Microsoft.Compute     |   2
    ///     --------------------------------------------------------
    ///  2. | Microsoft.Compute  | locations             |   3
    ///     --------------------------------------------------------
    ///  3. | artifacttypes      | vmextension           |   8
    ///     --------------------------------------------------------
    ///  4. | vmextension        | types                 |   9
    ///     --------------------------------------------------------
    /// </summary>
    public class ReferenceSegment : Segment
    {
        public string RefName { get; }

        public ReferenceSegment(string name, int position, string refName) : base(name, position)
        {
            this.RefName = refName;
        }
    }
}
