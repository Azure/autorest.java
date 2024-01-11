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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The rule criteria that defines the conditions of the alert rule.
 */
@Fluent
public class MetricAlertCriteria implements JsonSerializable<MetricAlertCriteria> {
    /*
     * The rule criteria that defines the conditions of the alert rule.
     */
    private Map<String, Object> additionalProperties;

    /**
     * Creates an instance of MetricAlertCriteria class.
     */
    public MetricAlertCriteria() {
    }

    /**
     * Get the additionalProperties property: The rule criteria that defines the conditions of the alert rule.
     * 
     * @return the additionalProperties value.
     */
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

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        if (additionalProperties != null) {
            for (Map.Entry<String, Object> additionalProperty : additionalProperties.entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MetricAlertCriteria from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MetricAlertCriteria if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing the polymorphic discriminator.
     * @throws IOException If an error occurs while reading the MetricAlertCriteria.
     */
    public static MetricAlertCriteria fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            JsonReader readerToUse = reader.bufferObject();

            readerToUse.nextToken(); // Prepare for reading
            while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = readerToUse.getFieldName();
                readerToUse.nextToken();
                if ("odata.type".equals(fieldName)) {
                    discriminatorValue = readerToUse.getString();
                    break;
                } else {
                    readerToUse.skipChildren();
                }
            }
            // Use the discriminator value to determine which subtype should be deserialized.
            if ("Microsoft.Azure.Monitor.SingleResourceMultipleMetricCriteria".equals(discriminatorValue)) {
                return MetricAlertSingleResourceMultipleMetricCriteria.fromJson(readerToUse.reset());
            } else {
                return fromJsonKnownDiscriminator(readerToUse.reset());
            }
        });
    }

    static MetricAlertCriteria fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MetricAlertCriteria deserializedMetricAlertCriteria = new MetricAlertCriteria();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if (additionalProperties == null) {
                    additionalProperties = new LinkedHashMap<>();
                }

                additionalProperties.put(fieldName, reader.readUntyped());
            }
            deserializedMetricAlertCriteria.additionalProperties = additionalProperties;

            return deserializedMetricAlertCriteria;
        });
    }
}
