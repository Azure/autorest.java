// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.template.example;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.GenericType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodTransformationDetail;
import com.azure.autorest.model.clientmodel.ParameterMapping;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleHelperFeature;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.MethodParameter;
import com.azure.autorest.model.javamodel.JavaBlock;
import com.azure.autorest.util.ClientModelUtil;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.MethodUtil;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.core.http.ContentType;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.polling.SyncPoller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ClientMethodExampleWriter {

    private final Set<String> imports = new HashSet<>();
    private final Consumer<JavaBlock> methodBodyWriter;
    private final Consumer<JavaBlock> responseAssertionWriter;
    private final ModelExampleWriter.ExampleNodeModelInitializationVisitor nodeVisitor = new ModelExampleWriter.ExampleNodeModelInitializationVisitor();

    public ClientMethodExampleWriter(ClientMethod method, String clientVarName, ProxyMethodExample proxyMethodExample){

        List<MethodParameter> methodParameters = MethodUtil.getParameters(method, true);
        List<ExampleNode> exampleNodes = methodParameters
                .stream()
                .map(methodParameter -> parseNodeFromParameter(method, proxyMethodExample, methodParameter))
                .collect(Collectors.toList());

        String parameterInvocations = exampleNodes.stream()
                .map(nodeVisitor::accept)
                .collect(Collectors.joining(", "));

        this.imports.add("org.junit.jupiter.api.Assertions");
        this.imports.addAll(nodeVisitor.getImports());
        // for mapOf
        this.imports.add(HashMap.class.getName());
        method.getReturnValue().getType().addImportsTo(imports, false);
        addClientModelImports(method.getReturnValue().getType());

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
        responseAssertionWriter = methodBlock -> {
            Optional<ProxyMethodExample.Response> responseOpt = proxyMethodExample.getPrimaryResponse();
            if (responseOpt.isPresent()) {
                ProxyMethodExample.Response response = responseOpt.get();
                IType returnType = method.getReturnValue().getType();
                if (returnType instanceof GenericType) {
                    GenericType responseType = (GenericType) returnType;
                    if (SyncPoller.class.getSimpleName().equals(responseType.getName())) {
                        // SyncPoller<>

                        if (response.getStatusCode() / 100 == 2) {
                            // it should have a 202 leading to SUCCESSFULLY_COMPLETED
                            // but x-ms-examples usually does not include the final result
                            methodBlock.line("Assertions.assertEquals(LongRunningOperationStatus.SUCCESSFULLY_COMPLETED, response.waitForCompletion().getStatus());");
                        }
                    } else if (PagedIterable.class.getSimpleName().equals(responseType.getName())) {
                        // PagedIterable<>

                        // assert status code
                        methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.iterableByPage().iterator().next().getStatusCode());", response.getStatusCode()));
                        // assert headers
                        response.getHttpHeaders().stream().forEach(header -> {
                            String expectedValueStr = ClassType.String.defaultValueExpression(header.getValue());
                            String keyStr = ClassType.String.defaultValueExpression(header.getName());
                            methodBlock.line(String.format("Assertions.assertEquals(%1$s, response.iterableByPage().iterator().next().getHeaders().get(HttpHeaderName.fromString(%2$s)).getValue());", expectedValueStr, keyStr));
                        });
                        // assert JSON of first item, or assert count=0
                        if (ContentType.APPLICATION_JSON.equals(method.getProxyMethod().getRequestContentType())
                                && responseType.getTypeArguments().length > 0
                                && ClientModelUtil.isClientModel(responseType.getTypeArguments()[0])
                                && method.getMethodPageDetails() != null
                                && response.getBody() instanceof Map) {
                            Map<String, Object> bodyMap = (Map<String, Object>) response.getBody();
                            if (bodyMap.containsKey(method.getMethodPageDetails().getSerializedItemName())) {
                                Object items = bodyMap.get(method.getMethodPageDetails().getSerializedItemName());
                                if (items instanceof List) {
                                    List<Object> itemArray = (List<Object>) items;
                                    if (itemArray.isEmpty()) {
                                        methodBlock.line("Assertions.assertEquals(0, response.stream().count());");
                                    } else {
                                        Object firstItem = itemArray.iterator().next();
                                        methodBlock.line("%s firstItem = %s;", responseType.getTypeArguments()[0], "response.iterator().next()");
                                        writeModelAssertion(methodBlock, nodeVisitor, (ClassType) responseType.getTypeArguments()[0], (Map<String, Object>) firstItem, "firstItem");
                                    }
                                }
                            }
                        }
                    }
                } else if (ClientModelUtil.isClientModel(returnType) && response.getBody() instanceof Map) {
                    // Client Model
                    // TODO(xiaofei) when return type is boolean (exists through HEAD), or Map/List and primitive types(are they possible?)
                    writeModelAssertion(methodBlock, nodeVisitor, (ClassType) returnType, (Map<String, Object>) response.getBody(), "response");
                }
            } else {
                methodBlock.line("Assertions.assertNotNull(response);");
            }
        };
    }

    private void addClientModelImports(IType type) {
        ClientModel clientModel = null;
        if (type instanceof GenericType) {
            GenericType responseType = (GenericType) type;
            if (PagedIterable.class.getSimpleName().equals(responseType.getName())) {
                if (ClientModelUtil.isClientModel(responseType.getTypeArguments()[0])) {
                    clientModel = ClientModelUtil.getClientModel(((ClassType) responseType.getTypeArguments()[0]).getName());
                }
            }
        } else if (type instanceof ClassType && ClientModelUtil.isClientModel(type)) {
            clientModel = ClientModelUtil.getClientModel(((ClassType) type).getName());
        }
        if (clientModel != null) {
            clientModel.addImportsTo(this.imports, JavaSettings.getInstance());
        }
    }

    private void writeModelAssertion(JavaBlock methodBlock, ModelExampleWriter.ExampleNodeModelInitializationVisitor nodeVisitor, ClassType modelType, Map<String, Object> body, String variableReference) {
        methodBlock.line(String.format("Assertions.assertNotNull(%s);", variableReference));
        ClientModel clientModel = ClientModelUtil.getClientModel(modelType.getName());
        if (clientModel.getProperties() != null) {
            for (ClientModelProperty property : clientModel.getProperties()) {
                String serializedName = property.getSerializedName();
                Object value = body.get(serializedName);
                if (value != null) {
                    String propertyReference = String.format("%s.%s()", variableReference, property.getGetterName());
                    if (!ClientModelUtil.isClientModel(property.getClientType()) && (!(property.getClientType() instanceof ListType))) {
                        // simple model that can be compared by "Assertions.assertEquals()"
                        methodBlock.line(String.format(
                                "Assertions.assertEquals(%s, %s);",
                                nodeVisitor.accept(ModelExampleUtil.parseNode(property.getClientType(), property.getWireType(), value)),
                                propertyReference
                        ));
                    } else if (property.getClientType() instanceof ClassType
                            && ClientModelUtil.isClientModel(property.getClientType())
                            && value instanceof Map) {
                        // Client Model
                        String varName = String.format("%s%s", variableReference, CodeNamer.toPascalCase(property.getName()));
                        methodBlock.line("%s %s = %s;", property.getClientType(), varName, propertyReference);
                        writeModelAssertion(methodBlock, nodeVisitor, (ClassType) property.getClientType(),(Map<String, Object>) value, varName);
                    } else if (property.getClientType() instanceof ListType && value instanceof List) {
                        // property is List
                        List<Object> values = (List<Object>) value;
                        if (values.size() > 0) {
                            ListType listType = (ListType) property.getClientType();
                            IType elementType = listType.getElementType();
                            Object firstItemValue = values.iterator().next();
                            if (firstItemValue != null) {
                                String firstItemVarName = String.format("%s%s", variableReference, "FirstItem");
                                methodBlock.line("%s %s = %s.iterator().next();", elementType, firstItemVarName, propertyReference);
                                if (ClientModelUtil.isClientModel(elementType) && firstItemValue instanceof Map) {
                                    // List of Client Models
                                    writeModelAssertion(methodBlock, nodeVisitor, (ClassType) elementType, (Map<String, Object>) firstItemValue, firstItemVarName);
                                } else if (!(elementType instanceof ListType)){
                                    // List of simple types that can be compared by "equals", ignore List of List
                                    methodBlock.line("Assertions.assertEquals(%s, %s);", nodeVisitor.accept(ModelExampleUtil.parseNode(elementType.getClientType(), firstItemValue)), firstItemVarName);
                                }
                            }
                        } else {
                            methodBlock.line("Assertions.assertEquals(0, %s);", String.format("%s.size()", propertyReference));
                        }
                    }
                }
            }
        }
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
        return new HashSet<>(nodeVisitor.getHelperFeatures());
    }

    public void writeResponseAssertion(JavaBlock methodBlock) {
        responseAssertionWriter.accept(methodBlock);
    }
}
