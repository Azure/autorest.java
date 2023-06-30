// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.MethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.MethodUtil;
import com.azure.autorest.util.ModelExampleUtil;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientMethodExampleWriter {

    private final Set<String> imports = new HashSet<>();
    private final Consumer<JavaBlock> methodBodyWriter;
    private final Set<ExampleHelperFeature> helperFeatures = new HashSet<>();

    public ClientMethodExampleWriter(ClientMethod method, String clientVarName, ProxyMethodExample proxyMethodExample){
        ModelExampleWriter.ExampleNodeModelInitializationVisitor nodeVisitor = new ModelExampleWriter.ExampleNodeModelInitializationVisitor();

        List<MethodParameter> methodParameters = MethodUtil.getParameters(method, true);
        List<ExampleNode> exampleNodes = methodParameters
                .stream()
                .map(methodParameter -> ModelExampleUtil.parseNodeFromParameter(proxyMethodExample, methodParameter))
                .collect(Collectors.toList());

        String parameterInvocations = exampleNodes.stream()
                .map(nodeVisitor::accept)
                .collect(Collectors.joining(", "));

        this.imports.addAll(nodeVisitor.getImports());
        this.helperFeatures.addAll(nodeVisitor.getHelperFeatures());

        StringBuilder methodInvocation = new StringBuilder();

        if (method.getReturnValue().getType().asNullable() != ClassType.Void) {
            String assignment = String.format("%s %s = ", method.getReturnValue().getType(), "response");
            methodInvocation.append(assignment);
        }

        methodInvocation.append(
                String.format("%s.%s(%s);",
                        clientVarName,
                        method.getName(),
                        parameterInvocations));

        methodBodyWriter = javaBlock -> javaBlock.line(methodInvocation.toString());
    }

    public Set<String> getImports() {
        return new HashSet<>(this.imports);
    }

    public void write(JavaBlock javaBlock) {
        methodBodyWriter.accept(javaBlock);
    }

    public Set<ExampleHelperFeature> getHelperFeatures() {
        return helperFeatures;
    }
}
