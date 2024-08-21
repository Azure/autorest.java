// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.streamstyleserialization.models;

import com.azure.core.annotation.Fluent;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The MyBaseType model.
 */
@Fluent
public class MyBaseType implements JsonSerializable<MyBaseType> {
    /*
     * The kind property.
     */
    private MyKind kind = MyKind.fromString("MyBaseType");

    /*
     * The propB1 property.
     */
    private String propB1;

    /*
     * The propBH1 property.
     */
    private String propBH1;

    /**
     * Creates an instance of MyBaseType class.
     */
    public MyBaseType() {
    }

    /**
     * Get the kind property: The kind property.
     * 
     * @return the kind value.
     */
    public MyKind getKind() {
        return this.kind;
    }

    /**
     * Get the propB1 property: The propB1 property.
     * 
     * @return the propB1 value.
     */
    public String getPropB1() {
        return this.propB1;
    }

    /**
     * Set the propB1 property: The propB1 property.
     * 
     * @param propB1 the propB1 value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setPropB1(String propB1) {
        this.propB1 = propB1;
        return this;
    }

    /**
     * Get the propBH1 property: The propBH1 property.
     * 
     * @return the propBH1 value.
     */
    public String getPropBH1() {
        return this.propBH1;
    }

    /**
     * Set the propBH1 property: The propBH1 property.
     * 
     * @param propBH1 the propBH1 value to set.
     * @return the MyBaseType object itself.
     */
    public MyBaseType setPropBH1(String propBH1) {
        this.propBH1 = propBH1;
        return this;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("kind", this.kind == null ? null : this.kind.toString());
        jsonWriter.writeStringField("propB1", this.propB1);
        if (propBH1 != null) {
            jsonWriter.writeStartObject("helper");
            jsonWriter.writeStringField("propBH1", this.propBH1);
            jsonWriter.writeEndObject();
        }
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of MyBaseType from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of MyBaseType if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IOException If an error occurs while reading the MyBaseType.
     */
    public static MyBaseType fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("kind".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("Kind1".equals(discriminatorValue)) {
                    return MyDerivedType.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static MyBaseType fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            MyBaseType deserializedMyBaseType = new MyBaseType();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if (!MyBaseType.fromJsonShared(reader, fieldName, deserializedMyBaseType)) {
                    reader.skipChildren();
                }
            }

            return deserializedMyBaseType;
        });
    }

    static boolean fromJsonShared(JsonReader reader, String fieldName, MyBaseType deserializedMyBaseType)
        throws IOException {
        if ("kind".equals(fieldName)) {
            deserializedMyBaseType.kind = MyKind.fromString(reader.getString());
            return true;
        } else if ("propB1".equals(fieldName)) {
            deserializedMyBaseType.propB1 = reader.getString();
            return true;
        } else if ("helper".equals(fieldName) && reader.currentToken() == JsonToken.START_OBJECT) {
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                fieldName = reader.getFieldName();
                reader.nextToken();

                if ("propBH1".equals(fieldName)) {
                    deserializedMyBaseType.propBH1 = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return true;
        }
        return false;
    }
}
