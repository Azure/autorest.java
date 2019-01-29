// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Net;
using System.Text;
using AutoRest.Extensions;
using AutoRest.Core.Utilities;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using AutoRest.Java.Model;
using System.Text.RegularExpressions;

namespace AutoRest.Java
{
    /// <summary>
    /// Writes an XmlSequenceWrapper to a JavaFile.
    /// </summary>
    public class XmlSequenceWrapperTemplate : IJavaTemplate<XmlSequenceWrapper, JavaFile>
    {
        private static XmlSequenceWrapperTemplate _instance = new XmlSequenceWrapperTemplate();
        public static XmlSequenceWrapperTemplate Instance => _instance;

        private XmlSequenceWrapperTemplate()
        {
        }

        public void Write(XmlSequenceWrapper xmlSequenceWrapper, JavaFile javaFile)
        {
            string xmlRootElementName = xmlSequenceWrapper.XmlRootElementName;
            string xmlListElementName = xmlSequenceWrapper.XmlListElementName;

            string xmlElementNameCamelCase = xmlRootElementName.ToCamelCase();

            ListType sequenceType = xmlSequenceWrapper.SequenceType;

            javaFile.Import(xmlSequenceWrapper.Imports);

            javaFile.JavadocComment(comment =>
            {
                comment.Description($"A wrapper around {sequenceType} which provides top-level metadata for serialization.");
            });
            javaFile.Annotation($"JacksonXmlRootElement(localName = \"{xmlRootElementName}\")");
            javaFile.PublicFinalClass(xmlSequenceWrapper.WrapperClassName, classBlock =>
            {
                classBlock.Annotation($"JacksonXmlProperty(localName = \"{xmlListElementName}\")");
                classBlock.PrivateFinalMemberVariable(sequenceType.ToString(), xmlElementNameCamelCase);

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Creates an instance of {xmlSequenceWrapper.WrapperClassName}.");
                    comment.Param(xmlElementNameCamelCase, "the list");
                });
                classBlock.Annotation("JsonCreator");
                classBlock.PublicConstructor($"{xmlSequenceWrapper.WrapperClassName}(@JsonProperty(\"{xmlListElementName}\") {sequenceType} {xmlElementNameCamelCase})", constructor =>
                {
                    constructor.Line($"this.{xmlElementNameCamelCase} = {xmlElementNameCamelCase};");
                });

                classBlock.JavadocComment(comment =>
                {
                    comment.Description($"Get the {sequenceType} contained in this wrapper.");
                    comment.Return($"the {sequenceType}");
                });
                classBlock.PublicMethod($"{sequenceType} items()", function =>
                {
                    function.Return(xmlElementNameCamelCase);
                });
            });
        }
    }
}