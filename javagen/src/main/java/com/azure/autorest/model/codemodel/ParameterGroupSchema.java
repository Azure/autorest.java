
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * a schema that represents a set of parameters.
 * 
 */
public class ParameterGroupSchema extends ComplexSchema {

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
    private List<Parameter> parameters = new ArrayList<Parameter>();

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
    public List<Parameter> getParameters() {
        return parameters;
    }

    /**
     * the collection of properties that are in this object
     * (Required)
     * 
     */
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
