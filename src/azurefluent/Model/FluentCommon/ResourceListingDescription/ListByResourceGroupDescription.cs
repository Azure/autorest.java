﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing list description of standard model (of a method group) under resource group scope.
    /// </summary>
    public class ListByResourceGroupDescription : ListDescriptionBase
    {
        public ListByResourceGroupDescription(FluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup)
        {
        }

        private bool supportsListing;
        public override bool SupportsListing
        {
            get
            {
                this.Process();
                return this.supportsListing;
            }
        }

        private StandardFluentMethod listMethod;
        public override StandardFluentMethod ListMethod
        {
            get
            {
                this.Process();
                return this.listMethod;
            }
        }

        public override HashSet<string> MethodGroupInterfaceExtendsFrom
        {
            get
            {
                HashSet<string> extendsFrom = new HashSet<string>();
                if (this.SupportsListing)
                {
                    extendsFrom.Add($"SupportsListingByResourceGroup<{this.ListMethod.ReturnModel.JavaInterfaceName}>");
                }
                return extendsFrom;
            }
        }

        public override HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListing)
                {
                    imports.Add("com.microsoft.azure.arm.resources.collection.SupportsListingByResourceGroup");
                }
                return imports;
            }
        }

        public override HashSet<string> ImportsForMethodGroupImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListing)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    imports.Add("com.microsoft.azure.PagedList");
                    StandardFluentMethod method = this.ListMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                }
                return imports;
            }
        }

        public override HashSet<string> ImportsForGeneralizedInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListing)
                {
                    imports.Add("rx.Observable");
                    imports.Add($"{package}.{this.FluentMethodGroup.StandardFluentModel.JavaInterfaceName}");
                }
                return imports;
            }
        }

        public override HashSet<string> ImportsForGeneralizedImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListing)
                {
                    imports.Add("rx.Observable");
                    imports.Add("rx.functions.Func1");
                    StandardFluentMethod method = this.ListMethod;
                    if (method.InnerMethod.IsPagingOperation || method.InnerMethod.SimulateAsPagingOperation)
                    {
                        imports.Add("com.microsoft.azure.Page");
                        imports.Add("rx.functions.Func1");
                    }
                    if (!method.InnerMethod.IsPagingOperation && !method.InnerMethod.SimulateAsPagingOperation && method.InnerMethod.ReturnTypeResponseName.StartsWith("List<"))
                    {
                        imports.Add("rx.Observable");
                        imports.Add("java.util.List");
                        imports.Add("rx.functions.Func1");
                    }
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    imports.Add($"{package}.{modelInterfaceName}");
                }
                return imports;
            }
        }

        public override string GeneralizedMethodDecl
        {
            get
            {
                if (this.SupportsListing)
                {
                    StandardModel standardModel = this.FluentMethodGroup.StandardFluentModel;
                    string modelInterfaceName = standardModel.JavaInterfaceName;
                    //
                    var method = this.ListMethod;
                    var innerMethod = method.InnerMethod;
                    //
                    StringBuilder methodsBuilder = new StringBuilder();
                    methodsBuilder.AppendLine($"/**");
                    if (!string.IsNullOrEmpty(innerMethod.Summary))
                    {
                        methodsBuilder.AppendLine($" * {innerMethod.Summary.EscapeXmlComment().Period()}");
                    }
                    if (!string.IsNullOrEmpty(innerMethod.Description))
                    {
                        methodsBuilder.AppendLine($" * {innerMethod.Description.EscapeXmlComment().Period()}");
                    }
                    methodsBuilder.AppendLine($" *");
                    methodsBuilder.AppendLine($" * @param resourceGroupName resource group name");
                    methodsBuilder.AppendLine($" * @throws IllegalArgumentException thrown if parameters fail the validation");
                    methodsBuilder.AppendLine($" * @return the observable for the request");
                    methodsBuilder.AppendLine($" */");
                    methodsBuilder.AppendLine($"Observable<{modelInterfaceName}> {method.Name}Async(String resourceGroupName);");
                    return methodsBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public override string GeneralizedMethodImpl
        {
            get
            {
                if (this.SupportsListing)
                {
                    return this.ListRxAsyncMethodImplementation(true);
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public override string ListRxAsyncMethodImplementation(bool isGeneralized)
        {
            if (this.SupportsListing)
            {
                if (isGeneralized)
                {
                    return ListRxAsyncMethodImplementation(method: this.ListMethod,
                        fluentMethodName: this.ListMethod.Name,
                        parameterDecl: "String resourceGroupName",
                        parameterInvoke: "resourceGroupName",
                        isGeneralized: true);
                }
                else
                {
                    return ListRxAsyncMethodImplementation(method: this.ListMethod,
                        fluentMethodName: "listByResourceGroup",
                        parameterDecl: "String resourceGroupName",
                        parameterInvoke: "resourceGroupName",
                        isGeneralized: false);
                }
            }
            else
            {
                return string.Empty;
            }
        }

        public override string ListSyncMethodImplementation(string convertToPagedListMethodName)
        {
            if (this.SupportsListing)
            {
                return ListSyncMethodImplementation(convertToPagedListMethodName: convertToPagedListMethodName,
                    method: this.ListMethod,
                    fluentMethodName: "listByResourceGroup",
                    parameterDecl: "String resourceGroupName",
                    parameterInvoke: "resourceGroupName",
                    isGeneralized: false);
            }
            else
            {
                return string.Empty;
            }
        }

        private bool isProcessed;
        private void Process()
        {
            if (this.isProcessed)
            {
                return;
            }
            else
            {
                this.isProcessed = true;
                this.CheckListByResourceGroupSupport();
            }
        }

        /// <summary>
        /// Checks can support "SupportsListByResourceGroup" interface
        /// </summary>
        private void CheckListByResourceGroupSupport()
        {
            if (this.FluentMethodGroup.Level == 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    var armUri = new ARMUri(innerMethod);
                    Segment lastSegment = armUri.LastOrDefault();
                    if (lastSegment != null && lastSegment is TerminalSegment)
                    {
                        TerminalSegment terminalSegment = (TerminalSegment)lastSegment;
                        var requiredParameters = Utils.RequiredParametersOfMethod(innerMethod);
                        if (terminalSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase) && requiredParameters.Count() == 1)
                        {
                            var subscriptionSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("subscriptions"));
                            var resourceGroupSegment = armUri.OfType<ParentSegment>().FirstOrDefault(segment => segment.Name.EqualsIgnoreCase("resourceGroups"));
                            if (subscriptionSegment != null && resourceGroupSegment != null)
                            {
                                var singleParameter = requiredParameters.First();
                                if (resourceGroupSegment.Parameter.SerializedName.EqualsIgnoreCase(singleParameter.SerializedName))
                                {
                                    if (innerMethod.ReturnTypeResponseName.StartsWith("PagedList<")
                                        || innerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                    {
                                        if (StandardFluentMethod.CanWrap(innerMethod))
                                        {
                                            this.supportsListing = true;
                                            this.listMethod = new StandardFluentMethod(innerMethod, this.FluentMethodGroup);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else
            {
                this.supportsListing = false;
                this.listMethod = null;
            }
        }
    }
}
