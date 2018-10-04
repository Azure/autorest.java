// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents "wrapModel" method (that wraps an inner resource) in it's normal or generalized form.
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

        #region Implementation of IWrapNewModelFunc contract.

        public bool IsWrapNewModelSupported
        {
            get
            {
                return this.resourceCreateDescription.SupportsCreating;
            }
        }

        public string MethodName
        {
            get
            {
                return $"wrapModel";
            }
        }

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

        #endregion

        /// <summary>
        /// The wrapModel method wraps an Azure inner resource, StandardModel describes same resource 
        /// </summary>
        private StandardModel StandardModel
        {
            get
            {
                if (this.resourceCreateDescription.FluentMethodGroup.StandardFluentModel == null)
                {
                    throw new InvalidOperationException("StandardModel cannot be null.");
                }
                return this.resourceCreateDescription.FluentMethodGroup.StandardFluentModel;
            }
        }
    }
}
