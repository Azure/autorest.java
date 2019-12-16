package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * The HeadersResponseDatetimeHeaders model.
 */
@Fluent
public final class HeadersResponseDatetimeHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private OffsetDateTime value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public OffsetDateTime getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDatetimeHeaders object itself.
     */
    public HeadersResponseDatetimeHeaders setValue(OffsetDateTime value) {
        this.value = value;
        return this;
    }

    public void validate() {
    }
}
