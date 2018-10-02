// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentMethodGroupInterfaceModel
    {
        private readonly IFluentMethodGroup fluentMethodGroup;

        public FluentMethodGroupInterfaceModel(IFluentMethodGroup fluentMethodGroup)
        {
            this.fluentMethodGroup = fluentMethodGroup;
        }

        public string ExtendsFrom
        {
            get
            {
                return this.fluentMethodGroup.ExtendsFrom;
            }
        }

        public string JavaInterfaceName
        {
            get
            {
                return this.fluentMethodGroup.JavaInterfaceName;
            }
        }

        public HashSet<string> Imports
        {
            get
            {
                HashSet<string> imports = new HashSet<string>();
                imports.AddRange(this.fluentMethodGroup.ImportsForInterface);
                //
                var importsForGeneralizedMethods = this.fluentMethodGroup.GeneralizedOutputs
                    .SelectMany(go => go.ImportsForInterface)
                    .ToHashSet<string>();
                imports.AddRange(importsForGeneralizedMethods);
                //
                return imports;
            }
        }

        public IEnumerable<string> MethodDecls
        {
            get
            {
                //
                IEnumerable<IDefineFunc> defineFuns = this.fluentMethodGroup.GeneralizedOutputs
                    .Select(go => go.DefineFunc)
                    .Where(def => def.IsDefineSupported);
                foreach (IDefineFunc defineFunc in defineFuns)
                {
                    yield return defineFunc.GeneralizedMethodDecl;
                }
                //
                foreach (string methodDecl in this.fluentMethodGroup.OtherMethods.MethodDecls)
                {
                    yield return methodDecl;
                }
                //
                foreach (string methodDecl in this.fluentMethodGroup.ListGetDeleteByParentMethodDecls)
                {
                    yield return methodDecl;
                }
                //
                IEnumerable<string> methodDecls = this.fluentMethodGroup.GeneralizedOutputs
                    .SelectMany(go => go.MethodDecl);
                foreach (string methodDecl in methodDecls)
                {
                    yield return methodDecl;
                }
                //
            }
        }
    }
}
