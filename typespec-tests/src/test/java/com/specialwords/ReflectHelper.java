// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.specialwords;

import com.azure.core.http.rest.RequestOptions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class ReflectHelper {

    public static <T> void invokeWithResponseMethods(Class<T> clazz, Object client, Object parameter) throws InvocationTargetException, IllegalAccessException {
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().endsWith("WithResponse")) {
                if (parameter != null) {
                    m.invoke(client, parameter, (RequestOptions) null);
                } else {
                    m.invoke(client, (RequestOptions) null);
                }
            }
        }
    }
}
