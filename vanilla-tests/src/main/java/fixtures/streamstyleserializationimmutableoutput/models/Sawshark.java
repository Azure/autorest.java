// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationimmutableoutput.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Sawshark model.
 */
@Fluent
public final class Sawshark extends Shark {
    /*
     * The fishtype property.
     */
    private String fishtype = "sawshark";

    /*
     * The picture property.
     */
    private byte[] picture;

    /**
     * Creates an instance of Sawshark class.
     */
    public Sawshark() {
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
     * Get the picture property: The picture property.
     * 
     * @return the picture value.
     */
    public byte[] getPicture() {
        return CoreUtils.clone(this.picture);
    }

    /**
     * Set the picture property: The picture property.
     * 
     * @param picture the picture value to set.
     * @return the Sawshark object itself.
     */
    public Sawshark setPicture(byte[] picture) {
        this.picture = CoreUtils.clone(picture);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sawshark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sawshark setBirthday(OffsetDateTime birthday) {
        super.setBirthday(birthday);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sawshark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sawshark setLength(float length) {
        super.setLength(length);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sawshark setSiblings(List<Fish> siblings) {
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

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("birthday",
            getBirthday() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getBirthday()));
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeNumberField("age", getAge());
        jsonWriter.writeStringField("fishtype", this.fishtype);
        jsonWriter.writeBinaryField("picture", this.picture);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Sawshark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Sawshark if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Sawshark.
     */
    public static Sawshark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Sawshark deserializedSawshark = new Sawshark();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    deserializedSawshark.setLength(reader.getFloat());
                } else if ("birthday".equals(fieldName)) {
                    deserializedSawshark.setBirthday(reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())));
                } else if ("species".equals(fieldName)) {
                    deserializedSawshark.setSpecies(reader.getString());
                } else if ("siblings".equals(fieldName)) {
                    List<Fish> siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                    deserializedSawshark.setSiblings(siblings);
                } else if ("age".equals(fieldName)) {
                    deserializedSawshark.setAge(reader.getNullable(JsonReader::getInt));
                } else if ("fishtype".equals(fieldName)) {
                    deserializedSawshark.fishtype = reader.getString();
                } else if ("picture".equals(fieldName)) {
                    deserializedSawshark.picture = reader.getBinary();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSawshark;
        });
    }
}
