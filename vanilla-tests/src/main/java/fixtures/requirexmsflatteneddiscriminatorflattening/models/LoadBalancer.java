package fixtures.requirexmsflatteneddiscriminatorflattening.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/** LoadBalancer resource. */
@JsonFlatten
@Fluent
public class LoadBalancer {
    /*
     * Collection of backend address pools used by a load balancer.
     */
    @JsonProperty(value = "properties.backendAddressPools")
    private List<BackendAddressPool> backendAddressPools;

    /**
     * Get the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @return the backendAddressPools value.
     */
    public List<BackendAddressPool> getBackendAddressPools() {
        return this.backendAddressPools;
    }

    /**
     * Set the backendAddressPools property: Collection of backend address pools used by a load balancer.
     *
     * @param backendAddressPools the backendAddressPools value to set.
     * @return the LoadBalancer object itself.
     */
    public LoadBalancer setBackendAddressPools(List<BackendAddressPool> backendAddressPools) {
        this.backendAddressPools = backendAddressPools;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getBackendAddressPools() != null) {
            getBackendAddressPools().forEach(e -> e.validate());
        }
    }
}
