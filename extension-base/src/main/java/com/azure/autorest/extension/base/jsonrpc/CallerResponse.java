// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.jsonrpc;

import com.fasterxml.jackson.databind.JavaType;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Represents a response from a remote procedure call.
 *
 * @param <T> The type of the result of the remote procedure call.
 */
public class CallerResponse<T> extends CompletableFuture<T> {
    private final int id;
    private final JavaType type;

    /**
     * Creates a new CallerResponse.
     *
     * @param id The id of the remote procedure call.
     * @param type The type of the result of the remote procedure call.
     */
    public CallerResponse(int id, JavaType type) {
        this.id = id;
        this.type = type;
    }

    /**
     * Gets the id of the remote procedure call.
     *
     * @return The id of the remote procedure call.
     */
    public int getId() {
        return id;
    }

    /**
     * Gets the type of the result of the remote procedure call.
     *
     * @return The type of the result of the remote procedure call.
     */
    public JavaType getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean complete(Object result) {
        T value;
        Function<Object, Boolean> trueLikeValue = obj -> obj == null ? null : (!obj.equals(0) && !obj.equals(false) && !obj.equals(""));
        if (type.isTypeOrSubTypeOf(Boolean.class)) {
            value = (T) (trueLikeValue.apply(result));
        } else {
            value = (T) result;
        }
        return super.complete(value);
    }

    @Override
    public boolean completeExceptionally(Throwable error) {
        return super.completeExceptionally(error);
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return super.cancel(mayInterruptIfRunning);
    }
}
