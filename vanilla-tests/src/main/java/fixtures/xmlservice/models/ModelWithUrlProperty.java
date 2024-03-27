// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.net.URL;
import java.util.Objects;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * The ModelWithUrlProperty model.
 */
@Fluent
public final class ModelWithUrlProperty implements XmlSerializable<ModelWithUrlProperty> {
    /*
     * The Url property.
     */
    private URL url;

    /**
     * Creates an instance of ModelWithUrlProperty class.
     */
    public ModelWithUrlProperty() {
    }

    /**
     * Get the url property: The Url property.
     * 
     * @return the url value.
     */
    public URL getUrl() {
        return this.url;
    }

    /**
     * Set the url property: The Url property.
     * 
     * @param url the url value to set.
     * @return the ModelWithUrlProperty object itself.
     */
    public ModelWithUrlProperty setUrl(URL url) {
        this.url = url;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "ModelWithUrlProperty" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Url", Objects.toString(this.url, null));
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ModelWithUrlProperty from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of ModelWithUrlProperty if the XmlReader was pointing to an instance of it, or null if it
     * was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ModelWithUrlProperty.
     */
    public static ModelWithUrlProperty fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ModelWithUrlProperty from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of ModelWithUrlProperty if the XmlReader was pointing to an instance of it, or null if it
     * was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ModelWithUrlProperty.
     */
    public static ModelWithUrlProperty fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = CoreUtils.isNullOrEmpty(rootElementName) ? "ModelWithUrlProperty" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            ModelWithUrlProperty deserializedModelWithUrlProperty = new ModelWithUrlProperty();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Url".equals(elementName.getLocalPart())) {
                    deserializedModelWithUrlProperty.url = reader.getNullableElement(URL::new);
                } else {
                    reader.skipElement();
                }
            }

            return deserializedModelWithUrlProperty;
        });
    }
}
