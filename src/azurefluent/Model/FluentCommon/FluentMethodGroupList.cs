// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type holding all "Fluent Method Group" derived from a "Inner Method Group".
    /// </summary>
    public class FluentMethodGroupList : List<FluentMethodGroup>
    {
        private readonly MethodGroupJvaf innerMethodGroup;
        private FluentMethodGroup defaultLevel0FluentMethodGroup;

        public FluentMethodGroupList(MethodGroupJvaf innerMethodGroup)
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

        public List<FluentMethodGroup> DeferredFluentMethodGroups
        {
            get
            {
                return this.Where(group => DeferredFluentMethodGroupNamePrefix.HasPrefix(group.LocalNameInPascalCase)).ToList();
            }
        }

        public IEnumerable<FluentMethodGroup> OrphanFluentMethodGroups
        {
            get
            {
                return this.Where(group => group.Level > 0) // Level 0 will be skipped as they are the root parents
                    .Where(group => group.ParentFluentMethodGroup == null)
                    .OrderByDescending(group => group.Level);
            }
        }

        public void AddFluentMethodGroup(FluentMethodGroup fluentMethodGroup)
        {
            fluentMethodGroup.SetInnerMethodGroup(this.innerMethodGroup);
            this.Add(fluentMethodGroup);
        }

        public FluentMethodGroup FindFluentMethodGroup(string localName)
        {
            return this.FirstOrDefault(group => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase));
        }

        public FluentMethodGroup FindFluentMethodGroup(string localName, int level)
        {
            return this.FirstOrDefault(group => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase) && group.Level == level);
        }

        public int FindFluentMethodGroupIndex(string localName)
        {
            int e = this.Select((group, i) => group.LocalNameInPascalCase.Equals(localName, StringComparison.OrdinalIgnoreCase) ? i + 1 : -1)
                .FirstOrDefault(i => i != -1);
            return e == 0 ? -1 : e - 1;
        }

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

        public FluentMethodGroup FindBestMatchingLevel0FluentMethodGroupOrCreateOne(FluentMethodGroups groups)
        {
            if (this.defaultLevel0FluentMethodGroup == null)
            {
                // First look for a level 0 "fluent method group" with name same as the "Inner Method Group" name.
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
                        this.defaultLevel0FluentMethodGroup = new FluentMethodGroup(fluentMethodGroups: groups, 
                            localName: this.InnerMethodGroupName, 
                            parentMethodGroupNames: new List<string>());
                        //
                        this.AddFluentMethodGroup(this.defaultLevel0FluentMethodGroup);
                    }
                }
            }
            return this.defaultLevel0FluentMethodGroup;
        }
    }
}
