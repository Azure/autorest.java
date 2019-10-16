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
    /// Maps a PropertyJv to a ClientModelProperty.
    /// </summary>
    public class ModelPropertyMapper : IMapper<PropertyJv, ClientModelProperty>
    {
        private ModelPropertyMapper()
        {
        }

        private static ModelPropertyMapper _instance = new ModelPropertyMapper();
        public static ModelPropertyMapper Instance => _instance;

        public ClientModelProperty Map(PropertyJv property)
        {
            string description = "";
            if (string.IsNullOrEmpty(property.Summary) && string.IsNullOrEmpty(property.Documentation))
            {
                description = $"The {property.Name} property.";
            }
            else
            {
                description = property.Summary;

                string documentation = property.Documentation;
                if (!string.IsNullOrEmpty(documentation))
                {
                    if (!string.IsNullOrEmpty(description))
                    {
                        description += Environment.NewLine;
                    }
                    description += documentation;
                }
            }

            string xmlName;
            try
            {
                xmlName = property.ModelType.XmlProperties?.Name
                    ?? property.XmlName;
            }
            catch
            {
                xmlName = null;
            }

            List<string> annotationArgumentList = new List<string>()
            {
                $"value = \"{(JavaSettings.Instance.ShouldGenerateXmlSerialization ? xmlName : property.SerializedName)}\""
            };
            if (property.IsRequired)
            {
                annotationArgumentList.Add("required = true");
            }
            if (property.IsReadOnly)
            {
                annotationArgumentList.Add("access = JsonProperty.Access.WRITE_ONLY");
            }
            string annotationArguments = string.Join(", ", annotationArgumentList);

            bool isXmlAttribute = property.XmlIsAttribute;

            string serializedName = property.SerializedName;

            bool isXmlWrapper = property.XmlIsWrapped;

            string headerCollectionPrefix = property.Extensions?.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

            IType propertyWireType = Mappers.TypeMapper.Map((IModelTypeJv)property.ModelType);
            if (propertyWireType != null && property.IsNullable())
            {
                propertyWireType = propertyWireType.AsNullable();
            }

            IType propertyClientType = Mappers.TypeMapper.Map(((IModelTypeJv)property.ModelType).ClientType);

            IModelTypeJv autoRestPropertyModelType = (IModelTypeJv) property.ModelType;
            string xmlListElementName = null;
            if (autoRestPropertyModelType is SequenceTypeJv sequence)
            {
                try
                {
                    xmlListElementName = sequence.ElementType.XmlProperties?.Name ?? sequence.ElementXmlName;
                }
                catch { }
            }

            bool isConstant = property.IsConstant;

            string defaultValue;
            try
            {
                defaultValue = propertyWireType.DefaultValueExpression(property.DefaultValue);
            }
            catch (NotSupportedException)
            {
                defaultValue = null;
            }

            bool isReadOnly = property.IsReadOnly;

            bool wasFlattened = property.WasFlattened();

            bool useVarArgs = false;

            if (property.Parent is CompositeType parent)
            {
                var modelPropertyName= $"{parent.Name}.{property.Name}";
                if (Settings.Instance.CustomSettings["ModelProperties"] is Dictionary<string, Dictionary<string, bool>> modelProperties)
                {
                    if (modelProperties.ContainsKey(modelPropertyName) &&
                        modelProperties[modelPropertyName].ContainsKey("varArg") &&
                        modelProperties[modelPropertyName]["varArg"])
                    {
                        useVarArgs = true;
                    }
                }
                
            }

            return new ClientModelProperty(property.Name, description, annotationArguments, isXmlAttribute, xmlName, serializedName, isXmlWrapper, xmlListElementName, propertyWireType, propertyClientType, isConstant, defaultValue, isReadOnly, wasFlattened, headerCollectionPrefix, useVarArgs);
        }
    }
}