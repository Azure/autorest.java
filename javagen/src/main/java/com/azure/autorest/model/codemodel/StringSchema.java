package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a Schema that represents a string value
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "maxLength",
    "minLength",
    "pattern"
})
public class StringSchema {

    /**
     * the maximum length of the string
     * 
     */
    @JsonProperty("maxLength")
    @JsonPropertyDescription("the maximum length of the string")
    private Double maxLength;
    /**
     * the minimum length of the string
     * 
     */
    @JsonProperty("minLength")
    @JsonPropertyDescription("the minimum length of the string")
    private Double minLength;
    /**
     * a regular expression that the string must be validated against
     * 
     */
    @JsonProperty("pattern")
    @JsonPropertyDescription("a regular expression that the string must be validated against")
    private String pattern;

    /**
     * the maximum length of the string
     * 
     */
    @JsonProperty("maxLength")
    public Double getMaxLength() {
        return maxLength;
    }

    /**
     * the maximum length of the string
     * 
     */
    @JsonProperty("maxLength")
    public void setMaxLength(Double maxLength) {
        this.maxLength = maxLength;
    }

    /**
     * the minimum length of the string
     * 
     */
    @JsonProperty("minLength")
    public Double getMinLength() {
        return minLength;
    }

    /**
     * the minimum length of the string
     * 
     */
    @JsonProperty("minLength")
    public void setMinLength(Double minLength) {
        this.minLength = minLength;
    }

    /**
     * a regular expression that the string must be validated against
     * 
     */
    @JsonProperty("pattern")
    public String getPattern() {
        return pattern;
    }

    /**
     * a regular expression that the string must be validated against
     * 
     */
    @JsonProperty("pattern")
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(StringSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("maxLength");
        sb.append('=');
        sb.append(((this.maxLength == null)?"<null>":this.maxLength));
        sb.append(',');
        sb.append("minLength");
        sb.append('=');
        sb.append(((this.minLength == null)?"<null>":this.minLength));
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
        result = ((result* 31)+((this.maxLength == null)? 0 :this.maxLength.hashCode()));
        result = ((result* 31)+((this.minLength == null)? 0 :this.minLength.hashCode()));
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
        return ((((this.pattern == rhs.pattern)||((this.pattern!= null)&&this.pattern.equals(rhs.pattern)))&&((this.maxLength == rhs.maxLength)||((this.maxLength!= null)&&this.maxLength.equals(rhs.maxLength))))&&((this.minLength == rhs.minLength)||((this.minLength!= null)&&this.minLength.equals(rhs.minLength))));
    }

}
