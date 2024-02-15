// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The Goblinshark model.
 */
@Fluent
public final class Goblinshark extends Shark {
    /*
     * The jawsize property.
     */
    private Integer jawsize;

    /*
     * Colors possible
     */
    private GoblinSharkColor color;

    /**
     * Creates an instance of Goblinshark class.
     * 
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    public Goblinshark(float length, OffsetDateTime birthday) {
        super(length, birthday);
    }

    /**
     * Get the jawsize property: The jawsize property.
     * 
     * @return the jawsize value.
     */
    public Integer getJawsize() {
        return this.jawsize;
    }

    /**
     * Set the jawsize property: The jawsize property.
     * 
     * @param jawsize the jawsize value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setJawsize(Integer jawsize) {
        this.jawsize = jawsize;
        return this;
    }

    /**
     * Get the color property: Colors possible.
     * 
     * @return the color value.
     */
    public GoblinSharkColor getColor() {
        return this.color;
    }

    /**
     * Set the color property: Colors possible.
     * 
     * @param color the color value to set.
     * @return the Goblinshark object itself.
     */
    public Goblinshark setColor(GoblinSharkColor color) {
        this.color = color;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Goblinshark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Goblinshark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Goblinshark setSiblings(List<Fish> siblings) {
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
        jsonWriter.writeStringField("birthday",
            getBirthday() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getBirthday()));
        jsonWriter.writeStringField("fishtype", getFishtype());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeNumberField("age", getAge());
        jsonWriter.writeNumberField("jawsize", this.jawsize);
        jsonWriter.writeStringField("color", this.color == null ? null : this.color.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Goblinshark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Goblinshark if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Goblinshark.
     */
    public static Goblinshark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean lengthFound = false;
            float length = 0.0f;
            boolean birthdayFound = false;
            OffsetDateTime birthday = null;
            String fishtype = "goblin";
            String species = null;
            List<Fish> siblings = null;
            Integer age = null;
            Integer jawsize = null;
            GoblinSharkColor color = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getFloat();
                    lengthFound = true;
                } else if ("birthday".equals(fieldName)) {
                    birthday = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                    birthdayFound = true;
                } else if ("fishtype".equals(fieldName)) {
                    fishtype = reader.getString();
                } else if ("species".equals(fieldName)) {
                    species = reader.getString();
                } else if ("siblings".equals(fieldName)) {
                    siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                } else if ("age".equals(fieldName)) {
                    age = reader.getNullable(JsonReader::getInt);
                } else if ("jawsize".equals(fieldName)) {
                    jawsize = reader.getNullable(JsonReader::getInt);
                } else if ("color".equals(fieldName)) {
                    color = GoblinSharkColor.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }
            if (lengthFound && birthdayFound) {
                Goblinshark deserializedGoblinshark = new Goblinshark(length, birthday);
                deserializedGoblinshark.setFishtype(fishtype);
                deserializedGoblinshark.setSpecies(species);
                deserializedGoblinshark.setSiblings(siblings);
                deserializedGoblinshark.setAge(age);
                deserializedGoblinshark.jawsize = jawsize;
                deserializedGoblinshark.color = color;

                return deserializedGoblinshark;
            }
            List<String> missingProperties = new ArrayList<>();
            if (!lengthFound) {
                missingProperties.add("length");
            }
            if (!birthdayFound) {
                missingProperties.add("birthday");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
