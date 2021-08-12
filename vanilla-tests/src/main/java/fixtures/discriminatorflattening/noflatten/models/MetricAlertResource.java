package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The metric alert resource. */
@Fluent
public final class MetricAlertResource {
    /*
     * The alert rule properties of the resource.
     */
    @JsonProperty(value = "properties", required = true)
    private MetricAlertProperties properties;

    /**
     * Get the properties property: The alert rule properties of the resource.
     *
     * @return the properties value.
     */
    public MetricAlertProperties getProperties() {
        return this.properties;
    }

    /**
     * Set the properties property: The alert rule properties of the resource.
     *
     * @param properties the properties value to set.
     * @return the MetricAlertResource object itself.
     */
    public MetricAlertResource setProperties(MetricAlertProperties properties) {
        this.properties = properties;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getProperties() == null) {
            throw new IllegalArgumentException("Missing required property properties in model MetricAlertResource");
        } else {
            getProperties().validate();
        }
    }
}
