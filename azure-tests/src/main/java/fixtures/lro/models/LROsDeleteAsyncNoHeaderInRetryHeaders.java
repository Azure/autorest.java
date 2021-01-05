package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.logging.ClientLogger;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The LROsDeleteAsyncNoHeaderInRetryHeaders model.
 */
@Fluent
public final class LROsDeleteAsyncNoHeaderInRetryHeaders {
    @JsonIgnore
    private final ClientLogger logger = new ClientLogger(LROsDeleteAsyncNoHeaderInRetryHeaders.class);

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
     * @return the LROsDeleteAsyncNoHeaderInRetryHeaders object itself.
     */
    public LROsDeleteAsyncNoHeaderInRetryHeaders withLocation(String location) {
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
