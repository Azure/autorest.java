package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;

/** The HeadersResponseDurationHeaders model. */
@Fluent
public final class HeadersResponseDurationHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Duration value;

    // Maintains deserialization status for value.
    private boolean valueHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseDurationHeaders class. */
    HeadersResponseDurationHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Duration getValue() {
        if (!this.valueHasBeenDeserialized) {
            if (rawHeaders.getValue("value") != null) {
                this.value = Duration.parse(rawHeaders.getValue("value"));
            }
            this.valueHasBeenDeserialized = true;
        }
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

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
