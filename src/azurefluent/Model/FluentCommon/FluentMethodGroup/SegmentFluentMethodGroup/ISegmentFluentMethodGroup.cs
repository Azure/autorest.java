// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a "Segment Fluent Method Group" derived from a 'collection segment' in ARM Uri.
    /// </summary>
    public interface ISegmentFluentMethodGroup : IFluentMethodGroup
    {
        /// <summary>
        /// The level of this "Segment Fluent Method Group" in the ARM Uri.
        /// </summary>
        int Level { get; }
        /// <summary>
        /// The inner methods belongs to this "Segment Fluent Method Group".
        /// </summary>
        IReadOnlyList<MethodJvaf> InnerMethods { get; }
        /// <summary>
        /// The parent "Fluent Method Group" of this "Segment Fluent Method Group".
        /// The parent segment "Fluent Method Group" derived from the parent segment in the ARM Uri.
        /// </summary>
        ISegmentFluentMethodGroup ParentFluentMethodGroup { get; }
        /// <summary>
        /// The list of "Segment Fluent Method Group"s derived from immediate child segments in ARM Uri.
        /// </summary>
        IReadOnlyList<ISegmentFluentMethodGroup> ChildFluentMethodGroups { get; }
        /// <summary>
        /// mapper to map non-standard model to standard model of this method group.
        /// </summary>
        NonStandardToStandardModelMappingHelper ModelMapper { get; }
    }
}
