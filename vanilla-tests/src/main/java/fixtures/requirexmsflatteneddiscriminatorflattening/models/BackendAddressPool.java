package fixtures.requirexmsflatteneddiscriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Pool of backend IP addresses. */
@JsonFlatten
@Fluent
public class BackendAddressPool {
    /*
     * The location of the backend address pool.
     */
    @JsonProperty(value = "properties.location")
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
     * @return the BackendAddressPool object itself.
     */
    public BackendAddressPool setLocation(String location) {
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
