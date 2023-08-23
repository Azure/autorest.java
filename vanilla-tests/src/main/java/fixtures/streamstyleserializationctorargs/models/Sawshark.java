// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserializationctorargs.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The Sawshark model. */
@Fluent
public final class Sawshark extends Shark {
    /*
     * The fishtype property.
     */
    private static final String FISHTYPE = "sawshark";

    /*
     * The picture property.
     */
    private byte[] picture;

    /**
     * Creates an instance of Sawshark class.
     *
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    public Sawshark(float length, OffsetDateTime birthday) {
        super(length, birthday);
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

    /** {@inheritDoc} */
    @Override
    public Sawshark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Sawshark setSpecies(String species) {
        super.setSpecies(species);
        return this;
    }

    /** {@inheritDoc} */
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("birthday", Objects.toString(getBirthday(), null));
        jsonWriter.writeStringField("species", getSpecies());
        jsonWriter.writeArrayField("siblings", getSiblings(), (writer, element) -> writer.writeJson(element));
        jsonWriter.writeNumberField("age", getAge());
        jsonWriter.writeBinaryField("picture", this.picture);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Sawshark from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Sawshark if the JsonReader was pointing to an instance of it, or null if it was pointing
     *     to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties or the
     *     polymorphic discriminator.
     * @throws IOException If an error occurs while reading the Sawshark.
     */
    public static Sawshark fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    boolean lengthFound = false;
                    float length = 0.0f;
                    boolean birthdayFound = false;
                    OffsetDateTime birthday = null;
                    String species = null;
                    List<Fish> siblings = null;
                    Integer age = null;
                    byte[] picture = new byte[0];
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("fishtype".equals(fieldName)) {
                            String fishtype = reader.getString();
                            if (!FISHTYPE.equals(fishtype)) {
                                throw new IllegalStateException(
                                        "'fishtype' was expected to be non-null and equal to '"
                                                + FISHTYPE
                                                + "'. The found 'fishtype' was '"
                                                + fishtype
                                                + "'.");
                            }
                        } else if ("length".equals(fieldName)) {
                            length = reader.getFloat();
                            lengthFound = true;
                        } else if ("birthday".equals(fieldName)) {
                            birthday =
                                    reader.getNullable(
                                            nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                            birthdayFound = true;
                        } else if ("species".equals(fieldName)) {
                            species = reader.getString();
                        } else if ("siblings".equals(fieldName)) {
                            siblings = reader.readArray(reader1 -> Fish.fromJson(reader1));
                        } else if ("age".equals(fieldName)) {
                            age = reader.getNullable(JsonReader::getInt);
                        } else if ("picture".equals(fieldName)) {
                            picture = reader.getBinary();
                        } else {
                            reader.skipChildren();
                        }
                    }
                    if (lengthFound && birthdayFound) {
                        Sawshark deserializedSawshark = new Sawshark(length, birthday);
                        deserializedSawshark.setSpecies(species);
                        deserializedSawshark.setSiblings(siblings);
                        deserializedSawshark.setAge(age);
                        deserializedSawshark.picture = picture;

                        return deserializedSawshark;
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
