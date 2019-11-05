
package com.azure.autorest.model.codemodel;



/**
 * a container for the actual constant value
 * 
 */
public class ConstantValue {

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    private Languages language;
    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    private Object value;
    private DictionaryAny extensions;

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    public Languages getLanguage() {
        return language;
    }

    /**
     * custom extensible metadata for individual language generators
     * (Required)
     * 
     */
    public void setLanguage(Languages language) {
        this.language = language;
    }

    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    public Object getValue() {
        return value;
    }

    /**
     * the actual constant value to use
     * (Required)
     * 
     */
    public void setValue(Object value) {
        this.value = value;
    }

    public DictionaryAny getExtensions() {
        return extensions;
    }

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
