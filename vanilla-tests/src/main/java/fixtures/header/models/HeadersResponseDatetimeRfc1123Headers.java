package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The HeadersResponseDatetimeRfc1123Headers model.
 */
@Fluent
public final class HeadersResponseDatetimeRfc1123Headers {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private DateTimeRfc1123 value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public OffsetDateTime getValue() {
        if (this.value == null) {
            return null;
        }
        return this.value.getDateTime();
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDatetimeRfc1123Headers object itself.
     */
    public HeadersResponseDatetimeRfc1123Headers setValue(OffsetDateTime value) {
        if (value == null) {
            this.value = null;
        } else {
            this.value = new DateTimeRfc1123(value);
        }
        return this;
    }

    public void validate() {
    }
}
