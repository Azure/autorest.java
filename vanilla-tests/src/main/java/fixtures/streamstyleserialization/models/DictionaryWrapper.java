// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.util.Map;

/** The DictionaryWrapper model. */
@Fluent
public final class DictionaryWrapper implements JsonSerializable<DictionaryWrapper> {
    /*
     * Dictionary of <string>
     */
    private Map<String, String> defaultProgram;

    /** Creates an instance of DictionaryWrapper class. */
    public DictionaryWrapper() {}

    /**
     * Get the defaultProgram property: Dictionary of &lt;string&gt;.
     *
     * @return the defaultProgram value.
     */
    public Map<String, String> getDefaultProgram() {
        return this.defaultProgram;
    }

    /**
     * Set the defaultProgram property: Dictionary of &lt;string&gt;.
     *
     * @param defaultProgram the defaultProgram value to set.
     * @return the DictionaryWrapper object itself.
     */
    public DictionaryWrapper setDefaultProgram(Map<String, String> defaultProgram) {
        this.defaultProgram = defaultProgram;
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
        jsonWriter.writeMapField(
                "defaultProgram", this.defaultProgram, false, (writer, element) -> writer.writeString(element));
        return jsonWriter.writeEndObject().flush();
    }

    /**
     * Reads an instance of DictionaryWrapper from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DictionaryWrapper if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     */
    public static DictionaryWrapper fromJson(JsonReader jsonReader) {
        return jsonReader.readObject(
                reader -> {
                    Map<String, String> defaultProgram = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("defaultProgram".equals(fieldName)) {
                            defaultProgram = reader.readMap(reader1 -> reader1.getStringValue());
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DictionaryWrapper deserializedValue = new DictionaryWrapper();
                    deserializedValue.defaultProgram = defaultProgram;

                    return deserializedValue;
                });
    }
}
