// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.noflatten.models;

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
public final class MetricAlertResource implements JsonSerializable<MetricAlertResource> {
    /*
     * The alert rule properties of the resource.
     */
    private MetricAlertProperties properties;

    /**
     * Creates an instance of MetricAlertResource class.
     */
    public MetricAlertResource() {
    }

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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("properties", this.properties);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MetricAlertResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MetricAlertResource if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the MetricAlertResource.
     */
    public static MetricAlertResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MetricAlertResource deserializedMetricAlertResource = new MetricAlertResource();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("properties".equals(fieldName)) {
                    deserializedMetricAlertResource.properties = MetricAlertProperties.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedMetricAlertResource;
        });
    }
}
