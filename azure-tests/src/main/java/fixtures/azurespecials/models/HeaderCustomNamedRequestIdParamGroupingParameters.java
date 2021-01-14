package fixtures.azurespecials.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Parameter group. */
@Fluent
public final class HeaderCustomNamedRequestIdParamGroupingParameters {
    /*
     * The fooRequestId
     */
    @JsonProperty(value = "foo-client-request-id", required = true)
    private String fooClientRequestId;

    /**
     * Get the fooClientRequestId property: The fooRequestId.
     *
     * @return the fooClientRequestId value.
     */
    public String getFooClientRequestId() {
        return this.fooClientRequestId;
    }

    /**
     * Set the fooClientRequestId property: The fooRequestId.
     *
     * @param fooClientRequestId the fooClientRequestId value to set.
     * @return the HeaderCustomNamedRequestIdParamGroupingParameters object itself.
     */
    public HeaderCustomNamedRequestIdParamGroupingParameters setFooClientRequestId(String fooClientRequestId) {
        this.fooClientRequestId = fooClientRequestId;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getFooClientRequestId() == null) {
            throw new IllegalArgumentException(
                    "Missing required property fooClientRequestId in model HeaderCustomNamedRequestIdParamGroupingParameters");
        }
    }
}
