// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


using System.Collections.Generic;

namespace AutoRest.Java.Model
{
    public class XmlSequenceWrapper
    {
        private readonly string sequenceType;
        private readonly string xmlElementName;
        private readonly string wrapperClassName;
        private readonly ISet<string> imports;

        public XmlSequenceWrapper(string sequenceType, string xmlElementName, ISet<string> imports)
        {
            this.sequenceType = sequenceType;
            this.xmlElementName = xmlElementName;
            this.wrapperClassName = xmlElementName + "Wrapper";
            this.imports = imports;
        }

        public string SequenceType => sequenceType;

        public string XmlElementName => xmlElementName;

        public string WrapperClassName => wrapperClassName;

        public ISet<string> Imports => imports;
    }
}
