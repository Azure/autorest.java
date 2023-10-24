// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.Collection;

/**
 * State of the selection mark.
 */
public final class SelectionMarkState extends ExpandableStringEnum<SelectionMarkState> {
    /**
     * Static value selected for SelectionMarkState.
     */
    public static final SelectionMarkState SELECTED = fromString("selected");

    /**
     * Static value unselected for SelectionMarkState.
     */
    public static final SelectionMarkState UNSELECTED = fromString("unselected");

    /**
     * Creates a new instance of SelectionMarkState value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public SelectionMarkState() {}

    /**
     * Creates or finds a SelectionMarkState from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding SelectionMarkState.
     */
    @JsonCreator
    public static SelectionMarkState fromString(String name) {
        return fromString(name, SelectionMarkState.class);
    }

    /**
     * Gets known SelectionMarkState values.
     * 
     * @return known SelectionMarkState values.
     */
    public static Collection<SelectionMarkState> values() {
        return values(SelectionMarkState.class);
    }
}
