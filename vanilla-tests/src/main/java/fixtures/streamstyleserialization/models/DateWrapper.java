// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.time.LocalDate;

/** The DateWrapper model. */
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
    public void validate() {}

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("field", this.field == null ? null : this.field.toString(), false);
        jsonWriter.writeStringField("leap", this.leap == null ? null : this.leap.toString(), false);
        return jsonWriter.writeEndObject().flush();
    }

    /**
     * Reads an instance of DateWrapper from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DateWrapper if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     */
    public static DateWrapper fromJson(JsonReader jsonReader) {
        return jsonReader.readObject(
                reader -> {
                    LocalDate field = null;
                    LocalDate leap = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("field".equals(fieldName)) {
                            field = reader.getNullableValue(r -> LocalDate.parse(reader.getStringValue()));
                        } else if ("leap".equals(fieldName)) {
                            leap = reader.getNullableValue(r -> LocalDate.parse(reader.getStringValue()));
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DateWrapper deserializedValue = new DateWrapper();
                    deserializedValue.field = field;
                    deserializedValue.leap = leap;

                    return deserializedValue;
                });
    }
}
