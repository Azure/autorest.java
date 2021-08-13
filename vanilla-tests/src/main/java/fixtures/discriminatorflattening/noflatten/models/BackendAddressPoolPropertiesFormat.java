package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Properties of the backend address pool. */
@Fluent
public final class BackendAddressPoolPropertiesFormat {
    /*
     * The location of the backend address pool.
     */
    @JsonProperty(value = "location")
    private String location;

    /**
     * Get the location property: The location of the backend address pool.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location of the backend address pool.
     *
     * @param location the location value to set.
     * @return the BackendAddressPoolPropertiesFormat object itself.
     */
    public BackendAddressPoolPropertiesFormat setLocation(String location) {
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
