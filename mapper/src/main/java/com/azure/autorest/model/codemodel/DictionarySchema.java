package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a schema that represents a key-value collection
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "elementType"
})
public class DictionarySchema {

    /**
     * The Schema Object allows the definition of input and output data types.
     * (Required)
     * 
     */
    @JsonProperty("elementType")
    @JsonPropertyDescription("The Schema Object allows the definition of input and output data types.")
    private Schema elementType;

    /**
     * The Schema Object allows the definition of input and output data types.
     * (Required)
     * 
     */
    @JsonProperty("elementType")
    public Schema getElementType() {
        return elementType;
    }

    /**
     * The Schema Object allows the definition of input and output data types.
     * (Required)
     * 
     */
    @JsonProperty("elementType")
    public void setElementType(Schema elementType) {
        this.elementType = elementType;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DictionarySchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("elementType");
        sb.append('=');
        sb.append(((this.elementType == null)?"<null>":this.elementType));
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
        result = ((result* 31)+((this.elementType == null)? 0 :this.elementType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DictionarySchema) == false) {
            return false;
        }
        DictionarySchema rhs = ((DictionarySchema) other);
        return ((this.elementType == rhs.elementType)||((this.elementType!= null)&&this.elementType.equals(rhs.elementType)));
    }

}
