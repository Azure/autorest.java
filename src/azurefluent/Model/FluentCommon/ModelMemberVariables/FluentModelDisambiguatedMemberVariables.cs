// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type to help disambiguate names of member variable in a fluent model impl [e.g. VirtualMachineImpl].
    /// A model impl can contain member variables corrosponds to 'Create', 'Update' and 'Get' methods,
    /// this type is used to ensure those member variable names are not conflicting, by renaming those member
    ///  varaibles upon conflict.
    ///  
    /// Refer:
    ///     FluentModelMemberVariablesForCreate: descrbies memeber variable corrosponds to 'Create' methods
    ///     FluentModelMemberVariablesForUpdate: descrbies memeber variable corrosponds to 'Update' methods
    ///     FluentModelMemberVariablesForGet:    descrbies memeber variable corrosponds to 'Get' methods
    /// </summary>
    public class FluentModelDisambiguatedMemberVariables
    {
        public const string CreateParameterVariableName = "createParameter";
        public const string UpdateParameterVariableName = "updateParameter";
        public const string CreateOrUpdateParameterVariableName = "createOrUpdateParameter";

        private FluentModelMemberVariablesForCreate cVariables;
        private FluentModelMemberVariablesForUpdate uVariables;
        private FluentModelMemberVariablesForGet gVariables;

        private IDictionary<string, FluentModelMemberVariable> memberVariablesForCreate;
        private IDictionary<string, FluentModelMemberVariable> memberVariablesForUpdate;
        private bool disambiguated;

        /// <summary>
        /// Creates FluentModelDisambiguatedMemberVariables.
        /// </summary>
        public FluentModelDisambiguatedMemberVariables()
        {
            // Initialize create, update and get variables to empty memeber variable collection
            // Each of this will be overriden "With[Create|Update|Get]MemberVariable()" methods
            //
            this.cVariables = new FluentModelMemberVariablesForCreate();
            this.uVariables = new FluentModelMemberVariablesForUpdate();
            this.gVariables = new FluentModelMemberVariablesForGet();
            //
            this.memberVariablesForCreate = null;
            this.memberVariablesForUpdate = null;
            this.disambiguated = false;
        }

        /// <summary>
        /// Sets the Create member variables, this needs to happens first.
        /// </summary>
        /// <param name="cVariables">create member variables</param>
        /// <returns>FluentModelDisambiguatedMemberVariables</returns>
        public FluentModelDisambiguatedMemberVariables WithCreateMemberVariable(FluentModelMemberVariablesForCreate cVariables)
        {
            this.cVariables = cVariables;
            return this;
        }

        /// <summary>
        /// Sets the Update member variables, this needs to happens second.
        /// </summary>
        /// <param name="cVariables">update member variables</param>
        /// <returns>FluentModelDisambiguatedMemberVariables</returns>
        public FluentModelDisambiguatedMemberVariables WithUpdateMemberVariable(FluentModelMemberVariablesForUpdate uVariables)
        {
            if (this.cVariables.IsCompatibleWith(uVariables))
            {
                this.uVariables = uVariables;
            }
            return this;
        }

        /// <summary>
        /// Sets the Get member variables, this needs to happens thrid.
        /// </summary>
        /// <param name="cVariables">get member variables</param>
        /// <returns>FluentModelDisambiguatedMemberVariables</returns>
        public FluentModelDisambiguatedMemberVariables WithGetMemberVariable(FluentModelMemberVariablesForGet gVariables)
        {
            if (this.cVariables.IsCompatibleWith(gVariables) && this.uVariables.IsCompatibleWith(gVariables))
            {
                this.gVariables = gVariables;
            }
            return this;
        }

        /// <summary>
        /// Disambiguated member variables corrosponds to 'Create' method parameters.
        /// </summary>
        public IDictionary<string, FluentModelMemberVariable> MemeberVariablesForCreate
        {
            get
            {
                return this.memberVariablesForCreate;
            }
        }

        /// <summary>
        /// Disambiguated member variables corrosponds to 'Update' method parameters.
        /// </summary>
        public IDictionary<string, FluentModelMemberVariable> MemeberVariablesForUpdate
        {
            get
            {
                return this.memberVariablesForUpdate;
            }
        }

        /// <summary>
        /// Disambiguated member variables corrosponds to 'Get' method parameters.
        /// </summary>
        public IEnumerable<FluentModelMemberVariable> MemeberVariablesForGet
        {
            get
            {
                return this.MemberVariables.Where(v => (v is FluentModelParentRefMemberVariable || v is FluentModelPositionalPathMemberVariable));
            }
        }

        private string MethodGroupWrappedModelTypeName
        {
            get
            {
                if (this.cVariables.FluentMethodGroup != null)
                {
                    return this.cVariables.FluentMethodGroup.StandardFluentModel.RawModel.Name;
                }
                else if (this.uVariables.FluentMethodGroup != null)
                {
                    return this.uVariables.FluentMethodGroup.StandardFluentModel.RawModel.Name;
                }
                else if (this.gVariables.FluentMethodGroup != null)
                {
                    return this.gVariables.FluentMethodGroup.StandardFluentModel.RawModel.Name;
                }
                else
                {
                    return null;
                }
            }
        }

        /// <summary>
        /// List of disambiguated member variables.
        /// </summary>
        public IList<FluentModelMemberVariable> MemberVariables
        {
            get; private set;
        }

        /// <summary>
        /// A list string with each line representing declaration of disambiguated member variable.
        /// </summary>
        public IEnumerable<string> DeclareMemberVariables
        {
            get
            {
                return this.MemberVariables
                    .Select(m => m.VariableDeclaration);
            }
        }

        /// <summary>
        /// A list string with each line representing initialization of disambiguated member variable.
        /// </summary>
        public IEnumerable<string> InitMemberVariables
        {
            get
            {
                return this.MemberVariables
                    .Select(m => m.VariableInitialize)
                    .Where(d => !string.IsNullOrEmpty(d));
            }
        }

        /// <summary>
        /// Identity and return name of the member variable that should be holding resource name.
        /// </summary>
        public string MemberVariableAccessorHoldingResourceName
        {
            get
            {
                return this.MemberVariables
                    .OfType<FluentModelParentRefMemberVariable>()
                    .OrderBy(v => v.IndexOfUriSegment)  // Is this the best way to derive resource param?
                    .Select(v => v.VariableAccessor)
                    .Last();
            }
        }

        /// <summary>
        /// A list of strings with each line containing initialization of parent reference or positional
        /// parameter, initialized from the provided id by parsing it.
        /// </summary>
        /// <param name="fromId"></param>
        /// <returns></returns>
        public IEnumerable<string> InitParentRefAndPosMemberVariablesFromId(string fromId)
        {
            var parentVars = this.MemberVariables.OfType<FluentModelParentRefMemberVariable>();
            foreach (var parentVar in parentVars)
            {
                yield return $"{parentVar.VariableAccessor} = {parentVar.ExtractParentRefFrom(fromId)}";
            }

            var posVars = this.MemberVariables.OfType<FluentModelPositionalPathMemberVariable>();
            foreach (var posVar in posVars)
            {
                yield return $"{posVar.VariableAccessor} = {posVar.ExtractPositionParameterFrom(fromId)}";
            }
        }

        /// <summary>
        ///  Disambiguate member variable names by renaming conflicting member variable name.
        /// </summary>
        /// <returns></returns>
        public FluentModelDisambiguatedMemberVariables Disambiguate()
        {
            if (this.disambiguated)
            {
                return this;
            }
            this.disambiguated = true;

            this.memberVariablesForCreate = new Dictionary<string, FluentModelMemberVariable>();
            this.memberVariablesForUpdate = new Dictionary<string, FluentModelMemberVariable>();
            //
            //
            cVariables.ParentRefAndPositionalPathMemberVariables
            .ForEach(cv =>
            {
                this.memberVariablesForCreate.Add(cv.VariableName, cv);
            });

            //
            uVariables.ParentRefAndPositionalPathMemberVariables
            .ForEach(uv =>
            {
                if (!this.memberVariablesForCreate.Any())
                {
                    this.memberVariablesForUpdate.Add(uv.VariableName, uv);
                }
                else
                {
                    if (uv is FluentModelParentRefMemberVariable prv)
                    {
                        var matchedcv = this.memberVariablesForCreate
                            .Values
                            .OfType<FluentModelParentRefMemberVariable>()
                            .First(p => p.ParentRefName.EqualsIgnoreCase(prv.ParentRefName));

                        this.memberVariablesForUpdate.Add(uv.VariableName, matchedcv);
                    }
                    else if (uv is FluentModelPositionalPathMemberVariable ppv)
                    {
                        var pName = ppv.Position;
                        var matchedcv = this.memberVariablesForCreate
                            .Values
                            .OfType<FluentModelPositionalPathMemberVariable>()
                            .First(p => p.Position == ppv.Position);

                        this.memberVariablesForUpdate.Add(uv.VariableName, matchedcv);
                    }
                }
            });

            if (this.memberVariablesForCreate.Any())
            {
                this.MemberVariables = this.memberVariablesForCreate.Values.ToList();
            }
            else if (this.memberVariablesForUpdate.Any())
            {
                this.MemberVariables = this.memberVariablesForUpdate.Values.ToList();
            }
            else
            {
                this.MemberVariables = new List<FluentModelMemberVariable>();
            }

            cVariables.NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables
            .ForEach(cv =>
            {
                string newVariableName = $"c{cv.VariableName}";
                var newVariable = new FluentModelMemberVariable(newVariableName, cv.FromParameter)
                {
                    IndexInMethod = cv.IndexInMethod
                };

                this.memberVariablesForCreate.Add(cv.VariableName, newVariable);
                this.MemberVariables.Add(newVariable);
            });

            uVariables.NotParentRefNotPositionalPathAndNotCompositePayloadMemberVariables
            .ForEach(uv =>
            {
                string newVariableName = $"u{uv.VariableName}";
                var newVariable = new FluentModelMemberVariable(newVariableName, uv.FromParameter)
                {
                    IndexInMethod = uv.IndexInMethod
                };

                this.memberVariablesForUpdate.Add(uv.VariableName, newVariable);
                this.MemberVariables.Add(newVariable);
            });

            var createPayloadVariable = cVariables.CompositePayloadVariable;
            var updatePayloadVariable = uVariables.CompositePayloadVariable;
            if (createPayloadVariable == null)
            {
                if (updatePayloadVariable != null)
                {
                    // createInnerPayload == null && updateInnerPayload != null
                    //

                    string uPayloadTypeName = updatePayloadVariable.VariableTypeName;
                    if (uPayloadTypeName.EqualsIgnoreCase(this.MethodGroupWrappedModelTypeName))
                    {
                        // inner() type and update payload type are same
                        //
                        string newVariableName = $"inner()";
                        var newVariable = new FluentModelMemberVariable(newVariableName, updatePayloadVariable.FromParameter)
                        {
                            IndexInMethod = updatePayloadVariable.IndexInMethod
                        };
                        //
                        this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, newVariable);
                    }
                    else
                    {
                        // inner() type and update payload type are different
                        //
                        string newVariableName = UpdateParameterVariableName;
                        var newVariable = new FluentModelMemberVariable(newVariableName, updatePayloadVariable.FromParameter)
                        {
                            IndexInMethod = updatePayloadVariable.IndexInMethod
                        };
                        //
                        this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, newVariable);
                        //
                        this.MemberVariables.Add(newVariable);
                    }
                }
            }
            else if (updatePayloadVariable == null)
            {
                // createInnerPayload != null
                //
                string cPayloadTypeName = createPayloadVariable.VariableTypeName;
                if (cPayloadTypeName.EqualsIgnoreCase(this.MethodGroupWrappedModelTypeName))
                {
                    // inner() type and create payload type are same
                    string newVariableName = $"inner()";
                    var newVariable = new FluentModelMemberVariable(newVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, newVariable);
                }
                else
                {
                    // inner() type and create payload type are different
                    //
                    string newVariableName = CreateParameterVariableName;
                    var newVariable = new FluentModelMemberVariable(newVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };

                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, newVariable);
                    //
                    this.MemberVariables.Add(newVariable);
                }
            }
            else
            {
                // createInnerPayload != null && updateInnerPayload != null
                //
                string createPayloadTypeName = createPayloadVariable.VariableTypeName;
                string updatePayloadTypeName = updatePayloadVariable.VariableTypeName;

                if (this.MethodGroupWrappedModelTypeName.Equals(createPayloadTypeName) 
                    && this.MethodGroupWrappedModelTypeName.Equals(updatePayloadTypeName))
                {
                    // inner type, create param type & update param type are same
                    //
                    string uNewVariableName = $"inner()";
                    var uNewVariable = new FluentModelMemberVariable(uNewVariableName, updatePayloadVariable.FromParameter)
                    {
                        IndexInMethod = updatePayloadVariable.IndexInMethod
                    };

                    //
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);

                    string cnewVariableName = $"inner()";
                    var cNewVariable = new FluentModelMemberVariable(cnewVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };

                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);
                }
                else if (this.MethodGroupWrappedModelTypeName.Equals(createPayloadTypeName))
                {
                    // inner type & create param type are same but update param type is different
                    //
                    string cnewVariableName = $"inner()";
                    var cNewVariable = new FluentModelMemberVariable(cnewVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };

                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);

                    string uNewVariableName = UpdateParameterVariableName;
                    var uNewVariable = new FluentModelMemberVariable(uNewVariableName, updatePayloadVariable.FromParameter)
                    {
                        IndexInMethod = updatePayloadVariable.IndexInMethod
                    };

                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);
                    //
                    this.MemberVariables.Add(uNewVariable);
                }
                else if (this.MethodGroupWrappedModelTypeName.Equals(updatePayloadTypeName))
                {
                    // inner type & update param type are same but create param type is different
                    //
                    string unewVariableName = $"inner()";
                    var uNewVariable = new FluentModelMemberVariable(unewVariableName, updatePayloadVariable.FromParameter)
                    {
                        IndexInMethod = updatePayloadVariable.IndexInMethod
                    };

                    //
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);

                    string cNewVariableName = CreateParameterVariableName;
                    var cNewVariable = new FluentModelMemberVariable(cNewVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };

                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);
                    //
                    this.MemberVariables.Add(cNewVariable);

                }
                else if (createPayloadTypeName.Equals(updatePayloadTypeName))
                {
                    // create type & update param type are same
                    //
                    string newVariableName = CreateOrUpdateParameterVariableName;
                    var cNewVariable = new FluentModelMemberVariable(newVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);

                    var uNewVariable = new FluentModelMemberVariable(newVariableName, updatePayloadVariable.FromParameter)
                    {
                        IndexInMethod = updatePayloadVariable.IndexInMethod
                    };
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);
                    //
                    this.MemberVariables.Add(cNewVariable);
                }
                else
                {
                    string cNewVariableName = CreateParameterVariableName;
                    var cNewVariable = new FluentModelMemberVariable(cNewVariableName, createPayloadVariable.FromParameter)
                    {
                        IndexInMethod = createPayloadVariable.IndexInMethod
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);
                    // 
                    this.MemberVariables.Add(cNewVariable);


                    string uNewVariableName = UpdateParameterVariableName;
                    var uNewVariable = new FluentModelMemberVariable(uNewVariableName, updatePayloadVariable.FromParameter)
                    {
                        IndexInMethod = updatePayloadVariable.IndexInMethod
                    };

                    //
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);
                    // 
                    this.MemberVariables.Add(uNewVariable);
                }
            }
            //
            if (!this.MemberVariables.Any())
            {
                gVariables.ParentRefAndPositionalPathMemberVariables
                    .ForEach(gv =>
                    {
                        this.MemberVariables.Add(gv);
                    });
            }
            return this;
        }
    }
}
