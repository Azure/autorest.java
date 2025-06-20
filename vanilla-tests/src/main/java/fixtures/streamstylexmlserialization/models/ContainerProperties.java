// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.logging.ClientLogger;
import com.azure.xml.XmlReader;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlToken;
import com.azure.xml.XmlWriter;
import java.time.OffsetDateTime;
import java.util.Objects;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamException;

/**
 * Properties of a container.
 */
@Fluent
public final class ContainerProperties implements XmlSerializable<ContainerProperties> {
    /*
     * The Last-Modified property.
     */
    @Generated
    private DateTimeRfc1123 lastModified;

    /*
     * The Etag property.
     */
    @Generated
    private String etag;

    /*
     * The LeaseStatus property.
     */
    @Generated
    private LeaseStatusType leaseStatus;

    /*
     * The LeaseState property.
     */
    @Generated
    private LeaseStateType leaseState;

    /*
     * The LeaseDuration property.
     */
    @Generated
    private LeaseDurationType leaseDuration;

    /*
     * The PublicAccess property.
     */
    @Generated
    private PublicAccessType publicAccess;

    /**
     * Creates an instance of ContainerProperties class.
     */
    @Generated
    public ContainerProperties() {
    }

    /**
     * Get the lastModified property: The Last-Modified property.
     * 
     * @return the lastModified value.
     */
    @Generated
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
    @Generated
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
    @Generated
    public String getEtag() {
        return this.etag;
    }

    /**
     * Set the etag property: The Etag property.
     * 
     * @param etag the etag value to set.
     * @return the ContainerProperties object itself.
     */
    @Generated
    public ContainerProperties setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the leaseStatus property: The LeaseStatus property.
     * 
     * @return the leaseStatus value.
     */
    @Generated
    public LeaseStatusType getLeaseStatus() {
        return this.leaseStatus;
    }

    /**
     * Set the leaseStatus property: The LeaseStatus property.
     * 
     * @param leaseStatus the leaseStatus value to set.
     * @return the ContainerProperties object itself.
     */
    @Generated
    public ContainerProperties setLeaseStatus(LeaseStatusType leaseStatus) {
        this.leaseStatus = leaseStatus;
        return this;
    }

    /**
     * Get the leaseState property: The LeaseState property.
     * 
     * @return the leaseState value.
     */
    @Generated
    public LeaseStateType getLeaseState() {
        return this.leaseState;
    }

    /**
     * Set the leaseState property: The LeaseState property.
     * 
     * @param leaseState the leaseState value to set.
     * @return the ContainerProperties object itself.
     */
    @Generated
    public ContainerProperties setLeaseState(LeaseStateType leaseState) {
        this.leaseState = leaseState;
        return this;
    }

    /**
     * Get the leaseDuration property: The LeaseDuration property.
     * 
     * @return the leaseDuration value.
     */
    @Generated
    public LeaseDurationType getLeaseDuration() {
        return this.leaseDuration;
    }

    /**
     * Set the leaseDuration property: The LeaseDuration property.
     * 
     * @param leaseDuration the leaseDuration value to set.
     * @return the ContainerProperties object itself.
     */
    @Generated
    public ContainerProperties setLeaseDuration(LeaseDurationType leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the publicAccess property: The PublicAccess property.
     * 
     * @return the publicAccess value.
     */
    @Generated
    public PublicAccessType getPublicAccess() {
        return this.publicAccess;
    }

    /**
     * Set the publicAccess property: The PublicAccess property.
     * 
     * @param publicAccess the publicAccess value to set.
     * @return the ContainerProperties object itself.
     */
    @Generated
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
            throw LOGGER.atError()
                .log(new IllegalArgumentException(
                    "Missing required property lastModified in model ContainerProperties"));
        }
        if (getEtag() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property etag in model ContainerProperties"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(ContainerProperties.class);

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Generated
    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "ContainerProperties" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Last-Modified", Objects.toString(this.lastModified, null));
        xmlWriter.writeStringElement("Etag", this.etag);
        xmlWriter.writeStringElement("LeaseStatus", this.leaseStatus == null ? null : this.leaseStatus.toString());
        xmlWriter.writeStringElement("LeaseState", this.leaseState == null ? null : this.leaseState.toString());
        xmlWriter.writeStringElement("LeaseDuration",
            this.leaseDuration == null ? null : this.leaseDuration.toString());
        xmlWriter.writeStringElement("PublicAccess", this.publicAccess == null ? null : this.publicAccess.toString());
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of ContainerProperties from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @return An instance of ContainerProperties if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ContainerProperties.
     */
    @Generated
    public static ContainerProperties fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of ContainerProperties from the XmlReader.
     * 
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of ContainerProperties if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws XMLStreamException If an error occurs while reading the ContainerProperties.
     */
    @Generated
    public static ContainerProperties fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName
            = rootElementName == null || rootElementName.isEmpty() ? "ContainerProperties" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            ContainerProperties deserializedContainerProperties = new ContainerProperties();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();

                if ("Last-Modified".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.lastModified = reader.getNullableElement(DateTimeRfc1123::new);
                } else if ("Etag".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.etag = reader.getStringElement();
                } else if ("LeaseStatus".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.leaseStatus = LeaseStatusType.fromString(reader.getStringElement());
                } else if ("LeaseState".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.leaseState = LeaseStateType.fromString(reader.getStringElement());
                } else if ("LeaseDuration".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.leaseDuration
                        = LeaseDurationType.fromString(reader.getStringElement());
                } else if ("PublicAccess".equals(elementName.getLocalPart())) {
                    deserializedContainerProperties.publicAccess
                        = PublicAccessType.fromString(reader.getStringElement());
                } else {
                    reader.skipElement();
                }
            }

            return deserializedContainerProperties;
        });
    }
}
