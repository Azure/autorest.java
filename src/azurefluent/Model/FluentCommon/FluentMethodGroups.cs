// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using AutoRest.Java.azurefluent.Model;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using AutoRest.Java.Azure.Fluent.Model;
using System.IO;
using System.Text;
using Pluralize.NET;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentMethodGroups : Dictionary<string, List<FluentMethodGroup>>
    {
        private static string DeferFMGResolution = "<Defered_FluentMethodGroup_Resolution>:";

        public CodeModelJvaf CodeModel { get; private set; }

        private FluentMethodGroups(CodeModelJvaf codeModel)
        {
            this.CodeModel = codeModel;
        }

        public IEnumerable<GroupableFluentModelInterface> GroupableFluentModels
        {
            get; private set;
        }
        public IEnumerable<NonGroupableTopLevelFluentModelInterface> NonGroupableTopLevelFluentModels { get; private set; }

        public IEnumerable<NestedFluentModelInterface> NestedFluentModels
        {
            get; private set;
        }
        public IEnumerable<ReadOnlyFluentModelInterface> ReadonlyFluentModels
        {
            get; private set;
        }

        public Dictionary<string, FluentModel> InnerToFluentModelMap
        {
            get; private set;
        }

        public Dictionary<string, ActionOrChildAccessorOnlyMethodGroupImpl> ActionOrChildAccessorOnlyMethodGroups
        {
            get; private set;
        }

        public string ManagerTypeName
        {
            get
            {
                return $"{this.CodeModel.ServiceName}Manager";
            }
        }

        public static FluentMethodGroups InnerMethodGroupToFluentMethodGroups(CodeModelJvaf codeModel)
        {
            FluentMethodGroups innerMethodGroupToFluentMethodGroups = new FluentMethodGroups(codeModel);

            foreach (MethodGroupJvaf innerMethodGroup in codeModel.AllOperations)
            {
                List<FluentMethodGroup> fluentMGroupsInCurrentInnerMGroup = new List<FluentMethodGroup>();
                innerMethodGroupToFluentMethodGroups.Add(innerMethodGroup.Name, fluentMGroupsInCurrentInnerMGroup);

                foreach (MethodJvaf innerMethod in innerMethodGroup.Methods)
                {
                    if (innerMethod.Name.ToLowerInvariant().StartsWith("begin", StringComparison.OrdinalIgnoreCase))
                    {
                        // Skip LRO begin methods
                        continue;
                    }
                    else
                    {
                        ARMUri armUri = new ARMUri(innerMethod);
                        if (!armUri.IsNullOrEmpty())   // uri can be empty for method such as 'listNext' so ensure uri exists before proceeding
                        {
                            int providersSegmentIndex = armUri.IndexOfSegment("providers");
                            if (providersSegmentIndex != -1)
                            {
                                IEnumerable<Segment> uriAfterProvider = armUri.Skip(providersSegmentIndex + 2); // Skip the "/providers/" and provider "e.g. /Microsoft.Compute/"
                                if (uriAfterProvider.Any())
                                {
                                    FluentMethodGroup fluentMGroup = null;
                                    if (uriAfterProvider.Count() == 1)
                                    {
                                        // e.g. providers/Microsoft.Network/networkInterfaces
                                        // e.g. providers/Microsoft.Network/checkNameAvailability
                                        //
                                        string possibleFMGName = uriAfterProvider.First().Name;
                                        fluentMGroup = new FluentMethodGroup(innerMethodGroupToFluentMethodGroups)
                                        {
                                            LocalNameInPascalCase = $"{DeferFMGResolution}{possibleFMGName}"
                                        };
                                    }
                                    else
                                    {
                                        fluentMGroup = FluentMethodGroup.ResolveFluentMethodGroup(innerMethodGroupToFluentMethodGroups, uriAfterProvider, innerMethod.HttpMethod);
                                    }
                                    if (fluentMGroup != null)
                                    {
                                        Debug.Assert(fluentMGroup != null);
                                        // Checks whether we already derived a method group with same name in the current "Operation group" (inner method group)
                                        //
                                        FluentMethodGroup matchedFluentMethodGroup = fluentMGroupsInCurrentInnerMGroup.FirstOrDefault(fmg => fmg.LocalNameInPascalCase.EqualsIgnoreCase(fluentMGroup.LocalNameInPascalCase));

                                        if (matchedFluentMethodGroup != null)
                                        {
                                            matchedFluentMethodGroup.InnerMethods.Add(innerMethod);
                                        }
                                        else
                                        {
                                            fluentMGroup.InnerMethods.Add(innerMethod);
                                            fluentMGroup.InnerMethodGroup = innerMethodGroup;
                                            fluentMGroupsInCurrentInnerMGroup.Add(fluentMGroup);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            innerMethodGroupToFluentMethodGroups.ResolveDeferedFluentMethodGroups(codeModel);
            innerMethodGroupToFluentMethodGroups.LinkFluentMethodGroups();
            innerMethodGroupToFluentMethodGroups.InjectPlaceHolderFluentMethodGroups();
            innerMethodGroupToFluentMethodGroups.EnsureUniqueJavaInterfaceNameForFluentMethodGroup();
            innerMethodGroupToFluentMethodGroups.DeriveStandardFluentModelForMethodGroups();
            innerMethodGroupToFluentMethodGroups.EnsureUniqueJvaModelInterfaceName();
            innerMethodGroupToFluentMethodGroups.SpecializeFluentModels();

            return innerMethodGroupToFluentMethodGroups;
        }

        private void InjectPlaceHolderFluentMethodGroups()
        {
           IEnumerable<FluentMethodGroup> orphanFluentMethodGroups = this.Select(kv => kv.Value)
                .SelectMany(fmg => fmg)
                .Where(fmg => fmg.Level > 0)    // Level 0 don't have parent (they will hang under manager)
                .Where(fmg => fmg.ParentFluentMethodGroup == null)
                .OrderByDescending(fmg => fmg.Level);

            if (!orphanFluentMethodGroups.Any())
            {
                return;
            }
            else
            {
                foreach (FluentMethodGroup orphanFluentMethodGroup in orphanFluentMethodGroups)
                {
                    string ancestorName = orphanFluentMethodGroup.ParentMethodGroupNames.LastOrDefault();
                    if (ancestorName != null)
                    {
                        string innerMethodGroupName = orphanFluentMethodGroup.InnerMethodGroup.Name;
                        List<FluentMethodGroup> fluentMethodGroups = this[innerMethodGroupName];
                        FluentMethodGroup fosterParentFluentMethodGroup = fluentMethodGroups
                            .FirstOrDefault(fmg => fmg.LocalNameInPascalCase.EqualsIgnoreCase(ancestorName) && fmg.Level == orphanFluentMethodGroup.Level - 1);
                        if (fosterParentFluentMethodGroup == null)
                        {
                            fosterParentFluentMethodGroup = new FluentMethodGroup(this)
                            {
                                LocalNameInPascalCase = ancestorName,
                                Level = orphanFluentMethodGroup.Level - 1,
                                InnerMethodGroup = orphanFluentMethodGroup.InnerMethodGroup,
                                ParentMethodGroupNames = orphanFluentMethodGroup.ParentMethodGroupNames.SkipLast(1).ToList()
                            };
                            fluentMethodGroups.Add(fosterParentFluentMethodGroup);
                        }
                        orphanFluentMethodGroup.ParentFluentMethodGroup = fosterParentFluentMethodGroup;
                        fosterParentFluentMethodGroup.ChildFluentMethodGroups.Add(orphanFluentMethodGroup);
                    }
                }
                this.InjectPlaceHolderFluentMethodGroups();
            }
        }

        private void ResolveDeferedFluentMethodGroups(CodeModelJvaf codeModel)
        {
            foreach (KeyValuePair<String, List<FluentMethodGroup>> kvPair in this) 
            {
                string currentInnerFluentMethodGroupName = kvPair.Key;
                List<FluentMethodGroup> fluentMethodGroupsInCurrentInnerMethodGroup = kvPair.Value;
                //
                // Retrieve the list of method group in the current inner FMG defered from resolution.
                //
                List<FluentMethodGroup> deferredFluentMethodGroups = fluentMethodGroupsInCurrentInnerMethodGroup
                    .Where(fmg => fmg.LocalNameInPascalCase.StartsWith(DeferFMGResolution))
                    .ToList();

                FluentMethodGroup level0FluentMethodGroup = null;
                //
                foreach (FluentMethodGroup deferredFluentMethodGroup in deferredFluentMethodGroups)
                {
                    // deferredFluentMethodGroup.LocalName -> "<Defered_FluentMethodGroup_Resolution>:[possibleFluentMethodGroupName]"
                    //
                    string possibleFluentMethodGroupName = deferredFluentMethodGroup.LocalNameInPascalCase.Substring(DeferFMGResolution.Length);
                    //
                    // Check there is a real FMG with this possible FMG name
                    //
                    var realFluentMethodGroup = fluentMethodGroupsInCurrentInnerMethodGroup
                        .FirstOrDefault(fmg => fmg.LocalNameInPascalCase.EqualsIgnoreCase(possibleFluentMethodGroupName));
                    //
                    if (realFluentMethodGroup != null)
                    {
                        // Each defered FMG contains one method, migrate it to realFluentMethodGroup
                        //
                        realFluentMethodGroup
                            .InnerMethods
                            .AddRange(deferredFluentMethodGroup.InnerMethods);
                        // Given we have taken care of deferred FluentMethodGroup methods, remove it from being tracked.
                        //
                        int index = fluentMethodGroupsInCurrentInnerMethodGroup.Select((fmg, i) => new { fmg, i })
                            .Where(fmgi => fmgi.fmg.LocalNameInPascalCase.Equals(deferredFluentMethodGroup.LocalNameInPascalCase))
                            .Select(fmgi => fmgi.i)
                            .First();
                        fluentMethodGroupsInCurrentInnerMethodGroup.RemoveAt(index);
                    }
                    else
                    {
                        // If there is no FMG with "possibleFluentMethodGroupName" then
                        if (level0FluentMethodGroup == null)
                        {
                            // Pick a level 0 fluent method group in the current inner method group
                            //
                            level0FluentMethodGroup = fluentMethodGroupsInCurrentInnerMethodGroup
                                .Where(fmg => fmg.Level == 0)
                                .Where(fmg =>
                                {
                                    string innerMethodGroupName = fmg.InnerMethodGroup.Name.ToString();
                                    return innerMethodGroupName.StartsWith(fmg.LocalNameInPascalCase, StringComparison.OrdinalIgnoreCase);
                                })
                                .FirstOrDefault();

                            if (level0FluentMethodGroup == null)
                            {
                                level0FluentMethodGroup = fluentMethodGroupsInCurrentInnerMethodGroup
                                    .OrderBy(fmg => fmg.LocalNameInPascalCase)
                                    .Where(fmg => fmg.Level == 0)
                                    .FirstOrDefault();
                            }

                            if (level0FluentMethodGroup == null)
                            {
                                level0FluentMethodGroup = new FluentMethodGroup(this)
                                {
                                    LocalNameInPascalCase = kvPair.Key,
                                    InnerMethodGroup = (MethodGroupJvaf)codeModel
                                                            .AllOperations
                                                            .First(mg => mg.Name.EqualsIgnoreCase(kvPair.Key))
                                };
                                fluentMethodGroupsInCurrentInnerMethodGroup.Add(level0FluentMethodGroup);
                            }
                        }
                        // Each defered FMG contains one method, migrate it to level 0 FluentMethodGroup
                        //
                        level0FluentMethodGroup
                            .InnerMethods
                            .AddRange(deferredFluentMethodGroup.InnerMethods);
                        // Given we have taken care of deferred FluentMethodGroup methods, remove it from being tracked.
                        //
                        int index = fluentMethodGroupsInCurrentInnerMethodGroup.Select((fmg, i) => new { fmg, i })
                            .Where(fmgi => fmgi.fmg.LocalNameInPascalCase.Equals(deferredFluentMethodGroup.LocalNameInPascalCase))
                            .Select(fmgi => fmgi.i)
                            .First();
                        fluentMethodGroupsInCurrentInnerMethodGroup.RemoveAt(index);
                    }
                }
            }
        }

        private void LinkFluentMethodGroups()
        {
            Dictionary<String, FluentMethodGroup> fmgNameToFmgMap = new Dictionary<string, FluentMethodGroup>();
            foreach (KeyValuePair<String, List<FluentMethodGroup>> kvPair in this)
            {
                List<FluentMethodGroup> fluentMethodGroups = kvPair.Value;
                foreach (FluentMethodGroup fluentMethodGroup in fluentMethodGroups)
                {
                    if (!String.IsNullOrEmpty(fluentMethodGroup.FullyQualifiedName) 
                                && !fmgNameToFmgMap.ContainsKey(fluentMethodGroup.FullyQualifiedName)) 
                    {
                        fmgNameToFmgMap.Add(fluentMethodGroup.FullyQualifiedName, fluentMethodGroup);
                    }
                }
            }
            foreach (KeyValuePair<String, List<FluentMethodGroup>> kvPair in this)
            {
                List<FluentMethodGroup> fluentMethodGroups = kvPair.Value;
                foreach (FluentMethodGroup fluentMethodGroup in fluentMethodGroups)
                {
                    if (!String.IsNullOrEmpty(fluentMethodGroup.FullyQualifiedParentName) 
                                && fmgNameToFmgMap.ContainsKey(fluentMethodGroup.FullyQualifiedParentName)) 
                    {
                        fluentMethodGroup.ParentFluentMethodGroup = fmgNameToFmgMap[fluentMethodGroup.FullyQualifiedParentName];
                        fmgNameToFmgMap[fluentMethodGroup.FullyQualifiedParentName].ChildFluentMethodGroups.Add(fluentMethodGroup);
                    }
                }
            }
        }

        private void EnsureUniqueJavaInterfaceNameForFluentMethodGroup() 
        {
            this.ResetAncestorsStacks();
            //
            // Start with FMG interface name same as it's local name
            //
            this.Select(innerMGroupToFluentMethodGroups => 
            {
                List<FluentMethodGroup> fluentMethodGroups = innerMGroupToFluentMethodGroups.Value;
                return fluentMethodGroups;
            })
            .SelectMany(fluentMethodGroups => fluentMethodGroups)
            .ForEach(fluentMethodGroup =>
            {
                fluentMethodGroup.JavaInterfaceName = fluentMethodGroup.LocalNameInPascalCase;
            });

            Dictionary<String, List<FluentMethodGroup>> interfaceNameToFluentMethodGroups = new Dictionary<string, List<FluentMethodGroup>>();
            //
            while (true)
            {
                this.Select(innerMGroupToFluentMethodGroups =>
                {
                    List<FluentMethodGroup> fluentMethodGroups = innerMGroupToFluentMethodGroups.Value;
                    return fluentMethodGroups;
                })
                .SelectMany(fluentMethodGroups => fluentMethodGroups)
                .ForEach(fluentMethodGroup => 
                {
                    string fmgJvaInterfaceName = fluentMethodGroup.JavaInterfaceName;
                    if (!interfaceNameToFluentMethodGroups.ContainsKey(fmgJvaInterfaceName))
                    {
                        interfaceNameToFluentMethodGroups.Add(fmgJvaInterfaceName, new List<FluentMethodGroup>());
                    }
                    interfaceNameToFluentMethodGroups[fmgJvaInterfaceName].Add(fluentMethodGroup);
                });

                var conflicts = interfaceNameToFluentMethodGroups.Where(nameToFMGroups => 
                {
                    List<FluentMethodGroup> fluentMethodGroups = nameToFMGroups.Value;
                    // true if there is more than one FMGs with same java interface name.
                    //
                    return fluentMethodGroups.Count() > 1;
                });

                if (conflicts.Any())
                {
                    conflicts
                        .SelectMany(nameToFMGs =>
                        {
                               List<FluentMethodGroup> fluentMethodGroups = nameToFMGs.Value;
                               return fluentMethodGroups;
                        })
                        .ForEach(fluentMethodGroup =>
                        {
                            if (fluentMethodGroup.Level > 0)
                            {
                                string parentMethodGroupName = fluentMethodGroup.PopAncestorFluentMethodGroupLocalSingularNameInPascalCase;
                                fluentMethodGroup.JavaInterfaceName = $"{parentMethodGroupName}{fluentMethodGroup.JavaInterfaceName}";
                            }
                            else
                            {
                                string prefixName = fluentMethodGroup.PopAncestorFluentMethodGroupLocalSingularNameInPascalCase;
                                fluentMethodGroup.JavaInterfaceName = $"{prefixName}{fluentMethodGroup.JavaInterfaceName}";
                            }
                        });
                }
                else
                {
                    break;
                }
                interfaceNameToFluentMethodGroups.Clear();
            }
        }

        private void DeriveStandardFluentModelForMethodGroups()
        {
            // Derive standard fluent model for all method groups.
            //
            this.Select(innerMethodGroupToFluentMethodGroups => 
            {
                List<FluentMethodGroup> fluentMethodGroups = innerMethodGroupToFluentMethodGroups.Value;
                return fluentMethodGroups;
            })
            .SelectMany(fluentMethodGroups => fluentMethodGroups)
            .ForEach(fluentMethodGroup =>
            {
                fluentMethodGroup.DeriveStandrdFluentModelForMethodGroup();
            });
        }

        private void EnsureUniqueJvaModelInterfaceName()
        {
            // -- Multiple fluent method group each with different inner method group
            //=======================================================================


            // Each FluentMethodGroup work with only the InnerMethodGroup it was derived from.
            // "FluentMethodGroup : HasInner<InnerMethodGroup>
            // If there two FluentMethodGroup wrapping different InnerMethodGroups
            //
            // 1. FluentMethodGroup1 : HasInner<InnerMethodGroup1>
            // 2. FluentMethodGroup2 : HasInner<InnerMethodGroup2>
            //
            // and if these two FMG has the same StandardFluentModel name then we need abandon 
            // that SFM name and derive two different new StandardFluentModel names, one for each FMG.
            // 
            // Let's say SFM represents a child resource with different parent then when creating this child resource
            // the def flow need to take different parent & SFM needs to have accessor for the parent which needs
            // to be named explcitly.Hence we need different SFM here.
            //
            this.ResetAncestorsStacks();

            var standardModelsToCheckForConflict = this.Select(kv => kv.Value)
                 .SelectMany(fmg => fmg)
                 .Where(fmg => fmg.StandardFluentModel != null)
                 .Select(fmg => {
                     return new
                     {
                         fluentMethodGroup = fmg,
                         standardFluentModel = fmg.StandardFluentModel
                     };
                 });

            // SFM => [FluentMethodGroup] where FMG just wrapper for innerMG
            //
            Dictionary<string, List<FluentMethodGroup>> dict = new Dictionary<string, List<FluentMethodGroup>>();

            while (true)
            {
                standardModelsToCheckForConflict
                    .Select(smtc => smtc.fluentMethodGroup)
                    .ForEach(currentFmg => {
                        string modelJvaInterfaceName = currentFmg.StandardFluentModel.JavaInterfaceName;
                        if (!dict.ContainsKey(modelJvaInterfaceName))
                        {
                            dict.Add(modelJvaInterfaceName, new List<FluentMethodGroup>());
                        }

                        string currentMgInnerName = currentFmg.InnerMethodGroup.Name;
                        bool exists = dict[modelJvaInterfaceName].Any(fmg =>
                        {
                            string mgInnerName = fmg.InnerMethodGroup.Name;
                            return mgInnerName.EqualsIgnoreCase(currentMgInnerName);
                        });
                        if (!exists)
                        {
                            dict[modelJvaInterfaceName].Add(currentFmg);
                        }
                    });

                // Note: a specific StandardFluentModel wraps a single inner model (one to one mapping)

                // If there are multiple different innerMG for specific StandardFluentModel then disambiguate it.
                // By disambiguate it means there will be multiple StandardFluentModel diff names wrapping the 
                // same inner model
                // 
                var conflicts = dict.Where(kv => kv.Value.Count() > 1);
                if (conflicts.Any())
                {
                    conflicts
                        .SelectMany(kv => kv.Value)
                        .ForEach(fmg =>
                        {
                            string modelJvaInterfaceCurrentName = fmg.StandardFluentModel.JavaInterfaceName;
                            string parentFMGLocalSingularName = fmg.PopAncestorFluentMethodGroupLocalSingularNameInPascalCase;
                            string modelJvaInterfaceNewName = $"{parentFMGLocalSingularName}{fmg.StandardFluentModel.JavaInterfaceName}";
                            fmg.StandardFluentModel.SetJavaInterfaceName(modelJvaInterfaceNewName);
                        });
                }
                else
                {
                    break;
                }
                dict.Clear();
            }


            // -- Multiple fluent method group sharing the same inner method group
            //=======================================================================

            // disambiguation is required only if the model is creatable, updatable.
            //

            // SFM.Name_InnerMethodGroup.Name => [FMG]
            //
            dict.Clear();

            while (true)
            {
                standardModelsToCheckForConflict
                .Select(smtc => smtc.fluentMethodGroup)
                .ForEach(currentFmg =>
                {
                    string key = $"{currentFmg.InnerMethodGroup.Name}:{currentFmg.StandardFluentModel.JavaInterfaceName}";
                    if (!dict.ContainsKey(key))
                    {
                        dict.Add(key, new List<FluentMethodGroup>());
                    }

                    string currentMgInnerName = currentFmg.InnerMethodGroup.Name;
                    bool exists = dict[key].Any(fmg => fmg.JavaInterfaceName.EqualsIgnoreCase(currentFmg.JavaInterfaceName));
                    if (!exists)
                    {
                        dict[key].Add(currentFmg);
                    }
                });

                var conflicts = dict.Where(kv => kv.Value.Count() > 1)
                                    .Where(kv => kv.Value.Any(v => v.ResourceCreateDescription.SupportsCreating || v.ResourceUpdateDescription.SupportsUpdating));

                if (conflicts.Any())
                {
                    conflicts
                        .SelectMany(kv => kv.Value)
                        .ForEach(fmg =>
                        {
                            string modelJvaInterfaceCurrentName = fmg.StandardFluentModel.JavaInterfaceName;
                            string parentFMGLocalSingularName = fmg.PopAncestorFluentMethodGroupLocalSingularNameInPascalCase;
                            string modelJvaInterfaceNewName = $"{parentFMGLocalSingularName}{fmg.StandardFluentModel.JavaInterfaceName}";
                            fmg.StandardFluentModel.SetJavaInterfaceName(modelJvaInterfaceNewName);
                        });
                }
                else
                {
                    break;
                }
                dict.Clear();
            }
        }

        private void SpecializeFluentModels()
        {
            HashSet<string> topLevelAndNestedModelNames = new HashSet<string>();

            // Promotes the general fluent models to top-level-groupable vs top-level-non-groupable nested child vs other.
            //

            // Specialize the GROUPABLEMODEL
            //
            this.GroupableFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fmg => fmg)
                 .Where(fmg => fmg.StandardFluentModel != null)
                 .Where(fmg => fmg.IsGroupableTopLevel)
                 .Select(fmg => new GroupableFluentModelInterface(fmg.StandardFluentModel, fmg))
                 .Distinct(GroupableFluentModelInterface.EqualityComparer());

            this.GroupableFluentModels.ForEach(m => topLevelAndNestedModelNames.Add(m.JavaInterfaceName));

            // Specialize the NESTEDFLUENTMODEL
            //
            this.NestedFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fmg => fmg)
                 .Where(fmg => fmg.StandardFluentModel != null)
                 .Where(fmg => fmg.IsNested)
                 .Select(fmg => new NestedFluentModelInterface(fmg.StandardFluentModel, fmg))
                 .Distinct(NestedFluentModelInterface.EqualityComparer());

            this.NestedFluentModels.ForEach(m => topLevelAndNestedModelNames.Add(m.JavaInterfaceName));

            // Specialize the TOP-LEVEL NONGROUPABLEMODEL
            //
            this.NonGroupableTopLevelFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fmg => fmg)
                 .Where(fmg => fmg.StandardFluentModel != null)
                 .Where(fmg => fmg.IsNonGroupableTopLevel)
                 .Select(fmg => new NonGroupableTopLevelFluentModelInterface(fmg.StandardFluentModel, fmg))
                 .Distinct(NonGroupableTopLevelFluentModelInterface.EqualityComparer());

            NonGroupableTopLevelFluentModels.ForEach(m => topLevelAndNestedModelNames.Add(m.JavaInterfaceName));

            // Specialize the READONLYMODEL
            //
            this.ReadonlyFluentModels = this.Select(kv => kv.Value)
                .SelectMany(fmg => fmg)
                .SelectMany(fmg => fmg.OtherMethods.OtherFluentModels)
                .Where(m => !(m is PrimtiveFluentModel))
                .Distinct(FluentModel.EqualityComparer())
                .Where(fluentModel => !topLevelAndNestedModelNames.Contains(fluentModel.JavaInterfaceName))
                .Select(fluentModel => new ReadOnlyFluentModelInterface(fluentModel, this.ManagerTypeName));

            // Not groupable or nested method group
            //
            this.ActionOrChildAccessorOnlyMethodGroups = new Dictionary<string, ActionOrChildAccessorOnlyMethodGroupImpl>();
            this.Select(kv => kv.Value)
                 .SelectMany(fmg => fmg)
                 .Where(fmg => fmg.StandardFluentModel == null)
                 .ForEach(fmg =>
                 {
                     if (!ActionOrChildAccessorOnlyMethodGroups.ContainsKey(fmg.JavaInterfaceName))
                     {
                         ActionOrChildAccessorOnlyMethodGroups.Add(fmg.JavaInterfaceName, new ActionOrChildAccessorOnlyMethodGroupImpl(fmg));
                     }
                 });
        }

        private void ResetAncestorsStacks()
        {
            this.Select(innerMGroupToFluentMethodGroups =>
            {
                List<FluentMethodGroup> fluentMethodGroups = innerMGroupToFluentMethodGroups.Value;
                return fluentMethodGroups;
            })
            .SelectMany(fluentMethodGroups => fluentMethodGroups)
            .ForEach(fluentMethodGroup => fluentMethodGroup.ResetAncestorStack());
        }
    }
}