// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;

import javax.xml.namespace.QName;
import java.time.OffsetDateTime;
import java.util.Objects;

/** A banana. */
@Fluent
public final class Banana implements XmlSerializable<Banana> {
    /*
     * The name property.
     */
    private String name;

    /*
     * The flavor property.
     */
    private String flavor;

    /*
     * The time at which you should reconsider eating this banana
     */
    private OffsetDateTime expiration;

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the Banana object itself.
     */
    public Banana setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the flavor property: The flavor property.
     *
     * @return the flavor value.
     */
    public String getFlavor() {
        return this.flavor;
    }

    /**
     * Set the flavor property: The flavor property.
     *
     * @param flavor the flavor value to set.
     * @return the Banana object itself.
     */
    public Banana setFlavor(String flavor) {
        this.flavor = flavor;
        return this;
    }

    /**
     * Get the expiration property: The time at which you should reconsider eating this banana.
     *
     * @return the expiration value.
     */
    public OffsetDateTime getExpiration() {
        return this.expiration;
    }

    /**
     * Set the expiration property: The time at which you should reconsider eating this banana.
     *
     * @param expiration the expiration value to set.
     * @return the Banana object itself.
     */
    public Banana setExpiration(OffsetDateTime expiration) {
        this.expiration = expiration;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("banana");
        xmlWriter.writeStringElement("name", this.name);
        xmlWriter.writeStringElement("flavor", this.flavor);
        xmlWriter.writeStringElement("expiration", Objects.toString(this.expiration, null));
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Banana from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of Banana if the XmlReader was pointing to an instance of it, or null if it was pointing to
     *     XML null.
     */
    public static Banana fromXml(XmlReader xmlReader) {
        return xmlReader.readObject(
                "banana",
                reader -> {
                    String name = null;
                    String flavor = null;
                    OffsetDateTime expiration = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("name".equals(fieldName.getLocalPart())) {
                            name = reader.getStringElement();
                        } else if ("flavor".equals(fieldName.getLocalPart())) {
                            flavor = reader.getStringElement();
                        } else if ("expiration".equals(fieldName.getLocalPart())) {
                            expiration = reader.getNullableElement(OffsetDateTime::parse);
                        } else {
                            reader.skipElement();
                        }
                    }
                    Banana deserializedValue = new Banana();
                    deserializedValue.name = name;
                    deserializedValue.flavor = flavor;
                    deserializedValue.expiration = expiration;

                    return deserializedValue;
                });
    }
}
