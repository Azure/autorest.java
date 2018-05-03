// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Model;
using AutoRest.Java.Model;
using System;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentMethod
    {
        private readonly bool isStandard;
        private CompositeTypeJvaf innerReturnType;

        private FluentModel returnModel;

        public MethodJvaf InnerMethod { get; private set; }

        public FluentMethodGroup MethodGroup { get; private set; }

        public string Name
        {
            get
            {
                return this.InnerMethod.Name.Value;
            }
        }

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

        public FluentModel ReturnModel
        {
            get
            {
                if (returnModel == null)
                {
                    if (this.isStandard)
                    {
                        this.returnModel = this.MethodGroup.StandardFluentModel;
                    }
                    else
                    {
                        if (this.InnerReturnType != null)
                        {
                            var similarMethodGroup = MethodGroup.FluentMethodGroups.SelectMany(gs => gs.Value).Where(fmg => fmg.StandardFluentModel != null && fmg.StandardFluentModel.InnerModel.Name == InnerReturnType.Name);
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

        public FluentMethod(bool isStandard, MethodJvaf innerMethod, FluentMethodGroup methodGroup)
        {
            this.isStandard = isStandard;
            this.InnerMethod = innerMethod;
            this.MethodGroup = methodGroup;
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
