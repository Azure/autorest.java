
package com.azure.autorest.extension.base.model.codemodel;



/**
 * a property is a child value in an object
 * 
 */
public class Property extends Value {

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    private boolean readOnly;
    /**
     * the wire name of this property
     * (Required)
     * 
     */
    private String serializedName;
    /**
     * if this property is used as a discriminator for a polymorphic type
     * 
     */
    private boolean isDiscriminator;

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * if the property is marked read-only (ie, not intended to be sent to the service)
     * 
     */
    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    /**
     * the wire name of this property
     * (Required)
     * 
     */
    public String getSerializedName() {
        return serializedName;
    }

    /**
     * the wire name of this property
     * (Required)
     * 
     */
    public void setSerializedName(String serializedName) {
        this.serializedName = serializedName;
    }

    /**
     * if this property is used as a discriminator for a polymorphic type
     * 
     */
    public boolean isIsDiscriminator() {
        return isDiscriminator;
    }

    /**
     * if this property is used as a discriminator for a polymorphic type
     * 
     */
    public void setIsDiscriminator(boolean isDiscriminator) {
        this.isDiscriminator = isDiscriminator;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Property.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("readOnly");
        sb.append('=');
        sb.append(this.readOnly);
        sb.append(',');
        sb.append("serializedName");
        sb.append('=');
        sb.append(((this.serializedName == null)?"<null>":this.serializedName));
        sb.append(',');
        sb.append("isDiscriminator");
        sb.append('=');
        sb.append(this.isDiscriminator);
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
        result = ((result* 31)+(this.isDiscriminator? 1 : 0));
        result = ((result* 31)+(this.readOnly? 1 : 0));
        result = ((result* 31)+((this.serializedName == null)? 0 :this.serializedName.hashCode()));
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
        return (((this.isDiscriminator == rhs.isDiscriminator)&&(this.readOnly == rhs.readOnly))&&((this.serializedName == rhs.serializedName)||((this.serializedName!= null)&&this.serializedName.equals(rhs.serializedName))));
    }

}
