// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Description of "getInnerAsync" method for retrieving inner resource from a parent scope.
    /// </summary>
    public class GetInnerFromParentAsyncFunc : IGetInnerAsyncFunc
    {
        /// <summary>
        /// Describes how to retrieve the inner standard model.
        /// </summary>
        private readonly ResourceGetDescription resourceGetDescription;

        public GetInnerFromParentAsyncFunc(ResourceGetDescription resourceGetDescription)
        {
            this.resourceGetDescription = resourceGetDescription;
        }

        /// <summary>
        /// Returns true if retrieving inner from a parent resource or scope is supported.
        /// </summary>
        public bool IsGetInnerSupported
        {
            get
            {
                return this.resourceGetDescription.SupportsGetByImmediateParent;
            }
        }

        /// <summary>
        /// Imports needed when using "getInnerAsync" method.
        /// </summary>
        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                if (IsGetInnerSupported)
                {
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

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

        public string MethodInvocationParameter
        {
            get
            {
                return $"inner.id()";
                // will be used like: getInnerAsync(inner.id())
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
                    methodBuilder.AppendLine($"private Observable<{this.InnerModelName}> {this.GeneralizedMethodName}(String id) {{");
                    foreach (string localVariableInit in LocalVariableInitializations)
                    {
                        methodBuilder.AppendLine($"    {localVariableInit}");
                    }
                    methodBuilder.AppendLine($"    {this.InnerClientName} client = this.inner();");
                    methodBuilder.AppendLine($"    return client.{this.resourceGetDescription.GetByImmediateParentMethod.Name}Async({LocalVariablesAsMethodInvokeParameters});");
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
            if (this.IsGetInnerSupported)
            {
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"private Observable<{InnerModelName}> {MethodName}(String id) {{");
                foreach (string localVariableInit in LocalVariableInitializations)
                {
                    methodBuilder.AppendLine($"    {localVariableInit}");
                }
                methodBuilder.AppendLine($"    {InnerClientName} client = this.inner();");
                methodBuilder.AppendLine($"    return client.{this.resourceGetDescription.GetByImmediateParentMethod.Name}Async({LocalVariablesAsMethodInvokeParameters});");
                methodBuilder.AppendLine($"}}");
                //
                return methodBuilder.ToString();
            }
            else
            {
                return string.Empty;
            }
        }

        private IEnumerable<string> LocalVariableInitializations
        {
            get
            {
                if (this.IsGetInnerSupported)
                {
                    var pathParamSegements = this.GetByImmediateParentMethodArmUri.LocalPathParameterSegments.Values;
                    foreach (var segment in pathParamSegements)
                    {
                        if (segment is ParentSegment parentSegment)
                        {
                            yield return $"String {parentSegment.Parameter.SerializedName} = IdParsingUtils.getValueFromIdByName(id, \"{parentSegment.Name}\");";
                        }
                        else if (segment is PositionalSegment positionalSegment)
                        {
                            yield return $"String {positionalSegment.Parameter.SerializedName} = IdParsingUtils.getValueFromIdByPosition(id, {positionalSegment.Position});";
                        }
                        else
                        {
                            throw new InvalidOperationException($"invalid segment with name '{segment.Name}'.");
                        }
                    }
                }
            }
        }

        private string LocalVariablesAsMethodInvokeParameters
        {
            get
            {
                if (this.IsGetInnerSupported)
                {
                    var segments = this.GetByImmediateParentMethodArmUri.LocalPathParameterSegments;
                    //
                    List<int> parameterPositionsInMethod = segments.Keys.ToList();
                    parameterPositionsInMethod.Sort();
                    //
                    List<string> methodArguments = new List<string>();
                    foreach (int position in parameterPositionsInMethod)
                    {
                        if (segments[position] is SegmentParameter segmentParameter)
                        {
                            methodArguments.Add(segmentParameter.Parameter.SerializedName);
                        }
                        else
                        {
                            throw new InvalidOperationException($"invalid segment with name '{segments[position].Name}'.");
                        }
                    }
                    return string.Join(", ", methodArguments);
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        ARMUri armUri;
        private ARMUri GetByImmediateParentMethodArmUri
        {
            get
            {
                if (this.armUri == null && this.IsGetInnerSupported)
                {
                    var method = this.resourceGetDescription.GetByImmediateParentMethod;
                    this. armUri = new ARMUri(method.InnerMethod);
                }
                return this.armUri;
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
