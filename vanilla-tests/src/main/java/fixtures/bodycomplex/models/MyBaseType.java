// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package fixtures.bodycomplex.models;

import com.azure.core.annotation.Immutable;
import com.azure.core.annotation.JsonFlatten;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * The MyBaseType model.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "kind",
    defaultImpl = MyBaseType.class)
@JsonTypeName("MyBaseType")
@JsonSubTypes({ @JsonSubTypes.Type(name = "Kind1", value = MyDerivedType.class) })
@JsonFlatten
@Immutable
public class MyBaseType {
    /*
     * The propB1 property.
     */
    @JsonProperty(value = "propB1")
    private String propB1;

    /*
     * The propBH1 property.
     */
    @JsonProperty(value = "helper.propBH1")
    private String propBH1;

    /**
     * Creates an instance of MyBaseType class.
     */
    protected MyBaseType() {}

    /**
     * Get the propB1 property: The propB1 property.
     * 
     * @return the propB1 value.
     */
    public String getPropB1() {
        return this.propB1;
    }

    /**
     * Get the propBH1 property: The propBH1 property.
     * 
     * @return the propBH1 value.
     */
    public String getPropBH1() {
        return this.propBH1;
    }

    /**
     * Validates the instance.
     * 
     * @throws IllegalArgumentException thrown if the instance is not valid.
     */
    public void validate() {}
}
