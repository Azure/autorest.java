// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing a standard Arm URI.
    /// e.g. /subscriptions/{subscriptionId}/resourceGroups/{resourceGroupName}/providers/Microsoft.Compute/availabilitySets/{availabilitySetName}
    /// </summary>
    public class ARMUri : List<Segment>
    {
        /// <summary>
        /// The Uri of the method (api) that this instance represents.
        /// </summary>
        private readonly MethodJvaf method;
        /// <summary>
        /// The uri as raw string.
        /// </summary>
        private readonly string rawUrl;

        /// <summary>
        /// Creates an ARMUri instance representing uri to an api call.
        /// </summary>
        /// <param name="method">the api method</param>
        public ARMUri(MethodJvaf method)
        {
            this.method = method;
            this.rawUrl = this.FluentConfig.MappedUrl(method.Url);
            this.Init();
        }

        /// <summary>
        /// Gets index of a segment in the uri with the given name.
        /// </summary>
        /// <param name="segmentName">the segment name</param>
        /// <returns>the segment index if such segment exists, -1 if segment does not exists</returns>
        public int IndexOfSegment(string segmentName)
        {
            int e = this.Select((segment, i) => segment.Name.Equals(segmentName) ? i + 1 : -1)
                .FirstOrDefault(i => i != -1);
            return e == 0 ? -1 : e - 1;
        }


        /// <summary>
        /// Gets the segments in the ARM Uri that appear after the provider namespace.
        /// </summary>
        public IEnumerable<Segment> SegmentsAfterProvider
        {
            get
            {
                int index = this.IndexOfSegment("providers");
                if (index == -1)
                {
                    return new List<Segment>();
                }
                else if (this.Skip(index + 1).Any() && (this.Skip(index + 1).First() is ReferenceSegment))
                {
                    // Skip the "/providers/" [ReferenceSegment] and provider "e.g. /Microsoft.Compute/" [ReferenceSegment]
                    return this.Skip(index + 2);
                }
                else
                {
                    return new List<Segment>();
                }
            }
        }

        /// <summary>
        /// Checks this Uri matches with the provided Uri.
        /// </summary>
        /// <param name="other">the other uri</param>
        /// <returns>true if matches, false otherwise</returns>
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


        private Dictionary<int, Segment> localPathParameterIndexToSegments;
        /// <summary>
        /// Get a map with value as path parameters of the method this ARMUri identifies.
        /// The key associated with each path parameter value will be position of that
        /// parameter in the method.
        /// </summary>
        public IReadOnlyDictionary<int, Segment> LocalPathParameterSegments
        {
            get
            {
                if (localPathParameterIndexToSegments == null)
                {
                    // Dict with key as position of each parameter in the method and value as ARM Uri segments corrosponding to the parameter.
                    //
                    this.localPathParameterIndexToSegments = new Dictionary<int, Segment>();
                    //
                    var segments = this.OfType<ParameterSegment>()                                               // Retrieve the segments representing parameter (ParentSegment or PositionalSegment)
                        .Where(segment => segment.Parameter.Location == ParameterLocation.Path)                  // Segments are always from path so this is not really needed
                        .Where(segment => !segment.Parameter.IsClientProperty && !segment.Parameter.IsConstant); // Retrieve only local parameters
                                                                                                                 //
                    foreach (var segment in segments)
                    {
                        // Find the position of the method parameter corrosponding to the segment.
                        //
                        int e = this.method.LocalParameters.Where(p => !p.IsConstant && p.IsRequired)
                            .Select((p, i) => p.Name.EqualsIgnoreCase(segment.Parameter.Name) ? i + 1 : -1)
                            .FirstOrDefault(i => i > 0);
                        int paramIndex = e == 0 ? -1 : e - 1;
                        //
                        if (paramIndex == -1)
                        {
                            throw new InvalidOperationException($"Could not find the method parameter for the segment {segment.Name}.");
                        }
                        else
                        {
                            this.localPathParameterIndexToSegments.Add(paramIndex, segment);
                        }
                    }
                }
                return this.localPathParameterIndexToSegments;
            }
        }

        /// <summary>
        /// Initialize this instance of ArmUri by parsing raw uri parts and categorize them into different segments.
        /// </summary>
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
                this.Add(new PositionalSegment(name: name, position: pos)
                {
                    Parameter = this.method.Parameters
                        .OfType<ParameterJv>()
                        .First(p => p.Location == Core.Model.ParameterLocation.Path && p.SerializedName.EqualsIgnoreCase(name))
                });
            }
            else
            {
                this.Add(new ParentSegment(name: itr.Current, position: pos));
            }
            //
            // Intermediate segments
            //
            while (itr.MoveNext())
            {
                pos++;
                //
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
                        this.Add(new PositionalSegment(name: name, position: pos)
                        {
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
                            this.Add(new ReferenceSegment(name: parentSegment.Name, 
                                position: parentSegment.Position, 
                                refName: itr.Current));
                        }
                    }
                    this.Add(new ParentSegment(name: itr.Current, position: pos));
                }
            }
            // Last segment special handling
            //
            if (this.Last() is ParentSegment)
            {
                ParentSegment parentSegment = (ParentSegment)this.Last();
                if (parentSegment.Parameter == null)
                {
                    this.RemoveAt(this.Count - 1);
                    this.Add(new TerminalSegment(name: parentSegment.Name, position: parentSegment.Position));
                }
            }
        }

        /// <summary>
        /// The configuration passed as autorest argument with key '--fconfig'.
        /// </summary>
        private FluentConfig fluentConfig;
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
}
