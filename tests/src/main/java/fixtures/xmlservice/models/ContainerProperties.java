package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.OffsetDateTime;

/**
 * The ContainerProperties model.
 */
@JacksonXmlRootElement(localName = "ContainerProperties")
@Fluent
public final class ContainerProperties {
    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "Last-Modified", required = true)
    private DateTimeRfc1123 lastModified;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Etag", required = true)
    private String etag;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "LeaseStatus")
    private LeaseStatusType leaseStatus;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "LeaseState")
    private LeaseStateType leaseState;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "LeaseDuration")
    private LeaseDurationType leaseDuration;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "PublicAccess")
    private PublicAccessType publicAccess;

    /**
     * Get the lastModified property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
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
     * Set the lastModified property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
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
     * Get the etag property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the etag value.
     */
    public String getEtag() {
        return this.etag;
    }

    /**
     * Set the etag property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param etag the etag value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the leaseStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the leaseStatus value.
     */
    public LeaseStatusType getLeaseStatus() {
        return this.leaseStatus;
    }

    /**
     * Set the leaseStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param leaseStatus the leaseStatus value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseStatus(LeaseStatusType leaseStatus) {
        this.leaseStatus = leaseStatus;
        return this;
    }

    /**
     * Get the leaseState property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the leaseState value.
     */
    public LeaseStateType getLeaseState() {
        return this.leaseState;
    }

    /**
     * Set the leaseState property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param leaseState the leaseState value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseState(LeaseStateType leaseState) {
        this.leaseState = leaseState;
        return this;
    }

    /**
     * Get the leaseDuration property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the leaseDuration value.
     */
    public LeaseDurationType getLeaseDuration() {
        return this.leaseDuration;
    }

    /**
     * Set the leaseDuration property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param leaseDuration the leaseDuration value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setLeaseDuration(LeaseDurationType leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the publicAccess property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the publicAccess value.
     */
    public PublicAccessType getPublicAccess() {
        return this.publicAccess;
    }

    /**
     * Set the publicAccess property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param publicAccess the publicAccess value to set.
     * @return the ContainerProperties object itself.
     */
    public ContainerProperties setPublicAccess(PublicAccessType publicAccess) {
        this.publicAccess = publicAccess;
        return this;
    }

    public void validate() {
        if (getLastModified() == null) {
            throw new IllegalArgumentException("Missing required property lastModified in model ContainerProperties");
        }
        if (getEtag() == null) {
            throw new IllegalArgumentException("Missing required property etag in model ContainerProperties");
        }
    }
}
