// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import com.azure.autorest.extension.base.util.JsonUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a credential schema.
 */
public class CredentialSchema extends PrimitiveSchema {
    private double maxLength;
    private double minLength;
    private String pattern;

    /**
     * Creates a new instance of the CredentialSchema class.
     */
    public CredentialSchema() {
        super();
    }

    /**
     * Get the maximum length of the string.
     *
     * @return The maximum length of the string.
     */
    public double getMaxLength() {
        return maxLength;
    }

    /**
     * Set the maximum length of the string.
     *
     * @param maxLength The maximum length of the string.
     */
    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * Get the minimum length of the string.
     *
     * @return The minimum length of the string.
     */
    public double getMinLength() {
        return minLength;
    }

    /**
     * Set the minimum length of the string.
     *
     * @param minLength The minimum length of the string.
     */
    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }

    /**
     * Get a regular expression that the string must be validated against.
     *
     * @return A regular expression that the string must be validated against.
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * Set a regular expression that the string must be validated against.
     *
     * @param pattern A regular expression that the string must be validated against.
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        return CredentialSchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this))
            + "[maxLength=" + maxLength + ",minLength=" + minLength + ",pattern=" + Objects.toString(pattern, "<null>")
            + "]";
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

        if (!(other instanceof CredentialSchema)) {
            return false;
        }

        CredentialSchema rhs = ((CredentialSchema) other);
        return Objects.equals(maxLength, rhs.maxLength) && Objects.equals(minLength, rhs.minLength)
            && Objects.equals(pattern, rhs.pattern);
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        return jsonWriter.writeStartObject()
            .writeDoubleField("maxLength", maxLength)
            .writeDoubleField("minLength", minLength)
            .writeStringField("pattern", pattern)
            .writeEndObject();
    }

    /**
     * Deserializes a CredentialSchema instance from the JSON data.
     *
     * @param jsonReader The JSON reader to deserialize from.
     * @return A CredentialSchema instance deserialized from the JSON data.
     * @throws IOException If an error occurs during deserialization.
     */
    public static CredentialSchema fromJson(JsonReader jsonReader) throws IOException {
        return JsonUtils.readObject(jsonReader, CredentialSchema::new, (schema, fieldName, reader) -> {
            if ("maxLength".equals(fieldName)) {
                schema.maxLength = reader.getDouble();
            } else if ("minLength".equals(fieldName)) {
                schema.minLength = reader.getDouble();
            } else if ("pattern".equals(fieldName)) {
                schema.pattern = reader.getString();
            } else {
                reader.skipChildren();;
            }
        });
    }
}
