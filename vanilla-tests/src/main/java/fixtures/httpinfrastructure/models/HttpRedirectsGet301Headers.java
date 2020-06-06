package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HttpRedirectsGet301Headers model. */
@Fluent
public final class HttpRedirectsGet301Headers {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /** Creates an instance of HttpRedirectsGet301Headers class. */
    public HttpRedirectsGet301Headers() {
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
     * @return the HttpRedirectsGet301Headers object itself.
     */
    public HttpRedirectsGet301Headers setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
