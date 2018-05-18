// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents 'wrapModel(string name)' function or it's generalized form that wraps 
    /// new standard inner model in it's impl model, such wrapping is done only when a new Azure 
    /// resource representing inner model needs to be created.
    /// </summary>
    public class WrapNewModelFunc : IWrapNewModelFunc
    {
        /// <summary>
        /// Describes resource creation.
        /// </summary>
        private readonly ResourceCreateDescription resourceCreateDescription;

        public WrapNewModelFunc(ResourceCreateDescription resourceCreateDescription)
        {
            this.resourceCreateDescription = resourceCreateDescription;
        }

        /// <summary>
        /// Returns true if 'wrapModel' method can be emitted.
        /// </summary>
        public bool IsWrapNewModelSupported
        {
            get
            {
                return this.resourceCreateDescription.SupportsCreating;
            }
        }

        public string GeneralizedMethodName
        {
            get
            {
                if (this.IsWrapNewModelSupported)
                {
                    // e.g. wrapAuthorizartionRuleModel
                    string resourceName = this.resourceCreateDescription.FluentMethodGroup.LocalSingularNameInPascalCase;
                    return $"wrap{resourceName}Model";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string MethodName
        {
            get
            {
                if (this.IsWrapNewModelSupported)
                {
                    return $"wrapModel";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string GeneralizedMethodImpl
        {
            get
            {
                if (this.IsWrapNewModelSupported)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"private {this.StandardModel.JavaClassName} {this.GeneralizedMethodName}(String name) {{");
                    methodBuilder.AppendLine($"    return {this.StandardModel.CtrInvocationForWrappingNewInnerModel}");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        /// <summary>
        /// Provide implementation of 'ImplT CreatableWrappersImpl::wrapModel(string name)' abstract method
        /// when applyOverride param is true, otherwise provide a private method implementation of wrapModel
        /// if wrapping can be done.
        /// </summary>
        public string MethodImpl(bool applyOverride)
        {
            if (applyOverride)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"protected {this.StandardModel.JavaClassName} {this.MethodName}(String name) {{");
                if (this.IsWrapNewModelSupported)
                {
                    methodBuilder.AppendLine($"    return {this.StandardModel.CtrInvocationForWrappingNewInnerModel}");
                }
                else
                {
                    methodBuilder.AppendLine($"    return null; // Model is not creatable");
                }
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
            else
            {
                if (this.IsWrapNewModelSupported)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"private {this.StandardModel.JavaClassName} {this.MethodName}(String name) {{");

                    methodBuilder.AppendLine($"    return {this.StandardModel.CtrInvocationForWrappingNewInnerModel}");
                    methodBuilder.AppendLine($"}}");
                    //
                    return methodBuilder.ToString();
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        private StandardModel StandardModel
        {
            get
            {
                if (this.resourceCreateDescription.FluentMethodGroup.StandardFluentModel == null)
                {
                    throw new InvalidOperationException("standardModel cannot be null");
                }
                return this.resourceCreateDescription.FluentMethodGroup.StandardFluentModel;
            }
        }
    }
}
