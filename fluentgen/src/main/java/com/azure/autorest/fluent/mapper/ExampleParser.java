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
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
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

            List<MethodParameter> parameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                ProxyMethodExample example = entry.getValue();
                FluentCollectionMethodExample collectionMethodExample = new FluentCollectionMethodExample(entry.getKey(), collection, collectionMethod);

                for (MethodParameter parameter : parameters) {
                    String serializedName = parameter.getSerializedName();
                    if (serializedName == null && parameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.Body) {
                        serializedName = parameter.getProxyMethodParameter().getName();
                    }

                    ProxyMethodExample.ParameterValue parameterValue = example.getParameters().get(serializedName);
                    FluentCollectionMethodExample.ParameterExample parameterExample;
                    if (parameterValue == null) {
                        if (ClassType.Context.equals(parameter.getClientMethodParameter().getClientType())) {
                            parameterExample = new FluentCollectionMethodExample.ParameterExample(parameter, new LiteralNode(ClassType.Context, null).setLiteralsValue(""));
                        } else {
                            parameterExample = new FluentCollectionMethodExample.ParameterExample(parameter, new LiteralNode(ClassType.Void, null));
                        }
                    } else {
                        parameterExample = new FluentCollectionMethodExample.ParameterExample(parameter, parseNode(parameter.getClientMethodParameter().getClientType(), parameterValue.getObjectValue()));
                    }
                    collectionMethodExample.getParameters().add(parameterExample);
                }

                ret.add(collectionMethodExample);
            }
        }

        return ret;
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
