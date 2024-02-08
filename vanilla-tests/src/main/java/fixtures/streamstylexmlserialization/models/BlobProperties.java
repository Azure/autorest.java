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

/**
 * Properties of a blob.
 */
@Fluent
public final class BlobProperties implements XmlSerializable<BlobProperties> {

    /*
     * The Last-Modified property.
     */
    private DateTimeRfc1123 lastModified;

    /*
     * The Etag property.
     */
    private String etag;

    /*
     * Size in bytes
     */
    private Long contentLength;

    /*
     * The Content-Type property.
     */
    private String contentType;

    /*
     * The Content-Encoding property.
     */
    private String contentEncoding;

    /*
     * The Content-Language property.
     */
    private String contentLanguage;

    /*
     * The Content-MD5 property.
     */
    private String contentMD5;

    /*
     * The Content-Disposition property.
     */
    private String contentDisposition;

    /*
     * The Cache-Control property.
     */
    private String cacheControl;

    /*
     * The x-ms-blob-sequence-number property.
     */
    private Integer blobSequenceNumber;

    /*
     * The BlobType property.
     */
    private BlobType blobType;

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
     * The CopyId property.
     */
    private String copyId;

    /*
     * The CopyStatus property.
     */
    private CopyStatusType copyStatus;

    /*
     * The CopySource property.
     */
    private String copySource;

    /*
     * The CopyProgress property.
     */
    private String copyProgress;

    /*
     * The CopyCompletionTime property.
     */
    private DateTimeRfc1123 copyCompletionTime;

    /*
     * The CopyStatusDescription property.
     */
    private String copyStatusDescription;

    /*
     * The ServerEncrypted property.
     */
    private Boolean serverEncrypted;

    /*
     * The IncrementalCopy property.
     */
    private Boolean incrementalCopy;

    /*
     * The DestinationSnapshot property.
     */
    private String destinationSnapshot;

    /*
     * The DeletedTime property.
     */
    private DateTimeRfc1123 deletedTime;

    /*
     * The RemainingRetentionDays property.
     */
    private Integer remainingRetentionDays;

    /*
     * The AccessTier property.
     */
    private AccessTier accessTier;

    /*
     * The AccessTierInferred property.
     */
    private Boolean accessTierInferred;

    /*
     * The ArchiveStatus property.
     */
    private ArchiveStatus archiveStatus;

    /**
     * Creates an instance of BlobProperties class.
     */
    public BlobProperties() {
    }

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
     * Get the blobSequenceNumber property: The x-ms-blob-sequence-number property.
     *
     * @return the blobSequenceNumber value.
     */
    public Integer getBlobSequenceNumber() {
        return this.blobSequenceNumber;
    }

    /**
     * Set the blobSequenceNumber property: The x-ms-blob-sequence-number property.
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
     * Get the copyStatusDescription property: The CopyStatusDescription property.
     *
     * @return the copyStatusDescription value.
     */
    public String getCopyStatusDescription() {
        return this.copyStatusDescription;
    }

    /**
     * Set the copyStatusDescription property: The CopyStatusDescription property.
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
     * Get the remainingRetentionDays property: The RemainingRetentionDays property.
     *
     * @return the remainingRetentionDays value.
     */
    public Integer getRemainingRetentionDays() {
        return this.remainingRetentionDays;
    }

    /**
     * Set the remainingRetentionDays property: The RemainingRetentionDays property.
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

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getLastModified() == null) {
            throw new IllegalArgumentException("Missing required property lastModified in model BlobProperties");
        }
        if (getEtag() == null) {
            throw new IllegalArgumentException("Missing required property etag in model BlobProperties");
        }
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter) throws XMLStreamException {
        return toXml(xmlWriter, null);
    }

    @Override
    public XmlWriter toXml(XmlWriter xmlWriter, String rootElementName) throws XMLStreamException {
        rootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "BlobProperties" : rootElementName;
        xmlWriter.writeStartElement(rootElementName);
        xmlWriter.writeStringElement("Last-Modified", Objects.toString(this.lastModified, null));
        xmlWriter.writeStringElement("Etag", this.etag);
        xmlWriter.writeNumberElement("Content-Length", this.contentLength);
        xmlWriter.writeStringElement("Content-Type", this.contentType);
        xmlWriter.writeStringElement("Content-Encoding", this.contentEncoding);
        xmlWriter.writeStringElement("Content-Language", this.contentLanguage);
        xmlWriter.writeStringElement("Content-MD5", this.contentMD5);
        xmlWriter.writeStringElement("Content-Disposition", this.contentDisposition);
        xmlWriter.writeStringElement("Cache-Control", this.cacheControl);
        xmlWriter.writeNumberElement("x-ms-blob-sequence-number", this.blobSequenceNumber);
        xmlWriter.writeStringElement("BlobType", this.blobType == null ? null : this.blobType.toString());
        xmlWriter.writeStringElement("LeaseStatus", this.leaseStatus == null ? null : this.leaseStatus.toString());
        xmlWriter.writeStringElement("LeaseState", this.leaseState == null ? null : this.leaseState.toString());
        xmlWriter.writeStringElement("LeaseDuration",
            this.leaseDuration == null ? null : this.leaseDuration.toString());
        xmlWriter.writeStringElement("CopyId", this.copyId);
        xmlWriter.writeStringElement("CopyStatus", this.copyStatus == null ? null : this.copyStatus.toString());
        xmlWriter.writeStringElement("CopySource", this.copySource);
        xmlWriter.writeStringElement("CopyProgress", this.copyProgress);
        xmlWriter.writeStringElement("CopyCompletionTime", Objects.toString(this.copyCompletionTime, null));
        xmlWriter.writeStringElement("CopyStatusDescription", this.copyStatusDescription);
        xmlWriter.writeBooleanElement("ServerEncrypted", this.serverEncrypted);
        xmlWriter.writeBooleanElement("IncrementalCopy", this.incrementalCopy);
        xmlWriter.writeStringElement("DestinationSnapshot", this.destinationSnapshot);
        xmlWriter.writeStringElement("DeletedTime", Objects.toString(this.deletedTime, null));
        xmlWriter.writeNumberElement("RemainingRetentionDays", this.remainingRetentionDays);
        xmlWriter.writeStringElement("AccessTier", this.accessTier == null ? null : this.accessTier.toString());
        xmlWriter.writeBooleanElement("AccessTierInferred", this.accessTierInferred);
        xmlWriter.writeStringElement("ArchiveStatus",
            this.archiveStatus == null ? null : this.archiveStatus.toString());
        return xmlWriter.writeEndElement();
    }

    /**
     * Reads an instance of BlobProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @return An instance of BlobProperties if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the BlobProperties.
     */
    public static BlobProperties fromXml(XmlReader xmlReader) throws XMLStreamException {
        return fromXml(xmlReader, null);
    }

    /**
     * Reads an instance of BlobProperties from the XmlReader.
     *
     * @param xmlReader The XmlReader being read.
     * @param rootElementName Optional root element name to override the default defined by the model. Used to support
     * cases where the model can deserialize from different root element names.
     * @return An instance of BlobProperties if the XmlReader was pointing to an instance of it, or null if it was
     * pointing to XML null.
     * @throws IllegalStateException If the deserialized XML object was missing any required properties.
     * @throws XMLStreamException If an error occurs while reading the BlobProperties.
     */
    public static BlobProperties fromXml(XmlReader xmlReader, String rootElementName) throws XMLStreamException {
        String finalRootElementName = CoreUtils.isNullOrEmpty(rootElementName) ? "BlobProperties" : rootElementName;
        return xmlReader.readObject(finalRootElementName, reader -> {
            BlobProperties deserializedBlobProperties = new BlobProperties();
            while (reader.nextElement() != XmlToken.END_ELEMENT) {
                QName elementName = reader.getElementName();
                if ("Last-Modified".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.lastModified = reader.getNullableElement(DateTimeRfc1123::new);
                } else if ("Etag".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.etag = reader.getStringElement();
                } else if ("Content-Length".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentLength = reader.getNullableElement(Long::parseLong);
                } else if ("Content-Type".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentType = reader.getStringElement();
                } else if ("Content-Encoding".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentEncoding = reader.getStringElement();
                } else if ("Content-Language".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentLanguage = reader.getStringElement();
                } else if ("Content-MD5".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentMD5 = reader.getStringElement();
                } else if ("Content-Disposition".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.contentDisposition = reader.getStringElement();
                } else if ("Cache-Control".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.cacheControl = reader.getStringElement();
                } else if ("x-ms-blob-sequence-number".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.blobSequenceNumber = reader.getNullableElement(Integer::parseInt);
                } else if ("BlobType".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.blobType = BlobType.fromString(reader.getStringElement());
                } else if ("LeaseStatus".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.leaseStatus = LeaseStatusType.fromString(reader.getStringElement());
                } else if ("LeaseState".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.leaseState = LeaseStateType.fromString(reader.getStringElement());
                } else if ("LeaseDuration".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.leaseDuration = LeaseDurationType.fromString(reader.getStringElement());
                } else if ("CopyId".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copyId = reader.getStringElement();
                } else if ("CopyStatus".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copyStatus = CopyStatusType.fromString(reader.getStringElement());
                } else if ("CopySource".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copySource = reader.getStringElement();
                } else if ("CopyProgress".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copyProgress = reader.getStringElement();
                } else if ("CopyCompletionTime".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copyCompletionTime = reader.getNullableElement(DateTimeRfc1123::new);
                } else if ("CopyStatusDescription".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.copyStatusDescription = reader.getStringElement();
                } else if ("ServerEncrypted".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.serverEncrypted = reader.getNullableElement(Boolean::parseBoolean);
                } else if ("IncrementalCopy".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.incrementalCopy = reader.getNullableElement(Boolean::parseBoolean);
                } else if ("DestinationSnapshot".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.destinationSnapshot = reader.getStringElement();
                } else if ("DeletedTime".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.deletedTime = reader.getNullableElement(DateTimeRfc1123::new);
                } else if ("RemainingRetentionDays".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.remainingRetentionDays = reader.getNullableElement(Integer::parseInt);
                } else if ("AccessTier".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.accessTier = AccessTier.fromString(reader.getStringElement());
                } else if ("AccessTierInferred".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.accessTierInferred = reader.getNullableElement(Boolean::parseBoolean);
                } else if ("ArchiveStatus".equals(elementName.getLocalPart())) {
                    deserializedBlobProperties.archiveStatus = ArchiveStatus.fromString(reader.getStringElement());
                } else {
                    reader.skipElement();
                }
            }
            return deserializedBlobProperties;
        });
    }
}
