// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a list of "Segment Fluent Method Group" derived from a "Inner Method Group".
    /// </summary>
    public class SegmentFluentMethodGroupList : List<SegmentFluentMethodGroup>
    {
        /// <summary>
        /// AutoRest core provided "Operation Group" containing a list of inner methods (each of which has an ARM Uri)
        /// whose ARM Uri is parsed to produce "Segment Fluent Method Group" in this list.
        /// </summary>
        private readonly MethodGroupJvaf innerMethodGroup;

        /// <summary>
        /// Creates SegmentFluentMethodGroupList to hold "Segment Fluent Method Group"s derived from methods in the inner method group.
        /// </summary>
        /// <param name="innerMethodGroup">AutoRest core provided "Operation Group"</param>
        public SegmentFluentMethodGroupList(MethodGroupJvaf innerMethodGroup)
        {
            this.innerMethodGroup = innerMethodGroup;
        }

        public string InnerMethodGroupName
        {
            get
            {
                return this.innerMethodGroup.Name.ToString();
            }
        }

        public List<SegmentFluentMethodGroup> DeferredFluentMethodGroups
        {
            get
            {
                return this.Where(group => DeferredFluentMethodGroupNamePrefix.HasPrefix(group.LocalNameInPascalCase)).ToList();
            }
        }

        public IEnumerable<SegmentFluentMethodGroup> OrphanFluentMethodGroups
        {
            get
            {
                return this.Where(group => group.Level > 0) // Level 0 will be skipped as they are the root parents
                    .Where(group => group.ParentFluentMethodGroup == null)
                    .OrderByDescending(group => group.Level);
            }
        }

        /// <summary>
        /// Add a "Segment Fluent Method Group" to the list of "Segment Fluent Method Group"s.
        /// </summary>
        /// <param name="fluentMethodGroup">The segment fluent method group</param>
        public void AddFluentMethodGroup(SegmentFluentMethodGroup fluentMethodGroup)
        {
            fluentMethodGroup.SetInnerMethodGroup(this.innerMethodGroup);
            this.Add(fluentMethodGroup);
        }

        /// <summary>
        /// Given local name of "Segment Fluent Method Group" return it.
        /// </summary>
        /// <param name="localName">the local name (this is a name of the Arm uri segment targetting this "Segment Fluent Method Group")</param>
        /// <returns></returns>
        public SegmentFluentMethodGroup FindFluentMethodGroup(string localName)
        {
            return this.FirstOrDefault(group => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase));
        }

        /// <summary>
        /// Given local name of "Segment Fluent Method Group" and it's level, return "Segment Fluent Method Group"
        /// if it exists, null otherwise.
        /// </summary>
        /// <param name="localName">the local name (this is a name of the Arm uri segment targetting this "Segment Fluent Method Group")</param>
        /// <param name="level">the level of local name in Arm Uri</param>
        /// <returns></returns>
        public SegmentFluentMethodGroup FindFluentMethodGroup(string localName, int level)
        {
            return this.FirstOrDefault(group => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase) && group.Level == level);
        }

        /// <summary>
        /// Given local name of "Segment Fluent Method Group", return it's index in this collection.
        /// </summary>
        /// <param name="localName">the local name (this is a name of the Arm uri segment targetting this "Segment Fluent Method Group")</param>
        /// <returns>if item exits then return it's index, else return -1</returns>
        public int FindFluentMethodGroupIndex(string localName)
        {
            int e = this.Select((group, i) => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase) ? i + 1 : -1)
                .FirstOrDefault(i => i != -1);
            return e == 0 ? -1 : e - 1;
        }

        /// <summary>
        /// Given local name of "Segment Fluent Method Group", remove it from this collection.
        /// </summary>
        /// <param name="localName">the local name (this is a name of the Arm uri segment targetting this "Segment Fluent Method Group")</param>
        /// <returns>true if item exits and removed, false it item not found</returns>
        public bool RemoveFluentMethodGroup(string localName)
        {
            int index = this.FindFluentMethodGroupIndex(localName);
            if (index == -1)
            {
                return false;
            }
            else
            {
                this.RemoveAt(index);
                return true;
            }
        }

        private SegmentFluentMethodGroup defaultLevel0FluentMethodGroup;
        /// <summary>
        /// Find or create a level 0 "Segment Fluent Method Group".
        /// </summary>
        /// <param name="groups">a dictionary of all "Segment Fluent Method Group List" indexed by "Inner Client" name</param>
        /// <returns>level 0 "Segment Fluent Method Group"</returns>
        public SegmentFluentMethodGroup FindBestMatchingLevel0FluentMethodGroupOrCreateOne(SegmentFluentMethodGroups groups)
        {
            if (this.defaultLevel0FluentMethodGroup == null)
            {
                // First look for a level 0 "Segment Fluent Method Group" with name same as the "Inner Method Group" name.
                //
                this.defaultLevel0FluentMethodGroup = this.Where(group => group.Level == 0)
                    .Where(group => this.InnerMethodGroupName.StartsWith(group.LocalNameInPascalCase, StringComparison.OrdinalIgnoreCase))
                    .FirstOrDefault();
                //
                if (this.defaultLevel0FluentMethodGroup == null)
                {
                    // If no such group then pick the first one in level 0 sorted by the name
                    //
                    this.defaultLevel0FluentMethodGroup = this.OrderBy(group => group.LocalNameInPascalCase).Where(group => group.Level == 0).FirstOrDefault();
                    // if there no level 0 then create one
                    //
                    if (this.defaultLevel0FluentMethodGroup == null)
                    {
                        this.defaultLevel0FluentMethodGroup = new SegmentFluentMethodGroup(fluentMethodGroups: groups, 
                            localName: this.InnerMethodGroupName, 
                            parentMethodGroupNames: new List<string>());
                        //
                        this.AddFluentMethodGroup(this.defaultLevel0FluentMethodGroup);
                    }
                }
            }
            return this.defaultLevel0FluentMethodGroup;
        }

        /// <summary>
        /// The "Fluent Method Group" derived from this list of "Segment Fluent Method Group".
        /// </summary>
        public IFluentMethodGroup PrunedMethodGroup { get; private set; }

        /// <summary>
        /// Prune the "Segment Fluent Method Group" in this list to produce a "Fluent Method Group".
        /// </summary>
        /// <returns>Fluent Method Group</returns>
        public IFluentMethodGroup Prune()
        {
            var localGroups = this;
            if (localGroups.Count == 0)
            {
                return null;
            }
            else
            {
                IFluentMethodGroup prunedGroup = ProxyFluentMethodGroup.Create(localGroups.First());
                foreach(ISegmentFluentMethodGroup currentGroup in localGroups.Skip(1))
                {
                    if (prunedGroup.StandardFluentModel == null)
                    {
                        if (currentGroup.StandardFluentModel == null)
                        {
                            // If both doesn't have standard model then create a proxy by generalizing both
                            //
                            prunedGroup = ProxyFluentMethodGroup.Create(prunedGroup, currentGroup, true);
                        }
                        else
                        {
                            if (prunedGroup.GeneralizedOutputs.Select(go => go.DefineFunc).Any(d => d.IsDefineSupported))
                            {
                                prunedGroup = ProxyFluentMethodGroup.Create(prunedGroup, currentGroup, true);
                            }
                            else
                            {
                                // If the current one have a standard model then make it the subject and generalize the pruned one
                                //
                                prunedGroup = ProxyFluentMethodGroup.Create(currentGroup, prunedGroup, false);
                            }
                        }
                    }
                    else
                    {
                        if (prunedGroup.LocalNameInPascalCase.Equals(this.InnerMethodGroupName, StringComparison.OrdinalIgnoreCase) 
                            && currentGroup.LocalNameInPascalCase.Equals(this.InnerMethodGroupName, StringComparison.OrdinalIgnoreCase))
                        {
                            // If both has standard model and both local names matches with the inner method group name then 
                            // create a proxy by generalizing both.
                            //
                            prunedGroup = ProxyFluentMethodGroup.Create(prunedGroup, currentGroup, true);
                        }
                        else if (!prunedGroup.LocalNameInPascalCase.Equals(this.InnerMethodGroupName, StringComparison.OrdinalIgnoreCase) 
                            && !currentGroup.LocalNameInPascalCase.Equals(this.InnerMethodGroupName, StringComparison.OrdinalIgnoreCase))
                        {
                            // If both has standard model and both local names doesn't matches with the inner method group name then 
                            // create a proxy by generalizing both.
                            //
                            prunedGroup = ProxyFluentMethodGroup.Create(prunedGroup, currentGroup, true);
                        }
                        else if (prunedGroup.LocalNameInPascalCase.Equals(this.InnerMethodGroupName, StringComparison.OrdinalIgnoreCase))
                        {
                            // If both has standard models but only the local name of pruned matches with the inner method 
                            // group name then create a proxy with pruned as subject and current one generalized.
                            //
                            prunedGroup = ProxyFluentMethodGroup.Create(prunedGroup, currentGroup, false);
                        }
                        else
                        {
                            // If both has standard models but only the local name of current matches with the inner method 
                            // group name then create a proxy with current as subject and pruned one generalized.
                            //
                            prunedGroup = ProxyFluentMethodGroup.Create(currentGroup, prunedGroup, false);
                        }
                    }
                }
                this.PrunedMethodGroup = prunedGroup;
                return prunedGroup;
            }
        }
    }
}
