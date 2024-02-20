// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.dpgcustomization.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The Input model.
 */
@Immutable
public final class Input implements JsonSerializable<Input> {
    /*
     * The hello property.
     */
    @Generated
    private final String hello;

    /**
     * Creates an instance of Input class.
     * 
     * @param hello the hello value to set.
     */
    @Generated
    public Input(String hello) {
        this.hello = hello;
    }

    /**
     * Get the hello property: The hello property.
     * 
     * @return the hello value.
     */
    @Generated
    public String getHello() {
        return this.hello;
    }

    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("hello", this.hello);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Input from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Input if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Input.
     */
    @Generated
    public static Input fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean helloFound = false;
            String hello = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("hello".equals(fieldName)) {
                    hello = reader.getString();
                    helloFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (helloFound) {
                return new Input(hello);
            }
            throw new IllegalStateException("Missing required property: hello");
        });
    }
}
