package com.azure.autorest.model.codemodelfour;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * language metadata specific to schema instances
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "namespace",
    "discriminatorValue"
})
public class SchemaMetadata {

    /**
     * namespace of the implementation of this item
     * 
     */
    @JsonProperty("namespace")
    @JsonPropertyDescription("namespace of the implementation of this item")
    private String namespace;
    /**
     * if this is a child of a polymorphic class, this will have the value of the descriminator.
     * 
     */
    @JsonProperty("discriminatorValue")
    @JsonPropertyDescription("if this is a child of a polymorphic class, this will have the value of the descriminator.")
    private String discriminatorValue;

    /**
     * namespace of the implementation of this item
     * 
     */
    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    /**
     * namespace of the implementation of this item
     * 
     */
    @JsonProperty("namespace")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    /**
     * if this is a child of a polymorphic class, this will have the value of the descriminator.
     * 
     */
    @JsonProperty("discriminatorValue")
    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    /**
     * if this is a child of a polymorphic class, this will have the value of the descriminator.
     * 
     */
    @JsonProperty("discriminatorValue")
    public void setDiscriminatorValue(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SchemaMetadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("namespace");
        sb.append('=');
        sb.append(((this.namespace == null)?"<null>":this.namespace));
        sb.append(',');
        sb.append("discriminatorValue");
        sb.append('=');
        sb.append(((this.discriminatorValue == null)?"<null>":this.discriminatorValue));
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
        result = ((result* 31)+((this.namespace == null)? 0 :this.namespace.hashCode()));
        result = ((result* 31)+((this.discriminatorValue == null)? 0 :this.discriminatorValue.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SchemaMetadata) == false) {
            return false;
        }
        SchemaMetadata rhs = ((SchemaMetadata) other);
        return (((this.namespace == rhs.namespace)||((this.namespace!= null)&&this.namespace.equals(rhs.namespace)))&&((this.discriminatorValue == rhs.discriminatorValue)||((this.discriminatorValue!= null)&&this.discriminatorValue.equals(rhs.discriminatorValue))));
    }

}
