// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.model.codemodel;

import java.util.ArrayList;
import java.util.List;


/**
 * an AND relationship between several schemas
 * 
 */
public class AndSchema extends ComplexSchema {

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    private List<ComplexSchema> allOf = new ArrayList<ComplexSchema>();
    private String discriminatorValue;

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    public List<ComplexSchema> getAllOf() {
        return allOf;
    }

    /**
     * the set of schemas that this schema is composed of.
     * (Required)
     * 
     */
    public void setAllOf(List<ComplexSchema> allOf) {
        this.allOf = allOf;
    }

    public String getDiscriminatorValue() {
        return discriminatorValue;
    }

    public void setDiscriminatorValue(String discriminatorValue) {
        this.discriminatorValue = discriminatorValue;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AndSchema.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("allOf");
        sb.append('=');
        sb.append(((this.allOf == null)?"<null>":this.allOf));
        sb.append(',');
        sb.append("discriminatorValue");
        sb.append('=');
        sb.append(((this.discriminatorValue == null)?"<null>":this.discriminatorValue));
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
        result = ((result* 31)+((this.allOf == null)? 0 :this.allOf.hashCode()));
        result = ((result* 31)+((this.discriminatorValue == null)? 0 :this.discriminatorValue.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AndSchema) == false) {
            return false;
        }
        AndSchema rhs = ((AndSchema) other);
        return (((this.allOf == rhs.allOf)||((this.allOf!= null)&&this.allOf.equals(rhs.allOf)))&&((this.discriminatorValue == rhs.discriminatorValue)||((this.discriminatorValue!= null)&&this.discriminatorValue.equals(rhs.discriminatorValue))));
    }

}
