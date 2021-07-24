package fixtures.discriminatorflattening.noflatten.models;

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
    public BackendAddressPoolPropertiesFormat getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of load balancer backend address pool.
     *
     * @param properties the properties value to set.
     * @return the BackendAddressPool object itself.
     */
    public BackendAddressPool setProperties(BackendAddressPoolPropertiesFormat properties) {
        this.properties = properties;
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
