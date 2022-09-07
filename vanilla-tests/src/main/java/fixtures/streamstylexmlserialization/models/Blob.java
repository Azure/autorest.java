// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/** An Azure Storage blob. */
@Fluent
public final class Blob implements XmlSerializable<Blob> {
    /*
     * The Name property.
     */
    private String name;

    /*
     * The Deleted property.
     */
    private boolean deleted;

    /*
     * The Snapshot property.
     */
    private String snapshot;

    /*
     * Properties of a blob
     */
    private BlobProperties properties;

    /*
     * Dictionary of <string>
     */
    private Map<String, String> metadata;

    /**
     * Get the name property: The Name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The Name property.
     *
     * @param name the name value to set.
     * @return the Blob object itself.
     */
    public Blob setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the deleted property: The Deleted property.
     *
     * @return the deleted value.
     */
    public boolean isDeleted() {
        return this.deleted;
    }

    /**
     * Set the deleted property: The Deleted property.
     *
     * @param deleted the deleted value to set.
     * @return the Blob object itself.
     */
    public Blob setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    /**
     * Get the snapshot property: The Snapshot property.
     *
     * @return the snapshot value.
     */
    public String getSnapshot() {
        return this.snapshot;
    }

    /**
     * Set the snapshot property: The Snapshot property.
     *
     * @param snapshot the snapshot value to set.
     * @return the Blob object itself.
     */
    public Blob setSnapshot(String snapshot) {
        this.snapshot = snapshot;
        return this;
    }

    /**
     * Get the properties property: Properties of a blob.
     *
     * @return the properties value.
     */
    public BlobProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of a blob.
     *
     * @param properties the properties value to set.
     * @return the Blob object itself.
     */
    public Blob setProperties(BlobProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Get the metadata property: Dictionary of &lt;string&gt;.
     *
     * @return the metadata value.
     */
    public Map<String, String> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Dictionary of &lt;string&gt;.
     *
     * @param metadata the metadata value to set.
     * @return the Blob object itself.
     */
    public Blob setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getName() == null) {
            throw new IllegalArgumentException("Missing required property name in model Blob");
        }
        if (getSnapshot() == null) {
            throw new IllegalArgumentException("Missing required property snapshot in model Blob");
        }
        if (getProperties() == null) {
            throw new IllegalArgumentException("Missing required property properties in model Blob");
        } else {
            getProperties().validate();
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        xmlWriter.writeStartElement("Blob");
        xmlWriter.writeStringElement("Name", this.name);
        xmlWriter.writeBooleanElement("Deleted", this.deleted);
        xmlWriter.writeStringElement("Snapshot", this.snapshot);
        xmlWriter.writeXml(this.properties);
        if (this.metadata != null) {
            xmlWriter.writeStartElement("Metadata");
            this.metadata.forEach((key, value) -> xmlWriter.writeStringElement("key", value));
            xmlWriter.writeEndElement();
        }
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of Blob from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of Blob if the XmlReader was pointing to an instance of it, or null if it was pointing to XML
     *     null.
     */
    public static Blob fromXml(XmlReader xmlReader) throws XMLStreamException {
        return xmlReader.readObject(
                "Blob",
                reader -> {
                    String name = null;
                    boolean deleted = false;
                    String snapshot = null;
                    BlobProperties properties = null;
                    Map<String, String> metadata = null;
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName fieldName = reader.getElementName();

                        if ("Name".equals(fieldName.getLocalPart())) {
                            name = reader.getStringElement();
                        } else if ("Deleted".equals(fieldName.getLocalPart())) {
                            deleted = reader.getBooleanElement();
                        } else if ("Snapshot".equals(fieldName.getLocalPart())) {
                            snapshot = reader.getStringElement();
                        } else if ("Properties".equals(fieldName.getLocalPart())) {
                            properties = BlobProperties.fromXml(reader);
                        } else if ("Metadata".equals(fieldName.getLocalPart())) {
                            if (metadata == null) {
                                metadata = new LinkedHashMap<>();
                            }
                            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                                metadata.put(reader.getElementName().getLocalPart(), reader.getStringElement());
                            }
                        } else {
                            reader.skipElement();
                        }
                    }
                    Blob deserializedValue = new Blob();
                    deserializedValue.name = name;
                    deserializedValue.deleted = deleted;
                    deserializedValue.snapshot = snapshot;
                    deserializedValue.properties = properties;
                    deserializedValue.metadata = metadata;

                    return deserializedValue;
                });
    }
}
