package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.List;


/**
 * an AND relationship between several schemas
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "allOf"
})
public class AndSchema {

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    @JsonProperty("allOf")
    @JsonPropertyDescription("the set of schemas that this schema is composed of.")
    private List<Object> allOf = new ArrayList<Object>();

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    @JsonProperty("allOf")
    public List<Object> getAllOf() {
        return allOf;
    }

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    @JsonProperty("allOf")
    public void setAllOf(List<Object> allOf) {
        this.allOf = allOf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AndSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("allOf");
        sb.append('=');
        sb.append(((this.allOf == null)?"<null>":this.allOf));
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
        result = ((result* 31)+((this.allOf == null)? 0 :this.allOf.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AndSchema) == false) {
            return false;
        }
        AndSchema rhs = ((AndSchema) other);
        return ((this.allOf == rhs.allOf)||((this.allOf!= null)&&this.allOf.equals(rhs.allOf)));
    }

}
