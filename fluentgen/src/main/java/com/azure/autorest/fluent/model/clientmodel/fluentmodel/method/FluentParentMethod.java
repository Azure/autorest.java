/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.FluentInterfaceStage;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentParentMethod extends FluentMethod {

    private List<ClientMethodParameter> parameters;

    public FluentParentMethod(FluentResourceModel fluentResourceModel, FluentMethodType type,
                              FluentInterfaceStage stage, String parentResourceName, List<ClientMethodParameter> parameters) {
        super(fluentResourceModel, type);

        this.parameters = parameters;

        this.name = "withExisting" + parentResourceName;
        this.description = String.format("Specifies %1$s.", parameters.stream().map(ClientMethodParameter::getName).collect(Collectors.joining(", ")));
        this.interfaceReturnValue = new ReturnValue("the next definition stage.", new ClassType.Builder().name(stage.getNextStage().getName()).build());
    }

    @Override
    public String getInterfaceMethodSignature() {
        String parameterText = parameters.stream()
                .map(p -> String.format("%1$s %2$s", p.getClientType().toString(), p.getName()))
                .collect(Collectors.joining(", "));
        return String.format("%1$s %2$s(%3$s)",
                interfaceReturnValue.getType().toString(),
                this.name, parameterText);
    }

    @Override
    public void writeJavadoc(JavaJavadocComment commentBlock) {
        commentBlock.description(description);
        parameters.forEach(p -> commentBlock.param(p.getName(), p.getDescription()));
        commentBlock.methodReturns(interfaceReturnValue.getDescription());
    }

    @Override
    public void addImportsTo(Set<String> imports, boolean includeImplementationImports) {
        parameters.forEach(p -> p.addImportsTo(imports, false));
    }
}
