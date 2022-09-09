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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
    private static final class CorsWrapper implements XmlSerializable<CorsWrapper> {
        private final List<CorsRule> items;

        private CorsWrapper(List<CorsRule> items) {
            this.items = items;
        }

        @Override
        public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
            xmlWriter.writeStartElement("Cors");
            if (items != null) {
                for (CorsRule element : items) {
                    xmlWriter.writeXml(element);
                }
            }
            return xmlWriter.writeEndElement();
        }

        public static CorsWrapper fromXml(XmlReader xmlReader) throws XMLStreamException {
            return xmlReader.readObject(
                    "Cors",
                    reader -> {
                        List<CorsRule> items = null;

                        while (reader.nextElement() != XmlToken.END_ELEMENT) {
                            String elementName = reader.getElementName().getLocalPart();

                            if ("CorsRule".equals(elementName)) {
                                if (items == null) {
                                    items = new ArrayList<>();
                                }

                                items.add(CorsRule.fromXml(reader));
                            } else {
                                reader.nextElement();
                            }
                        }
                        return new CorsWrapper(items);
                    });
        }
    }

    private CorsWrapper cors;

    /*
     * The default version to use for requests to the Blob service if an incoming request's version is not specified.
     * Possible values include version 2008-10-27 and all more recent versions
     */
    private String defaultServiceVersion;

    /*
     * The Delete Retention Policy for the service
     */
    private RetentionPolicy deleteRetentionPolicy;

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
            this.cors = new CorsWrapper(new ArrayList<CorsRule>());
        }
        return this.cors.items;
    }

    /**
     * Set the cors property: The set of CORS rules.
     *
     * @param cors the cors value to set.
     * @return the StorageServiceProperties object itself.
     */
    public StorageServiceProperties setCors(List<CorsRule> cors) {
        this.cors = new CorsWrapper(cors);
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
        xmlWriter.writeStartElement("StorageServiceProperties");
        xmlWriter.writeXml(this.logging);
        xmlWriter.writeXml(this.hourMetrics);
        xmlWriter.writeXml(this.minuteMetrics);
        xmlWriter.writeXml(this.cors);
        xmlWriter.writeStringElement("DefaultServiceVersion", this.defaultServiceVersion);
        xmlWriter.writeXml(this.deleteRetentionPolicy);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of StorageServiceProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of StorageServiceProperties if the XmlReader was pointing to an instance of it, or null if it
     *     was pointing to XML null.
     */
    public static StorageServiceProperties fromXml(XmlReader xmlReader) throws XMLStreamException {
        return xmlReader.readObject(
                "StorageServiceProperties",
                reader -> {
                    Logging logging = null;
                    Metrics hourMetrics = null;
                    Metrics minuteMetrics = null;
                    List<CorsRule> cors = null;
                    String defaultServiceVersion = null;
                    RetentionPolicy deleteRetentionPolicy = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("Logging".equals(fieldName.getLocalPart())) {
                            logging = Logging.fromXml(reader);
                        } else if ("HourMetrics".equals(fieldName.getLocalPart())) {
                            hourMetrics = Metrics.fromXml(reader);
                        } else if ("MinuteMetrics".equals(fieldName.getLocalPart())) {
                            minuteMetrics = Metrics.fromXml(reader);
                        } else if ("Cors".equals(fieldName.getLocalPart())) {
                            if (cors == null) {
                                cors = new LinkedList<>();
                            }
                            cors.add(CorsRule.fromXml(reader));
                        } else if ("DefaultServiceVersion".equals(fieldName.getLocalPart())) {
                            defaultServiceVersion = reader.getStringElement();
                        } else if ("DeleteRetentionPolicy".equals(fieldName.getLocalPart())) {
                            deleteRetentionPolicy = RetentionPolicy.fromXml(reader);
                        } else {
                            reader.skipElement();
                        }
                    }
                    StorageServiceProperties deserializedValue = new StorageServiceProperties();
                    deserializedValue.logging = logging;
                    deserializedValue.hourMetrics = hourMetrics;
                    deserializedValue.minuteMetrics = minuteMetrics;
                    deserializedValue.setCors(cors);
                    deserializedValue.defaultServiceVersion = defaultServiceVersion;
                    deserializedValue.deleteRetentionPolicy = deleteRetentionPolicy;

                    return deserializedValue;
                });
    }
}
