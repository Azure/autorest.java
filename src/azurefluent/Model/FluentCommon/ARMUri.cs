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
        private FluentConfig fluentConfig;

        public ARMUri(MethodJvaf method)
        {
            this.method = method;
            this.rawUrl = this.FluentConfig.MappedUrl(method.Url);
            this.Init();
        }

        public int IndexOfSegment(string segmentName)
        {
            int e = this.Select((segment, i) => segment.Name.Equals(segmentName) ? i + 1 : -1)
                .FirstOrDefault(i => i != -1);
            return e == 0 ? -1 : e - 1;
        }

        public IEnumerable<Segment> SegmentsAfterProvider
        {
            get
            {
                int index = this.IndexOfSegment("providers");
                if (index == -1)
                {
                    return new List<Segment>();
                }
                else
                {
                    // Skip the "/providers/" and provider "e.g. /Microsoft.Compute/"
                    return this.Skip(index + 2);
                }
            }
        }

        // TODO:anuchan - ArmUri type should implement IEqualityComparer
        // no# lines can be reduced, but keeping like this for readability
        //
        public bool IsSame(ARMUri other)
        {
            var thisUriItr = this.GetEnumerator();
            var otherUriItr = other.GetEnumerator();
            //
            while (thisUriItr.MoveNext() && otherUriItr.MoveNext())
            {
                if (thisUriItr.Current is PositionalSegment)
                {
                    if (otherUriItr.Current is PositionalSegment)
                    {
                        continue;
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (thisUriItr.Current is ParentSegment)
                {
                    if (otherUriItr.Current is ParentSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (thisUriItr.Current is ReferenceSegment)
                {
                    if (otherUriItr.Current is ReferenceSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
                else if (thisUriItr.Current is TerminalSegment)
                {
                    if (otherUriItr.Current is TerminalSegment)
                    {
                        if (thisUriItr.Current.Name.EqualsIgnoreCase(otherUriItr.Current.Name))
                        {
                            continue;
                        }
                        else
                        {
                            return false;
                        }
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            return thisUriItr.MoveNext() == false
                && otherUriItr.MoveNext() == false;
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
                    if (this.Last() is ParentSegment && ((ParentSegment) this.Last()).Parameter == null)
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

        private FluentConfig FluentConfig
        {
            get
            {
                if (this.fluentConfig == null)
                {
                    this.fluentConfig = FluentConfig.Create();
                }
                return this.fluentConfig;
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
