// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.discriminatorflattening.requirexmsflattened.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Specifies the metric alert criteria for a single resource that has multiple metric criteria.
 */
@Fluent
public final class MetricAlertSingleResourceMultipleMetricCriteria extends MetricAlertCriteria {
    /*
     * The list of metric criteria for this 'all of' operation.
     */
    private List<String> allOf;

    /**
     * Creates an instance of MetricAlertSingleResourceMultipleMetricCriteria class.
     */
    public MetricAlertSingleResourceMultipleMetricCriteria() {
        setOdataType(Odatatype.MICROSOFT_AZURE_MONITOR_SINGLE_RESOURCE_MULTIPLE_METRIC_CRITERIA);
    }

    /**
     * Get the allOf property: The list of metric criteria for this 'all of' operation.
     * 
     * @return the allOf value.
     */
    public List<String> getAllOf() {
        return this.allOf;
    }

    /**
     * Set the allOf property: The list of metric criteria for this 'all of' operation.
     * 
     * @param allOf the allOf value to set.
     * @return the MetricAlertSingleResourceMultipleMetricCriteria object itself.
     */
    public MetricAlertSingleResourceMultipleMetricCriteria setAllOf(List<String> allOf) {
        this.allOf = allOf;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("odata.type", getOdataType() == null ? null : getOdataType().toString());
        jsonWriter.writeArrayField("allOf", this.allOf, (writer, element) -> writer.writeString(element));
        if (getAdditionalProperties() != null) {
            for (Map.Entry<String, Object> additionalProperty : getAdditionalProperties().entrySet()) {
                jsonWriter.writeUntypedField(additionalProperty.getKey(), additionalProperty.getValue());
            }
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MetricAlertSingleResourceMultipleMetricCriteria from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MetricAlertSingleResourceMultipleMetricCriteria if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the MetricAlertSingleResourceMultipleMetricCriteria.
     */
    public static MetricAlertSingleResourceMultipleMetricCriteria fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MetricAlertSingleResourceMultipleMetricCriteria deserializedMetricAlertSingleResourceMultipleMetricCriteria
                = new MetricAlertSingleResourceMultipleMetricCriteria();
            Map<String, Object> additionalProperties = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("odata.type".equals(fieldName)) {
                    deserializedMetricAlertSingleResourceMultipleMetricCriteria
                        .setOdataType(Odatatype.fromString(reader.getString()));
                } else if ("allOf".equals(fieldName)) {
                    List<String> allOf = reader.readArray(reader1 -> reader1.getString());
                    deserializedMetricAlertSingleResourceMultipleMetricCriteria.allOf = allOf;
                } else {
                    if (additionalProperties == null) {
                        additionalProperties = new LinkedHashMap<>();
                    }

                    additionalProperties.put(fieldName, reader.readUntyped());
                }
            }
            deserializedMetricAlertSingleResourceMultipleMetricCriteria.setAdditionalProperties(additionalProperties);

            return deserializedMetricAlertSingleResourceMultipleMetricCriteria;
        });
    }
}
