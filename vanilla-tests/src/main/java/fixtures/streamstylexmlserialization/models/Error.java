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

/** The Error model. */
@Fluent
public final class Error implements XmlSerializable<Error> {
    /*
     * The status property.
     */
    private Integer status;

    /*
     * The message property.
     */
    private String message;

    /**
     * Get the status property: The status property.
     *
     * @return the status value.
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     *
     * @param status the status value to set.
     * @return the Error object itself.
     */
    public Error setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the Error object itself.
     */
    public Error setMessage(String message) {
        this.message = message;
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
        xmlWriter.writeStartElement("Error");
        xmlWriter.writeNumberElement("status", this.status);
        xmlWriter.writeStringElement("message", this.message);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Error from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of Error if the XmlReader was pointing to an instance of it, or null if it was pointing to
     *     XML null.
     */
    public static Error fromXml(XmlReader xmlReader) throws XMLStreamException {
        return xmlReader.readObject(
                "Error",
                reader -> {
                    Integer status = null;
                    String message = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("status".equals(fieldName.getLocalPart())) {
                            status = reader.getNullableElement(Integer::parseInt);
                        } else if ("message".equals(fieldName.getLocalPart())) {
                            message = reader.getStringElement();
                        } else {
                            reader.skipElement();
                        }
                    }
                    Error deserializedValue = new Error();
                    deserializedValue.status = status;
                    deserializedValue.message = message;

                    return deserializedValue;
                });
    }
}
