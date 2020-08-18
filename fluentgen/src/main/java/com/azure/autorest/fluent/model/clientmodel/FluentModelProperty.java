/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel;

import com.azure.autorest.fluent.model.clientmodel.implmethod.WrapperMethod;
import com.azure.autorest.fluent.model.clientmodel.implmethod.WrapperPropertyImplementationMethod;
import com.azure.autorest.fluent.model.clientmodel.implmethod.WrapperPropertyTypeConversionMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.Set;

public class FluentModelProperty {

    private final ClientModelProperty modelProperty;

    private final IType fluentType;

    private final WrapperMethod wrapperMethod;

    public FluentModelProperty(ClientModelProperty property) {
        this.modelProperty = property;
        this.fluentType = getWrapperType(property.getClientType());
        this.wrapperMethod = this.fluentType == property.getClientType()
                ? new WrapperPropertyImplementationMethod(this, this.modelProperty)
                : new WrapperPropertyTypeConversionMethod(this, this.modelProperty);
    }

    public String getName() {
        return modelProperty.getName();
    }

    public String getDescription() {
        return modelProperty.getDescription();
    }

    public IType getFluentType() {
        return fluentType;
    }

    // method signature for model property
    public String getMethodSignature() {
        return String.format("%1$s %2$s()", this.getFluentType(), this.getGetterName());
    }

    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        this.fluentType.addImportsTo(imports, false);

        if (includeImplementationImports) {
            this.wrapperMethod.getMethodTemplate().addImportsTo(imports);
        }
    }

    public MethodTemplate getImplementationMethodTemplate() {
        return wrapperMethod.getMethodTemplate();
    }

    private String getGetterName() {
        return CodeNamer.getModelNamer().modelPropertyGetterName(modelProperty);
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

    public ClientModelProperty getInnerProperty() {
        return modelProperty;
    }
}
