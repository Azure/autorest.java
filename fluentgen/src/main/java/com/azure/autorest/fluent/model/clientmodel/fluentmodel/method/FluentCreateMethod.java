/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.model.clientmodel.fluentmodel.method;

import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ReturnValue;
import com.azure.autorest.model.javamodel.JavaJavadocComment;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FluentCreateMethod extends FluentMethod {

    private List<ClientMethodParameter> parameters;

    public FluentCreateMethod(FluentResourceModel model, FluentMethodType type,
                              List<ClientMethodParameter> parameters) {
        super(model, type);

        this.name = "create";
        this.description = "Executes the create request.";
        this.interfaceReturnValue = new ReturnValue("the created resource.", model.getInterfaceType());
        this.implementationReturnValue = interfaceReturnValue;

        this.parameters = parameters;
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
        interfaceReturnValue.addImportsTo(imports, false);
        parameters.forEach(p -> p.addImportsTo(imports, false));
    }
}
