// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Java.Model;
using System.Collections.Generic;

namespace AutoRest.Java.Azure.Fluent.Model
{
    public class FluentDefinitionOrUpdateStageMethod
    {
        public FluentDefinitionOrUpdateStageMethod(string methodName, string parameterDeclaration, IModelTypeJv parameterType)
            : this(methodName, parameterDeclaration, new List<IModelTypeJv>() { parameterType })
        {
        }

        public FluentDefinitionOrUpdateStageMethod(string methodName, string parameterDeclaration, IEnumerable<IModelTypeJv> parameterTypes)
        {
            this.Name = methodName;
            this.ParameterDeclaration = parameterDeclaration;
            this.ParameterTypes = parameterTypes;
        }

        public string Name { get; private set; }

        public FluentDefinitionOrUpdateStage NextStage { get; set; }

        public IEnumerable<IModelTypeJv> ParameterTypes { get; private set; }

        public string ParameterTypesKey => string.Join('_', ParameterTypes);

        public string CommentFor { get; set; }

        public string ParameterDeclaration { get; private set; }

        public string Body { get; set; }

        public IEnumerable<FluentModelMemberVariable> UsedMemberVariables { get; set; } 

        public string Comment
        {
            get
            {
                return $"Specifies {this.CommentFor}.";
            }
        }

        public static IEqualityComparer<FluentDefinitionOrUpdateStageMethod> EqualityComparer()
        {
            return new DefinitionOrUpdateStageMethodComparer();
        }

        private class DefinitionOrUpdateStageMethodComparer : IEqualityComparer<FluentDefinitionOrUpdateStageMethod>
        {
            public bool Equals(FluentDefinitionOrUpdateStageMethod x, FluentDefinitionOrUpdateStageMethod y)
            {
                string s1 = $"{x.Name}_{x.ParameterTypesKey}";
                string s2 = $"{y.Name}_{y.ParameterTypesKey}";
                return s1.Equals(s2);
            }

            public int GetHashCode(FluentDefinitionOrUpdateStageMethod obj)
            {
                return $"{obj.Name}_{obj.ParameterTypesKey}".GetHashCode();
            }
        }
    }
}
