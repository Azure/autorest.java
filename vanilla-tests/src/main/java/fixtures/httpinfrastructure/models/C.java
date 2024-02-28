// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.httpinfrastructure.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The C model.
 */
@Fluent
public final class C implements JsonSerializable<C> {
    /*
     * The httpCode property.
     */
    private String httpCode;

    /**
     * Creates an instance of C class.
     */
    public C() {
    }

    /**
     * Get the httpCode property: The httpCode property.
     * 
     * @return the httpCode value.
     */
    public String getHttpCode() {
        return this.httpCode;
    }

    /**
     * Set the httpCode property: The httpCode property.
     * 
     * @param httpCode the httpCode value to set.
     * @return the C object itself.
     */
    public C setHttpCode(String httpCode) {
        this.httpCode = httpCode;
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
        jsonWriter.writeStringField("httpCode", this.httpCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of C from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of C if the JsonReader was pointing to an instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the C.
     */
    public static C fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            C deserializedC = new C();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("httpCode".equals(fieldName)) {
                    deserializedC.httpCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedC;
        });
    }
}
