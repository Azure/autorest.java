
package com.azure.autorest.model.codemodel;



/**
 * a Schema that represents a Number value
 * 
 */
public class NumberSchema extends PrimitiveSchema {

    /**
     * precision (# of bits?) of the number
     * (Required)
     * 
     */
    private double precision;
    /**
     * if present, the number must be an exact multiple of this value
     * 
     */
    private double multipleOf;
    /**
     * if present, the value must be lower than or equal to this (unless exclusiveMaximum is true)
     * 
     */
    private double maximum;
    /**
     * if present, the value must be lower than maximum
     * 
     */
    private boolean exclusiveMaximum;
    /**
     * if present, the value must be highter than or equal to this (unless exclusiveMinimum is true)
     * 
     */
    private double minimum;
    /**
     * if present, the value must be higher than minimum
     * 
     */
    private boolean exclusiveMinimum;

    /**
     * precision (# of bits?) of the number
     * (Required)
     * 
     */
    public double getPrecision() {
        return precision;
    }

    /**
     * precision (# of bits?) of the number
     * (Required)
     * 
     */
    public void setPrecision(double precision) {
        this.precision = precision;
    }

    /**
     * if present, the number must be an exact multiple of this value
     * 
     */
    public double getMultipleOf() {
        return multipleOf;
    }

    /**
     * if present, the number must be an exact multiple of this value
     * 
     */
    public void setMultipleOf(double multipleOf) {
        this.multipleOf = multipleOf;
    }

    /**
     * if present, the value must be lower than or equal to this (unless exclusiveMaximum is true)
     * 
     */
    public double getMaximum() {
        return maximum;
    }

    /**
     * if present, the value must be lower than or equal to this (unless exclusiveMaximum is true)
     * 
     */
    public void setMaximum(double maximum) {
        this.maximum = maximum;
    }

    /**
     * if present, the value must be lower than maximum
     * 
     */
    public boolean isExclusiveMaximum() {
        return exclusiveMaximum;
    }

    /**
     * if present, the value must be lower than maximum
     * 
     */
    public void setExclusiveMaximum(boolean exclusiveMaximum) {
        this.exclusiveMaximum = exclusiveMaximum;
    }

    /**
     * if present, the value must be highter than or equal to this (unless exclusiveMinimum is true)
     * 
     */
    public double getMinimum() {
        return minimum;
    }

    /**
     * if present, the value must be highter than or equal to this (unless exclusiveMinimum is true)
     * 
     */
    public void setMinimum(double minimum) {
        this.minimum = minimum;
    }

    /**
     * if present, the value must be higher than minimum
     * 
     */
    public boolean isExclusiveMinimum() {
        return exclusiveMinimum;
    }

    /**
     * if present, the value must be higher than minimum
     * 
     */
    public void setExclusiveMinimum(boolean exclusiveMinimum) {
        this.exclusiveMinimum = exclusiveMinimum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(NumberSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("precision");
        sb.append('=');
        sb.append(this.precision);
        sb.append(',');
        sb.append("multipleOf");
        sb.append('=');
        sb.append(this.multipleOf);
        sb.append(',');
        sb.append("maximum");
        sb.append('=');
        sb.append(this.maximum);
        sb.append(',');
        sb.append("exclusiveMaximum");
        sb.append('=');
        sb.append(this.exclusiveMaximum);
        sb.append(',');
        sb.append("minimum");
        sb.append('=');
        sb.append(this.minimum);
        sb.append(',');
        sb.append("exclusiveMinimum");
        sb.append('=');
        sb.append(this.exclusiveMinimum);
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
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.multipleOf)^(Double.doubleToLongBits(this.multipleOf)>>> 32))));
        result = ((result* 31)+(this.exclusiveMaximum? 1 : 0));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.precision)^(Double.doubleToLongBits(this.precision)>>> 32))));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.maximum)^(Double.doubleToLongBits(this.maximum)>>> 32))));
        result = ((result* 31)+(this.exclusiveMinimum? 1 : 0));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.minimum)^(Double.doubleToLongBits(this.minimum)>>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NumberSchema) == false) {
            return false;
        }
        NumberSchema rhs = ((NumberSchema) other);
        return ((((((Double.doubleToLongBits(this.multipleOf) == Double.doubleToLongBits(rhs.multipleOf))&&(this.exclusiveMaximum == rhs.exclusiveMaximum))&&(Double.doubleToLongBits(this.precision) == Double.doubleToLongBits(rhs.precision)))&&(Double.doubleToLongBits(this.maximum) == Double.doubleToLongBits(rhs.maximum)))&&(this.exclusiveMinimum == rhs.exclusiveMinimum))&&(Double.doubleToLongBits(this.minimum) == Double.doubleToLongBits(rhs.minimum)));
    }

}
