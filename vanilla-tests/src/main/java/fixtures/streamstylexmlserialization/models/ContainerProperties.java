// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstylexmlserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.xml.XmlSerializable;
import com.azure.xml.XmlWriter;
import java.time.OffsetDateTime;

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
    public XmlWriter toXml(XmlWriter xmlWriter) {
        xmlWriter.writeStartElement("ContainerProperties");
        xmlWriter.writeStringElement("Last-Modified", this.lastModified);
        xmlWriter.writeStringElement("Etag", this.etag);
        xmlWriter.writeStringElement("LeaseStatus", this.leaseStatus == null ? null : this.leaseStatus.toString());
        xmlWriter.writeStringElement("LeaseState", this.leaseState == null ? null : this.leaseState.toString());
        xmlWriter.writeStringElement(
                "LeaseDuration", this.leaseDuration == null ? null : this.leaseDuration.toString());
        xmlWriter.writeStringElement("PublicAccess", this.publicAccess == null ? null : this.publicAccess.toString());
        return xmlWriter.writeEndElement();
    }
}
