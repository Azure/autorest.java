
package com.azure.autorest.extension.base.model.codemodel;



/**
 * a schema that represents a key-value collection
 * 
 */
public class DictionarySchema extends ComplexSchema {

    /**
     * 
     * (Required)
     * 
     */
    private Schema elementType;

    /**
     * 
     * (Required)
     * 
     */
    public Schema getElementType() {
        return elementType;
    }

    /**
     * 
     * (Required)
     * 
     */
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
