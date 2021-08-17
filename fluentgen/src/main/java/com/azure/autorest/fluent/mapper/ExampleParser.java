/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.mapper;

import com.azure.autorest.extension.base.model.codemodel.RequestParameterLocation;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.fluent.FluentGen;
import com.azure.autorest.fluent.model.clientmodel.FluentCollectionMethod;
import com.azure.autorest.fluent.model.clientmodel.FluentExample;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceCollection;
import com.azure.autorest.fluent.model.clientmodel.FluentResourceModel;
import com.azure.autorest.fluent.model.clientmodel.FluentStatic;
import com.azure.autorest.fluent.model.clientmodel.MethodParameter;
import com.azure.autorest.fluent.model.clientmodel.ModelProperty;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentClientMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ObjectNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.ParameterExample;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageBlank;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageMisc;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.DefinitionStageParent;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.create.ResourceCreate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentDefineMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.method.FluentMethod;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.ResourceUpdate;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.UpdateStage;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.UpdateStageApply;
import com.azure.autorest.fluent.model.clientmodel.fluentmodel.update.UpdateStageMisc;
import com.azure.autorest.fluent.util.FluentUtils;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientMethod;
import com.azure.autorest.model.clientmodel.ClientMethodParameter;
import com.azure.autorest.model.clientmodel.ClientMethodType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.serializer.CollectionFormat;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExampleParser {

    private static final Logger logger = new PluginLogger(FluentGen.getPluginInstance(), ExampleParser.class);

    private final boolean aggregateExamples;

    public ExampleParser() {
        this(true);
    }

    public ExampleParser(boolean aggregateExamples) {
        this.aggregateExamples = aggregateExamples;
    }

    public List<FluentExample> parseMethodGroup(MethodGroupClient methodGroup) {
        List<FluentClientMethodExample> methodExamples = new ArrayList<>();

        methodGroup.getClientMethods().forEach(m -> {
            List<FluentClientMethodExample> examples = ExampleParser.parseMethod(methodGroup, m);
            if (examples != null) {
                methodExamples.addAll(examples);
            }
        });

        Map<String, FluentExample> examples = new HashMap<>();
        methodExamples.forEach(e -> {
            FluentExample example = getExample(examples, e.getMethodGroup(), e.getClientMethod(), e.getName());
            example.getClientMethodExamples().add(e);
        });

        return new ArrayList<>(examples.values());
    }

    public List<FluentExample> parseResourceCollection(FluentResourceCollection resourceCollection) {
        List<FluentCollectionMethodExample> methodExamples = new ArrayList<>();
        List<FluentResourceCreateExample> resourceCreateExamples = new ArrayList<>();
        List<FluentResourceUpdateExample> resourceUpdateExamples = new ArrayList<>();

        resourceCollection.getMethodsForTemplate().forEach(m -> {
            List<FluentCollectionMethodExample> examples = ExampleParser.parseMethod(resourceCollection, m);
            if (examples != null) {
                methodExamples.addAll(examples);
            }
        });
        resourceCollection.getResourceCreates().forEach(rc -> {
            List<FluentResourceCreateExample> examples = ExampleParser.parseResourceCreate(resourceCollection, rc);
            if (examples != null) {
                resourceCreateExamples.addAll(examples);
            }
        });
        resourceCollection.getResourceUpdates().forEach(ru -> {
            List<FluentResourceUpdateExample> examples = ExampleParser.parseResourceUpdate(resourceCollection, ru);
            if (examples != null) {
                resourceUpdateExamples.addAll(examples);
            }
        });

        Map<String, FluentExample> examples = new HashMap<>();
        methodExamples.forEach(e -> {
            FluentExample example = getExample(examples, e.getResourceCollection(), e.getCollectionMethod(), e.getName());
            example.getCollectionMethodExamples().add(e);
        });
        resourceCreateExamples.forEach(e -> {
            FluentExample example = getExample(examples, e.getResourceCollection(), e.getResourceCreate().getMethodReferences().iterator().next(), e.getName());
            example.getResourceCreateExamples().add(e);
        });
        resourceUpdateExamples.forEach(e -> {
            FluentExample example = getExample(examples, e.getResourceCollection(), e.getResourceUpdate().getMethodReferences().iterator().next(), e.getName());
            example.getResourceUpdateExamples().add(e);
        });

        return new ArrayList<>(examples.values());
    }

    private FluentExample getExample(Map<String, FluentExample> examples,
                                     FluentResourceCollection resourceCollection, FluentCollectionMethod collectionMethod,
                                     String exampleName) {
        return getExample(examples, resourceCollection.getInnerGroupClient(), collectionMethod.getInnerClientMethod(), exampleName);
    }

    private FluentExample getExample(Map<String, FluentExample> examples,
                                     MethodGroupClient methodGroup, ClientMethod clientMethod,
                                     String exampleName) {
        String groupName = methodGroup.getClassBaseName();
        String methodName = clientMethod.getProxyMethod().getName();
        String name = CodeNamer.toPascalCase(groupName) + CodeNamer.toPascalCase(methodName);
        if (!this.aggregateExamples) {
            name += com.azure.autorest.preprocessor.namer.CodeNamer.getTypeName(exampleName);
        }
        FluentExample example = examples.get(name);
        if (example == null) {
            example = new FluentExample(CodeNamer.toPascalCase(groupName), CodeNamer.toPascalCase(methodName),
                    this.aggregateExamples ? null : exampleName,
                    clientMethod.getProxyMethod().getOperationId(),
                    getApiVersion(clientMethod));
            examples.put(name, example);
        }
        return example;
    }

    private static String getApiVersion(ClientMethod clientMethod) {
        String apiVersion = clientMethod.getProxyMethod().getParameters().stream()
                .filter(p -> "api-version".equals(p.getRequestParameterName()))
                .map(ProxyMethodParameter::getDefaultValue)
                .findFirst()
                .orElse(null);
        if (apiVersion == null) {
            logger.warn("Failed to find api-version in method '{}'", clientMethod.getName());
        }
        return apiVersion;
    }

    private static List<FluentCollectionMethodExample> parseMethod(FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {
        List<FluentCollectionMethodExample> ret = null;

        ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
        if (collectionMethod.getInnerClientMethod().getProxyMethod().getExamples() != null && requiresExample(clientMethod)) {
            ret = new ArrayList<>();

            List<MethodParameter> methodParameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                logger.info("Parse collection method example '{}'", entry.getKey());

                FluentCollectionMethodExample collectionMethodExample =
                        parseMethodForExample(collection, collectionMethod, methodParameters, entry.getKey(), entry.getValue());
                ret.add(collectionMethodExample);
            }
        }
        return ret;
    }

    private static List<FluentClientMethodExample> parseMethod(MethodGroupClient methodGroup, ClientMethod clientMethod) {
        List<FluentClientMethodExample> ret = null;

        if (clientMethod.getProxyMethod().getExamples() != null && requiresExample(clientMethod)) {
            ret = new ArrayList<>();

            List<MethodParameter> methodParameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : clientMethod.getProxyMethod().getExamples().entrySet()) {
                logger.info("Parse client method example '{}'", entry.getKey());

                FluentClientMethodExample collectionMethodExample =
                        parseMethodForExample(methodGroup, clientMethod, methodParameters, entry.getKey(), entry.getValue());
                ret.add(collectionMethodExample);
            }
        }
        return ret;
    }

    private static FluentCollectionMethodExample parseMethodForExample(FluentResourceCollection collection, FluentCollectionMethod collectionMethod,
                                                                       List<MethodParameter> methodParameters,
                                                                       String exampleName, ProxyMethodExample proxyMethodExample) {
        FluentCollectionMethodExample collectionMethodExample = new FluentCollectionMethodExample(exampleName,
                FluentStatic.getFluentManager(), collection, collectionMethod);

        for (MethodParameter methodParameter : methodParameters) {
            ExampleNode node = parseNodeFromParameter(proxyMethodExample, methodParameter);

            if (node.getObjectValue() == null) {
                if (methodParameter.getClientMethodParameter().getIsRequired()) {
                    logger.warn("Failed to assign sample value to required parameter '{}'", methodParameter.getClientMethodParameter().getName());
                }
            }

            ParameterExample parameterExample = new ParameterExample(node);
            collectionMethodExample.getParameters().add(parameterExample);
        }

        return collectionMethodExample;
    }

    private static FluentClientMethodExample parseMethodForExample(MethodGroupClient methodGroup, ClientMethod clientMethod,
                                                                       List<MethodParameter> methodParameters,
                                                                       String exampleName, ProxyMethodExample proxyMethodExample) {
        FluentClientMethodExample collectionMethodExample = new FluentClientMethodExample(exampleName,methodGroup, clientMethod);

        for (MethodParameter methodParameter : methodParameters) {
            ExampleNode node = parseNodeFromParameter(proxyMethodExample, methodParameter);

            if (node.getObjectValue() == null) {
                if (methodParameter.getClientMethodParameter().getIsRequired()) {
                    logger.warn("Failed to assign sample value to required parameter '{}'", methodParameter.getClientMethodParameter().getName());
                }
            }

            ParameterExample parameterExample = new ParameterExample(node);
            collectionMethodExample.getParameters().add(parameterExample);
        }

        return collectionMethodExample;
    }

    private static List<FluentResourceCreateExample> parseResourceCreate(FluentResourceCollection collection, ResourceCreate resourceCreate) {
        List<FluentResourceCreateExample> ret = null;

        final boolean methodIsCreateOrUpdate = methodIsCreateOrUpdate(resourceCreate.getResourceModel());

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
                    if (methodIsCreateOrUpdate && exampleIsUpdate(entry.getKey())) {
                        // likely a resource update example
                        break;
                    }

                    logger.info("Parse resource create example '{}'", entry.getKey());

                    ProxyMethodExample example = entry.getValue();
                    FluentResourceCreateExample resourceCreateExample = new FluentResourceCreateExample(entry.getKey(),
                            FluentStatic.getFluentManager(), collection, resourceCreate);

                    FluentDefineMethod defineMethod = resourceCreate.getDefineMethod();
                    ExampleNode defineNode = null;
                    if (defineMethod.getMethodParameter() != null) {
                        MethodParameter methodParameter = findMethodParameter(methodParameters, defineMethod.getMethodParameter());
                        defineNode = parseNodeFromParameter(example, methodParameter);

                        if (defineNode.getObjectValue() == null) {
                            logger.warn("Failed to assign sample value to define method '{}'", defineMethod.getName());
                        }
                    }
                    resourceCreateExample.getParameters().add(new ParameterExample(defineMethod, defineNode));

                    for (DefinitionStage stage : resourceCreate.getDefinitionStages()) {
                        List<FluentMethod> fluentMethods = stage.getMethods();
                        if (!fluentMethods.isEmpty()) {
                            FluentMethod fluentMethod = fluentMethods.iterator().next();
                            List<ExampleNode> exampleNodes = new ArrayList<>();

                            if (stage instanceof DefinitionStageBlank || stage instanceof DefinitionStageCreate) {
                                // blank and create stage does not have parameter
                            } else if (stage instanceof DefinitionStageParent) {
                                List<MethodParameter> parameters = fluentMethod.getParameters().stream()
                                        .map(p -> findMethodParameter(methodParameters, p))
                                        .collect(Collectors.toList());
                                exampleNodes.addAll(parameters.stream()
                                        .map(p -> parseNodeFromParameter(example, p))
                                        .collect(Collectors.toList()));
                            } else if (stage instanceof DefinitionStageMisc) {
                                DefinitionStageMisc miscStage = (DefinitionStageMisc) stage;
                                MethodParameter methodParameter = findMethodParameter(methodParameters, miscStage.getMethodParameter());
                                ExampleNode node = parseNodeFromParameter(example, methodParameter);

                                if (stage.isMandatoryStage() || !node.isNull()) {
                                    exampleNodes.add(node);
                                }
                            } else {
                                ModelProperty modelProperty = stage.getModelProperty();
                                if (modelProperty != null) {
                                    ExampleNode node = parseNodeFromModelProperty(example, requestBodyParameter, requestBodyClientModel, modelProperty);

                                    if (stage.isMandatoryStage() || !node.isNull()) {
                                        exampleNodes.add(node);
                                    }
                                }
                            }

                            if (exampleNodes.stream().anyMatch(ExampleNode::isNull)) {
                                if (stage.isMandatoryStage()) {
                                    logger.warn("Failed to assign sample value to required stage '{}'", stage.getName());
                                }
                            }

                            if (!exampleNodes.isEmpty()) {
                                resourceCreateExample.getParameters().add(new ParameterExample(fluentMethod, exampleNodes));
                            }
                        }
                    }

                    ret.add(resourceCreateExample);
                }
            }
        }
        return ret;
    }

    private static List<FluentResourceUpdateExample> parseResourceUpdate(FluentResourceCollection collection, ResourceUpdate resourceUpdate) {
        List<FluentResourceUpdateExample> ret = null;

        final boolean methodIsCreateOrUpdate = methodIsCreateOrUpdate(resourceUpdate.getResourceModel());
        FluentCollectionMethod resourceGetMethod = null;
        if (resourceUpdate.getResourceModel().getResourceRefresh() != null) {
            resourceGetMethod = resourceUpdate.getResourceModel().getResourceRefresh().getMethodReferences().stream()
                    .filter(m -> m.getInnerClientMethod().getParameters().stream().anyMatch(p -> ClassType.Context.equals(p.getClientType())))
                    .findFirst().orElse(null);
        }
        if (resourceGetMethod == null) {
            // 'get' method not found
            return null;
        }
        List<MethodParameter> resourceGetMethodParameters = getParameters(resourceGetMethod.getInnerClientMethod());

        List<FluentCollectionMethod> collectionMethods = resourceUpdate.getMethodReferences();
        for (FluentCollectionMethod collectionMethod : collectionMethods) {
            ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
            if (collectionMethod.getInnerClientMethod().getProxyMethod().getExamples() != null && requiresExample(clientMethod)) {
                if (ret == null) {
                    ret = new ArrayList<>();
                }

                List<MethodParameter> methodParameters = getParameters(clientMethod);
                ClientModel requestBodyClientModel = resourceUpdate.getRequestBodyParameterModel();
                MethodParameter requestBodyParameter = methodParameters.stream()
                        .filter(p -> p.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.Body)
                        .findFirst().orElse(null);

                for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                    if (methodIsCreateOrUpdate && !exampleIsUpdate(entry.getKey())) {
                        // likely not a resource update example
                        break;
                    }

                    logger.info("Parse resource update example '{}'", entry.getKey());

                    ProxyMethodExample example = entry.getValue();
                    FluentCollectionMethodExample resourceGetExample =
                            parseMethodForExample(collection, resourceGetMethod, resourceGetMethodParameters, entry.getKey(), example);
                    FluentResourceUpdateExample resourceUpdateExample = new FluentResourceUpdateExample(entry.getKey(),
                            FluentStatic.getFluentManager(), collection, resourceUpdate, resourceGetExample);

                    for (UpdateStage stage : resourceUpdate.getUpdateStages()) {
                        List<FluentMethod> fluentMethods = stage.getMethods();
                        if (!fluentMethods.isEmpty()) {
                            FluentMethod fluentMethod = fluentMethods.iterator().next();
                            List<ExampleNode> exampleNodes = new ArrayList<>();

                            if (stage instanceof UpdateStageApply) {
                                // apply stage does not have parameter
                            } else if (stage instanceof UpdateStageMisc) {
                                UpdateStageMisc miscStage = (UpdateStageMisc) stage;
                                MethodParameter methodParameter = findMethodParameter(methodParameters, miscStage.getMethodParameter());
                                ExampleNode node = parseNodeFromParameter(example, methodParameter);

                                if (!node.isNull()) {
                                    exampleNodes.add(node);
                                }
                            } else {
                                ModelProperty modelProperty = stage.getModelProperty();
                                if (modelProperty != null) {
                                    ExampleNode node = parseNodeFromModelProperty(example, requestBodyParameter, requestBodyClientModel, modelProperty);

                                    if (!node.isNull()) {
                                        exampleNodes.add(node);
                                    }
                                }
                            }

                            if (!exampleNodes.isEmpty()) {
                                resourceUpdateExample.getParameters().add(new ParameterExample(fluentMethod, exampleNodes));
                            }
                        }
                    }

                    ret.add(resourceUpdateExample);
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
                node = new LiteralNode(ClassType.Context, "").setLiteralsValue("");
            } else {
                node = new LiteralNode(methodParameter.getClientMethodParameter().getClientType(), null);
            }
        } else {
            node = parseNodeFromMethodParameter(methodParameter, parameterValue.getObjectValue());
        }
        return node;
    }

    private static ExampleNode parseNodeFromModelProperty(ProxyMethodExample example, MethodParameter methodParameter,
                                                          ClientModel clientModel, ModelProperty modelProperty) {
        String serializedName = methodParameter.getProxyMethodParameter().getName();

        ProxyMethodExample.ParameterValue parameterValue = findParameter(example, serializedName);
        ExampleNode node;
        if (parameterValue == null) {
            node = new LiteralNode(modelProperty.getClientType(), null);
        } else {
            List<String> jsonPropertyNames = modelProperty.getSerializedNames();

            Object childObjectValue = getChildObjectValue(jsonPropertyNames, parameterValue.getObjectValue());
            if (childObjectValue != null) {
                node = parseNode(modelProperty.getClientType(), childObjectValue);
            } else {
                node = new LiteralNode(modelProperty.getClientType(), null);
            }
        }
        return node;
    }

    private static ExampleNode parseNodeFromMethodParameter(MethodParameter methodParameter, Object objectValue) {
        IType type = methodParameter.getClientMethodParameter().getClientType();
        if (methodParameter.getProxyMethodParameter().getCollectionFormat() != null && type instanceof ListType && objectValue instanceof String) {
            // handle parameter style

            IType elementType = ((ListType) type).getElementType();
            ListNode listNode = new ListNode(elementType, objectValue);
            String value = (String) objectValue;

            CollectionFormat collectionFormat = methodParameter.getProxyMethodParameter().getCollectionFormat();
            List<String> elements;
            switch (collectionFormat) {
                case CSV:
                    elements = Arrays.asList(value.split(Pattern.quote(",")));
                    break;
                case SSV:
                    elements = Arrays.asList(value.split(Pattern.quote(" ")));
                    break;
                case PIPES:
                    elements = Arrays.asList(value.split(Pattern.quote("|")));
                    break;
                case TSV:
                    elements = Arrays.asList(value.split(Pattern.quote("\t")));
                    break;
                default:
                    // TODO CollectionFormat.MULTI
                    elements = Arrays.stream(value.split(Pattern.quote(","))).collect(Collectors.toList());
                    logger.error("Parameter style '{}' is not supported, fallback to CSV", collectionFormat);
            }
            for (String childObjectValue : elements) {
                ExampleNode childNode = parseNode(elementType, childObjectValue);
                listNode.getChildNodes().add(childNode);
            }
            return listNode;
        } else {
            return parseNode(type, objectValue);
        }
    }

    @SuppressWarnings("unchecked")
    private static ExampleNode parseNode(IType type, Object objectValue) {
        ExampleNode node;
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
            for (Map.Entry<String, Object> entry : dict.entrySet()) {
                ExampleNode childNode = parseNode(elementType, entry.getValue());
                node.getChildNodes().add(childNode);
                mapNode.getKeys().add(entry.getKey());
            }
        } else if (type == ClassType.Object) {
            node = new ObjectNode(type, objectValue);
        } else if (type instanceof ClassType && objectValue instanceof Map) {
            ClientModel model = FluentUtils.getClientModel(((ClassType) type).getName());
            if (model != null) {
                if (model.getIsPolymorphic()) {
                    // polymorphic, need to get the correct subclass from discriminator
                    String serializedName = model.getPolymorphicDiscriminator();
                    List<String> jsonPropertyNames = Collections.singletonList(serializedName);
                    if (model.getNeedsFlatten()) {
                        jsonPropertyNames = FluentUtils.splitFlattenedSerializedName(serializedName);
                    }

                    Object childObjectValue = getChildObjectValue(jsonPropertyNames, objectValue);
                    if (childObjectValue instanceof String) {
                        String discriminatorValue = (String) childObjectValue;
                        ClientModel directiveModel = model.getDerivedModels().stream()
                                .filter(m -> discriminatorValue.equalsIgnoreCase(m.getSerializedName()))
                                .findFirst().orElse(null);
                        if (directiveModel != null) {
                            // use the subclass
                            type = directiveModel.getType();
                            model = directiveModel;
                        } else {
                            logger.warn("Failed to find the subclass with discriminator value '{}'", discriminatorValue);
                        }
                    } else {
                        logger.warn("Failed to find the sample value for discriminator property '{}'", serializedName);
                    }
                }

                ClientModelNode clientModelNode = new ClientModelNode(type, objectValue).setClientModel(model);
                node = clientModelNode;

                for (ModelProperty modelProperty : getWritablePropertiesIncludeSuperclass(model)) {
                    List<String> jsonPropertyNames = modelProperty.getSerializedNames();

                    Object childObjectValue = getChildObjectValue(jsonPropertyNames, objectValue);
                    if (childObjectValue != null) {
                        ExampleNode childNode = parseNode(modelProperty.getClientType(), childObjectValue);
                        node.getChildNodes().add(childNode);
                        clientModelNode.getClientModelProperties().put(childNode, modelProperty);
                    }
                }
            } else {
                throw new IllegalStateException("model type not found for type " + type + " and value " + objectValue);
            }
        } else if (objectValue == null) {
            node = null;
        } else {
            LiteralNode literalNode = new LiteralNode(type, objectValue);
            node = literalNode;

            literalNode.setLiteralsValue(objectValue.toString());
        }
        return node;
    }

    private static Object getChildObjectValue(List<String> jsonPropertyNames, Object objectValue) {
        boolean found = true;
        Object childObjectValue = objectValue;
        for (String name : jsonPropertyNames) {
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
        return found ? childObjectValue : null;
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

    private static List<ModelProperty> getWritablePropertiesIncludeSuperclass(ClientModel model) {
        Map<String, ModelProperty> propertiesMap = new LinkedHashMap<>();
        List<ModelProperty> properties = new ArrayList<>();

        List<ClientModel> parentModels = new ArrayList<>();
        String parentModelName = model.getParentModelName();
        while (!CoreUtils.isNullOrEmpty(parentModelName)) {
            ClientModel parentModel = FluentUtils.getClientModel(parentModelName);
            if (parentModel != null) {
                parentModels.add(parentModel);
            }
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }

        List<List<ModelProperty>> propertiesFromTypeAndParents = new ArrayList<>();
        propertiesFromTypeAndParents.add(new ArrayList<>());
        model.getAccessibleProperties().forEach(p -> {
            ModelProperty modelProperty = ModelProperty.ofClientModelProperty(p);
            if (propertiesMap.putIfAbsent(modelProperty.getName(), modelProperty) == null) {
                propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(modelProperty);
            }
        });

        for (ClientModel parent : parentModels) {
            propertiesFromTypeAndParents.add(new ArrayList<>());

            parent.getAccessibleProperties().forEach(p -> {
                ModelProperty modelProperty = ModelProperty.ofClientModelProperty(p);
                if (propertiesMap.putIfAbsent(modelProperty.getName(), modelProperty) == null) {
                    propertiesFromTypeAndParents.get(propertiesFromTypeAndParents.size() - 1).add(modelProperty);
                }
            });
        }

        Collections.reverse(propertiesFromTypeAndParents);
        for (List<ModelProperty> properties1 : propertiesFromTypeAndParents) {
            properties.addAll(properties1);
        }

        return properties.stream()
                .filter(p -> !p.isReadOnly() && !p.isConstant())
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

    private static boolean exampleIsUpdate(String name) {
        name = name.toLowerCase(Locale.ROOT);
        return name.contains("update") && !name.contains("create");
    }

    private static boolean methodIsCreateOrUpdate(FluentResourceModel resourceModel) {
        return resourceModel.getResourceCreate() != null && resourceModel.getResourceUpdate() != null
                && Objects.equals(resourceModel.getResourceCreate().getMethodReferences().iterator().next().getMethodName(), resourceModel.getResourceUpdate().getMethodReferences().iterator().next().getMethodName());
    }
}
