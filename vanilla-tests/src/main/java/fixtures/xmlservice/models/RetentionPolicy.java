package fixtures.xmlservice.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/** The RetentionPolicy model. */
@JacksonXmlRootElement(localName = "RetentionPolicy")
@Fluent
public final class RetentionPolicy {
    /*
     * Indicates whether a retention policy is enabled for the storage service
     */
    @JsonProperty(value = "Enabled", required = true)
    private boolean enabled;

    /*
     * Indicates the number of days that metrics or logging or soft-deleted
     * data should be retained. All data older than this value will be deleted
     */
    @JsonProperty(value = "Days")
    private Integer days;

    /**
     * Get the enabled property: Indicates whether a retention policy is enabled for the storage service.
     *
     * @return the enabled value.
     */
    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: Indicates whether a retention policy is enabled for the storage service.
     *
     * @param enabled the enabled value to set.
     * @return the RetentionPolicy object itself.
     */
    public RetentionPolicy setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the days property: Indicates the number of days that metrics or logging or soft-deleted data should be
     * retained. All data older than this value will be deleted.
     *
     * @return the days value.
     */
    public Integer getDays() {
        return this.days;
    }

    /**
     * Set the days property: Indicates the number of days that metrics or logging or soft-deleted data should be
     * retained. All data older than this value will be deleted.
     *
     * @param days the days value to set.
     * @return the RetentionPolicy object itself.
     */
    public RetentionPolicy setDays(Integer days) {
        this.days = days;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
