package fixtures.lro.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/** The Resource model. */
@Fluent
public class Resource {
    /*
     * Resource Id
     */
    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    /*
     * Resource Type
     */
    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    private String type;

    /*
     * Dictionary of <string>
     */
    @JsonProperty(value = "tags")
    private Map<String, String> tags;

    /*
     * Resource Location
     */
    @JsonProperty(value = "location")
    private String location;

    /*
     * Resource Name
     */
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /**
     * Get the id property: Resource Id.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Get the type property: Resource Type.
     *
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the tags property: Dictionary of &lt;string&gt;.
     *
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: Dictionary of &lt;string&gt;.
     *
     * @param tags the tags value to set.
     * @return the Resource object itself.
     */
    public Resource setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Get the location property: Resource Location.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: Resource Location.
     *
     * @param location the location value to set.
     * @return the Resource object itself.
     */
    public Resource setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the name property: Resource Name.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
