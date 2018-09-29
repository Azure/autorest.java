// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Java.Model;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Base type for any specialized segment type that contains a parameter
    /// There are two such specialized types ParentSegment and PositionalSegment.
    /// </summary>
    public abstract class ParameterSegment : Segment
    {
        public ParameterJv Parameter { get; set; }

        public ParameterSegment(string name, int position) : base(name, position)
        { }
    }
}
