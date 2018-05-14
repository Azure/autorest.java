// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents "getInnerAsync" function or it's generalized form that retrieves a
    /// standard inner resource using apiCall.
    /// </summary>
    public class GetInnerAsyncFunc
    {
        /// <summary>
        /// Describes how to retrieve the inner standard model.
        /// </summary>
        private readonly ResourceGetDescription resourceGetDescription;

        public GetInnerAsyncFunc(ResourceGetDescription resourceGetDescription)
        {
            this.resourceGetDescription = resourceGetDescription;
        }

        /// <summary>
        /// Returns true if retrieving inner is supported.
        /// </summary>
        public bool IsGetInnerSupported
        {
            get
            {
                return this.resourceGetDescription.SupportsGetByResourceGroup;
            }
        }

        public string GeneralizedMethodName
        {
            get
            {
                if (this.IsGetInnerSupported)
                {
                    // e.g. getVirtualMachineInnerUsingVirtualMachinesInnerAsync
                    return $"get{this.InnerModelName}Using{this.InnerClientName}Async";
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
                if (this.IsGetInnerSupported)
                {
                    return $"getInnerAsync";
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
                if (this.IsGetInnerSupported)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"private Observable<{this.InnerModelName}> {this.GeneralizedMethodName}(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    {this.InnerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{this.resourceGetDescription.GetByResourceGroupMethod.Name}Async(resourceGroupName, name);");
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

        public string MethodImpl(bool applyOverride)
        {
            if (applyOverride)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine("@Override");
                methodBuilder.AppendLine($"protected Observable<{this.InnerModelName}> {this.MethodName}(String resourceGroupName, String name) {{");
                if (this.IsGetInnerSupported)
                {
                    methodBuilder.AppendLine($"    {InnerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{this.resourceGetDescription.GetByResourceGroupMethod.Name}Async(resourceGroupName, name);");
                }
                else
                {
                    methodBuilder.AppendLine($"    return null; // NOP Retrieve by resource group not supported");
                }
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
            else
            {
                if (this.IsGetInnerSupported)
                {
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine($"private Observable<{InnerModelName}> {MethodName}(String resourceGroupName, String name) {{");
                    methodBuilder.AppendLine($"    {InnerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{this.resourceGetDescription.GetByResourceGroupMethod.Name}Async(resourceGroupName, name);");
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

        private string InnerClientName
        {
            get
            {
                return this.resourceGetDescription.FluentMethodGroup.InnerMethodGroupTypeName;
            }
        }

        private string InnerModelName
        {
            get
            {
                return StandardModel.InnerModelName;
            }
        }

        private StandardModel StandardModel
        {
            get
            {
                if (this.resourceGetDescription.FluentMethodGroup.StandardFluentModel == null)
                {
                    throw new InvalidOperationException("standardModel cannot be null");
                }
                return this.resourceGetDescription.FluentMethodGroup.StandardFluentModel;
            }
        }

        public static IEqualityComparer<GetInnerAsyncFunc> EqualityComparer()
        {
            return new InnerGetFuncComparer();
        }

        private class InnerGetFuncComparer : IEqualityComparer<GetInnerAsyncFunc>
        {
            public bool Equals(GetInnerAsyncFunc x, GetInnerAsyncFunc y)
            {
                return x.GeneralizedMethodName.Equals(y.GeneralizedMethodName);

            }

            public int GetHashCode(GetInnerAsyncFunc obj)
            {
                return $"{obj.GeneralizedMethodName}".GetHashCode();
            }
        }
    }
}
