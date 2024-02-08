// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.
package com.cadl.optional.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.optional.implementation.CoreToCodegenBridgeUtils;
import java.io.IOException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * The AllPropertiesOptional model.
 */
@Immutable
public final class AllPropertiesOptional implements JsonSerializable<AllPropertiesOptional> {

    /*
     * The boolean property.
     */
    @Generated
    private Boolean booleanProperty;

    /*
     * The booleanNullable property.
     */
    @Generated
    private Boolean booleanNullable;

    /*
     * The booleanRequired property.
     */
    @Generated
    private Boolean booleanRequired;

    /*
     * The booleanRequiredNullable property.
     */
    @Generated
    private Boolean booleanRequiredNullable;

    /*
     * The string property.
     */
    @Generated
    private String string;

    /*
     * The stringNullable property.
     */
    @Generated
    private String stringNullable;

    /*
     * The stringRequired property.
     */
    @Generated
    private String stringRequired;

    /*
     * The stringRequiredNullable property.
     */
    @Generated
    private String stringRequiredNullable;

    /*
     * The bytes property.
     */
    @Generated
    private byte[] bytes;

    /*
     * The int property.
     */
    @Generated
    private Integer intProperty;

    /*
     * The long property.
     */
    @Generated
    private Long longProperty;

    /*
     * The float property.
     */
    @Generated
    private Double floatProperty;

    /*
     * The double property.
     */
    @Generated
    private Double doubleProperty;

    /*
     * The duration property.
     */
    @Generated
    private Duration duration;

    /*
     * The dateTime property.
     */
    @Generated
    private OffsetDateTime dateTime;

    /*
     * The stringList property.
     */
    @Generated
    private List<String> stringList;

    /*
     * The bytesDict property.
     */
    @Generated
    private Map<String, byte[]> bytesDict;

    /*
     * The immutable property.
     */
    @Generated
    private ImmutableModel immutable;

    /**
     * Creates an instance of AllPropertiesOptional class.
     */
    @Generated
    private AllPropertiesOptional() {
    }

    /**
     * Get the booleanProperty property: The boolean property.
     *
     * @return the booleanProperty value.
     */
    @Generated
    public Boolean isBooleanProperty() {
        return this.booleanProperty;
    }

    /**
     * Get the booleanNullable property: The booleanNullable property.
     *
     * @return the booleanNullable value.
     */
    @Generated
    public Boolean isBooleanNullable() {
        return this.booleanNullable;
    }

    /**
     * Get the booleanRequired property: The booleanRequired property.
     *
     * @return the booleanRequired value.
     */
    @Generated
    public Boolean isBooleanRequired() {
        return this.booleanRequired;
    }

    /**
     * Get the booleanRequiredNullable property: The booleanRequiredNullable property.
     *
     * @return the booleanRequiredNullable value.
     */
    @Generated
    public Boolean isBooleanRequiredNullable() {
        return this.booleanRequiredNullable;
    }

    /**
     * Get the string property: The string property.
     *
     * @return the string value.
     */
    @Generated
    public String getString() {
        return this.string;
    }

    /**
     * Get the stringNullable property: The stringNullable property.
     *
     * @return the stringNullable value.
     */
    @Generated
    public String getStringNullable() {
        return this.stringNullable;
    }

    /**
     * Get the stringRequired property: The stringRequired property.
     *
     * @return the stringRequired value.
     */
    @Generated
    public String getStringRequired() {
        return this.stringRequired;
    }

    /**
     * Get the stringRequiredNullable property: The stringRequiredNullable property.
     *
     * @return the stringRequiredNullable value.
     */
    @Generated
    public String getStringRequiredNullable() {
        return this.stringRequiredNullable;
    }

    /**
     * Get the bytes property: The bytes property.
     *
     * @return the bytes value.
     */
    @Generated
    public byte[] getBytes() {
        return CoreUtils.clone(this.bytes);
    }

    /**
     * Get the intProperty property: The int property.
     *
     * @return the intProperty value.
     */
    @Generated
    public Integer getIntProperty() {
        return this.intProperty;
    }

    /**
     * Get the longProperty property: The long property.
     *
     * @return the longProperty value.
     */
    @Generated
    public Long getLongProperty() {
        return this.longProperty;
    }

    /**
     * Get the floatProperty property: The float property.
     *
     * @return the floatProperty value.
     */
    @Generated
    public Double getFloatProperty() {
        return this.floatProperty;
    }

    /**
     * Get the doubleProperty property: The double property.
     *
     * @return the doubleProperty value.
     */
    @Generated
    public Double getDoubleProperty() {
        return this.doubleProperty;
    }

    /**
     * Get the duration property: The duration property.
     *
     * @return the duration value.
     */
    @Generated
    public Duration getDuration() {
        return this.duration;
    }

    /**
     * Get the dateTime property: The dateTime property.
     *
     * @return the dateTime value.
     */
    @Generated
    public OffsetDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Get the stringList property: The stringList property.
     *
     * @return the stringList value.
     */
    @Generated
    public List<String> getStringList() {
        return this.stringList;
    }

    /**
     * Get the bytesDict property: The bytesDict property.
     *
     * @return the bytesDict value.
     */
    @Generated
    public Map<String, byte[]> getBytesDict() {
        return this.bytesDict;
    }

    /**
     * Get the immutable property: The immutable property.
     *
     * @return the immutable value.
     */
    @Generated
    public ImmutableModel getImmutable() {
        return this.immutable;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("boolean", this.booleanProperty);
        jsonWriter.writeBooleanField("booleanNullable", this.booleanNullable);
        jsonWriter.writeBooleanField("booleanRequired", this.booleanRequired);
        jsonWriter.writeBooleanField("booleanRequiredNullable", this.booleanRequiredNullable);
        jsonWriter.writeStringField("string", this.string);
        jsonWriter.writeStringField("stringNullable", this.stringNullable);
        jsonWriter.writeStringField("stringRequired", this.stringRequired);
        jsonWriter.writeStringField("stringRequiredNullable", this.stringRequiredNullable);
        jsonWriter.writeBinaryField("bytes", this.bytes);
        jsonWriter.writeNumberField("int", this.intProperty);
        jsonWriter.writeNumberField("long", this.longProperty);
        jsonWriter.writeNumberField("float", this.floatProperty);
        jsonWriter.writeNumberField("double", this.doubleProperty);
        jsonWriter.writeStringField("duration", CoreToCodegenBridgeUtils.durationToStringWithDays(this.duration));
        jsonWriter.writeStringField("dateTime",
            this.dateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.dateTime));
        jsonWriter.writeArrayField("stringList", this.stringList, (writer, element) -> writer.writeString(element));
        jsonWriter.writeMapField("bytesDict", this.bytesDict, (writer, element) -> writer.writeBinary(element));
        jsonWriter.writeJsonField("immutable", this.immutable);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AllPropertiesOptional from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AllPropertiesOptional if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IOException If an error occurs while reading the AllPropertiesOptional.
     */
    public static AllPropertiesOptional fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AllPropertiesOptional deserializedAllPropertiesOptional = new AllPropertiesOptional();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();
                if ("boolean".equals(fieldName)) {
                    deserializedAllPropertiesOptional.booleanProperty = reader.getNullable(JsonReader::getBoolean);
                } else if ("booleanNullable".equals(fieldName)) {
                    deserializedAllPropertiesOptional.booleanNullable = reader.getNullable(JsonReader::getBoolean);
                } else if ("booleanRequired".equals(fieldName)) {
                    deserializedAllPropertiesOptional.booleanRequired = reader.getNullable(JsonReader::getBoolean);
                } else if ("booleanRequiredNullable".equals(fieldName)) {
                    deserializedAllPropertiesOptional.booleanRequiredNullable
                        = reader.getNullable(JsonReader::getBoolean);
                } else if ("string".equals(fieldName)) {
                    deserializedAllPropertiesOptional.string = reader.getString();
                } else if ("stringNullable".equals(fieldName)) {
                    deserializedAllPropertiesOptional.stringNullable = reader.getString();
                } else if ("stringRequired".equals(fieldName)) {
                    deserializedAllPropertiesOptional.stringRequired = reader.getString();
                } else if ("stringRequiredNullable".equals(fieldName)) {
                    deserializedAllPropertiesOptional.stringRequiredNullable = reader.getString();
                } else if ("bytes".equals(fieldName)) {
                    deserializedAllPropertiesOptional.bytes = reader.getBinary();
                } else if ("int".equals(fieldName)) {
                    deserializedAllPropertiesOptional.intProperty = reader.getNullable(JsonReader::getInt);
                } else if ("long".equals(fieldName)) {
                    deserializedAllPropertiesOptional.longProperty = reader.getNullable(JsonReader::getLong);
                } else if ("float".equals(fieldName)) {
                    deserializedAllPropertiesOptional.floatProperty = reader.getNullable(JsonReader::getDouble);
                } else if ("double".equals(fieldName)) {
                    deserializedAllPropertiesOptional.doubleProperty = reader.getNullable(JsonReader::getDouble);
                } else if ("duration".equals(fieldName)) {
                    deserializedAllPropertiesOptional.duration
                        = reader.getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()));
                } else if ("dateTime".equals(fieldName)) {
                    deserializedAllPropertiesOptional.dateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("stringList".equals(fieldName)) {
                    List<String> stringList = reader.readArray(reader1 -> reader1.getString());
                    deserializedAllPropertiesOptional.stringList = stringList;
                } else if ("bytesDict".equals(fieldName)) {
                    Map<String, byte[]> bytesDict = reader.readMap(reader1 -> reader1.getBinary());
                    deserializedAllPropertiesOptional.bytesDict = bytesDict;
                } else if ("immutable".equals(fieldName)) {
                    deserializedAllPropertiesOptional.immutable = ImmutableModel.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return deserializedAllPropertiesOptional;
        });
    }
}
