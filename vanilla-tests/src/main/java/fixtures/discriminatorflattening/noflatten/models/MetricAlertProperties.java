package fixtures.discriminatorflattening.noflatten.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;

/** An alert rule. */
@Fluent
public final class MetricAlertProperties {
    /*
     * defines the specific alert criteria information.
     */
    @JsonProperty(value = "criteria", required = true)
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
     * @return the MetricAlertProperties object itself.
     */
    public MetricAlertProperties setCriteria(MetricAlertCriteria criteria) {
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
            throw new IllegalArgumentException("Missing required property criteria in model MetricAlertProperties");
        } else {
            getCriteria().validate();
        }
    }
}
