package fixtures.azurespecials.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HeadersCustomNamedRequestIdHeaders model. */
@Fluent
public final class HeadersCustomNamedRequestIdHeaders {
    /*
     * The foo-request-id property.
     */
    @JsonProperty(value = "foo-request-id")
    private String fooRequestId;

    /**
     * Get the fooRequestId property: The foo-request-id property.
     *
     * @return the fooRequestId value.
     */
    public String getFooRequestId() {
        return this.fooRequestId;
    }

    /**
     * Set the fooRequestId property: The foo-request-id property.
     *
     * @param fooRequestId the fooRequestId value to set.
     * @return the HeadersCustomNamedRequestIdHeaders object itself.
     */
    public HeadersCustomNamedRequestIdHeaders setFooRequestId(String fooRequestId) {
        this.fooRequestId = fooRequestId;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
