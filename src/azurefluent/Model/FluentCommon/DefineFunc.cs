// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that represents "define" function or it's generalized form that starts
    /// fluent defintion of a resource.
    /// </summary>
    public class DefineFunc
    {
        /// <summary>
        /// Describes how to create the resource.
        /// </summary>
        private readonly ResourceCreateDescription resourceCreateDescription;

        public DefineFunc(ResourceCreateDescription resourceCreateDescription)
        {
            this.resourceCreateDescription = resourceCreateDescription;
        }

        /// <summary>
        /// Returns true if define is supported.
        /// </summary>
        public bool IsDefineSupported
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
                if (this.IsDefineSupported)
                {
                    return "define";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string MethodDecl
        {
            get
            {
                if (this.IsDefineSupported)
                {
                    // VirtualMachines.DefinitionStages.Blank define(sting name)
                    //
                    return $"{this.StandardModel.JavaInterfaceName}.DefinitionStages.Blank {MethodName}(string name);";
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
                if (this.IsDefineSupported)
                {
                    // defineExtension
                    //
                    string resourceName = this.resourceCreateDescription.FluentMethodGroup.LocalSingularNameInPascalCase;
                    return $"define{resourceName}";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string GeneralizedMethodDecl
        {
            get
            {
                if (this.IsDefineSupported)
                {
                    // Extensions.DefinitionStages.Blank defineExtension(sting name)
                    //
                    return $"{this.StandardModel.JavaInterfaceName}.DefinitionStages.Blank {this.GeneralizedMethodName}(string name);";
                }
                else
                {
                    return string.Empty;
                }
            }
        }

        public string MethodImpl
        {
            get
            {
                if (this.IsDefineSupported)
                {
                    string wrapMethodName = this.resourceCreateDescription.WrapNewModelFunc.MethodName;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.StandardModel.JavaClassName} {this.MethodName}(String name) {{");
                    methodBuilder.AppendLine($"    return {wrapMethodName}(name);");
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

        public string GeneralizedMethodImpl
        {
            get
            {
                if (this.IsDefineSupported)
                {
                    string wrapMethodName = this.resourceCreateDescription.WrapNewModelFunc.GeneralizedMethodName;
                    //
                    StringBuilder methodBuilder = new StringBuilder();
                    //
                    methodBuilder.AppendLine("@Override");
                    methodBuilder.AppendLine($"public {this.StandardModel.JavaClassName} {this.GeneralizedMethodName}(String name) {{");
                    methodBuilder.AppendLine($"    return {wrapMethodName}(name);");
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
