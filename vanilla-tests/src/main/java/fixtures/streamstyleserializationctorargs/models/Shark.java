// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
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
 * The Shark model.
 */
@Fluent
public class Shark extends Fish {
    /*
     * The age property.
     */
    private Integer age;

    /*
     * The birthday property.
     */
    private final OffsetDateTime birthday;

    /**
     * Creates an instance of Shark class.
     * 
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    public Shark(float length, OffsetDateTime birthday) {
        super(length);
        this.birthday = birthday;
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
        super.validate();
        if (getBirthday() == null) {
            throw LOGGER
                .logExceptionAsError(new IllegalArgumentException("Missing required property birthday in model Shark"));
        }
    }

    private static final ClientLogger LOGGER = new ClientLogger(Shark.class);

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("fishtype", "shark");
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("birthday",
            this.birthday == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.birthday));
        jsonWriter.writeNumberField("age", this.age);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Shark from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Shark if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     * polymorphic discriminator.
     * @throws IOException If an error occurs while reading the Shark.
     */
    public static Shark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            JsonReader readerToUse = reader.bufferObject();

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
            if (discriminatorValue == null || "shark".equals(discriminatorValue)) {
                return fromJsonKnownDiscriminator(readerToUse);
            } else if ("sawshark".equals(discriminatorValue)) {
                return Sawshark.fromJson(readerToUse.reset());
            } else if ("goblin".equals(discriminatorValue)) {
                return Goblinshark.fromJson(readerToUse.reset());
            } else if ("cookiecuttershark".equals(discriminatorValue)) {
                return Cookiecuttershark.fromJson(readerToUse.reset());
            } else {
                return fromJsonKnownDiscriminator(readerToUse.reset());
            }
        });
    }

    static Shark fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean lengthFound = false;
            float length = 0.0f;
            String species = null;
            List<Fish> siblings = null;
            boolean birthdayFound = false;
            OffsetDateTime birthday = null;
            Integer age = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("length".equals(fieldName)) {
                    length = reader.getFloat();
                    lengthFound = true;
                } else if ("species".equals(fieldName)) {
                    species = reader.getString();
                } else if ("siblings".equals(fieldName)) {
                    siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                } else if ("birthday".equals(fieldName)) {
                    birthday = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(),
                        DateTimeFormatter.ISO_OFFSET_DATE_TIME));
                    birthdayFound = true;
                } else if ("age".equals(fieldName)) {
                    age = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }
            if (lengthFound && birthdayFound) {
                Shark deserializedShark = new Shark(length, birthday);
                deserializedShark.setSpecies(species);
                deserializedShark.setSiblings(siblings);
                deserializedShark.age = age;

                return deserializedShark;
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
