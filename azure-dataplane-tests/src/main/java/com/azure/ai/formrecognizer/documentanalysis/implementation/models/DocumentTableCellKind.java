// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) AutoRest Code Generator.

package com.azure.ai.formrecognizer.documentanalysis.implementation.models;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Table cell kind.
 */
public final class DocumentTableCellKind extends ExpandableStringEnum<DocumentTableCellKind> {
    /**
     * Static value content for DocumentTableCellKind.
     */
    @Generated
    public static final DocumentTableCellKind CONTENT = fromString("content");

    /**
     * Static value rowHeader for DocumentTableCellKind.
     */
    @Generated
    public static final DocumentTableCellKind ROW_HEADER = fromString("rowHeader");

    /**
     * Static value columnHeader for DocumentTableCellKind.
     */
    @Generated
    public static final DocumentTableCellKind COLUMN_HEADER = fromString("columnHeader");

    /**
     * Static value stubHead for DocumentTableCellKind.
     */
    @Generated
    public static final DocumentTableCellKind STUB_HEAD = fromString("stubHead");

    /**
     * Static value description for DocumentTableCellKind.
     */
    @Generated
    public static final DocumentTableCellKind DESCRIPTION = fromString("description");

    /**
     * Creates a new instance of DocumentTableCellKind value.
     * 
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public DocumentTableCellKind() {
    }

    /**
     * Creates or finds a DocumentTableCellKind from its string representation.
     * 
     * @param name a name to look for.
     * @return the corresponding DocumentTableCellKind.
     */
    @Generated
    public static DocumentTableCellKind fromString(String name) {
        return fromString(name, DocumentTableCellKind.class);
    }

    /**
     * Gets known DocumentTableCellKind values.
     * 
     * @return known DocumentTableCellKind values.
     */
    @Generated
    public static Collection<DocumentTableCellKind> values() {
        return values(DocumentTableCellKind.class);
    }
}
