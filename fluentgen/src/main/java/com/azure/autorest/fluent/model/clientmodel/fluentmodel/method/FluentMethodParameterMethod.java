// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.LocalVariable;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.autorest.util.CodeNamer;

import java.util.Set;

public class FluentMethodParameterMethod extends FluentMethod {

    private final ClientMethodParameter methodParameter;
    private final LocalVariable localVariable;

    public FluentMethodParameterMethod(FluentResourceModel model, FluentMethodType type,
                                       FluentInterfaceStage stage,
                                       ClientMethodParameter methodParameter, LocalVariable localVariable) {
        super(model, type);

        this.methodParameter = methodParameter;
        this.localVariable = localVariable;

        this.name = CodeNamer.getModelNamer().modelPropertySetterName(methodParameter.getName());
        this.description = String.format("Specifies the %1$s property: %2$s.", methodParameter.getName(), methodParameter.getDescription());
        this.interfaceReturnValue = new ReturnValue("the next definition stage.", new ClassType.Builder().name(stage.getNextStage().getName()).build());
        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    block.line("this.%1$s = %2$s;", localVariable.getName(), methodParameter.getName());
                    block.methodReturn("this");
                })
                .build();

        this.parameters.add(methodParameter);
    }

    public FluentMethodParameterMethod(FluentResourceModel model, FluentMethodType type,
                                       FluentInterfaceStage stage,
                                       ClientMethodParameter methodParameter, LocalVariable localVariable,
                                       String methodName) {
        super(model, type);

        this.methodParameter = methodParameter;
        this.localVariable = localVariable;

        this.name = methodName;
        this.description = String.format("Specifies the %1$s property: %2$s.", methodParameter.getName(), methodParameter.getDescription());
        this.interfaceReturnValue = new ReturnValue("the next definition stage.", new ClassType.Builder().name(stage.getNextStage().getName()).build());
        this.implementationReturnValue = new ReturnValue("", model.getImplementationType());

        this.implementationMethodTemplate = MethodTemplate.builder()
                .methodSignature(this.getImplementationMethodSignature())
                .method(block -> {
                    block.line("this.%1$s = %2$s;", localVariable.getName(), methodParameter.getName());
                    block.methodReturn("this");
                })
                .build();
    }

    @Override
    protected String getBaseMethodSignature() {
        return String.format("%1$s(%2$s %3$s)",
                this.name,
                methodParameter.getClientType().toString(),
                methodParameter.getName());
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        commentBlock.description(description);
        commentBlock.param(methodParameter.getName(), methodParameter.getDescription());
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        methodParameter.addImportsTo(imports, false);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FluentMethodParameterMethod) {
            FluentMethodParameterMethod other = (FluentMethodParameterMethod) obj;
            return this.methodParameter == other.methodParameter && this.localVariable == other.localVariable;
        } else {
            return false;
        }
    }

    public ClientMethodParameter getMethodParameter() {
        return methodParameter;
    }
}
