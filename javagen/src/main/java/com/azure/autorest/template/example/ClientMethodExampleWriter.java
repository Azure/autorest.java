// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.MethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.MethodUtil;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.core.util.CoreUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
                .map(methodParameter -> parseNodeFromParameter(method, proxyMethodExample, methodParameter))
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

    /**
     * Parse example node from given parameter, taking into account parameter grouping.
     *
     * @param convenienceMethod the convenience method to generate example for
     * @param proxyMethodExample the proxy method example
     * @param methodParameter mapped convenience method parameter to protocol(proxy) method parameter
     * @return example node
     */
    private ExampleNode parseNodeFromParameter(ClientMethod convenienceMethod, ProxyMethodExample proxyMethodExample, MethodParameter methodParameter) {
        if (isGroupingParameter(convenienceMethod, methodParameter)) {
            // grouping, possible with flattening first

            // group example values into a map
            Map<String, Object> exampleValue = new HashMap<>();
            for (MethodTransformationDetail detail : convenienceMethod.getMethodTransformationDetails()) {
                for (ParameterMapping parameterMapping : detail.getParameterMappings()) {
                    String serializedParameterName = parameterMapping.getInputParameterProperty().getSerializedName();
                    ClientMethodParameter parameter = detail.getOutParameter();
                    if (parameterMapping.getOutputParameterPropertyName() != null) {
                        // this is a flattened property, so put flattening(real parameter) value
                        serializedParameterName = detail.getOutParameter().getName();
                        Map<String, Object> flattenedParameterValue = (Map<String, Object>) ModelExampleUtil.getParameterExampleValue(proxyMethodExample, serializedParameterName, parameter.getRequestParameterLocation());
                        if (flattenedParameterValue != null) {
                            exampleValue.putAll(flattenedParameterValue);
                        }
                    } else {
                        exampleValue.put(serializedParameterName,
                                ModelExampleUtil.getParameterExampleValue(
                                        proxyMethodExample, serializedParameterName, parameter.getRequestParameterLocation()));
                    }
                }
            }
            IType type = methodParameter.getClientMethodParameter().getClientType();
            IType wireType = methodParameter.getClientMethodParameter().getWireType();
            return ModelExampleUtil.parseNode(type, wireType, exampleValue);
        } else if (isFlattenParameter(convenienceMethod, methodParameter)) {
            // flatten, no grouping
            String realParameterName = convenienceMethod.getMethodTransformationDetails().iterator().next().getOutParameter().getName();
            Map<String, Object> realParameterValue = (Map<String, Object>) proxyMethodExample.getParameters().get(realParameterName).getObjectValue();

            IType type = methodParameter.getClientMethodParameter().getClientType();
            IType wireType = methodParameter.getClientMethodParameter().getWireType();

            Object flatteningParameterValue = null;
            if (realParameterValue != null) {
                flatteningParameterValue = realParameterValue
                        .entrySet()
                        .stream()
                        .filter(entry -> Objects.equals(
                                CodeNamer.getEscapedReservedClientMethodParameterName(entry.getKey()),
                                methodParameter.getClientMethodParameter().getName()))
                        .findFirst()
                        .map(Map.Entry::getValue).orElse(null);
            }
            return ModelExampleUtil.parseNode(type, wireType, flatteningParameterValue);
        } else {
            return ModelExampleUtil.parseNodeFromParameter(proxyMethodExample, methodParameter);
        }
    }

    private boolean isGroupingParameter(ClientMethod convenienceMethod, MethodParameter methodParameter) {
        List<MethodTransformationDetail> details = convenienceMethod.getMethodTransformationDetails();
        if (CoreUtils.isNullOrEmpty(details) || details.size() <= 1) {
            return false;
        }

        return details.stream().allMatch(
                detail ->
                        !CoreUtils.isNullOrEmpty(detail.getParameterMappings())
                                && detail.getOutParameter() != null
                                &&
                                // same name
                                Objects.equals(
                                        CodeNamer.getEscapedReservedClientMethodParameterName(
                                                detail.getParameterMappings().iterator().next().getInputParameter().getName()),
                                        methodParameter.getClientMethodParameter().getName())
                                // same client type
                                && detail.getParameterMappings().iterator().next().getInputParameter().getClientType()
                                .equals(methodParameter.getClientMethodParameter().getClientType())
        );
    }

    private boolean isFlattenParameter(ClientMethod convenienceMethod, MethodParameter methodParameter) {
        List<MethodTransformationDetail> details = convenienceMethod.getMethodTransformationDetails();
        if (CoreUtils.isNullOrEmpty(details) || details.size() != 1) {
            return false;
        }
        return details.stream().anyMatch(
                detail ->
                        !CoreUtils.isNullOrEmpty(detail.getParameterMappings())
                                && detail.getOutParameter() != null
                                && detail.getParameterMappings().stream()
                                .allMatch(mapping -> mapping.getOutputParameterPropertyName() != null
                                        && mapping.getInputParameterProperty() == null)
                        && detail.getParameterMappings()
                                .stream()
                                .anyMatch(mapping -> Objects.equals(methodParameter.getClientMethodParameter().getName(), mapping.getInputParameter().getName()))
        );
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
