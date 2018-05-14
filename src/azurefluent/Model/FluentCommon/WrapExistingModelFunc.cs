// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents 'wrapModel(innerT inner)' function that wraps inner model in it's impl model.
    /// </summary>
    public class WrapExistingModelFunc
    {
        private readonly FluentModel fluentModel;

        public WrapExistingModelFunc(FluentModel fluentModel)
        {
            this.fluentModel = fluentModel ?? throw new ArgumentNullException(nameof(fluentModel));
        }

        public string GeneralizedMethodName
        {
            get
            {
                return $"wrapModel";
            }
        }

        public string MethodName
        {
            get
            {
                return $"wrapModel";
            }
        }

        /// <summary>
        /// The innner model.
        /// </summary>
        public string WrappedInnerName
        {
            get
            {
                return fluentModel.InnerModelName;
            }
        }

        /// <summary>
        /// The impl model that wraps the inner model.
        /// </summary>
        public string WrapperImplName
        {
            get
            {
                return fluentModel.JavaClassName;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                // wrapModel method will appear in the operation group impl. The argument (inner) and 
                // return type (Modelimpl) of wrapModel are in the same namespace as operation group
                // impl hence nothing to import.
                //
                return new HashSet<string>();
            }
        }

        public string GeneralizedMethodImpl
        {
            get
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"private {this.WrapperImplName} {this.GeneralizedMethodName}({this.WrappedInnerName} inner) {{");
                methodBuilder.AppendLine($"    return {this.fluentModel.CtrInvocationForWrappingExistingInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        /// <summary>
        /// The wrapModel method implementation.
        /// When applyOverride is true this method provide Implementation for 'ImplT ReadableWrappersImpl::wrapModel(InnerT inner)'.
        /// </summary>
        public string MethodImpl(bool applyOveride)
        {
            if (applyOveride)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"@Override");
                methodBuilder.AppendLine($"protected {this.WrapperImplName} {this.MethodName}({this.WrappedInnerName} inner) {{");
                methodBuilder.AppendLine($"    return {this.fluentModel.CtrInvocationForWrappingExistingInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
            else
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"private {this.WrapperImplName} {MethodName}({this.WrappedInnerName} inner) {{");
                methodBuilder.AppendLine($"    return {this.fluentModel.CtrInvocationForWrappingExistingInnerModel}");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
        }

        public static IEqualityComparer<WrapExistingModelFunc> EqualityComparer()
        {
            return new WrapModelFuncComparer();
        }
        
        private class WrapModelFuncComparer : IEqualityComparer<WrapExistingModelFunc>
        {
            public bool Equals(WrapExistingModelFunc x, WrapExistingModelFunc y)
            {
                return x.WrappedInnerName.Equals(y.WrappedInnerName) && x.WrapperImplName.Equals(y.WrapperImplName);
            }

            public int GetHashCode(WrapExistingModelFunc obj)
            {
                return $"{obj.WrappedInnerName}:{obj.WrapperImplName}".GetHashCode();
            }
        }
    }
}
