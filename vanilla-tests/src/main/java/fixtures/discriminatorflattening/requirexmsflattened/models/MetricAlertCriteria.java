// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.requirexmsflattened.models;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The rule criteria that defines the conditions of the alert rule.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "odata.type",
    defaultImpl = MetricAlertCriteria.class,
    visible = true)
@JsonTypeName("MetricAlertCriteria")
@JsonSubTypes({
    @JsonSubTypes.Type(
        name = "Microsoft.Azure.Monitor.SingleResourceMultipleMetricCriteria",
        value = MetricAlertSingleResourceMultipleMetricCriteria.class) })
@Fluent
public class MetricAlertCriteria {
    /*
     * specifies the type of the alert criteria.
     */
    @JsonTypeId
    @JsonProperty(value = "odata.type", required = true)
    Odatatype odataType;

    /*
     * The rule criteria that defines the conditions of the alert rule.
     */
    @JsonIgnore
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of MetricAlertCriteria class.
     */
    public MetricAlertCriteria() {
        this.odataType = Odatatype.fromString("MetricAlertCriteria");
    }

    /**
     * Get the odataType property: specifies the type of the alert criteria.
     * 
     * @return the odataType value.
     */
    public Odatatype getOdataType() {
        return this.odataType;
    }

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
            additionalProperties = new LinkedHashMap<>();
        }
        additionalProperties.put(key, value);
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }
}
