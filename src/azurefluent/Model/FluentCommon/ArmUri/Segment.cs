// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Base type for all segments in the ARM Uri.
    /// </summary>
    public abstract class Segment
    {
        /// <summary>
        /// The name of the segment.
        /// </summary>
        public string Name { get; }
        /// <summary>
        /// Position of the segment in the raw uri
        /// </summary>
        public int Position { get; }

        public Segment(string name, int position)
        {
            this.Name = name;
            this.Position = position;
        }
    }
}
