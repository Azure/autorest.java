// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
     * 
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    public Cookiecuttershark(float length, OffsetDateTime birthday) {
        super(length, birthday);
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
    public Cookiecuttershark setSpecies(String species) {
        super.setSpecies(species);
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
                .log(new IllegalArgumentException("Missing required property birthday in model Cookiecuttershark"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Cookiecuttershark.class);

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
            long foundTracker = 0;
            float length = 0.0f;
            OffsetDateTime birthday = null;
            String species = null;
            List<Fish> siblings = null;
            Integer age = null;
            String fishtype = "cookiecuttershark";
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getFloat();
                    foundTracker |= 1;
                } else if ("birthday".equals(fieldName)) {
                    birthday = reader
                        .getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                    foundTracker |= 2;
                } else if ("species".equals(fieldName)) {
                    species = reader.getString();
                } else if ("siblings".equals(fieldName)) {
                    siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                } else if ("age".equals(fieldName)) {
                    age = reader.getNullable(JsonReader::getInt);
                } else if ("fishtype".equals(fieldName)) {
                    fishtype = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            if (foundTracker == 3) {
                Cookiecuttershark deserializedCookiecuttershark = new Cookiecuttershark(length, birthday);
                deserializedCookiecuttershark.setSpecies(species);
                deserializedCookiecuttershark.setSiblings(siblings);
                deserializedCookiecuttershark.setAge(age);
                deserializedCookiecuttershark.fishtype = fishtype;

                return deserializedCookiecuttershark;
            }
            List<String> missingProperties = new ArrayList<>();
            if ((foundTracker & 1) != 1) {
                missingProperties.add("length");
            }
            if ((foundTracker & 2) != 2) {
                missingProperties.add("birthday");
            }

            throw new IllegalStateException(
                "Missing required property/properties: " + String.join(", ", missingProperties));
        });
    }
}
