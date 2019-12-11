package com.azure.autorest.extension.base.jsonrpc;


import com.azure.core.implementation.TypeUtil;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ProtocolUtils {
    private static final List<Type> PRIMITIVES = Arrays.asList(
            Boolean.class,
            Integer.class,
            Float.class,
            Double.class,
            Short.class,
            Long.class,
            Byte.class,
            Character.class);

    public static boolean isPrimitive(Object value) {
        Objects.requireNonNull(value);
        return PRIMITIVES.stream().anyMatch(type -> TypeUtil.isTypeOrSubTypeOf(value.getClass(), type));
    }

    public static String quote(Object value) {
        if (value == null) {
            return "null";
        }
        String input = value.toString();
        return input.
                        replace( "\\","\\\\" ). // backslashes
                        replace( "\"","\\\"" ). // quotes
                        replace( "\0","\\0" ).  // nulls
                        replace( "\b","\\b" ).  // backspace
                        replace( "\f","\\f" ).  // formfeed
                        replace( "\n","\\n" ).  // newline
                        replace( "\r","\\r" ).  // return
                        replace( "\t","\\t" );  // tab
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
