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
 * The Error model.
 */
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
     * Creates an instance of Error class.
     */
    public Error() {}

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
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "Error" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeNumberElement("status", this.status);
        xmlWriter.writeStringElement("message", this.message);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Error from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of Error if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Error.
     */
    public static Error fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of Error from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of Error if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Error.
     */
    public static Error fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "Error" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            Error deserializedError = new Error();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("status".equals(elementName.getLocalPart())) {
                    deserializedError.status = reader.getNullableElement(Integer::parseInt);
                } else if ("message".equals(elementName.getLocalPart())) {
                    deserializedError.message = reader.getStringElement();
                } else {
                    reader.skipElement();
                }
            }

            return deserializedError;
        });
    }
}
