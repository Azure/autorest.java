
package com.azure.autorest.model.codemodel;

import java.util.HashMap;
import java.util.Map;


/**
 * a schema that represents a ByteArray value
 * 
 */
public class ByteArraySchema extends ValueSchema {

    /**
     * date-time format
     * (Required)
     * 
     */
    private ByteArraySchema.Format format;

    /**
     * date-time format
     * (Required)
     * 
     */
    public ByteArraySchema.Format getFormat() {
        return format;
    }

    /**
     * date-time format
     * (Required)
     * 
     */
    public void setFormat(ByteArraySchema.Format format) {
        this.format = format;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ByteArraySchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("format");
        sb.append('=');
        sb.append(((this.format == null)?"<null>":this.format));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.format == null)? 0 :this.format.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ByteArraySchema) == false) {
            return false;
        }
        ByteArraySchema rhs = ((ByteArraySchema) other);
        return ((this.format == rhs.format)||((this.format!= null)&&this.format.equals(rhs.format)));
    }

    public enum Format {

        BASE_64_URL("base64url"),
        BYTE("byte");
        private final String value;
        private final static Map<String, ByteArraySchema.Format> CONSTANTS = new HashMap<String, ByteArraySchema.Format>();

        static {
            for (ByteArraySchema.Format c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private Format(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        public String value() {
            return this.value;
        }

        public static ByteArraySchema.Format fromValue(String value) {
            ByteArraySchema.Format constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
