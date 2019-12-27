package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.time.OffsetDateTime;

/**
 * The BlobProperties model.
 */
@JacksonXmlRootElement(localName = "BlobProperties")
@Fluent
public final class BlobProperties {
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
     * Size in bytes
     */
    @JsonProperty(value = "Content-Length")
    private Long contentLength;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Content-Encoding")
    private String contentEncoding;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Content-Language")
    private String contentLanguage;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Content-MD5")
    private String contentMD5;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Content-Disposition")
    private String contentDisposition;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "Cache-Control")
    private String cacheControl;

    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "x-ms-blob-sequence-number")
    private Integer blobSequenceNumber;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "BlobType")
    private BlobType blobType;

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
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "CopyId")
    private String copyId;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "CopyStatus")
    private CopyStatusType copyStatus;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "CopySource")
    private String copySource;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "CopyProgress")
    private String copyProgress;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "CopyCompletionTime")
    private DateTimeRfc1123 copyCompletionTime;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "CopyStatusDescription")
    private String copyStatusDescription;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "ServerEncrypted")
    private Boolean serverEncrypted;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "IncrementalCopy")
    private Boolean incrementalCopy;

    /*
     * MISSING·SCHEMA-DESCRIPTION-STRING
     */
    @JsonProperty(value = "DestinationSnapshot")
    private String destinationSnapshot;

    /*
     * MISSING·SCHEMA-DESCRIPTION-DATETIME
     */
    @JsonProperty(value = "DeletedTime")
    private DateTimeRfc1123 deletedTime;

    /*
     * MISSING·SCHEMA-DESCRIPTION-INTEGER
     */
    @JsonProperty(value = "RemainingRetentionDays")
    private Integer remainingRetentionDays;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "AccessTier")
    private AccessTier accessTier;

    /*
     * MISSING·SCHEMA-DESCRIPTION-BOOLEAN
     */
    @JsonProperty(value = "AccessTierInferred")
    private Boolean accessTierInferred;

    /*
     * MISSING·SCHEMA-DESCRIPTION-CHOICE
     */
    @JsonProperty(value = "ArchiveStatus")
    private ArchiveStatus archiveStatus;

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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLastModified(OffsetDateTime lastModified) {
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setEtag(String etag) {
        this.etag = etag;
        return this;
    }

    /**
     * Get the contentLength property: Size in bytes.
     * 
     * @return the contentLength value.
     */
    public Long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: Size in bytes.
     * 
     * @param contentLength the contentLength value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentLength(Long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    /**
     * Get the contentType property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param contentType the contentType value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the contentEncoding property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the contentEncoding value.
     */
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    /**
     * Set the contentEncoding property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param contentEncoding the contentEncoding value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
        return this;
    }

    /**
     * Get the contentLanguage property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the contentLanguage value.
     */
    public String getContentLanguage() {
        return this.contentLanguage;
    }

    /**
     * Set the contentLanguage property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param contentLanguage the contentLanguage value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
        return this;
    }

    /**
     * Get the contentMD5 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the contentMD5 value.
     */
    public String getContentMD5() {
        return this.contentMD5;
    }

    /**
     * Set the contentMD5 property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param contentMD5 the contentMD5 value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
        return this;
    }

    /**
     * Get the contentDisposition property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the contentDisposition value.
     */
    public String getContentDisposition() {
        return this.contentDisposition;
    }

    /**
     * Set the contentDisposition property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param contentDisposition the contentDisposition value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
        return this;
    }

    /**
     * Get the cacheControl property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the cacheControl value.
     */
    public String getCacheControl() {
        return this.cacheControl;
    }

    /**
     * Set the cacheControl property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param cacheControl the cacheControl value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
        return this;
    }

    /**
     * Get the blobSequenceNumber property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the blobSequenceNumber value.
     */
    public Integer getBlobSequenceNumber() {
        return this.blobSequenceNumber;
    }

    /**
     * Set the blobSequenceNumber property: MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @param blobSequenceNumber the blobSequenceNumber value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setBlobSequenceNumber(Integer blobSequenceNumber) {
        this.blobSequenceNumber = blobSequenceNumber;
        return this;
    }

    /**
     * Get the blobType property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the blobType value.
     */
    public BlobType getBlobType() {
        return this.blobType;
    }

    /**
     * Set the blobType property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param blobType the blobType value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setBlobType(BlobType blobType) {
        this.blobType = blobType;
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseStatus(LeaseStatusType leaseStatus) {
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseState(LeaseStateType leaseState) {
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseDuration(LeaseDurationType leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the copyId property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the copyId value.
     */
    public String getCopyId() {
        return this.copyId;
    }

    /**
     * Set the copyId property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param copyId the copyId value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyId(String copyId) {
        this.copyId = copyId;
        return this;
    }

    /**
     * Get the copyStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the copyStatus value.
     */
    public CopyStatusType getCopyStatus() {
        return this.copyStatus;
    }

    /**
     * Set the copyStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param copyStatus the copyStatus value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyStatus(CopyStatusType copyStatus) {
        this.copyStatus = copyStatus;
        return this;
    }

    /**
     * Get the copySource property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the copySource value.
     */
    public String getCopySource() {
        return this.copySource;
    }

    /**
     * Set the copySource property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param copySource the copySource value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopySource(String copySource) {
        this.copySource = copySource;
        return this;
    }

    /**
     * Get the copyProgress property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the copyProgress value.
     */
    public String getCopyProgress() {
        return this.copyProgress;
    }

    /**
     * Set the copyProgress property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param copyProgress the copyProgress value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyProgress(String copyProgress) {
        this.copyProgress = copyProgress;
        return this;
    }

    /**
     * Get the copyCompletionTime property:
     * MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the copyCompletionTime value.
     */
    public OffsetDateTime getCopyCompletionTime() {
        if (this.copyCompletionTime == null) {
            return null;
        }
        return this.copyCompletionTime.getDateTime();
    }

    /**
     * Set the copyCompletionTime property:
     * MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @param copyCompletionTime the copyCompletionTime value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyCompletionTime(OffsetDateTime copyCompletionTime) {
        if (copyCompletionTime == null) {
            this.copyCompletionTime = null;
        } else {
            this.copyCompletionTime = new DateTimeRfc1123(copyCompletionTime);
        }
        return this;
    }

    /**
     * Get the copyStatusDescription property:
     * MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the copyStatusDescription value.
     */
    public String getCopyStatusDescription() {
        return this.copyStatusDescription;
    }

    /**
     * Set the copyStatusDescription property:
     * MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param copyStatusDescription the copyStatusDescription value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyStatusDescription(String copyStatusDescription) {
        this.copyStatusDescription = copyStatusDescription;
        return this;
    }

    /**
     * Get the serverEncrypted property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the serverEncrypted value.
     */
    public Boolean isServerEncrypted() {
        return this.serverEncrypted;
    }

    /**
     * Set the serverEncrypted property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @param serverEncrypted the serverEncrypted value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setServerEncrypted(Boolean serverEncrypted) {
        this.serverEncrypted = serverEncrypted;
        return this;
    }

    /**
     * Get the incrementalCopy property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the incrementalCopy value.
     */
    public Boolean isIncrementalCopy() {
        return this.incrementalCopy;
    }

    /**
     * Set the incrementalCopy property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @param incrementalCopy the incrementalCopy value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setIncrementalCopy(Boolean incrementalCopy) {
        this.incrementalCopy = incrementalCopy;
        return this;
    }

    /**
     * Get the destinationSnapshot property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @return the destinationSnapshot value.
     */
    public String getDestinationSnapshot() {
        return this.destinationSnapshot;
    }

    /**
     * Set the destinationSnapshot property: MISSING·SCHEMA-DESCRIPTION-STRING.
     * 
     * @param destinationSnapshot the destinationSnapshot value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setDestinationSnapshot(String destinationSnapshot) {
        this.destinationSnapshot = destinationSnapshot;
        return this;
    }

    /**
     * Get the deletedTime property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @return the deletedTime value.
     */
    public OffsetDateTime getDeletedTime() {
        if (this.deletedTime == null) {
            return null;
        }
        return this.deletedTime.getDateTime();
    }

    /**
     * Set the deletedTime property: MISSING·SCHEMA-DESCRIPTION-DATETIME.
     * 
     * @param deletedTime the deletedTime value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setDeletedTime(OffsetDateTime deletedTime) {
        if (deletedTime == null) {
            this.deletedTime = null;
        } else {
            this.deletedTime = new DateTimeRfc1123(deletedTime);
        }
        return this;
    }

    /**
     * Get the remainingRetentionDays property:
     * MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @return the remainingRetentionDays value.
     */
    public Integer getRemainingRetentionDays() {
        return this.remainingRetentionDays;
    }

    /**
     * Set the remainingRetentionDays property:
     * MISSING·SCHEMA-DESCRIPTION-INTEGER.
     * 
     * @param remainingRetentionDays the remainingRetentionDays value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setRemainingRetentionDays(Integer remainingRetentionDays) {
        this.remainingRetentionDays = remainingRetentionDays;
        return this;
    }

    /**
     * Get the accessTier property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the accessTier value.
     */
    public AccessTier getAccessTier() {
        return this.accessTier;
    }

    /**
     * Set the accessTier property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param accessTier the accessTier value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setAccessTier(AccessTier accessTier) {
        this.accessTier = accessTier;
        return this;
    }

    /**
     * Get the accessTierInferred property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @return the accessTierInferred value.
     */
    public Boolean isAccessTierInferred() {
        return this.accessTierInferred;
    }

    /**
     * Set the accessTierInferred property: MISSING·SCHEMA-DESCRIPTION-BOOLEAN.
     * 
     * @param accessTierInferred the accessTierInferred value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setAccessTierInferred(Boolean accessTierInferred) {
        this.accessTierInferred = accessTierInferred;
        return this;
    }

    /**
     * Get the archiveStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @return the archiveStatus value.
     */
    public ArchiveStatus getArchiveStatus() {
        return this.archiveStatus;
    }

    /**
     * Set the archiveStatus property: MISSING·SCHEMA-DESCRIPTION-CHOICE.
     * 
     * @param archiveStatus the archiveStatus value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setArchiveStatus(ArchiveStatus archiveStatus) {
        this.archiveStatus = archiveStatus;
        return this;
    }

    public void validate() {
        if (getLastModified() == null) {
            throw new IllegalArgumentException("Missing required property lastModified in model BlobProperties");
        }
        if (getEtag() == null) {
            throw new IllegalArgumentException("Missing required property etag in model BlobProperties");
        }
    }
}
