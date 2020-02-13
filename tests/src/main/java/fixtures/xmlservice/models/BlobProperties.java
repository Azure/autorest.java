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
     * The Last-Modified property.
     */
    @JsonProperty(value = "Last-Modified", required = true)
    private DateTimeRfc1123 lastModified;

    /*
     * The Etag property.
     */
    @JsonProperty(value = "Etag", required = true)
    private String etag;

    /*
     * Size in bytes
     */
    @JsonProperty(value = "Content-Length")
    private Long contentLength;

    /*
     * The Content-Type property.
     */
    @JsonProperty(value = "Content-Type")
    private String contentType;

    /*
     * The Content-Encoding property.
     */
    @JsonProperty(value = "Content-Encoding")
    private String contentEncoding;

    /*
     * The Content-Language property.
     */
    @JsonProperty(value = "Content-Language")
    private String contentLanguage;

    /*
     * The Content-MD5 property.
     */
    @JsonProperty(value = "Content-MD5")
    private String contentMD5;

    /*
     * The Content-Disposition property.
     */
    @JsonProperty(value = "Content-Disposition")
    private String contentDisposition;

    /*
     * The Cache-Control property.
     */
    @JsonProperty(value = "Cache-Control")
    private String cacheControl;

    /*
     * The x-ms-blob-sequence-number property.
     */
    @JsonProperty(value = "x-ms-blob-sequence-number")
    private Integer blobSequenceNumber;

    /*
     * The BlobType property.
     */
    @JsonProperty(value = "BlobType")
    private BlobType blobType;

    /*
     * The LeaseStatus property.
     */
    @JsonProperty(value = "LeaseStatus")
    private LeaseStatusType leaseStatus;

    /*
     * The LeaseState property.
     */
    @JsonProperty(value = "LeaseState")
    private LeaseStateType leaseState;

    /*
     * The LeaseDuration property.
     */
    @JsonProperty(value = "LeaseDuration")
    private LeaseDurationType leaseDuration;

    /*
     * The CopyId property.
     */
    @JsonProperty(value = "CopyId")
    private String copyId;

    /*
     * The CopyStatus property.
     */
    @JsonProperty(value = "CopyStatus")
    private CopyStatusType copyStatus;

    /*
     * The CopySource property.
     */
    @JsonProperty(value = "CopySource")
    private String copySource;

    /*
     * The CopyProgress property.
     */
    @JsonProperty(value = "CopyProgress")
    private String copyProgress;

    /*
     * The CopyCompletionTime property.
     */
    @JsonProperty(value = "CopyCompletionTime")
    private DateTimeRfc1123 copyCompletionTime;

    /*
     * The CopyStatusDescription property.
     */
    @JsonProperty(value = "CopyStatusDescription")
    private String copyStatusDescription;

    /*
     * The ServerEncrypted property.
     */
    @JsonProperty(value = "ServerEncrypted")
    private Boolean serverEncrypted;

    /*
     * The IncrementalCopy property.
     */
    @JsonProperty(value = "IncrementalCopy")
    private Boolean incrementalCopy;

    /*
     * The DestinationSnapshot property.
     */
    @JsonProperty(value = "DestinationSnapshot")
    private String destinationSnapshot;

    /*
     * The DeletedTime property.
     */
    @JsonProperty(value = "DeletedTime")
    private DateTimeRfc1123 deletedTime;

    /*
     * The RemainingRetentionDays property.
     */
    @JsonProperty(value = "RemainingRetentionDays")
    private Integer remainingRetentionDays;

    /*
     * The AccessTier property.
     */
    @JsonProperty(value = "AccessTier")
    private AccessTier accessTier;

    /*
     * The AccessTierInferred property.
     */
    @JsonProperty(value = "AccessTierInferred")
    private Boolean accessTierInferred;

    /*
     * The ArchiveStatus property.
     */
    @JsonProperty(value = "ArchiveStatus")
    private ArchiveStatus archiveStatus;

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
     * Get the contentType property: The Content-Type property.
     * 
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The Content-Type property.
     * 
     * @param contentType the contentType value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the contentEncoding property: The Content-Encoding property.
     * 
     * @return the contentEncoding value.
     */
    public String getContentEncoding() {
        return this.contentEncoding;
    }

    /**
     * Set the contentEncoding property: The Content-Encoding property.
     * 
     * @param contentEncoding the contentEncoding value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
        return this;
    }

    /**
     * Get the contentLanguage property: The Content-Language property.
     * 
     * @return the contentLanguage value.
     */
    public String getContentLanguage() {
        return this.contentLanguage;
    }

    /**
     * Set the contentLanguage property: The Content-Language property.
     * 
     * @param contentLanguage the contentLanguage value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
        return this;
    }

    /**
     * Get the contentMD5 property: The Content-MD5 property.
     * 
     * @return the contentMD5 value.
     */
    public String getContentMD5() {
        return this.contentMD5;
    }

    /**
     * Set the contentMD5 property: The Content-MD5 property.
     * 
     * @param contentMD5 the contentMD5 value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentMD5(String contentMD5) {
        this.contentMD5 = contentMD5;
        return this;
    }

    /**
     * Get the contentDisposition property: The Content-Disposition property.
     * 
     * @return the contentDisposition value.
     */
    public String getContentDisposition() {
        return this.contentDisposition;
    }

    /**
     * Set the contentDisposition property: The Content-Disposition property.
     * 
     * @param contentDisposition the contentDisposition value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
        return this;
    }

    /**
     * Get the cacheControl property: The Cache-Control property.
     * 
     * @return the cacheControl value.
     */
    public String getCacheControl() {
        return this.cacheControl;
    }

    /**
     * Set the cacheControl property: The Cache-Control property.
     * 
     * @param cacheControl the cacheControl value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
        return this;
    }

    /**
     * Get the blobSequenceNumber property: The x-ms-blob-sequence-number
     * property.
     * 
     * @return the blobSequenceNumber value.
     */
    public Integer getBlobSequenceNumber() {
        return this.blobSequenceNumber;
    }

    /**
     * Set the blobSequenceNumber property: The x-ms-blob-sequence-number
     * property.
     * 
     * @param blobSequenceNumber the blobSequenceNumber value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setBlobSequenceNumber(Integer blobSequenceNumber) {
        this.blobSequenceNumber = blobSequenceNumber;
        return this;
    }

    /**
     * Get the blobType property: The BlobType property.
     * 
     * @return the blobType value.
     */
    public BlobType getBlobType() {
        return this.blobType;
    }

    /**
     * Set the blobType property: The BlobType property.
     * 
     * @param blobType the blobType value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setBlobType(BlobType blobType) {
        this.blobType = blobType;
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseStatus(LeaseStatusType leaseStatus) {
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseState(LeaseStateType leaseState) {
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
     * @return the BlobProperties object itself.
     */
    public BlobProperties setLeaseDuration(LeaseDurationType leaseDuration) {
        this.leaseDuration = leaseDuration;
        return this;
    }

    /**
     * Get the copyId property: The CopyId property.
     * 
     * @return the copyId value.
     */
    public String getCopyId() {
        return this.copyId;
    }

    /**
     * Set the copyId property: The CopyId property.
     * 
     * @param copyId the copyId value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyId(String copyId) {
        this.copyId = copyId;
        return this;
    }

    /**
     * Get the copyStatus property: The CopyStatus property.
     * 
     * @return the copyStatus value.
     */
    public CopyStatusType getCopyStatus() {
        return this.copyStatus;
    }

    /**
     * Set the copyStatus property: The CopyStatus property.
     * 
     * @param copyStatus the copyStatus value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyStatus(CopyStatusType copyStatus) {
        this.copyStatus = copyStatus;
        return this;
    }

    /**
     * Get the copySource property: The CopySource property.
     * 
     * @return the copySource value.
     */
    public String getCopySource() {
        return this.copySource;
    }

    /**
     * Set the copySource property: The CopySource property.
     * 
     * @param copySource the copySource value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopySource(String copySource) {
        this.copySource = copySource;
        return this;
    }

    /**
     * Get the copyProgress property: The CopyProgress property.
     * 
     * @return the copyProgress value.
     */
    public String getCopyProgress() {
        return this.copyProgress;
    }

    /**
     * Set the copyProgress property: The CopyProgress property.
     * 
     * @param copyProgress the copyProgress value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyProgress(String copyProgress) {
        this.copyProgress = copyProgress;
        return this;
    }

    /**
     * Get the copyCompletionTime property: The CopyCompletionTime property.
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
     * Set the copyCompletionTime property: The CopyCompletionTime property.
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
     * Get the copyStatusDescription property: The CopyStatusDescription
     * property.
     * 
     * @return the copyStatusDescription value.
     */
    public String getCopyStatusDescription() {
        return this.copyStatusDescription;
    }

    /**
     * Set the copyStatusDescription property: The CopyStatusDescription
     * property.
     * 
     * @param copyStatusDescription the copyStatusDescription value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setCopyStatusDescription(String copyStatusDescription) {
        this.copyStatusDescription = copyStatusDescription;
        return this;
    }

    /**
     * Get the serverEncrypted property: The ServerEncrypted property.
     * 
     * @return the serverEncrypted value.
     */
    public Boolean isServerEncrypted() {
        return this.serverEncrypted;
    }

    /**
     * Set the serverEncrypted property: The ServerEncrypted property.
     * 
     * @param serverEncrypted the serverEncrypted value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setServerEncrypted(Boolean serverEncrypted) {
        this.serverEncrypted = serverEncrypted;
        return this;
    }

    /**
     * Get the incrementalCopy property: The IncrementalCopy property.
     * 
     * @return the incrementalCopy value.
     */
    public Boolean isIncrementalCopy() {
        return this.incrementalCopy;
    }

    /**
     * Set the incrementalCopy property: The IncrementalCopy property.
     * 
     * @param incrementalCopy the incrementalCopy value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setIncrementalCopy(Boolean incrementalCopy) {
        this.incrementalCopy = incrementalCopy;
        return this;
    }

    /**
     * Get the destinationSnapshot property: The DestinationSnapshot property.
     * 
     * @return the destinationSnapshot value.
     */
    public String getDestinationSnapshot() {
        return this.destinationSnapshot;
    }

    /**
     * Set the destinationSnapshot property: The DestinationSnapshot property.
     * 
     * @param destinationSnapshot the destinationSnapshot value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setDestinationSnapshot(String destinationSnapshot) {
        this.destinationSnapshot = destinationSnapshot;
        return this;
    }

    /**
     * Get the deletedTime property: The DeletedTime property.
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
     * Set the deletedTime property: The DeletedTime property.
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
     * Get the remainingRetentionDays property: The RemainingRetentionDays
     * property.
     * 
     * @return the remainingRetentionDays value.
     */
    public Integer getRemainingRetentionDays() {
        return this.remainingRetentionDays;
    }

    /**
     * Set the remainingRetentionDays property: The RemainingRetentionDays
     * property.
     * 
     * @param remainingRetentionDays the remainingRetentionDays value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setRemainingRetentionDays(Integer remainingRetentionDays) {
        this.remainingRetentionDays = remainingRetentionDays;
        return this;
    }

    /**
     * Get the accessTier property: The AccessTier property.
     * 
     * @return the accessTier value.
     */
    public AccessTier getAccessTier() {
        return this.accessTier;
    }

    /**
     * Set the accessTier property: The AccessTier property.
     * 
     * @param accessTier the accessTier value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setAccessTier(AccessTier accessTier) {
        this.accessTier = accessTier;
        return this;
    }

    /**
     * Get the accessTierInferred property: The AccessTierInferred property.
     * 
     * @return the accessTierInferred value.
     */
    public Boolean isAccessTierInferred() {
        return this.accessTierInferred;
    }

    /**
     * Set the accessTierInferred property: The AccessTierInferred property.
     * 
     * @param accessTierInferred the accessTierInferred value to set.
     * @return the BlobProperties object itself.
     */
    public BlobProperties setAccessTierInferred(Boolean accessTierInferred) {
        this.accessTierInferred = accessTierInferred;
        return this;
    }

    /**
     * Get the archiveStatus property: The ArchiveStatus property.
     * 
     * @return the archiveStatus value.
     */
    public ArchiveStatus getArchiveStatus() {
        return this.archiveStatus;
    }

    /**
     * Set the archiveStatus property: The ArchiveStatus property.
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
