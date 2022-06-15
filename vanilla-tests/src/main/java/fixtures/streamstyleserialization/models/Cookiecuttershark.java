// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** The Cookiecuttershark model. */
@Fluent
public final class Cookiecuttershark extends Shark {
    /**
     * Creates an instance of Cookiecuttershark class.
     *
     * @param length the length value to set.
     * @param birthday the birthday value to set.
     */
    public Cookiecuttershark(float length, OffsetDateTime birthday) {
        super(length, birthday);
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setAge(Integer age) {
        super.setAge(age);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setSiblings(List<Fish> siblings) {
        super.setSiblings(siblings);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public Cookiecuttershark setSpecies(String species) {
        super.setSpecies(species);
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
    public JsonWriter toJson(JsonWriter jsonWriter) {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("fishtype", "cookiecuttershark");
        jsonWriter.writeFloatField("length", getLength());
        jsonWriter.writeStringField("birthday", getBirthday() == null ? null : getBirthday().toString(), false);
        JsonUtils.writeArray(
                jsonWriter, "siblings", getSiblings(), (writer, element) -> writer.writeJson(element, false));
        jsonWriter.writeStringField("species", getSpecies(), false);
        jsonWriter.writeIntegerField("age", getAge(), false);
        return jsonWriter.writeEndObject().flush();
    }

    public static Cookiecuttershark fromJson(JsonReader jsonReader) {
        return JsonUtils.readObject(
                jsonReader,
                reader -> {
                    boolean discriminatorPropertyFound = false;
                    String discriminatorProperty = null;
                    boolean lengthFound = false;
                    float length = 0.0f;
                    boolean birthdayFound = false;
                    OffsetDateTime birthday = null;
                    List<Fish> siblings = null;
                    String species = null;
                    Integer age = null;
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("fishtype".equals(fieldName)) {
                            discriminatorPropertyFound = true;
                            discriminatorProperty = reader.getStringValue();
                        } else if ("length".equals(fieldName)) {
                            length = reader.getFloatValue();
                            lengthFound = true;
                        } else if ("birthday".equals(fieldName)) {
                            birthday =
                                    JsonUtils.getNullableProperty(
                                            reader, r -> OffsetDateTime.parse(reader.getStringValue()));
                            birthdayFound = true;
                        } else if ("siblings".equals(fieldName)) {
                            siblings =
                                    JsonUtils.readArray(
                                            reader, r -> JsonUtils.getNullableProperty(r, r1 -> Fish.fromJson(reader)));
                        } else if ("species".equals(fieldName)) {
                            species = JsonUtils.getNullableProperty(reader, r -> reader.getStringValue());
                        } else if ("age".equals(fieldName)) {
                            age = JsonUtils.getNullableProperty(reader, r -> reader.getIntValue());
                        } else {
                            reader.skipChildren();
                        }
                    }

                    if (!discriminatorPropertyFound || !Objects.equals(discriminatorProperty, "cookiecuttershark")) {
                        throw new IllegalStateException(
                                "'fishtype' was expected to be non-null and equal to 'cookiecuttershark'. The found 'fishtype' was '"
                                        + discriminatorProperty
                                        + "'.");
                    }

                    List<String> missingProperties = new ArrayList<>();
                    if (!lengthFound) {
                        missingProperties.add("length");
                    }
                    if (!birthdayFound) {
                        missingProperties.add("birthday");
                    }

                    if (!CoreUtils.isNullOrEmpty(missingProperties)) {
                        throw new IllegalStateException(
                                "Missing required property/properties: " + String.join(", ", missingProperties));
                    }
                    Cookiecuttershark deserializedValue = new Cookiecuttershark(length, birthday);
                    deserializedValue.setSiblings(siblings);
                    deserializedValue.setSpecies(species);
                    deserializedValue.setAge(age);

                    return deserializedValue;
                });
    }
}
