// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Core;
using AutoRest.Core.Utilities;
using AutoRest.Extensions;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core.Utilities.Collections;

namespace AutoRest.Java.Model
{
    public class EnumTypeJv : AutoRest.Core.Model.EnumType, IModelTypeJv
    {
        public string ModelTypeName
        {
            get
            {
                var result = this.Name.ToString();
                result = (string.IsNullOrEmpty(result) || result == "enum" ? "String" : CodeNamer.Instance.GetTypeName(result));
                return result;
            }
        }

        public IModelTypeJv ConvertToClientType()
        {
            return this;
        }

        private IType _itype;
        public IType GenerateType(JavaSettings settings)
        {
            if (_itype == null) {
                string enumTypeName = Name?.ToString();

                if (string.IsNullOrEmpty(enumTypeName) || enumTypeName == "enum")
                {
                    _itype = ClassType.String;
                }
                else
                {
                    string enumSubpackage = (settings.IsFluent ? "" : settings.ModelsSubpackage);
                    string enumPackage = CodeGeneratorJv.GetPackage(settings, enumSubpackage);

                    enumTypeName = CodeNamerJv.Instance.GetTypeName(enumTypeName);

                    List<ServiceEnumValue> enumValues = new List<ServiceEnumValue>();
                    foreach (EnumValue enumValue in Values)
                    {
                        enumValues.Add(new ServiceEnumValue(enumValue.MemberName, enumValue.SerializedName));
                    }

                    _itype = new EnumType(enumPackage, enumTypeName, ModelAsString, enumValues);
                }
            }

            return _itype;
        }
    }
}