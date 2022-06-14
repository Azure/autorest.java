// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.DefaultJsonReader;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The Fish model. */
@Fluent
public class Fish implements JsonSerializable<Fish> {
    private String species;

    private float length;

    private List<Fish> siblings;

    /**
     * Creates an instance of Fish class.
     *
     * @param length the length value to set.
     */
    public Fish(float length) {
        this.length = length;
    }

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
     * @return the Fish object itself.
     */
    public Fish setSpecies(String species) {
        this.species = species;
        return this;
    }

    /**
     * Get the length property: The length property.
     *
     * @return the length value.
     */
    public float getLength() {
        return this.length;
    }

    /**
     * Get the siblings property: The siblings property.
     *
     * @return the siblings value.
     */
    public List<Fish> getSiblings() {
        return this.siblings;
    }

    /**
     * Set the siblings property: The siblings property.
     *
     * @param siblings the siblings value to set.
     * @return the Fish object itself.
     */
    public Fish setSiblings(List<Fish> siblings) {
        this.siblings = siblings;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        return jsonWriter.flush();
    }

    public static Fish fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    String discriminatorValue = null;
                    JsonReader readerToUse = null;

                    // Read the first field name and determine if it's the discriminator field.
                    reader.nextToken();
                    if ("fishtype".equals(reader.getFieldName())) {
                        reader.nextToken();
                        discriminatorValue = reader.getStringValue();
                        readerToUse = reader;
                    } else {
                        // If it isn't the discriminator field buffer the JSON structure to make it
                        // replayable and find the discriminator field value.
                        String json = JsonUtils.bufferJsonObject(reader);
                        JsonReader replayReader = DefaultJsonReader.fromString(json);
                        while (replayReader.nextToken() != JsonToken.END_OBJECT) {
                            String fieldName = replayReader.getFieldName();
                            replayReader.nextToken();
                            if ("fishtype".equals(fieldName)) {
                                discriminatorValue = replayReader.getStringValue();
                                break;
                            } else {
                                replayReader.skipChildren();
                            }
                        }
                        if (discriminatorValue != null) {
                            readerToUse = DefaultJsonReader.fromString(json);
                        }
                    }
                    // Use the discriminator value to determine which subtype should be deserialized.
                    if (discriminatorValue == null || "Fish".equals(discriminatorValue)) {
                        return fromJsonKnownDiscriminator(readerToUse);
                    } else if ("salmon".equals(discriminatorValue)) {
                        return Salmon.fromJson(readerToUse);
                    } else if ("smart_salmon".equals(discriminatorValue)) {
                        return SmartSalmon.fromJson(readerToUse);
                    } else if ("shark".equals(discriminatorValue)) {
                        return Shark.fromJson(readerToUse);
                    } else if ("sawshark".equals(discriminatorValue)) {
                        return Sawshark.fromJson(readerToUse);
                    } else if ("goblin".equals(discriminatorValue)) {
                        return Goblinshark.fromJson(readerToUse);
                    } else {
                        throw new IllegalStateException(
                                "Discriminator field 'fishtype' was present and didn't match one of the expected values 'Fish', 'salmon', 'smart_salmon', 'shark', 'sawshark', or 'goblin'. It was: '"
                                        + discriminatorValue
                                        + "'.");
                    }
                });
    }

    static Fish fromJsonKnownDiscriminator(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    boolean discriminatorPropertyFound = false;
                    String discriminatorProperty = null;
                    boolean lengthFound = false;
                    float length = 0.0f;
                    String species = null;
                    List<Fish> siblings = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if (fieldName.equals("fishtype")) {
                            discriminatorPropertyFound = true;
                            discriminatorProperty = reader.getStringValue();
                        } else if ("length".equals(fieldName)) {
                            length = reader.getFloatValue();
                            lengthFound = true;
                        } else if ("species".equals(fieldName)) {
                            species = reader.getStringValue();
                        } else if ("siblings".equals(fieldName)) {
                            siblings = JsonUtils.readArray(reader, r -> Fish.fromJson(reader));
                        } else {
                            reader.skipChildren();
                        }
                    }

                    if (!discriminatorPropertyFound || !Objects.equals(discriminatorProperty, "Fish")) {
                        throw new IllegalStateException(
                                "'fishtype' was expected to be non-null and equal to 'Fish'. The found 'fishtype' was '"
                                        + discriminatorProperty
                                        + "'.");
                    }

                    List<String> missingProperties = new ArrayList<>();
                    if (!lengthFound) {
                        missingProperties.add("length");
                    }

                    if (!CoreUtils.isNullOrEmpty(missingProperties)) {
                        throw new IllegalStateException(
                                "Missing required property/properties: " + String.join(", ", missingProperties));
                    }
                    Fish deserializedValue = new Fish(length);
                    deserializedValue.setSpecies(species);
                    deserializedValue.setSiblings(siblings);

                    return deserializedValue;
                });
    }
}
