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
    private MetricAlertProperties innerProperties = new MetricAlertProperties();

    /**
     * Get the innerProperties property: The alert rule properties of the resource.
     *
     * @return the innerProperties value.
     */
    private MetricAlertProperties getInnerProperties() {
        return this.innerProperties;
    }

    /**
     * Get the criteria property: defines the specific alert criteria information.
     *
     * @return the criteria value.
     */
    public MetricAlertCriteria getCriteria() {
        return this.getInnerProperties() == null ? null : this.getInnerProperties().getCriteria();
    }

    /**
     * Set the criteria property: defines the specific alert criteria information.
     *
     * @param criteria the criteria value to set.
     * @return the MetricAlertResource object itself.
     */
    public MetricAlertResource setCriteria(MetricAlertCriteria criteria) {
        if (this.getInnerProperties() == null) {
            this.innerProperties = new MetricAlertProperties();
        }
        this.getInnerProperties().setCriteria(criteria);
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getInnerProperties() == null) {
            throw new IllegalArgumentException(
                    "Missing required property innerProperties in model MetricAlertResource");
        } else {
            getInnerProperties().validate();
        }
    }
}
