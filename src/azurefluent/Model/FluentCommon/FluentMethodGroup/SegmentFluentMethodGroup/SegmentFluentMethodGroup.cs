// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using Pluralize.NET;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An implementation of ISegmentFluentMethodGroup, an instance of this type is derived from a collection segement in Arm Uri.
    /// </summary>
    public class SegmentFluentMethodGroup : ISegmentFluentMethodGroup
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        public SegmentFluentMethodGroup(SegmentFluentMethodGroups fluentMethodGroups, string localName)
        {
            this.FluentMethodGroups = fluentMethodGroups ?? throw new ArgumentNullException(nameof(fluentMethodGroups));
            //
            if (localName == null)
            {
                throw new ArgumentNullException(nameof(localName));
            }
            this.LocalNameInPascalCase = $"{localName.First().ToString().ToUpper()}{localName.Substring(1)}";
            //
            this.ParentMethodGroupNames = new List<string>();
            this.Level = -1;
            this.innerMethods = new List<MethodJvaf>();
            this.childFluentMethodGroups = new List<ISegmentFluentMethodGroup>();
            this.otherMethods = null;
        }

        public SegmentFluentMethodGroup(SegmentFluentMethodGroups fluentMethodGroups, string localName, List<string> parentMethodGroupNames)
        {
            this.FluentMethodGroups = fluentMethodGroups ?? throw new ArgumentNullException(nameof(fluentMethodGroups));
            //
            if (localName == null)
            {
                throw new ArgumentNullException(nameof(localName));
            }
            this.LocalNameInPascalCase = $"{localName.First().ToString().ToUpper()}{localName.Substring(1)}";
            this.ParentMethodGroupNames = parentMethodGroupNames ?? throw new ArgumentNullException(nameof(parentMethodGroupNames));
            //
            this.Level = this.ParentMethodGroupNames.Count(); // Level is zero indexed (level of this group/collection in ARM Uri)
            //
            this.innerMethods = new List<MethodJvaf>();
            this.childFluentMethodGroups = new List<ISegmentFluentMethodGroup>();
            this.otherMethods = null;
        }

        /// <summary>
        /// Given an ARM operation endpoint Uri, derive a "Segment Fluent Method Group" that the operation can possibly belongs to.
        /// </summary>
        /// <param name="fluentMethodGroups">the map holding all "Segment Fluent Method Group"s</param>
        /// <param name="innerMethod">inner Swagger method</param>
        /// <param name="segments">the ARM operation endpoint uri segments those appear after Provider name</param>
        /// <returns>The segment fluent method group</returns>
        public static SegmentFluentMethodGroup ResolveFluentMethodGroup(SegmentFluentMethodGroups fluentMethodGroups,
            MethodJvaf innerMethod,
            IEnumerable<Segment> segments,
            string defaultMethodGroupName)
        {
            List<string> fluentMethodGroupNamesInSegments = new List<string>();
            Pluralizer pluralizer = new Pluralizer();
            HttpMethod httpMethod = innerMethod.HttpMethod;

            segments
            .Where(segment => !(segment is PositionalSegment) && Utils.IsPlural(segment.Name, fluentMethodGroups.FluentConfig))
            .ForEach(segment =>
            {
                fluentMethodGroupNamesInSegments.Add(segment.Name);
            });
            //
            if (fluentMethodGroupNamesInSegments.Count() == 0)
            {
                // Level 0 "Fluent Method Group"
                return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                    localName: defaultMethodGroupName,
                    parentMethodGroupNames: new List<string>());
            }
            if (fluentMethodGroupNamesInSegments.Count() == 1)
            {
                // Level 0 "Fluent Method Group"
                return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                    localName: fluentMethodGroupNamesInSegments[0],
                    parentMethodGroupNames: new List<string>());
            }
            else if (httpMethod == HttpMethod.Post)
            {
                if (segments.Last() is TerminalSegment
                    && segments.Last().Name.EqualsIgnoreCase(fluentMethodGroupNamesInSegments.Last()))
                {
                    //POST /providers/Microsoft.EventHub/namespaces/{nsname}/authorizationRules/{ruleName}/listKeys
                    //
                    return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.SkipLast(1).Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(2).ToList());
                }
                else
                {
                    return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(1).ToList());
                }
            }
            else
            {
                IModelTypeJv retType = innerMethod.ReturnTypeJva.BodyClientType;
                if ((httpMethod == HttpMethod.Get || httpMethod == HttpMethod.Put) &&
                    (retType is PrimaryType || (retType as SequenceType)?.ElementType is PrimaryType))
                {
                    return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.SkipLast(1).Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(2).ToList());
                }
                else
                {
                    return new SegmentFluentMethodGroup(fluentMethodGroups: fluentMethodGroups,
                        localName: fluentMethodGroupNamesInSegments.Last(),
                        parentMethodGroupNames: fluentMethodGroupNamesInSegments.SkipLast(1).ToList());
                }
            }
        }

        #region ISegmentFluentMethodGroup contract implementation.

        public int Level { get; private set; }

        private List<MethodJvaf> innerMethods;
        public IReadOnlyList<MethodJvaf> InnerMethods
        {
            get
            {
                return this.innerMethods;
            }
        }

        public ISegmentFluentMethodGroup ParentFluentMethodGroup { get; private set; }

        private List<ISegmentFluentMethodGroup> childFluentMethodGroups;
        public IReadOnlyList<ISegmentFluentMethodGroup> ChildFluentMethodGroups
        {
            get
            {
                return childFluentMethodGroups;
            }
        }

        private NonStandardToStandardModelMappingHelper mapper;
        public NonStandardToStandardModelMappingHelper ModelMapper
        {
            get
            {
                if (this.mapper == null)
                {
                    this.mapper = new NonStandardToStandardModelMappingHelper(this);
                }
                return this.mapper;
            }
        }

        public string ManagerName
        {
            get
            {
                return this.FluentMethodGroups.ManagerName;
            }
        }
        
        public SegmentFluentMethodGroups FluentMethodGroups { get; private set; }

        private MethodGroupType? methodGroupType;
        public MethodGroupType Type
        {
            get
            {
                if (this.methodGroupType == null)
                {
                    if (!this.derivedStandardInnerModel)
                    {
                        throw new InvalidOperationException("Standard model is not derived");
                    }
                    //
                    if (this.IsGroupableTopLevel)
                    {
                        this.methodGroupType = MethodGroupType.GroupableTopLevel;
                    }
                    else if (this.IsNonGroupableTopLevel)
                    {
                        this.methodGroupType = MethodGroupType.NonGroupableTopLevel;
                    }
                    else if (this.IsNested)
                    {
                        this.methodGroupType = MethodGroupType.Nested;
                    }
                    else
                    {
                        this.methodGroupType = MethodGroupType.ActionsOrChildAccessorsOnly;
                    }
                }
                return this.methodGroupType.Value;
            }
        }

        public string JavaInterfaceName
        {
            get; set;
        }

        public string SingularJavaInterfaceName
        {
            get
            {
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

        public string LocalNameInPascalCase
        {
            get; private set;
        }

        public string LocalSingularNameInPascalCase
        {
            get
            {
                if (this.FluentMethodGroups.FluentConfig.IsKnownSingular(LocalNameInPascalCase))
                {
                    return LocalNameInPascalCase;
                }
                else
                {
                    Pluralizer pluralizer = new Pluralizer();
                    // e.g. VirtualMachines -> VirtualMachine
                    return pluralizer.Singularize(LocalNameInPascalCase);
                }
            }
        }

        private StandardModel standardFluentModel;
        public StandardModel StandardFluentModel
        {
            get
            {
                if (!this.derivedStandardInnerModel)
                {
                    throw new InvalidOperationException("Standard model is not derived");
                }
                //
                if (this.standardFluentModel == null && this.standardInnerModel != null)
                {
                    this.standardFluentModel = new StandardModel(this, this.standardInnerModel);
                }
                return this.standardFluentModel;
            }
        }

        public MethodGroupJvaf InnerMethodGroup { get; private set; }

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
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.OtherMethods.ImportsForImpl);
                imports.AddRange(this.ModelMapper.Imports);
                return imports;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.ResourceCreateDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceDeleteDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceGetDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.ResourceListingDescription.ImportsForMethodGroupInterface);
                imports.AddRange(this.OtherMethods.ImportsForInterface);
                imports.Add($"{this.package}.implementation.{this.InnerMethodGroupTypeName}");
                imports.Add("com.microsoft.azure.arm.model.HasInner");

                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                List<string> extends = new List<string>();
                extends.AddRange(this.ResourceCreateDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceDeleteDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceGetDescription.MethodGroupInterfaceExtendsFrom);
                extends.AddRange(this.ResourceListingDescription.MethodGroupInterfaceExtendsFrom);
                extends.Add($"HasInner<{this.InnerMethodGroup.MethodGroupImplType}>");

                if (extends.Count() > 0)
                {
                    return $" extends {string.Join(", ", extends)}";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private IResourceCreateDescription resourceCreateDescription;
        public IResourceCreateDescription ResourceCreateDescription
        {
            get
            {
                if (this.resourceCreateDescription == null)
                {
                    this.resourceCreateDescription = new ResourceCreateDescription(this);
                }
                return this.resourceCreateDescription;
            }
        }

        private IResourceUpdateDescription resourceUpdateDescription;
        public IResourceUpdateDescription ResourceUpdateDescription
        {
            get
            {
                if (this.resourceUpdateDescription == null)
                {
                    this.resourceUpdateDescription = new ResourceUpdateDescription(this.ResourceCreateDescription, this);
                }
                return this.resourceUpdateDescription;
            }
        }

        private IResourceGetDescription resourceGetDescription;
        public IResourceGetDescription ResourceGetDescription
        {
            get
            {
                if (this.resourceGetDescription == null)
                {
                    this.resourceGetDescription = new ResourceGetDescription(this);
                }
                return this.resourceGetDescription;
            }
        }

        private IResourceListingDescription resourceListingDescription;
        public IResourceListingDescription ResourceListingDescription
        {
            get
            {
                if (this.resourceListingDescription == null)
                {
                    this.resourceListingDescription = new ResourceListingDescription(this);
                }
                return this.resourceListingDescription;
            }
        }



        private IResourceDeleteDescription resourceDeleteDescription;
        public IResourceDeleteDescription ResourceDeleteDescription
        {
            get
            {
                if (this.resourceDeleteDescription == null)
                {
                    this.resourceDeleteDescription = new ResourceDeleteDescription(this);
                }
                return this.resourceDeleteDescription;
            }
        }

        public IReadOnlyList<GeneralizedOutput> GeneralizedOutputs
        {
            get
            {
                return GeneralizedOutput.EmptyList;
            }
        }

        public IEnumerable<string> ListGetDeleteByParentMethodDecls
        {
            get
            {
                if (this.ResourceGetDescription.SupportsGetByImmediateParent)
                {
                    yield return this.ResourceGetDescription.GetByImmediateParentMethodDecl;
                }
                if (this.ResourceListingDescription.SupportsListByImmediateParent)
                {
                    yield return this.ResourceListingDescription.ListByImmediateParentMethodDecl;
                }
                if (this.ResourceDeleteDescription.SupportsDeleteByImmediateParent)
                {
                    yield return this.ResourceDeleteDescription.DeleteByImmediateParentMethodDecl;
                }
            }
        }

        private IOtherMethods otherMethods;
        public IOtherMethods OtherMethods
        {
            get
            {
                if (this.otherMethods == null)
                {
                    this.otherMethods = new OtherMethods(this);
                }
                return this.otherMethods;
            }
        }

        #endregion


        private AncestorsStack ancestorsStack;
        public AncestorsStack AncestorsStack
        {
            get
            {
                if (this.ancestorsStack == null)
                {
                    this.ancestorsStack = new AncestorsStack(this);
                }
                return this.ancestorsStack;
            }
        }

        /// <summary>
        /// Local name of this "Segment Fluent Method Group" in camel case.
        /// </summary>
        public string LocalNameInCamelCase
        {
            get
            {
                // e.g. virtualMachines
                return this.LocalNameInPascalCase.ToCamelCase();
            }
        }

        /// <summary>
        /// A fully qualified name of this "Segment Fluent Method Group" which is created by combining 
        /// name of all ancestor seperated by _ char.
        /// </summary>
        public string FullyQualifiedName
        {
            get
            {
                string parentsStr = FullyQualifiedParentName;
                if (!string.IsNullOrEmpty(parentsStr))
                {
                    return $"{parentsStr}_{LocalNameInPascalCase}".ToLowerInvariant();
                }
                else
                {
                    return LocalNameInPascalCase.ToLowerInvariant();
                }
            }
        }

        #region Operations on ParentMethodGroupNames property.

        public IReadOnlyList<string> ParentMethodGroupNames { get; private set; }

        public string FullyQualifiedParentName
        {
            get
            {
                string parentsStr = string.Join("_", this.ParentMethodGroupNames);
                if (!string.IsNullOrEmpty(parentsStr))
                {
                    return parentsStr.ToLowerInvariant();
                }
                else
                {
                    return null;
                }
            }
        }

        #endregion

        #region Operations InnerMethodGroup property.

        public void SetInnerMethodGroup(MethodGroupJvaf innerMethodGroup)
        {
            this.InnerMethodGroup = innerMethodGroup;
        }

        #endregion

        #region Operations on InnerMethods property.

        public void AddInnerMethod(MethodJvaf innerMethod)
        {
            this.innerMethods.Add(innerMethod);
        }

        public void AddInnerMethods(IReadOnlyList<MethodJvaf> innerMethods)
        {
            this.innerMethods.AddRange(innerMethods);
        }

        #endregion

        #region Operations on ParentFluentMethodGroup proeprty.

        public void SetParentFluentMethodGroup(ISegmentFluentMethodGroup fluentMethodGroup)
        {
            this.ParentFluentMethodGroup = fluentMethodGroup;
        }

        #endregion

        #region Operations on ChildFluentMethodGroups property.

        public void AddToChildFluentMethodGroup(ISegmentFluentMethodGroup fluentMethodGroup)
        {
            this.childFluentMethodGroups.Add(fluentMethodGroup);
        }

        #endregion

        
        private bool derivedStandardInnerModel;         // flag indicating whether the standard model is already derived or not.
        private CompositeTypeJvaf standardInnerModel;   // The derived standard inner model.
        /// <summary>
        /// The "Segment Fluent Method Group" composes a bunch of methods, now given all these methods identify
        /// the "Standard Inner Model" from the standard methods.
        /// </summary>
        public void DeriveStandardInnerModelForMethodGroup()
        {
            if (this.derivedStandardInnerModel)
            {
                return;
            }

            this.derivedStandardInnerModel = true;

            // Find "ONE" fluent model that can be used across "Standard methods" 
            // 1. (GetByResourceGroup | ListByResourceGroup | ListBySubscription | Create in RG)
            // 2. (GetByImmediateParent | ListByImmediateParent | Create in Parent)
            //
            // Derive an "inner model then a fluent model" that represents the return type of standard methods 
            // in this fluent model. We want all thoses standard methods to return same fluent type though the
            // inner methods can return different inner model types.
            //
            if (ResourceGetDescription.SupportsGetByResourceGroup)
            {
                this.standardInnerModel = ResourceGetDescription.GetByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceCreateDescription.SupportsCreating)
            {
                this.standardInnerModel = ResourceCreateDescription.CreateMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByResourceGroup)
            {
                this.standardInnerModel = ResourceListingDescription.ListByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListBySubscription)
            {
                this.standardInnerModel = ResourceListingDescription.ListBySubscriptionMethod.InnerReturnType;
            }
            //
            else if (ResourceGetDescription.SupportsGetByImmediateParent)
            {
                this.standardInnerModel = ResourceGetDescription.GetByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByImmediateParent)
            {
                this.standardInnerModel = ResourceListingDescription.ListByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceUpdateDescription.SupportsUpdating)
            {
                this.standardInnerModel = ResourceUpdateDescription.UpdateMethod.InnerReturnType;
            }
        }

        /// <summary>
        /// Checks the method group and it's standard model belongs to groupable category. This will be 
        /// used to decide whether methodgroup can be extends from "GroupableResourcesImpl" and standard
        /// model can implements GroupableResource and extends GroupableResourceImpl.
        /// </summary>
        /// 
        private bool IsGroupableTopLevel
        {
            get
            {
                if (!this.derivedStandardInnerModel)
                {
                    throw new InvalidOperationException("Standard model is not derived");
                }
                //
                if (this.Level == 0)
                {
                    if (this.ResourceCreateDescription.SupportsCreating)
                    {
                        // It's guarnteed to be have a standardModel if create is supported
                        //
                        if (this.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent)
                        {
                            if (Utils.IsTrackedResource(this.standardInnerModel))
                            {
                                // FModel -> implements GroupableResourceCore extends GroupableResourceCoreImpl
                                // FGroup -> extends GroupableResourcesCoreImpl
                                //
                                return true;
                            }
                            else
                            {
                                // FModel -> implements SupportsCreating
                                // FGroup -> extends CreatableUpdate
                                //
                                // Treat as "NonGroupableTopLevel"
                                return false;
                            }
                        }
                        else
                        {
                            // can be created but not under a resource group, don't consider it as Groupable
                            // even though standard model is a resource.
                            // FModel -> implements SupportsCreating
                            // FGroup -> extends CreatableUpdate
                            //
                            // Treat as "NonGroupableTopLevel"
                            //
                            return false;
                        }
                    }
                    else if (this.ResourceUpdateDescription.SupportsUpdating)
                    {
                        // It's guarnteed to be have a standardModel if update is supported
                        //
                        if (this.ResourceUpdateDescription.UpdateType == UpdateType.WithResourceGroupAsParent)
                        {
                            if (Utils.IsTrackedResource(this.standardInnerModel))
                            {
                                // FModel -> implements GroupableResourceCore extends GroupableResourceCoreImpl
                                // FGroup -> extends GroupableResourcesCoreImpl
                                //
                                return true;
                            }
                            else
                            {
                                // FModel -> implements Appliable
                                // FGroup -> extends CreatableUpdate
                                //
                                // Treat as "NonGroupableTopLevel"
                                //
                                return false;
                            }
                        }
                        else
                        {
                            // FModel -> implements Appliable
                            // FGroup -> extends CreatableUpdate
                            //
                            // Treat as "NonGroupableTopLevel"
                            //
                            return false;
                        }
                    }
                    else
                    {
                        // Do not support creation or updation then we treat this as Groupable 
                        // only if there is a standard model and it is a Resource.
                        //
                        return Utils.IsTrackedResource(this.standardInnerModel);
                    }
                }
                return false;
            }
        }

        /// <summary>
        /// Check the method group and it's standard model belongs to non-groupable top-level category.
        /// The method group will derive from WrapperImpl<innerMethodGroup> and the standard model
        /// derive from CreatableUpdatableImpl, IndexableRefreshableWrapperImpl or WrapperImpl.
        /// </summary>
        private bool IsNonGroupableTopLevel
        {
            get
            {
                if (!this.derivedStandardInnerModel)
                {
                    throw new InvalidOperationException("Standard model is not derived");
                }
                //
                if (this.Level == 0)
                {
                    if (!this.IsGroupableTopLevel)
                    {
                        if (this.standardInnerModel != null)
                        {
                            return true;
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
                else
                {
                    return false;
                }
            }
        }

        private bool IsNested
        {
            get
            {
                if (!this.derivedStandardInnerModel)
                {
                    throw new InvalidOperationException("Standard model is not derived");
                }
                //
                if (this.Level > 0)
                {
                    if (this.standardInnerModel != null)
                    {
                        return true;
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
    }
}