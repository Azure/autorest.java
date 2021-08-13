package fixtures.discriminatorflattening.clientflatten.models;

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
    private LoadBalancerPropertiesFormat innerProperties;

    /**
     * Get the innerProperties property: Properties of load balancer.
     *
     * @return the innerProperties value.
     */
    private LoadBalancerPropertiesFormat getInnerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @return the backendAddressPools value.
     */
    public List<BackendAddressPool> getBackendAddressPools() {
        return this.getInnerProperties() == null ? null : this.getInnerProperties().getBackendAddressPools();
    }

    /**
     * Set the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @param backendAddressPools the backendAddressPools value to set.
     * @return the LoadBalancer object itself.
     */
    public LoadBalancer setBackendAddressPools(List<BackendAddressPool> backendAddressPools) {
        if (this.getInnerProperties() == null) {
            this.innerProperties = new LoadBalancerPropertiesFormat();
        }
        this.getInnerProperties().setBackendAddressPools(backendAddressPools);
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
