/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 *
 */

package com.azure.autorest.fluent.util;

import com.azure.autorest.extension.base.model.codemodel.Metadata;
import org.slf4j.Logger;

import java.lang.reflect.Field;

public class Utils {

    public static String getDefaultName(Metadata m) {
        return m.getLanguage().getDefault().getName();
    }

    public static String getJavaName(Metadata m) {
        return m.getLanguage().getJava().getName();
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
                    logger.error("Failed to copy field: {}", f.getName());
                }
            }

            clazz = clazz.getSuperclass();
        }
    }
}
