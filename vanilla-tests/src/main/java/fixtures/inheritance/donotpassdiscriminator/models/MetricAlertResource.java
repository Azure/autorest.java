package fixtures.inheritance.donotpassdiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;

/** The metric alert resource. */
@JsonFlatten
@Fluent
public class MetricAlertResource {
    /*
     * defines the specific alert criteria information.
     */
    @JsonProperty(value = "properties.criteria", required = true)
    private MetricAlertCriteria criteria;

    /**
     * Get the criteria property: defines the specific alert criteria information.
     *
     * @return the criteria value.
     */
    public MetricAlertCriteria getCriteria() {
        return this.criteria;
    }

    /**
     * Set the criteria property: defines the specific alert criteria information.
     *
     * @param criteria the criteria value to set.
     * @return the MetricAlertResource object itself.
     */
    public MetricAlertResource setCriteria(MetricAlertCriteria criteria) {
        this.criteria = criteria;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getCriteria() == null) {
            throw new IllegalArgumentException("Missing required property criteria in model MetricAlertResource");
        } else {
            getCriteria().validate();
        }
    }
}
