package fixtures.header.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.implementation.util.ImplUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HeadersResponseByteHeaders model.
 */
@Fluent
public final class HeadersResponseByteHeaders {
    /*
     * The value property.
     */
    @JsonProperty(value = "value")
    private byte[] value;

    /**
     * Get the value property: The value property.
     * 
     * @return the value value.
     */
    public byte[] getValue() {
        return ImplUtils.clone(this.value);
    }

    /**
     * Set the value property: The value property.
     * 
     * @param value the value value to set.
     * @return the HeadersResponseByteHeaders object itself.
     */
    public HeadersResponseByteHeaders setValue(byte[] value) {
        this.value = ImplUtils.clone(value);
        return this;
    }
}
