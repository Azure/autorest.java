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
import javax.xml.stream.XMLStreamException;

/** The JsonOutput model. */
@Fluent
public final class JsonOutput implements XmlSerializable<JsonOutput> {
    /*
     * The id property.
     */
    private Integer id;

    /** Creates an instance of JsonOutput class. */
    public JsonOutput() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the JsonOutput object itself.
     */
    public JsonOutput setId(Integer id) {
        this.id = id;
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
        xmlWriter.writeStartElement("JsonOutput");
        xmlWriter.writeNumberElement("id", this.id);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of JsonOutput from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of JsonOutput if the XmlReader was pointing to an instance of it, or null if it was pointing
     *     to XML null.
     */
    public static JsonOutput fromXml(XmlReader xmlReader) throws XMLStreamException {
        return xmlReader.readObject(
                "JsonOutput",
                reader -> {
                    Integer id = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("id".equals(fieldName.getLocalPart())) {
                            id = reader.getNullableElement(Integer::parseInt);
                        } else {
                            reader.skipElement();
                        }
                    }

                    return deserializedJsonOutput;
                });
    }
}
