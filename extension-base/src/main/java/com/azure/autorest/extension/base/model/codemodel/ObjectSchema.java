
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
    private Discriminator discriminator;
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
    public Discriminator getDiscriminator() {
        return discriminator;
    }

    /**
     * a property is a child value in an object
     * 
     */
    public void setDiscriminator(Discriminator discriminator) {
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
