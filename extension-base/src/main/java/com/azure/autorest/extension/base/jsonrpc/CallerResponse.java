// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.azure.autorest.extension.base.jsonrpc;

import com.fasterxml.jackson.databind.JavaType;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public class CallerResponse<T> extends CompletableFuture<T> {
    public int id;
    public JavaType type;

    public CallerResponse(int id, JavaType type) {
        this.id = id;
        this.type = type;
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
