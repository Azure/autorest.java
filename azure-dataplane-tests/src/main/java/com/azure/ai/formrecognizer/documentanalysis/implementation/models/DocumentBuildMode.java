// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Custom document model build mode.
 */
public final class DocumentBuildMode extends ExpandableStringEnum<DocumentBuildMode> {
    /**
     * Static value template for DocumentBuildMode.
     */
    public static final DocumentBuildMode TEMPLATE = fromString("template");

    /**
     * Static value neural for DocumentBuildMode.
     */
    public static final DocumentBuildMode NEURAL = fromString("neural");

    /**
     * Creates a new instance of DocumentBuildMode value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public DocumentBuildMode() {
    }

    /**
     * Creates or finds a DocumentBuildMode from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding DocumentBuildMode.
     */
    public static DocumentBuildMode fromString(String name) {
        return fromString(name, DocumentBuildMode.class);
    }

    /**
     * Gets known DocumentBuildMode values.
     * 
     * @return known DocumentBuildMode values.
     */
    public static Collection<DocumentBuildMode> values() {
        return values(DocumentBuildMode.class);
    }
}
