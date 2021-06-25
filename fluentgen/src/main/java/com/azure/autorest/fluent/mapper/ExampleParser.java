/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageBlank;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageMisc;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageParent;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExampleParser {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ExampleParser.class);

    public static List<FluentCollectionMethodExample> parseMethod(FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {
        List<FluentCollectionMethodExample> ret = null;

        ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
        if (collectionMethod.getInnerClientMethod().getProxyMethod().getExamples() != null && requiresExample(clientMethod)) {
            ret = new ArrayList<>();

            List<MethodParameter> methodParameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                ProxyMethodExample example = entry.getValue();
                FluentCollectionMethodExample collectionMethodExample = new FluentCollectionMethodExample(entry.getKey(),
                        FluentStatic.getFluentManager(), collection, collectionMethod);

                for (MethodParameter methodParameter : methodParameters) {
                    ExampleNode node = parseNodeFromParameter(example, methodParameter);

                    if (node.getClientType() == ClassType.Void) {
                        if (methodParameter.getClientMethodParameter().getIsRequired()) {
                            logger.warn("Failed to assign sample value to required parameter '{}'", methodParameter.getClientMethodParameter().getName());
                        }
                    }

                    FluentCollectionMethodExample.ParameterExample parameterExample = new FluentCollectionMethodExample.ParameterExample(methodParameter, node);
                    collectionMethodExample.getParameters().add(parameterExample);
                }

                ret.add(collectionMethodExample);
            }
        }
        return ret;
    }

    public static List<FluentResourceCreateExample> parseResourceCreate(FluentResourceCollection collection, ResourceCreate resourceCreate) {
        List<FluentResourceCreateExample> ret = null;

        List<FluentCollectionMethod> collectionMethods = resourceCreate.getMethodReferences();
        for (FluentCollectionMethod collectionMethod : collectionMethods) {
            ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
            if (collectionMethod.getInnerClientMethod().getProxyMethod().getExamples() != null && requiresExample(clientMethod)) {
                if (ret == null) {
                    ret = new ArrayList<>();
                }

                List<MethodParameter> methodParameters = getParameters(clientMethod);
                ClientModel requestBodyClientModel = resourceCreate.getRequestBodyParameterModel();
                MethodParameter requestBodyParameter = methodParameters.stream()
                        .filter(p -> p.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.Body)
                        .findFirst().orElse(null);

                for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                    ProxyMethodExample example = entry.getValue();
                    FluentResourceCreateExample resourceCreateExample = new FluentResourceCreateExample(entry.getKey(),
                            FluentStatic.getFluentManager(), collection);

                    FluentDefineMethod defineMethod = resourceCreate.getDefineMethod();
                    ExampleNode defineNode = null;
                    if (defineMethod.getMethodParameter() != null) {
                        MethodParameter methodParameter = findMethodParameter(methodParameters, defineMethod.getMethodParameter());
                        defineNode = parseNodeFromParameter(example, methodParameter);

                        if (defineNode.getClientType() == ClassType.Void) {
                            logger.warn("Failed to assign sample value to define method '{}'", defineMethod.getName());
                        }
                    }
                    resourceCreateExample.getParameters().add(new FluentResourceCreateExample.ParameterExample(defineMethod, defineNode));

                    for (DefinitionStage stage : resourceCreate.getDefinitionStages()) {
                        List<FluentMethod> fluentMethods = stage.getMethods();
                        if (!fluentMethods.isEmpty()) {
                            FluentMethod fluentMethod = fluentMethods.iterator().next();
                            List<ExampleNode> exampleNodes = new ArrayList<>();
                            if (stage instanceof DefinitionStageMisc) {
                                DefinitionStageMisc miscStage = (DefinitionStageMisc) stage;
                                MethodParameter methodParameter = findMethodParameter(methodParameters, miscStage.getMethodParameter());
                                ExampleNode node = parseNodeFromParameter(example, methodParameter);
                                exampleNodes.add(node);
                            } else if (stage instanceof DefinitionStageParent) {
                                List<MethodParameter> parameters = fluentMethod.getParameters().stream()
                                        .map(p -> findMethodParameter(methodParameters, p))
                                        .collect(Collectors.toList());
                                exampleNodes.addAll(parameters.stream()
                                        .map(p -> parseNodeFromParameter(example, p))
                                        .collect(Collectors.toList()));
                            } else if (stage instanceof DefinitionStageBlank || stage instanceof DefinitionStageCreate) {
                                // this stage does not have parameter
                            } else {
                                ClientModelProperty clientModelProperty = stage.getModelProperty();
                                if (clientModelProperty != null) {
                                    ExampleNode node = parseNodeFromModelProperty(example, requestBodyParameter, requestBodyClientModel, clientModelProperty);
                                    if (node != null) {
                                        exampleNodes.add(node);
                                    }
                                }
                            }

                            if (exampleNodes.isEmpty() || exampleNodes.stream().anyMatch(n -> n.getClientType() == ClassType.Void)) {
                                if (stage.isMandatoryStage()) {
                                    logger.warn("Failed to assign sample value to required stage '{}'", stage.getName());
                                }
                            }

                            if (!exampleNodes.isEmpty()) {
                                resourceCreateExample.getParameters().add(new FluentResourceCreateExample.ParameterExample(fluentMethod, exampleNodes));
                            }
                        }
                    }

                    ret.add(resourceCreateExample);
                }
            }
        }
        return ret;
    }

    private static ProxyMethodExample.ParameterValue findParameter(ProxyMethodExample example, String serializedName) {
        return example.getParameters().entrySet()
                .stream().filter(p -> p.getKey().equalsIgnoreCase(serializedName))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }

    private static MethodParameter findMethodParameter(List<MethodParameter> methodParameters, ClientMethodParameter clientMethodParameter) {
        return methodParameters.stream()
                .filter(p -> p.getClientMethodParameter() == clientMethodParameter)
                .findFirst().orElse(null);
    }

    private static ExampleNode parseNodeFromParameter(ProxyMethodExample example, MethodParameter methodParameter) {
        String serializedName = methodParameter.getSerializedName();
        if (serializedName == null && methodParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.Body) {
            serializedName = methodParameter.getProxyMethodParameter().getName();
        }

        ProxyMethodExample.ParameterValue parameterValue = findParameter(example, serializedName);
        ExampleNode node;
        if (parameterValue == null) {
            if (ClassType.Context.equals(methodParameter.getClientMethodParameter().getClientType())) {
                node = new LiteralNode(ClassType.Context, null).setLiteralsValue("");
            } else {
                node = new LiteralNode(ClassType.Void, null);
            }
        } else {
            node = parseNode(methodParameter.getClientMethodParameter().getClientType(), parameterValue.getObjectValue());
        }
        return node;
    }

    private static ExampleNode parseNodeFromModelProperty(ProxyMethodExample example, MethodParameter methodParameter,
                                                          ClientModel clientModel, ClientModelProperty clientModelProperty) {
        String serializedName = methodParameter.getProxyMethodParameter().getName();

        ProxyMethodExample.ParameterValue parameterValue = findParameter(example, serializedName);
        ExampleNode node;
        if (parameterValue == null) {
            node = new LiteralNode(ClassType.Void, null);
        } else {
            List<String> flattenedNames = flattenedNames(clientModelProperty.getSerializedName());

            boolean found = true;
            Object childObjectValue = parameterValue.getObjectValue();
            for (String name : flattenedNames) {
                if (childObjectValue instanceof Map) {
                    childObjectValue = ((Map<String, Object>) childObjectValue).get(name);
                    if (childObjectValue == null) {
                        found = false;
                        break;
                    }
                } else {
                    found = false;
                    break;
                }
            }
            if (found) {
                node = parseNode(clientModelProperty.getClientType(), childObjectValue);
            } else {
                node = null;
            }
        }
        return node;
    }

    private static ExampleNode parseNode(IType type, Object objectValue) {
        ExampleNode node = null;
        if (type instanceof ListType && objectValue instanceof List) {
            IType elementType = ((ListType) type).getElementType();
            ListNode listNode = new ListNode(elementType, objectValue);
            node = listNode;

            List<Object> elements = (List<Object>) objectValue;
            for (Object childObjectValue : elements) {
                ExampleNode childNode = parseNode(elementType, childObjectValue);
                node.getChildNodes().add(childNode);
            }
        } else if (type instanceof MapType && objectValue instanceof Map) {
            IType elementType = ((MapType) type).getValueType();
            MapNode mapNode = new MapNode(elementType, objectValue);
            node = mapNode;

            Map<String, Object> dict = (Map<String, Object>) objectValue;
            for (Map.Entry<String, Object> entry: dict.entrySet()) {
                ExampleNode childNode = parseNode(elementType, entry.getValue());
                node.getChildNodes().add(childNode);
                mapNode.getKeys().put(childNode, entry.getKey());
            }
        } else if (type instanceof ClassType && objectValue instanceof Map) {
            ClientModel model = FluentUtils.getClientModel(((ClassType) type).getName());
            if (model != null) {
                ClientModelNode clientModelNode = new ClientModelNode(type, objectValue);
                node = clientModelNode;

                clientModelNode.setClientModel(model);

                for (ClientModelProperty modelProperty : model.getProperties()) {
                    String serializedName = modelProperty.getSerializedName();

                    List<String> flattenedNames = Collections.singletonList(serializedName);
                    if (model.getNeedsFlatten()) {
                        flattenedNames = flattenedNames(serializedName);
                    }

                    boolean found = true;
                    Object childObjectValue = objectValue;
                    for (String name : flattenedNames) {
                        if (childObjectValue instanceof Map) {
                            childObjectValue = ((Map<String, Object>) childObjectValue).get(name);
                            if (childObjectValue == null) {
                                found = false;
                                break;
                            }
                        } else {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        ExampleNode childNode = parseNode(modelProperty.getClientType(), childObjectValue);
                        node.getChildNodes().add(childNode);
                        clientModelNode.getClientModelProperties().put(childNode, modelProperty);
                    }
                }
            } else {
                // TODO error
            }
        } else {
            LiteralNode literalNode = new LiteralNode(type, objectValue);
            node = literalNode;

            literalNode.setLiteralsValue(objectValue.toString());
        }
        return node;
    }

    private static List<String> flattenedNames(String serializedName) {
        // TODO escaped .
        return Arrays.asList(serializedName.split(Pattern.quote(".")));
    }

    private static List<MethodParameter> getParameters(ClientMethod clientMethod) {
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = clientMethod.getProxyMethod().getParameters().stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return clientMethod.getMethodParameters().stream()
                .filter(p -> proxyMethodParameterByClientParameterName.containsKey(p.getName()))
                .filter(p -> !p.getIsConstant() && !p.getFromClient())
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    private static boolean requiresExample(ClientMethod clientMethod) {
        if (clientMethod.getType() == ClientMethodType.SimpleSync
                || clientMethod.getType() == ClientMethodType.SimpleSyncRestResponse
                || clientMethod.getType() == ClientMethodType.PagingSync
                || clientMethod.getType() == ClientMethodType.LongRunningSync) {
            // generate example for the method with full parameters
            return clientMethod.getParameters().stream().anyMatch(p -> ClassType.Context.equals(p.getClientType()));
        }
        return false;
    }
}
