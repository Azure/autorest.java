// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

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
import com.azure.autorest.model.clientmodel.ModelProperty;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentClientMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentCollectionMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentMethodExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceCreateExample;
import com.azure.autorest.fluent.model.clientmodel.examplemodel.FluentResourceUpdateExample;
import com.azure.autorest.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.model.clientmodel.examplemodel.LiteralNode;
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
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MethodGroupClient;
import com.azure.autorest.model.clientmodel.ProxyMethodExample;
import com.azure.autorest.model.clientmodel.ProxyMethodParameter;
import com.azure.autorest.util.CodeNamer;
import com.azure.autorest.util.ModelExampleUtil;
import com.azure.core.util.serializer.CollectionFormat;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ExampleParser {

    private static final Logger LOGGER = new PluginLogger(FluentGen.getPluginInstance(), ExampleParser.class);

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
                    this.aggregateExamples ? null : exampleName);
            examples.put(name, example);
        }
        return example;
    }

    private static List<FluentCollectionMethodExample> parseMethod(FluentResourceCollection collection, FluentCollectionMethod collectionMethod) {
        List<FluentCollectionMethodExample> ret = null;

        ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
        if (FluentUtils.validToGenerateExample(clientMethod)) {
            ret = new ArrayList<>();

            List<MethodParameter> methodParameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                LOGGER.info("Parse collection method example '{}'", entry.getKey());

                FluentCollectionMethodExample collectionMethodExample =
                        parseMethodForExample(collection, collectionMethod, methodParameters, entry.getKey(), entry.getValue());
                ret.add(collectionMethodExample);
            }
        }
        return ret;
    }

    private static List<FluentClientMethodExample> parseMethod(MethodGroupClient methodGroup, ClientMethod clientMethod) {
        List<FluentClientMethodExample> ret = null;

        if (FluentUtils.validToGenerateExample(clientMethod)) {
            ret = new ArrayList<>();

            List<MethodParameter> methodParameters = getParameters(clientMethod);
            for (Map.Entry<String, ProxyMethodExample> entry : clientMethod.getProxyMethod().getExamples().entrySet()) {
                LOGGER.info("Parse client method example '{}'", entry.getKey());

                FluentClientMethodExample clientMethodExample =
                        parseMethodForExample(methodGroup, clientMethod, methodParameters, entry.getKey(), entry.getValue());
                ret.add(clientMethodExample);
            }
        }
        return ret;
    }

    protected static FluentCollectionMethodExample parseMethodForExample(FluentResourceCollection collection, FluentCollectionMethod collectionMethod,
                                                                       List<MethodParameter> methodParameters,
                                                                       String exampleName, ProxyMethodExample proxyMethodExample) {
        FluentCollectionMethodExample collectionMethodExample = new FluentCollectionMethodExample(
                exampleName, proxyMethodExample.getRelativeOriginalFileName(),
                FluentStatic.getFluentManager(), collection, collectionMethod);

        addMethodParametersToMethodExample(methodParameters, proxyMethodExample, collectionMethodExample);
        return collectionMethodExample;
    }

    public static FluentCollectionMethodExample parseMethodExample(FluentResourceCollection resourceCollection, Collection<FluentCollectionMethod> collectionMethods, ProxyMethodExample example) {
        FluentCollectionMethod collectionMethod = collectionMethods.stream().filter(method -> FluentUtils.requiresExample(method.getInnerClientMethod())).findFirst().get();
        return parseMethodForExample(resourceCollection, collectionMethod, getParameters(collectionMethod.getInnerClientMethod()), example.getName(), example);
    }

    private static FluentClientMethodExample parseMethodForExample(
            MethodGroupClient methodGroup, ClientMethod clientMethod, List<MethodParameter> methodParameters,
            String exampleName, ProxyMethodExample proxyMethodExample) {

        FluentClientMethodExample clientMethodExample = new FluentClientMethodExample(
                exampleName, proxyMethodExample.getRelativeOriginalFileName(), methodGroup, clientMethod);

        addMethodParametersToMethodExample(methodParameters, proxyMethodExample, clientMethodExample);
        return clientMethodExample;
    }

    private static void addMethodParametersToMethodExample(List<MethodParameter> methodParameters, ProxyMethodExample proxyMethodExample, FluentMethodExample methodExample) {
        for (MethodParameter methodParameter : methodParameters) {
            ExampleNode node = parseNodeFromParameter(proxyMethodExample, methodParameter);

            if (node.getObjectValue() == null) {
                if (methodParameter.getClientMethodParameter().isRequired()) {
                    LOGGER.warn("Failed to assign sample value to required parameter '{}'", methodParameter.getClientMethodParameter().getName());
                }
            }

            ParameterExample parameterExample = new ParameterExample(node);
            methodExample.getParameters().add(parameterExample);
        }
    }

    private static List<FluentResourceCreateExample> parseResourceCreate(FluentResourceCollection collection, ResourceCreate resourceCreate) {
        List<FluentResourceCreateExample> ret = null;

        final boolean methodIsCreateOrUpdate = methodIsCreateOrUpdate(resourceCreate.getResourceModel());

        List<FluentCollectionMethod> collectionMethods = resourceCreate.getMethodReferences();
        for (FluentCollectionMethod collectionMethod : collectionMethods) {
            ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
            if (FluentUtils.validToGenerateExample(clientMethod)) {
                if (ret == null) {
                    ret = new ArrayList<>();
                }

                List<MethodParameter> methodParameters = getParameters(clientMethod);
                MethodParameter requestBodyParameter = findRequestBodyParameter(methodParameters);

                for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                    if (methodIsCreateOrUpdate && FluentUtils.exampleIsUpdate(entry.getKey())) {
                        // likely a resource update example
                        LOGGER.info("Skip possible resource update example '{}' in create", entry.getKey());
                        continue;
                    }

                    LOGGER.info("Parse resource create example '{}'", entry.getKey());

                    FluentResourceCreateExample resourceCreateExample = parseResourceCreate(collection, resourceCreate, entry.getValue(), methodParameters, requestBodyParameter);

                    ret.add(resourceCreateExample);
                }
            }
        }
        return ret;
    }

    protected static FluentResourceCreateExample parseResourceCreate(FluentResourceCollection collection, ResourceCreate resourceCreate, ProxyMethodExample example, List<MethodParameter> methodParameters, MethodParameter requestBodyParameter) {
        FluentResourceCreateExample resourceCreateExample = new FluentResourceCreateExample(
                example.getName(), example.getRelativeOriginalFileName(),
                FluentStatic.getFluentManager(), collection, resourceCreate);

        FluentDefineMethod defineMethod = resourceCreate.getDefineMethod();
        ExampleNode defineNode = null;
        if (defineMethod.getMethodParameter() != null) {
            MethodParameter methodParameter = findMethodParameter(methodParameters, defineMethod.getMethodParameter());
            defineNode = parseNodeFromParameter(example, methodParameter);

            if (defineNode.getObjectValue() == null) {
                LOGGER.warn("Failed to assign sample value to define method '{}'", defineMethod.getName());
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
                        ExampleNode node = parseNodeFromModelProperty(example, requestBodyParameter, resourceCreate.getRequestBodyParameterModel(), modelProperty);

                        if (stage.isMandatoryStage() || !node.isNull()) {
                            exampleNodes.add(node);
                        }
                    }
                }

                if (exampleNodes.stream().anyMatch(ExampleNode::isNull)) {
                    if (stage.isMandatoryStage()) {
                        LOGGER.warn("Failed to assign sample value to required stage '{}'", stage.getName());
                    }
                }

                if (!exampleNodes.isEmpty()) {
                    resourceCreateExample.getParameters().add(new ParameterExample(fluentMethod, exampleNodes));
                }
            }
        }
        return resourceCreateExample;
    }

    public static FluentResourceCreateExample parseResourceCreate(FluentResourceCollection resourceCollection, ResourceCreate create, ProxyMethodExample example) {
        List<MethodParameter> methodParameters = getParameters(
            create.getMethodReferences()
                .stream()
                .filter(collectionMethod-> FluentUtils.requiresExample(collectionMethod.getInnerClientMethod()))
                .findFirst().get()
                .getInnerClientMethod());
        MethodParameter requestBodyParameter = findRequestBodyParameter(methodParameters);
        return parseResourceCreate(resourceCollection, create, example, methodParameters, requestBodyParameter);
    }

    private static List<FluentResourceUpdateExample> parseResourceUpdate(FluentResourceCollection collection, ResourceUpdate resourceUpdate) {
        List<FluentResourceUpdateExample> ret = null;

        final boolean methodIsCreateOrUpdate = methodIsCreateOrUpdate(resourceUpdate.getResourceModel());
        FluentCollectionMethod resourceGetMethod = findResourceGetMethod(resourceUpdate);
        if (resourceGetMethod == null) {
            // 'get' method not found
            return null;
        }
        List<MethodParameter> resourceGetMethodParameters = getParameters(resourceGetMethod.getInnerClientMethod());

        List<FluentCollectionMethod> collectionMethods = resourceUpdate.getMethodReferences();
        for (FluentCollectionMethod collectionMethod : collectionMethods) {
            ClientMethod clientMethod = collectionMethod.getInnerClientMethod();
            if (FluentUtils.validToGenerateExample(clientMethod)) {
                if (ret == null) {
                    ret = new ArrayList<>();
                }

                List<MethodParameter> methodParameters = getParameters(clientMethod);
                MethodParameter requestBodyParameter = findRequestBodyParameter(methodParameters);

                for (Map.Entry<String, ProxyMethodExample> entry : collectionMethod.getInnerClientMethod().getProxyMethod().getExamples().entrySet()) {
                    if (methodIsCreateOrUpdate && !FluentUtils.exampleIsUpdate(entry.getKey())) {
                        // likely not a resource update example
                        LOGGER.info("Skip possible resource create example '{}' in update", entry.getKey());
                        continue;
                    }

                    LOGGER.info("Parse resource update example '{}'", entry.getKey());

                    ProxyMethodExample example = entry.getValue();
                    FluentResourceUpdateExample resourceUpdateExample = parseResourceUpdate(collection, resourceUpdate, example, resourceGetMethod, resourceGetMethodParameters, methodParameters, requestBodyParameter);

                    ret.add(resourceUpdateExample);
                }
            }
        }
        return ret;
    }

    private static FluentCollectionMethod findResourceGetMethod(ResourceUpdate resourceUpdate) {
        FluentCollectionMethod resourceGetMethod = null;
        if (resourceUpdate.getResourceModel().getResourceRefresh() != null) {
            resourceGetMethod = resourceUpdate.getResourceModel().getResourceRefresh().getMethodReferences().stream()
                    .filter(m -> m.getInnerClientMethod().getParameters().stream().anyMatch(p -> ClassType.Context.equals(p.getClientType())))
                    .findFirst().orElse(null);
        }
        return resourceGetMethod;
    }

    private static FluentResourceUpdateExample parseResourceUpdate(FluentResourceCollection collection, ResourceUpdate resourceUpdate, ProxyMethodExample example, FluentCollectionMethod resourceGetMethod, List<MethodParameter> resourceGetMethodParameters, List<MethodParameter> methodParameters, MethodParameter requestBodyParameter) {
        FluentCollectionMethodExample resourceGetExample =
                parseMethodForExample(collection, resourceGetMethod, resourceGetMethodParameters, example.getName(), example);
        FluentResourceUpdateExample resourceUpdateExample = new FluentResourceUpdateExample(
                example.getName(), example.getRelativeOriginalFileName(),
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
                        ExampleNode node = parseNodeFromModelProperty(example, requestBodyParameter, resourceUpdate.getRequestBodyParameterModel(), modelProperty);

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
        return resourceUpdateExample;
    }

    public static FluentResourceUpdateExample parseResourceUpdate(FluentResourceCollection resourceCollection, ResourceUpdate resourceUpdate, ProxyMethodExample example) {
        FluentCollectionMethod resourceGetMethod = findResourceGetMethod(resourceUpdate);
        if (resourceGetMethod == null) {
            // 'get' method not found
            return null;
        }
        List<MethodParameter> resourceGetMethodParameters = getParameters(resourceGetMethod.getInnerClientMethod());
        List<MethodParameter> methodParameters = getParameters(
            resourceUpdate.getMethodReferences()
                .stream()
                .filter(collectionMethod-> FluentUtils.requiresExample(collectionMethod.getInnerClientMethod()))
                .findFirst().get()
                .getInnerClientMethod()
        );
        MethodParameter requestBodyParameter = findRequestBodyParameter(methodParameters);
        return parseResourceUpdate(resourceCollection, resourceUpdate, example, resourceGetMethod, resourceGetMethodParameters, methodParameters, requestBodyParameter);

    }

    protected static MethodParameter findRequestBodyParameter(List<MethodParameter> methodParameters) {
        return methodParameters.stream()
                .filter(p -> p.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY)
                .findFirst().orElse(null);
    }

    private static ProxyMethodExample.ParameterValue findParameter(ProxyMethodExample example, String serializedName) {
        return example.getParameters().entrySet()
                .stream().filter(p -> p.getKey().equalsIgnoreCase(serializedName))
                .map(Map.Entry::getValue)
                .findFirst().orElse(null);
    }

    private static MethodParameter findMethodParameter(List<MethodParameter> methodParameters, ClientMethodParameter clientMethodParameter) {
        MethodParameter parameter = methodParameters.stream()
                .filter(p -> p.getClientMethodParameter() == clientMethodParameter)
                .findFirst().orElse(null);
        if (parameter == null) {
            parameter = methodParameters.stream()
                    .filter(p -> p.getClientMethodParameter().getName().equals(clientMethodParameter.getName()))
                    .findFirst().orElse(null);
        }
        return parameter;
    }

    private static ExampleNode parseNodeFromParameter(ProxyMethodExample example, MethodParameter methodParameter) {
        String serializedName = methodParameter.getSerializedName();
        if (serializedName == null && methodParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
            serializedName = methodParameter.getProxyMethodParameter().getName();
        }

        ProxyMethodExample.ParameterValue parameterValue = findParameter(example, serializedName);
        if (parameterValue == null && methodParameter.getProxyMethodParameter().getRequestParameterLocation() == RequestParameterLocation.BODY) {
            // special handling for body, as it does not have serializedName
            String paramSuffix = "Param";
            if (serializedName.endsWith(paramSuffix)) {
                // hack, remove Param, as it likely added by codegen to avoid naming conflict
                serializedName = serializedName.substring(0, serializedName.length() - paramSuffix.length());
                if (!serializedName.isEmpty()) {
                    parameterValue = findParameter(example, serializedName);
                }
            }
        }

        ExampleNode node;
        if (parameterValue == null) {
            if (ClassType.Context.equals(methodParameter.getClientMethodParameter().getClientType())) {
                node = new LiteralNode(ClassType.Context, "").setLiteralsValue("");
            } else {
                node = new LiteralNode(methodParameter.getClientMethodParameter().getClientType(), null);
            }
        } else {
            Object exampleValue = methodParameter.getClientMethodParameter().getRequestParameterLocation() == RequestParameterLocation.QUERY
                    ? parameterValue.getUnescapedQueryValue()
                    : parameterValue.getObjectValue();
            node = parseNodeFromMethodParameter(methodParameter, exampleValue);
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

            Object childObjectValue = ModelExampleUtil.getChildObjectValue(jsonPropertyNames, parameterValue.getObjectValue());
            if (childObjectValue != null) {
                node = ModelExampleUtil.parseNode(modelProperty.getClientType(), modelProperty.getWireType(), childObjectValue);
            } else {
                node = new LiteralNode(modelProperty.getClientType(), null);
            }
        }
        return node;
    }

    private static ExampleNode parseNodeFromMethodParameter(MethodParameter methodParameter, Object objectValue) {
        IType type = methodParameter.getClientMethodParameter().getClientType();
        IType wireType = methodParameter.getClientMethodParameter().getWireType();
        if (methodParameter.getProxyMethodParameter().getCollectionFormat() != null && type instanceof ListType && objectValue instanceof String) {
            // handle parameter style

            IType elementType = ((ListType) type).getElementType();
            ListNode listNode = new ListNode(elementType, objectValue);
            String value = (String) objectValue;

            CollectionFormat collectionFormat = methodParameter.getProxyMethodParameter().getCollectionFormat();
            List<String> elements;
            switch (collectionFormat) {
                case CSV:
                    elements = Arrays.asList(value.split(Pattern.quote(","), -1));
                    break;
                case SSV:
                    elements = Arrays.asList(value.split(Pattern.quote(" "), -1));
                    break;
                case PIPES:
                    elements = Arrays.asList(value.split(Pattern.quote("|"), -1));
                    break;
                case TSV:
                    elements = Arrays.asList(value.split(Pattern.quote("\t"), -1));
                    break;
                default:
                    // TODO (weidxu): CollectionFormat.MULTI
                    elements = Arrays.asList(value.split(Pattern.quote(","), -1));
                    LOGGER.error("Parameter style '{}' is not supported, fallback to CSV", collectionFormat);
                    break;
            }
            for (String childObjectValue : elements) {
                ExampleNode childNode = ModelExampleUtil.parseNode(elementType, childObjectValue);
                listNode.getChildNodes().add(childNode);
            }
            return listNode;
        } else {
            return ModelExampleUtil.parseNode(type, wireType, objectValue);
        }
    }

    protected static List<MethodParameter> getParameters(ClientMethod clientMethod) {
        Map<String, ProxyMethodParameter> proxyMethodParameterByClientParameterName = clientMethod.getProxyMethod().getParameters().stream()
                .collect(Collectors.toMap(p -> CodeNamer.getEscapedReservedClientMethodParameterName(p.getName()), Function.identity()));
        return clientMethod.getMethodParameters().stream()
                .filter(p -> proxyMethodParameterByClientParameterName.containsKey(p.getName()))
                .filter(p -> !p.isConstant() && !p.isFromClient())
                .map(p -> new MethodParameter(proxyMethodParameterByClientParameterName.get(p.getName()), p))
                .collect(Collectors.toList());
    }

    private static boolean methodIsCreateOrUpdate(FluentResourceModel resourceModel) {
        return resourceModel.getResourceCreate() != null && resourceModel.getResourceUpdate() != null
                && Objects.equals(resourceModel.getResourceCreate().getMethodReferences().iterator().next().getMethodName(), resourceModel.getResourceUpdate().getMethodReferences().iterator().next().getMethodName());
    }
}
