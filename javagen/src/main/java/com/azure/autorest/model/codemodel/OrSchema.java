
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * an OR relationship between several schemas
 * 
 */
public class OrSchema extends ComplexSchema {

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    private List<ComplexSchema> anyOf = new ArrayList<ComplexSchema>();

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    public List<ComplexSchema> getAnyOf() {
        return anyOf;
    }

    /**
     * the set of schemas that this schema is composed of. Every schema is optional
     * (Required)
     * 
     */
    public void setAnyOf(List<ComplexSchema> anyOf) {
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
