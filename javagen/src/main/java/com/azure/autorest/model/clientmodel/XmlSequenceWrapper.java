package com.azure.autorest.model.clientmodel;

import com.azure.autorest.util.CodeNamer;
import java.util.Set;

// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License. See License.txt in the project root for license information.


/**
 * The details needed to create an XML sequence wrapper class for the client.
 */
public class XmlSequenceWrapper {
    private ListType sequenceType;
    private String xmlRootElementName;
    private String xmlListElementName;
    private String wrapperClassName;
    private Set<String> imports;
    private String packageName;

    public XmlSequenceWrapper(String package_Keyword, ListType sequenceType, String xmlRootElementName, String xmlListElementName, Set<String> imports) {
        this.packageName = package_Keyword;
        this.sequenceType = sequenceType;
        this.xmlRootElementName = xmlRootElementName;
        this.xmlListElementName = xmlListElementName;
        this.wrapperClassName = CodeNamer.toPascalCase(xmlRootElementName) + "Wrapper";
        this.imports = imports;
    }

    public final String getPackage() {
        return packageName;
    }

    public final ListType getSequenceType() {
        return sequenceType;
    }

    public final String getXmlRootElementName() {
        return xmlRootElementName;
    }

    public final String getXmlListElementName() {
        return xmlListElementName;
    }

    public final String getWrapperClassName() {
        return wrapperClassName;
    }

    public final Set<String> getImports() {
        return imports;
    }
}
