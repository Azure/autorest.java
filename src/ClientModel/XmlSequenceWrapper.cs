// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using AutoRest.Core.Utilities;

namespace AutoRest.Java.Model
{
    /// <summary>
    /// The details needed to create an XML sequence wrapper class for the client.
    /// </summary>
    public class XmlSequenceWrapper
    {
        private readonly ListType sequenceType;
        private readonly string xmlRootElementName;
        private readonly string xmlListElementName;
        private readonly string wrapperClassName;
        private readonly ISet<string> imports;

        public XmlSequenceWrapper(string package, ListType sequenceType, string xmlRootElementName, string xmlListElementName, ISet<string> imports)
        {
            this.Package = package;
            this.sequenceType = sequenceType;
            this.xmlRootElementName = xmlRootElementName;
            this.xmlListElementName = xmlListElementName;
            this.wrapperClassName = xmlRootElementName.ToPascalCase() + "Wrapper";
            this.imports = imports;
        }

        public string Package { get; }

        public ListType SequenceType => sequenceType;

        public string XmlRootElementName => xmlRootElementName;

        public string XmlListElementName => xmlListElementName;

        public string WrapperClassName => wrapperClassName;

        public ISet<string> Imports => imports;
    }
}
