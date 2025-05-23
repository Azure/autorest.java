// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The Salmon model.
 */
@Fluent
public class Salmon extends Fish {
    /*
     * The location property.
     */
    @Generated
    private String location;

    /*
     * The iswild property.
     */
    @Generated
    private Boolean iswild;

    /**
     * Creates an instance of Salmon class.
     */
    @Generated
    public Salmon() {
        this.fishtype = "salmon";
    }

    /**
     * Get the location property: The location property.
     * 
     * @return the location value.
     */
    @Generated
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     * 
     * @param location the location value to set.
     * @return the Salmon object itself.
     */
    @Generated
    public Salmon setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the iswild property: The iswild property.
     * 
     * @return the iswild value.
     */
    @Generated
    public Boolean iswild() {
        return this.iswild;
    }

    /**
     * Set the iswild property: The iswild property.
     * 
     * @param iswild the iswild value to set.
     * @return the Salmon object itself.
     */
    @Generated
    public Salmon setIswild(Boolean iswild) {
        this.iswild = iswild;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Salmon setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Salmon setLength(float length) {
        super.setLength(length);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Salmon setSiblings(List<Fish> siblings) {
        super.setSiblings(siblings);
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        toJsonShared(jsonWriter);
        return jsonWriter.writeEndObject();
    }

    void toJsonShared(JsonWriter jsonWriter) throws IOException {
        super.toJsonShared(jsonWriter);
        jsonWriter.writeStringField("location", this.location);
        jsonWriter.writeBooleanField("iswild", this.iswild);
    }

    /**
     * Reads an instance of Salmon from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Salmon if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Salmon.
     */
    @Generated
    public static Salmon fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("fishtype".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("smart_salmon".equals(discriminatorValue)) {
                    return SmartSalmon.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    @Generated
    static Salmon fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Salmon deserializedSalmon = new Salmon();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if (!Salmon.fromJsonShared(reader, fieldName, deserializedSalmon)) {
                    reader.skipChildren();
                }
            }

            return deserializedSalmon;
        });
    }

    @Generated
    static boolean fromJsonShared(JsonReader reader, String fieldName, Salmon deserializedSalmon) throws IOException {
        if (Fish.fromJsonShared(reader, fieldName, deserializedSalmon)) {
            return true;
        } else if ("location".equals(fieldName)) {
            deserializedSalmon.location = reader.getString();
            return true;
        } else if ("iswild".equals(fieldName)) {
            deserializedSalmon.iswild = reader.getNullable(JsonReader::getBoolean);
            return true;
        }
        return false;
    }
}
