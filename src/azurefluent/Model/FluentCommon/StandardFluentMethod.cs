// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core.Model;
using AutoRest.Java.Azure.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type that wraps a standard inner method.
    /// </summary>
    public class StandardFluentMethod
    {
        public StandardFluentMethod(MethodJvaf innerMethod, IFluentMethodGroup methodGroup)
        {
            if (!CanWrap(innerMethod))
            {
                throw new ArgumentException($"StandardFluentMethod can wrap only inner methods that return wrappable return type, received inner method '{innerMethod.ReturnTypeResponseName} {innerMethod.Name}(..)'");
            }
            //
            this.InnerMethod = innerMethod;
            this.FluentMethodGroup = methodGroup;
        }

        public static bool CanWrap(MethodJvaf innerMethod)
        {
            if (innerMethod.HttpMethod == HttpMethod.Delete)
            {
                return true;
            }
            else
            {
                return innerMethod.HasWrappableReturnType();
            }
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
        public CompositeTypeJvaf InnerReturnType
        {
            get
            {
                if (innerReturnType == null)
                {
                    if (this.InnerMethod.HttpMethod == HttpMethod.Delete)
                    {
                        throw new InvalidOperationException("Invoking InnerReturnType on a method wrapping Delete operation is invalid");
                    }
                    else
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
                            else
                            {
                                // This and below exception should not hit as the ctr validate that return type
                                // is wrappable which requires type to be composite and have Inner suffix.
                                //
                                ThrowExpectedCompositeTypeNotFound(mtype);
                            }
                        }
                        else
                        {
                            ThrowExpectedCompositeTypeNotFound(mtype);
                        }
                    }
                }
                return this.innerReturnType;
            }
        }

        public WrappableFluentModel ReturnModel
        {
            get
            {
                return this.FluentMethodGroup.StandardFluentModel;
            }
        }

        private static void ThrowExpectedCompositeTypeNotFound(IModelType mtype)
        {
            throw new InvalidOperationException($"Expected CompositeTypeJvaf but found {mtype.ClassName}");
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
    }
}
