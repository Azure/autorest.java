// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The DateWrapper model.
 */
@Fluent
public final class DateWrapper implements JsonSerializable<DateWrapper> {
    /*
     * The field property.
     */
    private LocalDate field;

    /*
     * The leap property.
     */
    private LocalDate leap;

    /**
     * Creates an instance of DateWrapper class.
     */
    public DateWrapper() {
    }

    /**
     * Get the field property: The field property.
     * 
     * @return the field value.
     */
    public LocalDate getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     * 
     * @param field the field value to set.
     * @return the DateWrapper object itself.
     */
    public DateWrapper setField(LocalDate field) {
        this.field = field;
        return this;
    }

    /**
     * Get the leap property: The leap property.
     * 
     * @return the leap value.
     */
    public LocalDate getLeap() {
        return this.leap;
    }

    /**
     * Set the leap property: The leap property.
     * 
     * @param leap the leap value to set.
     * @return the DateWrapper object itself.
     */
    public DateWrapper setLeap(LocalDate leap) {
        this.leap = leap;
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
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("field", Objects.toString(this.field, null));
        jsonWriter.writeStringField("leap", Objects.toString(this.leap, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DateWrapper from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DateWrapper if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DateWrapper.
     */
    public static DateWrapper fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DateWrapper deserializedDateWrapper = new DateWrapper();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("field".equals(fieldName)) {
                    deserializedDateWrapper.field
                        = reader.getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()));
                } else if ("leap".equals(fieldName)) {
                    deserializedDateWrapper.leap
                        = reader.getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDateWrapper;
        });
    }
}
