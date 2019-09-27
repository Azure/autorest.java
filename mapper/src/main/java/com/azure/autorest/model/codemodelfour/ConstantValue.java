package com.azure.autorest.model.codemodelfour;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a container for the actual constant value
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "language",
    "value",
    "extensions"
})
public class ConstantValue {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    @JsonPropertyDescription("custom extensible metadata for individual language generators")
    private Languages language;
    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    @JsonProperty("value")
    @JsonPropertyDescription("the actual constant value to use")
    private Object value;
    @JsonProperty("extensions")
    private DictionaryAny extensions;

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public Languages getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    @JsonProperty("language")
    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    @JsonProperty("value")
    public Object getValue() {
        return value;
    }

    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    @JsonProperty("value")
    public void setValue(Object value) {
        this.value = value;
    }

    @JsonProperty("extensions")
    public DictionaryAny getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(DictionaryAny extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantValue.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("language");
        sb.append('=');
        sb.append(((this.language == null)?"<null>":this.language));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
        sb.append(',');
        sb.append("extensions");
        sb.append('=');
        sb.append(((this.extensions == null)?"<null>":this.extensions));
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
        result = ((result* 31)+((this.language == null)? 0 :this.language.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConstantValue) == false) {
            return false;
        }
        ConstantValue rhs = ((ConstantValue) other);
        return ((((this.language == rhs.language)||((this.language!= null)&&this.language.equals(rhs.language)))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
    }

}
