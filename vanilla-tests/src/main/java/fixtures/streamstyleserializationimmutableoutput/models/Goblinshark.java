// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * The Goblinshark model.
 */
@Fluent
public final class Goblinshark extends Shark {
    /*
     * The fishtype property.
     */
    private String fishtype = "goblin";

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
     */
    public Goblinshark() {
    }

    /**
     * Get the fishtype property: The fishtype property.
     * 
     * @return the fishtype value.
     */
    @Override
    public String getFishtype() {
        return this.fishtype;
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
    public Goblinshark setBirthday(OffsetDateTime birthday) {
        super.setBirthday(birthday);
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
    public Goblinshark setLength(float length) {
        super.setLength(length);
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
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
        if (getBirthday() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property birthday in model Goblinshark"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Goblinshark.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        toJsonShared(jsonWriter);
        jsonWriter.writeStringField("fishtype", this.fishtype);
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
            Goblinshark deserializedGoblinshark = new Goblinshark();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    deserializedGoblinshark.setLength(reader.getFloat());
                } else if ("birthday".equals(fieldName)) {
                    deserializedGoblinshark.setBirthday(reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())));
                } else if ("species".equals(fieldName)) {
                    deserializedGoblinshark.setSpecies(reader.getString());
                } else if ("siblings".equals(fieldName)) {
                    List<Fish> siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                    deserializedGoblinshark.setSiblings(siblings);
                } else if ("age".equals(fieldName)) {
                    deserializedGoblinshark.setAge(reader.getNullable(JsonReader::getInt));
                } else if ("fishtype".equals(fieldName)) {
                    deserializedGoblinshark.fishtype = reader.getString();
                } else if ("jawsize".equals(fieldName)) {
                    deserializedGoblinshark.jawsize = reader.getNullable(JsonReader::getInt);
                } else if ("color".equals(fieldName)) {
                    deserializedGoblinshark.color = GoblinSharkColor.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedGoblinshark;
        });
    }
}
