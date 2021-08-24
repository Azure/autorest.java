package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LROsPutNoHeaderInRetryHeaders model. */
@Fluent
public final class LROsPutNoHeaderInRetryHeaders {
    /*
     * The location property.
     */
    @JsonProperty(value = "location")
    private String location;

    /**
     * Get the location property: The location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     *
     * @param location the location value to set.
     * @return the LROsPutNoHeaderInRetryHeaders object itself.
     */
    public LROsPutNoHeaderInRetryHeaders setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
