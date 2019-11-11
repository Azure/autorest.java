
package com.azure.autorest.extension.base.model.codemodel;



/**
 * a schema that represents a ODataQuery value
 * 
 */
public class ODataQuerySchema extends Schema {


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ODataQuerySchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        if ((other instanceof ODataQuerySchema) == false) {
            return false;
        }
        ODataQuerySchema rhs = ((ODataQuerySchema) other);
        return true;
    }

}
