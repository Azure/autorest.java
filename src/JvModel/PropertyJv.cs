// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using AutoRest.Core.Utilities;
using AutoRest.Core.Model;
using Newtonsoft.Json;
using AutoRest.Core;
using System;
using AutoRest.Extensions;

namespace AutoRest.Java.Model
{
    public class PropertyJv : Property
    {
        public PropertyJv()
            : base()
        {}

        public IType GenerateType(JavaSettings settings)
        {
            IType result = ((IModelTypeJv)ModelType)?.GenerateType(settings);
            if (result != null && this.IsNullable())
            {
                result = result.AsNullable();
            }
            return result;
        }

        public ServiceModelProperty GenerateProperty(JavaSettings settings)
        {
            string description = "";
            if (string.IsNullOrEmpty(Summary) && string.IsNullOrEmpty(Documentation))
            {
                description = $"The {Name} property.";
            }
            else
            {
                description = Summary;

                string documentation = Documentation;
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
                xmlName = ModelType.XmlProperties?.Name
                    ?? XmlName;
            }
            catch
            {
                xmlName = null;
            }

            List<string> annotationArgumentList = new List<string>()
            {
                $"value = \"{(settings.ShouldGenerateXmlSerialization ? xmlName : SerializedName)}\""
            };
            if (IsRequired)
            {
                annotationArgumentList.Add("required = true");
            }
            if (IsReadOnly)
            {
                annotationArgumentList.Add("access = JsonProperty.Access.WRITE_ONLY");
            }
            string annotationArguments = string.Join(", ", annotationArgumentList);

            bool isXmlAttribute = XmlIsAttribute;

            string serializedName = SerializedName;

            bool isXmlWrapper = XmlIsWrapped;

            string headerCollectionPrefix = Extensions?.GetValue<string>(SwaggerExtensions.HeaderCollectionPrefix);

            IType propertyWireType = ((IModelTypeJv)ModelType).GenerateType(settings);
            if (propertyWireType != null && this.IsNullable())
            {
                propertyWireType = propertyWireType.AsNullable();
            }

            IType propertyClientType = ((IModelTypeJv)ModelType).ConvertToClientType().GenerateType(settings);

            IModelTypeJv autoRestPropertyModelType = (IModelTypeJv) ModelType;
            string xmlListElementName = null;
            if (autoRestPropertyModelType is SequenceTypeJv sequence)
            {
                try
                {
                    xmlListElementName = sequence.ElementType.XmlProperties?.Name ?? sequence.ElementXmlName;
                }
                catch { }
            }

            bool isConstant = IsConstant;

            string defaultValue;
            try
            {
                defaultValue = propertyWireType.DefaultValueExpression(DefaultValue);
            }
            catch (NotSupportedException)
            {
                defaultValue = null;
            }

            bool isReadOnly = IsReadOnly;

            bool wasFlattened = this.WasFlattened();

            return new ServiceModelProperty(Name, description, annotationArguments, isXmlAttribute, xmlName, serializedName, isXmlWrapper, xmlListElementName, propertyWireType, propertyClientType, isConstant, defaultValue, isReadOnly, wasFlattened, headerCollectionPrefix);
        }
    }
}