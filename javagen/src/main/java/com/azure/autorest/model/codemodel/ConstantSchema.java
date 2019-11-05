
package com.azure.autorest.model.codemodel;



/**
 * a schema that represents a constant value
 * 
 */
public class ConstantSchema extends Schema {

    /**
     * 
     * (Required)
     * 
     */
    private Schema constantSchema;
    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    private ConstantValue value;

    /**
     * 
     * (Required)
     * 
     */
    public Schema getConstantSchema() {
        return constantSchema;
    }

    /**
     * 
     * (Required)
     * 
     */
    public void setConstantSchema(Schema constantSchema) {
        this.constantSchema = constantSchema;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    public ConstantValue getValue() {
        return value;
    }

    /**
     * a container for the actual constant value
     * (Required)
     * 
     */
    public void setValue(ConstantValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(ConstantSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("constantSchema");
        sb.append('=');
        sb.append(((this.constantSchema == null)?"<null>":this.constantSchema));
        sb.append(',');
        sb.append("value");
        sb.append('=');
        sb.append(((this.value == null)?"<null>":this.value));
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
        result = ((result* 31)+((this.constantSchema == null)? 0 :this.constantSchema.hashCode()));
        result = ((result* 31)+((this.value == null)? 0 :this.value.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ConstantSchema) == false) {
            return false;
        }
        ConstantSchema rhs = ((ConstantSchema) other);
        return (((this.constantSchema == rhs.constantSchema)||((this.constantSchema!= null)&&this.constantSchema.equals(rhs.constantSchema)))&&((this.value == rhs.value)||((this.value!= null)&&this.value.equals(rhs.value))));
    }

}
