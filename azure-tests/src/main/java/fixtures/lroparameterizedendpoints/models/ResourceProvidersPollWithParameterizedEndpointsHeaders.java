package fixtures.lroparameterizedendpoints.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The ResourceProvidersPollWithParameterizedEndpointsHeaders model. */
@Fluent
public final class ResourceProvidersPollWithParameterizedEndpointsHeaders {
    @JsonIgnore
    private final ClientLogger logger = new ClientLogger(ResourceProvidersPollWithParameterizedEndpointsHeaders.class);

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
    public String location() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     *
     * @param location the location value to set.
     * @return the ResourceProvidersPollWithParameterizedEndpointsHeaders object itself.
     */
    public ResourceProvidersPollWithParameterizedEndpointsHeaders withLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
