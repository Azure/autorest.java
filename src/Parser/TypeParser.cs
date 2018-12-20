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
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;

namespace AutoRest.Java
{
    public class TypeParser
    {
        private JavaSettings settings;

        public TypeParser(JavaSettings settings)
        {
            this.settings = settings;
        }

        Dictionary<IModelTypeJv, IType> parsed = new Dictionary<IModelTypeJv, IType>();

        public IType Parse(EnumTypeJv enumType)
        {
            if (parsed.ContainsKey(enumType))
            {
                return parsed[enumType];
            }
            IType _itype;
            string enumTypeName = enumType.Name?.ToString();

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
                foreach (EnumValue enumValue in enumType.Values)
                {
                    enumValues.Add(new ServiceEnumValue(enumValue.MemberName, enumValue.SerializedName));
                }

                _itype = new Model.EnumType(enumPackage, enumTypeName, enumType.ModelAsString, enumValues);
                parsed[enumType] = _itype;
            }

            return _itype;
        }

        public IType Parse(PrimaryTypeJv primaryType)
        {
            if (parsed.ContainsKey(primaryType))
            {
                return parsed[primaryType];
            }
            IType _itype;
            switch (primaryType.KnownPrimaryType)
            {
                case KnownPrimaryType.None:
                    _itype = PrimitiveType.Void;
                    break;
                case KnownPrimaryType.Base64Url:
                    _itype = ClassType.Base64Url;
                    break;
                case KnownPrimaryType.Boolean:
                    _itype = PrimitiveType.Boolean;
                    break;
                case KnownPrimaryType.ByteArray:
                    _itype = ArrayType.ByteArray;
                    break;
                case KnownPrimaryType.Date:
                    _itype = ClassType.LocalDate;
                    break;
                case KnownPrimaryType.DateTime:
                    _itype = ClassType.DateTime;
                    break;
                case KnownPrimaryType.DateTimeRfc1123:
                    _itype = ClassType.DateTimeRfc1123;
                    break;
                case KnownPrimaryType.Double:
                    _itype = PrimitiveType.Double;
                    break;
                case KnownPrimaryType.Decimal:
                    _itype = ClassType.BigDecimal;
                    break;
                case KnownPrimaryType.Int:
                    _itype = PrimitiveType.Int;
                    break;
                case KnownPrimaryType.Long:
                    _itype = PrimitiveType.Long;
                    break;
                case KnownPrimaryType.Stream:
                    _itype = GenericType.FlowableByteBuffer;
                    break;
                case KnownPrimaryType.String:
                    if (primaryType.Format.EqualsIgnoreCase(ClassType.URL.Name))
                    {
                        _itype = ClassType.URL;
                    }
                    else
                    {
                        _itype = ClassType.String;
                    }
                    break;
                case KnownPrimaryType.TimeSpan:
                    _itype = ClassType.Duration;
                    break;
                case KnownPrimaryType.UnixTime:
                    _itype = PrimitiveType.UnixTimeLong;
                    break;
                case KnownPrimaryType.Uuid:
                    _itype = ClassType.UUID;
                    break;
                case KnownPrimaryType.Object:
                    _itype = ClassType.Object;
                    break;
                case KnownPrimaryType.Credentials:
                    _itype = ClassType.ServiceClientCredentials;
                    break;
                default:
                    throw new NotImplementedException($"Unrecognized AutoRest KnownPrimaryType: {primaryType.KnownPrimaryType}");
            }
            parsed[primaryType] = _itype;
            return _itype;
        }

        public IType Parse(SequenceTypeJv sequenceType)
        {
            if (parsed.ContainsKey(sequenceType))
            {
                return parsed[sequenceType];
            }
            var _itype = new ListType(((IModelTypeJv)sequenceType.ElementType).GenerateType(settings));
            parsed[sequenceType] = _itype;
            return _itype;
        }

        public IType Parse(DictionaryTypeJv dictionaryType)
        {
            if (parsed.ContainsKey(dictionaryType))
            {
                return parsed[dictionaryType];
            }
            var _itype = new MapType(((IModelTypeJv)dictionaryType.ValueType).GenerateType(settings));
            parsed[dictionaryType] = _itype;
            return _itype;
        }

        public IType Parse(CompositeTypeJv compositeType)
        {
            if (parsed.ContainsKey(compositeType))
            {
                return parsed[compositeType];
            }
            IType result = null;
            if (settings.IsAzureOrFluent)
            {
                // TODO: Not that simple
                if (compositeType.ModelTypeName == ClassType.Resource.Name)
                {
                    result = ClassType.Resource;
                }
                else if (compositeType.ModelTypeName == ClassType.ProxyResource.Name)
                {
                    result = ClassType.ProxyResource;
                }
                else if (compositeType.ModelTypeName == ClassType.SubResource.Name)
                {
                    result = ClassType.SubResource;
                }
            }

            if (result == null)
            {
                string classPackage;
                if (!settings.IsFluent)
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings, settings.ModelsSubpackage);
                }
                else if (compositeType.IsInnerModel)
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings, settings.ImplementationSubpackage);
                }
                else
                {
                    classPackage = CodeGeneratorJv.GetPackage(settings);
                }

                IDictionary<string, string> extensions = null;
                if (compositeType.Extensions.ContainsKey(SwaggerExtensions.NameOverrideExtension))
                {
                    JContainer ext = compositeType.Extensions[SwaggerExtensions.NameOverrideExtension] as JContainer;
                    if (ext != null && ext["name"] != null)
                    {
                        extensions = new Dictionary<string, string>();
                        extensions[SwaggerExtensions.NameOverrideExtension] = ext["name"].ToString();
                    }
                }
                result = new ClassType(classPackage, compositeType.ModelTypeName, null, extensions, compositeType.IsInnerModel);
            }

            parsed[compositeType] = result;
            return result;
        }
    }
}