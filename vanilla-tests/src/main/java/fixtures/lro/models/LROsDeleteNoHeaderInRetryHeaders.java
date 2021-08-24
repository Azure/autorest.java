package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The LROsDeleteNoHeaderInRetryHeaders model. */
@Fluent
public final class LROsDeleteNoHeaderInRetryHeaders {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /**
     * Get the location property: The Location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     *
     * @param location the location value to set.
     * @return the LROsDeleteNoHeaderInRetryHeaders object itself.
     */
    public LROsDeleteNoHeaderInRetryHeaders setLocation(String location) {
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
