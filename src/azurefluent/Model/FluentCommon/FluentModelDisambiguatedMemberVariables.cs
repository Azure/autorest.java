// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Utilities;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentModelDisambiguatedMemberVariables
    {
        private FluentModelMemberVariablesForCreate cVariables;
        private FluentModelMemberVariablesForUpdate uVariables;
        private FluentModelMemberVariablesForGet gVariables;

        private IDictionary<string, FluentModelMemberVariable> memberVariablesForCreate;
        private IDictionary<string, FluentModelMemberVariable> memberVariablesForUpdate;
        private bool disambiguated;

        public FluentModelDisambiguatedMemberVariables()
        {
            // Initialize create, update and get variables to empty memeber variable collection
            // Each of this will be overriden "With[Create|Update|Get]MemberVariable()" methods
            //
            this.cVariables = new FluentModelMemberVariablesForCreate();
            this.uVariables = new FluentModelMemberVariablesForUpdate();
            this.gVariables = new FluentModelMemberVariablesForGet();

            this.memberVariablesForCreate = null;
            this.memberVariablesForUpdate = null;
            this.disambiguated = false;
        }

        /// <summary>
        /// Sets the create memebr variables, this needs to happens first
        /// </summary>
        /// <param name="cVariables">create member variables</param>
        /// <returns>FluentModelDisambiguatedMemberVariables</returns>
        public FluentModelDisambiguatedMemberVariables WithCreateMemberVariable(FluentModelMemberVariablesForCreate cVariables)
        {
            this.cVariables = cVariables;
            return this;
        }

        /// <summary>
        /// Sets the update memebr variables, this needs to happens second
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
        /// Sets the get memebr variables, this needs to happens thrid
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

        public IDictionary<string, FluentModelMemberVariable> MemeberVariablesForCreate
        {
            get
            {
                return this.memberVariablesForCreate;
            }
        }

        public IDictionary<string, FluentModelMemberVariable> MemeberVariablesForUpdate
        {
            get
            {
                return this.memberVariablesForUpdate;
            }
        }

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
                    return this.cVariables.FluentMethodGroup.StandardFluentModel.InnerModel.Name;
                }
                else if (this.uVariables.FluentMethodGroup != null)
                {
                    return this.uVariables.FluentMethodGroup.StandardFluentModel.InnerModel.Name;
                }
                else if (this.gVariables.FluentMethodGroup != null)
                {
                    return this.gVariables.FluentMethodGroup.StandardFluentModel.InnerModel.Name;
                }
                else
                {
                    return null;
                }
            }
        }

        public IList<FluentModelMemberVariable> MemberVariables
        {
            get; private set;
        }

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

            cVariables.NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables
            .ForEach(cv =>
            {
                string newVariableName = $"c{cv.VariableName}";
                var newVariable = new FluentModelMemberVariable(newVariableName, cv.FromParameter)
                {
                    Index = cv.Index
                };

                this.memberVariablesForCreate.Add(cv.VariableName, newVariable);
                this.MemberVariables.Add(newVariable);
            });

            uVariables.NotParentRefNotPositionalPathAndNotPayloadInnerMemberVariables
            .ForEach(uv =>
            {
                string newVariableName = $"u{uv.VariableName}";
                var newVariable = new FluentModelMemberVariable(newVariableName, uv.FromParameter)
                {
                    Index = uv.Index
                };

                this.memberVariablesForUpdate.Add(uv.VariableName, newVariable);
                this.MemberVariables.Add(newVariable);
            });

            var createPayloadVariable = cVariables.PayloadInnerModelVariable;
            var updatePayloadVariable = uVariables.PayloadInnerModelVariable;
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
                            Index = updatePayloadVariable.Index
                        };
                        //
                        this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, newVariable);
                    }
                    else
                    {
                        // inner() type and update payload type are different
                        //
                        string newVariableName = $"updateParameter";
                        var newVariable = new FluentModelMemberVariable(newVariableName, updatePayloadVariable.FromParameter)
                        {
                            Index = updatePayloadVariable.Index
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
                        Index = createPayloadVariable.Index
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, newVariable);
                }
                else
                {
                    // inner() type and create payload type are different
                    //
                    string newVariableName = $"createParameter";
                    var newVariable = new FluentModelMemberVariable(newVariableName, createPayloadVariable.FromParameter)
                    {
                        Index = createPayloadVariable.Index
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
                        Index = updatePayloadVariable.Index
                    };

                    //
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);

                    string cnewVariableName = $"inner()";
                    var cNewVariable = new FluentModelMemberVariable(cnewVariableName, createPayloadVariable.FromParameter)
                    {
                        Index = createPayloadVariable.Index
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
                        Index = createPayloadVariable.Index
                    };

                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);

                    string uNewVariableName = $"updateParameter";
                    var uNewVariable = new FluentModelMemberVariable(uNewVariableName, updatePayloadVariable.FromParameter)
                    {
                        Index = updatePayloadVariable.Index
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
                        Index = updatePayloadVariable.Index
                    };

                    //
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);

                    string cNewVariableName = $"createParameter";
                    var cNewVariable = new FluentModelMemberVariable(cNewVariableName, createPayloadVariable.FromParameter)
                    {
                        Index = createPayloadVariable.Index
                    };

                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);
                    //
                    this.MemberVariables.Add(cNewVariable);

                }
                else if (createPayloadTypeName.Equals(updatePayloadTypeName))
                {
                    // create type & update param type are same
                    //
                    string newVariableName = $"createOrUpdateParameter";
                    var cNewVariable = new FluentModelMemberVariable(newVariableName, createPayloadVariable.FromParameter)
                    {
                        Index = createPayloadVariable.Index
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);

                    var uNewVariable = new FluentModelMemberVariable(newVariableName, updatePayloadVariable.FromParameter)
                    {
                        Index = updatePayloadVariable.Index
                    };
                    this.memberVariablesForUpdate.Add(updatePayloadVariable.VariableName, uNewVariable);
                    //
                    this.MemberVariables.Add(cNewVariable);
                }
                else
                {
                    string cNewVariableName = $"createParameter";
                    var cNewVariable = new FluentModelMemberVariable(cNewVariableName, createPayloadVariable.FromParameter)
                    {
                        Index = createPayloadVariable.Index
                    };
                    //
                    this.memberVariablesForCreate.Add(createPayloadVariable.VariableName, cNewVariable);
                    // 
                    this.MemberVariables.Add(cNewVariable);


                    string uNewVariableName = $"updateParameter";
                    var uNewVariable = new FluentModelMemberVariable(uNewVariableName, updatePayloadVariable.FromParameter)
                    {
                        Index = updatePayloadVariable.Index
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
