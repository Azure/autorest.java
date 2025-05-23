// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The MyDerivedType model.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "kind", defaultImpl = MyDerivedType.class, visible = true)
@JsonTypeName("Kind1")
@Immutable
public final class MyDerivedType extends MyBaseType {
    /*
     * The kind property.
     */
    @Generated
    @JsonTypeId
    @JsonProperty(value = "kind", required = true)
    private MyKind kind = MyKind.KIND1;

    /*
     * The propD1 property.
     */
    @Generated
    @JsonProperty(value = "propD1")
    private String propD1;

    /**
     * Creates an instance of MyDerivedType class.
     */
    @Generated
    private MyDerivedType() {
    }

    /**
     * Get the kind property: The kind property.
     * 
     * @return the kind value.
     */
    @Generated
    @Override
    public MyKind getKind() {
        return this.kind;
    }

    /**
     * Get the propD1 property: The propD1 property.
     * 
     * @return the propD1 value.
     */
    @Generated
    public String getPropD1() {
        return this.propD1;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    @Override
    public void validate() {
        super.validate();
    }
}
