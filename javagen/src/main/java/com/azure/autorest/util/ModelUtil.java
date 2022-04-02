// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public class ModelUtil {

    private static final Pattern SPLIT_FLATTEN_PROPERTY_PATTERN = Pattern.compile("((?<!\\\\))\\.");

    /**
     * Split possible flattened serialized property name to components.
     *
     * @param serializedName the serialized property name belongs to either model or property that has {@code @JsonFlatten} annotation.
     * @return the components of the serialized property names
     */
    public static List<String> splitFlattenedSerializedName(String serializedName) {
        if (serializedName == null) {
            return Collections.emptyList();
        }

        String[] values = SPLIT_FLATTEN_PROPERTY_PATTERN.split(serializedName);
        for (int i = 0; i < values.length; ++i) {
            values[i] = values[i].replace("\\\\.", ".");
        }
        return Arrays.asList(values);
    }
}
