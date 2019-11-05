
package com.azure.autorest.model.codemodel;



/**
 * schema types that can be objects
 * 
 */
public class ComplexSchema extends Schema {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ComplexSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ComplexSchema) == false) {
            return false;
        }
        ComplexSchema rhs = ((ComplexSchema) other);
        return true;
    }

}
