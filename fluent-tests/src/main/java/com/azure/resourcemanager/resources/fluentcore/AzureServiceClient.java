/*
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for license information.
 */

package com.azure.resourcemanager.resources.fluentcore;

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.rest.Response;
import com.azure.core.management.AzureEnvironment;
import com.azure.core.management.exception.ManagementError;
import com.azure.core.management.exception.ManagementException;
import com.azure.core.management.polling.PollerFactory;
import com.azure.core.management.polling.PollResult;
import com.azure.core.management.serializer.AzureJacksonAdapter;
import com.azure.core.util.Context;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.polling.AsyncPollResponse;
import com.azure.core.util.polling.LongRunningOperationStatus;
import com.azure.core.util.polling.PollerFlux;
import com.azure.core.util.serializer.SerializerAdapter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.Type;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.Map;

/**
 * ServiceClient is the abstraction for accessing REST operations and their payload data types.
 */
public abstract class AzureServiceClient {

    protected AzureServiceClient(HttpPipeline httpPipeline, AzureEnvironment environment) {

    }

    private static final String OS;
    private static final String OS_NAME;
    private static final String OS_VERSION;
    private static final String JAVA_VERSION;
    private static final String SDK_VERSION = "2.0.0";

    static {
        OS_NAME = System.getProperty("os.name");
        OS_VERSION = System.getProperty("os.version");
        OS = OS_NAME + "/" + OS_VERSION;
        String version = System.getProperty("java.version");
        JAVA_VERSION = version != null ? version : "Unknown";
    }

    private SerializerAdapter serializerAdapter = new AzureJacksonAdapter();

    private String sdkName;

    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /**
     * Gets default client context.
     *
     * @return the default client context.
     */
    public Context getContext() {
        Context context = new Context("java.version", JAVA_VERSION);
        if (!CoreUtils.isNullOrEmpty(OS_NAME)) {
            context = context.addData("os.name", OS_NAME);
        }
        if (!CoreUtils.isNullOrEmpty(OS_VERSION)) {
            context = context.addData("os.version", OS_VERSION);
        }
        if (sdkName == null) {
            String packageName = this.getClass().getPackage().getName();
            if (packageName.endsWith(".models")) {
                sdkName = packageName.substring(0, packageName.length() - ".models".length());
            }
        }
        context = context.addData("Sdk-Name", sdkName);
        context = context.addData("Sdk-Version", SDK_VERSION);
        return context;
    }

    /**
     * Merges default client context with provided context.
     *
     * @param context the context to be merged with default client context.
     * @return the merged context.
     */
    public Context mergeContext(Context context) {
        for (Map.Entry<Object, Object> entry : this.getContext().getValues().entrySet()) {
            context = context.addData(entry.getKey(), entry.getValue());
        }
        return context;
    }

    /**
     * Gets long running operation result.
     *
     * @param lroInit the raw response of init operation.
     * @param httpPipeline the http pipeline.
     * @param pollResultType type of poll result.
     * @param finalResultType type of final result.
     * @param <T> type of poll result.
     * @param <U> type of final result.
     * @return poller flux for poll result and final result.
     */
    public <T, U> PollerFlux<PollResult<T>, U> getLroResultAsync(Mono<Response<Flux<ByteBuffer>>> lroInit,
                                                                 HttpPipeline httpPipeline,
                                                                 Type pollResultType, Type finalResultType) {
        return PollerFactory.create(
                getSerializerAdapter(),
                httpPipeline,
                pollResultType,
                finalResultType,
                Duration.ofSeconds(30),
                lroInit);
    }

    /**
     * Gets the final result, or an error, based on last async poll response.
     *
     * @param response the last async poll response.
     * @param <T> type of poll result.
     * @param <U> type of final result.
     * @return the final result, or an error.
     */
    public <T, U> Mono<U> getLroFinalResultOrError(AsyncPollResponse<PollResult<T>, U> response) {
        if (response.getStatus() != LongRunningOperationStatus.SUCCESSFULLY_COMPLETED) {
            String errorMessage = response.getValue().getError() != null
                    ? response.getValue().getError().getMessage()
                    : "Unknown error";
            return Mono.error(new ManagementException(errorMessage, null,
                    new ManagementError(response.getStatus().toString(), errorMessage)));
        } else {
            return response.getFinalResult();
        }
    }
}
