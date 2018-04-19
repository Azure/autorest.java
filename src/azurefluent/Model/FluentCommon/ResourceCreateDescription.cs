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
    public enum CreateType
    {
        None,
        WithResourceGroupAsParent,
        AsNestedChild,
        WithSubscriptionAsParent,
        WithParameterizedParent
    }

    public class ResourceCreateDescription
    {
        private readonly FluentMethodGroup fluentMethodGroup;
        private bool isProcessed;
        private FluentMethod createMethod;
        private CreateType createType = CreateType.None;

        public ResourceCreateDescription(FluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
        }

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

        public FluentMethod CreateMethod
        {
            get
            {
                this.Process();
                return this.createMethod;
            }
        }

        public HashSet<string> ImportsForModelInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.management.resources.fluentcore.model.Creatable"
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
                    imports.Add("com.microsoft.azure.management.resources.fluentcore.collection.SupportsCreating");
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
                // methods, if there are. The unchoosen create methods will be exposed as it is in "fluent method group",
                // just lik any "other methods".
                // 
                this.isProcessed = true;
                FluentMethod createInRgMethod = this.TryGetCreateInResourceGroupMethod();
                if (createInRgMethod != null)
                {
                    this.createMethod = createInRgMethod;
                    this.createType = CreateType.WithResourceGroupAsParent;
                }
                else
                {
                    FluentMethod createInSubMethod = this.TryGetCreateInSubscriptionMethod();
                    if (createInSubMethod != null)
                    {
                        this.createMethod = createInSubMethod;
                        this.createType = CreateType.WithSubscriptionAsParent;
                    }
                    else
                    {
                        FluentMethod createInParamParentMethod = this.TryGetCreateInParameterizedParentMethod();
                        if (createInParamParentMethod != null)
                        {
                            this.createMethod = createInParamParentMethod;
                            this.createType = CreateType.WithParameterizedParent;
                        }
                        else
                        {
                            FluentMethod createAsNestedMethod = this.TryGetCreateAsNestedChildMethod();
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

        private FluentMethod TryGetCreateInResourceGroupMethod()
        {
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
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
                    if (innerMethod.ReturnTypeJva.BodyClientType is PrimaryTypeJv)
                    {
                        // In order to be able to implement SupportsCreating<T>, we should be able to map resource of create to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip create methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else
                    {
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
                                        if (resourceGroupSegment != null)
                                        {
                                            return new FluentMethod(true, innerMethod, this.fluentMethodGroup);
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

        private FluentMethod TryGetCreateInSubscriptionMethod()
        {
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
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
                    if (innerMethod.ReturnTypeJva.BodyClientType is PrimaryTypeJv)
                    {
                        // In order to be able to implement SupportsCreating<T>, we should be able to map resource of create to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip create methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else
                    {
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
                                        if (resourceGroupSegment == null)
                                        {
                                            return new FluentMethod(true, innerMethod, this.fluentMethodGroup);
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

        private FluentMethod TryGetCreateAsNestedChildMethod()
        {
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
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
                    if (innerMethod.ReturnTypeJva.BodyClientType is PrimaryTypeJv)
                    {
                        // In order to be able to implement SupportsCreating<T>, we should be able to map resource of create to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip create methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is ParentSegment)
                        {
                            ParentSegment resourceSegment = (ParentSegment)lastSegment;
                            if (resourceSegment.Name.EqualsIgnoreCase(fluentMethodGroup.LocalNameInPascalCase))
                            {
                                if (this.fluentMethodGroup.Level > 0)
                                {
                                    return new FluentMethod(true, innerMethod, this.fluentMethodGroup);
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private FluentMethod TryGetCreateInParameterizedParentMethod()
        {
            foreach (MethodJvaf innerMethod in fluentMethodGroup.InnerMethods)
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
                    if (innerMethod.ReturnTypeJva.BodyClientType is PrimaryTypeJv)
                    {
                        // In order to be able to implement SupportsCreating<T>, we should be able to map resource of create to T
                        // if the return type is primitive type (e.g. void) then mapping cannot be done. Skip create methods 
                        // returning such primitve they will be appear as other methods
                        continue;
                    }
                    else
                    {
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

                                    if (subscriptionSegment == null && resourceGroupSegment == null)
                                    {
                                        return new FluentMethod(true, innerMethod, this.fluentMethodGroup);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return null;
        }

        private static IEnumerable<ParameterJv> RequiredParametersOfMethod(MethodJvaf method)
        {
            return method.LocalParameters.Where(parameter => parameter.IsRequired && !parameter.IsConstant);
        }
    }
}