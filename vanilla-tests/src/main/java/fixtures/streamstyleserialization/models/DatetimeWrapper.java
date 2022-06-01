// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.time.OffsetDateTime;

/** The DatetimeWrapper model. */
@Fluent
public final class DatetimeWrapper implements JsonSerializable<DatetimeWrapper> {
    private OffsetDateTime field;

    private OffsetDateTime now;

    /**
     * Get the field property: The field property.
     *
     * @return the field value.
     */
    public OffsetDateTime getField() {
        return this.field;
    }

    /**
     * Set the field property: The field property.
     *
     * @param field the field value to set.
     * @return the DatetimeWrapper object itself.
     */
    public DatetimeWrapper setField(OffsetDateTime field) {
        this.field = field;
        return this;
    }

    /**
     * Get the now property: The now property.
     *
     * @return the now value.
     */
    public OffsetDateTime getNow() {
        return this.now;
    }

    /**
     * Set the now property: The now property.
     *
     * @param now the now value to set.
     * @return the DatetimeWrapper object itself.
     */
    public DatetimeWrapper setNow(OffsetDateTime now) {
        this.now = now;
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
        return jsonWriter.flush();
    }

    public static DatetimeWrapper fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    OffsetDateTime field = null;
                    OffsetDateTime now = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("field".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                field = OffsetDateTime.parse(reader.getStringValue());
                            }
                        } else if ("now".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                now = OffsetDateTime.parse(reader.getStringValue());
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DatetimeWrapper deserializedValue = new DatetimeWrapper();
                    deserializedValue.setField(field);
                    deserializedValue.setNow(now);

                    return deserializedValue;
                });
    }
}
