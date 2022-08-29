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

/** Azure Analytics Logging settings. */
@Fluent
public final class Logging implements XmlSerializable<Logging> {
    /*
     * The version of Storage Analytics to configure.
     */
    private String version;

    /*
     * Indicates whether all delete requests should be logged.
     */
    private boolean delete;

    /*
     * Indicates whether all read requests should be logged.
     */
    private boolean read;

    /*
     * Indicates whether all write requests should be logged.
     */
    private boolean write;

    /*
     * the retention policy
     */
    private RetentionPolicy retentionPolicy;

    /**
     * Get the version property: The version of Storage Analytics to configure.
     *
     * @return the version value.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version of Storage Analytics to configure.
     *
     * @param version the version value to set.
     * @return the Logging object itself.
     */
    public Logging setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the delete property: Indicates whether all delete requests should be logged.
     *
     * @return the delete value.
     */
    public boolean isDelete() {
        return this.delete;
    }

    /**
     * Set the delete property: Indicates whether all delete requests should be logged.
     *
     * @param delete the delete value to set.
     * @return the Logging object itself.
     */
    public Logging setDelete(boolean delete) {
        this.delete = delete;
        return this;
    }

    /**
     * Get the read property: Indicates whether all read requests should be logged.
     *
     * @return the read value.
     */
    public boolean isRead() {
        return this.read;
    }

    /**
     * Set the read property: Indicates whether all read requests should be logged.
     *
     * @param read the read value to set.
     * @return the Logging object itself.
     */
    public Logging setRead(boolean read) {
        this.read = read;
        return this;
    }

    /**
     * Get the write property: Indicates whether all write requests should be logged.
     *
     * @return the write value.
     */
    public boolean isWrite() {
        return this.write;
    }

    /**
     * Set the write property: Indicates whether all write requests should be logged.
     *
     * @param write the write value to set.
     * @return the Logging object itself.
     */
    public Logging setWrite(boolean write) {
        this.write = write;
        return this;
    }

    /**
     * Get the retentionPolicy property: the retention policy.
     *
     * @return the retentionPolicy value.
     */
    public RetentionPolicy getRetentionPolicy() {
        return this.retentionPolicy;
    }

    /**
     * Set the retentionPolicy property: the retention policy.
     *
     * @param retentionPolicy the retentionPolicy value to set.
     * @return the Logging object itself.
     */
    public Logging setRetentionPolicy(RetentionPolicy retentionPolicy) {
        this.retentionPolicy = retentionPolicy;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getVersion() == null) {
            throw new IllegalArgumentException("Missing required property version in model Logging");
        }
        if (getRetentionPolicy() == null) {
            throw new IllegalArgumentException("Missing required property retentionPolicy in model Logging");
        } else {
            getRetentionPolicy().validate();
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("Logging");
        xmlWriter.writeStringElement("Version", this.version);
        xmlWriter.writeBooleanElement("Delete", this.delete);
        xmlWriter.writeBooleanElement("Read", this.read);
        xmlWriter.writeBooleanElement("Write", this.write);
        xmlWriter.writeXml(this.retentionPolicy);
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Logging from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of Logging if the XmlReader was pointing to an instance of it, or null if it was pointing to
     *     XML null.
     */
    public static Logging fromXml(XmlReader xmlReader) {
        return xmlReader.readObject(
                "Logging",
                reader -> {
                    String version = null;
                    boolean delete = false;
                    boolean read = false;
                    boolean write = false;
                    RetentionPolicy retentionPolicy = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("Version".equals(fieldName.getLocalPart())) {
                            version = reader.getStringElement();
                        } else if ("Delete".equals(fieldName.getLocalPart())) {
                            delete = reader.getBooleanElement();
                        } else if ("Read".equals(fieldName.getLocalPart())) {
                            read = reader.getBooleanElement();
                        } else if ("Write".equals(fieldName.getLocalPart())) {
                            write = reader.getBooleanElement();
                        } else if ("RetentionPolicy".equals(fieldName.getLocalPart())) {
                            retentionPolicy = RetentionPolicy.fromXml(reader);
                        } else {
                            reader.skipElement();
                        }
                    }
                    Logging deserializedValue = new Logging();
                    deserializedValue.version = version;
                    deserializedValue.delete = delete;
                    deserializedValue.read = read;
                    deserializedValue.write = write;
                    deserializedValue.retentionPolicy = retentionPolicy;

                    return deserializedValue;
                });
    }
}
