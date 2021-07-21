package fixtures.clientflattendiscriminatorflattening.models;

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
    private MetricAlertProperties getProperties() {
        return this.properties;
    }

    /**
     * Get the criteria property: defines the specific alert criteria information.
     *
     * @return the criteria value.
     */
    public MetricAlertCriteria getCriteria() {
        return this.getProperties() == null ? null : this.getProperties().getCriteria();
    }

    /**
     * Set the criteria property: defines the specific alert criteria information.
     *
     * @param criteria the criteria value to set.
     * @return the MetricAlertResource object itself.
     */
    public MetricAlertResource setCriteria(MetricAlertCriteria criteria) {
        if (this.getProperties() == null) {
            this.properties = new MetricAlertProperties();
        }
        this.getProperties().setCriteria(criteria);
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
