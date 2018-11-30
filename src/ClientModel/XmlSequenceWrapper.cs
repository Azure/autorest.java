// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.

using System.Collections.Generic;
using AutoRest.Core.Utilities;

namespace AutoRest.Java.Model
{
    public class XmlSequenceWrapper
    {
        private readonly ListType sequenceType;
        private readonly string xmlRootElementName;
        private readonly string xmlListElementName;
        private readonly string wrapperClassName;
        private readonly ISet<string> imports;

        public XmlSequenceWrapper(ListType sequenceType, string xmlRootElementName, string xmlListElementName, ISet<string> imports)
        {
            this.sequenceType = sequenceType;
            this.xmlRootElementName = xmlRootElementName;
            this.xmlListElementName = xmlListElementName;
            this.wrapperClassName = xmlRootElementName.ToPascalCase() + "Wrapper";
            this.imports = imports;
        }

        public ListType SequenceType => sequenceType;

        public string XmlRootElementName => xmlRootElementName;

        public string XmlListElementName => xmlListElementName;

        public string WrapperClassName => wrapperClassName;

        public ISet<string> Imports => imports;
    }
}
