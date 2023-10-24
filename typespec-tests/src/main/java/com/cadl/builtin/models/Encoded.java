// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.builtin.models;

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.core.util.Base64Url;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.regex.Pattern;

/**
 * The Encoded model.
 */
@Fluent
public final class Encoded {
    /*
     * The timeInSeconds property.
     */
    @Generated
    @JsonProperty(value = "timeInSeconds")
    private Long timeInSeconds;

    /*
     * The timeInSecondsFraction property.
     */
    @Generated
    @JsonProperty(value = "timeInSecondsFraction")
    private Double timeInSecondsFraction;

    /*
     * The dateTime property.
     */
    @Generated
    @JsonProperty(value = "dateTime")
    private OffsetDateTime dateTime;

    /*
     * The dateTimeRfc7231 property.
     */
    @Generated
    @JsonProperty(value = "dateTimeRfc7231")
    private DateTimeRfc1123 dateTimeRfc7231;

    /*
     * The unixTimestamp property.
     */
    @Generated
    @JsonProperty(value = "unixTimestamp")
    private Long unixTimestamp;

    /*
     * The base64 property.
     */
    @Generated
    @JsonProperty(value = "base64")
    private byte[] base64;

    /*
     * The base64url property.
     */
    @Generated
    @JsonProperty(value = "base64url")
    private Base64Url base64Url;

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
     * Get the base64Url property: The base64url property.
     * 
     * @return the base64Url value.
     */
    @Generated
    public byte[] getBase64Url() {
        if (this.base64Url == null) {
            return null;
        }
        return this.base64Url.decodedBytes();
    }

    /**
     * Set the base64Url property: The base64url property.
     * 
     * @param base64Url the base64Url value to set.
     * @return the Encoded object itself.
     */
    @Generated
    public Encoded setBase64Url(byte[] base64Url) {
        if (base64Url == null) {
            this.base64Url = null;
        } else {
            this.base64Url = Base64Url.encode(CoreUtils.clone(base64Url));
        }
        return this;
    }
}
