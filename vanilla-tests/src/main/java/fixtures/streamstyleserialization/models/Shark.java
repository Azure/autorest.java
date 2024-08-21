// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.logging.ClientLogger;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Shark model.
 */
@Fluent
public class Shark extends Fish {
    /*
     * The fishtype property.
     */
    private String fishtype = "shark";

    /*
     * The age property.
     */
    private Integer age;

    /*
     * The birthday property.
     */
    private OffsetDateTime birthday;

    /**
     * Creates an instance of Shark class.
     */
    public Shark() {
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
     * Get the age property: The age property.
     * 
     * @return the age value.
     */
    public Integer getAge() {
        return this.age;
    }

    /**
     * Set the age property: The age property.
     * 
     * @param age the age value to set.
     * @return the Shark object itself.
     */
    public Shark setAge(Integer age) {
        this.age = age;
        return this;
    }

    /**
     * Get the birthday property: The birthday property.
     * 
     * @return the birthday value.
     */
    public OffsetDateTime getBirthday() {
        return this.birthday;
    }

    /**
     * Set the birthday property: The birthday property.
     * 
     * @param birthday the birthday value to set.
     * @return the Shark object itself.
     */
    public Shark setBirthday(OffsetDateTime birthday) {
        this.birthday = birthday;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shark setLength(float length) {
        super.setLength(length);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shark setSiblings(List<Fish> siblings) {
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
        if (getBirthday() == null) {
            throw LOGGER.atError()
                .log(new IllegalArgumentException("Missing required property birthday in model Shark"));
        }
        if (getSiblings() != null) {
            getSiblings().forEach(e -> e.validate());
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Shark.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("birthday",
            this.birthday == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.birthday));
        jsonWriter.writeStringField("fishtype", this.fishtype);
        jsonWriter.writeNumberField("age", this.age);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Shark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Shark if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Shark.
     */
    public static Shark fromJson(JsonReader jsonReader) throws IOException {
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
                if ("sawshark".equals(discriminatorValue)) {
                    return Sawshark.fromJson(readerToUse.reset());
                } else if ("goblin".equals(discriminatorValue)) {
                    return Goblinshark.fromJson(readerToUse.reset());
                } else if ("cookiecuttershark".equals(discriminatorValue)) {
                    return Cookiecuttershark.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static Shark fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Shark deserializedShark = new Shark();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if (!Shark.fromJsonShared(reader, fieldName, deserializedShark)) {
                    reader.skipChildren();
                }
            }

            return deserializedShark;
        });
    }

    static boolean fromJsonShared(JsonReader reader, String fieldName, Shark deserializedShark) throws IOException {
        if (Fish.fromJsonShared(reader, fieldName, deserializedShark)) {
            return true;
        } else if ("birthday".equals(fieldName)) {
            deserializedShark.birthday
                = reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
            return true;
        } else if ("age".equals(fieldName)) {
            deserializedShark.age = reader.getNullable(JsonReader::getInt);
            return true;
        }
        return false;
    }
}
