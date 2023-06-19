// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.model.clientmodel;

import java.util.Set;

/**
 * The details needed to create an XML sequence wrapper class for the client.
 */
public class XmlSequenceWrapper {
    private final String packageName;
    private final IType sequenceType;
    private final String xmlRootElementName;
    private final String xmlListElementName;
    private final String wrapperClassName;
    private final Set<String> imports;

    public XmlSequenceWrapper(String packageName, IType sequenceType, String modelTypeName, String xmlRootElementName,
        String xmlListElementName, Set<String> imports) {
        this.packageName = packageName;
        this.sequenceType = sequenceType;
        this.xmlRootElementName = xmlRootElementName;
        this.xmlListElementName = xmlListElementName;
        this.wrapperClassName = modelTypeName + "Wrapper";
        this.imports = imports;
    }

    public final String getPackage() {
        return packageName;
    }

    public final IType getSequenceType() {
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
