// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.openai.implementation.models;

import com.generic.core.annotation.Generated;
import com.generic.core.annotation.Immutable;
import com.generic.json.JsonReader;
import com.generic.json.JsonSerializable;
import com.generic.json.JsonToken;
import com.generic.json.JsonWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** The CreateTranslationResponse model. */
@Immutable
public final class CreateTranslationResponse implements JsonSerializable<CreateTranslationResponse> {
    /*
     * The text property.
     */
    @Generated private final String text;

    /**
     * Creates an instance of CreateTranslationResponse class.
     *
     * @param text the text value to set.
     */
    @Generated
    private CreateTranslationResponse(String text) {
        this.text = text;
    }

    /**
     * Get the text property: The text property.
     *
     * @return the text value.
     */
    @Generated
    public String getText() {
        return this.text;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("text", this.text);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of CreateTranslationResponse from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of CreateTranslationResponse if the JsonReader was pointing to an instance of it, or null if
     *     it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the CreateTranslationResponse.
     */
    public static CreateTranslationResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean textFound = false;
                    String text = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("text".equals(fieldName)) {
                            text = reader.getString();
                            textFound = true;
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (textFound) {
                        CreateTranslationResponse deserializedCreateTranslationResponse =
                                new CreateTranslationResponse(text);

                        return deserializedCreateTranslationResponse;
                    }
                    List<String> missingProperties = new ArrayList<>();
                    if (!textFound) {
                        missingProperties.add("text");
                    }

                    throw new IllegalStateException(
                            "Missing required property/properties: " + String.join(", ", missingProperties));
                });
    }
}
