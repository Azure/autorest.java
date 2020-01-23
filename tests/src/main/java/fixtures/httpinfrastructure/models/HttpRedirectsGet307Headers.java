package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The HttpRedirectsGet307Headers model.
 */
@Fluent
public final class HttpRedirectsGet307Headers {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /**
     * Creates an instance of HttpRedirectsGet307Headers class.
     */
    public HttpRedirectsGet307Headers() {
        location = "/http/success/get/200";
    }

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
     * @return the HttpRedirectsGet307Headers object itself.
     */
    public HttpRedirectsGet307Headers setLocation(String location) {
        this.location = location;
        return this;
    }

    public void validate() {
    }
}
