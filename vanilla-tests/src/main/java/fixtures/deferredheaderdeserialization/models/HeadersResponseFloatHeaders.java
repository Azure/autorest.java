package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseFloatHeaders model. */
@Fluent
public final class HeadersResponseFloatHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Float value;

    // Maintains deserialization status for value.
    private boolean valueHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseFloatHeaders class. */
    HeadersResponseFloatHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Float getValue() {
        if (!this.valueHasBeenDeserialized) {
            this.value = Float.valueOf(rawHeaders.getValue("value"));
            this.valueHasBeenDeserialized = true;
        }
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseFloatHeaders object itself.
     */
    public HeadersResponseFloatHeaders setValue(Float value) {
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
