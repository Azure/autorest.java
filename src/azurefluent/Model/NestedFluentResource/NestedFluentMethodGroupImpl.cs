﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class NestedFluentMethodGroupImpl : FluentMethodGroupImpl
    {
        public NestedFluentMethodGroupImpl(IFluentMethodGroup methodGroup) : base(methodGroup)
        {
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>
                {
                    "com.microsoft.azure.arm.model.implementation.WrapperImpl",
                    $"{this.package}.{this.JvaInterfaceName}",
                };
                imports.AddRange(this.Interface.ResourceCreateDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceDeleteDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceGetDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.ResourceListingDescription.ImportsForMethodGroupImpl);
                imports.AddRange(this.Interface.OtherMethods.ImportsForImpl);

                if (this.Interface.ResourceGetDescription.SupportsGetByImmediateParent 
                    || this.Interface.ResourceListingDescription.SupportsListByImmediateParent)
                {
                    imports.Add($"{this.package}.{this.Model.JavaInterfaceName}");
                }
                //
                imports.AddRange(base.ImportsForGeneralizedMethodImpls);
                return imports;
            }
        }

        public string ExtendsFrom
        {
            get
            {
                return $" extends WrapperImpl<{this.InnerMethodGroupName}>";
            }
        }

        public string Implements
        {
            get
            {
                return $" implements {this.Interface.JavaInterfaceName}";
            }
        }

        public IEnumerable<string> JavaMethods
        {
            get
            {
                yield return this.CtrImplementation;
                yield return this.ManagerGetterImplementation;
                yield return this.DefineMethodImplementation;
                yield return this.WrapExistingModelImplementation;
                yield return this.WrapNewModelImplementation;
                foreach (string impl in this.Interface.OtherMethods.MethodImpls)
                {
                    yield return impl;
                }
                yield return this.ListByImmediateParentMethodImplementation;
                yield return this.GetByImmediateParentMethodImplementation;
                yield return this.DeleteByImmediateParentMethodImplementation;
                foreach (string impl in base.GeneralizedMethodImpls)
                {
                    yield return impl;
                }
            }
        }

        private string CtrImplementation
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                methodBuilder.AppendLine($"{this.JavaClassName}({this.ManagerTypeName} manager) {{");
                methodBuilder.AppendLine($"    super(manager.inner().{this.InnerClientAccessorName}());"); // WrapperImpl(inner)
                methodBuilder.AppendLine($"    this.manager = manager;");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        private string DefineMethodImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.DefineFunc.MethodImpl;
            }
        }

        private string WrapExistingModelImplementation
        {
            get
            {
                return this.Model.WrapExistingModelFunc.MethodImpl(false);
            }
        }

        private string ListByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceListingDescription.ListByImmediateParentRxAsyncMethodImplementation(false);
            }
        }


        private string GetByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceGetDescription.GetByImmediateParentRxAsyncMethodImplementation(false);
            }
        }

        private string DeleteByImmediateParentMethodImplementation
        {
            get
            {
                return this.Interface.ResourceDeleteDescription.DeleteByImmediateParentRxAsyncMethodImplementation;
            }
        }

        private string WrapNewModelImplementation
        {
            get
            {
                return this.Interface.ResourceCreateDescription.WrapNewModelFunc.MethodImpl(false);
            }
        }
    }
}
