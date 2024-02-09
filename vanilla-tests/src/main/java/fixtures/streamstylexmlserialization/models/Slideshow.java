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
 * Data about a slideshow.
 */
@Fluent
public final class Slideshow implements XmlSerializable<Slideshow> {
    /*
     * The title property.
     */
    private String title;

    /*
     * The date property.
     */
    private String date;

    /*
     * The author property.
     */
    private String author;

    /*
     * The slides property.
     */
    private List<Slide> slides = new ArrayList<>();

    /**
     * Creates an instance of Slideshow class.
     */
    public Slideshow() {
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
     * @return the Slideshow object itself.
     */
    public Slideshow setTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get the date property: The date property.
     * 
     * @return the date value.
     */
    public String getDate() {
        return this.date;
    }

    /**
     * Set the date property: The date property.
     * 
     * @param date the date value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setDate(String date) {
        this.date = date;
        return this;
    }

    /**
     * Get the author property: The author property.
     * 
     * @return the author value.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Set the author property: The author property.
     * 
     * @param author the author value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setAuthor(String author) {
        this.author = author;
        return this;
    }

    /**
     * Get the slides property: The slides property.
     * 
     * @return the slides value.
     */
    public List<Slide> getSlides() {
        return this.slides;
    }

    /**
     * Set the slides property: The slides property.
     * 
     * @param slides the slides value to set.
     * @return the Slideshow object itself.
     */
    public Slideshow setSlides(List<Slide> slides) {
        this.slides = slides;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getSlides() != null) {
            getSlides().forEach(e -> e.validate());
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "slideshow" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringAttribute("title", this.title);
        xmlWriter.writeStringAttribute("date", this.date);
        xmlWriter.writeStringAttribute("author", this.author);
        if (this.slides != null) {
            xmlWriter.writeStartElement("slides");
            for (Slide element : this.slides) {
                xmlWriter.writeXml(element, "slide");
            }
            xmlWriter.writeEndElement();
        }
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Slideshow from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of Slideshow if the XmlReader was pointing to an instance of it, or null if it was pointing
     * to XML null.
     * @throws XMLStreamException If an error occurs while reading the Slideshow.
     */
    public static Slideshow fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of Slideshow from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of Slideshow if the XmlReader was pointing to an instance of it, or null if it was pointing
     * to XML null.
     * @throws XMLStreamException If an error occurs while reading the Slideshow.
     */
    public static Slideshow fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "slideshow" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            Slideshow deserializedSlideshow = new Slideshow();
            deserializedSlideshow.title = reader.getStringAttribute(null, "title");
            deserializedSlideshow.date = reader.getStringAttribute(null, "date");
            deserializedSlideshow.author = reader.getStringAttribute(null, "author");
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("slides".equals(elementName.getLocalPart())) {
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        elementName = reader.getElementName();
                        if ("slide".equals(elementName.getLocalPart())) {
                            if (deserializedSlideshow.slides == null) {
                                deserializedSlideshow.slides = new ArrayList<>();
                            }
                            deserializedSlideshow.slides.add(Slide.fromXml(reader, "slide"));
                        } else {
                            reader.skipElement();
                        }
                    }
                } else {
                    reader.skipElement();
                }
            }

            return deserializedSlideshow;
        });
    }
}
