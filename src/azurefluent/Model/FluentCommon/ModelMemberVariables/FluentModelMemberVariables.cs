﻿// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Core.Utilities;
using AutoRest.Java.Model;
using Pluralize.NET;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that provide a view of parameters of an API method as model variables. That way when needed this
    /// type can be used to declare those member variables in a model, intialize and access them. 
    /// This is useful for models that need to expand scope of a method parameters in class level in  inorder
    /// to support resource updation, refresh 
    /// </summary>
    public class FluentModelMemberVariables : Dictionary<string, FluentModelMemberVariable>
    {
        /// <summary>
        /// Creates FluentModelMemberVariables.
        /// </summary>
        /// <param name="fluentMethod">The method for which declared memeber variables will be used</param>
        public FluentModelMemberVariables(StandardFluentMethod fluentMethod)
        {
            if (fluentMethod != null)
            {
                this.FluentMethod = fluentMethod;
                this.Pluralizer = new Pluralizer();
                this.Init();
            }
        }

        protected Pluralizer Pluralizer
        {
            get; private set;
        }

        /// <summary>
        /// The fluent method whose parameters used as the source to derive variables.
        /// </summary>
        public StandardFluentMethod FluentMethod { get; private set; }

        public bool IsCompatibleWith(FluentModelMemberVariables other)
        {
            if (other.FluentMethod == null || this.FluentMethod == null)
            {
                return true;
            }
            else
            {
                var thisArmUri = new ARMUri(this.FluentMethod.InnerMethod);
                var otherArmUri = new ARMUri(other.FluentMethod.InnerMethod);
                return thisArmUri.IsSame(otherArmUri);
            }
        }


        /// <summary>
        /// The member variables corrosponding to parent ref parameters and positional parameters.
        /// </summary>
        public IOrderedEnumerable<FluentModelMemberVariable> ParentRefAndPositionalPathMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .Where(v => v is FluentModelParentRefMemberVariable || v is FluentModelPositionalPathMemberVariable)
                    .OrderBy(v => v.Index);
            }
        }

        /// <summary>
        /// The member variables corrosponding to parent ref parameters.
        /// </summary>
        public IOrderedEnumerable<FluentModelParentRefMemberVariable> ParentRefMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .OfType<FluentModelParentRefMemberVariable>()
                    .OrderBy(v => v.Index);
            }
        }

        /// <summary>
        /// The member variables corrosponding to positional path and rest of the member variables.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> PositionalPathAndOtherMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .Except(this.ParentRefMemberVariables);
            }
        }

        /// <summary>
        /// The member variables corrosponding to parameters except path and payload inner parameters.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                            .Except(ParentRefAndPositionalPathMemberVariables)
                            .Where(v => !IsVariableOfTypePayloadInnerModel(v));
            }
        }

        /// <summary>
        /// The member variable corrosponding to positional path parameters and reset of the parameters except payload model.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> PositionalPathAndNotPayloadInnerMemberVariables
        {
            get
            {
                return this.PositionalPathAndOtherMemberVariables
                    .Where(v => !IsVariableOfTypePayloadInnerModel(v));
            }
        }

        /// <summary>
        /// The member variable represening payload parameter if one exists.
        /// </summary>
        public FluentModelMemberVariable PayloadInnerModelVariable
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .FirstOrDefault(v => IsVariableOfTypePayloadInnerModel(v));
            }
        }

        protected virtual bool IsRequiredParamter(ParameterJv parameter)
        {
            return parameter != null
                && !string.IsNullOrWhiteSpace(parameter.Name)
                && parameter.IsRequired;
        }

        /// <summary>
        /// Gets all the parameters of the method including client level params (e.g. subscription).
        /// </summary>
        private IEnumerable<ParameterJv> MethodRequiredParameters
        {
            get
            {
                return this.FluentMethod.InnerMethod
                    .Parameters
                    .OfType<ParameterJv>()
                    .Where(p => IsRequiredParamter(p));
            }
        }

        /// <summary>
        /// Gets all required path parameters including client level params (e.g. subscription).
        /// </summary>
        private IEnumerable<ParameterJv> MethodRequiredPathParameters
        {
            get
            {
                return MethodRequiredParameters
                    .Where(p => p.Location == Core.Model.ParameterLocation.Path);
            }
        }

        /// <summary>
        /// Gets the inner model representing the method payload.
        /// </summary>
        private CompositeTypeJvaf MethodPayloadInnerModel
        {
            get
            {
                if (FluentMethod.InnerMethod.Body is ParameterJv parameter)
                {
                    if (parameter.ClientType is CompositeTypeJvaf compositeType)
                    {
                        return compositeType;
                    }
                    else
                    {
                        throw new InvalidOperationException("Unable to derive the inner model used as payload");
                    }
                }
                else
                {
                    throw new InvalidOperationException("Unable to derive the inner model used as payload");
                }
            }
        }

        private bool IsVariableOfTypePayloadInnerModel(FluentModelMemberVariable variable)
        {
            if (variable.FromParameter.ClientType is CompositeTypeJvaf ctjv)
            {
                if (ctjv.Name.EqualsIgnoreCase(MethodPayloadInnerModel.Name))
                {
                    return true;
                }
            }
            return false;
        }

        /// <summary>
        /// Get position of the parameter in the actual method call.
        /// </summary>
        /// <param name="param">the parameter</param>
        /// <returns></returns>
        private int GetParameterIndex(ParameterJv param)
        {
            int e =  this.FluentMethod
                            .InnerMethod
                            .LocalParameters.Where(p => !p.IsConstant && p.IsRequired)
                            // Ref: MethodJva.MethodRequiredParameterDeclaration ^
                            .Select((p, i) => p.Name.EqualsIgnoreCase(param.Name) ? i + 1 : -1)
                            .FirstOrDefault(i => i > 0);
                return e == 0 ? -1 : e - 1;
        }

        private void Init()
        {
            ARMUri armUri = new ARMUri(this.FluentMethod.InnerMethod);
            //
            foreach (ParameterJv parameter in this.MethodRequiredParameters.Where(p => !p.IsConstant && !p.IsClientProperty))
            {
                FluentModelMemberVariable memberVariable = null;
                if (parameter.Location == ParameterLocation.Path)
                {
                    var parentSegment = armUri.OfType<ParentSegment>()
                        .Where(segment => segment.Parameter.Name.Equals(parameter.Name))
                        .FirstOrDefault();
                    if (parentSegment != null)
                    {
                        memberVariable = new FluentModelParentRefMemberVariable(parentSegment);
                    }
                    else
                    {
                        var positionalSegment = armUri.OfType<PositionalSegment>()
                            .Where(segment => segment.Parameter.Name.Equals(parameter.Name))
                            .FirstOrDefault();
                        if (positionalSegment != null)
                        {
                            memberVariable = new FluentModelPositionalPathMemberVariable(positionalSegment);
                        }
                    }
                    //
                    if (memberVariable == null)
                    {
                        throw new InvalidOperationException($"Unable to locate a parameter segment with name '{parameter.Name}' in the ARM Uri '{this.FluentMethod.InnerMethod.Url}'.");
                    }
                }
                else
                {
                    memberVariable = new FluentModelMemberVariable(variableName: parameter.Name, fromParameter: parameter);
                }
                memberVariable.Index = GetParameterIndex(parameter);
                // 
                //
                this.Add(memberVariable.VariableName, memberVariable);
            }
        }
    }

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
        public int Index { get; set; }

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

    /// <summary>
    /// A member variable representing one of the ancestor of the model.
    /// </summary>
    public class FluentModelParentRefMemberVariable : FluentModelMemberVariable
    {
        public FluentModelParentRefMemberVariable(ParentSegment parentSegment) 
            : base(variableName : parentSegment.Parameter.Name, fromParameter: parentSegment.Parameter)
        {
            this.ParentRefName = parentSegment.Name;
        }

        public string ParentRefName { get; private set; }
    }

    /// <summary>
    /// A member variable representing a positional param in ARM path that identifies model.
    /// </summary>
    public class FluentModelPositionalPathMemberVariable : FluentModelMemberVariable
    {
        public FluentModelPositionalPathMemberVariable(PositionalSegment positionalSegment) 
            : base(variableName: positionalSegment.Parameter.Name, fromParameter: positionalSegment.Parameter)
        {
            this.Position = positionalSegment.Position;
        }

        public int Position { get; private set; }
    }

}
