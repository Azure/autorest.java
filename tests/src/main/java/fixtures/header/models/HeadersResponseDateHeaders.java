package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

/**
 * The HeadersResponseDateHeaders model.
 */
@Fluent
public final class HeadersResponseDateHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private LocalDate value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public LocalDate getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseDateHeaders object itself.
     */
    public HeadersResponseDateHeaders setValue(LocalDate value) {
        this.value = value;
        return this;
    }
}
