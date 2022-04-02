// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.Javagen;
import com.azure.autorest.extension.base.plugin.PluginLogger;
import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.autorest.model.clientmodel.ModelProperty;
import com.azure.autorest.model.clientmodel.examplemodel.ClientModelNode;
import com.azure.autorest.model.clientmodel.examplemodel.ExampleNode;
import com.azure.autorest.model.clientmodel.examplemodel.ListNode;
import com.azure.autorest.model.clientmodel.examplemodel.LiteralNode;
import com.azure.autorest.model.clientmodel.examplemodel.MapNode;
import com.azure.autorest.model.clientmodel.examplemodel.ObjectNode;
import com.azure.core.util.CoreUtils;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ModelExampleUtil {

    private static final Logger logger = new PluginLogger(Javagen.getPluginInstance(), ModelExampleUtil.class);

    @SuppressWarnings("unchecked")
    public static ExampleNode parseNode(IType type, Object objectValue) {
        ExampleNode node;
        if (type instanceof ListType) {
            IType elementType = ((ListType) type).getElementType();
            if (objectValue instanceof List) {
                ListNode listNode = new ListNode(elementType, objectValue);
                node = listNode;

                List<Object> elements = (List<Object>) objectValue;
                for (Object childObjectValue : elements) {
                    ExampleNode childNode = parseNode(elementType, childObjectValue);
                    node.getChildNodes().add(childNode);
                }
            } else {
                logger.error("Example value is not List type: {}", objectValue);
                node = new ListNode(elementType, null);
            }
        } else if (type instanceof MapType) {
            IType elementType = ((MapType) type).getValueType();
            if (objectValue instanceof Map) {
                MapNode mapNode = new MapNode(elementType, objectValue);
                node = mapNode;

                Map<String, Object> dict = (Map<String, Object>) objectValue;
                for (Map.Entry<String, Object> entry : dict.entrySet()) {
                    ExampleNode childNode = parseNode(elementType, entry.getValue());
                    node.getChildNodes().add(childNode);
                    mapNode.getKeys().add(entry.getKey());
                }
            } else {
                logger.error("Example value is not Map type: {}", objectValue);
                node = new MapNode(elementType, null);
            }
        } else if (type == ClassType.Object) {
            node = new ObjectNode(type, objectValue);
        } else if (type instanceof ClassType && objectValue instanceof Map) {
            ClientModel model = getClientModel(((ClassType) type).getName());
            if (model != null) {
                if (model.getIsPolymorphic()) {
                    // polymorphic, need to get the correct subclass from discriminator
                    String serializedName = model.getPolymorphicDiscriminator();
                    List<String> jsonPropertyNames = Collections.singletonList(serializedName);
                    if (model.getNeedsFlatten()) {
                        jsonPropertyNames = ModelUtil.splitFlattenedSerializedName(serializedName);
                    }

                    Object childObjectValue = getChildObjectValue(jsonPropertyNames, objectValue);
                    if (childObjectValue instanceof String) {
                        String discriminatorValue = (String) childObjectValue;
                        ClientModel derivedModel = getDerivedModel(model, discriminatorValue);
                        if (derivedModel != null) {
                            // use the subclass
                            type = derivedModel.getType();
                            model = derivedModel;
                        } else {
                            logger.warn("Failed to find the subclass with discriminator value '{}'", discriminatorValue);
                        }
                    } else {
                        logger.warn("Failed to find the sample value for discriminator property '{}'", serializedName);
                    }
                }

                ClientModelNode clientModelNode = new ClientModelNode(type, objectValue).setClientModel(model);
                node = clientModelNode;

                List<ModelProperty> modelProperties = getWritablePropertiesIncludeSuperclass(model);
                for (ModelProperty modelProperty : modelProperties) {
                    List<String> jsonPropertyNames = modelProperty.getSerializedNames();

                    Object childObjectValue = getChildObjectValue(jsonPropertyNames, objectValue);
                    if (childObjectValue != null) {
                        ExampleNode childNode = parseNode(modelProperty.getClientType(), childObjectValue);
                        node.getChildNodes().add(childNode);
                        clientModelNode.getClientModelProperties().put(childNode, modelProperty);
                    }
                }

                // additional properties
                ModelProperty additionalPropertiesProperty = getAdditionalPropertiesProperty(model);
                if (additionalPropertiesProperty != null) {
                    // properties already defined in model
                    Set<String> propertySerializedNames = modelProperties.stream()
                            .map(p -> p.getSerializedNames().iterator().next())
                            .collect(Collectors.toSet());
                    // the remaining properties in json
                    Map<String, Object> remainingValues = ((Map<String, Object>) objectValue).entrySet().stream()
                            .filter(e -> !propertySerializedNames.contains(e.getKey()))
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

                    ExampleNode childNode = parseNode(additionalPropertiesProperty.getClientType(), remainingValues);
                    node.getChildNodes().add(childNode);
                    clientModelNode.getClientModelProperties().put(childNode, additionalPropertiesProperty);
                }
            } else {
                throw new IllegalStateException("Model type not found for type " + type + " and value " + objectValue);
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

    public static Object getChildObjectValue(List<String> jsonPropertyNames, Object objectValue) {
        boolean found = true;
        Object childObjectValue = objectValue;
        // walk the sequence of serialized names
        for (String name : jsonPropertyNames) {
            if (name.isEmpty()) {
                found = false;
                break;
            }

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

    private static ModelProperty getAdditionalPropertiesProperty(ClientModel model) {
        ModelProperty modelProperty = null;
        ClientModelProperty property = model.getProperties().stream()
                .filter(ClientModelProperty::isAdditionalProperties)
                .findFirst().orElse(null);
        if (property != null && property.getClientType() instanceof MapType) {
            modelProperty = ModelProperty.ofClientModelProperty(property);
        }
        return modelProperty;
    }

    private static List<ModelProperty> getWritablePropertiesIncludeSuperclass(ClientModel model) {
        Map<String, ModelProperty> propertiesMap = new LinkedHashMap<>();
        List<ModelProperty> properties = new ArrayList<>();

        List<ClientModel> parentModels = new ArrayList<>();
        String parentModelName = model.getParentModelName();
        while (!CoreUtils.isNullOrEmpty(parentModelName)) {
            ClientModel parentModel = getClientModel(parentModelName);
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

    private static ClientModel getDerivedModel(ClientModel model, String discriminatorValue) {
        if (discriminatorValue.equals(model.getSerializedName())) {
            return model;
        }

        // depth first search
        if (model.getDerivedModels() != null) {
            for (ClientModel childModel : model.getDerivedModels()) {
                if (discriminatorValue.equalsIgnoreCase(childModel.getSerializedName())) {
                    // found
                    return childModel;
                } else if (childModel.getDerivedModels() != null) {
                    // recursive
                    ClientModel childModel2 = getDerivedModel(childModel, discriminatorValue);
                    if (childModel2 != null) {
                        return childModel2;
                    }
                }
            }
        }

        // not found
        return null;
    }

    private static Function<String, ClientModel> getClientModelFunction = ClientModels.Instance::getModel;

    public static void setGetClientModelFunction(Function<String, ClientModel> function) {
        getClientModelFunction = function;
    }

    private static ClientModel getClientModel(String name) {
        return getClientModelFunction.apply(name);
    }
}
