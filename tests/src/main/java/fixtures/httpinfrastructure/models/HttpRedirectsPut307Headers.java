package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HttpRedirectsPut307Headers model.
 */
@Fluent
public final class HttpRedirectsPut307Headers {
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
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The Location property.
     * 
     * @param location the location value to set.
     * @return the HttpRedirectsPut307Headers object itself.
     */
    public HttpRedirectsPut307Headers setLocation(String location) {
        this.location = location;
        return this;
    }

    public void validate() {
    }
}
