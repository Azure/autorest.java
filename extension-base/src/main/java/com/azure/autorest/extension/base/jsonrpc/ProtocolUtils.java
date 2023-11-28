// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.jsonrpc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ProtocolUtils {
    private static final Set<Class<?>> PRIMITIVES = new HashSet<>(Arrays.asList(Boolean.class, Integer.class,
        Float.class, Double.class, Short.class, Long.class, Byte.class, Character.class));

    public static boolean isPrimitive(Object value) {
        Objects.requireNonNull(value);
        return PRIMITIVES.contains(value.getClass());
    }

    public static String quote(Object value) {
        if (value == null) {
            return "null";
        }
        String input = value.toString();
        StringBuilder builder = null;

        int inputLength = input.length();
        int prevStart = 0;

        for (int i = 0; i < inputLength; i++) {
            char c = input.charAt(i);
            String replacement = null;

            switch (c) {
                case '\\': // backslashes
                    replacement = "\\\\";
                    break;
                case '"': // quotes
                    replacement = "\\\"";
                    break;
                case '\0': // nulls
                    replacement = "\\0";
                    break;
                case '\b': // backspace
                    replacement = "\\b";
                    break;
                case '\f': // formfeed
                    replacement = "\\f";
                    break;
                case '\n': // newline
                    replacement = "\\n";
                    break;
                case '\r': // return
                    replacement = "\\r";
                    break;
                case '\t': // tab
                    replacement = "\\t";
                    break;
                default:
                    break;
            }

            if (replacement != null) {
                if (builder == null) {
                    builder = new StringBuilder(inputLength * 2);
                }

                if (prevStart != i) {
                    builder.append(input, prevStart, i);
                }
                builder.append(replacement);
                prevStart = i + 1;
            }
        }

        if (builder == null) {
            return input;
        }

        builder.append(input, prevStart, inputLength);
        return builder.toString();
    }

    /**
     * Converts a boolean value to a JSON primitive equivalent.
     * @param value the boolean value
     * @return the JSON primitive equivalent
     */
    public static String quote(boolean value) {
        return Boolean.toString(value).toLowerCase();
    }

    /**
     * Converts a int value to a JSON primitive equivalent.
     * @param value the int value
     * @return the JSON primitive equivalent
     */
    public static String quote(int value) {
        return Integer.toString(value).toLowerCase();
    }

    /**
     * Converts a float value to a JSON primitive equivalent.
     * @param value the float value
     * @return the JSON primitive equivalent
     */
    public static String quote(float value) {
        return Float.toString(value).toLowerCase();
    }

    /**
     * Converts a double value to a JSON primitive equivalent.
     * @param value the double value
     * @return the JSON primitive equivalent
     */
    public static String quote(double value) {
        return Double.toString(value).toLowerCase();
    }

    /**
     * Converts a short value to a JSON primitive equivalent.
     * @param value the short value
     * @return the JSON primitive equivalent
     */
    public static String quote(short value) {
        return Short.toString(value).toLowerCase();
    }

    /**
     * Converts a long value to a JSON primitive equivalent.
     * @param value the long value
     * @return the JSON primitive equivalent
     */
    public static String quote(long value) {
        return Long.toString(value).toLowerCase();
    }

    /**
     * Converts a byte value to a JSON primitive equivalent.
     * @param value the byte value
     * @return the JSON primitive equivalent
     */
    public static String quote(byte value) {
        return Byte.toString(value).toLowerCase();
    }

    /**
     * Converts a char value to a JSON primitive equivalent.
     * @param value the char value
     * @return the JSON primitive equivalent
     */
    public static String quote(char value) {
        return Character.toString(value).toLowerCase();
    }

}
