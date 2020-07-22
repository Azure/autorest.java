/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.autorest.fluent.template;

import com.azure.autorest.extension.base.plugin.JavaSettings;
import com.azure.autorest.template.ServiceClientTemplate;
import com.azure.autorest.template.prototype.MethodTemplate;
import com.azure.core.http.rest.Response;
import com.azure.core.management.polling.PollResult;
import com.azure.core.management.polling.PollerFactory;
import com.azure.core.util.Context;
import com.azure.core.util.polling.PollerFlux;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

public class FluentServiceClientTemplate extends ServiceClientTemplate {

    private static final FluentServiceClientTemplate instance = new FluentServiceClientTemplate();
    static {
        if (JavaSettings.getInstance().isFluent()) {
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

            MethodTemplate getLroResultAsyncMethod = MethodTemplate.builder()
                    .imports(Arrays.asList(PollerFlux.class.getName(), PollResult.class.getName(),
                            Mono.class.getName(), Flux.class.getName(), Response.class.getName(),
                            ByteBuffer.class.getName(), Type.class.getName(),
                            PollerFactory.class.getName()))
                    .methodSignature("<T, U> PollerFlux<PollResult<T>, U> getLroResultAsync(Mono<Response<Flux<ByteBuffer>>> activationResponse, HttpPipeline httpPipeline, Type pollResultType, Type finalResultType)")
                    .comment(comment -> {
                        comment.description("Gets long running operation result.");
                        comment.param("activationResponse", "the response of activation operation.");
                        comment.param("httpPipeline", "the http pipeline.");
                        comment.param("pollResultType", "type of poll result.");
                        comment.param("finalResultType", "type of final result.");
                        comment.param("<T>", "type of poll result.");
                        comment.param("<U>", "type of final result.");
                        comment.methodReturns("poller flux for poll result and final result.");
                    })
                    .method(method -> {
                        method.methodReturn("PollerFactory.create(serializerAdapter, httpPipeline, pollResultType, finalResultType, defaultPollInterval, activationResponse)");
                    })
                    .build();

            instance.additionalMethods.add(getContextMethod);
            instance.additionalMethods.add(mergeContextMethod);
            instance.additionalMethods.add(getLroResultAsyncMethod);
        }
    }

    public static FluentServiceClientTemplate getInstance() {
        return instance;
    }
}
