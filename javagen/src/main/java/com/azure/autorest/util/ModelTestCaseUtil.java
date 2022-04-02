// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.ClientModels;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.core.util.CoreUtils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class ModelTestCaseUtil {

    private static final class Configuration {
        private float nullableProbability = 0.0f;
    }

    private static final Random RANDOM = new Random(3);
    private static final Configuration CONFIGURATION = new Configuration();

    private static Object jsonFromType(IType type) {
        if (type.asNullable() == ClassType.Integer) {
            return RANDOM.nextInt() & Integer.MAX_VALUE;
        } else if (type.asNullable() == ClassType.Long) {
            return RANDOM.nextLong() & Long.MAX_VALUE;
        } else if (type.asNullable() == ClassType.Float) {
            return RANDOM.nextFloat();
        } else if (type.asNullable() == ClassType.Double) {
            return RANDOM.nextDouble();
        } else if (type.asNullable() == ClassType.Boolean) {
            return RANDOM.nextBoolean();
        } else if (type == ClassType.String) {
            return randomString();
        } else if (type instanceof ClassType) {
            ClientModel model = ClientModels.Instance.getModel(((ClassType) type).getName());
            if (model != null) {
                return jsonFromModel(model);
            }
        }
        return null;
    }

    public static Map<String, Object> jsonFromModel(ClientModel model) {
        Map<String, Object> jsonObject = new LinkedHashMap<>();

        // polymorphism
        if (model.getIsPolymorphic()) {
            jsonObject.put(model.getPolymorphicDiscriminator(), model.getSerializedName());
        }

        // class
        for (ClientModelProperty property : model.getProperties()) {
            addForProperty(jsonObject, property, model.getNeedsFlatten());
        }

        // superclasses
        String parentModelName = model.getParentModelName();
        while (!CoreUtils.isNullOrEmpty(parentModelName)) {
            ClientModel parentModel = ClientModels.Instance.getModel(parentModelName);
            if (parentModel != null) {
                for (ClientModelProperty property : parentModel.getProperties()) {
                    addForProperty(jsonObject, property, parentModel.getNeedsFlatten());
                }
            }
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }

        return jsonObject;
    }

    private static void addForProperty(Map<String, Object> jsonObject,
                                       ClientModelProperty property, boolean modelNeedsFlatten) {
        Object value = null;
        if (property.getIsConstant()) {
            // TODO skip for now, as the property.getDefaultValue() is the code, not the raw data
            //value = property.getDefaultValue();
            return;
        } else {
            if (property.isRequired() || RANDOM.nextFloat() > CONFIGURATION.nullableProbability) {
                value = jsonFromType(property.getWireType());
            }
        }

        if (value != null) {
            List<String> serializedNames;
            if (modelNeedsFlatten || property.getNeedsFlatten()) {
                serializedNames = ModelUtil.splitFlattenedSerializedName(property.getSerializedName());
            } else {
                serializedNames = Collections.singletonList(property.getSerializedName());
            }
            addToJsonObject(jsonObject, serializedNames, value);
        }
    }

    private static void addToJsonObject(Map<String, Object> jsonObject, List<String> serializedNames, Object value) {
        if (serializedNames.size() == 1) {
            jsonObject.put(serializedNames.iterator().next(), value);
        } else {
            String serializedName = serializedNames.iterator().next();
            serializedNames.remove(0);
            if (jsonObject.containsKey(serializedName)) {
                Object nextJsonObject = jsonObject.get(serializedName);
                if (nextJsonObject instanceof Map) {
                    addToJsonObject((Map<String, Object>) nextJsonObject, serializedNames, value);
                }
            } else {
                Map<String, Object> nextJsonObject = new LinkedHashMap<>();
                jsonObject.put(serializedName, nextJsonObject);
                addToJsonObject(nextJsonObject, serializedNames, value);
            }
        }
    }

    private static String randomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
}
