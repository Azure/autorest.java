// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Base type for a member variable in fluent model impl (e.g. VirtualMachineImpl).
    /// </summary>
    public class FluentModelMemberVariable
    {
        /// <summary>
        /// Creates FluentModelMemberVariable.
        /// </summary>
        /// <param name="variableName">The variable name</param>
        /// <param name="fromParameter">The method parameter for which the variable is needed</param>
        public FluentModelMemberVariable(string variableName, ParameterJv fromParameter)
        {
            this.VariableName = variableName;
            this.FromParameter = fromParameter;
            this.VariableType = fromParameter.ClientType;
        }

        /// <summary>
        /// Type of the variable.
        /// </summary>
        public IModelTypeJv VariableType { get; }
        /// <summary>
        /// The Parameter of the method from which the variable is created.
        /// </summary>
        public ParameterJv FromParameter { get; }
        /// <summary>
        /// The name for the variable.
        /// </summary>
        public string VariableName { get; }
        /// <summary>
        /// Index of the method parameter from which variable is created
        /// </summary>
        public int IndexInMethod { get; set; }

        /// <summary>
        /// The line representing the declaration of the variable in the model.
        /// </summary>
        public string VariableDeclaration
        {
            get
            {
                if (this.VariableName.EqualsIgnoreCase("inner()"))
                {
                    return null;
                }
                else
                {
                    return $"private {this.VariableTypeName} {this.VariableName};";
                }
            }
        }
        /// <summary>
        /// The line reresenting the intialization of the variable.
        /// </summary>
        public string VariableInitialize
        {
            get
            {
                if (this.VariableName.EqualsIgnoreCase("inner()"))
                {
                    return null;
                }
                else
                {
                    if (this.VariableType is CompositeType)
                    {
                        return $"{this.VariableAccessor} = new {this.VariableType.Name}();";
                    }
                    else
                    {
                        return null;
                    }
                }
            }
        }
        /// <summary>
        /// The string representation of the variable accessor.
        /// </summary>
        public string VariableAccessor
        {
            get
            {
                return $"this.{this.VariableName}";
            }
        }

        /// <summary>
        /// The type of the variable.
        /// </summary>
        public string VariableTypeName
        {
            get
            {
                return this.FromParameter.ModelTypeName;
            }
        }
    }
}
