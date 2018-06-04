// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class OtherMethod
    {
        public OtherMethod(MethodJvaf innerMethod, IFluentMethodGroup methodGroup)
        {
            this.InnerMethod = innerMethod;
            this.FluentMethodGroup = methodGroup;
        }

        public MethodJvaf InnerMethod { get; }

        public IFluentMethodGroup FluentMethodGroup { get; }

        public string Name
        {
            get
            {
                return this.InnerMethod.Name.Value;
            }
        }

        private CompositeTypeJvaf innerReturnType;
        private CompositeTypeJvaf InnerReturnType
        {
            get
            {
                if (innerReturnType == null)
                {
                    IModelType mtype = InnerMethod.ReturnTypeJva.BodyClientType;
                    if (mtype is CompositeTypeJvaf)
                    {
                        this.innerReturnType = (CompositeTypeJvaf) mtype;
                    }
                    else if (mtype is SequenceTypeJva)
                    {
                        mtype = ((SequenceTypeJva) mtype).ElementType;

                        if (mtype is CompositeTypeJvaf)
                        {
                            this.innerReturnType = (CompositeTypeJvaf) mtype;
                        }
                        else if (mtype is PrimaryTypeJv)
                        {
                            this.innerReturnType = null;
                        }
                        else
                        {
                            throw new NotImplementedException($"Handling return type '{mtype.ClassName}' for OtherMethod is not implemented");
                        }
                    }
                    else if (mtype is PrimaryTypeJv)
                    {
                        this.innerReturnType = null;
                    }
                    else
                    {
                        throw new NotImplementedException($"Handling return type '{mtype.ClassName}' for OtherMethod is not implemented");
                    }
                }
                return this.innerReturnType;
            }
        }

        private IModel returnModel;
        public IModel ReturnModel
        {
            get
            {
                if (this.returnModel == null)
                {
                    if (this.InnerReturnType != null)
                    {
                        var group = this.FluentMethodGroup.FluentMethodGroups
                            .SelectMany(gs => gs.Value)
                            .FirstOrDefault(g => g.StandardFluentModel != null && g.StandardFluentModel.InnerModel.Name == InnerReturnType.Name);
                        //
                        if (group != null)
                        {
                            this.returnModel = group.StandardFluentModel;
                        }
                        else if (this.InnerReturnType.ClassName.EndsWith("Inner"))
                        {
                            this.returnModel = new WrappableFluentModel(this.InnerReturnType);
                        }
                        else
                        {
                            // compositeInnerType == true && this.InnerReturnType.ClassName.EndsWith("Inner") == false
                            //
                            this.returnModel = new NonWrappableModel(this.InnerReturnType);
                        }
                    }
                    else
                    {
                        this.returnModel = new PrimitiveModel();
                    }
                }
                return this.returnModel;
            }
        }

        public string InnerMethodInvocationParameters
        {
            get
            {
                List<string> invoke = new List<string>();
                foreach (var parameter in this.InnerMethod.LocalParameters.Where(p => !p.IsConstant && p.IsRequired))
                {
                    invoke.Add(parameter.Name);
                }
                return string.Join(", ", invoke);
            }
        }

        public string InnerMethodRequiredParameterDeclaration
        {
            get
            {
                return InnerMethod.MethodRequiredParameterDeclaration;
            }
        }
    }
}
