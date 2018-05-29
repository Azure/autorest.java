// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Text;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// An implementation of 'IDefineFunc' that represents "define" method in it's normal or generalized form.
    /// "define" method produced by this type is the entry point to fluent defintion of an Azure resource.
    /// </summary>
    public class DefineFunc : IDefineFunc
    {
        /// <summary>
        /// Describes how to create the Azure resource.
        /// </summary>
        private readonly ResourceCreateDescription resourceCreateDescription;

        /// <summary>
        /// Creates "define" method description that starts defintion of an Azure resource whose creation is
        /// described by the given resourceCreateDescription.
        /// </summary>
        /// <param name="resourceCreateDescription">Describes Azure resource creation</param>
        public DefineFunc(ResourceCreateDescription resourceCreateDescription)
        {
            this.resourceCreateDescription = resourceCreateDescription;
        }

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
                    return $"{this.StandardModel.JavaInterfaceName}.DefinitionStages.Blank {MethodName}(String name);";
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
                    return $"{this.StandardModel.JavaInterfaceName}.DefinitionStages.Blank {this.GeneralizedMethodName}(String name);";
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

        /// <summary>
        /// The define method starts definition of an Azure resource, StandardModel describes same resource 
        /// </summary>
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
