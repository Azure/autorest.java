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

/** Storage Service Properties. */
@Fluent
public final class StorageServiceProperties implements XmlSerializable<StorageServiceProperties> {
    /*
     * Azure Analytics Logging settings
     */
    private Logging logging;

    /*
     * A summary of request statistics grouped by API in hourly aggregates for blobs
     */
    private Metrics hourMetrics;

    /*
     * a summary of request statistics grouped by API in minute aggregates for blobs
     */
    private Metrics minuteMetrics;

    /*
     * The set of CORS rules.
     */
    private List<CorsRule> cors = new ArrayList<>();

    /*
     * The default version to use for requests to the Blob service if an incoming request's version is not specified.
     * Possible values include version 2008-10-27 and all more recent versions
     */
    private String defaultServiceVersion;

    /*
     * The Delete Retention Policy for the service
     */
    private RetentionPolicy deleteRetentionPolicy;

    /** Creates an instance of StorageServiceProperties class. */
    public StorageServiceProperties() {}

    /**
     * Get the logging property: Azure Analytics Logging settings.
     *
     * @return the logging value.
     */
    public Logging getLogging() {
        return this.logging;
    }

    /**
     * Set the logging property: Azure Analytics Logging settings.
     *
     * @param logging the logging value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setLogging(Logging logging) {
        this.logging = logging;
        return this;
    }

    /**
     * Get the hourMetrics property: A summary of request statistics grouped by API in hourly aggregates for blobs.
     *
     * @return the hourMetrics value.
     */
    public Metrics getHourMetrics() {
        return this.hourMetrics;
    }

    /**
     * Set the hourMetrics property: A summary of request statistics grouped by API in hourly aggregates for blobs.
     *
     * @param hourMetrics the hourMetrics value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setHourMetrics(Metrics hourMetrics) {
        this.hourMetrics = hourMetrics;
        return this;
    }

    /**
     * Get the minuteMetrics property: a summary of request statistics grouped by API in minute aggregates for blobs.
     *
     * @return the minuteMetrics value.
     */
    public Metrics getMinuteMetrics() {
        return this.minuteMetrics;
    }

    /**
     * Set the minuteMetrics property: a summary of request statistics grouped by API in minute aggregates for blobs.
     *
     * @param minuteMetrics the minuteMetrics value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setMinuteMetrics(Metrics minuteMetrics) {
        this.minuteMetrics = minuteMetrics;
        return this;
    }

    /**
     * Get the cors property: The set of CORS rules.
     *
     * @return the cors value.
     */
    public List<CorsRule> getCors() {
        if (this.cors == null) {
            this.cors = new ArrayList<>();
        }
        return this.cors;
    }

    /**
     * Set the cors property: The set of CORS rules.
     *
     * @param cors the cors value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setCors(List<CorsRule> cors) {
        this.cors = cors;
        return this;
    }

    /**
     * Get the defaultServiceVersion property: The default version to use for requests to the Blob service if an
     * incoming request's version is not specified. Possible values include version 2008-10-27 and all more recent
     * versions.
     *
     * @return the defaultServiceVersion value.
     */
    public String getDefaultServiceVersion() {
        return this.defaultServiceVersion;
    }

    /**
     * Set the defaultServiceVersion property: The default version to use for requests to the Blob service if an
     * incoming request's version is not specified. Possible values include version 2008-10-27 and all more recent
     * versions.
     *
     * @param defaultServiceVersion the defaultServiceVersion value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setDefaultServiceVersion(String defaultServiceVersion) {
        this.defaultServiceVersion = defaultServiceVersion;
        return this;
    }

    /**
     * Get the deleteRetentionPolicy property: The Delete Retention Policy for the service.
     *
     * @return the deleteRetentionPolicy value.
     */
    public RetentionPolicy getDeleteRetentionPolicy() {
        return this.deleteRetentionPolicy;
    }

    /**
     * Set the deleteRetentionPolicy property: The Delete Retention Policy for the service.
     *
     * @param deleteRetentionPolicy the deleteRetentionPolicy value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setDeleteRetentionPolicy(RetentionPolicy deleteRetentionPolicy) {
        this.deleteRetentionPolicy = deleteRetentionPolicy;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getLogging() != null) {
            getLogging().validate();
        }
        if (getHourMetrics() != null) {
            getHourMetrics().validate();
        }
        if (getMinuteMetrics() != null) {
            getMinuteMetrics().validate();
        }
        if (getCors() != null) {
            getCors().forEach(e -> e.validate());
        }
        if (getDeleteRetentionPolicy() != null) {
            getDeleteRetentionPolicy().validate();
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "StorageServiceProperties" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeXml(this.logging, "Logging");
        xmlWriter.writeXml(this.hourMetrics, "HourMetrics");
        xmlWriter.writeXml(this.minuteMetrics, "MinuteMetrics");
        if (this.cors != null) {
            xmlWriter.writeStartElement("Cors");
            for (CorsRule element : this.cors) {
                xmlWriter.writeXml(element, "CorsRule");
            }
            xmlWriter.writeEndElement();
        }
        xmlWriter.writeStringElement("DefaultServiceVersion", this.defaultServiceVersion);
        xmlWriter.writeXml(this.deleteRetentionPolicy, "DeleteRetentionPolicy");
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of StorageServiceProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of StorageServiceProperties if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the StorageServiceProperties.
     */
    public static StorageServiceProperties fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of StorageServiceProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     *     cases where the model can deserialize from different root element names.
     * @return An instance of StorageServiceProperties if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the StorageServiceProperties.
     */
    public static StorageServiceProperties fromXml(XmlReader xmlReader, String rootElementName)
            throws XMLStreamException {
        String finalRootElementName =
                CoreUtils.isNullOrEmpty(rootElementName) ? "StorageServiceProperties" : rootElementName;
        return xmlReader.readObject(
                finalRootElementName,
                reader -> {
                    StorageServiceProperties deserializedStorageServiceProperties = new StorageServiceProperties();
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName elementName = reader.getElementName();

                        if ("Logging".equals(elementName.getLocalPart())) {
                            deserializedStorageServiceProperties.logging = Logging.fromXml(reader, "Logging");
                        } else if ("HourMetrics".equals(elementName.getLocalPart())) {
                            deserializedStorageServiceProperties.hourMetrics = Metrics.fromXml(reader, "HourMetrics");
                        } else if ("MinuteMetrics".equals(elementName.getLocalPart())) {
                            deserializedStorageServiceProperties.minuteMetrics =
                                    Metrics.fromXml(reader, "MinuteMetrics");
                        } else if ("Cors".equals(elementName.getLocalPart())) {
                            if (deserializedStorageServiceProperties.cors == null) {
                                deserializedStorageServiceProperties.cors = new ArrayList<>();
                            }
                            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                                elementName = reader.getElementName();
                                if ("CorsRule".equals(elementName.getLocalPart())) {
                                    deserializedStorageServiceProperties.cors.add(CorsRule.fromXml(reader, "CorsRule"));
                                } else {
                                    reader.skipElement();
                                }
                            }
                        } else if ("DefaultServiceVersion".equals(elementName.getLocalPart())) {
                            deserializedStorageServiceProperties.defaultServiceVersion = reader.getStringElement();
                        } else if ("DeleteRetentionPolicy".equals(elementName.getLocalPart())) {
                            deserializedStorageServiceProperties.deleteRetentionPolicy =
                                    RetentionPolicy.fromXml(reader, "DeleteRetentionPolicy");
                        } else {
                            reader.skipElement();
                        }
                    }

                    return deserializedStorageServiceProperties;
                });
    }
}
