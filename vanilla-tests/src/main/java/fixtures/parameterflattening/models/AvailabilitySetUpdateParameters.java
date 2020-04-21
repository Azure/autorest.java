package fixtures.parameterflattening.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * The AvailabilitySetUpdateParameters model.
 */
@Fluent
public final class AvailabilitySetUpdateParameters {
    /*
     * A description about the set of tags.
     */
    @JsonProperty(value = "tags", required = true)
    private Map<String, String> tags;

    /**
     * Get the tags property: A description about the set of tags.
     * 
     * @return the tags value.
     */
    public Map<String, String> getTags() {
        return this.tags;
    }

    /**
     * Set the tags property: A description about the set of tags.
     * 
     * @param tags the tags value to set.
     * @return the AvailabilitySetUpdateParameters object itself.
     */
    public AvailabilitySetUpdateParameters setTags(Map<String, String> tags) {
        this.tags = tags;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getTags() == null) {
            throw new IllegalArgumentException("Missing required property tags in model AvailabilitySetUpdateParameters");
        }
    }
}
