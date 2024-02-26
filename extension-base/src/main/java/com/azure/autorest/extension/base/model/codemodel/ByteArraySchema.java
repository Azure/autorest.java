// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a byte array schema.
 */
public class ByteArraySchema extends PrimitiveSchema {
    private ByteArraySchema.Format format;

    /**
     * Gets the byte array format. (Required)
     *
     * @return The byte array format.
     */
    public ByteArraySchema.Format getFormat() {
        return format;
    }

    /**
     * Sets the byte array format. (Required)
     *
     * @param format The byte array format.
     */
    public void setFormat(ByteArraySchema.Format format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return ByteArraySchema.class.getName() + "@" + Integer.toHexString(System.identityHashCode(this)) + "[format="
            + Objects.toString(format, "<null>") + "]";
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(format);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ByteArraySchema)) {
            return false;
        }

        ByteArraySchema rhs = ((ByteArraySchema) other);
        return Objects.equals(format, rhs.format);
    }

    public enum Format {
        BASE_64_URL("base64url"),
        BYTE("byte");

        private final String value;

        Format(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        /**
         * Gets the value of the format.
         *
         * @return The value of the format.
         */
        public String value() {
            return this.value;
        }

        public static ByteArraySchema.Format fromValue(String value) {
            if ("base64url".equals(value)) {
                return BASE_64_URL;
            } else if ("byte".equals(value)) {
                return BYTE;
            } else {
                throw new IllegalArgumentException(value);
            }
        }

    }

}
