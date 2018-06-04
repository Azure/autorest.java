// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An implementation of 'IResourceUpdateDescription' that describes an Azure resource update.
    /// </summary>
    public class ResourceUpdateDescription : IResourceUpdateDescription
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private readonly IResourceCreateDescription createDescription;
        private StandardFluentMethod updateMethod;
        private UpdateType updateType = UpdateType.None;
        private bool isProcessed;

        public ResourceUpdateDescription(IResourceCreateDescription createDescription, 
            FluentMethodGroup fluentMethodGroup) 
        {
            this.createDescription = createDescription;
             this.fluentMethodGroup = fluentMethodGroup;
        }

        public bool SupportsUpdating
        {
            get
            {
                this.Process();
                return this.updateType != UpdateType.None;
            }
        }

        public UpdateType UpdateType
        {
            get
            {
                this.Process();
                return this.updateType;
            }
        }

        public StandardFluentMethod UpdateMethod
        {
            get
            {
                this.Process();
                return this.updateMethod;
            }
        }

        public HashSet<string> ImportsForModelInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.model.Updatable",
                    "com.microsoft.azure.arm.model.Appliable"
                };
                return imports;
            }
        }

        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            //
            this.isProcessed = true;
            //
            // ALGO:
            // 1. Look for an update operation using PATCH
            // 2. If only tags can be updated through PATCH then look for an update operation using PUT with method name heuristics
            // 3. If no such update then use create description for update
            //
            // Why: ARM is mandating atlease to have an update for tags, we don't want to pick such update or resource update
            //
            StandardFluentMethod patchUpdateInRgMethod = this.TryGetUpdateInResourceGroupMethod(findPatchUpdate: true);
            if (patchUpdateInRgMethod != null)
            {
                bool canUpdateOnlyTags = SupportsOnlyTagsUpdate(patchUpdateInRgMethod);
                if (canUpdateOnlyTags == false)
                {
                    this.updateMethod = patchUpdateInRgMethod;
                    this.updateType = UpdateType.WithResourceGroupAsParent;
                }
                else
                {
                    // Only tags can be updated through PATCH
                    //
                    StandardFluentMethod putUpdateInRgMethod = this.TryGetUpdateInResourceGroupMethod(findPatchUpdate: false);
                    if (putUpdateInRgMethod != null)
                    {
                        this.updateMethod = putUpdateInRgMethod;
                        this.updateType = UpdateType.WithResourceGroupAsParent;
                    }
                    else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithResourceGroupAsParent)
                    {
                        this.updateMethod = this.createDescription.CreateMethod;
                        this.updateType = UpdateType.WithResourceGroupAsParent;
                    }
                    else
                    {
                        this.updateMethod = patchUpdateInRgMethod;
                        this.updateType = UpdateType.WithResourceGroupAsParent;
                    }
                }
            }
            else
            {
                StandardFluentMethod putUpdateInRgMethod = this.TryGetUpdateInResourceGroupMethod(findPatchUpdate: false);
                if (putUpdateInRgMethod != null)
                {
                    this.updateMethod = putUpdateInRgMethod;
                    this.updateType = UpdateType.WithResourceGroupAsParent;
                }
                else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithResourceGroupAsParent)
                {
                    this.updateMethod = this.createDescription.CreateMethod;
                    this.updateType = UpdateType.WithResourceGroupAsParent;
                }
            }
            //
            if (this.updateType != UpdateType.None)
            {
                return;
            }
            //
            StandardFluentMethod patchUpdateInSubMethod = this.TryGetUpdateInSubscriptionMethod(findPatchUpdate: true);
            if (patchUpdateInSubMethod != null)
            {
                bool canUpdateOnlyTags = SupportsOnlyTagsUpdate(patchUpdateInSubMethod);
                if (canUpdateOnlyTags == false)
                {
                    this.updateMethod = patchUpdateInSubMethod;
                    this.updateType = UpdateType.WithSubscriptionAsParent;
                }
                else
                {
                    StandardFluentMethod putUpdateInSubMethod = this.TryGetUpdateInSubscriptionMethod(findPatchUpdate: false);
                    if (putUpdateInSubMethod != null)
                    {
                        this.updateMethod = putUpdateInSubMethod;
                        this.updateType = UpdateType.WithSubscriptionAsParent;
                    }
                    else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithSubscriptionAsParent)
                    {
                        this.updateMethod = this.createDescription.CreateMethod;
                        this.updateType = UpdateType.WithSubscriptionAsParent;
                    }
                    else
                    {
                        this.updateMethod = patchUpdateInSubMethod;
                        this.updateType = UpdateType.WithSubscriptionAsParent;
                    }
                }
            }
            else
            {
                StandardFluentMethod putUpdateInSubMethod = this.TryGetUpdateInSubscriptionMethod(findPatchUpdate: false);
                if (putUpdateInSubMethod != null)
                {
                    this.updateMethod = putUpdateInSubMethod;
                    this.updateType = UpdateType.WithSubscriptionAsParent;
                }
                else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithSubscriptionAsParent)
                {
                    this.updateMethod = this.createDescription.CreateMethod;
                    this.updateType = UpdateType.WithSubscriptionAsParent;
                }
            }
            //
            if (this.updateType != UpdateType.None)
            {
                return;
            }
            //
            StandardFluentMethod patchUpdateInParameterizedParentMethod = this.TryGetUpdateInParameterizedParentMethod(findPatchUpdate: true);
            if (patchUpdateInParameterizedParentMethod != null)
            {
                bool canUpdateOnlyTags = SupportsOnlyTagsUpdate(patchUpdateInParameterizedParentMethod);
                if (canUpdateOnlyTags == false)
                {
                    this.updateMethod = patchUpdateInParameterizedParentMethod;
                    this.updateType = UpdateType.WithParameterizedParent;
                }
                else
                {
                    StandardFluentMethod putUpdateInParameterizedParentMethod = this.TryGetUpdateInParameterizedParentMethod(findPatchUpdate: false);
                    if (putUpdateInParameterizedParentMethod != null)
                    {
                        this.updateMethod = putUpdateInParameterizedParentMethod;
                        this.updateType = UpdateType.WithParameterizedParent;
                    }
                    else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithParameterizedParent)
                    {
                        this.updateMethod = this.createDescription.CreateMethod;
                        this.updateType = UpdateType.WithParameterizedParent;
                    }
                    else
                    {
                        this.updateMethod = patchUpdateInParameterizedParentMethod;
                        this.updateType = UpdateType.WithParameterizedParent;
                    }
                }
            }
            else
            {
                StandardFluentMethod putUpdateInParameterizedParentMethod = this.TryGetUpdateInParameterizedParentMethod(findPatchUpdate: false);
                if (putUpdateInParameterizedParentMethod != null)
                {
                    this.updateMethod = putUpdateInParameterizedParentMethod;
                    this.updateType = UpdateType.WithParameterizedParent;
                }
                else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.WithParameterizedParent)
                {
                    this.updateMethod = this.createDescription.CreateMethod;
                    this.updateType = UpdateType.WithParameterizedParent;
                }
            }
            //
            if (this.updateType != UpdateType.None)
            {
                return;
            }
            //
            StandardFluentMethod patchUpdateAsNestedMethod = this.TryGetUpdateAsNestedChildMethod(findPatchUpdate: true);
            if (patchUpdateAsNestedMethod != null)
            {
                bool canUpdateOnlyTags = SupportsOnlyTagsUpdate(patchUpdateAsNestedMethod);
                if (canUpdateOnlyTags == false)
                {
                    this.updateMethod = patchUpdateAsNestedMethod;
                    this.updateType = UpdateType.AsNestedChild;
                }
                else
                {
                    StandardFluentMethod putUpdateAsNestedMethod = this.TryGetUpdateAsNestedChildMethod(findPatchUpdate: false);
                    if (putUpdateAsNestedMethod != null)
                    {
                        this.updateMethod = putUpdateAsNestedMethod;
                        this.updateType = UpdateType.AsNestedChild;
                    }
                    else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.AsNestedChild)
                    {
                        this.updateMethod = this.createDescription.CreateMethod;
                        this.updateType = UpdateType.AsNestedChild;
                    }
                    else
                    {
                        this.updateMethod = patchUpdateAsNestedMethod;
                        this.updateType = UpdateType.AsNestedChild;
                    }
                }
            }
            else
            {
                StandardFluentMethod putUpdateAsNestedMethod = this.TryGetUpdateAsNestedChildMethod(findPatchUpdate: false);
                if (putUpdateAsNestedMethod != null)
                {
                    this.updateMethod = putUpdateAsNestedMethod;
                    this.updateType = UpdateType.AsNestedChild;
                }
                else if (this.createDescription.SupportsCreating && this.createDescription.CreateType == CreateType.AsNestedChild)
                {
                    this.updateMethod = this.createDescription.CreateMethod;
                    this.updateType = UpdateType.AsNestedChild;
                }
            }
        }

        private StandardFluentMethod TryGetUpdateInResourceGroupMethod(bool findPatchUpdate)
        {
            bool findPutUpdate = !findPatchUpdate;
            //
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
            {
                if ((findPatchUpdate && innerMethod.HttpMethod == HttpMethod.Patch) || (findPutUpdate && innerMethod.HttpMethod == HttpMethod.Put))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportUpdating<T>, we should be able to map resource of update to T
                        // if the return type is primitive type (e.g. void), sequence type, dict type then mapping cannot be done.
                        // Skip update methods returning such types they will be appear as other methods
                        continue;
                    }
                    else if (!Utils.HasProperty(innerMethod.ReturnTypeJva.BodyClientType, "name"))
                    {
                        // A model that is updatable has to be derive from CreatableUpdatableImpl which requires name 
                        // property to present.
                        continue;
                    }
                    else
                    {
                        if (findPutUpdate)
                        {
                            string innerMethodName = innerMethod.Name.ToLowerInvariant();
                            if (innerMethodName.Contains("create") && !innerMethodName.Contains("update"))
                            {
                                // There are resources that does not support update, but support create through PUT
                                // here using method name pattern as heuristics to skip such methods to be considered
                                // as update method.
                                //
                                continue;
                            }
                        }
                        //
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.fluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    if (subscriptionSegment != null)
                                    {
                                        var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                        if (resourceGroupSegment != null && StandardFluentMethod.CanWrap(innerMethod))
                                        {
                                            return new StandardFluentMethod(innerMethod, this.fluentMethodGroup);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private StandardFluentMethod TryGetUpdateInSubscriptionMethod(bool findPatchUpdate)
        {
            bool findPutUpdate = !findPatchUpdate;
            //
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
            {
                if ((findPatchUpdate && innerMethod.HttpMethod == HttpMethod.Patch) || (findPutUpdate && innerMethod.HttpMethod == HttpMethod.Put))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportUpdating<T> where T is class/interface type, we should be
                        // able to map response resource of update to T. if the return type is primitive type (e.g. void),
                        // sequence type, dict type then mapping cannot be done. Skip create methods returning such types
                        // they will be appear as other methods
                        continue;
                    }
                    else if (!Utils.HasProperty(innerMethod.ReturnTypeJva.BodyClientType, "name"))
                    {
                        // A model that is updatable has to be derive from CreatableUpdatableImpl which requires name 
                        // property to present.
                        continue;
                    }
                    else
                    {
                        if (findPutUpdate)
                        {
                            string innerMethodName = innerMethod.Name.ToLowerInvariant();
                            if (innerMethodName.Contains("create") && !innerMethodName.Contains("update"))
                            {
                                // There are resources that does not support update, but support create through PUT
                                // here using method name pattern as heuristics to skip such methods to be considered
                                // as update method.
                                //
                                continue;
                            }
                        }
                        //
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.fluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    if (subscriptionSegment != null)
                                    {
                                        var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                        if (resourceGroupSegment == null && StandardFluentMethod.CanWrap(innerMethod))
                                        {
                                            return new StandardFluentMethod(innerMethod, this.fluentMethodGroup);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private StandardFluentMethod TryGetUpdateAsNestedChildMethod(bool findPatchUpdate)
        {
            bool findPutUpdate = !findPatchUpdate;
            //
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
            {
                if ((findPatchUpdate && innerMethod.HttpMethod == HttpMethod.Patch) || (findPutUpdate && innerMethod.HttpMethod == HttpMethod.Put))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportUpdating<T>, we should be able to map resource of update to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip update methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else if (!Utils.HasProperty(innerMethod.ReturnTypeJva.BodyClientType, "name"))
                    {
                        // A model that is updatable has to be derive from CreatableUpdatableImpl which requires name 
                        // property to present.
                        continue;
                    }
                    else
                    {
                        if (findPutUpdate)
                        {
                            string innerMethodName = innerMethod.Name.ToLowerInvariant();
                            if (innerMethodName.Contains("create") && !innerMethodName.Contains("update"))
                            {
                                // There are resources that does not support update, but support create through PUT
                                // here using method name pattern as heuristics to skip such methods to be considered
                                // as update method.
                                //
                                continue;
                            }
                        }
                        //
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.fluentMethodGroup.Level > 0 && StandardFluentMethod.CanWrap(innerMethod))
                                {
                                    return new StandardFluentMethod(innerMethod, this.fluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private StandardFluentMethod TryGetUpdateInParameterizedParentMethod(bool findPatchUpdate)
        {
            bool findPutUpdate = !findPatchUpdate;
            //
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
            {
                if ((findPatchUpdate && innerMethod.HttpMethod == HttpMethod.Patch) || (findPutUpdate && innerMethod.HttpMethod == HttpMethod.Put))
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportUpdating<T>, we should be able to map resource of update to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip update methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else if (!Utils.HasProperty(innerMethod.ReturnTypeJva.BodyClientType, "name"))
                    {
                        // A model that is creatable has to be derive from CreatableUpdatableImpl which requires name 
                        // property to present.
                        continue;
                    }
                    else
                    {
                        if (findPutUpdate)
                        {
                            string innerMethodName = innerMethod.Name.ToLowerInvariant();
                            if (innerMethodName.Contains("create") && !innerMethodName.Contains("update"))
                            {
                                // There are resources that does not support update, but support create through PUT
                                // here using method name pattern as heuristics to skip such methods to be considered
                                // as update method.
                                //
                                continue;
                            }
                        }
                        //
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.fluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));

                                    if (subscriptionSegment == null && resourceGroupSegment == null && StandardFluentMethod.CanWrap(innerMethod))
                                    {
                                        return new StandardFluentMethod(innerMethod, this.fluentMethodGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private bool SupportsOnlyTagsUpdate(StandardFluentMethod fluentMethod)
        {
            if (fluentMethod.InnerMethod.Body is ParameterJv p && p.ClientType is CompositeTypeJvaf payloadType)
            {
                var properties= payloadType.Properties;
                if (properties.Count == 1 && properties.First().SerializedName.EqualsIgnoreCase("tags"))
                {
                    return true;
                }
            }
            return false;
        }
    }
}