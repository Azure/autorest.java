// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Represents two consecutive segments in the raw uri of the form "parents/{name}"
    /// For the raw Uri - /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets/{availabilitySetName}
    /// the ParentSegments are:
    /// 
    ///     ||    Name                         Parameter                           Position ||
    ///     ==================================================================================
    ///  1. | subscriptions      | ParameterJv instance for {subscriptionId}     | 0
    ///     ----------------------------------------------------------------------------------
    ///  2. | resourceGroups     | ParameterJv instance for {resourceGroupName}  | 2
    ///     ----------------------------------------------------------------------------------
    ///  3. | availabilitySets   | ParameterJv instance for {availabilitySetName}| 6
    ///     ----------------------------------------------------------------------------------
    /// </summary>
    public class ParentSegment : ParameterSegment
    {
        public ParentSegment(string name, int position) : base(name, position)
        { }
    }
}
