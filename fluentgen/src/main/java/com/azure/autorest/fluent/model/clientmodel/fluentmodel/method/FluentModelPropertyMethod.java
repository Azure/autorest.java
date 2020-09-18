/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Set;

public class FluentModelPropertyMethod extends FluentMethod {

    private final ClientModel clientModel;
    private final ClientModelProperty modelProperty;
    private final LocalVariable localVariable;

    public FluentModelPropertyMethod(FluentResourceModel model, FluentMethodType type,
                                     FluentInterfaceStage stage, ClientModel clientModel,
                                     ClientModelProperty modelProperty,
                                     LocalVariable localVariable) {
        super(model, type);

        this.clientModel = clientModel;
        this.modelProperty = modelProperty;
        this.localVariable = localVariable;

        this.name = modelProperty.getSetterName();
        this.description = String.format("Specifies the %1$s property: %2$s.", modelProperty.getName(), modelProperty.getDescription());
        this.interfaceReturnValue = new ReturnValue("the next definition stage.", new ClassType.Builder().name(stage.getNextStage().getName()).build());
        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    if (fluentResourceModel.getInnerModel() == clientModel) {
                        block.line("this.%1$s().%2$s(%3$s);", ModelNaming.METHOD_INNER, modelProperty.getSetterName(), modelProperty.getName());
                    } else {
                        block.line("this.%1$s.%2$s(%3$s);", localVariable.getName(), modelProperty.getSetterName(), modelProperty.getName());
                    }
                    block.methodReturn("this");
                })
                .build();
    }

    public ClientModel getClientModel() {
        return clientModel;
    }

    @Override
    protected String getBaseMethodSignature() {
        return String.format("%1$s(%2$s %3$s)",
                this.name,
                modelProperty.getClientType().toString(),
                modelProperty.getName());
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        commentBlock.description(description);
        commentBlock.param(modelProperty.getName(), modelProperty.getDescription());
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        modelProperty.addImportsTo(imports, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FluentModelPropertyMethod) {
            FluentModelPropertyMethod other = (FluentModelPropertyMethod) obj;
            return this.clientModel == other.clientModel && this.modelProperty == other.modelProperty && this.localVariable == other.localVariable;
        } else {
            return false;
        }
    }
}
