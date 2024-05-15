// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.parameters.spread.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;

/**
 * Options for spreadWithMultipleParameters API.
 */
@Immutable
public final class SpreadWithMultipleParametersOptions {
    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String id;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String xMsTestHeader;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop1;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop2;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop3;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop4;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop5;

    /*
     * A sequence of textual characters.
     */
    @Generated
    private final String prop6;

    /**
     * Creates an instance of SpreadWithMultipleParametersOptions class.
     * 
     * @param id the id value to set.
     * @param xMsTestHeader the xMsTestHeader value to set.
     * @param prop1 the prop1 value to set.
     * @param prop2 the prop2 value to set.
     * @param prop3 the prop3 value to set.
     * @param prop4 the prop4 value to set.
     * @param prop5 the prop5 value to set.
     * @param prop6 the prop6 value to set.
     */
    @Generated
    public SpreadWithMultipleParametersOptions(String id, String xMsTestHeader, String prop1, String prop2,
        String prop3, String prop4, String prop5, String prop6) {
        this.id = id;
        this.xMsTestHeader = xMsTestHeader;
        this.prop1 = prop1;
        this.prop2 = prop2;
        this.prop3 = prop3;
        this.prop4 = prop4;
        this.prop5 = prop5;
        this.prop6 = prop6;
    }

    /**
     * Get the id property: A sequence of textual characters.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the xMsTestHeader property: A sequence of textual characters.
     * 
     * @return the xMsTestHeader value.
     */
    @Generated
    public String getXMsTestHeader() {
        return this.xMsTestHeader;
    }

    /**
     * Get the prop1 property: A sequence of textual characters.
     * 
     * @return the prop1 value.
     */
    @Generated
    public String getProp1() {
        return this.prop1;
    }

    /**
     * Get the prop2 property: A sequence of textual characters.
     * 
     * @return the prop2 value.
     */
    @Generated
    public String getProp2() {
        return this.prop2;
    }

    /**
     * Get the prop3 property: A sequence of textual characters.
     * 
     * @return the prop3 value.
     */
    @Generated
    public String getProp3() {
        return this.prop3;
    }

    /**
     * Get the prop4 property: A sequence of textual characters.
     * 
     * @return the prop4 value.
     */
    @Generated
    public String getProp4() {
        return this.prop4;
    }

    /**
     * Get the prop5 property: A sequence of textual characters.
     * 
     * @return the prop5 value.
     */
    @Generated
    public String getProp5() {
        return this.prop5;
    }

    /**
     * Get the prop6 property: A sequence of textual characters.
     * 
     * @return the prop6 value.
     */
    @Generated
    public String getProp6() {
        return this.prop6;
    }
}
