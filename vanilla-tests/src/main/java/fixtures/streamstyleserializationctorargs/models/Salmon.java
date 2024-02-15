// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
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
    private String location;

    /*
     * The iswild property.
     */
    private Boolean iswild;

    /**
     * Creates an instance of Salmon class.
     * 
     * @param length the length value to set.
     */
    public Salmon(float length) {
        super(length);
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
     * @return the Salmon object itself.
     */
    public Salmon setLocation(String location) {
        this.location = location;
        return this;
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
     * Set the iswild property: The iswild property.
     * 
     * @param iswild the iswild value to set.
     * @return the Salmon object itself.
     */
    public Salmon setIswild(Boolean iswild) {
        this.iswild = iswild;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Salmon setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
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
        super.validate();
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("fishtype", getFishtype());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("location", this.location);
        jsonWriter.writeBooleanField("iswild", this.iswild);
        return jsonWriter.writeEndObject();
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

    static Salmon fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean lengthFound = false;
            float length = 0.0f;
            String fishtype = "salmon";
            String species = null;
            List<Fish> siblings = null;
            String location = null;
            Boolean iswild = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getFloat();
                    lengthFound = true;
                } else if ("fishtype".equals(fieldName)) {
                    fishtype = reader.getString();
                } else if ("species".equals(fieldName)) {
                    species = reader.getString();
                } else if ("siblings".equals(fieldName)) {
                    siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                } else if ("location".equals(fieldName)) {
                    location = reader.getString();
                } else if ("iswild".equals(fieldName)) {
                    iswild = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }
            if (lengthFound) {
                Salmon deserializedSalmon = new Salmon(length);
                deserializedSalmon.setFishtype(fishtype);
                deserializedSalmon.setSpecies(species);
                deserializedSalmon.setSiblings(siblings);
                deserializedSalmon.location = location;
                deserializedSalmon.iswild = iswild;

                return deserializedSalmon;
            }
            throw new IllegalStateException("Missing required property: length");
        });
    }
}
