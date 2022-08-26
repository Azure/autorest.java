// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlWriter;
import java.util.ArrayList;
import java.util.List;

/** Data about a slideshow. */
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
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("slideshow");
        xmlWriter.writeStringAttribute("title", this.title);
        xmlWriter.writeStringAttribute("date", this.date);
        xmlWriter.writeStringAttribute("author", this.author);
        if (this.slides != null) {
            this.slides.forEach(element -> xmlWriter.writeXml(element));
        }
        return xmlWriter.writeEndElement();
    }
}
