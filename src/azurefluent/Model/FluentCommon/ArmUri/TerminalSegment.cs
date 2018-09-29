// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An ARMUri instance can optionally end with one TerminalSegment, such a segment will exists only if last segment
    /// of the raw Uri is not a parameter.
    /// e.g:
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets
    /// there will be a terminal segment with value 'availabilitySets'
    /// //
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets/{availabilitySetName}
    /// there will NOT be a terminal segment since the last raw segment is a parameter.
    /// 
    /// </summary>
    public class TerminalSegment : Segment
    {
        public TerminalSegment(string name, int position) : base(name, position)
        { }
    }
}
