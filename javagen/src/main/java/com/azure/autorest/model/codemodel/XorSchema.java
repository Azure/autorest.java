
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * an XOR relationship between several schemas
 * 
 */
public class XorSchema extends ComplexSchema {

    /**
     * the set of schemas that this must be one and only one of.
     * (Required)
     * 
     */
    private List<Schema> oneOf = new ArrayList<Schema>();

    /**
     * the set of schemas that this must be one and only one of.
     * (Required)
     * 
     */
    public List<Schema> getOneOf() {
        return oneOf;
    }

    /**
     * the set of schemas that this must be one and only one of.
     * (Required)
     * 
     */
    public void setOneOf(List<Schema> oneOf) {
        this.oneOf = oneOf;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(XorSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("oneOf");
        sb.append('=');
        sb.append(((this.oneOf == null)?"<null>":this.oneOf));
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
        result = ((result* 31)+((this.oneOf == null)? 0 :this.oneOf.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof XorSchema) == false) {
            return false;
        }
        XorSchema rhs = ((XorSchema) other);
        return ((this.oneOf == rhs.oneOf)||((this.oneOf!= null)&&this.oneOf.equals(rhs.oneOf)));
    }

}
