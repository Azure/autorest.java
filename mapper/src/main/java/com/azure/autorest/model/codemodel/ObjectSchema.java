package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a schema that represents a type with child properties.
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "discriminator",
    "properties",
    "maxProperties",
    "minProperties"
})
public class ObjectSchema {

    /**
     * Disciminator for polymorphic class hierarchy
     * 
     */
    @JsonProperty("discriminator")
    @JsonPropertyDescription("Disciminator for polymorphic class hierarchy")
    private Discriminator discriminator;
    /**
     * the collection of properties that are in this object
     * 
     */
    @JsonProperty("properties")
    @JsonPropertyDescription("the collection of properties that are in this object")
    private List<Property> properties = new ArrayList<Property>();
    /**
     * maximum number of properties permitted
     * 
     */
    @JsonProperty("maxProperties")
    @JsonPropertyDescription("maximum number of properties permitted")
    private Double maxProperties;
    /**
     * minimum number of properties permitted
     * 
     */
    @JsonProperty("minProperties")
    @JsonPropertyDescription("minimum number of properties permitted")
    private Double minProperties;

    /**
     * Disciminator for polymorphic class hierarchy
     * 
     */
    @JsonProperty("discriminator")
    public Discriminator getDiscriminator() {
        return discriminator;
    }

    /**
     * Disciminator for polymorphic class hierarchy
     * 
     */
    @JsonProperty("discriminator")
    public void setDiscriminator(Discriminator discriminator) {
        this.discriminator = discriminator;
    }

    /**
     * the collection of properties that are in this object
     * 
     */
    @JsonProperty("properties")
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * the collection of properties that are in this object
     * 
     */
    @JsonProperty("properties")
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    /**
     * maximum number of properties permitted
     * 
     */
    @JsonProperty("maxProperties")
    public Double getMaxProperties() {
        return maxProperties;
    }

    /**
     * maximum number of properties permitted
     * 
     */
    @JsonProperty("maxProperties")
    public void setMaxProperties(Double maxProperties) {
        this.maxProperties = maxProperties;
    }

    /**
     * minimum number of properties permitted
     * 
     */
    @JsonProperty("minProperties")
    public Double getMinProperties() {
        return minProperties;
    }

    /**
     * minimum number of properties permitted
     * 
     */
    @JsonProperty("minProperties")
    public void setMinProperties(Double minProperties) {
        this.minProperties = minProperties;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ObjectSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("discriminator");
        sb.append('=');
        sb.append(((this.discriminator == null)?"<null>":this.discriminator));
        sb.append(',');
        sb.append("properties");
        sb.append('=');
        sb.append(((this.properties == null)?"<null>":this.properties));
        sb.append(',');
        sb.append("maxProperties");
        sb.append('=');
        sb.append(((this.maxProperties == null)?"<null>":this.maxProperties));
        sb.append(',');
        sb.append("minProperties");
        sb.append('=');
        sb.append(((this.minProperties == null)?"<null>":this.minProperties));
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
        result = ((result* 31)+((this.maxProperties == null)? 0 :this.maxProperties.hashCode()));
        result = ((result* 31)+((this.properties == null)? 0 :this.properties.hashCode()));
        result = ((result* 31)+((this.discriminator == null)? 0 :this.discriminator.hashCode()));
        result = ((result* 31)+((this.minProperties == null)? 0 :this.minProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ObjectSchema) == false) {
            return false;
        }
        ObjectSchema rhs = ((ObjectSchema) other);
        return (((((this.maxProperties == rhs.maxProperties)||((this.maxProperties!= null)&&this.maxProperties.equals(rhs.maxProperties)))&&((this.properties == rhs.properties)||((this.properties!= null)&&this.properties.equals(rhs.properties))))&&((this.discriminator == rhs.discriminator)||((this.discriminator!= null)&&this.discriminator.equals(rhs.discriminator))))&&((this.minProperties == rhs.minProperties)||((this.minProperties!= null)&&this.minProperties.equals(rhs.minProperties))));
    }

}
