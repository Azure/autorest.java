// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import com.cadl.builtin.implementation.CoreToCodegenBridgeUtils;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * The Builtin model.
 */
@Immutable
public final class Builtin implements JsonSerializable<Builtin> {
    private static final DateTimeFormatter ISO_8601 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");

    /*
     * The boolean property.
     */
    @Generated
    private final boolean booleanProperty;

    /*
     * The string property.
     */
    @Generated
    private final String string;

    /*
     * The bytes property.
     */
    @Generated
    private final byte[] bytes;

    /*
     * The int property.
     */
    @Generated
    private final int intProperty;

    /*
     * The safeint property.
     */
    @Generated
    private final long safeint;

    /*
     * The decimal property.
     */
    @Generated
    private final double decimal;

    /*
     * The long property.
     */
    @Generated
    private final long longProperty;

    /*
     * The float property.
     */
    @Generated
    private final double floatProperty;

    /*
     * The double property.
     */
    @Generated
    private final double doubleProperty;

    /*
     * The duration property.
     */
    @Generated
    private final Duration duration;

    /*
     * The date property.
     */
    @Generated
    private final LocalDate date;

    /*
     * The dateTime property.
     */
    @Generated
    private final OffsetDateTime dateTime;

    /*
     * The stringList property.
     */
    @Generated
    private final List<String> stringList;

    /*
     * The bytesDict property.
     */
    @Generated
    private final Map<String, byte[]> bytesDict;

    /*
     * The url property.
     */
    @Generated
    private final String url;

    /*
     * The nullableFloatDict property.
     */
    @Generated
    private final Map<String, Double> nullableFloatDict;

    /*
     * The encoded property.
     */
    @Generated
    private final Encoded encoded;

    /**
     * Creates an instance of Builtin class.
     * 
     * @param booleanProperty the booleanProperty value to set.
     * @param string the string value to set.
     * @param bytes the bytes value to set.
     * @param intProperty the intProperty value to set.
     * @param safeint the safeint value to set.
     * @param decimal the decimal value to set.
     * @param longProperty the longProperty value to set.
     * @param floatProperty the floatProperty value to set.
     * @param doubleProperty the doubleProperty value to set.
     * @param duration the duration value to set.
     * @param date the date value to set.
     * @param dateTime the dateTime value to set.
     * @param stringList the stringList value to set.
     * @param bytesDict the bytesDict value to set.
     * @param url the url value to set.
     * @param nullableFloatDict the nullableFloatDict value to set.
     * @param encoded the encoded value to set.
     */
    @Generated
    public Builtin(boolean booleanProperty, String string, byte[] bytes, int intProperty, long safeint, double decimal,
        long longProperty, double floatProperty, double doubleProperty, Duration duration, LocalDate date,
        OffsetDateTime dateTime, List<String> stringList, Map<String, byte[]> bytesDict, String url,
        Map<String, Double> nullableFloatDict, Encoded encoded) {
        this.booleanProperty = booleanProperty;
        this.string = string;
        this.bytes = bytes;
        this.intProperty = intProperty;
        this.safeint = safeint;
        this.decimal = decimal;
        this.longProperty = longProperty;
        this.floatProperty = floatProperty;
        this.doubleProperty = doubleProperty;
        this.duration = duration;
        this.date = date;
        this.dateTime = dateTime;
        this.stringList = stringList;
        this.bytesDict = bytesDict;
        this.url = url;
        this.nullableFloatDict = nullableFloatDict;
        this.encoded = encoded;
    }

    /**
     * Get the booleanProperty property: The boolean property.
     * 
     * @return the booleanProperty value.
     */
    @Generated
    public boolean isBooleanProperty() {
        return this.booleanProperty;
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
    public int getIntProperty() {
        return this.intProperty;
    }

    /**
     * Get the safeint property: The safeint property.
     * 
     * @return the safeint value.
     */
    @Generated
    public long getSafeint() {
        return this.safeint;
    }

    /**
     * Get the decimal property: The decimal property.
     * 
     * @return the decimal value.
     */
    @Generated
    public double getDecimal() {
        return this.decimal;
    }

    /**
     * Get the longProperty property: The long property.
     * 
     * @return the longProperty value.
     */
    @Generated
    public long getLongProperty() {
        return this.longProperty;
    }

    /**
     * Get the floatProperty property: The float property.
     * 
     * @return the floatProperty value.
     */
    @Generated
    public double getFloatProperty() {
        return this.floatProperty;
    }

    /**
     * Get the doubleProperty property: The double property.
     * 
     * @return the doubleProperty value.
     */
    @Generated
    public double getDoubleProperty() {
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
     * Get the date property: The date property.
     * 
     * @return the date value.
     */
    @Generated
    public LocalDate getDate() {
        return this.date;
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
     * Get the url property: The url property.
     * 
     * @return the url value.
     */
    @Generated
    public String getUrl() {
        return this.url;
    }

    /**
     * Get the nullableFloatDict property: The nullableFloatDict property.
     * 
     * @return the nullableFloatDict value.
     */
    @Generated
    public Map<String, Double> getNullableFloatDict() {
        return this.nullableFloatDict;
    }

    /**
     * Get the encoded property: The encoded property.
     * 
     * @return the encoded value.
     */
    @Generated
    public Encoded getEncoded() {
        return this.encoded;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("boolean", this.booleanProperty);
        jsonWriter.writeStringField("string", this.string);
        jsonWriter.writeBinaryField("bytes", this.bytes);
        jsonWriter.writeIntField("int", this.intProperty);
        jsonWriter.writeLongField("safeint", this.safeint);
        jsonWriter.writeDoubleField("decimal", this.decimal);
        jsonWriter.writeLongField("long", this.longProperty);
        jsonWriter.writeDoubleField("float", this.floatProperty);
        jsonWriter.writeDoubleField("double", this.doubleProperty);
        jsonWriter.writeStringField("duration", CoreToCodegenBridgeUtils.durationToStringWithDays(this.duration));
        jsonWriter.writeStringField("date", Objects.toString(this.date, null));
        jsonWriter.writeStringField("dateTime", this.dateTime == null ? null : ISO_8601.format(this.dateTime));
        jsonWriter.writeArrayField("stringList", this.stringList, (writer, element) -> writer.writeString(element));
        jsonWriter.writeMapField("bytesDict", this.bytesDict, (writer, element) -> writer.writeBinary(element));
        jsonWriter.writeStringField("url", this.url);
        jsonWriter.writeMapField("nullableFloatDict", this.nullableFloatDict,
            (writer, element) -> writer.writeDouble(element));
        jsonWriter.writeJsonField("encoded", this.encoded);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Builtin from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Builtin if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Builtin.
     */
    public static Builtin fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            boolean booleanProperty = false;
            String string = null;
            byte[] bytes = null;
            int intProperty = 0;
            long safeint = 0L;
            double decimal = 0.0;
            long longProperty = 0L;
            double floatProperty = 0.0;
            double doubleProperty = 0.0;
            Duration duration = null;
            LocalDate date = null;
            OffsetDateTime dateTime = null;
            List<String> stringList = null;
            Map<String, byte[]> bytesDict = null;
            String url = null;
            Map<String, Double> nullableFloatDict = null;
            Encoded encoded = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("boolean".equals(fieldName)) {
                    booleanProperty = reader.getBoolean();
                } else if ("string".equals(fieldName)) {
                    string = reader.getString();
                } else if ("bytes".equals(fieldName)) {
                    bytes = reader.getBinary();
                } else if ("int".equals(fieldName)) {
                    intProperty = reader.getInt();
                } else if ("safeint".equals(fieldName)) {
                    safeint = reader.getLong();
                } else if ("decimal".equals(fieldName)) {
                    decimal = reader.getDouble();
                } else if ("long".equals(fieldName)) {
                    longProperty = reader.getLong();
                } else if ("float".equals(fieldName)) {
                    floatProperty = reader.getDouble();
                } else if ("double".equals(fieldName)) {
                    doubleProperty = reader.getDouble();
                } else if ("duration".equals(fieldName)) {
                    duration = reader.getNullable(nonNullReader -> Duration.parse(nonNullReader.getString()));
                } else if ("date".equals(fieldName)) {
                    date = reader.getNullable(nonNullReader -> LocalDate.parse(nonNullReader.getString()));
                } else if ("dateTime".equals(fieldName)) {
                    dateTime = reader
                        .getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString(), ISO_8601));
                } else if ("stringList".equals(fieldName)) {
                    stringList = reader.readArray(reader1 -> reader1.getString());
                } else if ("bytesDict".equals(fieldName)) {
                    bytesDict = reader.readMap(reader1 -> reader1.getBinary());
                } else if ("url".equals(fieldName)) {
                    url = reader.getString();
                } else if ("nullableFloatDict".equals(fieldName)) {
                    nullableFloatDict = reader.readMap(reader1 -> reader1.getDouble());
                } else if ("encoded".equals(fieldName)) {
                    encoded = Encoded.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }
            return new Builtin(booleanProperty, string, bytes, intProperty, safeint, decimal, longProperty,
                floatProperty, doubleProperty, duration, date, dateTime, stringList, bytesDict, url, nullableFloatDict,
                encoded);
        });
    }
}
