/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;

public class FluentModelProperty {

    private final ClientModelProperty clientModelProperty;

    private final IType clientType;

    public FluentModelProperty(ClientModelProperty clientModelProperty) {
        this.clientModelProperty = clientModelProperty;
        this.clientType = getWrapperType(clientModelProperty.getClientType());
    }

    public String getName() {
        return clientModelProperty.getName();
    }

    public String getDescription() {
        return clientModelProperty.getDescription();
    }

    public final IType getClientType() {
        return clientType;
    }

    private static IType getWrapperType(IType clientType) {
        IType wrapperType = clientType;
        if (clientType instanceof ClassType) {
            ClassType type = (ClassType) clientType;
            if (FluentUtils.isInnerClassType(type)) {
                wrapperType = FluentUtils.resourceModelInterfaceClassType(type);
            }
        } else if (clientType instanceof ListType) {
            ListType type = (ListType) clientType;
            IType wrapperElementType = getWrapperType(type.getElementType());
            wrapperType = wrapperElementType == type.getElementType() ? type : new ListType(wrapperElementType);
        } else if (clientType instanceof MapType) {
            MapType type = (MapType) clientType;
            IType wrapperElementType = getWrapperType(type.getValueType());
            wrapperType = wrapperElementType == type.getValueType() ? type : new MapType(wrapperElementType);
        }
        return wrapperType;
    }

}
