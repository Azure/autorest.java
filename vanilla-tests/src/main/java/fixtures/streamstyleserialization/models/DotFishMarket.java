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
import java.util.List;

/** The DotFishMarket model. */
@Fluent
public final class DotFishMarket implements JsonSerializable<DotFishMarket> {
    private DotSalmon sampleSalmon;

    private List<DotSalmon> salmons;

    private DotFish sampleFish;

    private List<DotFish> fishes;

    /**
     * Get the sampleSalmon property: The sampleSalmon property.
     *
     * @return the sampleSalmon value.
     */
    public DotSalmon getSampleSalmon() {
        return this.sampleSalmon;
    }

    /**
     * Set the sampleSalmon property: The sampleSalmon property.
     *
     * @param sampleSalmon the sampleSalmon value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleSalmon(DotSalmon sampleSalmon) {
        this.sampleSalmon = sampleSalmon;
        return this;
    }

    /**
     * Get the salmons property: The salmons property.
     *
     * @return the salmons value.
     */
    public List<DotSalmon> getSalmons() {
        return this.salmons;
    }

    /**
     * Set the salmons property: The salmons property.
     *
     * @param salmons the salmons value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSalmons(List<DotSalmon> salmons) {
        this.salmons = salmons;
        return this;
    }

    /**
     * Get the sampleFish property: The sampleFish property.
     *
     * @return the sampleFish value.
     */
    public DotFish getSampleFish() {
        return this.sampleFish;
    }

    /**
     * Set the sampleFish property: The sampleFish property.
     *
     * @param sampleFish the sampleFish value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setSampleFish(DotFish sampleFish) {
        this.sampleFish = sampleFish;
        return this;
    }

    /**
     * Get the fishes property: The fishes property.
     *
     * @return the fishes value.
     */
    public List<DotFish> getFishes() {
        return this.fishes;
    }

    /**
     * Set the fishes property: The fishes property.
     *
     * @param fishes the fishes value to set.
     * @return the DotFishMarket object itself.
     */
    public DotFishMarket setFishes(List<DotFish> fishes) {
        this.fishes = fishes;
        return this;
    }

    /**
     * Validates the instance.
     *
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
        if (getSampleSalmon() != null) {
            getSampleSalmon().validate();
        }
        if (getSalmons() != null) {
            getSalmons().forEach(e -> e.validate());
        }
        if (getSampleFish() != null) {
            getSampleFish().validate();
        }
        if (getFishes() != null) {
            getFishes().forEach(e -> e.validate());
        }
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) {
        return jsonWriter.flush();
    }

    public static DotFishMarket fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    DotSalmon sampleSalmon = null;
                    List<DotSalmon> salmons = null;
                    DotFish sampleFish = null;
                    List<DotFish> fishes = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("sampleSalmon".equals(fieldName)) {
                            sampleSalmon = DotSalmon.fromJson(reader);
                        } else if ("salmons".equals(fieldName)) {
                            salmons = JsonUtils.readArray(reader, r -> DotSalmon.fromJson(reader));
                        } else if ("sampleFish".equals(fieldName)) {
                            sampleFish = DotFish.fromJson(reader);
                        } else if ("fishes".equals(fieldName)) {
                            fishes = JsonUtils.readArray(reader, r -> DotFish.fromJson(reader));
                        } else {
                            reader.skipChildren();
                        }
                    }
                    DotFishMarket deserializedValue = new DotFishMarket();
                    deserializedValue.setSampleSalmon(sampleSalmon);
                    deserializedValue.setSalmons(salmons);
                    deserializedValue.setSampleFish(sampleFish);
                    deserializedValue.setFishes(fishes);

                    return deserializedValue;
                });
    }
}
