
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * a schema that represents a type with child properties.
 * 
 */
public class ObjectSchema extends ComplexSchema {

    /**
     * a property is a child value in an object
     * 
     */
    private Property discriminator;
    /**
     * the collection of properties that are in this object
     * 
     */
    private List<Property> properties = new ArrayList<Property>();
    /**
     * maximum number of properties permitted
     * 
     */
    private double maxProperties;
    /**
     * minimum number of properties permitted
     * 
     */
    private double minProperties;

    private Relations parents;

    private Relations children;

    private String discriminatorValue;

    /**
     * a property is a child value in an object
     * 
     */
    public Property getDiscriminator() {
        return discriminator;
    }

    /**
     * a property is a child value in an object
     * 
     */
    public void setDiscriminator(Property discriminator) {
        this.discriminator = discriminator;
    }

    /**
     * the collection of properties that are in this object
     * 
     */
    public List<Property> getProperties() {
        return properties;
    }

    /**
     * the collection of properties that are in this object
     * 
     */
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    /**
     * maximum number of properties permitted
     * 
     */
    public double getMaxProperties() {
        return maxProperties;
    }

    /**
     * maximum number of properties permitted
     * 
     */
    public void setMaxProperties(double maxProperties) {
        this.maxProperties = maxProperties;
    }

    /**
     * minimum number of properties permitted
     * 
     */
    public double getMinProperties() {
        return minProperties;
    }

    /**
     * minimum number of properties permitted
     * 
     */
    public void setMinProperties(double minProperties) {
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
        sb.append(this.maxProperties);
        sb.append(',');
        sb.append("minProperties");
        sb.append('=');
        sb.append(this.minProperties);
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
        result = ((result* 31)+((this.discriminator == null)? 0 :this.discriminator.hashCode()));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.maxProperties)^(Double.doubleToLongBits(this.maxProperties)>>> 32))));
        result = ((result* 31)+((this.properties == null)? 0 :this.properties.hashCode()));
        result = ((result* 31)+((int)(Double.doubleToLongBits(this.minProperties)^(Double.doubleToLongBits(this.minProperties)>>> 32))));
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
        return (((((this.discriminator == rhs.discriminator)||((this.discriminator != null)&&this.discriminator.equals(rhs.discriminator)))&&(Double.doubleToLongBits(this.maxProperties) == Double.doubleToLongBits(rhs.maxProperties)))&&((this.properties == rhs.properties)||((this.properties!= null)&&this.properties.equals(rhs.properties))))&&(Double.doubleToLongBits(this.minProperties) == Double.doubleToLongBits(rhs.minProperties)));
    }

    public Relations getParents() {
        return parents;
    }

    public void setParents(Relations parents) {
        this.parents = parents;
    }

    public Relations getChildren() {
        return children;
    }

    public void setChildren(Relations children) {
        this.children = children;
    }

    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    public void setDiscriminatorValue(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }
}
