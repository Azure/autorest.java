// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.azurefluent.Model;
using Pluralize.NET;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentMethodGroup
    {
        private readonly string package = Settings.Instance.Namespace.ToLower();

        private String localName;
        private ResourceCreateDescription resourceCreateDescription;
        private ResourceUpdateDescription resourceUpdateDescription;
        private ResourceListingDescription resourceListingDescription;
        private ResourceGetDescription resourceGetDescription;
        private ResourceDeleteDescription resourceDeleteDescription;
        private OtherMethods otherMethods;
        private FluentModel standardFluentModel;
        private Dictionary<string, CompositeTypeJvaf> innersRequireWrapping;

        private Dictionary<string, FluentModel> fluentModels;
        private bool derivedFluentModels;

        public FluentMethodGroup(FluentMethodGroups fluentMethodGroups)
        {
            this.FluentMethodGroups = fluentMethodGroups;
            this.Level = -1;
            this.ParentMethodGroupNames = new List<String>();
            this.InnerMethods = new List<MethodJvaf>();
            this.ChildFluentMethodGroups = new List<FluentMethodGroup>();
            this.otherMethods = null;
        }

        public FluentMethodGroups FluentMethodGroups { get; private set; }

        /// <summary>
        /// The name of the manager type.
        /// </summary>
        public string ManagerTypeName
        {
            get
            {
                return this.FluentMethodGroups.ManagerTypeName;
            }
        }

        /// <summary>
        /// The level of this fluent method group in the url.
        /// </summary>
        public int Level { get; set; }

        public String JavaInterfaceName
        {
            get; set;
        }

        public string LocalNameInPascalCase
        {
            get
            {
                return this.localName;
            }
            set
            {
                // e.g. VirtualMachines
                this.localName = $"{value.First().ToString().ToUpper()}{value.Substring(1)}";
            }
        }

        public string LocalNameInCamelCase
        {
            get
            {
                // e.g. virtualMachines
                return this.localName.ToCamelCase();
            }
        }

        public String LocalSingularNameInPascalCase
        {
            get
            {
                Pluralizer pluralizer = new Pluralizer();
                // e.g. VirtualMachine
                return pluralizer.Singularize(LocalNameInPascalCase);
            }
        }

        public String FullyQualifiedName
        {
            get
            {
                String parentsStr = FullyQualifiedParentName;
                if (!String.IsNullOrEmpty(parentsStr))
                {
                    return $"{parentsStr}_{LocalNameInPascalCase}".ToLowerInvariant();
                }
                else
                {
                    return LocalNameInPascalCase.ToLowerInvariant();
                }
            }
        }

        public List<String> ParentMethodGroupNames { get; set; }

        public String FullyQualifiedParentName
        {
            get
            {
                String parentsStr = String.Join("_", ParentMethodGroupNames);
                if (!String.IsNullOrEmpty(parentsStr))
                {
                    return parentsStr.ToLowerInvariant();
                }
                else
                {
                    return null;
                }
            }
        }

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
                    return $" extends {String.Join(", ", extends)}";
                }
                else
                {
                    return String.Empty;
                }
            }
        }

        public HashSet<string> Imports
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
                imports.Add("com.microsoft.azure.management.resources.fluentcore.model.HasInner");
                return imports;
            }
        }

        public OtherMethods OtherMethods
        {
            get
            {
                if (otherMethods != null)
                {
                    return this.otherMethods;
                }
                else
                {
                    HashSet<string> knownMethodNames = new HashSet<string>();
                    if (ResourceCreateDescription.SupportsCreating)
                    {
                        knownMethodNames.Add(ResourceCreateDescription.CreateMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceUpdateDescription.SupportsUpdating)
                    {
                        knownMethodNames.Add(ResourceUpdateDescription.UpdateMethod.Name.ToLowerInvariant());
                        //
                        FluentMethod updateMethod = ResourceUpdateDescription.UpdateMethod;
                        if (updateMethod.InnerMethod.HttpMethod == HttpMethod.Put)
                        {
                            // If PUT based update is supported then skip any PATCH based update method
                            // being treated as "Other methods".
                            //
                            var patchUpdateMethod = this.InnerMethods
                                .Where(m => m.HttpMethod == HttpMethod.Patch)
                                .Where(m => m.Url.EqualsIgnoreCase(updateMethod.InnerMethod.Url))
                                .FirstOrDefault();
                            if (patchUpdateMethod != null)
                            {
                                knownMethodNames.Add(patchUpdateMethod.Name.ToLowerInvariant());
                            }
                        }
                    }

                    if (ResourceListingDescription.SupportsListByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceListingDescription.SupportsListByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListByResourceGroupMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceListingDescription.SupportsListBySubscription)
                    {
                        knownMethodNames.Add(ResourceListingDescription.ListBySubscriptionMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceGetDescription.SupportsGetByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceGetDescription.GetByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceGetDescription.SupportsGetByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceGetDescription.GetByResourceGroupMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceDeleteDescription.SupportsDeleteByImmediateParent)
                    {
                        knownMethodNames.Add(ResourceDeleteDescription.DeleteByImmediateParentMethod.Name.ToLowerInvariant());
                    }

                    if (ResourceDeleteDescription.SupportsDeleteByResourceGroup)
                    {
                        knownMethodNames.Add(ResourceDeleteDescription.DeleteByResourceGroupMethod.Name.ToLowerInvariant());
                    }
                    this.otherMethods = new OtherMethods(this, knownMethodNames);
                    return this.otherMethods;
                }
            }
        }

        public Dictionary<string, FluentModel> FluentModels
        {
            get
            {
                if (this.fluentModels == null)
                {
                    this.fluentModels = new Dictionary<string, FluentModel>();
                }
                return this.fluentModels;
            }
        }

        public MethodGroupJvaf InnerMethodGroup { get; set; }
        public List<MethodJvaf> InnerMethods { get; set; }
        public FluentMethodGroup ParentFluentMethodGroup { get; set; }
        public List<FluentMethodGroup> ChildFluentMethodGroups { get; set; }

        public ResourceCreateDescription ResourceCreateDescription
        {
            get
            {
                if (resourceCreateDescription == null)
                {
                    this.resourceCreateDescription = new ResourceCreateDescription(this);
                }
                return this.resourceCreateDescription;
            }
        }

        public ResourceUpdateDescription ResourceUpdateDescription
        {
            get
            {
                if (resourceUpdateDescription == null)
                {
                    this.resourceUpdateDescription = new ResourceUpdateDescription(this.ResourceCreateDescription, this);
                }
                return this.resourceUpdateDescription;
            }
        }

        public ResourceListingDescription ResourceListingDescription
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

        public ResourceGetDescription ResourceGetDescription
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

        public ResourceDeleteDescription ResourceDeleteDescription
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

        public FluentModel StandardFluentModel
        {
            get
            {
                if (!this.derivedFluentModels)
                {
                    throw new InvalidOperationException("DeriveFluentModelForMethodGroup requires to be invoked before InnersRequireWrapping");
                }
                return this.standardFluentModel;
            }
        }

        public Dictionary<string, CompositeTypeJvaf> InnersRequireWrapping
        {
            get
            {
                if (!this.derivedFluentModels)
                {
                    throw new InvalidOperationException("DeriveFluentModelForMethodGroup requires to be invoked before InnersRequireWrapping");
                }
                return this.innersRequireWrapping;
            }
        }

        public IEnumerable<string> NonStandardInnerToStandardInnerWrappingMethodImplementations
        {
            get
            {
                var standardInnerTypeName =  StandardFluentModel.InnerModel.ClassName;
                StringBuilder methodBuilder = new StringBuilder();
                foreach (var innerTypeName in InnersRequireWrapping.Keys)
                {
                    methodBuilder.Clear();
                    methodBuilder.AppendLine($"private {StandardFluentModel.JavaInterfaceName} wrapModel({innerTypeName} inner) {{");
                    methodBuilder.AppendLine($"    {standardInnerTypeName} standardInnerModel = new {standardInnerTypeName}();");
                    methodBuilder.AppendLine($"    return wrapModel(standardInnerModel);");
                    methodBuilder.AppendLine($"}}");
                    yield return methodBuilder.ToString();
                }
                yield break;
            }
        }

        internal void DeriveStandrdFluentModelForMethodGroup()
        {
            if (this.derivedFluentModels)
            {
                return;
            }

            this.derivedFluentModels = true;

            // Find "ONE" fluent model that can be used across "Standard methods" (GetByResourceGroup |
            // ListByResourceGroup | ListBySubscription | GetByImmediateParent | ListByImmediateParent |
            // Create in RG, Update)
            //
            // Derive an "inner model then a fluent model" that represents the return type of standard methods 
            // in this fluent model. We want all thoses standard methods to return same fluent type though the
            // inner methods can return different inner model types.
            //
            CompositeTypeJvaf standardModelInner = null;
            this.innersRequireWrapping = new Dictionary<string, CompositeTypeJvaf>();

            if (ResourceGetDescription.SupportsGetByResourceGroup)
            {
                standardModelInner = ResourceGetDescription.GetByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceCreateDescription.SupportsCreating)
            {
                standardModelInner = ResourceCreateDescription.CreateMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByResourceGroup)
            {
                standardModelInner = ResourceListingDescription.ListByResourceGroupMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListBySubscription)
            {
                standardModelInner = ResourceListingDescription.ListBySubscriptionMethod.InnerReturnType;
            }
            else if (ResourceGetDescription.SupportsGetByImmediateParent)
            {
                standardModelInner = ResourceGetDescription.GetByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceListingDescription.SupportsListByImmediateParent)
            {
                standardModelInner = ResourceListingDescription.ListByImmediateParentMethod.InnerReturnType;
            }
            else if (ResourceUpdateDescription.SupportsUpdating)
            {
                standardModelInner = ResourceUpdateDescription.UpdateMethod.InnerReturnType;
            }

            // For the "standard model" (FModel) in a FluentMethodGroup we need to gen "FModel wrapModel(ModelInner)"
            // but if there are different ModelInner types mapping that needs to be mapped to the same FModel
            // we will be generating one over load per inner -> FModel mapping
            //
            if (standardModelInner != null)
            {
                this.standardFluentModel = new FluentModel(standardModelInner);

                if (ResourceGetDescription.SupportsGetByResourceGroup)
                {
                    var im = ResourceGetDescription.GetByResourceGroupMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceCreateDescription.SupportsCreating)
                {
                    var im = ResourceCreateDescription.CreateMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceListingDescription.SupportsListByResourceGroup)
                {
                    var im = ResourceListingDescription.ListByResourceGroupMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceListingDescription.SupportsListBySubscription)
                {
                    var im = ResourceListingDescription.ListBySubscriptionMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceGetDescription.SupportsGetByImmediateParent)
                {
                    var im = ResourceGetDescription.GetByImmediateParentMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceListingDescription.SupportsListByImmediateParent)
                {
                    var im = ResourceListingDescription.ListByImmediateParentMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                if (ResourceUpdateDescription.SupportsUpdating)
                {
                    var im = ResourceUpdateDescription.UpdateMethod.InnerReturnType;
                    this.innersRequireWrapping.AddIfNotExists(im.ClassName, im);
                }
                // Remove wrapping for standard model as each fluent method group takes care of it locally
                //
                this.innersRequireWrapping.Remove(this.standardFluentModel.InnerModel.ClassName);
            }
        }

        /// <summary>
        /// Checks the method group and it's standard model belongs to groupable category. This will be 
        /// used to decide whether methodgroup can be extends from "GroupableResourcesImpl" and standard
        /// model can implements GroupableResource and extends GroupableResourceImpl.
        /// </summary>
        /// 
        public bool IsGroupableTopLevel
        {
            get
            {
                if (this.Level == 0)
                {
                    if (this.ResourceCreateDescription.SupportsCreating)
                    {
                        // It's guarnteed to be have a standardModel if create is supported
                        //
                        if (this.ResourceCreateDescription.CreateType == CreateType.WithResourceGroupAsParent)
                        {
                            if (this.IsStandardModelAResource)
                            {
                                // FModel -> implements GroupableResource extends GroupableResourceImpl
                                // FGroup -> extends GroupableResourcesImpl
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
                            if (this.IsStandardModelAResource)
                            {
                                // FModel -> implements GroupableResource extends GroupableResourceImpl
                                // FGroup -> extends GroupableResourcesImpl
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
                        return this.IsStandardModelAResource;
                    }
                }
                return false;
            }
        }

        public bool IsNonGroupableTopLevel
        {
            get
            {
                if (this.Level == 0)
                {
                    if (!this.IsGroupableTopLevel)
                    {
                        return this.StandardFluentModel != null;
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

        public bool IsNested
        {
            get
            {
                return this.Level > 0;
            }
        }

        /// <summary>
        /// Given an ARM operation endpoint url derive a fluent method group that the operation can possibly belongs to.
        /// </summary>
        /// <param name="fluentMethodGroups">the dict holding fluent method groups</param>
        /// <param name="urlParts">the ARM operation endpoint url parts</param>
        /// <param name="httpMethod">the http method associated with the ARM operation</param>
        /// <returns>The method group</returns>
        public static FluentMethodGroup ResolveFluentMethodGroup(FluentMethodGroups fluentMethodGroups, List<String> urlParts, HttpMethod httpMethod)
        {
            int level = 0;
            List<String> fluentMethodGroupNamesInUrl = new List<String>();
            Pluralizer pluralizer = new Pluralizer();

            foreach (String urlPart in urlParts)
            {
                if (!IsParameter(urlPart) && IsPlural(urlPart))
                {
                    fluentMethodGroupNamesInUrl.Add(urlPart);
                    level++;
                }
            }

            if (fluentMethodGroupNamesInUrl.Count() == 1)
            {
                return new FluentMethodGroup(fluentMethodGroups)
                {
                    LocalNameInPascalCase = fluentMethodGroupNamesInUrl[0],
                    Level = 0,
                    ParentMethodGroupNames = new List<string>()
                };
            }
            else if (httpMethod == HttpMethod.Post)
            {
                if (!IsParameter(urlParts.Last()) && urlParts.Last().EqualsIgnoreCase(fluentMethodGroupNamesInUrl.Last()))
                {
                    return new FluentMethodGroup(fluentMethodGroups)
                    {
                        LocalNameInPascalCase = fluentMethodGroupNamesInUrl.SkipLast(1).Last(),
                        Level = fluentMethodGroupNamesInUrl.Count() - 2,
                        ParentMethodGroupNames = fluentMethodGroupNamesInUrl.SkipLast(2).ToList()
                    };
                }
                else
                {
                    return new FluentMethodGroup(fluentMethodGroups)
                    {
                        LocalNameInPascalCase = fluentMethodGroupNamesInUrl.Last(),
                        Level = fluentMethodGroupNamesInUrl.Count() - 1,
                        ParentMethodGroupNames = fluentMethodGroupNamesInUrl.SkipLast(1).ToList()
                    };
                }
            }
            else
            {
                return new FluentMethodGroup(fluentMethodGroups)
                {
                    LocalNameInPascalCase = fluentMethodGroupNamesInUrl.Last(),
                    Level = fluentMethodGroupNamesInUrl.Count() - 1,
                    ParentMethodGroupNames = fluentMethodGroupNamesInUrl.SkipLast(1).ToList()
                };
            }
        }

        /// <param name="strToCheck"></param>
        /// <returns>true if the given string represent a url parameter</returns>
        private static bool IsParameter(string strToCheck)
        {
            if (strToCheck == null)
            {
                throw new ArgumentNullException("strToCheck");
            }
            return strToCheck.Trim().StartsWith("{");
        }

        /// <param name="strToCheck"></param>
        /// <returns>true if the given string is plural</returns>
        private static bool IsPlural(string strToCheck)
        {
            if (strToCheck == null)
            {
                throw new ArgumentNullException("strToCheck");
            }
            // TODO: need more reliable way to check the plural
            //
            return strToCheck.EndsWith("s");
        }

        private bool IsStandardModelAResource
        {
            get
            {
                if (this.StandardFluentModel == null)
                {
                    return false;
                }
                else
                {
                    CompositeTypeJvaf innerModel = this.StandardFluentModel.InnerModel;
                    //
                    bool hasId = innerModel.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("id"));
                    bool hasName = innerModel.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("name"));
                    bool hasType = innerModel.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("type"));
                    bool hasLocation = innerModel.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("location"));
                    bool hasTags = innerModel.ComposedProperties.Any(p => p.Name.ToLowerInvariant().Equals("tags"));
                    //
                    return hasId && hasName && hasType && hasLocation && hasTags;
                }
            }
        }
    }
}