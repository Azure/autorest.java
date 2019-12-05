package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/**
 * The HeadersResponseDurationHeaders model.
 */
@Fluent
public final class HeadersResponseDurationHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Duration value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public Duration getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDurationHeaders object itself.
     */
    public HeadersResponseDurationHeaders setValue(Duration value) {
        this.value = value;
        return this;
    }
}
