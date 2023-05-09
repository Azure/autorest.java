// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * a schema that represents a Duration value
 * 
 */
public class DurationSchema extends PrimitiveSchema {

    public enum Format {

        DURATION("duration-rfc3339"),
        SECONDS_INTEGER("seconds-integer"),
        SECONDS_NUMBER("seconds-number");

        private final String value;
        private final static Map<String, Format> CONSTANTS = new HashMap<>();

        static {
            for (Format c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Format(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static Format fromValue(String value) {
            Format constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }
    }

    private Format format;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DurationSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DurationSchema that = (DurationSchema) o;
        return format == that.format;
    }

    @Override
    public int hashCode() {
        return Objects.hash(format);
    }
}
