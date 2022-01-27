// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.model.codemodel.Metadata;
import com.azure.autorest.extension.base.model.codemodel.Parameter;
import com.azure.autorest.extension.base.model.codemodel.Property;
import org.slf4j.Logger;

import java.lang.reflect.Field;

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
}
