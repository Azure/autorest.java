// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/** Properties of a container. */
@Fluent
public final class ContainerProperties implements XmlSerializable<ContainerProperties> {
    /*
     * The Last-Modified property.
     */
    private DateTimeRfc1123 lastModified;

    /*
     * The Etag property.
     */
    private String etag;

    /*
     * The LeaseStatus property.
     */
    private LeaseStatusType leaseStatus;

    /*
     * The LeaseState property.
     */
    private LeaseStateType leaseState;

    /*
     * The LeaseDuration property.
     */
    private LeaseDurationType leaseDuration;

    /*
     * The PublicAccess property.
     */
    private PublicAccessType publicAccess;

    /** Creates an instance of ContainerProperties class. */
    public ContainerProperties() {}

    /**
     * Get the lastModified property: The Last-Modified property.
     *
     * @return the lastModified value.
     */
    public OffsetDateTime getLastModified() {
        if (this.lastModified == null) {
            return null;
        }
        return this.lastModified.getDateTime();
    }

    /**
     * Set the lastModified property: The Last-Modified property.
     *
     * @param lastModified the lastModified value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLastModified(OffsetDateTime lastModified) {
        if (lastModified == null) {
            this.lastModified = null;
        } else {
            this.lastModified = new DateTimeRfc1123(lastModified);
        }
        return this;
    }

    /**
     * Get the etag property: The Etag property.
     *
     * @return the etag value.
     */
    public String getEtag() {
        return this.etag;
    }

    /**
     * Set the etag property: The Etag property.
     *
     * @param etag the etag value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the leaseStatus property: The LeaseStatus property.
     *
     * @return the leaseStatus value.
     */
    public LeaseStatusType getLeaseStatus() {
        return this.leaseStatus;
    }

    /**
     * Set the leaseStatus property: The LeaseStatus property.
     *
     * @param leaseStatus the leaseStatus value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseStatus(LeaseStatusType leaseStatus) {
        this.leaseStatus = leaseStatus;
        return this;
    }

    /**
     * Get the leaseState property: The LeaseState property.
     *
     * @return the leaseState value.
     */
    public LeaseStateType getLeaseState() {
        return this.leaseState;
    }

    /**
     * Set the leaseState property: The LeaseState property.
     *
     * @param leaseState the leaseState value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseState(LeaseStateType leaseState) {
        this.leaseState = leaseState;
        return this;
    }

    /**
     * Get the leaseDuration property: The LeaseDuration property.
     *
     * @return the leaseDuration value.
     */
    public LeaseDurationType getLeaseDuration() {
        return this.leaseDuration;
    }

    /**
     * Set the leaseDuration property: The LeaseDuration property.
     *
     * @param leaseDuration the leaseDuration value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseDuration(LeaseDurationType leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the publicAccess property: The PublicAccess property.
     *
     * @return the publicAccess value.
     */
    public PublicAccessType getPublicAccess() {
        return this.publicAccess;
    }

    /**
     * Set the publicAccess property: The PublicAccess property.
     *
     * @param publicAccess the publicAccess value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setPublicAccess(PublicAccessType publicAccess) {
        this.publicAccess = publicAccess;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getLastModified() == null) {
            throw new IllegalArgumentException("Missing required property lastModified in model ContainerProperties");
        }
        if (getEtag() == null) {
            throw new IllegalArgumentException("Missing required property etag in model ContainerProperties");
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "ContainerProperties" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Last-Modified", Objects.toString(this.lastModified, null));
        xmlWriter.writeStringElement("Etag", this.etag);
        xmlWriter.writeStringElement("LeaseStatus", Objects.toString(this.leaseStatus, null));
        xmlWriter.writeStringElement("LeaseState", Objects.toString(this.leaseState, null));
        xmlWriter.writeStringElement("LeaseDuration", Objects.toString(this.leaseDuration, null));
        xmlWriter.writeStringElement("PublicAccess", Objects.toString(this.publicAccess, null));
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ContainerProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of ContainerProperties if the XmlReader was pointing to an instance of it, or null if it was
     *     pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ContainerProperties.
     */
    public static ContainerProperties fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ContainerProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     *     cases where the model can deserialize from different root element names.
     * @return An instance of ContainerProperties if the XmlReader was pointing to an instance of it, or null if it was
     *     pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the ContainerProperties.
     */
    public static ContainerProperties fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName =
                CoreUtils.isNullOrEmpty(rootElementName) ? "ContainerProperties" : rootElementName;
        return xmlReader.readObject(
                finalRootElementName,
                reader -> {
                    ContainerProperties deserializedContainerProperties = new ContainerProperties();
                    while (reader.nextElement() != XmlToken.END_ELEMENT) {
                        QName elementName = reader.getElementName();

                        if ("Last-Modified".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.setLastModified(
                                    reader.getNullableElement(DateTimeRfc1123::new).getDateTime());
                        } else if ("Etag".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.etag = reader.getStringElement();
                        } else if ("LeaseStatus".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.leaseStatus =
                                    reader.getNullableElement(LeaseStatusType::fromString);
                        } else if ("LeaseState".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.leaseState =
                                    reader.getNullableElement(LeaseStateType::fromString);
                        } else if ("LeaseDuration".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.leaseDuration =
                                    reader.getNullableElement(LeaseDurationType::fromString);
                        } else if ("PublicAccess".equals(elementName.getLocalPart())) {
                            deserializedContainerProperties.publicAccess =
                                    reader.getNullableElement(PublicAccessType::fromString);
                        } else {
                            reader.skipElement();
                        }
                    }

                    return deserializedContainerProperties;
                });
    }
}
