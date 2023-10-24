// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * signed identifier.
 */
@Fluent
public final class SignedIdentifier implements XmlSerializable<SignedIdentifier> {
    /*
     * a unique id
     */
    private String id;

    /*
     * The access policy
     */
    private AccessPolicy accessPolicy;

    /**
     * Creates an instance of SignedIdentifier class.
     */
    public SignedIdentifier() {}

    /**
     * Get the id property: a unique id.
     * 
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: a unique id.
     * 
     * @param id the id value to set.
     * @return the SignedIdentifier object itself.
     */
    public SignedIdentifier setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the accessPolicy property: The access policy.
     * 
     * @return the accessPolicy value.
     */
    public AccessPolicy getAccessPolicy() {
        return this.accessPolicy;
    }

    /**
     * Set the accessPolicy property: The access policy.
     * 
     * @param accessPolicy the accessPolicy value to set.
     * @return the SignedIdentifier object itself.
     */
    public SignedIdentifier setAccessPolicy(AccessPolicy accessPolicy) {
        this.accessPolicy = accessPolicy;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getId() == null) {
            throw new IllegalArgumentException("Missing required property id in model SignedIdentifier");
        }
        if (getAccessPolicy() == null) {
            throw new IllegalArgumentException("Missing required property accessPolicy in model SignedIdentifier");
        } else {
            getAccessPolicy().validate();
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "SignedIdentifier" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Id", this.id);
        xmlWriter.writeXml(this.accessPolicy, "AccessPolicy");
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of SignedIdentifier from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of SignedIdentifier if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the SignedIdentifier.
     */
    public static SignedIdentifier fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of SignedIdentifier from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of SignedIdentifier if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the SignedIdentifier.
     */
    public static SignedIdentifier fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "SignedIdentifier" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            SignedIdentifier deserializedSignedIdentifier = new SignedIdentifier();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Id".equals(elementName.getLocalPart())) {
                    deserializedSignedIdentifier.id = reader.getStringElement();
                } else if ("AccessPolicy".equals(elementName.getLocalPart())) {
                    deserializedSignedIdentifier.accessPolicy = AccessPolicy.fromXml(reader, "AccessPolicy");
                } else {
                    reader.skipElement();
                }
            }

            return deserializedSignedIdentifier;
        });
    }
}
