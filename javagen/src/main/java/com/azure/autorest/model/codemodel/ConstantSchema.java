package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a schema that represents a constant value
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "constantSchema",
    "value"
})
public class ConstantSchema {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("constantSchema")
    private ConstantType constantSchema;
    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("a container for the actual constant value")
    private ConstantValue value;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("constantSchema")
    public ConstantType getConstantSchema() {
        return constantSchema;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("constantSchema")
    public void setConstantSchema(ConstantType constantSchema) {
        this.constantSchema = constantSchema;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    @JsonProperty("value")
    public ConstantValue getValue() {
        return value;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    @JsonProperty("value")
    public void setValue(ConstantValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("constantSchema");
        sb.append('=');
        sb.append(((this.constantSchema == null)?"<null>":this.constantSchema));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
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
        result = ((result* 31)+((this.constantSchema == null)? 0 :this.constantSchema.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConstantSchema) == false) {
            return false;
        }
        ConstantSchema rhs = ((ConstantSchema) other);
        return (((this.constantSchema == rhs.constantSchema)||((this.constantSchema!= null)&&this.constantSchema.equals(rhs.constantSchema)))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
    }

}
