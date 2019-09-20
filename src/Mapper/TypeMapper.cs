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
    /// <summary>
    /// Maps an IModelTypeJv to an IType.
    /// </summary>
    public class TypeMapper : IMapper<IModelTypeJv, IType>
    {
        private TypeMapper()
        {
        }

        private static TypeMapper _instance = new TypeMapper();
        public static TypeMapper Instance => _instance;

        Dictionary<IModelTypeJv, IType> parsed = new Dictionary<IModelTypeJv, IType>();

        public IType Map(IModelTypeJv modelType)
        {
            if (modelType == null)
            {
                return null;
            }
            if (modelType is EnumTypeJv enumType)
            {
                return Parse(enumType);
            }
            else if (modelType is PrimaryTypeJv primaryType)
            {
                return Parse(primaryType);
            }
            else if (modelType is SequenceTypeJv sequenceType)
            {
                return Parse(sequenceType);
            }
            else if (modelType is DictionaryTypeJv dictionaryType)
            {
                return Parse(dictionaryType);
            }
            else if (modelType is CompositeTypeJv compositeType)
            {
                return Parse(compositeType);
            }
            else
            {
                throw new NotSupportedException($"Not supported type {modelType.ClassName}.");
            }
        }

        public IType Parse(EnumTypeJv enumType)
        {
            var settings = JavaSettings.Instance;

            if (enumType == null)
            {
                return null;
            }
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
                if (settings.IsCustomType(enumTypeName)) {
                    enumSubpackage = settings.CustomTypesSubpackage;
                }
                string enumPackage = settings.GetPackage(enumSubpackage);

                enumTypeName = CodeNamerJv.Instance.GetTypeName(enumTypeName);

                List<ClientEnumValue> enumValues = new List<ClientEnumValue>();
                foreach (EnumValue enumValue in enumType.Values)
                {
                    enumValues.Add(new ClientEnumValue(enumValue.MemberName, enumValue.SerializedName));
                }

                _itype = new Model.EnumType(enumPackage, enumTypeName, enumType.ModelAsString, enumValues);
                parsed[enumType] = _itype;
            }

            return _itype;
        }

        public IType Parse(PrimaryTypeJv primaryType)
        {
            if (primaryType == null)
            {
                return null;
            }
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
                    _itype = GenericType.FluxByteBuffer;
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
            if (sequenceType == null)
            {
                return null;
            }
            if (parsed.ContainsKey(sequenceType))
            {
                return parsed[sequenceType];
            }
            var _itype = new ListType(Mappers.TypeMapper.Map((IModelTypeJv)sequenceType.ElementType));
            parsed[sequenceType] = _itype;
            return _itype;
        }

        public IType Parse(DictionaryTypeJv dictionaryType)
        {
            if (dictionaryType == null)
            {
                return null;
            }
            if (parsed.ContainsKey(dictionaryType))
            {
                return parsed[dictionaryType];
            }
            var _itype = new MapType(Mappers.TypeMapper.Map((IModelTypeJv)dictionaryType.ValueType));
            parsed[dictionaryType] = _itype;
            return _itype;
        }

        public IType Parse(CompositeTypeJv compositeType)
        {
            var settings = JavaSettings.Instance;

            if (compositeType == null)
            {
                return null;
            }
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
                if (settings.IsCustomType(compositeType.ModelTypeName))
                {
                    classPackage = settings.GetPackage(settings.CustomTypesSubpackage);
                }
                else if (!settings.IsFluent)
                {
                    classPackage = settings.GetPackage(settings.ModelsSubpackage);
                }
                else if (compositeType.IsInnerModel)
                {
                    classPackage = settings.GetPackage(settings.ImplementationSubpackage);
                }
                else
                {
                    classPackage = settings.Package;
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