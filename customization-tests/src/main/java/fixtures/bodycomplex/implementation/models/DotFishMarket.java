// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The DotFishMarket model.
 */
@Fluent
public final class DotFishMarket implements JsonSerializable<DotFishMarket> {
    /*
     * The sampleSalmon property.
     */
    @Generated
    private DotSalmon sampleSalmon;

    /*
     * The salmons property.
     */
    @Generated
    private List<DotSalmon> salmons;

    /*
     * The sampleFish property.
     */
    @Generated
    private DotFish sampleFish;

    /*
     * The fishes property.
     */
    @Generated
    private List<DotFish> fishes;

    /**
     * Creates an instance of DotFishMarket class.
     */
    @Generated
    public DotFishMarket() {
    }

    /**
     * Get the sampleSalmon property: The sampleSalmon property.
     * 
     * @return the sampleSalmon value.
     */
    @Generated
    public DotSalmon getSampleSalmon() {
        return this.sampleSalmon;
    }

    /**
     * Set the sampleSalmon property: The sampleSalmon property.
     * 
     * @param sampleSalmon the sampleSalmon value to set.
     * @return the DotFishMarket object itself.
     */
    @Generated
    public DotFishMarket setSampleSalmon(DotSalmon sampleSalmon) {
        this.sampleSalmon = sampleSalmon;
        return this;
    }

    /**
     * Get the salmons property: The salmons property.
     * 
     * @return the salmons value.
     */
    @Generated
    public List<DotSalmon> getSalmons() {
        return this.salmons;
    }

    /**
     * Set the salmons property: The salmons property.
     * 
     * @param salmons the salmons value to set.
     * @return the DotFishMarket object itself.
     */
    @Generated
    public DotFishMarket setSalmons(List<DotSalmon> salmons) {
        this.salmons = salmons;
        return this;
    }

    /**
     * Get the sampleFish property: The sampleFish property.
     * 
     * @return the sampleFish value.
     */
    @Generated
    public DotFish getSampleFish() {
        return this.sampleFish;
    }

    /**
     * Set the sampleFish property: The sampleFish property.
     * 
     * @param sampleFish the sampleFish value to set.
     * @return the DotFishMarket object itself.
     */
    @Generated
    public DotFishMarket setSampleFish(DotFish sampleFish) {
        this.sampleFish = sampleFish;
        return this;
    }

    /**
     * Get the fishes property: The fishes property.
     * 
     * @return the fishes value.
     */
    @Generated
    public List<DotFish> getFishes() {
        return this.fishes;
    }

    /**
     * Set the fishes property: The fishes property.
     * 
     * @param fishes the fishes value to set.
     * @return the DotFishMarket object itself.
     */
    @Generated
    public DotFishMarket setFishes(List<DotFish> fishes) {
        this.fishes = fishes;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("sampleSalmon", this.sampleSalmon);
        jsonWriter.writeArrayField("salmons", this.salmons, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeJsonField("sampleFish", this.sampleFish);
        jsonWriter.writeArrayField("fishes", this.fishes, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DotFishMarket from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DotFishMarket if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the DotFishMarket.
     */
    @Generated
    public static DotFishMarket fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DotFishMarket deserializedDotFishMarket = new DotFishMarket();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("sampleSalmon".equals(fieldName)) {
                    deserializedDotFishMarket.sampleSalmon = DotSalmon.fromJson(reader);
                } else if ("salmons".equals(fieldName)) {
                    List<DotSalmon> salmons = reader.readArray(reader1 -> DotSalmon.fromJson(reader1));
                    deserializedDotFishMarket.salmons = salmons;
                } else if ("sampleFish".equals(fieldName)) {
                    deserializedDotFishMarket.sampleFish = DotFish.fromJson(reader);
                } else if ("fishes".equals(fieldName)) {
                    List<DotFish> fishes = reader.readArray(reader1 -> DotFish.fromJson(reader1));
                    deserializedDotFishMarket.fishes = fishes;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDotFishMarket;
        });
    }
}
