package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * an OR relationship between several schemas
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "anyOf"
})
public class OrSchema {

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    @JsonProperty("anyOf")
    @JsonPropertyDescription("the set of schemas that this schema is composed of. Every schema is optional")
    private List<Object> anyOf = new ArrayList<Object>();

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    @JsonProperty("anyOf")
    public List<Object> getAnyOf() {
        return anyOf;
    }

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    @JsonProperty("anyOf")
    public void setAnyOf(List<Object> anyOf) {
        this.anyOf = anyOf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OrSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("anyOf");
        sb.append('=');
        sb.append(((this.anyOf == null)?"<null>":this.anyOf));
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
        result = ((result* 31)+((this.anyOf == null)? 0 :this.anyOf.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OrSchema) == false) {
            return false;
        }
        OrSchema rhs = ((OrSchema) other);
        return ((this.anyOf == rhs.anyOf)||((this.anyOf!= null)&&this.anyOf.equals(rhs.anyOf)));
    }

}
