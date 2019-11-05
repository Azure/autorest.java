
package com.azure.autorest.model.codemodel;

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
    private Property discriminatorProperty;
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

    /**
     * a property is a child value in an object
     * 
     */
    public Property getDiscriminatorProperty() {
        return discriminatorProperty;
    }

    /**
     * a property is a child value in an object
     * 
     */
    public void setDiscriminatorProperty(Property discriminatorProperty) {
        this.discriminatorProperty = discriminatorProperty;
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
        sb.append("discriminatorProperty");
        sb.append('=');
        sb.append(((this.discriminatorProperty == null)?"<null>":this.discriminatorProperty));
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
        result = ((result* 31)+((this.discriminatorProperty == null)? 0 :this.discriminatorProperty.hashCode()));
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
        return (((((this.discriminatorProperty == rhs.discriminatorProperty)||((this.discriminatorProperty!= null)&&this.discriminatorProperty.equals(rhs.discriminatorProperty)))&&(Double.doubleToLongBits(this.maxProperties) == Double.doubleToLongBits(rhs.maxProperties)))&&((this.properties == rhs.properties)||((this.properties!= null)&&this.properties.equals(rhs.properties))))&&(Double.doubleToLongBits(this.minProperties) == Double.doubleToLongBits(rhs.minProperties)));
    }

}
