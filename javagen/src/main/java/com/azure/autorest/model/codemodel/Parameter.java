
package com.azure.autorest.model.codemodel;

import java.util.HashMap;
import java.util.Map;


/**
 * a definition of an discrete input for an operation
 * 
 */
public class Parameter extends Value {

    private Parameter.ImplementationLocation implementation;

    public Parameter.ImplementationLocation getImplementation() {
        return implementation;
    }

    public void setImplementation(Parameter.ImplementationLocation implementation) {
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
        private final static Map<String, Parameter.ImplementationLocation> CONSTANTS = new HashMap<String, Parameter.ImplementationLocation>();

        static {
            for (Parameter.ImplementationLocation c: values()) {
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

        public String value() {
            return this.value;
        }

        public static Parameter.ImplementationLocation fromValue(String value) {
            Parameter.ImplementationLocation constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
