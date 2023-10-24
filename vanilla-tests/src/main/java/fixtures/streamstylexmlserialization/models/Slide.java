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
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * A slide in a slideshow.
 */
@Fluent
public final class Slide implements XmlSerializable<Slide> {
    /*
     * The type property.
     */
    private String type;

    /*
     * The title property.
     */
    private String title;

    /*
     * The items property.
     */
    private List<String> items = new ArrayList<>();

    /**
     * Creates an instance of Slide class.
     */
    public Slide() {}

    /**
     * Get the type property: The type property.
     * 
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     * 
     * @param type the type value to set.
     * @return the Slide object itself.
     */
    public Slide setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Get the title property: The title property.
     * 
     * @return the title value.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Set the title property: The title property.
     * 
     * @param title the title value to set.
     * @return the Slide object itself.
     */
    public Slide setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the items property: The items property.
     * 
     * @return the items value.
     */
    public List<String> getItems() {
        return this.items;
    }

    /**
     * Set the items property: The items property.
     * 
     * @param items the items value to set.
     * @return the Slide object itself.
     */
    public Slide setItems(List<String> items) {
        this.items = items;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "slide" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringAttribute("type", this.type);
        xmlWriter.writeStringElement("title", this.title);
        if (this.items != null) {
            xmlWriter.writeStartElement("items");
            for (String element : this.items) {
                xmlWriter.writeStringElement("item", element);
            }
            xmlWriter.writeEndElement();
        }
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Slide from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of Slide if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Slide.
     */
    public static Slide fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of Slide from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of Slide if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Slide.
     */
    public static Slide fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "slide" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            Slide deserializedSlide = new Slide();
            deserializedSlide.type = reader.getStringAttribute(null, "type");
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("title".equals(elementName.getLocalPart())) {
                    deserializedSlide.title = reader.getStringElement();
                } else if ("items".equals(elementName.getLocalPart())) {
                    if (deserializedSlide.items == null) {
                        deserializedSlide.items = new ArrayList<>();
                    }
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        elementName = reader.getElementName();
                        if ("item".equals(elementName.getLocalPart())) {
                            deserializedSlide.items.add(reader.getStringElement());
                        } else {
                            reader.skipElement();
                        }
                    }
                } else {
                    reader.skipElement();
                }
            }

            return deserializedSlide;
        });
    }
}
