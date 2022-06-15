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

/** The Error model. */
@Fluent
public final class Error implements JsonSerializable<Error> {
    private Integer status;

    private String message;

    /**
     * Get the status property: The status property.
     *
     * @return the status value.
     */
    public Integer getStatus() {
        return this.status;
    }

    /**
     * Set the status property: The status property.
     *
     * @param status the status value to set.
     * @return the Error object itself.
     */
    public Error setStatus(Integer status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the Error object itself.
     */
    public Error setMessage(String message) {
        this.message = message;
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
        jsonWriter.writeIntegerField("status", this.status, false);
        jsonWriter.writeStringField("message", this.message, false);
        return jsonWriter.writeEndObject().flush();
    }

    public static Error fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    Integer status = null;
                    String message = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("status".equals(fieldName)) {
                            status = JsonUtils.getNullableProperty(reader, r -> reader.getIntValue());
                        } else if ("message".equals(fieldName)) {
                            message = JsonUtils.getNullableProperty(reader, r -> reader.getStringValue());
                        } else {
                            reader.skipChildren();
                        }
                    }
                    Error deserializedValue = new Error();
                    deserializedValue.setStatus(status);
                    deserializedValue.setMessage(message);

                    return deserializedValue;
                });
    }
}
