
package com.azure.autorest.extension.base.model.codemodel;



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
}
