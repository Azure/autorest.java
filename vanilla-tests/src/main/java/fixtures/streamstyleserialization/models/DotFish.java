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

/** The DotFish model. */
@Fluent
public class DotFish implements JsonSerializable<DotFish> {
    private String species;

    /**
     * Get the species property: The species property.
     *
     * @return the species value.
     */
    public String getSpecies() {
        return this.species;
    }

    /**
     * Set the species property: The species property.
     *
     * @param species the species value to set.
     * @return the DotFish object itself.
     */
    public DotFish setSpecies(String species) {
        this.species = species;
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

    public static DotFish fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    String species = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("species".equals(fieldName)) {
                            species = reader.getStringValue();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DotFish deserializedValue = new DotFish();
                    deserializedValue.setSpecies(species);

                    return deserializedValue;
                });
    }
}
