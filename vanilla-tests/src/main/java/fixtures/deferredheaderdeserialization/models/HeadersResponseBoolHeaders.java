package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseBoolHeaders model. */
@Fluent
public final class HeadersResponseBoolHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private Boolean value;

    // Maintains deserialization status for value.
    private boolean valueHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseBoolHeaders class. */
    HeadersResponseBoolHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public Boolean isValue() {
        if (!this.valueHasBeenDeserialized) {
            if (rawHeaders.getValue("value") != null) {
                this.value = Boolean.valueOf(rawHeaders.getValue("value"));
            }
            this.valueHasBeenDeserialized = true;
        }
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseBoolHeaders object itself.
     */
    public HeadersResponseBoolHeaders setValue(Boolean value) {
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
