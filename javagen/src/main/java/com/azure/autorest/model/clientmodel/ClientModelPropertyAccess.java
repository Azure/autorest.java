/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.model.clientmodel;

import java.util.Set;

/**
 * Access to the client model property.
 */
public interface ClientModelPropertyAccess {

    String getName();

    String getDescription();

    String getGetterName();

    String getSetterName();

    IType getClientType();

    boolean getIsReadOnly();

    boolean getIsReadOnlyForCreate();

    boolean getIsReadOnlyForUpdate();

    boolean isRequired();

    boolean getIsConstant();

    void addImportsTo(Set<String> imports, boolean shouldGenerateXmlSerialization);
}
