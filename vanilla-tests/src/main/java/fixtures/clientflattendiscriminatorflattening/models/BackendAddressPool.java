package fixtures.clientflattendiscriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** Pool of backend IP addresses. */
@Fluent
public final class BackendAddressPool {
    /*
     * Properties of load balancer backend address pool.
     */
    @JsonProperty(value = "properties")
    private BackendAddressPoolPropertiesFormat properties;

    /**
     * Get the properties property: Properties of load balancer backend address pool.
     *
     * @return the properties value.
     */
    private BackendAddressPoolPropertiesFormat getProperties() {
        return this.properties;
    }

    /**
     * Get the location property: The location of the backend address pool.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.getProperties() == null ? null : this.getProperties().getLocation();
    }

    /**
     * Set the location property: The location of the backend address pool.
     *
     * @param location the location value to set.
     * @return the BackendAddressPool object itself.
     */
    public BackendAddressPool setLocation(String location) {
        if (this.getProperties() == null) {
            this.properties = new BackendAddressPoolPropertiesFormat();
        }
        this.getProperties().setLocation(location);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProperties() != null) {
            getProperties().validate();
        }
    }
}
