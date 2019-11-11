
package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;

public class FlagSchema extends ValueSchema {

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    private List<FlagValue> choices = new ArrayList<FlagValue>();

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    public List<FlagValue> getChoices() {
        return choices;
    }

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    public void setChoices(List<FlagValue> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(FlagSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("choices");
        sb.append('=');
        sb.append(((this.choices == null)?"<null>":this.choices));
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
        result = ((result* 31)+((this.choices == null)? 0 :this.choices.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof FlagSchema) == false) {
            return false;
        }
        FlagSchema rhs = ((FlagSchema) other);
        return ((this.choices == rhs.choices)||((this.choices!= null)&&this.choices.equals(rhs.choices)));
    }

}
