// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.model.codemodel.CodeModel;
import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import com.azure.autorest.extension.base.plugin.JavaSettings;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.stream.Collectors;

public class Utils {

    public final static String METHOD_POSTFIX_WITH_RESPONSE = "WithResponse";

    public static String getDefaultName(Metadata m) {
        if (m.getLanguage() == null || m.getLanguage().getDefault() == null) {
            return null;
        }
        return m.getLanguage().getDefault().getName();
    }

    public static String getJavaName(Metadata m) {
        if (m.getLanguage() == null || m.getLanguage().getJava() == null) {
            return null;
        }
        return m.getLanguage().getJava().getName();
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

    public static String getNameForUngroupedOperations(CodeModel codeModel, FluentJavaSettings settings) {
        String nameForUngroupOperations = null;
        if (settings.getNameForUngroupedOperations().isPresent()) {
            nameForUngroupOperations = settings.getNameForUngroupedOperations().get();
        } else if (JavaSettings.getInstance().isFluentLite()) {
            nameForUngroupOperations = FluentConsts.DEFAULT_NAME_FOR_UNGROUPED_OPERATIONS;

            Set<String> operationGroupNames = codeModel.getOperationGroups().stream()
                .map(Utils::getDefaultName)
                .collect(Collectors.toSet());
            if (operationGroupNames.contains(nameForUngroupOperations)) {
                nameForUngroupOperations += FluentConsts.ANTI_CONFLICT_SUFFIX_FOR_UNGROUPED_OPERATIONS;
            }
        }
        return nameForUngroupOperations;
    }
}
