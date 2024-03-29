// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.model.codemodel.Client;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.util.SchemaUtil;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public final static String METHOD_POSTFIX_WITH_RESPONSE = "WithResponse";

    public static String getDefaultName(Metadata m) {
        return SchemaUtil.getDefaultName(m);
    }

    public static String getJavaName(Metadata m) {
        return SchemaUtil.getJavaName(m);
    }

    public static boolean nonFlattenedProperty(Property p) {
        return p.getFlattenedNames() == null || p.getFlattenedNames().isEmpty();
    }

    public static boolean nonFlattenedParameter(Parameter p) {
        return !p.isFlattened();
    }

    public static <T> void shallowCopy(T obj, T newObj, Class clazz, Logger logger) {
        while (clazz != Object.class) {
            Field[] fields = clazz.getDeclaredFields();
            for (Field f : fields) {
                try {
                    Field t = clazz.getDeclaredField(f.getName());

                    if (t.getType() == f.getType()) {
                        f.setAccessible(true);
                        t.setAccessible(true);
                        t.set(newObj, f.get(obj));
                    }
                } catch (NoSuchFieldException ex) {
                    // skip it
                } catch (IllegalAccessException ex) {
                    logger.error("Failed to copy field '{}'", f.getName());
                }
            }

            clazz = clazz.getSuperclass();
        }
    }

    public static String getNameForUngroupedOperations(Client client, FluentJavaSettings settings) {
        String nameForUngroupOperations = null;
        if (settings.getNameForUngroupedOperations().isPresent()) {
            nameForUngroupOperations = settings.getNameForUngroupedOperations().get();
        } else if (JavaSettings.getInstance().isFluentLite()) {
            nameForUngroupOperations = Constants.DEFAULT_NAME_FOR_UNGROUPED_OPERATIONS;

            Set<String> operationGroupNames = client.getOperationGroups().stream()
                .map(Utils::getDefaultName)
                .collect(Collectors.toSet());
            if (operationGroupNames.contains(nameForUngroupOperations)) {
                nameForUngroupOperations += Constants.OPERATION_GROUP_DEDUPLICATE_SUFFIX;
            }
        }
        return nameForUngroupOperations;
    }


    public static String getSingular(String name) {
        if (name == null) {
            return null;
        }

        if (name.endsWith("ies")) {
            return name.substring(0, name.length() - 3) + 'y';
        } else if (name.endsWith("sses")) {
            return name.substring(0, name.length() - 2);
        } else if (name.endsWith("s") && !name.endsWith("ss")) {
            return name.substring(0, name.length() - 1);
        } else {
            return name;
        }
    }
}
