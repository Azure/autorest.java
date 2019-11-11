
package com.azure.autorest.extension.base.model.codemodel;



/**
 * a Schema that represents and array of values
 * 
 */
public class ArraySchema extends ValueSchema {

    /**
     * 
     * (Required)
     * 
     */
    private Schema elementType;
    /**
     * maximum number of elements in the array
     * 
     */
    private double maxItems;
    /**
     * minimum number of elements in the array
     * 
     */
    private double minItems;
    /**
     * if the elements in the array should be unique
     * 
     */
    private boolean uniqueItems;

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

    /**
     * maximum number of elements in the array
     * 
     */
    public double getMaxItems() {
        return maxItems;
    }

    /**
     * maximum number of elements in the array
     * 
     */
    public void setMaxItems(double maxItems) {
        this.maxItems = maxItems;
    }

    /**
     * minimum number of elements in the array
     * 
     */
    public double getMinItems() {
        return minItems;
    }

    /**
     * minimum number of elements in the array
     * 
     */
    public void setMinItems(double minItems) {
        this.minItems = minItems;
    }

    /**
     * if the elements in the array should be unique
     * 
     */
    public boolean isUniqueItems() {
        return uniqueItems;
    }

    /**
     * if the elements in the array should be unique
     * 
     */
    public void setUniqueItems(boolean uniqueItems) {
        this.uniqueItems = uniqueItems;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ArraySchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("elementType");
        sb.append('=');
        sb.append(((this.elementType == null)?"<null>":this.elementType));
        sb.append(',');
        sb.append("maxItems");
        sb.append('=');
        sb.append(this.maxItems);
        sb.append(',');
        sb.append("minItems");
        sb.append('=');
        sb.append(this.minItems);
        sb.append(',');
        sb.append("uniqueItems");
        sb.append('=');
        sb.append(this.uniqueItems);
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
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.minItems)^(Double.doubleToLongBits(this.minItems)>>> 32))));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.maxItems)^(Double.doubleToLongBits(this.maxItems)>>> 32))));
        result = ((result* 31)+((this.elementType == null)? 0 :this.elementType.hashCode()));
        result = ((result* 31)+(this.uniqueItems? 1 : 0));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ArraySchema) == false) {
            return false;
        }
        ArraySchema rhs = ((ArraySchema) other);
        return ((((Double.doubleToLongBits(this.minItems) == Double.doubleToLongBits(rhs.minItems))&&(Double.doubleToLongBits(this.maxItems) == Double.doubleToLongBits(rhs.maxItems)))&&((this.elementType == rhs.elementType)||((this.elementType!= null)&&this.elementType.equals(rhs.elementType))))&&(this.uniqueItems == rhs.uniqueItems));
    }

}
