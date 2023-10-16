// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Objects;

/** The MyDerivedType model. */
@Fluent
public final class MyDerivedType extends MyBaseType {
    /*
     * The kind property.
     */
    /*
     * The propD1 property.
     */
    private String propD1;

    /** Creates an instance of MyDerivedType class. */
    public MyDerivedType() {}

    /**
     * Get the propD1 property: The propD1 property.
     *
     * @return the propD1 value.
     */
    public String getPropD1() {
        return this.propD1;
    }

    /**
     * Set the propD1 property: The propD1 property.
     *
     * @param propD1 the propD1 value to set.
     * @return the MyDerivedType object itself.
     */
    public MyDerivedType setPropD1(String propD1) {
        this.propD1 = propD1;
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public MyDerivedType setPropB1(String propB1) {
        super.setPropB1(propB1);
        return this;
    }

    /** {@inheritDoc} */
    @Override
    public MyDerivedType setPropBH1(String propBH1) {
        super.setPropBH1(propBH1);
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
        jsonWriter.writeStringField("kind", Objects.toString(MyKind.KIND1, null));
        jsonWriter.writeStringField("propB1", getPropB1());
        jsonWriter.writeStringField("propD1", this.propD1);
        if (getPropBH1() != null) {
            jsonWriter.writeStartObject("helper");
            jsonWriter.writeStringField("propBH1", getPropBH1());
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MyDerivedType from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of MyDerivedType if the JsonReader was pointing to an instance of it, or null if it was
     *     pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing the polymorphic discriminator.
     * @throws IOException If an error occurs while reading the MyDerivedType.
     */
    public static MyDerivedType fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(
                reader -> {
                    MyDerivedType deserializedMyDerivedType = new MyDerivedType();
                    while (reader.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = reader.getFieldName();
                        reader.nextToken();

                        if ("kind".equals(fieldName)) {
                            String kind = reader.getString();
                            if (!"Kind1".equals(kind)) {
                                throw new IllegalStateException(
                                        "'kind' was expected to be non-null and equal to 'Kind1'. The found 'kind' was '"
                                                + kind
                                                + "'.");
                            }
                        } else if ("propB1".equals(fieldName)) {
                            deserializedMyDerivedType.setPropB1(reader.getString());
                        } else if ("propD1".equals(fieldName)) {
                            deserializedMyDerivedType.propD1 = reader.getString();
                        } else if ("helper".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
                            while (reader.nextToken() != JsonToken.END_OBJECT) {
                                fieldName = reader.getFieldName();
                                reader.nextToken();

                                if ("propBH1".equals(fieldName)) {
                                    deserializedMyDerivedType.setPropBH1(reader.getString());
                                } else {
                                    reader.skipChildren();
                                }
                            }
                        } else {
                            reader.skipChildren();
                        }
                    }

                    return deserializedMyDerivedType;
                });
    }
}
