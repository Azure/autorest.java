// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * The ModelWithByteProperty model.
 */
@Fluent
public final class ModelWithByteProperty implements XmlSerializable<ModelWithByteProperty> {
    /*
     * The Bytes property.
     */
    @Generated
    private byte[] bytes;

    /**
     * Creates an instance of ModelWithByteProperty class.
     */
    @Generated
    public ModelWithByteProperty() {
    }

    /**
     * Get the bytes property: The Bytes property.
     * 
     * @return the bytes value.
     */
    @Generated
    public byte[] getBytes() {
        return CoreUtils.clone(this.bytes);
    }

    /**
     * Set the bytes property: The Bytes property.
     * 
     * @param bytes the bytes value to set.
     * @return the ModelWithByteProperty object itself.
     */
    @Generated
    public ModelWithByteProperty setBytes(byte[] bytes) {
        this.bytes = CoreUtils.clone(bytes);
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
        rootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "ModelWithByteProperty" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeBinaryElement("Bytes", this.bytes);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ModelWithByteProperty from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of ModelWithByteProperty if the XmlReader was pointing to an instance of it, or null if it
     * was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ModelWithByteProperty.
     */
    @Generated
    public static ModelWithByteProperty fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ModelWithByteProperty from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of ModelWithByteProperty if the XmlReader was pointing to an instance of it, or null if it
     * was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ModelWithByteProperty.
     */
    @Generated
    public static ModelWithByteProperty fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "ModelWithByteProperty" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            ModelWithByteProperty deserializedModelWithByteProperty = new ModelWithByteProperty();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Bytes".equals(elementName.getLocalPart())) {
                    deserializedModelWithByteProperty.bytes = reader.getBinaryElement();
                } else {
                    reader.skipElement();
                }
            }

            return deserializedModelWithByteProperty;
        });
    }
}
