package com.azure.autorest.model.codemodelfour;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a schema that represents a set of parameters.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "parameters"
})
public class ParameterGroupSchema {

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
    @JsonProperty("parameters")
    @JsonPropertyDescription("the collection of properties that are in this object")
    private List<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
    @JsonProperty("parameters")
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
    @JsonProperty("parameters")
    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ParameterGroupSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("parameters");
        sb.append('=');
        sb.append(((this.parameters == null)?"<null>":this.parameters));
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
        result = ((result* 31)+((this.parameters == null)? 0 :this.parameters.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ParameterGroupSchema) == false) {
            return false;
        }
        ParameterGroupSchema rhs = ((ParameterGroupSchema) other);
        return ((this.parameters == rhs.parameters)||((this.parameters!= null)&&this.parameters.equals(rhs.parameters)));
    }

}
