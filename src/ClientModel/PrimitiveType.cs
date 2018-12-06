// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// A basic type used by a REST API method.
    /// </summary>
    public class PrimitiveType : IType
    {
        public static readonly PrimitiveType Void = new PrimitiveType("void", ClassType.Void);
        public static readonly PrimitiveType Boolean = new PrimitiveType("boolean", ClassType.Boolean, (string defaultValueExpression) => defaultValueExpression.ToLowerInvariant());
        public static readonly PrimitiveType Byte = new PrimitiveType("byte", ClassType.Byte);
        public static readonly PrimitiveType Int = new PrimitiveType("int", ClassType.Integer);
        public static readonly PrimitiveType Long = new PrimitiveType("long", ClassType.Long, (string defaultValueExpression) => defaultValueExpression + 'L');
        public static readonly PrimitiveType Double = new PrimitiveType("double", ClassType.Double, (string defaultValueExpression) => double.Parse(defaultValueExpression).ToString());

        public static readonly PrimitiveType UnixTimeLong = new PrimitiveType("long", ClassType.UnixTimeLong);

        /// <summary>
        /// Create a new PrimitiveType from the provided properties.
        /// </summary>
        /// <param name="name">The name of this type.</param>
        private PrimitiveType(string name, ClassType nullableType, Func<string,string> defaultValueExpressionConverter = null)
        {
            Name = name;
            NullableType = nullableType;
            DefaultValueExpressionConverter = defaultValueExpressionConverter;
        }

        /// <summary>
        /// The name of this type.
        /// </summary>
        public string Name { get; }

        /// <summary>
        /// The nullable version of this primitive type.
        /// </summary>
        private ClassType NullableType { get; }

        public void AddImportsTo(ISet<string> imports, bool includeImplementationImports)
        {
        }

        public IType AsNullable()
        {
            return NullableType;
        }

        public bool Contains(IType type)
        {
            return this == type;
        }

        private Func<string,string> DefaultValueExpressionConverter { get; }

        public string DefaultValueExpression(string sourceExpression)
        {
            string result = sourceExpression;
            if (result != null && DefaultValueExpressionConverter != null)
            {
                result = DefaultValueExpressionConverter(sourceExpression);
            }
            return result;
        }

        public IType ConvertToClientType()
        {
            IType clientType = this;
            if (this == PrimitiveType.UnixTimeLong)
            {
                clientType = ClassType.UnixTimeDateTime;
            }
            return clientType;
        }

        public override string ToString()
        {
            return Name;
        }
    }
}
