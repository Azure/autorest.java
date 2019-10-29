package com.azure.autorest.model.codemodel;

import com.azure.autorest.model.extensionmodel.XmsExtensions;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Map;


/**
 * Disciminator for polymorphic class hierarchy
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "propertyName",
    "mapping",
    "extensions"
})
public class Discriminator {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("propertyName")
    private String propertyName;
    @JsonProperty("mapping")
    private Map<String, String> mapping;
    @JsonProperty("extensions")
    private XmsExtensions extensions;

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("propertyName")
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("propertyName")
    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    @JsonProperty("mapping")
    public Map<String, String> getMapping() {
        return mapping;
    }

    @JsonProperty("mapping")
    public void setMapping(Map<String, String> mapping) {
        this.mapping = mapping;
    }

    @JsonProperty("extensions")
    public XmsExtensions getExtensions() {
        return extensions;
    }

    @JsonProperty("extensions")
    public void setExtensions(XmsExtensions extensions) {
        this.extensions = extensions;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Discriminator.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("propertyName");
        sb.append('=');
        sb.append(((this.propertyName == null)?"<null>":this.propertyName));
        sb.append(',');
        sb.append("mapping");
        sb.append('=');
        sb.append(((this.mapping == null)?"<null>":this.mapping));
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
        result = ((result* 31)+((this.mapping == null)? 0 :this.mapping.hashCode()));
        result = ((result* 31)+((this.extensions == null)? 0 :this.extensions.hashCode()));
        result = ((result* 31)+((this.propertyName == null)? 0 :this.propertyName.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Discriminator) == false) {
            return false;
        }
        Discriminator rhs = ((Discriminator) other);
        return ((((this.mapping == rhs.mapping)||((this.mapping!= null)&&this.mapping.equals(rhs.mapping)))&&((this.extensions == rhs.extensions)||((this.extensions!= null)&&this.extensions.equals(rhs.extensions))))&&((this.propertyName == rhs.propertyName)||((this.propertyName!= null)&&this.propertyName.equals(rhs.propertyName))));
    }

}
