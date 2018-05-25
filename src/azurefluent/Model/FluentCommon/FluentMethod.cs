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
    public class FluentMethod
    {
        private readonly bool isStandard;

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
        public CompositeTypeJvaf InnerReturnType
        {
            get
            {
                if (innerReturnType == null)
                {
                    IModelType mtype = InnerMethod.ReturnTypeJva.BodyClientType;
                    if (mtype is CompositeTypeJvaf)
                    {
                        this.innerReturnType = (CompositeTypeJvaf)mtype;
                    }
                    else if (mtype is SequenceTypeJva)
                    {
                        mtype = ((SequenceTypeJva)mtype).ElementType;

                        if (mtype is CompositeTypeJvaf)
                        {
                            this.innerReturnType = (CompositeTypeJvaf)mtype;
                        }
                        else if (mtype is PrimaryTypeJv)
                        {
                            this.innerReturnType = null;
                        }
                        else
                        {
                            throw new InvalidOperationException($"Expected CompositeTypeJvaf but found {mtype.ClassName}");
                        }
                    }
                    else if (mtype is PrimaryTypeJv)
                    {
                        this.innerReturnType = null;
                    }
                    else
                    {
                        throw new InvalidOperationException($"Expected CompositeTypeJvaf but found {mtype.ClassName}");
                    }
                }
                return this.innerReturnType;
            }
        }

        private FluentModel returnModel;
        public FluentModel ReturnModel
        {
            get
            {
                if (returnModel == null)
                {
                    if (this.isStandard)
                    {
                        this.returnModel = this.FluentMethodGroup.StandardFluentModel;
                    }
                    else
                    {
                        if (this.InnerReturnType != null)
                        {
                            var similarMethodGroup = this.FluentMethodGroup.FluentMethodGroups.SelectMany(gs => gs.Value).Where(fmg => fmg.StandardFluentModel != null && fmg.StandardFluentModel.InnerModel.Name == InnerReturnType.Name);
                            if (similarMethodGroup.Count() > 0)
                            {
                                this.returnModel = similarMethodGroup.First().StandardFluentModel;
                            }
                            else
                            {
                                this.returnModel = new FluentModel(this.InnerReturnType);
                            }
                        }
                        else
                        {
                            this.returnModel = new PrimtiveFluentModel();
                        }
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

        public FluentMethod(bool isStandard, MethodJvaf innerMethod, IFluentMethodGroup methodGroup)
        {
            this.isStandard = isStandard;
            this.InnerMethod = innerMethod;
            this.FluentMethodGroup = methodGroup;
        }
    }

    /// <summary>
    ///  This model is a hack to be removed when we've better design.
    /// </summary>
    public class PrimtiveFluentModel : FluentModel
    {
        public PrimtiveFluentModel() : base()
        {
        }
    }
}
