// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;



/**
 * a Schema that represents a string value
 * 
 */
public class StringSchema extends PrimitiveSchema {

    /**
     * the maximum length of the string
     * 
     */
    private double maxLength;
    /**
     * the minimum length of the string
     * 
     */
    private double minLength;
    /**
     * a regular expression that the string must be validated against
     * 
     */
    private String pattern;

    /**
     * the maximum length of the string
     * 
     */
    public double getMaxLength() {
        return maxLength;
    }

    /**
     * the maximum length of the string
     * 
     */
    public void setMaxLength(double maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * the minimum length of the string
     * 
     */
    public double getMinLength() {
        return minLength;
    }

    /**
     * the minimum length of the string
     * 
     */
    public void setMinLength(double minLength) {
        this.minLength = minLength;
    }

    /**
     * a regular expression that the string must be validated against
     * 
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * a regular expression that the string must be validated against
     * 
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("maxLength");
        sb.append('=');
        sb.append(this.maxLength);
        sb.append(',');
        sb.append("minLength");
        sb.append('=');
        sb.append(this.minLength);
        sb.append(',');
        sb.append("pattern");
        sb.append('=');
        sb.append(((this.pattern == null)?"<null>":this.pattern));
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
        result = ((result* 31)+((this.pattern == null)? 0 :this.pattern.hashCode()));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.maxLength)^(Double.doubleToLongBits(this.maxLength)>>> 32))));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.minLength)^(Double.doubleToLongBits(this.minLength)>>> 32))));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof StringSchema) == false) {
            return false;
        }
        StringSchema rhs = ((StringSchema) other);
        return ((((this.pattern == rhs.pattern)||((this.pattern!= null)&&this.pattern.equals(rhs.pattern)))&&(Double.doubleToLongBits(this.maxLength) == Double.doubleToLongBits(rhs.maxLength)))&&(Double.doubleToLongBits(this.minLength) == Double.doubleToLongBits(rhs.minLength)));
    }

}
