// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
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
    @Generated
    private Duration field;

    /**
     * Creates an instance of DurationWrapper class.
     */
    @Generated
    public DurationWrapper() {
    }

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    @Generated
    public Duration getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the DurationWrapper object itself.
     */
    @Generated
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

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("field", CoreUtils.durationToStringWithDays(this.field));
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
    @Generated
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
