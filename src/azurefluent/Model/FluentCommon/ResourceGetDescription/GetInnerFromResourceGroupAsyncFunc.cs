// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a method in fluent method group (fluent collection) impl for retrieving inner resource from a resource group scope.
    /// 
    /// The "Standard Name" of such a method in fluent method group (fluent collection) impl is "getInnerAsync".
    /// The "Generalized Name" of such a method in fluent method group (fluent collection) impl is derived from name of the inner model and client.
    /// </summary>
    public class GetInnerFromResourceGroupAsyncFunc : IGetInnerAsyncFunc
    {
        /// <summary>
        /// Describes the inner method used by "getInnerAsync" method or corrosponding "generalized" method
        /// to retrieve the inner resource.
        /// </summary>
        private readonly ResourceGetDescription resourceGetDescription;

        /// <summary>
        /// Creates GetInnerFromResourceGroupAsyncFunc.
        /// </summary>
        /// <param name="resourceGetDescription">Description of inner method that "getInnerAsync" or corrosponding "generalized" method uses</param>
        public GetInnerFromResourceGroupAsyncFunc(ResourceGetDescription resourceGetDescription)
        {
            this.resourceGetDescription = resourceGetDescription;
        }

        /// <summary>
        /// Returns true if retrieving inner resource from a resource group is supported.
        /// </summary>
        public bool IsGetInnerSupported
        {
            get
            {
                return this.resourceGetDescription.SupportsGetByResourceGroup;
            }
        }

        /// <summary>
        /// Gets the imports to be imported by a fluent method group (fluent collection) impl that contains definition of
        /// 'getInnerAsync' method or corrosponding "generalized" method.
        /// </summary>
        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (IsGetInnerSupported)
                {
                    imports.Add("com.microsoft.azure.arm.resources.ResourceUtilsCore");
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

        /// <summary>
        /// Gets the "generalized name" of method that retrieves the inner resource from a resource group.
        /// </summary>
        public string GeneralizedMethodName
        {
            get
            {
                if (this.IsGetInnerSupported)
                {
                    // e.g. getVirtualMachineInnerUsingVirtualMachinesInnerAsync
                    //
                    return $"get{this.InnerModelName}Using{this.InnerClientName}Async";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        /// <summary>
        /// Gets the "standard name" of method that retrieves the inner resource.
        /// This is always 'getInnerAsync'.
        /// </summary>
        public string MethodName
        {
            get
            {
                // We will not return empty string from here, because when we 'override'
                // getInnerAsync from base class we need the name, irrespective of service
                // support retrieving the resource or not. See "MethodImpl(bool applyOverride)"
                //
                return $"getInnerAsync";
            }
        }

        /// <summary>
        /// The parameter to be passed when invoking 'getInnerAsync' method or corrosponding "generalized" method.
        /// </summary>
        public string MethodInvocationParameter
        {
            get
            {
                return $"ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id())";
                // will be used as: 
                //      getInnerAsync(ResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id())) OR
                //      getVirtualMachineInnerUsingVirtualMachinesInnerAsyncResourceUtilsCore.groupFromResourceId(inner.id()), ResourceUtilsCore.nameFromResourceId(inner.id()))
            }
        }

        /// <summary>
        /// Gets inner resource retrieval method implementation in it's generalized form.
        /// </summary>
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

        /// <summary>
        /// Gets inner resource retrieval method implementation in it's standard form.
        /// </summary>
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

        /// <summary>
        /// The name of the inner collection name exposing inner method to retrive the resource.
        /// </summary>
        private string InnerClientName
        {
            get
            {
                return this.resourceGetDescription.FluentMethodGroup.InnerMethodGroupTypeName;
            }
        }

        /// <summary>
        /// Gets the name of the inner model (i.e. inner resource) returned by inner method.
        /// </summary>
        private string InnerModelName
        {
            get
            {
                return StandardModel.RawModelName;
            }
        }

        /// <summary>
        /// Gets the standard model wrapping the inner model (i.e. inner resource) returned by inner method.
        /// </summary>
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

        /// <summary>
        /// Comparer to compare two instances of GetInnerFromResourceGroupAsyncFunc type.
        /// </summary>
        /// <returns>the comparer</returns>
        public static IEqualityComparer<IGetInnerAsyncFunc> EqualityComparer()
        {
            return new InnerGetFuncComparer();
        }

        private class InnerGetFuncComparer : IEqualityComparer<IGetInnerAsyncFunc>
        {
            public bool Equals(IGetInnerAsyncFunc x, IGetInnerAsyncFunc y)
            {
                return x.GeneralizedMethodName.Equals(y.GeneralizedMethodName);

            }

            public int GetHashCode(IGetInnerAsyncFunc obj)
            {
                return $"{obj.GeneralizedMethodName}".GetHashCode();
            }
        }
    }
}
