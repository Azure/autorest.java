
package com.azure.autorest.model.codemodel;



/**
 * a schema that represents a Date value
 * 
 */
public class DateSchema extends PrimitiveSchema {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DateSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof DateSchema) == false) {
            return false;
        }
        DateSchema rhs = ((DateSchema) other);
        return true;
    }

}
