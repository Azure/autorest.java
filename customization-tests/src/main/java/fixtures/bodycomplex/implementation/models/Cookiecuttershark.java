// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.implementation.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Cookiecuttershark model.
 */
@Fluent
public final class Cookiecuttershark extends Shark {
    /*
     * The fishtype property.
     */
    private String fishtype = "cookiecuttershark";

    /**
     * Creates an instance of Cookiecuttershark class.
     */
    public Cookiecuttershark() {
    }

    /**
     * Get the fishtype property: The fishtype property.
     * 
     * @return the fishtype value.
     */
    public String getFishtype() {
        return this.fishtype;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cookiecuttershark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cookiecuttershark setBirthday(OffsetDateTime birthday) {
        super.setBirthday(birthday);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cookiecuttershark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cookiecuttershark setLength(float length) {
        super.setLength(length);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cookiecuttershark setSiblings(List<Fish> siblings) {
        super.setSiblings(siblings);
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("fishtype", this.fishtype);
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("birthday",
            getBirthday() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getBirthday()));
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeNumberField("age", getAge());
        jsonWriter.writeStringField("fishtype", this.fishtype);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Cookiecuttershark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Cookiecuttershark if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Cookiecuttershark.
     */
    public static Cookiecuttershark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Cookiecuttershark deserializedCookiecuttershark = new Cookiecuttershark();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    deserializedCookiecuttershark.setLength(reader.getFloat());
                } else if ("birthday".equals(fieldName)) {
                    deserializedCookiecuttershark.setBirthday(
                        reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString())));
                } else if ("species".equals(fieldName)) {
                    deserializedCookiecuttershark.setSpecies(reader.getString());
                } else if ("siblings".equals(fieldName)) {
                    List<Fish> siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                    deserializedCookiecuttershark.setSiblings(siblings);
                } else if ("age".equals(fieldName)) {
                    deserializedCookiecuttershark.setAge(reader.getNullable(JsonReader::getInt));
                } else if ("fishtype".equals(fieldName)) {
                    deserializedCookiecuttershark.fishtype = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedCookiecuttershark;
        });
    }
}
