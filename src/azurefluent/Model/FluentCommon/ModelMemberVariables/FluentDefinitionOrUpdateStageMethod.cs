// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using AutoRest.Java.Model;
using System.Collections.Generic;
using System.Linq;

namespace AutoRest.Java.Azure.Fluent.Model
{
    /// <summary>
    /// Describes a method in a Fluent "Defintion" or "Update" stage.
    /// </summary>
    public class FluentDefinitionOrUpdateStageMethod
    {
        /// <summary>
        /// Creates FluentDefinitionOrUpdateStageMethod describing a method in a Fluent "Defintion" or "Update" stage.
        /// </summary>
        /// <param name="methodName">the method name</param>
        /// <param name="parameterDeclaration">The string containing method parameter declaration</param>
        /// <param name="parameterType">The parameter type</param>
        public FluentDefinitionOrUpdateStageMethod(string methodName, string parameterDeclaration, IModelTypeJv parameterType)
            : this(methodName, parameterDeclaration, new List<IModelTypeJv>() { parameterType })
        {
        }

        /// <summary>
        /// Creates FluentDefinitionOrUpdateStageMethod describing a method in a Fluent "Defintion" or "Update" stage.
        /// </summary>
        /// <param name="methodName">the method nameThe string containing method parameters declaration</param>
        /// <param name="parameterDeclaration"></param>
        /// <param name="parameterTypes">The parameter types</param>
        public FluentDefinitionOrUpdateStageMethod(string methodName, string parameterDeclaration, IEnumerable<IModelTypeJv> parameterTypes)
        {
            this.Name = methodName;
            this.ParameterDeclaration = parameterDeclaration;
            this.ParameterTypes = parameterTypes;
        }

        /// <summary>
        /// The method name.
        /// </summary>
        public string Name { get; private set; }

        /// <summary>
        /// The next stage after this method (return value of method is next stage)
        /// </summary>
        public FluentDefinitionOrUpdateStage NextStage { get; set; }

        /// <summary>
        /// The types of parameters this method takes.
        /// </summary>
        public IEnumerable<IModelTypeJv> ParameterTypes { get; private set; }

        /// <summary>
        /// A unique key derived from parameter type names by concating them seperated by underscore.
        /// </summary>
        public string ParameterTypesKey => string.Join('_', ParameterTypes.Select(mt => mt.Name));

        /// <summary>
        /// The doc comment for each parameters of this method, with key as parameter name
        /// and value as parameter description.
        /// </summary>
        public Dictionary<string, string> CommentFor { get; set; }

        /// <summary>
        /// The declaration of parameters of this method.
        /// </summary>
        public string ParameterDeclaration { get; private set; }

        /// <summary>
        /// The method body.
        /// </summary>
        public string Body { get; set; }

        public IEnumerable<FluentModelMemberVariable> UsedMemberVariables { get; set; } 

        /// <summary>
        /// The doc comment for this method
        /// </summary>
        public string Comment
        {
            get
            {
                return $"Specifies {string.Join(", ", this.CommentFor.Select(pair => pair.Key)) }.";
            }
        }

        /// <summary>
        /// Equality comparer to compare two instance of FluentDefinitionOrUpdateStageMethod.
        /// </summary>
        /// <returns></returns>
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
