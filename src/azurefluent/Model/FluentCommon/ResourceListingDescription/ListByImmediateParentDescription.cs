// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.azurefluent.Model;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing list description of standard model (of a method group) under parent scope.
    /// </summary>
    public class ListByImmediateParentDescription : ListDescriptionBase
    {
        public ListByImmediateParentDescription(FluentMethodGroup fluentMethodGroup) : base(fluentMethodGroup)
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

        private FluentMethod listMethod;
        public override FluentMethod ListMethod
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
                return Utils.EmptyStringSet;
            }
        }

        public override HashSet<string> ImportsForMethodGroupInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (this.SupportsListing)
                {
                    imports.Add("rx.Observable");
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
                    FluentMethod method = this.ListMethod;
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
                    FluentMethod method = this.ListMethod;
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
                    //
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
                    FluentMethod method = this.ListMethod;
                    string parameterDecl = method.InnerMethod.MethodRequiredParameterDeclaration;
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
                    foreach (var param in innerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                    {
                        methodsBuilder.AppendLine($" * @param {param.Name} {param.Documentation.Else("the " + param.ModelType.Name + " value").EscapeXmlComment().Trim()}");
                    }
                    methodsBuilder.AppendLine($" * @throws IllegalArgumentException thrown if parameters fail the validation");
                    methodsBuilder.AppendLine($" * @return the observable for the request");
                    methodsBuilder.AppendLine($" */");
                    methodsBuilder.AppendLine($"Observable<{modelInterfaceName}> {method.Name}Async({parameterDecl});");
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
                        parameterDecl: this.ListMethod.InnerMethod.MethodRequiredParameterDeclaration,
                        parameterInvoke: this.ListMethod.InnerMethodInvocationParameters,
                        isGeneralized: true);
                }
                else
                {
                    return ListRxAsyncMethodImplementation(method: this.ListMethod,
                        fluentMethodName: this.ListMethod.Name,
                        parameterDecl: this.ListMethod.InnerMethod.MethodRequiredParameterDeclaration,
                        parameterInvoke: this.ListMethod.InnerMethodInvocationParameters,
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
            return string.Empty;
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
                this.CheckListByImmediateParentSupport();
            }
        }

        /// <summary>
        /// Check can support list by immediate parent.
        /// </summary>
        private void CheckListByImmediateParentSupport()
        {
            if (this.FluentMethodGroup.Level > 0)
            {
                foreach (MethodJvaf innerMethod in FluentMethodGroup.InnerMethods.Where(method => method.HttpMethod == HttpMethod.Get))
                {
                    IFluentMethodGroup parentMethodGroup = this.FluentMethodGroup.ParentFluentMethodGroup;
                    if (parentMethodGroup != null)
                    {
                        var armUri = new ARMUri(innerMethod);
                        Segment lastSegment = armUri.LastOrDefault();
                        if (lastSegment != null && lastSegment is TerminalSegment)
                        {
                            TerminalSegment terminalSegment = (TerminalSegment)lastSegment;
                            if (terminalSegment.Name.EqualsIgnoreCase(FluentMethodGroup.LocalNameInPascalCase))
                            {
                                Segment secondLastSegment = armUri.SkipLast(1).LastOrDefault();
                                if (secondLastSegment != null && secondLastSegment is ParentSegment)
                                {
                                    ParentSegment parentSegment = (ParentSegment)secondLastSegment;
                                    if (parentSegment.Name.EqualsIgnoreCase(parentMethodGroup.LocalNameInPascalCase))
                                    {
                                        if (innerMethod.ReturnTypeResponseName.StartsWith("PagedList<")
                                            || innerMethod.ReturnTypeResponseName.StartsWith("List<"))
                                        {
                                            if (innerMethod.HasWrappableReturnType())
                                            {
                                                this.supportsListing = true;
                                                this.listMethod = new FluentMethod(true, innerMethod, this.FluentMethodGroup);
                                                break;
                                            }
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
