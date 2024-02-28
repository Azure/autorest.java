// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.requirexmsflattened.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The metric alert resource.
 */
@Fluent
public class MetricAlertResource implements JsonSerializable<MetricAlertResource> {
    /*
     * defines the specific alert criteria information.
     */
    private MetricAlertCriteria criteria;

    /**
     * Creates an instance of MetricAlertResource class.
     */
    public MetricAlertResource() {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (criteria != null) {
            jsonWriter.writeStartObject("properties");
            jsonWriter.writeJsonField("criteria", this.criteria);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MetricAlertResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MetricAlertResource if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the MetricAlertResource.
     */
    public static MetricAlertResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MetricAlertResource deserializedMetricAlertResource = new MetricAlertResource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("properties".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("criteria".equals(fieldName)) {
                            deserializedMetricAlertResource.criteria = MetricAlertCriteria.fromJson(reader);
                        } else {
                            reader.skipChildren();
                        }
                    }
                }
                reader.skipChildren();
            }

            return deserializedMetricAlertResource;
        });
    }
}
