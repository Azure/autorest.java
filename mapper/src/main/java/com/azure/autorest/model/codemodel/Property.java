package com.azure.autorest.model.codemodel;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * a property is a child value in an object
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "readOnly",
    "serializedName"
})
public class Property {

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    @JsonProperty("readOnly")
    @JsonPropertyDescription("if the property is marked read-only (ie, not intended to be sent to the service)")
    private Boolean readOnly;
    /**
     * the wire name of this property
     * (Required)
     * 
     */
    @JsonProperty("serializedName")
    @JsonPropertyDescription("the wire name of this property")
    private String serializedName;

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    @JsonProperty("readOnly")
    public Boolean getReadOnly() {
        return readOnly;
    }

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    @JsonProperty("readOnly")
    public void setReadOnly(Boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * the wire name of this property
     * (Required)
     * 
     */
    @JsonProperty("serializedName")
    public String getSerializedName() {
        return serializedName;
    }

    /**
     * the wire name of this property
     * (Required)
     * 
     */
    @JsonProperty("serializedName")
    public void setSerializedName(String serializedName) {
        this.serializedName = serializedName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Property.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("readOnly");
        sb.append('=');
        sb.append(((this.readOnly == null)?"<null>":this.readOnly));
        sb.append(',');
        sb.append("serializedName");
        sb.append('=');
        sb.append(((this.serializedName == null)?"<null>":this.serializedName));
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
        result = ((result* 31)+((this.serializedName == null)? 0 :this.serializedName.hashCode()));
        result = ((result* 31)+((this.readOnly == null)? 0 :this.readOnly.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Property) == false) {
            return false;
        }
        Property rhs = ((Property) other);
        return (((this.serializedName == rhs.serializedName)||((this.serializedName!= null)&&this.serializedName.equals(rhs.serializedName)))&&((this.readOnly == rhs.readOnly)||((this.readOnly!= null)&&this.readOnly.equals(rhs.readOnly))));
    }

}
