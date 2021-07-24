package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** LoadBalancer resource. */
@Fluent
public final class LoadBalancer {
    /*
     * Properties of load balancer.
     */
    @JsonProperty(value = "properties")
    private LoadBalancerPropertiesFormat properties;

    /**
     * Get the properties property: Properties of load balancer.
     *
     * @return the properties value.
     */
    public LoadBalancerPropertiesFormat getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: Properties of load balancer.
     *
     * @param properties the properties value to set.
     * @return the LoadBalancer object itself.
     */
    public LoadBalancer setProperties(LoadBalancerPropertiesFormat properties) {
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
