// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * A barrel of apples.
 */
@Fluent
public final class AppleBarrel implements XmlSerializable<AppleBarrel> {
    /*
     * The GoodApples property.
     */
    @Generated
    private List<String> goodApples;

    /*
     * The BadApples property.
     */
    @Generated
    private List<String> badApples;

    /**
     * Creates an instance of AppleBarrel class.
     */
    @Generated
    public AppleBarrel() {
    }

    /**
     * Get the goodApples property: The GoodApples property.
     * 
     * @return the goodApples value.
     */
    @Generated
    public List<String> getGoodApples() {
        if (this.goodApples == null) {
            this.goodApples = new ArrayList<>();
        }
        return this.goodApples;
    }

    /**
     * Set the goodApples property: The GoodApples property.
     * 
     * @param goodApples the goodApples value to set.
     * @return the AppleBarrel object itself.
     */
    @Generated
    public AppleBarrel setGoodApples(List<String> goodApples) {
        this.goodApples = goodApples;
        return this;
    }

    /**
     * Get the badApples property: The BadApples property.
     * 
     * @return the badApples value.
     */
    @Generated
    public List<String> getBadApples() {
        if (this.badApples == null) {
            this.badApples = new ArrayList<>();
        }
        return this.badApples;
    }

    /**
     * Set the badApples property: The BadApples property.
     * 
     * @param badApples the badApples value to set.
     * @return the AppleBarrel object itself.
     */
    @Generated
    public AppleBarrel setBadApples(List<String> badApples) {
        this.badApples = badApples;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = rootElementName == null || rootElementName.isEmpty() ? "AppleBarrel" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        if (this.goodApples != null) {
            xmlWriter.writeStartElement("GoodApples");
            for (String element : this.goodApples) {
                xmlWriter.writeStringElement("Apple", element);
            }
            xmlWriter.writeEndElement();
        }
        if (this.badApples != null) {
            xmlWriter.writeStartElement("BadApples");
            for (String element : this.badApples) {
                xmlWriter.writeStringElement("Apple", element);
            }
            xmlWriter.writeEndElement();
        }
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of AppleBarrel from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of AppleBarrel if the XmlReader was pointing to an instance of it, or null if it was pointing
     * to XML null.
     * @throws XMLStreamException If an error occurs while reading the AppleBarrel.
     */
    @Generated
    public static AppleBarrel fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of AppleBarrel from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of AppleBarrel if the XmlReader was pointing to an instance of it, or null if it was pointing
     * to XML null.
     * @throws XMLStreamException If an error occurs while reading the AppleBarrel.
     */
    @Generated
    public static AppleBarrel fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "AppleBarrel" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            AppleBarrel deserializedAppleBarrel = new AppleBarrel();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("GoodApples".equals(elementName.getLocalPart())) {
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        elementName = reader.getElementName();
                        if ("Apple".equals(elementName.getLocalPart())) {
                            if (deserializedAppleBarrel.goodApples == null) {
                                deserializedAppleBarrel.goodApples = new ArrayList<>();
                            }
                            deserializedAppleBarrel.goodApples.add(reader.getStringElement());
                        } else {
                            reader.skipElement();
                        }
                    }
                } else if ("BadApples".equals(elementName.getLocalPart())) {
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        elementName = reader.getElementName();
                        if ("Apple".equals(elementName.getLocalPart())) {
                            if (deserializedAppleBarrel.badApples == null) {
                                deserializedAppleBarrel.badApples = new ArrayList<>();
                            }
                            deserializedAppleBarrel.badApples.add(reader.getStringElement());
                        } else {
                            reader.skipElement();
                        }
                    }
                } else {
                    reader.skipElement();
                }
            }

            return deserializedAppleBarrel;
        });
    }
}
