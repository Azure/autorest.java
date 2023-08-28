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
                    if (parameterMapping.getOutputParameterPropertyName() != null) {
                        // this is a flattened property, so put flattening(real parameter) value

                        // output parameter's name is the "escaped reserved client method parameter name" of the real parameter's serialized name
                        // since flattened parameter is always in body, we can deal with that explicitly
                        ClientMethodParameter outputParameter = detail.getOutParameter();
                        Map<String, Object> flattenedParameterValue = getFlattenedBodyParameterExampleValue(proxyMethodExample, outputParameter.getName());
                        if (flattenedParameterValue != null) {
                            exampleValue.putAll(flattenedParameterValue);
                        }
                        // since it's flattened property, all parameterMappings share the same outputParameter(real parameter)
                        // we only need to put example value once, which is the flattened(real) parameter's value
                        break;
                    } else {
                        // Group property's "serializedName" is the real parameter's "serializedName" on the wire.
                        // This implicit equivalence is defined in emitter and preserved in mapping client method.
                        String serializedParameterName = parameterMapping.getInputParameterProperty().getSerializedName();
                        ClientMethodParameter parameter = detail.getOutParameter();
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
            String outputParameterName = convenienceMethod.getMethodTransformationDetails().iterator().next().getOutParameter().getName();
            Map<String, Object> realParameterValue = getFlattenedBodyParameterExampleValue(proxyMethodExample, outputParameterName);

            IType type = methodParameter.getClientMethodParameter().getClientType();
            IType wireType = methodParameter.getClientMethodParameter().getWireType();

            ParameterMapping parameterMapping = convenienceMethod.getMethodTransformationDetails().iterator().next()
                    .getParameterMappings()
                    .stream()
                    .filter(mapping -> Objects.equals(mapping.getInputParameter().getName(), methodParameter.getClientMethodParameter().getName()))
                    .findFirst().orElse(null);

            Object methodParameterValue = null;
            if (realParameterValue != null && parameterMapping != null) {
                methodParameterValue = realParameterValue.get(parameterMapping.getOutputParameterProperty().getSerializedName());
            }
            return ModelExampleUtil.parseNode(type, wireType, methodParameterValue);
        } else {
            return ModelExampleUtil.parseNodeFromParameter(proxyMethodExample, methodParameter);
        }
    }

    private Map<String, Object> getFlattenedBodyParameterExampleValue(ProxyMethodExample example, String clientMethodParameterName) {
        ProxyMethodExample.ParameterValue parameterValue = example.getParameters().entrySet()
                .stream().filter(
                        p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getKey())
                                .equalsIgnoreCase(clientMethodParameterName))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
        if (parameterValue == null) {
            return null;
        }
        return (Map<String, Object>) parameterValue.getObjectValue();
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
                                detail.getParameterMappings()
                                        .stream()
                                        .allMatch(mapping -> Objects.equals(
                                                mapping.getInputParameter().getName(),
                                                methodParameter.getClientMethodParameter().getName()))
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

    public void writeMethodInvocation(JavaBlock javaBlock) {
        methodBodyWriter.accept(javaBlock);
    }

    public Set<ExampleHelperFeature> getHelperFeatures() {
        return helperFeatures;
    }
}
