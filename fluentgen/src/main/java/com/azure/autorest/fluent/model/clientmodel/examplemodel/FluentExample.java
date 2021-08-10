/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.examplemodel;

import com.azure.autorest.model.clientmodel.ClassType;

import java.util.List;

public interface FluentExample {

    String getName();

    ClassType getEntryType();

    String getEntryName();

    String getEntryDescription();

    List<ParameterExample> getParameters();
}
