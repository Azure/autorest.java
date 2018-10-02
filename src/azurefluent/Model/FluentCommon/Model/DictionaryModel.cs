// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Core;
using AutoRest.Core.Model;
using AutoRest.Java.Model;
using System;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing primitive return type of a fluent method.
    /// </summary>
    public class DictionaryModel : IModel
    {
        private ISegmentFluentMethodGroup fluentMethodGroup;

        public DictionaryModel(DictionaryTypeJv rawModel, ISegmentFluentMethodGroup fluentMethodGroup)
        {
            this.RawModel = rawModel;
            this.fluentMethodGroup = fluentMethodGroup;
        }

        public DictionaryTypeJv RawModel { get; }

        public string RawModelName
        {
            get
            {
                return $"Map<String, {ValueModel.RawModelName}>";
            }
        }

        private IModel valueModel;
        public IModel ValueModel
        {
            get
            {
                if (valueModel == null)
                {
                    var mtype = RawModel.ValueType;
                    if (mtype is CompositeTypeJvaf compositeType)
                    {
                        var group = fluentMethodGroup.FluentMethodGroups
                            .SelectMany(gs => gs.Value)
                            .FirstOrDefault(g => g.StandardFluentModel != null && g.StandardFluentModel.RawModel.Name == mtype.Name);
                        //
                        if (group != null)
                        {
                            this.valueModel = group.StandardFluentModel;
                        }
                        else if (compositeType.ClassName.EndsWith("Inner"))
                        {
                            this.valueModel = new WrappableFluentModel(compositeType);
                        }
                        else
                        {
                            // compositeInnerType == true && this.InnerReturnType.ClassName.EndsWith("Inner") == false
                            //
                            this.valueModel = new NonWrappableModel(compositeType);
                        }
                    }
                    else if (mtype is PrimaryTypeJv primaryType)
                    {
                        if (primaryType.KnownPrimaryType == KnownPrimaryType.None)
                        {
                            this.valueModel = null;
                        }
                        else
                        {
                            this.valueModel = new PrimitiveModel(primaryType);
                        }
                    }
                    else if (mtype is DictionaryTypeJv dictionaryType)
                    {
                        this.valueModel = new DictionaryModel(dictionaryType, fluentMethodGroup);
                    }
                    else
                    {
                        throw new NotImplementedException($"Handling return type 'Map<String, {mtype.ClassName}>' for OtherMethod is not implemented");
                    }

                }
                return valueModel;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                return this.RawModel.Imports.ToHashSet();
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                return this.RawModel.Imports.ToHashSet();
            }
        }
    }
}
