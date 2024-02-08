// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.
package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import fixtures.streamstyleserialization.implementation.CoreToCodegenBridgeUtils;
import java.io.IOException;
import java.time.Duration;

/**
 * The DurationWrapper model.
 */
@Fluent
public final class DurationWrapper implements JsonSerializable<DurationWrapper> {

    /*
     * The field property.
     */
    private Duration field;

    /**
     * Creates an instance of DurationWrapper class.
     */
    public DurationWrapper() {
    }

    /**
     * Get the field property: The field property.
     *
     * @return the field value.
     */
    public Duration getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     *
     * @param field the field value to set.
     * @return the DurationWrapper object itself.
     */
    public DurationWrapper setField(Duration field) {
        this.field = field;
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
        jsonWriter.writeStringField("field", CoreToCodegenBridgeUtils.durationToStringWithDays(this.field));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DurationWrapper from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DurationWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DurationWrapper.
     */
    public static DurationWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DurationWrapper deserializedDurationWrapper = new DurationWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("field".equals(fieldName)) {
                    deserializedDurationWrapper.field
                        = reader.getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedDurationWrapper;
        });
    }
}
