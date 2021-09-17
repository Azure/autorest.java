
package com.azure.autorest.extension.base.model.codemodel;


import java.util.List;

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

    // internal use, not from modelerfour
    private ObjectSchema parentSchema;

    /**
     * When a property is flattened, the property will be the set of serialized names to get to that target property.
     *
     * If flattenedName is present, then this property is a flattened property.
     *
     * (ie, ['properties','name'] )
     */
    private List<String> flattenedNames;

    private List<Parameter> originalParameter;

    private String clientDefaultValue;

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

    /**
     * When a property is flattened, the property will be the set of serialized names to get to that target property.
     *
     * If flattenedName is present, then this property is a flattened property.
     *
     * (ie, ['properties','name'] )
     */
    public List<String> getFlattenedNames() {
        return flattenedNames;
    }

    /**
     * When a property is flattened, the property will be the set of serialized names to get to that target property.
     *
     * If flattenedName is present, then this property is a flattened property.
     *
     * (ie, ['properties','name'] )
     */
    public void setFlattenedNames(List<String> flattenedNames) {
        this.flattenedNames = flattenedNames;
    }

    public ObjectSchema getParentSchema() {
        return parentSchema;
    }

    public void setParentSchema(ObjectSchema parentSchema) {
        this.parentSchema = parentSchema;
    }

    public List<Parameter> getOriginalParameter() {
        return originalParameter;
    }

    public void setOriginalParameter(List<Parameter> originalParameter) {
        this.originalParameter = originalParameter;
    }

    public String getClientDefaultValue() {
        if (clientDefaultValue == null) {
            if (this.getSchema() != null && this.getSchema() instanceof ConstantSchema) {
                ConstantSchema constantSchema = (ConstantSchema) this.getSchema();
                if (constantSchema.getValue() != null) {
                    this.setClientDefaultValue(constantSchema.getValue().getValue().toString());
                }
            }
        }
        return clientDefaultValue;
    }

    public void setClientDefaultValue(String clientDefaultValue) {
        this.clientDefaultValue = clientDefaultValue;
    }
}
