/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;

public class FluentModelPropertyMethod extends FluentMethod {

    private final FluentInterfaceStage interfaceStage;
    private final ClientModel clientModel;
    private final ClientModelProperty modelProperty;

    public FluentModelPropertyMethod(FluentResourceModel model, FluentMethodType type,
                                     FluentInterfaceStage interfaceStage, ClientModel clientModel, ClientModelProperty modelProperty) {
        this.fluentResourceModel = model;
        this.type = type;
        this.interfaceStage = interfaceStage;
        this.clientModel = clientModel;
        this.modelProperty = modelProperty;

        this.name = modelProperty.getSetterName();
        this.description = String.format("Specifies the %1$s property: %2$s.", modelProperty.getName(), modelProperty.getDescription());
        this.interfaceReturnValue = new ReturnValue("the next definition stage.", new ClassType.Builder().name(interfaceStage.getNextStage().getName()).build());
        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());
    }

    public String getInterfaceMethodSignature() {
        return String.format("%1$s %2$s(%3$s %4$s)",
                interfaceReturnValue.getType().toString(),
                this.name,
                modelProperty.getClientType().toString(),
                modelProperty.getName());
    }

    public void writeJavadoc(JavaJavadocComment commentBlock) {
        String propertyName = modelProperty.getName();
        commentBlock.description(description);
        commentBlock.param(propertyName, modelProperty.getDescription());
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }
}
