package fixtures.clientflattendiscriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

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
    private LoadBalancerPropertiesFormat getProperties() {
        return this.properties;
    }

    /**
     * Get the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @return the backendAddressPools value.
     */
    public List<BackendAddressPool> getBackendAddressPools() {
        return this.getProperties() == null ? null : this.getProperties().getBackendAddressPools();
    }

    /**
     * Set the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @param backendAddressPools the backendAddressPools value to set.
     * @return the LoadBalancer object itself.
     */
    public LoadBalancer setBackendAddressPools(List<BackendAddressPool> backendAddressPools) {
        if (this.getProperties() == null) {
            this.properties = new LoadBalancerPropertiesFormat();
        }
        this.getProperties().setBackendAddressPools(backendAddressPools);
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
