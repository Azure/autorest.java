package com.azure.autorest.extension.base.model.extensionmodel;

import java.util.List;

public class XmsEnum {
    private String name;
    private boolean modelAsString = false;
    private List<Value> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isModelAsString() {
        return modelAsString;
    }

    public void setModelAsString(boolean modelAsString) {
        this.modelAsString = modelAsString;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }


    public static class Value {
        private String value;
        private String description;
        private String name;

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
