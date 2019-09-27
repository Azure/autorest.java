package com.azure.autorest.model.codemodelfour;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * an operation group represents a container around set of operations
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "$key",
    "operations"
})
public class OperationGroup {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    private String $key;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("operations")
    private List<Operation> operations = new ArrayList<Operation>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public String get$key() {
        return $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("$key")
    public void set$key(String $key) {
        this.$key = $key;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("operations")
    public List<Operation> getOperations() {
        return operations;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("operations")
    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(OperationGroup.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("$key");
        sb.append('=');
        sb.append(((this.$key == null)?"<null>":this.$key));
        sb.append(',');
        sb.append("operations");
        sb.append('=');
        sb.append(((this.operations == null)?"<null>":this.operations));
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
        result = ((result* 31)+((this.$key == null)? 0 :this.$key.hashCode()));
        result = ((result* 31)+((this.operations == null)? 0 :this.operations.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof OperationGroup) == false) {
            return false;
        }
        OperationGroup rhs = ((OperationGroup) other);
        return (((this.$key == rhs.$key)||((this.$key!= null)&&this.$key.equals(rhs.$key)))&&((this.operations == rhs.operations)||((this.operations!= null)&&this.operations.equals(rhs.operations))));
    }

}
