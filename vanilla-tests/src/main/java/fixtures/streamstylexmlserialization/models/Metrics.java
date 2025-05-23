// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * The Metrics model.
 */
@Fluent
public final class Metrics implements XmlSerializable<Metrics> {
    /*
     * The version of Storage Analytics to configure.
     */
    @Generated
    private String version;

    /*
     * Indicates whether metrics are enabled for the Blob service.
     */
    @Generated
    private boolean enabled;

    /*
     * Indicates whether metrics should generate summary statistics for called API operations.
     */
    @Generated
    private Boolean includeAPIs;

    /*
     * the retention policy
     */
    @Generated
    private RetentionPolicy retentionPolicy;

    /**
     * Creates an instance of Metrics class.
     */
    @Generated
    public Metrics() {
    }

    /**
     * Get the version property: The version of Storage Analytics to configure.
     * 
     * @return the version value.
     */
    @Generated
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version of Storage Analytics to configure.
     * 
     * @param version the version value to set.
     * @return the Metrics object itself.
     */
    @Generated
    public Metrics setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the enabled property: Indicates whether metrics are enabled for the Blob service.
     * 
     * @return the enabled value.
     */
    @Generated
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Indicates whether metrics are enabled for the Blob service.
     * 
     * @param enabled the enabled value to set.
     * @return the Metrics object itself.
     */
    @Generated
    public Metrics setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the includeAPIs property: Indicates whether metrics should generate summary statistics for called API
     * operations.
     * 
     * @return the includeAPIs value.
     */
    @Generated
    public Boolean isIncludeAPIs() {
        return this.includeAPIs;
    }

    /**
     * Set the includeAPIs property: Indicates whether metrics should generate summary statistics for called API
     * operations.
     * 
     * @param includeAPIs the includeAPIs value to set.
     * @return the Metrics object itself.
     */
    @Generated
    public Metrics setIncludeAPIs(Boolean includeAPIs) {
        this.includeAPIs = includeAPIs;
        return this;
    }

    /**
     * Get the retentionPolicy property: the retention policy.
     * 
     * @return the retentionPolicy value.
     */
    @Generated
    public RetentionPolicy getRetentionPolicy() {
        return this.retentionPolicy;
    }

    /**
     * Set the retentionPolicy property: the retention policy.
     * 
     * @param retentionPolicy the retentionPolicy value to set.
     * @return the Metrics object itself.
     */
    @Generated
    public Metrics setRetentionPolicy(RetentionPolicy retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getRetentionPolicy() != null) {
            getRetentionPolicy().validate();
        }
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = rootElementName == null || rootElementName.isEmpty() ? "Metrics" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Version", this.version);
        xmlWriter.writeBooleanElement("Enabled", this.enabled);
        xmlWriter.writeBooleanElement("IncludeAPIs", this.includeAPIs);
        xmlWriter.writeXml(this.retentionPolicy, "RetentionPolicy");
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Metrics from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of Metrics if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Metrics.
     */
    @Generated
    public static Metrics fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of Metrics from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of Metrics if the XmlReader was pointing to an instance of it, or null if it was pointing to
     * XML null.
     * @throws XMLStreamException If an error occurs while reading the Metrics.
     */
    @Generated
    public static Metrics fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "Metrics" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            Metrics deserializedMetrics = new Metrics();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Version".equals(elementName.getLocalPart())) {
                    deserializedMetrics.version = reader.getStringElement();
                } else if ("Enabled".equals(elementName.getLocalPart())) {
                    deserializedMetrics.enabled = reader.getBooleanElement();
                } else if ("IncludeAPIs".equals(elementName.getLocalPart())) {
                    deserializedMetrics.includeAPIs = reader.getNullableElement(Boolean::parseBoolean);
                } else if ("RetentionPolicy".equals(elementName.getLocalPart())) {
                    deserializedMetrics.retentionPolicy = RetentionPolicy.fromXml(reader, "RetentionPolicy");
                } else {
                    reader.skipElement();
                }
            }

            return deserializedMetrics;
        });
    }
}
