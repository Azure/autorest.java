// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * The Encoded model.
 */
@Fluent
public final class Encoded implements JsonSerializable<Encoded> {
    /*
     * The timeInSeconds property.
     */
    @Generated
    private Long timeInSeconds;

    /*
     * The timeInSecondsFraction property.
     */
    @Generated
    private Double timeInSecondsFraction;

    /*
     * The dateTime property.
     */
    @Generated
    private OffsetDateTime dateTime;

    /*
     * The dateTimeRfc7231 property.
     */
    @Generated
    private DateTimeRfc1123 dateTimeRfc7231;

    /*
     * The unixTimestamp property.
     */
    @Generated
    private Long unixTimestamp;

    /*
     * The base64 property.
     */
    @Generated
    private byte[] base64;

    /*
     * The base64url property.
     */
    @Generated
    private Base64Url base64url;

    /**
     * Creates an instance of Encoded class.
     */
    @Generated
    public Encoded() {
    }

    /**
     * Get the timeInSeconds property: The timeInSeconds property.
     * 
     * @return the timeInSeconds value.
     */
    @Generated
    public Duration getTimeInSeconds() {
        if (this.timeInSeconds == null) {
            return null;
        }
        return Duration.ofSeconds(this.timeInSeconds);
    }

    /**
     * Set the timeInSeconds property: The timeInSeconds property.
     * 
     * @param timeInSeconds the timeInSeconds value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setTimeInSeconds(Duration timeInSeconds) {
        if (timeInSeconds == null) {
            this.timeInSeconds = null;
        } else {
            this.timeInSeconds = timeInSeconds.getSeconds();
        }
        return this;
    }

    /**
     * Get the timeInSecondsFraction property: The timeInSecondsFraction property.
     * 
     * @return the timeInSecondsFraction value.
     */
    @Generated
    public Duration getTimeInSecondsFraction() {
        if (this.timeInSecondsFraction == null) {
            return null;
        }
        return Duration.ofNanos((long) (this.timeInSecondsFraction * 1000_000_000L));
    }

    /**
     * Set the timeInSecondsFraction property: The timeInSecondsFraction property.
     * 
     * @param timeInSecondsFraction the timeInSecondsFraction value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setTimeInSecondsFraction(Duration timeInSecondsFraction) {
        if (timeInSecondsFraction == null) {
            this.timeInSecondsFraction = null;
        } else {
            this.timeInSecondsFraction = (double) timeInSecondsFraction.toNanos() / 1000_000_000L;
        }
        return this;
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
     * Set the dateTime property: The dateTime property.
     * 
     * @param dateTime the dateTime value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Get the dateTimeRfc7231 property: The dateTimeRfc7231 property.
     * 
     * @return the dateTimeRfc7231 value.
     */
    @Generated
    public OffsetDateTime getDateTimeRfc7231() {
        if (this.dateTimeRfc7231 == null) {
            return null;
        }
        return this.dateTimeRfc7231.getDateTime();
    }

    /**
     * Set the dateTimeRfc7231 property: The dateTimeRfc7231 property.
     * 
     * @param dateTimeRfc7231 the dateTimeRfc7231 value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setDateTimeRfc7231(OffsetDateTime dateTimeRfc7231) {
        if (dateTimeRfc7231 == null) {
            this.dateTimeRfc7231 = null;
        } else {
            this.dateTimeRfc7231 = new DateTimeRfc1123(dateTimeRfc7231);
        }
        return this;
    }

    /**
     * Get the unixTimestamp property: The unixTimestamp property.
     * 
     * @return the unixTimestamp value.
     */
    @Generated
    public OffsetDateTime getUnixTimestamp() {
        if (this.unixTimestamp == null) {
            return null;
        }
        return OffsetDateTime.ofInstant(Instant.ofEpochSecond(this.unixTimestamp), ZoneOffset.UTC);
    }

    /**
     * Set the unixTimestamp property: The unixTimestamp property.
     * 
     * @param unixTimestamp the unixTimestamp value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setUnixTimestamp(OffsetDateTime unixTimestamp) {
        if (unixTimestamp == null) {
            this.unixTimestamp = null;
        } else {
            this.unixTimestamp = unixTimestamp.toEpochSecond();
        }
        return this;
    }

    /**
     * Get the base64 property: The base64 property.
     * 
     * @return the base64 value.
     */
    @Generated
    public byte[] getBase64() {
        return CoreUtils.clone(this.base64);
    }

    /**
     * Set the base64 property: The base64 property.
     * 
     * @param base64 the base64 value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setBase64(byte[] base64) {
        this.base64 = CoreUtils.clone(base64);
        return this;
    }

    /**
     * Get the base64url property: The base64url property.
     * 
     * @return the base64url value.
     */
    @Generated
    public byte[] getBase64url() {
        if (this.base64url == null) {
            return null;
        }
        return this.base64url.decodedBytes();
    }

    /**
     * Set the base64url property: The base64url property.
     * 
     * @param base64url the base64url value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setBase64url(byte[] base64url) {
        if (base64url == null) {
            this.base64url = null;
        } else {
            this.base64url = Base64Url.encode(CoreUtils.clone(base64url));
        }
        return this;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeNumberField("timeInSeconds", this.timeInSeconds);
        jsonWriter.writeNumberField("timeInSecondsFraction", this.timeInSecondsFraction);
        jsonWriter.writeStringField("dateTime",
            this.dateTime == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.dateTime));
        jsonWriter.writeStringField("dateTimeRfc7231", Objects.toString(this.dateTimeRfc7231, null));
        jsonWriter.writeNumberField("unixTimestamp", this.unixTimestamp);
        jsonWriter.writeBinaryField("base64", this.base64);
        jsonWriter.writeStringField("base64url", Objects.toString(this.base64url, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Encoded from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of Encoded if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IOException If an error occurs while reading the Encoded.
     */
    public static Encoded fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Encoded deserializedEncoded = new Encoded();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("timeInSeconds".equals(fieldName)) {
                    deserializedEncoded.timeInSeconds = reader.getNullable(JsonReader::getLong);
                } else if ("timeInSecondsFraction".equals(fieldName)) {
                    deserializedEncoded.timeInSecondsFraction = reader.getNullable(JsonReader::getDouble);
                } else if ("dateTime".equals(fieldName)) {
                    deserializedEncoded.dateTime
                        = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else if ("dateTimeRfc7231".equals(fieldName)) {
                    deserializedEncoded.dateTimeRfc7231
                        = reader.getNullable(nonNullReader -> new DateTimeRfc1123(nonNullReader.getString()));
                } else if ("unixTimestamp".equals(fieldName)) {
                    deserializedEncoded.unixTimestamp = reader.getNullable(JsonReader::getLong);
                } else if ("base64".equals(fieldName)) {
                    deserializedEncoded.base64 = reader.getBinary();
                } else if ("base64url".equals(fieldName)) {
                    deserializedEncoded.base64url
                        = reader.getNullable(nonNullReader -> new Base64Url(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedEncoded;
        });
    }
}
