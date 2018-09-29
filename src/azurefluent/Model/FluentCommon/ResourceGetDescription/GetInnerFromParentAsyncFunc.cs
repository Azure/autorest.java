// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type describing a private method in fluent method group (fluent collection) impl for retrieving inner resource
    /// from a parent scope.
    /// 
    /// The "Standard Name" of such a private method in fluent method group (fluent collection) impl is "getInnerAsync".
    /// The "Generalized Name" of such a private method in fluent method group (fluent collection) impl is derived from name of the inner model and client.
    /// </summary>
    public class GetInnerFromParentAsyncFunc : IGetInnerAsyncFunc
    {
        /// <summary>
        /// Describes the inner method used by "getInnerAsync" method or corrosponding "generalized" method
        /// to retrieve the inner resource.
        /// </summary>
        private readonly ResourceGetDescription resourceGetDescription;
        private const string idParamName = "id";

        /// <summary>
        /// Creates GetInnerFromParentAsyncFunc.
        /// </summary>
        /// <param name="resourceGetDescription">Description of inner method that "getInnerAsync" or corrosponding "generalized" method uses</param>
        public GetInnerFromParentAsyncFunc(ResourceGetDescription resourceGetDescription)
        {
            this.resourceGetDescription = resourceGetDescription;
        }

        /// <summary>
        /// Returns true if retrieving inner resource from a parent resource or scope is supported.
        /// </summary>
        public bool IsGetInnerSupported
        {
            get
            {
                return this.resourceGetDescription.SupportsGetByImmediateParent;
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
                    imports.Add("rx.Observable");
                }
                return imports;
            }
        }

        /// <summary>
        /// Gets the "generalized name" of method that retrieves the inner resource.
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

        /// <summary>
        /// The parameter to be passed when invoking 'getInnerAsync' method or corrosponding "generalized" method.
        /// </summary>
        public string MethodInvocationParameter
        {
            get
            {
                return $"inner.id()";
                // will be used as: 
                //      getInnerAsync(inner.id()) OR
                //      getVirtualMachineInnerUsingVirtualMachinesInnerAsync(inner.id())
            }
        }

        /// <summary>
        /// Gets inner resource retrieval method implementation in it's generalized form.
        /// </summary>
        public string GeneralizedMethodImpl
        {
            get
            {
                return this.MethodImplIntern(generalized: true);
            }
        }

        /// <summary>
        /// Gets inner resource retrieval method implementation in it's standard form.
        /// </summary>
        public string MethodImpl(bool applyOverride)
        {
            // param 'applyOverride' is ignored: scope of getInnerAsync is always private.
            return this.MethodImplIntern(generalized: false);
        }

        private string MethodImplIntern(bool generalized)
        {
            if (this.IsGetInnerSupported)
            {
                string getInnerMethodName = generalized ? this.GeneralizedMethodName : this.MethodName;
                //
                StringBuilder methodBuilder = new StringBuilder();
                //
                methodBuilder.AppendLine($"private Observable<{this.InnerModelName}> {getInnerMethodName}(String {idParamName}) {{");
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

        /// <summary>
        /// List with each entry representing declaration and initialization of local variables in 'getInnerAsync' method or corrosponding "generalized" method impl.
        /// These variables are used as parameters to inner method to retrieve the resource.
        /// </summary>
        private IEnumerable<string> LocalVariableInitializations
        {
            get
            {
                if (this.IsGetInnerSupported)
                {
                    var pathParamSegements = this.GetByImmediateParentMethodArmUri.LocalPathParameterSegments.Values;
                    foreach (var segmentParameter in pathParamSegements.OfType<ParameterSegment>())
                    {
                        var varName = segmentParameter.Parameter.SerializedName;
                        if (varName == idParamName)
                        {
                            // 'GetInnerAsync(String id) has parameter with name id, hence rename any local variable with conflicting name.
                            //
                            varName = $"{idParamName}Parameter";
                        }
                        //
                        string toStringTemplate = Utils.ToStringTemplateForType(segmentParameter.Parameter.ClientType);
                        if (segmentParameter is ParentSegment parentSegment)
                        {
                            yield return string.Format($"String {varName} = {toStringTemplate};", $"IdParsingUtils.getValueFromIdByName({idParamName}, \"{parentSegment.Name}\")");
                        }
                        else if (segmentParameter is PositionalSegment positionalSegment)
                        {
                            yield return string.Format($"String {varName} = {toStringTemplate};", $"IdParsingUtils.getValueFromIdByPosition({idParamName}, {positionalSegment.Position})");
                        }
                        else
                        {
                            throw new InvalidOperationException($"invalid segment with name '{segmentParameter.Name}' (not ParentSegment or PositionalSegment but {segmentParameter.GetType()}).");
                        }
                    }
                }
            }
        }

        /// <summary>
        /// Gets the comma seperated list of local variables in 'getInnerAsync' method or corrosponding "generalized" method impl.
        /// These variables are used as parameters to inner method to retrieve the resource.
        /// </summary>
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
                        if (segments[position] is ParameterSegment segmentParameter)
                        {
                            methodArguments.Add(segmentParameter.Parameter.SerializedName);
                        }
                        else
                        {
                            throw new InvalidOperationException($"Invalid segment with name '{segments[position].Name}'.");
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

        /// <summary>
        /// The ARMUri of inner method to retrieve the inner resource.
        /// </summary>
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
                    throw new InvalidOperationException("StandardModel cannot be null.");
                }
                return this.resourceGetDescription.FluentMethodGroup.StandardFluentModel;
            }
        }

        /// <summary>
        /// Comparer to compare two instances of GetInnerFromParentAsyncFunc type.
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
