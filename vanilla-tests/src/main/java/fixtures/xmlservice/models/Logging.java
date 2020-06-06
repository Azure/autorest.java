package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/** The Logging model. */
@JacksonXmlRootElement(localName = "Logging")
@Fluent
public final class Logging {
    /*
     * The version of Storage Analytics to configure.
     */
    @JsonProperty(value = "Version", required = true)
    private String version;

    /*
     * Indicates whether all delete requests should be logged.
     */
    @JsonProperty(value = "Delete", required = true)
    private boolean delete;

    /*
     * Indicates whether all read requests should be logged.
     */
    @JsonProperty(value = "Read", required = true)
    private boolean read;

    /*
     * Indicates whether all write requests should be logged.
     */
    @JsonProperty(value = "Write", required = true)
    private boolean write;

    /*
     * the retention policy
     */
    @JsonProperty(value = "RetentionPolicy", required = true)
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
}
