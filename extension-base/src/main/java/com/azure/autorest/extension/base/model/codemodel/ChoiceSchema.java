// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * a schema that represents a choice of several values (ie, an 'enum')
 */
public class ChoiceSchema extends ValueSchema {

    /**
     * a Schema that represents a string value (Required)
     */
    private Schema choiceType;
    /**
     * the possible choices for in the set (Required)
     */
    private List<ChoiceValue> choices = new ArrayList<>();
    private String summary;

    /**
     * a Schema that represents a string value (Required)
     */
    public Schema getChoiceType() {
        return choiceType;
    }

    /**
     * a Schema that represents a string value (Required)
     */
    public void setChoiceType(Schema choiceType) {
        this.choiceType = choiceType;
    }

    /**
     * the possible choices for in the set (Required)
     */
    public List<ChoiceValue> getChoices() {
        return choices;
    }

    /**
     * the possible choices for in the set (Required)
     */
    public void setChoices(List<ChoiceValue> choices) {
        this.choices = choices;
    }

    @Override
    public String getSummary() {
        return summary;
    }

    @Override
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return sharedToString(this, ChoiceSchema.class.getName());
    }

    static String sharedToString(ChoiceSchema value, String className) {
        StringBuilder sb = new StringBuilder();
        sb.append(className).append('@').append(Integer.toHexString(System.identityHashCode(value))).append('[');
        sb.append("choiceType=");
        sb.append(((value.choiceType == null) ? "<null>" : value.choiceType));
        sb.append(",choices=");
        sb.append(((value.choices == null) ? "<null>" : value.choices));
        sb.append(',');
        if (sb.charAt((sb.length() - 1)) == ',') {
            sb.setCharAt((sb.length() - 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return sharedHashCode(this);
    }

    static int sharedHashCode(ChoiceSchema value) {
        int result = 1;
        result = ((result * 31) + Objects.hashCode(value.choiceType));
        result = ((result * 31) + Objects.hashCode(value.choices));
        result = ((result * 31) + Objects.hashCode(value.getLanguage().getJava().getName()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ChoiceSchema)) {
            return false;
        }

        return sharedEquals(this, (ChoiceSchema) other);
    }

    static boolean sharedEquals(ChoiceSchema lhs, ChoiceSchema rhs) {
        return Objects.equals(lhs.choiceType, rhs.choiceType)
            && Objects.equals(lhs.choices, rhs.choices)
            && Objects.equals(lhs.getLanguage().getJava().getName(), rhs.getLanguage().getJava().getName());
    }
}
