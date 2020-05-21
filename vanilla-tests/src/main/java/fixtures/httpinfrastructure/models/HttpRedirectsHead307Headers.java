package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The HttpRedirectsHead307Headers model. */
@Fluent
public final class HttpRedirectsHead307Headers {
    /*
     * The Location property.
     */
    @JsonProperty(value = "Location")
    private String location;

    /** Creates an instance of HttpRedirectsHead307Headers class. */
    public HttpRedirectsHead307Headers() {
        location = "/http/success/head/200";
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
     * @return the HttpRedirectsHead307Headers object itself.
     */
    public HttpRedirectsHead307Headers setLocation(String location) {
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
