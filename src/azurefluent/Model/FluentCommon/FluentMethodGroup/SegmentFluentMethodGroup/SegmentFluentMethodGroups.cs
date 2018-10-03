// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class SegmentFluentMethodGroups : Dictionary<string, SegmentFluentMethodGroupList>
    {
        private FluentConfig fluentConfig;
        public FluentConfig FluentConfig
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

        private readonly CodeModelJvaf codeModel;

        private SegmentFluentMethodGroups(CodeModelJvaf codeModel)
        {
            this.codeModel = codeModel;
        }

        public IEnumerable<ClientFluentGroupableModelInterface> GroupableFluentModels
        {
            get; private set;
        }
        public IEnumerable<ClientFluentNonGroupableTopLevelModelInterface> NonGroupableTopLevelFluentModels { get; private set; }

        public IEnumerable<ClientFluentNestedModelInterface> NestedFluentModels
        {
            get; private set;
        }

        public IEnumerable<ClientFluentReadOnlyModelInterface> ReadonlyFluentModels
        {
            get; private set;
        }

        private string managerName;
        public string ManagerName
        {
            get
            {
                if (managerName == null)
                {
                    FluentConfig fluentConfig = FluentConfig.Create();
                    this.managerName = fluentConfig.ModuleName == null ? $"{this.codeModel.ServiceName}Manager" : $"{fluentConfig.ModuleName}Manager";
                }
                return this.managerName;
            }
        }

        private void Add(SegmentFluentMethodGroupList list)
        {
            this.Add(list.InnerMethodGroupName, list);
        }

        public static SegmentFluentMethodGroups InnerMethodGroupToFluentMethodGroups(CodeModelJvaf codeModel)
        {
            IEnumerable<MethodGroupJv> allInnerMethodGroups = codeModel.AllOperations;
            //
            SegmentFluentMethodGroups fluentMethodGroups = new SegmentFluentMethodGroups(codeModel);
            //
            foreach (MethodGroupJvaf currentInnerMethodGroup in allInnerMethodGroups)
            {
                SegmentFluentMethodGroupList fluentMethodGroupsInCurrentInnerMethodGroup = new SegmentFluentMethodGroupList(currentInnerMethodGroup);
                //
                fluentMethodGroups.Add(fluentMethodGroupsInCurrentInnerMethodGroup);
                //
                foreach (MethodJvaf innerMethod in currentInnerMethodGroup.Methods)
                {
                    if (innerMethod.Name.ToLowerInvariant().StartsWith("begin", StringComparison.OrdinalIgnoreCase))
                    {
                        // Skip LRO begin methods
                        continue;
                    }
                    else
                    {
                        ARMUri armUri = new ARMUri(innerMethod);
                        // Skip below two methods
                        //    1. uri can be empty for method such as 'listNext'
                        //    2. uri can be just 'nextLink' for method to retrieve next page
                        if (!armUri.IsNullOrEmpty() && !(armUri.Count == 1 && armUri.First().Name.EqualsIgnoreCase("nextLink")))
                        {
                            IEnumerable<Segment> segments = armUri.SegmentsAfterProvider;
                            segments = segments.Any() ? segments : armUri;
                            //
                            if (segments.Any())
                            {
                                SegmentFluentMethodGroup fluentMethodGroup = null;
                                if (segments.Count() == 1 && (segments.First() is TerminalSegment))
                                {
                                    // e.g. providers/Microsoft.Network/networkInterfaces
                                    // e.g. providers/Microsoft.Network/checkNameAvailability
                                    //
                                    string name = segments.First().Name;
                                    fluentMethodGroup = new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                                        localName: DeferredFluentMethodGroupNamePrefix.AddPrefix(name));
                                }
                                else
                                {
                                    string methodGroupDefaultName = Utils.TrimInnerSuffix(currentInnerMethodGroup.Name.ToString());
                                    fluentMethodGroup = SegmentFluentMethodGroup.ResolveFluentMethodGroup(fluentMethodGroups, innerMethod, segments, methodGroupDefaultName);
                                    fluentMethodGroup = fluentMethodGroup ?? throw new ArgumentNullException(nameof(fluentMethodGroup));
                                }
                                // Checks whether we already derived a method group with same name in the current "Inner Method Group"
                                //
                                SegmentFluentMethodGroup matchedFluentMethodGroup = fluentMethodGroupsInCurrentInnerMethodGroup.FindFluentMethodGroup(fluentMethodGroup.LocalNameInPascalCase);
                                if (matchedFluentMethodGroup != null)
                                {
                                    matchedFluentMethodGroup.AddInnerMethod(innerMethod);
                                }
                                else
                                {
                                    fluentMethodGroup.AddInnerMethod(innerMethod);
                                    fluentMethodGroupsInCurrentInnerMethodGroup.AddFluentMethodGroup(fluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            //
            fluentMethodGroups.ResolveDeferredFluentMethodGroups(codeModel);
            fluentMethodGroups.LinkFluentMethodGroups();
            fluentMethodGroups.InjectPlaceHolderFluentMethodGroups();
            fluentMethodGroups.DeriveStandardInnerModelForMethodGroups();
            fluentMethodGroups.PruneMethodGroups();
            fluentMethodGroups.Select(m => m.Value).SelectMany(fluentMethodGroupList => fluentMethodGroupList)
                .ForEach(fluentMethodGroup =>
                {
                    fluentMethodGroup.JavaInterfaceName = fluentMethodGroup.LocalNameInPascalCase;
                });
            fluentMethodGroups.EnsureUniqueJvaModelInterfaceName();
            fluentMethodGroups.SpecializeFluentModels();
            //
            return fluentMethodGroups;
        }

        public void PruneMethodGroups()
        {
            this.Select(m => m.Value).ForEach(group => group.Prune());
        }

        public string CtrToCreateModelFromExistingResource(string modelJavaClassName)
        {
           var standardModel = this.Select(kv => kv.Value)
                 .SelectMany(fluentMethodGropList => fluentMethodGropList)
                 .Where(group => group.StandardFluentModel != null) // Groupable, NonGroupableTopLevel or Nested
                 .Select(group => group.StandardFluentModel)
                 .FirstOrDefault(model => model.JavaClassName.Equals(modelJavaClassName));

            if (standardModel != null)
            {
                return standardModel.CtrInvocationForWrappingExistingInnerModel;
            }

            var roModel = this.ReadonlyFluentModels // Return models of OtherMethods those are not standard models
                .FirstOrDefault(model => model.JavaClassName.Equals(modelJavaClassName));

            if (roModel != null)
            {
                return roModel.CtrInvocationForWrappingExistingInnerModel;
            }

            throw new ArgumentException($"Unable to resolve the ctr for the fluent model type '{modelJavaClassName}' that wraps an existing inner resource");
        }

        private void InjectPlaceHolderFluentMethodGroups()
        {
            IEnumerable<SegmentFluentMethodGroup> orphanFluentMethodGroups = this.Select(kv => kv.Value)
                 .SelectMany(fluentMethodGroupList => fluentMethodGroupList.OrphanFluentMethodGroups)
                 .OrderByDescending(group => group.Level);

            if (!orphanFluentMethodGroups.Any())
            {
                return;
            }
            else
            {
                foreach (SegmentFluentMethodGroup orphanFluentMethodGroup in orphanFluentMethodGroups)
                {
                    string ancestorName = orphanFluentMethodGroup.ParentMethodGroupNames.LastOrDefault();
                    if (ancestorName != null)
                    {
                        SegmentFluentMethodGroupList fluentMethodGroupList = this[orphanFluentMethodGroup.InnerMethodGroup.Name];
                        SegmentFluentMethodGroup fosterParentFluentMethodGroup = fluentMethodGroupList.FindFluentMethodGroup(ancestorName, orphanFluentMethodGroup.Level - 1);
                        if (fosterParentFluentMethodGroup == null)
                        {
                            fosterParentFluentMethodGroup = new SegmentFluentMethodGroup(fluentMethodGroups: this,
                                localName: ancestorName,
                                parentMethodGroupNames: orphanFluentMethodGroup.ParentMethodGroupNames.SkipLast(1).ToList());
                            //
                            fluentMethodGroupList.AddFluentMethodGroup(fosterParentFluentMethodGroup);
                        }
                        orphanFluentMethodGroup.SetParentFluentMethodGroup(fosterParentFluentMethodGroup);
                        fosterParentFluentMethodGroup.AddToChildFluentMethodGroup(orphanFluentMethodGroup);
                    }
                }
                this.InjectPlaceHolderFluentMethodGroups();
            }
        }

        private void ResolveDeferredFluentMethodGroups(CodeModelJvaf codeModel)
        {
            // For each "Inner Method Group", process list of "Fluent Method Groups" belongs to it.
            //
            foreach (SegmentFluentMethodGroupList fluentMethodGroupList in this.Values)
            {
                List<SegmentFluentMethodGroup> deferredFluentMethodGroups = fluentMethodGroupList.DeferredFluentMethodGroups;
                //
                foreach (SegmentFluentMethodGroup deferredFluentMethodGroup in deferredFluentMethodGroups)
                {
                    string possibleFluentMethodGroupName = DeferredFluentMethodGroupNamePrefix.RemovePrefix(deferredFluentMethodGroup.LocalNameInPascalCase);
                    //
                    // Find a "Fluent Method Group" that can own the methods in the "Deferred Fluent Method Group".
                    //
                    SegmentFluentMethodGroup newOwnerFluentMethodGroup = fluentMethodGroupList.FindFluentMethodGroup(possibleFluentMethodGroupName);
                    if (newOwnerFluentMethodGroup == null)
                    {
                        newOwnerFluentMethodGroup = fluentMethodGroupList.FindBestMatchingLevel0FluentMethodGroupOrCreateOne(this);
                    }
                    // Migrate methods in "Defered Fluent Method Group" to new owner
                    //
                    newOwnerFluentMethodGroup.AddInnerMethods(deferredFluentMethodGroup.InnerMethods);
                    // Remove "Defered Fluent Method Group", given it's methods has new owner
                    //
                    fluentMethodGroupList.RemoveFluentMethodGroup(deferredFluentMethodGroup.LocalNameInPascalCase);
                }
            }
        }

        private void LinkFluentMethodGroups()
        {
            Dictionary<string, SegmentFluentMethodGroup> map = new Dictionary<string, SegmentFluentMethodGroup>();
            //
            this.Select(m => m.Value).SelectMany(fluentMethodGroupList => fluentMethodGroupList)
                .ForEach(fluentMethodGroup =>
                {
                    map.AddIfNotExists(fluentMethodGroup.FullyQualifiedName, fluentMethodGroup);
                });
            //
            this.Select(m => m.Value).SelectMany(fluentMethodGroupList => fluentMethodGroupList)
                .ForEach(fluentMethodGroup =>
                {
                    if (map.ContainsNonEmptyKey(fluentMethodGroup.FullyQualifiedParentName))
                    {
                        fluentMethodGroup.SetParentFluentMethodGroup(map[fluentMethodGroup.FullyQualifiedParentName]);
                        map[fluentMethodGroup.FullyQualifiedParentName].AddToChildFluentMethodGroup(fluentMethodGroup);
                    }
                });
        }

        private void DeriveStandardInnerModelForMethodGroups()
        {
            // Derive standard fluent model for all method groups.
            //
            this.Select(innerMethodGroupToFluentMethodGroups =>
            {
                List<SegmentFluentMethodGroup> fluentMethodGroups = innerMethodGroupToFluentMethodGroups.Value;
                return fluentMethodGroups;
            })
            .SelectMany(fluentMethodGroups => fluentMethodGroups)
            .ForEach(fluentMethodGroup =>
            {
                fluentMethodGroup.DeriveStandardInnerModelForMethodGroup();
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

            var standardModelsToCheckForConflict = this.Select(kv => kv.Value)
                 .SelectMany(group => group)
                 .Where(group => group.StandardFluentModel != null)
                 .Select(group => {
                     return new
                     {
                         fluentMethodGroup = group,
                         standardFluentModel = group.StandardFluentModel
                     };
                 });

            // SFM => [FluentMethodGroup] where FMG just wrapper for innerMG
            //
            Dictionary<string, List<SegmentFluentMethodGroup>> dict = new Dictionary<string, List<SegmentFluentMethodGroup>>();
            this.ResetAncestorsStacks();
            while (true)
            {
                standardModelsToCheckForConflict
                    .Select(smtc => smtc.fluentMethodGroup)
                    .ForEach(currentFmg => {
                        string modelJvaInterfaceName = currentFmg.StandardFluentModel.JavaInterfaceName;
                        if (!dict.ContainsKey(modelJvaInterfaceName))
                        {
                            dict.Add(modelJvaInterfaceName, new List<SegmentFluentMethodGroup>());
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
                    IDictionary<string, List<SegmentFluentMethodGroup>> failedToDeconflict = new Dictionary<string, List<SegmentFluentMethodGroup>>();
                    //

                    conflicts
                        .SelectMany(kv => kv.Value)
                        .ForEach(fluentMethodGroup =>
                        {
                            string modelJvaInterfaceCurrentName = fluentMethodGroup.StandardFluentModel.JavaInterfaceName;
                            string ancestorName = fluentMethodGroup.AncestorsStack.PopNextAncestorSingularName;
                            string modelJvaInterfaceNewName = $"{ancestorName}{fluentMethodGroup.StandardFluentModel.JavaInterfaceName}";
                            fluentMethodGroup.StandardFluentModel.SetJavaInterfaceName(modelJvaInterfaceNewName);
                            if (ancestorName == null)
                            {
                                // If parentMethodGeoup is null then we need to start using Model suffix to avoid infinite
                                // conflict resolution attempts, hence track FMG with 'failed to de-conflicte std model'.
                                if (!failedToDeconflict.ContainsKey(fluentMethodGroup.StandardFluentModel.JavaInterfaceName))
                                {
                                    failedToDeconflict.Add(fluentMethodGroup.StandardFluentModel.JavaInterfaceName, new List<SegmentFluentMethodGroup>());
                                }
                                failedToDeconflict[fluentMethodGroup.StandardFluentModel.JavaInterfaceName].Add(fluentMethodGroup);
                            }
                        });

                    foreach (var kv in failedToDeconflict)
                    {
                        List<SegmentFluentMethodGroup> fluentMethodGroups = kv.Value;
                        if (fluentMethodGroups.Count > 1)
                        {
                            // Skip one "FMG" so that it's std model get good name without "Model". Giving "Model" suffix to next one.
                            SegmentFluentMethodGroup secondFluentMethodGroup = fluentMethodGroups.Skip(1).First();
                            string modelJavaInterfaceName = secondFluentMethodGroup.StandardFluentModel.JavaInterfaceName;
                            secondFluentMethodGroup.StandardFluentModel.SetJavaInterfaceName(modelJavaInterfaceName + "Model");
                            // If there are more than two conflicting FMG then start using suffix "Model{1 <= i <= n}"
                            int i = 1;
                            foreach (SegmentFluentMethodGroup nextFluentMethodGroup in fluentMethodGroups.Skip(2))
                            {
                                modelJavaInterfaceName = nextFluentMethodGroup.StandardFluentModel.JavaInterfaceName;
                                nextFluentMethodGroup.StandardFluentModel.SetJavaInterfaceName(modelJavaInterfaceName + $"Model{i}");
                                i++;
                            }
                        }
                    }
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
            this.ResetAncestorsStacks();
            while (true)
            {
                standardModelsToCheckForConflict
                .Select(smtc => smtc.fluentMethodGroup)
                .ForEach(currentFmg =>
                {
                    string key = $"{currentFmg.InnerMethodGroup.Name}:{currentFmg.StandardFluentModel.JavaInterfaceName}";
                    if (!dict.ContainsKey(key))
                    {
                        dict.Add(key, new List<SegmentFluentMethodGroup>());
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
                    IDictionary<string, List<SegmentFluentMethodGroup>> failedToDeconflict = new Dictionary<string, List<SegmentFluentMethodGroup>>();

                    conflicts
                        .SelectMany(kv => kv.Value)
                        .ForEach(fmg =>
                        {
                            string modelJvaInterfaceCurrentName = fmg.StandardFluentModel.JavaInterfaceName;

                            if (!modelJvaInterfaceCurrentName.EndsWith(fmg.LocalNameInPascalCase))
                            {
                                fmg.StandardFluentModel.SetJavaInterfaceName(fmg.LocalNameInPascalCase);
                            }
                            else
                            {
                                string ancestorName = fmg.AncestorsStack.PopNextAncestorSingularName;
                                string modelJvaInterfaceNewName = $"{ancestorName}{fmg.StandardFluentModel.JavaInterfaceName}";
                                // If parentMethodGeoup is null then we need to start using Model suffix to avoid infinite
                                // conflict resolution attempts, hence track FMG with 'failed to de-conflicte std model'.
                                if (!failedToDeconflict.ContainsKey(fmg.StandardFluentModel.JavaInterfaceName))
                                {
                                    failedToDeconflict.Add(fmg.StandardFluentModel.JavaInterfaceName, new List<SegmentFluentMethodGroup>());
                                }
                                failedToDeconflict[fmg.StandardFluentModel.JavaInterfaceName].Add(fmg);
                            }
                        });

                    foreach (var kv in failedToDeconflict)
                    {
                        List<SegmentFluentMethodGroup> fluentMethodGroups = kv.Value;
                        if (fluentMethodGroups.Count > 1)
                        {
                            // Skip one "FMG" so that it's std model get good name without "Model". Giving "Model" suffix to next one.
                            SegmentFluentMethodGroup secondFluentMethodGroup = fluentMethodGroups.Skip(1).First();
                            string modelJavaInterfaceName = secondFluentMethodGroup.StandardFluentModel.JavaInterfaceName;
                            secondFluentMethodGroup.StandardFluentModel.SetJavaInterfaceName(modelJavaInterfaceName + "Model");
                            // If there are more than two conflicting FMG then start using suffix "Model{1 <= i <= n}"
                            int i = 1;
                            foreach (SegmentFluentMethodGroup nextFluentMethodGroup in fluentMethodGroups.Skip(2))
                            {
                                modelJavaInterfaceName = nextFluentMethodGroup.StandardFluentModel.JavaInterfaceName;
                                nextFluentMethodGroup.StandardFluentModel.SetJavaInterfaceName(modelJavaInterfaceName + $"Model{i}");
                                i++;
                            }
                        }
                    }
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
            HashSet<string> seenModels = new HashSet<string>();

            // Promotes the general fluent models to top-level-groupable vs top-level-non-groupable nested child vs other.
            //

            // Specialize the GROUPABLEMODEL
            //
            this.GroupableFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fluentMethodGropList => fluentMethodGropList)
                 .Where(group => group.Type == MethodGroupType.GroupableTopLevel)
                 .Select(group => new ClientFluentGroupableModelInterface(group.StandardFluentModel))
                 .Distinct(CreatableUpdatableModel.EqualityComparer<ClientFluentGroupableModelInterface>());

            this.GroupableFluentModels.ForEach(m => seenModels.Add(m.JavaInterfaceName));

            // Specialize the NESTEDFLUENTMODEL
            //
            this.NestedFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fluentMethodGropList => fluentMethodGropList)
                 .Where(group => group.Type == MethodGroupType.Nested)
                 .Select(group => new ClientFluentNestedModelInterface(group.StandardFluentModel))
                 .Distinct(CreatableUpdatableModel.EqualityComparer<ClientFluentNestedModelInterface>());

            this.NestedFluentModels.ForEach(m => seenModels.Add(m.JavaInterfaceName));

            // Specialize the TOP-LEVEL NONGROUPABLEMODEL
            //
            this.NonGroupableTopLevelFluentModels = this.Select(kv => kv.Value)
                 .SelectMany(fluentMethodGropList => fluentMethodGropList)
                 .Where(group => group.Type == MethodGroupType.NonGroupableTopLevel)
                 .Select(group => new ClientFluentNonGroupableTopLevelModelInterface(group.StandardFluentModel))
                 .Distinct(CreatableUpdatableModel.EqualityComparer<ClientFluentNonGroupableTopLevelModelInterface>());

            NonGroupableTopLevelFluentModels.ForEach(m => seenModels.Add(m.JavaInterfaceName));

            // Specialize wrappable READONLYMODEL
            //
            this.ReadonlyFluentModels = this.Select(kv => kv.Value)
                .SelectMany(fluentMethodGropList => fluentMethodGropList)
                .SelectMany(group => group.OtherMethods.OtherFluentModels)
                .OfType<WrappableFluentModel>()
                .Distinct(WrappableFluentModel.EqualityComparer())
                .Where(fluentModel => !seenModels.Contains(fluentModel.JavaInterfaceName))
                .Select(fluentModel => new ClientFluentReadOnlyModelInterface(fluentModel, this, this.ManagerName));
        }

        private void ResetAncestorsStacks()
        {
            this.Select(m => m.Value).SelectMany(fluentMethodGroupList => fluentMethodGroupList)
                            .ForEach(group => group.AncestorsStack.Reset());
        }
    }
}