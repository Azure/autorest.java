
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
}
