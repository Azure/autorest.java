// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.cadl.builtin.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.CoreUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

/** The Builtin model. */
@Immutable
public final class Builtin {
    /*
     * The boolean property.
     */
    @Generated
    @JsonProperty(value = "boolean")
    private boolean booleanProperty;

    /*
     * The string property.
     */
    @Generated
    @JsonProperty(value = "string")
    private String string;

    /*
     * The bytes property.
     */
    @Generated
    @JsonProperty(value = "bytes")
    private byte[] bytes;

    /*
     * The int property.
     */
    @Generated
    @JsonProperty(value = "int")
    private int intProperty;

    /*
     * The safeint property.
     */
    @Generated
    @JsonProperty(value = "safeint")
    private long safeint;

    /*
     * The long property.
     */
    @Generated
    @JsonProperty(value = "long")
    private long longProperty;

    /*
     * The float property.
     */
    @Generated
    @JsonProperty(value = "float")
    private double floatProperty;

    /*
     * The double property.
     */
    @Generated
    @JsonProperty(value = "double")
    private double doubleProperty;

    /*
     * The duration property.
     */
    @Generated
    @JsonProperty(value = "duration")
    private Duration duration;

    /*
     * The date property.
     */
    @Generated
    @JsonProperty(value = "date")
    private LocalDate date;

    /*
     * The dateTime property.
     */
    @Generated
    @JsonProperty(value = "dateTime")
    private OffsetDateTime dateTime;

    /*
     * The stringList property.
     */
    @Generated
    @JsonProperty(value = "stringList")
    private List<String> stringList;

    /*
     * The bytesDict property.
     */
    @Generated
    @JsonProperty(value = "bytesDict")
    private Map<String, byte[]> bytesDict;

    /*
     * The url property.
     */
    @Generated
    @JsonProperty(value = "url")
    private String url;

    /*
     * The nullableFloatDict property.
     */
    @Generated
    @JsonInclude(value = JsonInclude.Include.NON_NULL, content = JsonInclude.Include.ALWAYS)
    @JsonProperty(value = "nullableFloatDict")
    private Map<String, Double> nullableFloatDict;

    /*
     * The encoded property.
     */
    @Generated
    @JsonProperty(value = "encoded")
    private Encoded encoded;

    /**
     * Creates an instance of Builtin class.
     *
     * @param booleanProperty the booleanProperty value to set.
     * @param string the string value to set.
     * @param bytes the bytes value to set.
     * @param intProperty the intProperty value to set.
     * @param safeint the safeint value to set.
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
    @JsonCreator
    public Builtin(
            @JsonProperty(value = "boolean") boolean booleanProperty,
            @JsonProperty(value = "string") String string,
            @JsonProperty(value = "bytes") byte[] bytes,
            @JsonProperty(value = "int") int intProperty,
            @JsonProperty(value = "safeint") long safeint,
            @JsonProperty(value = "long") long longProperty,
            @JsonProperty(value = "float") double floatProperty,
            @JsonProperty(value = "double") double doubleProperty,
            @JsonProperty(value = "duration") Duration duration,
            @JsonProperty(value = "date") LocalDate date,
            @JsonProperty(value = "dateTime") OffsetDateTime dateTime,
            @JsonProperty(value = "stringList") List<String> stringList,
            @JsonProperty(value = "bytesDict") Map<String, byte[]> bytesDict,
            @JsonProperty(value = "url") String url,
            @JsonProperty(value = "nullableFloatDict") Map<String, Double> nullableFloatDict,
            @JsonProperty(value = "encoded") Encoded encoded) {
        this.booleanProperty = booleanProperty;
        this.string = string;
        this.bytes = bytes;
        this.intProperty = intProperty;
        this.safeint = safeint;
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
}
