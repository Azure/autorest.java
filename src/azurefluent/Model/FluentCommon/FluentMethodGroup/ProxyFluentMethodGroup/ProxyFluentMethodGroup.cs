﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ProxyFluentMethodGroup : IFluentMethodGroup
    {
        private IFluentMethodGroup subjectFluentMethodGroup;

        private ProxyFluentMethodGroup() { }

        /// <summary>
        /// Creates a proxy fluent method group for the given fluent method group.
        /// </summary>
        /// <param name="subjectFluentMethodGroup">the subject for which proxy needs to be created</param>
        /// <returns>proxy fluent method group</returns>
        public static ProxyFluentMethodGroup Create(IFluentMethodGroup subjectFluentMethodGroup)
        {
            ProxyFluentMethodGroup proxy = Init(subjectFluentMethodGroup);
            //
            proxy.subjectFluentMethodGroup = subjectFluentMethodGroup;
            //
            foreach (var output in subjectFluentMethodGroup.GeneralizedOutputs.Where(gop => !gop.IsEmpty))
            {
                proxy.generalizedOutputs.Add(output);
            }
            return proxy;
        }

        /// <summary>
        /// Creates a proxy Fluent Method Group.
        /// </summary>
        /// <param name="subjectFluentMethodGroup">the first group that become either subject or gets generalized depending on the generalizeSubject parameter</param>
        /// <param name="secondaryFluentMethodGroup">the second group which is always gets generalized</param>
        /// <param name="generalizeSubject">decides whether the subject fluent method group also needs to be generalized</param>
        /// <returns>proxy fluent method group</returns>
        public static ProxyFluentMethodGroup Create(IFluentMethodGroup subjectFluentMethodGroup, IFluentMethodGroup secondaryFluentMethodGroup, bool generalizeSubject)
        {
            ProxyFluentMethodGroup proxy = Init(subjectFluentMethodGroup);
            //
            if (generalizeSubject)
            {
                proxy.subjectFluentMethodGroup = null;  // No subject, means use nullObjects
                // -- Generalize the subject Fluent Method Group --
                GeneralizedOutput subjectGeneralized = GeneralizedOutput.Generalize(subjectFluentMethodGroup);
                //
                if (!subjectGeneralized.IsEmpty)
                {
                    proxy.generalizedOutputs.Add(subjectGeneralized);
                }
                foreach (var output in subjectGeneralized.GeneralizedOutputs.Where(gop => !gop.IsEmpty))
                {
                    proxy.generalizedOutputs.Add(output);
                }
            }
            else
            {
                proxy.subjectFluentMethodGroup = subjectFluentMethodGroup;
                //
                foreach (var output in subjectFluentMethodGroup.GeneralizedOutputs.Where(gop => !gop.IsEmpty))
                {
                    proxy.generalizedOutputs.Add(output);
                }
            }
            //
            // -- Generalize the secondary  Fluent Method Group  --
            GeneralizedOutput secondaryGeneralized = GeneralizedOutput.Generalize(secondaryFluentMethodGroup);
            //
            if (!secondaryGeneralized.IsEmpty)
            {
                proxy.generalizedOutputs.Add(secondaryGeneralized);
            }
            foreach (var output in secondaryGeneralized.GeneralizedOutputs.Where(gop => !gop.IsEmpty))
            {
                proxy.generalizedOutputs.Add(output);
            }
            return proxy;
        }

        private static ProxyFluentMethodGroup Init(IFluentMethodGroup fluentMethodGroup)
        {
            return new ProxyFluentMethodGroup
            {
                // The "FluentMethodGroups" and "ManagerName" are golbals, same across all fluent method groups.
                FluentMethodGroups = fluentMethodGroup.FluentMethodGroups,
                ManagerName = fluentMethodGroup.ManagerName,
                // The "InnerMethodGroup" is same across all fluent method groups belongs to the same inner method group
                // an instance of proxy always works with fluent method groups in the same inner method group.
                InnerMethodGroup = fluentMethodGroup.InnerMethodGroup,
                //
                generalizedOutputs = new List<GeneralizedOutput>(),
            };
        }

        public SegmentFluentMethodGroups FluentMethodGroups { get; private set; }

        public string ManagerName { get; private set; }

        public MethodGroupJvaf InnerMethodGroup { get; private set; }

        public StandardModel StandardFluentModel => this.subjectFluentMethodGroup?.StandardFluentModel;

        public string LocalNameInPascalCase => this.subjectFluentMethodGroup?.LocalNameInPascalCase;

        public string JavaInterfaceName => Utils.TrimInnerSuffix(this.InnerMethodGroupTypeName);

        public string InnerMethodGroupTypeName => InnerMethodGroup.MethodGroupImplType;

        public string InnerMethodGroupAccessorName => InnerMethodGroup.Name.ToCamelCase();

        public HashSet<string> ImportsForImpl => this.subjectFluentMethodGroup?.ImportsForImpl ?? Utils.EmptyStringSet;

        public HashSet<string> ImportsForInterface => this.subjectFluentMethodGroup?.ImportsForInterface ?? Utils.EmptyStringSet;

        public IOtherMethods OtherMethods => this.subjectFluentMethodGroup?.OtherMethods ?? OtherMethodsNull.Instance;

        public IResourceCreateDescription ResourceCreateDescription => this.subjectFluentMethodGroup?.ResourceCreateDescription ?? ResourceCreateNullDescription.Instance;

        public IResourceUpdateDescription ResourceUpdateDescription => this.subjectFluentMethodGroup?.ResourceUpdateDescription ?? ResourceUpdateNullDefinition.Instance;

        public IResourceGetDescription ResourceGetDescription => this.subjectFluentMethodGroup?.ResourceGetDescription ?? ResourceGetNullDescription.Instance;

        public IResourceListingDescription ResourceListingDescription => this.subjectFluentMethodGroup?.ResourceListingDescription ?? ResourceListingNullDescription.Instance;

        public IResourceDeleteDescription ResourceDeleteDescription => this.subjectFluentMethodGroup?.ResourceDeleteDescription ?? ResourceDeleteNullDescription.Instance;

        private List<GeneralizedOutput> generalizedOutputs;
        public IReadOnlyList<GeneralizedOutput> GeneralizedOutputs => this.generalizedOutputs;

        public MethodGroupType Type => this.subjectFluentMethodGroup?.Type ?? MethodGroupType.ActionsOnly;

        public string LocalSingularNameInPascalCase => this.subjectFluentMethodGroup.LocalSingularNameInPascalCase;

        public string ExtendsFrom => this.subjectFluentMethodGroup?.ExtendsFrom ?? string.Empty;

        public IEnumerable<string> ListGetDeleteByParentMethodDecls => this.subjectFluentMethodGroup?.ListGetDeleteByParentMethodDecls ?? Utils.EmptyStringList;

        public string SingularJavaInterfaceName => subjectFluentMethodGroup.SingularJavaInterfaceName;
    }
}
