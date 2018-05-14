// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Type representing the generalized view of "Standard Methods" in a "Fluent Method Group"
    /// it also expose "OtherMethods" in the "Fluent Method Group" as it is.
    /// </summary>
    public class GeneralizedOutput
    {
        public DefineFunc DefineFunc
        {
            get
            {
                return this.fluentMethodGroup.ResourceCreateDescription.DefineFunc;
            }
        }

        public WrapExistingModelFunc WrapExistingModelFunc
        { 
            get
            {
                if (this.fluentMethodGroup.StandardFluentModel != null)
                {
                    return this.fluentMethodGroup.StandardFluentModel.WrapExistingModelFunc;
                }
                else
                {
                    return null;
                }
            }
        }

        public WrapNewModelFunc WrapNewModelFunc
        {
            get
            {
                return this.fluentMethodGroup.ResourceCreateDescription.WrapNewModelFunc;
            }
        }

        public HashSet<string> ImportsForInterface
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                foreach (var view in this.GeneralizedViews)
                {
                    imports.AddRange(view.ImportsForGeneralizedInterface);
                }
                imports.AddRange(this.OtherMethods.ImportsForInterface);
                return imports;
            }
        }

        public HashSet<string> ImportsForImpl
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                foreach (var view in this.GeneralizedViews)
                {
                        imports.AddRange(view.ImportsForGeneralizedImpl);
                }
                imports.AddRange(this.OtherMethods.ImportsForImpl);
                return imports;
            }
        }

        public IEnumerable<string> MethodDecl
        {
            get
            {
                foreach (var view in this.GeneralizedViews)
                {
                    foreach (string decl in view.GeneralizedMethodDecls)
                    {
                        yield return decl;
                    }
                }
                foreach (var decl in this.OtherMethods.MethodDecls)
                {
                    yield return decl;
                }
            }
        }

        public IEnumerable<string> MethodImpl
        {
            get
            {
                foreach (var view in this.GeneralizedViews)
                {
                    foreach (string impl in view.GeneralizedMethodImpls)
                    {
                        yield return impl;
                    }
                }
                foreach (var impl in this.OtherMethods.MethodImpls)
                {
                    yield return impl;
                }
            }
        }

        private OtherMethods OtherMethods
        {
            get
            {
                return this.fluentMethodGroup.OtherMethods;
            }
        }

        private IEnumerable<ISupportsGeneralizedView> GeneralizedViews
        {
            get
            {
                yield return this.fluentMethodGroup.ResourceGetDescription;
                yield return this.fluentMethodGroup.ResourceListingDescription;
                yield return this.fluentMethodGroup.ResourceDeleteDescription;
            }
        }


        private readonly FluentMethodGroup fluentMethodGroup;
        private GeneralizedOutput(FluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
        }

        public GeneralizedOutput Generalize(FluentMethodGroup fluentMethodGroup)
        {
            GeneralizedOutput generalizedOutput = new GeneralizedOutput(fluentMethodGroup);
            return generalizedOutput;
        }
    }

    public interface ISupportsGeneralizedView
    {
        HashSet<string> ImportsForGeneralizedInterface { get; }
        HashSet<string> ImportsForGeneralizedImpl { get; }
        IEnumerable<string> GeneralizedMethodDecls { get; }
        IEnumerable<string> GeneralizedMethodImpls { get; }
    }
}
