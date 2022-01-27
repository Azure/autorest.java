// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * a schema that represents a choice of several values (ie, an 'enum')
 * 
 */
public class ChoiceSchema extends ValueSchema {

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    private Schema choiceType;
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
    public Schema getChoiceType() {
        return choiceType;
    }

    /**
     * a Schema that represents a string value
     * (Required)
     * 
     */
    public void setChoiceType(Schema choiceType) {
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
        sb.append(ChoiceSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
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
        result = ((result* 31)+((this.getLanguage().getJava().getName() == null)? 0 :this.getLanguage().getJava().getName().hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof ChoiceSchema) == false) {
            return false;
        }
        ChoiceSchema rhs = ((ChoiceSchema) other);
        return Objects.equals(this.choiceType, rhs.choiceType) && Objects.equals(this.choices, rhs.choices)
                && Objects.equals(this.getLanguage().getJava().getName(), rhs.getLanguage().getJava().getName());
    }

}
