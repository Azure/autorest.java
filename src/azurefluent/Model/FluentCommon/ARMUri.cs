// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ARMUri : List<Segment>
    {
        private readonly MethodJvaf method;
        private readonly string rawUrl;

        public ARMUri(MethodJvaf method)
        {
            this.method = method;
            this.rawUrl = method.Url;
            this.Init();
        }

        private void Init()
        {
            if (string.IsNullOrEmpty(this.rawUrl))
            {
                return;
            }
            IEnumerable<string> parts = this.rawUrl.Split("/").Where(part => !string.IsNullOrEmpty(part));
            //
            IEnumerator<string> itr = parts.GetEnumerator();
            if (!itr.MoveNext())
            {
                return;
            }
            //
            int pos = 0;
            // First Segment
            //
            if (itr.Current.StartsWith("{"))
            {
                string name = itr.Current.Trim(new char[] { '{', '}' });
                this.Add(new PositionalSegment
                {
                    Name = name,
                    Position = pos,
                    Parameter = this.method.Parameters
                        .OfType<ParameterJv>()
                        .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name))
            });
            }
            else
            {
                this.Add(new ParentSegment
                {
                    Name = itr.Current
                });
            }
            //
            // Intermediate segments
            //
            while (itr.MoveNext())
            {
                if (itr.Current.StartsWith("{"))
                {
                    string name = itr.Current.Trim(new char[] { '{', '}' });
                    if (this.Last() is ParentSegment)
                    {
                        ParentSegment parentSegment = (ParentSegment)this.Last();
                        parentSegment.Parameter = this.method.Parameters
                            .OfType<ParameterJv>()
                            .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name));
                    }
                    else
                    {
                        pos++;
                        this.Add(new PositionalSegment
                        {
                            Name = name,
                            Position = pos,
                            Parameter = this.method.Parameters
                                .OfType<ParameterJv>()
                                .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name))
                        });
                    }
                }
                else
                {
                    if (this.Last() is ParentSegment)
                    {
                        ParentSegment parentSegment = (ParentSegment)this.Last();
                        if (parentSegment.Parameter == null)
                        {
                            this.RemoveAt(this.Count - 1);
                            this.Add(new ReferenceSegment
                            {
                                Name = parentSegment.Name,
                                RefName = itr.Current
                            });
                        }
                    }
                    this.Add(new ParentSegment
                    {
                        Name = itr.Current
                    });
                }
            }
            //
            if (this.Last() is ParentSegment)
            {
                ParentSegment parentSegment = (ParentSegment)this.Last();
                if (parentSegment.Parameter == null)
                {
                    this.RemoveAt(this.Count - 1);
                    this.Add(new TerminalSegment
                    {
                        Name = parentSegment.Name
                    });
                }
            }
        }
    }

    public class Segment
    {
        public string Name { get; set; }
    }

    public class TerminalSegment : Segment
    {
    }

    public class ReferenceSegment : Segment
    {
        public string RefName { get; set; }
    }

    public class SegmentParameter : Segment
    {
        public ParameterJv Parameter { get; set; }
    }

    public class ParentSegment : SegmentParameter
    {
    }

    public class PositionalSegment : SegmentParameter
    {
        public int Position { get; set; }
    }
}
