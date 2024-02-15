// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import java.io.IOException;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The DotSalmon model.
 */
@Fluent
public class DotSalmon extends DotFish {
    /*
     * The location property.
     */
    private String location;

    /*
     * The iswild property.
     */
    @JsonProperty(value = "isWild")
    private Boolean isWild;

    /**
     * Creates an instance of DotSalmon class.
     */
    public DotSalmon() {
    }

    /**
     * Get the location property: The location property.
     * 
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     * 
     * @param location the location value to set.
     * @return the DotSalmon object itself.
     */
    public DotSalmon setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the iswild property: The iswild property.
     * 
     * @return the iswild value.
     */
    public boolean isWild() {
        Boolean returnValue = this.isWild;
        return returnValue;
    }

    /**
     * Set the iswild property: The iswild property.
     * 
     * @param iswild the iswild value to set.
     */
    public void setWild(Boolean iswild) {
        this.isWild = iswild;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DotSalmon setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("fish\\.type", getFishType());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeStringField("location", this.location);
        jsonWriter.writeBooleanField("iswild", this.isWild);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DotSalmon from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of DotSalmon if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the DotSalmon.
     */
    public static DotSalmon fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DotSalmon deserializedDotSalmon = new DotSalmon();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("fish\\.type".equals(fieldName)) {
                    deserializedDotSalmon.setFishType(reader.getString());
                } else if ("species".equals(fieldName)) {
                    deserializedDotSalmon.setSpecies(reader.getString());
                } else if ("location".equals(fieldName)) {
                    deserializedDotSalmon.location = reader.getString();
                } else if ("iswild".equals(fieldName)) {
                    deserializedDotSalmon.isWild = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDotSalmon;
        });
    }

    /**
     * Return if the salmon is a domestic species.
     * 
     * @return true if the salmon is domestic
     * @deprecated Removing in the next version
     */
    @Deprecated
    public boolean isDomestic() {
        return "US".equalsIgnoreCase(getLocation());
    }
}
