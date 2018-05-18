// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using Pluralize.NET;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class ProxyFluentMethodGroup : IFluentMethodGroup
    {
        private readonly IFluentMethodGroup subjectFluentMethodGroup;

        /// <summary>
        /// Creates ProxyFluentMethodGroup for a primary fluent method groups.
        /// </summary>
        /// <param name="primaryFluentMethodGroup">the primary fluent method group that proxy redirect calls to</param>
        public ProxyFluentMethodGroup(IFluentMethodGroup primaryFluentMethodGroup)
        {
            this.subjectFluentMethodGroup = primaryFluentMethodGroup;
            this.FluentMethodGroups = primaryFluentMethodGroup.FluentMethodGroups;
            this.ManagerName = primaryFluentMethodGroup.ManagerName;
            this.InnerMethodGroup = primaryFluentMethodGroup.InnerMethodGroup;
            //
            this.generalizedOutputs = new List<GeneralizedOutput>();
            this.generalizedOutputs.AddRange(this.subjectFluentMethodGroup.GeneralizedOutputs);
            //
            this.innerMethods = new List<MethodJvaf>();
            this.innerMethods.AddRange(this.subjectFluentMethodGroup.InnerMethods);
            //
            this.childFluentMethodGroups = new List<IFluentMethodGroup>();
            this.childFluentMethodGroups.AddRange(this.subjectFluentMethodGroup.ChildFluentMethodGroups);
        }

        /// <summary>
        /// Creates ProxyFluentMethodGroup for a primary and secondary fluent method groups.
        /// </summary>
        /// <param name="primaryFluentMethodGroup">the primary fluent method group that proxy redirect calls to</param>
        /// <param name="secondaryFluentMethodGroup">the secondary fluent method group whose methods gets generalized, proxy redirect calls to these generalized methods</param>
        public ProxyFluentMethodGroup(IFluentMethodGroup primaryFluentMethodGroup, IFluentMethodGroup secondaryFluentMethodGroup) : this(primaryFluentMethodGroup)
        {
            this.subjectFluentMethodGroup = primaryFluentMethodGroup;
            this.FluentMethodGroups = primaryFluentMethodGroup.FluentMethodGroups;
            this.ManagerName = primaryFluentMethodGroup.ManagerName;
            this.InnerMethodGroup = primaryFluentMethodGroup.InnerMethodGroup;
            //
            GeneralizedOutput generalizedOutput = GeneralizedOutput.Generalize(secondaryFluentMethodGroup);
            //
            this.generalizedOutputs.Add(generalizedOutput);
            this.generalizedOutputs.AddRange(generalizedOutput.GeneralizedOutputs); // Flatting it for easy processing later
            //
            this.innerMethods.AddRange(secondaryFluentMethodGroup.InnerMethods);
            //
            this.childFluentMethodGroups.AddRange(secondaryFluentMethodGroup.ChildFluentMethodGroups);
        }

        public ProxyFluentMethodGroup(IFluentMethodGroup fluentMethodGroup1, IFluentMethodGroup fluentMethodGroup2, bool generalizeBoth)
        {
            this.subjectFluentMethodGroup = null;
            this.FluentMethodGroups = fluentMethodGroup1.FluentMethodGroups;
            this.ManagerName = fluentMethodGroup1.ManagerName;
            this.InnerMethodGroup = fluentMethodGroup1.InnerMethodGroup;
            //
            GeneralizedOutput generalizedOutput1 = GeneralizedOutput.Generalize(fluentMethodGroup1);
            GeneralizedOutput generalizedOutput2 = GeneralizedOutput.Generalize(fluentMethodGroup2);
            //
            this.generalizedOutputs.Add(generalizedOutput1);
            this.generalizedOutputs.AddRange(generalizedOutput1.GeneralizedOutputs); // Flatting it for easy processing later
            this.generalizedOutputs.Add(generalizedOutput2);
            this.generalizedOutputs.AddRange(generalizedOutput2.GeneralizedOutputs); // Flatting it for easy processing later
            //
            this.innerMethods.AddRange(fluentMethodGroup1.InnerMethods);
            this.innerMethods.AddRange(fluentMethodGroup2.InnerMethods);
            //
            this.childFluentMethodGroups.AddRange(fluentMethodGroup1.ChildFluentMethodGroups);
            this.childFluentMethodGroups.AddRange(fluentMethodGroup2.ChildFluentMethodGroups);
        }
        public FluentMethodGroups FluentMethodGroups { get; }

        public StandardModel StandardFluentModel
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return null;
                }
                else
                {
                    return this.subjectFluentMethodGroup.StandardFluentModel;
                }
            }
        }

        public string LocalNameInPascalCase
        {
            get
            {
                if (this.subjectFluentMethodGroup.LocalNameInPascalCase == null)
                {
                    return null;
                }
                else
                {
                    return this.subjectFluentMethodGroup.LocalNameInPascalCase;
                }
            }
        }

        public string JavaInterfaceName
        {
            get
            {
                return Utils.TrimInnerSuffix(this.InnerMethodGroupTypeName);
            }
        }

        public string ManagerName { get; }

        public MethodGroupJvaf InnerMethodGroup { get; }

        public string InnerMethodGroupTypeName
        {
            get
            {
                return InnerMethodGroup.MethodGroupImplType;
            }
        }

        public string InnerMethodGroupAccessorName
        {
            get
            {
                return InnerMethodGroup.Name.ToCamelCase();
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return Utils.EmptyStringSet;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ImportsForImpl;
                }
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return Utils.EmptyStringSet;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ImportsForInterface;
                }
            }
        }

        public IOtherMethods OtherMethods
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return OtherMethodsNull.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.OtherMethods;
                }
            }
        }

        public IResourceCreateDescription ResourceCreateDescription
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return ResourceCreateNullDescription.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ResourceCreateDescription;
                }
            }
        }

        public IResourceUpdateDescription ResourceUpdateDescription
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return ResourceUpdateNullDefinition.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ResourceUpdateDescription;
                }
            }
        }

        public IResourceGetDescription ResourceGetDescription
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return ResourceGetNullDescription.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ResourceGetDescription;
                }
            }
        }

        public IResourceListingDescription ResourceListingDescription
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return ResourceListingNullDescription.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ResourceListingDescription;
                }
            }
        }

        public IResourceDeleteDescription ResourceDeleteDescription
        {
            get
            {
                if (this.subjectFluentMethodGroup == null)
                {
                    return ResourceDeleteNullDescription.Instance;
                }
                else
                {
                    return this.subjectFluentMethodGroup.ResourceDeleteDescription;
                }
            }
        }

        private List<GeneralizedOutput> generalizedOutputs;
        public IReadOnlyList<GeneralizedOutput> GeneralizedOutputs
        {
            get
            {
                return this.generalizedOutputs;
            }
        }

        public int Level
        {
            get
            {
                return this.subjectFluentMethodGroup.Level;
            }
        }

        public string LocalSingularNameInPascalCase
        {
            get
            {
                return this.subjectFluentMethodGroup.LocalSingularNameInPascalCase;
            }
        }

        private List<IFluentMethodGroup> childFluentMethodGroups;
        public IReadOnlyList<IFluentMethodGroup> ChildFluentMethodGroups
        {
            get
            {
                // ChildFluentMethodGroups implementation exists only to fully conform to IFluentMethodGroup
                // contract. This is not used in ProxyFluentMethodGroup context. This overhead
                // needs to be removed by splitting IFluentMethodGroup into two interfaces.
                //
                return childFluentMethodGroups
                    .Where(group => !group.InnerMethodGroupTypeName.Equals(this.InnerMethodGroupTypeName, StringComparison.OrdinalIgnoreCase))
                    .ToList();
                // An instance of proxy can proxy over two or more Fluent Method Groups (recursively). At the end
                // a proxy represents all fluent method groups belongs to the same inner method group. That is
                // siblings (from inner method group point of view) fluent method groups in different levels are
                // represented by one proxy, hence a Fluent Method Group is treated as child Fluent Method Group 
                // only if it's a child with different Inner Fluent Method Group.
            }
        }

        private List<MethodJvaf> innerMethods;
        public IReadOnlyList<MethodJvaf> InnerMethods
        {
            get
            {
                // InnerMethod implementation exists only to fully conform to IFluentMethodGroup
                // contract. This is not at-all used in ProxyFluentMethodGroup context. This overhead
                // needs to be removed by splitting IFluentMethodGroup into two interfaces.
                //
                return this.innerMethods;
            }
        }


        public IFluentMethodGroup ParentFluentMethodGroup
        {
            get
            {
                // ParentFluentMethodGroup implementation exists only to fully conform to IFluentMethodGroup
                // contract. This is not at-all used in ProxyFluentMethodGroup context. This overhead
                // needs to be removed by splitting IFluentMethodGroup into two interfaces.
                //
                return this.subjectFluentMethodGroup.ParentFluentMethodGroup;
            }
        }

        public string SingularJavaInterfaceName
        {
            get
            {
                // SingularJavaInterfaceName implementation exists only to fully conform to IFluentMethodGroup
                // contract. This is not at-all used in ProxyFluentMethodGroup context. This overhead
                // needs to be removed by splitting IFluentMethodGroup into two interfaces.
                //
                if (this.FluentMethodGroups.FluentConfig.IsKnownSingular(JavaInterfaceName))
                {
                    return JavaInterfaceName;
                }
                else
                {
                    Pluralizer pluralizer = new Pluralizer();
                    return pluralizer.Singularize(JavaInterfaceName);
                }
            }
        }

        public string AccessorMethodName
        {
            // AccessorMethodName implementation exists only to fully conform to IFluentMethodGroup
            // contract. This is not at-all used in ProxyFluentMethodGroup context. This overhead
            // needs to be removed by splitting IFluentMethodGroup into two interfaces.
            //
            get
            {
                return JavaInterfaceName.ToPascalCase();
            }
            set
            {
                // NOP
            }
        }

        public NonStandardToStanardModelMappingHelper ModelMapper
        {
            // ModelMapper implementation exists only to fully conform to IFluentMethodGroup
            // contract. This is not at-all used in ProxyFluentMethodGroup context. This overhead
            // needs to be removed by splitting IFluentMethodGroup into two interfaces.
            //
            get
            {
                return this.subjectFluentMethodGroup.ModelMapper;
            }
        }
    }
}
