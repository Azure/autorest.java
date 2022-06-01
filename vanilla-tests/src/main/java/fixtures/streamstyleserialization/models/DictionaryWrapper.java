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
import java.util.LinkedHashMap;
import java.util.Map;

/** The DictionaryWrapper model. */
@Fluent
public final class DictionaryWrapper implements JsonSerializable<DictionaryWrapper> {
    private Map<String, String> defaultProgram;

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
        return jsonWriter.flush();
    }

    public static DictionaryWrapper fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    Map<String, String> defaultProgram = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("defaultProgram".equals(fieldName)) {
                            if (reader.currentToken() != JsonToken.NULL) {
                                if (defaultProgram == null) {
                                    defaultProgram = new LinkedHashMap<>();
                                }

                                while (reader.nextToken() != JsonToken.END_OBJECT) {
                                    fieldName = reader.getFieldName();
                                    reader.nextToken();

                                    String value = reader.getStringValue();
                                    defaultProgram.put(fieldName, value);
                                }
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DictionaryWrapper deserializedValue = new DictionaryWrapper();
                    deserializedValue.setDefaultProgram(defaultProgram);

                    return deserializedValue;
                });
    }
}
