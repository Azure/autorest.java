// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.models;

import com.generic.core.annotation.Generated;
import com.generic.core.annotation.Immutable;
import com.generic.json.JsonReader;
import com.generic.json.JsonSerializable;
import com.generic.json.JsonToken;
import com.generic.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The ListModelsResponse model.
 */
@Immutable
public final class ListModelsResponse implements JsonSerializable<ListModelsResponse> {
    /*
     * The object property.
     */
    @Generated
    private final String object;

    /*
     * The data property.
     */
    @Generated
    private final List<Model> data;

    /**
     * Creates an instance of ListModelsResponse class.
     * 
     * @param object the object value to set.
     * @param data the data value to set.
     */
    @Generated
    private ListModelsResponse(String object, List<Model> data) {
        this.object = object;
        this.data = data;
    }

    /**
     * Get the object property: The object property.
     * 
     * @return the object value.
     */
    @Generated
    public String getObject() {
        return this.object;
    }

    /**
     * Get the data property: The data property.
     * 
     * @return the data value.
     */
    @Generated
    public List<Model> getData() {
        return this.data;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("object", this.object);
        jsonWriter.writeArrayField("data", this.data, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ListModelsResponse from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ListModelsResponse if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ListModelsResponse.
     */
    public static ListModelsResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean objectFound = false;
            String object = null;
            boolean dataFound = false;
            List<Model> data = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("object".equals(fieldName)) {
                    object = reader.getString();
                    objectFound = true;
                } else if ("data".equals(fieldName)) {
                    data = reader.readArray(reader1 -> Model.fromJson(reader1));
                    dataFound = true;
                } else {
                    reader.skipChildren();
                }
            }
            if (objectFound && dataFound) {
                ListModelsResponse deserializedListModelsResponse = new ListModelsResponse(object, data);

                return deserializedListModelsResponse;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!objectFound) {
                missingProperties.add("object");
            }
            if (!dataFound) {
                missingProperties.add("data");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
