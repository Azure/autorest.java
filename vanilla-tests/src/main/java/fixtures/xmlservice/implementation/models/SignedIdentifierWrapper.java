// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlservice.implementation.models;

import fixtures.xmlservice.models.SignedIdentifier;
import java.util.List;

import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.util.ArrayList;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * A wrapper around List&lt;SignedIdentifier&gt; which provides top-level metadata for serialization.
 */
public final class SignedIdentifierWrapper implements XmlSerializable<SignedIdentifierWrapper> {
    private final List<SignedIdentifier> signedIdentifiers;

    /**
     * Creates an instance of SignedIdentifierWrapper.
     * 
     * @param signedIdentifiers the list.
     */
    public SignedIdentifierWrapper(List<SignedIdentifier> signedIdentifiers) {
        this.signedIdentifiers = signedIdentifiers;
    }

    /**
     * Get the List&lt;SignedIdentifier&gt; contained in this wrapper.
     * 
     * @return the List&lt;SignedIdentifier&gt;.
     */
    public List<SignedIdentifier> items() {
        return signedIdentifiers;
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "SignedIdentifiers" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        if (signedIdentifiers != null) {
            for (SignedIdentifier element : signedIdentifiers) {
                xmlWriter.writeXml(element, "SignedIdentifier");
            }
        }
        return xmlWriter.writeEndElement();
    }

    public static SignedIdentifierWrapper fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    public static SignedIdentifierWrapper fromXml(XmlReader xmlReader, String rootElementName)
        throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "SignedIdentifiers" : rootElementName;
        return xmlReader.readObject(rootElementName, reader -> {
            List<SignedIdentifier> items = null;

            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("SignedIdentifier".equals(elementName.getLocalPart())) {
                    if (items == null) {
                        items = new ArrayList<>();
                    }

                    items.add(SignedIdentifier.fromXml(reader));
                } else {
                    reader.nextElement();
                }
            }
            return new SignedIdentifierWrapper(items);
        });
    }
}
