// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.ModelNaming;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.ResourceLocalVariables;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;

import java.util.Set;

public class FluentUpdateMethod extends FluentMethod {
    public FluentUpdateMethod(FluentResourceModel model, FluentMethodType type,
                              ResourceLocalVariables resourceLocalVariables) {
        super(model, type);

        this.name = "update";
        String interfaceTypeName = model.getInterfaceType().getName();
        this.description = String.format("Begins update for the %1$s resource.", interfaceTypeName);;

        this.interfaceReturnValue = new ReturnValue("the stage of resource update",
                new ClassType.Builder()
                        .name(String.format("%1$s.%2$s", interfaceTypeName, ModelNaming.MODEL_FLUENT_INTERFACE_UPDATE))
                        .build());
        this.implementationReturnValue = new ReturnValue("", fluentResourceModel.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    // init
                    resourceLocalVariables.getLocalVariablesMap().values().stream()
                            .filter(LocalVariable::isInitializeRequired)
                            .forEach(var -> {
                                block.line(String.format("this.%1$s = %2$s;", var.getName(), var.getInitializeExpression()));
                            });

                    block.methodReturn("this");
                })
                .build();
    }

    @Override
    protected String getBaseMethodSignature() {
        return String.format("%1$s()", this.name);
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        commentBlock.description(description);
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
    }
}
