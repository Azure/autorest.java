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
    private BackendAddressPoolPropertiesFormat innerProperties;

    /**
     * Get the innerProperties property: Properties of load balancer backend address pool.
     *
     * @return the innerProperties value.
     */
    private BackendAddressPoolPropertiesFormat getInnerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the location property: The location of the backend address pool.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.getInnerProperties() == null ? null : this.getInnerProperties().getLocation();
    }

    /**
     * Set the location property: The location of the backend address pool.
     *
     * @param location the location value to set.
     * @return the BackendAddressPool object itself.
     */
    public BackendAddressPool setLocation(String location) {
        if (this.getInnerProperties() == null) {
            this.innerProperties = new BackendAddressPoolPropertiesFormat();
        }
        this.getInnerProperties().setLocation(location);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getInnerProperties() != null) {
            getInnerProperties().validate();
        }
    }
}
