package com.azure.autorest.model.codemodelfour;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;


/**
 * a definition of an discrete input for an operation
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "implementation"
})
public class Parameter {

    @JsonProperty("implementation")
    private ImplementationLocation implementation;

    @JsonProperty("implementation")
    public ImplementationLocation getImplementation() {
        return implementation;
    }

    @JsonProperty("implementation")
    public void setImplementation(ImplementationLocation implementation) {
        this.implementation = implementation;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Parameter.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("implementation");
        sb.append('=');
        sb.append(((this.implementation == null)?"<null>":this.implementation));
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
        result = ((result* 31)+((this.implementation == null)? 0 :this.implementation.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Parameter) == false) {
            return false;
        }
        Parameter rhs = ((Parameter) other);
        return ((this.implementation == rhs.implementation)||((this.implementation!= null)&&this.implementation.equals(rhs.implementation)));
    }

    public enum ImplementationLocation {

        CLIENT("Client"),
        METHOD("Method");
        private final String value;
        private final static Map<String, ImplementationLocation> CONSTANTS = new HashMap<String, ImplementationLocation>();

        static {
            for (ImplementationLocation c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        private ImplementationLocation(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static ImplementationLocation fromValue(String value) {
            ImplementationLocation constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
