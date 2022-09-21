// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import com.azure.autorest.model.clientmodel.ClassType;
import com.azure.autorest.model.clientmodel.ClientEnumValue;
import com.azure.autorest.model.clientmodel.ClientModel;
import com.azure.autorest.model.clientmodel.ClientModelProperty;
import com.azure.autorest.model.clientmodel.EnumType;
import com.azure.autorest.model.clientmodel.IType;
import com.azure.autorest.model.clientmodel.ListType;
import com.azure.autorest.model.clientmodel.MapType;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ModelTestCaseUtil {

    private static final class Configuration {
        private final float nullableProbability = 0.0f;

        private final int maxDepth = 5;

        private final int maxStringLen = 16 + 1;
        private final int maxList = 4 + 1;
        private final int maxDict = 4 + 1;
    }

    private static final Random RANDOM = new Random(3);
    private static final Configuration CONFIGURATION = new Configuration();

    /**
     * Compose a random JSON object according to the structure of client model.
     *
     * @param model the client model
     * @return the JSON object as Map
     */
    public static Map<String, Object> jsonFromModel(ClientModel model) {
        return jsonFromModel(0, model);
    }

    private static Map<String, Object> jsonFromModel(int depth, ClientModel model) {
        if (depth > CONFIGURATION.maxDepth) {
            // abort
            return null;
        }

        Map<String, Object> jsonObject = new LinkedHashMap<>();

        // polymorphism
        if (model.isPolymorphic()) {
            addForProperty(jsonObject,
                    model.getPolymorphicDiscriminator(), model.getNeedsFlatten(),
                    model.getSerializedName());
        }

        // class
        for (ClientModelProperty property : model.getProperties()) {
            addForProperty(depth, jsonObject, property, model.getNeedsFlatten());
        }

        // superclasses
        String parentModelName = model.getParentModelName();
        while (!CoreUtils.isNullOrEmpty(parentModelName)) {
            ClientModel parentModel = ClientModelUtil.getClientModel(parentModelName);
            if (parentModel != null) {
                for (ClientModelProperty property : parentModel.getProperties()) {
                    addForProperty(depth, jsonObject, property, parentModel.getNeedsFlatten());
                }
            }
            parentModelName = parentModel == null ? null : parentModel.getParentModelName();
        }

        return jsonObject;
    }

    public static Object jsonFromType(int depth, IType type) {
        if (type.asNullable() == ClassType.Integer) {
            return RANDOM.nextInt() & Integer.MAX_VALUE;
        } else if (type.asNullable() == ClassType.Long) {
            return RANDOM.nextLong() & Long.MAX_VALUE;
        } else if (type.asNullable() == ClassType.Float) {
            return RANDOM.nextFloat() * 100;
        } else if (type.asNullable() == ClassType.Double) {
            return RANDOM.nextDouble() * 100;
        } else if (type.asNullable() == ClassType.Boolean) {
            return RANDOM.nextBoolean();
        } else if (type == ClassType.String) {
            return randomString();
        } else if (type == ClassType.DateTime) {
            return randomDateTime().toString();
        } else if (type == ClassType.DateTimeRfc1123) {
            return DateTimeRfc1123.toRfc1123String(randomDateTime());
        } else if (type == ClassType.Duration) {
            Duration duration = Duration.parse("PT0S");
            duration = duration.plusSeconds(RANDOM.nextInt(10 * 24 * 60 * 60));
            return duration.toString();
        } else if (type instanceof EnumType) {
            IType elementType = ((EnumType) type).getElementType();
            List<String> values = ((EnumType) type).getValues().stream().map(ClientEnumValue::getValue).collect(Collectors.toList());
            int index = RANDOM.nextInt(values.size());
            String value = values.get(index);
            if (elementType.asNullable() == ClassType.Integer) {
                return Integer.valueOf(value);
            } else if (elementType.asNullable() == ClassType.Long) {
                return Long.valueOf(value);
            } else if (elementType.asNullable() == ClassType.Float) {
                return Float.valueOf(value);
            } else if (elementType.asNullable() == ClassType.Double) {
                return Double.valueOf(value);
            } else if (elementType.asNullable() == ClassType.Boolean) {
                return Boolean.valueOf(value);
            } else if (elementType == ClassType.String) {
                return value;
            }
        } else if (type instanceof ListType) {
            List<Object> list = new ArrayList<>();
            if (depth <= CONFIGURATION.maxDepth) {
                IType elementType = ((ListType) type).getElementType();
                int count = RANDOM.nextInt(CONFIGURATION.maxList - 1) + 1;
                for (int i = 0; i < count; ++i) {
                    Object element = jsonFromType(depth + 1, elementType);
                    if (element != null) {
                        list.add(element);
                    }
                }
            } // else abort
            return list;
        } else if (type instanceof MapType) {
            Map<String, Object> map = new LinkedHashMap<>();
            if (depth <= CONFIGURATION.maxDepth) {
                IType elementType = ((MapType) type).getValueType();
                int count = RANDOM.nextInt(CONFIGURATION.maxDict - 1) + 1;
                for (int i = 0; i < count; ++i) {
                    Object element = jsonFromType(depth + 1, elementType);
                    if (element != null) {
                        map.put(randomString(), element);
                    }
                }
            } // else abort
            return map;
        } else if (type instanceof ClassType) {
            ClientModel model = ClientModelUtil.getClientModel(((ClassType) type).getName());
            if (model != null) {
                return jsonFromModel(depth + 1, model);
            }
        }
        return null;
    }

    private static void addForProperty(int depth, Map<String, Object> jsonObject,
                                       ClientModelProperty property, boolean modelNeedsFlatten) {
        Object value = null;
        if (property.isConstant()) {
            // TODO (weidxu): skip for now, as the property.getDefaultValue() is the code, not the raw data
            //value = property.getDefaultValue();
            return;
        } else {
            if (property.isRequired() || RANDOM.nextFloat() > CONFIGURATION.nullableProbability) {
                value = jsonFromType(depth + 1, property.getWireType());
            }
        }

        addForProperty(jsonObject,
                property.getSerializedName(), modelNeedsFlatten || property.getNeedsFlatten(),
                value);
    }

    private static void addForProperty(Map<String, Object> jsonObject,
                                       String serializedName, boolean modelNeedsFlatten,
                                       Object value) {
        if (value != null) {
            List<String> serializedNames;
            if (modelNeedsFlatten) {
                serializedNames = ClientModelUtil.splitFlattenedSerializedName(serializedName);
            } else {
                serializedNames = Collections.singletonList(serializedName);
            }
            addToJsonObject(jsonObject, serializedNames, value);
        }
    }

    @SuppressWarnings("unchecked")
    private static void addToJsonObject(Map<String, Object> jsonObject, List<String> serializedNames, Object value) {
        if (serializedNames.size() == 1) {
            jsonObject.put(serializedNames.iterator().next(), value);
        } else {
            serializedNames = new ArrayList<>(serializedNames);
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
        int targetStringLength = RANDOM.nextInt(CONFIGURATION.maxStringLen - 1) + 1;

        return RANDOM.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private static OffsetDateTime randomDateTime() {
        OffsetDateTime time = OffsetDateTime.parse("2020-12-20T00:00:00.000Z");
        time = time.plusSeconds(RANDOM.nextInt(356 * 24 * 60 * 60));
        return time;
    }
}
