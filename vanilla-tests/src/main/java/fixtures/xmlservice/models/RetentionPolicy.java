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
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * the retention policy.
 */
@Fluent
public final class RetentionPolicy implements XmlSerializable<RetentionPolicy> {
    /*
     * Indicates whether a retention policy is enabled for the storage service
     */
    private boolean enabled;

    /*
     * Indicates the number of days that metrics or logging or soft-deleted data should be retained. All data older than
     * this value will be deleted
     */
    private Integer days;

    /**
     * Creates an instance of RetentionPolicy class.
     */
    public RetentionPolicy() {
    }

    /**
     * Get the enabled property: Indicates whether a retention policy is enabled for the storage service.
     * 
     * @return the enabled value.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Indicates whether a retention policy is enabled for the storage service.
     * 
     * @param enabled the enabled value to set.
     * @return the RetentionPolicy object itself.
     */
    public RetentionPolicy setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the days property: Indicates the number of days that metrics or logging or soft-deleted data should be
     * retained. All data older than this value will be deleted.
     * 
     * @return the days value.
     */
    public Integer getDays() {
        return this.days;
    }

    /**
     * Set the days property: Indicates the number of days that metrics or logging or soft-deleted data should be
     * retained. All data older than this value will be deleted.
     * 
     * @param days the days value to set.
     * @return the RetentionPolicy object itself.
     */
    public RetentionPolicy setDays(Integer days) {
        this.days = days;
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
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "RetentionPolicy" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeBooleanElement("Enabled", this.enabled);
        xmlWriter.writeNumberElement("Days", this.days);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of RetentionPolicy from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of RetentionPolicy if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the RetentionPolicy.
     */
    public static RetentionPolicy fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of RetentionPolicy from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of RetentionPolicy if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the RetentionPolicy.
     */
    public static RetentionPolicy fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "RetentionPolicy" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            RetentionPolicy deserializedRetentionPolicy = new RetentionPolicy();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Enabled".equals(elementName.getLocalPart())) {
                    deserializedRetentionPolicy.enabled = reader.getBooleanElement();
                } else if ("Days".equals(elementName.getLocalPart())) {
                    deserializedRetentionPolicy.days = reader.getNullableElement(Integer::parseInt);
                } else {
                    reader.skipElement();
                }
            }

            return deserializedRetentionPolicy;
        });
    }
}
