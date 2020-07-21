/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.template.ServiceClientTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.core.util.Context;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class FluentServiceClientTemplate extends ServiceClientTemplate {

    private static FluentServiceClientTemplate _instance = new FluentServiceClientTemplate();
    static {
        MethodTemplate getContextMethod = MethodTemplate.builder()
                .imports(Collections.singleton(Context.class.getName()))
                .methodSignature("Context getContext()")
                .comment(comment -> {
                    comment.description("Gets default client context.");
                    comment.methodReturns("the default client context.");
                })
                .method(method -> {
                    method.methodReturn("Context.NONE");
                })
                .build();

        MethodTemplate mergeContextMethod = MethodTemplate.builder()
                .imports(Arrays.asList(Context.class.getName(), Map.class.getName()))
                .methodSignature("Context mergeContext(Context context)")
                .comment(comment -> {
                    comment.description("Merges default client context with provided context.");
                    comment.param("context", "the context to be merged with default client context.");
                    comment.methodReturns("the merged context.");
                })
                .method(method -> {
                    method.block("for (Map.Entry<Object, Object> entry : this.getContext().getValues().entrySet())", block -> {
                        block.line("context = context.addData(entry.getKey(), entry.getValue());");
                    });
                    method.methodReturn("context");
                })
                .build();

        _instance.additionalMethods.add(getContextMethod);
        _instance.additionalMethods.add(mergeContextMethod);
    }

    public static FluentServiceClientTemplate getInstance() {
        return _instance;
    }
}
