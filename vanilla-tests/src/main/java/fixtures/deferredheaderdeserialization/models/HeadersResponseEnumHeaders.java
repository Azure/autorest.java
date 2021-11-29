package fixtures.deferredheaderdeserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.http.HttpHeaders;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersResponseEnumHeaders model. */
@Fluent
public final class HeadersResponseEnumHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private GreyscaleColors value;

    // Maintains deserialization status for value.
    private boolean valueHasBeenDeserialized;

    // HttpHeaders containing the raw property values.
    private final HttpHeaders rawHeaders;

    /** Creates an instance of HeadersResponseEnumHeaders class. */
    HeadersResponseEnumHeaders(HttpHeaders rawHeaders) {
        this.rawHeaders = rawHeaders;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public GreyscaleColors getValue() {
        if (!this.valueHasBeenDeserialized) {
            this.value = GreyscaleColors.fromString(rawHeaders.getValue("value"));
            this.valueHasBeenDeserialized = true;
        }
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the HeadersResponseEnumHeaders object itself.
     */
    public HeadersResponseEnumHeaders setValue(GreyscaleColors value) {
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
