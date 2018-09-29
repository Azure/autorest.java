// Copyright (c) Microsoft Corporation. All rights reserved.
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
    /// Type that provide a view of parameters of an API method as model/class variables. That way when needed
    /// this type can be used to declare those member variables in a class, intialize and access them. 
    /// This is useful for models that need to expand scope of a method parameters in class level in inorder
    /// to support resource updation, refresh 
    /// </summary>
    public class FluentModelMemberVariables : Dictionary<string, FluentModelMemberVariable>
    {
        /// <summary>
        /// Creates FluentModelMemberVariables.
        /// </summary>
        /// <param name="fluentMethod">The method for which memeber variables will be used as parameters</param>
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
        /// The member variable corrosponding to composite payload parameter if one exists.
        /// </summary>
        public FluentModelMemberVariable CompositePayloadVariable
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .FirstOrDefault(v => IsVariableCorrospondsToPayloadParameterAndIsComposite(v));
            }
        }

        /// <summary>
        /// The member variables corrosponding to path parameters that refer ancestors (parent, 
        /// grand-parent, great-grand-parent etc..).
        /// </summary>
        public IOrderedEnumerable<FluentModelParentRefMemberVariable> ParentRefMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .OfType<FluentModelParentRefMemberVariable>()
                    .OrderBy(v => v.IndexInMethod);
            }
        }


        /// <summary>
        /// The member variables corrosponding path parameters those refer ancestors (parent, grand-parent, 
        /// great-grand-parent etc..) and positional path parameters.
        /// </summary>
        public IOrderedEnumerable<FluentModelMemberVariable> ParentRefAndPositionalPathMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .Where(v => v is FluentModelParentRefMemberVariable || v is FluentModelPositionalPathMemberVariable)
                    .OrderBy(v => v.IndexInMethod);
            }
        }

        /// <summary>
        /// The member variables corrosponding to positional path parameter and rest of the member variables.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> NotParentRefButPositionalPathAndOtherMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                    .Except(this.ParentRefMemberVariables);
            }
        }

        /// <summary>
        /// The member variables corrosponding to parameters except path (positional + parent ref) parameters
        /// and composite payload parameter.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables
        {
            get
            {
                IEnumerable<FluentModelMemberVariable> memberVariables = this.Values;
                return memberVariables
                            .Except(ParentRefAndPositionalPathMemberVariables)
                            .Where(v => !IsVariableCorrospondsToPayloadParameterAndIsComposite(v));
            }
        }

        /// <summary>
        /// The member variable corrosponding to positional path parameters and rest of the parameters 
        /// except composite payload model.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> NotParentRefNotCompositePayloadButPositionalAndOtherMemberVariables
        {
            get
            {
                return this.NotParentRefButPositionalPathAndOtherMemberVariables
                    .Where(v => !IsVariableCorrospondsToPayloadParameterAndIsComposite(v));
            }
        }

        protected virtual bool IsRequiredParamter(ParameterJv parameter)
        {
            return parameter != null
                && !string.IsNullOrWhiteSpace(parameter.Name)
                && parameter.IsRequired;
        }

        /// <summary>
        /// Returns the type of the method payload if there is one, null otherwise.
        /// </summary>
        private IModelTypeJv MethodPayloadType
        {
            get
            {
                if (FluentMethod.InnerMethod.Body is ParameterJv parameter)
                {
                    return parameter.ClientType;
                }
                else
                {
                    return null;
                }
            }
        }

        /// <summary>
        /// Return True if the given variable is corrosponding to method payload parameter and is composite.
        /// </summary>
        /// <param name="variable">the variable to check</param>
        /// <returns></returns>
        private bool IsVariableCorrospondsToPayloadParameterAndIsComposite(FluentModelMemberVariable variable)
        {
            if (variable.VariableType is CompositeTypeJvaf vt && MethodPayloadType is CompositeTypeJvaf pt)
            {
                return vt.Name.EqualsIgnoreCase(pt.Name);
            }
            else
            {
                return false;
            }
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
        /// Gets all the parameters of the method including client level params (e.g. subscription).
        /// </summary>
        private IEnumerable<ParameterJv> MethodParameters
        {
            get
            {
                return this.FluentMethod.InnerMethod
                    .Parameters
                    .OfType<ParameterJv>();
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
        /// Get position of the parameter in the actual method call.
        /// </summary>
        /// <param name="param">the parameter</param>
        /// <returns></returns>
        private int GetParameterIndex(ParameterJv param)
        {
            int e =  this.FluentMethod
                            .InnerMethod
                            .LocalParameters.Where(p => !p.IsConstant)
                            // Ref: MethodJva.MethodRequiredParameterDeclaration ^
                            .Select((p, i) => p.Name.EqualsIgnoreCase(param.Name) ? i + 1 : -1)
                            .FirstOrDefault(i => i > 0);
                return e == 0 ? -1 : e - 1;
        }

        /// <summary>
        /// Populate this dictionary. An entry in the dictionary describes an Impl class's member variables 
        /// corrosponding to eligible method parameters  A parameter is eligible if it is not a constant
        /// or client level property.
        /// </summary>
        private void Init()
        {
            ARMUri armUri = new ARMUri(this.FluentMethod.InnerMethod);
            //
            // Map all parameters of the methods to memeber variables except constant parameters and client level parameters.
            //
            foreach (ParameterJv parameter in this.MethodParameters.Where(p => !p.IsConstant && !p.IsClientProperty))
            {
                FluentModelMemberVariable memberVariable = null;
                if (parameter.Location == ParameterLocation.Path)
                {
                    // Path parameters can be "named" or "positional" 
                    // "Named path parameters"      : Path parameters those preceded by parent name.
                    //             E.g. in the URI. '/../VirtualMachines/{vmName}', the parameter "vmName" is preceded by parent name "VirtualMachines".
                    // "Positional path parameters" : Path parameters not preceeded by parent name.
                    //             E.g. '/../VirtualMachines/{vmName}/{intanceId}'the parameter "instanceId" is a positional parameter.
                    //
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
                    // if a parameter is not a path param then it's either 
                    // Payload parameter (payload of PUT/POST etc..)
                    // OR Required Query parameters
                    //
                    memberVariable = new FluentModelMemberVariable(variableName: parameter.Name, fromParameter: parameter);
                }
                memberVariable.IndexInMethod = GetParameterIndex(parameter);
                // 
                //
                this.Add(memberVariable.VariableName, memberVariable);
            }
        }
    }
}
