
package com.azure.autorest.extension.base.model.codemodel;

import java.util.HashMap;
import java.util.Map;


/**
 * a definition of an discrete input for an operation
 * 
 */
public class Parameter extends Value {
    private String clientDefaultValue;
    private Parameter.ImplementationLocation implementation;
    private Operation operation;
    private boolean flattened = false;
    private Parameter originalParameter;
    private Parameter groupedBy;
    private Property targetProperty;

    public String getClientDefaultValue() {
        return clientDefaultValue;
    }

    public void setClientDefaultValue(String clientDefaultValue) {
        this.clientDefaultValue = clientDefaultValue;
    }

    public Parameter.ImplementationLocation getImplementation() {
        return implementation;
    }

    public void setImplementation(Parameter.ImplementationLocation implementation) {
        this.implementation = implementation;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public boolean isFlattened() {
        return flattened;
    }

    public void setFlattened(boolean hidden) {
        this.flattened = hidden;
    }

    public Parameter getOriginalParameter() {
        return originalParameter;
    }

    public void setOriginalParameter(Parameter originalParameter) {
        this.originalParameter = originalParameter;
    }

    public Property getTargetProperty() {
        return targetProperty;
    }

    public void setTargetProperty(Property targetProperty) {
        this.targetProperty = targetProperty;
    }

    public Parameter getGroupedBy() {
        return groupedBy;
    }

    public void setGroupedBy(Parameter groupedBy) {
        this.groupedBy = groupedBy;
    }

    public enum ImplementationLocation {

        CLIENT("Client"),
        CONTEXT("Context"),
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
