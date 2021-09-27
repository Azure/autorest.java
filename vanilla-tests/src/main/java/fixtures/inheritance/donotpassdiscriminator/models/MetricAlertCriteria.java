package fixtures.inheritance.donotpassdiscriminator.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.HashMap;
import java.util.Map;

/** The rule criteria that defines the conditions of the alert rule. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "odata\\.type",
        defaultImpl = MetricAlertCriteria.class)
@JsonTypeName("MetricAlertCriteria")
@JsonSubTypes({
    @JsonSubTypes.Type(
            name = "Microsoft.Azure.Monitor.SingleResourceMultipleMetricCriteria",
            value = MetricAlertSingleResourceMultipleMetricCriteria.class)
})
@JsonFlatten
@Fluent
public class MetricAlertCriteria {
    /*
     * The rule criteria that defines the conditions of the alert rule.
     */
    @JsonIgnore private Map<String, Object> additionalProperties;

    /**
     * Get the additionalProperties property: The rule criteria that defines the conditions of the alert rule.
     *
     * @return the additionalProperties value.
     */
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    /**
     * Set the additionalProperties property: The rule criteria that defines the conditions of the alert rule.
     *
     * @param additionalProperties the additionalProperties value to set.
     * @return the MetricAlertCriteria object itself.
     */
    public MetricAlertCriteria setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
        return this;
    }

    @JsonAnySetter
    void setAdditionalProperties(String key, Object value) {
        if (additionalProperties == null) {
            additionalProperties = new HashMap<>();
        }
        additionalProperties.put(key.replace("\\.", "."), value);
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
