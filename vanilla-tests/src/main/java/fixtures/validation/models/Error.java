// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.validation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Error model.
 */
@Fluent
public final class Error implements JsonSerializable<Error> {
    /*
     * The code property.
     */
    private Integer code;

    /*
     * The message property.
     */
    private String message;

    /*
     * The fields property.
     */
    private String fields;

    /**
     * Creates an instance of Error class.
     */
    public Error() {
    }

    /**
     * Get the code property: The code property.
     * 
     * @return the code value.
     */
    public Integer getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     * 
     * @param code the code value to set.
     * @return the Error object itself.
     */
    public Error setCode(Integer code) {
        this.code = code;
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
     * Get the fields property: The fields property.
     * 
     * @return the fields value.
     */
    public String getFields() {
        return this.fields;
    }

    /**
     * Set the fields property: The fields property.
     * 
     * @param fields the fields value to set.
     * @return the Error object itself.
     */
    public Error setFields(String fields) {
        this.fields = fields;
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
        jsonWriter.writeNumberField("code", this.code);
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeStringField("fields", this.fields);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Error from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Error if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the Error.
     */
    public static Error fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Error deserializedError = new Error();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("code".equals(fieldName)) {
                    deserializedError.code = reader.getNullable(JsonReader::getInt);
                } else if ("message".equals(fieldName)) {
                    deserializedError.message = reader.getString();
                } else if ("fields".equals(fieldName)) {
                    deserializedError.fields = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedError;
        });
    }
}
