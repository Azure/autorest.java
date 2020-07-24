/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.HashSet;
import java.util.Set;

public class FluentModelProperty {

    private final ClientModelProperty clientModelProperty;

    private final IType clientType;

    private final WrapperTypeConversionMethod wrapperTypeConversionMethod;

    public FluentModelProperty(ClientModelProperty property) {
        this.clientModelProperty = property;
        this.clientType = getWrapperType(property.getClientType());
        this.wrapperTypeConversionMethod = this.clientType == property.getClientType()
                ? null : new WrapperTypeConversionMethod(this, this.clientModelProperty);
    }

    public String getName() {
        return clientModelProperty.getName();
    }

    public String getDescription() {
        return clientModelProperty.getDescription();
    }

    public IType getClientType() {
        return clientType;
    }

    public String getMethodSignature() {
        return String.format("%1$s %2$s()", this.getClientType(), this.getGetterName());
    }

    public MethodTemplate getImplementationMethodTemplate() {
        if (wrapperTypeConversionMethod != null) {
            return wrapperTypeConversionMethod.getConversionMethodTemplate();
        } else {
            Set<String> imports = new HashSet<>();
            this.getClientType().addImportsTo(imports, false);

            return MethodTemplate.builder()
                    .imports(imports)
                    .methodSignature(this.getMethodSignature())
                    .method(block -> {
                        block.methodReturn(String.format("this.inner().%1$s()", this.getGetterName()));
                    })
                    .build();
        }
    }

    private String getGetterName() {
        return CodeNamer.getModelNamer().modelPropertyGetterName(clientModelProperty);
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
