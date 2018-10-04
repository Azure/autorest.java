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
    /// <summary>
    /// OtherMethod is a type representing a "Non-Standard Method" that needs to exposed in a fluent method group (fluent collection) interface & implemented in collection impl.
    /// [e.g. VirtualMachines::capture(captureParams), Disks::grantAccess(accessData)].
    /// "Standard Method"s are those exposed via standard fluent interfaces defined under fluentcore.
    ///     https://github.com/Azure/azure-libraries-for-java/tree/master/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/collection
    ///     https://github.com/Azure/azure-libraries-for-java/tree/master/azure-mgmt-resources/src/main/java/com/microsoft/azure/management/resources/fluentcore/arm/collection
    ///
    /// </summary>
    public class OtherMethod
    {
        public OtherMethod(MethodJvaf innerMethod, ISegmentFluentMethodGroup methodGroup)
        {
            this.InnerMethod = innerMethod;
            this.FluentMethodGroup = methodGroup;
        }

        public MethodJvaf InnerMethod { get; }

        public ISegmentFluentMethodGroup FluentMethodGroup { get; }

        public string Name
        {
            get
            {
                return this.InnerMethod.Name.Value;
            }
        }

        private IModelType innerReturnType;
        private IModelType InnerReturnType
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
                        else if (mtype is PrimaryTypeJv primaryType)
                        {
                            this.innerReturnType = mtype;
                        }
                        else
                        {
                            throw new NotImplementedException($"Handling return type '{mtype.ClassName}' for OtherMethod is not implemented");
                        }
                    }
                    else if (mtype is DictionaryTypeJv)
                    {
                        this.innerReturnType = (DictionaryTypeJv) mtype;
                    }
                    else if (mtype is PrimaryTypeJv primaryType)
                    {
                        this.innerReturnType = mtype;
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
                    if (this.InnerReturnType is CompositeTypeJvaf compositeType)
                    {
                        var group = this.FluentMethodGroup.FluentMethodGroups
                            .SelectMany(gs => gs.Value)
                            .FirstOrDefault(g => g.StandardFluentModel != null && g.StandardFluentModel.RawModel.Name == InnerReturnType.Name);
                        //
                        if (group != null)
                        {
                            this.returnModel = group.StandardFluentModel;
                        }
                        else if (compositeType.ClassName.EndsWith("Inner"))
                        {
                            this.returnModel = new WrappableFluentModel(compositeType);
                        }
                        else
                        {
                            // compositeInnerType == true && this.InnerReturnType.ClassName.EndsWith("Inner") == false
                            //
                            this.returnModel = new NonWrappableModel(compositeType);
                        }
                    }
                    else if (this.InnerReturnType is PrimaryTypeJv primaryType && primaryType.KnownPrimaryType != KnownPrimaryType.None)
                    {
                        this.returnModel = new PrimitiveModel(primaryType);
                    }
                    else if (this.InnerReturnType is DictionaryTypeJv dictionaryType)
                    {
                        this.returnModel = new DictionaryModel(dictionaryType, this.FluentMethodGroup);
                    }
                    else
                    {
                        this.returnModel = null;
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

        public override string ToString()
        {
            var returnType = ReturnModel is WrappableFluentModel wfm ? wfm.JavaInterfaceName : ReturnModel is NonWrappableModel nwm ? nwm.RawModelName : "void";
            return string.Format("{0} {1}({2})", returnType, Name, InnerMethod.MethodParameterDeclaration);
        }
    }
}
