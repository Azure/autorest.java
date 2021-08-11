/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.ClassType;

import java.util.List;

/**
 * Basic info for Fluent samples.
 */
public interface FluentExample {

    /**
     * @return the name of the sample.
     */
    String getName();

    /**
     * @return the type of the entry (usually a {@link com.azure.autorest.fluent.model.clientmodel.FluentManager}).
     */
    ClassType getEntryType();

    /**
     * @return the name of the entry.
     */
    String getEntryName();

    /**
     * @return the description of the entry.
     */
    String getEntryDescription();

    /**
     * @return the list of parameters used in the sample.
     */
    List<ParameterExample> getParameters();
}
