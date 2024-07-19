// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a Uri value.
 */
public class UriSchema extends PrimitiveSchema {
    private double maxLength;
    private double minLength;
    private String pattern;

    /**
     * Creates a new instance of the UriSchema class.
     */
    public UriSchema() {
        super();
    }

    /**
     * Get the maximum length of the URI.
     *
     * @return The maximum length of the URI.
     */
    public double getMaxLength() {
        return maxLength;
    }

    /**
     * Set the maximum length of the URI.
     *
     * @param maxLength The maximum length of the URI.
     */
    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Get the minimum length of the URI.
     *
     * @return The minimum length of the URI.
     */
    public double getMinLength() {
        return minLength;
    }

    /**
     * Set the minimum length of the URI.
     *
     * @param minLength The minimum length of the URI.
     */
    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }

    /**
     * Get a regular expression that the URI must be validated against.
     *
     * @return A regular expression that the URI must be validated against.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set a regular expression that the URI must be validated against.
     *
     * @param pattern A regular expression that the URI must be validated against.
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return UriSchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[maxLength="
            + maxLength + ",minLength=" + minLength + ",pattern=" + Objects.toString(pattern, "<null>") + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, maxLength, minLength);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof UriSchema)) {
            return false;
        }

        UriSchema rhs = ((UriSchema) other);
        return maxLength == rhs.maxLength && minLength == rhs.minLength && Objects.equals(pattern, rhs.pattern);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return super.writeParentProperties(jsonWriter.writeStartObject())
            .writeDoubleField("maxLength", maxLength)
            .writeDoubleField("minLength", minLength)
            .writeStringField("pattern", pattern)
            .writeEndObject();
    }

    public static UriSchema fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, UriSchema::new, (schema, fieldName, reader) -> {
            if (schema.tryConsumeParentProperties(schema, fieldName, reader)) {
                return;
            }

            if ("maxLength".equals(fieldName)) {
                schema.maxLength = reader.getDouble();
            } else if ("minLength".equals(fieldName)) {
                schema.minLength = reader.getDouble();
            } else if ("pattern".equals(fieldName)) {
                schema.pattern = reader.getString();
            } else {
                reader.skipChildren();
            }
        });
    }
}
