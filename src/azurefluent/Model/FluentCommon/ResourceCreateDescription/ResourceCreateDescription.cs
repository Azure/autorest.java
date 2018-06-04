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
    /// An implementation of 'IResourceCreateDescription' that describes creation of an Azure resource.
    /// </summary>
    public class ResourceCreateDescription : IResourceCreateDescription
    {
        private bool isProcessed;
        private StandardFluentMethod createMethod;
        private CreateType createType = CreateType.None;

        public ResourceCreateDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.FluentMethodGroup = fluentMethodGroup;
        }

        public IFluentMethodGroup FluentMethodGroup { get; }

        public bool SupportsCreating
        {
            get
            {
                this.Process();
                return this.createType != CreateType.None;
            }
        }

        public CreateType CreateType
        {
            get
            {
                this.Process();
                return this.createType;
            }
        }

        public StandardFluentMethod CreateMethod
        {
            get
            {
                this.Process();
                return this.createMethod;
            }
        }

        private IWrapNewModelFunc wrapNewModelFunc;
        public IWrapNewModelFunc WrapNewModelFunc
        {
            get
            {
                if (this.wrapNewModelFunc == null)
                {
                    this.Process();
                    this.wrapNewModelFunc = new WrapNewModelFunc(this);
                }
                return this.wrapNewModelFunc;
            }
        }

        private IDefineFunc defineFunc;
        public IDefineFunc DefineFunc
        {
            get
            {
                if (this.defineFunc == null)
                {
                    this.Process();
                    this.defineFunc = new DefineFunc(this);
                }
                return this.defineFunc;
            }
        }

        public HashSet<string> ImportsForModelInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.model.Creatable"
                };
                return imports;
            }
        }

        public HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsCreating)
                {
                    extendsFrom.Add($"SupportsCreating<{this.CreateMethod.ReturnModel.JavaInterfaceName}.DefinitionStages.Blank>");
                }
                return extendsFrom;
            }
        }

        public HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsCreating)
                {
                    imports.Add("com.microsoft.azure.arm.collection.SupportsCreating");
                }
                return imports;
            }
        }

        public HashSet<String> ImportsForMethodGroupImpl
        {
            get
            {
                return new HashSet<string>();
            }
        }

        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            else
            {
                // A "fluent method group" can expose only one "define(name)" method. Though some resource can be
                // created in different scope (immediately under a resource group, in subscription etc..),
                // we need to decide which one to expose via "define". Below we choose one from multiple create
                // methods, if there are. The unchoosen create methods will be exposed as-it-is in "Fluent Method Group"
                // just lik any "other methods".
                // 
                this.isProcessed = true;
                StandardFluentMethod createInRgMethod = this.TryGetCreateInResourceGroupMethod();
                if (createInRgMethod != null)
                {
                    this.createMethod = createInRgMethod;
                    this.createType = CreateType.WithResourceGroupAsParent;
                }
                else
                {
                    StandardFluentMethod createInSubMethod = this.TryGetCreateInSubscriptionMethod();
                    if (createInSubMethod != null)
                    {
                        this.createMethod = createInSubMethod;
                        this.createType = CreateType.WithSubscriptionAsParent;
                    }
                    else
                    {
                        StandardFluentMethod createInParamParentMethod = this.TryGetCreateInParameterizedParentMethod();
                        if (createInParamParentMethod != null)
                        {
                            this.createMethod = createInParamParentMethod;
                            this.createType = CreateType.WithParameterizedParent;
                        }
                        else
                        {
                            StandardFluentMethod createAsNestedMethod = this.TryGetCreateAsNestedChildMethod();
                            if (createAsNestedMethod != null)
                            {
                                this.createMethod = createAsNestedMethod;
                                this.createType = CreateType.AsNestedChild;
                            }
                        }
                    }
                }
            }
        }

        private StandardFluentMethod TryGetCreateInResourceGroupMethod()
        {
            foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods)
            {
                string innerMethodName = innerMethod.Name.ToLowerInvariant();
                if (innerMethodName.Contains("update") && !innerMethodName.Contains("create"))
                {
                    // There are resources that does not support create, but support update through PUT
                    // here using method name pattern as heuristics to skip such methods to be considered
                    // as create method.
                    //
                    continue;
                }
                if (innerMethod.HttpMethod == HttpMethod.Put)
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsCreating<T> where T is class/interface type, we should be
                        // able to map response resource of create to T. if the return type is primitive type (e.g. void),
                        // sequence type, dict type then mapping cannot be done. Skip create methods returning such types
                        // they will be appear as other methods
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
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.FluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    if (subscriptionSegment != null)
                                    {
                                        var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                        if (resourceGroupSegment != null && StandardFluentMethod.CanWrap(innerMethod))
                                        {
                                            return new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
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

        private StandardFluentMethod TryGetCreateInSubscriptionMethod()
        {
            foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods)
            {
                string innerMethodName = innerMethod.Name.ToLowerInvariant();
                if (innerMethodName.Contains("update") && !innerMethodName.Contains("create"))
                {
                    // There are resources that does not support create, but support update through PUT
                    // here using method name pattern as heuristics to skip such methods to be considered
                    // as create method.
                    //
                    continue;
                }
                if (innerMethod.HttpMethod == HttpMethod.Put)
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsCreating<T> where T is class/interface type, we should be
                        // able to map response resource of create to T. if the return type is primitive type (e.g. void),
                        // sequence type, dict type then mapping cannot be done. Skip create methods returning such types
                        // they will be appear as other methods
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
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.FluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    if (subscriptionSegment != null)
                                    {
                                        var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                                        if (resourceGroupSegment == null && StandardFluentMethod.CanWrap(innerMethod))
                                        {
                                            return new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
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

        private StandardFluentMethod TryGetCreateAsNestedChildMethod()
        {
            foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods)
            {
                string innerMethodName = innerMethod.Name.ToLowerInvariant();
                if (innerMethodName.Contains("update") && !innerMethodName.Contains("create"))
                {
                    // There are resources that does not support create, but support update through PUT
                    // here using method name pattern as heuristics to skip such methods to be considered
                    // as create method.
                    //
                    continue;
                }

                if (innerMethod.HttpMethod == HttpMethod.Put)
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsCreating<T> where T is class/interface type, we should be
                        // able to map response resource of create to T. if the return type is primitive type (e.g. void),
                        // sequence type, dict type then mapping cannot be done. Skip create methods returning such types
                        // they will be appear as other methods
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
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.FluentMethodGroup.Level > 0 && StandardFluentMethod.CanWrap(innerMethod))
                                {
                                    return new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private StandardFluentMethod TryGetCreateInParameterizedParentMethod()
        {
            foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods)
            {
                string innerMethodName = innerMethod.Name.ToLowerInvariant();
                if (innerMethodName.Contains("update") && !innerMethodName.Contains("create"))
                {
                    // There are resources that does not support create, but support update through PUT
                    // here using method name pattern as heuristics to skip such methods to be considered
                    // as create method.
                    //
                    continue;
                }
                if (innerMethod.HttpMethod == HttpMethod.Put)
                {
                    bool isResponseCompositeType = innerMethod.ReturnTypeJva.BodyClientType is CompositeTypeJv;
                    if (!isResponseCompositeType)
                    {
                        // In order to be able to implement SupportsCreating<T> where T is class/interface type, we should be
                        // able to map response resource of create to T. if the return type is primitive type (e.g. void),
                        // sequence type, dict type then mapping cannot be done. Skip create methods returning such types
                        // they will be appear as other methods
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
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.FluentMethodGroup.Level == 0)
                                {
                                    var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                                    var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));

                                    if (subscriptionSegment == null && resourceGroupSegment == null && StandardFluentMethod.CanWrap(innerMethod))
                                    {
                                        return new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }
    }
}