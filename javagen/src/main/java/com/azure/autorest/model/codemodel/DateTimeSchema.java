
package com.azure.autorest.model.codemodel;

import java.util.HashMap;
import java.util.Map;


/**
 * a schema that represents a DateTime value
 * 
 */
public class DateTimeSchema extends PrimitiveSchema {

    /**
     * date-time format
     * (Required)
     * 
     */
    private DateTimeSchema.Format format;

    /**
     * date-time format
     * (Required)
     * 
     */
    public DateTimeSchema.Format getFormat() {
        return format;
    }

    /**
     * date-time format
     * (Required)
     * 
     */
    public void setFormat(DateTimeSchema.Format format) {
        this.format = format;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DateTimeSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof DateTimeSchema) == false) {
            return false;
        }
        DateTimeSchema rhs = ((DateTimeSchema) other);
        return ((this.format == rhs.format)||((this.format!= null)&&this.format.equals(rhs.format)));
    }

    public enum Format {

        DATE_TIME("date-time"),
        DATE_TIME_RFC_1123("date-time-rfc1123");
        private final String value;
        private final static Map<String, DateTimeSchema.Format> CONSTANTS = new HashMap<String, DateTimeSchema.Format>();

        static {
            for (DateTimeSchema.Format c: values()) {
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

        public static DateTimeSchema.Format fromValue(String value) {
            DateTimeSchema.Format constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
