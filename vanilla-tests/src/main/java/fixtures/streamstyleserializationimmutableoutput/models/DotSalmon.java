// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The DotSalmon model.
 */
@Immutable
public final class DotSalmon extends DotFish {
    /*
     * The fish.type property.
     */
    private String fishType = "DotSalmon";

    /*
     * The location property.
     */
    private String location;

    /*
     * The iswild property.
     */
    private Boolean iswild;

    /**
     * Creates an instance of DotSalmon class.
     */
    private DotSalmon() {
    }

    /**
     * Get the fishType property: The fish.type property.
     * 
     * @return the fishType value.
     */
    @Override
    public String getFishType() {
        return this.fishType;
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
     * Get the iswild property: The iswild property.
     * 
     * @return the iswild value.
     */
    public Boolean iswild() {
        return this.iswild;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeStringField("fish.type", this.fishType);
        jsonWriter.writeStringField("location", this.location);
        jsonWriter.writeBooleanField("iswild", this.iswild);
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

                if (DotFish.fromJsonShared(reader, fieldName, deserializedDotSalmon)) {
                    continue;
                } else if ("location".equals(fieldName)) {
                    deserializedDotSalmon.location = reader.getString();
                } else if ("iswild".equals(fieldName)) {
                    deserializedDotSalmon.iswild = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDotSalmon;
        });
    }
}
