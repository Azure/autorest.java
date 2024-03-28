// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.clientflatten.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * An alert rule.
 */
@Fluent
public final class MetricAlertProperties implements JsonSerializable<MetricAlertProperties> {
    /*
     * defines the specific alert criteria information.
     */
    private MetricAlertCriteria criteria;

    /**
     * Creates an instance of MetricAlertProperties class.
     */
    public MetricAlertProperties() {
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("criteria", this.criteria);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MetricAlertProperties from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MetricAlertProperties if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the MetricAlertProperties.
     */
    public static MetricAlertProperties fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MetricAlertProperties deserializedMetricAlertProperties = new MetricAlertProperties();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("criteria".equals(fieldName)) {
                    deserializedMetricAlertProperties.criteria = MetricAlertCriteria.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedMetricAlertProperties;
        });
    }
}
