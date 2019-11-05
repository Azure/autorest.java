
package com.azure.autorest.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * a schema that represents a choice of several values (ie, an 'enum')
 * 
 */
public class SealedChoiceSchema extends ValueSchema {

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    private StringSchema choiceType;
    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    private List<ChoiceValue> choices = new ArrayList<ChoiceValue>();

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    public StringSchema getChoiceType() {
        return choiceType;
    }

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    public void setChoiceType(StringSchema choiceType) {
        this.choiceType = choiceType;
    }

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    public List<ChoiceValue> getChoices() {
        return choices;
    }

    /**
     * the possible choices for in the set
     * (Required)
     * 
     */
    public void setChoices(List<ChoiceValue> choices) {
        this.choices = choices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(SealedChoiceSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("choiceType");
        sb.append('=');
        sb.append(((this.choiceType == null)?"<null>":this.choiceType));
        sb.append(',');
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
        result = ((result* 31)+((this.choiceType == null)? 0 :this.choiceType.hashCode()));
        result = ((result* 31)+((this.choices == null)? 0 :this.choices.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof SealedChoiceSchema) == false) {
            return false;
        }
        SealedChoiceSchema rhs = ((SealedChoiceSchema) other);
        return (((this.choiceType == rhs.choiceType)||((this.choiceType!= null)&&this.choiceType.equals(rhs.choiceType)))&&((this.choices == rhs.choices)||((this.choices!= null)&&this.choices.equals(rhs.choices))));
    }

}
