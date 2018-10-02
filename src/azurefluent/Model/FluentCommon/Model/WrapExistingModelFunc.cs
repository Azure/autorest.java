// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that describes definition of 'wrapModel(innerT inner)' method or corrosponding generalized method
    /// in java Fluent Method Group impl class.
    /// </summary>
    public class WrapExistingModelFunc
    {
        /// <summary>
        /// The Fluent Model whose Inner Model that the 'wrapModel' method wraps.
        /// </summary>
        private readonly WrappableFluentModel fluentModel;

        /// <summary>
        /// Creates WrapExistingModelFunc describing 'wrapModel(innerT inner)' method in model impl class.
        /// </summary>
        /// <param name="fluentModel">The Fluent Model whose Inner Model that the 'wrapModel' method wraps.</param>
        public WrapExistingModelFunc(WrappableFluentModel fluentModel)
        {
            this.fluentModel = fluentModel ?? throw new ArgumentNullException(nameof(fluentModel));
        }

        /// <summary>
        /// The Inner Model that 'wrapModel' or corrosponding generalized method wraps.
        /// </summary>
        public string WrappedInnerName
        {
            get
            {
                return fluentModel.RawModelName;
            }
        }

        /// <summary>
        /// The name of Java Model Impl class that wraps the Inner Model.
        /// </summary>
        public string WrapperImplName
        {
            get
            {
                return fluentModel.JavaClassName;
            }
        }

        /// <summary>
        /// The standard name of the wrap method. This is always 'wrapModel'.
        /// </summary>
        public string MethodName
        {
            get
            {
                return $"wrapModel";
            }
        }

        /// <summary>
        /// The generalized name of the wrap model.
        /// </summary>
        public string GeneralizedMethodName
        {
            get
            {
                return $"wrap{this.fluentModel.JavaInterfaceName}Model";
            }
        }

        /// <summary>
        /// The imports to be imported in a java fluent method group impl class which contains definition of 'wrapModel'.
        /// </summary>
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

        /// <summary>
        /// The 'wrapModel' method implementation.
        /// </summary>
        /// <param name="applyOveride">When applyOverride is true this method provide Implementation for 'ImplT ReadableWrappersImpl::wrapModel(InnerT inner)'.</param>
        /// <returns></returns>
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

        /// <summary>
        /// The generalized variant for 'wrapModel' method.
        /// </summary>
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
        /// The equality comparer to compare two instance of WrapExistingModelFunc.
        /// </summary>
        /// <returns></returns>
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
